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
package org.bonitasoft.studio.tests.configuration;

import java.util.List;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;

import junit.framework.TestCase;


/**
 * @author Romain Bioteau
 *
 */
public class TestConfigurationSynhronizer extends TestCase {


    private Configuration configuration;

    @Override
    protected void setUp() throws Exception {
        configuration = ConfigurationFactory.eINSTANCE.createConfiguration() ;
    }

    public void testDependenciesMainFragmentContainers() throws Exception {
        AbstractProcess dummyProcess = ProcessFactory.eINSTANCE.createPool() ;

        assertTrue("No fragment container should be defined",configuration.getProcessDependencies().isEmpty());

        final ConfigurationSynchronizer synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        assertTrue("Missing fragment container",!configuration.getProcessDependencies().isEmpty()) ;

        boolean connectorFragmentFound = false ;
        boolean actorFilterFragmentFound = false ;
        boolean groovyFragmentFound = false ;
        boolean otherFragmentFound = false ;
        for(FragmentContainer fc : configuration.getProcessDependencies()){
            if(fc.getId().equals(FragmentTypes.CONNECTOR)){
                connectorFragmentFound = true ;
            }
            if(fc.getId().equals(FragmentTypes.ACTOR_FILTER)){
                actorFilterFragmentFound = true ;
            }

            if(fc.getId().equals(FragmentTypes.GROOVY_SCRIPT)){
                groovyFragmentFound = true ;
            }
            if(fc.getId().equals(FragmentTypes.OTHER)){
                otherFragmentFound = true ;
            }
        }


        assertTrue("Missing Connector fragment container",connectorFragmentFound);
        assertTrue("Missing Actor filter fragment container",actorFilterFragmentFound);
        assertTrue("Missing Groovy fragment container",groovyFragmentFound);
        assertTrue("Missing Other fragment container",otherFragmentFound);
    }

    public void testActorMappingSynchronization() throws Exception {
        AbstractProcess dummyProcess = ProcessFactory.eINSTANCE.createPool() ;
        Actor actor = ProcessFactory.eINSTANCE.createActor() ;
        actor.setName("DeliveryMan") ;
        Actor actor2 = ProcessFactory.eINSTANCE.createActor() ;
        actor2.setName("Customer") ;
        actor2.setInitiator(true);
        dummyProcess.getActors().add(actor) ;
        dummyProcess.getActors().add(actor2) ;

        ConfigurationSynchronizer synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        assertNotNull(configuration.getActorMappings()) ;
        List<ActorMapping> mappings = configuration.getActorMappings().getActorMapping() ;
        assertEquals("Actor mapping synchronization failed",2,mappings.size()) ;

        boolean deliveryManExists = false ;
        boolean customerManExists = false ;

        for(ActorMapping mapping : mappings){
            if(mapping.getName().equals(actor.getName())){
                deliveryManExists = true ;
            }
            if(mapping.getName().equals(actor2.getName())){
                customerManExists = true ;
            }
        }

        assertTrue("DeliveryMan actor is missing",deliveryManExists) ;
        assertTrue("Customer actor is missing",customerManExists) ;


        dummyProcess.getActors().remove(actor2) ;

        synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        assertNotNull(configuration.getActorMappings()) ;
        mappings = configuration.getActorMappings().getActorMapping() ;
        assertEquals("Actor mapping synchronization failed",1,mappings.size()) ;

        deliveryManExists = false ;

        for(ActorMapping mapping : mappings){
            if(mapping.getName().equals(actor.getName())){
                deliveryManExists = true ;
            }
        }

        assertTrue("DeliveryMan actor is missing",deliveryManExists) ;
    }

    public void testConnectorsSynchronization() throws Exception {
        AbstractProcess dummyProcess = ProcessFactory.eINSTANCE.createPool() ;
        Connector c1 = createEmailConnector("emailConnector1");
        Connector c2 = createEmailConnector("emailConnector2");

        dummyProcess.getConnectors().add(c1) ;
        Task t1 = ProcessFactory.eINSTANCE.createTask();
        t1.setName("t1");
        t1.getConnectors().add(c2);
        dummyProcess.getElements().add(t1) ;

        ConfigurationSynchronizer synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        assertNotNull(configuration.getDefinitionMappings()) ;
        List<DefinitionMapping> mappings = configuration.getDefinitionMappings() ;
        assertEquals("Connector mapping synchronization failed with 2 connector of same definition",1,mappings.size()) ;

        FragmentContainer container = getProcessContainer(FragmentTypes.CONNECTOR,configuration) ;
        assertEquals("Connector dependencies synchronization failed with 2 connector of same definition",1,container.getChildren().size()) ;

        dummyProcess.getConnectors().remove(c1) ;

        synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        mappings = configuration.getDefinitionMappings() ;
        assertEquals("Connector mapping synchronization failed after removing a connector",1,mappings.size()) ;
        assertEquals("Connector dependencies synchronization failed after removing a connector",1,container.getChildren().size()) ;


        t1.getConnectors().remove(c2);
        synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        mappings = configuration.getDefinitionMappings() ;
        assertEquals("Connector mapping synchronization failed after removing all connector",0,mappings.size()) ;
        assertEquals("Connector dependencies synchronization failed after removing all connector",0,container.getChildren().size()) ;
    }

    private FragmentContainer getProcessContainer(String id, Configuration conf) {
        for(FragmentContainer container: conf.getProcessDependencies()){
            if(container.getId().equals(id)){
                return container ;
            }
        }
        return null;
    }

    public void testActorFiltersSynchronization() throws Exception {
        AbstractProcess dummyProcess = ProcessFactory.eINSTANCE.createPool() ;
        ActorFilter af1 = createActorFilter("initiatorFilter1");
        ActorFilter af2 = createActorFilter("initiatorFilter2");

        Task t1 = ProcessFactory.eINSTANCE.createTask();
        t1.setName("t1");
        dummyProcess.getElements().add(t1) ;
        Task t2 = ProcessFactory.eINSTANCE.createTask();
        t2.setName("t2");
        dummyProcess.getElements().add(t2) ;

        t2.getFilters().add(af2);

        ConfigurationSynchronizer synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        assertNotNull(configuration.getDefinitionMappings()) ;
        List<DefinitionMapping> mappings = configuration.getDefinitionMappings() ;
        assertEquals("Actor filter mapping synchronization failed with 2 connector of same definition",1,mappings.size()) ;

        t1.getFilters().remove(af1) ;

        synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        mappings = configuration.getDefinitionMappings() ;
        assertEquals("Actor filter mapping synchronization failed after removing a connector",1,mappings.size()) ;

        t2.getFilters().remove(af2);
        synchronizer =  new ConfigurationSynchronizer(dummyProcess,configuration) ;
        synchronizer.synchronize() ;

        mappings = configuration.getDefinitionMappings() ;
        assertEquals("Actor filter mapping synchronization failed after removing all connector",0,mappings.size()) ;
    }

    private ActorFilter createActorFilter(String name) {
        ActorFilter c = ProcessFactory.eINSTANCE.createActorFilter();
        c.setName(name);
        c.setDefinitionId("initiator");
        c.setDefinitionVersion("1.0.0");
        c.setEvent(ConnectorEvent.ON_ENTER.toString());
        return c;
    }

    //TODO add to a util class.
    private Connector createEmailConnector(String connectorName) {
        Connector c = ProcessFactory.eINSTANCE.createConnector();
        c.setName(connectorName);
        c.setDefinitionId("email");
        c.setDefinitionVersion("1.0.0");
        c.setEvent(ConnectorEvent.ON_ENTER.toString());
        return c;
    }

    @Override
    protected void tearDown() throws Exception {
        configuration = null ;
    }

}
