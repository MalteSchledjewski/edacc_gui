/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edacc.experiment;

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

    public InstanceTableModelRowFilter() {
        filter_name = filter_numAtoms = filter_numClauses = filter_ratio = filter_maxClauseLength = false;
    }

    public boolean include(Entry entry) {
        if (entry == null) return false;
        boolean incl = true;

        if (incl && filter_name) {
            String instance_name = (String)entry.getValue(0);
            if (instance_name == null) instance_name = "";
            if (!instance_name.toLowerCase().contains(name.toLowerCase())) incl = false;
        }

        if (incl && filter_numAtoms) {
            int numAtoms = ((Integer)entry.getValue(1)).intValue();
            if (numAtoms < numAtoms_low || numAtoms > numAtoms_high) incl = false;
        }

        if (incl && filter_numClauses) {
            int numClauses = ((Integer)entry.getValue(2)).intValue();
            if (numClauses < numClauses_low || numClauses > numClauses_high) incl = false;
        }

        if (incl && filter_ratio) {
            float ratio = ((Float)entry.getValue(3)).floatValue();
            if (ratio < ratio_low || ratio > ratio_high) incl = false;
        }

        if (incl && filter_maxClauseLength) {
            int maxClauseLength = ((Integer)entry.getValue(4)).intValue();
            if (maxClauseLength < maxClauseLength_low || maxClauseLength > maxClauseLength_high) incl = false;
        }

        return incl;

    }
}
