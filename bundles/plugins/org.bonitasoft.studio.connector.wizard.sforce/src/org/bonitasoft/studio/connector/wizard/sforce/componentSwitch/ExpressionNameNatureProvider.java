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
package org.bonitasoft.studio.connector.wizard.sforce.componentSwitch;

import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Maxence Raoux
 *
 */
public class ExpressionNameNatureProvider implements IExpressionNatureProvider {

	private final Map<String, String> additionalItems;

	public ExpressionNameNatureProvider(final Map<String, String> additionalItems) {
		Assert.isNotNull(additionalItems);
		this.additionalItems = additionalItems;
	}

	@Override
    public Expression[] getExpressions(final EObject context) {
		final Expression[] expressionTable = new Expression[additionalItems.size()];
		int i = 0;
		for (final Entry<String, String> entry : additionalItems.entrySet()) {
			expressionTable[i] = ExpressionHelper.createConstantExpression(entry.getKey(), entry.getKey(), String.class.getName());
			i++;
		}
		return expressionTable;
	}

}
