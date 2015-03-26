/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.contract;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public abstract class AbstractCreateContractCustomMigration extends CustomMigration {

    public AbstractCreateContractCustomMigration() {
        super();
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        addContract(model, getParentTypeToMigrate());
    }

    protected void addContract(final Model model, final String classType) {
        for (final Instance taskInstance : model.getAllInstances(classType)) {
            addContract(model, taskInstance);
        }
    }

    protected void addContract(final Model model, final Instance contractContainerInstance) {
        final Instance contract = contractContainerInstance.get("contract");
        if (contract == null) {
            final Instance contractInstance = model.newInstance("process.Contract");
            contractContainerInstance.set("contract", contractInstance);
        }
    }

    protected abstract String getParentTypeToMigrate();

}
