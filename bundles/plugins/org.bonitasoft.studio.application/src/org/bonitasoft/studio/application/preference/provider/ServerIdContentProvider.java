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
import java.util.stream.Stream;

import org.apache.maven.settings.Mirror;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.Settings;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;

public class ServerIdContentProvider {

    private IObservableList<Profile> profilesObservable;
    private IObservableList<Mirror> mirrorsObservable;

    public ServerIdContentProvider(IObservableValue<Settings> settingsObservable) {
        profilesObservable = PojoProperties.list(Settings.class, "profiles", Profile.class)
                .observeDetail(settingsObservable);
        mirrorsObservable = PojoProperties.list(Settings.class, "mirrors", Mirror.class)
                .observeDetail(settingsObservable);
    }

    public String[] toArray() {
        return Stream.concat(profilesObservable.stream()
                .map(Profile::getRepositories)
                .flatMap(Collection::stream)
                .map(Repository::getId),
                mirrorsObservable.stream()
                        .map(Mirror::getId))
                .toArray(String[]::new);
    }

}
