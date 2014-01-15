/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.viewer;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Romain Bioteau
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ExpressionViewer.class)
public class ExpressionViewerTest {
	
	@Mock
	private Composite parent;
	
	@Mock
	private Display	fakeDisplay;
	
	private ExpressionViewer expressionViewer; 

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		PowerMockito.suppress(PowerMockito.constructor(ExpressionViewer.class,Composite.class,Integer.class,EReference.class));
		when(parent.getDisplay()).thenReturn(fakeDisplay);
		expressionViewer = new ExpressionViewer(parent, SWT.BORDER, null);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void shoudCompatibleReturnType_ReturnTrueForExpressionsWithSameReturnTypes() throws Exception {
		Expression constantBooleanExpression = ExpressionHelper.createConstantExpression("true", Boolean.class.getName());
		Expression groovyBooleanExpression = ExpressionHelper.createGroovyScriptExpression("return 1==1",  Boolean.class.getName());
		assertThat(expressionViewer.compatibleReturnTypes(constantBooleanExpression, groovyBooleanExpression)).isTrue();
	}
	
	@Test
	public void shoudCompatibleReturnType_ReturnTrueIfCurrentExpressionsIsAssignableReturnType() throws Exception {
		Expression collectionExpression = ExpressionHelper.createConstantExpression("true", Collection.class.getName());
		Expression groovyListExpression = ExpressionHelper.createGroovyScriptExpression("[1,2]",  List.class.getName());
		assertThat(expressionViewer.compatibleReturnTypes(collectionExpression, groovyListExpression)).isTrue();
	}
	
	@Test
	public void shoudCompatibleReturnType_ReturnFalseIfCurrentExpressionsIsNotAssignableReturnType() throws Exception {
		Expression collectionExpression = ExpressionHelper.createConstantExpression("true", Collection.class.getName());
		Expression groovyListExpression = ExpressionHelper.createGroovyScriptExpression("[1,2]",  List.class.getName());
		assertThat(expressionViewer.compatibleReturnTypes(groovyListExpression, collectionExpression)).isFalse();
	}
	
	@Test
	public void shoudCompatibleReturnType_ReturnTrueIfOneOfEvaluatedTypeIsUnknown() throws Exception {
		Expression customTypeExpression = ExpressionHelper.createConstantExpression("true", "org.bonitasoft.test.MyType");
		Expression stringExpression = ExpressionHelper.createGroovyScriptExpression("[1,2]",  String.class.getName());
		assertThat(expressionViewer.compatibleReturnTypes(stringExpression, customTypeExpression)).isTrue();
	}

}
