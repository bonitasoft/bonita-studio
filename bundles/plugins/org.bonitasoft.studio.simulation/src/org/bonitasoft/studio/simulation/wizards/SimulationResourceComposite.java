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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.simulation.Resource;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * @author Baptiste Mesta
 */
public class SimulationResourceComposite {

    /**
	 *
	 */
    private Label nameText;
    /**
	 *
	 */
    private Label typeText;
    /**
	 *
	 */
    private Label quantityText;
    /**
	 *
	 */
    private Label fixedCostText;
    /**
	 *
	 */
    private Label timeCost;
    /**
	 *
	 */
    private Label costUnitCombo;
    /**
	 *
	 */
    private Label timeUnitCombo;
    private Label maxQuantityText;

    /**
	 *
	 */
    public SimulationResourceComposite() {
    }

    /**
     * @return the nameText
     */
    public Label getNameText() {
        return nameText;
    }

    /**
     * @param nameText the nameText to set
     */
    public void setNameText(final Label nameText) {
        this.nameText = nameText;
    }

    /**
     * @return the typeText
     */
    public Label getTypeText() {
        return typeText;
    }

    /**
     * @param typeText the typeText to set
     */
    public void setTypeText(final Label typeText) {
        this.typeText = typeText;
    }

    /**
     * @return the quantityText
     */
    public Label getQuantityText() {
        return quantityText;
    }

    /**
     * @param quantityText the quantityText to set
     */
    public void setQuantityText(final Label quantityText) {
        this.quantityText = quantityText;
    }

    /**
     * @return the quantityText
     */
    public Label getMaxQuantityText() {
        return maxQuantityText;
    }

    /**
     * @param quantityText the quantityText to set
     */
    public void setMaxQuantityText(final Label quantityText) {
        maxQuantityText = quantityText;
    }

    /**
     * @return the fixedCostText
     */
    public Label getFixedCostText() {
        return fixedCostText;
    }

    /**
     * @param fixedCostText the fixedCostText to set
     */
    public void setFixedCostText(final Label fixedCostText) {
        this.fixedCostText = fixedCostText;
    }

    /**
     * @return the timeCost
     */
    public Label getTimeCost() {
        return timeCost;
    }

    /**
     * @param timeCost the timeCost to set
     */
    public void setTimeCost(final Label timeCost) {
        this.timeCost = timeCost;
    }

    /**
     * @return the costUnitCombo
     */
    public Label getCostUnitCombo() {
        return costUnitCombo;
    }

    /**
     * @param costUnitCombo the costUnitCombo to set
     */
    public void setCostUnitCombo(final Label costUnitCombo) {
        this.costUnitCombo = costUnitCombo;
    }

    /**
     * @return the timeUnitCombo
     */
    public Label getTimeUnitCombo() {
        return timeUnitCombo;
    }

    /**
     * @param timeUnitCombo the timeUnitCombo to set
     */
    public void setTimeUnitCombo(final Label timeUnitCombo) {
        this.timeUnitCombo = timeUnitCombo;
    }

    /**
     * @param manageResourcesWizardPage TODO
     * @param sashForm
     */
    void createResourcePanel(final ManageResourcesWizardPage manageResourcesWizardPage, final SashForm sashForm) {
        final Group composite = new Group(sashForm, SWT.NONE);
        composite.setText(Messages.resourceInfo);
        composite.setLayout(new GridLayout(4, false));

        final Label lblName = new Label(composite, SWT.RIGHT);
        lblName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        lblName.setText(Messages.ManageResourcesWizardPage_lblName_text);

        setNameText(new Label(composite, SWT.NONE));
        final GridData gd_nameText = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
        getNameText().setLayoutData(gd_nameText);

        final Label lblQuantity = new Label(composite, SWT.RIGHT);
        lblQuantity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        lblQuantity.setText(Messages.ManageResourcesWizardPage_lblQuantity_text);

        setQuantityText(new Label(composite, SWT.NONE));
        getQuantityText().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

        final Label lblMaxQuantity = new Label(composite, SWT.RIGHT);
        lblMaxQuantity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        lblMaxQuantity.setText(Messages.ManageResourcesWizardPage_lblTargetQuantity_text);

        setMaxQuantityText(new Label(composite, SWT.NONE));
        getMaxQuantityText().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

        final Label lblFixedCost = new Label(composite, SWT.RIGHT);
        lblFixedCost.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        lblFixedCost.setText(Messages.ManageResourcesWizardPage_lblFixedCost_text);

        setFixedCostText(new Label(composite, SWT.NONE));
        getFixedCostText().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

        final Label lblTimeCost = new Label(composite, SWT.RIGHT);
        lblTimeCost.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        lblTimeCost.setText(Messages.ManageResourcesWizardPage_lblTimeCost_text);

        setTimeCost(new Label(composite, SWT.NONE));
        getTimeCost().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

    }

    /**
     * @param manageResourcesWizardPage TODO
     * @param firstElement
     */
    protected void changeBinding(final Object firstElement) {
        Resource currentResource;
        if (firstElement instanceof IRepositoryFileStore) {
            try {
                currentResource = (Resource) ((IRepositoryFileStore) firstElement).getContent();

                getNameText().setText(currentResource.getName());

                getTimeCost()
                        .setText(
                                currentResource.getTimeCost()
                                        + " " + currentResource.getCostUnit() + " " + Messages.per + " " + currentResource.getTimeUnit().getLiteral());//$NON-NLS-1$

                if (currentResource.isUnlimited()) {
                    getQuantityText().setText(Messages.unlimited);//$NON-NLS-1$
                } else {
                    getQuantityText().setText(String.valueOf(currentResource.getQuantity()));//$NON-NLS-1$
                }

                getMaxQuantityText().setText(String.valueOf(currentResource.getMaximumQuantity()));//$NON-NLS-1$
                if (currentResource.getCostUnit() != null) {
                    getFixedCostText().setText(currentResource.getFixedCost() + " " + currentResource.getCostUnit());//$NON-NLS-1$
                } else {
                    getFixedCostText().setText(String.valueOf(currentResource.getFixedCost()));//$NON-NLS-1$
                }
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to read simulation resource content", e);
            }
        } else {
            currentResource = null;
            getNameText().setText("");//$NON-NLS-1$
            getTimeCost().setText("");//$NON-NLS-1$
            getQuantityText().setText("");//$NON-NLS-1$
            getFixedCostText().setText("");//$NON-NLS-1$
            getMaxQuantityText().setText("");//$NON-NLS-1$
        }
    }
}
