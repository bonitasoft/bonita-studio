/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.operation;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.core.runtime.IProgressMonitor;

public class CreateLayoutOperation extends CreateUIDArtifactOperation {

    public CreateLayoutOperation(PageDesignerURLFactory pageDesignerURLBuilder, RepositoryAccessor repositoryAccessor) {
        super(pageDesignerURLBuilder, repositoryAccessor);
    }

    @Override
    protected void doRun(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.creatingNewLayout, IProgressMonitor.UNKNOWN);
        setArtifactName(getNewName());
        try {
            responseObject = createArtifact(pageDesignerURLBuilder.newPage(), createBody());
        } catch (MalformedURLException e) {
            throw new InvocationTargetException(e, "Failed to create new page URL.");
        }
        openArtifact(getNewArtifactId());
    }
    
    @Override
    protected String getTaskName() {
        return Messages.creatingNewLayout;
    }

    @Override
    protected ArtifactyType getArtifactType() {
        return ArtifactyType.LAYOUT;
    }

    private String getNewName() {
        List<String> existingLayouts = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChildren()
                .stream()
                .filter(store -> Objects.equals(store.getType(), "layout"))
                .map(WebPageFileStore::getCustomPageName)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(DEFAULT_LAYOUT_NAME, existingLayouts);
    }

}
