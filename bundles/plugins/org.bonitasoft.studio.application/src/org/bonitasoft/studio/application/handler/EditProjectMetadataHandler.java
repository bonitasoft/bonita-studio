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
package org.bonitasoft.studio.application.handler;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.SetProjectMetadataOperation;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;

public class EditProjectMetadataHandler extends AbstractProjectMetadataHandler {

    protected String getWizardDescription() {
        return Messages.editProjectMetadataDescription;
    }

    protected String getWizardTitle() {
        return Messages.editProjectMetadata;
    }
    
    @Override
    public String getFinishLabel() {
        return Messages.modify;
    }

    protected SetProjectMetadataOperation createOperation(MavenProjectHelper mavenProjectHelper,
            ProjectMetadata metadata, RepositoryAccessor repositoryAccessor) {
        return new SetProjectMetadataOperation(metadata, repositoryAccessor, mavenProjectHelper);
    }

    @Override
    protected boolean isNewProject() {
        return false;
    }

    @Override
    protected ProjectMetadata initialMetadata(AbstractRepository currentRepository) {
        return ProjectMetadata.read(currentRepository.getProject());
    }

}
