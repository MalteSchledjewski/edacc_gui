/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edacc.properties;

import edacc.EDACCManagePropertyDialog;
import edacc.model.NoConnectionToDBException;
import edacc.model.Parameter;
import edacc.model.ParameterDAO;
import edacc.model.Property;
import edacc.model.PropertyDAO;
import edacc.model.PropertyHasParameterDAO;
import edacc.model.PropertyHasParameterNotInDBException;
import edacc.model.PropertyIsUsedException;
import edacc.model.PropertyNotInDBException;
import edacc.satinstances.PropertyValueType;
import edacc.satinstances.PropertyValueTypeManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * Controller class of the EDACCManageSolverDialog
 * @author rretz
 */
public class SolverPropertiesController {
    private EDACCManagePropertyDialog main;
    private JPanel panel;
    private JTable tableSolverProperty;
    private int editId;

    /**
     * Constructor of the SolverPropertiesController
     * @param manage the EDACCManagePropertyDialog to controll
     * @param panelManageResultProperty main panel of the EDACCManagerSolverPropertyDialog
     * @param tableResultProperty the table which contains the Overview over all Property objects.
     */
    public SolverPropertiesController(EDACCManagePropertyDialog manage, JPanel panelManageResultProperty, JTable tableResultProperty) {
        this.main = manage;
        this.panel = panelManageResultProperty;
        this.tableSolverProperty = tableResultProperty;
        this.editId = -1;
    }

    /**
     * Enables or disables all input fields of the GUI which are required for editing and creating new SolverProperties
     * @param enable true for enable, false for disable
     */
    public void showSolverPropertyEditField(boolean enable) {
        main.enableSolverPropertyEditField(enable);
    }

    /**
     * Clears and enables the input fields in the gui.
     */
    public void NewSolverProperty() {
        showSolverPropertyEditField(true);
        clearSolverPropertyEditField();
        this.editId = -1;
    }

    /**
     * Clears the input fields of the gui.
     */
    private void clearSolverPropertyEditField() {
       main.clearSolverPropertyEditField();
    }

    /**
     * Enables and disables some of the input fields of the gui controlled by the selected PropertySource
     */
    public void SolverPropertyTypeChanged() {
        main.SolverPropertyTypeChanged();
    }

    /**
     * 
     * @throws NoConnectionToDBException
     * @throws SQLException
     * @throws SQLException
     * @throws PropertyNotInDBException
     * @throws SolverPropertyTypeNotExistException
     * @throws IOException
     */
    public void loadSolverProperties() 
            throws NoConnectionToDBException, SQLException, SQLException, PropertyNotInDBException,
        SolverPropertyTypeNotExistException, IOException {
        ((SolverPropertyTableModel)this.tableSolverProperty.getModel()).clear();
        ((SolverPropertyTableModel)this.tableSolverProperty.getModel()).addResultProperties(new Vector<Property>(PropertyDAO.getAll()));
    }

    public void loadPropertyValueTypes() throws IOException, NoConnectionToDBException, SQLException{
        Vector<PropertyValueType<?>> propValueTypes = PropertyValueTypeManager.getInstance().getAll();
        Vector<String> items = new Vector<String>();
        for(int i = 0; i < propValueTypes.size(); i++){
            items.add(propValueTypes.get(i).getName());
        }
        main.setComboBoxPropertyValueTypesItems(items);
    }

    public void removeSolverProperty(int convertRowIndexToModel) throws NoConnectionToDBException, SQLException, PropertyIsUsedException {
        Property toRemove = (Property)((SolverPropertyTableModel)tableSolverProperty.getModel()).getValueAt(convertRowIndexToModel, 5);
        PropertyDAO.remove(toRemove);
        ((SolverPropertyTableModel)tableSolverProperty.getModel()).removeSolverProperty(toRemove);
    }

    public void showSolver(int convertRowIndexToModel) {
        if(convertRowIndexToModel != -1){
            try {
                Property toShow = (Property) ((SolverPropertyTableModel)tableSolverProperty.getModel()).getValueAt(convertRowIndexToModel, 5);
                if (!toShow.getPropertySource().equals(PropertySource.Parameter)) {
                    main.showSolverProperty(toShow);
                } else {
                    main.showSolverProperty(toShow, PropertyHasParameterDAO.getByProperty(toShow));
                }
                this.editId = toShow.getId();
            } catch (NoConnectionToDBException ex) {
                Logger.getLogger(SolverPropertiesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SolverPropertiesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PropertyHasParameterNotInDBException ex) {
                Logger.getLogger(SolverPropertiesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PropertyNotInDBException ex) {
                Logger.getLogger(SolverPropertiesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SolverPropertyTypeNotExistException ex) {
                Logger.getLogger(SolverPropertiesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SolverPropertiesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else 
            this.editId = -1;
    }

    public void saveSolverProperty(String name, String prefix, String description, PropertySource propType, String parameter)
            throws NoConnectionToDBException, SQLException, PropertyNotInDBException, SolverPropertyTypeNotExistException, IOException, PropertyIsUsedException{

        if(editId != -1){
            Property toEdit = PropertyDAO.getById(editId);
            toEdit.setName(name);
            toEdit.setRegularExpression(prefix);
            toEdit.setDescription(description);
            PropertyDAO.save(toEdit);
        }else {
            PropertyDAO.createProperty(name, prefix, description, propType, parameter);
        }
        loadSolverProperties();
        main.clearSolverPropertyEditField();
    }

    public void loadParameters() throws NoConnectionToDBException, SQLException {
        main.setComboBoxParameters(ParameterDAO.getAllNames());
    }

     public void saveSolverProperty(String name, String prefix, String description, PropertySource propType, String valueType, boolean isMultiple)
            throws NoConnectionToDBException, SQLException, PropertyNotInDBException, SolverPropertyTypeNotExistException, IOException, PropertyIsUsedException{

        if(editId != -1){
            Property toEdit = PropertyDAO.getById(editId);
            toEdit.setName(name);
            toEdit.setRegularExpression(prefix);
            toEdit.setDescription(description);
            PropertyDAO.save(toEdit);
        }else {
            PropertyDAO.createResultProperty(name, prefix, description, PropertyValueTypeManager.getInstance().getPropertyValueTypeByName(valueType), propType, isMultiple);
        }
        loadSolverProperties();
        main.clearSolverPropertyEditField();
    }
      

}
