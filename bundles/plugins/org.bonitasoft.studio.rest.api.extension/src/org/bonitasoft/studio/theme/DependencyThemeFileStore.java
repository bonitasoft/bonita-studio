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
package org.bonitasoft.studio.theme;

import java.io.File;

import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.swt.graphics.Image;

public class DependencyThemeFileStore extends ThemeFileStore {

    private Theme theme;

    public DependencyThemeFileStore(Theme theme, ThemeRepositoryStore parentStore) {
        super(new File(theme.getArtifact().getFile()).getName(), parentStore);
        this.theme = theme;
    }
    
    @Override
    protected ThemeExtensionDescriptor doGetContent() throws ReadFileStoreException {
        return new DependencyThemeExtensionDescriptor(theme);
    }
    
    @Override
    public File getArchiveFile() {
        return new File(theme.getArtifact().getFile());
    }
    
    @Override
    public String getDisplayName() {
        return theme.getDisplayName();
    }
    
    @Override
    public String getDescription() {
        return theme.getDescription();
    }
    
    @Override
    public String getName() {
        return getArchiveFile().getName();
    }
    
    @Override
    public boolean isReadOnly() {
        return true;
    }
    
    @Override
    public Image getIcon() {
        return Pics.getImage("binary.png", RestAPIExtensionActivator.getDefault());
    }
}
