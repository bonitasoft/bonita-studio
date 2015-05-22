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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

public class SendSystemInfoJob extends Job {

    private final String data;
    private final IPreferenceStore prefStore;
    private final String email;

    public SendSystemInfoJob(final String data, final IPreferenceStore prefStore, final String email) {
        super("Send system infos");
        this.data = data;
        this.prefStore = prefStore;
        this.email = email;
    }

    @Override
    protected IStatus run(final IProgressMonitor monitor) {
    	try {
            final URL url = new URL("http://stats.bonitasoft.org/stats.php");
    		final URLConnection conn = url.openConnection();
    		conn.setDoOutput(true);
            final OutputStreamWriter wr = sendData(conn);
            final BufferedReader rd = checkResponse(conn);
    		wr.close();
    		rd.close();
    	} catch (final Exception e) {
    		// may not be online saving info for sending it later
            prefStore.setValue(BonitaRegistration.BONITA_USER_INFOS, data);
            prefStore.setValue(BonitaRegistration.BONITA_INFO_SENT, 0);
    		return Status.CANCEL_STATUS;
    	}
    	return Status.OK_STATUS;
    }

    protected OutputStreamWriter sendData(final URLConnection conn) throws IOException {
        final OutputStreamWriter wr = createOutputStreamWriter(conn);
        wr.write(email);
        wr.write(data);
        wr.flush();
        return wr;
    }

    protected OutputStreamWriter createOutputStreamWriter(final URLConnection conn) throws IOException {
        return new OutputStreamWriter(conn.getOutputStream());
    }

    protected BufferedReader checkResponse(final URLConnection conn) throws IOException {
        final BufferedReader rd = createBufferedReaderForResponse(conn);
        String line;
        while ((line = rd.readLine()) != null) {
            if ("1".equals(line)) {
                prefStore.setValue(BonitaRegistration.BONITA_INFO_SENT, 1);
                return rd;
        	}
        }
        throw new RuntimeException("Error sending data");
    }

    protected BufferedReader createBufferedReaderForResponse(final URLConnection conn) throws IOException {
        return new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }
}