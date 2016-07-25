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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.Widget;

public class AvailableValueExpressionTypeFilter extends AvailableExpressionTypeFilter {

    private final Widget widget;

    public AvailableValueExpressionTypeFilter(final Widget widget) {
        super(ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SCRIPT_TYPE);
        this.widget = widget;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter#isExpressionAllowed(java.lang.Object, java.util.Set)
     */
    @Override
    protected boolean isExpressionAllowed(final Object element, final Set<String> allowedExpressionTypes) {
        if (widget instanceof SuggestBox && ((SuggestBox) widget).isAsynchronous()) {
            final Set<String> newAllowedExpressionTypes = newHashSet(allowedExpressionTypes);
            newAllowedExpressionTypes.add(ExpressionConstants.FORM_FIELD_TYPE);
            return super.isExpressionAllowed(element, newAllowedExpressionTypes);
        }
        return super.isExpressionAllowed(element, allowedExpressionTypes);
    }
}
