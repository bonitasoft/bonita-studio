package org.bonitasoft.studio.identity.organization.validator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Groups;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MembershipGroupValidatorTest {

    private static final String GROUP_NAME = "myGroup";
    private static final String GROUP_PARENT_PATH = "/acme";

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    MembershipGroupValidator validator;

    @Before
    public void init() {
        Group group1 = mock(Group.class);
        when(group1.getName()).thenReturn(GROUP_NAME);
        when(group1.getParentPath()).thenReturn(GROUP_PARENT_PATH);

        EList<Group> groupList = new BasicEList();
        groupList.add(group1);

        Groups groups = mock(Groups.class);
        when(groups.getGroup()).thenReturn(groupList);

        Organization orga = mock(Organization.class);
        when(orga.getGroups()).thenReturn(groups);

        IObservableValue<Organization> orgaObservable = new WritableValue();
        orgaObservable.setValue(orga);

        validator = new MembershipGroupValidator(orgaObservable);
    }

    @Test
    public void should_accept_valid_group() {
        Membership membership = mock(Membership.class);
        when(membership.getGroupName()).thenReturn(GROUP_NAME);
        when(membership.getGroupParentPath()).thenReturn(GROUP_PARENT_PATH);

        IStatus status = validator.validate(membership);
        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_not_accept_null_or_empty_group() {
        Membership membership = mock(Membership.class);

        when(membership.getGroupName()).thenReturn(null);
        IStatus status = validator.validate(membership);
        StatusAssert.assertThat(status).isError();

        when(membership.getGroupName()).thenReturn("");
        status = validator.validate(membership);
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_not_accept_unknown_group() {
        Membership membership = mock(Membership.class);
        when(membership.getGroupName()).thenReturn("Yolo");
        when(membership.getGroupParentPath()).thenReturn(GROUP_PARENT_PATH);

        IStatus status = validator.validate(membership);
        StatusAssert.assertThat(status).isError();
    }

}
