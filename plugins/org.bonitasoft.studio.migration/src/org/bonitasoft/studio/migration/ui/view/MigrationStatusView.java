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
package org.bonitasoft.studio.migration.ui.view;

import java.io.IOException;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportPackage;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jdt.internal.ui.actions.AbstractToggleLinkingAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.navigator.CommonNavigatorMessages;
import org.eclipse.ui.internal.navigator.NavigatorPlugin;
import org.eclipse.ui.internal.navigator.filters.SelectFiltersAction;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * @author Aurelien Pupier
 * @author Romain Bioteau
 */
public class MigrationStatusView extends ViewPart implements ISelectionListener,IDoubleClickListener {

	public static String ID = "org.bonitasoft.studio.migration.view";
	private TableViewer tableViewer;
	private ISelectionProvider selectionProvider;


	@Override
	public void createPartControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

		createTopComposite(mainComposite);
		createTableComposite(mainComposite);
		createBottomComposite(mainComposite);

		ISelectionService ss = getSite().getWorkbenchWindow().getSelectionService();
		ss.addPostSelectionListener(this);
		if(getSite().getPage().getActiveEditor() != null){
			selectionProvider =  getSite().getPage().getActiveEditor().getEditorSite().getSelectionProvider();
			getSite().setSelectionProvider(selectionProvider);
		}
		createActions();

	}

	protected void createActions() {
		IActionBars actionBars = getViewSite().getActionBars();
		IMenuManager dropDownMenu = actionBars.getMenuManager();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		IAction exportAction = createExportAction();
		dropDownMenu.add(exportAction);
		
		IAction synchronizeAction = createSynchronizeAction();
		toolBar.add(synchronizeAction);
		
		IAction selectFiltersAction = new Action(){
			public void run() {
				
			}
		};
		selectFiltersAction.setToolTipText(CommonNavigatorMessages.SelectFiltersActionDelegate_1);
		selectFiltersAction.setText(Messages.filters);
		ImageDescriptor selectFiltersIcon = NavigatorPlugin.getImageDescriptor("icons/full/elcl16/filter_ps.gif"); //$NON-NLS-1$ 
		selectFiltersAction.setImageDescriptor(selectFiltersIcon);
		selectFiltersAction.setHoverImageDescriptor(selectFiltersIcon);
		dropDownMenu.add(selectFiltersAction);
	}

	private IAction createSynchronizeAction() {
		IAction action = new AbstractToggleLinkingAction() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		};
		return action;
	}

	private IAction createExportAction() {
		IAction action = new Action() {
			@Override
			public String getText() {
				return Messages.exportAsPdf;
			}
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
		};
		return action;
	}

	protected void createBottomComposite(Composite mainComposite) {
		final Composite bottomComposite = new Composite(mainComposite, SWT.NONE);
		bottomComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		bottomComposite.setLayoutData(GridDataFactory.fillDefaults().create());

		createPrintButton(bottomComposite);
		createMarkAsCompletedButton(bottomComposite);

	}

	protected void createMarkAsCompletedButton(Composite bottomComposite) {
		Button markAsCompletedButton = new Button(bottomComposite, SWT.NONE);
		markAsCompletedButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
		markAsCompletedButton.setText(Messages.completeImport);
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
							//TODO : write the report to the specified filename
						}
					}
					try {
						clearMigrationReport(true);
					} catch (IOException e1) {
						BonitaStudioLog.error(e1,MigrationPlugin.PLUGIN_ID);
					}
					final String id = BonitaPerspectivesUtils.getPerspectiveId((IEditorPart) tableViewer.getInput());
					if (id != null) {
						BonitaPerspectivesUtils.switchToPerspective(id);
					}
				}

			}
		});
	}

	private void clearMigrationReport(boolean save) throws IOException{
		final IEditorPart editorPart = (IEditorPart) tableViewer.getInput();
		if(editorPart != null && editorPart instanceof DiagramEditor){
			final Resource emfResource = ((DiagramEditor)editorPart).getDiagramEditPart().getNotationView().eResource();
			final Report report = getMigrationReport(emfResource); 
			if(report != null){
				final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(emfResource);
				if(domain != null){
					domain.getCommandStack().execute(new RecordingCommand(domain) {
						protected void doExecute() {
							emfResource.getContents().remove(report);
						}
					});
					if(save){
						ICommandService service = (ICommandService) getSite().getService(ICommandService.class);
						Command cmd = service.getCommand("org.eclipse.ui.file.save");
						try {
							cmd.executeWithChecks(new ExecutionEvent());
						} catch (Exception e) {
							BonitaStudioLog.error(e,MigrationPlugin.PLUGIN_ID);
						} 
					}
				}
			}
		}
	}

	private Report getMigrationReport(Resource resource) {
		for(EObject r : resource.getContents()){
			if(r instanceof Report){
				return (Report) r;
			}
		}
		return null;
	}

	protected void createTableComposite(Composite mainComposite) {
		Composite tableComposite = new Composite(mainComposite, SWT.NONE);
		tableComposite.setLayout(GridLayoutFactory.fillDefaults().create());
		tableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());


		tableViewer = new TableViewer(tableComposite);
		tableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(400, SWT.DEFAULT).create());
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true);

		addElementTypeColumn();
		addElementNameColumn();
		addPropertyColumn();
		addDescriptionColumn();
		addStatusColumn();
		addReviewedColumn();

		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(15));
		layout.addColumnData(new ColumnWeightData(20));
		layout.addColumnData(new ColumnWeightData(15));
		layout.addColumnData(new ColumnWeightData(30));
		layout.addColumnData(new ColumnWeightData(8));
		layout.addColumnData(new ColumnWeightData(12));

		tableViewer.getTable().setLayout(layout);

		tableViewer.setContentProvider(new ReportContentProvider());
		tableViewer.setInput(getSite().getPage().getActiveEditor());
		tableViewer.addDoubleClickListener(this);
	}

	protected void addElementNameColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.name);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getElementName();
				}
				return Messages.unknown;
			}
			@Override
			public String getToolTipText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getElementName();
				}

				return null ;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 4000 ;
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 50;
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5,5);
			}
		});
	}

	protected void addElementTypeColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.element);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getElementType();
				}
				return Messages.unknown;
			}
			@Override
			public String getToolTipText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getElementType();
				}

				return null ;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 4000 ;
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 50;
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5,5);
			}
		});
	}

	protected void addPropertyColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.property);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getPropertyName();
				}
				return Messages.unknown;
			}
			@Override
			public String getToolTipText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getPropertyName();
				}

				return null ;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 4000 ;
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 50;
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5,5);
			}
		});
	}

	protected void addDescriptionColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.MULTI | SWT.WRAP);
		column.getColumn().setAlignment(SWT.CENTER);
		column.getColumn().setText(Messages.information);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getDescription();
				}
				return Messages.unknown;
			}

			@Override
			public String getToolTipText(Object element) {
				if(element instanceof Change){
					return ((Change) element).getDescription();
				}

				return null ;
			}

			@Override
			public int getToolTipTimeDisplayed(Object object) {
				return 4000 ;
			}

			@Override
			public int getToolTipDisplayDelayTime(Object object) {
				return 50;
			}

			@Override
			public Point getToolTipShift(Object object) {
				return new Point(5,5);
			}
		});
	}

	protected void addStatusColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.status);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return null;
			}

			@Override
			public Image getImage(Object element) {
				if(element instanceof Change){
					int status =  ((Change) element).getStatus();
					return getImageForStatus(status);
				}
				return super.getImage(element);
			}
		});

	}

	protected void addReviewedColumn() {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.FILL);
		column.getColumn().setText(Messages.reviewed);
		column.getColumn().setAlignment(SWT.CENTER);
		column.setLabelProvider(new CheckboxLabelProvider(tableViewer.getControl()));

		column.setEditingSupport(new EditingSupport(tableViewer) {

			@Override
			protected void setValue(Object element, Object value) {
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(element);
				if(editingDomain != null){
					editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, MigrationReportPackage.Literals.CHANGE__REVIEWED, value));
				}

				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						tableViewer.refresh();		
					}
				}) ;
			}

			@Override
			protected Object getValue(Object element) {
				return ((Change)element).isReviewed();
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new CheckboxCellEditor(tableViewer.getTable(), SWT.CHECK);
			}

			@Override
			protected boolean canEdit(Object element) {
				return true;
			}
		});

		TableColumnSorter sorter = new TableColumnSorter(tableViewer) ;
		sorter.setColumn(column.getColumn()) ;
	}

	protected void createTopComposite(Composite mainComposite) {
		final Composite topComposite = new Composite(mainComposite, SWT.NONE);
		topComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(5, 5, 5, 0).create());
		topComposite.setLayoutData(GridDataFactory.fillDefaults().create());

		createFilterComposite(topComposite);

	}

	protected void createFilterComposite(Composite topComposite) {
		final Text findText = new Text(topComposite, SWT.BORDER | SWT.SEARCH | SWT.ICON_CANCEL | SWT.ICON_SEARCH);
		findText.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).align(SWT.RIGHT, SWT.CENTER).hint(150, SWT.DEFAULT).create());
		findText.setMessage(Messages.find);

	}

	protected void createPrintButton(Composite topComposite) {
		final Button printButton = new Button(topComposite, SWT.PUSH);
		printButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).grab(true, false).create());
		printButton.setText(Messages.exportAsPdf);
		printButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);

			}
		});
	}

	@Override
	public void setFocus() {

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection instanceof StructuredSelection && !tableViewer.getTable().isDisposed()){
			Object selectedEP = ((StructuredSelection) selection).getFirstElement();
			if(selectedEP instanceof IGraphicalEditPart){
				IEditorPart editorPart = getSite().getPage().getActiveEditor();
				if(editorPart != null && !editorPart.equals(tableViewer.getInput())){
					selectionProvider = editorPart.getEditorSite().getSelectionProvider();
					getSite().setSelectionProvider(selectionProvider); 
					tableViewer.setInput(editorPart);
				}else if(editorPart != null && editorPart.equals(tableViewer.getInput())){
					tableViewer.refresh();
				}
				tableViewer.getTable().layout(true,true);
			}
		}
	}

	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySheetPage.class){
			return getSite().getPage().getActiveEditor().getAdapter(adapter);
		}else if(adapter == IEditingDomainProvider.class){
			return new IEditingDomainProvider() {
				@Override
				public EditingDomain getEditingDomain() {
					IEditorPart part = getSite().getPage().getActiveEditor();
					if(part instanceof DiagramEditor){
						return ((DiagramEditor) part).getEditingDomain();
					}
					return null;
				}
			};
		}
		return super.getAdapter(adapter);
	}

	private Image getImageForStatus(int status) {
		switch (status) {
		case IStatus.OK: return Pics.getImage("valid.png");
		case IStatus.WARNING: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
		case IStatus.INFO: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		case IStatus.ERROR: return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		default:break;
		}

		return null;
	}



	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void doubleClick(DoubleClickEvent event) {
		Object selection = ((IStructuredSelection)event.getSelection()).getFirstElement();
		// TODO retrieve the selected element and set the focus in the editor for it

	}


}
