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
package org.bonitasoft.studio.ui.provider;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

public class FileStoreLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    @Override
    public void update(ViewerCell cell) {
        if (cell.getElement() instanceof IRepositoryFileStore) {
            final IRepositoryFileStore fileStore = (IRepositoryFileStore) cell.getElement();
            final StyledString styledString = new StyledString();
            styledString.append(fileStore.getName());
            cell.setText(styledString.getString());
            cell.setImage(getImage(cell.getElement()));
            contentValidation(fileStore, styledString, cell);

            cell.setStyleRanges(styledString.getStyleRanges());
        }
    }

    protected void contentValidation(IRepositoryFileStore fileStore, StyledString styledString, ViewerCell cell) {
        // Default implementation doesn't perform any content validation.
    };

    @Override
    public String getToolTipText(Object element) {
        final IRepositoryFileStore fileStore = (IRepositoryFileStore) element;
        try {
            fileStore.getContent();
        } catch (ReadFileStoreException e) {
            return Messages.unparsableFile;
        }
        return super.getToolTipText(element);
    }

    @Override
    public Image getImage(Object element) {
        return ((IRepositoryFileStore) element).getIcon();
    }

    @Override
    public String getText(Object element) {
        return ((IRepositoryFileStore) element).getDisplayName();
    }

}
