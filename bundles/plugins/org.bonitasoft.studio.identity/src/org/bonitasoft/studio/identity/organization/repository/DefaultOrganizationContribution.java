/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terMrs of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.identity.organization.repository;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.preferences.OrganizationPreferenceConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.ContactData;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Groups;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Memberships;
import org.bonitasoft.studio.identity.organization.model.organization.MetaDatasType;
import org.bonitasoft.studio.identity.organization.model.organization.Metadata;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.PasswordType;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.model.organization.Roles;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.model.organization.Users;
import org.eclipse.emf.common.util.EList;

/**
 * @author Romain Bioteau
 */
public class DefaultOrganizationContribution implements IFileStoreContribution {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IFileStoreContribution#appliesTo(org.bonitasoft.studio.common.repository.model.IRepositoryStore)
     */
    @Override
    public boolean appliesTo(final IRepositoryStore<? extends IRepositoryFileStore> repository) {
        return repository instanceof OrganizationRepositoryStore;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IFileStoreContribution#execute(org.bonitasoft.studio.common.repository.model.IRepositoryStore)
     */
    @Override
    public void execute(final IRepositoryStore<? extends IRepositoryFileStore> repository) {
        final Organization organization = OrganizationFactory.eINSTANCE.createOrganization();
        organization.setName(OrganizationPreferenceConstants.DEFAULT_ORGANIZATION_NAME);
        organization.setDescription(Messages.defaultOrganizationDescription);

        final Groups groups = createGroups();
        final Users users = createUsers();
        final Roles roles = createRoles();
        final Memberships memberships = createMemberships();

        organization.setGroups(groups);
        organization.setUsers(users);
        organization.setRoles(roles);
        organization.setMemberships(memberships);

        final IRepositoryFileStore file = repository
                .createRepositoryFileStore(OrganizationPreferenceConstants.DEFAULT_ORGANIZATION_NAME + "."
                        + OrganizationRepositoryStore.ORGANIZATION_EXT);
        file.save(organization);
    }

    protected Memberships createMemberships() {
        final Memberships memberships = OrganizationFactory.eINSTANCE.createMemberships();
        final EList<Membership> membershipList = memberships.getMembership();
        membershipList.add(createMembership("william.jobs", null, "acme", "member"));
        membershipList.add(createMembership("april.sanchez", "/acme", "hr", "member"));
        membershipList.add(createMembership("helen.kelly", "/acme", "hr", "member"));
        membershipList.add(createMembership("walter.bates", "/acme", "hr", "member"));

        membershipList.add(createMembership("zachary.williamson", "/acme", "finance", "member"));
        membershipList.add(createMembership("patrick.gardenier", "/acme", "finance", "member"));
        membershipList.add(createMembership("virginie.jomphe", "/acme", "finance", "member"));
        membershipList.add(createMembership("thorsten.hartmann", "/acme", "finance", "member"));

        membershipList.add(createMembership("jan.fisher", "/acme", "it", "member"));

        membershipList.add(createMembership("isabel.bleasdale", "/acme", "marketing", "member"));
        membershipList.add(createMembership("favio.riviera", "/acme", "marketing", "member"));

        membershipList.add(createMembership("michael.morrison", "/acme", "production", "member"));
        membershipList.add(createMembership("marc.marseau", "/acme/production", "rd", "member"));
        membershipList.add(createMembership("joseph.hovell", "/acme/production", "rd", "member"));

        membershipList.add(createMembership("mauro.zetticci", "/acme/production", "services", "member"));
        membershipList.add(createMembership("thomas.wallis", "/acme/production", "services", "member"));

        membershipList.add(createMembership("daniela.angelo", "/acme/sales", "europe", "member"));
        membershipList.add(createMembership("misa.kumagai", "/acme/sales", "asia", "member"));
        membershipList.add(createMembership("norio.yamazaki", "/acme/sales", "asia", "member"));
        membershipList.add(createMembership("giovanna.almeida", "/acme/sales", "latin_america", "member"));
        membershipList.add(createMembership("anthony.nichols", "/acme/sales", "north_america", "member"));
        return memberships;
    }

    protected Roles createRoles() {
        final Roles roles = OrganizationFactory.eINSTANCE.createRoles();
        roles.getRole().add(createRole("member", "Member", ""));
        return roles;
    }

    protected Users createUsers() {
        final Users users = OrganizationFactory.eINSTANCE.createUsers();
        final EList<User> userList = users.getUser();
        userList.addAll(getDefaultUsers());
        return users;
    }

    public List<User> getDefaultUsers() {
        final List<User> defaultUsers = new ArrayList<>();
        final String mr = "Mr";
        final String mrs = "Mrs";

        defaultUsers.add(createUser(mr, "william.jobs", "William", "Jobs", "bpm", "WilliamJobs@acme.com", null,
                "Chief Executive Officer"));
        defaultUsers.add(createUser(mrs, "april.sanchez", "April", "Sanchez", "bpm", "AprilGSanchez@acme.com", "helen.kelly",
                "Compensation specialist"));
        defaultUsers.add(createUser(mrs, "helen.kelly", "Helen", "Kelly", "bpm", "HelenKelly@acme.com", "william.jobs",
                "Human resource manager"));
        defaultUsers.add(createUser(mr, "walter.bates", "Walter", "Bates", "bpm", "WalterRBates@acme.com", "helen.kelly",
                "Human resources benefits"));

        defaultUsers.add(
                createUser(mr, "zachary.williamson", "Zachary", "Williamson", "bpm", "ZacharyWilliamson@acme.com",
                        "william.jobs", "Chief Financial Officer"));
        defaultUsers.add(
                createUser(mr, "patrick.gardenier", "Patrick", "Gardenier", "bpm", "PatrickGardenier@acme.com",
                        "zachary.williamson", "Financial controller"));
        defaultUsers.add(createUser(mrs, "virginie.jomphe", "Virginie", "Jomphe", "bpm", "VirginieJomphe@acme.com",
                "zachary.williamson", "Accountant"));
        defaultUsers.add(createUser(mr, "thorsten.hartmann", "Thorsten", "Hartmann", "bpm", "ThorstenHartmann@acme.com",
                "zachary.williamson",
                "Financial planning manager"));

        defaultUsers.add(createUser(mr, "jan.fisher", "Jan", "Fisher", "bpm", "JanFisher@acme.com", "favio.riviera",
                "Infrastucture specialist"));

        defaultUsers.add(
                createUser(mrs, "isabel.bleasdale", "Isabel", "Bleasdale", "bpm", "IsabelBleasdale@acme.com",
                        "favio.riviera", "Product marketing manager"));
        defaultUsers.add(createUser(mr, "favio.riviera", "Favio", "Riviera", "bpm", "FavioRiviera@acme.com", "william.jobs",
                "Vice President of Marketing"));

        defaultUsers.add(createUser(mr, "michael.morrison", "Michael", "Morrison", "bpm", "MichaelMorrison@acme.com",
                "william.jobs", "Chief Technical Officer"));
        defaultUsers.add(createUser(mr, "marc.marseau", "Marc", "Marseau", "bpm", "MarcMarseau@acme.com", "michael.morrison",
                "Engineer"));
        defaultUsers
                .add(createUser(mr, "joseph.hovell", "Joseph", "Hovell", "bpm", "JosephHovell@acme.com", "michael.morrison",
                        "Engineer"));

        defaultUsers.add(createUser(mr, "mauro.zetticci", "Mauro", "Zetticci", "bpm", "MauroZetticci@acme.com",
                "michael.morrison", "Consultant"));
        defaultUsers
                .add(createUser(mr, "thomas.wallis", "Thomas", "Wallis", "bpm", "ThomasWallis@acme.com", "michael.morrison",
                        "Consultant"));

        defaultUsers
                .add(createUser(mrs, "daniela.angelo", "Daniela", "Angelo", "bpm", "DanielaAngelo@acme.com", "william.jobs",
                        "Vice President of Sales"));
        defaultUsers.add(createUser(mr, "anthony.nichols", "Anthony", "Nichols", "bpm", "AnthonyNicholls@acme.com",
                "daniela.angelo", "Account manager"));
        defaultUsers.add(createUser(mrs, "misa.kumagai", "Misa", "Kumagai", "bpm", "MisaKumagai@acme.com", "daniela.angelo",
                "Account manager"));
        defaultUsers
                .add(createUser(mr, "norio.yamazaki", "Norio", "Yamazaki", "bpm", "NorioYamazaki@acme.com", "daniela.angelo",
                        "Account manager"));
        defaultUsers.add(createUser(mrs, "giovanna.almeida", "Giovanna", "Almeida", "bpm", "GiovannaRochaAlmeida@acme.com",
                "daniela.angelo", "Account manager"));

        return defaultUsers;
    }

    protected Groups createGroups() {
        final Groups groups = OrganizationFactory.eINSTANCE.createGroups();
        final EList<Group> groupList = groups.getGroup();
        groupList.add(createGroup(null, "acme", "Acme"));
        groupList.add(createGroup("/acme", "hr", "Human Resources"));
        groupList.add(createGroup("/acme", "finance", "Finance"));
        groupList.add(createGroup("/acme", "it", "Infrastructure"));
        groupList.add(createGroup("/acme", "marketing", "Marketing"));
        groupList.add(createGroup("/acme", "production", "Production"));
        groupList.add(createGroup("/acme", "sales", "Sales"));

        groupList.add(createGroup("/acme/sales", "europe", "Europe"));
        groupList.add(createGroup("/acme/sales", "asia", "Asia"));
        groupList.add(createGroup("/acme/sales", "latin_america", "Latin America"));
        groupList.add(createGroup("/acme/sales", "north_america", "North America"));
        groupList.add(createGroup("/acme/production", "rd", "Research & Development"));
        groupList.add(createGroup("/acme/production", "services", "Services"));
        return groups;
    }

    private Role createRole(final String name, final String displayName, final String description) {
        final Role r = OrganizationFactory.eINSTANCE.createRole();
        r.setName(name);
        r.setDisplayName(displayName);
        r.setDescription(description);
        return r;
    }

    private Membership createMembership(final String username, final String parentGroupPath, final String groupName,
            final String roleName) {
        final Membership membership = OrganizationFactory.eINSTANCE.createMembership();
        membership.setGroupParentPath(parentGroupPath);
        membership.setGroupName(groupName);
        membership.setUserName(username);
        membership.setRoleName(roleName);
        return membership;
    }

    private Group createGroup(final String parentPath, final String name, final String displayName) {
        final Group group = OrganizationFactory.eINSTANCE.createGroup();
        group.setName(name);
        group.setParentPath(parentPath);
        group.setDescription(
                "This group represents the " + displayName.toLowerCase() + " department of the ACME organization");
        group.setDisplayName(displayName);
        return group;
    }

    private User createUser(final String title, final String username, final String firstname, final String lastname,
            final String password, final String email,
            final String manager, final String jobTitle) {
        final User user = OrganizationFactory.eINSTANCE.createUser();
        user.setTitle(title);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setUserName(username);
        user.setPassword(createPassword(password));
        user.setManager(manager);
        user.setJobTitle(jobTitle);
        user.setEnabled(true);
        final ContactData data = OrganizationFactory.eINSTANCE.createContactData();
        data.setEmail(username + "@acme.com");
        data.setAddress("Renwick Drive");
        data.setBuilding("70");
        data.setCity("Philadelphia");

        data.setPhoneNumber("484-302-5000");
        data.setFaxNumber("484-302-0000");
        data.setZipCode("19108");
        data.setCountry("United States");
        data.setState("PA");
        user.setProfessionalData(data);
        final MetaDatasType metadataType = OrganizationFactory.eINSTANCE.createMetaDatasType();
        Metadata metadata = OrganizationFactory.eINSTANCE.createMetadata();
        metadata.setName("Skype ID");
        metadataType.getMetaData().add(metadata);
        metadata = OrganizationFactory.eINSTANCE.createMetadata();
        metadata.setName("Twitter");
        metadataType.getMetaData().add(metadata);
        metadata = OrganizationFactory.eINSTANCE.createMetadata();
        metadata.setName("Facebook");
        metadataType.getMetaData().add(metadata);
        user.setMetaDatas(metadataType);

        return user;
    }

    private PasswordType createPassword(final String password) {
        final PasswordType passwordType = OrganizationFactory.eINSTANCE.createPasswordType();
        passwordType.setValue(password);
        passwordType.setEncrypted(false);
        return passwordType;
    }

}
