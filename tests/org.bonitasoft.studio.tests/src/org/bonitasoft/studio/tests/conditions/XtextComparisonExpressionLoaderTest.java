/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.conditions;


import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.any;

import java.util.Collections;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Equals;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.expression.ComparisonExpressionLoadException;
import org.bonitasoft.studio.condition.ui.expression.ComparisonExpressionValidator;
import org.bonitasoft.studio.condition.ui.expression.ProjectXtextResourceProvider;
import org.bonitasoft.studio.condition.ui.expression.XtextComparisonExpressionLoader;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.inject.Injector;

public class XtextComparisonExpressionLoaderTest {

    @Test
    @Ignore("can't make failing the test with the known bug... sometimes it seems to work")
    public void testLoadResourceWIthUTF8() throws ComparisonExpressionLoadException {
        final Injector injector = ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
        final XtextComparisonExpressionLoader xtextComparisonExpressionLoader = Mockito
                .spy(new XtextComparisonExpressionLoader(injector.getInstance(ConditionModelGlobalScopeProvider.class),
                        new ModelSearch(Collections::emptyList), new ProjectXtextResourceProvider(injector)));
        Mockito.doReturn(Collections.singletonList("管理者")).when(xtextComparisonExpressionLoader).getAccessibleReferences(Mockito.any(EObject.class));
        final Resource resource = xtextComparisonExpressionLoader.loadResource("管理者 == \"test\"", null);
        final TreeIterator<EObject> allContents = resource.getAllContents();
        while (allContents.hasNext()) {
            final EObject current = allContents.next();
            if (current instanceof Operation_Equals) {
                final Expression_ProcessRef left = (Expression_ProcessRef) ((Operation_Equals) current).getLeft();
                final EObject value = left.getValue();
                Assert.assertEquals("管理者", value);
                return;
            }
        }
        fail("Condition Expression not loaded");
    }

    @Test
    @Ignore("I wasn't able to mock what is required AND can't make failing the test with the known bug... sometimes it seems to work")
    public void testValidationWithUTF8() {
        final ComparisonExpressionValidator comparisonExpressionValidator = new ComparisonExpressionValidator();
        final ComparisonExpressionValidator spy = Mockito.spy(comparisonExpressionValidator);
        Mockito.doReturn(new ResourceSetImpl()).when(spy).getContextResourceSet();
        final Injector injector = ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
        final XtextComparisonExpressionLoader xtextComparisonExpressionLoader = Mockito
                .spy(new XtextComparisonExpressionLoader(injector.getInstance(ConditionModelGlobalScopeProvider.class),
                        new ModelSearch(Collections::emptyList), new ProjectXtextResourceProvider(injector)));
        Mockito.doReturn(xtextComparisonExpressionLoader).when(spy).getXtextExpressionLoader(Mockito.any(Injector.class),
                any(IModelSearch.class));
        Mockito.doReturn(Collections.singletonList("管理者")).when(xtextComparisonExpressionLoader).getAccessibleReferences(Mockito.any(EObject.class));
        spy.setInputExpression(createNewComparisonExpression(""));

        final IStatus validate = spy.validate("管理者 == \"test\"");
        Assert.assertTrue(validate.isOK());
    }

    private Expression createNewComparisonExpression(final String script) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setReturnType(Boolean.class.getName());
        exp.setReturnTypeFixed(true);
        exp.setType(ExpressionConstants.CONDITION_TYPE);
        exp.setContent(script);
        return exp;
    }
}
