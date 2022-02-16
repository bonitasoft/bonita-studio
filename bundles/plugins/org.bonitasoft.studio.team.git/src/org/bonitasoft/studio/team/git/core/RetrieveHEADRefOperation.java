/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.egit.core.credentials.UserPasswordCredentials;
import org.eclipse.egit.core.internal.credentials.EGitCredentialsProvider;
import org.eclipse.egit.core.op.ListRemoteOperation;
import org.eclipse.egit.core.settings.GitSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.URIish;

public class RetrieveHEADRefOperation implements IRunnableWithProgress {

    private Ref head;
    private URIish uri;
    private UserPasswordCredentials credentials;

    public RetrieveHEADRefOperation(URIish uri, UserPasswordCredentials credentials) {
        this.uri = uri;
        this.credentials = credentials;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
            final Repository db = FileRepositoryBuilder
                    .create(new File("/tmp")); //$NON-NLS-1$
            int timeout = GitSettings.getRemoteConnectionTimeout();
            ListRemoteOperation listRemoteOp = new ListRemoteOperation(db, uri, timeout);
            if (credentials != null) {
                listRemoteOp
                        .setCredentialsProvider(new EGitCredentialsProvider(
                                credentials.getUser(), credentials
                                        .getPassword()));
            }
            listRemoteOp.run(monitor);
            final Ref idHEAD = listRemoteOp.getRemoteRef(Constants.HEAD);
            boolean headIsMaster = false;
            final String masterBranchRef = Constants.R_HEADS + Constants.MASTER;
            for (final Ref r : listRemoteOp.getRemoteRefs()) {
                final String n = r.getName();
                if (!n.startsWith(Constants.R_HEADS)) {
                    continue;
                }
                if (idHEAD == null || headIsMaster) {
                    continue;
                }
                ObjectId objectId = r.getObjectId();
                if (objectId == null) {
                    continue;
                }
                if (objectId.equals(idHEAD.getObjectId())) {
                    headIsMaster = masterBranchRef.equals(r.getName());
                    if (head == null || headIsMaster) {
                        head = r;
                    }
                }
            }
            if (idHEAD != null && head == null) {
                head = idHEAD;
            }
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        }
    }

    public Ref getHead() {
        return head;
    }

}
