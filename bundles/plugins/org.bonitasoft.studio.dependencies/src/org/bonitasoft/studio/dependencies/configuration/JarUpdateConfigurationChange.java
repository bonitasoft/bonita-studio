/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.dependencies.configuration;

import java.util.Collection;
import java.util.Objects;

import org.bonitasoft.studio.model.configuration.Configuration;

public class JarUpdateConfigurationChange implements ProcessConfigurationChange {

    private String previousJarName;
    private String newJarName;
    private Collection<Configuration> configurations;

    public JarUpdateConfigurationChange(String previousJarName, String newJarName,
            Collection<Configuration> configurations) {
        this.previousJarName = previousJarName;
        this.newJarName = newJarName;
        this.configurations = configurations;
    }

    @Override
    public void apply(Configuration configuration) {
        var otherJarFragmentContainer = getOtherJarFragmentContainer(configuration);
        otherJarFragmentContainer.getFragments().stream()
                .filter(f -> Objects.equals(f.getValue(), previousJarName))
                .forEach(toUpdate -> {
                    toUpdate.setKey(newJarName);
                    toUpdate.setValue(newJarName);
                });
    }

    @Override
    public Collection<Configuration> getConfigurations() {
        return configurations;
    }

}
