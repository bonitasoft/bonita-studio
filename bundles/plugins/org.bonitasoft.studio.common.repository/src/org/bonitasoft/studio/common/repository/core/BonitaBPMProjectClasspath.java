/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.forms.client.model.FormFieldValue;
import org.bonitasoft.forms.server.validator.IFormFieldValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaModelManager;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathsBlock;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElement;
import org.eclipse.jdt.launching.JavaRuntime;

import com.google.common.collect.Lists;

/**
 * @author Romain Bioteau
 */
public class BonitaBPMProjectClasspath {

    private final IProject project;
    private final IRepository repository;

    public BonitaBPMProjectClasspath(final IProject project, final IRepository repository) {
        this.project = project;
        this.repository = repository;
    }

    public void create(final IProgressMonitor monitor) throws CoreException {
        if (!classpathExists()) {
            monitor.subTask(Messages.initializingProjectClasspath);

            final IJavaProject javaProject = asJavaProject();
            final List<IClasspathEntry> entries = addClasspathEntries();

            try {
                addSpecificEntriesForDevMode(entries);
            } catch (final MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            BonitaStudioLog.debug("Updating build path...", CommonRepositoryPlugin.PLUGIN_ID);
            javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), true, monitor);
        }
    }

    public void refresh(final IProgressMonitor monitor) throws CoreException {
        final JavaModelManager manager = JavaModelManager.getJavaModelManager();
        manager.getJavaModel().refreshExternalArchives(null, monitor);
        final IJavaProject javaProject = asJavaProject();
        BuildPathsBlock.flush(Arrays.asList(CPListElement.createFromExisting(javaProject)), javaProject.getOutputLocation(), javaProject, null, monitor);
    }

    protected void addSpecificEntriesForDevMode(final List<IClasspathEntry> entries) throws IOException, MalformedURLException {
        /* Workaround for dev mode */
        if (Platform.inDevelopmentMode()) { // WORKAROUND FOR DEV MODE: see Eclipse Bug 111238
            // BOS Common
            final String bosCommonResource = AbstractConnector.class.getCanonicalName().replace('.', '/') + ".class";
            final URL bosBundleResource = AbstractConnector.class.getClassLoader().getResource(bosCommonResource);
            URL serverResource = FileLocator.resolve(bosBundleResource);
            if (serverResource.toString().startsWith("jar:file")) {
                serverResource = new URL(serverResource.toString().substring("jar:".length(), serverResource.toString().lastIndexOf('!')));
            }
            entries.add(JavaCore.newLibraryEntry(Path.fromOSString(serverResource.getFile()), null, null));

            // Forms client
            final String formsClientResource = FormFieldValue.class.getCanonicalName().replace('.', '/') + ".class";
            final URL formsClientBundleResource = FormFieldValue.class.getClassLoader().getResource(formsClientResource);
            URL formsResource = FileLocator.resolve(formsClientBundleResource);
            if (formsResource.toString().startsWith("jar:file")) {
                formsResource = new URL(formsResource.toString().substring("jar:".length(), formsResource.toString().lastIndexOf('!')));
            }
            entries.add(JavaCore.newLibraryEntry(Path.fromOSString(formsResource.getFile()), null, null));

            // Forms server
            final String formsServerResourcePath = IFormFieldValidator.class.getCanonicalName().replace('.', '/') + ".class";
            final URL formsServerBundleResource = FormFieldValue.class.getClassLoader().getResource(formsServerResourcePath);
            URL formsServerResource = FileLocator.resolve(formsServerBundleResource);
            if (formsServerResource.toString().startsWith("jar:file")) {
                formsServerResource = new URL(formsServerResource.toString().substring("jar:".length(), formsServerResource.toString().lastIndexOf('!')));
            }
            entries.add(JavaCore.newLibraryEntry(Path.fromOSString(formsServerResource.getFile()), null, null));

        }
    }

    @SuppressWarnings("rawtypes")
    protected List<IClasspathEntry> addClasspathEntries() {
        final List<IClasspathEntry> entries = Lists.newArrayList(
                JavaCore.newContainerEntry(new Path("repositoryDependencies"), true),
                JavaCore.newContainerEntry(JavaRuntime.newJREContainerPath(JavaRuntime.getExecutionEnvironmentsManager().getEnvironment("JavaSE-1.7"))),
                JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins")),
                JavaCore.newContainerEntry(new Path("GROOVY_SUPPORT"), true));

        // Add src folders in classpath
        for (final IRepositoryStore store : repository.getAllStores()) {
            if (store instanceof SourceRepositoryStore) {
                final IClasspathEntry newSourceEntry = JavaCore.newSourceEntry(store.getResource().getFullPath());
                if (!entries.contains(newSourceEntry)) {
                    entries.add(newSourceEntry);
                }
            }
        }
        return entries;
    }

    private IJavaProject asJavaProject() throws CoreException {
        return (IJavaProject) project.getNature(JavaCore.NATURE_ID);
    }

    public boolean classpathExists() {
        return project.findMember(".classpath") != null;
    }

    public void delete(final IProgressMonitor monitor) throws CoreException {
        final IFile classpathFile = project.getFile(".classpath");
        if (classpathFile.exists()) {
            classpathFile.delete(true, false, monitor);
        }
    }

}
