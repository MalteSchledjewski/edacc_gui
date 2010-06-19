package edacc.model;

import edacc.manageDB.NoSolverBinarySpecifiedException;
import edacc.manageDB.NoSolverNameSpecifiedException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Hashtable;
import java.util.LinkedList;
import edacc.manageDB.Util;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author simon
 */
public class SolverDAO {

    protected static final String table = "Solver";
    protected static final String insertQuery = "INSERT INTO " + table + " (`name`, `binaryName`, `binary`, `description`, `md5`, `code`) VALUES (?, ?, ?, ?, ?, ?)";
    protected static final String updateQueryBin = "UPDATE " + table + " SET `binaryName`=?, `binary`=? WHERE `idSolver`=?";
    protected static final String updateQueryCode = "UPDATE " + table + " SET `code`=? WHERE `idSolver`=?";
    protected static final String updateQuery = "UPDATE " + table + " SET `name`=?, `description`=?, `md5`=? WHERE `idSolver`=?";
    protected static final String removeQuery = "DELETE FROM " + table + " WHERE idSolver=?";
    private static final ObjectCache<Solver> cache = new ObjectCache<Solver>();

    /**
     * persists a solver to database and assigns an id. it also ensures that
     * the solver is cached.
     * @param solver The Solver object to persist.
     */
    public static void save(Solver solver) throws SQLException, FileNotFoundException, NoSolverBinarySpecifiedException, NoSolverNameSpecifiedException,  IOException {
        //if (solver.isSaved()) {
        //    return;
        //}

        // new solvers without binary aren't allowed
        if (solver.isNew() && solver.getName().isEmpty())
            throw new NoSolverNameSpecifiedException();
        if (solver.isNew() && solver.getBinaryFile() == null)
            throw new NoSolverBinarySpecifiedException();

        PreparedStatement ps;

        boolean alreadyInDB = solverAlreadyInDB(solver) != null;

        // insert  into db
        if (solver.isNew() && !alreadyInDB) {
            ps = DatabaseConnector.getInstance().getConn().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, solver.getName());
            ps.setString(2, solver.getBinaryName());
            ps.setBinaryStream(3, new FileInputStream(solver.getBinaryFile()));
            ps.setString(4, solver.getDescription());
            ps.setString(5, solver.getMd5());
            if (solver.getCodeFile() != null && solver.getCodeFile().length > 0) {
                // zip up directory
                ByteArrayOutputStream zipped = Util.zipFileArrayToByteStream(solver.getCodeFile());
                ps.setBinaryStream(6, new ByteArrayInputStream(zipped.toByteArray()));
            }
            else
                ps.setNull(6, Types.BLOB);
            ps.executeUpdate();
        } else {
            // if solver already in DB, then update it
            // first update the basic data
            ps = DatabaseConnector.getInstance().getConn().prepareStatement(updateQuery);
            ps.setString(1, solver.getName());
            ps.setString(2, solver.getDescription());
            ps.setString(3, solver.getMd5());
            ps.setInt(4, solver.getId());
            ps.executeUpdate();
            // then update the solver binary if necessary
            if (solver.getBinaryFile() != null) { // if binary is null, don't update the solver binary
                ps = DatabaseConnector.getInstance().getConn().prepareStatement(updateQueryBin);
                ps.setString(1, solver.getBinaryName());
                ps.setBinaryStream(2, new FileInputStream(solver.getBinaryFile()));
                ps.setInt(3, solver.getId());
                ps.executeUpdate();
            }

            // update the code if necessary
            if (solver.getCodeFile() != null) { // if code is null, don't update the code (at the moment code can't be deleted)
                ps = DatabaseConnector.getInstance().getConn().prepareStatement(updateQueryCode);
                // zip up directory
                ByteArrayOutputStream zipped = Util.zipFileArrayToByteStream(solver.getCodeFile());
                ps.setBinaryStream(1, new ByteArrayInputStream(zipped.toByteArray()));
                ps.setInt(2, solver.getId());
                ps.executeUpdate();
            }
        }

        // set id if solver is new (id is assigned by the db automatically)
        if (solver.isNew() && !alreadyInDB) {
            // set id
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                solver.setId(rs.getInt(1));
            }
        } else if (alreadyInDB) {
            solver.setId(solverAlreadyInDB(solver).getId());
        }

        cache.cache(solver);
        solver.setSaved();
    }

    /**
     * Removes a solver from DB and cache. It also ensures that all parameters of a solver are deleted.
     * TODO delete SolverCOnfigs??
     * @param solver the solver to remove.
     * @throws SQLException if an error occurs while executing the SQL query.
     * @throws SolverIsInExperimentException if the solver is used in an experiment. In this case you have to remove the experiment first.
     * @throws SolverNotInDBException if the solver is not persisted in the db. In this case, the object will be marked as "deleted" but nothing will be done to the cache or db.
     */
    public static void removeSolver(Solver solver) throws SolverIsInExperimentException, SQLException, SolverNotInDBException {
        if (solver.isNew()) {
            solver.setDeleted();
            throw new SolverNotInDBException(solver);
        }

        // don't remove solver if it is used in an experiment
        if (isInExperiment(solver)) {
            throw new SolverIsInExperimentException(solver);
        }

        // remove also the parameters of the solver
        ParameterDAO.removeParametersOfSolver(solver);

        // now remove the solver from the db
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(removeQuery);
        ps.setInt(1, solver.getId());
        ps.executeUpdate();

        // finally remove it from the cache if cached
        cache.remove(solver);

        // mark the object as deleted
        solver.setDeleted();
    }

    private static Solver getSolverFromResultset(ResultSet rs) throws SQLException {
        Solver i = new Solver();
        i.setId(rs.getInt("idSolver"));
        i.setName(rs.getString("name"));
        i.setBinaryName(rs.getString("binaryName"));
        i.setDescription(rs.getString("description"));
        i.setMd5(rs.getString("md5"));
        return i;
    }
    
    /**
     * retrieves an solver from the database
     * @param id the id of the solver to be retrieved
     * @return the solver specified by its id
     * @throws SQLException
     */
    public static Solver getById(int id) throws SQLException {
        Solver c = cache.getCached(id);
        if (c != null) return c;

        PreparedStatement st = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT * FROM " + table + " WHERE idSolver=?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Solver i = getSolverFromResultset(rs);

            i.setSaved();
            cache.cache(i);
            return i;
        }
        return null;
    }

    /**
     * retrieves all solvers from the database
     * @return all solvers in a List
     * @throws SQLException
     */
    public static LinkedList<Solver> getAll() throws SQLException {
        Statement st = DatabaseConnector.getInstance().getConn().createStatement();
        ResultSet rs = st.executeQuery("SELECT idSolver, name, binaryName, description, md5 FROM " + table);
        LinkedList<Solver> res = new LinkedList<Solver>();
        while (rs.next()) {
            Solver c = cache.getCached(rs.getInt("idSolver"));
            if (c != null) res.add(c);
            else {
                Solver i = getSolverFromResultset(rs);
                i.setSaved();
                cache.cache(i);
                res.add(i);
            }

        }
        rs.close();
        return res;
    }

    /**
     * Tests if a solver is already in DB (by MD5) and returns the cached object or
     * null if solver isn't in DB.
     * @param s
     * @return
     * @throws NoConnectionToDBException
     * @throws SQLException
     */
    public static Solver solverAlreadyInDB(Solver s) throws NoConnectionToDBException, SQLException {
        PreparedStatement ps;
        final String Query = "SELECT idSolver, name, binaryName, description, md5 FROM " + table + " WHERE md5 = ?";
        ps = DatabaseConnector.getInstance().getConn().prepareStatement(Query);
        ps.setString(1, s.getMd5());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Solver c = cache.getCached(rs.getInt("idSolver"));
            if (c != null) {
                return c;
            }
            else {
                Solver i = getSolverFromResultset(rs);
                i.setSaved();
                cache.cache(i);
                return i;
            }
        }
        return null;
    }

    private static boolean isInExperiment(Solver solver) throws NoConnectionToDBException, SQLException {
        Statement st = DatabaseConnector.getInstance().getConn().createStatement();

        ResultSet rs = st.executeQuery("SELECT s.idSolver FROM " + table + " AS s JOIN SolverConfig as sc ON "
                + "s.idSolver = sc.Solver_idSolver WHERE idSolver = " + solver.getId());
        return rs.next();
    }

    /**
     * Copies the binary file of a solver to a specified location on the file system.
     * @param s
     * @param f the file where the binary shall be stored
     * @return
     */
    public static void getBinaryFileOfSolver(Solver s, File f) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT `binary` FROM " + table + " WHERE idSolver=?");
        ps.setInt(1, s.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            FileOutputStream out = new FileOutputStream(f);
            InputStream in = rs.getBinaryStream("binary");
            int len = 0;
            byte[] buffer = new byte[256*1024];
            while ((len = in.read(buffer)) > -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        }
    }

    /**
     * Copies the binary file of a solver to a temporary location on the file system
     * and returns a File reference on it.
     * @param s
     * @return
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static File getBinaryFileOfSolver(Solver s) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        File f = new File("tmp" + System.getProperty("file.separator") + s.getBinaryName());
        // create missing direcotries
        f.getParentFile().mkdirs();
        getBinaryFileOfSolver(s, f);
        return f;
    }

    /**
     * Exports the code of the solver s to the directory specified by f
     * @param s solver
     * @param f File referencing a directory on the filesystem
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void exportSolverCode(Solver s, File f) throws NoConnectionToDBException, SQLException, FileNotFoundException, IOException {
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement("SELECT `code` FROM " + table + " WHERE idSolver=?");
        ps.setInt(1, s.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            InputStream in = rs.getBinaryStream("code");
            if (in == null) return;

            // open temporary file to write the zip file to
            new File("tmp").mkdir();
            File tmp = new File("tmp" + System.getProperty("file.separator") + s.getId() + ".zip.tmp");
            FileOutputStream out = new FileOutputStream(tmp);
            
            
            byte[] buffer = new byte[8192];
            int read;
            while (-1 != (read = in.read(buffer))) {
                out.write(buffer, 0, read);
            }
            out.close();
            in.close();

            Util.unzip(tmp, f);
            tmp.delete(); // delete temporary file
        }
        rs.close();
    }

    public static void clearCache() {
        cache.clear();
    }
}
