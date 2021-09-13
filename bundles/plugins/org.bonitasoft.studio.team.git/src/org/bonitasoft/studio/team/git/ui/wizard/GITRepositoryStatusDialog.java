/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.ui.wizard;

import java.io.IOException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.repository.Repository;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.ui.Activator;
import org.eclipse.egit.ui.internal.GitLabels;
import org.eclipse.egit.ui.internal.UIIcons;
import org.eclipse.egit.ui.internal.selection.SelectionUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jgit.lib.BranchTrackingStatus;
import org.eclipse.jgit.lib.UserConfig;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class GITRepositoryStatusDialog extends Dialog {

    private Repository currentRepository;
    private org.eclipse.jgit.lib.Repository gitRepository;

    public GITRepositoryStatusDialog(Shell parentShell, Repository currentRepository) {
        super(parentShell);
        this.currentRepository = currentRepository;
        gitRepository = getGitRepository(currentRepository);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        getShell().setText(Messages.GITStatusDialog);
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());

        createGitTitle(composite);

        Composite gitDetailsComposite = new Composite(composite, SWT.None);
        gitDetailsComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 0).spacing(10, 5).create());
        gitDetailsComposite.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());

        createUser(gitDetailsComposite);
        createRepositoryURL(gitDetailsComposite);
        createBranchInfoLabel(gitDetailsComposite);

        return composite;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    }

    private void createGitTitle(Composite parent) {
        Label gitIcon = new Label(parent, SWT.None);
        gitIcon.setLayoutData(GridDataFactory.fillDefaults().create());
        gitIcon.setImage(TeamPlugin.getImage("icons/git.png"));

        Label state = new Label(parent, SWT.WRAP);
        state.setLayoutData(GridDataFactory.fillDefaults().create());
        state.setText(String.format("%s:", String.format(Messages.sharedWithGit, currentRepository.getName())));
    }

    @SuppressWarnings("restriction")
    private void createBranchInfoLabel(Composite parent) {
        String brancheInfo = GitLabels.getStyledLabelSafe(gitRepository).toString();
        if (RepositoryUtil.hasChanges(gitRepository)) { // '> ' is added before the branch name, we do not want it
            brancheInfo = brancheInfo.substring(2);
        }
        brancheInfo = brancheInfo.substring(currentRepository.getName().length() + 1, brancheInfo.length()); // we remove the repo name

        Label branchIcon = new Label(parent, SWT.None);
        branchIcon.setLayoutData(GridDataFactory.fillDefaults().create());
        branchIcon.setImage(UIIcons.getImage(Activator.getDefault().getResourceManager(), UIIcons.BRANCH));

        Label branch = new Label(parent, SWT.WRAP);
        branch.setLayoutData(GridDataFactory.fillDefaults().create());
        branch.setText(String.format("%s:  %s", Messages.currentBranch, brancheInfo));

        try {
            RepositoryUtil repositoryUtil = RepositoryUtil.getInstance();
            BranchTrackingStatus trackingStatus = BranchTrackingStatus.of(gitRepository,
                    repositoryUtil.getShortBranch(gitRepository));
            if (trackingStatus != null
                    && (trackingStatus.getAheadCount() != 0 || trackingStatus
                            .getBehindCount() != 0)) {
                createBranchStatus(parent, trackingStatus);
            }
        } catch (IOException e) {
            BonitaStudioLog.error("An error occured while retrieving branch status", e);
        }

    }

    private void createBranchStatus(Composite parent, BranchTrackingStatus trackingStatus) {
        Composite branchStatusComposite = new Composite(parent, SWT.None);
        branchStatusComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 0).spacing(5, 5).create());
        branchStatusComposite.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());

        if (trackingStatus.getAheadCount() > 0) {
            Label aheadIcon = new Label(branchStatusComposite, SWT.None);
            aheadIcon.setLayoutData(GridDataFactory.fillDefaults().create());
            aheadIcon.setImage(TeamPlugin.getImage("icons/aheadIcon.png"));

            Label aheadLabel = new Label(branchStatusComposite, SWT.WRAP);
            aheadLabel.setLayoutData(GridDataFactory.fillDefaults().create());
            aheadLabel.setText(String.format(Messages.commitAhead, trackingStatus.getAheadCount()));
        }

        if (trackingStatus.getBehindCount() > 0) {
            Label behind = new Label(branchStatusComposite, SWT.None);
            behind.setLayoutData(GridDataFactory.fillDefaults().create());
            behind.setImage(TeamPlugin.getImage("icons/behindIcon.png"));

            Label behindLabel = new Label(branchStatusComposite, SWT.WRAP);
            behindLabel.setLayoutData(GridDataFactory.fillDefaults().create());
            behindLabel.setText(String.format(Messages.commitBehind, trackingStatus.getBehindCount()));
        }
    }

    private void createRepositoryURL(Composite parent) {
        gitRepository.getRemoteNames().stream().findFirst().ifPresent(remote -> {
            Label urlIcon = new Label(parent, SWT.None);
            urlIcon.setLayoutData(GridDataFactory.fillDefaults().create());
            urlIcon.setImage(TeamPlugin.getImage("icons/url16.png"));

            Label url = new Label(parent, SWT.WRAP);
            url.setLayoutData(GridDataFactory.fillDefaults().create());
            url.setText(String.format("%s:  %s", Messages.remoteURL,
                    gitRepository.getConfig().getString("remote", remote, "url")));
        });
    }

    private void createUser(Composite parent) {
        UserConfig userConfig = gitRepository.getConfig().get(UserConfig.KEY);

        Label userIcon = new Label(parent, SWT.None);
        userIcon.setLayoutData(GridDataFactory.fillDefaults().create());
        userIcon.setImage(TeamPlugin.getImage("icons/user.gif"));

        Label gitUser = new Label(parent, SWT.WRAP);
        gitUser.setLayoutData(GridDataFactory.fillDefaults().create());
        gitUser.setText(
                String.format("%s:  %s [%s]", Messages.gitUser, userConfig.getAuthorName(), userConfig.getAuthorEmail()));
    }

    private org.eclipse.jgit.lib.Repository getGitRepository(AbstractRepository repository) {
        IStructuredSelection selection = new StructuredSelection(repository.getProject());
        return SelectionUtils.getRepository(selection);
    }

}
