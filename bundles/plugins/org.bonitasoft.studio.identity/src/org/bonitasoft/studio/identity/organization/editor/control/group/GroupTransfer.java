/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.control.group;

import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.eclipse.gef.dnd.SimpleObjectTransfer;

public class GroupTransfer extends SimpleObjectTransfer {

    private static GroupTransfer INSTANCE = new GroupTransfer();

    public static GroupTransfer getInstance() {
        return INSTANCE;
    }

    private static final String ID_NAME = Group.class.getName();
    private static final int ID = registerType(ID_NAME);

    @Override
    protected int[] getTypeIds() {
        return new int[] { ID };
    }

    @Override
    protected String[] getTypeNames() {
        return new String[] { ID_NAME };
    }

    @Override
    protected boolean validate(Object object) {
        return checkBusinessObject(object);
    }

    private boolean checkBusinessObject(Object object) {
        return object != null && object instanceof Group;
    }

}
