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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.editingdomain.CustomDiagramEditingDomainFactory;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.ExtensionContextInjectionFactory;
import org.bonitasoft.studio.common.gmf.tools.CopyToImageUtilEx;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

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

    public BusinessArchive createBusinessArchive(final AbstractProcess process, final Configuration configuration, final Set<EObject> excludedObject)
            throws Exception {
        return createBusinessArchive(process, configuration, excludedObject, true);
    }

    public BusinessArchive createBusinessArchive(final AbstractProcess process, final Configuration configuration, final Set<EObject> excludedObject,
            final boolean addProcessImage) throws Exception {

        checkArgument(configuration != null);
        BonitaStudioLog.info("Building bar for process " + process.getName() + " (" + process.getVersion() + " )...", EnginePlugin.PLUGIN_ID);
        final DesignProcessDefinitionBuilder procBuilder = getProcessDefinitionBuilder();
        procBuilder.seteObjectNotExported(excludedObject);
        final DesignProcessDefinition def = procBuilder.createDefinition(process);

        if (def == null) {
            throw new Exception(Messages.cantDeployEmptyPool);
        }

        final BusinessArchiveBuilder builder = new BusinessArchiveBuilder().createNewBusinessArchive();
        builder.setProcessDefinition(def);

        builder.setParameters(getParameterMapFromConfiguration(configuration));
        final byte[] content = new ActorMappingExporter().toByteArray(configuration);
        if (content != null) {
            builder.setActorMapping(content);
        }

        for (final BARResourcesProvider resourceProvider : getBARResourcesProvider()) {
            resourceProvider.addResourcesForConfiguration(builder, process, configuration, excludedObject);
        }

        //Add forms resources
        final BARResourcesProvider provider = getBARApplicationResourcesProvider();
        if (provider != null) {
            provider.addResourcesForConfiguration(builder, process, configuration, excludedObject);
        }

        if (!(process instanceof SubProcessEvent)) {
            if (addProcessImage) {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            addProcessImage(builder, process);
                        } catch (final CoreException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                });
            }
        }

        final BusinessArchive archive = builder.done();
        BonitaStudioLog.info("Build complete for process " + process.getName() + " (" + process.getVersion() + " ).", EnginePlugin.PLUGIN_ID);
        return archive;
    }

    public DesignProcessDefinitionBuilder getProcessDefinitionBuilder() {
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(PROCESS_DEFINITION_EXPORTER_ID)) {
            try {
                return (DesignProcessDefinitionBuilder) element.createExecutableExtension("class");
            } catch (final CoreException e) {
                BonitaStudioLog.error(e, EnginePlugin.PLUGIN_ID);
            }
        }
        return new DesignProcessDefinitionBuilder();
    }

    /**
     * @param process The pool to export
     * @param configurationId
     * @param excludedObject elements of the process not exported in process definition
     */
    public BusinessArchive createBusinessArchive(final AbstractProcess process, final String configurationId, final Set<EObject> excludedObject)
            throws Exception {
        return createBusinessArchive(process, getConfiguration(process, configurationId), excludedObject);
    }

    public Configuration getConfiguration(final AbstractProcess process, String configurationId) {
        Configuration configuration = null;
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        if (configurationId == null) {
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore().getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        }
        if (configurationId.equals(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON)) {
            final String id = ModelHelper.getEObjectID(process);
            IRepositoryFileStore file = processConfStore.getChild(id + ".conf");
            if (file == null) {
                file = processConfStore.createRepositoryFileStore(id + ".conf");
                configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
                configuration.setName(configurationId);
                configuration.setVersion(ModelVersion.CURRENT_VERSION);
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
        if (configuration == null) {
            configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
            configuration.setName(configurationId);
            configuration.setVersion(ModelVersion.CURRENT_VERSION);
        }
        //Synchronize configuration with definition
        new ConfigurationSynchronizer(process, configuration).synchronize();
        return configuration;
    }

    public Map<String, String> getParameterMapFromConfiguration(final Configuration configuration) {
        final Map<String, String> result = new HashMap<String, String>();
        for (final Parameter p : configuration.getParameters()) {
            if (p.getValue() != null) {
                result.put(p.getName(), p.getValue());
            }
        }
        return result;
    }

    protected BARResourcesProvider getBARApplicationResourcesProvider() {
        BARResourcesProvider result = null;
        int maxPriority = -1;
        final IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
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
        final List<BARResourcesProvider> res = new ArrayList<BARResourcesProvider>();
        final IConfigurationElement[] extensions = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
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

    protected void addProcessImage(final BusinessArchiveBuilder builder, final AbstractProcess process) throws CoreException {
        if (PlatformUI.isWorkbenchRunning()) {
            final String processName = process.getName() + "_" + process.getVersion();
            final String path = processName + ".png"; //$NON-NLS-1$

            try {
                Diagram diagram = ModelHelper.getDiagramFor(ModelHelper.getMainProcess(process));
                if (diagram == null) {
                    return;//DON'T ADD IMAGE, DON'T THROW EXCEPTION FOR TESTS PURPUSES
                }
                final ResourceSet resourceSet = new ResourceSetImpl();
                final TransactionalEditingDomain editingDomain = CustomDiagramEditingDomainFactory.getInstance().createEditingDomain(resourceSet);
                final Resource resource = resourceSet.createResource(diagram.eResource().getURI());
                try {
                    resource.load(resourceSet.getLoadOptions());
                } catch (final IOException e1) {
                    BonitaStudioLog.error(e1);
                }
                diagram = (Diagram) resource.getEObject(diagram.eResource().getURIFragment(diagram));
                final CopyToImageUtilEx copyToImageUtil = new CopyToImageUtilEx();
                byte[] imageBytes = null;
                try {
                    imageBytes = copyToImageUtil.copyToImageByteArray(diagram, process, ImageFileFormat.PNG, Repository.NULL_PROGRESS_MONITOR,
                            new PreferencesHint("exportToImage"), true);
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                    return;
                } finally {
                    editingDomain.dispose();
                }
                if (imageBytes != null) {
                    try {
                        builder.addExternalResource(new BarResource(path, imageBytes));
                    } catch (final Exception e) {
                        BonitaStudioLog.log("Process image file generation has failed"); //$NON-NLS-1$
                    }
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
    }

}
