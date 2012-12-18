/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

package org.bonitasoft.studio.application.actions.wizards.export;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 * 
 */
public class AdvancedExportQuestionWizardPage extends WizardPage {	

	private AdvancedExportRecapWizardPage recapPage;
	private List<AbstractProcess> openProcesses;
	private ExportableContentProvider exportEntriesContentProvider;

	protected AdvancedExportQuestionWizardPage(List<AbstractProcess> openProcesses,AdvancedExportRecapWizardPage recapPage) {
		super(AdvancedExportWizard.class.getName());
		this.setTitle(Messages.exportWarWizardTitle);
		this.setDescription(Messages.exportWarWizardDescription);
		this.setMessage(Messages.exportWarWizardMessage);
		this.recapPage = recapPage ;
		this.openProcesses = openProcesses ;
		setImageDescriptor(Pics.getWizban());
	}

	public void createControl(Composite parent) {
	
		
		Composite mainComposite = new Composite(parent,SWT.NONE) ;
		mainComposite.setLayout(new GridLayout(1, false));
		
		final Label info = new Label(mainComposite,SWT.WRAP ) ;
		info.setText(Messages.advancedExportInfo) ;
		info.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).hint(200, 50).create()) ;
		final Group group = new Group(mainComposite, SWT.NONE);

		group.setLayout(new GridLayout(1, false));
		group.setLayoutData(new GridData(GridData.FILL,SWT.DEFAULT,true,false)) ;
		
		final Label  questionExportEngine = new Label(group, SWT.NONE) ;
		questionExportEngine.setText(Messages.questionExportEngine) ;
		
		final Button[] questionExportEngineButtons = createYesNoRadioComposite(group) ;
		questionExportEngineButtons[0].setSelection(false) ;
		questionExportEngineButtons[1].setSelection(true) ;
		
		Label questionSameAppAndEngine = new Label(group, SWT.NONE) ;
		questionSameAppAndEngine.setText(Messages.questionSameAppAndEngine) ;
		
		final Button[] questionSameAppAndEngineButtons = createYesNoRadioComposite(group) ;
		questionSameAppAndEngineButtons[0].setSelection(true) ;
		questionSameAppAndEngineButtons[1].setSelection(false) ;
		
		final Label  questionManyAppOnServer = new Label(group, SWT.NONE) ;
		questionManyAppOnServer.setText(Messages.questionManyAppOnServer) ;
		
		final Button[] questionManyAppOnServerButtons = createYesNoRadioComposite(group) ;
		questionManyAppOnServerButtons[0].setSelection(true) ;
		questionManyAppOnServerButtons[1].setSelection(false) ;

		questionSameAppAndEngineButtons[0].addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				questionManyAppOnServer.setVisible(questionSameAppAndEngineButtons[0].getSelection()) ;
				questionManyAppOnServerButtons[0].setVisible(questionSameAppAndEngineButtons[0].getSelection()) ;
				questionManyAppOnServerButtons[1].setVisible(questionSameAppAndEngineButtons[0].getSelection()) ;
				AdvancedExportWizard wiz = (AdvancedExportWizard)getWizard() ;
				wiz.updateExportConfiguration(questionSameAppAndEngineButtons[1].getSelection(),questionManyAppOnServerButtons[0].getSelection()) ;
			}
		});

		questionManyAppOnServerButtons[0].addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AdvancedExportWizard wiz = (AdvancedExportWizard)getWizard() ;
				wiz.updateExportConfiguration(questionSameAppAndEngineButtons[1].getSelection(),questionManyAppOnServerButtons[0].getSelection()) ;
			}
		});
		
		questionExportEngineButtons[0].addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AdvancedExportWizard wiz = (AdvancedExportWizard)getWizard() ;
				wiz.updateExportRuntimeConfiguration(questionExportEngineButtons[0].getSelection()) ;
			}
		});
		
		Button skipButton =	new Button(mainComposite, SWT.FLAT) ;
		skipButton.setText(Messages.skip) ;
		skipButton.setLayoutData(new GridData(GridData.END, SWT.DEFAULT, false, false)) ;
		
		exportEntriesContentProvider = new ExportableContentProvider(){
			public Object[] getElements(Object inputElement) {
				List<ExportEntry> list = new ArrayList<ExportEntry>() ;
				list.add(userXP);
				for(ExportEntry e : getAllProcessEntries()){
					list.add(e) ;
				}
				return list.toArray();
			};
		};
		
		skipButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {	
				for(AbstractProcess proc :  openProcesses){
					ExportEntry entry = exportEntriesContentProvider.getEntry(proc) ; 
					if(entry != null){
						entry.setExportBAR(true) ;
						entry.setExportWAR(true) ;
					}
				}
				((AdvancedExportWizard)getWizard()).updateExportConfiguration(Arrays.asList(exportEntriesContentProvider.getAllProcessEntries()),true, true) ;
				getContainer().showPage(recapPage) ;
			}
		});
		
		this.setControl(mainComposite);

	}

	private Button[] createYesNoRadioComposite(Composite parent) {
		Composite radioComposite = new Composite(parent, SWT.NONE) ;
		radioComposite.setLayout(new GridLayout(2, false));
		Button yesButton = new Button(radioComposite,SWT.RADIO) ;
		yesButton.setText(Messages.yes) ;
		Button noButton = new Button(radioComposite,SWT.RADIO) ;
		noButton.setText(Messages.no) ;
		return new Button[]{yesButton,noButton};
	} 



}
