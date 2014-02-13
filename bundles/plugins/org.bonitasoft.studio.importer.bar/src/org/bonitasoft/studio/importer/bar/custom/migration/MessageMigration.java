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
public class MessageMigration extends ReportCustomMigration {

	private Map<String, String> targetProcessNameConditions = new HashMap<String,String>();
	private Map<String, String> targetElementNameConditions = new HashMap<String,String>();
	private Map<String, List<Instance>> messageContents = new HashMap<String,List<Instance>>();

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance message : model.getAllInstances("process.Message")){
			final String targetProcess = message.get("targetProcessName");
			final String targetElement = message.get("targetElementName");
			message.set("targetProcessName", null);
			message.set("targetElementName", null);
			if(targetProcess != null && !targetProcess.trim().isEmpty()){
				targetProcessNameConditions.put(message.getUuid(), targetProcess);
			}
			if(targetElement != null && !targetElement.trim().isEmpty()){
				targetElementNameConditions.put(message.getUuid(), targetElement);
			}
			List<Instance> data = message.get("data");
			List<Instance> content = new ArrayList<Instance>();
			for(Instance d : data){
				Instance expression = ((Instance) d.get("defaultValue")).copy();
				expression.set("returnTypeFixed", false);
				content.add(expression);
				model.delete(d);
			}
			if(!content.isEmpty()){
				messageContents.put(message.getUuid(), content);
			}
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance message : model.getAllInstances("process.Message")){
			setMessageContent(message, model);
			setTargetProcessExpression(message,model);
			setTargetElementExpression(message,model);
		}
	}

	private void setMessageContent(Instance message,Model model) {
		final Instance tableExpression = model.newInstance("expression.TableExpression");
		if(messageContents.containsKey(message.getUuid())){
			final List<Instance> content = messageContents.get(message.getUuid());
			for(Instance expression : content){
				final Instance rowExpression = model.newInstance("expression.ListExpression");
				final Instance keyExpression = StringToExpressionConverter.createExpressionInstance(model, expression.get("name")+"Key", expression.get("name")+"Key", String.class.getName(),ExpressionConstants.CONSTANT_TYPE,true);
				rowExpression.add("expressions", keyExpression);
				rowExpression.add("expressions", expression);
				tableExpression.add("expressions", rowExpression);
			}
			message.set("messageContent", tableExpression);
			addReportChange((String) message.get("name"),message.getType().getEClass().getName(), message.getUuid(),Messages.messageContentMigrationDescription, Messages.messagesProperty, IStatus.WARNING);
		}else{
			message.set("messageContent", tableExpression);
		}
	}

	private void setTargetProcessExpression(Instance message, Model model) {
		Instance expression = null; 
		if(targetProcessNameConditions.containsKey(message.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(message));
			final String script = targetProcessNameConditions.get(message.getUuid());
			expression = converter.parse(script, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "targetProcessScript");
			}
			addReportChange((String) message.get("name"),message.getType().getEClass().getName(), message.getUuid(),Messages.targetProcessNameMigrationDescription, Messages.messagesProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		message.set("targetProcessExpression", expression);
	}



	private void setTargetElementExpression(Instance message, Model model) {
		Instance expression = null; 
		if(targetElementNameConditions.containsKey(message.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(message));
			final String url = targetElementNameConditions.get(message.getUuid());
			expression = converter.parse(url, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "targetElementScriptScript");
			}
			addReportChange((String) message.get("name"),message.getType().getEClass().getName(), message.getUuid(),Messages.targetElementNameMigrationDescription, Messages.messagesProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		message.set("targetElementExpression", expression);
	}
}
