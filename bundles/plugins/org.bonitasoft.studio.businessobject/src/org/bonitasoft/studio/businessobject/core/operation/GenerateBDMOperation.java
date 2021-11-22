/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.core.operation;

import static com.google.common.io.ByteStreams.toByteArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.maven.artifact.Artifact;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.business.data.generator.AbstractBDMJarBuilder;
import org.bonitasoft.engine.business.data.generator.client.ClientBDMJarBuilder;
import org.bonitasoft.engine.business.data.generator.client.ResourcesLoader;
import org.bonitasoft.engine.business.data.generator.filter.OnlyDAOImplementationFileFilter;
import org.bonitasoft.engine.business.data.generator.filter.WithoutDAOImplementationFileFilter;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;

public class GenerateBDMOperation implements IRunnableWithProgress {

    public static final String BDM_DEPLOYED_TOPIC = "bdm/deployed";
    public static final String BDM_CLIENT = "bdm-client";
    public static final String BDM_DAO = "bdm-dao";
    public static final String MODEL = "model";
    public static final String FILE_CONTENT = "fileContent";
    public static final String BDM_ARTIFACT_DESCRIPTOR = "artifactDescriptor";

    private final BusinessObjectModelFileStore fileStore;

    private static Object generationLock = new Object();

    public GenerateBDMOperation(final BusinessObjectModelFileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        synchronized (generationLock) {
            doGenerateBDM(monitor);
        }
    }

    protected void doGenerateBDM(IProgressMonitor monitor) throws InvocationTargetException {
        if (monitor == null) {
            monitor = AbstractRepository.NULL_PROGRESS_MONITOR;
        }
        BusinessObjectModel model;
        try {
            model = fileStore.getContent();
        } catch (ReadFileStoreException e1) {
            throw new InvocationTargetException(e1);
        }
        if (containsBusinessObjects(model)) {
            monitor.beginTask(Messages.generatingJarFromBDMModel, IProgressMonitor.UNKNOWN);
            BonitaStudioLog.debug(Messages.generatingJarFromBDMModel, BusinessObjectPlugin.PLUGIN_ID);
            try {
                final Map<String, byte[]> resources = new HashMap<>();
                // Build jar with Model
                ResourcesLoader bundleResourcesLoader = new ResourcesLoader();
                AbstractBDMJarBuilder builder = new ClientBDMJarBuilder(bundleResourcesLoader);
                final byte[] modelJarContent = builder.build(model, new WithoutDAOImplementationFileFilter());
                resources.put(BDM_CLIENT, modelJarContent);

                // Build jar with DAO
                builder = new ClientBDMJarBuilder(bundleResourcesLoader);
                final byte[] daoJarContent = builder.build(model, new OnlyDAOImplementationFileFilter());
                resources.put(BDM_DAO, daoJarContent);

                final Map<String, Object> data = new HashMap<>();
                data.put(MODEL, model);
                data.put(BDM_CLIENT, resources.get(BDM_CLIENT));
                data.put(BDM_DAO, resources.get(BDM_DAO));
                var bdmArtifactDescriptor = fileStore.loadArtifactDescriptor();
                data.put(BDM_ARTIFACT_DESCRIPTOR, bdmArtifactDescriptor);
                data.put(FILE_CONTENT, new String(((BusinessObjectModelRepositoryStore) fileStore.getParentStore())
                        .getConverter().marshall(model)));
                
                eventBroker().send(BDM_DEPLOYED_TOPIC, data);
                
                updateProjectBDMDependency(bdmArtifactDescriptor);
            } catch (final Exception e) {
                throw new InvocationTargetException(e);
            }
        } else {
            removeDependency();
        }
    }

    private void updateProjectBDMDependency(BDMArtifactDescriptor bdmArtifactDescriptor) {
        var removeBDMClientDependencyOperation = new RemoveDependencyOperation(
                bdmArtifactDescriptor.getGroupId(),
                GenerateBDMOperation.BDM_CLIENT,
                bdmArtifactDescriptor.getVersion(),
                Artifact.SCOPE_PROVIDED);
        var addBDMClientDependencyOperation = new AddDependencyOperation(
                bdmArtifactDescriptor.getGroupId(),
                GenerateBDMOperation.BDM_CLIENT,
                bdmArtifactDescriptor.getVersion(),
                Artifact.SCOPE_PROVIDED);

        try {
            // Force a proper java project update by remove/adding the dependency in the project 
            removeBDMClientDependencyOperation.disableAnalyze().run(new NullProgressMonitor());
            addBDMClientDependencyOperation.disableAnalyze().run(new NullProgressMonitor());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }

        new WorkspaceJob("Update Project BDM dependency") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                // Update bdm model and dao list cache
                var currentRepository = fileStore.getRepositoryAccessor().getCurrentRepository();
                BusinessObjectModelRepositoryStore businessObjectModelRepositoryStore = currentRepository
                        .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
                businessObjectModelRepositoryStore.allBusinessObjectDao(currentRepository.getJavaProject());
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    protected IEventBroker eventBroker() {
        return PlatformUI.getWorkbench().getService(IEventBroker.class);
    }

    protected void removeDependency() {
        try {
            BDMArtifactDescriptor loadArtifactDescriptor = fileStore.loadArtifactDescriptor();
            RemoveDependencyOperation operation = new RemoveDependencyOperation(loadArtifactDescriptor.getGroupId(),
                    BDM_CLIENT, loadArtifactDescriptor.getVersion(), Artifact.SCOPE_PROVIDED);
            new WorkspaceJob("Remove Project BDM dependency") {

                @Override
                public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                    operation.disableAnalyze().run(monitor);
                    return Status.OK_STATUS;
                }
            }.schedule();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    private boolean containsBusinessObjects(final BusinessObjectModel bom) {
        return bom != null && !bom.getBusinessObjects().isEmpty();
    }

    protected Map<String, byte[]> retrieveContent(final byte[] zipContent) throws IOException {
        Assert.isNotNull(zipContent);
        final Map<String, byte[]> result = new HashMap<>();
        try (ByteArrayInputStream is = new ByteArrayInputStream(zipContent);
                ZipInputStream zis = new ZipInputStream(is);) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String entryName = entry.getName();
                if (entryName.contains(MODEL) && entryName.endsWith(".jar")) {
                    result.put(BDM_CLIENT, toByteArray(zis));
                }
                if (entryName.contains("dao") && entryName.endsWith(".jar")) {
                    result.put(BDM_DAO, toByteArray(zis));
                }
            }
        }
        return result;
    }

}
