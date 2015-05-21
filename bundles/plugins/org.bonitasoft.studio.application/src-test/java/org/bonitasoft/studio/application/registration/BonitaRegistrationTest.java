/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.registration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.bonitasoft.studio.common.ProductVersion;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BonitaRegistrationTest {

    @Mock
    private IPreferenceStore prefStore;
    @Mock
    private SendSystemInfoJob job;
    private BonitaRegistration bonitaRegistration;

    @Before
    public void setUp() throws Exception {
        bonitaRegistration = spy(new BonitaRegistration(prefStore));
        doReturn(job).when(bonitaRegistration).createSystemSendJob(eq(prefStore), anyString(), anyString());
        doNothing().when(bonitaRegistration).addScreenSizeInfo(any(Map.class));
    }

    @Test
    public void testSendUserInfoIfNotSent_withDataNotYetSent() throws Exception {
        bonitaRegistration.sendUserInfoIfNotSent();

        final ArgumentCaptor<String> mailCaptor = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> dataCaptor = ArgumentCaptor.forClass(String.class);
        verify(bonitaRegistration).createSystemSendJob(eq(prefStore), mailCaptor.capture(), dataCaptor.capture());

        assertThat(mailCaptor.getValue()).isNotNull();
        assertThat(dataCaptor.getValue())
                .contains("bonita.version")
                .contains(ProductVersion.CURRENT_VERSION)
                .contains("field_user_systeme_lang");
        for (final String systemProtyToSend : BonitaRegistration.SYSTEM_PROPERTIES_TO_SEND) {
            assertThat(dataCaptor.getValue()).contains(systemProtyToSend);
        }
        verify(prefStore).setValue(BonitaRegistration.BONITA_USER_REGISTERED, 1);
    }

    @Test
    public void testSendUserInfoIfNotSent_withDataAlreadySent_shouldNottryAgain() throws Exception {
        doReturn(1).when(prefStore).getInt(BonitaRegistration.BONITA_INFO_SENT);
        bonitaRegistration.sendUserInfoIfNotSent();

        verify(bonitaRegistration, never()).createSystemSendJob(eq(prefStore), anyString(), anyString());
    }

    @Test
    public void testSendUserInfoIfNotSent_withAlreadyTried10_shouldNottryAgain() throws Exception {
        doReturn(8).when(prefStore).getInt(BonitaRegistration.BONITA_USER_REGISTER_TRY);
        bonitaRegistration.sendUserInfoIfNotSent();

        verify(bonitaRegistration, never()).createSystemSendJob(eq(prefStore), anyString(), anyString());
    }

}
