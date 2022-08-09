/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.organization;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.organization.BotOrganizationEditor;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class OrganizationIT {

    private static final String DISPLAY_NAME_PREFIX = "displayName";
    private static final String FIRST_NAME_PREFIX = "firstName";
    private static final String LAST_NAME_PREFIX = "lastName";
    private static final String DESCRIPTION_SUFFIX = "description";
    private static final String PROFESSIONAL_SUFFIX = "professional";
    private static final String PERSONNAL_SUFFIX = "personnal";

    private static final String ORGA_NAME = "OrgaIT";
    private static final String ORGA_DESCRIPTION = "Organization built by the organization intergation test";

    private static final String GROUP1 = "OrgaITGroup1";
    private static final String GROUP2 = "OrgaITGroup2";
    private static final String SUBGROUP = "OrgaITSubgroup";

    private static final String ROLE1 = "OrgaITRole1";
    private static final String ROLE2 = "OrgaITRole2";

    private static final String USER = "OrgaITUser";
    private static final String PASSWORD = "OrgaITPassword";
    private static final String TITLE = "OrgaITTitle";
    private static final String JOB_TITLE = "OrgaITJobTitle";

    private static final String EMAIL = "OrgaITEmail";
    private static final String PHONE = "OrgaITPhone";
    private static final String MOBILE = "OrgaITMobile";
    private static final String FAX = "OrgaITFax";
    private static final String WEBSITE = "OrgaITWebSite";

    private static final String BUILDING = "OrgaITBuilding";
    private static final String ROOM = "OrgaITRoom";
    private static final String ADDRESS = "OrgaITAddress";
    private static final String CITY = "OrgaITCity";
    private static final String STATE = "OrgaITState";
    private static final String ZIPCODE = "OrgaITZipcode";
    private static final String COUNTRY = "OrgaITCountry";

    private static final String CUSTOM_INFORMATION_NAME = "OrgaITCustomInfoName";
    private static final String CUSTOM_INFORMATION_VALUE = "OrgaITCustomInfoValue";

    private SWTGefBot bot = new SWTGefBot();
    private RepositoryAccessor repositoryAccessor;

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Before
    public void init() throws Exception {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        BOSEngineManager.getInstance().start();
    }

    @Test
    public void should_create_fill_and_deploy_a_new_organization() throws Exception {
        String orgaName = findNewOrgaName();
        ProjectExplorerBot projectExplorerBot = new ProjectExplorerBot(bot);

        // Create new Organization
        projectExplorerBot.newOrganization();
        projectExplorerBot.waitUntilActiveEditorTitleIs(toOrgaFullName(orgaName), Optional.empty());
        BotOrganizationEditor botOrganizationEditor = new BotOrganizationEditor(bot, toOrgaFullName(orgaName));

        // Configure general information (Overview)
        botOrganizationEditor.overviewPage()
                .setName(ORGA_NAME)
                .setDescription(ORGA_DESCRIPTION)
                .save();

        // Configure groups
        botOrganizationEditor.groupPage()
                .addGroup(GROUP1, toDisplayName(GROUP1))
                .removeGroup(toDisplayName(GROUP1))
                .addGroup(GROUP1, toDisplayName(GROUP1))
                .selectGroup(toDisplayName(GROUP1))
                .setDescription(toDescription(GROUP1))
                .addGroup(GROUP2, toDisplayName(GROUP2))
                .selectGroup(toDisplayName(GROUP2))
                .setDescription(toDescription(GROUP2))
                .addSubGroup(SUBGROUP, toDisplayName(SUBGROUP), toDisplayName(GROUP2))
                .moveSubGroup(toDisplayName(GROUP1), toDisplayName(SUBGROUP), toDisplayName(GROUP2))
                .selectSubGroup(toDisplayName(SUBGROUP), toDisplayName(GROUP1))
                .setDescription(toDescription(SUBGROUP))
                .save();

        // Configure roles
        botOrganizationEditor.rolePage()
                .addRole(ROLE1, toDisplayName(ROLE1))
                .removeRole(toDisplayName(ROLE1))
                .addRole(ROLE1, toDisplayName(ROLE1))
                .selectRole(toDisplayName(ROLE1))
                .setDescription(toDescription(ROLE1))
                .addRole(ROLE2, toDisplayName(ROLE2))
                .selectRole(toDisplayName(ROLE2))
                .setDescription(toDescription(ROLE2))
                .save();

        // Configure custom informations
        botOrganizationEditor.userPage()
                .expandCustomInfoSection()
                .addCustomInformationDefinition(CUSTOM_INFORMATION_NAME, toDescription(CUSTOM_INFORMATION_NAME))
                .removeCustomInformationDefinition(CUSTOM_INFORMATION_NAME)
                .addCustomInformationDefinition(CUSTOM_INFORMATION_NAME, toDescription(CUSTOM_INFORMATION_NAME))
                .save();

        // Configure users
        String userDisplayName = botOrganizationEditor.userPage().toUserDisplayName(toFirstName(USER), toLastName(USER));

        botOrganizationEditor.userPage()
                .addUser(USER, toFirstName(USER), toLastName(USER))
                .setPassword(userDisplayName, PASSWORD)
                .setTitle(userDisplayName, TITLE)
                .setJobTitle(userDisplayName, JOB_TITLE)
                .removeMembership(userDisplayName, 0)
                .addMembership(userDisplayName, "/" + GROUP1 + "/" + SUBGROUP, ROLE2)
                .selectProfessionalDataTab(userDisplayName)
                .setContactInformations(toProfessional(EMAIL),
                        toProfessional(PHONE),
                        toProfessional(MOBILE),
                        toProfessional(FAX),
                        toProfessional(WEBSITE))
                .setLocationInformations(toProfessional(BUILDING),
                        toProfessional(ROOM),
                        toProfessional(ADDRESS),
                        toProfessional(CITY),
                        toProfessional(STATE),
                        toProfessional(ZIPCODE),
                        toProfessional(COUNTRY))
                .selectPersonnalDataTab(userDisplayName)
                .setContactInformations(toPersonnal(EMAIL),
                        toPersonnal(PHONE),
                        toPersonnal(MOBILE),
                        toPersonnal(FAX),
                        toPersonnal(WEBSITE))
                .setLocationInformations(toPersonnal(BUILDING),
                        toPersonnal(ROOM),
                        toPersonnal(ADDRESS),
                        toPersonnal(CITY),
                        toPersonnal(STATE),
                        toPersonnal(ZIPCODE),
                        toPersonnal(COUNTRY))
                .selectCustomDataTab(userDisplayName)
                .setCustomInformation(CUSTOM_INFORMATION_NAME, CUSTOM_INFORMATION_VALUE)
                .save();

        validateOrganizationContent();
    }

    private void validateOrganizationContent() throws Exception {
        OrganizationFileStore fileStore = repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class)
                .getChild(toOrgaFullName(ORGA_NAME), false);
        assertThat(fileStore).isNotNull();

        Organization organization = fileStore.getContent();

        assertThat(organization.getName()).isEqualTo(ORGA_NAME);
        assertThat(organization.getDescription()).isEqualTo(ORGA_DESCRIPTION);

        validateGroups(organization);
        validateRoles(organization);
        validateCustomInformationDefinitions(organization);
        validateUsers(organization);
    }

    private void validateUsers(Organization organization) {
        EList<User> users = organization.getUsers().getUser();
        assertThat(users).hasSize(1);
        User user = users.get(0);

        // General infos
        assertThat(user.getUserName()).isEqualTo(USER);
        assertThat(user.getFirstName()).isEqualTo(toFirstName(USER));
        assertThat(user.getLastName()).isEqualTo(toLastName(USER));
        assertThat(user.getPassword().getValue()).isEqualTo(PASSWORD);
        assertThat(user.getTitle()).isEqualTo(TITLE);
        assertThat(user.getJobTitle()).isEqualTo(JOB_TITLE);

        // Memberships
        assertThat(organization.getMemberships().getMembership()).hasSize(1);
        Membership membership = organization.getMemberships().getMembership().get(0);
        assertThat(membership.getUserName()).isEqualTo(user.getUserName());
        assertThat(membership.getGroupName()).isEqualTo(SUBGROUP);
        assertThat(membership.getGroupParentPath()).isEqualTo("/" + GROUP1);
        assertThat(membership.getRoleName()).isEqualTo(ROLE2);

        // Professional informations
        assertThat(user.getProfessionalData().getEmail()).isEqualTo(toProfessional(EMAIL));
        assertThat(user.getProfessionalData().getPhoneNumber()).isEqualTo(toProfessional(PHONE));
        assertThat(user.getProfessionalData().getMobileNumber()).isEqualTo(toProfessional(MOBILE));
        assertThat(user.getProfessionalData().getFaxNumber()).isEqualTo(toProfessional(FAX));
        assertThat(user.getProfessionalData().getWebsite()).isEqualTo(toProfessional(WEBSITE));
        assertThat(user.getProfessionalData().getEmail()).isEqualTo(toProfessional(EMAIL));

        assertThat(user.getProfessionalData().getBuilding()).isEqualTo(toProfessional(BUILDING));
        assertThat(user.getProfessionalData().getRoom()).isEqualTo(toProfessional(ROOM));
        assertThat(user.getProfessionalData().getAddress()).isEqualTo(toProfessional(ADDRESS));
        assertThat(user.getProfessionalData().getCity()).isEqualTo(toProfessional(CITY));
        assertThat(user.getProfessionalData().getState()).isEqualTo(toProfessional(STATE));
        assertThat(user.getProfessionalData().getZipCode()).isEqualTo(toProfessional(ZIPCODE));
        assertThat(user.getProfessionalData().getCountry()).isEqualTo(toProfessional(COUNTRY));

        // Personnal informations
        assertThat(user.getPersonalData().getEmail()).isEqualTo(toPersonnal(EMAIL));
        assertThat(user.getPersonalData().getPhoneNumber()).isEqualTo(toPersonnal(PHONE));
        assertThat(user.getPersonalData().getMobileNumber()).isEqualTo(toPersonnal(MOBILE));
        assertThat(user.getPersonalData().getFaxNumber()).isEqualTo(toPersonnal(FAX));
        assertThat(user.getPersonalData().getWebsite()).isEqualTo(toPersonnal(WEBSITE));
        assertThat(user.getPersonalData().getEmail()).isEqualTo(toPersonnal(EMAIL));

        assertThat(user.getPersonalData().getBuilding()).isEqualTo(toPersonnal(BUILDING));
        assertThat(user.getPersonalData().getRoom()).isEqualTo(toPersonnal(ROOM));
        assertThat(user.getPersonalData().getAddress()).isEqualTo(toPersonnal(ADDRESS));
        assertThat(user.getPersonalData().getCity()).isEqualTo(toPersonnal(CITY));
        assertThat(user.getPersonalData().getState()).isEqualTo(toPersonnal(STATE));
        assertThat(user.getPersonalData().getZipCode()).isEqualTo(toPersonnal(ZIPCODE));
        assertThat(user.getPersonalData().getCountry()).isEqualTo(toPersonnal(COUNTRY));

        // Custom informations
        EList<CustomUserInfoValue> customUserInfoValues = user.getCustomUserInfoValues().getCustomUserInfoValue();
        assertThat(customUserInfoValues.stream()).extracting(CustomUserInfoValue::getName)
                .containsExactlyInAnyOrder(CUSTOM_INFORMATION_NAME);
        assertThat(customUserInfoValues.stream()).extracting(CustomUserInfoValue::getValue)
                .containsExactlyInAnyOrder(CUSTOM_INFORMATION_VALUE);
    }

    private void validateCustomInformationDefinitions(Organization organization) {
        EList<CustomUserInfoDefinition> customUserInfoDefinition = organization.getCustomUserInfoDefinitions()
                .getCustomUserInfoDefinition();
        assertThat(customUserInfoDefinition.stream()).extracting(CustomUserInfoDefinition::getName)
                .containsExactlyInAnyOrder(CUSTOM_INFORMATION_NAME);
        assertThat(customUserInfoDefinition.stream()).extracting(CustomUserInfoDefinition::getDescription)
                .containsExactlyInAnyOrder(toDescription(CUSTOM_INFORMATION_NAME));
    }

    private void validateRoles(Organization organization) {
        EList<Role> roles = organization.getRoles().getRole();
        assertThat(roles.stream()).extracting(Role::getName).containsExactlyInAnyOrder(ROLE1, ROLE2);
        assertThat(roles.stream()).extracting(Role::getDisplayName)
                .containsExactlyInAnyOrder(toDisplayName(ROLE1), toDisplayName(ROLE2));
        assertThat(roles.stream()).extracting(Role::getDescription)
                .containsExactlyInAnyOrder(toDescription(ROLE1), toDescription(ROLE2));
    }

    private void validateGroups(Organization organization) {
        EList<Group> groups = organization.getGroups().getGroup();
        assertThat(groups.stream()).extracting(Group::getName).containsExactlyInAnyOrder(GROUP1, GROUP2, SUBGROUP);
        assertThat(groups.stream()).extracting(Group::getDisplayName)
                .containsExactlyInAnyOrder(toDisplayName(GROUP1), toDisplayName(GROUP2), toDisplayName(SUBGROUP));
        assertThat(groups.stream()).extracting(Group::getDescription)
                .containsExactlyInAnyOrder(toDescription(GROUP1), toDescription(GROUP2), toDescription(SUBGROUP));
        assertThat(groups.stream()).extracting(Group::getParentPath).containsExactlyInAnyOrder(null, null, "/" + GROUP1);
    }

    private String findNewOrgaName() {
        List<String> existingOrgaNameList = repositoryAccessor.getRepositoryStore(OrganizationRepositoryStore.class)
                .getChildren().stream().map(OrganizationFileStore::getDisplayName).collect(Collectors.toList());
        String newName = StringIncrementer.getNextIncrement(Messages.defaultOrganizationName,
                existingOrgaNameList);
        return newName;
    }

    private String toOrgaFullName(String orgaName) {
        return String.format("%s.%s", orgaName, OrganizationRepositoryStore.ORGANIZATION_EXT);
    }

    private String toDisplayName(String name) {
        return String.format("%s_%s", DISPLAY_NAME_PREFIX, name);
    }

    private String toDescription(String name) {
        return String.format("%s %s", name, DESCRIPTION_SUFFIX);
    }

    private String toFirstName(String name) {
        return String.format("%s_%s", FIRST_NAME_PREFIX, name);
    }

    private String toLastName(String name) {
        return String.format("%s_%s", LAST_NAME_PREFIX, name);
    }

    private String toProfessional(String name) {
        return String.format("%s_%s", name, PROFESSIONAL_SUFFIX);
    }

    private String toPersonnal(String name) {
        return String.format("%s_%s", name, PERSONNAL_SUFFIX);
    }

}
