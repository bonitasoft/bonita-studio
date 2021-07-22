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
package org.bonitasoft.studio.application.views.extension.card;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.eclipse.swt.widgets.Composite;

public class ExtensionCardFactory {

    private ExtensionCardFactory() {

    }

    public static ExtensionCard createExtensionCard(Composite parent,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        switch (bonitaDep.getArtifactType()) {
            case CONNECTOR:
                return new ConnectorExtensionCard(parent, dep, bonitaDep);
            case ACTOR_FILTER:
                return new ActorFilterExtensionCard(parent, dep, bonitaDep);
            case REST_API:
                return new RestApiExtensionCard(parent, dep, bonitaDep);
            default:
                return new ExtensionCard(parent, dep, bonitaDep);
        }
    }

}
