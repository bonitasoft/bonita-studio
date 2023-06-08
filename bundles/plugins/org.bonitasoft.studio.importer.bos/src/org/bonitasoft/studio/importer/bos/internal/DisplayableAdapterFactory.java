/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bos.internal;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractImportModel;
import org.bonitasoft.studio.importer.bos.model.ImportFileModel;
import org.bonitasoft.studio.importer.bos.model.ImportFolderFileStoreModel;
import org.bonitasoft.studio.importer.bos.model.ImportFolderModel;
import org.bonitasoft.studio.importer.bos.model.ImportStoreModel;
import org.bonitasoft.studio.importer.bos.model.LegacyRestAPIExtensionsImportStoreModel;
import org.bonitasoft.studio.importer.bos.model.LegacyStoreModel;
import org.bonitasoft.studio.importer.bos.model.LegacyThemesImportStoreModel;
import org.bonitasoft.studio.importer.bos.model.RootFileModel;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Adapts {@link AbstractImportModel} to {@link IDisplayable}
 * 
 * @author Vincent Hemery
 */
public class DisplayableAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adaptableObject instanceof AbstractImportModel && adapterType.isAssignableFrom(adapterType)) {
            // adapt to IDisplayable
            AbstractImportModel importModel = (AbstractImportModel) adaptableObject;
            return (T) new IDisplayable() {

                @Override
                public String getDisplayName() {
                    return importModel.getText();
                }

                @Override
                public Image getIcon() {
                    return getIconFor(importModel);
                }
            };
        }
        return null;
    }

    /**
     * Get appropriate icon
     * 
     * @param importModel the import model to get icon
     * @return icon image
     */
    private Image getIconFor(AbstractImportModel importModel) {
        if (importModel instanceof AbstractFileModel) {
            if (importModel instanceof ImportFileModel) {
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
            } else {
                Optional<IRepositoryStore> store = Optional.ofNullable(importModel.getAdapter(IRepositoryStore.class));
                Optional<IDisplayable> display = store.flatMap(IDisplayable::adapt);
                return display.map(IDisplayable::getIcon).orElse(null);
            }
        } else if (importModel instanceof ImportFolderModel) {
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
        }else if(importModel instanceof LegacyThemesImportStoreModel) {
            return Pics.getImage(PicsConstants.themeDefaultIcon);
        }else if(importModel instanceof LegacyRestAPIExtensionsImportStoreModel) {
            return Pics.getImage(PicsConstants.restApi);
        } else if (importModel instanceof ImportFolderFileStoreModel) {
            Optional<IRepositoryStore> store = Optional.ofNullable(importModel.getAdapter(IRepositoryStore.class));
            Optional<IDisplayable> display = store.flatMap(IDisplayable::adapt);
            return display.map(IDisplayable::getIcon)
                    .orElse(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER));
        } else if (importModel instanceof ImportStoreModel) {
            if (importModel instanceof LegacyStoreModel) {
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
            } else if (importModel instanceof RootFileModel) {
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
            } else {
                Optional<IRepositoryStore> store = Optional
                        .ofNullable(((ImportStoreModel) importModel).getRepositoryStore());
                Optional<IDisplayable> display = store.flatMap(IDisplayable::adapt);
                return display.map(IDisplayable::getIcon).orElse(null);
            }
        } else {
            return null;
        }
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class<?>[] { IDisplayable.class };
    }

}
