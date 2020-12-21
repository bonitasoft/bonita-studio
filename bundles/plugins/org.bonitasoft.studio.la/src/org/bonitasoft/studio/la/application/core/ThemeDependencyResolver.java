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
package org.bonitasoft.studio.la.application.core;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageDescriptor;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageProvider;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.theme.ThemeFileStore;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;

public class ThemeDependencyResolver {

    private final ThemeRepositoryStore store;
    private final PageAPI pageApi;

    public ThemeDependencyResolver(ThemeRepositoryStore store,
            PageAPI pageApi) {
        this.store = store;
        this.pageApi = pageApi;
    }

    public Stream<IRunnableWithStatus> prepareDeployOperation(Stream<String> themes) {
        return themes
                .distinct()
                .filter(pageId -> CustomPageProvider.DEFAULT_THEMES.stream()
                        .map(CustomPageDescriptor::getId)
                        .noneMatch(providedThemeId -> Objects.equals(providedThemeId, pageId)))
                .map(this::newDeployPageOperation);
    }

    private IRunnableWithStatus newDeployPageOperation(String pageId) {
        final Optional<ThemeFileStore> fStore = store.findByCustomPageId(pageId);
        if (fStore.isPresent()) {
            return new DeployThemeOperation(pageApi,
                    new HttpClientFactory(),
                    fStore.get());
        }
        return new UnknownDeployStatus(String.format(Messages.unknownPageDeployStatus, pageId));
    }

}
