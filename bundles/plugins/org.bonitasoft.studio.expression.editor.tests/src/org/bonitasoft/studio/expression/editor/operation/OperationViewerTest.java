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
package org.bonitasoft.studio.expression.editor.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.studio.model.expression.builders.OperatorBuilder.anOperator;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.expression.assertions.OperatorAssert;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.junit.Rule;
import org.junit.Test;

public class OperationViewerTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void should_update_operator_without_editing_domain() throws Exception {
        final OperationViewer operationViewer = new OperationViewer(realm.createComposite(), new TabbedPropertySheetWidgetFactory(), null, null, null);

        final Operation operation = anOperation().havingOperator(anOperator().withType(ExpressionConstants.ASSIGNMENT_OPERATOR)).build();
        final Operator newOperator = anOperator().withType(ExpressionConstants.JAVA_METHOD_OPERATOR).build();
        operationViewer.updateModelOperator(operation, newOperator);

        assertThat(operation.getOperator()).isNotEqualTo(newOperator);
        OperatorAssert.assertThat(operation.getOperator()).hasType(ExpressionConstants.JAVA_METHOD_OPERATOR);
    }

}
