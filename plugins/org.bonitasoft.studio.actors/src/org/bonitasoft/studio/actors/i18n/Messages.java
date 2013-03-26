/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 *
 */
public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages";

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String userName;
    public static String displayUsersPageTitle;
    public static String displayUsersPageDesc;
    public static String firstName;
    public static String lastName;
    public static String userInfo;
    public static String delete;
    public static String browse;
    public static String userTitle;
    public static String titleHint;
    public static String userNameHint;
    public static String firstNameHint;
    public static String lastNameHint;
    public static String personalData;
    public static String professionalData;
    public static String password;
    public static String jobLabel;
    public static String jobHint;
    public static String metadata;
    public static String general;
    public static String emailHint;
    public static String emailLabel;
    public static String phoneLabel;
    public static String phoneHint;
    public static String mobileLabel;
    public static String mobileHint;
    public static String faxLabel;
    public static String faxHint;
    public static String buildingLabel;
    public static String buildingHint;
    public static String roomLabel;
    public static String addressLabel;
    public static String addressHint;
    public static String zipCodeLabel;
    public static String zipCodeHint;
    public static String cityLabel;
    public static String cityHint;
    public static String stateLabel;
    public static String stateHint;
    public static String countryLabel;
    public static String coutryHint;
    public static String websiteLabel;
    public static String websiteHint;
    public static String roomHint;
    public static String addMetadata;
    public static String metadataName;
    public static String deleteMetadataTitle;
    public static String deleteMetadataMsg;
    public static String metadataAlreadyExists;
    public static String add;
    public static String remove;
    public static String defaultUserName;
    public static String initializingOrganizationRepository;
    public static String organizations;
    public static String saveOrganization;
    public static String membership;
    public static String groupName;
    public static String delegate;
    public static String role ;
    public static String roleName ;
    public static String description;
    public static String roleNameHint;
    public static String descriptionHint;
    public static String defaultRoleName;
    public static String displayRolesPageTitle;
    public static String displayRolesPageDesc;
    public static String roleInfo;
    public static String manager;
    public static String defaultGroupName;
    public static String deleteGroupMsg;
    public static String deleteGroupTitle;
    public static String displayName;
    public static String groupInfo;
    public static String displayGroupsPageTitle;
    public static String displayGroupsPageDesc;
    public static String groupNameAlreadyExistsForLevel;
    public static String userNameAlreadyExists;
    public static String roleNameAlreadyExists;
    public static String search;
    public static String importingOrganization;
    public static String exportingOrganization;
    public static String selectOrganizationTitle;
    public static String selectOrganizationDesc;
    public static String name;
    public static String addRemoveActors;
    public static String defaultActorName;
    public static String groupPath;
    public static String deleteMembershipTitle;
    public static String deleteMembershipMsg;
    public static String addMembership;
    public static String newOrganizationMsg;
    public static String newOrganizationTitle;
    public static String create;
    public static String selectActorTitle;
    public static String selectActorDesc;
    public static String actorDescriptionTitle;
    public static String actorDescriptionDesc;
    public static String actorMappingTitle;
    public static String actorMappingDesc;
    public static String mappingConfiguration;
    public static String defaultMappingName;
    public static String mappingNameAlreadyExists;
    public static String users;
    public static String groups;
    public static String roles;
    public static String memberships;
    public static String availableUsers;
    public static String selectedUsers;
    public static String nameAlreadyExists;
    public static String nameIsEmpty;
    public static String selectOrganization;
    public static String availableGroups;
    public static String selectedGroups;
    public static String availableRoles;
    public static String selectedRoles;
    public static String edit;
    public static String useActorsDefinedInLane;
    public static String useTaskActors;
    public static String groupMappingTitle;
    public static String groupMappingDesc;
    public static String roleMappingTitle;
    public static String roleMappingDesc;
    public static String userMappingTitle;
    public static String userMappingDesc;
    public static String membershipMappingTitle;
    public static String membershipMappingDesc;
    public static String actorMappings;
    public static String openingEditor;
    public static String newActorMappingTitle;
    public static String newActorMappingMsg;
    public static String selectActorMappingTitle;
    public static String selectActorMappingDesc;
    public static String actorSectionTitle;
    public static String actorSectionDesc;
    public static String addEtc;
    public static String selectMappingProcess;
    public static String importingActorMapping;
    public static String processActors;
    public static String actorMapping;
    public static String addGroup;
    public static String addRole;
    public static String addUser;
    public static String exportButtonLabel;
    public static String importButtonLabel;
    public static String selectUserTitle;
    public static String selectUserDescription;
    public static String selectRoleTitle;
    public static String selectRoleDescription;
    public static String selectGroupTitle;
    public static String selectGroupDescription;
    public static String selectMembershipTitle;
    public static String selectMembershipDescription;
    public static String mapping;
    public static String overwriteExistingFileTitle;
    public static String overwriteExistingFileMsg;
    public static String exportOrganizationTitle;
    public static String defaultOrganizationName;
    public static String manageOrganizationTitle;
    public static String manageOrganizationDesc;
    public static String synchronizeOrganizationTitle;
    public static String synchronizeInformationTitle;
    public static String synchronizeOrganizationSuccessMsg;
    public static String synchronizeOrganizationDesc;
    public static String synchronize;
    public static String synchronizingOrganization;
    public static String selectActor;
    public static String illegalCharacter;
    public static String filterDefRepositoryName;
    public static String filterImplRepositoryName;
    public static String filtersSourceRepositoryName;
    public static String newFilterDefinition;
    public static String editFilterDefinition;
    public static String filterImplementationTitle;
    public static String filterImplementationDesc;
    public static String newFilterImplementation;
    public static String editFilterImplementation;
    public static String selectFilterDefinitionTitle;
    public static String selectFilterDefinitionDesc;
    public static String uncategorized;
    public static String selectAFilterDefWarning;
    public static String selectFilterImplementationTitle;
    public static String selectFilterImplementationDesc;
    public static String selectAFilterImplWarning;
    public static String filterDefinitionNotFound;
    public static String down;
    public static String up;
    public static String deleteDialogTitle;
    public static String deleteDialogConfirmMessage;
    public static String selectOnlyOneElementMessage;
    public static String selectOnlyOneElementTitle;
    public static String filterConfRepositoryName;
    public static String actorFilter;
    public static String set;
    public static String processInitiator;
    public static String setAsProcessInitiator;
    public static String defaultOrganizationDescription;
    public static String actorFilterImplementationPageTitle;
    public static String actorFilterImplementationPageDesc;
    public static String importFilterArchive;
    public static String importingFilterArchive;
    public static String selectFilterImplementationToExportDesc;
    public static String selectFilterImplementationToExportTitle;
    public static String exportSuccessfulTitle;
    public static String exportSuccessfulMsg;
    public static String exportFailedTitle;
    public static String exportFailedMsg;
    public static String emtpyMembershipValue;
    public static String defaultUserOrganizationTitle;
    public static String defaultUserOrganizationDesc;
    public static String actorHasNoMapping;
    public static String incompleteMembership;
    public static String missingGroup;
    public static String missingGroupInMembership;
    public static String missingRoleInMembership;
    public static String missingUserInMembership;
    public static String missingRole;
    public static String missingUser;
    public static String organizationValidationFailed;
    public static String actorFilters;
    public static String actorFiltersConfigurationDescription;
    public static String addingImplementationDependencies;
    public static String invalidImplementationFor;
    public static String implementationNotFound;
    public static String importActorMappingFile;
    public static String exportActorMappingFile;
    public static String addMembershipEtc;
    public static String notMappedActors;
    public static String selectActorFitlerDefinition;
    public static String selectActorFitleImplementation;
    public static String groupIdExample;
    public static String groupNameExample;
    public static String addSubGroup;
    public static String addParentGroup;
    public static String missingMembershipForUser;
    public static String userLastNameMissing;
    public static String userFirstNameMissing;
    public static String userPasswordMissing;
    public static String userNameMissing;
    public static String validatingOrganizationContent;
    public static String deleteActorsTitle;
    public static String deleteActorsTitleMessage;
    public static String managerCycleDetected;
	public static String initiatorExplanation;
	public static String nameTooLong;
	public static String descTooLong;
	public static String active;
	public static String importOrganizationFailedTitle;
	public static String importOrganizationFailedMessage;
	public static String importOrganizationSuccessfullTitle;
	public static String importOrganizationSuccessfullMessage;
	public static String errorSelectionGroups;



}
