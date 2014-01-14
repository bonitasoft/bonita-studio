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
package org.bonitasoft.studio.properties.sections.operation;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationsComposite;
import org.bonitasoft.studio.expression.editor.operation.PropertyOperationsComposite;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * @author Romain Bioteau
 *
 */
public class OperationsPropertySection extends AbstractBonitaDescriptionSection {

	protected OperationsComposite operationComposite;
	protected TabbedPropertySheetPage tabbedPropertySheetPage;
	private Object lastEObject;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		tabbedPropertySheetPage = aTabbedPropertySheetPage;
		final Composite  mainComposite = getWidgetFactory().createComposite(super.composite);
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(15, 15).create());
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create());
		CLabel warningLabel = getWidgetFactory().createCLabel(mainComposite, Messages.transientDataWarning) ;
		warningLabel.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
		operationComposite = createActionLinesComposite(mainComposite);
		operationComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
	}

	protected OperationsComposite createActionLinesComposite(Composite parent) {
		AvailableExpressionTypeFilter actionFilter =  new AvailableExpressionTypeFilter(new String[]{
				ExpressionConstants.CONSTANT_TYPE,
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.SCRIPT_TYPE,
				ExpressionConstants.PARAMETER_TYPE,
				ExpressionConstants.DOCUMENT_TYPE
		}) ;

		AvailableExpressionTypeFilter dataFilter =  new AvailableExpressionTypeFilter(new String[]{
				ExpressionConstants.VARIABLE_TYPE,
				ExpressionConstants.SEARCH_INDEX_TYPE,
				ExpressionConstants.DOCUMENT_REF_TYPE
		}) ;

		return new PropertyOperationsComposite(tabbedPropertySheetPage, parent, actionFilter, dataFilter);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if(lastEObject == null || (lastEObject != null && !lastEObject.equals(getEObject()))){
			lastEObject = getEObject();
			operationComposite.setEObject(getEObject());
			if (lastEObject instanceof ReceiveTask){
				operationComposite.setOperationContainmentFeature(ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS);
			}
			operationComposite.setContext(new EMFDataBindingContext());
			operationComposite.removeLinesUI();
			operationComposite.fillTable();
			operationComposite.refresh();
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
		if(operationComposite != null){
			operationComposite.dispose();
		}
	}

	@Override
	public String getSectionDescription() {
		return Messages.operationExplanation;
	}


}
