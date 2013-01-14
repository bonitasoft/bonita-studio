package org.bonitasoft.studio.properties.form.sections.options.contributions;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class MandatoryFieldContribution implements IExtensibleGridPropertySectionContribution {

	private Widget widget;
	private TransactionalEditingDomain editingDomain;
	private Button check;
	private SelectionListener listener = new SelectionListener() {

		public void widgetSelected(SelectionEvent e) {
			updateWidget();
		}

		public void widgetDefaultSelected(SelectionEvent e) {
		}
	};
	private Link mandatoryStyleLink;

	public void createControl(final Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {

		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, false, true);
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(2,false));
		check = widgetFactory.createButton(composite, " ", SWT.CHECK); //$NON-NLS-1$
		check.setToolTipText(Messages.isMandatory_tooltip);
		if(ModelHelper.getParentProcess(widget) != null){
			mandatoryStyleLink = new Link(composite, SWT.NONE);
			mandatoryStyleLink.setBackground(composite.getBackground());
			mandatoryStyleLink.setText("<A>" + Messages.customizeMandatorySymbol + "</A>");
			mandatoryStyleLink.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(ModelHelper.getParentProcess(widget) != null){
						openMandatoryStyleWizard(composite.getShell(), ModelHelper.getParentProcess(widget));
					}
				}
			});
		}
		check.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(mandatoryStyleLink != null){
					mandatoryStyleLink.setVisible(check.getSelection());
				}
			}
		});
	}

	/**
	 * @param shell 
	 * 
	 */
	protected void openMandatoryStyleWizard(Shell shell, MandatoryFieldsCustomization mandatoryCusto) {
		new WizardDialog(shell, new CustomMandatoryFeedbackWizard(mandatoryCusto, editingDomain)).open();
	}

	public void dispose() {

	}

	public String getLabel() {
		return Messages.isMandatory;
	}

	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof FormField && !(eObject instanceof HiddenWidget) || eObject instanceof Group;
	}

	public void refresh() {
		if(check != null && !check.isDisposed()){
			removeListeners();
			check.setSelection(widget.isMandatory());
			if(mandatoryStyleLink != null){
				mandatoryStyleLink.setVisible(widget.isMandatory());
			}
			addListeners();
		}

	}

	private void addListeners() {
		check.addSelectionListener(listener);
	}

	private void removeListeners() {
		check.removeSelectionListener(listener);
	}

	public void setEObject(EObject object) {
		widget = (Widget) object;
	}

	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;

	}

	public void setSelection(ISelection selection) {
	}

	private void updateWidget() {
		editingDomain.getCommandStack().execute(
				new SetCommand(editingDomain, widget, FormPackage.Literals.WIDGET__MANDATORY, check.getSelection()));
	}

}
