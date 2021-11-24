/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

public class RedirectURLBuilder {

    private static final String BASE_URL = "https://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=";
    private static final String LOCATION_HEADER = "Location";

    public static String create(String redirectId) {
        return BASE_URL + redirectId + "&"
                + bosMajorVersion() + "&"
                + bosMinorVersion() + "&"
                + bosProduct();
    }

    public static URI createURI(String redirectId) {
        try {
            return new URI(create(redirectId));
        } catch (URISyntaxException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private static String bosProduct() {
        return "bos_redirect_product=bos";
    }

    private static String bosMinorVersion() {
        return "bos_redirect_minor_version=" + ProductVersion.maintenanceVersion();
    }

    private static String bosMajorVersion() {
        return "bos_redirect_major_version=" + ProductVersion.minorVersion();
    }

    public static String handleURLRedirection(String url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            if (isRedirect(connection.getResponseCode())) {
                return connection.getHeaderField(LOCATION_HEADER);
            }
            return connection.getURL().toString();
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return url;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

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
}
