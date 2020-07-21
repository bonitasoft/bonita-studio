/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.ui.provider;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestorePointLabelProviderTest {

    @Mock
    IRepositoryContainer repositoryContainer;
    private Locale defaultLocale;

    @Before
    public void setUp() throws Exception {
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
    }

    @After
    public void tearDown() throws Exception {
        if (defaultLocale != null) {
            Locale.setDefault(defaultLocale);
        }
    }

    @Test
    public void testGetText() {
        final RestorePointLabelProvider restorePointLabelProvider = new RestorePointLabelProvider();
        final String resourceName = "fakeTestWorkspaceName" + "_" + "20141105-145700";
        Mockito.doReturn(resourceName).when(repositoryContainer).getName();

        final String res = restorePointLabelProvider.getText(repositoryContainer);
        Assertions.assertThat(res).isEqualTo("05 November 2014 14:57:00");
    }

    @Test
    public void testCreateStyledStringWithComment() {
        final RestorePointLabelProvider restorePointLabelProvider = new RestorePointLabelProvider();
        final String datFormatted = restorePointLabelProvider.createDateInCorrectFormat("20141105-145700");
        final StyledString styledString = restorePointLabelProvider.createStyledString("aComment", "6.4", datFormatted);

        Assertions.assertThat(styledString.getString()).isEqualTo("05 November 2014 14:57:00 -- 6.4 -- aComment");
    }

    @Test
    public void testCreateStyledStringWithoutComment() {
        final RestorePointLabelProvider restorePointLabelProvider = new RestorePointLabelProvider();
        final String datFormatted = restorePointLabelProvider.createDateInCorrectFormat("20141105-145700");
        final StyledString styledString = restorePointLabelProvider.createStyledString("", "6.4", datFormatted);

        Assertions.assertThat(styledString.getString()).isEqualTo("05 November 2014 14:57:00 -- 6.4");
    }

}
