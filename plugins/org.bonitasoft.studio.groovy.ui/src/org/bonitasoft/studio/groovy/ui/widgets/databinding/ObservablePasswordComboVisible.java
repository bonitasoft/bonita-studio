package org.bonitasoft.studio.groovy.ui.widgets.databinding;

import org.bonitasoft.studio.groovy.ui.widgets.PasswordCombo;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;

public class ObservablePasswordComboVisible extends AbstractObservableValue  {

	private PasswordCombo combo;

	public ObservablePasswordComboVisible(PasswordCombo passwordCombo) {
		this.combo = passwordCombo;
	}
	
	@Override
	protected Object doGetValue() {
		return combo.getVisible();
	}

	public Object getValueType() {
		return Boolean.class;
	}
	
	@Override
	protected void doSetValue(Object value) {
		if(value instanceof Boolean){
			combo.setVisible((Boolean)value);
		}
	}

	

}
