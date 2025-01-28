/**
 * Copyright (C) 2025 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.core.migration.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * @author Vincent Hemery
 */
public class MigrationStepWizardPage extends WizardPage {

    private enum ExecutionStatus {

        INITIAL, PREREQUISITE_CHECKED, TRIGGERED, SKIPPED, RUNNING, EXECUTED, ERROR;

        /**
         * Returns whether step is in progress (triggered or running).
         * 
         * @return true when triggered or running, false for stable states
         */
        public boolean isInProgress() {
            switch (this) {
                case TRIGGERED:
                case RUNNING:
                    return true;
                default:
                    return false;
            }
        }

        /**
         * Returns whether step is successful (skipped or executed)
         * 
         * @return true when skipped of executed, false for waiting, unstable or error states
         */
        public boolean isSuccessful() {
            switch (this) {
                case SKIPPED:
                case EXECUTED:
                    return true;
                default:
                    return false;
            }
        }

        /**
         * Returns whether page should be displayed as a new page
         * 
         * @return true when prerequisite checked or in error
         */
        boolean isToBeDisplayed() {
            switch (this) {
                case PREREQUISITE_CHECKED:
                case ERROR:
                    return true;
                default:
                    return false;
            }
        }
    }

    private static final int MARGIN = 10;

    /** Execution status of the migration step */
    private AtomicReference<ExecutionStatus> status = new AtomicReference<>(ExecutionStatus.INITIAL);

    private MigrationStep step;

    /** Link to open log view */
    private Link logLink;

    /**
     * Default Constructor.
     * 
     * @param step the migration step
     */
    public MigrationStepWizardPage(MigrationStep step) {
        super(step.getDescription().title(), step.getDescription().title(), null);
        this.step = step;
        setPageComplete(false);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
     */
    @Override
    public boolean canFlipToNextPage() {
        return super.canFlipToNextPage() && !getWizard().finishStarted();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        var main = new Composite(parent, SWT.NONE);
        setControl(main);
        main.setLayout(new FormLayout());
        var label = new Link(main, SWT.WRAP);
        label.setText(step.getDescription().description());
        label.addListener(SWT.Selection, this::openBrowser);
        var data = new FormData();
        data.top = new FormAttachment(0, MARGIN);
        data.left = new FormAttachment(0, MARGIN);
        data.right = new FormAttachment(100, -MARGIN);
        label.setLayoutData(data);
        // also add a link to open the log view, but do not make it visible until an error is displayed
        logLink = new Link(main, SWT.NONE);
        logLink.setText(String.format("<a>%s</a>", Messages.projectMigrationOpenLog)); //$NON-NLS-1$
        logLink.setVisible(false);
        data = new FormData();
        data.bottom = new FormAttachment(100, -MARGIN);
        data.left = new FormAttachment(0, MARGIN);
        data.right = new FormAttachment(100, -MARGIN);
        logLink.setLayoutData(data);
        logLink.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                IHandlerService handlerServ = PlatformUI.getWorkbench().getService(IHandlerService.class);
                try {
                    handlerServ.executeCommand("org.bonitasoft.studio.application.openLog", null); //$NON-NLS-1$
                    gotoLastEditorLine();
                } catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException err) {
                    BonitaStudioLog.error(err);
                }
            }
        });
    }

    /**
     * Go to last editor line (for textual log)
     */
    protected void gotoLastEditorLine() {
        var active = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (active instanceof ITextEditor editor) {
            var docProvider = editor.getDocumentProvider();
            if (docProvider != null) {
                var doc = docProvider.getDocument(editor.getEditorInput());
                if (doc != null) {
                    try {
                        var lastLine = doc.getLineOfOffset(doc.getLength());
                        var offset = doc.getLineOffset(lastLine);
                        editor.selectAndReveal(offset, 0);
                    } catch (BadLocationException ex) {
                        BonitaStudioLog.error(ex);
                    }

                }
            }
        }

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#getPreviousPage()
     */
    @Override
    public IWizardPage getPreviousPage() {
        // no previous page ever.
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            triggerPageMigrationStep();
        }
    }

    /**
     * Trigger the migration step (if needed)
     * 
     * @return true when step was successfully (executed or skipped, usually on finish), false when step failed or will execute later
     * @throws InterruptedException manual interruption
     * @throws InvocationTargetException failure exception
     */
    @java.lang.SuppressWarnings("java:S2142")
    public void triggerPageMigrationStep() {
        if (status.compareAndSet(ExecutionStatus.PREREQUISITE_CHECKED, ExecutionStatus.TRIGGERED)) {
            // trigger the migration step
            Display display = getShell().getDisplay();
            Runnable jobOnContainer = () -> display.syncExec(() -> {
                String stepName = step.getDescription().title();
                try {
                    // this runs synchronously
                    getContainer().run(true, true, this::doMigrationStep);
                    // step succeeded
                    var msg = MessageFormat.format("Migration step {0} successful.\nProceed to next step.",
                            stepName);
                    setMessage(msg, INFORMATION);
                    setPageComplete(true);
                    getWizard().oneJobDone();
                } catch (InvocationTargetException e) {
                    var msg = MessageFormat.format(Messages.projectMigrationStepFailed, stepName, getMessage(e));
                    setErrorMessage(msg);
                    BonitaStudioLog.error(msg, e);
                } catch (InterruptedException e) {
                    // wizard was cancelled, set error message even if wizard should be discarded
                    status.set(ExecutionStatus.ERROR);
                    var msg = MessageFormat.format(Messages.projectMigrationStepCancelled,
                            stepName, getMessage(e));
                    setErrorMessage(msg);
                    BonitaStudioLog.debug(msg, e, getClass());
                }
            });
            // launch an async thread to let the wizard open first or the page show.
            new Thread(jobOnContainer::run).start();
        }
        // else, the task was already triggered, it may be waiting or running
    }

    /**
     * Test whether step was successful
     * 
     * @return true when step was successfully (executed or skipped), false when step failed or will execute later
     */
    public boolean isStepSuccessful() {
        return status.get().isSuccessful();
    }

    /**
     * Test whether this page should be displayed on project.
     * 
     * @param project the project working on
     * @return true when step must be executed or page must be displayed with prerequisite check error
     */
    public boolean displayPageForProject(Path project) {
        // compute result only once
        return status.updateAndGet(oldStatus -> {
            if (oldStatus == ExecutionStatus.INITIAL) {
                String stepName = step.getDescription().title();
                try {
                    boolean valid = step.appliesToProject(project);
                    var msg = MessageFormat.format(
                            valid ? "Prerequistes OK for migration step {0}." : "Skipped migration step {0}.",
                            stepName);
                    BonitaStudioLog.debug(msg, getClass());
                    if (!valid) {
                        // the skipped job should be considered done.
                        getWizard().oneJobDone();
                    }
                    return valid ? ExecutionStatus.PREREQUISITE_CHECKED : ExecutionStatus.SKIPPED;
                } catch (CoreException e) {
                    // page's prerequisites fail. Still display it with error details.
                    var msg = MessageFormat.format(Messages.projectMigrationPrerequisitesFailed, stepName,
                            getMessage(e));
                    BonitaStudioLog.error(msg, e);
                    setErrorMessage(msg);
                    return ExecutionStatus.ERROR;
                }
            }
            return oldStatus;
        }).isToBeDisplayed();
    }

    /**
     * Get Exception message
     * 
     * @param exception exception
     * @return message
     */
    protected String getMessage(Throwable exception) {
        Function<Throwable, Optional<String>> extractMessage = e -> Optional.ofNullable(e.getLocalizedMessage())
                .or(() -> Optional.ofNullable(e.getMessage()));
        // first, try and get local message
        Optional<String> explicitMessage = extractMessage.apply(exception)
                // otherwise, look for message in cause
                .or(() -> Optional.ofNullable(exception.getCause()).flatMap(extractMessage));
        // when no message, just use exception name
        return explicitMessage.orElseGet(() -> exception.getClass().getName());
    }

    /**
     * Do the migration step with monitor
     * 
     * @param monitor progress monitor
     * @throws InvocationTargetException exception during step
     */
    private void doMigrationStep(IProgressMonitor monitor) throws InvocationTargetException {
        // make sure we don't execute twice
        if (status.compareAndSet(ExecutionStatus.TRIGGERED, ExecutionStatus.RUNNING)) {
            String stepName = step.getDescription().title();
            try {
                monitor.beginTask(stepName, 100);
                step.run(getWizard().getProject(), monitor).merge(getWizard().getReport());
                var msg = MessageFormat.format("Migration step {0} successful.", stepName);
                BonitaStudioLog.info(msg);
                monitor.worked(100);
                status.set(ExecutionStatus.EXECUTED);
            } catch (CoreException e) {
                status.set(ExecutionStatus.ERROR);
                throw new InvocationTargetException(e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#getWizard()
     */
    @Override
    public ProjectMigrationWizard getWizard() {
        return (ProjectMigrationWizard) super.getWizard();
    }

    private void openBrowser(Event linkSelectionEvent) {
        var text = linkSelectionEvent.text;
        if (Pattern.matches("\\d+", text)) { //$NON-NLS-1$
            try {
                Desktop.getDesktop().browse(RedirectURLBuilder.createURI(text));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        // else, this is not a redirection link ; this should not happen unless the link has been tampered with
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#setErrorMessage(java.lang.String)
     */
    @Override
    public void setErrorMessage(String newMessage) {
        if (!Strings.isNullOrEmpty(newMessage)) {
            // display the log link
            logLink.setVisible(true);
        }
        super.setErrorMessage(newMessage);
    }

    /**
     * Make the current thread wait while the step is in progress (triggered or running).
     */
    public void waitWhileInProgress() {
        for (;;) {
            if (!status.get().isInProgress()) {
                return;
            }
        }
    }

}
