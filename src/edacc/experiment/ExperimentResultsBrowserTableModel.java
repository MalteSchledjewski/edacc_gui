/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edacc.experiment;

import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import edacc.model.ExperimentResult;
import edacc.model.Instance;
import edacc.model.InstanceDAO;
import edacc.model.Parameter;
import edacc.model.ParameterDAO;
import edacc.model.ParameterInstance;
import edacc.model.ParameterInstanceDAO;
import edacc.model.Solver;
import edacc.model.SolverConfiguration;
import edacc.model.SolverConfigurationDAO;
import edacc.model.SolverDAO;
import java.util.HashMap;

/**
 * In this class rowIndexes are always the visible rowIndexes and columnIndexes
 * are always the visible column indexes.
 * @author daniel
 */
public class ExperimentResultsBrowserTableModel extends AbstractTableModel {

    private Vector<ExperimentResult> jobs;
    private String[] columns = {"ID", "Solver", "Parameters", "Instance", "Run", "Result File", "Time", "Seed", "Status code"};
    private boolean[] visible = {true, true, true, true, true, true, true, true, true};
    private HashMap<Integer, Vector<ParameterInstance>> parameterInstances;

    /**
     * Returns the job id
     * @param row
     * @return
     */
    public Integer getId(int row) {
        return jobs.get(row).getId();
    }

    /**
     * Returns the solver
     * @param row
     * @return null if there was an error
     */
    public Solver getSolver(int row) {
        try {
            SolverConfiguration sc = SolverConfigurationDAO.getSolverConfigurationById(jobs.get(row).getSolverConfigId());
            return SolverDAO.getById(sc.getSolver_id());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns the instance
     * @param row
     * @return null if there was an error
     */
    public Instance getInstance(int row) {
        try {
            return InstanceDAO.getById(jobs.get(row).getInstanceId());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns the run
     * @param row
     * @return
     */
    public Integer getRun(int row) {
        return jobs.get(row).getRun();
    }

    /**
     * Returns the result file name
     * @param row
     * @return
     */
    public String getResultFileName(int row) {
        return jobs.get(row).getResultFileName();
    }

    /**
     * Returns the time
     * @param row
     * @return
     */
    public Float getTime(int row) {
        return jobs.get(row).getTime();
    }

    /**
     * Returns the seed
     * @param row
     * @return
     */
    public Integer getSeed(int row) {
        return jobs.get(row).getSeed();
    }

    /**
     * Returns the status code
     * @param row
     * @return
     */
    public Integer getStatusCode(int row) {
        return jobs.get(row).getStatus();
    }

    /**
     * Returns all parameter instances for that job
     * @param row
     * @return null, if there was an error
     */
    public Vector<ParameterInstance> getParameters(int row) {
        try {
            SolverConfiguration sc = SolverConfigurationDAO.getSolverConfigurationById(jobs.get(row).getSolverConfigId());
            Vector<ParameterInstance> params = parameterInstances.get(sc.getId());
            if (params == null) {
                params = ParameterInstanceDAO.getBySolverConfigId(sc.getId());
                parameterInstances.put(sc.getId(), params);
            }
            return params;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Transforms the parameters obtained by getParameters to a string
     * @param row
     * @return
     */
    public String getParameterString(int row) {
        try {
            Vector<ParameterInstance> params = getParameters(row);
            String paramString = "";

            for (ParameterInstance param : params) {
                Parameter solverParameter = ParameterDAO.getById(param.getParameter_id());
                paramString += solverParameter.getName() + " = " + param.getValue();
                if (params.lastElement() != param) {
                    paramString += ", ";
                }
            }
            return paramString;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Sets the jobs for that model
     * @param jobs
     * @throws SQLException
     */
    public void setJobs(Vector<ExperimentResult> jobs) throws SQLException {
        this.jobs = jobs;
        parameterInstances = new HashMap<Integer, Vector<ParameterInstance>>();
    }

    private int getIndexForColumn(int col) {
        for (int i = 0; i < visible.length; i++) {
            if (visible[i]) {
                col--;
            }
            if (col == -1) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Returns an array of all column names, not only the visible ones.
     * @return
     */
    public String[] getAllColumnNames() {
        return columns;
    }

    @Override
    public int getRowCount() {
        if (jobs == null) {
            return 0;
        }
        return jobs.size();
    }

    @Override
    public int getColumnCount() {
        int res = 0;
        for (int i = 0; i < visible.length; i++) {
            if (visible[i]) {
                res++;
            }
        }
        return res;
    }

    @Override
    public String getColumnName(int col) {
        return columns[getIndexForColumn(col)];
    }

    @Override
    public Class getColumnClass(int col) {
        return getValueAt(0, getIndexForColumn(col)).getClass();
    }

    /**
     * Sets the column visibility.
     * @param visible a boolean array - length must equal getAllCoulumnNames().length or this method does nothing.
     */
    public void setColumnVisibility(boolean[] visible) {
        if (columns.length != visible.length) {
            return;
        }
        this.visible = visible;
        this.fireTableStructureChanged();
    }

    /**
     * Returns the visibility array
     * @return
     */
    public boolean[] getColumnVisibility() {
        return visible;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ExperimentResult j = jobs.get(rowIndex);

        if (columnIndex != -1) {
            columnIndex = getIndexForColumn(columnIndex);
        }
        switch (columnIndex) {
            case 0:
                return j.getId();
            case 1:
                return getSolver(rowIndex).getName();
            case 2:
                return getParameterString(rowIndex);
            case 3:
                return getInstance(rowIndex).getName();
            case 4:
                return j.getRun();
            case 5:
                return j.getResultFileName();
            case 6:
                return j.getTime();
            case 7:
                return j.getSeed();
            case 8:
                return j.getStatus();
            default:
                return "";
        }
    }

    /**
     * Returns all disjunct instance names which are currently shown in the table.
     * @return
     */
    public Vector<String> getInstances() {
        Vector<String> res = new Vector<String>();
        for (int i = 0; i < getRowCount(); i++) {
            boolean found = false;
            for (String instance : res) {
                if (instance.equals(getInstance(i).getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                res.add(getInstance(i).getName());
            }
        }
        return res;
    }

    /**
     * Returns all disjunct status codes which are currently shown in the table.
     */
    public Vector<Integer> getStatusCodes() {
        Vector<Integer> res = new Vector<Integer>();
        for (int i = 0; i < getRowCount(); i++) {
            boolean found = false;
            for (Integer statusCode : res) {
                if (statusCode.equals(getStatusCode(i))) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                res.add(getStatusCode(i));
            }
        }
        return res;
    }

    /**
     * Returns all disjunct solver names which are currently shown in the table.
     */
    public Vector<String> getSolvers() {
        Vector<String> res = new Vector<String>();
        for (int i = 0; i < getRowCount(); i++) {
            boolean found = false;
            for (String solver : res) {
                if (solver.equals(getSolver(i).getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                res.add(getSolver(i).getName());
            }
        }
        return res;
    }
    
    public Vector<ExperimentResult> getJobs() {
        return jobs;
    }

}
