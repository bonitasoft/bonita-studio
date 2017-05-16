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
package org.bonitasoft.studio.la.profile.ui.provider;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.profile.repository.ApplicationProfileFileStore;
import org.bonitasoft.studio.la.ui.provider.FileStoreLabelProvider;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;

public class ProfileFileStoreLabelProvider extends FileStoreLabelProvider {

    @Override
    protected void contentValidation(IRepositoryFileStore fileStore, StyledString styledString, ViewerCell cell) {
        if (fileStore instanceof ApplicationProfileFileStore) {
            try {
                ((ApplicationProfileFileStore) fileStore).getContent().getProfiles();
            } catch (ReadFileStoreException e) {
                cell.setImage(new DecorationOverlayIcon(getImage(cell.getElement()),
                        LivingApplicationPlugin.getImageDescriptor("icons/problem.gif"), IDecoration.BOTTOM_RIGHT)
                                .createImage());
            }
        }
    }

}
