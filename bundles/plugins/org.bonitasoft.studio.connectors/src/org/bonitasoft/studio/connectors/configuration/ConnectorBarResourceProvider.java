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

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.io.Files.toByteArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.JarDependencies;
import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationResourceFactoryImpl;
import org.bonitasoft.studio.connector.model.implementation.util.ConnectorImplementationXMLProcessor;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * @author Romain Bioteau
 */
public class ConnectorBarResourceProvider implements BARResourcesProvider {

    protected final RepositoryAccessor repositoryAccessor;
    private final ConnectorImplementationResourceFactoryImpl resourceFactory;
    private final ConnectorImplementationXMLProcessor xmlProcessor;

    @Inject
    public ConnectorBarResourceProvider(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        this.resourceFactory = new ConnectorImplementationResourceFactoryImpl();
        this.xmlProcessor = new ConnectorImplementationXMLProcessor();
    }

    @Override
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process, final Configuration configuration,
            final Set<EObject> excludedObjects) throws IOException, InvocationTargetException, InterruptedException {
        if (configuration == null) {
            return;
        }
        final List<BarResource> resources = new ArrayList<>();
        final IImplementationRepositoryStore implStore = getImplementationStore();
        for (final DefinitionMapping association : filter(configuration.getDefinitionMappings(), and(actorFilterMappings(), withImplementation()))) {
            final String connectorImplementationFilename = NamingUtils.toConnectorImplementationFilename(
                    association.getImplementationId(),
                    association.getImplementationVersion(), true);
            final String implId = association.getImplementationId();
            final String implVersion = association.getImplementationVersion();

            final EMFFileStore implementationFileStore = (EMFFileStore) implStore.getImplementationFileStore(implId, implVersion);
            if (implementationFileStore == null) {
                throw new FileNotFoundException(String.format("%s (%s) not found in repository", association.getImplementationId(),
                        association.getImplementationVersion()));
            }
            addImplementation(builder, connectorImplementationFilename, implementationFileStore, configuration);

            final ConnectorImplementation connectorImplementation = (ConnectorImplementation) implementationFileStore.getContent();
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
                return getFragmentType().equals(mapping.getType());
            }

        };
    }

    protected String getFragmentType() {
        return FragmentTypes.CONNECTOR;
    }

    protected DependencyRepositoryStore getDependencyRepositoryStore() {
        return repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class);
    }

    protected IImplementationRepositoryStore getImplementationStore() {
        return repositoryAccessor.getRepositoryStore(ConnectorImplRepositoryStore.class);
    }

    protected void addProcessDependencies(final BusinessArchiveBuilder builder, final Configuration configuration, final List<BarResource> resources,
            final ConnectorImplementation implementation, final boolean customImpl) throws InvocationTargetException, InterruptedException, IOException {
        final DependencyRepositoryStore dependencyStore = repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class);
        final Optional<FragmentContainer> fragmentContainer = tryFind(configuration.getProcessDependencies(), withId(getFragmentType()));
        if (fragmentContainer.isPresent()) {
            final Optional<FragmentContainer> implementationContainer = tryFind(
                    fragmentContainer.get().getChildren(),
                    withId(NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(),
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


    protected void addResource(final List<BarResource> resources, final DependencyRepositoryStore libStore, final String jarName) throws IOException {
        final IRepositoryFileStore jarFileStore = libStore.getChild(jarName);
        if (jarFileStore != null) {
            resources.add(new BarResource(jarFileStore.getName(), jarFileStore.toByteArray()));
        }
    }

    private boolean addImplementationJar(final BusinessArchiveBuilder builder, final ConnectorImplementation impl) throws InvocationTargetException,
            InterruptedException, IOException {
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

    protected File exportJar(final String connectorJarName, final PackageFileStore file) throws InvocationTargetException, InterruptedException {
        final File tmpFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), connectorJarName);
        tmpFile.delete();
        file.exportAsJar(tmpFile.getAbsolutePath(), false);
        return tmpFile;
    }

    protected SourceRepositoryStore<?> getSourceStore() {
        return repositoryAccessor.getRepositoryStore(ConnectorSourceRepositoryStore.class);
    }

    protected void addImplementation(final BusinessArchiveBuilder builder, final String connectorImplementationFilename,
            final EMFFileStore implementationFileStore, final Configuration configuration)
            throws IOException {
        builder.addConnectorImplementation(
                newBarResource(connectorImplementationFilename, implementationFileStore, configuration));
    }

    protected BarResource newBarResource(final String connectorImplementationFilename, final EMFFileStore implementationFileStore,
            final Configuration configuration) throws UnsupportedEncodingException, IOException {
        return new BarResource(connectorImplementationFilename,
                toXMLString(implemetationWithSelfDep(implementationFileStore, configuration)).getBytes("UTF-8"));
    }

    protected ConnectorImplementation implemetationWithSelfDep(final EMFFileStore implementationFileStore, final Configuration configuration)
            throws IOException {
        final ConnectorImplementation content = (ConnectorImplementation) implementationFileStore.getContent();
        final ConnectorImplementation connectorImplementation = EcoreUtil.copy(content);
        JarDependencies dependencies = content.getJarDependencies();
        if (dependencies == null) {
            dependencies = ConnectorImplementationFactory.eINSTANCE.createJarDependencies();
            connectorImplementation.setJarDependencies(dependencies);
        }
        final EList<String> jarDependencies = dependencies.getJarDependency();
        final String implementationJarName = NamingUtils.toConnectorImplementationJarName(content);
        if (implementationFileStore.canBeShared()
                && selfDependenciesIsExported(configuration, content)
                && !jarDependencies.contains(implementationJarName)) {
            connectorImplementation.getJarDependencies().getJarDependency().add(implementationJarName);
        }
        return connectorImplementation;
    }

    protected String toXMLString(final ConnectorImplementation connectorImplementation) throws IOException {
        final XMLResource resource = (XMLResource) resourceFactory.createResource(URI.createURI("tmp.impl"));
        resource.getContents().add(connectorImplementation);
        final Map<Object, Object> options = resource.getDefaultSaveOptions();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        try {
            return xmlProcessor.saveToString(resource, options).replace("implementation:ConnectorImplementation", "implementation:connectorImplementation");
        } finally {
            if (resource != null) {
                resource.delete(options);
            }
        }
    }

    private boolean selfDependenciesIsExported(Configuration configuration, ConnectorImplementation implementation) {
        final Optional<FragmentContainer> connectorContainer = tryFind(configuration.getProcessDependencies(), withId(getFragmentType()));
        final Optional<FragmentContainer> implementationContainer = tryFind(connectorContainer.get().getChildren(),
                withId(NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(),
                        implementation.getImplementationVersion(), false)));
        final Optional<Fragment> fragment = tryFind(implementationContainer.get().getFragments(),
                Predicates.and(withValue(NamingUtils.toConnectorImplementationJarName(implementation)), exportedJarFragment()));
        return fragment.isPresent();
    }

    private Predicate<? super Fragment> exportedJarFragment() {
        return new Predicate<Fragment>() {

            @Override
            public boolean apply(Fragment fragment) {
                return fragment.isExported();
            }
        };
    }

    private Predicate<? super Fragment> withValue(final String value) {
        return new Predicate<Fragment>() {

            @Override
            public boolean apply(Fragment fragment) {
                return Objects.equals(fragment.getValue(), value);
            }
        };
    }

    private Predicate<FragmentContainer> withId(final String type) {
        return new Predicate<FragmentContainer>() {

            @Override
            public boolean apply(FragmentContainer fc) {
                return fc.getId().equals(type);
            }
        };
    }

}
