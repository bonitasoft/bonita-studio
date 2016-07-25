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
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import org.bonitasoft.simulation.model.Period;
import org.bonitasoft.studio.common.properties.DynamicAddRemoveLineComposite;
import org.bonitasoft.studio.model.simulation.DayPeriod;
import org.bonitasoft.studio.model.simulation.SimulationCalendar;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 *
 */
public class EditCalendarWizardPage extends WizardPage {
	private SimulationCalendar simulationCalendar = null;
	private DataBindingContext context;
	protected List<Composite> daysComposites = new ArrayList<Composite>();
	protected List<PeriodWidget> periods = new ArrayList<PeriodWidget>();
	private ScrolledComposite scrolledComposite;

	/**
	 * @param pageName
	 */
	protected EditCalendarWizardPage() {
		super("editCalendar"); //$NON-NLS-1$
		setMessage(Messages.EditCalendarWizardPage_Message);
		setTitle(Messages.EditCalendarWizardPage_Title);
	}

	/**
	 * @param simulationCalendar
	 */
	public EditCalendarWizardPage(SimulationCalendar simulationCalendar) {
		this();
		this.simulationCalendar = simulationCalendar;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(final Composite parent) {
		context = new DataBindingContext();


		scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);

		scrolledComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).align(SWT.FILL, SWT.TOP).hint(SWT.DEFAULT, 150).create());

		final Composite pageComposite = new Composite(scrolledComposite, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));
		DynamicAddRemoveLineComposite periodComposite = new DynamicAddRemoveLineComposite(pageComposite, SWT.None) {


			@Override
			protected void lineRemoved(int i) {
				daysComposites.remove(i);
				periods.remove(i);
				updateScrolledComposite(pageComposite);
				getContainer().updateButtons() ;
			}

			@Override
			protected void lineAdded(int i) {
				updateScrolledComposite(pageComposite);
				if(getContainer().getCurrentPage() != null){
					getContainer().updateButtons() ;
				}
				if(i == 0){
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
				Group mainComposite = new Group(parent, SWT.NONE);
				mainComposite.setLayout(new GridLayout(7,false));
				Composite daysComposite = new Composite(mainComposite, SWT.NONE);
				daysComposites.add(daysComposite);
				daysComposite.setLayout(new GridLayout(2, false));
				createDayCheckBox(daysComposite,Messages.Monday);
				createDayCheckBox(daysComposite,Messages.Tuesday);
				createDayCheckBox(daysComposite,Messages.Wednesday);
				createDayCheckBox(daysComposite,Messages.Thursday);
				createDayCheckBox(daysComposite,Messages.Friday);
				createDayCheckBox(daysComposite,Messages.Saturday);
				createDayCheckBox(daysComposite,Messages.Sunday);

				PeriodWidget periodWidget = new PeriodWidget(mainComposite, SWT.None);
				periodWidget.setAllDay();
				periodWidget.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						if(getContainer().getCurrentPage() != null){
							getContainer().updateButtons();
						}
					}
				});
				periods.add(periodWidget);



				//initialize the widget
				if(object instanceof DayPeriod){
					DayPeriod period = (DayPeriod) object;
					Calendar calendar = Calendar.getInstance();

					int i = calendar.getMinimum(Calendar.DAY_OF_WEEK);
					for (Control button : daysComposite.getChildren()) {
						if (button instanceof Button) {
							Button check = (Button) button;
							switch(i){
							case 1:check.setSelection(period.getDay().contains(Calendar.MONDAY));break;
							case 2:check.setSelection(period.getDay().contains(Calendar.TUESDAY));break;
							case 3:check.setSelection(period.getDay().contains(Calendar.WEDNESDAY));break;
							case 4:check.setSelection(period.getDay().contains(Calendar.THURSDAY));break;
							case 5:check.setSelection(period.getDay().contains(Calendar.FRIDAY));break;
							case 6:check.setSelection(period.getDay().contains(Calendar.SATURDAY));break;
							case 7:check.setSelection(period.getDay().contains(Calendar.SUNDAY));break;
							}

							i++;
						}
					}
					if(period.getStartHour() == 0 && period.getStartMinute() == 0 
							&& period.getEndHour() == 0 && period.getEndMinute() == 0){
						periodWidget.setAllDay();
					}else{
						periodWidget.setStartingHour(period.getStartHour());
						periodWidget.setStartingMinute(period.getStartMinute());
						periodWidget.setEndingHour(period.getEndHour());
						periodWidget.setEndingMinute(period.getEndMinute());
					}
				}

				return mainComposite;
			}

			private void createDayCheckBox(Composite daysComposite,String name) {
				Button check = new Button(daysComposite, SWT.CHECK);
				check.setSelection(true);
				check.setText(name);
				check.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if(getContainer().getCurrentPage() != null){
							getContainer().updateButtons();
						}
					}
				});
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
		periodComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		setControl(scrolledComposite);
		updateScrolledComposite(pageComposite);
		if(simulationCalendar != null){

			for (DayPeriod period : simulationCalendar.getDaysOfWeek()) {
				periodComposite.addLine(period);
			}
		}else{

				DayPeriod period = SimulationFactory.eINSTANCE.createDayPeriod() ;
				period.getDay().add(Calendar.MONDAY) ;
				period.getDay().add(Calendar.TUESDAY) ;
				period.getDay().add(Calendar.WEDNESDAY) ;
				period.getDay().add(Calendar.THURSDAY) ;
				period.getDay().add(Calendar.FRIDAY) ;
				period.getDay().add(Calendar.SATURDAY) ;
				period.getDay().add(Calendar.SUNDAY) ;
				periodComposite.addLine(period);
			
		}
		scrolledComposite.update() ;

	}
	
//	@Override
//	public void setVisible(boolean visible) {
//		super.setVisible(visible);
//		scrolledComposite.update() ;
//	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {
		if (context != null){
			context.dispose();
		}
	}

	/**
	 * @return
	 */
	public Collection<? extends DayPeriod> getDays() {
		Calendar calendar = Calendar.getInstance();
		List<DayPeriod> list = new ArrayList<DayPeriod>();

		for (int j = 0; j< daysComposites.size(); j++) {
			Composite dayComposite = daysComposites.get(j);
			int i = calendar.getMinimum(Calendar.DAY_OF_WEEK);
			DayPeriod period = SimulationFactory.eINSTANCE.createDayPeriod();

			period.setStartHour(periods.get(j).getStartingHour());
			period.setStartMinute(periods.get(j).getStartingMinute());
			period.setEndHour(periods.get(j).getEndingHour());
			period.setEndMinute(periods.get(j).getEndingMinute());


			for (Control child : dayComposite.getChildren()) {
				if(child instanceof Button){
					if(((Button) child).getSelection()){
						switch(i){
						case 1 :  period.getDay().add(Calendar.MONDAY);break;
						case 2 : period.getDay().add(Calendar.TUESDAY);break;
						case 3 : period.getDay().add(Calendar.WEDNESDAY);break;
						case 4 : period.getDay().add(Calendar.THURSDAY);break;
						case 5 : period.getDay().add(Calendar.FRIDAY);break;
						case 6 : period.getDay().add(Calendar.SATURDAY);break;
						case 7 : period.getDay().add(Calendar.SUNDAY);break;
						}

					}
					i++;
				}
			}
			if(period.getDay().size()>0){
				list.add(period);
			}
		}
		return list;
	}

	@Override
	public boolean isPageComplete() {
		setErrorMessage(null) ;

		if(getDays().isEmpty()){
			setErrorMessage(Messages.EditCalendarWizardPage_OnePeriodAtLeastDefined);
			return false ;
		}

		List<Period> periods = new ArrayList<Period>();
		Calendar c = GregorianCalendar.getInstance() ;
		c.set(2010, 6, 5, 0, 0,0) ;
		c.set(Calendar.MILLISECOND, 0) ;

		long initalTime = c.getTimeInMillis() ;

		for(DayPeriod dp : getDays()){
			for(int d : dp.getDay()){
				while(c.get(Calendar.DAY_OF_WEEK) != d){
					c.add(Calendar.DAY_OF_WEEK, 1);
				}
				c.set(Calendar.HOUR_OF_DAY, dp.getStartHour()) ;
				c.set(Calendar.MINUTE, dp.getStartMinute());
				long startPeriod = c.getTimeInMillis() ;

				long endPeriod = 0 ;
				if(dp.getEndHour() == 0){
					c.add(Calendar.DAY_OF_WEEK, 1);
					c.set(Calendar.HOUR_OF_DAY,0) ;
					c.set(Calendar.MINUTE, 0);
					endPeriod = c.getTimeInMillis() ;
				}else{
					c.set(Calendar.HOUR_OF_DAY,dp.getEndHour()) ;
					c.set(Calendar.MINUTE, dp.getEndMinute());
					endPeriod = c.getTimeInMillis() ;
				}
				periods.add(new Period(startPeriod, endPeriod));
				c.setTimeInMillis(initalTime) ;
			}
		}

		for(int i = 0 ; i< periods.size()-1; i++){
			Period p =  periods.get(i);
			for(int j= i+1 ; j<periods.size();j++){
				if(p.overlaps(periods.get(j))){
					setErrorMessage(Messages.EditCalendarWizardPage_OverlapingPeriods);
					return false;
				}
			}
		}

		return super.isPageComplete();
	}

	/**
	 * Update the size and content of the scrolledComposite
	 */
	private void updateScrolledComposite(Composite content) {
		scrolledComposite.setMinSize(content.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(content);
	}
}
