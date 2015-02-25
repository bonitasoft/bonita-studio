/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.pagedesigner.core;

import java.io.File;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pagedesigner.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebWidgetRepositoryStore;

/**
 * @author Romain Bioteau
 *
 */
public class WorkspaceSystemProperties {

    private static final String REPOSITORY_PAGES_PROPERTIES = "repository.pages";
    private static final String REPOSITORY_FRAGMENTS_PROPERTIES = "repository.fragments";
    private static final String REPOSITORY_WIDGETS_PROPERTIES = "repository.widgets";
    private static final String WORKSPACE_API_REST_URL = "workspace.api.rest.url";

    public String getPageRepositoryLocation() {
        final WebPageRepositoryStore webFormRepository = getWebPageRepository();
        if(webFormRepository == null){
            throw new IllegalStateException("WebFormRepositoryStore has not be loaded yet.");
        }
        return aSystemProperty(REPOSITORY_PAGES_PROPERTIES, webFormRepository.getResource().getLocation().toFile());
    }

    public String getFragmentRepositoryLocation() {
        final WebFragmentRepositoryStore webFragmentRepository = getWebFragmentRepository();
        if (webFragmentRepository == null) {
            throw new IllegalStateException("WebFragmentRepositoryStore has not be loaded yet.");
        }
        return aSystemProperty(REPOSITORY_FRAGMENTS_PROPERTIES, webFragmentRepository.getResource().getLocation().toFile());
    }

    public String getWidgetRepositoryLocation() {
        final WebWidgetRepositoryStore webWidgetRepository = getWebWidgetRepository();
        if (webWidgetRepository == null) {
            throw new IllegalStateException("WebWidgetRepositoryStore has not be loaded yet.");
        }
        return aSystemProperty(REPOSITORY_WIDGETS_PROPERTIES, webWidgetRepository.getResource().getLocation().toFile());
    }

    protected WebPageRepositoryStore getWebPageRepository() {
        return RepositoryManager.getInstance().getRepositoryStore(WebPageRepositoryStore.class);
    }

    protected WebFragmentRepositoryStore getWebFragmentRepository() {
        return RepositoryManager.getInstance().getRepositoryStore(WebFragmentRepositoryStore.class);
    }

    protected WebWidgetRepositoryStore getWebWidgetRepository() {
        return RepositoryManager.getInstance().getRepositoryStore(WebWidgetRepositoryStore.class);
    }

    static String aSystemProperty(final String propertyName, final Object value) {
        String properyValue = value.toString();
        if (value instanceof File) {
            properyValue = "\"" + ((File) value).getAbsolutePath() + "\"";
        }
        return "-D" + propertyName + "=" + properyValue;
    }

    public String getRestAPIURL(final int port) {
        return aSystemProperty(WORKSPACE_API_REST_URL, String.format("http://localhost:%s/api/workspace", port));
    }

}
