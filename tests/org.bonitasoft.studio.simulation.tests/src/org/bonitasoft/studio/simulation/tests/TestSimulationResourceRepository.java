/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.simulation.tests;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.simulation.Resource;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.simulation.repository.SimulationResourceFileStore;
import org.bonitasoft.studio.simulation.repository.SimulationResourceRepositoryStore;

/**
 * @author Baptiste Mesta
 *
 */
public class TestSimulationResourceRepository extends TestCase {

    public void testCreateArtifact(){
        SimulationResourceRepositoryStore srrs = (SimulationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class);

        int nbArtifact = srrs.getChildren().size();

        Resource simulationResource = SimulationFactory.eINSTANCE.createResource();
        simulationResource.setName("Duck");//$NON-NLS-1$
        simulationResource.setMaximumQuantity(25);
        simulationResource.setQuantity(12);
        simulationResource.setDescription("It's a beautiful Duck! yeah!");
        simulationResource.setType("Animal");
        simulationResource.setCostUnit("$");
        simulationResource.setFixedCost(32);
        srrs.createRepositoryFileStore("Duck."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT).save(simulationResource);

        assertEquals("artifact not added?", nbArtifact+1, srrs.getChildren().size());


    }


    public void testGetArtifact() {
        Resource simulationResource = SimulationFactory.eINSTANCE.createResource();
        simulationResource.setName("Donkey");
        simulationResource.setType("Animal");
        SimulationResourceRepositoryStore srrs = (SimulationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class);

        srrs.createRepositoryFileStore("Donkey."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT).save(simulationResource);

        SimulationResourceFileStore artifact = srrs.getChild("Donkey."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT);
        assertNotNull("getArtifact error", artifact);
        assertEquals("Artifact not correctly saved", "Donkey", ((Resource)artifact.getContent()).getName());
    }

    public void testSaveArtifactWithSameName() throws Exception {
        Resource resource = SimulationFactory.eINSTANCE.createResource();
        resource.setName("Snake");
        resource.setType("Machine");
        SimulationResourceRepositoryStore srrs = (SimulationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class);
        SimulationResourceFileStore artifact =  srrs.createRepositoryFileStore("Snake."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT);
        resource.setType("Animal");

        artifact.save(resource);
        assertEquals("Modification not saved", "Animal", ((Resource) artifact.getContent()).getType());
    }

    public void testSaveArtifactWithDifferentName() throws Exception {
        Resource resource = SimulationFactory.eINSTANCE.createResource();
        resource.setName("Dolphin");
        SimulationResourceRepositoryStore srrs = (SimulationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class);

        SimulationResourceFileStore artifact =  srrs.createRepositoryFileStore("Dolphin."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT);
        resource.setName("Whale");
        artifact.save(resource);
        final SimulationResourceFileStore child = srrs.getChild("Dolphin."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT);
        assertNotNull("Artifact has been renamed, we should not find it with its old name any more", child);
        assertEquals("Whale", ((Resource) child.getContent()).getName());

        child.rename("Whale."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT);
        final SimulationResourceFileStore child2 = srrs.getChild("Whale."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT);
        assertEquals("Whale", ((Resource) child2.getContent()).getName());
        assertNull("Artifact has been renamed, we should not find it with its old name any more",  srrs.getChild("Dolphin."+SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT));
    }


}
