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
package org.bonitasoft.studio.la.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplication;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.wizard.FinishHandler;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.bonitasoft.studio.ui.wizard.WizardPageBuilder;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class NewApplicationHandlerTest {

    @Test
    public void should_open_a_new_application_wizard() throws Exception {
        final NewApplicationHandler newApplicationHandler = spy(new NewApplicationHandler());
        final RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        final ApplicationFileStore applicationFileStore = mock(ApplicationFileStore.class);
        final WizardBuilder<ApplicationFileStore> wizardBuilder = mock(WizardBuilder.class);
        doReturn(wizardBuilder).when(newApplicationHandler).createWizard(notNull(WizardBuilder.class),
                any(RepositoryAccessor.class));
        when(wizardBuilder.open(any(Shell.class), eq(Messages.create))).thenReturn(Optional.of(applicationFileStore));

        newApplicationHandler.openNewApplicationWizard(mock(Shell.class), repositoryAccessor);

        verify(wizardBuilder).open(notNull(Shell.class), eq(Messages.create));
    }

    @Test
    public void should_create_a_new_application_wizard() throws Exception {
        final NewApplicationHandler newApplicationHandler = spy(new NewApplicationHandler());
        final RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        final WizardBuilder<ApplicationFileStore> builder = spy(WizardBuilder.newWizard());
        newApplicationHandler.createWizard(builder, repositoryAccessor);

        verify(builder).withTitle(Messages.createNewApplicationDescriptor);
        verify(builder).havingPage(notNull(WizardPageBuilder.class));
        verify(builder).onFinish(notNull(FinishHandler.class));
    }

    @Test
    public void should_create_an_applicationFileStore_onFinish() throws Exception {
        final NewApplicationHandler newApplicationHandler = new NewApplicationHandler();
        final RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        final ApplicationRepositoryStore applicationStore = mock(ApplicationRepositoryStore.class);
        final ApplicationFileStore applicationFileStore = mock(ApplicationFileStore.class);
        when(applicationStore.createRepositoryFileStore("testAppToken.xml")).thenReturn(applicationFileStore);
        when(repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)).thenReturn(applicationStore);

        final ApplicationNode applicationNode = newApplication("testAppToken", "My App Display Name", "0.1").create();
        final Optional<ApplicationFileStore> fileStore = newApplicationHandler.createApplicationFileStore(
                applicationNode, repositoryAccessor, "testAppToken");

        assertThat(fileStore).isPresent();
        final ArgumentCaptor<ApplicationNodeContainer> captor = ArgumentCaptor.forClass(ApplicationNodeContainer.class);
        verify(applicationFileStore).save(captor.capture());
        assertThat(captor.getValue().getApplications()).extracting("token").contains("testAppToken");
    }

}
