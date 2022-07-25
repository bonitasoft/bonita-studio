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
package org.bonitasoft.studio.identity.organization.editor.comparator;

import java.util.function.Function;

import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class UserComparator extends ViewerComparator {

    private Function<User, String> toUserDisplayName;

    public UserComparator(Function<User, String> toUserDisplayName) {
        this.toUserDisplayName = toUserDisplayName;
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof User && e2 instanceof User) {
            String user1 = toUserDisplayName.apply((User) e1);
            String user2 = toUserDisplayName.apply((User) e2);
            return user1.compareTo(user2);
        }
        return super.compare(viewer, e1, e2);
    }

}
