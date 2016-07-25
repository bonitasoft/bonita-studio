/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.ui.wizard.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 *
 */
public class SelectActorWizardPage extends WizardPage implements ISelectionChangedListener{

	private TableViewer viewer;
	private Actor actor;
	private List<AbstractProcess> processes;
	private boolean multiProcessSelction = false;

	public SelectActorWizardPage(List<AbstractProcess> processes) {
		super(SelectActorWizardPage.class.getName());
		setTitle(Messages.selectActorTitle) ;
		setDescription(Messages.selectActorDesc) ;
		this.processes = processes ;
		multiProcessSelction  = processes.size() > 1 ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create()) ;
		
		viewer = new TableViewer(mainComposite,SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE) ;
		viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		TableLayout layout = new TableLayout() ;
		if(multiProcessSelction){
			layout.addColumnData(new ColumnWeightData(30)) ;
			layout.addColumnData(new ColumnWeightData(30)) ;
			layout.addColumnData(new ColumnWeightData(40)) ;
		}else{
			layout.addColumnData(new ColumnWeightData(40)) ;
			layout.addColumnData(new ColumnWeightData(60)) ;
		}
		layout.addColumnData(new ColumnWeightData(70)) ;
		viewer.getTable().setLayout(layout) ;
		
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setHeaderVisible(true);
		viewer.setContentProvider(new IStructuredContentProvider() {
			
			@Override
			public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
	
			}
			
			@Override
			public void dispose() {

			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object[] getElements(Object element) {
				List<Actor> actors = new ArrayList<Actor>() ;
				for(AbstractProcess process : ((List<AbstractProcess>)element)){
					actors.addAll((Collection<? extends Actor>) ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.ACTOR)) ;
				}
				return actors.toArray();
			}
		}) ;
	
		TableViewerColumn column = new TableViewerColumn(viewer, SWT.FILL) ;
		TableColumn nameColumn = column.getColumn() ; 
		column.getColumn().setText(Messages.name);
		column.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element) {
				return ((Element)element).getName();
			}
		});

		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);

		if(multiProcessSelction){
			column = new TableViewerColumn(viewer, SWT.FILL) ;
			column.getColumn().setText("Defined in");
			column.setLabelProvider(new ColumnLabelProvider(){
				public String getText(Object element) {
					AbstractProcess process = ModelHelper.getParentProcess((EObject) element) ;
					return process.getName() +" - "+process.getVersion()  ;
				}
			});

			column.getColumn().setMoveable(false);
			column.getColumn().setResizable(true);
		}
		

		column = new TableViewerColumn(viewer, SWT.FILL) ;
		column.getColumn().setText(Messages.description);
		column.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element) {
				return ((Element)element).getDocumentation();
			}
		});

		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);
		
		
		TableColumnSorter sorter = new TableColumnSorter(viewer) ;
		sorter.setColumn(nameColumn) ;
		
		viewer.setInput(processes) ;
		viewer.addSelectionChangedListener(this) ;

		setControl(mainComposite) ;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if(!event.getSelection().isEmpty()){
			actor = (Actor) ((IStructuredSelection) event.getSelection()).getFirstElement() ;
		}
		getContainer().updateButtons() ;
	}


	@Override
	public boolean isPageComplete() {
		return !viewer.getSelection().isEmpty();
	}

	public Actor getActor() {
		return actor;
	}
	
}
