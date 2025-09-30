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

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * @author Vincent Hemery
 */
public class ProjectMigrationWizard extends Wizard {

    private MigrationReport report = new MigrationReport();
    private Path project;
    private int jobsDone = 0;

    private AtomicBoolean finishLaunched = new AtomicBoolean(false);

    /**
     * Default Constructor.
     * 
     * @param project project path
     */
    public ProjectMigrationWizard(Path project) {
        this.project = project;
        setNeedsProgressMonitor(true);
        setWindowTitle(MessageFormat.format(Messages.projectMigration, 0));
        setDefaultPageImageDescriptor(Pics.getWizban());
        setDialogSettings(WorkbenchPlugin.getDefault().getDialogSettings());
    }

    /**
     * Alert the dialog that one job was skipped or executed, so we update the monitor.
     */
    public void oneJobDone() {
        jobsDone++;
        if (!getShell().isDisposed()) {
            int advancement = jobsDone * 100 / getPageCount();
            setWindowTitle(MessageFormat.format(Messages.projectMigration, advancement));
        }
    }

    /**
     * Get the migration report
     * 
     * @return the report
     */
    public MigrationReport getReport() {
        return report;
    }

    /**
     * Get the project path
     * 
     * @return the project path
     */
    public Path getProject() {
        return project;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#canFinish()
     */
    @Override
    public boolean canFinish() {
        // we do not need every step to be finished, just the current one
        var current = getContainer().getCurrentPage();
        return !finishStarted() && current.isPageComplete();
    }

    /**
     * Test whether finish operation has started
     * 
     * @return true when finish operation has already started
     */
    protected boolean finishStarted() {
        return finishLaunched.get();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public synchronized boolean performFinish() {
        // first disable buttons to avoid concurrent finish operations
        finishLaunched.set(true);
        getContainer().updateButtons();
        // execute all the remaining migration steps
        for (IWizardPage page : getPages()) {
            if (page instanceof MigrationStepWizardPage step) {
                if (step.displayPageForProject(project)) {
                    // display the current page in progress
                    getContainer().showPage(page);
                    /*
                     * This triggers the migration step anyway...
                     * So no need to call #triggerPageMigrationStep
                     */
                }
                /*
                 * Step executions are launched using the very same UI main thread...
                 * So we need to wait for execution end in the modal context.
                 */
                try {
                    ModalContext.run(mon -> step.waitWhileInProgress(), true, new NullProgressMonitor(),
                            getContainer().getShell().getDisplay());
                } catch (InvocationTargetException | InterruptedException e) {
                    BonitaStudioLog.error(e);
                    return false;
                }
                // check page success
                boolean success = step.isStepSuccessful();
                if (!success) {
                    // the page in error is already displayed
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#getPreviousPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public IWizardPage getPreviousPage(IWizardPage page) {
        // no previous page ever.
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#getStartingPage()
     */
    @Override
    public IWizardPage getStartingPage() {
        IWizardPage firstPage = super.getStartingPage();
        if (firstPage instanceof MigrationStepWizardPage migrPage) {
            boolean display = migrPage.displayPageForProject(project);
            if (!display) {
                // skip this page
                migrPage.setPageComplete(true);
                return getNextPage(migrPage);
            }
        }
        return firstPage;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        var nextPage = super.getNextPage(page);
        if (nextPage instanceof MigrationStepWizardPage migrPage) {
            boolean display = migrPage.displayPageForProject(project);
            if (!display) {
                // skip this page
                migrPage.setPageComplete(true);
                return getNextPage(migrPage);
            }
        }
        return nextPage;
    }

}
