/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.wizard.office;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

public class FilteredGeneratedConnectorConfigurationWizardPage extends GeneratedConnectorWizardPage {

    private Set<String> filteredInputNames = new HashSet<>();

    public FilteredGeneratedConnectorConfigurationWizardPage(Set<String> filteredInputNames) {
        this.filteredInputNames = filteredInputNames;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage#getPageComponentSwitch(org.eclipse.emf.databinding.
     * EMFDataBindingContext, org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected PageComponentSwitch getPageComponentSwitch(EMFDataBindingContext context, Composite pageComposite) {
        if (componentSwitch == null) {
            final PageComponentSwitchBuilder builder = getComponentBuilder(context);
            componentSwitch = new PageComponentSwitch(getContainer(), pageComposite, builder) {

                /*
                 * (non-Javadoc)
                 * @see org.eclipse.emf.ecore.util.Switch#doSwitch(org.eclipse.emf.ecore.EObject)
                 */
                @Override
                public Component doSwitch(EObject eObject) {
                    if (eObject instanceof Component
                            && filteredInputNames.contains(((Component) eObject).getId())) {
                        return null;
                    }
                    return super.doSwitch(eObject);
                }
            };
        }
        return componentSwitch;
    }

    protected PageComponentSwitchBuilder getComponentBuilder(EMFDataBindingContext context) {
        return new PageComponentSwitchBuilder(getElementContainer(), getDefinition(), getConfiguration(), context,
                getMessageProvider(), getExpressionTypeFilter());
    }

}
