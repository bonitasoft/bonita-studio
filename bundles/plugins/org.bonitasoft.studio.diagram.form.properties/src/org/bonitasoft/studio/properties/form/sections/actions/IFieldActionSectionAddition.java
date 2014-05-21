package org.bonitasoft.studio.properties.form.sections.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public interface IFieldActionSectionAddition {

	void refresh(EditingDomain editingDomain,EObject eObject);
	
	void initControl(Composite mainComposite, TabbedPropertySheetWidgetFactory tabbedPropertySheetWidgetFactory);
	
	
	
}
