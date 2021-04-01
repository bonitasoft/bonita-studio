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
package org.bonitasoft.studio.application.preference.provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.settings.Mirror;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.Settings;
import org.eclipse.jface.viewers.IStructuredContentProvider;

public class ServerIdContentProvider implements IStructuredContentProvider {

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof Settings) {
            Settings settings = (Settings) inputElement;
            List<String> ids = settings.getProfiles().stream()
                    .map(Profile::getRepositories)
                    .flatMap(Collection::stream)
                    .map(Repository::getId)
                    .collect(Collectors.toList());
            settings.getMirrors().stream()
                    .map(Mirror::getId)
                    .forEach(ids::add);
            return ids.toArray();
        }
        return null;
    }

}
