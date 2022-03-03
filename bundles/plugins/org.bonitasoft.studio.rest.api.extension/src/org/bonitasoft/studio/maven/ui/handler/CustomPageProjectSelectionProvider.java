/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.handler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

@Creatable
public class CustomPageProjectSelectionProvider {

    private IWorkbenchPage activePage;
    private RepositoryAccessor repositoryAccessor;

    @PostConstruct
    @Inject
    public void init(final IWorkbenchPage activePage, final RepositoryAccessor repositoryAccessor) {
        this.activePage = activePage;
        this.repositoryAccessor = repositoryAccessor;
    }

    public CustomPageProjectFileStore getSelection() {
        final IEditorPart activeEditor = activePage.getActiveEditor();
        if (activeEditor != null) {
            final IEditorInput editorInput = activeEditor.getEditorInput();
            final IFile file = (IFile) editorInput.getAdapter(IFile.class);
            try {
                if (file != null && file.getProject().hasNature(RestAPIExtensionNature.NATURE_ID)) {
                    IRepositoryFileStore fileStore = repositoryAccessor.getCurrentRepository().getFileStore(file.getProject());
                    return (CustomPageProjectFileStore) fileStore;
                    //return repositoryAccessor.getRepositoryStore(RestAPIExtensionRepositoryStore.class).getChild(file.getProject().getName());
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

}
