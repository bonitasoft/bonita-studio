/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.editor.customPage;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.la.application.ui.provider.ProfileProposalProvider;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;

public class ApplicationEditorProviders {

    protected CustomPageProvider customPageProvider;
    private LayoutProposalProvider layoutProposalProvider;
    private ThemeProposalProvider themeProposalProvider;
    protected ProfileProposalProvider profileProposalProvider;

    public ApplicationEditorProviders(RepositoryAccessor repositoryAccessor) {
        this.customPageProvider = new CustomPageProvider(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class),
                repositoryAccessor.getRepositoryStore(ThemeRepositoryStore.class));
        this.layoutProposalProvider = new LayoutProposalProvider(customPageProvider, true);
        this.themeProposalProvider = new ThemeProposalProvider(customPageProvider, true);
        this.profileProposalProvider = createProfileProposalProvider(repositoryAccessor);
        profileProposalProvider.setFiltering(true);
    }

    protected ProfileProposalProvider createProfileProposalProvider(RepositoryAccessor repositoryAccessor) {
        return new ProfileProposalProvider(repositoryAccessor);
    }

    public CustomPageProvider getCustomPageProvider() {
        return customPageProvider;
    }

    public LayoutProposalProvider getLayoutProposalProvider() {
        return layoutProposalProvider;
    }

    public ThemeProposalProvider getThemeProposalProvider() {
        return themeProposalProvider;
    }

    public ProfileProposalProvider getProfileProposalProvider() {
        return profileProposalProvider;
    }

    public void dispose() {
        layoutProposalProvider.dispose();
        themeProposalProvider.dispose();
    }
}
