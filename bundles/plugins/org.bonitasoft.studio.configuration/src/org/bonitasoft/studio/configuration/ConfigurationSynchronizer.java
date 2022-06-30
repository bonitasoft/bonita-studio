/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.ui.wizard.page.JavaDependenciesConfigurationWizardPage;
import org.bonitasoft.studio.diagram.custom.repository.Synchronizer;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.studio.model.configuration.util.ConfigurationResourceFactoryImpl;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * @author Romain Bioteau
 */
public class ConfigurationSynchronizer implements Synchronizer {

    private AbstractProcess process;
    private Configuration configuration;
    private AdapterFactoryEditingDomain editingDomain;
    private ComposedAdapterFactory adapterFactory;
    private boolean synchronizeLocalConfiguraiton;
    private final ActiveOrganizationProvider activeOrganizationProvider = new ActiveOrganizationProvider();
    private static ArrayList<IConfigurationSynchronizer> synchronizers;
    private static ArrayList<IProcessConfigurationWizardPage> wizardPages;
    private static final String CONFIGURATION_WIZARD_PAGE_ID = "org.bonitasoft.studio.configuration.wizardPage";
    private static final String CLASS_ATTRIBUTE = "class";

    public ConfigurationSynchronizer(final AbstractProcess process, final Configuration configuration) {
        this();
        this.process = process;
        this.configuration = configuration;
        synchronizeLocalConfiguraiton = ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON
                .equals(configuration.getName()) || configuration.getName() == null;
        editingDomain = (AdapterFactoryEditingDomain) TransactionUtil.getEditingDomain(process);
    }

    public ConfigurationSynchronizer() {
        initializaSynchronizers();
        initializaWizardPages();
    }

    static void initializaSynchronizers() {
        if (synchronizers == null) {
            synchronizers = new ArrayList<>();
            for (final IConfigurationElement elem : BonitaStudioExtensionRegistryManager.getInstance()
                    .getConfigurationElements("org.bonitasoft.studio.configuration.synchronizer")) {
                try {
                    synchronizers.add((IConfigurationSynchronizer) elem.createExecutableExtension(CLASS_ATTRIBUTE));
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    static void initializaWizardPages() {
        if (wizardPages == null) {
            wizardPages = new ArrayList<>();
            final IConfigurationElement[] elems = BonitaStudioExtensionRegistryManager.getInstance()
                    .getConfigurationElements(CONFIGURATION_WIZARD_PAGE_ID);
            for (final IConfigurationElement e : elems) {
                try {
                    final IProcessConfigurationWizardPage page = (IProcessConfigurationWizardPage) e
                            .createExecutableExtension(CLASS_ATTRIBUTE);
                    wizardPages.add(page);
                } catch (final Exception e1) {
                    BonitaStudioLog.error(e1);
                }
            }
            wizardPages.add(new JavaDependenciesConfigurationWizardPage());
        }
    }

    protected void initializeEditingDomain() {
        // Create an adapter factory that yields item providers.
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory());
        adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

        // command stack that will notify this editor as commands are executed
        final BasicCommandStack commandStack = new BasicCommandStack();

        // Create the editing domain with our adapterFactory and command stack.
        editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<>());
        editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("conf",
                new ConfigurationResourceFactoryImpl());
    }

    public void synchronize() {
        synchronize(AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Override
    public void synchronize(Pool process, Configuration configuration) {
        this.process = process;
        this.configuration = configuration;
        synchronizeLocalConfiguraiton = ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON
                .equals(configuration.getName()) || configuration.getName() == null;
        editingDomain = (AdapterFactoryEditingDomain) TransactionUtil.getEditingDomain(process);
        synchronize();
    }

    public void synchronize(final IProgressMonitor monitor) {
        monitor.beginTask(Messages.synchronizingConfiguration, IProgressMonitor.UNKNOWN);
        boolean dispose = false;
        if (editingDomain == null) {
            initializeEditingDomain();
            dispose = true;
        }
        CompoundCommand cc = new CompoundCommand();
        boolean exists = false;
        for (final Configuration c : process.getConfigurations()) {
            if (c.equals(configuration)) {
                exists = true;
            }
        }

        if (configuration.getName() == null) {
            cc.append(SetCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__NAME,
                    ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON));
        }

        if (configuration.getVersion() == null
                || !ModelVersion.CURRENT_DIAGRAM_VERSION.equals(configuration.getVersion())) {
            cc.append(SetCommand.create(editingDomain, configuration,
                    ConfigurationPackage.Literals.CONFIGURATION__VERSION, ModelVersion.CURRENT_DIAGRAM_VERSION));
        }

        synchronizeFragmentContainers(cc);

        editingDomain.getCommandStack().execute(cc);
        cc = new CompoundCommand();

        if (!exists && !synchronizeLocalConfiguraiton) {
            cc.append(AddCommand.create(editingDomain, process,
                    ProcessPackage.Literals.ABSTRACT_PROCESS__CONFIGURATIONS, configuration));
        }

        for (final IConfigurationSynchronizer synchronier : synchronizers) {
            synchronier.synchronize(configuration, process, cc, editingDomain);
        }

        if (configuration.getUsername() == null || configuration.getUsername().isEmpty()) {
            final String defaultUserName = activeOrganizationProvider.getDefaultUser();
            cc.append(SetCommand.create(editingDomain, configuration,
                    ConfigurationPackage.Literals.CONFIGURATION__USERNAME, defaultUserName));
        }
        if (configuration.getPassword() == null || configuration.getPassword().isEmpty()) {
            final String defaultPassword = activeOrganizationProvider.getDefaultPassword();
            cc.append(SetCommand.create(editingDomain, configuration,
                    ConfigurationPackage.Literals.CONFIGURATION__PASSWORD, defaultPassword));
        }

        editingDomain.getCommandStack().execute(cc);

        if (dispose) {
            adapterFactory.dispose();
        }

    }

    private void synchronizeFragmentContainers(final CompoundCommand cc) {
        for (final IConfigurationSynchronizer synchronier : synchronizers) {
            final String containerId = synchronier.getFragmentContainerId();
            final EStructuralFeature dependencyKind = synchronier.getDependencyKind();
            if (containerId != null && dependencyKind != null) {
                synchronizeFragmentContainer(dependencyKind, containerId, cc);
            }
        }
        synchronizeFragmentContainer(ConfigurationPackage.Literals.CONFIGURATION__APPLICATION_DEPENDENCIES,
                FragmentTypes.OTHER, cc);
        synchronizeFragmentContainer(ConfigurationPackage.Literals.CONFIGURATION__PROCESS_DEPENDENCIES,
                FragmentTypes.OTHER, cc);
    }

    protected void synchronizeFragmentContainer(final EStructuralFeature dependencyKind, final String containerId,
            final CompoundCommand cc) {
        boolean containerExists = false;
        final List<FragmentContainer> containers = (List<FragmentContainer>) configuration.eGet(dependencyKind);
        for (final FragmentContainer fc : containers) {
            if (fc.getId().equals(containerId)) {
                containerExists = true;
                break;
            }
        }
        if (!containerExists) {
            final FragmentContainer fc = ConfigurationFactory.eINSTANCE.createFragmentContainer();
            fc.setId(containerId);
            cc.append(AddCommand.create(editingDomain, configuration, dependencyKind, fc));
        }
    }

    /**
     * @param configuration
     * @return true if the configuration is runnable
     */
    public boolean isConfigurationValid() {
        for (final IProcessConfigurationWizardPage page : wizardPages) {
            if (page.isConfigurationPageValid(configuration) != null) {
                return false;
            }
        }
        return true;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
