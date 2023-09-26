/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control.model.dependency;

/**
 * This class is used to know the different status of a {@link BonitaArtifactDependency} in the marketplace of bonita-studio.
 * A {@link BonitaMarketPlaceItem} is always created with a {@link BonitaArtifactDependency}. It can be installed, updatable, added or a new extension.
 */
public class BonitaMarketPlaceItem {

    /**
     * The {@link BonitaArtifactDependency} linked to this object.
     */
    private BonitaArtifactDependency bonitaArtifactDependency;

    /**
     * Allow to know if this dependency is already present/installed in the marketplace.
     */
    private boolean installed;
    
    private boolean installable = true;

    /**
     * Allow to know if this dependency is selected/added to be installed (with the wizard) in the marketplace.
     */
    private boolean selected;

    /**
     * Allow to know if this dependency can be updated in the marketplace.
     */
    private boolean updatable;

    public BonitaMarketPlaceItem(BonitaArtifactDependency dependency) {
        this.bonitaArtifactDependency = dependency;
    }

    /**
     * @return the bonitaArtifactDependency
     */
    public BonitaArtifactDependency getBonitaArtifactDependency() {
        return bonitaArtifactDependency;
    }

    /**
     * @param bonitaArtifactDependency the bonitaArtifactDependency to set
     */
    public void setBonitaArtifactDependency(BonitaArtifactDependency bonitaArtifactDependency) {
        this.bonitaArtifactDependency = bonitaArtifactDependency;
    }

    /**
     * @return the installed
     */
    public boolean isInstalled() {
        return installed;
    }

    /**
     * @param installed the installed to set
     */
    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the updatable
     */
    public boolean isUpdatable() {
        return updatable;
    }

    /**
     * @param updatable the updatable to set
     */
    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public boolean isInstallable() {
        return installable;
    }
    
    public void setInstallable(boolean installable) {
        this.installable = installable;
    }
}
