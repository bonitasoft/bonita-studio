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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.net.URL;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.junit.Before;
import org.junit.Test;


public class CaseDetailURLBuilderTest {

    private CaseDetailURLBuilder caseDetailURLBuilder;
    private String loginURL;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final AbstractProcess process = ProcessFactory.eINSTANCE.createPool();
        process.setName("testPool with space /and slash");
        process.setVersion("1.0");
        caseDetailURLBuilder = spy(new CaseDetailURLBuilder(process, ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION, 12L));
        doReturn("fr").when(caseDetailURLBuilder).getWebLocale();
        doReturn("william.jobs").when(caseDetailURLBuilder).getDefaultUsername();
        doReturn("bpm").when(caseDetailURLBuilder).getDefaultPassword();
        doReturn(45L).when(caseDetailURLBuilder).getUserProfileId(any(IProgressMonitor.class));
        loginURL = "http://fakeLoginURL";
        doReturn(loginURL).when(caseDetailURLBuilder).buildLoginUrl(anyString(), anyString());
    }

    /**
     * Test method for {@link org.bonitasoft.studio.engine.operation.ApplicationURLBuilder#toURL(org.eclipse.core.runtime.IProgressMonitor)}.
     */
    @Test
    public void shouldToURL_RetursAValidURL() throws Exception {
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        doReturn(configuration).when(caseDetailURLBuilder).getConfiguration();

        final URL url = caseDetailURLBuilder.toURL(Repository.NULL_PROGRESS_MONITOR);
        assertThat(url).isNotNull();
        final String validLocale = URLEncoder.encode("_l=fr", "UTF-8");
        final String appliPath = URLEncoder.encode("portal/homepage#?", "UTF-8");
        final String pageCaseMoreDetails = URLEncoder.encode("_p=casemoredetails", "UTF-8");
        final String profileID = URLEncoder.encode("_pf=45", "UTF-8");
        assertThat(url.toString())
                .contains(appliPath)
                .contains(pageCaseMoreDetails)
                .contains(validLocale)
                .contains(profileID)
                .startsWith(loginURL);
        verify(caseDetailURLBuilder).buildLoginUrl("william.jobs", "bpm");
    }

}
