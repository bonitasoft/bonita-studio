package org.bonitasoft.studio.identity.organization.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.model.organization.Roles;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MembershipRoleValidatorTest {

    private static final String ROLE_NAME = "myRole";

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    MembershipRoleValidator validator;

    @Before
    public void init() {
        Role role1 = mock(Role.class);
        when(role1.getName()).thenReturn(ROLE_NAME);

        EList<Role> roleList = new BasicEList();
        roleList.add(role1);

        Roles roles = mock(Roles.class);
        when(roles.getRole()).thenReturn(roleList);

        Organization orga = mock(Organization.class);
        when(orga.getRoles()).thenReturn(roles);

        IObservableValue<Organization> orgaObservable = new WritableValue();
        orgaObservable.setValue(orga);

        validator = new MembershipRoleValidator(orgaObservable);
    }

    @Test
    public void should_accept_valid_role() {
        Membership membership = mock(Membership.class);
        when(membership.getRoleName()).thenReturn(ROLE_NAME);

        IStatus status = validator.validate(membership);
        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_not_accept_null_or_empty_role() {
        Membership membership = mock(Membership.class);

        when(membership.getRoleName()).thenReturn(null);
        IStatus status = validator.validate(membership);
        StatusAssert.assertThat(status).isError();

        when(membership.getRoleName()).thenReturn("");
        status = validator.validate(membership);
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_not_accept_unknown_role() {
        Membership membership = mock(Membership.class);
        when(membership.getRoleName()).thenReturn("Yolo");

        IStatus status = validator.validate(membership);
        StatusAssert.assertThat(status).isError();
    }

}
