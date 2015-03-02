/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.extension.IConfigurationExportAction;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

public class ExportParametersAction extends Action implements IConfigurationExportAction {

    private Configuration configuration;
    private AbstractProcess process;
    private String path;

    public ExportParametersAction() {
        setText(Messages.parameters) ;
        setImageDescriptor(Pics.getImageDescriptor(PicsConstants.parameter)) ;
    }

    @Override
    public void run() {
        if(path == null){
            FileDialog fd = new FileDialog(Display.getDefault().getActiveShell(), SWT.SAVE) ;
            fd.setFileName(process.getName()+"_"+process.getVersion()+"_"+Messages.parameters+".properties") ;
            fd.setFilterExtensions(new String[]{"*.properties"}) ;
            path = fd.open() ;
            exportParameters(); ;
        }else{
            path = path + File.separator + "parameters.properties";
            exportParameters();
        }
    }

    protected void exportParameters() {
        File file = new File(path);
        if(file.exists()){
            if(!FileActionDialog.overwriteQuestion(file.getName())){
                return ;
            }else{
                file.delete() ;
            }
        }

        Properties properties = new Properties();
        for(Parameter p : configuration.getParameters()){
            final String paramValue = p.getValue();
			properties.put(p.getName(), paramValue != null ? paramValue : "") ;
        }


        FileOutputStream fos = null  ;
        try {
            fos = new FileOutputStream(file) ;
            properties.store(fos, "") ;
        } catch (IOException e1) {
            BonitaStudioLog.error(e1) ;
        }finally{
            if(fos != null){
                try {
                    fos.close() ;
                } catch (IOException e1) {

                }
            }
        }
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration ;
    }

    @Override
    public void setProcess(AbstractProcess process) {
        this.process = process ;
    }

    @Override
    public void setTargetPath(String path) {
        this.path = path ;
    }

}
