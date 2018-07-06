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
package org.bonitasoft.studio.refactoring.core.script;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.studio.refactoring.core.script.ReferenceDiff.newReferenceDiff;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProviderAdapterFactory;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder;
import org.bonitasoft.studio.refactoring.core.DataRefactorPair;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Sets;

@RunWith(MockitoJUnitRunner.class)
public class GroovyExpressionScriptContrainerTest {

    @Mock
    private GroovyScriptRefactoringOperation groovyScriptOperation;
    @Mock
    private IProgressMonitor monitor;
    @Mock
    private IScriptRefactoringOperationFactory scriptRefactorOperationFactory;

    @Test
    public void should_retrieve_script_value_form_expression() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final GroovyExpressionScriptContrainer groovyExpressionScriptContrainer = new GroovyExpressionScriptContrainer(aGroovyScriptExpression()
                .withContent("return myData").havingReferencedElements(myData).build(),
                ProcessPackage.Literals.ELEMENT__NAME, scriptRefactorOperationFactory);

        final String script = groovyExpressionScriptContrainer.getScript();

        assertThat(script).isEqualTo("return myData");
    }

    @Test
    public void should_update_script_value_for_given_diff() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final GroovyExpressionScriptContrainer groovyExpressionScriptContrainer = spy(new GroovyExpressionScriptContrainer(aGroovyScriptExpression()
                .withContent("return myData").havingReferencedElements(myData).build(),
                ProcessPackage.Literals.ELEMENT__NAME,
                scriptRefactorOperationFactory));
        doReturn(groovyScriptOperation).when(scriptRefactorOperationFactory).createScriptOperationFactory(anyString(), anyList());
        doNothing().when(groovyScriptOperation).run(monitor);
        doReturn("return myNewData").when(groovyScriptOperation).getRefactoredScript();

        groovyExpressionScriptContrainer.updateScript(newArrayList(newReferenceDiff("myData", "myNewData")),
                monitor);

        assertThat(groovyExpressionScriptContrainer.getNewScript()).isEqualTo("return myNewData");
    }

    @Test
    public void should_have_expression_name_as_name() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final GroovyExpressionScriptContrainer groovyExpressionScriptContrainer = new GroovyExpressionScriptContrainer(aGroovyScriptExpression()
                .withName("myScript")
                .withContent("return myData").havingReferencedElements(myData).build(),
                ProcessPackage.Literals.ELEMENT__NAME,
                scriptRefactorOperationFactory);

        final String name = groovyExpressionScriptContrainer.getName();

        assertThat(name).isEqualTo("myScript");
    }

    @Test
    public void should_build_a_compoundCommand_updating_expression_content_when_applyingUpdate() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final Expression expression = aGroovyScriptExpression()
                .withContent("return myData").havingReferencedElements(myData).build();
        final GroovyExpressionScriptContrainer groovyExpressionScriptContrainer = new GroovyExpressionScriptContrainer(expression,
                ProcessPackage.Literals.ELEMENT__NAME,
                scriptRefactorOperationFactory);

        groovyExpressionScriptContrainer.setNewScript("return myNewData");
        final CompoundCommand command = groovyExpressionScriptContrainer.applyUpdate(editingDomain());
        command.execute();

        ExpressionAssert.assertThat(expression).hasContent("return myNewData");
    }


    @Test
    public void should_build_a_compoundCommand_updating_expression_dependencies_when_updatingDependencies_for_a_data() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final Expression expression = aGroovyScriptExpression()
                .withContent("return myData").havingReferencedElements(myData).build();
        final GroovyExpressionScriptContrainer groovyExpressionScriptContrainer = spy(new GroovyExpressionScriptContrainer(expression,
                ProcessPackage.Literals.ELEMENT__NAME,
                scriptRefactorOperationFactory));

        groovyExpressionScriptContrainer.setNewScript("return myNewData");
        final Data copy = EcoreUtil.copy(myData);
        copy.setName("myNewData");
        final CompoundCommand command = groovyExpressionScriptContrainer.updateDependencies(editingDomain(), newArrayList(new DataRefactorPair(copy, myData)));
        command.execute();

        assertThat(expression.getReferencedElements()).extracting("name").containsOnly("myNewData");
    }


    @Test
    public void should_build_a_compoundCommand_removing_expression_dependencies_when_removingDependencies_for_a_data() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final Expression expression = aGroovyScriptExpression()
                .withContent("return myData").havingReferencedElements(myData).build();
        final GroovyExpressionScriptContrainer groovyExpressionScriptContrainer = new GroovyExpressionScriptContrainer(expression,
                ProcessPackage.Literals.ELEMENT__NAME, scriptRefactorOperationFactory);

        groovyExpressionScriptContrainer.setNewScript("return myNewData");
        final Data copy = EcoreUtil.copy(myData);
        copy.setName("myNewData");
        final CompoundCommand command = groovyExpressionScriptContrainer.removeDependencies(editingDomain(), newArrayList(new DataRefactorPair(copy, myData)));
        command.execute();

        assertThat(expression.getReferencedElements()).isEmpty();
    }

    @Test
    public void should_two_ScriptContainer_refrencening_the_same_modelElement_and_the_same_scriptFeature_be_equal() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final Expression expression = aGroovyScriptExpression()
                .withContent("return myData").havingReferencedElements(myData).build();

        assertThat(Sets.newHashSet(new GroovyExpressionScriptContrainer(expression,
                ProcessPackage.Literals.ELEMENT__NAME, scriptRefactorOperationFactory),
                new GroovyExpressionScriptContrainer(expression,
                        ProcessPackage.Literals.ELEMENT__NAME, scriptRefactorOperationFactory))).hasSize(1);
    }

    private TransactionalEditingDomain editingDomain() {
        return new TransactionalEditingDomainImpl(new ExpressionItemProviderAdapterFactory());
    }
}
