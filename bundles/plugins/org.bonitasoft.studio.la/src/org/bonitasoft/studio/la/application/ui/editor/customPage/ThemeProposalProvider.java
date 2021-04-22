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
package org.bonitasoft.studio.la.application.ui.editor.customPage;

import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;

public class ThemeProposalProvider extends CustomPageProposalProvider {

    public ThemeProposalProvider(CustomPageProvider provider, boolean filterProposals) {
        super(provider, filterProposals);
    }

    @Override
    protected Collection<CustomPageDescriptor> getCustomPageDescriptors(CustomPageProvider provider) {
        return provider.getThemes();
    }

    public Collection<CustomPageDescriptor> getThemes() {
        return proposals;
    }

    @Override
    protected boolean shouldUpdateProposals(IResourceDelta delta) {
        IResource resource = delta.getResource();
        return ("page.properties".equals(resource.getName()) || "pom.xml".equals(resource.getName()))
                && provider.getThemeStore().getResource().getLocation().isPrefixOf(resource.getLocation());
    }

}
