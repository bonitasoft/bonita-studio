/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.bonitasoft.forms.server.exception.InvalidFormDefinitionException;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.exporter.application.service.CssGeneratorService;
import org.bonitasoft.studio.exporter.form.FormsExporterService;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * Exports forms.xml from a process
 * 
 * @author Baptiste Mesta
 * 
 */
public class FormsXMLExporter {


    /**
     * 
     * exports forms.xml to the war file
     * 
     * @param process
     *            from which resources are exported
     * @param excludedObject 
     * @param processDefinitionUUID
     * @param warFile
     *            were to put resources
     * @param monitor
     * @return true if all files are exported
     * @throws InvalidFormDefinitionException
     * @throws IOException
     * @throws InvalidFormDefinitionException
     */
    public static boolean exportFormsXML(AbstractProcess process, File destFolderFile, boolean isAllInBarExport, Set<EObject> excludedObject, IProgressMonitor monitor) throws Exception {
        boolean succes = true;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File formsXmlNew = null ;
        File formXml = null ;
        try {

            formXml = FormsExporterService.getInstance().getFormsExporter().createXmlForms(process,isAllInBarExport,excludedObject);
            formsXmlNew = new File(formXml.getParent() + File.separatorChar + "forms.xml"); //$NON-NLS-1$
            fis = new FileInputStream(formXml);
            fos = new FileOutputStream(formsXmlNew);
            FileUtil.copy(fis, fos,-1);
            fis.close();
            fos.close();
            File destFile = new File(destFolderFile.getAbsolutePath()+File.separatorChar+"WEB-INF"+File.separatorChar+"classes"); //$NON-NLS-1$ //$NON-NLS-2$

            if(destFile.exists()){
                PlatformUtil.copyResource(destFile, formsXmlNew, monitor);
                formXml.delete() ;
                formsXmlNew.delete() ;
            }else{
                PlatformUtil.copyResource(destFolderFile, formsXmlNew, monitor);
                formsXmlNew.delete();
                formXml.delete() ;
            }

            //WarFactory.addFilesToExistingEntry(warFile, files, path);
            //add the css with custom widgets styles in application/css/
            CssGeneratorService.getInstance().getCssGenerator().addCssToWar(process, destFolderFile,monitor);

        } catch (IOException e1) {
            succes = false;
            BonitaStudioLog.error(e1);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            throw e;
        } finally {
            try{
                if(fis != null){
                    fis.close();
                }
                if(fos != null){
                    fos.close();
                }

            }catch (Exception e) {
                BonitaStudioLog.error(e);
            }
        }

        return succes;
    }

}
