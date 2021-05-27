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
package org.bonitasoft.studio.common.repository.migration.transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.repository.core.maven.migration.BonitaJarDependencyReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.DatabaseDriverDependencyReplacement;
import org.bonitasoft.studio.common.repository.migration.ProcessModelTransformation;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.eclipse.emf.ecore.EObject;

public class DatabaseDriverJarReferenceFragmentTransformation implements ProcessModelTransformation {

    private List<EObject> toRemove = new ArrayList<>();

    @Override
    public void transform(EObject modelObject) {
        FragmentContainer fragmentContainer = (FragmentContainer) modelObject;
        if (FragmentTypes.OTHER.equals(fragmentContainer.getId())) {
            fragmentContainer.getFragments().stream().filter(fragment -> BonitaJarDependencyReplacement
                    .getDatabaseDriverDependencyReplacements().stream()
                    .filter(bddDepReplacement -> Objects.equals(fragment.getValue(),
                            bddDepReplacement.getFileName()))
                    .findFirst()
                    .filter(DatabaseDriverDependencyReplacement::isProvidedScope)
                    .isPresent())
            .forEach(toRemove::add);
        }
    }

    @Override
    public boolean appliesTo(EObject modelObject) {
        return modelObject instanceof FragmentContainer;
    }

    @Override
    public List<EObject> markedForRemoval() {
        return toRemove;
    }

    @Override
    public void clear() {
        toRemove.clear();
    }
}
