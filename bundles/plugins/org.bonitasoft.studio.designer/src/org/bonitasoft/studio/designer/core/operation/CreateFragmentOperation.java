/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.designer.core.operation;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.repository.WebFragmentFileStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.core.runtime.IProgressMonitor;

public class CreateFragmentOperation extends CreateUIDArtifactOperation {

    public CreateFragmentOperation(PageDesignerURLFactory pageDesignerURLBuilder, RepositoryAccessor repositoryAccessor) {
        super(pageDesignerURLBuilder, repositoryAccessor);
    }

    @Override
    protected void doRun(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        setArtifactName(getNewName());
        try {
            responseObject = createArtifact(pageDesignerURLBuilder.newFragment(), createBody());
        } catch (MalformedURLException e) {
            throw new InvocationTargetException(e, "Failed to create new fragment URL.");
        }
        openArtifact(getNewArtifactId());
    }
    
    @Override
    protected String getTaskName() {
        return Messages.creatingNewFragment;
    }

    @Override
    protected ArtifactyType getArtifactType() {
        return ArtifactyType.FRAGMENT;
    }

    private String getNewName() {
        List<String> existingFragments = repositoryAccessor.getRepositoryStore(WebFragmentRepositoryStore.class)
                .getChildren()
                .stream()
                .map(WebFragmentFileStore::getDisplayName)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(DEFAULT_FRAGMENT_NAME, existingFragments);
    }

}
