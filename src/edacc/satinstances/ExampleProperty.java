/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edacc.satinstances;

/**
 *
 * @author dgall
 */
public class ExampleProperty implements SATInstanceProperty {

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object computeProperty(SATInstance f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PropertyValueType<?> getPropertyValueType() {
        return PropertyValueTypeManager.getInstance().getPropertyValueTypeByName("Integer");
    }

  

}
