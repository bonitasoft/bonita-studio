/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.ui.handler;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.repository.EnvironmentFileStore;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

public class SetSelectionAsDefaultConfigurationHandler {

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor) {
        repositoryAccessor.getCurrentRepository().ifPresent(repo -> {
            new FileStoreFinder().findSelectedFileStore(repo)
                    .filter(EnvironmentFileStore.class::isInstance)
                    .flatMap(IDisplayable::toDisplayName)
                    .ifPresent(displayName -> {
                        ConfigurationPlugin.getDefault().getPreferenceStore()
                                .setValue(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION, displayName);
                        AbstractFileStore.refreshExplorerView();
                    });
        });
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isPresent()
                &&  new FileStoreFinder().findSelectedFileStore(repositoryAccessor.getCurrentRepository().orElse(null))
                        .filter(EnvironmentFileStore.class::isInstance)
                        .isPresent();
    }

}
