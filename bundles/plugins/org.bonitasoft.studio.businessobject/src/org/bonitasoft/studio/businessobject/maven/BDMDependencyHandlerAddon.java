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
package org.bonitasoft.studio.businessobject.maven;

import javax.annotation.PostConstruct;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.e4.core.services.events.IEventBroker;

public class BDMDependencyHandlerAddon {

    private static final String BDM_DEPLOYED_TOPIC = "bdm/deployed";

    @PostConstruct
    public void registerHandler(IEventBroker eventBroker, RepositoryAccessor repositoryAccessor) {
        eventBroker.subscribe(BDM_DEPLOYED_TOPIC, new InstallBDMDependenciesEventHandler());
    }

}
