/**
 * Copyright (C) 2009-2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.palette.FormPaletteLabelProvider;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.AbstractTable;
import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.SingleValuatedFormField;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 *
 * display a combo that allow to change the type of a form field
 *
 * @author Aurelien Pupier
 * @author Baptiste Mesta
 *
 */
public class FormFielTypeSelectionGridPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

	private ComboViewer combo;
	private Widget formField;
	private GraphicalEditPart node;
	private final List<Listener> listeners;
	private final HashMap<String, EClass> eClasses;
	private ArrayList<String> labels;
	private final ArrayList<String> labels_single;
	private final ArrayList<String> labels_table;
	private final ArrayList<String> labels_multiplelist;
	private final ArrayList<String> labels_singlelist;
	private final ArrayList<String> labels_button;
	private final ArrayList<String> labels_info;
	private String last;
	private TransactionalEditingDomain editingDomain;

	public FormFielTypeSelectionGridPropertySectionContribution(final TabbedPropertySheetPage tabbedPropertySheetPage) {
		listeners = new ArrayList<Listener>();
		eClasses = new HashMap<String, EClass>();
		labels_single = new ArrayList<String>();
		labels_table = new ArrayList<String>();
		labels_multiplelist = new ArrayList<String>();
		labels_singlelist = new ArrayList<String>();
		labels_button = new ArrayList<String>();
		labels_info = new ArrayList<String>();
		labels = new ArrayList<String>();

		// eclasses and labels must be set in the same order
        final FormPaletteLabelProvider formPaletteLabelProvider = new FormPaletteLabelProvider();
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD),
                FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.DATE_FORM_FIELD), FormPackage.Literals.DATE_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.LIST_FORM_FIELD), FormPackage.Literals.LIST_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.PASSWORD_FORM_FIELD), FormPackage.Literals.PASSWORD_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.RADIO_FORM_FIELD), FormPackage.Literals.RADIO_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.SELECT_FORM_FIELD), FormPackage.Literals.SELECT_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TEXT_INFO), FormPackage.Literals.TEXT_INFO);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TEXT_FORM_FIELD), FormPackage.Literals.TEXT_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TEXT_AREA_FORM_FIELD), FormPackage.Literals.TEXT_AREA_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.SUBMIT_FORM_BUTTON), FormPackage.Literals.SUBMIT_FORM_BUTTON);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.PREVIOUS_FORM_BUTTON), FormPackage.Literals.PREVIOUS_FORM_BUTTON);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.NEXT_FORM_BUTTON), FormPackage.Literals.NEXT_FORM_BUTTON);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.FORM_BUTTON), FormPackage.Literals.FORM_BUTTON);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.MESSAGE_INFO), FormPackage.Literals.MESSAGE_INFO);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TEXT_FORM_FIELD), FormPackage.Literals.TEXT_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD),
                FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.DURATION_FORM_FIELD), FormPackage.Literals.DURATION_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.HIDDEN_WIDGET), FormPackage.Literals.HIDDEN_WIDGET);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD),
                FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TABLE), FormPackage.Literals.TABLE);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.DYNAMIC_TABLE), FormPackage.Literals.DYNAMIC_TABLE);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.IFRAME_WIDGET), FormPackage.Literals.IFRAME_WIDGET);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.HTML_WIDGET), FormPackage.Literals.HTML_WIDGET);
        eClasses.put(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.SUGGEST_BOX), FormPackage.Literals.SUGGEST_BOX);

        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.DATE_FORM_FIELD));
        labels_multiplelist.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.LIST_FORM_FIELD));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.PASSWORD_FORM_FIELD));
        labels_singlelist.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.RADIO_FORM_FIELD));
        labels_singlelist.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.SELECT_FORM_FIELD));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TEXT_FORM_FIELD));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TEXT_AREA_FORM_FIELD));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD));
        labels_singlelist.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.SUGGEST_BOX));
        labels_button.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.SUBMIT_FORM_BUTTON));
        labels_button.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.PREVIOUS_FORM_BUTTON));
        labels_button.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.NEXT_FORM_BUTTON));
        labels_button.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.FORM_BUTTON));
		// labels_button.add(FORMFIELD_TYPE__CUSTOMIZED);
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.MESSAGE_INFO));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TEXT_INFO));
        labels_info.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.MESSAGE_INFO));
        labels_info.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TEXT_INFO));
        labels_multiplelist.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.DURATION_FORM_FIELD));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.HIDDEN_WIDGET));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.IFRAME_WIDGET));
        labels_single.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.HTML_WIDGET));

        labels_table.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.DYNAMIC_TABLE));
        labels_table.add(formPaletteLabelProvider.getFormPaletteText(FormPackage.Literals.TABLE));

		Collections.sort(labels_single);
		Collections.sort(labels_button);
		Collections.sort(labels_multiplelist);
		Collections.sort(labels_singlelist);
		Collections.sort(labels_table);
	}

	public void refresh() {
		if (formField != null && combo != null) {
			for (int i = 0; i < labels.size(); i++) {
				if (formField.eClass().equals(eClasses.get(labels.get(i)))) {
					combo.setSelection(new StructuredSelection(labels.get(i)));
					break;
				}
			}
		}
	}

	/**
	 * @param aTabbedPropertySheetPage
	 * @param parent
	 */
	private void createformFieldSelectionCombo(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory) {
		if (formField instanceof AbstractTable) {
			labels = labels_table;
		} else if (formField.eClass().getESuperTypes().contains(FormPackage.Literals.SINGLE_VALUATED_FORM_FIELD)) {
			labels = labels_single;
		} else if (formField instanceof CheckBoxMultipleFormField || formField instanceof ListFormField) {
			labels = labels_multiplelist;
		} else if (formField instanceof RadioFormField || formField instanceof SelectFormField || formField instanceof SuggestBox) {
			labels = labels_singlelist;
		} else if (formField.eClass().equals(FormPackage.Literals.FORM_BUTTON) || formField.eClass().getESuperTypes().contains(FormPackage.Literals.FORM_BUTTON)) {
			labels = labels_button;
		} else if (formField.eClass().getESuperTypes().contains(FormPackage.Literals.INFO)) {
			labels = labels_single;
		}
		combo = new ComboViewer(parent, SWT.READ_ONLY);
		combo.setContentProvider(new ArrayContentProvider());
		combo.setLabelProvider(new LabelProvider());
		combo.setInput(labels);
		combo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent arg0) {
				if (toBeConverted()) {
					if (openWarning()) {
						final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
						if (editor instanceof FormDiagramEditor) {
							final EClass targetEClass = getTargetEClass();
							if (targetEClass.equals(FormPackage.Literals.HIDDEN_WIDGET) || targetEClass.equals(FormPackage.Literals.MESSAGE_INFO)) {
								editingDomain.getCommandStack().execute(
										new SetCommand(editingDomain, formField, FormPackage.Literals.WIDGET__DISPLAY_LABEL, null));
							}
							node = (GraphicalEditPart) ((IStructuredSelection) ((FormDiagramEditor) editor).getDiagramGraphicalViewer().getSelection())
									.getFirstElement();

							GMFTools.convert(targetEClass, node, new FormFieldsElementTypeResolver(), GMFTools.FORM_DIAGRAM);
							for(final IViewReference vr : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences() ){
								if(vr.getId().startsWith("org.bonitasoft.studio.views.properties")){
									final IViewPart viewPart = vr.getView(false) ;
									if(viewPart != null){
										final IPropertySheetPage page = (IPropertySheetPage) viewPart.getAdapter(IPropertySheetPage.class);
										if(page != null){
											page.selectionChanged(editor, ((FormDiagramEditor)editor).getDiagramGraphicalViewer().getSelection());
										}
									}
								}
							}
						}
					} else {
						combo.setSelection(new StructuredSelection(last));
					}
				}

				last = (String) ((IStructuredSelection) combo.getSelection()).getFirstElement();
			}
		});
	}

	/**
	 * @return
	 */
	private EClass getTargetEClass() {
		for (int i = 0; i < labels.size(); i++) {
			if (((IStructuredSelection) combo.getSelection()).getFirstElement().equals(labels.get(i))) {
				return eClasses.get(labels.get(i));
			}
		}
		return FormPackage.Literals.FORM_FIELD;
	}

	/**
	 * @return
	 */
	private boolean toBeConverted() {
		return !formField.eClass().equals(getTargetEClass());
	}

	/**
	 *
	 */
	protected boolean openWarning() {
		if (formField instanceof SingleValuatedFormField) {
			// validators will be deleted
			final String selectedText = (String) ((IStructuredSelection) combo.getSelection()).getFirstElement();
			if (!((SingleValuatedFormField) formField).getValidators().isEmpty() && selectedText != null && labels_info.contains(selectedText)) {
				return MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.ChangeType_confirm_title,
						Messages.ChangeType_confirm_msg);
			}

		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution
	 * #createControl(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory)
	 */
	public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory, final ExtensibleGridPropertySection page) {
		composite.setLayout(new RowLayout());
		createformFieldSelectionCombo(composite, widgetFactory);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution#getLabel()
	 */
	public String getLabel() {
		return Messages.formFieldType;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution
	 * #isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(final EObject eObject) {
		// a widget but not a filewidget, imageWidget or widget group
		return eObject instanceof Widget && !(eObject instanceof FileWidget) && !(eObject instanceof ImageWidget) && !(eObject instanceof Group);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution
	 * #setEObject(org.eclipse.emf.ecore.EObject)
	 */
	public void setEObject(final EObject object) {
		formField = (Widget) object;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution
	 * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
	 */
	public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution
	 * #setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(final ISelection selection) {
		node = (GraphicalEditPart) ((IStructuredSelection) selection).getFirstElement();
	}

	/**
	 * Adds a custom listener to add behavior when the type is changed. Typical
	 * use is to refresh some UI parts. The event value for
	 * {@link Listener#handleEvent(Event)} contains the new {@link EditPart} as
	 * Data.
	 *
	 * @param listener
	 */
	public void addTypeChangedListener(final Listener listener) {
		listeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.bonitasoft.studio.common.properties.
	 * IExtensibleGridPropertySectionContribution#dispose()
	 */
	public void dispose() {
	}

}
