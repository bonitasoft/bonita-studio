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

import org.bonitasoft.engine.bdm.BDMSimpleNameProvider;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * @author aurelie
 */
public class FieldTypeColumnLabelProvider extends ColumnLabelProvider {

    @Override
    public String getText(final Object element) {
        if (element instanceof FieldToContractInputMapping) {
            final Field field = ((FieldToContractInputMapping) element).getField();
            return typeLabel(field);
        }
        return super.getText(element);
    }

    private String typeLabel(final Field field) {
        final StringBuilder sb = new StringBuilder();
        if (field.isCollection()) {
            sb.append("List<");
        }
        if (field instanceof SimpleField) {
            sb.append(simpleFieldTypeLabel((SimpleField) field));
        } else if (field instanceof RelationField) {
            sb.append(BDMSimpleNameProvider
                    .getSimpleBusinessObjectName(((RelationField) field).getReference().getQualifiedName()));
        }
        if (field.isCollection()) {
            sb.append(">");
        }
        return sb.toString();
    }

    private String simpleFieldTypeLabel(final SimpleField field) {
        switch (field.getType()) {
            case DATE:
                return DateTypeLabels.DATE_DEPRECATED;
            case LOCALDATE:
                return DateTypeLabels.DATE_ONLY;
            case LOCALDATETIME:
                return DateTypeLabels.DATE_AND_TIME;
            case OFFSETDATETIME:
                return DateTypeLabels.DATE_TIME_WITH_TIMEZONE;
            default:
                return field.getType().name();
        }
    }
}
