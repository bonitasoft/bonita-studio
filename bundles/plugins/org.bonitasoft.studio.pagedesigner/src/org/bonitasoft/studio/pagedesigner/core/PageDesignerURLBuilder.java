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

import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * @author Romain Bioteau
 *
 */
public class PageDesignerURLBuilder {

    private static final String PAGE_BUILDER_ROOT = "page-builder";
    private final IPreferenceStore preferenceStore;

    public PageDesignerURLBuilder(final IPreferenceStore preferenceStore) {
        this.preferenceStore = preferenceStore;
    }

    public URL build() throws MalformedURLException {
        final String port = preferenceStore.getString(BonitaPreferenceConstants.CONSOLE_PORT);
        final String host = preferenceStore.getString(BonitaPreferenceConstants.CONSOLE_HOST);
        return new URL("http://" + host + ":" + port + "/" + PAGE_BUILDER_ROOT);
    }
}
