/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.simulation.wizards;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Baptiste Mesta
 */
public class EditSimulationLoadProfileWizard extends Wizard {

    private IRepositoryFileStore file;
    private LoadProfile loadProfile;
    private EditSimulationLoadProfileWizardPage lpPage;
    private EditSimulationLoadProfileCalendarWizardPage calendarPage;

    /**
	 *
	 */
    public EditSimulationLoadProfileWizard() {
        file = null;
        loadProfile = SimulationFactory.eINSTANCE.createLoadProfile();
        setWindowTitle(Messages.EditSimulationLoadProfileWizard_windowTitle);
    }

    /**
     * @param artifact2
     */
    public EditSimulationLoadProfileWizard(final IRepositoryFileStore artifact) {
        file = artifact;
        try {
            loadProfile = (LoadProfile) artifact.getContent();
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error("Failed to read load profile content", e);
        }
        loadProfile.setVersion(ModelVersion.CURRENT_VERSION);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        lpPage = new EditSimulationLoadProfileWizardPage(loadProfile);
        calendarPage = new EditSimulationLoadProfileCalendarWizardPage(loadProfile);
        addPage(lpPage);
        addPage(calendarPage);
        super.addPages();
    }

    @Override
    public boolean canFinish() {
        return calendarPage.isPageComplete();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {

        final String nameText = lpPage.getNameText();

        final LoadProfile simulationLoadProfile = loadProfile;
        simulationLoadProfile.setName(nameText);
        simulationLoadProfile.getInjectionPeriods().removeAll(simulationLoadProfile.getInjectionPeriods());
        simulationLoadProfile.getInjectionPeriods().addAll(lpPage.getInjectionPeriods());

        SimulationCalendar lpCalendar;
        if (simulationLoadProfile.getCalendar() == null) {
            lpCalendar = SimulationFactory.eINSTANCE.createSimulationCalendar();
        } else {
            lpCalendar = simulationLoadProfile.getCalendar();
        }

        lpCalendar.getDaysOfWeek().removeAll(lpCalendar.getDaysOfWeek());
        lpCalendar.getDaysOfWeek().addAll(calendarPage.getDays());

        simulationLoadProfile.setCalendar(lpCalendar);

        try {
            if (file != null) {
                file.save(simulationLoadProfile);
            } else {
                final SimulationLoadProfileRepositoryStore profileStore = RepositoryManager.getInstance().getRepositoryStore(
                        SimulationLoadProfileRepositoryStore.class);
                file = profileStore.createRepositoryFileStore(simulationLoadProfile.getName() + "."
                        + SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
                file.save(simulationLoadProfile);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            return false;
        }
        return true;
    }

    /**
     * @return the artifact
     */
    public IRepositoryFileStore getArtifact() {
        return file;
    }

}
