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
package org.bonitasoft.studio.migration.custom.migration.timer;

import java.util.Date;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.TimerUtil;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class StartTimerConditionCustomMigration extends CustomMigration {

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance startTimer : model.getAllInstances("process.StartTimerEvent")){
			EEnumLiteral scriptType = startTimer.get("scriptType");
			Date from =  startTimer.get("from");
			Date at = startTimer.get("at");
			int month = startTimer.get("month");
			int day = startTimer.get("day");
			int hours = startTimer.get("hours");
			int dayNumber = startTimer.get("dayNumber");
			int minutes = startTimer.get("minutes");
			int seconds = startTimer.get("seconds");
			StartTimerEvent event = ProcessFactory.eINSTANCE.createStartTimerEvent();	
			event.setAt(at);
			event.setFrom(from);
			event.setMonth(month);
			event.setDay(day);
			event.setHours(hours);
			event.setDayNumber(dayNumber);
			event.setMinutes(minutes);
			event.setSeconds(seconds);
			StartTimerScriptType type = null;
			for(StartTimerScriptType t : StartTimerScriptType.values()){
				if(t.getLiteral().equals(scriptType.getLiteral())){
					type = t;
				}
			}
			event.setScriptType(type);
			if(TimerUtil.isCycle(event)){
				String cron = TimerUtil.getTimerExpressionContent(event);
				if(cron != null){
					Instance condition = startTimer.get("condition");
					if(condition != null){
						model.delete(condition);
					}
					condition = model.newInstance("expression.Expression");
					condition.set("name", cron);
					condition.set("content", cron);
					condition.set("returnType", String.class.getName());
					condition.set("type", ExpressionConstants.CONSTANT_TYPE);
					startTimer.set("condition", condition);
				}
			}else if(type == StartTimerScriptType.CONSTANT && at != null){
				Instance condition = startTimer.get("condition");
				if(condition != null){
					model.delete(condition);
				}
				condition = model.newInstance("expression.Expression");
				condition.set("name", "fixedDate");
				condition.set("content", TimerUtil.generateConstant(at));
				condition.set("returnType", Date.class.getName());
				condition.set("type", ExpressionConstants.SCRIPT_TYPE);
				condition.set("interpreter", ExpressionConstants.GROOVY);
				startTimer.set("condition", condition);
			}
			
		}
	}
	
}
