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

        btnApply = new javax.swing.JButton();
        chkName = new javax.swing.JCheckBox();
        txtAtomsLow = new javax.swing.JTextField();
        txtMaxClauseLengthLow = new javax.swing.JTextField();
        txtMaxClauseLengthHigh = new javax.swing.JTextField();
        txtAtomsHigh = new javax.swing.JTextField();
        chkAtoms = new javax.swing.JCheckBox();
        txtClausesHigh = new javax.swing.JTextField();
        txtClausesLow = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        chkRatio = new javax.swing.JCheckBox();
        txtRatioLow = new javax.swing.JTextField();
        chkClauses = new javax.swing.JCheckBox();
        txtRatioHigh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        chkMaxClauseLength = new javax.swing.JCheckBox();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCInstanceFilter.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(470, 240));
        setModal(true);
        setName("Form"); // NOI18N

        btnApply.setText(resourceMap.getString("btnApply.text")); // NOI18N
        btnApply.setName("btnApply"); // NOI18N
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        chkName.setName("chkName"); // NOI18N

        txtAtomsLow.setText(resourceMap.getString("txtAtomsLow.text")); // NOI18N
        txtAtomsLow.setName("txtAtomsLow"); // NOI18N
        txtAtomsLow.setPreferredSize(new java.awt.Dimension(80, 20));


        txtMaxClauseLengthLow.setText(resourceMap.getString("txtMaxClauseLengthLow.text")); // NOI18N
        txtMaxClauseLengthLow.setName("txtMaxClauseLengthLow"); // NOI18N

        txtMaxClauseLengthHigh.setText(resourceMap.getString("txtMaxClauseLengthHigh.text")); // NOI18N
        txtMaxClauseLengthHigh.setName("txtMaxClauseLengthHigh"); // NOI18N

        txtAtomsHigh.setText(resourceMap.getString("txtAtomsHigh.text")); // NOI18N
        txtAtomsHigh.setName("txtAtomsHigh"); // NOI18N
        txtAtomsHigh.setPreferredSize(new java.awt.Dimension(80, 20));

        chkAtoms.setName("chkAtoms"); // NOI18N

        txtClausesHigh.setText(resourceMap.getString("txtClausesHigh.text")); // NOI18N
        txtClausesHigh.setName("txtClausesHigh"); // NOI18N

        txtClausesLow.setText(resourceMap.getString("txtClausesLow.text")); // NOI18N
        txtClausesLow.setName("txtClausesLow"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        chkRatio.setName("chkRatio"); // NOI18N

        txtRatioLow.setText(resourceMap.getString("txtRatioLow.text")); // NOI18N
        txtRatioLow.setName("txtRatioLow"); // NOI18N

        chkClauses.setName("chkClauses"); // NOI18N


        txtRatioHigh.setText(resourceMap.getString("txtRatioHigh.text")); // NOI18N
        txtRatioHigh.setName("txtRatioHigh"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        chkMaxClauseLength.setName("chkMaxClauseLength"); // NOI18N

        txtName.setName("txtName"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAtomsLow, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAtomsHigh, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClausesLow, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtClausesHigh, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRatioLow, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtRatioHigh, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaxClauseLengthLow, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaxClauseLengthHigh, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel2)
                            .addComponent(chkName)
                            .addComponent(chkAtoms)
                            .addComponent(chkClauses)
                            .addComponent(chkRatio)
                            .addComponent(chkMaxClauseLength)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnApply)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkName))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                    .addComponent(jLabel5)
                    .addComponent(txtAtomsLow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAtomsHigh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAtoms))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                    .addComponent(jLabel6)
                    .addComponent(txtClausesLow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClausesHigh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkClauses))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                    .addComponent(jLabel7)
                    .addComponent(txtRatioLow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRatioHigh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRatio))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                    .addComponent(jLabel3)
                    .addComponent(txtMaxClauseLengthLow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxClauseLengthHigh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkMaxClauseLength))
                .addGap(10, 10, 10)
                .addComponent(btnApply)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        btnApply();
}//GEN-LAST:event_btnApplyActionPerformed

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

            if (main.rowFilter.filter_name || main.rowFilter.filter_numAtoms ||
                main.rowFilter.filter_numClauses || main.rowFilter.filter_ratio ||
                main.rowFilter.filter_maxClauseLength) {
                main.setFilterStatus("This list of instances has filters applied to it. Use the filter button below to modify.");
            }
            else {
                main.setFilterStatus("");
            }

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
