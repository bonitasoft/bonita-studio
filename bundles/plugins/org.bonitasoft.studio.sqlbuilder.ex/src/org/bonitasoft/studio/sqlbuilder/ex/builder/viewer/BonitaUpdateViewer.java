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
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.Workbook;
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
public class BonitaUpdateViewer extends ContentViewer {

    private final SQLDomainModel sqlDomainModel;
    BonitaUpdateDesignViewer updateDesignViewer;
    BonitaCriteriaGridViewer criteriaView;
    private final List<Expression> filteredExpressions;

    public BonitaUpdateViewer(SQLDomainModel model, List<Expression> filteredExpressions) {
        sqlDomainModel = model;
        this.filteredExpressions = filteredExpressions;
        setContentProvider(model.createContentProvider());
    }

    @Override
    public void setInput(Object input) {
        updateDesignViewer.setInput(input);
        criteriaView.setInput(input);
        super.setInput(input);
    }

    @Override
    public Control getControl() {
        return canvas;
    }

    @Override
    public void refresh() {
    }

    Composite canvas;

    public Control createControl(Composite parent) {
        canvas = new Composite(parent, SWT.NULL);

        final Workbook workbook = new Workbook(canvas);

        updateDesignViewer = new BonitaUpdateDesignViewer(sqlDomainModel, filteredExpressions);
        updateDesignViewer.createControl(workbook.getClientComposite());
        updateDesignViewer.getControl().setLayoutData(ViewUtility.createFill());
        workbook.addPage(updateDesignViewer.getControl(), Messages._UI_WORKBOOK_SET, null, null);

        criteriaView = new BonitaCriteriaGridViewer(SWT.FULL_SELECTION | SWT.HIDE_SELECTION, sqlDomainModel, filteredExpressions, workbook.getClientComposite(),
                false);
        criteriaView.getTable().setLayoutData(ViewUtility.createFill());
        workbook.addPage(criteriaView.getControl(), Messages._UI_WORKBOOK_WHERE, null, null);

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
        criteriaView.setEnabled(enable);
        updateDesignViewer.setEnabled(enable);
    }
}
