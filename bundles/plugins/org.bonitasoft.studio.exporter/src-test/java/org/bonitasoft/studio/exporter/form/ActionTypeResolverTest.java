/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.form;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.operation.OperatorType;
import org.bonitasoft.forms.client.model.ActionType;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.junit.Test;

public class ActionTypeResolverTest {

    @Test
    public void operator_assignment_type_to_assignement_action_type() throws Exception {
        final ActionType actionType = ActionTypeResolver.toActionType(OperatorType.ASSIGNMENT.name());

        assertThat(actionType).isEqualTo(ActionType.ASSIGNMENT);
    }

    @Test
    public void operator_deletion_type_to_deletion_action_type() throws Exception {
        final ActionType actionType = ActionTypeResolver.toActionType(OperatorType.DELETION.name());

        assertThat(actionType).isEqualTo(ActionType.DELETION);
    }

    @Test
    public void operator_DOCUMENT_CREATE_UPDATE_type_to_assignment_action_type() throws Exception {
        final ActionType actionType = ActionTypeResolver.toActionType(OperatorType.DOCUMENT_CREATE_UPDATE.name());

        assertThat(actionType).isEqualTo(ActionType.ASSIGNMENT);
    }

    @Test
    public void operator_SET_LIST_DOCUMENT_OPERATOR_type_to_assignment_action_type() throws Exception {
        final ActionType actionType = ActionTypeResolver.toActionType(ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR);

        assertThat(actionType).isEqualTo(ActionType.ASSIGNMENT);
    }

    @Test
    public void operator_STRING_INDEX_type_to_assignment_action_type() throws Exception {
        final ActionType actionType = ActionTypeResolver.toActionType(OperatorType.STRING_INDEX.name());

        assertThat(actionType).isEqualTo(ActionType.ASSIGNMENT);
    }

    @Test
    public void operator_ATTACH_EXISTING_BUSINESS_DATA_type_to_assignment_action_type() throws Exception {
        final ActionType actionType = ActionTypeResolver.toActionType(ExpressionConstants.ATTACH_EXISTING_BUSINESS_DATA);

        assertThat(actionType).isEqualTo(ActionType.ASSIGNMENT);
    }

    @Test
    public void operator_CREATE_BUSINESS_DATA_OPERATOR_type_to_assignment_action_type() throws Exception {
        final ActionType actionType = ActionTypeResolver.toActionType(ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR);

        assertThat(actionType).isEqualTo(ActionType.ASSIGNMENT);
    }

    @Test
    public void operator_BUSINESS_DATA_JAVA_SETTER_OPERATOR_type_to_java_method_action_type() throws Exception {
        final ActionType actionType = ActionTypeResolver.toActionType(ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR);

        assertThat(actionType).isEqualTo(ActionType.JAVA_METHOD);
    }
}
