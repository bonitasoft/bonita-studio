/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.userguidance.wizards;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.userguidance.i18n.Messages;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 *
 */
public class DocSynchronizer implements IRunnableWithProgress {

    private static final String DOC_ZIP_FILENAME = "doc.zip";
    private static final String GUIDANCE_URL_PROPERTY = "guidance.url";
    private static final String URL_PARAM = UserGuideWizardPage.BOS_REDIRECT_MAJOR_VERSION+"="+UserGuideWizardPage.MAJOR+"&"+UserGuideWizardPage.BOS_REDIRECT_MINOR_VERSION+"="+UserGuideWizardPage.MINOR;


    public static final String DOC_ROOT = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().getAbsolutePath()+File.separatorChar+"doc" ;
    /* (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
        monitor.setTaskName(Messages.synchronizing) ;

        File tmpArchiveFile = new File(ProjectUtil.getBonitaStudioWorkFolder(),DOC_ZIP_FILENAME) ;

        InputStream stream =null ;
        FileOutputStream out = null ;
        try{
            Properties globalProperties = PlatformUtil.getStudioGlobalProperties() ;
            if(globalProperties != null){

                String url = globalProperties.getProperty(GUIDANCE_URL_PROPERTY) ;
                if(url != null){
                    if(url.contains("?")){
                        url =  url + "&" + URL_PARAM ;
                    }else{
                        url =  url + "?" + URL_PARAM ;
                    }
                    URLConnection connection = new URL(url).openConnection() ;
                    connection.setConnectTimeout(4000) ;
                    stream = connection.getInputStream() ;
                    out = new FileOutputStream(tmpArchiveFile);
                    FileUtil.copy(stream, out);
                    out.close();
                    stream.close();
                }
            }
        }catch (ConnectException e) {
            throw new InvocationTargetException(e);
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        }finally{
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    throw new InvocationTargetException(e);
                }
            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    throw new InvocationTargetException(e);
                }
            }
        }


        File destFolder =  new File(DOC_ROOT) ;
        if(destFolder.exists()){
            PlatformUtil.delete(destFolder, new NullProgressMonitor()) ;
        }
        destFolder.mkdir() ;

        try{
            PlatformUtil.unzipZipFiles(tmpArchiveFile,destFolder, new NullProgressMonitor()) ;
        }catch (Throwable e) {
            BonitaStudioLog.error(e) ;
            PlatformUtil.delete(destFolder, new NullProgressMonitor()) ;
        }

    }

    public static String getLatestDocRoot(){
        if(new File(DOC_ROOT).exists()){
            return DOC_ROOT ;
        }else{
            return "";
        }

    }

}
