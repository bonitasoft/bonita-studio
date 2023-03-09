/**
 * Copyright (C) 2020 BonitaSoft S.A.
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
package org.bonitasoft.studio.sqlbuilder.ex.builder.viewer;

import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.Workbook;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaGridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.GroupByViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.SelectStatementDistinctViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaSelectViewer extends ContentViewer {

	SQLDomainModel sqlDomainModel;
	SelectStatementDistinctViewer selectStatementDistinctViewer;
	BonitaSelectGridViewer selectGridViewer;
    CriteriaGridViewer criteriaView;
    CriteriaGridViewer havingView;
    GroupByViewer groupByViewer;
	Composite canvas;
    private final List<Expression> filteredExpressions;

	public BonitaSelectViewer(SQLDomainModel model,List<Expression> filteredExpressions) {
		sqlDomainModel = model;
		this.filteredExpressions = filteredExpressions ;
	}

	@Override
    public void setInput(Object input) {
		selectStatementDistinctViewer.setInput(input);
		selectGridViewer.setInput(input);
		criteriaView.setInput(input);
		havingView.setInput(input);
		groupByViewer.setInput(input);
	}

	@Override
    public Control getControl() {
		return canvas;
	}

	public Control createControl(Composite parent) {
		canvas = new Composite(parent, SWT.NULL);

		selectStatementDistinctViewer = new SelectStatementDistinctViewer(sqlDomainModel);
		selectStatementDistinctViewer.createControl(canvas);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(canvas, SQLBuilderContextIds.SQLB_SELECT_VIEW);
		final Workbook workbook = new Workbook(canvas);

        selectGridViewer = new BonitaSelectGridViewer(sqlDomainModel, filteredExpressions, workbook.getClientComposite());
		workbook.addPage(selectGridViewer.getControl(), Messages._UI_WORKBOOKPAGE_COLUMNS, null, null); 

		selectGridViewer.getTable().setLinesVisible(true);
		selectGridViewer.getTable().setLayoutData(ViewUtility.createFill());

        criteriaView = new CriteriaGridViewer(SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER, sqlDomainModel, workbook.getClientComposite(), false);
		workbook.addPage(criteriaView.getControl(), Messages._UI_WORKBOOKPAGE_CONDITIONS, null, null);

		criteriaView.getTable().setLayoutData(ViewUtility.createFill());
        groupByViewer = new GroupByViewer(sqlDomainModel);
		groupByViewer.createControl(workbook.getClientComposite());

		workbook.addPage(groupByViewer.getControl(), Messages._UI_WORKBOOKPAGE_GROUPS, null, null); 
		groupByViewer.getControl().setLayoutData(ViewUtility.createFill());

        havingView = new CriteriaGridViewer(SWT.FULL_SELECTION | SWT.HIDE_SELECTION, sqlDomainModel, workbook.getClientComposite(), true);
		workbook.addPage(havingView.getControl(), Messages._UI_WORKBOOKPAGE_GROUP_CONDITIONS, null, null); 

		havingView.getTable().setLayoutData(ViewUtility.createFill());

		final GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		canvas.setLayout(layout);
		return getControl();
	}

	@Override
    public void refresh() {

	}

	@Override
    public ISelection getSelection() {
		return null;
	}

	@Override
    public void setSelection(ISelection selection, boolean reveal) {
	}

	public void setEnabled(boolean enable) {
		selectStatementDistinctViewer.setEnabled(enable);
		selectGridViewer.setEnabled(enable);
		criteriaView.setEnabled(enable);
		havingView.setEnabled(enable);
		groupByViewer.setEnabled(enable);
	}
}
