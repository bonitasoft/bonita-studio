/*******************************************************************************
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.theme.handler;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.maven.MavenProjectConfiguration;
import org.bonitasoft.studio.maven.contribution.InstallRestAPIArchetypeContribution;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.bonitasoft.studio.theme.wizard.NewThemeWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class NewThemeHandler extends AbstractHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
            RepositoryAccessor repositoryAccessor,
            WidgetFactory widgetFactory,
            IWorkspace workspace)
            throws ExecutionException {
        installDependencies();
        final NewThemeWizard wizard = newWizard(repositoryAccessor, widgetFactory, workspace);
        final int open = newWizardDialog(wizard, Messages.create).open();
        if (open == IDialogConstants.OK_ID) {
            wizard.getNewFileStore().open();
        }
    }

    protected void installDependencies() throws ExecutionException {
        try {
            new ProgressMonitorDialog(Display.getDefault().getActiveShell()).run(true, false, monitor -> {
                monitor.beginTask(Messages.installingDependencies, IProgressMonitor.UNKNOWN);
                new InstallRestAPIArchetypeContribution().execute();
            });
        } catch (InvocationTargetException | InterruptedException e) {
            throw new ExecutionException(e.getMessage(), e);
        }
    }

    @CanExecute
    public boolean canExecute(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().isLoaded();
    }

    protected NewThemeWizard newWizard(RepositoryAccessor repositoryAccessor, WidgetFactory widgetFactory, IWorkspace workspace) {
        return new NewThemeWizard(repositoryAccessor.getRepositoryStore(ThemeRepositoryStore.class),
                MavenPlugin.getProjectConfigurationManager(), new MavenProjectConfiguration(), workspace, widgetFactory);
    }

    protected WizardDialog newWizardDialog(final Wizard wizard, String finishLabel) {
        return new CustomWizardDialog(Display.getDefault().getActiveShell(), wizard, finishLabel);
    }



    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        WidgetFactory widgetFactory = new WidgetFactory();
        execute(Display.getDefault().getActiveShell(), repositoryAccessor, widgetFactory,
                repositoryAccessor.getWorkspace());
        return null;
    }

}
