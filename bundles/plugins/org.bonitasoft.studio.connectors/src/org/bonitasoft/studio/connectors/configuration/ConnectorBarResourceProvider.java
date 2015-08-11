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
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class ConnectorBarResourceProvider implements BARResourcesProvider {

    private final RepositoryAccessor repositoryAccessor;

    public ConnectorBarResourceProvider(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process, final Configuration configuration,
            final Set<EObject> excludedObjects) {
        if (configuration == null) {
            return;
        }
        final List<BarResource> resources = new ArrayList<BarResource>();

        final DependencyRepositoryStore libStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
        final ConnectorImplRepositoryStore implStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
        for (final DefinitionMapping association : configuration.getDefinitionMappings()) {
            if (association.getType().equals(FragmentTypes.CONNECTOR)) {
                final String implId = association.getImplementationId();
                final String implVersion = association.getImplementationVersion();
                if (implId != null && implVersion != null) {
                    final ConnectorImplementation implementation = implStore.getImplementation(implId, implVersion);
                    if (implementation == null) {
                        throw new RuntimeException(implId + "(" + implVersion + ") not found in repository");
                    }
                    final String fileName = URI.decode(implementation.eResource().getURI().lastSegment());
                    final EMFFileStore file = implStore.getChild(fileName);
                    Assert.isNotNull(file);
                    final boolean isUserImplementation = file.canBeShared();
                    try {
                        File implementationFile = file.getResource().getLocation().toFile();
                        if (!implementationFile.exists()) {
                            implementationFile = new File(URI.decode(file.getEMFResource().getURI().toFileString()));
                        }
                        final FileInputStream fis = new FileInputStream(implementationFile);
                        final byte[] content = new byte[fis.available()];
                        fis.read(content);
                        fis.close();

                        builder.addConnectorImplementation(new BarResource(NamingUtils.toConnectorImplementationFilename(implId, implVersion, true), content));

                        //Add jar dependencies
                        addProcessDependencies(builder, configuration, resources, libStore, implId, implVersion, implementation, isUserImplementation);
                    } catch (final Exception e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        for (final BarResource barResource : resources) {
            builder.addClasspathResource(barResource);
        }
    }

    /**
     * @param builder
     * @param configuration
     * @param resources
     * @param libStore
     * @param implId
     * @param implVersion
     * @param implementation
     * @param isUserImplementation
     * @throws IOException
     * @throws InterruptedException
     * @throws InvocationTargetException
     */
    protected void addProcessDependencies(final BusinessArchiveBuilder builder, final Configuration configuration, final List<BarResource> resources,
            final DependencyRepositoryStore libStore, final String implId, final String implVersion, final ConnectorImplementation implementation,
            final boolean isUserImplementation) throws IOException, InvocationTargetException, InterruptedException {
        for (final FragmentContainer fc : configuration.getProcessDependencies()) {
            if (fc.getId().equals(FragmentTypes.CONNECTOR)) {
                for (final FragmentContainer connector : fc.getChildren()) {
                    if (connector.getId().equals(NamingUtils.toConnectorImplementationFilename(implId, implVersion, false))) {
                        for (final Fragment fragment : connector.getFragments()) {
                            final String jarName = fragment.getValue();
                            if (jarName.endsWith(".jar") && fragment.isExported()) {
                                if (isUserImplementation
                                        && NamingUtils.toConnectorImplementationJarName(implementation).equals(fragment.getValue())) { //Generate jar from source file
                                    if (!addImplementationJar(builder, implementation)) {
                                        addResource(resources, libStore, jarName);
                                    }
                                } else {
                                    addResource(resources, libStore, jarName);
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @param resources
     * @param libStore
     * @param jarName
     * @throws IOException
     */
    protected void addResource(final List<BarResource> resources, final DependencyRepositoryStore libStore, final String jarName) throws IOException {
        final IRepositoryFileStore jarFileStore = libStore.getChild(jarName);
        if (jarFileStore != null) {
            final File jarFile = jarFileStore.getResource().getLocation().toFile();
            resources.add(new BarResource(jarFile.getName(), Files.toByteArray(jarFile)));
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

}
