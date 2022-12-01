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
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.maven.Maven;
import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.model.Dependency;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.business.data.generator.AbstractBDMJarBuilder;
import org.bonitasoft.engine.business.data.generator.client.ClientBDMJarBuilder;
import org.bonitasoft.engine.business.data.generator.client.ResourcesLoader;
import org.bonitasoft.engine.business.data.generator.filter.OnlyDAOImplementationFileFilter;
import org.bonitasoft.engine.business.data.generator.filter.WithoutDAOImplementationFileFilter;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.MultiModuleProject;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;
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
        var project = RepositoryManager.getInstance().getCurrentProject().orElseThrow();
        if (containsBusinessObjects(model)) {
            monitor.beginTask(Messages.generatingJarFromBDMModel, IProgressMonitor.UNKNOWN);
            BonitaStudioLog.debug(Messages.generatingJarFromBDMModel, BusinessObjectPlugin.PLUGIN_ID);
            var data = new HashMap<String, Object>();
            data.put(MODEL, model);
            try {
                data.put(FILE_CONTENT, Files.readString(fileStore.getResource().getLocation().toFile().toPath()));
            } catch (Exception e) {
                throw new InvocationTargetException(e);
            }
            if (project instanceof MultiModuleProject) {
                try {
                    var multiModuleProject = (MultiModuleProject) project;
                    multiModuleProject.getBdmModelProject()
                            .build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
                    var status = mavenInstall(multiModuleProject.getBdmParentProject(), monitor);
                    if(!status.isOK()) {
                        throw new CoreException(status);
                    }
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                }
            } else {
                generateBdmJars(model, data);
            }
            eventBroker().send(BDM_DEPLOYED_TOPIC, data);
            try {
                updateProjectBDMDependency(fileStore.getParametrizedModelMavenDependency());
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
        } else {
            try {
                removeDependency(fileStore.getParametrizedModelMavenDependency());
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
        }
    }

    private IStatus mavenInstall(IProject bdmParent, IProgressMonitor monitor) throws CoreException {
        IMaven maven = MavenPlugin.getMaven();
        var mavenProject = new MavenProjectHelper().getMavenProject(bdmParent);
        if (mavenProject == null) {
            return new Status(IStatus.ERROR, getClass(),
                    "An error occured while installing the bdm artifacts. Cannot resolve the Maven project.");
        }
        var projectRegistry = MavenPlugin.getMavenProjectRegistry();
        List<String> goals = List.of("clean", "install");
        var ctx = maven.createExecutionContext();
        var request = ctx.getExecutionRequest();
        request.setGoals(goals);
        request.setNoSnapshotUpdates(true);
        request.setRecursive(true);
        request.setPom(mavenProject.getFile());
        var projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(bdmParent);
        MavenExecutionResult executionResult = projectRegistry.execute(projectFacade,
                new ICallable<MavenExecutionResult>() {

                    @Override
                    public MavenExecutionResult call(IMavenExecutionContext context, IProgressMonitor monitor)
                            throws CoreException {
                        return maven.lookup(Maven.class).execute(request);
                    }

                }, monitor);

        if (!(executionResult.getBuildSummary(executionResult.getProject()) instanceof BuildSuccess)) {
            return Status.error("An error occured while installing the bdm artifacts.",
                    executionResult.getExceptions().get(0));
        }
        return Status.OK_STATUS;
    }

    private void generateBdmJars(BusinessObjectModel model, Map<String, Object> data) throws InvocationTargetException {
        try {
            // Build jar with Model
            ResourcesLoader bundleResourcesLoader = new ResourcesLoader();
            AbstractBDMJarBuilder builder = new ClientBDMJarBuilder(bundleResourcesLoader);
            final byte[] modelJarContent = builder.build(model, new WithoutDAOImplementationFileFilter());
            data.put(BDM_CLIENT, modelJarContent);

            // Build jar with DAO
            builder = new ClientBDMJarBuilder(bundleResourcesLoader);
            final byte[] daoJarContent = builder.build(model, new OnlyDAOImplementationFileFilter());
            data.put(BDM_DAO, daoJarContent);

            var bdmArtifactDescriptor = fileStore.loadArtifactDescriptor();
            data.put(BDM_ARTIFACT_DESCRIPTOR, bdmArtifactDescriptor);
        } catch (final Exception e) {
            throw new InvocationTargetException(e);
        }
    }

    private void updateProjectBDMDependency(Dependency bdmModelDependency) {
        var removeBDMClientDependencyOperation = new RemoveDependencyOperation(bdmModelDependency);
        var addBDMClientDependencyOperation = new AddDependencyOperation(bdmModelDependency);

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
                var currentRepository = fileStore.getRepositoryAccessor().getCurrentRepository().orElse(null);
                if (currentRepository != null) {
                    var businessObjectModelRepositoryStore = currentRepository
                            .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
                    businessObjectModelRepositoryStore.allBusinessObjectDao(currentRepository.getJavaProject());
                }
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    protected IEventBroker eventBroker() {
        return PlatformUI.getWorkbench().getService(IEventBroker.class);
    }

    protected void removeDependency(Dependency bdmModelDependency) {
        RemoveDependencyOperation operation = new RemoveDependencyOperation(bdmModelDependency);
        new WorkspaceJob("Remove Project BDM dependency") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                operation.disableAnalyze().run(monitor);
                return Status.OK_STATUS;
            }
        }.schedule();
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
