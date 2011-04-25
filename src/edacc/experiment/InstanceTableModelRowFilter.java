/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edacc.experiment;

import java.util.HashSet;
import javax.swing.RowFilter;

public class InstanceTableModelRowFilter extends RowFilter<Object, Object> {
    public boolean filter_name;
    public String name;

    public boolean filter_numAtoms;
    public int numAtoms_low, numAtoms_high;

    public boolean filter_numClauses;
    public int numClauses_low, numClauses_high;

    public boolean filter_ratio;
    public float ratio_low, ratio_high;

    public boolean filter_maxClauseLength;
    public int maxClauseLength_low, maxClauseLength_high;

    private HashSet<Integer> instanceClassIds;
    private boolean filterInstanceClasses;
    public InstanceTableModelRowFilter() {
        filter_name = filter_numAtoms = filter_numClauses = filter_ratio = filter_maxClauseLength = false;
        instanceClassIds = new HashSet<Integer>();
        filterInstanceClasses = true;
    }

    public void addInstanceClassId(int id) {
        instanceClassIds.add(id);
    }

    public void removeInstanceClassId(int id) {
        instanceClassIds.remove(id);
    }

    public void clearInstanceClassIds() {
        instanceClassIds.clear();
    }

    @Override
    public boolean include(Entry entry) {
        if (entry == null) return false;

        //return include((String)entry.getValue(0), ((InstanceTableModel)entry.getModel()).getInstanceAt((Integer)entry.getIdentifier()).getInstanceClass().getId());
        return true;
     }


    public boolean include(String instance_name, Integer instanceClassId) {
        boolean incl = true;
        if (filterInstanceClasses && !instanceClassIds.contains(instanceClassId)) {
            incl = false;
        }
        if (incl && filter_name) {
            if (instance_name == null) instance_name = "";
            if (!instance_name.toLowerCase().contains(name.toLowerCase())) incl = false;
        }

        return incl;
    }

    public void setFilterInstanceClasses(boolean filterInstanceClasses) {
        this.filterInstanceClasses = filterInstanceClasses;
    }
}
