/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.callActivity;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.EMFFeatureLabelProvider;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;

public class OutputParametersMappingSection extends AbstractBonitaDescriptionSection {

    private ISharedImages sharedImages;

    private CallActivitySelectionProvider selectionProvider;

    private CallActivityHelper callActivityHelper;

    private MagicComposite outputMappingControl;

    private Composite mainComposite;

    private EMFDataBindingContext dbc;

    @Inject
    public OutputParametersMappingSection(RepositoryAccessor repositoryAccessor,
            CallActivitySelectionProvider selectionProvider, ISharedImages sharedImages) {
        this.selectionProvider = selectionProvider;
        this.callActivityHelper = new CallActivityHelper(repositoryAccessor, selectionProvider);
        this.sharedImages = sharedImages;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.subprocess.ParametersMappingSection#updateMappings()
     */
    protected void updateMappings() {
        final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
        for (final OutputMapping mapping : callActivity.getOutputMappings()) {
            addOutputMappingLine(outputMappingControl, mapping);
        }
        mainComposite.getParent().layout();
    }

    protected void refreshScrolledComposite(final Composite parent) {
        parent.getParent().getParent().layout(true, true);
        getTabbedPropertySheetPage().resizeScrolledComposite();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#createContent(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createContent(Composite parent) {
        dbc = new EMFDataBindingContext();
        mainComposite = getWidgetFactory().createComposite(parent);
        doCreateControls(mainComposite);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.subprocess.ParametersMappingSection#doCreateControls(org.eclipse.swt.widgets.Composite)
     */
    protected void doCreateControls(final Composite parent) {
        final GridLayout gridLayout = new GridLayout(3, false);
        parent.setLayout(gridLayout);
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        addOutputMappingControl(parent);
    }

    private void addOutputMappingControl(final Composite parent) {
        final Composite composite = getWidgetFactory().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        outputMappingControl = new MagicComposite(composite, SWT.NONE);
        outputMappingControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        getWidgetFactory().adapt(outputMappingControl);
        outputMappingControl.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(0, 0).create());

        final Label targetParameterLabel = getWidgetFactory().createLabel(outputMappingControl,
                Messages.dataFromCalledProcess);
        targetParameterLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).indent(15, 0).create());
        final Control sourceParameterLabel = getWidgetFactory().createLabel(outputMappingControl,
                Messages.dataInRootProcess);
        sourceParameterLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).create());

        final IObservableValue inputMappibngsObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                ProcessPackage.Literals.CALL_ACTIVITY__OUTPUT_MAPPINGS);
        dbc.bindValue(SWTObservables.observeVisible(sourceParameterLabel), inputMappibngsObservable,
                neverUpdateValueStrategy().create(), updateValueStrategy().withConverter(hideIfEmpty()).create());
        dbc.bindValue(SWTObservables.observeVisible(targetParameterLabel), inputMappibngsObservable,
                neverUpdateValueStrategy().create(), updateValueStrategy().withConverter(hideIfEmpty()).create());

        final Button addLineButton = getWidgetFactory().createButton(composite, Messages.Add, SWT.FLAT);
        addLineButton.setLayoutData(
                GridDataFactory.swtDefaults().hint(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).indent(15, 0).create());
        addLineButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_ADD_OUTPUT);
        addLineButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                createOutputMapping(null, null);
                refreshScrolledComposite(parent);
            }
        });

    }

    private IConverter hideIfEmpty() {
        return new Converter(List.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject instanceof List) {
                    return !((List<?>) fromObject).isEmpty();
                }
                return Boolean.FALSE;
            }
        };
    }

    protected void createOutputMapping(final Data target, final String source) {
        final OutputMapping outputMapping = ProcessFactory.eINSTANCE.createOutputMapping();
        if (target != null) {
            outputMapping.setProcessTarget(target);
        }
        if (source != null) {
            outputMapping.setSubprocessSource(source);
        }
        final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
        getEditingDomain().getCommandStack()
                .execute(new AddCommand(getEditingDomain(), callActivity.getOutputMappings(), outputMapping));
        addOutputMappingLine(outputMappingControl, outputMapping);
    }

    protected void addOutputMappingLine(final Composite outputMappingControl, final OutputMapping mapping) {
        final CCombo subprocessSourceCombo = createSubprocessSourceCombo(outputMappingControl, mapping);
        final Label assignToLabel = getWidgetFactory().createLabel(outputMappingControl, Messages.assignTo);
        final ComboViewer processTargetCombo = createProcessTargetCombo(outputMappingControl, mapping);

        final Button deleteButton = new Button(outputMappingControl, SWT.FLAT);
        deleteButton.setImage(sharedImages.getImage(ISharedImages.IMG_TOOL_DELETE));
        deleteButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
                final RemoveCommand command = new RemoveCommand(getEditingDomain(), callActivity.getOutputMappings(),
                        mapping);
                getEditingDomain().getCommandStack().execute(command);
                processTargetCombo.getControl().setData(MagicComposite.HIDDEN, true);
                processTargetCombo.getControl().setVisible(false);
                assignToLabel.setData(MagicComposite.HIDDEN, true);
                assignToLabel.setVisible(false);
                subprocessSourceCombo.setData(MagicComposite.HIDDEN, true);
                subprocessSourceCombo.setVisible(false);
                deleteButton.setData(MagicComposite.HIDDEN, true);
                deleteButton.setVisible(false);
                getTabbedPropertySheetPage().resizeScrolledComposite();
                refreshScrolledComposite(mainComposite);
            }
        });
    }

    private CCombo createSubprocessSourceCombo(final Composite outputMappingControl, final OutputMapping mapping) {
        final CCombo subprocessSourceCombo = getWidgetFactory().createCCombo(outputMappingControl, SWT.BORDER);
        for (final Data subprocessData : callActivityHelper.getCallActivityData()) {
            subprocessSourceCombo.add(subprocessData.getName());
        }
        subprocessSourceCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, 0).create());
        subprocessSourceCombo.addListener(SWT.Modify, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                getEditingDomain().getCommandStack()
                        .execute(
                                new SetCommand(getEditingDomain(), mapping,
                                        ProcessPackage.Literals.OUTPUT_MAPPING__SUBPROCESS_SOURCE, subprocessSourceCombo
                                                .getText()));
            }
        });
        if (mapping.getSubprocessSource() != null) {
            subprocessSourceCombo.setText(mapping.getSubprocessSource());
        }
        return subprocessSourceCombo;
    }

    private ComboViewer createProcessTargetCombo(final Composite outputMappingControl, final OutputMapping mapping) {
        final ComboViewer processTargetCombo = new ComboViewer(
                getWidgetFactory().createCCombo(outputMappingControl, SWT.READ_ONLY | SWT.BORDER));
        processTargetCombo.setContentProvider(new IStructuredContentProvider() {

            @Override
            public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public Object[] getElements(final Object inputElement) {
                final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
                return ModelHelper.getAccessibleData(callActivity).toArray();
            }
        });
        processTargetCombo.setLabelProvider(new EMFFeatureLabelProvider(ProcessPackage.Literals.ELEMENT__NAME));
        processTargetCombo.addPostSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.OUTPUT_MAPPING__PROCESS_TARGET,
                                ((IStructuredSelection) event
                                        .getSelection()).getFirstElement()));
            }
        });
        final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
        processTargetCombo.setInput(callActivity);
        processTargetCombo.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        if (mapping.getProcessTarget() != null) {
            processTargetCombo.setSelection(new StructuredSelection(mapping.getProcessTarget()));
        }
        return processTargetCombo;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        ISelection current = selectionProvider.getSelection();
        selectionProvider.setSelection(selection);

        if (!Objects.equals(current, selectionProvider.getSelection())) {
            BusyIndicator.showWhile(Display.getDefault(), () -> {
                /* Dispose and then redraw */
                for (final Control c : mainComposite.getChildren()) {
                    c.dispose();
                }
                doCreateControls(mainComposite);
                /* Fill with existing in/out mappings */
                updateMappings();
            });
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getSectionDescription()
     */
    @Override
    public String getSectionDescription() {
        return Messages.dataToRecieveSectionDescription;
    }
}
