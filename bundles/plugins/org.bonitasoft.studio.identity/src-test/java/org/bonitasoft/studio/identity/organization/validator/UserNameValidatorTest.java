package org.bonitasoft.studio.identity.organization.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Objects;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.actors.validator.ValidatorConstants;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.model.organization.Users;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UserNameValidatorTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    private OrganizationFactory factory = OrganizationFactory.eINSTANCE;
    private IObservableValue<User> selectedUserObservable;
    private IObservableValue<Organization> organizationObservable;
    private UserNameValidator validator;

    @Before
    public void init() {
        User user = factory.createUser();
        user.setUserName("userName");
        selectedUserObservable = new WritableValue<>(user, User.class);

        Organization organization = factory.createOrganization();
        Users users = factory.createUsers();
        users.getUser().add(user);
        organization.setUsers(users);
        organizationObservable = new WritableValue<>(organization, Organization.class);

        validator = new UserNameValidator(organizationObservable, selectedUserObservable);
    }

    @Test
    public void should_validate_valid_name() {
        StatusAssert.assertThat(validator.validate(selectedUserObservable.getValue().getUserName())).isOK();
    }

    @Test
    public void should_throw_error_when_user_name_is_empty() {
        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status)).isEqualTo(Messages.bind(org.bonitasoft.studio.common.Messages.emptyField, Messages.userName));

        status = validator.validate("");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status)).isEqualTo(Messages.bind(org.bonitasoft.studio.common.Messages.emptyField, Messages.userName));
    }

    @Test
    public void should_throw_error_when_user_name_is_too_long() {
        IStatus status = validator.validate("................................................................."
                + "......................................................................"
                + "......................................................................."
                + "........................................................................"
                + ".........................................................................");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(Messages.bind(org.bonitasoft.studio.common.Messages.fieldIsTooLong, Messages.userName, UserNameValidator.USERNAME_MAX_LENGTH));
    }

    @Test
    public void should_throw_error_when_user_name_is_duplicated() {
        User user = selectedUserObservable.getValue();

        User otherUser = factory.createUser();
        otherUser.setUserName(user.getUserName());
        organizationObservable.getValue().getUsers().getUser().add(otherUser);

        IStatus status = validator.validate(user.getUserName());
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(org.bonitasoft.studio.identity.i18n.Messages.userNameAlreadyExists);
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
