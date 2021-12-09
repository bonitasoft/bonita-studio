/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.expression.editor.provider;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;

public class ExpressionNatureProvider implements IExpressionNatureProvider {

	private final List<String> additionalItems;

	public ExpressionNatureProvider(final List<String> additionalItems) {
		Assert.isNotNull(additionalItems);
		this.additionalItems = additionalItems;
	}

	@Override
    public Expression[] getExpressions(final EObject context) {
		return additionalItems.stream()
		        .map(item -> ExpressionHelper.createConstantExpression(item, item, String.class.getName()))
		        .toArray(Expression[]::new);
	}


}
