/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.handler;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

public abstract class OpenWizardDialogAbstractHandler {

    @Execute
    public void execute(final RepositoryAccessor repositoryAccessor, final WidgetFactory widgetFactory,
            final HttpClientFactory httpClientFactory,
            final CustomPageProjectSelectionProvider selectionProvider)
            throws ExecutionException {
        final IWizard wizard = newWizard(repositoryAccessor, widgetFactory, httpClientFactory, selectionProvider);
        newWizardDialog(wizard, getFinishLabel()).open();
    }
    
    protected abstract CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> getStore(RepositoryAccessor repositoryAccessor);

    protected String getFinishLabel() {
        return IDialogConstants.FINISH_LABEL;
    }

    @CanExecute
    public boolean canExecute(final RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isLoaded()
                && !getStore(repositoryAccessor).getChildren().isEmpty();
    }

    protected abstract IWizard newWizard(RepositoryAccessor repositoryAccessor, WidgetFactory widgetFactory,
            final HttpClientFactory httpClientFactory,
            CustomPageProjectSelectionProvider selectionProvider);

    protected WizardDialog newWizardDialog(final IWizard wizard, final String finishLabel) {
        return new CustomWizardDialog(Display.getDefault().getActiveShell(), wizard, finishLabel);
    }

}
