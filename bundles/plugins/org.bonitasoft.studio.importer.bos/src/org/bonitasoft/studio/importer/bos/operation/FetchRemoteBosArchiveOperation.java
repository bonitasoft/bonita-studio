/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.bos.operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.wizard.URLTempPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class FetchRemoteBosArchiveOperation implements IRunnableWithProgress {

    private static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
    private static final String FILENAME_PARAM = "filename=";
    private static final String LOCATION_HEADER = "Location";

    private URLTempPath ulrTempPath;
    private String url;

    public FetchRemoteBosArchiveOperation(String url) {
        this.url = url;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.downloadingRemoteBosArchive, IProgressMonitor.UNKNOWN);
        HttpURLConnection httpConnection = null;
        try {
            httpConnection = handleRedirection((HttpURLConnection) (new URL(url).openConnection()));
            double completeFileSize = httpConnection.getContentLength();
            Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));
            Path tempFile = tempDir.resolve(getFilename(httpConnection));
            if (tempFile.toFile().exists()) {
                tempFile.toFile().delete();
            }
            tempFile = Files.createFile(tempFile);
            try (ReadableByteChannel readableByteChannel = Channels.newChannel(httpConnection.getURL().openStream());
                    FileOutputStream fileOutputStream = new FileOutputStream(tempFile.toFile());
                    FileChannel fileChannel = fileOutputStream.getChannel()) {
                long chunkSize = 500;
                int position = 0;
                monitor.beginTask(Messages.downloadingRemoteBosArchive, (int) completeFileSize);
                while (position < completeFileSize) {
                    position += fileOutputStream.getChannel().transferFrom(readableByteChannel, position, chunkSize);
                    monitor.worked((int) chunkSize);
                }
            }
            httpConnection.disconnect();
            ulrTempPath = new URLTempPath(httpConnection.getURL(), tempFile);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
    }

    public static HttpURLConnection handleRedirection(HttpURLConnection connection) throws IOException {
        if (isRedirect(connection.getResponseCode())) {
            String newUrl = connection.getHeaderField(LOCATION_HEADER);
            URL url = new URL(newUrl);
            return (HttpURLConnection) url.openConnection();
        }
        return connection;
    }

    private static boolean isRedirect(int statusCode) {
        if (statusCode != HttpURLConnection.HTTP_OK) {
            if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || statusCode == HttpURLConnection.HTTP_MOVED_PERM
                    || statusCode == HttpURLConnection.HTTP_SEE_OTHER) {
                return true;
            }
        }
        return false;
    }

    public static String getFilename(HttpURLConnection httpConnection) {
        String headerField = httpConnection.getHeaderField(CONTENT_DISPOSITION_HEADER);
        String filename = new File(httpConnection.getURL().getFile()).getName();
        if (headerField != null && !headerField.isEmpty() && headerField.contains(FILENAME_PARAM)) {
            filename = headerField.split(FILENAME_PARAM)[1];
            if (filename.startsWith("\"") && filename.startsWith("\"")) {
                filename = filename.substring(1, filename.length() - 1);
            }
            if (filename.startsWith("'") && filename.startsWith("'")) {
                filename = filename.substring(1, filename.length() - 1);
            }
        }
        return filename;
    }

    public URLTempPath getURLTempPath() {
        return ulrTempPath;
    }

}
