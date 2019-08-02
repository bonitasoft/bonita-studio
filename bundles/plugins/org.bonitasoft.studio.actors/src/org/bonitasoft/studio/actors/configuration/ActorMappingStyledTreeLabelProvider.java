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
package org.bonitasoft.studio.actors.configuration;

import java.util.Optional;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;

/**
 * @author Romain Bioteau
 */
public class ActorMappingStyledTreeLabelProvider extends StyledCellLabelProvider {

    private ActorMappingLabelProvider labelProvider;

    public ActorMappingStyledTreeLabelProvider(ComposedAdapterFactory adapterFactory,
            Optional<Organization> deployedOrganization) {
        labelProvider = new ActorMappingLabelProvider(new AdapterFactoryLabelProvider(adapterFactory), deployedOrganization);
    }

    @Override
    public String getToolTipText(Object element) {
        return null;//return null to set back the Tooltip to standard behavior
    }

    @Override
    public void update(ViewerCell cell) {
        final Object cellElement = cell.getElement();
        if (cellElement instanceof ActorMapping) {
            ActorMapping actor = (ActorMapping) cellElement;
            StyledString styledString = new StyledString();
            styledString.append(actor.getName(), null);
            if (isNotDefined(actor)) {
                styledString.append(" -- ");
                styledString.append(Messages.notMappedActors, StyledString.DECORATIONS_STYLER);
            }
            cell.setText(styledString.getString());
            cell.setImage(null);
            cell.setStyleRanges(styledString.getStyleRanges());
        } else if (cellElement != null) {
            cell.setText(labelProvider.getText(cellElement));
            cell.setImage(labelProvider.getImage(cellElement));
            cell.setForeground(labelProvider.getForeground(cellElement));
        }
    }

    private boolean isNotDefined(ActorMapping actor) {
        return actor.getGroups().getGroup().isEmpty() &&
                actor.getMemberships().getMembership().isEmpty() &&
                actor.getRoles().getRole().isEmpty() &&
                actor.getUsers().getUser().isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.StyledCellLabelProvider#dispose()
     */
    @Override
    public void dispose() {
        labelProvider.dispose();
        super.dispose();
    }

}
