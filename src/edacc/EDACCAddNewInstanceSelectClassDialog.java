/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCAddNewInstanceSelectClassDialog.java
 *
 * Created on 20.03.2010, 12:43:23
 */

package edacc;

import edacc.manageDB.AddInstanceInstanceClassTabelModel;
import edacc.model.InstanceClass;
import edacc.model.InstanceClassDAO;
import edacc.model.NoConnectionToDBException;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author rretz
 */
public class EDACCAddNewInstanceSelectClassDialog extends javax.swing.JDialog {
    InstanceClass input;
    AddInstanceInstanceClassTabelModel tableModel;

    /** Creates new form EDACCAddNewInstanceSelectClassDialog */
    public EDACCAddNewInstanceSelectClassDialog(java.awt.Frame parent, boolean modal) throws NoConnectionToDBException, SQLException {
        super(parent, modal);
        initComponents();
        //Filling the table with all instance source class
        tableModel = new AddInstanceInstanceClassTabelModel();
        tableModel.addClasses(new Vector<InstanceClass>(InstanceClassDAO.getAllSourceClass()));
        jTableInstanceClass.setModel(tableModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupAutomaticOrManuel = new javax.swing.ButtonGroup();
        jRadioButtonAutomatic = new javax.swing.JRadioButton();
        jRadioButtonChoose = new javax.swing.JRadioButton();
        jScrollPaneInstanceTable = new javax.swing.JScrollPane();
        jTableInstanceClass = new javax.swing.JTable();
        jPanelButtons = new javax.swing.JPanel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCAddNewInstanceSelectClassDialog.class);
        setTitle(resourceMap.getString("AddNewInstanceSelectClass.title")); // NOI18N
        setName("AddNewInstanceSelectClass"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        buttonGroupAutomaticOrManuel.add(jRadioButtonAutomatic);
        jRadioButtonAutomatic.setText(resourceMap.getString("jRadioButtonAutomatic.text")); // NOI18N
        jRadioButtonAutomatic.setName("jRadioButtonAutomatic"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        getContentPane().add(jRadioButtonAutomatic, gridBagConstraints);

        buttonGroupAutomaticOrManuel.add(jRadioButtonChoose);
        jRadioButtonChoose.setText(resourceMap.getString("jRadioButtonChoose.text")); // NOI18N
        jRadioButtonChoose.setName("jRadioButtonChoose"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        getContentPane().add(jRadioButtonChoose, gridBagConstraints);

        jScrollPaneInstanceTable.setName("jScrollPaneInstanceTable"); // NOI18N
        jScrollPaneInstanceTable.setPreferredSize(new java.awt.Dimension(375, 125));

        jTableInstanceClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableInstanceClass.setName("jTableInstanceClass"); // NOI18N
        jTableInstanceClass.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneInstanceTable.setViewportView(jTableInstanceClass);
        jTableInstanceClass.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTableInstanceClass.columnModel.title0")); // NOI18N
        jTableInstanceClass.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTableInstanceClass.columnModel.title1")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jScrollPaneInstanceTable, gridBagConstraints);

        jPanelButtons.setName("jPanelButtons"); // NOI18N
        jPanelButtons.setLayout(new java.awt.GridBagLayout());

        jButtonOk.setText(resourceMap.getString("jButtonOk.text")); // NOI18N
        jButtonOk.setName("jButtonOk"); // NOI18N
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelButtons.add(jButtonOk, gridBagConstraints);

        jButtonCancel.setText(resourceMap.getString("jButtonCancel.text")); // NOI18N
        jButtonCancel.setName("jButtonCancel"); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelButtons.add(jButtonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        getContentPane().add(jPanelButtons, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * Modifies the input object according to the user inputs, checks them and close the jDialog.
 * @param evt jButtonOk clicked
 */
    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        this.input = new InstanceClass();
        if(buttonGroupAutomaticOrManuel.getSelection() == null){
            JOptionPane.showMessageDialog(this,
                    "Please choose if the new instance class is a source class oder a user class." ,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
       }else {
          if(jRadioButtonAutomatic.isSelected()){
            this.input.setName("");
            this.setVisible(false);
          }else{
            if(jTableInstanceClass.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(this,
                    "Please select one of the instance source classes from the table or select \"Automatic instance class generation\"." ,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }else{
                int row = jTableInstanceClass.getSelectedRow();
                this.input = (InstanceClass) tableModel.getValueAt(row, 2);
                this.setVisible(false);
            }

          }
       }
        
    }//GEN-LAST:event_jButtonOkActionPerformed
    /**
     * Modifies the input object to null and close the jDialog.
     * @param evt jButtonCancel clicked
     */
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        input = null;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    public InstanceClass getInput(){
        return input;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupAutomaticOrManuel;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JRadioButton jRadioButtonAutomatic;
    private javax.swing.JRadioButton jRadioButtonChoose;
    private javax.swing.JScrollPane jScrollPaneInstanceTable;
    private javax.swing.JTable jTableInstanceClass;
    // End of variables declaration//GEN-END:variables

}
