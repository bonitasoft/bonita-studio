/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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

package org.bonitasoft.studio.engine.operation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveFactory;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author Romain Bioteau
 */
public class ExportBarOperation  {

    private final ArrayList<AbstractProcess> processes;
    protected String configurationId;
    private String targetFolderPath;
    public IStatus status = Status.OK_STATUS;

    public ExportBarOperation() {
        processes = new ArrayList<AbstractProcess>() ;
    }


    public void addProcessToDeploy(AbstractProcess process){
        Assert.isTrue(!(process instanceof MainProcess),"process can't be a MainProcess") ;
        if(!processes.contains(process)){
            processes.add(process) ;
        }
    }

    public void setTargetFolder(String targetFolderPath){
        this.targetFolderPath = targetFolderPath;
    }

    public void setConfigurationId(String configurationId){
        this.configurationId = configurationId;
    }


    public IStatus run(IProgressMonitor monitor)  {
        monitor.beginTask(Messages.exporting, IProgressMonitor.UNKNOWN) ;

        Assert.isNotNull(targetFolderPath) ;
        Assert.isTrue(!processes.isEmpty()) ;

        status = Status.OK_STATUS ;

        for (final AbstractProcess process : processes) {
            final File targetFolder = new File(targetFolderPath) ;
            if(!targetFolder.exists()){
                targetFolder.mkdirs() ;
            }

            final File outputFile = new File(targetFolder, process.getName()+"--"+process.getVersion()+".bar") ;
            if(outputFile.exists()){
                if(FileActionDialog.overwriteQuestion(outputFile.getName())){
                    outputFile.delete() ;
                }else{
                    status = Status.CANCEL_STATUS ;
                    return status ;
                }
            }

            status = exportBar(process, outputFile,monitor);
            if(!status.isOK()){
                return status ;
            }
        }
        monitor.done() ;
        return status;
    }


    protected IStatus exportBar( final AbstractProcess process, final File outputFile,IProgressMonitor monitor) {
        try {
            final BusinessArchive bar = BarExporter.getInstance().createBusinessArchive(process,configurationId,Collections.EMPTY_SET);
            writeBusinessArchiveToFile(outputFile, bar);
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
            monitor.done() ;
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID, ex.getMessage(), ex)  ;
            return status;
        }
        return status;
    }


	protected void writeBusinessArchiveToFile(final File outputFile, final BusinessArchive bar) throws IOException {
		BusinessArchiveFactory.writeBusinessArchiveToFile(bar,outputFile);
	}


    public IStatus getStatus() {
        return status;
    }

}
