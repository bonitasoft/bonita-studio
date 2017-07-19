/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class SendTaskMigration extends ReportCustomMigration {

    @Override
    public void migrateBefore(Model model, Metamodel metamodel)
            throws MigrationException {
        for (Instance sendTask : model.getAllInstances("process.SendTask")) {
            List<Instance> events = sendTask.get("events");
            if (events.size() > 1) {
                events.size();
                final List<Instance> toRemove = new ArrayList<Instance>();
                for (int i = 0; i < events.size(); i++) {
                    if (i != 0) {
                        toRemove.add(events.get(i));
                        addReportChange((String) sendTask.get("name"), events.get(i).getType().getEClass().getName(),
                                sendTask.getUuid(), Messages.bind(Messages.removeMessageEventFromSendTaskDescription,
                                        (String) events.get(i).get("name")),
                                Messages.messagesProperty, IStatus.ERROR);
                    }
                }
                for (Instance instance : toRemove) {
                    model.delete(instance);
                }
            }
        }
    }

}
