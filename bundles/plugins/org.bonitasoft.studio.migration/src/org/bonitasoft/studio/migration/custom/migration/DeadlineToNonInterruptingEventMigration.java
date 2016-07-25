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
package org.bonitasoft.studio.migration.custom.migration;

import org.bonitasoft.studio.migration.utils.DeadlineMigrationStore;
import org.bonitasoft.studio.migration.utils.DeadlineStore;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class DeadlineToNonInterruptingEventMigration extends CustomMigration {

	@Override
	public void migrateAfter(final Model model, final Metamodel metamodel)
			throws MigrationException {
		for(final Instance task : model.getAllInstances("process.Task")){
			if(DeadlineMigrationStore.hasDeadline(task.getUuid())){
				addNonInterrutongTimerEvent(model, task);
			}
		}
		for(final Instance callactivity : model.getAllInstances("process.CallActivity")){
			if(DeadlineMigrationStore.hasDeadline(callactivity.getUuid())){
				addNonInterrutongTimerEvent(model, callactivity);
			}
		}
	}

	protected void addNonInterrutongTimerEvent(final Model model, final Instance task) {
		for(final DeadlineStore deadline : DeadlineMigrationStore.getDeadlines(task.getUuid())){
			final Instance instance = model.newInstance("process.NonInterruptingBoundaryTimerEvent");
			instance.set("name",deadline.getName());
			final Instance exp = new StringToExpressionConverter(model, task).parse(deadline.getCondition(), Long.class.getName(), false);
			instance.set("condition", exp);
			task.add("BoundaryIntermediateEvents", instance);
		}
	}

}
