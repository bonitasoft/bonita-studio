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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
import org.bonitasoft.studio.model.process.builders.StringDataTypeBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TextExpressionScriptContainerTest {

    @Mock
    private IProgressMonitor monitor;

    @Test
    public void should_replace_old_reference_with_new_reference_when_updatingScript() throws Exception {
        final Data myData = DataBuilder.aData().havingDataType(StringDataTypeBuilder.aStringDataType()).build();
        final TextExpressionScriptContainer textExpressionScriptContainer = new TextExpressionScriptContainer(anExpression()
                .withExpressionType(ExpressionConstants.PATTERN_TYPE)
                .withContent("Hello ${myData}, do you have ${myData}, but do not replace myData").havingReferencedElements(myData).build(),
                ProcessPackage.Literals.ELEMENT__NAME);

        textExpressionScriptContainer.updateScript(newArrayList(newReferenceDiff("myData", "myNewData")), monitor);

        assertThat(textExpressionScriptContainer.getNewScript()).isEqualTo("Hello ${myNewData}, do you have ${myNewData}, but do not replace myData");
    }
}
