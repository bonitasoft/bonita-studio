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
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.simulation.Resource;
import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.model.simulation.TimeUnit;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationResourceRepositoryStore;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Baptiste Mesta
 */
public class EditSimulationResourceWizard extends Wizard {

    private EditSimulationResourceInfoWizardPage infoPage;
    private IRepositoryFileStore artifact;
    private Resource resource;
    private EditSimulationResourceCalendarWizardPage calendarPage;

    /**
     * @param firstElement
     */
    public EditSimulationResourceWizard(final IRepositoryFileStore artifact) {
        this.artifact = artifact;
        try {
            resource = (Resource) artifact.getContent();
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error("Failed to read resource content", e);
        }
        setWindowTitle(Messages.EditSimulationResourceWizard_WindowTitle);
    }

    /**
	 *
	 */
    public EditSimulationResourceWizard() {
        artifact = null;
        resource = SimulationFactory.eINSTANCE.createResource();
        resource.setVersion(ModelVersion.CURRENT_VERSION);
        setWindowTitle(Messages.EditSimulationResourceWizard_WindowTitle);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        infoPage = new EditSimulationResourceInfoWizardPage(resource);

        calendarPage = new EditSimulationResourceCalendarWizardPage(resource);
        addPage(infoPage);

        addPage(calendarPage);
        super.addPages();
    }

    @Override
    public boolean canFinish() {
        return infoPage.isPageComplete() && calendarPage.isPageComplete();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        infoPage.setErrorMessage(null);
        int quantity = 0;
        try {
            quantity = Integer.parseInt(infoPage.getQuantityText());
        } catch (final NumberFormatException e) {
            infoPage.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorQuantity); //$NON-NLS-1$
            return false;
        }
        int maxQuantity = 0;
        try {
            maxQuantity = Integer.parseInt(infoPage.getTargetQuantity());
        } catch (final NumberFormatException e) {
            infoPage.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorMaxQuantity); //$NON-NLS-1$
            return false;
        }
        double fixedCost = 0;
        try {
            fixedCost = Double.parseDouble(infoPage.getFixedCostText());
        } catch (final NumberFormatException e) {
            infoPage.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorFixedCost); //$NON-NLS-1$
            return false;
        }

        double timeCost = 0;
        try {
            timeCost = Double.parseDouble(infoPage.getTimeCostText());
        } catch (final NumberFormatException e) {
            infoPage.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorTimeCost); //$NON-NLS-1$
            return false;
        }
        final String nameText = infoPage.getNameText();
        if (nameText.length() == 0) {
            infoPage.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorEmptyName); //$NON-NLS-1$
            return false;
        }

        final IRepositoryStore resourceStore = RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class);
        if (!nameText.equals(resource.getName()) && resourceStore.getChild(nameText + "." + SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT) != null) {
            infoPage.setErrorMessage(Messages.EditSimulationResourceWizard_ErrorSameName); //$NON-NLS-1$
            return false;
        }

        final Resource simulationResource = resource;
        simulationResource.setName(nameText);
        simulationResource.setType(infoPage.getTypeText());
        simulationResource.setUnlimited(infoPage.getUnlimited());
        simulationResource.setQuantity(quantity);
        simulationResource.setMaximumQuantity(maxQuantity);
        simulationResource.setTimeCost(timeCost);
        simulationResource.setFixedCost(fixedCost);
        simulationResource.setCostUnit(infoPage.getCostUnitText());
        final String timeUnit = infoPage.getTimeUnitText();
        if (timeUnit.equals(Messages.minute)) { //$NON-NLS-1$
            simulationResource.setTimeUnit(TimeUnit.MINUTE);
        } else if (timeUnit.equals(Messages.hour)) { //$NON-NLS-1$
            simulationResource.setTimeUnit(TimeUnit.HOUR);
        } else if (timeUnit.equals(Messages.day)) { //$NON-NLS-1$
            simulationResource.setTimeUnit(TimeUnit.DAY);
        } else if (timeUnit.equals(Messages.week)) { //$NON-NLS-1$
            simulationResource.setTimeUnit(TimeUnit.WEEK);
        } else if (timeUnit.equals(Messages.month)) { //$NON-NLS-1$
            simulationResource.setTimeUnit(TimeUnit.MONTH);
        } else if (timeUnit.equals(Messages.year)) { //$NON-NLS-1$
            simulationResource.setTimeUnit(TimeUnit.YEAR);
        }

        calendarPage.setErrorMessage(null);
        SimulationCalendar resourceCalendar;
        if (resource.getCalendar() == null) {
            resourceCalendar = SimulationFactory.eINSTANCE.createSimulationCalendar();
        } else {
            resourceCalendar = resource.getCalendar();
        }
        resourceCalendar.getDaysOfWeek().removeAll(resourceCalendar.getDaysOfWeek());
        resourceCalendar.getDaysOfWeek().addAll(calendarPage.getDays());

        resource.setCalendar(resourceCalendar);

        try {
            if (artifact != null) {
                artifact.save(simulationResource);
            } else {
                artifact = resourceStore.createRepositoryFileStore(simulationResource.getName() + "."
                        + SimulationResourceRepositoryStore.SIMULATION_RESOURCE_EXT);
                artifact.save(simulationResource);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return true;
    }

    /**
     * @return the artifact
     */
    public IRepositoryFileStore getArtifact() {
        return artifact;
    }

}
