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
package org.bonitasoft.studio.application.ui.control.model.dependency;

import org.bonitasoft.studio.common.repository.Messages;

public enum ArtifactType {

        CONNECTOR(Messages.connector), 
        ACTOR_FILTER(Messages.actorFilter), 
        THEME( Messages.theme), 
        PAGE(Messages.page), 
        FORM(Messages.form), 
        REST_API(Messages.restApi), 
        UNKNOWN(Messages.unknown);

        private String name;

        ArtifactType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

}
