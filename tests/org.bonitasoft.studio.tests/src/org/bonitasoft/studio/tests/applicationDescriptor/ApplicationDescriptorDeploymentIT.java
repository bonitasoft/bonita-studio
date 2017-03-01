/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.applicationDescriptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplication;
import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplicationContainer;

import java.util.stream.Stream;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.Application;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.la.core.DeleteApplicationRunnable;
import org.bonitasoft.studio.la.core.DeployApplicationRunnable;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationDescriptorDeploymentIT {

    private RepositoryAccessor repositoryAccessor;
    private IProgressMonitor monitor;
    private BOSEngineManager engineManager;
    private APISession session;

    @Before
    public void init() throws Exception {
        engineManager = BOSEngineManager.getInstance();
        session = engineManager.loginDefaultTenant(monitor);
        monitor = new NullProgressMonitor();
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @After
    public void closeSession() throws Exception {
        if (session != null) {
            engineManager.logoutDefaultTenant(session);
        }
    }

    @Test
    public void should_create_update_and_delete_application_descriptors() throws Exception {
        final ApplicationRepositoryStore store = repositoryAccessor
                .getRepositoryStore(ApplicationRepositoryStore.class);
        final ApplicationFileStore appFileSotre = store.createRepositoryFileStore("myApp.xml");
        appFileSotre.save(newApplicationContainer()
                .havingApplications(
                        newApplication("myApp", "My App", "1.0"),
                        newApplication("myOtherApp", "My Other App", "1.0"))
                .create());

        final ApplicationAPI applicationAPI = engineManager.getApplicationAPI(session);

        //Create new app descriptors
        DeployApplicationRunnable deployApplicationRunnable = new DeployApplicationRunnable(applicationAPI,
                appFileSotre);
        deployApplicationRunnable.run(monitor);

        StatusAssert.assertThat(deployApplicationRunnable.getStatus()).isOK();

        final Stream<Application> deployedAppsStream = applicationAPI
                .searchApplications(new SearchOptionsBuilder(0, 10).done()).getResult().stream();
        assertThat(deployedAppsStream).extracting(Application::getToken).containsOnly("myApp", "myOtherApp");

        appFileSotre.save(newApplicationContainer()
                .havingApplications(
                        newApplication("myApp", "My New App Name", "1.0"),
                        newApplication("myOtherApp", "My Other App", "2.0"))
                .create());

        //Update existing app descriptors
        deployApplicationRunnable = new DeployApplicationRunnable(applicationAPI,
                appFileSotre);
        deployApplicationRunnable.run(monitor);

        StatusAssert.assertThat(deployApplicationRunnable.getStatus()).isOK();
        final Stream<Application> applications = applicationAPI
                .searchApplications(new SearchOptionsBuilder(0, 10).done()).getResult().stream();
        assertThat(applications).hasSize(2).extracting("token", "displayName", "version").contains(
                tuple("myApp", "My New App Name", "1.0"),
                tuple("myOtherApp", "My Other App", "2.0"));

        //Delete app descriptors
        final DeleteApplicationRunnable deleteApplicationRunnable = new DeleteApplicationRunnable(applicationAPI,
                appFileSotre);
        deleteApplicationRunnable.run(monitor);

        StatusAssert.assertThat(deleteApplicationRunnable.getStatus()).isOK();
        assertThat(applicationAPI
                .searchApplications(new SearchOptionsBuilder(0, 10).done()).getResult().stream())
                        .isEmpty();
    }

}
