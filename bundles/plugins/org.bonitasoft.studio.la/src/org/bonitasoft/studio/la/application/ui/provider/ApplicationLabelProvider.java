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
package org.bonitasoft.studio.la.application.ui.provider;

import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
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
            styledString.append(fileStore.getName());
            cell.setText(styledString.getString());
            cell.setImage(getImage(cell.getElement()));
            try {
                if (!fileStore.getContent().getApplications().isEmpty()) {
                    cell.setText(appendAppTokens(fileStore, styledString));
                }
            } catch (ReadFileStoreException e) {
                //Do not display app descriptors
                cell.setImage(new DecorationOverlayIcon(getImage(cell.getElement()),
                        LivingApplicationPlugin.getImageDescriptor("icons/problem.gif"), IDecoration.BOTTOM_RIGHT)
                                .createImage());
            }

            cell.setStyleRanges(styledString.getStyleRanges());
        }
    }

    private String appendAppTokens(final ApplicationFileStore fileStore, final StyledString styledString)
            throws ReadFileStoreException {
        styledString.append("  ");
        fileStore.getContent().getApplications().stream()
                .forEach(
                        app -> styledString.append("../apps/" + app.getToken() + ", ", StyledString.COUNTER_STYLER));
        return styledString.getString().substring(0, styledString.getString().length() - 2);
    }

    /**
     * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
     */
    @Override
    public String getToolTipText(Object element) {
        final ApplicationFileStore fileStore = (ApplicationFileStore) element;
        try {
            fileStore.getContent();
        } catch (ReadFileStoreException e) {
            return Messages.unparsableApplicationFile;
        }
        return super.getToolTipText(element);
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
