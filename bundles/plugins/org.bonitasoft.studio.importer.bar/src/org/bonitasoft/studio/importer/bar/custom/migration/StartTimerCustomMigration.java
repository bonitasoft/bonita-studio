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

import org.bonitasoft.studio.common.TimerUtil;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class StartTimerCustomMigration extends ReportCustomMigration {

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for(Instance startTimer : model.getAllInstances("process.StartTimerEvent")){
			EEnumLiteral scriptType = startTimer.get("scriptType");
			StartTimerEvent event = ProcessFactory.eINSTANCE.createStartTimerEvent();
			StartTimerScriptType type =null;
			for(StartTimerScriptType t : StartTimerScriptType.values()){
				if(t.getLiteral().equals(scriptType.getLiteral())){
					type  = t;
				}
			}
			event.setScriptType(type);
			if(TimerUtil.isCycle(event)){
				addReportChange((String) startTimer.get("name"), startTimer.getEClass().getName(), startTimer.getUuid(), Messages.startTimerCycleMigration, Messages.timerCondition, IStatus.WARNING);
			}
		}
	}
	
}
