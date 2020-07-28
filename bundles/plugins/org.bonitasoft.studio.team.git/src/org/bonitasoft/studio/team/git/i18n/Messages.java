/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.git.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    public static String shareRepositoryProgressTitle;
    public static String shareRepositoryProgress;
    public static String warningRepoNotPushedMsg;
    public static String showGitStaging;
    public static String openGitPreferences;
    public static String shareWithGit;
    public static String clone;
    public static String cloneRepoDialogTitle;
    public static String cloneRepoDialogMsg;
    public static String repositoryClonedTitle;
    public static String repositoryClonedMsg;
    public static String workspaceLocation;
    public static String workspaceLocationHint;
    public static String repositoryClonedWithMigration;
    public static String noBonitaProjectDescriptorFound;
    public static String noVersionFoundInBonitaProjectDescriptor;
    public static String noNatureFoundInBonitaProjectDescriptor;
    public static String cannotMigrateRepository;
    public static String pull;
    public static String push;
    public static String targetRepoPageTitle;
    public static String targetRepoPageMsg;
    public static String showHistory;
    public static String share;
    public static String shareRepositoryTitle;
    public static String shareRepositoryDesc;
    public static String repository;
    public static String repoAlreadyShared;
    public static String switchTo;
    public static String reset;
    public static String fetch;
    public static String pushToUpstream;
    public static String commit;
    public static String rebase;
    public static String merge;
    public static String gitInit;
    public static String createGitIgnore;
    public static String firstCommit;
    public static String firstPush;
    public static String firstCommitMessage;
    public static String commitAndPush;
    public static String repositoryStatus;
    public static String legacyFormsRemoved;
    public static String clonedBranchContentIsNotCompatible;
    public static String confirmMigratonTitle;
    public static String confirmMigraton;
    public static String cloneOperationCancelled;

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }

}
