package org.bonitasoft.studio.contract.core.refactoring;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.refactoring.core.RefactorPair;

public class ContractInputRefactorPair extends RefactorPair<ContractInput, ContractInput> {

    public ContractInputRefactorPair(final ContractInput newValue, final ContractInput oldValue) {
        super(newValue, oldValue);
    }

    @Override
    public String getOldValueName() {
        return getOldValue().getName();
    }

    @Override
    public String getNewValueName() {
        final ContractInput contractInput = getNewValue();
        return contractInput != null ? contractInput.getName() : super.getNewValueName();
    }

}
