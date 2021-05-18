/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.validator;

import static com.google.common.collect.Sets.newHashSet;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.la.application.ui.control.model.AddApplicationMode;
import org.bonitasoft.studio.la.application.ui.editor.NavigationPageNode;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageDescriptor;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageProvider;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.RegExpValidator;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ApplicationDescriptorValidators {

    private static final Set<String> RESERVED_TOKENS = newHashSet("API", "content", "theme");
    private static final int TOKEN_MAX_LENGTH = 50;
    private static final Integer MAX_LENGTH = 255;

    private static InputLengthValidator menuLengthValidator = new InputLengthValidator(Messages.menu, MAX_LENGTH);
    private static InputLengthValidator pageTokenLengthValidator = new InputLengthValidator(Messages.applicationPageToken,
            MAX_LENGTH);
    public static InputLengthValidator displayNameValidator = new InputLengthValidator(Messages.displayName, MAX_LENGTH);

    private ApplicationDescriptorValidators() {

    }

    public static Function<NavigationPageNode, IStatus> menuColumnValidator() {
        return node -> {
            final String menuLabel = node.getMenuLabel();
            return !node.isOrphan() && Strings.isNullOrEmpty(menuLabel)
                    ? ValidationStatus.error(org.bonitasoft.studio.ui.i18n.Messages.required)
                    : menuLengthValidator.validate(menuLabel);
        };
    }

    public static Function<NavigationPageNode, IStatus> applicationPageColumnValidator(CustomPageProvider provider) {
        return node -> {
            if (node.isTopMenu()) {
                return ValidationStatus.ok();
            }
            final String applicationPage = node.getApplicationPage();
            if (Strings.isNullOrEmpty(applicationPage)) {
                return ValidationStatus.error(org.bonitasoft.studio.ui.i18n.Messages.required);
            }
            final IStatus pageFormatStatus = customPageNameValidator().validate(applicationPage);
            if (!pageFormatStatus.isOK()) {
                return pageFormatStatus;
            }
            final List<CustomPageDescriptor> applicationPages = provider.getApplicationPages();
            final CustomPageDescriptor selectedDescriptor = CustomPageProvider
                    .getCustomPageDescriptorConverter(applicationPages)
                    .convert(applicationPage);

            return applicationPages.stream()
                    .noneMatch(
                            customPage -> Objects.equals(customPage.getDisplayName(), selectedDescriptor.getDisplayName()))
                                    ? ValidationStatus.warning(Messages.unknownApplicationPage)
                                    : ValidationStatus.ok();
        };
    }

    public static IValidator<String> customPageNameValidator() {
        return pageName -> Strings.isNullOrEmpty(pageName) || pageName.startsWith("custompage_")
                ? ValidationStatus.ok()
                : ValidationStatus.error(Messages.invalidPageNameFormat);
    }

    public static Function<NavigationPageNode, IStatus> tokenPageColumnValidator(
            List<NavigationPageNode> navigationPageNodes) {
        return node -> {
            if (node.isTopMenu()) {
                return ValidationStatus.ok();
            }
            final String applicationToken = node.getApplicationToken();
            if (Strings.isNullOrEmpty(applicationToken)) {
                return ValidationStatus.error(org.bonitasoft.studio.ui.i18n.Messages.required);
            }
            final IStatus tokenLengthStatus = pageTokenLengthValidator.validate(applicationToken);
            if (!tokenLengthStatus.isOK()) {
                return tokenLengthStatus;
            }
            final IStatus tokenFormatStatus = alphaNumericValidator().validate(applicationToken);
            if (!tokenFormatStatus.isOK()) {
                return tokenFormatStatus;
            }
            final IStatus tokenStatus = reservedTokenValidator().validate(applicationToken);
            if (!tokenStatus.isOK()) {
                return tokenStatus;
            }
            return countToken(navigationPageNodes, applicationToken) > 1
                    ? ValidationStatus.error(Messages.applicationPageTokenMustBeUnique)
                    : ValidationStatus.ok();
        };
    }

    private static IValidator alphaNumericValidator() {
        return new RegExpValidator.Builder().matches("^[a-zA-Z0-9-_]+$")
                .withMessage(org.bonitasoft.studio.la.i18n.Messages.alphaNumericOnly)
                .create();
    }

    private static long countToken(List<NavigationPageNode> navigationPageNodes, String applicationToken) {
        return navigationPageNodes.stream()
                .flatMap(NavigationPageNode::flattened)
                .map(NavigationPageNode::getApplicationToken)
                .filter(Objects::nonNull)
                .filter(applicationToken::equals)
                .count();
    }

    public static IValidator appTokenValidator(final ApplicationTokenUnicityValidator applicationTokenUnicityValidator) {
        return new MultiValidator.Builder().havingValidators(
                new EmptyInputValidator.Builder()
                        .withMessage(org.bonitasoft.studio.ui.i18n.Messages.required).create(),
                new InputLengthValidator(Messages.applicationToken, TOKEN_MAX_LENGTH),
                new RegExpValidator.Builder().matches("^[a-zA-Z0-9-_]+$")
                        .withMessage(org.bonitasoft.studio.la.i18n.Messages.alphaNumericOnly).create(),
                reservedTokenValidator(),
                applicationTokenUnicityValidator).create();
    }

    public static IValidator<String> appDisplayNameValidator() {
        return new MultiValidator.Builder().havingValidators(
                new EmptyInputValidator.Builder()
                        .withMessage(org.bonitasoft.studio.ui.i18n.Messages.required).create(),
                displayNameValidator).create();
    }

    public static IValidator<String> addApplicationDisplayNameValidator(AddApplicationMode mode,
            SelectObservableValue<AddApplicationMode> currentMode) {
        return new IValidator<>() {

            IValidator<String> validator = appDisplayNameValidator();

            @Override
            public IStatus validate(String value) {
                if (Objects.equals(currentMode.getValue(), mode)) {
                    return validator.validate(value);
                }
                return ValidationStatus.ok();
            }
        };
    }

    public static IValidator addApplicationTokenValidator(
            final ApplicationTokenUnicityValidator applicationTokenUnicityValidator,
            AddApplicationMode mode, SelectObservableValue<AddApplicationMode> currentMode) {
        return new IValidator() {

            IValidator validator = appTokenValidator(applicationTokenUnicityValidator);

            @Override
            public IStatus validate(Object value) {
                if (currentMode.getValue().equals(mode)) {
                    return validator.validate(value);
                }
                return ValidationStatus.ok();
            }
        };
    }

    private static IValidator reservedTokenValidator() {
        return value -> {
            final String applicationToken = (String) value;
            if (RESERVED_TOKENS.stream().anyMatch(applicationToken::equalsIgnoreCase)) {
                return ValidationStatus.error(String.format(Messages.reservedToken, applicationToken));
            }
            return ValidationStatus.ok();
        };
    }

}
