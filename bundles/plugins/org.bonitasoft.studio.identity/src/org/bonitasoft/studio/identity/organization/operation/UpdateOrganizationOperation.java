/**
 * Copyright (C) 2016 BonitaSoft S.A.
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
package org.bonitasoft.studio.identity.organization.operation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.identity.ImportPolicy;
import org.bonitasoft.engine.identity.OrganizationImportException;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;

public class UpdateOrganizationOperation extends PublishOrganizationOperation {

    public UpdateOrganizationOperation(Organization organization) {
        super(organization);
    }
    
    @Override
    protected void importOrganization(IdentityAPI identityAPI) throws IOException, OrganizationImportException {
    	identityAPI.importOrganizationWithWarnings(Files.readString(Paths.get(organization.eResource().getURI().toFileString())), ImportPolicy.MERGE_DUPLICATES);
    }

}
