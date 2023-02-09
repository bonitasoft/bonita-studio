/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.team.git.ui.wizard.FirstCommitDialog;
import org.bonitasoft.studio.team.git.ui.wizard.FirstPushAbortedDialog;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.egit.core.op.CommitOperation;
import org.eclipse.egit.ui.Activator;
import org.eclipse.egit.ui.internal.commit.CommitHelper;
import org.eclipse.egit.ui.internal.dialogs.BasicConfigurationDialog;
import org.eclipse.egit.ui.internal.push.PushBranchWizard;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class ShareGitProject extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        execute(RepositoryManager.getInstance().getCurrentProject().orElseThrow());
        return null;
    }

    @Override
    public boolean isEnabled() {
        return RepositoryManager.getInstance().getCurrentRepository()
                .stream().anyMatch(r -> !r.isShared());
    }

    public void execute(BonitaProject project) {
        Shell activeShell = Display.getDefault().getActiveShell();
        if (project != null) {
            try {
                connectProject(project);
                project.createDefaultIgnoreFile();
                commitAndPush(activeShell, project);
            } catch (CoreException e) {
                new ExceptionDialogHandler().openErrorDialog(activeShell, e);
                return;
            }
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
            PlatformUtil.openIntroIfNoOtherEditorOpen();
        }
    }

    protected void commitAndPush(Shell activeShell, BonitaProject project) {
        Repository repository = project.getAdapter(Repository.class);
        BasicConfigurationDialog.show(repository);//ask for username, email if not set in gitconfig
        try (Git git = new Git(repository)) {
            git.add().addFilepattern(".").call();
            String commitMessage = String.format("Initial commit of '%s'", project.getId());
            FirstCommitDialog commitDialog = new FirstCommitDialog(activeShell, commitMessage);
            commitDialog.create();
            if (commitDialog.open() == FirstCommitDialog.COMMIT_AND_PUSH_ID) {
                commitMessage = commitDialog.getCommitMessage();
            }
            var commitHelper = new CommitHelper(repository);
            var commitOperation = new CommitOperation(repository,
                    commitHelper.getAuthor(),
                    commitHelper.getCommitter(),
                    commitMessage);
            commitOperation.setCommitAll(true);
            IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            progressService.run(true, false, monitor -> {
                try {
                    commitOperation.execute(monitor);
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            });
            Ref head = getBranchRef(repository);
            if (head != null) {
                PushBranchWizard pushBranchWizard = new PushBranchWizard(
                        repository, head);

                WizardDialog dlg = new WizardDialog(activeShell,
                        pushBranchWizard);
                dlg.create();
                CustomGitWizardHelper.removeAdvancedPushLink(pushBranchWizard);
                if (dlg.open() == IDialogConstants.CANCEL_ID) {
                    new FirstPushAbortedDialog(activeShell).open();
                }
            }
        } catch (CoreException | InvocationTargetException | InterruptedException | GitAPIException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(),
                    "Failed to commit and push to remote", e);
        }
    }

    private Ref getBranchRef(Repository repository) {
        try {
            String fullBranch = repository.getFullBranch();
            if (fullBranch != null && fullBranch.startsWith(Constants.R_HEADS))
                return repository.exactRef(fullBranch);
        } catch (IOException e) {
            Activator.handleError(e.getLocalizedMessage(), e, false);
        }
        return null;
    }

    protected void connectProject(BonitaProject project) throws CoreException {
        try {
            IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            progressService.run(true, false, project.newConnectProviderOperation());
        } catch (InvocationTargetException | InterruptedException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), "Share project failed",
                    e);
        }
    }

    public boolean canExecute(IProject project) {
        return project != null && !RepositoryProvider.isShared(project);
    }

}
