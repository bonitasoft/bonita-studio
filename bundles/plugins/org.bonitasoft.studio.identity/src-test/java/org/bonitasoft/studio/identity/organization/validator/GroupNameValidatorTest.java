package org.bonitasoft.studio.identity.organization.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Objects;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.identity.actors.validator.ValidatorConstants;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Groups;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class GroupNameValidatorTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    private OrganizationFactory factory = OrganizationFactory.eINSTANCE;
    private IObservableValue<Group> selectedGroupObservable;
    private IObservableValue<Organization> organizationObservable;
    private GroupNameValidator validator;

    @Before
    public void init() {
        Group group = factory.createGroup();
        group.setName("groupName");
        selectedGroupObservable = new WritableValue<>(group, Group.class);

        Organization organization = factory.createOrganization();
        Groups groups = factory.createGroups();
        groups.getGroup().add(group);
        organization.setGroups(groups);
        organizationObservable = new WritableValue<>(organization, Organization.class);

        validator = new GroupNameValidator(organizationObservable, selectedGroupObservable);
    }

    @Test
    public void should_validate_valid_name() {
        StatusAssert.assertThat(validator.validate(selectedGroupObservable.getValue().getName())).isOK();
    }

    @Test
    public void should_throw_error_when_group_name_is_empty() {
        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status)).isEqualTo(Messages.bind(Messages.emptyField, Messages.name));

        status = validator.validate("");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status)).isEqualTo(Messages.bind(Messages.emptyField, Messages.name));
    }

    @Test
    public void should_throw_error_when_group_name_is_too_long() {
        IStatus status = validator.validate("................................................................."
                + "......................................................................"
                + ".......................................................................");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(Messages.bind(Messages.fieldIsTooLong, Messages.name, ValidatorConstants.GROUP_NAME_MAX_LENGTH));
    }

    @Test
    public void should_throw_error_when_group_name_contains_illegal_char() {
        IStatus status = validator.validate("illegal/group");
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status)).isEqualTo(org.bonitasoft.studio.identity.i18n.Messages.illegalCharacter);
    }

    @Test
    public void should_throw_error_when_group_name_is_duplicated() {
        Group group = selectedGroupObservable.getValue();

        Group otherGroup = factory.createGroup();
        otherGroup.setName(group.getName());
        organizationObservable.getValue().getGroups().getGroup().add(otherGroup);

        IStatus status = validator.validate(group.getName());
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(org.bonitasoft.studio.identity.i18n.Messages.groupNameAlreadyExistsForLevel);

        otherGroup.setParentPath("parent/path");
        status = validator.validate(group.getName());
        StatusAssert.assertThat(status).isOK();

        group.setParentPath("parent/path");
        status = validator.validate(group.getName());
        StatusAssert.assertThat(status).isError();
        assertThat(getErrorMessage(status))
                .isEqualTo(org.bonitasoft.studio.identity.i18n.Messages.groupNameAlreadyExistsForLevel);
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
