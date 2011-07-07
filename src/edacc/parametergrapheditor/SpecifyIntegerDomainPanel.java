/*
 * SpecifyIntegerDomainPanel.java
 *
 * Created on 03.07.2011, 18:27:48
 */
package edacc.parametergrapheditor;

import edacc.parameterspace.domain.Domain;
import edacc.parameterspace.domain.IntegerDomain;

/**
 *
 * @author simon
 */
public class SpecifyIntegerDomainPanel extends javax.swing.JPanel implements IDomainPanel {

    /** Creates new form SpecifyIntegerDomainPanel */
    public SpecifyIntegerDomainPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLow = new javax.swing.JTextField();
        txtHigh = new javax.swing.JTextField();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(SpecifyIntegerDomainPanel.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        txtLow.setText(resourceMap.getString("txtLow.text")); // NOI18N
        txtLow.setName("txtLow"); // NOI18N

        txtHigh.setText(resourceMap.getString("txtHigh.text")); // NOI18N
        txtHigh.setName("txtHigh"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtHigh, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(txtLow, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHigh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtHigh;
    private javax.swing.JTextField txtLow;
    // End of variables declaration//GEN-END:variables

    @Override
    public Domain getDomain() throws InvalidDomainException {
        try {
            int lo = Integer.parseInt(txtLow.getText());
            int hi = Integer.parseInt(txtHigh.getText());
            return new IntegerDomain(lo, hi);
        } catch (NumberFormatException e) {
            throw new InvalidDomainException("You have to specify integers for lower and upper limit for integer domain.");
        }
    }

    @Override
    public void setDomain(Domain domain) {
        if (!(domain instanceof IntegerDomain)) {
            return;
        }
        IntegerDomain iDomain = (IntegerDomain) domain;
        txtLow.setText("" + iDomain.getLow());
        txtHigh.setText("" + iDomain.getHigh());
    }
}
