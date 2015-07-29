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
package org.bonitasoft.studio.connectors.configuration;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.and;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.io.Files.toByteArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.AbstractConnectorImplRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractConnectorBarResourceProvider implements BARResourcesProvider {

    protected final RepositoryAccessor repositoryAccessor;

    @Inject
    public AbstractConnectorBarResourceProvider(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process, final Configuration configuration,
            final Set<EObject> excludedObjects) throws Exception {
        checkArgument(configuration != null);

        final List<BarResource> resources = new ArrayList<BarResource>();
        final AbstractConnectorImplRepositoryStore<?> implStore = getImplementationStore();
        for (final DefinitionMapping association : filter(configuration.getDefinitionMappings(), and(actorFilterMappings(), withImplementation()))) {
            final String connectorImplementationFilename = NamingUtils.toConnectorImplementationFilename(
                    association.getImplementationId(),
                    association.getImplementationVersion(), true);
            final EMFFileStore implementationFileStore = implStore.getChild(connectorImplementationFilename);
            if (implementationFileStore == null) {
                throw new FileNotFoundException(String.format("%s (%s) not found in repository", association.getImplementationId(),
                        association.getImplementationVersion()));
            }
            addImplementation(builder, connectorImplementationFilename, implementationFileStore);

            final ConnectorImplementation connectorImplementation = (ConnectorImplementation) implementationFileStore.getContent();
            addProcessDependencies(builder, configuration, resources, connectorImplementation, implementationFileStore.canBeShared());
        }

        for (final BarResource barResource : resources) {
            builder.addClasspathResource(barResource);
        }
    }

    protected abstract void addImplementation(final BusinessArchiveBuilder builder, final String connectorImplementationFilename,
            final EMFFileStore implementationFileStore)
            throws IOException;

    protected abstract AbstractConnectorImplRepositoryStore<?> getImplementationStore();

    private Predicate<DefinitionMapping> withImplementation() {
        return new Predicate<DefinitionMapping>() {

            @Override
            public boolean apply(final DefinitionMapping mapping) {
                return !isNullOrEmpty(mapping.getImplementationId()) && !isNullOrEmpty(mapping.getDefinitionVersion());
            }
        };
    }

    private Predicate<DefinitionMapping> actorFilterMappings() {
        return new Predicate<DefinitionMapping>() {

            @Override
            public boolean apply(final DefinitionMapping mapping) {
                return getFragmentType().equals(mapping.getType());
            }

        };
    }

    protected abstract String getFragmentType();

    private void addProcessDependencies(final BusinessArchiveBuilder builder, final Configuration configuration, final List<BarResource> resources,
            final ConnectorImplementation implementation, final boolean customImpl)
            throws Exception {
        final DependencyRepositoryStore dependencyStore = repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class);
        final Optional<FragmentContainer> filterContainer = tryFind(configuration.getProcessDependencies(), containerWithId(getFragmentType()));
        if (filterContainer.isPresent()) {
            final Optional<FragmentContainer> implementationContainer = tryFind(
                    filterContainer.get().getChildren(),
                    containerWithId(NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(),
                            implementation.getImplementationVersion(),
                            false)));
            if (implementationContainer.isPresent()) {
                for (final Fragment fragment : filter(implementationContainer.get().getFragments(), exportedJarFragment())) {
                    if (customImpl && NamingUtils.toConnectorImplementationJarName(implementation).equals(fragment.getValue())) { //Generate jar from source file
                        if (addImplementationJar(builder, implementation)) {
                            continue;
                        }
                    }
                    final IRepositoryFileStore jarFile = dependencyStore.getChild(fragment.getValue());
                    if (jarFile != null) {
                        resources.add(new BarResource(jarFile.getName(), jarFile.toByteArray()));
                    }
                }
            }
        }
    }

    private Predicate<Fragment> exportedJarFragment() {
        return new Predicate<Fragment>() {

            @Override
            public boolean apply(final Fragment input) {
                return input.isExported() && input.getValue().endsWith(".jar");
            }
        };
    }

    private Predicate<FragmentContainer> containerWithId(final String containerId) {
        return new Predicate<FragmentContainer>() {

            @Override
            public boolean apply(final FragmentContainer input) {
                return containerId.equals(input.getId());
            }
        };
    }

    private boolean addImplementationJar(final BusinessArchiveBuilder builder, final ConnectorImplementation impl) throws Exception {
        final SourceRepositoryStore<?> sourceStore = getSourceStore();
        final String connectorJarName = NamingUtils.toConnectorImplementationJarName(impl);
        final String qualifiedClassName = impl.getImplementationClassname();
        String packageName = "";
        if (qualifiedClassName.indexOf(".") != -1) {
            packageName = qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf("."));
        }
        final PackageFileStore file = (PackageFileStore) sourceStore.getChild(packageName);
        if (file == null) {
            return false;
        }
        final File tmpFile = exportJar(connectorJarName, file);
        try {
            builder.addClasspathResource(new BarResource(connectorJarName, toByteArray(tmpFile)));
        } finally {
            tmpFile.delete();
        }
        return true;
    }

    protected abstract SourceRepositoryStore<?> getSourceStore();

    protected File exportJar(final String connectorJarName, final PackageFileStore file) throws InvocationTargetException, InterruptedException {
        final File tmpFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), connectorJarName);
        tmpFile.delete();
        file.exportAsJar(tmpFile.getAbsolutePath(), false);
        return tmpFile;
    }

}
