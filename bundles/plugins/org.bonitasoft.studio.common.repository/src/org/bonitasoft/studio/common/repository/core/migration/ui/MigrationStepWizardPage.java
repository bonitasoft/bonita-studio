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
import java.util.concurrent.atomic.AtomicBoolean;
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
import org.eclipse.swt.SwtCallable;
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

    private static final int MARGIN = 10;

    private MigrationStep step;

    /** Whether step has been executed once. */
    private AtomicBoolean executed = new AtomicBoolean(false);

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
            triggerPageMigrationStep(false);
        }
    }

    /**
     * Trigger the migration step
     * 
     * @param onFinish whether we are on the wizard's finish where wizard will close if running an asynchronous thread.
     * @return false when step did not succeed on wizard's finish
     * @throws InterruptedException manual interruption
     * @throws InvocationTargetException failure exception
     */
    @java.lang.SuppressWarnings("java:S2142")
    public boolean triggerPageMigrationStep(boolean onFinish) {
        if (!isPageComplete() && Strings.isNullOrEmpty(getErrorMessage())) {
            // trigger the migration step
            Display display = getShell().getDisplay();
            SwtCallable<Boolean, RuntimeException> jobOnContainer = () -> display.syncCall(() -> {
                String stepName = step.getDescription().title();
                try {
                    // this runs synchronously
                    getContainer().run(true, true, this::doMigrationStep);
                    // step succeeded
                    display.syncExec(() -> setPageComplete(true));
                    return true;
                } catch (InvocationTargetException e) {
                    var msg = MessageFormat.format(Messages.projectMigrationStepFailed, stepName, getMessage(e));
                    setErrorMessage(msg);
                    BonitaStudioLog.error(msg, e);
                    return false;
                } catch (InterruptedException e) {
                    // wizard was cancelled, set error message even if wizard should be discarded
                    var msg = MessageFormat.format(Messages.projectMigrationStepCancelled,
                            stepName, getMessage(e));
                    setErrorMessage(msg);
                    BonitaStudioLog.debug(msg, e, getClass());
                    return false;
                }
            });
            if (onFinish) {
                // do not launch an async thread, use the wizard synchronously to show the progress.
                return jobOnContainer.call();
            } else {
                // launch an async thread to let the wizard open first.
                new Thread(jobOnContainer::call).start();
            }

        }
        return Strings.isNullOrEmpty(getErrorMessage());
    }

    private Optional<Boolean> displayPage = Optional.empty();

    /**
     * Test whether this page should be displayed on project.
     * 
     * @param project the project working on
     * @return true when step must be executed or page must be displayed with prerequisite check error
     */
    public boolean displayPageForProject(Path project) {
        // compute result only once
        return displayPage.orElseGet(() -> {
            String stepName = step.getDescription().title();
            try {
                boolean valid = step.appliesToProject(project);
                var msg = MessageFormat.format(
                        valid ? "Prerequistes OK for migration step {0}." : "Skipped migration step {0}.", stepName);
                BonitaStudioLog.debug(msg, getClass());
                displayPage = Optional.of(valid);
                if (!valid) {
                    // the skipped job should be considered done.
                    if (getContainer() instanceof ProjectMigrationWizardDialog d) {
                        d.oneJobSkipped();
                    }
                }
                return valid;
            } catch (CoreException e) {
                // page's prerequisites fail. Still display it with error details.
                var msg = MessageFormat.format(Messages.projectMigrationPrerequisitesFailed, stepName,
                        getMessage(e));
                BonitaStudioLog.error(msg, e);
                setErrorMessage(msg);
                displayPage = Optional.of(true);
                return true;
            }
        });
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
        if (!executed.getAndSet(true)) {
            String stepName = step.getDescription().title();
            try {
                monitor.beginTask(stepName, 100);
                step.run(getWizard().getProject(), monitor).merge(getWizard().getReport());
                var msg = MessageFormat.format("Migration step {0} successful.", stepName);
                BonitaStudioLog.info(msg);
                monitor.worked(100);
            } catch (CoreException e) {
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

}
