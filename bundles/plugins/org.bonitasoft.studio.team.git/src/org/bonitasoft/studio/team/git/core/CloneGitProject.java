/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.core;

import java.util.HashMap;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReportWriter;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.team.git.ui.wizard.CustomGitCloneWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class CloneGitProject extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        execute();
        return null;
    }

    public void execute() {
        Shell activeShell = Display.getDefault().getActiveShell();
        var wizard = new CustomGitCloneWizard();
        WizardDialog dlg = new WizardDialog(activeShell, wizard) {

            @Override
            protected Point getInitialSize() {
                return new Point(800, 800);
            }
        };
        dlg.setHelpAvailable(true);

        if (dlg.open() == Window.OK) { // To prevent npe if the authentication failed
            var currentRepository = RepositoryManager.getInstance().getCurrentRepository().orElseThrow();
            if (currentRepository.isShared(GitProvider.ID)) {
                IProject project = currentRepository.getProject();
                PlatformUtil.closeIntro();
                PlatformUtil.openDashboardIfNoOtherEditorOpen(true);

                var reportFile = project.getFile(MigrationReportWriter.DEFAULT_REPORT_FILE_NAME);
                if (wizard.hasBeenMigrated() && reportFile.exists()) {
                    try {
                        IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(),
                                reportFile);
                    } catch (PartInitException e) {
                        BonitaStudioLog.error(e);
                    }
                }
                ProjectMetadata metadata;
                try {
                    metadata = ProjectMetadata.read(project, new NullProgressMonitor());
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                    return;
                }
                if (new MessageDialog(activeShell, Messages.repositoryClonedTitle, null,
                        !wizard.hasBeenMigrated() ? String.format(Messages.repositoryClonedMsg, metadata.getName())
                                : migrationAfterCloneMessage(wizard, metadata.getName()),
                        MessageDialog.INFORMATION, 0, org.bonitasoft.studio.importer.i18n.Messages.deploy,
                        IDialogConstants.CLOSE_LABEL).open() == 0) {
                    executeCommand("org.bonitasoft.studio.application.command.deployArtifacts");
                }

            }
        }
    }

    private void executeCommand(String command) {
        ECommandService eCommandService = PlatformUI.getWorkbench().getService(ECommandService.class);
        EHandlerService eHandlerService = PlatformUI.getWorkbench().getService(EHandlerService.class);
        ParameterizedCommand parameterizedCommand = eCommandService.createCommand(command, new HashMap<>());
        eHandlerService.executeHandler(parameterizedCommand);
    }

    private String migrationAfterCloneMessage(CustomGitCloneWizard wizard, String projectName) {
        String migrationMsg = String.format(Messages.repositoryClonedWithMigration, projectName,
                wizard.getOldVersion(),
                ProductVersion.CURRENT_VERSION,
                ProductVersion.CURRENT_VERSION);
        return ProductVersion.isBefore780Version(wizard.getOldVersion())
                ? migrationMsg + System.lineSeparator() + System.lineSeparator() + Messages.legacyFormsRemoved
                : migrationMsg;
    }

}
