/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class TabeMigration extends ReportCustomMigration {

	private Map<String, String> horizontalHeaders = new HashMap<String,String>();
	private Map<String, String> verticalHeaders = new HashMap<String,String>();
	private Map<String, String> maxRowForPaginations = new HashMap<String,String>();
	private Map<String, String> columnForInitialIndexes = new HashMap<String,String>();
	private Map<String, String> selectedValues = new HashMap<String,String>();
	private Map<String, Instance> cells = new HashMap<String,Instance>();
	private Map<String, String> minNumberOfColumns = new HashMap<String,String>();
	private Map<String, String> maxNumberOfColumns = new HashMap<String,String>();
	private Map<String, String> maxNumberOfRows = new HashMap<String,String>();
	private Map<String, String> minNumberOfRows = new HashMap<String,String>();


	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.AbstractTable")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				storeHorizontalHeaders(widget);
				storeVerticalHeaders(widget);
				storeCells(widget,model);
			}
		}
		for(Instance widget : model.getAllInstances("form.Table")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				storeMaxRowForPaginations(widget);
				storeColumnForInitialIndexes(widget);
				storeSelectedValues(widget);
			}
		}
		
		for(Instance widget : model.getAllInstances("form.DynamicTable")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				storeMinNumberOfColumns(widget);
				storeMaxNumberOfColumns(widget);
				storeMinNumberOfRows(widget);
				storeMaxNumberOfRows(widget);
			}
		}
	}

	private void storeMaxNumberOfRows(Instance widget) {
		final String maxNumberOfRow = widget.get("maxNumberOfRow");
		widget.set("maxNumberOfRow", null);
		if(maxNumberOfRow != null && !maxNumberOfRow.trim().isEmpty()){
			maxNumberOfRows.put(widget.getUuid(), maxNumberOfRow);
		}
	}

	private void storeMinNumberOfRows(Instance widget) {
		final String minNumberOfRow = widget.get("minNumberOfRow");
		widget.set("minNumberOfRow", null);
		if(minNumberOfRow != null && !minNumberOfRow.trim().isEmpty()){
			minNumberOfRows.put(widget.getUuid(), minNumberOfRow);
		}
	}

	private void storeMaxNumberOfColumns(Instance widget) {
		final String maxNumberOfColumn = widget.get("maxNumberOfColumn");
		widget.set("maxNumberOfColumn", null);
		if(maxNumberOfColumn != null && !maxNumberOfColumn.trim().isEmpty()){
			maxNumberOfColumns.put(widget.getUuid(), maxNumberOfColumn);
		}
	}

	private void storeMinNumberOfColumns(Instance widget) {
		final String minNumberOfColumn = widget.get("minNumberOfColumn");
		widget.set("minNumberOfColumn", null);
		if(minNumberOfColumn != null && !minNumberOfColumn.trim().isEmpty()){
			minNumberOfColumns.put(widget.getUuid(), minNumberOfColumn);
		}
	}

	private void storeColumnForInitialIndexes(Instance widget) {
		final String columnForInitialIndex = widget.get("columnForInitialSelectionIndex");
		widget.set("columnForInitialSelectionIndex", null);
		if(columnForInitialIndex != null && !columnForInitialIndex.trim().isEmpty()){
			columnForInitialIndexes.put(widget.getUuid(), columnForInitialIndex);
		}
	}
	
	private void storeSelectedValues(Instance widget) {
		final String selectedValues = widget.get("selectedValues");
		widget.set("selectedValues", null);
		if(selectedValues != null && !selectedValues.trim().isEmpty()){
			this.selectedValues.put(widget.getUuid(), selectedValues);
		}
	}

	private void storeMaxRowForPaginations(Instance widget) {
		final String maxRowForPagination = widget.get("maxRowForPagination");
		widget.set("maxRowForPagination", null);
		if(maxRowForPagination != null && !maxRowForPagination.trim().isEmpty()){
			maxRowForPaginations.put(widget.getUuid(), maxRowForPagination);
		}
	}

	private void storeCells(Instance widget, Model model) {
		final List<String> cells = widget.get("cells");	
		if(cells != null && !cells.isEmpty()){
			final Instance table = model.newInstance("expression.TableExpression");
			for(Object object : cells){
				List<String> row = (List<String>) object;
				if(!row.isEmpty()){
					Instance listExpression = model.newInstance("expression.ListExpression");
					for(String value : row){
						final Instance expression = getConverter(model,getScope(widget)).parse(value, String.class.getName(), false);
						if(ExpressionConstants.VARIABLE_TYPE.equals(expression.get("type"))){
							expression.set("returnType",StringToExpressionConverter.getDataReturnType(((List<Instance>) expression.get("referencedElements")).get(0)));
						}else if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
							expression.set("name","cellScript");
						}
						listExpression.add("expressions", expression);
					}
					table.add("expressions", listExpression);
				}
			}
			this.cells.put(widget.getUuid(), table);
		}
		widget.set("cells", null);
	}

	private void storeHorizontalHeaders(Instance widget) {
		final String horizontalHeader = widget.get("horizontalHeader");
		widget.set("horizontalHeader", null);
		if(horizontalHeader != null && !horizontalHeader.trim().isEmpty()){
			horizontalHeaders.put(widget.getUuid(), horizontalHeader);
		}
	}

	private void storeVerticalHeaders(Instance widget) {
		final String verticalHeader = widget.get("verticalHeader");
		widget.set("verticalHeader", null);
		if(verticalHeader != null && !verticalHeader.trim().isEmpty()){
			verticalHeaders.put(widget.getUuid(), verticalHeader);
		}
	}

	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance widget : model.getAllInstances("form.AbstractTable")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				setHorizontalHeaders(widget,model);
				setVerticalHeaders(widget,model);
				setCells(widget,model);
			}
		}
		
		for(Instance widget : model.getAllInstances("form.Table")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				setMaxRowForPaginations(widget,model);
				setColumnForInitialIndexes(widget,model);
				setSelectedValues(widget,model);
			}
		}
		
		for(Instance widget : model.getAllInstances("form.DynamicTable")){
			if(!(widget.getContainer().instanceOf("expression.Expression"))){
				setMinNumberOfColumns(widget,model);
				setMaxNumberOfColumns(widget,model);
				setMaxNumberOfRows(widget,model);
				setMinNumberOfRows(widget,model);
			}
		}
	}
	
	private void setMinNumberOfRows(Instance widget, Model model) {
		Instance expression = null ;
		if(minNumberOfRows.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(minNumberOfRows.get(widget.getUuid()), Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "minRowScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.minNumberOfRowMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("minNumberOfRow", expression);
	}

	private void setMaxNumberOfRows(Instance widget, Model model) {
		Instance expression = null ;
		if(maxNumberOfRows.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(maxNumberOfRows.get(widget.getUuid()), Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "maxRowScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.maxNumberOfRowMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("maxNumberOfRow", expression);
	}

	private void setMinNumberOfColumns(Instance widget, Model model) {
		Instance expression = null ;
		if(minNumberOfColumns.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(minNumberOfColumns.get(widget.getUuid()), Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "minColumnScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.minNumberOfColumnMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("minNumberOfColumn", expression);
	}
	
	private void setMaxNumberOfColumns(Instance widget, Model model) {
		Instance expression = null ;
		if(maxNumberOfColumns.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(maxNumberOfColumns.get(widget.getUuid()), Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "maxColumnScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.maxNumberOfColumnMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("maxNumberOfColumn", expression);
	}
	
	private void setMaxRowForPaginations(Instance widget, Model model) {
		Instance expression = null ;
		if(maxRowForPaginations.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(maxRowForPaginations.get(widget.getUuid()), Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "maxRowScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.maxRowForPaginationMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("maxRowForPagination", expression);
	}
	
	private void setColumnForInitialIndexes(Instance widget, Model model) {
		Instance expression = null ;
		if(columnForInitialIndexes.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(columnForInitialIndexes.get(widget.getUuid()), Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "columnForInitialSelectionIndexScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.columnForInitialIndexMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("columnForInitialSelectionIndex", expression);
	}
	
	private void setSelectedValues(Instance widget, Model model) {
		Instance expression = null ;
		if(selectedValues.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(selectedValues.get(widget.getUuid()), List.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "selectedValuesScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.selectedValuesMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", List.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("selectedValues", expression);
	}

	private void setCells(Instance widget, Model model) {
		Instance expression = null ;
		if(cells.containsKey(widget.getUuid())){
			expression = cells.get(widget.getUuid());
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.cellExpressionMigrationDescription, Messages.dataProperty, IStatus.WARNING);
			widget.set("tableExpression", expression);
		}
	}

	private void setVerticalHeaders(Instance widget, Model model) {
		Instance expression = null ;
		if(verticalHeaders.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(verticalHeaders.get(widget.getUuid()), List.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "verticalHeaderScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.verticalHeaderMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", List.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("verticalHeaderExpression", expression);
	}

	private void setHorizontalHeaders(Instance widget, Model model) {
		Instance expression = null ;
		if(horizontalHeaders.containsKey(widget.getUuid())){
			expression = getConverter(model,getScope(widget)).parse(horizontalHeaders.get(widget.getUuid()), List.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "horizontalHeaderScript");
			}
			addReportChange((String) widget.get("name"),widget.getEClass().getName(), widget.getUuid(), Messages.horizontalHeaderMigrationDescription, Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", List.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		widget.set("horizontalHeaderExpression", expression);
	}
	
}
