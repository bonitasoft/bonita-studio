/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.actors.repository;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.AbstractConnectorImplRepositoryStore;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.graphics.Image;

public class ActorFilterImplRepositoryStore extends AbstractConnectorImplRepositoryStore<ActorFilterImplFileStore> {

    public static final String IMPL_EXT = "impl";
    
    private static final String STORE_NAME = "filters-impl";
    private static final Set<String> extensions = Set.of(IMPL_EXT);

    @Override
    public ActorFilterImplFileStore createRepositoryFileStore(String fileName) {
        return new ActorFilterImplFileStore(fileName, this);
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.filterImplRepositoryName;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("actor_filter-implem-new.png", IdentityPlugin.getDefault());
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    public List<ActorFilterImplFileStore> getChildren() {
        List<ActorFilterImplFileStore> result = super.getChildren();
        var projectDependenciesStore = getRepository().getProjectDependenciesStore();
        if (projectDependenciesStore != null) {
            projectDependenciesStore.getActorFilterImplementations().stream()
                    .map(t -> new DependencyActorFilterImplFileStore(t, this))
                    .forEach(result::add);
        }
        return result;
    }

    @Override
    protected IRepositoryStore<? extends IRepositoryFileStore> getSourceRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ActorFilterSourceRepositoryStore.class);
    }

    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        if (filename != null && filename.endsWith("." + IMPL_EXT)) {
            return new XMLModelCompatibilityValidator(
                    new ModelNamespaceValidator(ModelVersion.CURRENT_CONNECTOR_IMPLEMENTATION_NAMESPACE,
                            String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion, filename),
                            String.format(org.bonitasoft.studio.common.Messages.migrationWillBreakRetroCompatibility,
                                    filename))).validate(inputStream);
        }
        return super.validate(filename, inputStream);
    }
    
    @Override
    public int getImportOrder() {
        return 5;
    }

}
