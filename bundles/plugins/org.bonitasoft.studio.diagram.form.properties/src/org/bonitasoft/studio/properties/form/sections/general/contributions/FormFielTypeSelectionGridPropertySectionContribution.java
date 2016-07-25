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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.palette.FormPaletteLabelProvider;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.SingleValuatedFormField;
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
import org.eclipse.jface.viewers.ViewerSorter;
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
    private final List<Listener> listeners = new ArrayList<Listener>();
    private final List<EClass> buttonsEclasses = new ArrayList<EClass>();
    private final List<EClass> multipleListFieldEclasses = new ArrayList<EClass>();
    private final List<EClass> singleListFieldEclasses = new ArrayList<EClass>();
    private final List<EClass> infoFieldEclasses = new ArrayList<EClass>();
    private final List<EClass> singleFieldEclasses = new ArrayList<EClass>();
    private final List<EClass> tableFieldEclasses = new ArrayList<EClass>();
    private EClass lastSelection;
    private TransactionalEditingDomain editingDomain;


    public FormFielTypeSelectionGridPropertySectionContribution(final TabbedPropertySheetPage tabbedPropertySheetPage) {
        buttonsEclasses.addAll(Arrays.asList(FormPackage.Literals.SUBMIT_FORM_BUTTON,
                FormPackage.Literals.PREVIOUS_FORM_BUTTON,
                FormPackage.Literals.NEXT_FORM_BUTTON,
                FormPackage.Literals.FORM_BUTTON));

        multipleListFieldEclasses.addAll(Arrays.asList(FormPackage.Literals.LIST_FORM_FIELD,
                FormPackage.Literals.CHECK_BOX_MULTIPLE_FORM_FIELD));

        singleListFieldEclasses.addAll(Arrays.asList(FormPackage.Literals.RADIO_FORM_FIELD,
                FormPackage.Literals.SELECT_FORM_FIELD,
                FormPackage.Literals.SUGGEST_BOX));


        infoFieldEclasses.addAll(Arrays.asList(FormPackage.Literals.MESSAGE_INFO,
                FormPackage.Literals.TEXT_INFO));


        singleFieldEclasses.addAll(Arrays.asList(FormPackage.Literals.CHECK_BOX_SINGLE_FORM_FIELD,
                FormPackage.Literals.DATE_FORM_FIELD,
                FormPackage.Literals.PASSWORD_FORM_FIELD,
                FormPackage.Literals.TEXT_FORM_FIELD,
                FormPackage.Literals.TEXT_AREA_FORM_FIELD,
                FormPackage.Literals.RICH_TEXT_AREA_FORM_FIELD,
                FormPackage.Literals.MESSAGE_INFO,
                FormPackage.Literals.TEXT_INFO,
                FormPackage.Literals.DURATION_FORM_FIELD,
                FormPackage.Literals.HIDDEN_WIDGET,
                FormPackage.Literals.IFRAME_WIDGET,
                FormPackage.Literals.HTML_WIDGET));

        tableFieldEclasses.addAll(Arrays.asList(FormPackage.Literals.DYNAMIC_TABLE,
                FormPackage.Literals.TABLE));
    }

    public void refresh() {
    }

    /**
     * @param aTabbedPropertySheetPage
     * @param parent
     */
    private void createformFieldSelectionCombo(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory) {
        combo = new ComboViewer(parent, SWT.READ_ONLY);
        combo.setContentProvider(ArrayContentProvider.getInstance());
        final FormPaletteLabelProvider formPaletteLabelProvider = new FormPaletteLabelProvider();
        combo.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof EClass) {
                    return formPaletteLabelProvider.getFormPaletteText((EClass) element);
                }
                return super.getText(element);
            }
        });
        combo.setSorter(new ViewerSorter());
        combo.setInput(getEClassesFor(formField));
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
                            for (final IViewReference vr : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences()) {
                                if (vr.getId().startsWith("org.bonitasoft.studio.views.properties")) {
                                    final IViewPart viewPart = vr.getView(false);
                                    if (viewPart != null) {
                                        final IPropertySheetPage page = (IPropertySheetPage) viewPart.getAdapter(IPropertySheetPage.class);
                                        if (page != null) {
                                            page.selectionChanged(editor, ((FormDiagramEditor) editor).getDiagramGraphicalViewer().getSelection());
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        combo.setSelection(new StructuredSelection(lastSelection));
                    }
                }
                lastSelection = (EClass) ((IStructuredSelection) combo.getSelection()).getFirstElement();
            }
        });
        combo.setSelection(new StructuredSelection(formField.eClass()));
    }

    private List<EClass> getEClassesFor(final Widget formField) {
        final EClass eClass = formField.eClass();
        if (buttonsEclasses.contains(eClass)) {
            return buttonsEclasses;
        }
        if (multipleListFieldEclasses.contains(eClass)) {
            return multipleListFieldEclasses;
        }
        if (singleListFieldEclasses.contains(eClass)) {
            return singleListFieldEclasses;
        }

        if (infoFieldEclasses.contains(eClass)) {
            return infoFieldEclasses;
        }

        if (tableFieldEclasses.contains(eClass)) {
            return tableFieldEclasses;
        }

        if (singleFieldEclasses.contains(eClass)) {
            return singleFieldEclasses;
        }

        return Collections.emptyList();
    }

    /**
     * @return
     */
    private EClass getTargetEClass() {
        final Object element = ((IStructuredSelection) combo.getSelection()).getFirstElement();
        if (element instanceof EClass) {
            return (EClass) element;
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
            final EClass selectedEClass = (EClass) ((IStructuredSelection) combo.getSelection()).getFirstElement();
            if (!((SingleValuatedFormField) formField).getValidators().isEmpty() && infoFieldEclasses.contains(selectedEClass)) {
                return MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.ChangeType_confirm_title,
                        Messages.ChangeType_confirm_msg);
            }

        }
        return true;
    }

    /*
     * (non-Javadoc)
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
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#getLabel()
     */
    public String getLabel() {
        return Messages.formFieldType;
    }

    /*
     * (non-Javadoc)
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
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setEObject(final EObject object) {
        formField = (Widget) object;
    }

    /*
     * (non-Javadoc)
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /*
     * (non-Javadoc)
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(final ISelection selection) {
        node = (GraphicalEditPart) ((IStructuredSelection) selection).getFirstElement();
    }

    /**
     * Adds a custom listener to add behavior when the type is changed. Typical
     * use is to refresh some UI parts. The event value for {@link Listener#handleEvent(Event)} contains the new {@link EditPart} as
     * Data.
     *
     * @param listener
     */
    public void addTypeChangedListener(final Listener listener) {
        listeners.add(listener);
    }

    /*
     * (non-Javadoc)
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#dispose()
     */
    public void dispose() {
    }

}
