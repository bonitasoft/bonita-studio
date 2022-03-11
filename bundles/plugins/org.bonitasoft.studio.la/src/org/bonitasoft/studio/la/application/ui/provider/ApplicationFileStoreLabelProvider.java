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

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.ui.UIPlugin;
import org.bonitasoft.studio.ui.provider.FileStoreLabelProvider;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;

public class ApplicationFileStoreLabelProvider extends FileStoreLabelProvider {

    @Override
    protected void contentValidation(IRepositoryFileStore fileStore, StyledString styledString, ViewerCell cell) {
        if (fileStore instanceof ApplicationFileStore) {
            try {
                ApplicationFileStore applicationFileStore = (ApplicationFileStore) fileStore;
                if (!applicationFileStore.getContent().getApplications().isEmpty()) {
                    cell.setText(appendAppTokens(applicationFileStore, styledString));
                }
            } catch (ReadFileStoreException e) {
                //Do not display app descriptors
                applyUnparsableFileStyle(cell);
            }
        }
    }

    protected void applyUnparsableFileStyle(ViewerCell cell) {
        cell.setImage(new DecorationOverlayIcon(getImage(cell.getElement()),
                UIPlugin.getImageDescriptor("icons/problem.gif"), IDecoration.BOTTOM_RIGHT)
                        .createImage());
    }

    private String appendAppTokens(final ApplicationFileStore fileStore, final StyledString styledString)
            throws ReadFileStoreException {
        styledString.append("  ");
        List<ApplicationNode> applications = fileStore.getContent().getApplications();
        styledString.append(
                applications.stream()
                        .map(application -> "../apps/" + application.getToken())
                        .collect(Collectors.joining(", ")),
                StyledString.COUNTER_STYLER);
        return styledString.getString();
    }

}
