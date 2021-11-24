/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.connector.wizard.uipath.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.ExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Composite;

public class UIPathStartJobConfigurationPage extends GeneratedConnectorWizardPage {

    private static final String RUNTIME_TYPE_INPUT = "runtimeType";
    private static final List<String> RUNTIME_TYPES = new ArrayList<>();
    static {
        RUNTIME_TYPES.add("NonProduction");
        RUNTIME_TYPES.add("Attended");
        RUNTIME_TYPES.add("Unattended");
        RUNTIME_TYPES.add("Studio");
        RUNTIME_TYPES.add("Development");
        RUNTIME_TYPES.add("StudioX");
        RUNTIME_TYPES.add("Headless");
        RUNTIME_TYPES.add("StudioPro");
        RUNTIME_TYPES.add("TestAutomation");
    }
    
    @Override
    protected PageComponentSwitch getPageComponentSwitch(EMFDataBindingContext context, Composite pageComposite) {
        return new UIPathPageComponentSwitch(getContainer(),
                pageComposite,
                getElementContainer(),
                getDefinition(),
                getConfiguration(),
                context,
                getExpressionTypeFilter());
    }

    class UIPathPageComponentSwitch extends PageComponentSwitch {

        UIPathPageComponentSwitch(IWizardContainer wizardContainer,
                Composite parent, EObject container,
                ExtendedConnectorDefinition definition,
                ConnectorConfiguration connectorConfiguration,
                EMFDataBindingContext context,
                AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
            super(wizardContainer,
                    parent,
                    container,
                    definition,
                    connectorConfiguration,
                    context,
                    connectorExpressionContentTypeFilter);
        }

        @Override
        protected ExpressionViewer createTextControl(Composite composite, Text object) {
            ExpressionViewer expressionViewer = super.createTextControl(composite, object);
            if(Objects.equals(RUNTIME_TYPE_INPUT,object.getInputName())){
                expressionViewer.setExpressionNatureProvider(new ExpressionNatureProvider(RUNTIME_TYPES));
            }
            return expressionViewer;
        }

    }
    

}
