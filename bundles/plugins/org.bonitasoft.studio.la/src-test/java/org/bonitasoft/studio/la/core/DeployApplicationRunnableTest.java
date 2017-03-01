/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.la.core;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.ApplicationImportPolicy;
import org.bonitasoft.engine.exception.AlreadyExistsException;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.junit.Test;

public class DeployApplicationRunnableTest {

    @Test
    public void should_import_application_xml() throws Exception {
        final ApplicationAPI applicationAPI = mock(ApplicationAPI.class);
        final ApplicationFileStore appFileSotre = mock(ApplicationFileStore.class);
        final DeployApplicationRunnable operation = spy(new DeployApplicationRunnable(applicationAPI, appFileSotre));
        doReturn(Status.OK_STATUS).when(operation).deleteBeforeDeploy(any(IProgressMonitor.class));

        operation.run(new NullProgressMonitor());

        verify(applicationAPI).importApplications(appFileSotre.toByteArray(), ApplicationImportPolicy.FAIL_ON_DUPLICATES);
    }

    @Test
    public void should_fail_import_application_xml_when_app_already_exists() throws Exception {
        final ApplicationAPI applicationAPI = mock(ApplicationAPI.class);
        when(applicationAPI.importApplications(any(byte[].class), eq(ApplicationImportPolicy.FAIL_ON_DUPLICATES)))
                .thenThrow(AlreadyExistsException.class);
        final ApplicationFileStore appFileSotre = mock(ApplicationFileStore.class);
        final DeployApplicationRunnable operation = spy(new DeployApplicationRunnable(applicationAPI, appFileSotre));
        doReturn(Status.OK_STATUS).when(operation).deleteBeforeDeploy(any(IProgressMonitor.class));

        operation.run(new NullProgressMonitor());

        StatusAssert.assertThat(operation.getStatus()).isNotOK();
    }

}
