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

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

import org.apache.maven.Maven;
import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.event.BdmEvents;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
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
            monitor = new NullProgressMonitor();
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
            data.put(BdmEvents.MODEL_PROPERTY, model);
            try {
                data.put(BdmEvents.FILE_CONTENT_PROPERTY, Files.readString(fileStore.getResource().getLocation().toFile().toPath()));
            } catch (Exception e) {
                throw new InvocationTargetException(e);
            }
            try {
                project.getBdmModelProject()
                        .build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
                var status = mavenInstall(project.getBdmModelProject(), monitor);
                if (!status.isOK()) {
                    throw new CoreException(status);
                }
                data.put(BdmEvents.DEPENDENCY_PROPERTY, fileStore.getModelMavenDependency());
            } catch (CoreException e) {
                throw new InvocationTargetException(e);
            }
            eventBroker().send(BdmEvents.BDM_DEPLOYED_TOPIC, data);
        } 
    }

    private IStatus mavenInstall(IProject bdmModelProject, IProgressMonitor monitor) throws CoreException {
        IMaven maven = MavenPlugin.getMaven();
        var mavenProject = MavenProjectHelper.getMavenProject(bdmModelProject);
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
        request.setPom(mavenProject.getFile());
        var projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(bdmModelProject);
        var executionResult = projectRegistry.execute(projectFacade,
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

    protected IEventBroker eventBroker() {
        return PlatformUI.getWorkbench().getService(IEventBroker.class);
    }

    private boolean containsBusinessObjects(final BusinessObjectModel bom) {
        return bom != null && !bom.getBusinessObjects().isEmpty();
    }

}
