/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.handler.CustomPageProjectSelectionProvider;
import org.bonitasoft.studio.maven.ui.wizard.control.SelectRestAPIExtensionControlSupplier;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;

public class OpenCustomPageProjectHandler {

    @Execute
    public void execute(final Shell shell,
            final RepositoryAccessor repositoryAccessor,
            final WidgetFactory widgetFactory,
            final HttpClientFactory httpClientFactory,
            final CustomPageProjectSelectionProvider selectionProvider)
            throws ExecutionException {
        WritableValue fileStoreObservable = new WritableValue(null, RestAPIExtensionFileStore.class);
        Optional<RestAPIExtensionFileStore> fileStore = WizardBuilder.<RestAPIExtensionFileStore> newWizard()
                .withTitle(Messages.openRestApiExtensionTitle)
                .havingPage(newPage()
                        .withTitle(Messages.selectRestAPIExtensionToOpenTitle)
                        .withDescription(Messages.selectRestAPIExtensionToOpenDescription)
                        .withControl(new SelectRestAPIExtensionControlSupplier(
                                repositoryAccessor.getRepositoryStore(RestAPIExtensionRepositoryStore.class),
                                widgetFactory,
                                fileStoreObservable)))
                .onFinish(wizardContainer -> Optional.ofNullable((RestAPIExtensionFileStore) fileStoreObservable.getValue()))
                .open(shell, IDialogConstants.OPEN_LABEL);

        fileStore.ifPresent(file -> {
            file.open();
        });
    }

    @CanExecute
    public boolean canExecute(final RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isLoaded()
                && !repositoryAccessor.getRepositoryStore(RestAPIExtensionRepositoryStore.class).getChildren().isEmpty();
    }

}
