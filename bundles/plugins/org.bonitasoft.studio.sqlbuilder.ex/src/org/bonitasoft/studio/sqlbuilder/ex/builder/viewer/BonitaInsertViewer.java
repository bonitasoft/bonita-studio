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
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DesignViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaInsertViewer extends ContentViewer { 

    private final List<Expression> filteredExpressions;
	private final SQLDomainModel sqlDomainModel;
	BonitaInsertTypeViewer insertTypeViewer;
	DesignViewer designViewer;
	

    public BonitaInsertViewer(SQLDomainModel model, List<Expression> filteredExpressions) {
		sqlDomainModel = model ;
		setContentProvider(sqlDomainModel.createContentProvider());
        this.filteredExpressions = filteredExpressions;
	}


	@Override
    public void setInput(Object input) {
		insertTypeViewer.setInput(input);
		super.setInput(input);
	}

	@Override
    public Control getControl() {
		return canvas;
	}

	@Override
    public void refresh() {
		insertTypeViewer.setInput(getInput()); 

		// Work around solution for cases where user modifies a column and the value
		// does not show immediately [wsdbu00045516]
		if (designViewer != null) {
			designViewer.forceRefresh();
		}
	}

	/**
	 * Sets the design viewer
	 * @param viewer the design viewer
	 */
	public void setDesignViewer(DesignViewer viewer) {
		designViewer = viewer;
	}

	Composite canvas;

	public Control createControl(Composite parent) {
		canvas = new Composite(parent, SWT.NULL);

        insertTypeViewer = new BonitaInsertTypeViewer(sqlDomainModel, filteredExpressions);
		insertTypeViewer.createControl(canvas);
		insertTypeViewer.getControl().setLayoutData(ViewUtility.createFill());

		final GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		canvas.setLayout(layout);
		hookControl(canvas);
		return getControl();
	}

	@Override
    public ISelection getSelection() {
		return null;
	}

	@Override
    public void setSelection(ISelection selection, boolean reveal) {
	}

	public void setEnabled(boolean enable) {
		insertTypeViewer.setEnabled(enable);
	}

}
