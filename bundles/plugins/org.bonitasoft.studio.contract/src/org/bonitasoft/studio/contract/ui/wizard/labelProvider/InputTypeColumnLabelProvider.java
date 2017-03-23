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

import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author aurelie
 */
public class InputTypeColumnLabelProvider extends ColumnLabelProvider {

    private final Contract contract;

    public InputTypeColumnLabelProvider(Contract contract) {
        this.contract = contract;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        if (element instanceof FieldToContractInputMapping) {
            final FieldToContractInputMapping mapping = (FieldToContractInputMapping) element;
            return typeLabel(mapping);
        }

        return super.getText(element);
    }

    private String typeLabel(final FieldToContractInputMapping mapping) {
        switch (mapping.getContractInputType()) {
            case DATE:
                return DateTypeLabels.DATE_DEPRECATED;
            case LOCALDATE:
                return DateTypeLabels.DATE_ONLY;
            case LOCALDATETIME:
                return DateTypeLabels.DATE_AND_TIME;
            case OFFSETDATETIME:
                return DateTypeLabels.DATE_TIME_WITH_TIMEZONE;
            default:
                return mapping.getContractInputType().name();
        }
    }

    @Override
    public Image getImage(Object element) {
        if (!(contract.eContainer() instanceof Task) && element instanceof SimpleFieldToContractInputMapping) {
            if (Long.class.getName().equals(((SimpleFieldToContractInputMapping) element).getFieldType())) {
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
            }
        }
        return null;
    }

    @Override
    public String getToolTipText(Object element) {
        if (!(contract.eContainer() instanceof Task) && element instanceof SimpleFieldToContractInputMapping) {
            if (Long.class.getName().equals(((SimpleFieldToContractInputMapping) element).getFieldType())) {
                return Messages.longConversionWarning;
            }
        }
        return null;
    }
}
