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
package org.bonitasoft.studio.la.ui.providers;

import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

public class ApplicationLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    @Override
    public void update(ViewerCell cell) {
        if (cell.getElement() instanceof ApplicationFileStore) {
            final ApplicationFileStore fileStore = (ApplicationFileStore) cell.getElement();
            final StyledString styledString = new StyledString();
            styledString.append(fileStore.getDisplayName());
            styledString.append("  ../apps/" + fileStore.getDisplayName(), StyledString.COUNTER_STYLER);

            cell.setText(styledString.getString());
            cell.setImage(getImage(cell.getElement()));
            cell.setStyleRanges(styledString.getStyleRanges());
        }
    }

    @Override
    public Image getImage(Object element) {
        return ((ApplicationFileStore) element).getIcon();
    }

    @Override
    public String getText(Object element) {
        return ((ApplicationFileStore) element).getDisplayName();
    }

}
