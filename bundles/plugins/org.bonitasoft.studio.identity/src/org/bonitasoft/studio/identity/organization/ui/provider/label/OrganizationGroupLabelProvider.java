/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.identity.organization.ui.provider.label;

import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.ui.provider.content.GroupContentProvider;
import org.eclipse.jface.viewers.LabelProvider;


/**
 * @author Romain Bioteau
 *
 */
public class OrganizationGroupLabelProvider extends LabelProvider {

    protected static final int MAX_GROUP_PATH_LENGTH = 30;

    @Override
    public String getText(final Object element) {
        if (element instanceof Group) {
            final String groupPath = GroupContentProvider.getGroupPath((Group) element);
            if (groupPath.length() > MAX_GROUP_PATH_LENGTH) {
                return org.apache.commons.lang.StringUtils.abbreviateMiddle(groupPath, "...", 30);
            }
            return groupPath;
        }
        return super.getText(element);
    }

}
