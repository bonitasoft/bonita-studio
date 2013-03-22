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
package org.bonitasoft.studio.validation.ui.view;

import java.util.Arrays;
import java.util.Map;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorUtil;
import org.bonitasoft.studio.validation.constraints.ValidationContentProvider;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.internal.resources.Marker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * @author Florine Boudin
 *
 */
public class ValidationViewPart extends ViewPart implements ISelectionListener,ISelectionChangedListener,ISelectionProvider{

	public static String ID = "org.bonitasoft.studio.validation.view";

	private TableViewer tableViewer;
	private ISelectionProvider selectionProvider;
	private TableViewerColumn severityColumn;
	private ValidationViewAction validateAction;

	public ValidationViewPart() {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent) {

		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(5, 0, 3, 1).create());

		createValidateButton(mainComposite);
		createTableComposite(mainComposite);


		ISelectionService ss = getSite().getWorkbenchWindow().getSelectionService();
		ss.addPostSelectionListener(this);
		if(getSite().getPage().getActiveEditor() != null){
			selectionProvider =  getSite().getPage().getActiveEditor().getEditorSite().getSelectionProvider();
			getSite().setSelectionProvider(this);

		}

		TableColumnSorter sorter = new TableColumnSorter(tableViewer) ;
		sorter.setColumn(severityColumn.getColumn()) ;
	}

	private void createValidateButton(Composite mainComposite) {

		IWorkbenchPage activePage =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		validateAction = new ValidationViewAction();
		validateAction.setActivePage(activePage);
		validateAction.setTableViewer(tableViewer);


		Composite buttonComposite = new Composite(mainComposite,SWT.NONE);
		buttonComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0,0).create());
		buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		Button validateButton = new Button(buttonComposite,SWT.PUSH);
		validateButton.setLayoutData(GridDataFactory.fillDefaults().create());
		validateButton.setText(Messages.validationViewValidateButtonLabel);
		validateButton.addSelectionListener(new SelectionAdapter() {
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				validateAction.run();
			}
		});



	}

	/**
	 * 
	 * @param mainComposite
	 */
	private void createTableComposite(Composite mainComposite) {
		Composite tableComposite = new Composite(mainComposite, SWT.NONE);
		tableComposite.setLayout(GridLayoutFactory.fillDefaults().create());
		tableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());


		tableViewer = new TableViewer(tableComposite,SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION) ;
		final Table table = tableViewer.getTable();
		table.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(400, SWT.DEFAULT).create());
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		addSeverityDescriptionColumn();
		addElementNameColumn();
		addErrorDescriptionColumn();

		TableColumnLayout tcLayout = new TableColumnLayout();
		tcLayout.setColumnData(table.getColumn(0), new ColumnWeightData(1));
		tcLayout.setColumnData(table.getColumn(1), new ColumnWeightData(5));
		tcLayout.setColumnData(table.getColumn(2), new ColumnWeightData(11));
		table.getParent().setLayout(tcLayout);

		tableViewer.setContentProvider(new ValidationContentProvider());
		IEditorPart activeEditor = getSite().getPage().getActiveEditor();
		tableViewer.setInput(activeEditor);

		tableViewer.addSelectionChangedListener(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {

	}


	private void addElementNameColumn(){
		TableViewerColumn elements = new TableViewerColumn(tableViewer, SWT.NONE);
		elements.getColumn().setText(Messages.validationViewElementColumnName);
		elements.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				Marker marker = (Marker) element;
				try {

					String elementId = (String) marker.getAttribute(org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID);
					String location = (String) marker.getAttribute("location");
					int idx = location.lastIndexOf(":");
					if(idx>0){
						String result = location.substring(idx+1);;
						if(!(result.startsWith("<") && result.endsWith(">"))){
							return result;
						}else{
							DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
							DiagramFileStore file = store.getChild(marker.getResource().getLocation().toFile().getName());
							if(file != null){
								EObject view = file.getEMFResource().getEObject(elementId);
								if(view != null){
									for(EObject object : view.eCrossReferences()){
										if(object instanceof Element){
											return ((Element) object).getName();
										}
									}
								}
							}
							return "";
						}
					}

					return location;
				} catch (CoreException e) {
					BonitaStudioLog.error(e);
					return "";
				}
			}
		});

	}

	private void addErrorDescriptionColumn(){
		TableViewerColumn elements = new TableViewerColumn(tableViewer, SWT.NONE);
		elements.getColumn().setText(Messages.validationViewDescriptionColumnName);
		elements.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {

				Marker marker = (Marker) element;
				try {
					return (String) marker.getAttribute("message");
				} catch (CoreException e) {
					return "";
				}
			}
		});
	}


	private void addSeverityDescriptionColumn(){
		severityColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		severityColumn.getColumn().setText(Messages.validationViewSeverityColumnName);
		severityColumn.setLabelProvider(new SeverityColumnLabelProvider());
	}


	@Override
	public void dispose() {

	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {

	}

	@Override
	public ISelection getSelection() {
		return selectionProvider.getSelection();
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionProvider.removeSelectionChangedListener(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		selectionProvider.setSelection(selection);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if(event.getSelection() instanceof StructuredSelection && ((StructuredSelection)event.getSelection()).getFirstElement() instanceof Marker){
			Marker m =(Marker) ((StructuredSelection)event.getSelection()).getFirstElement();

			String elementId = m.getAttribute(org.eclipse.gmf.runtime.common.core.resources.IMarker.ELEMENT_ID, null);
			if (elementId == null || !(getSite().getPage().getActiveEditor() instanceof DiagramEditor)) {
				return ;
			}
			DiagramEditor editor = (DiagramEditor) getSite().getPage().getActiveEditor();
			Map editPartRegistry = editor.getDiagramGraphicalViewer().getEditPartRegistry();
			EObject targetView = editor.getDiagram().eResource().getEObject(elementId);
			if (targetView == null) {
				return ;
			}
			EditPart targetEditPart = (EditPart) editPartRegistry.get(targetView);
			if (targetEditPart != null) {
				ProcessDiagramEditorUtil.selectElementsInDiagram(editor,
						Arrays.asList(new EditPart[] { targetEditPart }));
			}
		}


	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		if(selection instanceof StructuredSelection && !tableViewer.getTable().isDisposed()){
			Object selectedEP = ((StructuredSelection) selection).getFirstElement();
			if(selectedEP instanceof IGraphicalEditPart){
				IEditorPart editorPart = getSite().getPage().getActiveEditor();
				if(editorPart != null ){
					if(!editorPart.equals(tableViewer.getInput())){
						selectionProvider = editorPart.getEditorSite().getSelectionProvider();
						tableViewer.setInput(editorPart);
					}else{

						tableViewer.refresh();
					}
				}
				tableViewer.getTable().layout(true,true);
			}

			// change Validate Action
			IWorkbenchPage activePage =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

			//tableViewer.setInput(activePage.getActiveEditor());
			validateAction = new ValidationViewAction();
			validateAction.setActivePage(activePage);
			validateAction.setTableViewer(tableViewer);

			tableViewer.refresh();


		}
	}

	public void refreshViewer() {
		if(tableViewer != null && !tableViewer.getTable().isDisposed()){
			tableViewer.setInput(tableViewer.getInput());
		}
	}

}
