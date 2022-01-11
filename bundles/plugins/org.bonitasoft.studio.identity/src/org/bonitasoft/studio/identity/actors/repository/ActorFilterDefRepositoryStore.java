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
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 */

public class ActorFilterDefRepositoryStore extends AbstractDefinitionRepositoryStore<ActorFilterDefFileStore> {

    public static final String STORE_NAME = "filters-def";
    private static final Set<String> extensions = new HashSet<>();
    public static final String DEF_EXT = "def";
    static {
        extensions.add(DEF_EXT);
    }

    private DefinitionResourceProvider resourceProvider;

    @Override
    public void createRepositoryStore(IRepository repository) {
        super.createRepositoryStore(repository);
        this.resourceProvider = DefinitionResourceProvider.getInstance(this, getBundle());
    }

    @Override
    public ActorFilterDefFileStore createRepositoryFileStore(final String fileName) {
        if (fileName.endsWith(DEF_EXT)) {
            return new ActorFilterDefFileStore(fileName, this);
        }
        return null;
    }

    @Override
    public List<ActorFilterDefFileStore> getChildren() {
        List<ActorFilterDefFileStore> defFileStores = super.getChildren();

        var projectDependenciesStore = getRepository().getProjectDependenciesStore();
        if (projectDependenciesStore != null) {
            projectDependenciesStore.getActorFilterDefinitions().stream()
                    .map(t -> new DependencyActorFilterDefFileStore(t, this))
                    .forEach(defFileStores::add);
        }

        return defFileStores;
    }

    @Override
    public DefinitionResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.filterDefRepositoryName;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.filterDef);
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    protected ActorFilterDefFileStore getDefFileStore(final URL url) {
        if (url.toString().endsWith(DEF_EXT)) {
            return new URLActorFilterDefFileStore(url, this);
        }
        return null;
    }

    @Override
    protected Bundle getBundle() {
        return IdentityPlugin.getDefault().getBundle();
    }

    @Override
    protected ActorFilterDefFileStore doImportInputStream(final String fileName, final InputStream inputStream) {
        final ActorFilterDefFileStore definition = super.doImportInputStream(fileName, inputStream);
        if (definition != null) {
            resourceProvider.loadDefinitionsCategories(null);
        }
        return definition;
    }

    @Override
    public MigrationReport migrate(IProgressMonitor monitor)
            throws CoreException, MigrationException {
        var report = super.migrate(monitor);
        if (PlatformUI.isWorkbenchRunning()) {
            getResourceProvider().loadDefinitionsCategories(null);
        }
        return report;
    }

    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        if (filename != null && filename.endsWith("." + DEF_EXT)) {
            return new XMLModelCompatibilityValidator(
                    new ModelNamespaceValidator(ModelVersion.CURRENT_CONNECTOR_DEFINITION_NAMESPACE,
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
