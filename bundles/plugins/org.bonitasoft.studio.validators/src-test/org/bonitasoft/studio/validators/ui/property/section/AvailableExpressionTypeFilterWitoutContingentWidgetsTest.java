/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.validators.ui.property.section;

import static org.mockito.Mockito.doReturn;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.builders.FormBuilder;
import org.bonitasoft.studio.model.form.builders.TextFormFieldBuilder;
import org.bonitasoft.studio.model.form.builders.ValidatorBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AvailableExpressionTypeFilterWitoutContingentWidgetsTest {

    private final AvailableExpressionTypeFilterWitoutContingentWidgets filter = new AvailableExpressionTypeFilterWitoutContingentWidgets();
    @Mock
    private ExpressionViewer viewer;
    private final TextFormField widget1 = TextFormFieldBuilder.aTextFormField()
            .withName("widget1")
            .build();

    @Test
    public void testContingentFieldNotAvailableOnWidgetValidator() {
        final Expression expression1 = ExpressionHelper.createWidgetExpression(widget1);
        final TextFormField widget2 = TextFormFieldBuilder
                .aTextFormField()
                .withName("widget2")
                .havingValidator(ValidatorBuilder.aValidator().withParameter(expression1))
                .havingContingengy(widget1)
                .build();
        doReturn(widget2).when(viewer).getContext();
        FormBuilder.aForm()
                .havingWidget(widget1, widget2)
                .build();
        Assertions.assertThat(filter.select(viewer, null, expression1)).isFalse();
    }

    @Test
    public void testItSelfFieldAvailableOnWidgetValidator() {
        final Expression expression = ExpressionHelper.createWidgetExpression(widget1);
        FormBuilder.aForm()
                .havingWidget(widget1)
                .havingValidator(ValidatorBuilder.aValidator().withParameter(expression))
                .build();
        doReturn(widget1).when(viewer).getContext();
        Assertions.assertThat(filter.select(viewer, null, expression)).isTrue();
    }

    @Test
    public void testAllFieldsAvailableOnPageValidator() {
        final TextFormField widget2 = TextFormFieldBuilder.aTextFormField().withName("widget2").build();
        final TextFormField widget3 = TextFormFieldBuilder.aTextFormField().havingContingengy(widget2).withName("widget3").build();
        final Expression expression1 = ExpressionHelper.createWidgetExpression(widget1);
        final Expression expression2 = ExpressionHelper.createWidgetExpression(widget2);
        final Expression expression3 = ExpressionHelper.createWidgetExpression(widget3);
        FormBuilder.aForm()
                .havingWidget(widget1, widget2, widget3)
                .havingValidator(ValidatorBuilder.aValidator().withParameter(expression1)).build();
        Assertions.assertThat(filter.select(viewer, null, expression1)).isTrue();
        Assertions.assertThat(filter.select(viewer, null, expression2)).isTrue();
        Assertions.assertThat(filter.select(viewer, null, expression3)).isTrue();
    }

}
