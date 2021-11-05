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

import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.GroupByContentViewer;
import org.eclipse.emf.ecore.EObject;
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
public class BonitaGroupByViewer extends ContentViewer {

	private final SQLDomainModel sqlDomainModel;
	private final EObject processElement;

	public BonitaGroupByViewer(SQLDomainModel model,EObject processElement) {
		sqlDomainModel = model;
		this.processElement= processElement ;
	}

	@Override
    public void setInput(Object input) {
		groupByContentViewer.setInput(input);
	}

	@Override
    public Control getControl() {
		return canvas;
	}

	protected Composite canvas;

    protected GroupByContentViewer groupByContentViewer;

	public Control createControl(Composite parent) {
		canvas = new Composite(parent, SWT.NULL);

        groupByContentViewer = new GroupByContentViewer(sqlDomainModel);
		groupByContentViewer.createControl(canvas);
		groupByContentViewer.getControl().setLayoutData(ViewUtility.createFill());

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
		groupByContentViewer.setEnabled(enable);

	}

}
