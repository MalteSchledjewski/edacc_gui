/*
 * SelectDomainPanel.java
 *
 * Created on 04.07.2011, 11:35:06
 */
package edacc.parametergrapheditor;

import java.util.Set;
import edacc.parameterspace.domain.*;
import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author simon
 */
public class SelectDomainPanel extends javax.swing.JPanel implements IDomainPanel {

    IDomainPanel pnlDomain;
    Set<String> possibleDomains;
    Domain domain;

    /** Creates new form SelectDomainPanel */
    public SelectDomainPanel(Domain domain) {
        initComponents();

        this.domain = domain;
        comboDomain.removeAllItems();
        comboDomain.addItem("");
        if (!getDomains(domain, CategoricalDomain.class).isEmpty()) {
            comboDomain.addItem("Categorical");
        }
        if (!getDomains(domain, FlagDomain.class).isEmpty()) {
            comboDomain.addItem("Flag");
        }
        if (!getDomains(domain, IntegerDomain.class).isEmpty()) {
            comboDomain.addItem("Integer");
        }
        comboDomain.addItem("Mixed");
        if (!getDomains(domain, OptionalDomain.class).isEmpty()) {
            comboDomain.addItem("Optional");
        }
        if (!getDomains(domain, OrdinalDomain.class).isEmpty()) {
            comboDomain.addItem("Ordinal");
        }
        if (!getDomains(domain, RealDomain.class).isEmpty()) {
            comboDomain.addItem("Real");
        }
        pnlDomainParameters.setLayout(new BorderLayout());
    }

    private List<Domain> getDomains(Domain domain, Class clazz) {
        List<Domain> domains = new LinkedList<Domain>();
        if (domain.getClass() == clazz) {
            domains.add(domain);
        } else if (domain instanceof MixedDomain) {
            for (Domain d : ((MixedDomain) domain).getDomains()) {
                domains.addAll(getDomains(d, clazz));
            }
        }
        return domains;
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
        pnlDomainParameters = new javax.swing.JPanel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(SelectDomainPanel.class);
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

        javax.swing.GroupLayout pnlDomainParametersLayout = new javax.swing.GroupLayout(pnlDomainParameters);
        pnlDomainParameters.setLayout(pnlDomainParametersLayout);
        pnlDomainParametersLayout.setHorizontalGroup(
            pnlDomainParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlDomainParametersLayout.setVerticalGroup(
            pnlDomainParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

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
            .addComponent(pnlDomainParameters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboDomain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDomainParameters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboDomainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDomainActionPerformed
        if ("Categorical".equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SelectCategoricalDomainPanel((CategoricalDomain) getDomains(domain, CategoricalDomain.class).get(0));
        } else if ("Integer".equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SelectIntegerDomainPanel();
        } else if ("Mixed".equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SelectMixedDomainPanel(domain);
        } else if ("Ordinal".equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SelectOrdinalDomainPanel((OrdinalDomain) getDomains(domain, OrdinalDomain.class).get(0));
        } else if ("Real".equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SelectRealDomainPanel();
        } else if ("Flag".equals(comboDomain.getSelectedItem())) {
            pnlDomain = new SelectFlagDomainPanel();
        } else {
            pnlDomain = null;
        }
        pnlDomainParameters.removeAll();
        if (pnlDomain == null) {
            pnlDomainParameters.setVisible(false);
        } else {
            ((JPanel) pnlDomain).invalidate();
            ((JPanel) pnlDomain).revalidate();
            pnlDomainParameters.setVisible(true);

            pnlDomainParameters.add(((JPanel) pnlDomain), BorderLayout.CENTER);
        }
        this.invalidate();
        this.revalidate();
        this.repaint();
}//GEN-LAST:event_comboDomainActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox comboDomain;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlDomainParameters;
    // End of variables declaration//GEN-END:variables

    @Override
    public Domain getDomain() throws InvalidDomainException {
        if (pnlDomain != null) {
            return pnlDomain.getDomain();
        }
        if ("Optional".equals(comboDomain.getSelectedItem())) {
            return new OptionalDomain();
        }
        throw new InvalidDomainException("No domain selected.");
    }

    @Override
    public void setDomain(Domain orDomain, Domain andDomain) throws InvalidDomainException {
        pnlDomain = null;
        this.domain = andDomain;
        
        if (domain instanceof CategoricalDomain) {
            comboDomain.setSelectedItem("Categorical");
            pnlDomain = new SelectCategoricalDomainPanel((CategoricalDomain) orDomain);
        } else if (domain instanceof IntegerDomain) {
            comboDomain.setSelectedItem("Integer");
            pnlDomain = new SelectIntegerDomainPanel();
        } else if (domain instanceof MixedDomain) {
            comboDomain.setSelectedItem("Mixed");
            pnlDomain = new SelectMixedDomainPanel(orDomain);
        } else if (domain instanceof OrdinalDomain) {
            comboDomain.setSelectedItem("Ordinal");
            pnlDomain = new SelectOrdinalDomainPanel((OrdinalDomain) orDomain);
        } else if (domain instanceof RealDomain) {
            comboDomain.setSelectedItem("Real");
            pnlDomain = new SelectRealDomainPanel();
        } else if (domain instanceof FlagDomain) {
            comboDomain.setSelectedItem("Flag");
            pnlDomain = new SelectFlagDomainPanel();
        }
        
        if (pnlDomain != null) {
            pnlDomain.setDomain(orDomain, andDomain);
        }
        pnlDomainParameters.removeAll();
        if (pnlDomain == null) {
            pnlDomainParameters.setVisible(false);
        } else {
            ((JPanel) pnlDomain).invalidate();
            ((JPanel) pnlDomain).revalidate();
            pnlDomainParameters.setVisible(true);

            pnlDomainParameters.add(((JPanel) pnlDomain), BorderLayout.CENTER);
        }
        this.invalidate();
        this.revalidate();
        this.repaint();
    }
}
