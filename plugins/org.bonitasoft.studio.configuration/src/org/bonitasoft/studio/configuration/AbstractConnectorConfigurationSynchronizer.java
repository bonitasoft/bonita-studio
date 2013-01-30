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

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
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
 *
 */
public abstract class AbstractConnectorConfigurationSynchronizer implements IConfigurationSynchronizer {

    public static final String DB_CONNECTOR_FOR_KPI_ID = "database-jdbc";
    public static final String DB_CONNECTOR_VERSION = "1.0.0";


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer#synchronize(org.eclipse.emf.common.command.CompoundCommand)
     */
    @Override
    public void synchronize(Configuration configuration, AbstractProcess process, CompoundCommand cc, EditingDomain editingDomain) {
        addNewConnectorDefinition(configuration, process, cc, editingDomain) ;
        removeConnectorDefinitions(configuration, process, cc, editingDomain) ;
    }

    protected void addNewConnectorDefinition(Configuration configuration, AbstractProcess process,CompoundCommand cc, EditingDomain editingDomain) {
        List<Connector> connectors = getExistingConnectors(process);
        for(Connector connector : connectors){
            String defId = connector.getDefinitionId() ;
            String defVersion = connector.getDefinitionVersion() ;
            boolean exists = false ;
            for(DefinitionMapping association : configuration.getDefinitionMappings()){
                if(association.getType().equals(getFragmentContainerId()) && association.getDefinitionId().equals(defId) && association.getDefinitionVersion().equals(defVersion)){
                    exists = true ;
                    updateAssociation(configuration,association,cc,editingDomain);
                    break ;
                }
            }
            if(!exists){
                DefinitionMapping newAssociation = ConfigurationFactory.eINSTANCE.createDefinitionMapping() ;
                newAssociation.setDefinitionId(defId) ;
                newAssociation.setDefinitionVersion(defVersion) ;
                newAssociation.setType(getFragmentContainerId()) ;
                editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__DEFINITION_MAPPINGS, newAssociation)) ;
                updateAssociation(configuration, newAssociation, cc, editingDomain) ;
            }
        }
    }

    protected abstract List<Connector> getExistingConnectors(AbstractProcess process);

    protected void updateAssociation(Configuration configuration, DefinitionMapping association,CompoundCommand cc, EditingDomain editingDomain) {
        final List<ConnectorImplementation> implementations = getAllImplementations(association.getDefinitionId(),association.getDefinitionVersion()) ;
        ConnectorImplementation implementation = null ;
        String implID = null ;
        String implVersion = null ;
        if(association.getImplementationId() != null && association.getImplementationVersion() != null){//Check if implementation still exists in store
            if(!implementations.isEmpty()){
                for(ConnectorImplementation impl : implementations){
                    if(impl.getImplementationId().equals(association.getImplementationId())
                            && impl.getImplementationVersion().equals(association.getImplementationVersion())){
                        implementation = impl ; //Implementation exists in repository
                        implID = implementation.getImplementationId() ;
                        implVersion = implementation.getImplementationVersion() ;
                        break ;
                    }
                }
            }

        }


        if(implementation == null && !implementations.isEmpty()){
            implID = implementations.get(0).getImplementationId() ;
            implVersion = implementations.get(0).getImplementationVersion();
            for(ConnectorImplementation impl : implementations){ //Search the latest version
                if(impl.getImplementationVersion().compareTo(implVersion) >= 0){
                    implVersion = impl.getImplementationVersion() ;
                    implID = impl.getImplementationId() ;
                    implementation = impl ;
                }
            }
        }
        cc.append(SetCommand.create(editingDomain, association, ConfigurationPackage.Literals.DEFINITION_MAPPING__IMPLEMENTATION_ID, implID)) ;
        cc.append(SetCommand.create(editingDomain, association, ConfigurationPackage.Literals.DEFINITION_MAPPING__IMPLEMENTATION_VERSION, implVersion)) ;
        if(implementation != null){
            updateConnectorDependencies(configuration,association,implementation,cc,editingDomain) ;
            importImplementationDependencies(implementation) ;
        }
    }

    protected abstract List<ConnectorImplementation> getAllImplementations(String defId, String defVersion);

    protected void importImplementationDependencies(final ConnectorImplementation implementation) {
        if(!implementation.getJarDependencies().getJarDependency().isEmpty()){
            final DefinitionResourceProvider resourceProvider = getDefinitionResourceProvider();
            final DependencyRepositoryStore depStore = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
            for(String jarName : implementation.getJarDependencies().getJarDependency()){
                if(depStore.getChild(jarName) == null){
                    InputStream is = resourceProvider.getDependencyInputStream(jarName) ;
                    if(is != null){
                        depStore.importInputStream(jarName, is) ;
                    }
                }
            }
        }
    }

    protected abstract DefinitionResourceProvider getDefinitionResourceProvider();

    public void updateConnectorDependencies(Configuration configuration, DefinitionMapping association, ConnectorImplementation implementation,CompoundCommand cc, EditingDomain editingDomain) {
        Assert.isNotNull(implementation) ;
        String id =  implementation.getImplementationId() ;
        String version =  implementation.getImplementationVersion() ;
        String implementationId = NamingUtils.toConnectorImplementationFilename(id, version, false) ;

        FragmentContainer container = getContainer(configuration) ;
        Assert.isNotNull(container) ;

        for(FragmentContainer fc : container.getChildren()){
            if(fc.getId().equals(implementationId)){
                Set<Fragment> toRemove = new HashSet<Fragment>();
                for(Fragment depFragment : fc.getFragments()){
                    if(!implementation.getJarDependencies().getJarDependency().contains(depFragment.getValue())){
                        toRemove.add(depFragment);
                    }
                }
                editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, fc, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, toRemove)) ;
            }
        }

        boolean exists = false ;
        for(FragmentContainer fc : container.getChildren()){
            if(fc.getId().equals(implementationId)){
                exists = true ;
                updateJarDependencies(fc,implementation,editingDomain,cc) ;
            }
        }
        if(!exists){
            FragmentContainer connectorContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer() ;
            connectorContainer.setId(implementationId) ;
            editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, container, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__CHILDREN, connectorContainer)) ;
            updateJarDependencies(connectorContainer,implementation,editingDomain,cc) ;
        }

    }

    private FragmentContainer getContainer(Configuration configuration) {
        for(FragmentContainer container: configuration.getProcessDependencies()){
            if(container.getId().equals(getFragmentContainerId())){
                return container ;
            }
        }
        return null;
    }

    protected void updateJarDependencies(FragmentContainer connectorContainer, ConnectorImplementation implementation, EditingDomain editingDomain, CompoundCommand cc) {
        for(String jar : implementation.getJarDependencies().getJarDependency()){
            boolean exists = false ;
            for(Fragment dep : connectorContainer.getFragments()){
                if(dep.getValue().equals(jar)){
                    exists = true ;
                    break ;
                }
            }
            if(!exists){
                Fragment depFragment = ConfigurationFactory.eINSTANCE.createFragment() ;
                depFragment.setExported(true) ;
                depFragment.setKey(implementation.getImplementationId() +" -- " + implementation.getImplementationVersion()) ;
                depFragment.setValue(jar) ;
                depFragment.setType(getFragmentContainerId()) ;
                editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, connectorContainer, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, depFragment)) ;
            }
        }
        final String connectorJarName = NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(), implementation.getImplementationVersion(), false) ;
        boolean exists = false ;
        for(Fragment dep : connectorContainer.getFragments()){
            if(dep.getValue().equals(connectorJarName)){
                exists = true ;
                break ;
            }
        }
        if(!exists){
            Fragment depFragment = ConfigurationFactory.eINSTANCE.createFragment() ;
            depFragment.setExported(true) ;
            depFragment.setKey(implementation.getImplementationId() +" -- " + implementation.getImplementationVersion()) ;
            depFragment.setValue(connectorJarName) ;
            depFragment.setType(getFragmentContainerId()) ;
            editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, connectorContainer, ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, depFragment)) ;
        }
       
    }

    private void removeConnectorDefinitions(Configuration configuration, AbstractProcess process,CompoundCommand cc, EditingDomain editingDomain) {
        List<Connector> connectors =  getExistingConnectors(process);
        List<DatabaseKPIBinding> kpis =  ModelHelper.getAllItemsOfType(process, KpiPackage.Literals.DATABASE_KPI_BINDING) ;
        Set<DefinitionMapping> toRemove = new HashSet<DefinitionMapping>();
        for(DefinitionMapping association : configuration.getDefinitionMappings()){
            if(getFragmentContainerId().equals(association.getType())){
                String defId = association.getDefinitionId() ;
                String defVersion = association.getDefinitionVersion() ;
                boolean exists = false ;
                for(Connector c : connectors){
                    String cDef = c.getDefinitionId() ;
                    String cVer = c.getDefinitionVersion() ;
                    if(defId.equals(cDef) && defVersion.equals(cVer)){
                        exists = true ;
                        break ;
                    }
                }
                if(!kpis.isEmpty() && defId.equals(DB_CONNECTOR_FOR_KPI_ID) && defVersion.equals(DB_CONNECTOR_VERSION)){
                    exists = true ;
                }
                if(!exists){
                    toRemove.add(association);
                    removeConnectorDependencies(configuration, NamingUtils.toConnectorImplementationFilename(association.getImplementationId(), association.getImplementationVersion(), false), cc, editingDomain);
                }
            }
        }
        editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__DEFINITION_MAPPINGS, toRemove)) ;
    }

    public void removeConnectorDependencies(Configuration configuration, String implementationId, CompoundCommand cc, EditingDomain editingDomain) {
        FragmentContainer container = getContainer(configuration) ;
        Assert.isNotNull(container) ;

        FragmentContainer toRemove = null ;
        for(FragmentContainer fc : container.getChildren()){
            if(fc.getId().equals(implementationId)){
                toRemove = fc ;
            }
        }
        if(toRemove != null){
            editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, container,ConfigurationPackage.Literals.FRAGMENT_CONTAINER__CHILDREN,toRemove)) ;
        }
    }

    @Override
    public EStructuralFeature getDependencyKind() {
        return PROCESS_DEPENDENCY;
    }


}
