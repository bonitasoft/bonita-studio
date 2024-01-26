/*******************************************************************************
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.theme.handler;

import jakarta.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.jface.CustomWizardDialog;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.theme.wizard.NewThemeWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class NewThemeHandler extends AbstractHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
            RepositoryAccessor repositoryAccessor,
            WidgetFactory widgetFactory,
            IWorkspace workspace)
            throws ExecutionException {
        try {
            var projectMetadata = repositoryAccessor.getCurrentProject().orElseThrow().getProjectMetadata(new NullProgressMonitor());
            final NewThemeWizard wizard = newWizard(repositoryAccessor, projectMetadata, widgetFactory, workspace);
            final int open = newWizardDialog(wizard, Messages.create).open();
            if (open == IDialogConstants.OK_ID) {
                wizard.getNewFileStore().open();
            }
        } catch (CoreException e) {
            throw new ExecutionException("Failed to create Theme Extension", e);
        }
     
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().filter(IRepository::isLoaded).isPresent();
    }

    protected NewThemeWizard newWizard(RepositoryAccessor repositoryAccessor, ProjectMetadata projectMetadata, WidgetFactory widgetFactory, IWorkspace workspace) {
        return new NewThemeWizard(projectMetadata, repositoryAccessor.getRepositoryStore(ExtensionRepositoryStore.class),
                 new MavenProjectConfiguration(), workspace, widgetFactory);
    }

    protected WizardDialog newWizardDialog(final Wizard wizard, String finishLabel) {
        return new CustomWizardDialog(Display.getDefault().getActiveShell(), wizard, finishLabel);
    }



    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        WidgetFactory widgetFactory = new WidgetFactory();
        RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        execute(Display.getDefault().getActiveShell(), repositoryAccessor, widgetFactory,
                repositoryAccessor.getWorkspace());
        return null;
    }

}
