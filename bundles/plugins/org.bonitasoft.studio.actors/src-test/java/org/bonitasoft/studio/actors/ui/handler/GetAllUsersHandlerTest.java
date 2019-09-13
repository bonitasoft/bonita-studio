package org.bonitasoft.studio.actors.ui.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.model.organization.Users;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.junit.Test;

public class GetAllUsersHandlerTest {

    public static String ORGA_FILE_NAME = "ACME.organization";

    @Test
    public void should_retrieve_all_users_from_active_orga() {
        GetAllUsersHandler handler = spy(new GetAllUsersHandler());
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        OrganizationRepositoryStore repositoryStore = mock(OrganizationRepositoryStore.class);
        OrganizationFileStore fileStore = mock(OrganizationFileStore.class);
        Organization orga = mock(Organization.class);
        Users users = mock(Users.class);

        EList<User> userList = new BasicEList<>();
        userList.add(createUser("walter.bates"));
        userList.add(createUser("jean.fisher"));

        doReturn(ORGA_FILE_NAME).when(handler).getActiveOrgaFileName();
        when(repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class)).thenReturn(repositoryStore);
        when(repositoryStore.getChild(ORGA_FILE_NAME, true)).thenReturn(fileStore);
        when(fileStore.getContent()).thenReturn(orga);
        when(orga.getUsers()).thenReturn(users);
        when(users.getUser()).thenReturn(userList);

        List<String> userNames = handler.execute(repositoryAccessor);
        assertThat(userNames).containsExactly("jean.fisher", "walter.bates"); // order matters as we want the result sorted

        when(repositoryStore.getChild(ORGA_FILE_NAME, true)).thenReturn(null);
        userNames = handler.execute(repositoryAccessor);
        assertThat(userNames).isEmpty();
    }

    private User createUser(String userName) {
        User user = mock(User.class);
        when(user.getUserName()).thenReturn(userName);
        return user;
    }

}
