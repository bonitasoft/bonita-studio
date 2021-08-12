/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.providers;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Maxence Raoux
 *
 */
public class AutoCompleteExpressionProvider implements
		IExpressionNatureProvider {

	private final List<String> additionalItems;

	public AutoCompleteExpressionProvider(final List<String> additionalItems) {
		this.additionalItems = additionalItems;
	}

	@Override
    public Expression[] getExpressions(final EObject context) {
		final Expression[] expressionTable = new Expression[additionalItems.size()];
		for (int i = 0; i < additionalItems.size(); i++) {
			expressionTable[i] = ExpressionHelper.createConstantExpression(
					additionalItems.get(i), additionalItems.get(i),
					String.class.getName());
		}
		return expressionTable;
	}

}
