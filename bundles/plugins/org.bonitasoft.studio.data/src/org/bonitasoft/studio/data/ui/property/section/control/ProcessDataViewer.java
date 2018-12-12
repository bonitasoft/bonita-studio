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
package org.bonitasoft.studio.data.ui.property.section.control;

import java.util.List;

import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.data.commands.MoveDataCommand;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.ui.property.section.IAddData;
import org.bonitasoft.studio.data.ui.property.section.RemoveDataHandler;
import org.bonitasoft.studio.data.ui.wizard.DataWizardDialog;
import org.bonitasoft.studio.data.ui.wizard.MoveDataWizard;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class ProcessDataViewer extends DataViewer implements IAddData {

    private final DataWizardFactory dataWizardFactory;

    public ProcessDataViewer(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory, final EStructuralFeature dataFeature,
            final DataWizardFactory dataWizardFactory) {
        super(parent, widgetFactory, dataFeature);
        this.dataWizardFactory = dataWizardFactory;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#addButtons(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void addButtons(final Composite buttonsComposite) {
        super.addButtons(buttonsComposite);
        final Button moveButton = createButton(buttonsComposite, Messages.moveData, null);
        moveButton.setToolTipText(Messages.moveData_tooltip);
        moveButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                moveData(getStructuredSelection());
            }
        });
    }

    protected void moveData(final IStructuredSelection structuredSelection) {
        final DataAware container = (DataAware) getDataContainerObservable().getValue();
        final MoveDataWizard moveDataWizard = new MoveDataWizard(container);
        if (createWizardDialog(moveDataWizard, IDialogConstants.FINISH_LABEL).open() == Dialog.OK) {
            final DataAware dataAware = moveDataWizard.getSelectedDataAwareElement();
            try {
                final MoveDataCommand cmd = new MoveDataCommand(TransactionUtil.getEditingDomain(dataAware), container, structuredSelection.toList(), dataAware);
                OperationHistoryFactory.getOperationHistory().execute(cmd, null, null);

                if (!(cmd.getCommandResult().getStatus().getSeverity() == Status.OK)) {
                    final List<Object> data = (List<Object>) cmd.getCommandResult().getReturnValue();
                    String dataNames = "";
                    for (final Object d : data) {
                        dataNames = dataNames + ((Element) d).getName() + ",";
                    }
                    dataNames = dataNames.substring(0, dataNames.length() - 1);
                    MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.PromoteDataWarningTitle,
                            Messages.bind(Messages.PromoteDataWarningMessage, dataNames));
                }

            } catch (final ExecutionException e1) {
                BonitaStudioLog.error(e1);
            }
        }
    }

    @Override
    public void addData() {
        final DataAware container = (DataAware) getDataContainerObservable().getValue();
        if (new DataWizardDialog(Display.getDefault().getActiveShell(),
                dataWizardFactory.createAddWizard(TransactionUtil.getEditingDomain(container), container),
                this).open() == Dialog.OK) {
            RepositoryManager.getInstance().getCurrentRepository().buildXtext();
        }
    }

    private ViewerFilter hideBusinessObjectData() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return !(element instanceof BusinessObjectData);
            }
        };
    }

    @Override
    protected void removeData(final IObservableValue observable, final EStructuralFeature dataFeature) {
        new RemoveDataHandler().execute(getStructuredSelection(), (EObject) observable.getValue(), dataFeature);
    }

    @Override
    protected void editData() {
        final IStructuredSelection selection = getStructuredSelection();
        if (onlyOneElementSelected(selection)) {
            final Data selectedData = (Data) selection.getFirstElement();;
            createWizardDialog(dataWizardFactory.createEditWizard(TransactionUtil.getEditingDomain(selectedData),
                    selectedData), IDialogConstants.OK_LABEL).open();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewerComposite#addFilters(org.eclipse.jface.viewers.StructuredViewer)
     */
    @Override
    protected void addFilters(final StructuredViewer viewer) {
        viewer.addFilter(hideBusinessObjectData());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewerComposite#getTitle()
     */
    @Override
    protected String getTitle() {
        return Messages.processData;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewerComposite#getTitleDescripiton()
     */
    @Override
    protected String getTitleDescripiton() {
        return Messages.processDataHint;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#createLabelProvider(org.eclipse.core.databinding.observable.map.IObservableMap[])
     */
    @Override
    protected IBaseLabelProvider createLabelProvider(final IObservableMap[] labelMaps) {
        return new DataStyledTreeLabelProvider(labelMaps);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#getAddButtonId()
     */
    @Override
    protected String getAddButtonId() {
        return SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#getRemoveButtonId()
     */
    @Override
    protected String getRemoveButtonId() {
        return SWTBotConstants.SWTBOT_ID_REMOVE_PROCESS_DATA;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#getEditDataId()
     */
    @Override
    protected String getEditDataId() {
        return SWTBotConstants.SWTBOT_ID_EDIT_PROCESS_DATA;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#getTableId()
     */
    @Override
    protected String getTableId() {
        return SWTBotConstants.SWTBOT_ID_PROCESS_DATA_LIST;
    }

}
