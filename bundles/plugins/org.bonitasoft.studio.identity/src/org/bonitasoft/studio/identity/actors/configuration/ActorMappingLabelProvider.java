/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.actors.configuration;

import java.util.Optional;

import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ActorMappingLabelProvider extends ColumnLabelProvider {

    private Optional<Organization> deployedOrganization;
    private Color errorColor;
    private AdapterFactoryLabelProvider adapterFactory;

    public ActorMappingLabelProvider(AdapterFactoryLabelProvider adapterFactory,
            Optional<Organization> deployedOrganization) {
        this.adapterFactory = adapterFactory;
        this.deployedOrganization = deployedOrganization;
        this.errorColor = new Color(Display.getDefault(), ColorConstants.ERROR_RGB);
    }

    private boolean isPresentInDeployedOrganization(Object element) {
        if (element instanceof MembershipType) {
            return isPresentInDeployedOrganization(((MembershipType) element).getGroup())
                    && isPresentInDeployedOrganization(((MembershipType) element).getRole());
        }
        String elt = (String) element;
        return deployedOrganization.isPresent()
                && (deployedOrganization.get().getGroups().getGroup().stream().map(this::getGroupPath).anyMatch(elt::equals)
                        || deployedOrganization.get().getRoles().getRole().stream().map(Role::getName).anyMatch(elt::equals)
                        || deployedOrganization.get().getUsers().getUser().stream().map(User::getUserName)
                                .anyMatch(elt::equals));
    }

    private String getGroupPath(Group group) {
        return String.format("%s/%s", group.getParentPath() == null ? "" : group.getParentPath(), group.getName());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(Object element) {
        return adapterFactory.getText(element);
    }

    @Override
    public Color getForeground(Object object) {
        return (object instanceof String || object instanceof MembershipType)
                ? isPresentInDeployedOrganization(object)
                        ? adapterFactory.getForeground(object)
                        : errorColor
                : adapterFactory.getForeground(object);
    }

    @Override
    public Image getImage(Object object) {
        if (object instanceof String || object instanceof MembershipType) {
            return null;
        }
        return adapterFactory.getImage(object);
    }

    @Override
    public void dispose() {
        adapterFactory.dispose();
        errorColor.dispose();
        super.dispose();
    }

}
