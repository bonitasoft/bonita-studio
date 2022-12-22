package org.bonitasoft.studio.identity.organization.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Objects;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.identity.actors.validator.ValidatorConstants;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.model.organization.Roles;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RoleNameValidatorTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    private OrganizationFactory factory = OrganizationFactory.eINSTANCE;
    private IObservableValue<Role> selectedRoleObservable;
    private IObservableValue<Organization> organizationObservable;
    private RoleNameValidator validator;

    @Before
    public void init() {
        Role role = factory.createRole();
        role.setName("roleName");
        selectedRoleObservable = new WritableValue<>(role, Role.class);

        Organization organization = factory.createOrganization();
        Roles roles = factory.createRoles();
        roles.getRole().add(role);
        organization.setRoles(roles);
        organizationObservable = new WritableValue<>(organization, Organization.class);

        validator = new RoleNameValidator(organizationObservable, selectedRoleObservable);
    }

    @Test
    public void should_validate_valid_name() {
        StatusAssert.assertThat(validator.validate(selectedRoleObservable.getValue().getName())).isOK();
    }

    @Test
    public void should_throw_error_when_role_name_is_empty() {
        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status)).isEqualTo(Messages.bind(Messages.emptyField, Messages.name));

        status = validator.validate("");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status)).isEqualTo(Messages.bind(Messages.emptyField, Messages.name));
    }

    @Test
    public void should_throw_error_when_role_name_is_too_long() {
        IStatus status = validator.validate("................................................................."
                + "......................................................................"
                + "......................................................................."
                + "........................................................................"
                + ".........................................................................");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(Messages.bind(Messages.fieldIsTooLong, Messages.name, ValidatorConstants.LONG_FIELD_MAX_LENGTH));
    }

    @Test
    public void should_throw_error_when_role_name_is_duplicated() {
        Role role = selectedRoleObservable.getValue();

        Role otherRole = factory.createRole();
        otherRole.setName(role.getName());
        organizationObservable.getValue().getRoles().getRole().add(otherRole);

        IStatus status = validator.validate(role.getName());
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(org.bonitasoft.studio.identity.i18n.Messages.roleNameAlreadyExists);
    }

    private String getErrorMessage(IStatus status) {
        if (!(status instanceof MultiStatus)) {
            return status.getMessage();
        }
        return Arrays.asList(((MultiStatus) status).getChildren()).stream()
                .filter(s -> Objects.equals(s.getSeverity(), IStatus.ERROR))
                .findFirst()
                .map(IStatus::getMessage).orElse("No error");
    }

}
