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
package org.bonitasoft.studio.simulation.tests;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.model.simulation.DayPeriod;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileFileStore;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;

/**
 * @author Baptiste Mesta
 *
 */
public class TestSimulationLoadProfileRepository extends TestCase {

    public void testCreateArtifact(){
        SimulationLoadProfileRepositoryStore srrs = (SimulationLoadProfileRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class);

        int nbArtifact = srrs.getChildren().size();

        LoadProfile simulationLoadProfile = SimulationFactory.eINSTANCE.createLoadProfile();
        simulationLoadProfile.setName("LoadProfile1");//$NON-NLS-1$
        SimulationCalendar resourceCalendar = SimulationFactory.eINSTANCE.createSimulationCalendar();
        DayPeriod dayPeriod = SimulationFactory.eINSTANCE.createDayPeriod();
        dayPeriod.setStartHour(8);
        dayPeriod.setEndHour(16);
        dayPeriod.getDay().add(1);
        dayPeriod.getDay().add(2);
        dayPeriod.getDay().add(3);
        resourceCalendar.getDaysOfWeek().add(dayPeriod);
        simulationLoadProfile.setCalendar(resourceCalendar);

        IRepositoryFileStore file = srrs.createRepositoryFileStore("LoadProfile1."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
        file.save(simulationLoadProfile);
        assertEquals("artifact not added?", nbArtifact+1, srrs.getChildren().size());

    }


    public void testGetArtifact() {
        LoadProfile simulationLoadProfile = SimulationFactory.eINSTANCE.createLoadProfile();
        simulationLoadProfile.setName("LoadProfile2");
        SimulationLoadProfileRepositoryStore srrs = (SimulationLoadProfileRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class);

        srrs.createRepositoryFileStore("LoadProfile2."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT).save(simulationLoadProfile);

        SimulationLoadProfileFileStore artifact = srrs.getChild("LoadProfile2."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
        assertNotNull("getArtifact error", artifact);
        assertEquals("Artifact not correctly saved", "LoadProfile2", ((LoadProfile)artifact.getContent()).getName());
    }

    public void testSaveArtifactWithSameName() throws Exception {
        LoadProfile loadProfile = SimulationFactory.eINSTANCE.createLoadProfile();
        loadProfile.setName("LoadProfile3");
        loadProfile.setDescription("plop");
        SimulationLoadProfileRepositoryStore srrs = (SimulationLoadProfileRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class);

        SimulationLoadProfileFileStore artifact =  srrs.createRepositoryFileStore("LoadProfile3."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
        loadProfile.setDescription("blop");
        artifact.save(loadProfile);
        assertEquals("Modification not saved", "blop", ((LoadProfile) artifact.getContent()).getDescription());
    }

    public void testSaveArtifactWithDifferentName() throws Exception {
        LoadProfile loadProfile = SimulationFactory.eINSTANCE.createLoadProfile();
        loadProfile.setName("LoadProfile4");

        SimulationLoadProfileRepositoryStore srrs = (SimulationLoadProfileRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class);
        SimulationLoadProfileFileStore artifact =  srrs.createRepositoryFileStore("LoadProfile4."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);

        loadProfile.setName("LoadProfile5");
        artifact.save(loadProfile);
        artifact.rename("LoadProfile5."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
        assertNull("Artifact has been renamed, we should not find it with its old name any more", srrs.getChild("LoadProfile4."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT));
        assertNotNull("Artifact has been renamed, the new artifact should exists", srrs.getChild("LoadProfile5."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT));
    }


}
