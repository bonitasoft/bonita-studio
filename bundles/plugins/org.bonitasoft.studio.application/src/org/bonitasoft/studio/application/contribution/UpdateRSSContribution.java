/**
 * Copyright (C) 2011-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
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
package org.bonitasoft.studio.application.contribution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;

/**
 * @author Romain Bioteau
 * 
 */
public class UpdateRSSContribution implements IPreStartupContribution {

    private static final long ONE_DAY = 24L * 60 * 60 * 1000L;

    protected class UpdateRSSThread extends Thread {

        private URL rssUrl;

        private final String xmlName;

        private final String url;

        private final Class<?> currentClass;

        public UpdateRSSThread(String xmlName, String url, Class<?> currentClass) {
            super();
            this.xmlName = xmlName;
            this.url = url;
            this.currentClass = currentClass;
        }

        @Override
        public void run() {
            try {
                File xmlFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), xmlName + ".xml");
                boolean isFirstRun = false;
                if (!xmlFile.exists()) {
                    isFirstRun = true;
                    FileOutputStream out = new FileOutputStream(xmlFile);
                    final InputStream initialValue = currentClass.getResourceAsStream(xmlName + ".xml");
                    FileUtil.copy(initialValue, out);
                    out.close();
                    initialValue.close();
                }
                rssUrl = xmlFile.toURL();

                long millisecondsSinceRefresh = System.currentTimeMillis() - xmlFile.lastModified();
                if (isFirstRun || millisecondsSinceRefresh > ONE_DAY) {
                    InputStream stream = null;
                    FileOutputStream out = null;
                    try {
                        BonitaStudioLog.debug("Updating RSS feed:" + xmlFile.getName(), ApplicationPlugin.PLUGIN_ID);
                        URLConnection connection = new URL(url).openConnection();
                        connection.setConnectTimeout(4000);
                        stream = connection.getInputStream();
                        out = copyStream(xmlFile, stream, out);
                    } catch (ConnectException e) {
                        BonitaStudioLog.error(e);
                    } catch (IOException e) {
                        BonitaStudioLog.error(e);
                    } finally {
                        closeStreams(stream, out);
                    }
                }
            } catch (Exception e) {
                BonitaStudioLog.error(e);
            }
        }

        private void closeStreams(InputStream stream, FileOutputStream out) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }

        private FileOutputStream copyStream(File xmlFile, InputStream stream,
                FileOutputStream out) throws IOException, FileNotFoundException {
            if (stream.available() > 0) {
                out = new FileOutputStream(xmlFile);
                FileUtil.copy(stream, out);
                out.close();
                stream.close();
                BonitaStudioLog.debug("RSS feed:" + xmlFile.getName() + " updated successfuly!", ApplicationPlugin.PLUGIN_ID);
            } else {
                BonitaStudioLog.debug("RSS feed:" + xmlFile.getName() + " update has failed!", ApplicationPlugin.PLUGIN_ID);
            }
            return out;
        }

        public URL getRss() {
            return rssUrl;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.application.contribution.IPreStartupContribution#execute()
     */
    @Override
    public void execute() {
        new UpdateRSSThread("6-3-community", "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=163", getClass()).start();
        new UpdateRSSThread("6-3-blogs", "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=152", getClass()).start();
        new UpdateRSSThread("6-3-documentation", "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=158", getClass()).start();
        new UpdateRSSThread("6-3-examples-community", "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=159", getClass()).start();
        new UpdateRSSThread("6-3-training", "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=153", getClass()).start();
        new UpdateRSSThread("6-3-videos", "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=154", getClass()).start();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.application.contribution.IPreStartupContribution#canExecute()
     */
    @Override
    public boolean canExecute() {
        return !PlatformUtil.isHeadless() && Platform.getProduct() != null && !Platform.getProduct().getId().equals("org.bonitasoft.studioEx.product")
                && !Platform.getProduct().getId().equals("org.bonitasoft.talendBPM.product");
    }

}
