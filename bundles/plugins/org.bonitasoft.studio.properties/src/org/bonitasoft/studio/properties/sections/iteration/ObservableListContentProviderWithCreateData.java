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
package org.bonitasoft.studio.properties.sections.iteration;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;


/**
 * @author Romain Bioteau
 *
 */
public class ObservableListContentProviderWithCreateData extends ObservableListContentProvider {

    private final IConfigurationElement[] proposalListeners;
    private final EObject context;

    public ObservableListContentProviderWithCreateData(final EObject context) {
        proposalListeners = getProposalListeners();
        this.context = context;
    }

    @Override
    public Object[] getElements(final Object inputElement) {
        final Object[] elements = super.getElements(inputElement);
        final List<Object> result = new ArrayList<Object>();
        for (final Object element : elements) {
            result.add(element);
        }
        for (final IConfigurationElement configElement : proposalListeners) {
            try {
                final IProposalListener extension = (IProposalListener) configElement.createExecutableExtension("providerClass");
                if (extension.isRelevant(context)) {
                    result.add(extension);
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return result.toArray();
    }

    private IConfigurationElement[] getProposalListeners() {
        final IConfigurationElement[] configurationElements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.expression.proposalListener");
        final List<IConfigurationElement> elements = new ArrayList<IConfigurationElement>();
        // Filters duplicates
        for (final IConfigurationElement e : configurationElements) {
            final String type = e.getAttribute("type");
            if (type.equals(ExpressionConstants.VARIABLE_TYPE)) {
                elements.add(e);
            }
        }
        return elements.toArray(new IConfigurationElement[elements.size()]);
    }

}
