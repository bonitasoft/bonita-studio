/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.repository;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.groovy.GroovyPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * @author Romain Bioteau
 */
public class GroovyFileStore extends AbstractFileStore<String> {

    private static final String UTF_8 = "UTF-8";
    public static final String EXPRESSION_SCRIPT_NAME = "BonitaScriptContext.groovy";

    public GroovyFileStore(final String fileName, final GroovyRepositoryStore parentStore) {
        super(fileName, parentStore);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.groovyScript);
    }
    
    @Override
    protected String doGetContent() throws ReadFileStoreException {
        try {
            if (getResource().exists()) {
                final FileInputStream fis = (FileInputStream) getResource().getContents();
                final byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                return new String(buffer, UTF_8);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return "";
    }


    public ICompilationUnit getCompilationUnit() {
        return JavaCore.createCompilationUnitFrom(getResource());
    }


    @Override
    protected void doSave(final Object content) {
        if (content instanceof String) {
            try {
                if (getResource().exists() && content.equals(getContent())) {
                    return;
                }
            } catch (ReadFileStoreException e1) {
               BonitaStudioLog.warning(e1.getMessage(), GroovyPlugin.PLUGIN_ID);
            }
            try {
                final String scriptContent = (String) content;
                final InputStream is = new ByteArrayInputStream(scriptContent.getBytes(UTF_8));
                final IFile sourceFile = getResource();
                if (sourceFile.exists() && FileActionDialog.overwriteQuestion(getName())) {
                    sourceFile.setContents(is, IResource.FOLDER, AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    sourceFile.create(is, true, AbstractRepository.NULL_PROGRESS_MONITOR);
                }
                if (!UTF_8.equals(sourceFile.getCharset())) {
                    sourceFile.setCharset(UTF_8, AbstractRepository.NULL_PROGRESS_MONITOR);
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }

    }

    @Override
    protected IWorkbenchPart doOpen() {
        try {
            return IDE.openEditor(getActivePage(), getResource());
        } catch (final PartInitException e) {
            throw new RuntimeException("Failed to open application descriptor.", e);
        }
    }

    protected IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
    @Override
    protected void doClose() {

    }

    public List<IFile> getClassFiles() {
        if (getResource().exists()) {
            final List<IFile> res = new ArrayList<>();
            final IProject project = getParentStore().getResource().getProject();
            final IFolder binFolder = project.getFolder("bin");
            final IFile classFile = binFolder.getFile(getName().replace(".groovy", ".class"));
            if (classFile.exists()) {
                res.add(classFile);
                //Search for closure files
                try {
                    final IResource[] potentialClosureFiles = ((IFolder) classFile.getParent()).members();
                    final String fileNameWithoutEnd = classFile.getName().replaceAll(".class", "");
                    for (final IResource potentialClosureFile : potentialClosureFiles) {
                        if (potentialClosureFile instanceof IFile) {
                            final String name = potentialClosureFile.getName();
                            final int indexOfSuffix = name.indexOf("$");
                            if (indexOfSuffix != -1) {
                                if (name.startsWith(fileNameWithoutEnd) && indexOfSuffix == fileNameWithoutEnd.length()) {
                                    res.add((IFile) potentialClosureFile);
                                }
                            }
                        }
                    }
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
                return res;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

}
