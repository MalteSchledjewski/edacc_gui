package edacc.model;

import java.util.LinkedList;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class ExperimentDAO {

    protected static final String table = "Experiment";
    protected static final String insertQuery = "INSERT INTO " + table + " (Name, Date, description, numRuns, timeOut, memOut, maxSeed, autoGeneratedSeeds, linkSeeds) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected static final String updateQuery = "UPDATE "+table+" SET Name =?, Date =?, description =?, numRuns =?, timeOut =?, memOut =?, maxSeed =?, autoGeneratedSeeds =?, linkSeeds =? WHERE idExperiment=?";
    protected static final String deleteQuery = "DELETE FROM "+table+" WHERE idExperiment=?";
    private static final ObjectCache<Experiment> cache = new ObjectCache<Experiment>();

    /**
     * Experiment factory method, ensures that the created experiment is persisted and assigned an ID
     * so it can be referenced by related objects
     * @return new Experiment object
     */
    public static Experiment createExperiment(String name, Date date, String description) throws SQLException, Exception {
        if (getExperimentByName(name) != null) {
            throw new Exception("There exists already an experiment with the same name.");
        }
        Experiment i = new Experiment();
        i.setName(name);
        i.setDescription(description);
        i.setDate(date);
        save(i);
        cache.cache(i);
        return i;
    }

    public static Experiment getExperimentByName(String name) throws SQLException {
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT * FROM " + table + " WHERE name=?");
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Experiment i = getExperimentFromResultset(rs);
            i.setSaved();
            cache.cache(i);
            return i;
        }
        rs.close();
        st.close();
        return null;
    }

    /**
     * persists an Experiment object in the database
     * @param experiment The Experiment object to persist
     */
    public static void save(Experiment experiment) throws SQLException {
        PreparedStatement st = null;
        if (experiment.isNew()) {
            st = DatabaseConnector.getInstance().getConn().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        } else if (experiment.isModified()) {
            st = DatabaseConnector.getInstance().getConn().prepareStatement(updateQuery);
            st.setInt(10, experiment.getId());
        } else {
            return;
        }
        st.setString(1, experiment.getName());
        st.setDate(2, experiment.getDate());
        st.setString(3, experiment.getDescription());
        st.setInt(4, 0);                            // only for compatibility (numRuns -> old)
        st.setInt(5, experiment.getTimeOut());
        st.setInt(6, experiment.getMemOut());
        st.setInt(7, experiment.getMaxSeed());
        st.setBoolean(8, experiment.isAutoGeneratedSeeds());
        st.setBoolean(9, experiment.isLinkSeeds());
        st.executeUpdate();

        if (experiment.isNew()) {
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                experiment.setId(generatedKeys.getInt(1));
            }

            cache.cache(experiment);
        }
        experiment.setSaved();
        st.close();
    }
    /**
     * removes an experiment from the database
     * @param experiment
     * @throws SQLException
     */
    public static void removeExperiment(Experiment experiment) throws SQLException {
        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement(deleteQuery);
        st.setInt(1, experiment.getId());
        st.executeUpdate();
        st.close();
        cache.remove(experiment);
        experiment.setDeleted();
    }

    private static Experiment getExperimentFromResultset(ResultSet rs) throws SQLException {
        Experiment i = new Experiment();
        i.setId(rs.getInt("idExperiment"));
        i.setName(rs.getString("Name"));
        i.setDate(rs.getDate("Date"));
        i.setDescription(rs.getString("description"));
        //i.setNumRuns(rs.getInt("numRuns"));
        i.setTimeOut(rs.getInt("timeOut"));
        i.setMemOut(rs.getInt("memOut"));
        i.setMaxSeed(rs.getInt("maxSeed"));
        i.setAutoGeneratedSeeds(rs.getBoolean("autoGeneratedSeeds"));
        i.setLinkSeeds(rs.getBoolean("linkSeeds"));
        updateNumRuns(i);
        return i;
    }

    private static Vector<SolverConfiguration> getExperimentSolverConfiguration(Experiment i) throws SQLException {
        return SolverConfigurationDAO.getSolverConfigurationByExperimentId(i.getId());
    }

    /**
     * retrieves an experiment from the database
     * @param id the id of the experiment to be retrieved
     * @return the experiment specified by its id
     * @throws SQLException
     */
    public static Experiment getById(int id) throws SQLException {
        Experiment c = cache.getCached(id);
        if (c != null) return c;

        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT * FROM " + table + " WHERE idExperiment=?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Experiment i = getExperimentFromResultset(rs);
            i.setSaved();
            cache.cache(i);
            return i;
        }
        rs.close();
        st.close();
        return null;
    }

    /**
     * retrieves all experiments from the database
     * @return all experiments in a List
     * @throws SQLException
     */
    public static LinkedList<Experiment> getAll() throws SQLException {
        Statement st = DatabaseConnector.getInstance().getConn().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + table);
        LinkedList<Experiment> res = new LinkedList<Experiment>();
        while (rs.next()) {
            int id = rs.getInt("idExperiment");
            Experiment c = cache.getCached(id);
            if (c != null) {
                res.add(c);
            }
            else {
                Experiment i = getExperimentFromResultset(rs);
                i.setSaved();
                cache.cache(i);
                res.add(i);
            }
        }
        rs.close();
        st.close();
        return res;
    }

    public static void setModified(Experiment e) {
        e.setModified();
    }

    /**
     * returns all solvers used in an experiment.
     * @param e
     * @return
     * @throws NoConnectionToDBException
     * @throws SQLException
     */
    public static Vector<Solver> getSolversInExperiment(Experiment e) throws NoConnectionToDBException, SQLException {
        final String query = "SELECT DISTINCT Solver_idSolver FROM SolverConfig WHERE Experiment_idExperiment=?";
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(query);
        ps.setInt(1, e.getId());
        ResultSet rs = ps.executeQuery();
        Vector<Solver> solvers = new Vector<Solver>();
        while (rs.next()) {
            int id = rs.getInt("Solver_idSolver");
            Solver s = SolverDAO.getById(id);
            solvers.add(s);
        }
        rs.close();
        ps.close();
        return solvers;
    }

    public static void updateNumRuns(Experiment exp) {
        try {
            final String query = "SELECT MAX(run)+1 from ExperimentResults where Experiment_idExperiment=?";
            PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(query);
            ps.setInt(1, exp.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exp.setNumRuns(rs.getInt(1));
            } else {
                exp.setNumRuns(0);
            }


            
        } catch (SQLException ex) {
            exp.setNumRuns(0);
        }
    }

    public static void clearCache() {
        cache.clear();
    }
}
