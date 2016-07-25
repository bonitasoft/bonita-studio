/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.properties.sections.forms.commands;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetDependency;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Florine Boudin
 */
@RunWith(MockitoJUnitRunner.class)
public class DuplicateFormCommandTest {

    private Form templateForm;

    @Mock
    private Element pageFlow;

    @Mock
    private InternalTransactionalEditingDomain editingDomain;

    @Mock
    private EStructuralFeature feature;

    private String formDesc;
    private String id;

    @Mock
    private Element pageFlow2;

    private DuplicateFormCommand duplicateFormCommand;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        FormFactory.eINSTANCE.createForm();

        id = "Toto";
        formDesc = "";

        // Creation of a form
        templateForm = FormFactory.eINSTANCE.createForm();

        // creation of a multiple widget

        final CheckBoxMultipleFormField checkBoxMultipleFormField = FormFactory.eINSTANCE.createCheckBoxMultipleFormField();
        checkBoxMultipleFormField.setAfterEventExpression(ExpressionHelper.createConstantExpression("sdf", String.class.getName()));
        checkBoxMultipleFormField.setDisplayAfterEventDependsOnConditionScript(ExpressionHelper.createConstantExpression("wxdgc", String.class.getName()));
        checkBoxMultipleFormField.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(ExpressionHelper.createConstantExpression("sdf<",
                String.class.getName()));
        checkBoxMultipleFormField.setDefaultExpressionAfterEvent(ExpressionHelper.createConstantExpression("SQSSQS", String.class.getName()));

        // creation of a simple widget
        final TextFormField textFieldWidget = FormFactory.eINSTANCE.createTextFormField();
        textFieldWidget.setAfterEventExpression(ExpressionHelper.createConstantExpression("sfdsf", String.class.getName()));
        textFieldWidget.setDisplayAfterEventDependsOnConditionScript(ExpressionHelper.createConstantExpression("wsdw<d", String.class.getName()));
        textFieldWidget.setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition(ExpressionHelper.createConstantExpression("qdsqsd",
                String.class.getName()));

        // creation of a widget dependency
        final WidgetDependency depends = FormFactory.eINSTANCE.createWidgetDependency();
        depends.setWidget(checkBoxMultipleFormField);

        // Add dependency to the text field
        textFieldWidget.getDependOn().add(depends);

        // add widgets to the form to duplicate 
        templateForm.getWidgets().add(checkBoxMultipleFormField);
        templateForm.getWidgets().add(textFieldWidget);

        duplicateFormCommand = spy(new DuplicateFormCommand(pageFlow2, feature, templateForm, id, formDesc, editingDomain));
    }

    @Test
    public void shouldDoExecuteWithResults_Check() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldcleanWidgetsContingenciesPropertiesOfForm_RemoveWidgetContingencies() throws Exception {
        duplicateFormCommand.cleanWidgetsContingenciesPropertiesOfForm(templateForm);

        final List<Widget> widgets = templateForm.getWidgets();
        for (final Widget widget : widgets) {
            assertThat(widget.getDependOn()).isEmpty();
            isExpressionEmpty(widget.getDisplayAfterEventDependsOnConditionScript());

            isExpressionEmpty(widget.getDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition());

            isExpressionEmpty(widget.getAfterEventExpression());
            if (widget instanceof MultipleValuatedFormField) {
                isExpressionEmpty(((MultipleValuatedFormField) widget).getDefaultExpressionAfterEvent());
            }

        }

    }

    private static void isExpressionEmpty(final Expression expr) {
        assertThat(expr.getName()).isEmpty();
        assertThat(expr.getContent()).isEmpty();
        assertThat(expr.getType()).isEqualTo(ExpressionConstants.CONSTANT_TYPE);
        assertThat(expr.getReferencedElements()).isEmpty();

    }

}
