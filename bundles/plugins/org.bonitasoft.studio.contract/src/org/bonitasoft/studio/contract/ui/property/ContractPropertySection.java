/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property;

import org.bonitasoft.studio.common.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.EObjectSelectionProviderSection;
import org.bonitasoft.studio.contract.core.ContractDefinitionValidator;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.table.ContractInputController;
import org.bonitasoft.studio.contract.ui.property.table.ContractInputTableViewer;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


/**
 * @author Romain Bioteau
 *
 */
public class ContractPropertySection extends EObjectSelectionProviderSection {

    private EMFDataBindingContext context;
    private ContractDefinitionValidator contractValidator;
    private ContractInputController inputController;

    @Override
    public String getSectionDescription() {
        return Messages.contractSectionDescription;
    }

    @Override
    public String getSectionTitle() {
        return Messages.contractInputs;
    }

    @Override
    protected void createContent(final Composite parent) {
        contractValidator = new ContractDefinitionValidator(
                getMessageManager());
        inputController = new ContractInputController(contractValidator);
        context = new EMFDataBindingContext();

        final CTabFolder tabFolder = getWidgetFactory().createTabFolder(parent, SWT.FLAT | SWT.TOP);
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        getWidgetFactory().adapt(tabFolder, true, true);

        final CTabItem inputTabItem = getWidgetFactory().createTabItem(tabFolder, SWT.NULL);
        inputTabItem.setText(Messages.inputTabLabel);
        final Composite inputComposite = getWidgetFactory().createComposite(tabFolder);
        inputComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        inputComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(15, 15, 10, 5).create());

        createInputTabContent(inputComposite);

        inputTabItem.setControl(inputComposite);

        final Composite constraintComposite = getWidgetFactory().createComposite(tabFolder);
        constraintComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        constraintComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(15, 15, 10, 5).create());

        final CTabItem constraintTabItem = getWidgetFactory().createTabItem(tabFolder, SWT.NULL);
        constraintTabItem.setText(Messages.constraintsTabLabel);
        constraintTabItem.setControl(constraintComposite);
        tabFolder.setSelection(0);
    }


    private void createInputTabContent(final Composite parent) {
        final Composite buttonsComposite = getWidgetFactory().createComposite(parent);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).align(SWT.FILL, SWT.TOP).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).extendedMargins(0, 0, 25, 0).create());

        final Button addButton = getWidgetFactory().createButton(buttonsComposite, Messages.add, SWT.PUSH);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());

        final Button removeButton = getWidgetFactory().createButton(buttonsComposite, Messages.remove, SWT.PUSH);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());

        final ContractInputTableViewer inputsTableViewer = new ContractInputTableViewer(parent, getWidgetFactory());
        inputsTableViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(500, 180).create());
        inputsTableViewer.initialize(inputController, contractValidator);

        final IObservableValue observeContractValue = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), getEObjectObservable(),
                ProcessPackage.Literals.TASK__CONTRACT);
        final IObservableList observeContractInputDetailList = CustomEMFEditObservables.observeDetailList(Realm.getDefault(), observeContractValue,
                ProcessPackage.Literals.CONTRACT__INPUTS);

        inputsTableViewer.setInput(observeContractInputDetailList);

        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                inputController.addInput(inputsTableViewer);
            }
        });

        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                inputController.removeInput(inputsTableViewer);
            }
        });
        observeContractValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                validate((Contract) event.diff.getNewValue());
            }
        });


        context.bindValue(SWTObservables.observeEnabled(removeButton),
                ViewersObservables.observeSingleSelection(inputsTableViewer),
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
                removeButtonEnablementModelStrategy());

        inputsTableViewer.getTable().setFocus();
    }

    private UpdateValueStrategy removeButtonEnablementModelStrategy() {
        final UpdateValueStrategy modelStrategy = new UpdateValueStrategy();
        modelStrategy.setConverter(new Converter(Object.class,Boolean.class) {

            @Override
            public Object convert(final Object from) {
                return from != null;
            }
        });
        return modelStrategy;
    }

    protected void validate(final Contract contract) {
        final ContractDefinitionValidator definitionValidator = new ContractDefinitionValidator(getMessageManager());
        definitionValidator.validate(contract);
    }


    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
    }

}
