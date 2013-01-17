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
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class PageFlowMigration extends ReportCustomMigration {

	private Map<String, String> confirmationMessages = new HashMap<String,String>();
	private Map<String, String> entryRedirectionUrls = new HashMap<String,String>();
	private Map<String, List<Instance>> entryRedirectionActions = new HashMap<String,List<Instance>>();
	
	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance pageFlow : model.getAllInstances("process.PageFlow")){
			final String confirmationScript = pageFlow.get("confirmationMessage");
			final String redirectionScript = pageFlow.get("entryRedirectionURL");
			pageFlow.set("confirmationMessage", null);
			pageFlow.set("entryRedirectionURL", null);
			if(confirmationScript != null && !confirmationScript.trim().isEmpty()){
				confirmationMessages.put(pageFlow.getUuid(), confirmationScript);
			}
			if(redirectionScript != null && !redirectionScript.trim().isEmpty()){
				entryRedirectionUrls.put(pageFlow.getUuid(), redirectionScript);
			}
			final List<Instance> actions = pageFlow.get("entryRedirectionActions");
			List<Instance> operations = new ArrayList<Instance>();
			for(Instance groovyScript : actions){
				final StringToExpressionConverter converter = getConverter(model);
				Instance operation = converter.parseOperation(groovyScript, String.class.getName(), true);
				operations.add(operation);
				model.delete(groovyScript);
			}
			if(!operations.isEmpty()){
				entryRedirectionActions.put(pageFlow.getUuid(), operations);
			}
		
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance pageFlow : model.getAllInstances("process.PageFlow")){
			setConfirmationMessage(pageFlow, model);
			setRedirectionURL(pageFlow,model);
			setRedirectionActions(pageFlow,model);
		}
	}

	private void setRedirectionActions(Instance pageFlow, Model model) {
		if(entryRedirectionActions.containsKey(pageFlow.getUuid())){
			pageFlow.get("entryRedirectionActions");
			for(Instance operation : entryRedirectionActions.get(pageFlow.getUuid())){
				pageFlow.add("entryRedirectionActions", operation);
				addReportChange((String) pageFlow.get("name"),pageFlow.getType().getEClass().getName(), pageFlow.getUuid(),Messages.redirectionActionMigrationDescription, Messages.entryPageflowProperty, ExpressionConstants.SCRIPT_TYPE.equals(((Instance)operation.get("rightOperand")).get("type")) ? IStatus.WARNING : IStatus.OK);
			}
		}
	}

	private void setRedirectionURL(Instance pageFlow, Model model) {
		Instance expression = null; 
		if(entryRedirectionUrls.containsKey(pageFlow.getUuid())){
			final StringToExpressionConverter converter = getConverter(model);
			final String url = entryRedirectionUrls.get(pageFlow.getUuid());
			expression = converter.parse(url, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "redirectionUrlScript");
			}
			addReportChange((String) pageFlow.get("name"),pageFlow.getType().getEClass().getName(), pageFlow.getUuid(),Messages.redirectionUrlMigrationDescription, Messages.entryPageflowProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		pageFlow.set("entryRedirectionURL", expression);
	}

	private void setConfirmationMessage(Instance pageFlow, Model model) {
		Instance expression = null; 
		if(confirmationMessages.containsKey(pageFlow.getUuid())){
			final StringToExpressionConverter converter = getConverter(model);
			final String confirmationMessage = confirmationMessages.get(pageFlow.getUuid());
			expression = converter.parse(confirmationMessage, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "confirmationMessageScript");
			}
			addReportChange((String) pageFlow.get("name"),pageFlow.getType().getEClass().getName(), pageFlow.getUuid(),Messages.confirmationMessageMigrationDescription, Messages.confirmationProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		pageFlow.set("confirmationMessage", expression);
		
	}
}
