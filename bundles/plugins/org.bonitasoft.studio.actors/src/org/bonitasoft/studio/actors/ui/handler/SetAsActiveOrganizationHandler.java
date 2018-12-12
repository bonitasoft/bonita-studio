/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.actors.ui.handler;

import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

public class SetAsActiveOrganizationHandler {

    private FileStoreFinder fileStoreFinder;
    private ActiveOrganizationProvider activeOrganizationProvider;

    public SetAsActiveOrganizationHandler() {
        fileStoreFinder = new FileStoreFinder();
        activeOrganizationProvider = new ActiveOrganizationProvider();
    }

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor) {
        fileStoreFinder.findSelectedFileStore(repositoryAccessor.getCurrentRepository())
                .filter(OrganizationFileStore.class::isInstance)
                .map(OrganizationFileStore.class::cast)
                .ifPresent(orga -> {
                    activeOrganizationProvider.saveActiveOrganization(orga.getContent().getName());
                    AbstractFileStore.refreshExplorerView();
                });
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return fileStoreFinder.findSelectedFileStore(repositoryAccessor.getCurrentRepository())
                .filter(OrganizationFileStore.class::isInstance)
                .isPresent();
    }

}
