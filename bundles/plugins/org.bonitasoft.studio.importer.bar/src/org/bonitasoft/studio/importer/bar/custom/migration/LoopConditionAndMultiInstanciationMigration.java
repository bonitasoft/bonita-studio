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
public class LoopConditionAndMultiInstanciationMigration extends
ReportCustomMigration {

	enum IterationType{NONE,LOOP,MULTI}
	private Map<String, String> loopConditions = new HashMap<String,String>();
	private Map<String, String> loopMaximums = new HashMap<String,String>();
	private Map<String, IterationType> iterationTypes = new HashMap<String,IterationType>();



	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance activity : model.getAllInstances("process.Activity")){
			Instance multinsantiationInstance =  activity.get("multiInstantiation");
			boolean isMulti = false ;
			boolean isLoop = activity.get("isLoop") ;
			if(multinsantiationInstance != null){
				isMulti = multinsantiationInstance.get("enabled");
				addReportChange((String) activity.get("name"), activity.getType().getEClass().getName(), activity.getUuid(), Messages.removeMultiInstantiationMigrationDescription, Messages.iterationProperty, IStatus.ERROR);
				model.delete(multinsantiationInstance);
			}

			final String conditionScript = activity.get("loopCondition");
			final String maximumScript = activity.get("loopMaximum");
			activity.set("loopCondition", null);
			activity.set("loopMaximum", null);
			if(conditionScript != null && !conditionScript.trim().isEmpty()){
				loopConditions.put(activity.getUuid(), conditionScript);
			}
			if(maximumScript != null && !maximumScript.trim().isEmpty()){
				loopMaximums.put(activity.getUuid(), maximumScript);
			}
			if(isMulti){
				iterationTypes.put(activity.getUuid(), IterationType.MULTI);
			}else if(isLoop){
				iterationTypes.put(activity.getUuid(), IterationType.LOOP);
			}else{
				iterationTypes.put(activity.getUuid(), IterationType.NONE);
			}
		}
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance activity : model.getAllInstances("process.Activity")){
			setIerationType(activity, model);
			setLoopCondition(activity,model);
			setLoopMaximum(activity,model);
		}
	}

	private void setLoopMaximum(Instance activity,Model model) {
		Instance expression = null; 
		if(loopMaximums.containsKey(activity.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(activity));
			final String maximum = loopMaximums.get(activity.getUuid());
			expression = converter.parse(maximum, Integer.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "maximumLoopScript");
			}
			addReportChange((String) activity.get("name"),activity.getType().getEClass().getName(), activity.getUuid(),Messages.loopMaximumScriptMigrationDescription, Messages.iterationProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Integer.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		activity.set("loopMaximum", expression);
	}

	private void setLoopCondition(Instance activity,Model model) {
		Instance expression = null; 
		if(loopConditions.containsKey(activity.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(activity));
			String condition = loopConditions.get(activity.getUuid());
			condition = "${" + condition + "}";
			expression = converter.parse(condition, Boolean.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "loopConditionScript");
			}
			addReportChange((String) activity.get("name"),activity.getType().getEClass().getName(), activity.getUuid(),Messages.loopConditionScriptMigrationDescription, Messages.iterationProperty,ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Boolean.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		activity.set("loopCondition", expression);
	}

	private void setIerationType(Instance activity,Model model) {
		switch (iterationTypes.get(activity.getUuid())) {
		case NONE: activity.set("isLoop",false);activity.set("isMultiInstance",false); break;
		case LOOP:activity.set("isLoop",true);activity.set("isMultiInstance",false); break;
		case MULTI:activity.set("isLoop",false);activity.set("isMultiInstance",true);activity.set("multiInstantiation",  model.newInstance("process.MultiInstantiation")); break;
		default:break;
		}
	}

}
