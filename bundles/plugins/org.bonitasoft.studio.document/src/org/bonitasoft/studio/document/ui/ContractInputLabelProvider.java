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
package org.bonitasoft.studio.document.ui;

import static com.google.common.base.Preconditions.checkArgument;
import static org.bonitasoft.studio.common.functions.ContractInputFunctions.toAncestorNameList;

import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.jface.viewers.LabelProvider;

import com.google.common.base.Joiner;

public class ContractInputLabelProvider extends LabelProvider {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        checkArgument(element instanceof ContractInput);
        final ContractInput input = (ContractInput) element;
        return contractInputPath(input);
    }

    private String contractInputPath(final ContractInput contractInput) {
        return Joiner.on(".").join(toAncestorNameList().apply(contractInput));
    }

}
