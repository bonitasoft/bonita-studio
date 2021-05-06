package org.bonitasoft.studio.identity.actors.ui.wizard.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.identity.actors.ui.wizard.connector.CustomUserInfoNameExpressionProvider;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.common.util.BasicEList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CustomUserInfoNameExpressionProviderTest {

    private static final String ACME = "ACME.organization";

    @Mock
    private OrganizationRepositoryStore repoStore;

    @Mock
    private OrganizationFileStore fileStore;

    private CustomUserInfoNameExpressionProvider provider;

    @Mock
    private Organization organization;

    @Mock
    private CustomUserInfoDefinitions definitionsContainer;

    @Before
    public void setUp() throws Exception {
        provider = new CustomUserInfoNameExpressionProvider(repoStore, ACME);
        when(repoStore.getChild(ACME, true)).thenReturn(fileStore);
        when(fileStore.getContent()).thenReturn(organization);
        given(organization.getCustomUserInfoDefinitions()).willReturn(definitionsContainer);
    }

    @Test
    public void getExpressions_return_empty_array_if_customUserDefition_container_is_null() throws Exception {
        given(organization.getCustomUserInfoDefinitions()).willReturn(null);

        //when
        final Expression[] expressions = provider.getExpressions(null);

        //then
        assertThat(expressions).isEmpty();
    }

    @Test
    public void getExpressions_return_empty_array_if_customUserDefition_container_is_empty() throws Exception {
        final BasicEList<CustomUserInfoDefinition> elements = new BasicEList<CustomUserInfoDefinition>();
        given(definitionsContainer.getCustomUserInfoDefinition()).willReturn(elements);

        //when
        final Expression[] expressions = provider.getExpressions(null);

        //then
        assertThat(expressions).isEmpty();
    }

    @Test
    public void getExpressions_return_one_expression_for_each_customUserDefition() throws Exception {
        final BasicEList<CustomUserInfoDefinition> elements = new BasicEList<CustomUserInfoDefinition>();
        elements.add(getUserDefinition("Skills"));
        elements.add(getUserDefinition("Office location"));
        given(definitionsContainer.getCustomUserInfoDefinition()).willReturn(elements);

        //when
        final Expression[] expressions = provider.getExpressions(null);

        //then
        assertThat(expressions).hasSize(2);
        assertThat(expressions[0].getContent()).isEqualTo("Skills");
        assertThat(expressions[1].getContent()).isEqualTo("Office location");
    }

    private CustomUserInfoDefinition getUserDefinition(final String name) {
        final CustomUserInfoDefinition userInfoDefinition = mock(CustomUserInfoDefinition.class);
        when(userInfoDefinition.getName()).thenReturn(name);
        return userInfoDefinition;
    }

}
