/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.application.security;

import javax.crypto.spec.PBEKeySpec;

import org.eclipse.equinox.security.storage.provider.IPreferencesContainer;

/**
 * @author Romain Bioteau
 * Use to generate a common secure storage password for all platforms
 */
public class BonitaStudioPasswordProvider extends org.eclipse.equinox.security.storage.provider.PasswordProvider {


	private static final String DEFAULT_PASSWORD = "Il0v380n1ta";

	public BonitaStudioPasswordProvider() {

	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.security.storage.provider.PasswordProvider#getPassword(org.eclipse.equinox.security.storage.provider.IPreferencesContainer, int)
	 */
	@Override
	public PBEKeySpec getPassword(IPreferencesContainer container,	int passwordType) {
		return new PBEKeySpec(DEFAULT_PASSWORD.toCharArray()) ;
	}

}
