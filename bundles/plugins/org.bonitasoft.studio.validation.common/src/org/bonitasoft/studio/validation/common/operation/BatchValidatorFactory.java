/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.common.operation;

import java.util.Set;

import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.emf.validation.service.ModelValidationService;

public class BatchValidatorFactory {
    
    private static final Set<String> DEPENDENCY_CONSTRAINTS = Set.of(
            "org.bonitasoft.studio.validation.constraints.connectorexistence",
            "org.bonitasoft.studio.validation.constraints.process.configuration.dependencies",
            "org.bonitasoft.studio.validation.constraints.process.localconfiguration.dependencies"
            );

    public IBatchValidator create() {
       IBatchValidator validator = (IBatchValidator) ModelValidationService.getInstance()
                .newValidator(EvaluationMode.BATCH);
        validator.setIncludeLiveConstraints(true);
        return validator;
    }
    
    public IBatchValidator create(IConstraintFilter constraintFilter) {
        IBatchValidator validator = (IBatchValidator) ModelValidationService.getInstance()
                .newValidator(EvaluationMode.BATCH);
        validator.setIncludeLiveConstraints(true);
        validator.addConstraintFilter(constraintFilter);
        return validator;
    }

    public static IConstraintFilter dependencyConstraintsFilter() {
        return (constraint, target) -> constraint.getId() != null && DEPENDENCY_CONSTRAINTS.contains(constraint.getId());
    }

}
