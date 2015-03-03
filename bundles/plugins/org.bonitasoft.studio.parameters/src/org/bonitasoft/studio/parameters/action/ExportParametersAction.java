/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
            final FileDialog fd = new FileDialog(Display.getDefault().getActiveShell(), SWT.SAVE) ;
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
        final File file = new File(path);
        if(file.exists()){
            if(!FileActionDialog.overwriteQuestion(file.getName())){
                return ;
            }else{
                file.delete() ;
            }
        }

        final Properties properties = new Properties();
        for(final Parameter p : configuration.getParameters()){
            final String paramValue = p.getValue();
			properties.put(p.getName(), paramValue != null ? paramValue : "") ;
        }


        FileOutputStream fos = null  ;
        try {
            fos = new FileOutputStream(file) ;
            properties.store(fos, "") ;
        } catch (final IOException e1) {
            BonitaStudioLog.error(e1) ;
        }finally{
            if(fos != null){
                try {
                    fos.close() ;
                } catch (final IOException e1) {

                }
            }
        }
    }

    @Override
    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration ;
    }

    @Override
    public void setProcess(final AbstractProcess process) {
        this.process = process ;
    }

    @Override
    public void setTargetPath(final String path) {
        this.path = path ;
    }

}
