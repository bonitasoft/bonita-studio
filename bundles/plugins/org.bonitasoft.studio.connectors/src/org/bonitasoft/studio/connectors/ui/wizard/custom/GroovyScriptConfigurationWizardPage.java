/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard.custom;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.bonitasoft.studio.connectors.extension.ICanFinishProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.GroovyOnlyExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 */
public class GroovyScriptConfigurationWizardPage extends AbstractConnectorConfigurationWizardPage
        implements ICanFinishProvider {

    private static final String FAKE_SCRIPT_EXPRESSION = "fakeScriptExpression";
    private static final String SCRIPT_INPUT_NAME = "script";

    @Override
    protected Control doCreateControl(final Composite parent, final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(3, 10).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final PageComponentSwitchBuilder builder = new PageComponentSwitchBuilder(getElementContainer(),
                getDefinition(),
                getConfiguration(),
                context,
                getExpressionTypeFilter());
        builder.setIsPageFlowContext(isPageFlowContext());
        createScriptEditorControl(pageComposite, context, builder);

        return mainComposite;
    }

    public ExpressionViewer createScriptEditorControl(final Composite composite, final EMFDataBindingContext context,
            final PageComponentSwitchBuilder builder) {
        final ConnectorParameter parameter = getScriptConnectorParameter();
        builder.createFieldLabel(composite, SWT.CENTER, SCRIPT_INPUT_NAME, true);
        final ExpressionViewer viewer = new GroovyOnlyExpressionViewer(composite, SWT.BORDER);
        viewer.setIsPageFlowContext(isPageFlowContext());
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        viewer.setContext(getElementContainer());
        final String fieldName = builder.getLabel("script");
        viewer.setMandatoryField(fieldName, context);
        viewer.addFilter(getExpressionTypeFilter());
        viewer.setInput(parameter);
        final String desc = builder.getDescription(SCRIPT_INPUT_NAME);
        if (desc != null && !desc.isEmpty()) {
            viewer.setMessage(desc);
        }
        context.bindValue(ViewersObservables.observeSingleSelection(viewer),
                EMFObservables.observeValue(parameter,
                        ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION));

        context.bindValue(
                EMFObservables.observeValue(parameter.getExpression(), ExpressionPackage.Literals.EXPRESSION__CONTENT),
                EMFObservables.observeValue(parameter.getExpression(), ExpressionPackage.Literals.EXPRESSION__CONTENT),
                mandatoryScriptContentStrategy(fieldName), mandatoryScriptContentStrategy(fieldName));

        return viewer;
    }

    private UpdateValueStrategy mandatoryScriptContentStrategy(final String fieldLabel) {
        final UpdateValueStrategy startegy = new UpdateValueStrategy();
        startegy.setAfterGetValidator(new EmptyInputValidator(fieldLabel));
        return startegy;
    }

    @Override
    protected AbstractExpression createExpression(final Input input) {
        if (input.getName().equals(FAKE_SCRIPT_EXPRESSION)) {
            final Expression expression = ExpressionHelper.createGroovyScriptExpression("", input.getType());
            expression.setReturnTypeFixed(true);
            expression.setName("");
            return expression;
        }
        return super.createExpression(input);
    }

    private ConnectorParameter getScriptConnectorParameter() {
        final Input input = getFakeInput();
        return getConnectorParameter(input);
    }

    protected Input getFakeInput() {
        final Input input = ConnectorDefinitionFactory.eINSTANCE.createInput();
        input.setName(FAKE_SCRIPT_EXPRESSION);
        input.setType(Object.class.getName());
        return input;
    }

    @Override
    public boolean canFinish(final ConnectorConfiguration configuration) {
        for (final ConnectorParameter param : configuration.getParameters()) {
            if (FAKE_SCRIPT_EXPRESSION.equals(param.getKey())) {
                final AbstractExpression expression = param.getExpression();
                if (expression != null && expression instanceof Expression
                        && ((Expression) expression).getContent() != null
                        && !((Expression) expression).getContent().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

}
