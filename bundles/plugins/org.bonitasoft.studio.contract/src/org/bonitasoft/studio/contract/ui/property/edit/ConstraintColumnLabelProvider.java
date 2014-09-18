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
package org.bonitasoft.studio.contract.ui.property.edit;

import org.bonitasoft.studio.contract.core.ContractConstraintUtil;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.jface.viewers.ColumnLabelProvider;


/**
 * @author Romain Bioteau
 *
 */
public class ConstraintColumnLabelProvider extends ColumnLabelProvider {

    @Override
    public String getText(final Object element) {
        if (element instanceof ContractInput) {
            final int nbConstraintForInput = ContractConstraintUtil.getConstraintsForInput((Contract) ((ContractInput) element).eContainer(),
                    (ContractInput) element)
                    .size();
            if (nbConstraintForInput == 0) {
                return Messages.none;
            }
            return String.valueOf(nbConstraintForInput);
        }
        return null;
    }

}
