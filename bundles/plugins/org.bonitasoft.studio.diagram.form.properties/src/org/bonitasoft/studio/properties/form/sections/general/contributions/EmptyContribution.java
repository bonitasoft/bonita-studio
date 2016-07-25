package org.bonitasoft.studio.properties.form.sections.general.contributions;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class EmptyContribution implements IExtensibleGridPropertySectionContribution {

	private EClass eClass;

	public EmptyContribution(EClass eClass) {
		this.eClass = eClass;
	}
	
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false,2,1));
		composite.setLayout(new GridLayout(2, false));
	}

	public void dispose() {

	}

	public String getLabel() {
		return ""; //$NON-NLS-1$
	}

	public boolean isRelevantFor(EObject eObject) {
		return eClass.isSuperTypeOf(eObject.eClass());
	}

	public void refresh() {
	}

	public void setEObject(EObject object) {
	}

	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
	}

	public void setSelection(ISelection selection) {
	}

}
