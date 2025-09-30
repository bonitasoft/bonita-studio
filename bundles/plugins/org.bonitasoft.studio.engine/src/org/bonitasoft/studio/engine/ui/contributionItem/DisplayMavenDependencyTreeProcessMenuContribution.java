/**
 * Copyright (C) 2025 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.ui.contributionItem;

import java.io.File;

import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.process.pomgen.ProcessPomGenerator;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.contributionItem.ListProcessContributionItem;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.ConnectorImplementationRegistryHelper;
import org.bonitasoft.studio.engine.ui.editor.EditorCloseWaiter;
import org.bonitasoft.studio.engine.ui.editor.MavenDependencyTreeEditor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.FileEditorInput;

public class DisplayMavenDependencyTreeProcessMenuContribution extends ListProcessContributionItem {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.diagram.custom.contributionItem.ListProcessContributionItem#createSelectionListener(org.bonitasoft.bpm.model.process.
     * AbstractProcess)
     */
    @Override
    protected Listener createSelectionListener(AbstractProcess process) {
        return e -> {
            try {
                var project = RepositoryManager.getInstance().getCurrentProject().orElseThrow();
                if (project != null && project.exists()) {
                    displayMavenDependencyTree(project.getAppProject(), process,
                            new NullProgressMonitor());
                }
            } catch (Exception ex) {
                BonitaStudioLog.error(ex);
            }
        };
    }

    private void displayMavenDependencyTree(IProject project, AbstractProcess process, IProgressMonitor monitor)
            throws Exception {

        IMavenProjectFacade projectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        if (projectFacade == null) {
            throw new CoreException(Status.error("Cannot find Maven project for " + project));
        }

        var gen = ProcessPomGenerator.create(projectFacade.getMavenProject(monitor),
                ConnectorImplementationRegistryHelper.getConnectorImplementationRegistry());

        openEditorAndWaitForClose(process, gen);

    }

    private void openEditorAndWaitForClose(AbstractProcess process,
            ProcessPomGenerator gen) {
        var diagramRepositoryStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);

        new Thread(() -> {
            try {
                gen.withGeneratedPom(diagramRepositoryStore.findProcess(process.getName(), process.getVersion()),
                        pomAccess -> {
                            Model modelPom = pomAccess.readPom();
                            MavenProject myPom = new MavenProject(modelPom);
                            myPom.setFile(modelPom.getPomFile());
                            FileEditorInput fileEditorInput = new FileEditorInput(getPomFile(myPom.getFile()));

                            // Create an instance of EditorCloseWaiter, enabling or disabling waiting as needed.
                            EditorCloseWaiter editorWaiter = new EditorCloseWaiter();
                            editorWaiter.openEditorAndWait(fileEditorInput, MavenDependencyTreeEditor.EDITOR_ID);

                            return null;
                        });
            } catch (Exception e) {
                BonitaStudioLog.error(e);
            }
        }).start();

    }

    private IFile getPomFile(File pomJavaFile) {
        return ResourcesPlugin.getWorkspace()
                .getRoot()
                .getFileForLocation(Path.fromOSString(pomJavaFile.getAbsolutePath()));
    }

}
