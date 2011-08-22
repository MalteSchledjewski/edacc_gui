/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EDACCManageComputationMethodDialog.java
 *
 * Created on 15.10.2010, 22:53:52
 */

package edacc;

import edacc.model.ComputationMethod;
import edacc.model.ComputationMethodAlreadyExistsException;
import edacc.model.ComputationMethodDoesNotExistException;
import edacc.model.ComputationMethodSameMD5AlreadyExists;
import edacc.model.ComputationMethodSameNameAlreadyExists;
import edacc.model.NoComputationMethodBinarySpecifiedException;
import edacc.model.NoConnectionToDBException;
import edacc.properties.ComputationMethodTableSelectionListener;
import edacc.properties.ComputationMethodTableModel;
import edacc.properties.ManageComputationMethodController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author rretz
 */
public class EDACCManageComputationMethodDialog extends javax.swing.JDialog {
    private ManageComputationMethodController controller;
    private ComputationMethodTableModel tableModel;
    private File selectedFile;
    private EDACCManagePropertyDialog main;

    /** Creates new form EDACCManageComputationMethodDialog */
    public EDACCManageComputationMethodDialog(java.awt.Frame parent, boolean modal, EDACCManagePropertyDialog main) {
        super(parent, modal);
        initComponents();

        this.main = main;
        // initialize the tableComputationMethod
        tableModel = new ComputationMethodTableModel();
        controller = new ManageComputationMethodController(this, panelMain, tableModel);
        tableComputationMethod.setModel(tableModel);
        tableComputationMethod.getSelectionModel().addListSelectionListener(new ComputationMethodTableSelectionListener(tableComputationMethod, controller));

    }

    private EDACCManageComputationMethodDialog(JFrame jFrame, boolean b) {
        super(jFrame, b);
        initComponents();
    }

    EDACCManageComputationMethodDialog(JFrame mainFrame, boolean b, EDACCCreateEditInstanceClassDialog aThis) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        panelMain = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableComputationMethod = new javax.swing.JTable();
        buttonNew = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();
        PanelEdit = new javax.swing.JPanel();
        panelDetails = new javax.swing.JPanel();
        labelName = new javax.swing.JLabel();
        textFieldName = new javax.swing.JTextField();
        labelDescription = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        labelBinary = new javax.swing.JLabel();
        buttonChooseBinary = new javax.swing.JButton();
        labelBinaryName = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        buttonDone = new javax.swing.JButton();

        jFileChooser1.setName("jFileChooser1"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edacc.EDACCApp.class).getContext().getResourceMap(EDACCManageComputationMethodDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(600, 440));
        setName("Form"); // NOI18N

        panelMain.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("panelMain.border.title"))); // NOI18N
        panelMain.setName("panelMain"); // NOI18N

        jSplitPane1.setDividerLocation(185);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        panelTable.setName("panelTable"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tableComputationMethod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableComputationMethod.setName("tableComputationMethod"); // NOI18N
        jScrollPane1.setViewportView(tableComputationMethod);

        buttonNew.setText(resourceMap.getString("buttonNew.text")); // NOI18N
        buttonNew.setName("buttonNew"); // NOI18N
        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewActionPerformed(evt);
            }
        });

        buttonRemove.setText(resourceMap.getString("buttonRemove.text")); // NOI18N
        buttonRemove.setName("buttonRemove"); // NOI18N
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                    .addGroup(panelTableLayout.createSequentialGroup()
                        .addComponent(buttonNew)
                        .addGap(18, 18, 18)
                        .addComponent(buttonRemove)))
                .addContainerGap())
        );

        panelTableLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonNew, buttonRemove});

        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNew)
                    .addComponent(buttonRemove))
                .addGap(16, 16, 16))
        );

        jSplitPane1.setLeftComponent(panelTable);

        PanelEdit.setName("PanelEdit"); // NOI18N

        panelDetails.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelDetails.setName("panelDetails"); // NOI18N

        labelName.setText(resourceMap.getString("labelName.text")); // NOI18N
        labelName.setName("labelName"); // NOI18N

        textFieldName.setText(resourceMap.getString("textFieldName.text")); // NOI18N
        textFieldName.setDragEnabled(true);
        textFieldName.setEnabled(false);
        textFieldName.setName("textFieldName"); // NOI18N

        labelDescription.setText(resourceMap.getString("labelDescription.text")); // NOI18N
        labelDescription.setName("labelDescription"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        textAreaDescription.setColumns(20);
        textAreaDescription.setRows(5);
        textAreaDescription.setWrapStyleWord(true);
        textAreaDescription.setEnabled(false);
        textAreaDescription.setName("textAreaDescription"); // NOI18N
        jScrollPane2.setViewportView(textAreaDescription);

        labelBinary.setText(resourceMap.getString("labelBinary.text")); // NOI18N
        labelBinary.setName("labelBinary"); // NOI18N

        buttonChooseBinary.setText(resourceMap.getString("buttonChooseBinary.text")); // NOI18N
        buttonChooseBinary.setEnabled(false);
        buttonChooseBinary.setName("buttonChooseBinary"); // NOI18N
        buttonChooseBinary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChooseBinaryActionPerformed(evt);
            }
        });

        labelBinaryName.setText(resourceMap.getString("labelBinaryName.text")); // NOI18N
        labelBinaryName.setName("labelBinaryName"); // NOI18N

        javax.swing.GroupLayout panelDetailsLayout = new javax.swing.GroupLayout(panelDetails);
        panelDetails.setLayout(panelDetailsLayout);
        panelDetailsLayout.setHorizontalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelName)
                    .addComponent(labelDescription)
                    .addComponent(labelBinary))
                .addGap(33, 33, 33)
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailsLayout.createSequentialGroup()
                        .addComponent(buttonChooseBinary)
                        .addGap(18, 18, 18)
                        .addComponent(labelBinaryName))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addComponent(textFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDetailsLayout.setVerticalGroup(
            panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetailsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelDescription))
                    .addGroup(panelDetailsLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(panelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonChooseBinary)
                    .addComponent(labelBinary)
                    .addComponent(labelBinaryName))
                .addContainerGap())
        );

        buttonSave.setText(resourceMap.getString("buttonSave.text")); // NOI18N
        buttonSave.setEnabled(false);
        buttonSave.setName("buttonSave"); // NOI18N
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelEditLayout = new javax.swing.GroupLayout(PanelEdit);
        PanelEdit.setLayout(PanelEditLayout);
        PanelEditLayout.setHorizontalGroup(
            PanelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSave))
                .addContainerGap())
        );
        PanelEditLayout.setVerticalGroup(
            PanelEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(PanelEdit);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addContainerGap())
        );

        buttonDone.setText(resourceMap.getString("buttonDone.text")); // NOI18N
        buttonDone.setName("buttonDone"); // NOI18N
        buttonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDone, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDone)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewActionPerformed
        controller.newComputationMethod();
    }//GEN-LAST:event_buttonNewActionPerformed

    private void buttonChooseBinaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseBinaryActionPerformed
        jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = jFileChooser1.showOpenDialog(panelMain);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
             selectedFile = jFileChooser1.getSelectedFile();
             labelBinaryName.setText(selectedFile.getName());
        }
    }//GEN-LAST:event_buttonChooseBinaryActionPerformed

    private void buttonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDoneActionPerformed
        this.setVisible(false);
        main.loadComputationMethods();
    }//GEN-LAST:event_buttonDoneActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
        if(tableComputationMethod.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this,
                "Nothing is selected. Select a computation method in the table.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }else {
            try {
                controller.removeComputationMethod(tableComputationMethod.convertRowIndexToModel(tableComputationMethod.getSelectedRow()));
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ComputationMethodAlreadyExistsException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoComputationMethodBinarySpecifiedException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ComputationMethodSameNameAlreadyExists ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ComputationMethodSameMD5AlreadyExists ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_buttonRemoveActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(textFieldName.getText().equals("")){
             JOptionPane.showMessageDialog(this,
                "You must specify the name of the computation method.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }else if(labelBinaryName.getText().equals("")){
            JOptionPane.showMessageDialog(this,
                "You have to select a binary for the computation method.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }else {
            try {
                controller.saveComputationMethod(textFieldName.getText(), textAreaDescription.getText(), selectedFile);
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ComputationMethodDoesNotExistException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ComputationMethodAlreadyExistsException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoComputationMethodBinarySpecifiedException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ComputationMethodSameNameAlreadyExists ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ComputationMethodSameMD5AlreadyExists ex) {
                Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EDACCManageComputationMethodDialog dialog = new EDACCManageComputationMethodDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelEdit;
    private javax.swing.JButton buttonChooseBinary;
    private javax.swing.JButton buttonDone;
    private javax.swing.JButton buttonNew;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JButton buttonSave;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelBinary;
    private javax.swing.JLabel labelBinaryName;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelName;
    private javax.swing.JPanel panelDetails;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPanel panelTable;
    private javax.swing.JTable tableComputationMethod;
    private javax.swing.JTextArea textAreaDescription;
    private javax.swing.JTextField textFieldName;
    // End of variables declaration//GEN-END:variables

      public void initialize(){
        try {
            controller.loadComputationMethods();
            clearInputFields();
        } catch (NoConnectionToDBException ex) {
            Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ComputationMethodDoesNotExistException ex) {
            Logger.getLogger(EDACCManageComputationMethodDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

    public void clearInputFields() {
        textFieldName.setText("");
        textAreaDescription.setText("");
        selectedFile = null;
        labelBinaryName.setText("");
    }

    public void enableInputFields(boolean enable) {
        textFieldName.setEnabled(enable);
        textAreaDescription.setEnabled(enable);
        buttonChooseBinary.setEnabled(enable);
        buttonSave.setEnabled(enable);
    }

    public void showComputationMethod(ComputationMethod computationMethod) {
        textFieldName.setText(computationMethod.getName());
        textAreaDescription.setText(computationMethod.getDescription());
        labelBinaryName.setText(computationMethod.getBinaryName());
    }

    public void enableEditInputFields(boolean b) {
        textFieldName.setEnabled(b);
        textAreaDescription.setEnabled(b);
        buttonSave.setEnabled(b);
    }

}
