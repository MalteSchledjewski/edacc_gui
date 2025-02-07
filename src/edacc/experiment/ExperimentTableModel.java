package edacc.experiment;

import java.util.ArrayList;
import edacc.model.Experiment;
import edacc.model.ExperimentDAO;
import java.sql.SQLException;
import java.util.List;

/**
 * Represents a experiment table model
 * @author simon
 */
public class ExperimentTableModel extends ThreadSafeDefaultTableModel {

    /** Column count if used as simple table */
    public static final int COL_COUNT_SIMPLE = 4;
    /** Index of the ID column */
    public static final int COL_ID = 0;
    /** Index of the name column */
    public static final int COL_NAME = 1;
    /** Index of the date column */
    public static final int COL_DATE = 2;
    /** Index of the configuration experiment column */
    public static final int COL_CONFIGURATIONEXPERIMENT = 3;
    /** Index of the number of runs column */
    public static final int COL_NUMRUNS = 4;
    /** Index of the not started count column */
    public static final int COL_NOTSTARTED = 5;
    /** Index of the running count column */
    public static final int COL_RUNNING = 6;
    /** Index of the finished count column */
    public static final int COL_FINISHED = 7;
    /** Index of the failed count column */
    public static final int COL_FAILED = 8;
    /** Index of the priority column */
    public static final int COL_PRIORITY = 9;
    /** Index of the active column */
    public static final int COL_ACTIVE = 10;
    private String[] columns = {"ID", "Name", "Date", "Configuration Experiment", "Number of jobs", "Not started", "Running", "Finished", "Failed", "Priority", "Active"};
    private ArrayList<Experiment> experiments;
    private Integer[] running;
    private Integer[] finished;
    private Integer[] failed;
    private Integer[] not_started;
    private Integer[] numruns;
    private boolean simple;
    
    /**
     * Creates a new experiment table model.
     * @param simple if true this will create a simple table model. Just the first <code>COL_COUNT_SIMPLE</code> columns will be used.
     */
    public ExperimentTableModel(boolean simple) {
        this.experiments = new ArrayList<Experiment>();
        this.simple = simple;
    }

    /**
     * Sets the experiments for the model
     * @param experiments the experiments
     */
    public void setExperiments(List<Experiment> experiments) {
        if (experiments == null)
            this.experiments = null;
        else {
            this.experiments = new ArrayList<Experiment>();
            this.experiments.addAll(experiments);
        }
        if (experiments != null) {
            running = new Integer[experiments.size()];
            finished = new Integer[experiments.size()];
            failed = new Integer[experiments.size()];
            not_started = new Integer[experiments.size()];
            numruns = new Integer[experiments.size()];
        } else {
            running = null;
            finished = null;
            failed = null;
            not_started = null;
            numruns = null;
        }
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return experiments == null ? 0 : experiments.size();
    }

    @Override
    public int getColumnCount() {
        return simple==true?COL_COUNT_SIMPLE:columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    @Override
    public Class getColumnClass(int col) {
        if (col == COL_NUMRUNS || col == COL_RUNNING || col == COL_FINISHED || col == COL_FAILED || col == COL_NOTSTARTED) {
            return Integer.class;
        }
        if (col == COL_CONFIGURATIONEXPERIMENT) {
            return char.class;
        }
        if (experiments == null || experiments.isEmpty()) {
            return String.class;
        }
        return getValueAt(0, col).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > experiments.size() - 1) {
            return null;
        }
        switch (columnIndex) {
            case COL_ID:
                return experiments.get(rowIndex).getId();
            case COL_NAME:
                return experiments.get(rowIndex).getName();
            case COL_DATE:
                return experiments.get(rowIndex).getDate();
            case COL_CONFIGURATIONEXPERIMENT:
                return experiments.get(rowIndex).isConfigurationExp() ? '\u2713' : ' ';
            case COL_NUMRUNS:
                return numruns[rowIndex];
            case COL_NOTSTARTED:
                return not_started[rowIndex];
            case COL_RUNNING:
                return running[rowIndex];
            case COL_FINISHED:
                return finished[rowIndex];
            case COL_FAILED:
                return failed[rowIndex];
            case COL_PRIORITY:
                return experiments.get(rowIndex).getPriority();
            case COL_ACTIVE:
                return experiments.get(rowIndex).isActive();
            default:
                return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == COL_ACTIVE || columnIndex == COL_PRIORITY;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == COL_ACTIVE) {
            experiments.get(rowIndex).setActive((Boolean) aValue);
            this.fireTableCellUpdated(rowIndex, columnIndex);
        } else if (columnIndex == COL_PRIORITY) {
            experiments.get(rowIndex).setPriority((Integer) aValue);
            this.fireTableCellUpdated(rowIndex, columnIndex);
        }
        try {
            ExperimentDAO.save(experiments.get(rowIndex));
        } catch (SQLException ex) {
            // TODO: error
        }
    }

    /**
     * Sets running at the <code>rowIndex</code>
     * @param rowIndex the row index
     * @param value the running count
     */
    public void setRunningAt(int rowIndex, Integer value) {
        running[rowIndex] = value;
        this.fireTableCellUpdated(rowIndex, COL_RUNNING);
    }
    
    public Integer getRunningAt(int rowIndex) {
        return running[rowIndex];
    }

    /**
     * Sets finished at the <code>rowIndex</code>
     * @param rowIndex the row index
     * @param value the finished count
     */
    public void setFinishedAt(int rowIndex, Integer value) {
        finished[rowIndex] = value;
        this.fireTableCellUpdated(rowIndex, COL_FINISHED);
    }
    
    public Integer getFinishedAt(int rowIndex) {
        return finished[rowIndex];
    }
    
    /**
     * Sets failed at the <code>rowIndex</code>
     * @param rowIndex the row index
     * @param value the failed count
     */
    public void setFailedAt(int rowIndex, Integer value) {
        failed[rowIndex] = value;
        this.fireTableCellUpdated(rowIndex, COL_FAILED);
    }
    
    public Integer getFailedAt(int rowIndex) {
        return failed[rowIndex];
    }
    
    /**
     * Sets not started at the <code>rowIndex</code>
     * @param rowIndex the row index
     * @param value the not started count
     */
    public void setNotStartedAt(int rowIndex, Integer value) {
        not_started[rowIndex] = value;
        this.fireTableCellUpdated(rowIndex, COL_NOTSTARTED);
    }
    
    public Integer getNotStartedAt(int rowIndex) {
        return not_started[rowIndex];
    }
    
    /**
     * Sets number of runs at the <code>rowIndex</code>
     * @param rowIndex the row index
     * @param value the number of runs
     */
    public void setNumRunsAt(int rowIndex, Integer value) {
        numruns[rowIndex] = value;
        this.fireTableCellUpdated(rowIndex, COL_NUMRUNS);
    }
    
    public Integer getNumRunsAt(int rowIndex) {
        return numruns[rowIndex];
    }

    /**
     * Returns the experiment represented by this row.
     * @param rowIndex the row index for which the experiment should be returned
     * @return 
     */
    public Experiment getExperimentAt(int rowIndex) {
        return experiments.get(rowIndex);
    }
}
