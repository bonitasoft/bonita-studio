/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.team.git.validation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.MessageFormat;

import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.team.repository.Repository;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.core.IteratorService;
import org.eclipse.egit.core.project.RepositoryMapping;
import org.eclipse.jgit.ignore.IgnoreNode;
import org.eclipse.jgit.ignore.IgnoreNode.MatchResult;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.WorkingTreeIterator;
import org.eclipse.team.core.RepositoryProvider;

/**
 * A runnable which validates the repositories team aspects
 * 
 * @author Vincent Hemery
 */
public class RepositoryValidator implements IRunnableWithStatus {

    /** repo to validate */
    private Repository repository;

    /** the internal status */
    private IStatus status = Status.CANCEL_STATUS;

    /**
     * Default Constructor.
     * 
     * @param repository repository to validate
     */
    public RepositoryValidator(Repository repository) {
        this.repository = repository;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        // reinitialize status
        status = Status.OK_STATUS;
        if (repository.isShared(GitProvider.ID)) {
            try {
                URL gitIgnoreTemplateUrl = Repository.getGitignoreTemplateFileURL();
                GitProvider gitProvider = (GitProvider) RepositoryProvider.getProvider(repository.getProject());
                IgnoreNode ignoreByTemplate = new IgnoreNode();
                try (InputStream templateStream = gitIgnoreTemplateUrl.openStream()) {
                    ignoreByTemplate.parse(templateStream);
                }
                RepositoryMapping mapping = RepositoryMapping.getMapping(repository.getProject());
                org.eclipse.jgit.lib.Repository repo = mapping.getRepository();
                var it = IteratorService.createInitialIterator(repo);
                it.setWalkIgnoredDirectories(true);
                try (TreeWalk walk = new TreeWalk(repo)) {
                    // we do not use setRecursive(true) to still check subtree nodes
                    walk.addTree(it);
                    while (walk.next()) {
                        String pathStr = walk.getPathString();
                        Path path = new Path(pathStr);
                        boolean isDir = walk.isSubtree();
                        MatchResult shouldBeIgnored = ignoreByTemplate.isIgnored(pathStr, isDir);
                        if (MatchResult.IGNORED.equals(shouldBeIgnored)) {
                            boolean actuallyIgnored;
                            if (!it.getEntryPathString().equals(pathStr)) {
                                /*
                                 * it is not always on the same entry as the walker...
                                 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=580692
                                 * Hence, we can not use it directly for some directories.
                                 */
                                WorkingTreeIterator workingTreeIterator = walk.getTree(0, WorkingTreeIterator.class);
                                actuallyIgnored = workingTreeIterator.isEntryIgnored();
                            } else {
                                actuallyIgnored = it.isEntryIgnored();
                            }
                            if (!actuallyIgnored) {
                                //log error
                                String format = isDir ? Messages.gitValidationDirectoryNotIgnored
                                        : Messages.gitValidationFileNotIgnored;
                                IStatus error = Status.error(MessageFormat.format(format, path));
                                appendStatus(error);
                                continue;
                            }
                            continue;
                        }
                        if (isDir) {
                            // enter subtree even when ignored : specific sub-files may be not ignored in an ignored folder...
                            walk.enterSubtree();
                        }
                    }
                }
            } catch (IOException e) {
                throw new InvocationTargetException(e);
            }
        }
    }

    /**
     * Add the sub-status to the general status
     * 
     * @param newStatus status to add
     */
    private synchronized void appendStatus(IStatus newStatus) {
        if (!newStatus.isOK()) {
            if (this.status.isOK()) {
                // just replace
                this.status = newStatus;
            } else if (this.status instanceof MultiStatus) {
                // append status
                ((MultiStatus) this.status).add(newStatus);
            } else {
                // make multi-status
                MultiStatus multi = new MultiStatus(RepositoryValidator.class, 0,
                        Messages.gitValidationIncorrectGitignore);
                multi.add(this.status);
                multi.add(newStatus);
                this.status = multi;
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.core.IRunnableWithStatus#getStatus()
     */
    @Override
    public IStatus getStatus() {
        return status;
    }

}
