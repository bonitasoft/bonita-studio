/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine.operation;

import java.io.UnsupportedEncodingException;

import org.bonitasoft.engine.api.ProfileAPI;
import org.bonitasoft.engine.profile.Profile;
import org.bonitasoft.engine.profile.ProfileSearchDescriptor;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IProgressMonitor;

public class CaseDetailURLBuilder extends AbstractProcessRelatedURLBuilder {

    private final Long caseId;

    public CaseDetailURLBuilder(final AbstractProcess process, final String configurationId, final Long caseId) {
        super(process, configurationId);
        this.caseId = caseId;
    }

    @Override
    protected String getRedirectURL(final String locale, final IProgressMonitor monitor) throws UnsupportedEncodingException {
        return "portal/homepage#?"
                + "id=" + caseId
                + "&_p=casemoredetails"
                + "&_pf=" + getUserProfileId(monitor)
                + "&" + getLocaleParameter(locale);
    }

    protected long getUserProfileId(final IProgressMonitor monitor) {
        try {
            final APISession session = BOSEngineManager.getInstance().createSession(process, configurationId, monitor);
            final ProfileAPI profileAPI = BOSEngineManager.getInstance().getProfileAPI(session);
            final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).filter(ProfileSearchDescriptor.NAME, "User").done();
            final SearchResult<Profile> profiles = profileAPI.searchProfiles(searchOptions);
            return profiles.getResult().get(0).getId();
        } catch (final Exception e) {
            BonitaStudioLog.error("Cannot retrieve Profile id for User.", e);
            return 1;//default value for User profile observed
        }
    }

    @Override
    protected String getLocaleParameter(final String locale) {
        return "_l=" + locale;
    }

}
