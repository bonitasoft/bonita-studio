/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.connectors.ui.provider;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.dialog.DefinitionCategoryContentProvider;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.osgi.framework.Bundle;

/**
 * @author Aurelie Zara
 *
 */
public class ConnectorConfigurationContentProvider implements
ITreeContentProvider {


	private final List<ConnectorDefinition> connectorDefList;
	private final DefinitionResourceProvider messageProvider;
	private final ConnectorConfRepositoryStore connectorConfStore;
	protected final String unloadableCategoryName;
	protected DefinitionCategoryContentProvider definitionCategoryContentProvider;
	private final Category unCategorizedCategory;


	public ConnectorConfigurationContentProvider(){
		final AbstractDefinitionRepositoryStore<?> connectorDefStore = RepositoryManager
				.getInstance().getRepositoryStore(
						ConnectorDefRepositoryStore.class);
		connectorDefList = connectorDefStore.getDefinitions();
		connectorConfStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorConfRepositoryStore.class);

		final Bundle bundle = getBundle();
		messageProvider = DefinitionResourceProvider.getInstance(
				connectorDefStore, bundle);
		definitionCategoryContentProvider = new DefinitionCategoryContentProvider(messageProvider.getAllCategories());
		unloadableCategoryName = messageProvider.getUnloadableCategory().getId();
		unCategorizedCategory = messageProvider.getUncategorizedCategory();

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {


	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {


	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(final Object inputElement) {
		final List<Category> categories = new ArrayList<Category>();
		for(final Category c : messageProvider.getAllCategories()){
			if(c.getParentCategoryId() == null || c.getParentCategoryId().isEmpty()){
				categories.add(c);
			}
		}
		return categories.toArray();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(final Object parentElement) {
		final List<Object> result = new ArrayList<Object>();
		if (parentElement instanceof Category) {
			final Category cat = (Category) parentElement;
			final String parentId = cat.getId();
			for(final Category c : messageProvider.getAllCategories()){
				if(parentId.equals(c.getParentCategoryId())){
					result.add(c);
				}
			}
			for (final ConnectorDefinition def : connectorDefList) {
				if (def instanceof UnloadableConnectorDefinition) {
					if(cat.getId().equals(unloadableCategoryName)){
                        result.addAll(connectorConfStore.getConnectorConfigurationsFor(def.getId(), def.getVersion()));
					}
				} else {
					if (def.getCategory().isEmpty()
							&& cat.getId().equals(Messages.uncategorized)) {//FIXME category id is nls string????
                        result.addAll(connectorConfStore.getConnectorConfigurationsFor(def.getId(), def.getVersion()));
					}
					for (final Category c : def.getCategory()) {
						if (c.getId().equals(((Category) parentElement).getId())) {
							if(definitionCategoryContentProvider.isLeafCategory(def, c)){
                                result.addAll(connectorConfStore.getConnectorConfigurationsFor(def.getId(), def.getVersion()));
							}else if(def.getCategory().size() == 1){
                                result.addAll(connectorConfStore.getConnectorConfigurationsFor(def.getId(), def.getVersion()));
							}
						}
					}
				}
			}
			return result.toArray();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(final Object element) {
		if(element instanceof ConnectorDefinition){
			final ConnectorDefinition def = (ConnectorDefinition) element ;
			if(def.getCategory().isEmpty()){
				return unCategorizedCategory;
			}
			for(final Category c : def.getCategory()){
				if(definitionCategoryContentProvider.isLeafCategory(def, c)){
					return c;
				}
			}
		}else if(element instanceof Category){
			final Category category = (Category) element ;
			for(final Category c : messageProvider.getAllCategories()){
				if(c.getId().equals(category.getParentCategoryId())){
					return c;
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(final Object element) {
		final Object[] children =  getChildren(element);
		return children!= null && children.length > 0;
	}


	protected Bundle getBundle() {
		return ConnectorPlugin.getDefault().getBundle();
	}


	protected Class<?> getDefStoreClass() {
		return ConnectorDefRepositoryStore.class;
	}

}
