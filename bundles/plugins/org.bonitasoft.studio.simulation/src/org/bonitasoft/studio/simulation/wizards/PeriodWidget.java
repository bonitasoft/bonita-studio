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

import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;


/**
 * @author Romain
 *
 */
public class PeriodWidget extends Composite {

	private DateTime startTime;
	private DateTime endTime;
	private Button allDayRadio;
	private Button periodRadio;

	public PeriodWidget(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(6, false)) ;
		allDayRadio = new Button(this, SWT.RADIO);
		allDayRadio.setText(Messages.allDayPeriod) ;
		allDayRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(allDayRadio.getSelection()){
					startTime.setEnabled(false);
					endTime.setEnabled(false);
				}else{
					startTime.setEnabled(true);
					endTime.setEnabled(true);
				}
			}

		
		});

		periodRadio = new Button(this, SWT.RADIO);
		periodRadio.setText(Messages.chooseAPeriod) ;
		periodRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(periodRadio.getSelection()){
					startTime.setEnabled(true);
					endTime.setEnabled(true);
				}else{
					startTime.setEnabled(false);
					endTime.setEnabled(false);
				}
			}
			
		
		});

		new Label(this,SWT.NONE).setText(Messages.from);
		startTime = new DateTime(this, SWT.BORDER | SWT.TIME );
		startTime.setSeconds(0);
		startTime.setHours(8);
		startTime.setMinutes(0);
		startTime.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if(startTime.getSeconds() != 0){
					startTime.setSeconds(0);
				}
				if(startTime.getHours() > endTime.getHours()){
					endTime.setHours(startTime.getHours());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		new Label(this,SWT.NONE).setText(Messages.to);
		endTime = new DateTime(this,SWT.BORDER | SWT.TIME ) ;
		endTime.setMinutes(0);
		endTime.setHours(19);
	
		endTime.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if(endTime.getHours() == 0){
					endTime.setHours(23);
				}
				if(endTime.getHours() < startTime.getHours()){
					endTime.setHours(startTime.getHours());
				}
				if(endTime.getSeconds() != 0){
					endTime.setSeconds(0);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		endTime.setSeconds(0);
	

	}

	public int getStartingHour(){
		if(periodRadio.getSelection()){
			return startTime.getHours();
		}else{
			return 0 ;
		}
	}

	public int getStartingMinute(){
		if(periodRadio.getSelection()){
			return startTime.getMinutes();
		}else{
			return 0 ;
		}
	}

	public int getEndingHour(){
		if(periodRadio.getSelection()){
			return endTime.getHours();
		}else{
			return 0 ;
		}
	}

	public int getEndingMinute(){
		if(periodRadio.getSelection()){
			return endTime.getMinutes();
		}else{
			return 0;
		}
	}

	public void setStartingHour(int hour){
		if(hour != 0){
			periodRadio.setSelection(true);
			allDayRadio.setSelection(false);
			startTime.setEnabled(true);
			endTime.setEnabled(true);
		}
		startTime.setHours(hour);
	}

	public void setStartingMinute(int minute){
		if(minute != 0){
			periodRadio.setSelection(true);
			allDayRadio.setSelection(false);
			startTime.setEnabled(true);
			endTime.setEnabled(true);
		}
		startTime.setMinutes(minute);
	}

	public void setEndingHour(int hour){
		if(hour != 0){
			periodRadio.setSelection(true);
			allDayRadio.setSelection(false);
			startTime.setEnabled(true);
			endTime.setEnabled(true);
		}
		endTime.setHours(hour);
	}

	public void setEndingMinute(int minute){
		if(minute != 0){
			periodRadio.setSelection(true);
			allDayRadio.setSelection(false);
			startTime.setEnabled(true);
			endTime.setEnabled(true);
		}
		endTime.setMinutes(minute);
	}

	public void	setAllDay(){
		allDayRadio.setSelection(true);
		periodRadio.setSelection(false);
		startTime.setEnabled(false);
		endTime.setEnabled(false);
	}

	public void addSelectionListener(SelectionListener listener) {
		startTime.addSelectionListener(listener);
		endTime.addSelectionListener(listener);
		allDayRadio.addSelectionListener(listener);
		periodRadio.addSelectionListener(listener);
	}

}
