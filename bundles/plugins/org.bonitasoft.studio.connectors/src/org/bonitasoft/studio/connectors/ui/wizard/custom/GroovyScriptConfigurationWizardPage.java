/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.custom;

import java.util.Arrays;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.GroovyOnlyExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * @author Romain Bioteau
 *
 */
public class GroovyScriptConfigurationWizardPage extends AbstractConnectorConfigurationWizardPage {

    private static final String SCRIPT_INPUT_NAME = "script";
    private static final String DEPENDENCIES_INPUT_NAME = "variables";

    @Override
    protected Control doCreateControl(final Composite parent, final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(3, 10).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final PageComponentSwitchBuilder builder = new PageComponentSwitchBuilder(getElementContainer(), getDefinition(), getConfiguration(), context,
                getMessageProvider(), getExpressionTypeFilter());
        builder.setIsPageFlowContext(isPageFlowContext());
        createScriptEditorControl(pageComposite, context, builder);


        return mainComposite;
    }

    public ExpressionViewer createScriptEditorControl(final Composite composite, final EMFDataBindingContext context, final PageComponentSwitchBuilder builder) {
        final ConnectorParameter scriptParameter = getConnectorParameter(getInput(SCRIPT_INPUT_NAME));

        final Expression expression = (Expression) scriptParameter.getExpression();
        expression.setType(ExpressionConstants.CONSTANT_TYPE);

        final ConnectorParameter dependenciesParameter = getConnectorParameter(getInput(DEPENDENCIES_INPUT_NAME));
        final ConnectorParameter parameter = buildScriptConnectorParameter();
        builder.createFieldLabel(composite, SWT.CENTER, SCRIPT_INPUT_NAME, true);
        final ExpressionViewer viewer = new GroovyOnlyExpressionViewer(composite, SWT.BORDER);
        viewer.setIsPageFlowContext(isPageFlowContext());
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        viewer.setContext(getElementContainer());
        viewer.setMandatoryField(builder.getLabel("script"), context);
        viewer.addFilter(getExpressionTypeFilter());
        viewer.setInput(parameter);
        final String desc = builder.getDescription(SCRIPT_INPUT_NAME);
        if (desc != null && !desc.isEmpty()) {
            viewer.setMessage(desc, IStatus.INFO);
        }
        context.bindValue(ViewersObservables.observeSingleSelection(viewer),
                EMFObservables.observeValue(parameter, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION));

        context.bindValue(EMFObservables.observeValue(scriptParameter.getExpression(), ExpressionPackage.Literals.EXPRESSION__CONTENT),
                EMFObservables.observeValue(parameter.getExpression(), ExpressionPackage.Literals.EXPRESSION__CONTENT));
        context.bindValue(EMFObservables.observeValue(scriptParameter.getExpression(), ExpressionPackage.Literals.EXPRESSION__NAME),
                EMFObservables.observeValue(parameter.getExpression(), ExpressionPackage.Literals.EXPRESSION__NAME));

        final UpdateListStrategy expressionToReferencedElements = new UpdateListStrategy();
        expressionToReferencedElements.setConverter(new Converter(ListExpression.class, EObject.class) {

            @Override
            public Object convert(final Object from) {
                if (from instanceof ListExpression) {
                    if (((ListExpression) from).getExpressions().size() == 2) {
                        final Expression expression = ((ListExpression) from).getExpressions().get(1);
                        final EList<EObject> referencedElements = expression.getReferencedElements();
                        if (referencedElements.isEmpty()) {
                            return expression;
                        } else {
                            return referencedElements.get(0);
                        }

                    }
                }
                return null;
            }
        });
        final UpdateListStrategy referencedElementsToExpressionTable = new UpdateListStrategy();
        referencedElementsToExpressionTable.setConverter(new Converter(EObject.class, ListExpression.class) {

            @Override
            public Object convert(final Object from) {
                if (from instanceof EObject) {
                    final Expression depValueExpression = ExpressionHelper.createExpressionFromEObject((EObject) from);
                    if (depValueExpression != null) {
                        final ListExpression depLine = ExpressionFactory.eINSTANCE.createListExpression();
                        final Expression depNameExpression = ExpressionHelper.createConstantExpression(depValueExpression.getName(), String.class.getName());
                        final EList<Expression> expressions = depLine.getExpressions();
                        expressions.addAll(Arrays.asList(depNameExpression, depValueExpression));
                        return depLine;
                    }
                    return null;
                }
                return null;
            }
        });

        context.bindList(EMFObservables.observeList(parameter.getExpression(), ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS),
                EMFObservables.observeList(dependenciesParameter.getExpression(), ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS),
                referencedElementsToExpressionTable,
                expressionToReferencedElements);

        return viewer;
    }

    @Override
    protected AbstractExpression createExpression(final Input input) {
        if (input.getName().equals(DEPENDENCIES_INPUT_NAME)) {
            final TableExpression expression = ExpressionFactory.eINSTANCE.createTableExpression();
            return expression;
        }
        return super.createExpression(input);
    }

    private ConnectorParameter buildScriptConnectorParameter() {
        final ConnectorParameter connectorParameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        connectorParameter.setKey("fakeScriptExpression");
        final ConnectorParameter scriptParameter = getConnectorParameter(getInput(SCRIPT_INPUT_NAME));
        final Expression scriptExpression = (Expression) scriptParameter.getExpression();
        String scriptContent = null;
        if (scriptExpression != null && scriptExpression.getContent() != null) {
            scriptContent = scriptExpression.getContent();
        }
        final Expression groovyScriptExpression = ExpressionHelper.createGroovyScriptExpression(scriptContent, Object.class.getName());
        groovyScriptExpression.setName("Click on pencil to edit/view groovy script");
        groovyScriptExpression.setReturnTypeFixed(true);
        connectorParameter.setExpression(groovyScriptExpression);
        return connectorParameter;
    }

}
