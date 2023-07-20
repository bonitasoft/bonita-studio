/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.export.builder;


import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.contribution.IEngineDefinitionBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;

public class RCPEngineDefintionBuilderProvider implements IEngineDefinitionBuilderProvider {

    private static final String ENGINE_DEFINITION_BUILDER_EXTENSION_ID = "org.bonitasoft.studio.engine.definition.builder";

    private List<IEngineDefinitionBuilder<?>> engineDefinitionBuilders;

    protected List<IEngineDefinitionBuilder<?>> createEngineDefinitionBuilders() {
        final List<IEngineDefinitionBuilder<?>> result = new ArrayList<IEngineDefinitionBuilder<?>>();
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                ENGINE_DEFINITION_BUILDER_EXTENSION_ID);
        for (final IConfigurationElement cfgElement : elements) {
            IEngineDefinitionBuilder<?> builder = null;
            try {
                builder = (IEngineDefinitionBuilder<?>) cfgElement.createExecutableExtension("class");
            } catch (final CoreException e) {
                BonitaStudioLog.error("Failed to initialize IEngineDefinitionBuilder: " + cfgElement, e,
                        EnginePlugin.PLUGIN_ID);
            }
            if (builder != null) {
                result.add(builder);
            }
        }
        return result;
    }

    @Override
    public <T> IEngineDefinitionBuilder<T> getEngineDefinitionBuilder(EObject context, EObject element,
            Class<T> builderType) throws EngineDefinitionBuilderNotFoundException {
        if (engineDefinitionBuilders == null) {
            engineDefinitionBuilders = createEngineDefinitionBuilders();
        }
        return (IEngineDefinitionBuilder<T>) engineDefinitionBuilders.stream()
                .filter(builder -> builder.appliesTo(context, element)).findFirst()
                .orElseThrow(() -> new EngineDefinitionBuilderNotFoundException(
                        String.format("No engine builder found for element %s in context %s ", element, context)));
    }



}
