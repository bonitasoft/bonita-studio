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

import static com.google.common.io.Files.toByteArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.bpm.connector.model.implementation.JarDependencies;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationResourceFactoryImpl;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationXMLProcessor;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.DefinitionMapping;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.core.JavaModelException;

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
    public IStatus addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process,
            final Configuration configuration) throws IOException, InvocationTargetException, InterruptedException {
        if (configuration == null) {
            return ValidationStatus.error("No configuration selected.");
        }
        final IImplementationRepositoryStore implStore = getImplementationStore();
        final List<BarResource> resources = new ArrayList<>();
        List<DefinitionMapping> mappings = configuration.getDefinitionMappings().stream()
                .filter(mapping -> Objects.equals(getFragmentType(), mapping.getType()))
                .filter(mapping -> java.util.Optional.ofNullable(mapping.getImplementationId()).isPresent()
                        && java.util.Optional.ofNullable(mapping.getDefinitionVersion()).isPresent())
                .collect(Collectors.toList());

        for (final DefinitionMapping association : mappings) {
            final String connectorImplementationFilename = NamingUtils.toConnectorImplementationFilename(
                    association.getImplementationId(),
                    association.getImplementationVersion(), true);
            final String implId = association.getImplementationId();
            final String implVersion = association.getImplementationVersion();

            final EMFFileStore implementationFileStore = (EMFFileStore) implStore.getImplementationFileStore(implId,
                    implVersion);
            if (implementationFileStore == null) {
                throw new FileNotFoundException(
                        String.format("%s (%s) not found in repository", association.getImplementationId(),
                                association.getImplementationVersion()));
            }
            try {
                addImplementation(builder, connectorImplementationFilename, implementationFileStore, configuration);
                ConnectorImplementation connectorImplementation = (ConnectorImplementation) implementationFileStore
                        .getContent();
                addProcessDependencies(builder, configuration, resources, connectorImplementation,
                        implementationFileStore.canBeShared());
            } catch (JavaModelException | ReadFileStoreException e) {
                throw new InvocationTargetException(e);
            }
        }

        for (final BarResource barResource : resources) {
            builder.addClasspathResource(barResource);
        }
        return Status.OK_STATUS;
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

    protected void addProcessDependencies(final BusinessArchiveBuilder builder, final Configuration configuration,
            final List<BarResource> resources,
            final ConnectorImplementation implementation, final boolean customImpl)
            throws InvocationTargetException, InterruptedException, IOException, JavaModelException {
        final DependencyRepositoryStore dependencyStore = repositoryAccessor
                .getRepositoryStore(DependencyRepositoryStore.class);
        final java.util.Optional<FragmentContainer> fragmentContainer = configuration.getProcessDependencies().stream()
                .filter(fc -> fc.getId().equals(getFragmentType())).findFirst();
        if (fragmentContainer.isPresent()) {
            final java.util.Optional<FragmentContainer> implementationContainer = fragmentContainer.get().getChildren()
                    .stream()
                    .filter(fragment -> Objects.equals(fragment.getId(),
                            NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(),
                                    implementation.getImplementationVersion(),
                                    false)))
                    .findFirst();

            if (implementationContainer.isPresent()) {
                for (final Fragment fragment : implementationContainer.get().getFragments().stream()
                        .filter(fragment -> fragment.isExported())
                        .collect(Collectors.toList())) {
                    if (customImpl && NamingUtils.toConnectorImplementationJarName(implementation)
                            .equals(fragment.getValue())) { //Generate jar from source file
                        addImplementationJar(builder, implementation);
                    }
                    final IRepositoryFileStore jarFile = dependencyStore.getChild(fragment.getValue(), true);
                    if (jarFile != null) {
                        resources.add(new BarResource(jarFile.getName(), jarFile.toByteArray()));
                    }
                }
            }
        }
    }

    protected void addResource(final List<BarResource> resources, final DependencyRepositoryStore libStore,
            final String jarName) throws IOException {
        final IRepositoryFileStore jarFileStore = libStore.getChild(jarName, true);
        if (jarFileStore != null) {
            resources.add(new BarResource(jarFileStore.getName(), jarFileStore.toByteArray()));
        }
    }

    private boolean addImplementationJar(final BusinessArchiveBuilder builder, final ConnectorImplementation impl)
            throws InvocationTargetException,
            InterruptedException, IOException, JavaModelException {
        final SourceRepositoryStore<?> sourceStore = getSourceStore();
        final String connectorJarName = NamingUtils.toConnectorImplementationJarName(impl);
        final String qualifiedClassName = impl.getImplementationClassname();
        String packageName = "";
        if (qualifiedClassName.indexOf(".") != -1) {
            packageName = qualifiedClassName.substring(0, qualifiedClassName.lastIndexOf("."));
        }
        final PackageFileStore file = (PackageFileStore) sourceStore.getChild(packageName, true);
        if (file == null) {
            return false;
        }
        if (classInSourceProject(qualifiedClassName)) {
            final File tmpFile = exportJar(connectorJarName, file);
            try {
                builder.addClasspathResource(new BarResource(connectorJarName, toByteArray(tmpFile)));
            } finally {
                tmpFile.delete();
            }
            return true;
        }
        return false;
    }

    protected boolean classInSourceProject(final String qualifiedClassName) throws JavaModelException {
        return repositoryAccessor.getCurrentRepository()
                .orElseThrow()
                .getJavaProject()
                .findType(qualifiedClassName) != null;
    }

    protected File exportJar(final String connectorJarName, final PackageFileStore file)
            throws InvocationTargetException, InterruptedException, IOException {
        var tmpFile = Files.createTempFile(null, connectorJarName);
        Files.delete(tmpFile);
        file.exportAsJar(tmpFile.toFile().getAbsolutePath(), false);
        return tmpFile.toFile();
    }

    protected SourceRepositoryStore<?> getSourceStore() {
        return repositoryAccessor.getRepositoryStore(ConnectorSourceRepositoryStore.class);
    }

    protected void addImplementation(final BusinessArchiveBuilder builder, final String connectorImplementationFilename,
            final EMFFileStore implementationFileStore, final Configuration configuration)
            throws IOException, ReadFileStoreException {
        builder.addConnectorImplementation(
                newBarResource(connectorImplementationFilename, implementationFileStore, configuration));
    }

    protected BarResource newBarResource(final String connectorImplementationFilename,
            final EMFFileStore implementationFileStore,
            final Configuration configuration)
            throws UnsupportedEncodingException, IOException, ReadFileStoreException {
        return new BarResource(connectorImplementationFilename,
                toXMLString(implemetationWithSelfDep(implementationFileStore, configuration)).getBytes("UTF-8"));
    }

    protected ConnectorImplementation implemetationWithSelfDep(final EMFFileStore implementationFileStore,
            final Configuration configuration)
            throws IOException, ReadFileStoreException {
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
            return xmlProcessor.saveToString(resource, options).replace("implementation:ConnectorImplementation",
                    "implementation:connectorImplementation");
        } finally {
            if (resource != null) {
                resource.delete(options);
            }
        }
    }

    private boolean selfDependenciesIsExported(Configuration configuration, ConnectorImplementation implementation) {
        final java.util.Optional<FragmentContainer> connectorContainer = configuration.getProcessDependencies().stream()
                .filter(fragment -> Objects.equals(fragment.getId(), getFragmentType())).findFirst();
        final java.util.Optional<FragmentContainer> implementationContainer = connectorContainer.get().getChildren()
                .stream()
                .filter(fragment -> Objects.equals(fragment.getId(),
                        NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(),
                                implementation.getImplementationVersion(), false)))
                .findAny();
        return implementationContainer.get().getFragments().stream()
                .filter(fragment -> Objects.equals(fragment.getValue(),
                        NamingUtils.toConnectorImplementationJarName(implementation)))
                .filter(fragment -> fragment.isExported()).findFirst().isPresent();
    }

}
