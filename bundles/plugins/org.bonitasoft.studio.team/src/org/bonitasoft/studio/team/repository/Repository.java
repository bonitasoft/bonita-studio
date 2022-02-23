/*******************************************************************************
 * Copyright (C) 2009, 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.repository;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.ProjectFileChangeListener;
import org.bonitasoft.studio.common.repository.jdt.JDTTypeHierarchyManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.egit.core.GitProvider;
import org.eclipse.egit.core.op.ConnectProviderOperation;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.team.core.RepositoryProvider;

/**
 * @author Romain Bioteau
 */
public class Repository extends AbstractRepository {

    private static final long INTERVAL = 30000;
    private static final String GITIGNORE_TEMPLATE = ".gitignore.template";

    public Repository(final IWorkspace workspace, final IProject project,
            final ExtensionContextInjectionFactory extensionContextInjectionFactory,
            final JDTTypeHierarchyManager jdtTypeHierarchyManager,
            IEventBroker eventBroker,
            final boolean migrationEnabled) {
        super(workspace,
                project,
                extensionContextInjectionFactory,
                jdtTypeHierarchyManager,
                eventBroker,
                migrationEnabled);
    }

    @Override
    protected ProjectFileChangeListener createProjectFileChangeListener() {
        return new ProjectFileChangeListenerEx(this);
    }

    @Override
    protected void connect(IProject project) throws CoreException {
        File gitDir = new File(project.getLocation().toFile().getAbsolutePath(),
                Constants.DOT_GIT);
        if (gitDir.exists() && !isShared(GitProvider.ID)) {
            ConnectProviderOperation op = new ConnectProviderOperation(project);
            op.setRefreshResources(false);
            op.execute(AbstractRepository.NULL_PROGRESS_MONITOR);
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
            Path gitFolder = getProject().getLocation().toFile().toPath().resolve(".git");
            Path svnFolder = getProject().getLocation().toFile().toPath().resolve(".svn");
            return gitFolder.toFile().exists() || svnFolder.toFile().exists();
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
                return location.toFile().toPath().resolve(".git").toFile().exists();
            }
        }
        return false;
    }

    @Override
    public void migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        migrateGitIgnoreFile(monitor);
        super.migrate(monitor);
    }

    private void migrateGitIgnoreFile(IProgressMonitor monitor) throws CoreException, MigrationException {
        IFile gitIgnore = getProject().getFile(Constants.GITIGNORE_FILENAME);
        if (gitIgnore.exists()) {
            try (InputStream is = gitIgnore.getContents()) {
                List<String> existingEntries = retrieveGitignoreEntries(is, StandardCharsets.UTF_8);
                List<String> entriesToAdd = retrieveEntriesToAdd(existingEntries);
                if (!entriesToAdd.isEmpty()) {
                    existingEntries.add(System.lineSeparator());
                    existingEntries.addAll(entriesToAdd);
                    String newContent = existingEntries.stream().reduce("",
                            (s1, s2) -> s1 + System.lineSeparator() + s2);
                    gitIgnore.setContents(new ByteArrayInputStream(newContent.getBytes(StandardCharsets.UTF_8)),
                            IResource.KEEP_HISTORY | IResource.FORCE, monitor);
                }
            } catch (IOException e) {
                throw new MigrationException("Failed to update .gitignore file.", e);
            }
        }
    }

    private List<String> retrieveEntriesToAdd(List<String> existingEntries) throws IOException {
        URL gitignoreTemplateUrl = getGitignoreTemplateFileURL();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(gitignoreTemplateUrl.openStream(), StandardCharsets.UTF_8))) {
            List<String> entries = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (lineIsValid(line) && !existingEntries.contains(line)) {
                    entries.add(line);
                }
            }
            return entries;
        }
    }

    private boolean lineIsValid(String line) {
        return line != null && !line.isEmpty() && !line.startsWith("#");
    }

    private static List<String> retrieveGitignoreEntries(InputStream is, Charset encoding) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding))) {
            List<String> entries = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                entries.add(line);
            }
            return entries;
        }
    }

    public static URL getGitignoreTemplateFileURL() throws IOException {
        return FileLocator.toFileURL(Repository.class.getResource(GITIGNORE_TEMPLATE));
    }

}
