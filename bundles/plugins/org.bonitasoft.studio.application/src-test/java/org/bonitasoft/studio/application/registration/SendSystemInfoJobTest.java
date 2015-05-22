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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.net.URLConnection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SendSystemInfoJobTest {

    @Mock
    private IPreferenceStore prefStore;
    @Mock
    private OutputStreamWriter osw;
    @Mock
    private BufferedReader br;

    @Test
    public void testSendSystemInfoWorking() throws Exception {
        final SendSystemInfoJob spy = spy(new SendSystemInfoJob("encodedData", prefStore, "email"));
        doReturn(osw).when(spy).createOutputStreamWriter(any(URLConnection.class));
        doReturn(br).when(spy).createBufferedReaderForResponse(any(URLConnection.class));
        when(br.readLine()).thenReturn("1", (String) null);

        assertThat(spy.run(new NullProgressMonitor()).isOK()).isTrue();
        verify(prefStore).setValue(BonitaRegistration.BONITA_INFO_SENT, 1);
        verify(osw).write("encodedData");
        verify(osw).write("email");
    }

    @Test
    public void testSendSystemInfoWhenExceptionThrown() throws Exception {
        final SendSystemInfoJob spy = spy(new SendSystemInfoJob("encodedData", prefStore, "email"));
        doReturn(osw).when(spy).createOutputStreamWriter(any(URLConnection.class));
        doThrow(new RuntimeException("argh")).when(osw).flush();
        doReturn(br).when(spy).createBufferedReaderForResponse(any(URLConnection.class));
        when(br.readLine()).thenReturn("1", (String) null);

        assertThat(spy.run(new NullProgressMonitor()).isOK()).isFalse();
        verify(prefStore).setValue(BonitaRegistration.BONITA_INFO_SENT, 0);
        verify(prefStore).setValue(BonitaRegistration.BONITA_USER_INFOS, "encodedData");
    }

    @Test
    public void testSendSystemInfoWithBadResponse() throws Exception {
        final SendSystemInfoJob spy = spy(new SendSystemInfoJob("encodedData", prefStore, "email"));
        doReturn(osw).when(spy).createOutputStreamWriter(any(URLConnection.class));
        doReturn(br).when(spy).createBufferedReaderForResponse(any(URLConnection.class));
        when(br.readLine()).thenReturn("badResponse", (String) null);

        assertThat(spy.run(new NullProgressMonitor()).isOK()).isFalse();
        verify(prefStore).setValue(BonitaRegistration.BONITA_INFO_SENT, 0);
        verify(prefStore).setValue(BonitaRegistration.BONITA_USER_INFOS, "encodedData");
    }

}
