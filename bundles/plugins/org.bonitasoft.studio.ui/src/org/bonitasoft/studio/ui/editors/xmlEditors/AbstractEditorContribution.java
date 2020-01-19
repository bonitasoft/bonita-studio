/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors.xmlEditors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

public abstract class AbstractEditorContribution implements IResourceChangeListener, Comparable<AbstractEditorContribution> {

    protected IEditorInput input;
    protected RepositoryAccessor repositoryAccessor;

    public AbstractEditorContribution() {
        this.repositoryAccessor = repositoryAccessor();
        this.input = initEditorInput();
    }

    private RepositoryAccessor repositoryAccessor() {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        return repositoryAccessor;
    }

    protected abstract IEditorInput initEditorInput();

    public abstract void addPages(AbstractMultiSourceFormEditor editor) throws PartInitException;

    public abstract void doSave(IProgressMonitor monitor);

    public abstract boolean isDirty();

    public abstract String getId();

    public abstract String getEditorId();

    public abstract int getContributionIndex();

    public abstract boolean canContribute();

    public abstract int getMainPageIndex();

    protected abstract void pageChange(int newPageIndex);

    public abstract void makeStale();

    @Override
    public int compareTo(AbstractEditorContribution o) {
        return getContributionIndex() - o.getContributionIndex();
    }

}
