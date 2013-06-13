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

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
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
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
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

	public static final String FORMFIELD_TYPE__CHECKBOX = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getCheckBoxSingleFormField());
	// public static final String FORMFIELD_TYPE__COMBO =
	// Messages.formFieldType_COMBO;
	public static final String FORMFIELD_TYPE__DATE = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getDateFormField());
	public static final String FORMFIELD_TYPE__LIST = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getListFormField());
	public static final String FORMFIELD_TYPE__PASSWORD = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getPasswordFormField());
	public static final String FORMFIELD_TYPE__RADIO = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getRadioFormField());
	public static final String FORMFIELD_TYPE__SELECT = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getSelectFormField());
	public static final String FORMFIELD_TYPE__TEXT = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getTextInfo());
	public static final String FORMFIELD_TYPE__TEXTAREA = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getTextAreaFormField());
	public static final String FORMFIELD_TYPE__TEXT_BOX = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getTextFormField());
	public static final String FORMFIELD_TYPE__MESSAGE = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getMessageInfo());
	public static final String FORMFIELD_TYPE__SUBMIT = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getSubmitFormButton());
	public static final String FORMFIELD_TYPE__PREVIOUS = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getPreviousFormButton());
	public static final String FORMFIELD_TYPE__NEXT = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getNextFormButton());
	public static final String FORMFIELD_TYPE__CHECKBOX_GROUP = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getCheckBoxMultipleFormField());
	public static final String FORMFIELD_TYPE__DURATION = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getDurationFormField());
	private static final String FORMFIELD_TYPE__HIDDEN = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getHiddenWidget());
	private static final String FORMFIELD_TYPE__RICH_TEXT_AREA = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getRichTextAreaFormField());
	private static final String FORMFIELD_TYPE__TABLE = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getTable());
	private static final String FORMFIELD_TYPE__EDITABLE_GRID = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getDynamicTable());
	private static final String FORMFIELD_TYPE__IFRAME_WIDGET = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getIFrameWidget());
	private static final String FORMFIELD_TYPE__HTML_WIDGET = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getHtmlWidget());
	private static final String FORMFIELD_TYPE__SUGGEST_BOX = NamingUtils.getFormPaletteText(false, FormPackage.eINSTANCE.getSuggestBox());
	// public static final String FORMFIELD_TYPE__CUSTOMIZED =
	// Messages.formFieldType_CUSTOMIZED;
	private ComboViewer combo;
	private Widget formField;
	private GraphicalEditPart node;
	private List<Listener> listeners;
	private HashMap<String, EClass> eClasses;
	private ArrayList<String> labels;
	private ArrayList<String> labels_single;
	private ArrayList<String> labels_table;
	private ArrayList<String> labels_multiplelist;
	private ArrayList<String> labels_singlelist;
	private ArrayList<String> labels_button;
	private ArrayList<String> labels_info;
	private String last;
	private TabbedPropertySheetPage tabbedPropertySheetPage;
	private TransactionalEditingDomain editingDomain;

	public FormFielTypeSelectionGridPropertySectionContribution(TabbedPropertySheetPage tabbedPropertySheetPage) {
		this.tabbedPropertySheetPage = tabbedPropertySheetPage;
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

		eClasses.put(FORMFIELD_TYPE__CHECKBOX, FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD);
		// eClasses.put(FORMFIELD_TYPE__COMBO,
		// FormPackage.Literals.COMBO_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__DATE, FormPackage.Literals.DATE_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__LIST, FormPackage.Literals.LIST_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__PASSWORD, FormPackage.Literals.PASSWORD_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__RADIO, FormPackage.Literals.RADIO_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__SELECT, FormPackage.Literals.SELECT_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__TEXT, FormPackage.Literals.TEXT_INFO);
		eClasses.put(FORMFIELD_TYPE__TEXTAREA, FormPackage.Literals.TEXT_AREA_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__SUBMIT, FormPackage.Literals.SUBMIT_FORM_BUTTON);
		eClasses.put(FORMFIELD_TYPE__PREVIOUS, FormPackage.Literals.PREVIOUS_FORM_BUTTON);
		eClasses.put(FORMFIELD_TYPE__NEXT, FormPackage.Literals.NEXT_FORM_BUTTON);
		eClasses.put(FORMFIELD_TYPE__MESSAGE, FormPackage.Literals.MESSAGE_INFO);
		eClasses.put(FORMFIELD_TYPE__TEXT_BOX, FormPackage.Literals.TEXT_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__CHECKBOX_GROUP, FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__DURATION, FormPackage.Literals.DURATION_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__HIDDEN, FormPackage.Literals.HIDDEN_WIDGET);
		eClasses.put(FORMFIELD_TYPE__RICH_TEXT_AREA, FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD);
		eClasses.put(FORMFIELD_TYPE__TABLE, FormPackage.Literals.TABLE);
		eClasses.put(FORMFIELD_TYPE__EDITABLE_GRID, FormPackage.Literals.DYNAMIC_TABLE);
		eClasses.put(FORMFIELD_TYPE__IFRAME_WIDGET, FormPackage.Literals.IFRAME_WIDGET);
		eClasses.put(FORMFIELD_TYPE__HTML_WIDGET, FormPackage.Literals.HTML_WIDGET);
		eClasses.put(FORMFIELD_TYPE__SUGGEST_BOX, FormPackage.Literals.SUGGEST_BOX);

		labels_single.add(FORMFIELD_TYPE__CHECKBOX);
		// labels_multiple.add(FORMFIELD_TYPE__COMBO);
		labels_single.add(FORMFIELD_TYPE__DATE);
		labels_multiplelist.add(FORMFIELD_TYPE__LIST);
		labels_single.add(FORMFIELD_TYPE__PASSWORD);
		labels_singlelist.add(FORMFIELD_TYPE__RADIO);
		labels_singlelist.add(FORMFIELD_TYPE__SELECT);
		labels_single.add(FORMFIELD_TYPE__TEXT_BOX);
		labels_single.add(FORMFIELD_TYPE__TEXTAREA);
		labels_single.add(FORMFIELD_TYPE__RICH_TEXT_AREA);
		labels_singlelist.add(FORMFIELD_TYPE__SUGGEST_BOX);
		labels_button.add(FORMFIELD_TYPE__SUBMIT);
		labels_button.add(FORMFIELD_TYPE__PREVIOUS);
		labels_button.add(FORMFIELD_TYPE__NEXT);
		// labels_button.add(FORMFIELD_TYPE__CUSTOMIZED);
		labels_single.add(FORMFIELD_TYPE__MESSAGE);
		labels_single.add(FORMFIELD_TYPE__TEXT);
		labels_info.add(FORMFIELD_TYPE__MESSAGE);
		labels_info.add(FORMFIELD_TYPE__TEXT);
		labels_multiplelist.add(FORMFIELD_TYPE__CHECKBOX_GROUP);
		labels_single.add(FORMFIELD_TYPE__DURATION);
		labels_single.add(FORMFIELD_TYPE__HIDDEN);
		labels_single.add(FORMFIELD_TYPE__IFRAME_WIDGET);
		labels_single.add(FORMFIELD_TYPE__HTML_WIDGET);

		labels_table.add(FORMFIELD_TYPE__EDITABLE_GRID);
		labels_table.add(FORMFIELD_TYPE__TABLE);

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
	private void createformFieldSelectionCombo(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
		if (formField instanceof AbstractTable) {
			labels = labels_table;
		} else if (formField.eClass().getESuperTypes().contains(FormPackage.Literals.SINGLE_VALUATED_FORM_FIELD)) {
			labels = labels_single;
		} else if (formField instanceof CheckBoxMultipleFormField || formField instanceof ListFormField) {
			labels = labels_multiplelist;
		} else if (formField instanceof RadioFormField || formField instanceof SelectFormField || formField instanceof SuggestBox) {
			labels = labels_singlelist;
		} else if (formField.eClass().getESuperTypes().contains(FormPackage.Literals.FORM_BUTTON)) {
			labels = labels_button;
		} else if (formField.eClass().getESuperTypes().contains(FormPackage.Literals.INFO)) {
			labels = labels_single;
		}
		combo = new ComboViewer(parent, SWT.READ_ONLY);
		combo.setContentProvider(new ArrayContentProvider());
		combo.setLabelProvider(new LabelProvider());
		combo.setInput(labels);
		combo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent arg0) {
				if (toBeConverted()) {
					if (openWarning()) {
						IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
						if (editor instanceof FormDiagramEditor) {
							EClass targetEClass = getTargetEClass();
							if (targetEClass.equals(FormPackage.Literals.HIDDEN_WIDGET) || targetEClass.equals(FormPackage.Literals.MESSAGE_INFO)) {
								editingDomain.getCommandStack().execute(
										new SetCommand(editingDomain, formField, FormPackage.Literals.WIDGET__DISPLAY_LABEL, null));
							}
							node = (GraphicalEditPart) ((IStructuredSelection) ((FormDiagramEditor) editor).getDiagramGraphicalViewer().getSelection())
									.getFirstElement();

							GMFTools.convert(targetEClass, node, new FormFieldsElementTypeResolver(), GMFTools.FORM_DIAGRAM);
							for(IViewReference vr : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences() ){
								if(vr.getId().startsWith("org.bonitasoft.studio.views.properties")){
									IViewPart viewPart = vr.getView(false) ;
									if(viewPart != null){
										IPropertySheetPage page = (IPropertySheetPage) viewPart.getAdapter(IPropertySheetPage.class);
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
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection page) {
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
	public boolean isRelevantFor(EObject eObject) {
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
	public void setEObject(EObject object) {
		this.formField = (Widget) object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution
	 * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
	 */
	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.bonitasoft.studio.properties.sections.general.
	 * IExtenstibleGridPropertySectionContribution
	 * #setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		this.node = (GraphicalEditPart) ((IStructuredSelection) selection).getFirstElement();
	}

	/**
	 * Adds a custom listener to add behavior when the type is changed. Typical
	 * use is to refresh some UI parts. The event value for
	 * {@link Listener#handleEvent(Event)} contains the new {@link EditPart} as
	 * Data.
	 * 
	 * @param listener
	 */
	public void addTypeChangedListener(Listener listener) {
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
