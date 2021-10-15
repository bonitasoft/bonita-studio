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
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ProcessInstantiationFormURLBuilderTest {

    private ProcessInstantiationFormURLBuilder applicationURLBuilder;
    private String loginURL;
    private Pool process;

    @Before
    public void setUp() throws Exception {
        process = ProcessFactory.eINSTANCE.createPool();
        process.setName("testPool with space /and slash");
        process.setVersion("1.0");
        applicationURLBuilder = spy(new ProcessInstantiationFormURLBuilder(process,
                ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION));
        doReturn("fr").when(applicationURLBuilder).getWebLocale();
        doReturn("william.jobs").when(applicationURLBuilder).getDefaultUsername();
        doReturn("bpm").when(applicationURLBuilder).getDefaultPassword();
        loginURL = "http://fakeLoginURL";
        doReturn(loginURL).when(applicationURLBuilder).buildLoginUrl(anyString(), anyString());
    }

    @Test
    public void shouldToURL_RetursAValidURL() throws Exception {
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        doReturn(configuration).when(applicationURLBuilder).getConfiguration();

        final URL url = applicationURLBuilder.toURL(AbstractRepository.NULL_PROGRESS_MONITOR);
        
        assertThat(url).isNotNull();
        assertThat(url.toString())
                .contains(URLEncoder.encode("apps/userAppBonita/process-list/", StandardCharsets.UTF_8))
                .contains(URLEncoder.encode("processName=", StandardCharsets.UTF_8) + "testPool%2520with%2520space%2520%2Fand%2520slash")
                .contains(URLEncoder.encode("processVersion=" + process.getVersion(), StandardCharsets.UTF_8))
                .contains(URLEncoder.encode("locale=fr", StandardCharsets.UTF_8))
                .contains(URLEncoder.encode("redirect=task-list", StandardCharsets.UTF_8))
                .startsWith(loginURL);
        verify(applicationURLBuilder).buildLoginUrl("william.jobs", "bpm");
    }

    @Test
    public void shouldToURL_RetursAValidURLForSpecifConf() throws Exception {
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        configuration.setUsername("userInAconf");
        doReturn(Optional.of("passwordInOrga")).when(applicationURLBuilder)
                .retrieveUserPasswordFromActiveOrga("userInAconf");
        doReturn(configuration).when(applicationURLBuilder).getConfiguration();

        final URL url = applicationURLBuilder.toURL(AbstractRepository.NULL_PROGRESS_MONITOR);
        assertThat(url).isNotNull();
        final String validApplicationPath = URLEncoder.encode("apps/userAppBonita/process-list/", "UTF-8");
        final String validProcessReference = "testPool%2520with%2520space%2520%2Fand%2520slash";
        final String validLocale = URLEncoder.encode("locale=fr", "UTF-8");
        verify(applicationURLBuilder).buildLoginUrl("userInAconf", "passwordInOrga");
        assertThat(url.toString())
                .contains(validApplicationPath)
                .contains(validProcessReference)
                .contains(validLocale)
                .startsWith(loginURL);
    }

}
