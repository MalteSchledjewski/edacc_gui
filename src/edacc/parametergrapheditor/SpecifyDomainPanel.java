/*
 * SpecifyDomainPanel.java
 *
 * Created on 03.07.2011, 21:25:41
 */
package edacc.parametergrapheditor;

import edacc.parameterspace.domain.*;
import javax.swing.JPanel;

/**
 *
 * @author simon
 */
public class SpecifyDomainPanel extends javax.swing.JPanel implements IDomainPanel {

    JPanel pnlDomain;
    SpecifyDomainDialog main;

    /** Creates new form SpecifyDomainPanel */
    public SpecifyDomainPanel(SpecifyDomainDialog main) {
        initComponents();
        this.main = main;
        comboDomain.removeAllItems();
        comboDomain.addItem("");
        for (String name : Domain.names) {
            comboDomain.addItem(name);
        }
    }

    @Override
    public void setDomain(Domain domain) {
        comboDomain.setSelectedItem(domain.getName());
        // null for Flag/Optional domain
        if (pnlDomain != null) {
            ((IDomainPanel) pnlDomain).setDomain(domain);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        comboDomain = new javax.swing.JComboBox();
        pnlDomainParameters = new javax.swing.JScrollPane();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(SpecifyDomainPanel.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        comboDomain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboDomain.setName("comboDomain"); // NOI18N
        comboDomain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDomainActionPerformed(evt);
            }
        });

        pnlDomainParameters.setName("pnlDomainParameters"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboDomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
            .addComponent(pnlDomainParameters, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboDomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDomainParameters, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboDomainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDomainActionPerformed
        if (CategoricalDomain.name.equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SpecifyCategoricalDomainPanel(main);
        } else if (IntegerDomain.name.equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SpecifyIntegerDomainPanel(main);
        } else if (MixedDomain.name.equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SpecifyMixedDomainPanel(main);
        } else if (OrdinalDomain.name.equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SpecifyOrdinalDomainPanel(main);
        } else if (RealDomain.name.equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SpecifyRealDomainPanel(main);
        } else {
            pnlDomain = null;
        }

        if (pnlDomain == null) {
            pnlDomainParameters.setVisible(false);
        } else {
            pnlDomain.invalidate();
            pnlDomain.revalidate();
            pnlDomainParameters.setVisible(true);
            pnlDomainParameters.setViewportView(pnlDomain);
        }
        this.invalidate();
        this.revalidate();
        this.repaint();
}//GEN-LAST:event_comboDomainActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox comboDomain;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane pnlDomainParameters;
    // End of variables declaration//GEN-END:variables

    @Override
    public Domain getDomain() throws InvalidDomainException {
        if (pnlDomain != null) {
            return ((IDomainPanel) pnlDomain).getDomain();
        } else if (FlagDomain.name.equals(comboDomain.getSelectedItem())) {
            return new FlagDomain(true, true);
        } else if (OptionalDomain.name.equals(comboDomain.getSelectedItem())) {
            return new OptionalDomain();
        }
        throw new InvalidDomainException("No domain selected.");
    }
}
