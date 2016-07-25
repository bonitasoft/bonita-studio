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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bonitasoft.simulation.model.Period;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.URLEncodableInputValidator;
import org.bonitasoft.studio.common.properties.DynamicAddRemoveLineComposite;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.simulation.InjectionPeriod;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.model.simulation.RepartitionType;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


/**
 * @author Baptiste Mesta
 *
 */
public class EditSimulationLoadProfileWizardPage extends WizardPage {


	private static final int DEFAULT_WIDTH = 200;
	private final LoadProfile loadProfile;
	private Text nameText;
	private List<InjectionPeriod> injectionPeriods = new ArrayList<InjectionPeriod>();
	private ScrolledComposite scrolledComposite;
	private Composite pageComposite;
	private SimulationLoadProfileRepositoryStore loadProfileStore;


	/**
	 * @param loadProfile 
	 * @param pageName
	 */
	protected EditSimulationLoadProfileWizardPage(LoadProfile loadProfile) {
		super("editLoadProfile");
		this.loadProfile = loadProfile;
		setTitle(Messages.EditSimulationLoadProfileWizardPage_title);
		setMessage(Messages.EditSimulationLoadProfileWizardPage_desc);
		setImageDescriptor(Pics.getWizban());
		this.loadProfileStore = RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class) ;

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(final Composite parent) {

		scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);

		scrolledComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).align(SWT.FILL, SWT.TOP).hint(SWT.DEFAULT, 150).create());

		pageComposite = new Composite(scrolledComposite, SWT.NONE);
		pageComposite.setLayout(new GridLayout(2, false));

		Label nameLabel = new Label(pageComposite, SWT.RIGHT);
		nameLabel.setText(Messages.EditSimulationLoadProfileWizardPage_name);

		nameText = new Text(pageComposite, SWT.BORDER);
		if(loadProfile.getName() != null){
			nameText.setText(loadProfile.getName());	
		}else{
			nameText.setText(Messages.loadProfileDefaultName+(loadProfileStore.getChildren().size()+1));
		}
		nameText.setLayoutData(GridDataFactory.swtDefaults().hint(DEFAULT_WIDTH, SWT.DEFAULT).create());
		nameText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				getContainer().updateButtons();
			}
		}) ;

		Label injectionPeriodsLabel = new Label(pageComposite, SWT.RIGHT);
		injectionPeriodsLabel.setText(Messages.EditSimulationLoadProfileWizardPage_injectionPeriods);

		DynamicAddRemoveLineComposite dynamicAddRemoveLineComposite = new DynamicAddRemoveLineComposite(pageComposite, SWT.NONE) {

			@Override
			protected void lineRemoved(int i) {
				injectionPeriods.remove(i);
				updateScrolledComposite();
				getContainer().updateButtons();
			}
			@Override
			protected void lineAdded(int i) {
				updateScrolledComposite();
				if(getContainer().getCurrentPage() != null){
					getContainer().updateButtons();
				}
				if(i==0){
					parent.getShell().pack(true);
				}
			}

			@Override
			protected TabbedPropertySheetWidgetFactory getWidgetFactory() {
				return null;
			}

			@Override
			protected Composite getTopComposite() {
				return scrolledComposite;
			}

			@Override
			protected Control createLineComposite(Composite parent, Object object) {


				final InjectionPeriod	injectionPeriod;
				if(object instanceof InjectionPeriod){
					injectionPeriod = (InjectionPeriod) object;
				}else{
					injectionPeriod = SimulationFactory.eINSTANCE.createInjectionPeriod();
				}
				injectionPeriods.add(injectionPeriod);
				Composite lineComposite = new Composite(parent, SWT.NONE);
				GridLayout layout = new GridLayout(5,false);
				layout.horizontalSpacing =10;
				lineComposite.setLayout(layout);

				//----begin date + time
				Label begin = new Label(lineComposite, SWT.NONE);
				begin.setText(Messages.EditSimulationLoadProfileWizardPage_begin);
				final DateTime dateTime = new DateTime(lineComposite, SWT.MEDIUM | SWT.DROP_DOWN);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(injectionPeriod.getBegin());
				if (injectionPeriod.getBegin() != 0) {
					dateTime.setYear(cal.get(Calendar.YEAR));
					dateTime.setMonth(cal.get(Calendar.MONTH));
					dateTime.setDay(cal.get(Calendar.DAY_OF_MONTH));
				}

				dateTime.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
				//hour + minutes
				final DateTime timeTime = new DateTime(lineComposite, SWT.TIME | SWT.SHORT);
				if (injectionPeriod.getBegin() != 0) {
					timeTime.setHours(0);
					timeTime.setMinutes(0);
					timeTime.setSeconds(0) ;
				}

				timeTime.setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), 0) ;
				timeTime.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
				SelectionAdapter listener = new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTimeInMillis(0);
						calendar.set(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(),
								timeTime.getHours(),
								timeTime.getMinutes(),0);
						calendar.set(Calendar.MILLISECOND, 0);
						injectionPeriod.setBegin(calendar.getTimeInMillis());
						if(	getContainer().getCurrentPage()!= null){
							getContainer().updateButtons();
						}
					}
				};
				dateTime.addSelectionListener(listener) ;
				timeTime.addSelectionListener(listener) ;
				//listener.widgetSelected(null) ;

				// ---- repartition
				Label repartitionLabel = new Label(lineComposite, SWT.NONE);
				repartitionLabel.setText(Messages.EditSimulationLoadProfileWizardPage_repartitionType);
				final Combo combo = new Combo(lineComposite, SWT.READ_ONLY | SWT.BORDER);
				final ControlDecoration controlDecoration = new ControlDecoration(combo, SWT.TOP);
				controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
				combo.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
				combo.add(RepartitionType.CONSTANT.getName());
				combo.add(RepartitionType.DIRECT.getLiteral());

				controlDecoration.setDescriptionText(Messages.EditSimulationLoadProfileWizardPage_constantRepartitionType_hint);

				if(injectionPeriod.getRepartition().equals(RepartitionType.CONSTANT)){
					combo.setText(RepartitionType.CONSTANT.getName());
				}
				if(injectionPeriod.getRepartition().equals(RepartitionType.DIRECT)){
					combo.setText(RepartitionType.DIRECT.getName());
				}
				combo.addModifyListener(new ModifyListener() {

					public void modifyText(ModifyEvent e) {
						if(RepartitionType.CONSTANT.getName().equals(combo.getText())){
							injectionPeriod.setRepartition(RepartitionType.CONSTANT);
							controlDecoration.setDescriptionText(Messages.EditSimulationLoadProfileWizardPage_constantRepartitionType_hint);
						}
						if(RepartitionType.DIRECT.getName().equals(combo.getText())){
							injectionPeriod.setRepartition(RepartitionType.DIRECT);
							controlDecoration.setDescriptionText(Messages.EditSimulationLoadProfileWizardPage_directRepartitionType_hint);
						}
					}
				});


				// ----- end date + time
				Label end = new Label(lineComposite, SWT.NONE);
				end.setText(Messages.EditSimulationLoadProfileWizardPage_end);

				cal.setTimeInMillis(injectionPeriod.getEnd());
				final DateTime dateTime2 = new DateTime(lineComposite, SWT.MEDIUM | SWT.DROP_DOWN);
				if (injectionPeriod.getEnd() != 0) {
					dateTime2.setYear(cal.get(Calendar.YEAR));
					dateTime2.setMonth(cal.get(Calendar.MONTH));
					dateTime2.setDay(cal.get(Calendar.DAY_OF_MONTH));
				}
				dateTime2.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
				//hour + minutes
				final DateTime timeTime2 = new DateTime(lineComposite, SWT.TIME | SWT.SHORT);
				if (injectionPeriod.getEnd() != 0) {
					timeTime2.setHours(0);
					timeTime2.setMinutes(0);
					timeTime2.setSeconds(0) ;
				}
				timeTime2.setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), 0) ;



				timeTime2.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());

				SelectionAdapter listener2 = new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						java.util.Calendar calendar = java.util.Calendar.getInstance();
						calendar.set(dateTime2.getYear(), dateTime2.getMonth(), dateTime2.getDay(),
								timeTime2.getHours(),
								timeTime2.getMinutes(),0);
						calendar.set(Calendar.MILLISECOND, 0);
						
						injectionPeriod.setEnd(calendar.getTimeInMillis());
						if(	getContainer().getCurrentPage()!= null){
							getContainer().updateButtons();
						}
					}
				};
				dateTime2.addSelectionListener(listener2);
				timeTime2.addSelectionListener(listener2);
				//	listener2.widgetSelected(null);
				
				if(object == null){
					listener.widgetSelected(null) ;
					listener2.widgetSelected(null);
				}

				// ----- nb instance
				Label nbInstanceLabel = new Label(lineComposite, SWT.NONE);
				nbInstanceLabel.setText(Messages.EditSimulationLoadProfileWizardPage_nbInstance);
				final Text nbInstance = new Text(lineComposite, SWT.BORDER);
				nbInstance.setText(String.valueOf(injectionPeriod.getNbInstances()));//$NON-NLS-1$
				nbInstance.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).create());
				final ControlDecoration controlDecoration2 = new ControlDecoration(nbInstance, SWT.LEFT|SWT.TOP);
				FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
				controlDecoration2.setImage(fieldDecoration.getImage());
				controlDecoration2.setDescriptionText("");
				controlDecoration2.hide();
				nbInstance.addModifyListener(new ModifyListener() {

					public void modifyText(ModifyEvent e) {
						controlDecoration2.hide();

						String text = nbInstance.getText();
						try {
							int nbInstanceInt = Integer.parseInt(text);
							injectionPeriod.setNbInstances(nbInstanceInt);

						} catch (NumberFormatException e2) {
							controlDecoration2.show();
						}
					}
				});

			
				return lineComposite;
			}

			/* (non-Javadoc)
			 * @see org.bonitasoft.studio.common.properties.DynamicAddRemoveLineComposite#createAddButton(org.eclipse.swt.widgets.Composite)
			 */
			@Override
			protected Button createAddButton(Composite parent) {
				Button button = new Button(parent, SWT.FLAT);
				button.setText(Messages.addAPeriod);
				return button;
			}
		};



		dynamicAddRemoveLineComposite.setLayoutData(GridDataFactory.fillDefaults().create());

		for (InjectionPeriod injectionP : loadProfile.getInjectionPeriods()) {
			dynamicAddRemoveLineComposite.addLine(injectionP) ;
		}
		
		if(loadProfile.getInjectionPeriods().isEmpty()){
			InjectionPeriod inj = SimulationFactory.eINSTANCE.createInjectionPeriod() ; 
			Calendar inst = Calendar.getInstance() ;
			inj.setBegin(inst.getTimeInMillis()) ;
			inst.add(Calendar.MONTH,1) ;
			inj.setEnd(inst.getTimeInMillis()) ;
			inj.setNbInstances(100) ;
			inj.setRepartition(RepartitionType.CONSTANT) ;
			dynamicAddRemoveLineComposite.addLine(inj) ;
		}

		updateScrolledComposite();
		setPageComplete(true);
		setControl(scrolledComposite);
	}

	public String getNameText() {
		return nameText.getText();
	}

	public List<InjectionPeriod> getInjectionPeriods(){
		return injectionPeriods;
	}

	/**
	 * Update the size and content of the scrolledComposite
	 */
	private void updateScrolledComposite() {
		scrolledComposite.setMinSize(pageComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(pageComposite);

	}

	@Override
	public boolean isPageComplete() {
		setErrorMessage(null);

		if(getNameText().length()==0){
			setErrorMessage(Messages.EditSimulationResourceWizard_ErrorEmptyName); //$NON-NLS-1$
			return false;
		}

		if(getInjectionPeriods().size() > 1){
			for(int i = 0 ; i< getInjectionPeriods().size()-1; i++){
				Period p =  new Period(getInjectionPeriods().get(i).getBegin(),getInjectionPeriods().get(i).getEnd());
				if(p.getBegin() >= p.getEnd()){
					setErrorMessage(Messages.EditCalendarWizardPage_InconsistentPeriod);
					return false;
				}

				for(int j= i+1 ; j<getInjectionPeriods().size();j++){
					Period next = new Period(getInjectionPeriods().get(j).getBegin(),getInjectionPeriods().get(i).getEnd());
					if(p.overlaps(next)){
						setErrorMessage(Messages.EditCalendarWizardPage_OverlapingPeriods);
						return false;
					}
				}
			}
		}
		for(InjectionPeriod ip : getInjectionPeriods()){
			Period p =  new Period(ip.getBegin(),ip.getEnd());
			if(p.getBegin() >= p.getEnd()){
				setErrorMessage(Messages.EditCalendarWizardPage_InconsistentPeriod);
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

		if( !getNameText().equals(loadProfile.getName()) && loadProfileStore.getChild(getNameText()+"."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT) != null){
			setErrorMessage(Messages.EditSimulationResourceWizard_ErrorSameName); //$NON-NLS-1$
			return false;
		}

		if(injectionPeriods.isEmpty()){
			setErrorMessage(Messages.EditSimulationResourceWizard_ErrorNoInjectionPeriod); //$NON-NLS-1$
			return false ;
		}

		return super.isPageComplete();
	}

}
