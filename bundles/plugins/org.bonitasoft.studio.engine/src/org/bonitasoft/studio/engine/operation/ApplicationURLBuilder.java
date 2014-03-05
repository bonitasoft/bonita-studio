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

	private static final String ENCODING = "UTF-8";
	public static final String APPLI_PATH = "portal/homepage?"; //$NON-NLS-1$
    public static final String MODE_APP ="app";
    public static final String MODE_FORM="form";

    private final AbstractProcess process;
    private final Long processId;
    private String configurationId;
    private String mode;

    public ApplicationURLBuilder(AbstractProcess process, Long processId, String configurationId){
    	this(process,processId,configurationId,MODE_APP);
    }
    
    public ApplicationURLBuilder(AbstractProcess process, Long processId, String configurationId,String mode) {
        this.process = process ;
        this.processId = processId ;
        this.configurationId = configurationId ;
        this.mode = mode;
    }

    public URL toURL(IProgressMonitor monitor) throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
        String locale = getWebLocale() ;
        String userName = getDefaultUsername() ;
        String password = getDefaultPassword() ;
      
        Configuration conf = getConfiguration() ;
        if(conf != null && conf.getUsername() != null){
            userName = conf.getUsername() ;
            password = conf.getPassword() ;
        }

        final String loginURL = buildLoginUrl(userName, password) ;
        return new URL(loginURL+"&redirectUrl="+URLEncoder.encode(getRedirectURL(locale), ENCODING));
    }
    
    protected String getRedirectURL(String locale) throws UnsupportedEncodingException {
		return APPLI_PATH + "ui=form&"+getLocaleParameter(locale)+"#form="+URLEncoder.encode(process.getName()+"--"+process.getVersion(), ENCODING)+"$entry&process="+processId+"&"+getModeParameter();
	}
    
    protected String getLocaleParameter(String locale) {
		return "locale="+locale;
	}

    protected String getModeParameter(){
    	Assert.isNotNull(mode);
    	return "mode="+mode;
    }
    
	protected String buildLoginUrl(String userName, String password) {
		return BOSWebServerManager.getInstance().generateLoginURL(userName, password);
	}

	protected IPreferenceStore getPreferenceStore() {
		return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
	}

	protected String getWebLocale() {
		return getPreferenceStore().getString(BonitaPreferenceConstants.CURRENT_UXP_LOCALE);
	}

	protected String getDefaultPassword() {
		return getPreferenceStore().getString(BonitaPreferenceConstants.USER_PASSWORD);
	}

	protected String getDefaultUsername() {
		return getPreferenceStore().getString(BonitaPreferenceConstants.USER_NAME);
	}
    
	protected Configuration getConfiguration() {
        Configuration configuration = null ;
        final ProcessConfigurationRepositoryStore processConfStore = (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class) ;
        if(configurationId == null){
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore().getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION) ;
        }
        if(configurationId.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)){
            String id = ModelHelper.getEObjectID(process) ;
            IRepositoryFileStore file = processConfStore.getChild(id+".conf") ;
            if(file == null){
            	return null;
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
