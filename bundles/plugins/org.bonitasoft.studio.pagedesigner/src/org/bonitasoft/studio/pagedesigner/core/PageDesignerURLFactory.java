/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.pagedesigner.core;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * @author Romain Bioteau
 */
public class PageDesignerURLFactory {

    private static final String PAGE_BUILDER_ROOT = "page-designer";
    private final String host;
    private final int port;

    public PageDesignerURLFactory(final String host, final int port) {
        checkNotNull(host);
        checkArgument(port > 0);
        this.host = host;
        this.port = port;
    }

    public URL openPageDesignerHome() throws MalformedURLException {
        return new URL("http://" + host() + ":" + port() + "/" + PAGE_BUILDER_ROOT);
    }

    public URL openPage(final String pageId) throws MalformedURLException {
        return new URL("http://" + host() + ":" + port() + "/" + PAGE_BUILDER_ROOT + "/#/" + Locale.getDefault().getLanguage() + "/pages/" + pageId);
    }

    public URL newPage() throws MalformedURLException {
        return new URL("http://" + host() + ":" + port() + "/" + PAGE_BUILDER_ROOT + "/api/rest/pages/");
    }

    private String host() {
        return host;
    }

    private String port() {
        return String.valueOf(port);
    }
}
