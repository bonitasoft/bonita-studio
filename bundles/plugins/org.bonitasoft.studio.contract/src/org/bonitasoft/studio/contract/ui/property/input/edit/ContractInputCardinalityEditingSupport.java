/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.contract.core.refactoring.ContractInputRefactorOperationFactory;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

public class ContractInputCardinalityEditingSupport extends PropertyEditingSupport {

    private static final EAttribute CONTRACT_INPUT_MULTIPLE = ProcessPackage.Literals.CONTRACT_INPUT__MULTIPLE;
    private ContractInput contractInput;
    private Factory transactionalEditingDomainFactory;
    private IProgressService progressService;
    private ContractInputRefactorOperationFactory refactorOperationFactory;

    public ContractInputCardinalityEditingSupport(ColumnViewer viewer,
            IPropertySourceProvider sourceProvider,
            ContractInputRefactorOperationFactory refactorOperationFactory,
            IProgressService progressService,
            TransactionalEditingDomain.Factory transactionalEditingDomainFactory) {
        super(viewer, sourceProvider,
                CONTRACT_INPUT_MULTIPLE.getName());
        this.refactorOperationFactory = refactorOperationFactory;
        this.progressService = progressService;
        this.transactionalEditingDomainFactory = transactionalEditingDomainFactory;
    }

    @Override
    protected void setValue(final Object element, final Object value) {
        setContractInput((ContractInput) element);
        if (contractInput != null && contractInput.isMultiple() != (Boolean) value) {
            refactorContractInputCardinality(contractInput, value);
        } else {
            super.setValue(element, value);
        }
        getViewer().update(element, null);
    }

    @Override
    protected CellEditor getCellEditor(final Object element) {
        return new CheckboxCellEditor((Composite) getViewer().getControl(), SWT.CHECK);
    }
    
    public ContractInput getContractInput() {
        return contractInput;
    }

    public void setContractInput(final ContractInput contractInput) {
        this.contractInput = contractInput;
    }
    
    protected void refactorContractInputCardinality(ContractInput contractInput, Object value) {
        final TransactionalEditingDomain editingDomain = transactionalEditingDomainFactory
                .getEditingDomain(contractInput.eResource().getResourceSet());
        var refactorOperation = refactorOperationFactory
                .createRefactorOperation(editingDomain, contractInput, value);
        refactorOperation.getCompoundCommand().append(
                SetCommand.create(editingDomain, contractInput, CONTRACT_INPUT_MULTIPLE, value));
        try {
            progressService.busyCursorWhile(refactorOperation);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(String.format("Failed to refactor %s into %s", contractInput.isMultiple(), value), e);
        }
    }

}
