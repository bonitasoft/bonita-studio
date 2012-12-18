/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.definition;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 * 
 */
public abstract class AbstractDefinitionContentProvider implements ITreeContentProvider {

    private final List<ConnectorDefinition> connectorDefList;
    private final DefinitionResourceProvider messageProvider;
    private final String unloadableCategoryName;

    public AbstractDefinitionContentProvider() {
        this(false);
    }

    public AbstractDefinitionContentProvider(boolean userDefinitionOnly) {
        final AbstractDefinitionRepositoryStore<?> connectorDefStore = (AbstractDefinitionRepositoryStore<?>) RepositoryManager
                .getInstance().getRepositoryStore(
                        getDefStoreClass());
        connectorDefList = connectorDefStore.getDefinitions();
        if (userDefinitionOnly) {
            List<ConnectorDefinition> toRemove = new ArrayList<ConnectorDefinition>();
            for (ConnectorDefinition definition : connectorDefList) {
                String path = definition.eResource().getURI().toFileString();
                if (!path.contains(connectorDefStore.getResource()
                        .getLocation().toFile().getAbsolutePath())) {
                    toRemove.add(definition);
                }
            }
            connectorDefList.removeAll(toRemove);
        }
        final Bundle bundle = getBundle();
        messageProvider = DefinitionResourceProvider.getInstance(
                connectorDefStore, bundle);

        unloadableCategoryName = messageProvider.getUnloadableCategory().getId();
    }

    protected abstract Bundle getBundle();

    protected abstract Class<?> getDefStoreClass();

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
    }

    @Override
    public Object[] getChildren(Object element) {
        if (element instanceof Category) {
            Category cat = (Category) element;
            List<ConnectorDefinition> result = new ArrayList<ConnectorDefinition>();
            for (ConnectorDefinition def : connectorDefList) {
                if (def instanceof UnloadableConnectorDefinition) {
                    if(cat.getId().equals(unloadableCategoryName)){
                        result.add(def);
                    }
                } else {
                    if (def.getCategory().isEmpty()
                            && cat.getId().equals(Messages.uncategorized)) {//FIXME category id is nls string????
                        result.add(def);
                    }
                    for (Category c : def.getCategory()) {
                        if (c.getId().equals(((Category) element).getId())) {
                            result.add(def);
                        }
                    }
                }
            }
            return result.toArray();
        }
        return null;
    }

    @Override
    public Object[] getElements(Object element) {
        List<Category> categories = messageProvider.getAllCategories();
        return categories.toArray();
    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return element instanceof Category;
    }

}
