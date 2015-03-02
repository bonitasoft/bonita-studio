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
            final FileDialog fd = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN) ;
            fd.setFilterExtensions(new String[]{"*.properties"}) ;
            filePath = fd.open() ;
        }
        if(filePath != null){
            final Properties p = loadProperties(filePath) ;
            for(final Entry<Object, Object> entry : p.entrySet()){
                final String name = (String) entry.getKey() ;
                final String value = (String) entry.getValue() ;
                for(final Parameter param : configuration.getParameters()){
                    if(param.getName().equals(name)){
                        param.setValue(value) ;
                    }
                }
            }

        }
    }

    protected Properties loadProperties(final String path) {
        FileInputStream fis = null ;
        Properties p = null ;
        try{
            fis = new FileInputStream(path) ;
            p = new Properties() ;
            p.load(fis) ;
        }catch (final Exception e) {
            BonitaStudioLog.error(e) ;
        }finally{
            if(fis != null){
                try {
                    fis.close() ;
                } catch (final IOException e1) {

                }
            }
        }

        return p;
    }

    @Override
    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration ;
    }

    @Override
    public void setProcess(final AbstractProcess process) {
    }

    @Override
    public void setFilePath(final String absolutePath) {
        filePath = absolutePath ;
    }



}
