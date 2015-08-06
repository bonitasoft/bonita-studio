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
package org.bonitasoft.studio.actors.configuration;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.and;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.io.Files.toByteArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.actors.repository.ActorFilterImplFileStore;
import org.bonitasoft.studio.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterSourceRepositoryStore;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
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
public class ActorFilterBarResourceProvider implements BARResourcesProvider {

    private final RepositoryAccessor repositoryAccessor;

    @Inject
    public ActorFilterBarResourceProvider(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process, final Configuration configuration,
            final Set<EObject> excludedObjects) throws Exception {
        checkArgument(configuration != null);

        final List<BarResource> resources = new ArrayList<BarResource>();
        final ActorFilterImplRepositoryStore implStore = repositoryAccessor.getRepositoryStore(ActorFilterImplRepositoryStore.class);
        for (final DefinitionMapping association : filter(configuration.getDefinitionMappings(), and(actorFilterMappings(), withImplementation()))) {
            final String connectorImplementationFilename = NamingUtils.toConnectorImplementationFilename(
                    association.getImplementationId(),
                    association.getImplementationVersion(), true);
            final ActorFilterImplFileStore implementationFileStore = implStore.getChild(connectorImplementationFilename);
            if (implementationFileStore == null) {
                throw new FileNotFoundException(String.format("%s (%s) not found in repository", association.getImplementationId(),
                        association.getImplementationVersion()));
            }
            builder.addUserFilters(new BarResource(connectorImplementationFilename, implementationFileStore.toByteArray()));

            final ConnectorImplementation connectorImplementation = implementationFileStore.getContent();
            addProcessDependencies(builder, configuration, resources, connectorImplementation, implementationFileStore.canBeShared());
        }

        for (final BarResource barResource : resources) {
            builder.addClasspathResource(barResource);
        }
    }

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
                return FragmentTypes.ACTOR_FILTER.equals(mapping.getType());
            }
        };
    }

    private void addProcessDependencies(final BusinessArchiveBuilder builder, final Configuration configuration, final List<BarResource> resources,
            final ConnectorImplementation implementation, final boolean customImpl)
            throws Exception {
        final DependencyRepositoryStore dependencyStore = repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class);
        final Optional<FragmentContainer> actorFilterContainer = tryFind(configuration.getProcessDependencies(), containerWithId(FragmentTypes.ACTOR_FILTER));
        if (actorFilterContainer.isPresent()) {
            final Optional<FragmentContainer> implementationContainer = tryFind(
                    actorFilterContainer.get().getChildren(),
                    containerWithId(NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(),
                            implementation.getImplementationVersion(),
                            false)));
            if (implementationContainer.isPresent()) {
                for (final Fragment fragment : filter(implementationContainer.get().getFragments(), exportedJarFragment())) {
                    if (customImpl && NamingUtils.toConnectorImplementationJarName(implementation).equals(fragment.getValue())) { //Generate jar from source file
                        addImplementationJar(builder, implementation);
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

    private void addImplementationJar(final BusinessArchiveBuilder builder, final ConnectorImplementation impl) throws Exception {
        final ActorFilterSourceRepositoryStore sourceStore = repositoryAccessor.getRepositoryStore(ActorFilterSourceRepositoryStore.class);
        final String connectorJarName = NamingUtils.toConnectorImplementationJarName(impl);
        final String qualifiedClassName = impl.getImplementationClassname();
        String packageName = "";
        if (qualifiedClassName.indexOf(".") != -1) {
            packageName = qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf("."));
        }
        final PackageFileStore file = (PackageFileStore) sourceStore.getChild(packageName);
        if (file != null) {
            final File tmpFile = exportJar(connectorJarName, file);
            try {
                builder.addClasspathResource(new BarResource(connectorJarName, toByteArray(tmpFile)));
            } finally {
                tmpFile.delete();
            }
        }
    }

    private File exportJar(final String connectorJarName, final PackageFileStore file) throws InvocationTargetException, InterruptedException {
        final File tmpFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), connectorJarName);
        tmpFile.delete();
        file.exportAsJar(tmpFile.getAbsolutePath(), false);
        return tmpFile;
    }

}
