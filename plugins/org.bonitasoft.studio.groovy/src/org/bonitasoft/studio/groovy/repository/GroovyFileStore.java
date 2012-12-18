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
package org.bonitasoft.studio.groovy.repository;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class GroovyFileStore extends AbstractFileStore {

    private static final String UTF_8 = "UTF-8";

    public GroovyFileStore(String fileName, IRepositoryStore parentStore) {
        super(fileName, parentStore);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.groovyScript);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getContent()
     */
    @Override
    public String getContent() {
        try{
            if(getResource().exists()){
                final FileInputStream fis = (FileInputStream) getResource().getContents() ;
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                return new String(buffer,UTF_8);
            }
        }catch (Exception e) {
            BonitaStudioLog.error(e) ;
        }
        return "" ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(Object content) {
        if(content instanceof String){
            if(getResource().exists() && content != null && content.equals(getContent())){
                return ;
            }
            try{
                String scriptContent = (String) content ;
                InputStream is = new ByteArrayInputStream(scriptContent.getBytes());
                IFile sourceFile = getResource() ;
                if(sourceFile.exists() && FileActionDialog.overwriteQuestion(getName())){
                    sourceFile.setContents(is, IResource.FOLDER, Repository.NULL_PROGRESS_MONITOR) ;
                }else{
                    sourceFile.create(is, true, Repository.NULL_PROGRESS_MONITOR) ;
                }
                if(!UTF_8.equals(sourceFile.getCharset())){
                    sourceFile.setCharset(UTF_8, Repository.NULL_PROGRESS_MONITOR);
                }
            }catch (Exception e) {
                BonitaStudioLog.error(e) ;
            }
        }

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
    @Override
    protected void doClose() {

    }

    public IFile getClassFile() {
        if(getResource().exists()){
            IProject project = getParentStore().getResource().getProject() ;
            IFolder binFolder = project.getFolder("bin") ;
            IFile classFile = binFolder.getFile(getName().replace(".groovy",".class"));
            if (classFile.exists()) {
                return classFile ;
            }
        }
        return null;
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

}
