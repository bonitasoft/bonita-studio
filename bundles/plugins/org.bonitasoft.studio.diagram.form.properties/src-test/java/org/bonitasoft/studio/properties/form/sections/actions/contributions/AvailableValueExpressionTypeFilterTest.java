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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.form.builders.TextFormFieldBuilder.aTextFormField;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.junit.Test;

public class AvailableValueExpressionTypeFilterTest {

    @Test
    public void should_not_allow_FORM_FIELD_type_if_widget_is_not_a_suggest_box() throws Exception {
        final AvailableValueExpressionTypeFilter filter = new AvailableValueExpressionTypeFilter(aTextFormField().build());

        assertThat(filter.select(null, null, anExpression().withExpressionType(ExpressionConstants.FORM_FIELD_TYPE).build())).isFalse();
    }

    @Test
    public void should_not_allow_FORM_FIELD_type_if_widget_is_an_asynchronous_suggest_box() throws Exception {
        final AvailableValueExpressionTypeFilter filter = new AvailableValueExpressionTypeFilter(FormFactory.eINSTANCE.createSuggestBox());

        assertThat(filter.select(null, null, anExpression().withExpressionType(ExpressionConstants.FORM_FIELD_TYPE).build())).isFalse();
    }

    @Test
    public void should_allow_FORM_FIELD_type_if_widget_is_an_asynchronous_suggest_box() throws Exception {
        final SuggestBox suggestBox = FormFactory.eINSTANCE.createSuggestBox();
        suggestBox.setAsynchronous(true);
        final AvailableValueExpressionTypeFilter filter = new AvailableValueExpressionTypeFilter(suggestBox);

        assertThat(filter.select(null, null, anExpression().withExpressionType(ExpressionConstants.FORM_FIELD_TYPE).build())).isTrue();
    }

}
