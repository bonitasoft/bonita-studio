/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.parameters.property.section;

import static org.bonitasoft.studio.common.Messages.removalConfirmationDialogTitle;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.dialog.OutlineDialog;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.widgets.GTKStyleHandler;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.parameters.ParameterPlugin;
import org.bonitasoft.studio.parameters.action.RemoveParametersOperation;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.parameters.property.section.editingsupport.ParameterDescriptionEditingSupport;
import org.bonitasoft.studio.parameters.property.section.editingsupport.ParameterNameEditingSupport;
import org.bonitasoft.studio.parameters.property.section.editingsupport.ParameterTypeEditingSupport;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterStyledLabelProvider;
import org.bonitasoft.studio.parameters.wizard.page.AddParameterWizard;
import org.bonitasoft.studio.parameters.wizard.page.EditParameterWizard;
import org.bonitasoft.studio.parameters.wizard.page.ParameterWizardDialog;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.xtext.ui.XtextProjectHelper;

/**
 * @author Romain Bioteau
 */
public class ParameterPropertySection extends AbstractBonitaDescriptionSection
        implements ISelectionChangedListener, IDoubleClickListener {

    private TableViewer parameterTableViewer;

    private TabbedPropertySheetWidgetFactory widgetFactory;

    private Button removeButton;

    private ParameterNameEditingSupport nameEditingSupport;

    private ParameterTypeEditingSupport typeEditingSupport;

    private Button addButton;

    private ParameterDescriptionEditingSupport descriptionEditingSupport;

    protected ObservablesManager observablesManager = new ObservablesManager();

    private IChangeListener parametersListener;

    private IObservableList observeParametersList;

    private IObservableList observeParametersNameList;

    private IObservableList observeParametersDescriptionList;

    private IObservableList observeParametersTypeList;

    private Button updateButton;

    @Override
    protected void createContent(final Composite parent) {
        widgetFactory = getWidgetFactory();
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 180).create());

        final Composite mainComposite = widgetFactory.createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createParameterComposite(mainComposite);

        updateButtons();
    }

    protected void createParameterComposite(final Composite parent) {
        final Composite parameterComposite = getWidgetFactory().createComposite(parent);
        parameterComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        parameterComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());

        final Composite buttonsComposite = getWidgetFactory().createComposite(parameterComposite, SWT.NONE);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 3).create());
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());

        createAddParameterButton(buttonsComposite);
        updateButton = createUpdateParameterButton(buttonsComposite);
        createRemoveParameterButton(buttonsComposite);

        parameterTableViewer = new TableViewer(widgetFactory.createTable(parameterComposite,
                GTKStyleHandler.removeBorderFlag(SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS)));
        parameterTableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
        getWidgetFactory().adapt(parameterTableViewer.getTable(), true,true);
        parameterTableViewer.getTable()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 120).create());
        parameterTableViewer.setSorter(new ViewerSorter());
        parameterTableViewer.addDoubleClickListener(this);
        parameterTableViewer.addSelectionChangedListener(this);
        parameterTableViewer.getTable().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    e.doit = false;
                    remove();
                }
            }
        });

        parameterTableViewer.setContentProvider(new ArrayContentProvider());
        parameterTableViewer.setLabelProvider(new ParameterStyledLabelProvider());
        ColumnViewerToolTipSupport.enableFor(parameterTableViewer, ToolTip.RECREATE);
    }

    protected void createRemoveParameterButton(final Composite buttonsComposite) {
        removeButton = widgetFactory.createButton(buttonsComposite, Messages.remove, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                remove();
            }

        });

    }

    protected void remove() {
        final IStructuredSelection selection = (IStructuredSelection) parameterTableViewer.getSelection();
        final StringBuilder sb = new StringBuilder();
        for (final Object selectionElement : selection.toList()) {
            if (selectionElement instanceof Parameter) {
                sb.append(((Parameter) selectionElement).getName() + "\n");
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        final String[] buttonList = { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
        final List<Object> selectionList = ((IStructuredSelection) parameterTableViewer.getSelection()).toList();
        final OutlineDialog dialog = new OutlineDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                removalConfirmationDialogTitle, Display
                        .getCurrent().getSystemImage(SWT.ICON_WARNING),
                NLS.bind(Messages.areYouSureMessage, sb.toString()), MessageDialog.CONFIRM, buttonList,
                1, selectionList);
        final int ok = 0;
        if (ok == dialog.open()) {
            for (final Object parameter : selection.toList()) {
                final RemoveParametersOperation op = new RemoveParametersOperation((Parameter) parameter,
                        (AbstractProcess) getEObject());
                op.setEditingDomain(getEditingDomain());
                op.setAskConfirmation(true);
                final IProgressService service = PlatformUI.getWorkbench().getProgressService();
                try {
                    service.busyCursorWhile(op);
                } catch (final InvocationTargetException ex) {
                    BonitaStudioLog.error(ex);
                } catch (final InterruptedException ex) {
                    BonitaStudioLog.error(ex);
                }
            }
            parameterTableViewer.refresh();
            try {
                RepositoryManager.getInstance().getCurrentRepository().getProject()
                        .build(IncrementalProjectBuilder.FULL_BUILD, XtextProjectHelper.BUILDER_ID,
                                Collections.<String, String> emptyMap(), null);
            } catch (final CoreException e1) {
                BonitaStudioLog.error(e1, ParameterPlugin.PLUGIN_ID);
            }
        }
    }

    protected Parameter createDefaultParameter() {
        final Parameter param = ParameterFactory.eINSTANCE.createParameter();
        final AbstractProcess process = (AbstractProcess) getEObject();

        final Set<String> names = new HashSet<>();
        for (final Parameter p : process.getParameters()) {
            names.add(p.getName());
        }

        param.setName(NamingUtils.generateNewName(names, Messages.paramDefaultName, 1));
        param.setValue("");
        param.setDescription("");
        param.setTypeClassname(String.class.getName());
        return param;
    }

    protected void createAddParameterButton(final Composite buttonsComposite) {
        addButton = widgetFactory.createButton(buttonsComposite, Messages.add, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                openAddParameterWizardDialog();
            }
        });

    }

    private Button createUpdateParameterButton(final Composite parent) {
        final Button updateButton = getWidgetFactory().createButton(parent, Messages.updateParameter, SWT.FLAT);
        updateButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create());
        updateButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                updateParametersAction();
            }
        });
        return updateButton;
    }

    private void updateParametersAction() {
        final IStructuredSelection selection = (IStructuredSelection) parameterTableViewer.getSelection();
        if (selection.size() != 1) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.selectOnlyOneElementTitle,
                    Messages.selectOnlyOneElementMessage);
        } else {
            final EditParameterWizard parameterWizard = new EditParameterWizard(ModelHelper.getParentProcess(getEObject()),
                    (Parameter) selection.getFirstElement(),
                    getEditingDomain());

            final ParameterWizardDialog dialog = new ParameterWizardDialog(Display.getCurrent().getActiveShell(),
                    parameterWizard, IDialogConstants.OK_LABEL);

            dialog.open();
            parameterTableViewer.refresh();
        }
    }

    protected void updateButtons() {
        if (removeButton != null && !removeButton.isDisposed()) {
            removeButton.setEnabled(parameterTableViewer != null && parameterTableViewer.getSelection() != null
                    && !parameterTableViewer.getSelection().isEmpty());
        }
        if (updateButton != null && !updateButton.isDisposed()) {
            updateButton.setEnabled(parameterTableViewer != null && parameterTableViewer.getSelection() != null
                    && !parameterTableViewer.getSelection().isEmpty());
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(final EObject object) {
        super.setEObject(object);
        refreshBindings();
    }

    @Override
    protected void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        super.setEditingDomain(editingDomain);
        if (nameEditingSupport != null) {
            nameEditingSupport.setTransactionalEditingDomain(editingDomain);
            typeEditingSupport.setTransactionalEditingDomain(editingDomain);
            descriptionEditingSupport.setTransactionalEditingDomain(editingDomain);
        }
    }

    private void refreshBindings() {

        final AbstractProcess process = (AbstractProcess) getEObject();
        updateButtons();
        if (parameterTableViewer != null && getEObject() != null) {
            parameterTableViewer
                    .setInput(EMFEditObservables.observeList(getEditingDomain(), process, getParameterFeature()));
            parameterTableViewer.getTable().forceFocus();
            bindParametersTree();
        }

    }

    private void bindParametersTree() {
        if (observeParametersList != null
                && parametersListener != null
                && observeParametersNameList != null
                && observeParametersDescriptionList != null
                && observeParametersTypeList != null) {
            observeParametersList.removeChangeListener(parametersListener);
            observeParametersList.dispose();
            observeParametersNameList.removeChangeListener(parametersListener);
            observeParametersNameList.dispose();
            observeParametersDescriptionList.removeChangeListener(parametersListener);
            observeParametersDescriptionList.dispose();
            observeParametersTypeList.removeChangeListener(parametersListener);
            observeParametersTypeList.dispose();
        }
        final IEMFEditListProperty list = EMFEditProperties.list(getEditingDomain(), getParameterFeature());
        observeParametersList = list.observe(getEObject());
        observeParametersNameList = list.values(ParameterPackage.Literals.PARAMETER__NAME).observe(getEObject());
        observeParametersDescriptionList = list.values(ParameterPackage.Literals.PARAMETER__DESCRIPTION)
                .observe(getEObject());
        observeParametersTypeList = list.values(ParameterPackage.Literals.PARAMETER__TYPE_CLASSNAME).observe(getEObject());
        parametersListener = new IChangeListener() {

            @Override
            public void handleChange(final ChangeEvent event) {
                if (!parameterTableViewer.getTable().isDisposed()) {
                    parameterTableViewer.refresh();
                }

            }
        };
        observeParametersList.addChangeListener(parametersListener);
        observeParametersNameList.addChangeListener(parametersListener);
        observeParametersDescriptionList.addChangeListener(parametersListener);
        observeParametersTypeList.addChangeListener(parametersListener);
        updateButtons();
    }

    @Override
    protected EObject getEObject() {
        final EObject eobject = super.getEObject();
        return eobject instanceof Lane ? ModelHelper.getParentProcess(eobject) : eobject;
    }

    @Override
    public void refresh() {
        super.refresh();
        updateButtons();
    }

    @Override
    public String getSectionDescription() {
        return Messages.createOrRemoveParameterDetail;
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        updateParametersAction();
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        updateButtons();

    }

    protected EStructuralFeature getParameterFeature() {
        return ProcessPackage.Literals.ABSTRACT_PROCESS__PARAMETERS;
    }

    public void openAddParameterWizardDialog() {
        final AddParameterWizard parameterWizard = new AddParameterWizard(ModelHelper.getParentProcess(getEObject()),
                getEditingDomain());
        final ParameterWizardDialog dialog = new ParameterWizardDialog(Display.getCurrent().getActiveShell(),
                parameterWizard, this);
        if (dialog.open() == Dialog.OK) {
            parameterTableViewer.refresh();
        }
    }

}
