/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public class Removev6FromCustomMigration extends CustomMigration {

    private Map<String, String> names = new HashMap<>();
    private Map<String, String> documentations = new HashMap<>();
    private Map<String, Object> textAnnotationAttachments = new HashMap<>();

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.CustomMigration#migrateBefore(org.eclipse.emf.edapt.spi.migration.Model,
     * org.eclipse.emf.edapt.spi.migration.Metamodel)
     */
    @Override
    public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {
        for (final Instance absractProcess : model.getAllInstances("process.AbstractProcess")) {
            store(absractProcess, model);
        }
        for (final Instance flowElement : model.getAllInstances("process.FlowElement")) {
            store(flowElement, model);
        }
    }

    private void store(final Instance instance, Model model) {
        names.put(instance.getUuid(), instance.get("name"));
        documentations.put(instance.getUuid(), instance.get("documentation"));
        List<Instance> attachments = instance.get("textAnnotationAttachment");
        List<Instance> newList = new ArrayList<>();
        for (Instance attacment : attachments) {
            newList.add(attacment.copy());
        }
        textAnnotationAttachments.put(instance.getUuid(), newList);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.CustomMigration#migrateAfter(org.eclipse.emf.edapt.spi.migration.Model,
     * org.eclipse.emf.edapt.spi.migration.Metamodel)
     */
    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
        for (final Instance absractProcess : model.getAllInstances("process.AbstractProcess")) {
            write(absractProcess);
        }
        for (final Instance flowElement : model.getAllInstances("process.FlowElement")) {
            write(flowElement);
        }

        for (final Instance instance : model.getAllInstances("process.MainProcess")) {
            Instance formMapping = instance.get("formMapping");
            if (formMapping != null) {
                model.delete(formMapping);
            }
            Instance overviewFormMapping = instance.get("overviewFormMapping");
            if (overviewFormMapping != null) {
                model.delete(overviewFormMapping);
            }
        }
    }

    private void write(final Instance instance) {
        instance.set("name", names.get(instance.getUuid()));
        instance.set("documentation", documentations.get(instance.getUuid()));
        List<Instance> attachements = (List<Instance>) textAnnotationAttachments.get(instance.getUuid());
        for (Instance attachement : attachements) {
            instance.add("textAnnotationAttachment", attachement);
        }
    }
}
