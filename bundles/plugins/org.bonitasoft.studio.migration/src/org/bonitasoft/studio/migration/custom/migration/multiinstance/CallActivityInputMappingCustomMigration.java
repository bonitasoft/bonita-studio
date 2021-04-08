/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.custom.migration.multiinstance;

import static com.google.common.collect.Iterables.filter;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 *
 */
public class CallActivityInputMappingCustomMigration extends CustomMigration {

    private final Map<String, Instance> dataInstances = new HashMap<String, Instance>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel) throws MigrationException {
        final Iterable<Instance> instancesWithProcessSource = filter(model.getAllInstances("process.InputMapping"), withProcessSource());
        for (final Instance instance : instancesWithProcessSource) {
            dataInstances.put(instance.getUuid(), ((Instance) instance.get("processSource")).copy());
        }
    }

    private Predicate<Instance> withProcessSource() {
        return new Predicate<Instance>() {

            @Override
            public boolean apply(final Instance inputMappingInstance) {
                return inputMappingInstance.get("processSource") instanceof Instance;
            }
        };
    }

    protected Map<String, Instance> getDataInstances() {
        return dataInstances;
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        final Iterable<Instance> instancesToMigrate = filter(model.getAllInstances("process.InputMapping"), matchingUUID());
        for (final Instance instance : instancesToMigrate) {
            convertDataToExpression(model, instance);
        }
    }

    private void convertDataToExpression(final Model model, final Instance inputMappingInstance) {
        final Instance dataInstance = dataInstances.get(inputMappingInstance.getUuid());
        final String name = dataInstance.get("name");
        final Instance defaultValueExpression = dataInstance.get("defaultValue");
        if (defaultValueExpression != null) {
            model.delete(defaultValueExpression);
        }
        final Instance expressionInstance = StringToExpressionConverter.createExpressionInstanceWithDependency(model,
                name,
                name,
                StringToExpressionConverter.getDataReturnType(dataInstance),
                ExpressionConstants.VARIABLE_TYPE,
                false,
                dataInstance);
        model.delete(dataInstance);
        inputMappingInstance.set("processSource", expressionInstance);
    }

    private Predicate<Instance> matchingUUID() {
        return new Predicate<Instance>() {

            @Override
            public boolean apply(final Instance inputMappingInstance) {
                return dataInstances.containsKey(inputMappingInstance.getUuid());
            }
        };
    }

}
