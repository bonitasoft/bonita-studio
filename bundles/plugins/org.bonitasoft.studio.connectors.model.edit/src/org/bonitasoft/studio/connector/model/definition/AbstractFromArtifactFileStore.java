/**
 * Copyright (C) 2025 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.model.definition;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A file store for a definition or implementation file (for connector or actor filter) that is loaded from an artifact (typically an extension submodule).
 * 
 * @param <T> the type of EObject contained in this file store (definition or implementation).
 */
public abstract class AbstractFromArtifactFileStore<T extends EObject> extends EMFFileStore<T> {

    /** The entry to file in project's output directory when concerned file is a project folder */
    private String entryInOutputDirectory = null;
    private final Class<T> contentClass;

    /**
     * Constructor for a file stored in a folder repository store.
     * 
     * @param fileName the name of the file.
     * @param store the parent store.
     * @param contentClass the class of the content stored in this file store.
     */
    protected AbstractFromArtifactFileStore(final String fileName,
            final AbstractEMFRepositoryStore<? extends AbstractFromArtifactFileStore<T>> store, Class<T> contentClass) {
        super(fileName, store);
        this.contentClass = contentClass;
    }

    /**
     * Constructor for a file stored in a submodule, at an entry in the output directory.
     * 
     * @param projectName the name of the project where the file is stored.
     * @param entryInOutputDirectory the entry in the output directory of the project where the file is stored.
     * @param store the parent store.
     * @param contentClass the class of the content stored in this file store.
     */
    protected AbstractFromArtifactFileStore(final String projectName, final String entryInOutputDirectory,
            final AbstractEMFRepositoryStore<? extends AbstractFromArtifactFileStore<T>> store, Class<T> contentClass) {
        super(projectName, store);
        this.entryInOutputDirectory = entryInOutputDirectory;
        this.contentClass = contentClass;
    }

    @Override
    protected T doGetContent() throws ReadFileStoreException {
        try {
            final EObject root = super.doGetContent();
            return Optional.ofNullable(root).map(this::extractRootContent).orElseGet(this::makeUnloadableContent);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            return makeUnloadableContent();
        }
    }

    /**
     * Extracts the content from the given document root.
     * 
     * @param documentRoot document root from file
     * @return store content
     */
    abstract protected T extractRootContent(EObject documentRoot);

    /**
     * Wrap the content in a document root to save in resource.
     * 
     * @return document root holding the content
     */
    abstract protected EObject wrapContentWithRoot(T content);

    /**
     * Make a template content, used when the content cannot be loaded.
     * 
     * @return a template for the content of this file store
     */
    abstract protected T makeUnloadableContent();

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.EMFFileStore#getFileStorePath()
     */
    @Override
    protected String getFileStorePath() {
        if (getParentStore().getResource() != null) {
            // file in parent store
            return super.getFileStorePath();
        }
        // try and find the definition file in the adequate module
        var rootProject = Adapters.adapt(getParentStore().getRepository(), BonitaProject.class);
        var extensionProject = rootProject.getExtensionsProjects().stream().filter(p -> p.getName().equals(getName()))
                .findFirst();
        if (extensionProject.isPresent() && entryInOutputDirectory != null) {
            try {
                var target = MavenProjectHelper.getOutputDirectory(extensionProject.get());
                if (target != null) {
                    var compiledFile = ResourcesPlugin.getWorkspace().getRoot().getFolder(IPath.fromFile(target))
                            .getFile(entryInOutputDirectory);
                    if (!compiledFile.exists()) {
                        // try and compile maven project
                        extensionProject.get().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
                        if (!compiledFile.exists()) {
                            extensionProject.get().build(IncrementalProjectBuilder.FULL_BUILD, null);
                        }
                    }
                    if (compiledFile.exists()) {
                        return compiledFile.getLocation().toString();
                    }
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    @Override
    protected void doSave(final Object content) {
        if (contentClass.isInstance(content)) {
            final Resource emfResource = getEMFResource();
            emfResource.getContents().clear();
            final EObject root = wrapContentWithRoot(contentClass.cast(content));
            emfResource.getContents().add(root);
            try {
                final Map<String, Object> options = new HashMap<>();
                options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                options.put(XMLResource.OPTION_XML_VERSION, "1.0");
                emfResource.save(options);
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    protected void doDelete() {

    }

}
