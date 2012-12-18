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
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 * 
 */
public class AdvancedExportChooseApplicationsWizardPage extends WizardPage {

	private ExportableContentProvider exportEntriesContentProvider;
	private List<ExportEntry> selectedProc = new ArrayList<ExportEntry>() ;
	private List<AbstractProcess> openProcesses ; 

	protected AdvancedExportChooseApplicationsWizardPage(List<AbstractProcess> openProcesses) {
		super(AdvancedExportWizard.class.getName());
		this.setTitle(Messages.exportWarWizardTitle);
		this.setDescription(Messages.exportWarWizardDescription);
		this.setMessage(Messages.exportWarWizardMessage);
		setImageDescriptor(Pics.getWizban());
		this.openProcesses = openProcesses ;
	}

	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Label chooseApplications = new Label(composite, SWT.NONE) ;
		chooseApplications.setText(Messages.chooseApplicationToExport) ;
		createProcessGroup(composite);
		this.setControl(composite);

	}

	/**
	 * @param composite
	 */
	private void createProcessGroup(final Composite composite) {
		final AdvancedExportWizard wiz = (AdvancedExportWizard)getWizard() ;		


		final CheckboxTreeViewer tree = new CheckboxTreeViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SCROLL_LINE | SWT.V_SCROLL);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true) ;
		gd.heightHint = 250 ;
		tree.getControl().setLayoutData(gd);
		tree.getTree().setHeaderVisible(true);
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
		tree.setContentProvider(exportEntriesContentProvider);
		tree.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent event) {
				selectedProc.clear() ;
				boolean userXPChecked = false ;
				boolean restWarChecked = false ;
				for(Object p : tree.getCheckedElements()){
					if(p instanceof ExportEntry){
						ExportEntry entry = (ExportEntry) p ;
						entry.setExportWAR(true);
						if(entry.getExportArtifact() != null){
							entry.setExportBAR(true) ;
							selectedProc.add(entry) ;
						} else if (entry.equals(exportEntriesContentProvider.getUserXPEntry())){
							userXPChecked = true ;
						} else if (entry.equals(exportEntriesContentProvider.getRestWarEntry())){
							restWarChecked = true ;
						}
					}
				}
				if(!userXPChecked){
					exportEntriesContentProvider.getUserXPEntry().setExportWAR(false) ;
				}
				wiz.updateExportConfiguration(selectedProc,exportEntriesContentProvider.getUserXPEntry().getExportWAR(),exportEntriesContentProvider.getRestWarEntry().getExportWAR()) ;
			}
		}) ;
		tree.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				if (element == exportEntriesContentProvider.getUserXPEntry()) {
					return Messages.userXP;
				}
				if (element == ExportableContentProvider.MY_PROCESSES_CATEGORY) {
					return Messages.myProcessCategory;
				}
				AbstractProcess process = ((ExportEntry)element).getExportArtifact() ;
				return NLS.bind(Messages.processLabel, process.getName(), process.getVersion());

			}

		}) ;
		tree.setInput(new Object());
		tree.setChecked(exportEntriesContentProvider.getUserXPEntry(), true);
		tree.setChecked(exportEntriesContentProvider.getRestWarEntry(), true);
		for(AbstractProcess proc :  openProcesses){
			ExportEntry entry = exportEntriesContentProvider.getEntry(proc) ; 
			if(entry != null){
				tree.setChecked(entry,true) ;
				selectedProc.add(entry) ;
			}
		}

		wiz.updateExportConfiguration(selectedProc, true, true) ;
		tree.refresh();
		//tree.getViewer().setExpandedState(ExportableContentProvider.MY_PROCESSES_CATEGORY, true);
		//wiz.updateExportConfiguration(Collections.EMPTY_LIST, false);
	}

	@Override
	public IWizardPage getNextPage() {
		((AdvancedExportWizard)getWizard()).updateExportConfiguration(selectedProc,exportEntriesContentProvider.getUserXPEntry().getExportWAR(), exportEntriesContentProvider.getRestWarEntry().getExportWAR()) ;
		return super.getNextPage();
	}


	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}


	/**
	 * @return
	 */
	public List<AbstractProcess> getProcessesToExport() {
		List<AbstractProcess> res = new ArrayList<AbstractProcess>();
		for (ExportEntry entry : exportEntriesContentProvider.getAllProcessEntries()) {
			if (entry.getExportBAR() && entry.getExportArtifact() != null) {
				res.add(entry.getExportArtifact());
			}
		}
		return res;
	}

	/**
	 * @return
	 */
	public List<AbstractProcess> getProcessesToExportAsWar() {
		List<AbstractProcess> res = new ArrayList<AbstractProcess>();
		for (ExportEntry entry : exportEntriesContentProvider.getAllProcessEntries()) {
			if (entry.getExportWAR() && entry.getExportArtifact() != null) {
				res.add(entry.getExportArtifact());
			}
		}
		return res;
	}

	/**
	 * @return
	 */
	public boolean getExportUserXP() {
		return exportEntriesContentProvider.getUserXPEntry().getExportWAR();
	}

	public boolean getExportRestWar() {
		return exportEntriesContentProvider.getRestWarEntry().getExportWAR();
	}
	
	/**
	 * This method is intended to be overriden by child classes.
	 * @param parent
	 */
	public void createAdditionalSection(Composite parent) {
	}

}
