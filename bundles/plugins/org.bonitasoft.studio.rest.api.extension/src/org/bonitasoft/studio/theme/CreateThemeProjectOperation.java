/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.theme;

import java.util.Set;

import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.model.CustomPageArchetypeConfiguration;
import org.bonitasoft.studio.maven.operation.CreateCustomPageProjectOperation;
import org.bonitasoft.studio.theme.builder.ThemeProjectBuilder;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;


public class CreateThemeProjectOperation extends CreateCustomPageProjectOperation {

    public CreateThemeProjectOperation(CustomPageProjectRepositoryStore<?> repositoryStore,
            IProjectConfigurationManager projectConfigurationManager,
            ProjectImportConfiguration projectImportConfiguration,
            CustomPageArchetypeConfiguration archetypeConfiguration) {
        super(repositoryStore, projectConfigurationManager, projectImportConfiguration, archetypeConfiguration);
    }
    
    @Override
    protected String getPagePropertyPath() {
        return "page.properties";
    }
    
    @Override
    protected Set<String> projectBuilders(IProjectDescription description) {
         Set<String> projectBuilders = super.projectBuilders(description);
         projectBuilders.add(ThemeProjectBuilder.BUILDER_ID);
         return projectBuilders;
    }

}
