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
import org.bonitasoft.studio.migration.utils.LegacyTimerExpressionGenerator;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class StartTimerConditionCustomMigration extends CustomMigration {

    private LegacyTimerExpressionGenerator timerExpressionGenerator;

    @Override
	public void migrateAfter(final Model model, final Metamodel metamodel)
			throws MigrationException {
		for(final Instance startTimer : model.getAllInstances("process.StartTimerEvent")){
			final EEnumLiteral scriptType = startTimer.get("scriptType");
			final Date from =  startTimer.get("from");
			final Date at = startTimer.get("at");
			final int month = startTimer.get("month");
			final int day = startTimer.get("day");
			final int hours = startTimer.get("hours");
			final int dayNumber = startTimer.get("dayNumber");
			final int minutes = startTimer.get("minutes");
			final int seconds = startTimer.get("seconds");
			final StartTimerEvent event = ProcessFactory.eINSTANCE.createStartTimerEvent();
			event.setAt(at);
			event.setFrom(from);
			event.setMonth(month);
			event.setDay(day);
			event.setHours(hours);
			event.setDayNumber(dayNumber);
			event.setMinutes(minutes);
			event.setSeconds(seconds);
			StartTimerScriptType type = null;
			for(final StartTimerScriptType t : StartTimerScriptType.values()){
				if(t.getLiteral().equals(scriptType.getLiteral())){
					type = t;
				}
			}
			event.setScriptType(type);
			if(LegacyTimerExpressionGenerator.isCycle(event)){
                timerExpressionGenerator = new LegacyTimerExpressionGenerator();
                final String cron = timerExpressionGenerator.getTimerExpressionContent(event);
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
				condition.set("content", LegacyTimerExpressionGenerator.generateConstant(at));
				condition.set("returnType", Date.class.getName());
				condition.set("type", ExpressionConstants.SCRIPT_TYPE);
				condition.set("interpreter", ExpressionConstants.GROOVY);
				startTimer.set("condition", condition);
			}

		}
	}

}
