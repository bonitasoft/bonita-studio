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
package org.bonitasoft.studio.properties.sections.general;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.EventSubProcessPool;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 */
public class SubProcessEventPropertySectionContribution implements
		IExtensibleGridPropertySectionContribution {
	
	private SubProcessEvent subProcessEvent;
	private ComboViewer viewer;
	private DataBindingContext context;
	
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof SubProcessEvent;
	}

	public void refresh() {
		IObservableValue viewerObservableSelection = ViewersObservables.observeSingleSelection(viewer);
		IObservableValue modelObservable = EMFObservables.observeValue(subProcessEvent, ProcessPackage.Literals.SUB_PROCESS_EVENT__SUB_PROCESS_EVENT);
		if(context != null){
			context.dispose();
		}
		viewer.setInput(ModelHelper.getMainProcess(subProcessEvent));
		context = new EMFDataBindingContext();
		context.bindValue(viewerObservableSelection, modelObservable);
	}

	public String getLabel() {
		return Messages.subProcEvent;
	}

	public void createControl(Composite composite,
			TabbedPropertySheetWidgetFactory widgetFactory,
			ExtensibleGridPropertySection extensibleGridPropertySection) {
		GridLayout gl = new GridLayout(3,false);
		composite.setLayout(gl);
		
		Combo combo  = new Combo(composite, SWT.READ_ONLY);
		viewer = new ComboViewer(combo);
		viewer.setContentProvider(new IStructuredContentProvider() {
			
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
			
			public void dispose() {	
			}
			
			public Object[] getElements(Object inputElement) {
				if(inputElement instanceof MainProcess){
					List<EventSubProcessPool> res = new ArrayList<EventSubProcessPool>();
					MainProcess mainProc = (MainProcess) inputElement;
					for (EObject iterable_element : mainProc.eContents()) {
						if(iterable_element instanceof EventSubProcessPool){
							EventSubProcessPool espp = (EventSubProcessPool)iterable_element;
							/*Add it in the list only if we can select it*/
							SubProcessEvent subProcessActivityInParentProcess = espp.getSubProcessActivityInParentProcess();
							if(subProcessActivityInParentProcess == null
									|| subProcessActivityInParentProcess.equals(subProcessEvent)){
								res.add(espp);
							}
						}
					}
					return res.toArray();
				}
				return null;
			}
		});
			
		viewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				if(element instanceof EventSubProcessPool){
					EventSubProcessPool espp = (EventSubProcessPool) element;
					return espp.getName() +"("+espp.getVersion()+")";
				}
				return super.getText(element);
			}
			
		});
		combo.setLayoutData(new GridData(350, SWT.DEFAULT));	
	}

	public void setEObject(EObject object) {
		subProcessEvent = (SubProcessEvent) object;
	}

	public void setSelection(ISelection selection) {
	}

	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
	}

	public void dispose() {
		if(context != null){
			context.dispose();
		}
	}

}
