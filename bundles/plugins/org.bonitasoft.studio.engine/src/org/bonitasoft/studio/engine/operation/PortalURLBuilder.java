/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.engine.operation;

import java.io.UnsupportedEncodingException;


/**
 * @author Romain Bioteau
 *
 */
public class PortalURLBuilder extends ApplicationURLBuilder{

	public PortalURLBuilder(){
		super(null, 0L, null);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.engine.operation.ApplicationURLBuilder#getRedirectURL(java.lang.String)
	 */
	@Override
	protected String getRedirectURL(String locale) 	throws UnsupportedEncodingException {
		return APPLI_PATH + getLocaleParameter(locale) +"#?_p=tasklistinguser&_pf=1&_f=available";
	}

	@Override
	protected String getLocaleParameter(String locale) {
		return "_l="+locale;
	}


}
