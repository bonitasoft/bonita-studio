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
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.refactoring.core.script.ReferenceDiff.newReferenceDiff;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProviderAdapterFactory;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConditionExpressionScriptContrainerTest {

    @Mock
    private IProgressMonitor monitor;

    @Test
    public void should_update_expression_name_when_applynigUpdate() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final Expression expression = anExpression()
                .withName("myData == \"hello\"")
                .withExpressionType(ExpressionConstants.CONDITION_TYPE)
                .withContent("myData == \"hello\"").havingReferencedElements(myData).build();
        final ConditionExpressionScriptContrainer textExpressionScriptContainer = spy(new ConditionExpressionScriptContrainer(expression,
                ProcessPackage.Literals.ELEMENT__NAME));

        textExpressionScriptContainer.updateScript(newArrayList(newReferenceDiff("myData", "myNewData")), monitor);
        textExpressionScriptContainer.applyUpdate(editingDomain()).execute();

        ExpressionAssert.assertThat(expression).hasName("myNewData == \"hello\"");
    }

    @Test
    public void should_replace_old_reference_with_new_reference_when_updatingScript() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final ConditionExpressionScriptContrainer textExpressionScriptContainer = new ConditionExpressionScriptContrainer(anExpression()
                .withExpressionType(ExpressionConstants.CONDITION_TYPE)
                .withContent("myData == \"hello\"").havingReferencedElements(myData).build(),
                ProcessPackage.Literals.ELEMENT__NAME);

        textExpressionScriptContainer.updateScript(newArrayList(newReferenceDiff("myData", "myNewData")), monitor);

        assertThat(textExpressionScriptContainer.getNewScript()).isEqualTo("myNewData == \"hello\"");
    }

    private EditingDomain editingDomain() {
        return new TransactionalEditingDomainImpl(new ExpressionItemProviderAdapterFactory());
    }
}
