/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.filestore;

import java.io.InputStream;
import java.util.Collections;

import org.bonitasoft.studio.common.editor.BonitaJavaEditor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.jarpackager.IJarExportRunnable;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;


/**
 * @author Romain Bioteau
 *
 */
public class SourceFileStore extends AbstractFileStore {

    private String qualifiedClassName;
    private IEditorPart editorPart;

    public SourceFileStore(String qualifiedClassName, IRepositoryStore parentStore) {
        super("", parentStore);
        this.qualifiedClassName = qualifiedClassName ;
    }

    @Override
    public String getName() {
        if(getResource() != null){
            return getResource().getName();
        }else{
            return qualifiedClassName;
        }

    }

    @Override
    public String getDisplayName() {
        return getName() ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("java.gif", CommonRepositoryPlugin.getDefault());
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getContent()
     */
    @Override
    public InputStream getContent() {
        try {
            return getResource().getContents() ;
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getResource()
     */
    @Override
    public IFile getResource() {
        IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
        IType type;
        try {
            type = project.findType(qualifiedClassName);
            if(type != null && type.getCompilationUnit() != null){
                return (IFile) type.getCompilationUnit().getResource() ;
            }
        } catch (JavaModelException e) {
            BonitaStudioLog.error(e) ;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(Object content) {


    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {

        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try {
                    editorPart = IDE.openEditor(page, new FileEditorInput(getResource()), BonitaJavaEditor.ID);
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveEditor(editorPart, false);
                } catch (PartInitException e) {
                }

            }
        }) ;
        return editorPart;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
    @Override
    protected void doClose() {

    }

    public void exportAsJar(String absoluteTargetFilePath, boolean includeSources) {
        final JarPackageData jarPackakeData = new JarPackageData() ;
        IFile[] elements = Collections.singletonList(getResource()).toArray(new IFile[1]) ;
        jarPackakeData.setJarLocation(new Path(absoluteTargetFilePath)) ;
        jarPackakeData.setElements(elements) ;
        jarPackakeData.setExportWarnings(false) ;
        jarPackakeData.setComment(SourceRepositoryStore.SIGNATURE_FILE_NAME) ;
        jarPackakeData.setBuildIfNeeded(true) ;
        jarPackakeData.setExportClassFiles(true) ;
        jarPackakeData.setExportJavaFiles(includeSources) ;
        jarPackakeData.setGenerateManifest(true) ;
        jarPackakeData.setUsesManifest(true) ;
        jarPackakeData.setOverwrite(true) ;
        jarPackakeData.setUseSourceFolderHierarchy(includeSources) ;
        final IJarExportRunnable runnable = jarPackakeData.createJarExportRunnable(null) ;
        try {
            runnable.run(Repository.NULL_PROGRESS_MONITOR) ;
        } catch (Exception e){
            BonitaStudioLog.error(e) ;
        }
    }

    @Override
    public void rename(String newQualifiedClassName) {
        IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
        String packageName = "" ;
        String className = newQualifiedClassName ;

        if(newQualifiedClassName.indexOf(".") != -1){
            packageName = newQualifiedClassName.substring(0,newQualifiedClassName.lastIndexOf(".")) ;
            className = newQualifiedClassName.substring(newQualifiedClassName.lastIndexOf(".")+1,newQualifiedClassName.length()) ;
        }

        try {
            final IRepositoryStore store = getParentStore() ;
            IPackageFragmentRoot root = project.findPackageFragmentRoot(store.getResource().getFullPath());
            root.createPackageFragment(packageName, true, Repository.NULL_PROGRESS_MONITOR) ;
            IPackageFragment targetContainer = project.findPackageFragment(store.getResource().getFullPath().append(packageName.replace(".","/"))) ;
            IType type = project.findType(qualifiedClassName) ;
            if(type != null){
                type.getCompilationUnit().move(targetContainer, null, className+".java", true, Repository.NULL_PROGRESS_MONITOR) ;
                qualifiedClassName = newQualifiedClassName ;
            }
        } catch (Exception e) {
            BonitaStudioLog.error(e) ;
        }

    }

}
