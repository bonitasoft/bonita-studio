/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.core;

import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.team.git.ui.wizard.FirstCommitDialog;
import org.bonitasoft.studio.team.git.ui.wizard.FirstPushAbortedDialog;
import org.bonitasoft.studio.team.git.ui.wizard.SelectRepositoryToShare;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.egit.core.op.CommitOperation;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.egit.ui.Activator;
import org.eclipse.egit.ui.internal.commit.CommitHelper;
import org.eclipse.egit.ui.internal.dialogs.BasicConfigurationDialog;
import org.eclipse.egit.ui.internal.push.PushBranchWizard;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

public class ShareGitProject extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        execute(RepositoryManager.getInstance().getCurrentRepository().getProject());
        return null;
    }

    public void share(RepositoryAccessor repositoryAccessor) {
        execute(repositoryAccessor.getCurrentRepository().getProject());
    }

    public void execute(IProject currentProject) {
        Shell activeShell = Display.getDefault().getActiveShell();
        IProject project = selectProjectToShare(activeShell, currentProject);
        if (project != null) {
            connectProject(project);
            createDefaultIgnoreFile(project);
            commitAndPush(activeShell, project);
        }
    }

    private IProject selectProjectToShare(Shell activeShell, IProject currentProject) {
        SelectRepositoryToShare selectRepoToShareControlSupplier = new SelectRepositoryToShare();
        selectRepoToShareControlSupplier.setRepositoryName(currentProject.getName());
        return WizardBuilder.<IProject> newWizard()
                .withTitle(Messages.shareWithGit)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.shareRepositoryTitle)
                        .withDescription(Messages.shareRepositoryDesc)
                        .withControl(selectRepoToShareControlSupplier))
                .onFinish(wizardContainer -> {
                    try {
                        return prepareProject(wizardContainer, selectRepoToShareControlSupplier,
                                currentProject);
                    } catch (InvocationTargetException | InterruptedException e) {
                        BonitaStudioLog.error(e);
                        return Optional.empty();
                    }
                })
                .open(activeShell, Messages.share)
                .orElse(null);
    }

    private Optional<IProject> prepareProject(IWizardContainer wizardContainer,
            SelectRepositoryToShare selectRepoToShareControlSupplier, IProject currentProject)
            throws InvocationTargetException, InterruptedException {
        if (Objects.equals(selectRepoToShareControlSupplier.getRepositoryName(), currentProject.getName())) {
            return Optional.of(currentProject);
        }
        String repositoryName = selectRepoToShareControlSupplier.getRepositoryName();
        wizardContainer.run(true, false, monitor -> {
            BOSWebServerManager.getInstance().stopServer(monitor);
            if (!ResourcesPlugin.getWorkspace().getRoot().getProject(repositoryName).exists()) {
                try {
                    currentProject.copy(Path.fromOSString(repositoryName), true,
                            monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                }
            }
            TeamRepositoryUtil.switchToRepository(repositoryName, false, true, false, monitor);
        });
        return Optional
                .of(currentProject.getWorkspace().getRoot()
                        .getProject(repositoryName));
    }

    protected void commitAndPush(Shell activeShell, IProject project) {
        Repository repository = ResourceUtil.getRepository(project);
        BasicConfigurationDialog.show(repository);//ask for username, email if not set in gitconfig
        try (Git git = new Git(repository)) {
            git.add().addFilepattern(".").call();
            String commitMessage = String.format("Initial commit of '%s'", project.getName());
            FirstCommitDialog commitDialog = new FirstCommitDialog(activeShell, commitMessage);
            commitDialog.create();
            if (commitDialog.open() == FirstCommitDialog.COMMIT_AND_PUSH_ID) {
                commitMessage = commitDialog.getCommitMessage();
            }
            CommitHelper commitHelper = new CommitHelper(repository);
            CommitOperation commitOperation = new CommitOperation(repository,
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
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().resetPerspective();
            PlatformUtil.openIntroIfNoOtherEditorOpen();
        } catch (CoreException | InvocationTargetException | InterruptedException | GitAPIException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(),
                    "Failed to commit and push to remote", e);
        }
    }

    protected void createDefaultIgnoreFile(IProject project) {
        IFile gitIgnoreFile = project.getFile(Constants.DOT_GIT_IGNORE);

        try (InputStream is = org.bonitasoft.studio.team.repository.Repository.getGitignoreTemplateFileURL().openStream()) {
            if (gitIgnoreFile.exists()) {
                gitIgnoreFile.setContents(is, true, true, new NullProgressMonitor());
            } else {
                gitIgnoreFile.create(is, true, new NullProgressMonitor());
            }
        } catch (IOException | CoreException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(),
                    "Failed to create .gitignore", e);
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

    protected void connectProject(IProject project) {
        try {
            final ConnectProviderOperation op = new ConnectProviderOperation(project);
            List<IProject> embeddedProjects = findEmbeddedProjects(project);
            IProgressService progressService = PlatformUI.getWorkbench().getProgressService();

            progressService.run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor)
                        throws InvocationTargetException {
                    File gitDir = new File(project.getLocation().toFile().getAbsolutePath(),
                            Constants.DOT_GIT);
                    try (Repository repository = FileRepositoryBuilder
                            .create(gitDir)){
                        repository.create();
                        if (!gitDir.toString().contains("..")) //$NON-NLS-1$
                            project.refreshLocal(IResource.DEPTH_ONE,
                                    new NullProgressMonitor());
                        RepositoryUtil.INSTANCE.addConfiguredRepository(gitDir);
                        var gitIgnore = project.getFile(Constants.GITIGNORE_FILENAME);
                        if(!gitIgnore.exists()) {
                            gitIgnore.create(new ByteArrayInputStream(new byte[0]), true, new NullProgressMonitor());
                        }
                        op.execute(monitor);
                        for (IProject project : embeddedProjects) {
                            ConnectProviderOperation connectProviderOperation = new ConnectProviderOperation(project,
                                    gitDir);
                            connectProviderOperation.execute(monitor);
                        }
                    } catch (CoreException | IOException ce) {
                        throw new InvocationTargetException(ce);
                    }
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), "Share project failed", e);
        }
    }

    private List<IProject> findEmbeddedProjects(IProject project) {
        IPath parentLocation = project.getLocation();
        return Stream.of(project.getWorkspace().getRoot().getProjects())
                .filter(Objects::nonNull)
                .filter(p -> !project.equals(p))
                .filter(p -> p.getLocation() != null)
                .filter(p -> parentLocation.isPrefixOf(p.getLocation()))
                .collect(Collectors.toList());
    }

    public boolean canExecute(IProject project) {
        return !RepositoryProvider.isShared(project);
    }

}
