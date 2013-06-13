package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ItemContainer;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class ItemAlignmentContribution implements IExtensibleGridPropertySectionContribution {

	private EMFDataBindingContext dataBindingContext;
	private Button horizontal;
	private Button vertical;
	private ItemContainer itemContainer;
	private TransactionalEditingDomain editingDomain;

	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
			ExtensibleGridPropertySection extensibleGridPropertySection) {

		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = InitialValueContribution.MARGIN_HEIGHT;
		layout.marginWidth = InitialValueContribution.MARGIN_WIDTH;
		composite.setLayout(layout);
		vertical = widgetFactory.createButton(composite, Messages.Vertical, SWT.RADIO);
		horizontal = widgetFactory.createButton(composite, Messages.Horizontal, SWT.RADIO);
		if(dataBindingContext != null){
			dataBindingContext.dispose();
		}
		dataBindingContext = new EMFDataBindingContext();

		UpdateValueStrategy selectionToString = new UpdateValueStrategy().setConverter(new Converter(Boolean.class,String.class) {
			public Object convert(Object fromObject) {
				return (Boolean)fromObject ? "v":"h";
			}
		});
		UpdateValueStrategy stringToSelection = new UpdateValueStrategy().setConverter(new Converter(String.class,Boolean.class) {
			public Object convert(Object fromObject) {
				if(fromObject == null || ((String)fromObject).length()==0){
					return !(itemContainer instanceof DurationFormField);
				}else{
					return "v".equals((String)fromObject);
				}
			}
		});
		UpdateValueStrategy stringToNotSelection = new UpdateValueStrategy().setConverter(new Converter(String.class,Boolean.class) {
			public Object convert(Object fromObject) {
			if(fromObject == null || ((String)fromObject).length()==0){
				return itemContainer instanceof DurationFormField;
			}else{
				return !"v".equals((String)fromObject);
			}
			}
		});
		
		dataBindingContext.bindValue(SWTObservables.observeSelection(vertical),
				EMFEditObservables.observeValue(editingDomain, itemContainer, FormPackage.Literals.ITEM_CONTAINER__ITEM_CLASS),
				selectionToString,
				stringToSelection);
		
		dataBindingContext.bindValue(SWTObservables.observeSelection(horizontal),
				EMFEditObservables.observeValue(editingDomain, itemContainer, FormPackage.Literals.ITEM_CONTAINER__ITEM_CLASS),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				stringToNotSelection);
		
	}

	public void dispose() {
		if (dataBindingContext != null)
			dataBindingContext.dispose();

	}

	public String getLabel() {
		return Messages.Action_ItemAlign;
	}

	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof ItemContainer;
	}

	public void refresh() {
	}

	public void setEObject(EObject object) {
		this.itemContainer = (ItemContainer)object;
	}

	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	public void setSelection(ISelection selection) {
	}

}
