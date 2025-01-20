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

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * @author Vincent Hemery
 */
public class ProjectMigrationWizard extends Wizard {

    private MigrationReport report = new MigrationReport();
    private Path project;

    private AtomicBoolean finishLaunched = new AtomicBoolean(false);

    /**
     * Default Constructor.
     * 
     * @param project project path
     */
    public ProjectMigrationWizard(Path project) {
        this.project = project;
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.projectMigration);
        setDefaultPageImageDescriptor(Pics.getWizban());
        setDialogSettings(WorkbenchPlugin.getDefault().getDialogSettings());
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
            if (!page.isPageComplete() && page instanceof MigrationStepWizardPage step) {
                boolean success = step.triggerPageMigrationStep(true);
                if (!success) {
                    // display the page in error
                    getContainer().showPage(page);
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
     * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
     */
    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        var nextPage = super.getNextPage(page);
        if (nextPage instanceof MigrationStepWizardPage migrPage) {
            boolean display = migrPage.displayPageForProject(project);
            if (!display) {
                // skip this page
                return getNextPage(migrPage);
            }
        }
        return nextPage;
    }

}
