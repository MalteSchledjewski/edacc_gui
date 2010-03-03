/*
 * EDACCGridSettingsView.java
 *
 * Created on Nov 26, 2009, 9:34:21 PM
 */

package edacc;

import javax.swing.JOptionPane;
import org.jdesktop.application.Action;



/**
 *
 * @author Daniel D.
 */
public class EDACCInstanceFilter extends javax.swing.JDialog {
    EDACCExperimentMode main;

    /** Creates new form EDACCGridSettingsView */
    public EDACCInstanceFilter(java.awt.Frame parent, boolean modal, EDACCExperimentMode main) {
        super(parent, modal);
        initComponents();
        this.main = main;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        chkMaxClauseLength = new javax.swing.JCheckBox();
        txtMaxClauseLengthLow = new javax.swing.JTextField();
        txtMaxClauseLengthHigh = new javax.swing.JTextField();
        chkName = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAtomsLow = new javax.swing.JTextField();
        txtAtomsHigh = new javax.swing.JTextField();
        chkAtoms = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtClausesLow = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtClausesHigh = new javax.swing.JTextField();
        chkClauses = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        txtRatioLow = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtRatioHigh = new javax.swing.JTextField();
        chkRatio = new javax.swing.JCheckBox();
        btnApply = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCInstanceFilter.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        getContentPane().add(jLabel3, gridBagConstraints);

        txtName.setText(resourceMap.getString("txtName.text")); // NOI18N
        txtName.setName("txtName"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtName, gridBagConstraints);

        chkMaxClauseLength.setText(resourceMap.getString("chkMaxClauseLength.text")); // NOI18N
        chkMaxClauseLength.setName("chkMaxClauseLength"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        getContentPane().add(chkMaxClauseLength, gridBagConstraints);

        txtMaxClauseLengthLow.setText(resourceMap.getString("txtMaxClauseLengthLow.text")); // NOI18N
        txtMaxClauseLengthLow.setName("txtMaxClauseLengthLow"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtMaxClauseLengthLow, gridBagConstraints);

        txtMaxClauseLengthHigh.setText(resourceMap.getString("txtMaxClauseLengthHigh.text")); // NOI18N
        txtMaxClauseLengthHigh.setName("txtMaxClauseLengthHigh"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtMaxClauseLengthHigh, gridBagConstraints);

        chkName.setText(resourceMap.getString("chkName.text")); // NOI18N
        chkName.setName("chkName"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        getContentPane().add(chkName, gridBagConstraints);

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jLabel4, gridBagConstraints);

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        getContentPane().add(jLabel5, gridBagConstraints);

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        getContentPane().add(jLabel7, gridBagConstraints);

        txtAtomsLow.setText(resourceMap.getString("txtAtomsLow.text")); // NOI18N
        txtAtomsLow.setName("txtAtomsLow"); // NOI18N
        txtAtomsLow.setPreferredSize(new java.awt.Dimension(80, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtAtomsLow, gridBagConstraints);

        txtAtomsHigh.setText(resourceMap.getString("txtAtomsHigh.text")); // NOI18N
        txtAtomsHigh.setName("txtAtomsHigh"); // NOI18N
        txtAtomsHigh.setPreferredSize(new java.awt.Dimension(80, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtAtomsHigh, gridBagConstraints);

        chkAtoms.setName("chkAtoms"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        getContentPane().add(chkAtoms, gridBagConstraints);

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        getContentPane().add(jLabel8, gridBagConstraints);

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        getContentPane().add(jLabel9, gridBagConstraints);

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        getContentPane().add(jLabel10, gridBagConstraints);

        txtClausesLow.setText(resourceMap.getString("txtClausesLow.text")); // NOI18N
        txtClausesLow.setName("txtClausesLow"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtClausesLow, gridBagConstraints);

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        getContentPane().add(jLabel11, gridBagConstraints);

        txtClausesHigh.setText(resourceMap.getString("txtClausesHigh.text")); // NOI18N
        txtClausesHigh.setName("txtClausesHigh"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtClausesHigh, gridBagConstraints);

        chkClauses.setName("chkClauses"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        getContentPane().add(chkClauses, gridBagConstraints);

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        getContentPane().add(jLabel12, gridBagConstraints);

        txtRatioLow.setText(resourceMap.getString("txtRatioLow.text")); // NOI18N
        txtRatioLow.setName("txtRatioLow"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtRatioLow, gridBagConstraints);

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        getContentPane().add(jLabel13, gridBagConstraints);

        txtRatioHigh.setText(resourceMap.getString("txtRatioHigh.text")); // NOI18N
        txtRatioHigh.setName("txtRatioHigh"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        getContentPane().add(txtRatioHigh, gridBagConstraints);

        chkRatio.setName("chkRatio"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        getContentPane().add(chkRatio, gridBagConstraints);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getActionMap(EDACCInstanceFilter.class, this);
        btnApply.setAction(actionMap.get("btnApply")); // NOI18N
        btnApply.setText(resourceMap.getString("btnApply.text")); // NOI18N
        btnApply.setName("btnApply"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btnApply, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void loadValues() {
        if (main.rowFilter.name != null)
            this.txtName.setText(main.rowFilter.name);
        this.chkName.setSelected(main.rowFilter.filter_name);

        this.txtAtomsLow.setText(String.valueOf(main.rowFilter.numAtoms_low));
        this.txtAtomsHigh.setText(String.valueOf(main.rowFilter.numAtoms_high));
        this.chkAtoms.setSelected(main.rowFilter.filter_numAtoms);

        this.txtClausesLow.setText(String.valueOf(main.rowFilter.numClauses_low));
        this.txtClausesHigh.setText(String.valueOf(main.rowFilter.numClauses_high));
        this.chkClauses.setSelected(main.rowFilter.filter_numClauses);

        this.txtRatioLow.setText(String.valueOf(main.rowFilter.ratio_low));
        this.txtRatioHigh.setText(String.valueOf(main.rowFilter.ratio_high));
        this.chkRatio.setSelected(main.rowFilter.filter_ratio);

        this.txtMaxClauseLengthLow.setText(String.valueOf(main.rowFilter.maxClauseLength_low));
        this.txtMaxClauseLengthHigh.setText(String.valueOf(main.rowFilter.maxClauseLength_high));
        this.chkMaxClauseLength.setSelected(main.rowFilter.filter_maxClauseLength);
    }

    @Action
    public void btnApply() {
        try {
            main.rowFilter.filter_name = this.chkName.isSelected();
            main.rowFilter.filter_numAtoms = this.chkAtoms.isSelected();
            main.rowFilter.filter_numClauses = this.chkClauses.isSelected();
            main.rowFilter.filter_ratio = this.chkRatio.isSelected();
            main.rowFilter.filter_maxClauseLength = this.chkMaxClauseLength.isSelected();

            main.rowFilter.name = txtName.getText();

            main.rowFilter.numAtoms_low = Integer.valueOf(this.txtAtomsLow.getText());
            main.rowFilter.numAtoms_high = Integer.valueOf(this.txtAtomsHigh.getText());

            main.rowFilter.numClauses_low = Integer.valueOf(this.txtClausesLow.getText());
            main.rowFilter.numClauses_high = Integer.valueOf(this.txtClausesHigh.getText());
            
            main.rowFilter.ratio_low = Float.valueOf(this.txtRatioLow.getText());
            main.rowFilter.ratio_high = Float.valueOf(this.txtRatioHigh.getText());
            
            main.rowFilter.maxClauseLength_low = Integer.valueOf(this.txtMaxClauseLengthLow.getText());
            main.rowFilter.maxClauseLength_high = Integer.valueOf(this.txtMaxClauseLengthHigh.getText());

            if (main.expController.getNumInstances() > 0)
                main.sorter.setRowFilter(main.rowFilter);
            main.insTableModel.fireTableDataChanged();
            this.setVisible(false);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Number formatting error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JCheckBox chkAtoms;
    private javax.swing.JCheckBox chkClauses;
    private javax.swing.JCheckBox chkMaxClauseLength;
    private javax.swing.JCheckBox chkName;
    private javax.swing.JCheckBox chkRatio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAtomsHigh;
    private javax.swing.JTextField txtAtomsLow;
    private javax.swing.JTextField txtClausesHigh;
    private javax.swing.JTextField txtClausesLow;
    private javax.swing.JTextField txtMaxClauseLengthHigh;
    private javax.swing.JTextField txtMaxClauseLengthLow;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtRatioHigh;
    private javax.swing.JTextField txtRatioLow;
    // End of variables declaration//GEN-END:variables

}
