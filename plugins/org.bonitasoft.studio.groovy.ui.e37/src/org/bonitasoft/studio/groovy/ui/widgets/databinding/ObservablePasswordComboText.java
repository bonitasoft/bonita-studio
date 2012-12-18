package org.bonitasoft.studio.groovy.ui.widgets.databinding;

import org.bonitasoft.studio.groovy.ui.widgets.PasswordCombo;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

public class ObservablePasswordComboText extends AbstractObservableValue {

	private PasswordCombo combo;
	private String oldValue;
	private ModifyListener listener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			String newValue = combo.getText();
			fireValueChange(Diffs.createValueDiff(oldValue, newValue));
			oldValue = newValue;
		}
	};

	public ObservablePasswordComboText(final PasswordCombo combo) {
		this.combo = combo;
		combo.addModifyListener(listener);
	}

	@Override
	protected Object doGetValue() {
		return combo.getText();
	}

	@Override
	protected void doSetValue(Object value) {
		if(value == null){
			combo.setText("");
		}
		if (value instanceof String) {
			combo.setText((String) value);
		}
	}

	public Object getValueType() {
		return String.class;
	}
	
	@Override
	public synchronized void dispose() {
		combo.removeModifyListener(listener);
		super.dispose();
	}

}
