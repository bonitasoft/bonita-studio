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
package org.bonitasoft.studio.contract.ui.wizard;

import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;

/**
 * @author aurelie
 */
public class FieldToContractInputMappingTreeStructureAdvisor extends TreeStructureAdvisor {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.databinding.viewers.TreeStructureAdvisor#getParent(java.lang.Object)
     */
    @Override
    public Object getParent(final Object element) {
        return ((FieldToContractInputMapping) element).getParent();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.databinding.viewers.TreeStructureAdvisor#hasChildren(java.lang.Object)
     */
    @Override
    public Boolean hasChildren(final Object element) {
        return !((FieldToContractInputMapping) element).getChildren().isEmpty();
    }

}
