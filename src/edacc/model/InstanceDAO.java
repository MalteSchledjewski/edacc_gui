package edacc.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Hashtable;
import java.sql.*;

/**
 * data access object for the Instance class
 * @author daniel
 */
public class InstanceDAO {
    protected static final String table = "Instances";
    private static final Hashtable<Instance, Instance> cache = new Hashtable<Instance, Instance>();
    /**
     * Instance factory method, ensures that the created instance is persisted and assigned an ID
     * so it can be referenced by related objects. Checks if the instance is already in the Datebase.
     * @param md5
     * @return new Instance object
     * @throws SQLException, FileNotFoundException, InstanceAlreadyInDBException
     */
     public static Instance createInstance(File file, String name, int numAtoms, int numClauses ,
             float ratio, int maxClauseLength, String md5) throws SQLException, FileNotFoundException,
             InstanceAlreadyInDBException {
         PreparedStatement ps;
         final String Query = "SELECT * FROM " + table +" WHERE md5 = ?";
         ps = DatabaseConnector.getInstance().getConn().prepareStatement(Query);
         ps.setString(1, md5);
         ResultSet rs = ps.executeQuery();
         if(rs.next()){
            throw new InstanceAlreadyInDBException();
         }
         Instance i = new Instance();
        i.setFile(file);
        i.setName(name);
        i.setNumAtoms(numAtoms);
        i.setNumClauses(numClauses);
        i.setRatio(ratio);
        i.setMaxClauseLength(maxClauseLength);
        i.setMd5(md5);
        save(i);
        cacheInstance(i);
        return i;
     }



     public static void delete(Instance i) throws NoConnectionToDBException, SQLException, InstanceIsInExperimentException {
         if(!IsInAnyExperiment(i.getId())){
             PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement("DELETE FROM Instances WHERE idInstance=?");
             ps.setInt(1, i.getId());
             ps.executeUpdate();
             cache.remove(i);
             i.setDeleted();
         } else throw new InstanceIsInExperimentException();

     }

    /**
     * persists an instance object in the database
     * @param instance The instance object to persist
     * @throws SQLException if an SQL error occurs while saving the instance.
     * @throws FileNotFoundException if the file of the instance couldn't be found.
     */
    public static void save(Instance instance) throws SQLException, FileNotFoundException {
        PreparedStatement ps;
        if (instance.isNew()) {
            // insert query, set ID!
            // TODO insert instance blob
            // insert instance into db
            final String insertQuery = "INSERT INTO " + table + " (name, md5, numAtoms, numClauses, ratio, maxClauseLength, instance) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = DatabaseConnector.getInstance().getConn().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        }
        else if (instance.isModified()) {
            // update query
            final String updateQuery = "UPDATE " + table + " SET name=?, md5=?, numAtoms=?, numClauses=?, ratio=?, maxClauseLength=?, instance=? " +
                    "WHERE idInstance=?";
            ps = DatabaseConnector.getInstance().getConn().prepareStatement(updateQuery);
           
            ps.setInt(8, instance.getId());
            
        } else
            return;

        ps.setString(1, instance.getName());
        ps.setString(2, instance.getMd5());
        ps.setInt(3, instance.getNumAtoms());
        ps.setInt(4, instance.getNumClauses());
        ps.setFloat(5, instance.getRatio());
        ps.setInt(6, instance.getMaxClauseLength());
        ps.setBinaryStream(7, new FileInputStream(instance.getFile()));
        ps.executeUpdate();

        // set id
        if (instance.isNew()) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                instance.setId(rs.getInt(1));
        }

        instance.setSaved();
    }

    private static Instance getCached(Instance i) {
        if (cache.containsKey(i)) {
            return cache.get(i);
        }
        else return null;
    }

    private static void cacheInstance(Instance i) {
        if (cache.containsKey(i)) return;
        else cache.put(i, i);
    }

    /**
     * retrieves an instance from the database
     * @param id the id of the instance to be retrieved
     * @return the instance specified by its id
     * @throws SQLException
     */
    public static Instance getById(int id) throws SQLException {
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT idInstance, maxClauseLength, md5, name, numAtoms, numClauses, ratio FROM " + table + " WHERE idInstance=?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        Instance i = new Instance();
        if (rs.next()) {
            i.setId(rs.getInt("idInstance"));
            i.setMaxClauseLength(rs.getInt("maxClauseLength"));
            i.setMd5(rs.getString("md5"));
            i.setName(rs.getString("name"));
            i.setNumAtoms(rs.getInt("numAtoms"));
            i.setNumClauses(rs.getInt("numClauses"));
            i.setRatio(rs.getInt("ratio"));

            Instance c = getCached(i);
            if (c != null) return c;
            else {
                i.setSaved();
                cacheInstance(i);
                return i;
            }
        }
        return null;
    }

    /**
     * retrieves all instances from the database
     * @return all instances in a List
     * @throws SQLException
     */
    public static LinkedList<Instance> getAll() throws SQLException {
        // return linked list with all instances
        Statement st = DatabaseConnector.getInstance().getConn().createStatement();
        ResultSet rs = st.executeQuery("SELECT idInstance, maxClauseLength, md5, name, numAtoms, numClauses, ratio FROM " + table);
        LinkedList<Instance> res = new LinkedList<Instance>();
        while (rs.next()) {
            Instance i = new Instance();
            i.setId(rs.getInt("idInstance"));
            i.setMaxClauseLength(rs.getInt("maxClauseLength"));
            i.setMd5(rs.getString("md5"));
            i.setName(rs.getString("name"));
            i.setNumAtoms(rs.getInt("numAtoms"));
            i.setNumClauses(rs.getInt("numClauses"));
            i.setRatio(rs.getInt("ratio"));
            
            Instance c = getCached(i);
            if (c != null) res.add(c);
            else {
                i.setSaved();
                cacheInstance(i);
                res.add(i);
            }
        }
        rs.close();
        return res;
    }

    public static LinkedList<Instance> getAllByExperimentId(int id) throws SQLException {
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(
                "SELECT i.idInstance, i.maxClauseLength, i.md5, i.name, i.numAtoms, i.numClauses, i.ratio FROM " + table + " as i JOIN Experiment_has_Instances as ei ON " +
                "i.idInstance = ei.Instances_idInstance WHERE ei.Experiment_idExperiment = ?"
                );
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        LinkedList<Instance> res = new LinkedList<Instance>();
        while (rs.next()) {
            Instance i = new Instance();
            i.setId(rs.getInt("i.idInstance"));
            i.setMaxClauseLength(rs.getInt("i.maxClauseLength"));
            i.setMd5(rs.getString("i.md5"));
            i.setName(rs.getString("i.name"));
            i.setNumAtoms(rs.getInt("i.numAtoms"));
            i.setNumClauses(rs.getInt("i.numClauses"));
            i.setRatio(rs.getInt("i.ratio"));

            Instance c = getCached(i);
            if (c != null) res.add(c);
            else {
                i.setSaved();
                cacheInstance(i);
                res.add(i);
            }
        }
        rs.close();
        return res;
    }

    /**
     * @author rretz
     * retrieves instances from the database.
     * @return Hashtable with all instances which belong to a experiment.
     * @throws NoConnectionToDBException if no connection to database exists.
     * @throws SQLException if an SQL error occurs while reading the instances from the database.
     */
    public static boolean IsInAnyExperiment(int id) throws NoConnectionToDBException, SQLException{
        Statement st = DatabaseConnector.getInstance().getConn().createStatement();
                
        ResultSet rs = st.executeQuery("SELECT i.idInstance FROM instances as i JOIN Experiment_has_Instances as ei ON " +
                "i.idInstance = ei.Instances_idInstance WHERE idInstance = "+ id);
       if(rs.next()) return true;
       else return false;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DatabaseConnector db = DatabaseConnector.getInstance();
        db.connect("localhost", 3306, "root", "EDACC", "sopra");
        Instance i = new Instance();
        i.setName("test1234");
        i.setNew();
        i.setMd5("12345");
       // save(i);
        cacheInstance(i);
        System.out.println(i.getId());
        db.getConn().close();
    }
}
