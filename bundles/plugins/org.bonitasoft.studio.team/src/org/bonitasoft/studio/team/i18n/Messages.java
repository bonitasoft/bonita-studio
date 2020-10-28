/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Aurelien Pupier
 * @author Baptiste Mesta
 */
public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
    public static String team_checkOurPorject;
    public static String team_checkout;
    public static String team_selectWorkspace;
    public static String team_switchingProject;
    public static String team_openingNewWorkspace;
    public static String connectToRepository_title;
    public static String connectToRepository_desc;
    public static String save_password;
    public static String password;
    public static String user_name;
    public static String url;
    public static String listWorkspaces_title;
    public static String listWorkspaces_desc;
    public static String availableWorkspaces;
    public static String alreadyExists;
    public static String createNewRepo;
    public static String team_cantconnect;
    public static String nameOfTheWorkspace;
    public static String team_shareWorkspaceComment;
    public static String team_firstCommitComment;
    public static String team_History;

    public static String createNewConnection;

    public static String team_conflictDetected_msg;
    public static String team_conflictDetected_title;
    public static String team_conflict_local;
    public static String team_conflict_remote;
    public static String team_conflict_openRemote;
    public static String removeRepository;
    public static String team_listRepositories;
    public static String team_connectToRepo;
    public static String team_updateWorkspace;
    public static String team_synchronizeData;
    public static String team_checkConflicts;
    public static String team_resolveConflicts;
    public static String default_workspace_name;
    public static String manageLocks_title;

    public static String team_restoreOK_msg;
    public static String team_restoreOK_title;
    public static String team_restoreLabel;
    public static String team_revertLabel;
    public static String team_revert_msg;
    public static String team_revert_title;
    public static String openInReadOnly_title;
    public static String openInReadOnly_msg;
    public static String switchRepositorySuccessful_Message;
    public static String switchRepositorySuccessful_Title;
    public static String manageSharingTypeRepositoryWIzardpageTitle;
    public static String manageSharingTypeRepositoryWizardPageDescription;
    public static String mixedModeSynchronization;
    public static String synchronizeAllManually;
    public static String synchronizeAllAutomatically;
    public static String createNewLocalRepo;
    public static String createNewLocalRepoDialogTitle;
    public static String createNewLocalRepoDialogMessage;
    public static String createNewLocalRepoDialogDefaultName;
    public static String createNewLocalRepo_emptyText;
    public static String team_empty;
    public static String lockLabel;
    public static String unlockLabel;
    public static String team_restoreFail_title;
    public static String migrateWorkspace;
    public static String migrationWarning;
    public static String migrateWorkspace_title;
    public static String migrateWorkspace_desc;
    public static String team_restoreFail_msg;
    public static String creatingRestorePoint;
    public static String backupSuccessTitle;
    public static String backupSuccessMsg;
    public static String addCommentLabel;
    public static String chooseARestoreAction;
    public static String createARestorePointAction;
    public static String restoreAction;
    public static String restoreLabel;
    public static String chooseWorkspaceToBackupTitle;
    public static String chooseWorkspaceToBackupDesc;
    public static String restoreActionDesc;
    public static String restoreActionTitle;
    public static String restorePointTitle;
    public static String restorePointDesc;
    public static String restoreSuccessTitle;
    public static String restoreSuccessMsg;
    public static String restoringWorkspace;
    public static String badWorkspaceVersionTitle;
    public static String badWorkspaceVersionMessage;
    public static String reconnectWorkspaceTitle;
    public static String reconnectWorkspaceMsg;
    public static String deleteRestorePoint;
    public static String restoreWarningTitle;
    public static String restoreWarningMsg;
    public static String beforeRestoreTo;
    public static String create;
    public static String deleteRestorePointTitle;
    public static String deleteRestorePointMsg;
    public static String svnConnector;
    public static String showOnlyCompatibleWorkspaces;
    public static String repositoryStatusDialog_title;//"Current repository Status"
    public static String repositoryStatusDialog_repositoryName;//"Repository name"
    public static String repositoryStatusDialog_kind;//Kind
    public static String repositoryStatusDialog_connected;
    public static String repositoryStatusDialog_local;
    public static String repositoryStatusDialog_connectionInfo;
    public static String repositoryStatusDialog_connectionStatus;
    public static String repositoryStatusDialog_online;
    public static String repositoryStatusDialog_offline;
    public static String repositoryStatusDialog_repoURL;
    public static String repositoryStatusDialog_connectionUsername;
    public static String automatic;
    public static String repository;
    public static String synchronization;
    public static String migrationOngoing;
    public static String migratingFiles;
    public static String switchRepositoryDialogTitle;
    public static String backupFailMsg;
    public static String backupFailTitle;
    public static String needTobeMigrated;
    public static String lockedResourceTitle;
    public static String lockedResourceMsg;
    public static String updatingRepository;
    public static String updateErrorTitle;
    public static String updateErrorMessage;
    public static String lockedBy;
    public static String lockEditorTitleTooltip;
    public static String readOnlyMode;
    public static String releaseLockQuestionMessage;
    public static String releaseLockQuestionTitle;
    public static String openInEditModeConfirmationTitle;
    public static String openInEditModeConfirmationMessage;
    public static String warningSVNConnectorKitVersionChange;
    public static String commitFailedTitle;
    public static String commitFailedMessage;
    public static String offline;
    public static String restorePointsWindowTitle;
    public static String invalidRemoteLocationTitle;
    public static String invalidRemoteLocationMsg;
    public static String restartingServer;
    public static String connectingToRemote;
    public static String localCopyAlreadyExistsInWorkspace;
    public static String createNewLocalRepo_invalidCharacter;
    public static String repoAlreadyExist;
    public static String confirmDeleteRepositoryTitle;
    public static String confirmDeleteRepository;
    public static String switchRepository;
    public static String SVNStatusDialog;
    public static String GITStatusDialog;
    public static String sharedWithGit;
    public static String currentBranch;
    public static String remoteURL;
    public static String gitUser;
    public static String commitAhead;
    public static String commitBehind;
    public static String switchIntoNewbranch;
    public static String legacyFormsRemoved;

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
