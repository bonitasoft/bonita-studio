/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.theme;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.archetype.catalog.Archetype;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.builder.validator.AbstractCustomPageValidator;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.ThemeArchetype;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.theme.builder.ThemeProjectBuilder;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.graphics.Image;

public class ThemeRepositoryStore extends CustomPageProjectRepositoryStore<ThemeFileStore> {

    public static final String STORE_NAME = "themes";

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.themesRepositoryName;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("theme.png", RestAPIExtensionActivator.getDefault());
    }

    @Override
    public ThemeFileStore createRepositoryFileStore(final String themeName) {
        return new ThemeFileStore(themeName, this);
    }

    @Override
    public Archetype getArchetype() {
        return ThemeArchetype.INSTANCE;
    }

    @Override
    public void refreshMarkers() throws CoreException {
        List<ThemeFileStore> children = getChildren();
        ThemeProjectBuilder builder = new ThemeProjectBuilder();
        for (CustomPageProjectFileStore fileStore : children) {
            for (AbstractCustomPageValidator validator : builder.createValidators(fileStore.getProject(),
                    new NullProgressMonitor())) {
                validator.acceptAndValidate();
            }
        }
    }

    @Override
    protected void refresh(final IFolder folder) {
        if (!folder.isSynchronized(IResource.DEPTH_ONE)) {
            try {
                folder.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    public Optional<ThemeFileStore> findTheme(String customPageName) {
        return getChildren().stream()
                .filter(fStore -> Objects.equals(customPageName, fStore.getPageId()))
                .findFirst();
    }

}
