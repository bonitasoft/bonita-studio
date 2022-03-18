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
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSearchTable;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSimpleTable;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function.ParamTable;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionElement;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionsTable;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaGridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.SelectTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.update.UpdateTreeViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class BonitaDynamicComboBoxCellEditor extends EditComboBoxCellEditor {

    TableViewer tableViewer = null;
    UpdateTreeViewer tableTreeViewer = null;
    private final List<Expression> filteredExpression;

    public BonitaDynamicComboBoxCellEditor(final Composite parent, final List<Expression> filteredExpression, final LabelValuePair[] items,
            final Object viewer) {
        super(parent, new LabelValuePair[0], true);
        fItems = items;
        this.filteredExpression = filteredExpression;
        if (viewer instanceof BonitaInsertGridViewer) {
            tableViewer = (TableViewer) viewer;
            fItems = new LabelValuePair[0];
            createItems(fItems);
        } else if (viewer instanceof UpdateTreeViewer) {
            tableTreeViewer = (UpdateTreeViewer) viewer;
            fItems = new LabelValuePair[0];
            createItems(fItems);
        } else if (viewer instanceof TableViewer) {
            tableViewer = (TableViewer) viewer;
        }

    }

    @Override
    public void createItems(final LabelValuePair[] items) {
        super.createItems(items);
        final List<LabelValuePair> processItems = new ArrayList<LabelValuePair>();
        for (final Expression exp : filteredExpression) {
            if (!processItems.contains(exp.getName())) {
                final String label = exp.getName() + " (" + exp.getReturnType() + ")";
                String value = "'${" + exp.getName() + "}'";
                if (!String.class.getName().equals(exp.getReturnType())) {
                    value = "${" + exp.getName() + "}";
                }
                processItems.add(new LabelValuePair(label, ExpressionHelper.createExpression(value)));
            }
        }

        addItemsToStart(processItems.toArray(new LabelValuePair[] {}));
    }

    public void addItemsToStart(final LabelValuePair[] newItemsToAdd) {
        final LabelValuePair[] fItemsNew = new LabelValuePair[newItemsToAdd.length + fItems.length];
        int i = 0;
        for (i = 0; i < newItemsToAdd.length; i++) {
            fItemsNew[i] = newItemsToAdd[i];
        }
        for (int j = 0; j < fItems.length; j++) {
            fItemsNew[i] = fItems[j];
            i++;
        }
        //fItems = new LabelValuePair[fItemsNew.length];
        fItems = fItemsNew;
    }

    /*
     * Handle a set focus by setting the combo value to the current
     * cell value. If we can't reasonably determine the cell value,
     * set it to blank.
     */
    @Override
    protected void doSetFocus() {
        String valStr = "";
        final Object valObj = getValue();
        if (valObj instanceof CriteriaElement) {
            //CriteriaElement critElem = (CriteriaElement) valObj;
            // TODO: figure out how to get the value from the appropriate column of the CriteriaElement
        }
        if (valObj instanceof ExpressionElement) {
            /*
             * Get the SQL model expression object from the ExpressionElement.
             * In the Expression Builder context the DynamicCombBoxCellEditor
             * is used only for the "Expression by Operators" dialog and the
             * Function Builder dialog, and it appears to be safe to simply
             * get the expression value from the ExpressionElement.
             */
            final ExpressionElement exprElem = (ExpressionElement) valObj;
            final QueryValueExpression expr = exprElem.getExpression();
            if (expr != null) {
                valStr = expr.getSQL();
            }
        } else if (valObj instanceof SelectTableElement) {
            /*
             * Get the column text of the first column, which is the
             * qualified column name. The DynamicComboBoxCellEditor class
             * appears to be only used for the first column of the Select
             * grid, so it looks like it's safe to assume that we get our
             * data from the first column of the SelectTableElement.
             */
            final SelectTableElement tableElem = (SelectTableElement) valObj;
            valStr = tableElem.getColumnText(0);
        } else if (valObj instanceof String) {
            valStr = valObj.toString();
        }
        combo.setText(valStr);
        combo.setFocus();
    }

    @Override
    protected LabelValuePair createComboBoxItem(final String newValue) {
        return new LabelValuePair(newValue, ExpressionHelper.createExpression(newValue));
    }

    @Override
    protected void doSetValue(final Object value) {
        // 	commitValue();
        super.doSetValue(value);
    }

    @Override
    protected void refreshComboItems() {
        int row = -1;
        if (tableViewer != null) {
            if (tableViewer instanceof CriteriaGridViewer) {
                final CriteriaGridViewer cgv = (CriteriaGridViewer) tableViewer;
                row = cgv.getTable().getSelectionIndex();
                if (row >= 0) {
                    cgv.refreshCellEditor(row);
                }
            } else if (tableViewer instanceof BonitaSelectGridViewer) {
                final BonitaSelectGridViewer sgv = (BonitaSelectGridViewer) tableViewer;
                row = sgv.getTable().getSelectionIndex();
                if (row >= 0) {
                    sgv.refreshCellEditor(row);
                }
            } else if (tableViewer instanceof ParamTable) {
                final ParamTable ptv = (ParamTable) tableViewer;
                row = ptv.getTable().getSelectionIndex();
                if (row >= 0) {
                    ptv.refreshCellEditor(row);
                }
            } else if (tableViewer instanceof ExpressionsTable) {
                final ExpressionsTable expTable = (ExpressionsTable) tableViewer;
                row = expTable.getTable().getSelectionIndex();
                if (row >= 0) {
                    expTable.refreshCellEditor(row);
                }
            } else if (tableViewer instanceof CaseSearchTable) {
                final CaseSearchTable caseSearchTable = (CaseSearchTable) tableViewer;
                row = caseSearchTable.getTable().getSelectionIndex();
                if (row >= 0) {
                    caseSearchTable.refreshCellEditor(row);
                }
            } else if (tableViewer instanceof CaseSimpleTable) {
                final CaseSimpleTable caseSimpleTable = (CaseSimpleTable) tableViewer;
                row = caseSimpleTable.getTable().getSelectionIndex();
                if (row >= 0) {
                    caseSimpleTable.refreshCellEditor(row);
                }
            }

            else if (tableViewer instanceof BonitaInsertGridViewer) {
                final BonitaInsertGridViewer igv = (BonitaInsertGridViewer) tableViewer;
                row = igv.getTable().getSelectionIndex();
                if (row >= 0) {
                    igv.refreshCellEditor(row);
                }
            }
        } else if (tableTreeViewer != null) {
            if (tableTreeViewer instanceof UpdateTreeViewer) {
                final UpdateTreeViewer utv = (UpdateTreeViewer) tableTreeViewer;
                if (row >= 0) {
                    utv.refresh();
                }
            }
        }
    }
}
