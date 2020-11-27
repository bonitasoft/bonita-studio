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
package org.bonitasoft.studio.configuration;

import static com.google.common.collect.Lists.newArrayList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.connector.model.implementation.AbstractConnectorImplRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.kpi.DatabaseKPIBinding;
import org.bonitasoft.studio.model.kpi.KpiPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractConnectorConfigurationSynchronizer implements IConfigurationSynchronizer {

    public static final String DB_CONNECTOR_FOR_KPI_ID = "database-jdbc";
    public static final String DB_CONNECTOR_VERSION = "1.0.0";

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer#synchronize(org.eclipse.emf.common.command.CompoundCommand)
     */
    @Override
    public void synchronize(final Configuration configuration, final AbstractProcess process, final CompoundCommand cc, final EditingDomain editingDomain) {
        addNewConnectorDefinition(configuration, process, cc, editingDomain);
        removeConnectorDefinitions(configuration, process, cc, editingDomain);
    }

    protected void addNewConnectorDefinition(final Configuration configuration, final AbstractProcess process, final CompoundCommand cc,
            final EditingDomain editingDomain) {
        final List<Connector> connectors = getExistingConnectors(process);
        final List<Pair<String, String>> definitions = getDefinitions(connectors);
        for (final Pair<String, String> definition : definitions) {
            final String defId = definition.getFirst();
            final String defVersion = definition.getSecond();
            boolean exists = false;
            for (final DefinitionMapping association : configuration.getDefinitionMappings()) {
                if (association.getType().equals(getFragmentContainerId()) && association.getDefinitionId().equals(defId)
                        && association.getDefinitionVersion().equals(defVersion)) {
                    exists = true;
                    updateAssociation(configuration, association, cc, editingDomain);
                    break;
                }
            }
            if (!exists) {
                final DefinitionMapping newAssociation = ConfigurationFactory.eINSTANCE.createDefinitionMapping();
                newAssociation.setDefinitionId(defId);
                newAssociation.setDefinitionVersion(defVersion);
                newAssociation.setType(getFragmentContainerId());
                editingDomain.getCommandStack().execute(
                        AddCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__DEFINITION_MAPPINGS, newAssociation));
                updateAssociation(configuration, newAssociation, cc, editingDomain);
            }
        }
    }

    private List<Pair<String, String>> getDefinitions(final List<Connector> connectors) {
        final List<Pair<String, String>> result = new ArrayList<Pair<String, String>>();
        for (final Connector c : connectors) {
            final Pair<String, String> def = new Pair<String, String>(c.getDefinitionId(), c.getDefinitionVersion());
            if (!result.contains(def)) {
                result.add(def);
            }
        }
        return result;
    }

    protected abstract List<Connector> getExistingConnectors(AbstractProcess process);

    protected void updateAssociation(final Configuration configuration, final DefinitionMapping association, final CompoundCommand cc,
            final EditingDomain editingDomain) {
        final List<ConnectorImplementation> implementations = getAllImplementations(association.getDefinitionId(), association.getDefinitionVersion());
        ConnectorImplementation implementation = null;
        String implID = null;
        String implVersion = null;
        if (association.getImplementationId() != null && association.getImplementationVersion() != null) {//Check if implementation still exists in store
            if (!implementations.isEmpty()) {
                for (final ConnectorImplementation impl : implementations) {
                    if (impl.getImplementationId().equals(association.getImplementationId())
                            && impl.getImplementationVersion().equals(association.getImplementationVersion())) {
                        implementation = impl; //Implementation exists in repository
                        implID = implementation.getImplementationId();
                        implVersion = implementation.getImplementationVersion();
                        break;
                    }
                }
            }

        }

        if (implementation == null && !implementations.isEmpty()) {
            implID = implementations.get(0).getImplementationId();
            implVersion = implementations.get(0).getImplementationVersion();
            for (final ConnectorImplementation impl : implementations) { //Search the latest version
                if (impl.getImplementationVersion().compareTo(implVersion) >= 0) {
                    implVersion = impl.getImplementationVersion();
                    implID = impl.getImplementationId();
                    implementation = impl;
                }
            }
        }
        editingDomain.getCommandStack().execute(
                SetCommand.create(editingDomain, association, ConfigurationPackage.Literals.DEFINITION_MAPPING__IMPLEMENTATION_ID, implID));
        editingDomain.getCommandStack().execute(
                SetCommand.create(editingDomain, association, ConfigurationPackage.Literals.DEFINITION_MAPPING__IMPLEMENTATION_VERSION, implVersion));
        if (implementation != null) {
            updateConnectorDependencies(configuration, association, implementation, cc, editingDomain, false);
            importImplementationDependencies(implementation);
        }
    }

    protected abstract List<ConnectorImplementation> getAllImplementations(String defId, String defVersion);

    protected void importImplementationDependencies(final ConnectorImplementation implementation) {
        if (!implementation.getJarDependencies().getJarDependency().isEmpty()) {
            final DefinitionResourceProvider resourceProvider = getDefinitionResourceProvider();
            final DependencyRepositoryStore depStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
            for (final String jarName : implementation.getJarDependencies().getJarDependency()) {
                if (depStore.getChild(jarName, true) == null) {
                    final InputStream is = resourceProvider.getDependencyInputStream(jarName);
                    if (is != null) {
                        depStore.importInputStream(jarName, is);
                    }
                }
            }
        }
    }

    protected abstract DefinitionResourceProvider getDefinitionResourceProvider();

    public void updateConnectorDependencies(final Configuration configuration, final DefinitionMapping association,
            final ConnectorImplementation implementation, final CompoundCommand cc, final EditingDomain editingDomain, final boolean forceDriver) {
        Assert.isNotNull(implementation);
        final String id = implementation.getImplementationId();
        final String version = implementation.getImplementationVersion();
        final String implementationId = NamingUtils.toConnectorImplementationFilename(id, version, false);

        final FragmentContainer container = getContainer(configuration);
        Assert.isNotNull(container);

        for (final FragmentContainer fc : container.getChildren()) {
            if (fc.getId().equals(implementationId)) {
                final Set<Fragment> toRemove = new HashSet<Fragment>();
                for (final Fragment depFragment : fc.getFragments()) {
                    if (!jarDependencies(implementation).contains(depFragment.getValue())
                            && !implementationId.equals(depFragment.getValue())) {
                        toRemove.add(depFragment);
                    }
                }
                if (!toRemove.isEmpty()) {
                    editingDomain.getCommandStack().execute(
                            RemoveCommand.create(editingDomain, fc, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, toRemove));
                }
            }
        }

        boolean exists = false;
        for (final FragmentContainer fc : container.getChildren()) {
            if (fc.getId().equals(implementationId)) {
                exists = true;
                updateJarDependencies(fc, implementation, editingDomain, cc, forceDriver);
            }
        }
        if (!exists) {
            final FragmentContainer connectorContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
            connectorContainer.setId(implementationId);
            editingDomain.getCommandStack().execute(
                    AddCommand.create(editingDomain, container, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__CHILDREN, connectorContainer));
            updateJarDependencies(connectorContainer, implementation, editingDomain, cc, forceDriver);
        }

    }

    private FragmentContainer getContainer(final Configuration configuration) {
        for (final FragmentContainer container : configuration.getProcessDependencies()) {
            if (container.getId().equals(getFragmentContainerId())) {
                return container;
            }
        }
        return null;
    }

    protected void updateJarDependencies(final FragmentContainer connectorContainer, final ConnectorImplementation implementation,
            final EditingDomain editingDomain, final CompoundCommand cc, final boolean forceDriver) {
        for (final String jar : jarDependencies(implementation)) {
            boolean exists = false;
            for (final Fragment dep : connectorContainer.getFragments()) {
                if (dep.getValue().equals(jar)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                final Fragment depFragment = ConfigurationFactory.eINSTANCE.createFragment();
                depFragment.setExported(true);
                depFragment.setKey(implementation.getImplementationId() + " -- " + implementation.getImplementationVersion());
                depFragment.setValue(jar);
                depFragment.setType(getFragmentContainerId());
                editingDomain.getCommandStack().execute(
                        AddCommand.create(editingDomain, connectorContainer, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, depFragment));
            }
        }
    }

    protected List<String> jarDependencies(final ConnectorImplementation implementation) {
        final AbstractConnectorImplRepositoryStore<EMFFileStore> store = getImplementationStore();
        final IRepositoryFileStore fileStore = store.getImplementationFileStore(implementation.getImplementationId(),
                implementation.getImplementationVersion());
        final List<String> dependencies = implementation.getJarDependencies() == null ? new ArrayList<String>() : newArrayList(implementation
                .getJarDependencies()
                .getJarDependency());
        if (fileStore != null && fileStore.canBeShared()) {
            final String implementationJar = NamingUtils.toConnectorImplementationJarName(implementation);
            if (!dependencies.contains(implementationJar)) {
                dependencies.add(implementationJar);
            }

        }
        return dependencies;
    }

    protected abstract AbstractConnectorImplRepositoryStore<EMFFileStore> getImplementationStore();

    private void removeConnectorDefinitions(final Configuration configuration, final AbstractProcess process, final CompoundCommand cc,
            final EditingDomain editingDomain) {
        final List<Connector> connectors = getExistingConnectors(process);
        final List<DatabaseKPIBinding> kpis = ModelHelper.getAllItemsOfType(process, KpiPackage.Literals.DATABASE_KPI_BINDING);
        final Set<DefinitionMapping> toRemove = new HashSet<DefinitionMapping>();
        for (final DefinitionMapping association : configuration.getDefinitionMappings()) {
            if (getFragmentContainerId().equals(association.getType())) {
                final String defId = association.getDefinitionId();
                final String defVersion = association.getDefinitionVersion();
                boolean exists = false;
                for (final Connector c : connectors) {
                    final String cDef = c.getDefinitionId();
                    final String cVer = c.getDefinitionVersion();
                    if (defId.equals(cDef) && defVersion.equals(cVer)) {
                        exists = true;
                        break;
                    }
                }
                if (!kpis.isEmpty() && defId.equals(DB_CONNECTOR_FOR_KPI_ID) && defVersion.equals(DB_CONNECTOR_VERSION)) {
                    exists = true;
                }
                if (!exists) {
                    toRemove.add(association);
                    removeConnectorDependencies(configuration,
                            NamingUtils.toConnectorImplementationFilename(association.getImplementationId(), association.getImplementationVersion(), false),
                            cc, editingDomain);
                }

            }
        }
        if (!toRemove.isEmpty()) {
            editingDomain.getCommandStack().execute(
                    RemoveCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__DEFINITION_MAPPINGS, toRemove));
        }
        final FragmentContainer container = getContainer(configuration);
        FragmentContainer toRemove2 = null;
        for (final FragmentContainer fc : container.getChildren()) {
            boolean exists = false;
            for (final DefinitionMapping association : configuration.getDefinitionMappings()) {
                if (fc.getId().equals(
                        NamingUtils.toConnectorImplementationFilename(association.getImplementationId(), association.getImplementationVersion(), false))) {
                    exists = true;
                }
            }
            if (!exists) {
                toRemove2 = fc;
                if (toRemove2 != null) {
                    cc.append(RemoveCommand.create(editingDomain, container, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__CHILDREN, toRemove2));
                }
            }

        }

    }

    public void removeConnectorDependencies(final Configuration configuration, final String implementationId, final CompoundCommand cc,
            final EditingDomain editingDomain) {
        final FragmentContainer container = getContainer(configuration);
        Assert.isNotNull(container);

        FragmentContainer toRemove = null;
        for (final FragmentContainer fc : container.getChildren()) {
            if (fc.getId().equals(implementationId)) {
                toRemove = fc;
            }
        }
        if (toRemove != null) {
            editingDomain.getCommandStack().execute(
                    RemoveCommand.create(editingDomain, container, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__CHILDREN, toRemove));
        }
    }

    @Override
    public EStructuralFeature getDependencyKind() {
        return PROCESS_DEPENDENCY;
    }

}
