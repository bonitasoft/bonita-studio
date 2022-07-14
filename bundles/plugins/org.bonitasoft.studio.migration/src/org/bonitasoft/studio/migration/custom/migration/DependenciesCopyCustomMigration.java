/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;


public class DependenciesCopyCustomMigration extends CustomMigration {

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {

        final List<Instance> allReferencesToBeDeleted = new ArrayList<>();
        for (final Instance expInstance : model.getAllInstances("expression.Expression")) {
            final List<Instance> referencedElements = expInstance.get("referencedElements");
            final List<Instance> newReferencedElements = new ArrayList<>();
            final List<Instance> referencesToBeDeleted = new ArrayList<>();
            if (referencedElements.isEmpty() && ExpressionConstants.VARIABLE_TYPE.equals(expInstance.get("type"))) {
                String content = expInstance.get("content");
                if (content != null && !content.isEmpty()) {
                    new StringToExpressionConverter(model, getScope(expInstance)).resolveDataDependencies(expInstance);
                }
            }
            if (referencedElements.isEmpty() && ExpressionConstants.PARAMETER_TYPE.equals(expInstance.get("type"))) {
                String content = expInstance.get("content");
                if (content != null && !content.isEmpty()) {
                    new StringToExpressionConverter(model, getScope(expInstance)).resolveParameterDependencies(expInstance);
                }
            }
            for (final Instance refElement : referencedElements) {
                Instance newCleanedDependency = null;
                if (refElement.instanceOf("process.Data")) {
                    newCleanedDependency = newCleaneDataDependency(refElement);
                    referencesToBeDeleted.add(refElement);
                } else if (refElement.instanceOf("process.Document")) {
                    newCleanedDependency = newCleanedDocumentDependency(refElement, model);
                    referencesToBeDeleted.add(refElement);
                } else if (model.getMetamodel().getEClass("process.ContractInput") != null
                        && refElement.instanceOf("process.ContractInput")) {
                    newCleanedDependency = newCleanedContractInputDependency(refElement, model);
                    referencesToBeDeleted.add(refElement);
                }
                if (newCleanedDependency != null) {
                    newReferencedElements.add(newCleanedDependency);
                }
            }
            for (final Instance instanceToDelete : referencesToBeDeleted) {
                expInstance.remove("referencedElements", instanceToDelete);
            }
            allReferencesToBeDeleted.addAll(referencesToBeDeleted);
            for (final Instance newInstance : newReferencedElements) {
                expInstance.add("referencedElements", newInstance);
            }
        }
        for (final Instance instance : allReferencesToBeDeleted) {
            model.delete(instance);
        }
        allReferencesToBeDeleted.clear();
        for (final Instance expInstance : model.getAllInstances("expression.Expression")) {
            if (expInstance.getContainer() == null) {
                allReferencesToBeDeleted.add(expInstance);
            }
        }
        for (final Instance instance : allReferencesToBeDeleted) {
            model.delete(instance);
        }
        allReferencesToBeDeleted.clear();
    }

    private Instance newCleanedContractInputDependency(Instance refElement, final Model model) {
        final Instance newInstance = refElement.copy();
        ((List<Instance>) newInstance.get("inputs")).stream().forEach(model::delete);
        return newInstance;
    }

    protected Instance newCleanedDocumentDependency(final Instance refElement, final Model model) {
        final Instance newInstance = model.newInstance(refElement.getEClass());
        newInstance.set("name", refElement.get("name"));
        if (newInstance.getEClass().getEStructuralFeature("multiple") != null) {
            newInstance.set("multiple", refElement.get("multiple"));
        }
        return newInstance;
    }

    protected Instance newCleaneDataDependency(final Instance refElement) {
        final Instance newInstance = refElement.copy();
        newInstance.set("defaultValue", null);
        return newInstance;
    }

    protected Instance getScope(final Instance element) {
        Instance container = element;
        while (container != null
                && !container.instanceOf("process.AbstractProcess")) {
            container = container.getContainer();
        }
        return container;
    }
}
