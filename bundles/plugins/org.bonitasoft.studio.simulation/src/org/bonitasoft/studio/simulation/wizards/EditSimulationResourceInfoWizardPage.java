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
package org.bonitasoft.studio.simulation.wizards;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IWidgetContribtution;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.URLEncodableInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.model.simulation.Resource;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationResourceRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Baptiste Mesta
 * @author Romain Bioteau
 */
public class EditSimulationResourceInfoWizardPage extends WizardPage {

	private static final Object UNILIMITED_RESOURCE_CONTRIBUTION_ID = "org.bonitasoft.studio.simulation.unlimitedResources";
	private Resource simulationResource;
	private Text nameText;
	private Text quantityText;
	private Text targetQuantityText;
	private Text fixedCostText;
	private Text timeCostText;
	private Combo costUnitCombo;
	private Combo timeUnitCombo;
	private Label lblCostUnit;
	private Button unlimitedResourceCheckbox;
	private IRepositoryStore resourceStore;

	/**
	 * @return the nameText
	 */
	public String getNameText() {
		return nameText.getText();
	}

	/**
	 * @return the typeText
	 */
	public String getTypeText() {
		return"";
	}

	/**
	 * @return the quantityText
	 */
	public String getQuantityText() {
		return quantityText.getText();
	}
	/**
	 * @return the quantityText
	 */
	public String getTargetQuantity() {
		return targetQuantityText.getText();
	}


	public boolean getUnlimited() {
		if(unlimitedResourceCheckbox == null){
			if(simulationResource != null)
				return simulationResource.isUnlimited() ;
			else
				return false ;
		}else{
			return unlimitedResourceCheckbox.getSelection();
		}
	}

	/**
	 * @param artifact
	 */
	protected EditSimulationResourceInfoWizardPage(Resource resource) {
		super("edit artifact");//$NON-NLS-1$
		simulationResource = resource;
		setTitle(Messages.EditSimulationResourceInfoWizardPage_Title);
		setMessage(Messages.EditSimulationResourceInfoWizardPage_Message);
		this.setImageDescriptor(Pics.getWizban());
		this.resourceStore = RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class) ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE);
		setControl(mainComposite);
		mainComposite.setLayout(new GridLayout(1,false));

		Group composite = new Group(mainComposite, SWT.NONE);
		composite.setText(Messages.resourceInfo) ;
		GridLayout layout =	new GridLayout(3, false) ; 
		layout.marginRight = 10 ;
		layout.horizontalSpacing = 10 ;
		composite.setLayout(layout);
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;


		Label lblName = new Label(composite, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText(Messages.EditSimulationResourceWizardPage_lblName_text);

		nameText = new Text(composite, SWT.BORDER);
		GridData gd_nameText = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		nameText.setLayoutData(gd_nameText);

		new Label(composite,SWT.NONE) ;

		Label lblQuantity = new Label(composite, SWT.NONE);
		lblQuantity.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQuantity.setText(Messages.EditSimulationResourceWizardPage_lblQuantity_text);

		quantityText = new Text(composite, SWT.BORDER);
		quantityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		ControlDecoration quantityControlDecoration = new ControlDecoration(quantityText, SWT.TOP | SWT.LEFT );
		quantityControlDecoration.setImage(Pics.getImage(PicsConstants.hint));
		quantityControlDecoration.setDescriptionText(Messages.quantityHint);

		addWidgetContribution(composite);

		Label lblMaxQuantity = new Label(composite, SWT.NONE);
		lblMaxQuantity.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMaxQuantity.setText(Messages.ManageResourcesWizardPage_lblTargetQuantity_text);

		targetQuantityText = new Text(composite, SWT.BORDER);
		targetQuantityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		ControlDecoration targetControlDecoration = new ControlDecoration(targetQuantityText, SWT.TOP | SWT.LEFT );
		targetControlDecoration.setImage(Pics.getImage(PicsConstants.hint));
		targetControlDecoration.setDescriptionText(Messages.targetQuantityHint);

		new Label(composite,SWT.NONE) ;

		if(simulationResource.getName() != null) {
			nameText.setText(simulationResource.getName());
		}else{
			nameText.setText(Messages.resourceDefaultName+(resourceStore.getChildren().size()+1));
		}
		quantityText.setText(String.valueOf(simulationResource.getQuantity()));//$NON-NLS-1$
		targetQuantityText.setText(String.valueOf(simulationResource.getMaximumQuantity()));//$NON-NLS-1$

		quantityText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getContainer().updateButtons();
			}
		});
		targetQuantityText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getContainer().updateButtons();

			}
		});
		nameText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getContainer().updateButtons();
			}
		});

		if(unlimitedResourceCheckbox != null){
			if(unlimitedResourceCheckbox.getSelection()){
				quantityText.setEnabled(false) ;
			}
		}


		Group composite2 = new Group(mainComposite, SWT.NONE);
		composite2.setText(Messages.ResourceCost) ;
		GridLayout layout2 = new GridLayout(4, false) ; 
		layout2.marginRight = 10 ;
		layout2.horizontalSpacing = 10 ;
		composite2.setLayout(layout2);
		composite2.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;

		lblCostUnit = new Label(composite2, SWT.NONE);
		lblCostUnit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCostUnit.setText(Messages.EditSimulationResourceWizardPage_lblCostUnit_text);

		costUnitCombo = new Combo(composite2, SWT.NONE);
		costUnitCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		costUnitCombo.add("\u0024");//$NON-NLS-1$
		costUnitCombo.add("\u20AC");//$NON-NLS-1$
		costUnitCombo.add("\u00A3");//$NON-NLS-1$
		costUnitCombo.add("\u00A5");//$NON-NLS-1$


		new Label(composite2, SWT.NONE);
		new Label(composite2, SWT.NONE);

		Label lblFixedCost = new Label(composite2, SWT.NONE);
		lblFixedCost.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFixedCost.setText(Messages.EditSimulationResourceWizardPage_lblFixedCost_text);

		fixedCostText = new Text(composite2, SWT.BORDER);
		fixedCostText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		ControlDecoration costPerUseControlDecoration = new ControlDecoration(fixedCostText, SWT.TOP | SWT.LEFT );
		costPerUseControlDecoration.setImage(Pics.getImage(PicsConstants.hint));
		costPerUseControlDecoration.setDescriptionText(Messages.costPerUseHint);

		new Label(composite2, SWT.NONE);
		new Label(composite2, SWT.NONE);

		Label lblTimeCost = new Label(composite2, SWT.NONE);
		lblTimeCost.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTimeCost.setText(Messages.EditSimulationResourceWizardPage_lblTimeCost_text);

		timeCostText = new Text(composite2, SWT.BORDER);
		timeCostText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		timeCostText.setText(String.valueOf(simulationResource.getTimeCost()));
		ControlDecoration costControlDecoration = new ControlDecoration(timeCostText, SWT.TOP | SWT.LEFT );
		costControlDecoration.setImage(Pics.getImage(PicsConstants.hint));
		costControlDecoration.setDescriptionText(Messages.timeCostHint);

		Label per = new Label(composite2, SWT.NONE);
		per.setText(Messages.per);


		timeUnitCombo = new Combo(composite2, SWT.READ_ONLY);
		timeUnitCombo.setItems(new String[] {
				Messages.minute,Messages.hour,Messages.day,Messages.week,Messages.month,Messages.year});
		timeUnitCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		timeUnitCombo.select(1);

		if(simulationResource.getCostUnit() != null) {
			costUnitCombo.setText(simulationResource.getCostUnit());
		}else{
			costUnitCombo.setText("\u0024");
		}
		switch (simulationResource.getTimeUnit()) {
		case DAY:
			timeUnitCombo.setText(Messages.day);
			break;
		case HOUR:
			timeUnitCombo.setText(Messages.hour);
			break;
		case WEEK:
			timeUnitCombo.setText(Messages.week);
			break;
		case MINUTE:
			timeUnitCombo.setText(Messages.minute);
			break;
		case MONTH:
			timeUnitCombo.setText(Messages.month);
			break;
		case YEAR:
			timeUnitCombo.setText(Messages.year);
			break;

		default:
			break;
		}

		fixedCostText.setText(String.valueOf(simulationResource.getFixedCost()));//$NON-NLS-1$


		fixedCostText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getContainer().updateButtons();
			}
		});
		timeCostText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getContainer().updateButtons();

			}
		});

	}

	private void addWidgetContribution(Group composite) {

		IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.properties.widget"); //$NON-NLS-1$
		IWidgetContribtution unlimitedResourceContribution = null;
		for (IConfigurationElement elem : elements) {
			try {
				if(elem.getAttribute("id").equals(UNILIMITED_RESOURCE_CONTRIBUTION_ID)){ //$NON-NLS-1$
					unlimitedResourceContribution = (IWidgetContribtution) elem.createExecutableExtension("class"); //$NON-NLS-1$
				}
			} catch (CoreException e) {
				BonitaStudioLog.error(e);
			}
		}

		if(unlimitedResourceContribution == null){
			new Label(composite,SWT.NONE) ;
			return ;
		}

		unlimitedResourceCheckbox = (Button) unlimitedResourceContribution.createControl(composite);
		if(simulationResource != null){
			unlimitedResourceCheckbox.setSelection(simulationResource.isUnlimited());
		}else{
			unlimitedResourceCheckbox.setSelection(false);
		}
		unlimitedResourceCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(unlimitedResourceCheckbox.getSelection()){
					quantityText.setEnabled(false) ;
				}else{
					quantityText.setEnabled(true) ;
				}
				getContainer().updateButtons();
			}
		});

	}
	

	@Override
	public boolean isPageComplete() {
		setErrorMessage(null);
		int quantity = 0;
		if(unlimitedResourceCheckbox == null || !unlimitedResourceCheckbox.getSelection()){
			try{
				quantity = Integer.parseInt(getQuantityText());
				if(quantity < 1){
					setErrorMessage(Messages.EditSimulationResourceWizard_ErrorQuantityLessThanZero); //$NON-NLS-1$
					return false;
				}
			}catch (NumberFormatException  e) {
				setErrorMessage(Messages.EditSimulationResourceWizard_ErrorQuantity); //$NON-NLS-1$
				return false;
			}
			int targetQty = 0;
			try{
				targetQty = Integer.parseInt(getTargetQuantity());
			}catch (NumberFormatException  e) {
				setErrorMessage(Messages.EditSimulationResourceWizard_ErrorTargetQuantity); //$NON-NLS-1$
				return false;
			}

			if(targetQty > quantity){
				setErrorMessage(Messages.EditSimulationResourceWizard_ErrorTooManyTargetQuantity); //$NON-NLS-1$
				return false;
			}
		}

		
		MultiValidator validator = new MultiValidator();
		validator.addValidator(new EmptyInputValidator(Messages.name));
		validator.addValidator(new URLEncodableInputValidator(Messages.name));
		IStatus nameStatus = validator.validate(getNameText());
        if(!nameStatus.isOK()){
            setErrorMessage(nameStatus.getMessage());
            return false;
        }

		try{
			Double.parseDouble(getFixedCostText());
		}catch (NumberFormatException  e) {
			setErrorMessage(Messages.EditSimulationResourceWizard_ErrorFixedCost); //$NON-NLS-1$
			return false;
		}


		try{
			Double.parseDouble(getTimeCostText());
		}catch (NumberFormatException  e) {
			setErrorMessage(Messages.EditSimulationResourceWizard_ErrorTimeCost); //$NON-NLS-1$
			return false;
		}

		return super.isPageComplete();
	}

	/**
	 * @return
	 */
	public String getFixedCostText() {
		return fixedCostText.getText();
	}

	/**
	 * @return
	 */
	public String getCostUnitText() {
		return costUnitCombo.getText();
	}

	/**
	 * @return
	 */
	public String getTimeUnitText() {
		return timeUnitCombo.getText();
	}


	/**
	 * @return the timeCostText
	 */
	public String getTimeCostText() {
		return timeCostText.getText();
	}

}
