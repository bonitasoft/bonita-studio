/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Maps.transformValues;
import static com.google.common.collect.Maps.uniqueIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.InvalidBusinessArchiveFormatException;
import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;
import org.bonitasoft.engine.bpm.process.InvalidProcessDefinitionException;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.builder.RCPEngineDefintionBuilderProvider;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.MultiStatus;

import com.google.common.collect.Maps;

/**
 * @author Romain Bioteau
 */
public class BarExporter {

    private static final String PROCESS_DEFINITION_EXPORTER_ID = "org.bonitasoft.studio.engine.processDefinitionExporter";
    private static final String BAR_RESOURCE_PROVIDERS_EXTENSION_POINT = "org.bonitasoft.studio.common.barResourcesProvider";
    private static final String BAR_APPLICATION_RESOURCE_PROVIDERS_EXTENSION_POINT = "org.bonitasoft.studio.exporter.barApplicationResourceProvider";

    private static BarExporter INSTANCE;
    private final ExtensionContextInjectionFactory extensionContextInjectionFactory;

    private BarExporter() {
        extensionContextInjectionFactory = new ExtensionContextInjectionFactory();
    }

    public static BarExporter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BarExporter();
        }
        return INSTANCE;
    }

    public BusinessArchive createBusinessArchive(final AbstractProcess process, final Configuration configuration)
            throws BarCreationException {

        checkArgument(configuration != null);
        BonitaStudioLog.info("Building bar for process " + process.getName() + " (" + process.getVersion() + " )...",
                EnginePlugin.PLUGIN_ID);
        final DesignProcessDefinitionBuilder procBuilder = getProcessDefinitionBuilder();
        DesignProcessDefinition def;
        try {
            def = procBuilder.createDefinition(process);
        } catch (final InvalidProcessDefinitionException e1) {
            throw new BarCreationException(String.format("Failed to create process definition for %s (%s)\n\n%s",
                    process.getName(), process.getVersion(), e1.getMessage()), e1);
        }

        if (def == null) {
            throw new BarCreationException(Messages.cantDeployEmptyPool);
        }

        final BusinessArchiveBuilder builder = new BusinessArchiveBuilder().createNewBusinessArchive();
        builder.setProcessDefinition(def).setParameters(getParameters(configuration))
                .setActorMapping(getActorMapping(configuration));

        MultiStatus resourceConfigurationStatus = new MultiStatus(EnginePlugin.PLUGIN_ID, 0, null, null);
        for (final BARResourcesProvider resourceProvider : getBARResourcesProvider()) {
            try {
                resourceConfigurationStatus.addAll(resourceProvider.addResourcesForConfiguration(builder, process, configuration));
            } catch (final Exception e) {
                throw new BarCreationException("Failed to add Process resources from configuration.", e);
            }
        }
        if(!resourceConfigurationStatus.isOK()) {
            throw new BarCreationException("Failed to add Process resources from configuration.", resourceConfigurationStatus);
        }

        //Add forms resources
        final BARResourcesProvider provider = getBARApplicationResourcesProvider();
        if (provider != null) {
            try {
                provider.addResourcesForConfiguration(builder, process, configuration);
            } catch (final Exception e) {
                throw new BarCreationException("Failed to add Application resources from configuration.", e);
            }
        }

        try {
            final BusinessArchive archive = builder.done();
            BonitaStudioLog.info("Build complete for process " + process.getName() + " (" + process.getVersion() + " ).",
                    EnginePlugin.PLUGIN_ID);
            return archive;
        } catch (final InvalidBusinessArchiveFormatException e) {
            throw new BarCreationException("Failed to create Business Archive.", e);
        }
    }

    protected org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping getActorMapping(Configuration configuration) {
        final ActorMappingsType mappings = configuration.getActorMappings();
        final org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping actorMapping = new org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping();
        if (mappings != null) {
            for (final ActorMapping mapping : mappings.getActorMapping()) {
                actorMapping.addActor(toEngineActor(mapping));
            }
        }
        return actorMapping;
    }

    private org.bonitasoft.engine.bpm.bar.actorMapping.Actor toEngineActor(ActorMapping mapping) {
        final org.bonitasoft.engine.bpm.bar.actorMapping.Actor actor = new org.bonitasoft.engine.bpm.bar.actorMapping.Actor(
                mapping.getName());
        actor.addUsers(mapping.getUsers().getUser());
        actor.addGroups(mapping.getGroups().getGroup());
        actor.addRoles(mapping.getRoles().getRole());
        for (final MembershipType membership : mapping.getMemberships().getMembership()) {
            actor.addMembership(membership.getGroup(), membership.getRole());
        }
        return actor;
    }

    public DesignProcessDefinitionBuilder getProcessDefinitionBuilder() {
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(PROCESS_DEFINITION_EXPORTER_ID)) {
            try {
                return (DesignProcessDefinitionBuilder) element.createExecutableExtension("class");
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }
        return new DesignProcessDefinitionBuilder(new RCPEngineDefintionBuilderProvider(),
                new ModelSearch(() -> RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class)
                        .getAllProcesses()));
    }

    /**
     * @param process The pool to export
     * @param configurationId
     * @param excludedObject elements of the process not exported in process definition
     */
    public BusinessArchive createBusinessArchive(final AbstractProcess process, final String configurationId)
            throws BarCreationException {
        return createBusinessArchive(process, getConfiguration(process, configurationId));
    }

    public Configuration getConfiguration(final AbstractProcess process, String configurationId) {
        Configuration configuration = null;
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        if (configurationId == null) {
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        }
        if (configurationId.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)) {
            final String id = ModelHelper.getEObjectID(process);
            IRepositoryFileStore file = processConfStore.getChild(id + ".conf", true);
            if (file == null) {
                file = processConfStore.createRepositoryFileStore(id + ".conf");
                configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
                configuration.setName(configurationId);
                configuration.setVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
                file.save(configuration);
            }
            try {
                configuration = (Configuration) file.getContent();
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to read process configuration", e);
            }
        } else {
            for (final Configuration conf : process.getConfigurations()) {
                if (configurationId.equals(conf.getName())) {
                    configuration = conf;
                }
            }
        }

        //TODO Remove configuration sync when all bar artifacts will be live update friendly (connectors, dependencies, parameters...) ? 
        if (configuration == null) {
            configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
            configuration.setName(configurationId);
            configuration.setVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
        }
        //Synchronize configuration with definition
        new ConfigurationSynchronizer(process, configuration).synchronize();
        return configuration;
    }

    protected Map<String, String> getParameters(final Configuration configuration) {
        return Maps.newHashMap(
                transformValues(uniqueIndex(configuration.getParameters(), Parameter::getName), Parameter::getValue));
    }


    protected BARResourcesProvider getBARApplicationResourcesProvider() {
        BARResourcesProvider result = null;
        int maxPriority = -1;
        final IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(
                        BAR_APPLICATION_RESOURCE_PROVIDERS_EXTENSION_POINT);
        for (final IConfigurationElement extension : extensions) {

            try {
                final int p = Integer.parseInt(extension.getAttribute("priority"));
                if (p >= maxPriority) {

                    result = (BARResourcesProvider) extension.createExecutableExtension("providerClass");
                    maxPriority = p;
                }
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
        return result;
    }

    public List<BARResourcesProvider> getBARResourcesProvider() {
        final List<BARResourcesProvider> res = new ArrayList<>();
        final IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(
                        BAR_RESOURCE_PROVIDERS_EXTENSION_POINT);
        for (final IConfigurationElement extension : extensions) {
            try {
                res.add(extensionContextInjectionFactory.make(extension, "providerClass", BARResourcesProvider.class));
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
            }
        }
        return res;
    }



}
