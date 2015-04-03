/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.form.preview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormPreviewOperationTest {

    @Mock
    private FormPreviewOperation formPreviewOperation;
    @Mock
    HumanTaskInstance task;

    @Before
    public void setup() throws UnsupportedEncodingException, MalformedURLException {

        doCallRealMethod().when(formPreviewOperation).toTaskURL(any(Configuration.class), any(AbstractProcess.class), eq(1L), any(HumanTaskInstance.class),
                any(NullProgressMonitor.class));
        doCallRealMethod().when(formPreviewOperation).getLoginURL(any(Configuration.class));
        doCallRealMethod().when(formPreviewOperation).getRunURLForTask(any(AbstractProcess.class), eq(1L), any(HumanTaskInstance.class));
        doReturn("fr").when(formPreviewOperation).getPortalLocale();
        doReturn(2L).when(task).getId();
        doReturn("http://fakeurLCalledWithRightCredentials").when(formPreviewOperation).generateLoginUrl("userNameOfConf", "pass");
    }

    @Test
    public void testToTaskURL() throws Exception {
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        configuration.setUsername("userNameOfConf");
        configuration.setPassword("pass");
        final AbstractProcess process = ProcessFactory.eINSTANCE.createPool();
        final Task taskStudio = ProcessFactory.eINSTANCE.createTask();
        taskStudio.setName("plop");
        process.getElements().add(taskStudio);
        final URL taskURL = formPreviewOperation.toTaskURL(configuration, process, 1L, task, new NullProgressMonitor());
        assertThat(taskURL.toURI().toString()).contains(URLEncoder.encode("$entry&task=2", "UTF-8"));
    }

}
