/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import org.apache.maven.model.Model;

public interface MavenModelBuilder {
   
    Model toMavenModel();

    void setDisplayName(String name);

    void setArtifactId(String artifactId);

    void setGroupId(String groupId);

    void setBonitaVersion(String bonitaRuntimeVersion);

    void setDescription(String description);

    void setVersion(String version);

    void setIncludeAdminApp(boolean includeAdminApp);
    
}
