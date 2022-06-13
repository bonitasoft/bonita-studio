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
package org.bonitasoft.studio.identity.actors.configuration;

import java.util.HashMap;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connectors.configuration.ConnectorConfigurationWizardPage;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.util.ConfigurationAdapterFactory;
import org.bonitasoft.studio.model.configuration.util.ConfigurationResourceFactoryImpl;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ActorFilterConfigurationWizardPage extends ConnectorConfigurationWizardPage
        implements IProcessConfigurationWizardPage, ISelectionChangedListener {

    private static final ActorFiltersConfigurationSynchronizer ACTOR_FILTERS_CONFIGURATION_SYNCHRONIZER = new ActorFiltersConfigurationSynchronizer();

    public ActorFilterConfigurationWizardPage() {
        super();
        setTitle(Messages.actorFilters);
        setDescription(Messages.actorFiltersConfigurationDescription);
    }

    @Override
    protected DefinitionResourceProvider getResourceProvider() {
        return DefinitionResourceProvider.getInstance(
                (IRepositoryStore<? extends IRepositoryFileStore>) getDefinitionStore(),
                IdentityPlugin.getDefault().getBundle());
    }

    @Override
    protected IImplementationRepositoryStore getImplStore() {
        return RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterImplRepositoryStore.class);
    }

    @Override
    protected IDefinitionRepositoryStore getDefinitionStore() {
        return RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterDefRepositoryStore.class);
    }

    @Override
    protected void openImplementationSelection() {
        DefinitionMapping connectorAssociation = (DefinitionMapping) ((IStructuredSelection) viewer.getSelection())
                .getFirstElement();
        SelectActorFilterImplementationWizard wizard = new SelectActorFilterImplementationWizard(connectorAssociation);
        WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
        if (dialog.open() == Dialog.OK) {
            ConnectorImplementation impl = wizard.getConnectorImplementation();
            DefinitionMapping association = (DefinitionMapping) ((IStructuredSelection) viewer.getSelection())
                    .getFirstElement();
            association.setImplementationId(impl.getImplementationId());
            association.setImplementationVersion(impl.getImplementationVersion());

            EditingDomain editingDomain = TransactionUtil.getEditingDomain(configuration);
            boolean dispose = false;
            ComposedAdapterFactory adapterFactory = null;
            if (editingDomain == null) {
                dispose = true;
                // Create an adapter factory that yields item providers.
                adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
                adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
                adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
                adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory());
                adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

                // command stack that will notify this editor as commands are executed
                BasicCommandStack commandStack = new BasicCommandStack();

                // Create the editing domain with our adapterFactory and command stack.
                editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
                        new HashMap<Resource, Boolean>());
                editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("conf",
                        new ConfigurationResourceFactoryImpl());
            }
            CompoundCommand cc = new CompoundCommand();
            ACTOR_FILTERS_CONFIGURATION_SYNCHRONIZER.updateConnectorDependencies(configuration, association, impl, cc,
                    editingDomain, false);
            editingDomain.getCommandStack().execute(cc);
            if (dispose) {
                adapterFactory.dispose();
            }
            viewer.refresh();
            getContainer().updateMessage();
        }
    }

    @Override
    protected void clearImplementation() {
        DefinitionMapping connectorAssociation = (DefinitionMapping) ((IStructuredSelection) viewer.getSelection())
                .getFirstElement();
        String implId = NamingUtils.toConnectorImplementationFilename(connectorAssociation.getImplementationId(),
                connectorAssociation.getImplementationVersion(), false);
        connectorAssociation.setImplementationId(null);
        connectorAssociation.setImplementationVersion(null);

        EditingDomain editingDomain = TransactionUtil.getEditingDomain(configuration);
        boolean dispose = false;
        ComposedAdapterFactory adapterFactory = null;
        if (editingDomain == null) {
            dispose = true;
            // Create an adapter factory that yields item providers.
            adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
            adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
            adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
            adapterFactory.addAdapterFactory(new ConfigurationAdapterFactory());
            adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

            // command stack that will notify this editor as commands are executed
            BasicCommandStack commandStack = new BasicCommandStack();

            // Create the editing domain with our adapterFactory and command stack.
            editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack,
                    new HashMap<Resource, Boolean>());
            editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("conf",
                    new ConfigurationResourceFactoryImpl());
        }
        CompoundCommand cc = new CompoundCommand();
        ACTOR_FILTERS_CONFIGURATION_SYNCHRONIZER.removeConnectorDependencies(configuration, implId, cc, editingDomain);
        editingDomain.getCommandStack().execute(cc);
        if (dispose) {
            adapterFactory.dispose();
        }

        viewer.refresh();
        getContainer().updateMessage();
    }

    @Override
    protected IStructuredContentProvider getContentProvider() {
        return new ActorFilterAssociationContentProvider();
    }

    @Override
    public String isConfigurationPageValid(Configuration conf) {
        if (viewer != null && viewer.getInput() != null) {
            for (Object element : ((IStructuredContentProvider) viewer.getContentProvider())
                    .getElements(viewer.getInput())) {
                DefinitionMapping association = (DefinitionMapping) element;
                String implementationId = association.getImplementationId();
                String implementationVersion = association.getImplementationVersion();
                if (implementationId == null || implementationVersion == null) {
                    return Messages.bind(Messages.invalidImplementationFor, association.getDefinitionId());
                }
                ConnectorImplementation impl = getImplStore().getImplementation(implementationId, implementationVersion);
                if (impl == null) {
                    return Messages.bind(Messages.implementationNotFound,
                            implementationId + " (" + implementationVersion + ")");
                }

            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage#getConfigurationImage()
     */
    @Override
    public Image getConfigurationImage() {
        return Pics.getImage(PicsConstants.filterDef);
    }

}
