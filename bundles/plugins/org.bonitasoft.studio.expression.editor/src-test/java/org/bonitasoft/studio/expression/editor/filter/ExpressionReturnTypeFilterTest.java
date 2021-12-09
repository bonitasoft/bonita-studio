/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExpressionReturnTypeFilterTest {

    private ExpressionReturnTypeFilter filter;

    @Mock
    private IJavaProject javaProject;

    @Mock
    private AbstractRepository javaContainer;

    @Mock
    private JDTTypeHierarchyManager typeHierarchyManager;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Before
    public void setUp() throws Exception {
        filter = new ExpressionReturnTypeFilter(repositoryAccessor);
        when(javaContainer.getJavaProject()).thenReturn(javaProject);
        when(javaContainer.getJdtTypeHierarchyManager()).thenReturn(typeHierarchyManager);
        when(javaProject.findType(anyString())).thenReturn(null);
        when(repositoryAccessor.getCurrentRepository()).thenReturn(javaContainer);
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

    @Test
    public void should_use_type_hierarchy_to_compare_types_when_classes_belongs_to_javaProject() throws Exception {
        final IType myClassIType = mock(IType.class);
        final IType booleanType = mock(IType.class);
        when(javaProject.findType("org.test.MyClass")).thenReturn(myClassIType);
        when(javaProject.findType(Boolean.class.getName())).thenReturn(booleanType);
        final ITypeHierarchy iTypeHierarchy = mock(ITypeHierarchy.class);
        when(typeHierarchyManager.getTypeHierarchy(myClassIType)).thenReturn(iTypeHierarchy);

        assertThat(filter.compatibleReturnTypes("org.test.MyClass", Boolean.class.getName())).isFalse();
    }

}
