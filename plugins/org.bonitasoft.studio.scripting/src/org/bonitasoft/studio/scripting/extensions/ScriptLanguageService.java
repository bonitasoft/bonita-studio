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
package org.bonitasoft.studio.scripting.extensions;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Romain Bioteau
 *
 */
public class ScriptLanguageService {

	private static ScriptLanguageService INSTANCE ;
	private Set<IScriptLanguageProvider>  scriptProviders ;
	
	private static final String SCRIPT_LANGUAGE_EXTENSION_ID = "org.bonitasoft.studio.scripting.scriptLanguageProvider";
	private static final String PROVIDER_CLASS_ATTRIBUTE = "languageProviderClass";
	
	public static ScriptLanguageService getInstance(){
		if(INSTANCE == null){
			INSTANCE = new ScriptLanguageService() ;
		}
		return INSTANCE;
	}
	
	private ScriptLanguageService(){
		getScriptLanguageProviders() ;
	}
	
	public Set<IScriptLanguageProvider> getScriptLanguageProviders() {
		if(scriptProviders == null){
			scriptProviders = new HashSet<IScriptLanguageProvider>() ;
			IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(SCRIPT_LANGUAGE_EXTENSION_ID) ;
			for(IConfigurationElement element : elements){
				try {
					IScriptLanguageProvider provider = (IScriptLanguageProvider) element.createExecutableExtension(PROVIDER_CLASS_ATTRIBUTE) ;
					scriptProviders.add(provider) ;
				}catch (Exception e) {
					BonitaStudioLog.error(e) ;
				}
			}
		}
		return scriptProviders;
	}

	public IScriptLanguageProvider getScriptLanguageProvider(String languageId) {
		for(IScriptLanguageProvider provider : getScriptLanguageProviders()){
			if(provider.getLanguageId().equals(languageId)){
				return provider ;
			}
		}
		return null;
	}

	public String getDefaultLanguage() {
		return getScriptLanguageProviders().isEmpty() ? null :getScriptLanguageProviders().iterator().next().getLanguageId() ;
	}
	
}
