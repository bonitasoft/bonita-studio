/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

package org.bonitasoft.studio.engine.preferences;

import org.bonitasoft.studio.common.BonitaConstants;


public class EnginePreferenceConstants {

    private EnginePreferenceConstants() {
        //implicit constructor
    }

    public static final String REMOTE_DEPLOYMENT_CHOICE = BonitaConstants.API_TYPE_PROPERTY ;
    public static final String REMOTE_DEPLOYMENT_URL = BonitaConstants.PROVIDER_URL_PROPERTY;
    public static final String REMOTE_DEPLOYMENT_FACTORY = BonitaConstants.INITIAL_CONTEXT_FACTORY_PROPERTY ;
    public static final String STANDARD_MODE = "Standard"; //$NON-NLS-1$
    public static final String EJB2_MODE = "EJB2"; //$NON-NLS-1$
    public static final String EJB3_MODE = "EJB3"; //$NON-NLS-1$
    public static final String REMOTE_DEPLOYMENT_JAAS_FILE = BonitaConstants.JAAS_PROPERTY ;
    public static final String CURRENT_CONFIG = "current deployment config";
    public static final String DEFAULT_CONFIG = "defaultConfiguration";
    public static final String REST_MODE = "REST";
    public static final String REST_SERVER_ADDRESS_PROPERTY = BonitaConstants.REST_SERVER_ADDRESS_PROPERTY ;
    public static final String DEBUG_MODE = "debug";
    public static final String TOGGLE_STATE_FOR_NO_INITIATOR="toggleStateForNoInitiatorWarning";
    public static final String TOGGLE_STATE_FOR_CONTRACT_AND_NOFORM_AND_INITIATOR = "TOGGLE_STATE_FOR_CONTRACT_AND_NOFORM_AND_INITIATOR";
    public static final String DROP_BUSINESS_DATA_DB_ON_EXIT_PREF = "DROP_BUSINESS_DATA_DB_ON_EXIT_PREF";
    public static final String DROP_BUSINESS_DATA_DB_ON_INSTALL = "DROP_BUSINESS_DATA_DB_ON_INSTALL";
    public static final String LAZYLOAD_ENGINE = "LAZYLOAD_ENGINE";
    public static final String TOMCAT_XMX_OPTION = "TOMCAT_XMX_OPTION";
    public static final String TOMCAT_EXTRA_PARAMS = "tomcat.extra.params";

}
