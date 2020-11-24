/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.data.provider;

import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Aurelien Pupier
 * /!\ not declared in extension points, we don't want it in the list, it is only used in Operation output
 *
 */
public class DataExpressionNatureProviderForFormOutput implements IExpressionNatureProvider {

    private final DataExpressionProviderForOutput dataExpressionProvider;

    public DataExpressionNatureProviderForFormOutput(final DataExpressionProviderForOutput dataExpressionProvider) {
        this.dataExpressionProvider = dataExpressionProvider;
    }

    @Override
    public Expression[] getExpressions(final EObject context) {
        final Set<Expression> expressions = dataExpressionProvider.getExpressions(context);
		expressions.addAll(ExpressionProviderService.getInstance().getExpressionProvider(ExpressionConstants.DOCUMENT_REF_TYPE).getExpressions(context));
		return expressions.toArray(new Expression[expressions.size()]);
	}



}
