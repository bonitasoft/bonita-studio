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
package org.bonitasoft.studio.contract.ui.wizard.labelProvider;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * @author aurelie
 */
public class FieldTypeColumnLabelProvider extends ColumnLabelProvider {

    @Override
    public String getText(final Object element) {
        if (element instanceof SimpleFieldToContractInputMapping) {
            final SimpleFieldToContractInputMapping mapping = (SimpleFieldToContractInputMapping) element;
            return ((SimpleField) mapping.getField()).getType().name();
        } else {
            if (element instanceof RelationFieldToContractInputMapping) {
                final RelationFieldToContractInputMapping mapping = (RelationFieldToContractInputMapping) element;
                return BDMQueryUtil.getSimpleBusinessObjectName(((RelationField) mapping.getField()).getReference().getQualifiedName());
            }
        }

        return super.getText(element);
    }
}
