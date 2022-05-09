/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.ui.property.section.control;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProvider;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProviderAdapterFactory;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebPageNameResourceChangeListener implements IResourceChangeListener {

    private MainProcess mainProcess;
    private RepositoryAccessor repositoryAccessor;
    private ExpressionItemProvider expressionItemProvider;
    private ObjectMapper objectMapper = new ObjectMapper();

    public WebPageNameResourceChangeListener(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        expressionItemProvider = new ExpressionItemProvider(new ExpressionItemProviderAdapterFactory());
    }

    @Override
    public void resourceChanged(final IResourceChangeEvent event) {
        if (mainProcess != null && event.getDelta() != null) {
            try {
                event.getDelta().accept(resourceDeltaVisitor());
            } catch (final CoreException e) {
                BonitaStudioLog.error("Failed to update form reference name", e);
            }
        }
    }

    private IResourceDeltaVisitor resourceDeltaVisitor() {
        return delta -> {
            String name = delta.getResource().getName();
            if(!repositoryAccessor.hasActiveRepository() || !repositoryAccessor.getCurrentRepository().isLoaded()) {
                return true;
            }
            WebPageRepositoryStore repositoryStore = repositoryAccessor
                    .getRepositoryStore(WebPageRepositoryStore.class);
            if ((delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.CHANGED)
                    && repositoryStore.getResource().getLocation().isPrefixOf(delta.getResource().getLocation())
                    && delta.getResource() instanceof IFolder
                    && delta.getResource().isSynchronized(IResource.DEPTH_INFINITE)
                    && repositoryStore.getChild(name, false) != null) {
                WebPageFileStore pageFileStore = repositoryStore.getChild(name, false);
                IFile indexJsonFile = retrieveIndexJsonFile(repositoryStore);
                if (indexJsonFile.exists()) {
                    try (var is = indexJsonFile.getContents()){
                        Map<String, Object> jsonObject = objectMapper.readValue(is, new TypeReference<Map<String, Object>>() {
                        });
                        updateMatchingFormMapping(mainProcess, jsonObject, pageFileStore.getDisplayName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return true;
        };
    }

    private IFile retrieveIndexJsonFile(WebPageRepositoryStore repositoryStore) {
        return repositoryStore.getResource()
                .getFile(".metadata/.index.json");
    }

    private void updateMatchingFormMapping(MainProcess container, Map<String, Object> jsonObject, String name) {
        ModelHelper.getAllElementOfTypeIn(container, FormMapping.class)
                .stream()
                .map(FormMapping::getTargetForm)
                .filter(expression -> jsonObject.containsKey(expression.getContent()))
                .filter(expression -> Objects.equals(jsonObject.get(expression.getContent()), name))
                .filter(expression -> !Objects.equals(expression.getName(), name))
                .forEach(expression -> expressionItemProvider.setPropertyValue(expression,
                            ExpressionPackage.Literals.EXPRESSION__NAME.getName(),
                            name));
    }

    public void setMainProcess(MainProcess mainProcess) {
        this.mainProcess = mainProcess;
    }
}
