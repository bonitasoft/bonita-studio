/*******************************************************************************
 * Copyright (C) 2009, 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.repository.core.internal;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.team.core.RepositoryProvider;

public class RepositoryImpl extends AbstractRepository {

    private BonitaProject bonitaProject;

    public RepositoryImpl(final IWorkspace workspace,
            BonitaProject bonitaProject,
            final ExtensionContextInjectionFactory extensionContextInjectionFactory,
            final JDTTypeHierarchyManager jdtTypeHierarchyManager,
            IEventBroker eventBroker) {
        super(workspace,
                bonitaProject,
                extensionContextInjectionFactory,
                jdtTypeHierarchyManager,
                eventBroker);
        this.bonitaProject = bonitaProject;
    }

    @Override
    protected void connect(IProject project) throws CoreException {
        if (project.isOpen()) {
            var bonitaProject = Adapters.adapt(this, BonitaProject.class);
            var gitDir = bonitaProject.getGitDir();
            if (gitDir.exists()) {
                try {
                    bonitaProject.newConnectProviderOperation().run(new NullProgressMonitor());
                } catch (InvocationTargetException | InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    @Override
    public boolean isShared() {
        if (!getProject().exists()) {
            return false;
        }
        if (getProject().isAccessible()) {
            return RepositoryProvider.getProvider(getProject()) != null;
        } else {
            return bonitaProject.getGitDir().exists();
        }
    }

    @Override
    public boolean isShared(String providerId) {
        IProject project = getProject();
        IPath location = project.getLocation();
        if (project.isAccessible()) {
            return RepositoryProvider.getProvider(project, providerId) != null;
        } else if (location != null) {
            if (providerId.equals("org.eclipse.egit.core.GitProvider")) {
                var projectFolder = location.toFile();
                var gitFolder = projectFolder.toPath().resolve(".git").toFile();
                var parentGitFolder = projectFolder.getParentFile().toPath().resolve(".git").toFile();
                return gitFolder.exists() || parentGitFolder.exists();
            }
        }
        return false;
    }


}
