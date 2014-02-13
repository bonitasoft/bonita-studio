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
package org.bonitasoft.studio.engine.operation;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import org.bonitasoft.engine.exception.BonitaRuntimeException;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * @author Romain Bioteau
 *
 */
public class ApplicationURLBuilder {

    public static final String APPLI_PATH = "/bonita/portal/homepage?"; //$NON-NLS-1$
    public static final String MODE_APP ="app";
    public static final String MODE_FORM="form";

    private final AbstractProcess process;
    private final Long processId;
    private final String configurationId;
    private String mode;

    
    public ApplicationURLBuilder(AbstractProcess process, Long processId, String configurationId){
    	this(process,processId,configurationId,MODE_APP);
    }
    
    public ApplicationURLBuilder(AbstractProcess process, Long processId, String configurationId,String mode) {
    	Assert.isNotNull(mode);
        this.process = process ;
        this.processId = processId ;
        this.configurationId = configurationId ;
        this.mode = mode;
    }

    public URL toURL(IProgressMonitor monitor) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
        Configuration conf = getConfiguration(process, configurationId) ;
        IPreferenceStore store =  BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore() ;
        String locale = store.getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE) ;
        String token = "" ;
        String userName = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.USER_NAME) ;
        String password = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.USER_PASSWORD) ;
        if(conf != null && conf.getUsername() != null){
            userName = conf.getUsername() ;
            password = conf.getPassword() ;
        }

        final String loginURL = BOSWebServerManager.getInstance().generateLoginURL(userName, password) ;
        final String runUrl = APPLI_PATH + token +"ui=form&locale="+locale+"#form="+URLEncoder.encode(process.getName()+"--"+process.getVersion(), "UTF-8")+"$entry&process="+processId+"&mode="+mode;
        return new URL(loginURL+"&redirectUrl="+URLEncoder.encode(runUrl, "UTF-8"));
    }
    
    private Configuration getConfiguration(final AbstractProcess process,String configurationId) {
        Configuration configuration = null ;
        final ProcessConfigurationRepositoryStore processConfStore = (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class) ;
        if(configurationId == null){
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore().getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION) ;
        }
        if(configurationId.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)){
            String id = ModelHelper.getEObjectID(process) ;
            IRepositoryFileStore file = processConfStore.getChild(id+".conf") ;
            if(file == null){
                //FIXME warn user that there is no configuration for the process
                throw new BonitaRuntimeException("Unable to run process, you must first configure it");
            }
            configuration = (Configuration) file.getContent();
        }else{
            for(Configuration conf : process.getConfigurations()){
                if(configurationId.equals(conf.getName())){
                    configuration = conf ;
                }
            }
        }
        return configuration ;
    }
}
