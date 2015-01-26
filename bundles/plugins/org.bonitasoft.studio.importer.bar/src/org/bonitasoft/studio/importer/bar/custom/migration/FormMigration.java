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

import java.util.ArrayList;
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
public class FormMigration extends ReportCustomMigration {

	private Map<String, List<Instance>> formActions = new HashMap<String,List<Instance>>();
	private Map<String, String> formLabels = new HashMap<String,String>();
	
	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance form : model.getAllInstances("form.Form")){
			final List<Instance> actions = form.get("scripts");
			if(!actions.isEmpty()){
				final StringToExpressionConverter converter = getConverter(model,getScope(form));
				final List<Instance> operations = new ArrayList<Instance>();
				for(Instance action : actions){
					final Instance operation = converter.parseOperation(action, String.class.getName(), false);
					operations.add(operation);
					model.delete(action);
				}
				formActions.put(form.getUuid(), operations);
			}
			String pageLabel = form.get("pageLabel");
			form.set("pageLabel", null);
			if(pageLabel != null && !pageLabel.trim().isEmpty()){
				formLabels.put(form.getUuid(), pageLabel);
			}
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance form : model.getAllInstances("form.Form")){
			if(formActions.containsKey(form.getUuid())){
				for(Instance operation : formActions.get(form.getUuid())){
					Instance actionExp = operation.get("rightOperand");
					if(actionExp != null){
						 if(ExpressionConstants.SCRIPT_TYPE.equals(actionExp.get("type"))){
							Instance leftExp = operation.get("leftOperand");
							if(leftExp!= null){
								actionExp.set("returnType",leftExp.get("returnType"));
							}
						}
					}
					form.add("actions", operation);
				}
				addReportChange((String) form.get("name"),form.getEClass().getName(), form.getUuid(), Messages.formActionsMigrationDescription, Messages.actionProperty, IStatus.WARNING);
			}
			
			Instance expression = null;
			if(formLabels.containsKey(form.getUuid())){
				expression = getConverter(model,getScope(form)).parse(formLabels.get(form.getUuid()), String.class.getName(), true);
				if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
					expression.set("name", "displayLabelScript");
				}
				addReportChange((String) form.get("name"),form.getEClass().getName(), form.getUuid(), Messages.displayLabelMigrationDescription, Messages.generalProperty, StringToExpressionConverter.getStatusForExpression(expression));
			}else{
				expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
			}
			form.set("pageLabel", expression);
		}
	}
	
}
