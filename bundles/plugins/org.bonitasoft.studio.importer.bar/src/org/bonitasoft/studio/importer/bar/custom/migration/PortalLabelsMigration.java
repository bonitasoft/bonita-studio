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
public class PortalLabelsMigration extends ReportCustomMigration {

	private Map<String, String> stepSummary = new HashMap<String,String>();
	private Map<String, String> dynamicLabel = new HashMap<String,String>();
	private Map<String, String> dynamicDescription = new HashMap<String,String>();
	
	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance flowElement : model.getAllInstances("process.FlowElement")){
			final String stepSummary = flowElement.get("stepSummary");
			final String dynamicLabel = flowElement.get("dynamicLabel");
			final String dynamicDescription = flowElement.get("dynamicDescription");
			flowElement.set("stepSummary", null);
			flowElement.set("dynamicLabel", null);
			flowElement.set("dynamicDescription", null);
			if(stepSummary != null && !stepSummary.trim().isEmpty()){
				this.stepSummary.put(flowElement.getUuid(), stepSummary);
			}
			if(dynamicLabel != null && !dynamicLabel.trim().isEmpty()){
				this.dynamicLabel.put(flowElement.getUuid(), dynamicLabel);
			}
			if(dynamicDescription != null && !dynamicDescription.trim().isEmpty()){
				this.dynamicDescription.put(flowElement.getUuid(), dynamicDescription);
			}
		}
	}
	
	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance flowElement : model.getAllInstances("process.FlowElement")){
			setDynamicLabel(flowElement, model);
			setDynamicDescription(flowElement,model);
			setStepSummary(flowElement,model);
		}
	}
	
	private void setDynamicLabel(Instance flowElement,Model model) {
		Instance expression = null; 
		if(dynamicLabel.containsKey(flowElement.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(flowElement));
			final String condition = dynamicLabel.get(flowElement.getUuid());
			expression = converter.parse(condition, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "dynamicLabelScript");
			}
			addReportChange((String) flowElement.get("name"),flowElement.getType().getEClass().getName(), flowElement.getUuid(),Messages.dynamicLabelMigrationDescription, Messages.portalProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		flowElement.set("dynamicLabel", expression);
	}

	private void setDynamicDescription(Instance flowElement,Model model) {
		Instance expression = null; 
		if(dynamicDescription.containsKey(flowElement.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(flowElement));
			final String condition = dynamicDescription.get(flowElement.getUuid());
			expression = converter.parse(condition, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "dynamicDescriptionScript");
			}
			addReportChange((String) flowElement.get("name"),flowElement.getType().getEClass().getName(), flowElement.getUuid(),Messages.dynamicDescriptionMigrationDescription, Messages.portalProperty,ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		flowElement.set("dynamicDescription", expression);
	}
	
	private void setStepSummary(Instance flowElement,Model model) {
		Instance expression = null; 
		if(stepSummary.containsKey(flowElement.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(flowElement));
			final String condition = stepSummary.get(flowElement.getUuid());
			expression = converter.parse(condition, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "desciptionAfterCompletionScript");
			}
			addReportChange((String) flowElement.get("name"),flowElement.getType().getEClass().getName(), flowElement.getUuid(),Messages.stepSummaryMigrationDescription, Messages.portalProperty,ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		flowElement.set("stepSummary", expression);
	}
}
