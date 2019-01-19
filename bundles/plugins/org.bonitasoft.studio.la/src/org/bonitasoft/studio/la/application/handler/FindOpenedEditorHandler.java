/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.handler;

import java.util.Optional;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.EditorFinder;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.editors.FilteredXMLEditor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.ui.IEditorPart;

public class FindOpenedEditorHandler {

    private static final String FILE_STORE_PARAMETER = "fileStore";

    @Execute
    public Optional<IEditorPart> execute(RepositoryAccessor repositoryAccessor,
            @org.eclipse.e4.core.di.annotations.Optional @Named(FILE_STORE_PARAMETER) String fileStore) {
        ApplicationFileStore application = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)
                .getChild(fileStore);
        return application != null
                ? EditorFinder.findOpenedEditor(application, this::validateEditorInstance)
                : Optional.empty();
    }

    protected boolean validateEditorInstance(IEditorPart editor) {
        return editor instanceof FilteredXMLEditor;
    }

}
