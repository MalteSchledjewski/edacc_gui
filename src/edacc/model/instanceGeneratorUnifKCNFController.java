/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edacc.model;

import edacc.instanceGenerator.unifRandomKSAT;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author balint
 */
public class instanceGeneratorUnifKCNFController {

    /**
     *
     * @param k the length of the clause
     * @param r the ratio between the number of valuses and the number of variables
     * @param n the number of variables
     * @param series specifies if a series of instances is desired
     * @param step if a series of instances is desired this specifies the steps between two elements of the series
     * @param stop where the series should stop
     * @param num number of instances per class
     * @param genClass generate instance classes
     */
    public void generate(int k, double r, int n, boolean series, int step, int stop, int num, boolean genClass, InstanceClass parent) throws SQLException, InstanceClassAlreadyInDBException {
        unifRandomKSAT instance;
        InstanceDAO instanceController = new InstanceDAO();
        InstanceClass ic;
        //parent classe braucht man noch
        String className = "";
        if (!series) {
            stop = n; //the next for-iteration will be executed only once
            step = 1;
        }
        for (int i = n; i <= stop; i = i + step) {
            if (genClass) {
                className = "v" + i;
                ic = InstanceClassDAO.createInstanceClass(className, "Automated generated Class by unif-KSAT Generator", parent, true);
            } else{
                ic=parent;
                className="";
            }
            //the input to the generator has to be correct 
            for (int j = 0; j < num; j++) {
                instance = new unifRandomKSAT(k, r, i);
                System.out.println(className + " : " + instance.suggestedFN());
                //add instance to the specified class
                try {
                    instanceController.createInstance(instance.suggestedFN(), instance.toDIMACS(), ic);
                } catch (Exception ex) {
                    Logger.getLogger(InstanceDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
