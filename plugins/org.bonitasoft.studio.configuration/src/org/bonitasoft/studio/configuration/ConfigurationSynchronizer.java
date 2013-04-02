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
package org.bonitasoft.studio.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.ui.wizard.page.ApplicationDependenciesConfigurationWizardPage;
import org.bonitasoft.studio.configuration.ui.wizard.page.ProcessDependenciesConfigurationWizardPage;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.studio.model.configuration.util.ConfigurationResourceFactoryImpl;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * @author Romain Bioteau
 *
 */
public class ConfigurationSynchronizer {

    private final AbstractProcess process;
    private final Configuration configuration;
    private AdapterFactoryEditingDomain editingDomain;
    private ComposedAdapterFactory adapterFactory;
    private final boolean synchronizeLocalConfiguraiton;
    private static ArrayList<IConfigurationSynchronizer> synchronizers;
    private static ArrayList<IProcessConfigurationWizardPage> wizardPages;
    private static final String CONFIGURATION_WIZARD_PAGE_ID = "org.bonitasoft.studio.configuration.wizardPage";
    private static final String CLASS_ATTRIBUTE = "class";

    public ConfigurationSynchronizer(AbstractProcess process, Configuration configuration){
        this.process = process ;
        this.configuration = configuration ;
        synchronizeLocalConfiguraiton= ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON.equals(configuration.getName()) || configuration.getName() == null ;
        editingDomain = (AdapterFactoryEditingDomain) TransactionUtil.getEditingDomain(process) ;
        initializaSynchronizers();
        initializaWizardPages();
    }

    protected void initializaSynchronizers() {
        if(synchronizers == null){
            synchronizers = new ArrayList<IConfigurationSynchronizer>() ;
            for(IConfigurationElement elem :  BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.configuration.synchronizer")){
                try {
                    synchronizers.add((IConfigurationSynchronizer) elem.createExecutableExtension("class"));
                } catch (CoreException e) {
                    BonitaStudioLog.error(e) ;
                }
            }
        }
    }

    protected void initializaWizardPages() {
        if(wizardPages == null){
            wizardPages = new ArrayList<IProcessConfigurationWizardPage>();
            IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(CONFIGURATION_WIZARD_PAGE_ID);
            for(IConfigurationElement e :elems){
                try {
                    IProcessConfigurationWizardPage page =  (IProcessConfigurationWizardPage) e.createExecutableExtension(CLASS_ATTRIBUTE) ;
                    wizardPages.add(page);
                } catch (Exception e1){
                    BonitaStudioLog.error(e1) ;
                }
            }
            wizardPages.add(new ProcessDependenciesConfigurationWizardPage()) ;
            wizardPages.add(new ApplicationDependenciesConfigurationWizardPage()) ;
        }
    }

    protected void initializeEditingDomain() {
        // Create an adapter factory that yields item providers.
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory()) ;
        adapterFactory.addAdapterFactory(new ProcessAdapterFactory()) ;

        // command stack that will notify this editor as commands are executed
        BasicCommandStack commandStack = new BasicCommandStack();

        // Create the editing domain with our adapterFactory and command stack.
        editingDomain = new AdapterFactoryEditingDomain(adapterFactory,commandStack, new HashMap<Resource, Boolean>());
        editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("conf", new ConfigurationResourceFactoryImpl()) ;
    }

    public void synchronize(){
        boolean dispose = false ;
        if(editingDomain == null){
            initializeEditingDomain() ;
            dispose = true ;
        }
        CompoundCommand cc = new CompoundCommand();
        boolean exists = false ;
        for(Configuration c : process.getConfigurations()){
            if(c.equals(configuration)){
                exists = true ;
            }
        }

        if(configuration.getName() == null){
            cc.append(SetCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__NAME, ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)) ;
        }
        
        if(configuration.getVersion() == null || !ProductVersion.CURRENT_VERSION.equals(configuration.getVersion())){
        	 cc.append(SetCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__VERSION, ProductVersion.CURRENT_VERSION)) ;
        }

        synchronizeFragmentContainers(cc) ;

        editingDomain.getCommandStack().execute(cc) ;
        cc = new CompoundCommand();

        if(!exists && !synchronizeLocalConfiguraiton){
            cc.append(AddCommand.create(editingDomain, process, ProcessPackage.Literals.ABSTRACT_PROCESS__CONFIGURATIONS, configuration)) ;
        }

        for(IConfigurationSynchronizer synchronier :synchronizers){
            synchronier.synchronize(configuration,process,cc,editingDomain) ;
        }

        if(configuration.getUsername() == null || configuration.getUsername().isEmpty()){
            String defaultUserName = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.USER_NAME) ;
            cc.append(SetCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__USERNAME, defaultUserName)) ;
        }
        if(configuration.getPassword() == null || configuration.getPassword().isEmpty()){
            String defaultPassword = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(BonitaPreferenceConstants.USER_PASSWORD) ;
            cc.append(SetCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__PASSWORD, defaultPassword)) ;
        }

        editingDomain.getCommandStack().execute(cc) ;

        if(dispose){
            adapterFactory.dispose() ;
        }

    }


    private void synchronizeFragmentContainers(CompoundCommand cc) {
        for(IConfigurationSynchronizer synchronier :synchronizers){
            String containerId =  synchronier.getFragmentContainerId() ;
            EStructuralFeature dependencyKind = synchronier.getDependencyKind()  ;
            if(containerId != null && dependencyKind != null){
                synchronizeFragmentContainer(dependencyKind,containerId,cc);
            }
        }
        synchronizeFragmentContainer(ConfigurationPackage.Literals.CONFIGURATION__APPLICATION_DEPENDENCIES,FragmentTypes.OTHER, cc) ;
        synchronizeFragmentContainer(ConfigurationPackage.Literals.CONFIGURATION__PROCESS_DEPENDENCIES,FragmentTypes.OTHER, cc) ;
    }

    protected void synchronizeFragmentContainer(EStructuralFeature dependencyKind,String containerId,CompoundCommand cc) {
        boolean containerExists = false ;
        List<FragmentContainer> containers = (List<FragmentContainer>) configuration.eGet(dependencyKind) ;
        for(FragmentContainer fc : containers){
            if(fc.getId().equals(containerId)){
                containerExists = true ;
                break ;
            }
        }
        if(!containerExists){
            FragmentContainer fc = ConfigurationFactory.eINSTANCE.createFragmentContainer() ;
            fc.setId(containerId) ;
            cc.append(AddCommand.create(editingDomain, configuration, dependencyKind,fc)) ;
        }
    }


    /**
     * 
     * @param configuration
     * @return true if the configuration is runnable
     */
    public boolean isConfigurationValid(){
        for(IProcessConfigurationWizardPage page : wizardPages){
            if(page.isConfigurationPageValid(configuration) != null){
                return false;
            }
        }
        return true;
    }


    public Configuration getConfiguration() {
        return configuration;
    }

}
