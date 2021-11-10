/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.core;

import static com.google.common.base.Preconditions.checkArgument;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.bonitasoft.studio.common.repository.AbstractRepository;

import com.google.common.base.Joiner;

public class WorkspaceSystemProperties {

    private static final String UID_WORKSPACE_PATH_PROPERTIES = "designer.workspace.path";
    private static final String WORKSPACE_API_REST_URL = "designer.workspace.apiUrl";
    private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

    private AbstractRepository repository;

    public WorkspaceSystemProperties(final AbstractRepository repository) {
        this.repository = repository;
    }

    public String getRestAPIURL(final int port) throws UnknownHostException {
        return aSystemProperty(WORKSPACE_API_REST_URL, String.format("http://%s:%s/api/workspace", InetAddress.getByName(null).getHostAddress(), port));
    }
    
    public String getWorspacePathLocation() {
        return aSystemProperty(UID_WORKSPACE_PATH_PROPERTIES, "\"" + repository.getProject().getLocation().toFile().toURI() + "\"");
    }

    public String activateSpringProfile(final String... profiles) {
        checkArgument(profiles != null && profiles.length > 0);
        return aSystemProperty(SPRING_PROFILES_ACTIVE, Joiner.on(",").skipNulls().join(profiles));
    }

    static String aSystemProperty(final String propertyName, final Object value) {
        return "-D" + propertyName + "=" + value;
    }

}
