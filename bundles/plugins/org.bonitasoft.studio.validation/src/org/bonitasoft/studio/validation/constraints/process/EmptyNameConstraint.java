/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import static com.google.common.base.Preconditions.checkArgument;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.fileNameValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.forbiddenCharacterSequenceValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.forbiddenCharactersValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.databinding.validator.ValidatorFactory.utf8InputValidator;

import org.bonitasoft.bpm.model.process.Element;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.studio.common.databinding.validator.MultiValidatorFactory;
import org.bonitasoft.studio.common.databinding.validator.ValidatorFactory;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 * @author Vincent Hemery
 */
public class EmptyNameConstraint extends AbstractLiveValidationMarkerConstraint {

    private static final int MAX_NAME_LENGTH = 255;
    public static final int MAX_FILE_NAME_LENGTH = 75;

    /**
     * Get the multi-validator that will check the name of an element
     * 
     * @param elementsEClass the element's EClass (to deduce validation rules)
     * @return multi-validator to use
     */
    public static IValidator<String> nameValidator(EClass elementsEClass) {
        checkArgument(ProcessPackage.Literals.ELEMENT.isSuperTypeOf(elementsEClass));
        final String inputName = String.format("%s %s", elementsEClass.getName(), Messages.elementName);
        MultiValidatorFactory factory = multiValidator();
        if (!elementCanHaveEmptyName(elementsEClass)) {
            factory.addValidator(mandatoryValidator(inputName));
        }
        boolean validFileName = elementMustHaveAValidFileName(elementsEClass);
        if (validFileName) {
            factory.addValidator(fileNameValidator(inputName))
                    .addValidator(maxLengthValidator(inputName, MAX_FILE_NAME_LENGTH));
        }
        if (elementMustHaveARestrictedName(elementsEClass)) {
            factory.addValidator(utf8InputValidator(inputName))
                    .addValidator(forbiddenCharactersValidator(inputName, '#', '%', '$'))
                    .addValidator(ValidatorFactory.reservedRESTAPIKeywordsValidator());
            if (!validFileName) {
                factory.addValidator(maxLengthValidator(inputName, MAX_NAME_LENGTH));
            }
            // else, file name has its own max length
            if (ProcessPackage.Literals.TASK.isSuperTypeOf(elementsEClass)) {
                factory.addValidator(forbiddenCharacterSequenceValidator(Messages.invalidColumnUsageInTaskName, " :"));
            }
        }
        return factory.create();
    }

    private static boolean elementCanHaveEmptyName(final EClass elementsEClass) {
        // note : SequenceFlow may have a non-empty name or an empty name
        return ProcessPackage.Literals.SEQUENCE_FLOW.isSuperTypeOf(elementsEClass)
                || ProcessPackage.Literals.TEXT_ANNOTATION.isSuperTypeOf(elementsEClass);
    }

    private static boolean elementMustHaveARestrictedName(final EClass elementsEClass) {
        return ProcessPackage.Literals.SEQUENCE_FLOW.isSuperTypeOf(elementsEClass)
                || ProcessPackage.Literals.FLOW_ELEMENT.isSuperTypeOf(elementsEClass)
                /*
                 * It looks like AbstractProcess also needs this validation, according to previous
                 * org.bonitasoft.studio.common.diagram.dialog.OpenNameAndVersionDialog.nameUpdateStrategy() and
                 * org.bonitasoft.studio.properties.sections.general.ProcessElementNameContribution.nameValidationStatusProvider(IObservableValue)
                 * implementations.
                 */
                || ProcessPackage.Literals.ABSTRACT_PROCESS.isSuperTypeOf(elementsEClass);
    }

    /**
     * Test whether the element must have a name which can correspond to a file name.<br/>
     * This is usefull when name is used to export to a file.
     * 
     * @param elementsEClass the {@link EClass} of the model element
     * @return true when name must be a valid file name
     */
    private static boolean elementMustHaveAValidFileName(final EClass elementsEClass) {
        return ProcessPackage.Literals.ABSTRACT_PROCESS.isSuperTypeOf(elementsEClass);
    }

    protected IStatus doValidate(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        checkArgument(eObj instanceof Element);
        final Element element = (Element) eObj;
        final String name = element.getName();
        IValidator<String> validator = nameValidator(element.eClass());
        final IStatus status = validator.validate(name);
        if (!status.isOK()) {
            return ctx.createFailureStatus(status.getMessage());
        } else {
            return ctx.createSuccessStatus();
        }
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.nonemptynames";
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        return doValidate(ctx);
    }

    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        return doValidate(ctx);
    }

}
