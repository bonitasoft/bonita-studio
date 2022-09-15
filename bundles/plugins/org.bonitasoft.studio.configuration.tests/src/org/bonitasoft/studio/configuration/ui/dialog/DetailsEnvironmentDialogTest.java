/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.configuration.ui.dialog;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.configuration.environment.EnvironmentFactory;
import org.bonitasoft.studio.configuration.repository.EnvironmentFileStore;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DetailsEnvironmentDialogTest {

    public static class AdaptableEnvironmentFileStore extends EnvironmentFileStore implements IAdaptable {

        public AdaptableEnvironmentFileStore(String fileName, IRepositoryStore<EnvironmentFileStore> store) {
            super(fileName, store);
        }
    }

    @Mock
    private EnvironmentRepositoryStore envStore;

    @Test
    public void validateExistingEnv() throws Exception {
        // Given
        when(envStore.getChild("Hello.xml", false)).thenReturn(Mockito.mock(EnvironmentFileStore.class));
        var helloEnv = env("Hello");
        var prodEnv = env("Production");
        when(envStore.getChildren()).thenReturn(List.of(helloEnv, prodEnv));
        var envToModify = EnvironmentFactory.eINSTANCE.createEnvironment();
        envToModify.setName("Hello");
        var validator = new DetailsEnvironmentDialog(new Shell(), envToModify).existingEnvValidator(envStore);

        // When 
        assertThat(validator.validate("Hello")).isOK();
        assertThat(validator.validate("HellO")).isError();
        assertThat(validator.validate("Production")).isError();
        assertThat(validator.validate("Qualif")).isOK();
    }

    private EnvironmentFileStore env(String envDisplayName) {
        var env = mock(AdaptableEnvironmentFileStore.class);
        IDisplayable displayable = () -> envDisplayName;
        when(env.getAdapter(IDisplayable.class)).thenReturn(displayable);
        return env;
    }

}
