/**
 * Copyright (C) 2011-2015 BonitaSoft S.A.
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
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Version;

/**
 * @author Romain Bioteau
 */
public class UpdateRSSContribution implements IPreStartupContribution {

    private static final long ONE_DAY = 24L * 60 * 60 * 1000L;

    protected class UpdateRSSThread extends Thread {

        private URL rssUrl;

        private final String xmlName;

        private final String url;

        private final Class<?> currentClass;

        public UpdateRSSThread(final String xmlName, final String url, final Class<?> currentClass) {
            super();
            this.xmlName = xmlName;
            this.url = url;
            this.currentClass = currentClass;
        }

        @Override
        public void run() {
            try {
                final File xmlFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), xmlName + ".xml");
                boolean isFirstRun = false;
                if (!xmlFile.exists()) {
                    isFirstRun = true;
                    final FileOutputStream out = new FileOutputStream(xmlFile);
                    final InputStream initialValue = currentClass.getResourceAsStream(xmlName + ".xml");
                    FileUtil.copy(initialValue, out);
                    out.close();
                    initialValue.close();
                }
                rssUrl = xmlFile.toURL();

                final long millisecondsSinceRefresh = System.currentTimeMillis() - xmlFile.lastModified();
                if (isFirstRun || millisecondsSinceRefresh > ONE_DAY) {
                    InputStream stream = null;
                    FileOutputStream out = null;
                    try {
                        BonitaStudioLog.debug("Updating RSS feed:" + xmlFile.getName(), ApplicationPlugin.PLUGIN_ID);
                        final URLConnection connection = new URL(url).openConnection();
                        connection.setConnectTimeout(4000);
                        stream = connection.getInputStream();
                        out = copyStream(xmlFile, stream, out);
                    } catch (final ConnectException e) {
                        BonitaStudioLog.error(e);
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e);
                    } finally {
                        closeStreams(stream, out);
                    }
                }
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }

        private void closeStreams(final InputStream stream, final FileOutputStream out) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }

        private FileOutputStream copyStream(final File xmlFile, final InputStream stream,
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
        new UpdateRSSThread(
                "7-0-community",
                "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=163&bos_redirect_product=bos&bos_redirect_major_version=" + getMajorVersion()
                        + "&bos_redirect_minor_version=" + getMinorVersion(),
                getClass()).start();
        new UpdateRSSThread(
                "7-0-blogs",
                "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=152&bos_redirect_product=bos&bos_redirect_major_version=" + getMajorVersion()
                        + "&bos_redirect_minor_version=" + getMinorVersion(),
                getClass()).start();
        new UpdateRSSThread(
                "7-0-documentation",
                "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=158&bos_redirect_product=bos&bos_redirect_major_version=" + getMajorVersion()
                        + "&bos_redirect_minor_version=" + getMinorVersion(),
                getClass()).start();
        new UpdateRSSThread(
                "7-0-examples-community",
                "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=159&bos_redirect_product=bos&bos_redirect_major_version=" + getMajorVersion()
                        + "&bos_redirect_minor_version=" + getMinorVersion(),
                getClass()).start();
        new UpdateRSSThread(
                "7-0-training",
                "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=153&bos_redirect_product=bos&bos_redirect_major_version=" + getMajorVersion()
                        + "&bos_redirect_minor_version=" + getMinorVersion(),
                getClass()).start();
        new UpdateRSSThread(
                "7-0-videos-community",
                "http://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=154&bos_redirect_product=bos&bos_redirect_major_version=" + getMajorVersion()
                        + "&bos_redirect_minor_version=" + getMinorVersion(),
                getClass()).start();
    }

    protected Version getProductVersion() {
        return new Version(ProductVersion.CURRENT_VERSION);
    }

    protected String getMajorVersion() {
        final Version version = getProductVersion();
        return version.getMajor() + "." + version.getMinor();
    }

    protected String getMinorVersion() {
        final Version version = getProductVersion();
        return Integer.toString(version.getMicro());
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
