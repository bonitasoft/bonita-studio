/**
 * Copyright (C) 2013 BonitaSoft S.A.
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

import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.forbiddenCharactersValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.utf8InputValidator;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class LabelMigration extends CustomMigration {

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance instance : model.getAllInstances("process.FlowElement")) {
            final String label = instance.get("label");
            if (isValidAsName(label)) {
                instance.set("name", label);
            }
        }
        for (final Instance instance : model.getAllInstances("process.Pool")) {
            final String label = instance.get("label");
            if (isValidAsName(label)) {
                instance.set("name", label);
            }
        }
    }

    private boolean isValidAsName(final String label) {
        return multiValidator()
                .addValidator(utf8InputValidator(""))
                .addValidator(maxLengthValidator("", 255))
                .addValidator(forbiddenCharactersValidator("", '#', '%', '$')).create()
                .validate(label).isOK();
    }

}
