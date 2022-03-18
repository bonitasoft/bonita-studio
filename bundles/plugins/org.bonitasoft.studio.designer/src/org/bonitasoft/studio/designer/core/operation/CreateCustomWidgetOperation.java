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

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.repository.WebWidgetFileStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.core.runtime.IProgressMonitor;

public class CreateCustomWidgetOperation extends CreateUIDArtifactOperation {

    public CreateCustomWidgetOperation(PageDesignerURLFactory pageDesignerURLBuilder,
            RepositoryAccessor repositoryAccessor) {
        super(pageDesignerURLBuilder, repositoryAccessor);
    }

    @Override
    protected void doRun(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Map<String, Object> bodyObject = createBody();
        try {
            responseObject = createArtifact(pageDesignerURLBuilder.newWidget(), bodyObject);
        } catch (MalformedURLException e) {
            throw new InvocationTargetException(e, "Failed to create new widget URL.");
        }
        openArtifact(getNewArtifactId());
    }

    @Override
    protected String getTaskName() {
        return Messages.creatingNewWidget;
    }

    @Override
    protected ArtifactyType getArtifactType() {
        return ArtifactyType.WIDGET;
    }

    @Override
    protected Map<String, Object> createBody() {
        try (InputStream inputStream = UIDesignerPlugin.getDefault().getBundle()
                .getResource("/resources/CustomWidgetTemplate.json")
                .openStream()) {
            if (inputStream == null) {
                throw new IOException("Failed to retrieve CustomWidgetTemplate.json");
            }
            Map body = objectMapper.readValue(inputStream, Map.class);
            body.put("name", getNewName());
            return body;
        } catch (IOException e) {
            throw new RuntimeException("An error occured while retrieving custom widget template", e);
        }
    }

    private String getNewName() {
        List<String> existingWidgets = repositoryAccessor.getRepositoryStore(WebWidgetRepositoryStore.class)
                .getChildren()
                .stream()
                .map(WebWidgetFileStore::getDisplayName)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(DEFAULT_WIDGET_NAME, existingWidgets);
    }

}
