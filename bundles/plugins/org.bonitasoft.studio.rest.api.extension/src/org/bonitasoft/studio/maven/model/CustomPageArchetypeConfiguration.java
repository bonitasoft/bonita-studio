/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven.model;

import java.util.Properties;

import org.apache.maven.model.Model;

public abstract class CustomPageArchetypeConfiguration {

    private String pageName;

    private String pageDisplayName;

    private String pageDescription;
    
    private String groupId;
    
    private String version;
    
    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageDisplayName() {
        return pageDisplayName;
    }

    public void setPageDisplayName(String pageDisplayName) {
        this.pageDisplayName = pageDisplayName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Model toModel() {
        final Model model = new Model();
        model.setArtifactId(getPageName());
        model.setGroupId(getGroupId());
        model.setVersion(getVersion());
        return model;
    }

    public abstract Properties toProperties();
    
    public abstract String getArtifactLabel() ;
}
