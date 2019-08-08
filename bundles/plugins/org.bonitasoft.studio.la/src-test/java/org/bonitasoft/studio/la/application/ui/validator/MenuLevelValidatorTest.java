/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.validator;

import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplication;
import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplicationContainer;
import static org.bonitasoft.studio.fakes.IResourceFakesBuilder.anIFile;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.la.application.ui.validator.MenuLevelValidator;
import org.eclipse.core.resources.IFile;
import org.junit.Test;

public class MenuLevelValidatorTest {

    @Test
    public void should_add_warning_message() throws Exception {
        final IFile resource = anIFile().build();
        final MenuLevelValidator menuLevelValidator = spy(new MenuLevelValidator(new ApplicationNodeContainerConverter()));
        doReturn(appWithDeepMenus()).when(menuLevelValidator)
                .toApplicationContainer(resource);
        doReturn(null).when(menuLevelValidator).createMessage(resource);

        menuLevelValidator.validate(resource, 0, null, null);

        verify(menuLevelValidator, times(1)).createMessage(resource);
    }

    @Test
    public void should_not_add_warning_message() throws Exception {
        final IFile resource = anIFile().build();
        final MenuLevelValidator menuLevelValidator = spy(new MenuLevelValidator(new ApplicationNodeContainerConverter()));
        doReturn(appWithSingleMenus()).when(menuLevelValidator)
                .toApplicationContainer(resource);
        doReturn(null).when(menuLevelValidator).createMessage(resource);

        menuLevelValidator.validate(resource, 0, null, null);

        verify(menuLevelValidator, never()).createMessage(resource);
    }

    private ApplicationNodeContainer appWithDeepMenus() {
        return newApplicationContainer()
                .havingApplications(
                        newApplication("", "", "").havingApplicationMenus(ApplicationNodeBuilder.newMenu("level0", "")
                                .havingMenu(ApplicationNodeBuilder.newMenu("level1", "")
                                        .havingMenu(ApplicationNodeBuilder.newMenu("level2", "")))))
                .create();
    }

    private ApplicationNodeContainer appWithSingleMenus() {
        return newApplicationContainer()
                .havingApplications(
                        newApplication("", "", "").havingApplicationMenus(ApplicationNodeBuilder.newMenu("level0", "")
                                .havingMenu(ApplicationNodeBuilder.newMenu("level1", ""))))
                .create();
    }

}
