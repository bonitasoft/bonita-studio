/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.extension.IConfigurationImportAction;
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

public class ImportParametersAction extends Action implements IConfigurationImportAction  {

    private Configuration configuration;
    private String filePath;

    public ImportParametersAction() {
        setText(Messages.parameters) ;
        setImageDescriptor(Pics.getImageDescriptor(PicsConstants.parameter)) ;
    }

    @Override
    public void run() {
        if(filePath == null){
            FileDialog fd = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN) ;
            fd.setFilterExtensions(new String[]{"*.properties"}) ;
            filePath = fd.open() ;
        }
        if(filePath != null){
            Properties p = loadProperties(filePath) ;
            for(Entry<Object, Object> entry : p.entrySet()){
                String name = (String) entry.getKey() ;
                String value = (String) entry.getValue() ;
                for(Parameter param : configuration.getParameters()){
                    if(param.getName().equals(name)){
                        param.setValue(value) ;
                    }
                }
            }

        }
    }

    protected Properties loadProperties(String path) {
        FileInputStream fis = null ;
        Properties p = null ;
        try{
            fis = new FileInputStream(path) ;
            p = new Properties() ;
            p.load(fis) ;
        }catch (Exception e) {
            BonitaStudioLog.error(e) ;
        }finally{
            if(fis != null){
                try {
                    fis.close() ;
                } catch (IOException e1) {

                }
            }
        }

        return p;
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration ;
    }

    @Override
    public void setProcess(AbstractProcess process) {
    }

    @Override
    public void setFilePath(String absolutePath) {
        filePath = absolutePath ;
    }



}
