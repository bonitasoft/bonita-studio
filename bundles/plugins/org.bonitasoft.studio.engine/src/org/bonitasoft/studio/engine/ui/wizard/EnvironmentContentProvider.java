/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.engine.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class EnvironmentContentProvider implements IStructuredContentProvider {


    @Override
    public void dispose() {}


    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {}
    
    @Override
    public Object[] getElements(final Object inputElement) {
        final EnvironmentRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(EnvironmentRepositoryStore.class);
        final List<String> result = new ArrayList<>();
        for (final IRepositoryFileStore file : store.getChildren()) {
            try {
                final Environment env = (Environment) file.getContent();
                if (env != null) {
                    result.add(env.getName());
                }
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed read environement content", e);
            }

        }
        return result.toArray();
    }

}
