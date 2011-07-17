/*
 * EDACCExperimentModeSolverConfigurationTablePanel.java
 *
 * Created on 17.03.2011, 19:44:10
 */
package edacc;

import edacc.experiment.SolverConfigEntryTableModel;
import edacc.model.ParameterInstance;
import edacc.model.ParameterInstanceDAO;
import edacc.model.Solver;
import edacc.model.SolverConfigCache;
import edacc.model.SolverDAO;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author simon
 */
public class EDACCExperimentModeSolverConfigurationTablePanel extends javax.swing.JPanel implements Observer {

    private EDACCSolverConfigPanel solverConfigPanel;
    private TableModel tableModel;

    /** Creates new form EDACCExperimentModeSolverConfigurationTablePanel */
    public EDACCExperimentModeSolverConfigurationTablePanel(EDACCSolverConfigPanel solverConfigPanel) {
        initComponents();
        this.solverConfigPanel = solverConfigPanel;
        tableModel = new TableModel();
        table.setModel(tableModel);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public void update() {
        tableModel.update();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        itemEdit = new javax.swing.JMenuItem();
        itemRemove = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        popupMenu.setName("popupMenu"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCExperimentModeSolverConfigurationTablePanel.class);
        itemEdit.setText(resourceMap.getString("itemEdit.text")); // NOI18N
        itemEdit.setName("itemEdit"); // NOI18N
        itemEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditActionPerformed(evt);
            }
        });
        popupMenu.add(itemEdit);

        itemRemove.setText(resourceMap.getString("itemRemove.text")); // NOI18N
        itemRemove.setName("itemRemove"); // NOI18N
        itemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRemoveActionPerformed(evt);
            }
        });
        popupMenu.add(itemRemove);

        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setName("table"); // NOI18N
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setSolverConfigCache(SolverConfigCache solverConfigCache) {
        solverConfigCache.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        tableModel.update();
    }

    private class SolverConfigDialog extends JDialog {

        private boolean apply;

        public SolverConfigDialog(EDACCSolverConfigEntry entry) {
            super();
            
            setTitle("Edit Solver Configuration");
            JButton btnCancel = new JButton("Cancel");
            JButton btnApply = new JButton("Apply");
            apply = false;
            btnCancel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SolverConfigDialog.this.setVisible(false);
                }
            });

            btnApply.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    SolverConfigDialog.this.apply = true;
                    SolverConfigDialog.this.setVisible(false);
                }
            });

            JPanel bottom = new JPanel();
            bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));
            bottom.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            bottom.add(Box.createHorizontalGlue());

            bottom.add(btnCancel);

            bottom.add(Box.createRigidArea(new Dimension(10, 0)));
            bottom.add(btnApply);

            Container contentPane = getContentPane();
            contentPane.add(entry, BorderLayout.CENTER);
            contentPane.add(bottom, BorderLayout.SOUTH);
        }

        public boolean userApplied() {
            return apply;
        }
    }

    private void itemEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditActionPerformed
        if (table.getSelectedRow() != -1) {
            // TODO: fix
            EDACCSolverConfigEntry entry = tableModel.getEntry(table.convertRowIndexToModel(table.getSelectedRow()));
            try {
                EDACCSolverConfigEntry copy;
                if (entry.getSolverConfiguration() != null) {
                    copy = new EDACCSolverConfigEntry(entry.getSolverConfiguration(), ParameterInstanceDAO.getBySolverConfig(entry.getSolverConfiguration()));
                } else {
                    copy = new EDACCSolverConfigEntry(SolverDAO.getById(entry.getSolverId()), entry.getTitle());
                }
                copy.assign(entry);
                copy.removeButtons();
                copy.setParent(entry.parent);

                SolverConfigDialog dialog = new SolverConfigDialog(copy);
                dialog.setLocationRelativeTo(EDACCApp.getApplication().getMainFrame());
                dialog.setModal(true);
                EDACCApp.getApplication().show(dialog);
                if (dialog.userApplied()) {
                    entry.assign(copy);
                }
                dialog.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
            update();
        }
    }//GEN-LAST:event_itemEditActionPerformed

    private void itemRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRemoveActionPerformed
        for (int row : table.getSelectedRows()) {
            tableModel.getEntry(table.convertRowIndexToModel(row)).btnRemove();
        }
        tableModel.update();
    }//GEN-LAST:event_itemRemoveActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() >= 2) {
            itemEditActionPerformed(null);
            evt.consume();
        }
    }//GEN-LAST:event_tableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemEdit;
    private javax.swing.JMenuItem itemRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    private class TableModel extends DefaultTableModel {

        private String[] columns = new String[]{"Solver", "Solver Configuration", "Parameters"};
        ArrayList<String> solverConfigNames;
        ArrayList<Solver> solvers;
        ArrayList<String> parameterStrings;
        ArrayList<EDACCSolverConfigEntry> entries;

        public void update() {
            solverConfigNames = new ArrayList<String>();
            solvers = new ArrayList<Solver>();
            entries = new ArrayList<EDACCSolverConfigEntry>();
            parameterStrings = new ArrayList<String>();
            for (EDACCSolverConfigPanelSolver pnlSolver : solverConfigPanel.getAllSolverConfigSolverPanels()) {
                for (EDACCSolverConfigEntry entry : pnlSolver.getAllSolverConfigEntries()) {
                    Solver solver = null;
                    try {
                        if (entry.getSolverConfiguration() != null) {
                            solver = SolverDAO.getById(entry.getSolverConfiguration().getSolverBinary().getIdSolver());
                        } else {
                            solver = SolverDAO.getById(entry.getSolverId());
                        }
                        solverConfigNames.add(entry.getTitle());
                    } catch (Exception e) {
                    }
                    solvers.add(solver);
                    entries.add(entry);
                    ArrayList<ParameterInstance> pis = new ArrayList<ParameterInstance>();
                    SolverConfigEntryTableModel model = entry.solverConfigEntryTableModel;
                    for (int row = 0; row < model.getRowCount(); row++) {
                        if (model.isSelected(row)) {
                            ParameterInstance pi = new ParameterInstance();
                            pi.setParameter_id(model.getParameterAt(row).getId());
                            pi.setValue(model.getValueAt(row));
                            pis.add(pi);
                        }
                    }
                    parameterStrings.add(edacc.experiment.Util.getParameterString(pis, solver));
                }
            }
            fireTableDataChanged();
        }

        public EDACCSolverConfigEntry getEntry(int row) {
            return entries.get(row);
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public int getRowCount() {
            return solverConfigNames == null ? 0 : solverConfigNames.size();
        }

        @Override
        public Object getValueAt(int row, int column) {
            try {
                switch (column) {
                    case 0:
                        return solvers.get(row);
                    case 1:
                        return solverConfigNames.get(row);
                    case 2:
                        return parameterStrings.get(row);
                    default:
                        return "";
                }
            } catch (Exception e) {
                return "";
            }
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getRowCount() == 0) {
                return String.class;
            } else {
                return getValueAt(0, columnIndex).getClass();
            }
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
