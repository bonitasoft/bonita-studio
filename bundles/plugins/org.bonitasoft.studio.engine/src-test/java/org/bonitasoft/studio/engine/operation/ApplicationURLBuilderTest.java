/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.operation;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.net.URL;
import java.net.URLEncoder;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class ApplicationURLBuilderTest {

	private ApplicationURLBuilder applicationURLBuilder;
	private String loginURL;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		AbstractProcess process = ProcessFactory.eINSTANCE.createPool();
		process.setName("testPool");
		process.setVersion("1.0");
		Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
		applicationURLBuilder = spy(new ApplicationURLBuilder(process, 1L, ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION));
		doReturn("fr").when(applicationURLBuilder).getWebLocale();
		doReturn("william.jobs").when(applicationURLBuilder).getDefaultUsername();
		doReturn("bpm").when(applicationURLBuilder).getDefaultPassword();
		doReturn(configuration).when(applicationURLBuilder).getConfiguration();
		loginURL = "http://fakeLoginURL";
		doReturn(loginURL).when(applicationURLBuilder).buildLoginUrl(anyString(),anyString());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.bonitasoft.studio.engine.operation.ApplicationURLBuilder#toURL(org.eclipse.core.runtime.IProgressMonitor)}.
	 */
	@Test
	public void shouldToURL_RetursAValidURL() throws Exception {
		URL url = applicationURLBuilder.toURL(Repository.NULL_PROGRESS_MONITOR);
		assertThat(url).isNotNull();
		String validApplicationPath = URLEncoder.encode("portal/homepage","UTF-8");
		String validProcessReference = URLEncoder.encode("form=","UTF-8");
		String validMode = URLEncoder.encode("mode=app","UTF-8");
		String validLocale = URLEncoder.encode("locale=fr","UTF-8");
		assertThat(url.toString()).contains(validApplicationPath).contains(validProcessReference).contains(validMode).contains(validLocale).startsWith(loginURL);
	}

}
