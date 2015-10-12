/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorFactory;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorType;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

/**
 * @author Romain Bioteau
 */
public class ValidatorMigration extends ReportCustomMigration {

    private final Map<String, String> validatorLabels = new HashMap<String, String>();
    private final Map<String, String> validatorParameters = new HashMap<String, String>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance validator : model.getAllInstances("form.Validator")) {
            final String label = validator.get("label");
            final String parameter = validator.get("parameter");
            validator.set("label", null);
            validator.set("parameter", null);
            if (label != null && !label.trim().isEmpty()) {
                validatorLabels.put(validator.getUuid(), label);
            }
            if (parameter != null && !parameter.trim().isEmpty()) {
                validatorParameters.put(validator.getUuid(), parameter);
            }
        }
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        buildRepository();
        for (final Instance validator : model.getAllInstances("form.Validator")) {
            setDisplayName(validator, model);
            setParameter(validator, model);
            try {
                createValidatorDescriptor(validator);
            } catch (final JavaModelException e) {
                throw new MigrationException("Failed to create a validator descriptor for " + validator.get("validatorClass"), e);
            }
        }
    }

    protected void buildRepository() {
        RepositoryManager.getInstance().getCurrentRepository().build(Repository.NULL_PROGRESS_MONITOR);
    }

    protected void createValidatorDescriptor(final Instance validator) throws JavaModelException {
        final String validatorClassName = validator.get("validatorClass");
        final ValidatorDescriptorRepositoryStore validatorDescriptorStore = RepositoryManager.getInstance().getRepositoryStore(
                ValidatorDescriptorRepositoryStore.class);
        ValidatorDescriptor descriptor = validatorDescriptorStore.getValidatorDescriptor(validatorClassName);
        if (descriptor == null) {
            descriptor = ValidatorFactory.eINSTANCE.createValidatorDescriptor();
            descriptor.setClassName(validatorClassName);
            descriptor.setHasParameter(true);
            descriptor.setType(ValidatorType.FILED_VALIDATOR);
            final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
            final IType type = javaProject.findType(validatorClassName);
            if (type != null) {
                for (final IType interfaceType : type.newSupertypeHierarchy(Repository.NULL_PROGRESS_MONITOR).getAllInterfaces()) {
                    if (interfaceType.getTypeQualifiedName().equals("IFormPageValidator")) {
                        descriptor.setType(ValidatorType.PAGE_VALIDATOR);
                    }
                }
            }
            String name = validatorClassName;
            if (name.indexOf(".") != -1) {
                name = name.substring(name.lastIndexOf(".") + 1);
            }
            descriptor.setName(name);
            final String fileName = descriptor.getName() + "." + ValidatorDescriptorRepositoryStore.VALIDATOR_EXT;
            final IRepositoryFileStore fileStore = validatorDescriptorStore.createRepositoryFileStore(fileName);
            fileStore.save(descriptor);
        }
    }

    protected void setParameter(final Instance validator, final Model model) {
        Instance expression = null;
        final String validatorUUID = validator.getUuid();
        if (validatorParameters.containsKey(validatorUUID)) {
            String stringToParse = validatorParameters.get(validatorUUID);
            String newReturnType = String.class.getName();
            boolean returnTypeFixed = false;
            if (isBonitaGroovyBasedValidator(validator)) {
                stringToParse = stringToParse.startsWith("${") ? stringToParse : "${" + stringToParse + "}";
                newReturnType = Boolean.class.getName();
                returnTypeFixed = true;
            }
            expression = getConverter(model, getScope(validator)).parse(stringToParse, newReturnType, returnTypeFixed);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "parameterScript");
            }
            final int status = computeStatus(expression, newReturnType);
            addReportChange(
                    (String) validator.get("name"),
                    validator.getEClass().getName(),
                    validatorUUID,
                    Messages.validatorParameterMigrationDescription,
                    Messages.validatorsProperty,
                    status);
        } else {
            expression = createEmptyParameterExpression(validator, model);
        }
        validator.set("parameter", expression);
    }

    protected Instance createEmptyParameterExpression(final Instance validator, final Model model) {
        final boolean isBonitaGroovyBasedValidator = isBonitaGroovyBasedValidator(validator);
        final String returnType = isBonitaGroovyBasedValidator ? Boolean.class.getName() : String.class.getName();
        return StringToExpressionConverter.createExpressionInstance(
                model,
                "",
                "",
                returnType,
                ExpressionConstants.CONSTANT_TYPE,
                isBonitaGroovyBasedValidator);
    }

    protected int computeStatus(final Instance expression, final String newReturnType) {
        int status = StringToExpressionConverter.getStatusForExpression(expression);
        if (!Boolean.class.getName().equals(newReturnType)) {
            status = IStatus.WARNING;
        }
        return status;
    }

    protected boolean isBonitaGroovyBasedValidator(final Instance validator) {
        final String className = validator.get("validatorClass");
        return className != null
                && (className.contains("GroovyPageValidator") || className.contains("GroovyFieldValidator"));
    }

    private void setDisplayName(final Instance validator, final Model model) {
        Instance expression = null;
        final String validatorUUID = validator.getUuid();
        if (validatorLabels.containsKey(validatorUUID)) {
            expression = getConverter(model, getScope(validator)).parse(validatorLabels.get(validatorUUID), String.class.getName(), true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "errorMessageScript");
            }
            addReportChange((String) validator.get("name"),
                    validator.getEClass().getName(),
                    validatorUUID,
                    Messages.validatorDisplayNameMigrationDescription,
                    Messages.validatorsProperty,
                    StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        validator.set("displayName", expression);
    }

}
