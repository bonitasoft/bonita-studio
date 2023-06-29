/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.sqlbuilder.ex.wizard.specific;

import java.io.UnsupportedEncodingException;

import org.bonitasoft.studio.datatools.ConnectionProfileUtil;
import org.bonitasoft.studio.sqlbuilder.ex.wizard.AbstractConnectDBWizardWizardPage;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;


/**
 * @author Romain Bioteau
 *
 */
public class SybaseConfigExtendedWizardPage extends AbstractConnectDBWizardWizardPage {

	@Override
    public IConnectionProfile getConnectionProfile(final String className, final String jdbcUrl, final String username, final String password)
            throws ConnectionProfileException, ClassNotFoundException, UnsupportedEncodingException {
        return ConnectionProfileUtil.createSybaseConnectionProfile(jdbcUrl, username, password, className, parseDatabaseFromURL(jdbcUrl));
	}

}
