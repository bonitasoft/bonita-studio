/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.identity.organization.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.ProfileAPI;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.exception.CreationException;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.exception.ProcessInstanceHierarchicalDeletionException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.identity.OrganizationImportException;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserCriterion;
import org.bonitasoft.engine.profile.Profile;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public abstract class PublishOrganizationOperation implements IRunnableWithProgress {

    protected final Organization organization;
    private APISession session;
    private boolean shouldApplyAllProfileToUser = true;

    PublishOrganizationOperation(final Organization organization) {
        this.organization = organization;
    }

    public void setSession(final APISession session) {
        this.session = session;
    }
    
    public PublishOrganizationOperation doNotApplyAllProfileToUsers() {
        shouldApplyAllProfileToUser = false;
        return this;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(organization);

        var organizationName = organization.eResource() != null ?  organization.eResource().getURI().lastSegment() : "";
        if(organizationName != null && organizationName.endsWith(".xml")) {
        	organizationName = organizationName.substring(0, organizationName.length() - 4);
        }
        monitor.beginTask(String.format(Messages.deployingOrganization, organizationName), IProgressMonitor.UNKNOWN);

        var flushSession = false;
        BonitaStudioLog.info("Importing " + organizationName + " organization in runtime...", IdentityPlugin.PLUGIN_ID);
        try {
            if (session == null) {
                session = BOSEngineManager.getInstance().loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
                flushSession = true;
            }
            final IdentityAPI identityAPI = BOSEngineManager.getInstance().getIdentityAPI(session);
            final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
            final SearchResult<ProcessDeploymentInfo> result = processApi
                    .searchProcessDeploymentInfos(new SearchOptionsBuilder(0, Integer.MAX_VALUE).done());
            for (final ProcessDeploymentInfo info : result.getResult()) {
                deleteProcessInstances(processApi, info);
                processApi.deleteArchivedProcessInstances(info.getProcessId(), 0, Integer.MAX_VALUE);
            }
            importOrganization(identityAPI);
            new ActiveOrganizationProvider().saveActiveOrganization(organizationName);
            if (shouldApplyAllProfileToUsers()) {
                applyAllProfileToUsers(identityAPI, BOSEngineManager.getInstance().getProfileAPI(session));
            }
        } catch (final Exception e) {
            throw new InvocationTargetException(e);
        } finally {
            if (flushSession && session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
                session = null;
            }
        }
    }

    private void deleteProcessInstances(final ProcessAPI processApi, final ProcessDeploymentInfo info)
            throws DeletionException {
        try {
            processApi.deleteProcessInstances(info.getProcessId(), 0, Integer.MAX_VALUE);
        }catch (ProcessInstanceHierarchicalDeletionException e) {
            processApi.deleteProcessInstance(e.getProcessInstanceId());
            deleteProcessInstances(processApi, info);
        }
    }

    protected boolean shouldApplyAllProfileToUsers() {
        return shouldApplyAllProfileToUser;
    }

    protected abstract void importOrganization(IdentityAPI identityAPI)
            throws IOException, DeletionException, OrganizationImportException;

    protected void applyAllProfileToUsers(final IdentityAPI identityAPI, final ProfileAPI profileAPI)
            throws SearchException {
        final List<Long> profiles = new ArrayList<>();
        final SearchOptions options = new SearchOptionsBuilder(0, Integer.MAX_VALUE).sort("name", Order.DESC).done();
        final SearchResult<Profile> searchedProfiles = profileAPI.searchProfiles(options);
        searchedProfiles.getResult().stream()
                .map(Profile::getId)
                .forEach(profiles::add);

        identityAPI.getUsers(0, Integer.MAX_VALUE, UserCriterion.USER_NAME_ASC).stream()
                .map(User::getId)
                .forEach(userId -> profiles.stream().forEach(profileId -> {
                    try {
                        profileAPI.createProfileMember(profileId, userId, -1L, -1L);
                    } catch (final CreationException e) {
                        BonitaStudioLog.debug("Failed to map a profile to user", e, IdentityPlugin.PLUGIN_ID);
                    }
                }));
    }

}
