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
