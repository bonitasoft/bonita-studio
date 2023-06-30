/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
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
public class SQLServerConfigExtendedWizardPage extends AbstractConnectDBWizardWizardPage {

	@Override
    public IConnectionProfile getConnectionProfile(final String className, final String jdbcUrl, final String username, final String password)
            throws ConnectionProfileException, ClassNotFoundException, UnsupportedEncodingException {
        return ConnectionProfileUtil.createSQLServerConnectionProfile(jdbcUrl, username, password, className, parseDatabaseFromURL(jdbcUrl));
	}
}
