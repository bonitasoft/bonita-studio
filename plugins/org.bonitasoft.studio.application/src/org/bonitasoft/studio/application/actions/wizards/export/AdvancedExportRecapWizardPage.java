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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.exporter.application.service.IWarFactory.ExportMode;
import org.bonitasoft.studio.exporter.runtime.RuntimeExportMode;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Romain Bioteau
 * 
 */
public class AdvancedExportRecapWizardPage extends WizardPage {

	private class MyCheckboxCellEditor extends CheckboxCellEditor {
		public MyCheckboxCellEditor(Composite parent) {
			super(parent);
		}

		protected void doSetValue(Object element) {
			super.doSetValue(element);
		}


		public void activate() {
			super.activate();
			deactivate();
			Display.getCurrent().getFocusControl().redraw();
		}
	}


	private static final String HOME_DIR = System.getProperty("user.home"); //$NON-NLS-1$
	private Text targetFolder;
	private Button exportRuntimeButton;
	private Group processesGroup;
	private Button jeeExportButtonForProcesses;
	private Button heavyExportButtonForProcesses;
	private Button lightExportModeForProcesses;
	private Label labelExportMode;
	private Button browseButton;
	private Composite locationComposite;
	private ExportableContentProvider exportEntriesContentProvider;
	private FilteredTree tree;
	private DataBindingContext context;
	private Button fullModeButton;
	private Button ejb2ModeButton;
	private Button ejb3ModeButton;
	private Button restModeButton;
	private Composite runtimeModeComposite;


	protected AdvancedExportRecapWizardPage() {
		super(AdvancedExportWizard.class.getName());
		this.setTitle(Messages.exportWarWizardTitle);
		this.setDescription(Messages.exportWarWizardDescription);
		this.setMessage(Messages.exportWarWizardMessage);
		setImageDescriptor(Pics.getWizban());
		exportEntriesContentProvider = new ExportableContentProvider();
	}

	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData gd;



		createRuntimeGroup(composite);
		createProcessGroup(composite);


		Label locationLabel = new Label(composite, SWT.NONE);
		locationLabel.setText(Messages.targetFolderLabel);

		locationComposite = new Composite(composite, SWT.NONE);
		locationComposite.setLayout(new GridLayout(3, false));
		gd = new GridData(GridData.FILL, GridData.FILL, true, false);
		locationComposite.setLayoutData(gd);

		targetFolder = new Text(locationComposite, SWT.BORDER);
		gd = new GridData(GridData.FILL, GridData.CENTER, true, false);
		targetFolder.setLayoutData(gd);
		String basePath = HOME_DIR + File.separator + Messages.defaultExportFolder;
		String path = basePath;
		int i = 1;
		while (new File(path).exists()) {
			path = basePath + i;
			i++;
		}
		targetFolder.setText(path); //$NON-NLS-1$
		targetFolder.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				getContainer().updateButtons();
			}
		});

		browseButton = new Button(locationComposite, SWT.FLAT);
		browseButton.setText(Messages.browseLabel);
		browseButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog fd = new DirectoryDialog(locationComposite.getShell(), SWT.SAVE);
				fd.setFilterPath(Messages.defaultExportFolder);
				fd.setText(Messages.exportWarWizardTitle);

				String location = fd.open();

				if (location != null) {
					targetFolder.setText(location);
				}
				getContainer().updateButtons();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		createAdditionalSection(composite);

		//	getContainer().updateButtons();
		this.setControl(composite);

	}

	/**
	 * @param composite
	 */
	private void createProcessGroup(final Composite composite) {
		GridData gd;
		processesGroup = new Group(composite, SWT.NONE);
		processesGroup.setText(Messages.processesLabel);
		processesGroup.setLayout(new GridLayout(1, false));

		gd = new GridData(SWT.FILL, SWT.DEFAULT, true, false);
		gd.heightHint = 350;
		processesGroup.setLayoutData(gd);
		final TreeViewer viewer = createTreeViewer();

		Composite buttonComposite2 = new Composite(processesGroup, SWT.NONE);
		GridData radioLayoutData = new GridData(SWT.DEFAULT, SWT.FILL, false, false);
		buttonComposite2.setLayoutData(radioLayoutData);
		buttonComposite2.setLayout(new RowLayout(SWT.HORIZONTAL));



		labelExportMode = new Label(buttonComposite2, SWT.NONE);
		labelExportMode.setText(Messages.libExportMode);
		new Label(buttonComposite2, SWT.NONE).setText(" "); //$NON-NLS-1$
		lightExportModeForProcesses = createExportmodeButtonForProcess(buttonComposite2, Messages.lightModeLabel, Messages.lightModeTooltip);


		ControlDecoration cd = new ControlDecoration(lightExportModeForProcesses, SWT.TOP | SWT.LEFT) ;
		cd.setImage(Pics.getImage(PicsConstants.hint)) ;
		cd.setDescriptionText(Messages.lightModeTooltip+"\n"+Messages.embeddedModeTooltip+"\n"+Messages.jeeModeTooltip) ;
		cd.setShowOnlyOnFocus(false) ;

		heavyExportButtonForProcesses = createExportmodeButtonForProcess(buttonComposite2, Messages.EmbeddedMode, Messages.embeddedModeTooltip);

		jeeExportButtonForProcesses = createExportmodeButtonForProcess(buttonComposite2, Messages.jeeMode, Messages.jeeModeTooltip);
		

		lightExportModeForProcesses.setSelection(true);

		IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (part instanceof ProcessDiagramEditor) {
			DiagramEditPart diagram = ((DiagramDocumentEditor)part).getDiagramEditPart();
			AbstractProcess process = (AbstractProcess) diagram.resolveSemanticElement();
			if(process instanceof MainProcess){
				for(Element el : process.getElements()){
					if(el instanceof AbstractProcess){
						ExportEntry entry = exportEntriesContentProvider.getEntry((AbstractProcess)el); 
						if(entry != null){
							entry.setExportWAR(true);
							entry.setExportBAR(true);
						}
					}
				}
			}else{
				ExportEntry entry = exportEntriesContentProvider.getEntry(process); 
				entry.setExportWAR(true);
				entry.setExportBAR(true);
			}
			viewer.refresh();

		}

	}

	protected Button createExportmodeButtonForProcess(Composite buttonComposite, String buttonText, String tooltipText) {
		Button button = new Button(buttonComposite, SWT.RADIO);
		button.setToolTipText(tooltipText);
		button.setText(buttonText);
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				getContainer().updateButtons();
				updateButton();
			}

		});
		return button;
	}

	protected TreeViewer createTreeViewer() {
		tree = new FilteredTree(processesGroup, SWT.BORDER | SWT.FULL_SELECTION | SWT.SCROLL_LINE | SWT.V_SCROLL, new PatternFilter(),true);
		GridData treeLayoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
	
		treeLayoutData.heightHint = 250;
		final TreeViewer viewer = tree.getViewer();
		viewer.getControl().setLayoutData(treeLayoutData);
		viewer.getTree().setHeaderVisible(true);
		viewer.setContentProvider(exportEntriesContentProvider);
		
		createTreeColumns(viewer);
		
		CellEditor[] editors = new CellEditor[3];
		editors[0] = null;
		editors[1] = new MyCheckboxCellEditor(viewer.getTree());
		editors[2] = new MyCheckboxCellEditor(viewer.getTree());
		
		viewer.setCellEditors(editors);
		viewer.setCellModifier(new ICellModifier() {

			public void modify(Object element, String property, Object value) {
				TreeItem item = (TreeItem)element ;
				if(property.equals(Messages.exportAsWAR)){
					exportEntriesContentProvider.getElement(item.getText()).setExportWAR((Boolean)value) ;

				}else if(property.equals(Messages.exportAsBAR)){
					exportEntriesContentProvider.getElement(item.getText()).setExportBAR((Boolean)value) ;
				}
				boolean state = viewer.getExpandedState(ExportableContentProvider.MY_PROCESSES_CATEGORY) ;
				viewer.setInput(new Object()) ;
				viewer.setExpandedState(ExportableContentProvider.MY_PROCESSES_CATEGORY, state);
			}

			public Object getValue(Object element, String property) {
				if(property.equals(Messages.exportAsWAR)){
					return ((ExportEntry)element).getExportWAR() ;
				}else if(property.equals(Messages.exportAsBAR)){
					return ((ExportEntry)element).getExportBAR() ;
				}
				return null;
			}

			public boolean canModify(Object element, String property) {
				if(property.equals(Messages.exportAsWAR)){
					return !element.equals(ExportableContentProvider.MY_PROCESSES_CATEGORY);
				}else if(property.equals(Messages.exportAsBAR)){
					return !element.equals(ExportableContentProvider.MY_PROCESSES_CATEGORY) &&
						!element.equals(exportEntriesContentProvider.getUserXPEntry()) &&
						!element.equals(exportEntriesContentProvider.getRestWarEntry());
				}else{
					return false ;
				}
			}
		}) ;
		viewer.setLabelProvider(new CheckboxLabelProvider(viewer,exportEntriesContentProvider)) ;
		viewer.setInput(new Object());
		viewer.setExpandedState(ExportableContentProvider.MY_PROCESSES_CATEGORY, true);
		return viewer;
	}

	protected void createTreeColumns(final TreeViewer viewer) {
		TreeColumn nameColumn = new TreeColumn(viewer.getTree(), SWT.NONE);
		nameColumn.setWidth(200);
		nameColumn.setMoveable(true);
		nameColumn.setResizable(true);

		TreeColumn exportAsBarColum = new TreeColumn(viewer.getTree(), SWT.NONE);
		exportAsBarColum.setText(Messages.exportAsBAR);
		exportAsBarColum.setWidth(150);
		exportAsBarColum.setMoveable(true);
		exportAsBarColum.setResizable(true);

		TreeColumn exportAsWarColumn = new TreeColumn(viewer.getTree(), SWT.NONE);
		exportAsWarColumn.setText(Messages.exportAsWAR);
		exportAsWarColumn.setWidth(150);
		exportAsWarColumn.setMoveable(true);
		exportAsWarColumn.setResizable(true);

		String columnProperties[] = {"", Messages.exportAsBAR, Messages.exportAsWAR };
		viewer.setColumnProperties(columnProperties);
	}


	/**
	 * @param composite
	 */
	private void createRuntimeGroup(final Composite composite) {
		GridData gd;
		Group runtimeGroup = new Group(composite, SWT.NONE);
		runtimeGroup.setText(Messages.runtimeLabel);
		runtimeGroup.setLayout(new GridLayout(2, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		runtimeGroup.setLayoutData(gd);

		exportRuntimeButton = new Button(runtimeGroup, SWT.CHECK);
		exportRuntimeButton.setSelection(false);
		exportRuntimeButton.setText(Messages.exportRuntime);
		exportRuntimeButton.setToolTipText(Messages.exportRuntimeTooltip);
		gd = new GridData();
		gd.horizontalSpan = 2;
		exportRuntimeButton.setLayoutData(gd);
		exportRuntimeButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				getContainer().updateButtons();
			}

		});
		
		runtimeModeComposite = createRuntimeModeRadios(runtimeGroup);
		
		if(context == null){
			context = new DataBindingContext();
		}
		context.bindValue(SWTObservables.observeSelection(exportRuntimeButton), SWTObservables.observeEnabled(runtimeModeComposite));
		context.bindValue(SWTObservables.observeSelection(exportRuntimeButton), SWTObservables.observeEnabled(fullModeButton));
		context.bindValue(SWTObservables.observeSelection(exportRuntimeButton), SWTObservables.observeEnabled(ejb2ModeButton));
		context.bindValue(SWTObservables.observeSelection(exportRuntimeButton), SWTObservables.observeEnabled(ejb3ModeButton));
		context.bindValue(SWTObservables.observeSelection(exportRuntimeButton), SWTObservables.observeEnabled(restModeButton));
	
	}

	protected Composite createRuntimeModeRadios(Group runtimeGroup) {
		Composite runtimeModeComposite = new Composite(runtimeGroup, SWT.NONE);
		runtimeModeComposite.setLayout(new GridLayout(4, true));
		fullModeButton = new Button(runtimeModeComposite, SWT.RADIO);
		fullModeButton.setText("Full");
		ejb2ModeButton = new Button(runtimeModeComposite, SWT.RADIO);
		ejb2ModeButton.setText("EJB2");
		ejb3ModeButton = new Button(runtimeModeComposite, SWT.RADIO);
		ejb3ModeButton.setText("EJB3");
		restModeButton = new Button(runtimeModeComposite, SWT.RADIO);
		restModeButton.setText("REST");
		
		runtimeModeComposite.setEnabled(false);
		fullModeButton.setSelection(true);
		fullModeButton.setEnabled(false);
		ejb2ModeButton.setEnabled(false);
		ejb3ModeButton.setEnabled(false);
		restModeButton.setEnabled(false);
		return runtimeModeComposite;
	}

	protected void updateButton() {
		if (lightExportModeForProcesses.getSelection()) {
			jeeExportButtonForProcesses.setSelection(false);
			heavyExportButtonForProcesses.setSelection(false);
		} else if (jeeExportButtonForProcesses.getSelection()) {
			lightExportModeForProcesses.setSelection(false);
			heavyExportButtonForProcesses.setSelection(false);
		} else if (heavyExportButtonForProcesses.getSelection()) {
			lightExportModeForProcesses.setSelection(false);
			jeeExportButtonForProcesses.setSelection(false);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		return getLocation() != null
		&& (!getLocation().exists() || getLocation().isDirectory())
		&& (hasAProcessToExport() || exportRuntimeButton.getSelection());
	}

	/**
	 * @return
	 */
	private boolean hasAProcessToExport() {
		return true;
	}


	public File getLocation() {
		if (targetFolder != null) {
			return new File(targetFolder.getText());
		} else {
			return null;
		}
	}

	public RuntimeExportMode getExportRuntimeMode() {
		if(exportRuntimeButton.getSelection()){
			if(fullModeButton.getSelection()){
				return RuntimeExportMode.FULL;
			} else if(ejb2ModeButton.getSelection()){
				return RuntimeExportMode.EJB2;
			} else if(ejb3ModeButton.getSelection()){
				return RuntimeExportMode.EJB3;
			} else if(restModeButton.getSelection()){
				return RuntimeExportMode.REST;
			}
		}
		return null;//should not occur	
	}

	public ExportMode getProcessesExportMode() {
		if (lightExportModeForProcesses.getSelection()) {
			return ExportMode.LIGHT;
		} else if (heavyExportButtonForProcesses.getSelection()) {
			return ExportMode.EMBEDDED;
		} else if (jeeExportButtonForProcesses.getSelection()) {
			return ExportMode.JEE;
		}
		return null;
	}


	/**
	 * @return
	 */
	public List<AbstractProcess> getProcessesToExportAsBar() {
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

	/**
	 * This method is intended to be overriden by child classes.
	 * @param parent
	 */
	public void createAdditionalSection(Composite parent) {
	}

	public void setExportMode(ExportMode mode) {
		if(mode.equals(ExportMode.JEE)){
			jeeExportButtonForProcesses.setSelection(true);
			heavyExportButtonForProcesses.setSelection(false);
			lightExportModeForProcesses.setSelection(false);
			exportRuntimeButton.setSelection(true);
			fullModeButton.setEnabled(true);
			ejb2ModeButton.setEnabled(true);
			ejb3ModeButton.setEnabled(true);
			restModeButton.setEnabled(true);
			restModeButton.setSelection(false);
		}else if(mode.equals(ExportMode.LIGHT)){
			lightExportModeForProcesses.setSelection(true);
			heavyExportButtonForProcesses.setSelection(false);
			jeeExportButtonForProcesses.setSelection(false);
			exportRuntimeButton.setSelection(false);
			fullModeButton.setEnabled(false);
			ejb2ModeButton.setEnabled(false);
			ejb3ModeButton.setEnabled(false);
			restModeButton.setEnabled(false);
		}else if(mode.equals(ExportMode.EMBEDDED)){
			heavyExportButtonForProcesses.setSelection(true);
			lightExportModeForProcesses.setSelection(false);
			jeeExportButtonForProcesses.setSelection(false);
			exportRuntimeButton.setSelection(false);
			fullModeButton.setEnabled(false);
			ejb2ModeButton.setEnabled(false);
			ejb3ModeButton.setEnabled(false);
			restModeButton.setEnabled(false);
		}
	}

	public void setExportApplications(List<ExportEntry> applications,boolean exportUserXP, boolean exportRestWar) {
		exportEntriesContentProvider.getUserXPEntry().setExportWAR(exportUserXP) ;
		exportEntriesContentProvider.getRestWarEntry().setExportWAR(exportRestWar);

		exportEntriesContentProvider.setProcessEntries(applications) ;
		if (tree != null) {
			tree.getViewer().setInput(new Object()) ;
			tree.getViewer().expandAll() ;
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if(context != null){
			context.dispose();
		}
	}

	/**
	 * @return
	 */
	public boolean getExportRestWar() {
		return exportEntriesContentProvider.getRestWarEntry().getExportWAR();
	}

	public void setExportRuntime(boolean exportRuntime) {
		exportRuntimeButton.setSelection(exportRuntime) ;
		runtimeModeComposite.setEnabled(exportRuntime) ;
		fullModeButton.setEnabled(exportRuntime);
		ejb2ModeButton.setEnabled(exportRuntime);
		ejb3ModeButton.setEnabled(exportRuntime);
		restModeButton.setEnabled(exportRuntime);
	}

}
