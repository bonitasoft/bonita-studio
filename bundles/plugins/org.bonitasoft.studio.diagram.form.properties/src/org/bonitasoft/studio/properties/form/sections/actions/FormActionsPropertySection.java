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
package org.bonitasoft.studio.properties.form.sections.actions;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.data.provider.DataExpressionNatureProviderForFormOutput;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationsComposite;
import org.bonitasoft.studio.expression.editor.operation.PropertyOperationsComposite;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * 
 * @author Baptiste Mesta
 * @author Aurelien Pupier : imporve resource management (dispose element and
 *         use imageRegistry)
 */
public class FormActionsPropertySection extends AbstractBonitaDescriptionSection {

	protected OperationsComposite table;
	protected Composite mainComposite;
	protected TabbedPropertySheetPage tabbedPropertySheetPage;
	private Object lastEObject;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		tabbedPropertySheetPage = aTabbedPropertySheetPage;
		mainComposite = getWidgetFactory().createComposite(parent);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(15, 15).create());
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		table = createActionLinesComposite();
		table.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
	}

	protected OperationsComposite createActionLinesComposite() {
		AvailableExpressionTypeFilter actionFilter =  new AvailableExpressionTypeFilter(new String[]{
				ExpressionConstants.CONSTANT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.PARAMETER_TYPE,
				ExpressionConstants.SCRIPT_TYPE,
				ExpressionConstants.FORM_FIELD_TYPE,
				ExpressionConstants.DOCUMENT_TYPE
		}) ;

		AvailableExpressionTypeFilter storageFilter =  new AvailableExpressionTypeFilter(new String[]{
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.DOCUMENT_REF_TYPE
		}) ;

		final OperationsComposite operationsComposite = new PropertyOperationsComposite(tabbedPropertySheetPage, mainComposite, actionFilter, storageFilter);
		operationsComposite.setStorageExpressionNatureContentProvider(new DataExpressionNatureProviderForFormOutput());
		return operationsComposite;
	}


	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if(lastEObject == null || (lastEObject != null && !lastEObject.equals(getEObject()))){
			table.setEObject(getEObject());
			table.setContext(new EMFDataBindingContext());
			table.removeLinesUI();
			table.fillTable();
			table.refresh() ;
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gmf.runtime.diagram.ui.properties.sections.
	 * AbstractModelerPropertySection#dispose()
	 */
	 @Override
	 public void dispose() {
		super.dispose();
		if(table != null){
			table.dispose();
		}
	 }

	 /**
	  * @return the mainComposite
	  */
	 public Composite getMainComposite() {
		 return mainComposite;
	 }

	 @Override
	 public String getSectionDescription() {
		 // TODO Auto-generated method stub
		 return null;
	 }

}
