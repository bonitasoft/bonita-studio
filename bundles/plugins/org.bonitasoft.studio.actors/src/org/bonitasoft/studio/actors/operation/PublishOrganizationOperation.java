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
package org.bonitasoft.studio.actors.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.ProfileAPI;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.exception.CreationException;
import org.bonitasoft.engine.exception.DeletionException;
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
import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.DocumentRoot;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 */
public abstract class PublishOrganizationOperation implements IRunnableWithProgress {

    protected final Organization organization;
    private APISession session;
    private boolean flushSession;
    private boolean shouldApplyAllProfileToUser = true;

    public PublishOrganizationOperation(final Organization organization) {
        this.organization = organization;
    }

    public void setSession(final APISession session) {
        this.session = session;
    }
    
    public PublishOrganizationOperation doNotAllProfileToUsers() {
        shouldApplyAllProfileToUser = false;
        return this;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(organization);

        monitor.beginTask(String.format(Messages.deployingOrganization, organization.getName()), IProgressMonitor.UNKNOWN);

        flushSession = false;
        BonitaStudioLog.info("Loading organization " + organization.getName() + " in portal...", ActorsPlugin.PLUGIN_ID);
        try {
            if (session == null) {
                session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
                flushSession = true;
            }
            final IdentityAPI identityAPI = BOSEngineManager.getInstance().getIdentityAPI(session);
            final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
            final SearchResult<ProcessDeploymentInfo> result = processApi
                    .searchProcessDeploymentInfos(new SearchOptionsBuilder(0, Integer.MAX_VALUE).done());
            for (final ProcessDeploymentInfo info : result.getResult()) {
                processApi.deleteProcessInstances(info.getProcessId(), 0, Integer.MAX_VALUE);
                processApi.deleteArchivedProcessInstances(info.getProcessId(), 0, Integer.MAX_VALUE);
            }
            importOrganization(identityAPI);
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

    protected boolean shouldApplyAllProfileToUsers() {
        return shouldApplyAllProfileToUser;
    }

    protected abstract void importOrganization(IdentityAPI identityAPI)
            throws IOException, DeletionException, OrganizationImportException;

    protected String toString(final Organization organization) throws IOException {
        final XMLResource resource = createResourceFromOrganization(organization);
        final XMLProcessor processor = new OrganizationXMLProcessor();
        final Map<String, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_XML_VERSION, "1.0");
        options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, Boolean.TRUE);
        return processor.saveToString(resource, options);
    }

    private XMLResource createResourceFromOrganization(final Organization organization) {
        final DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot();
        final Organization exportedCopy = EcoreUtil.copy(organization);
        exportedCopy.setName(null);
        exportedCopy.setDescription(null);
        root.setOrganization(exportedCopy);
        final XMLResource resource = new XMLResourceImpl();
        resource.setEncoding("UTF-8");
        resource.getContents().add(root);
        return resource;
    }

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
                        BonitaStudioLog.debug("Failed to map a profile to user", e, ActorsPlugin.PLUGIN_ID);
                    }
                }));
    }

}
