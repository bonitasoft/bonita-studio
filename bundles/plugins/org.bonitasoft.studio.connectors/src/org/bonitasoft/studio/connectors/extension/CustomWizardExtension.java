/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.extension;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.page.AbstractConnectorOutputWizardPage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Mickael Istria
 *
 */
public class CustomWizardExtension {

    private final String definitionId;
    private final String definitionVersion;
    private final boolean useDefaultOutputPage;
    private ArrayList<AbstractConnectorConfigurationWizardPage> pages;
    private final IConfigurationElement config;
    private boolean useDefaultGeneratedPages = false;

    public CustomWizardExtension(IConfigurationElement config) {
        this.config = config;
        useDefaultOutputPage = Boolean.parseBoolean(config.getAttribute("useDefaultOutputPage"));
        if(config.getAttribute("useDefaultGeneratedPages") != null){
            useDefaultGeneratedPages = Boolean.parseBoolean(config.getAttribute("useDefaultGeneratedPages"));
        }
        definitionId = config.getAttribute("DefinitionId");
        definitionVersion = config.getAttribute("DefinitionVersion");
    }


    public List<AbstractConnectorConfigurationWizardPage> getPages()  {
        pages = new ArrayList<AbstractConnectorConfigurationWizardPage>();
        for (IConfigurationElement pageClass : config.getChildren("configurationPage")) {
            AbstractConnectorConfigurationWizardPage page;
            try {
                page = (AbstractConnectorConfigurationWizardPage)pageClass.createExecutableExtension("page");
                pages.add(page);
            } catch (CoreException e) {
                BonitaStudioLog.error(e) ;
            }

        }
        return pages;
    }


    public AbstractConnectorOutputWizardPage getOutputPage()  {
        try {
        	 IConfigurationElement[] outputPagesConfig =  config.getChildren("outputPage");
        	 if(outputPagesConfig.length > 0){
            return (AbstractConnectorOutputWizardPage) outputPagesConfig[0].createExecutableExtension("page");
        	 }else{
        		 return null;
        	 }
        } catch (CoreException e) {
            BonitaStudioLog.error(e) ;
        }
        return null ;
    }



    public boolean useDefaultOutputPage() {
        return useDefaultOutputPage;
    }


    public boolean hasCanFinishProvider(){
        return config.getAttribute("canFinishProvider") != null;
    }

    /**
     * You need to be sure that there is an existing CanFinishProvider.
     * @return
     */
    public boolean canFinish(ConnectorConfiguration configuration){
        String attribute = config.getAttribute("canFinishProvider");
        if(attribute != null){
            ICanFinishProvider canFinishProvider;
            try {
                canFinishProvider = (ICanFinishProvider) config.createExecutableExtension("canFinishProvider");
                return canFinishProvider.canFinish(configuration);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
                return true;
            }
        } else {
            throw new IllegalStateException("You need to check before that there is CanFinishProvider");
        }
    }


    public boolean useDefaultGeneratedPages() {
        return useDefaultGeneratedPages;
    }

    public boolean appliesTo(ConnectorDefinition definition){
        return definition.getId().equals(definitionId) 
        		&& (definitionVersion == null || definitionVersion.isEmpty() || definition.getVersion().equals(definitionVersion)) ;
    }


    public int getPriority() {
        return Integer.parseInt(config.getAttribute("priority"));
    }


    public boolean hasCanBeUsedProvider() {
        return config.getAttribute("canBeUsedProvider") != null;
    }


    public boolean canBeUsed(ConnectorDefinition definition, Connector connector) {
        try {
            ICanBeUsedProvider provider = (ICanBeUsedProvider) config.createExecutableExtension("canBeUsedProvider") ;
            return provider.canBeUsed(definition, connector) ;
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return true;
        }
    }

}