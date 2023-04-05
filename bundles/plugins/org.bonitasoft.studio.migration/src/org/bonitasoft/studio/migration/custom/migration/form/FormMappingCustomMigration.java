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
package org.bonitasoft.studio.migration.custom.migration.form;

import static com.google.common.collect.Iterables.filter;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public class FormMappingCustomMigration extends CustomMigration {

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        for (final Instance instance : filter(model.getAllInstances("process.PageFlow"),
                withoutFormMapping(ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING))) {
                instantiateFormMapping(instance, model, "formMapping");
        }

        for (final Instance instance : filter(model.getAllInstances("process.RecapFlow"),
                withoutFormMapping(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING))) {
                instantiateFormMapping(instance, model, "overviewFormMapping");
        }
    }

    private Predicate<Instance> withoutFormMapping(final EStructuralFeature feature) {
        return new Predicate<Instance>() {

            @Override
            public boolean apply(final Instance input) {
                return !input.isSet(feature);
            }
        };
    }

    private void instantiateFormMapping(final Instance input, final Model model, final String featureName) {
        final Instance newInstance = model.newInstance("process.FormMapping");
        final Instance targetFormExpression = newTargetFormExpressionInstance(model);
        newInstance.set("targetForm", targetFormExpression);
        newInstance.set("type", model.getMetamodel().getEEnum("process.FormMappingType").getEEnumLiteral("INTERNAL"));
        input.set(featureName, newInstance);
    }

    private Instance newTargetFormExpressionInstance(final Model model) {
        final Instance targetFormExpression = model.newInstance("expression.Expression");
        targetFormExpression.set("name", "");
        targetFormExpression.set("content", "");
        targetFormExpression.set("returnType", String.class.getName());
        targetFormExpression.set("type", ExpressionConstants.FORM_REFERENCE_TYPE);
        targetFormExpression.set("returnTypeFixed", true);
        return targetFormExpression;
    }

}
