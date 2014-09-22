package org.bonitasoft.studio.expression.editor.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jdt.core.IJavaProject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExpressionReturnTypeFilterTest {

    @Spy
    private ExpressionReturnTypeFilter filter;

    @Mock
    private IJavaProject javaProject;

    @Before
    public void setUp() throws Exception {
        doReturn(javaProject).when(filter).getJavaProject();
        when(javaProject.findType(anyString())).thenReturn(null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shoudCompatibleReturnType_ReturnTrueForExpressionsWithSameReturnTypes() throws Exception {
        final Expression constantBooleanExpression = ExpressionHelper.createConstantExpression("true", Boolean.class.getName());
        final Expression groovyBooleanExpression = ExpressionHelper.createGroovyScriptExpression("return 1==1", Boolean.class.getName());
        assertThat(filter.compatibleReturnTypes(constantBooleanExpression.getReturnType(), groovyBooleanExpression.getReturnType())).isTrue();
    }

    @Test
    public void shoudCompatibleReturnType_ReturnTrueIfCurrentExpressionsIsAssignableReturnType() throws Exception {
        final Expression collectionExpression = ExpressionHelper.createConstantExpression("true", Collection.class.getName());
        final Expression groovyListExpression = ExpressionHelper.createGroovyScriptExpression("[1,2]", List.class.getName());
        assertThat(filter.compatibleReturnTypes(collectionExpression.getReturnType(), groovyListExpression.getReturnType())).isTrue();
    }

    @Test
    public void shoudCompatibleReturnType_ReturnFalseIfCurrentExpressionsIsNotAssignableReturnType() throws Exception {
        final Expression collectionExpression = ExpressionHelper.createConstantExpression("true", Collection.class.getName());
        final Expression groovyListExpression = ExpressionHelper.createGroovyScriptExpression("[1,2]", List.class.getName());
        assertThat(filter.compatibleReturnTypes(groovyListExpression.getReturnType(), collectionExpression.getReturnType())).isFalse();
    }

    @Test
    public void shoudCompatibleReturnType_ReturnTrueIfOneOfEvaluatedTypeIsUnknown() throws Exception {
        final Expression customTypeExpression = ExpressionHelper.createConstantExpression("true", "org.bonitasoft.test.MyType");
        final Expression stringExpression = ExpressionHelper.createGroovyScriptExpression("[1,2]", String.class.getName());
        assertThat(filter.compatibleReturnTypes(stringExpression.getReturnType(), customTypeExpression.getReturnType())).isTrue();
    }

}
