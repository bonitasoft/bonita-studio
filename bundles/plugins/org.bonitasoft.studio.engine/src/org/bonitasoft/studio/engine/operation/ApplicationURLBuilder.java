/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.operation;

import java.io.UnsupportedEncodingException;

import org.bonitasoft.studio.engine.operation.PortalURLBuilder;
import org.eclipse.core.runtime.IProgressMonitor;

public class ApplicationURLBuilder extends PortalURLBuilder {

    private final String appToken;

    public ApplicationURLBuilder(String appToken) {
        this.appToken = appToken;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.engine.operation.PortalURLBuilder#getRedirectURL(java.lang.String, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected String getRedirectURL(String locale, IProgressMonitor monitor) throws UnsupportedEncodingException {
        return String.format("apps/%s?%s", appToken, getLocaleParameter(locale));
    }
}
