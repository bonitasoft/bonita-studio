/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.wizard.office.templating;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.TypedValidator;
import org.bonitasoft.studio.connector.wizard.office.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class TableExpressionWithoutLazyLoadedRefs extends TypedValidator<TableExpression, IStatus> {

    private final BusinessObjectModelRepositoryStore store;
    private boolean shortMessage = false;

    public TableExpressionWithoutLazyLoadedRefs(BusinessObjectModelRepositoryStore store) {
        this.store = store;
    }

    public TableExpressionWithoutLazyLoadedRefs(BusinessObjectModelRepositoryStore store, boolean shortMessage) {
        this(store);
        this.shortMessage = shortMessage;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.databinding.validator.TypedValidator#doValidate(java.lang.Object)
     */
    @Override
    protected IStatus doValidate(TableExpression table) {
        final List<Expression> expression = ModelHelper.getAllItemsOfType(table, ExpressionPackage.Literals.EXPRESSION);
        for (final Expression exp : expression) {
            if (Objects.equals(ExpressionConstants.VARIABLE_TYPE, exp.getType())
                    && !exp.getReferencedElements().isEmpty()
                    && exp.getReferencedElements().get(0) instanceof BusinessObjectData) {
                return ValidationStatus
                        .warning(shortMessage ? Messages.shortValueWithBusinessObjectWithLazyRefs : Messages.valueWithBusinessObjectWithLazyRefs);
            }
        }
        return ValidationStatus.ok();
    }

}
