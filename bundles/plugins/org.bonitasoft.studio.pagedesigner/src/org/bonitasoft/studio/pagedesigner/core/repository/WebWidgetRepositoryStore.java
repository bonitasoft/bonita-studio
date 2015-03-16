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
package org.bonitasoft.studio.pagedesigner.core.repository;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class WebWidgetRepositoryStore extends AbstractRepositoryStore<WebWidgetFileStore> {

    private final static Set<String> extensions = new HashSet<String>();
    public static final String JSON_EXTENSION = "json";
    public static final String WEB_WIDGET_REPOSITORY_NAME = "web_widgets";

    static {
        extensions.add(JSON_EXTENSION);
    }

    @Override
    public String getName() {
        return WEB_WIDGET_REPOSITORY_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.widgetRepository;
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    public WebWidgetFileStore createRepositoryFileStore(final String fileName) {
        return new WebWidgetFileStore(fileName, this);
    }

}
