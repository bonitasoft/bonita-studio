/**
 * Copyright (C) 2020 BonitaSoft S.A.
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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorConfigurationSupport;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.pattern.PatternExpressionViewer;
import org.bonitasoft.studio.expression.editor.pattern.richtext.RichPatternExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class MailContentComponentSwitch extends PageComponentSwitch {

    private final ConnectorConfigurationSupport connectorConfigurationSupport;

    public MailContentComponentSwitch(final IWizardContainer iWizardContainer,
            final Composite parent, final EObject container,
            final ExtendedConnectorDefinition definition,
            final ConnectorConfiguration connectorConfiguration,
            final EMFDataBindingContext context,
            final AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
        super(iWizardContainer, parent, container, definition, connectorConfiguration,
                context, connectorExpressionContentTypeFilter);
        connectorConfigurationSupport = new ConnectorConfigurationSupport(connectorConfiguration);
    }

    @Override
    protected PatternExpressionViewer createTextAreaControl(final Composite composite, final TextArea object) {
        final Input input = getConnectorInput(object.getInputName());
        final ConnectorParameter parameter = connectorConfigurationSupport.getConnectorParameter(object.getInputName(),
                object, input);

        if (parameter != null) {
            createFieldLabel(composite, SWT.TOP, object.getId(), input.isMandatory());
            final RichPatternExpressionViewer richTextViewer = createRichTextEditorViewer(composite, object, input,
                    parameter);
            richTextViewer.setRichTextActive(isHTML(object, input));
            return richTextViewer;
        }
        return null;
    }

    private boolean isHTML(final TextArea object, final Input input) {
        ConnectorParameter htmlParamater = connectorConfigurationSupport.getConnectorParameter("html",
                object, input);
        Expression htmlExpression = (Expression) htmlParamater.getExpression();
        return ExpressionConstants.CONSTANT_TYPE.equals(htmlExpression.getType()) && "true".equalsIgnoreCase(htmlExpression.getContent());
    }

    private RichPatternExpressionViewer createRichTextEditorViewer(final Composite composite, final TextArea object,
            final Input input, final ConnectorParameter parameter) {
        final RichPatternExpressionViewer richTextViewer = new RichPatternExpressionViewer(composite, SWT.NONE);
        richTextViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(500, 300).create());
        richTextViewer.addFilter(getConnectorExpressionContentTypeFilter());

        final Expression exp = (Expression) parameter.getExpression();
        final String desc = getDefinition().getFieldDescription(object.getId());
        if (desc != null && !desc.isEmpty()) {
            richTextViewer.setHint(desc);
        }

        final UpdateValueStrategy startegy = new UpdateValueStrategy();
        if (input.isMandatory()) {
            startegy.setAfterConvertValidator(new EmptyInputValidator(getLabel(object.getId())));
        }
        richTextViewer.setEMFBindingContext(getContext());
        if (input.isMandatory()) {
            richTextViewer.setMandatoryField(getLabel(object.getId()));
        }
        richTextViewer.setInput(parameter);
        richTextViewer.setContextInput(getContainer());
        richTextViewer.setExpression(exp);
        return richTextViewer;
    }
  
}
