/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.provider;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;

public class ConnectorDefinitionRegistry {

    private Map<String, ExtendedConnectorDefinition> definitionRegistry = new HashMap<>();
    private Map<String, ExtendedCategory> categoryRegistry = new HashMap<>();

    private static final Locale DEFAULT_LOCALE = new Locale(Platform.getNL());

    public synchronized ConnectorDefinitionRegistry build(IDefinitionRepositoryStore<IRepositoryFileStore<?>> store) {
        BonitaStudioLog.info(String.format("Building %s registry...", ((IRepositoryStore<?>) store).getName()), CommonRepositoryPlugin.PLUGIN_ID);
        clearRegistries();
        for (ConnectorDefinition definition : store.getDefinitions()) {
            DefinitionResourceLoaderProvider resourceLoaderProvider = store.find(definition)
                    .map(DefinitionResourceLoaderProvider.class::cast).orElse(null);
            if (resourceLoaderProvider != null) {
                ResourceBundle resourceBundle = resourceLoaderProvider.getBundleResourceLoader()
                        .getResourceBundle(DEFAULT_LOCALE);
                Image icon = resourceLoaderProvider.getDefinitionImageResourceLoader().getIcon(definition);
                definitionRegistry.put(id(definition), new ExtendedConnectorDefinition(definition,
                        icon,
                        resourceBundle));

                definition.getCategory().stream()
                        .map(category -> new ExtendedCategory(category,
                                    resourceLoaderProvider.getDefinitionImageResourceLoader().getIcon(category),
                                    getCategoryLabel(resourceBundle, category)))
                        .forEach(ec -> {
                            ExtendedCategory extendedCategory = categoryRegistry.get(ec.getId());
                            if (extendedCategory != null
                                    && extendedCategory.getLabel() != null
                                    && !extendedCategory.getLabel().isEmpty()
                                    && !Objects.equals(extendedCategory.getLabel(), ec.getLabel())) {
                                //Keep existing category label
                            } else if (extendedCategory == null || (extendedCategory != null
                                    && (extendedCategory.getLabel() == null
                                            || extendedCategory.getLabel().isEmpty()))) {
                                categoryRegistry.put(ec.getId(), ec);
                            }
                        });
            }
        }

        return this;
    }

    private void clearRegistries() {
        definitionRegistry.values().forEach(ExtendedConnectorDefinition::dispose);
        definitionRegistry.clear();
        categoryRegistry.values().forEach(ExtendedCategory::dispose);
        categoryRegistry.clear();
    }

    private String getCategoryLabel(ResourceBundle resourceBundle, Category category) {
        try {
            return resourceBundle != null ? resourceBundle
                    .getString(category.getId() + "." + LocalizedConnectorDefinition.CATEGORY) : null;
        }catch (MissingResourceException e) {
            return null;
        }
       
    }
    
    public Optional<ExtendedConnectorDefinition> find(ConnectorDefinition definition) {
        return Optional.ofNullable(definitionRegistry.get(id(definition)));
    }
    
    public Optional<ExtendedConnectorDefinition> find(String id, String version) {
        return Optional.ofNullable(definitionRegistry.get(id(id,version)));
    }

    public Collection<ExtendedCategory> getCategories(){
        return categoryRegistry.values();
    }

    public static String id(ConnectorDefinition definition) {
        return id(definition.getId(),definition.getVersion());
    }

    public Optional<ExtendedCategory> find(Category category) {
       return Optional.ofNullable(categoryRegistry.get(category.getId()));
    }

    public static String id(String id, String version) {
        return id + "-" + version;
    }
    
    public Category findParentCategory(Category category) {
        if(category.getParentCategoryId() != null) {
            return categoryRegistry.get(category.getParentCategoryId());
        }
        return null;
    }

    public List<ExtendedConnectorDefinition> getDefinitions() {
        return definitionRegistry.values().stream().collect(Collectors.toList());
    }
}
