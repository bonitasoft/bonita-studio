/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Aurelien Pupier
 *
 */
public class MigrationStatusView extends ViewPart {
	
	public static String ID = "org.bonitasoft.studio.migration.view";
	
	
	@Override
	public void createPartControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.None);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());
		
		createTopComposite(mainComposite);
		createTableComposite(mainComposite);
		createBottomComposite(mainComposite);
		
		
	}

	private void createBottomComposite(Composite mainComposite) {
		Composite bottomComposite = new Composite(mainComposite, SWT.NONE);
		bottomComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		
		createPrintButton(bottomComposite);
		createMarkAsCompletedButton(bottomComposite);
		
	}

	private void createMarkAsCompletedButton(Composite bottomComposite) {
		Button markAsCompletedButton = new Button(bottomComposite, SWT.NONE);
		markAsCompletedButton.setText("Complete Import");
		markAsCompletedButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);				
				MessageDialogWithToggle mdwt = MessageDialogWithToggle.openYesNoQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Complete import", "Completing the import will remove the import status report from your repository.\n Do you want to continue?", "Export the import status report", true, MigrationPlugin.getDefault().getPreferenceStore(), "toggleStateForImportExportStatus");
				if(IDialogConstants.YES_ID == mdwt.getReturnCode()){
					
					if(mdwt.getToggleState()){
						FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.SAVE);
						String filePath = fd.open();
						if(filePath != null){
							//TODO : write the report to teh specified filename
						}
					}
					//TODO : remove the migration report artifact
				}
				
			}
		});
	}

	private void createTableComposite(Composite mainComposite) {
		Composite tableComposite = new Composite(mainComposite, SWT.NONE);
		tableComposite.setLayout(GridLayoutFactory.fillDefaults().create());
		tableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		
		
		TableViewer tableViewer = new TableViewer(tableComposite);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(new Object());
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				Object selection = ((IStructuredSelection)event.getSelection()).getFirstElement();
				// TODO retrieve the selected element and set the focus in the editor for it
				
			}
		});
		
		tableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
	}

	private void createTopComposite(Composite mainComposite) {
		Composite topComposite = new Composite(mainComposite, SWT.NONE);
		topComposite.setLayout(GridLayoutFactory.fillDefaults().create());
		topComposite.setLayoutData(GridDataFactory.fillDefaults().create());
		
		createFilterComposite(topComposite);
		
	}

	private void createFilterComposite(Composite topComposite) {
		Text findText = new Text(topComposite, SWT.BORDER);
		findText.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).align(SWT.RIGHT, SWT.CENTER).hint(100, SWT.DEFAULT).create());
		findText.setMessage("Find...");
		
	}

	private void createPrintButton(Composite topComposite) {
		Button printButton = new Button(topComposite, SWT.FLAT);
		printButton.setText("Export");
		printButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				
			}
		});
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	
	

}
