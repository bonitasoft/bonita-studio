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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.insert.InsertSelectViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.PageBook;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaInsertTypeViewer extends ContentViewer {

  //  Button valuesButton;
   // Button queryButton;
    QueryStatement currentStatement;
    SQLDomainModel domainModel;

    PageBook insertPageBook;

    InsertSelectViewer insertSelectViewer;
    BonitaInsertGridViewer insertGridViewer;

    Combo insertQueryCombo;
    List stmtsList;

    Composite mainUIComponent;

    List prevValuesList = new ArrayList();
    QueryExpressionRoot prevQueryExpr;
    QueryInsertStatement insert = null;

    Object element;

    int fieldWidth = 10;
    int mleHeight = 10;
    private final List<Expression> filteredExpressions;

    public BonitaInsertTypeViewer(SQLDomainModel sqlDomainModel, List<Expression> filteredExpressions) {
        domainModel = sqlDomainModel;
        insert = null;
        this.filteredExpressions = filteredExpressions;
        setContentProvider(domainModel.createContentProvider());
    }

    public Control createControl(Composite parent) {
        mainUIComponent = ViewUtility.createComposite(parent, 1);

        insertPageBook = new PageBook(mainUIComponent, SWT.NULL);
        insertPageBook.setSize(150, 80);
        insertPageBook.setLayoutData(ViewUtility.createFill());

        insertGridViewer = new BonitaInsertGridViewer(domainModel, filteredExpressions, insertPageBook);
        insertGridViewer.getTable().setLinesVisible(true);
        insertGridViewer.getTable().setLayoutData(ViewUtility.createFill());

        insertSelectViewer = new InsertSelectViewer(domainModel);
        insertSelectViewer.createControl(insertPageBook);

        insertPageBook.showPage(insertGridViewer.getControl());
        hookControl(mainUIComponent);
        return mainUIComponent;
    }

    @Override
    public Control getControl() {
        return mainUIComponent;
    }

    @Override
    public void setInput(Object input) {
        insert = (QueryInsertStatement) input;
        insertGridViewer.setInput(input);
        insertSelectViewer.setInput(input);
        super.setInput(input);
    }

    @Override
    public Object getInput() {
        return insert;
    }

    @Override
    public void inputChanged(Object newElement, Object oldElement) {
        super.inputChanged(newElement, oldElement);
    }

    boolean comboFilled = false; // use as flag when to fill combo

    @Override
    public void refresh() { }

    @Override
    public final void setSelection(ISelection sel, boolean reveal) {

    }

    @Override
    public ISelection getSelection() {
        return null;
    }

    public void setEnabled(boolean enable) {
        insertSelectViewer.setEnabled(enable);
        insertGridViewer.setEnabled(enable);
    }

}
