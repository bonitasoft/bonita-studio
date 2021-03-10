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
package org.bonitasoft.studio.connector.wizard.uipath.switchBuilder;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class UIPathPageComponentSwitchBuilder extends PageComponentSwitchBuilder {

    private Map<String, IValidator> customMandatoryValidators;

    public UIPathPageComponentSwitchBuilder(EObject container, ConnectorDefinition definition,
            ConnectorConfiguration connectorConfiguration, EMFDataBindingContext context,
            DefinitionResourceProvider messageProvider, 
            AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
        super(container, definition, connectorConfiguration, context, messageProvider, connectorExpressionContentTypeFilter);
        this.customMandatoryValidators = new HashMap<>();
    }

    public void addCustomMandatoryValidator(String key, IValidator value) {
        customMandatoryValidators.put(key, value);
    }

    @Override
    protected void handleMandatory(Text object, Input input, ExpressionViewer viewer) {
        if (customMandatoryValidators.containsKey(object.getId())) {
            viewer.setMandatoryField(getLabel(object.getId()), context, customMandatoryValidators.get(object.getId()));
        } else {
            super.handleMandatory(object, input, viewer);
        }
    }

    @Override
    public Label createFieldLabel(Composite composite, int verticalAlignment, String id, boolean isMandatory) {
        return super.createFieldLabel(composite, verticalAlignment, id,
                customMandatoryValidators.containsKey(id) || isMandatory);
    }

}
