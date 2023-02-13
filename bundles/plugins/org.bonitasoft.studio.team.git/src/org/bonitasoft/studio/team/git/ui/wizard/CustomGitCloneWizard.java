/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.team.git.core.ImportClonedRepository;
import org.bonitasoft.studio.team.git.core.RetrieveHEADRefOperation;
import org.bonitasoft.studio.team.git.core.ValidateClonedRepository;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.credentials.UserPasswordCredentials;
import org.eclipse.egit.core.internal.credentials.EGitCredentialsProvider;
import org.eclipse.egit.core.op.CloneOperation;
import org.eclipse.egit.core.op.CloneOperation.PostCloneTask;
import org.eclipse.egit.core.op.ConfigureFetchAfterCloneTask;
import org.eclipse.egit.core.op.ConfigureGerritAfterCloneTask;
import org.eclipse.egit.core.op.ConfigurePushAfterCloneTask;
import org.eclipse.egit.core.op.SetRepositoryConfigPropertyTask;
import org.eclipse.egit.core.settings.GitSettings;
import org.eclipse.egit.ui.Activator;
import org.eclipse.egit.ui.internal.KnownHosts;
import org.eclipse.egit.ui.internal.SecureStoreUtils;
import org.eclipse.egit.ui.internal.UIText;
import org.eclipse.egit.ui.internal.clone.GitCloneWizard;
import org.eclipse.egit.ui.internal.components.RepositorySelection;
import org.eclipse.egit.ui.internal.provisional.wizards.GitRepositoryInfo;
import org.eclipse.egit.ui.internal.provisional.wizards.GitRepositoryInfo.PushInfo;
import org.eclipse.egit.ui.internal.provisional.wizards.GitRepositoryInfo.RepositoryConfigProperty;
import org.eclipse.egit.ui.internal.provisional.wizards.IRepositorySearchResult;
import org.eclipse.egit.ui.internal.provisional.wizards.NoRepositoryInfoException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;

public class CustomGitCloneWizard extends GitCloneWizard {

    private ValidateClonedRepository validateRepoTask = new ValidateClonedRepository();
    private CustomSourceBranchPage sourceBranchPage;
    private PostCloneTask importClonedRepository = new ImportClonedRepository();
    private CloneOperation cloneOperation;

    public CustomGitCloneWizard() {
        super();
        setShowProjectImport(false);
        setCallerRunsCloneOperation(true);
        sourceBranchPage = new CustomSourceBranchPage() {

            @Override
            public void setVisible(boolean visible) {
                RepositorySelection selection = getRepositorySelection();
                if (selection != null && visible) {
                    setSelection(selection);
                    setCredentials(getCredentials());
                }
                super.setVisible(visible);
            }
        };
    }

    @Override
    protected void addPreClonePages() {
        addPage(sourceBranchPage);
    }

    @Override
    public void addPage(IWizardPage page) {
        if (!page.equals(cloneDestination) && !page.equals(validSource)) {
            if (page instanceof IRepositorySearchResult) {
                currentSearchResult = (IRepositorySearchResult) page;
            }
            super.addPage(page);
        }
    }

    @Override
    public boolean canFinish() {
        // Default implementation is to check if all pages are complete.
        for (int i = 0; i < getPages().length; i++) {
            if (!getPages()[i].isPageComplete()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        if (page instanceof IRepositorySearchResult) {
            currentSearchResult = (IRepositorySearchResult) page;
            return sourceBranchPage;
        }
        List<IWizardPage> pages = Stream.of(getPages()).collect(Collectors.toList());
        int index = pages.indexOf(page);
        if (index == pages.size() - 1 || index == -1) {
            // last page or page not found
            return null;
        }
        IWizardPage nextPage = pages.get(index + 1);
        super.getNextPage(nextPage);
        return nextPage;
    }

    @Override
    public boolean performFinish() {
        var finish = super.performFinish();
        if (finish) {
            try {
                GitRepositoryInfo gitRepositoryInfo = currentSearchResult.getGitRepositoryInfo();
                getContainer().run(true, true, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor)
                            throws InvocationTargetException, InterruptedException {
                        executeCloneOperation(cloneOperation, gitRepositoryInfo, monitor);
                    }
                });
            } catch (InvocationTargetException | NoRepositoryInfoException e) {
                Activator.handleError(UIText.GitCloneWizard_failed, e.getCause(), true);
                return false;
            } catch (InterruptedException e) {
                // nothing to do
            } finally {
                if (cloneOperation != null && cloneOperation.getGitDir().exists()) {
                    try {
                        FileUtil.deleteDir(cloneOperation.getGitDir().getParentFile().toPath());
                    } catch (IOException e) {
                        BonitaStudioLog.error("Failed to delete cloned repository tmp folder.", e);
                    }
                }
            }
            return true;
        }
        return finish;
    }

    private IStatus executeCloneOperation(final CloneOperation op,
            final GitRepositoryInfo repositoryInfo,
            final IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {

        final RepositoryUtil util = RepositoryUtil.INSTANCE;

        op.run(monitor);
        util.addConfiguredRepository(op.getGitDir());
        try {
            if (repositoryInfo.shouldSaveCredentialsInSecureStore())
                SecureStoreUtils.storeCredentials(repositoryInfo.getCredentials(),
                        new URIish(repositoryInfo.getCloneUri()));
        } catch (Exception e) {
            Activator.error(e.getMessage(), e);
        }
        return Status.OK_STATUS;
    }

    @Override
    protected boolean performClone(GitRepositoryInfo gitRepositoryInfo) throws URISyntaxException {
        cloneOperation = prepareCloneOperation(gitRepositoryInfo);
        return cloneOperation != null;
    }

    protected CloneOperation prepareCloneOperation(GitRepositoryInfo gitRepositoryInfo) throws URISyntaxException {
        URIish uri = new URIish(gitRepositoryInfo.getCloneUri());
        UserPasswordCredentials credentials = gitRepositoryInfo.getCredentials();
        setWindowTitle(NLS.bind(UIText.GitCloneWizard_jobName, uri.toString()));
        final boolean allSelected;
        final Collection<Ref> selectedBranches;
        if (sourceBranchPage.isSourceRepoEmpty()) {
            // fetch all branches of empty repo
            allSelected = true;
            selectedBranches = Collections.emptyList();
        } else {
            allSelected = sourceBranchPage.isAllSelected();
            selectedBranches = sourceBranchPage.getSelectedBranches();
        }
        File workdir = null;
        try {
            var name = uri.getHumanishName();
            var workdirPath = Paths.get(System.getProperty("java.io.tmpdir")).resolve(name);
            if (Files.isDirectory(workdirPath)) {
                FileUtil.deleteDir(workdirPath);
            }
            Files.createDirectory(workdirPath);
            workdir = workdirPath.toFile();
        } catch (IOException e1) {
            BonitaStudioLog.error(e1);
            return null;
        }
        Ref ref;
        try {
            final Ref headRef = retrieveHEAD();
            ref = headRef;
            if (!selectedBranches.isEmpty()) {
                ref = selectedBranches.stream()
                        .filter(r -> Objects.equals(r.getName(), headRef.getName()))
                        .findFirst()
                        .orElse(selectedBranches.stream().findFirst().orElse(headRef));
            }
        } catch (InvocationTargetException | NoRepositoryInfoException | InterruptedException e) {
            new ExceptionDialogHandler().openErrorDialog(getShell(), "Cannot retrieve HEAD reference", e);
            return null;
        }
        final String remoteName = "origin";
        boolean created = workdir.exists();
        if (!created)
            created = workdir.mkdirs();

        if (!created || !workdir.isDirectory()) {
            final String errorMessage = NLS.bind(
                    UIText.GitCloneWizard_errorCannotCreate, workdir.getPath());
            ErrorDialog.openError(getShell(), getWindowTitle(),
                    UIText.GitCloneWizard_failed, new Status(IStatus.ERROR,
                            Activator.PLUGIN_ID, 0, errorMessage, null));
            // let's give user a chance to fix this minor problem
            return null;
        }

        int timeout = GitSettings.getRemoteConnectionTimeout();
        final CloneOperation op = new CloneOperation(uri, allSelected,
                allSelected ? null : selectedBranches, workdir, ref != null ? ref.getName() : null,
                remoteName, timeout);
        CredentialsProvider credentialsProvider = null;
        if (credentials != null) {
            credentialsProvider = new EGitCredentialsProvider(
                    credentials.getUser(), credentials.getPassword());
        } else {
            credentialsProvider = new EGitCredentialsProvider();
        }
        op.setCredentialsProvider(credentialsProvider);
        op.setCloneSubmodules(false);

        rememberHttpHost(op, uri);
        configureFetchSpec(op, gitRepositoryInfo, remoteName);
        configurePush(op, gitRepositoryInfo, remoteName);
        configureRepositoryConfig(op, gitRepositoryInfo);
        configureGerrit(op, gitRepositoryInfo, credentialsProvider, remoteName,
                timeout);

        op.addPostCloneTask(validateRepoTask);
        op.addPostCloneTask(importClonedRepository);

        alreadyClonedInto = workdir.getPath();
        return op;
    }

    private void rememberHttpHost(CloneOperation op, URIish uri) {
        String scheme = uri.getScheme();
        if (scheme != null && scheme.toLowerCase().startsWith("http")) { //$NON-NLS-1$
            String host = uri.getHost();
            if (host != null) {
                op.addPostCloneTask((repo, monitor) -> {
                    PlatformUI.getWorkbench().getDisplay()
                            .asyncExec(() -> KnownHosts.addKnownHost(host));
                });
            }
        }
    }

    private void configureFetchSpec(CloneOperation op,
            GitRepositoryInfo gitRepositoryInfo, String remoteName) {
        for (String fetchRefSpec : gitRepositoryInfo.getFetchRefSpecs())
            op.addPostCloneTask(new ConfigureFetchAfterCloneTask(remoteName, fetchRefSpec));
    }

    private void configurePush(CloneOperation op,
            GitRepositoryInfo gitRepositoryInfo, String remoteName) {
        for (PushInfo pushInfo : gitRepositoryInfo.getPushInfos())
            try {
                URIish uri = pushInfo.getPushUri() != null ? new URIish(
                        pushInfo.getPushUri()) : null;
                ConfigurePushAfterCloneTask task = new ConfigurePushAfterCloneTask(
                        remoteName, pushInfo.getPushRefSpec(), uri);
                op.addPostCloneTask(task);
            } catch (URISyntaxException e) {
                Activator.handleError(UIText.GitCloneWizard_failed, e, true);
            }
    }

    private void configureRepositoryConfig(CloneOperation op, GitRepositoryInfo gitRepositoryInfo) {
        for (RepositoryConfigProperty p : gitRepositoryInfo.getRepositoryConfigProperties()) {
            SetRepositoryConfigPropertyTask task = new SetRepositoryConfigPropertyTask(
                    p.getSection(), p.getSubsection(), p.getName(),
                    p.getValue());
            op.addPostCloneTask(task);
        }
    }

    private void configureGerrit(CloneOperation op,
            GitRepositoryInfo gitRepositoryInfo,
            CredentialsProvider credentialsProvider, String remoteName,
            int timeout) {
        ConfigureGerritAfterCloneTask task = new ConfigureGerritAfterCloneTask(
                gitRepositoryInfo.getCloneUri(), remoteName,
                credentialsProvider, timeout);
        op.addPostCloneTask(task);
    }

    protected Ref retrieveHEAD() throws NoRepositoryInfoException, InvocationTargetException, InterruptedException {
        RetrieveHEADRefOperation retrieveHEADRefOperation = new RetrieveHEADRefOperation(
                getRepositorySelection().getURI(),
                currentSearchResult.getGitRepositoryInfo().getCredentials());
        getContainer().run(true, true, retrieveHEADRefOperation);
        return retrieveHEADRefOperation.getHead();
    }

    public boolean hasBeenMigrated() {
        return validateRepoTask.migrationRequired();
    }

    public String getOldVersion() {
        return validateRepoTask.getVersion();
    }

}
