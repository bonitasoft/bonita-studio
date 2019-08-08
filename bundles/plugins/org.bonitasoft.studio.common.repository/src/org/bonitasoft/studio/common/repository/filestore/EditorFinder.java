/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.repository.filestore;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class EditorFinder {

    public static Optional<IEditorPart> findOpenedEditor(AbstractFileStore fileStore,
            Function<IEditorPart, Boolean> editorInstanceValidator) {
        if (PlatformUI.isWorkbenchRunning()
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            final String resourceName = fileStore.getResource().getName();
            return Arrays.asList(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences())
                    .stream()
                    .filter(ref -> {
                        return getEditorInput(ref)
                                .map(IEditorInput::getName)
                                .filter(resourceName::equals)
                                .isPresent();
                    })
                    .map(ref -> ref.getEditor(false))
                    .filter(editorInstanceValidator::apply)
                    .findFirst();
        }
        return Optional.empty();
    }

    private static Optional<IEditorInput> getEditorInput(IEditorReference ref) {
        try {
            return Optional.of(ref.getEditorInput());
        } catch (PartInitException e) {
            BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
        } catch (NullPointerException e) {
            BonitaStudioLog.log("There is an editor without input.");
        }
        return Optional.empty();
    }

}
