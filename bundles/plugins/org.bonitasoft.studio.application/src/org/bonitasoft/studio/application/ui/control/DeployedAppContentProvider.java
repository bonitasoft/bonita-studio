/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.ui.control;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.api.ProfileAPI;
import org.bonitasoft.engine.api.UserAPI;
import org.bonitasoft.engine.api.result.StatusCode;
import org.bonitasoft.engine.business.application.Application;
import org.bonitasoft.engine.business.application.ApplicationSearchDescriptor;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.identity.UserNotFoundException;
import org.bonitasoft.engine.profile.Profile;
import org.bonitasoft.engine.profile.ProfileCriterion;
import org.bonitasoft.engine.profile.ProfileNotFoundException;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.engine.operation.ApplicationURLBuilder;
import org.bonitasoft.studio.engine.operation.PortalURLBuilder;
import org.eclipse.core.runtime.IStatus;

public class DeployedAppContentProvider {

    private String selection;
    private List<ApplicationItem> applications;

    public DeployedAppContentProvider(IStatus status, ApplicationAPI appAPI, ProfileAPI profileAPI,
            UserAPI userAPI) {
        this.applications = appFromStatus(status, appAPI, profileAPI, userAPI);
        this.applications.addAll(portalApplications(profileAPI, userAPI));
        this.selection = applications.stream()
                .findFirst()
                .map(ApplicationItem::toString)
                .orElseThrow(() -> new IllegalStateException("No application found."));
    }

    private Collection<? extends ApplicationItem> portalApplications(ProfileAPI profileAPI, UserAPI userAPI) {
        return getDefaultUserProfiles(profileAPI, userAPI).stream()
                .map(profile -> createPortalApplication(profile))
                .sorted()
                .collect(Collectors.toList());
    }

    private ApplicationItem createPortalApplication(Profile profile) {
        ApplicationItem applicationItem = new ApplicationItem();
        applicationItem.setName(Messages.portalAppName);
        applicationItem.setProfileName(profile.getName());
        applicationItem.setProfileId(profile.getId());
        try {
            applicationItem.setURL(new PortalURLBuilder().withProfile(profile.getId()).toURL(Repository.NULL_PROGRESS_MONITOR));
        } catch (MalformedURLException | UnsupportedEncodingException | URISyntaxException e) {
            BonitaStudioLog.error(e);
            return null;
        }
        return applicationItem;

    }

    private List<Profile> getDefaultUserProfiles(ProfileAPI profileAPI, UserAPI userAPI) {
        try {
            return profileAPI.getProfilesForUser(userAPI.getUserByUserName(getDefaultUsername()).getId(), 0,
                    Integer.MAX_VALUE, ProfileCriterion.NAME_ASC);
        } catch (UserNotFoundException e) {
            BonitaStudioLog.error(e);
            return Collections.emptyList();
        }
    }

    private String getDefaultUsername() {
        return new ActiveOrganizationProvider().getDefaultUser();
    }

    private List<ApplicationItem> appFromStatus(IStatus status, ApplicationAPI appAPI, ProfileAPI profileAPI,
            UserAPI userAPI) {
        return Stream.of(status.getChildren())
                .filter(s -> Objects.equals(s.getCode(), StatusCode.LIVING_APP_DEPLOYMENT.ordinal()))
                .map(IStatus::getMessage)
                .map(appToken -> createApplicationItem(appToken, appAPI, profileAPI, userAPI))
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());
    }

    public String[] getItems() {
        List<String> appTokens = applications.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        return appTokens.toArray(new String[] {});
    }

    private ApplicationItem createApplicationItem(String appToken, ApplicationAPI appAPI, ProfileAPI profileAPI,
            UserAPI userAPI) {
        try {
            List<Application> apps = appAPI.searchApplications(new SearchOptionsBuilder(0, 1)
                    .filter(ApplicationSearchDescriptor.TOKEN, appToken)
                    .done()).getResult();
            if (!apps.isEmpty()) {
                Application application = apps.get(0);
                if (getDefaultUserProfiles(profileAPI, userAPI).stream().map(Profile::getId)
                        .anyMatch(application.getProfileId()::equals)) {
                    ApplicationItem applicationItem = new ApplicationItem();
                    applicationItem.setName(application.getDisplayName());
                    applicationItem.setProfileId(application.getProfileId());
                    applicationItem.setProfileName(profileAPI.getProfile(application.getProfileId()).getName());
                    applicationItem.setURL(new ApplicationURLBuilder(appToken)
                            .toURL(Repository.NULL_PROGRESS_MONITOR));
                    return applicationItem;
                }
            }
        } catch (SearchException | ProfileNotFoundException | MalformedURLException | UnsupportedEncodingException
                | URISyntaxException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    public URL getSelectedURL() throws MalformedURLException, UnsupportedEncodingException, URISyntaxException {
        return getAppURL(getSelection());
    }

    private URL getAppURL(String displayName) {
        return applications.stream()
                .filter(app -> Objects.equals(displayName, app.toString()))
                .findFirst()
                .map(ApplicationItem::getUrl)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Application not found for %s", displayName)));
    }

    class ApplicationItem implements Comparable<ApplicationItem>{

        private String name;
        private String profileName;
        private long profileId;
        private URL url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getProfileName() {
            return profileName;
        }

        public void setProfileName(String profileName) {
            this.profileName = profileName;
        }

        public long getProfileId() {
            return profileId;
        }

        public void setProfileId(long profileId) {
            this.profileId = profileId;
        }

        public void setURL(URL url) {
            this.url = url;
        }

        public URL getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return String.format("%s -- %s", getName(), getProfileName());
        }

        @Override
        public int compareTo(ApplicationItem item) {
            return toString().compareTo(item.toString());
        }

    }

}
