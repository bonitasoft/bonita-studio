/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.custom.migration.callactivity;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public class DefaultCallActivityDefaultMapping extends CustomMigration {

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        for (final Instance inputmappingInstance : model.getAllInstances("process.InputMapping")) {
            final EEnum eEnum = metamodel.getEEnum("process.InputMappingAssignationType");
            inputmappingInstance.set("assignationType", eEnum.getEEnumLiteral("Data"));
        }
    }

}
