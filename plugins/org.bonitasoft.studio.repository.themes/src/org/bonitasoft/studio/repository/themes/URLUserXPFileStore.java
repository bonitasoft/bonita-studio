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
package org.bonitasoft.studio.repository.themes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.theme.ThemeDescriptorManager;
import org.bonitasoft.theme.exception.ThemeDescriptorNotFoundException;
import org.bonitasoft.theme.model.ThemeDescriptor;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileExportOperation;

/**
 * @author Romain Bioteau
 *
 */
public class URLUserXPFileStore extends UserXpFileStore {

    private final URL url;

    public URLUserXPFileStore(URL url, IRepositoryStore store) {
        super(url.toString(), store);
        this.url = url ;
    }

    /**
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getName()
     */
    @Override
    public String getName() {
        String file = url.getFile() ;
        String[] segments = file.split("/") ;
        return segments[segments.length -1] ;
    }

    @Override
    public boolean isReadOnly() {
        return true ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getFile()
     */
    @Override
    public IFolder getResource() {
        return null ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#isShared()
     */
    @Override
    public boolean isShared() {
        return false;
    }


    @Override
    public boolean canBeExported() {
        return false ;
    }

    @Override
    protected void doDelete() {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#rename(java.lang.String)
     */
    @Override
    public void rename(String newName) {


    }

    public URL getUrl() {
        return url;
    }


    @Override
    public File getRootFile() {
        try {
            return new File(FileLocator.toFileURL(url).getFile());
        } catch (IOException e) {
            BonitaStudioLog.error(e) ;
        }
        return null;
    }


    @Override
    protected ThemeDescriptor getThemeDescriptor()  {
        try {
            return getThemeDescriptorManager().getThemeDescriptor(new File(getRootFile(), ThemeDescriptorManager.THEME_DESCRIPTOR_NAME));
        } catch (ThemeDescriptorNotFoundException e) {
            BonitaStudioLog.error(e) ;
        }
        return null ;
    }

    @Override
    public void setReadOnly(boolean readOnly) {

    }

    @Override
    protected void doSave(Object content) {

    }

    @Override
    public boolean canBeShared() {
        return false;
    }

    @Override
    public FileInputStream getContent() {
        try {
            final File tempFile = File.createTempFile(getName(), ".zip", ProjectUtil.getBonitaStudioWorkFolder());
            final IFolder tempFolder = getParentStore().getResource().getProject().getFolder("tmp");
            if(tempFolder.exists()){
                tempFolder.delete(true, Repository.NULL_PROGRESS_MONITOR);
                getParentStore().getResource().getProject().refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
            }

            PlatformUtil.copyResource(tempFolder.getLocation().toFile(), new File(FileLocator.toFileURL(url).getFile()), Repository.NULL_PROGRESS_MONITOR);
            tempFolder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
            replaceUrl(tempFolder);

            //zip it
            final ArchiveFileExportOperation exportOperation = new ArchiveFileExportOperation(Arrays.asList(tempFolder.members()), tempFile.getAbsolutePath());
            exportOperation.setCreateLeadupStructure(false);
            exportOperation.run(Repository.NULL_PROGRESS_MONITOR);
            //delete temp files
            tempFolder.delete(true, Repository.NULL_PROGRESS_MONITOR);
            getParentStore().getResource().getProject().refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR) ;
            final FileInputStream fis = new FileInputStream(tempFile);
            return fis;

        } catch (final Exception e){
            BonitaStudioLog.error(e) ;
        }
        return null;
    }

    @Override
    public void export(String targetAbsoluteFilePath) {
        targetAbsoluteFilePath = targetAbsoluteFilePath + File.separatorChar + getName() +"."+LookNFeelRepositoryStore.LF_EXTENSION;
        try {
            final File file = new File(targetAbsoluteFilePath);
            if(file.exists()){
                if(!FileActionDialog.overwriteQuestion(file.getName())){
                    return;
                }else{
                    file.delete();
                }
            }
            file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);
            FileUtil.copy(getContent(), stream);
        } catch (FileNotFoundException e) {
            BonitaStudioLog.error(e);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
    }

}
