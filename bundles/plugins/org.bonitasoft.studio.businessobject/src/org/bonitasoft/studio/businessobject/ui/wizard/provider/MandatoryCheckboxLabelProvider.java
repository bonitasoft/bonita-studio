/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard.provider;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;

public class MandatoryCheckboxLabelProvider extends AbstractCheckboxLabelProvider {

    public MandatoryCheckboxLabelProvider(final ColumnViewer viewer) {
        super(viewer);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider#isSelected(java.lang.Object)
     */
    @Override
    protected boolean isSelected(final Object element) {
        if (element instanceof Field) {
            return !((Field) element).isNullable();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider#isEnabled(java.lang.Object)
     */
    @Override
    protected boolean isEnabled(final Object element) {
        if (isAFieldCollection(element)) {
            return false;
        }
        return true;
    }

    protected boolean isAFieldCollection(final Object element) {
        return element instanceof Field && ((Field) element).isCollection() != null && ((Field) element).isCollection();
    }

    @Override
    public String getToolTipText(final Object element) {
        if (isAFieldCollection(element)) {
            return Messages.disabledMandatoryTooltip;
        }
        return super.getToolTipText(element);
    }
}
