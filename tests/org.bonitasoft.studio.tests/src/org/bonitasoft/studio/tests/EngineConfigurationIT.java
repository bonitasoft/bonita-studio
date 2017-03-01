/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.bonitasoft.engine.api.PlatformAPI;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.InvalidPlatformCredentialsException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.platform.PlatformLoginException;
import org.bonitasoft.engine.platform.PlatformLogoutException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.PlatformSession;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class EngineConfigurationIT {

    private APISession session;
    private PlatformSession platformSession;

    @Before
    public void setUp() throws LoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException,
            InvalidPlatformCredentialsException,
            PlatformLoginException {
        session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
        platformSession = BOSEngineManager.getInstance().loginPlatform();
    }

    @After
    public void tearDown() throws PlatformLogoutException, SessionNotFoundException, BonitaHomeNotSetException,
            ServerAPIException, UnknownAPITypeException {
        BOSEngineManager.getInstance().logoutDefaultTenant(session);
        BOSEngineManager.getInstance().logoutPlatform(platformSession);
    }

    @Test
    public void should_enable_debug_mode() throws Exception {
        final PlatformSession platformSession = BOSEngineManager.getInstance().loginPlatform();
        final PlatformAPI platformAPI = BOSEngineManager.getInstance().getPlatformAPI(platformSession);
        final Map<Long, Map<String, byte[]>> clientPlatformConfigurations = platformAPI.getClientTenantConfigurations();
        final byte[] content = clientPlatformConfigurations.get(1L).get("security-config.properties");

        assertThat(readProperties(content)).contains(entry("security.rest.api.authorizations.check.debug", "true"));
    }

    protected Properties readProperties(final byte[] content) throws IOException {
        final Properties properties = new Properties();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content);
        properties.load(byteArrayInputStream);
        byteArrayInputStream.close();
        return properties;
    }

}
