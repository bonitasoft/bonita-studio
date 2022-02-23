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
package org.bonitasoft.studio.diagram.custom;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class DiagramPropertyTester extends PropertyTester {

    public static final String DIAGRAM_FOLDER_PROPERTY = "isDiagramFolder";
    public static final String DIAGRAM_FILE_PROPERTY = "isDiagramFile";
    public static final String DIAGRAM_ACTIVE_EDITOR_PROPERTY = "isSelectedDiagramActive";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        switch (property) {
            case DIAGRAM_FOLDER_PROPERTY:
                return isDiagramFolder((IAdaptable) receiver, store);
            case DIAGRAM_FILE_PROPERTY:
                return isDiagramFile((IAdaptable) receiver, store);
            case DIAGRAM_ACTIVE_EDITOR_PROPERTY:
                return isSelectedDiagramActiveEditor((IAdaptable) receiver, store);
            default:
                return false;
        }
    }

    private boolean isSelectedDiagramActiveEditor(IAdaptable receiver, DiagramRepositoryStore store) {
        IFile file = receiver.getAdapter(IFile.class);
        if (file != null) {
            Optional<DiagramEditor> editor = store.getChildren().stream()
                    .filter(fileStore -> Objects.equals(fileStore.getResource(), file))
                    .findFirst()
                    .map(DiagramFileStore::getOpenedEditor);
            if (editor.isPresent()) {
                Optional<IEditorPart> activeEditor = getActiveEditor();
                return activeEditor.isPresent() && Objects.equals(editor.get(), activeEditor.get());
            }
        }
        return false;
    }

    private Optional<IEditorPart> getActiveEditor() {
        return Optional.ofNullable(PlatformUI.getWorkbench().getActiveWorkbenchWindow())
                .map(IWorkbenchWindow::getActivePage)
                .map(IWorkbenchPage::getActiveEditor);
    }

    private boolean isDiagramFile(IAdaptable receiver, DiagramRepositoryStore store) {
        IFile file = receiver.getAdapter(IFile.class);
        if (file != null) {
             return store.getChildren().stream()
                    .filter( fStore -> Objects.equals(file,fStore.getResource()))
                    .findFirst()
                    .filter( fStore -> fStore.validate().isOK())
                    .isPresent();
        }
        return false;
    }

    private boolean isDiagramFolder(IAdaptable receiver, DiagramRepositoryStore repositoryStore) {
        return Objects.equals(receiver.getAdapter(IFolder.class), repositoryStore.getResource());
    }

}
