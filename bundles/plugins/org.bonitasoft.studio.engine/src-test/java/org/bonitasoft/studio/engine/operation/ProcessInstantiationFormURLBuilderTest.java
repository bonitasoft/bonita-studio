/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.net.URL;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class ProcessInstantiationFormURLBuilderTest {

    private ProcessInstantiationFormURLBuilder applicationURLBuilder;
    private String loginURL;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final AbstractProcess process = ProcessFactory.eINSTANCE.createPool();
        process.setName("testPool with space /and slash");
        process.setVersion("1.0");
        applicationURLBuilder = spy(new ProcessInstantiationFormURLBuilder(process, ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION, 12L));
        doReturn("fr").when(applicationURLBuilder).getWebLocale();
        doReturn("william.jobs").when(applicationURLBuilder).getDefaultUsername();
        doReturn("bpm").when(applicationURLBuilder).getDefaultPassword();
        loginURL = "http://fakeLoginURL";
        doReturn(loginURL).when(applicationURLBuilder).buildLoginUrl(anyString(),anyString());
    }

    /**
     * Test method for {@link org.bonitasoft.studio.engine.operation.ProcessInstantiationFormURLBuilder#toURL(org.eclipse.core.runtime.IProgressMonitor)}.
     */
    @Test
    public void shouldToURL_RetursAValidURL() throws Exception {
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        doReturn(configuration).when(applicationURLBuilder).getConfiguration();

        final URL url = applicationURLBuilder.toURL(AbstractRepository.NULL_PROGRESS_MONITOR);
        assertThat(url).isNotNull();
        final String validApplicationPath = URLEncoder.encode("portal/resource/process/", "UTF-8");
        final String validProcessReference = "testPool%2520with%2520space%2520%2Fand%2520slash%2F1.0%2Fcontent";
        final String validProcDefId = URLEncoder.encode("id=12", "UTF-8");
        final String validLocale = URLEncoder.encode("locale=fr","UTF-8");
        assertThat(url.toString())
                .contains(validApplicationPath)
                .contains(validProcessReference)
                .contains(validLocale)
                .contains(validProcDefId)
                .startsWith(loginURL);
        verify(applicationURLBuilder).buildLoginUrl("william.jobs", "bpm");
    }

    /**
     * Test method for {@link org.bonitasoft.studio.engine.operation.ProcessInstantiationFormURLBuilder#toURL(org.eclipse.core.runtime.IProgressMonitor)}.
     */
    @Test
    public void shouldToURL_RetursAValidURLForSpecifConf() throws Exception {
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        configuration.setUsername("userInAconf");
        configuration.setPassword("passwordInCOnf");
        doReturn(configuration).when(applicationURLBuilder).getConfiguration();

        final URL url = applicationURLBuilder.toURL(AbstractRepository.NULL_PROGRESS_MONITOR);
        assertThat(url).isNotNull();
        final String validApplicationPath = URLEncoder.encode("portal/resource/process/", "UTF-8");
        final String validProcessReference = "testPool%2520with%2520space%2520%2Fand%2520slash%2F1.0%2Fcontent";
        final String validProcDefId = URLEncoder.encode("id=12", "UTF-8");
        final String validLocale = URLEncoder.encode("locale=fr", "UTF-8");
        verify(applicationURLBuilder).buildLoginUrl("userInAconf", "passwordInCOnf");
        assertThat(url.toString())
                .contains(validApplicationPath)
                .contains(validProcessReference)
                .contains(validLocale)
                .contains(validProcDefId)
                .startsWith(loginURL);
    }

}
