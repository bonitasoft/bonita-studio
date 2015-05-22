/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.ui.wizard;

import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * @author Romain Bioteau
 */
public class ExportRepositoryWizard extends Wizard {

    public List<IRepositoryStore<? extends IRepositoryFileStore>> stores;
    protected ExportRepositoryWizardPage mainPage;
    protected final boolean isZip;
    protected final String wizardTitle;
    protected Set<Object> defaultSelectedFiles;
    protected final String defaultFileName;

    public ExportRepositoryWizard(final List<IRepositoryStore<? extends IRepositoryFileStore>> stores, final boolean isZip,
            final Set<Object> defaultSelectedFiles, final String defaultFileName, final String wizardTitle) {
        this.stores = stores;
        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
        this.isZip = isZip;
        this.wizardTitle = wizardTitle;
        this.defaultSelectedFiles = defaultSelectedFiles;
        this.defaultFileName = defaultFileName;
        setWindowTitle(wizardTitle);
        final IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault()
                .getDialogSettings();
        IDialogSettings wizardSettings = workbenchSettings
                .getSection("ExportRepositoryWizard"); //$NON-NLS-1$
        if (wizardSettings == null) {
            wizardSettings = workbenchSettings.addNewSection("ExportRepositoryWizard"); //$NON-NLS-1$
        }
        setDialogSettings(wizardSettings);
    }

    @Override
    public void addPages() {
        mainPage = new ExportRepositoryWizardPage(stores, isZip, defaultFileName);
        mainPage.setTitle(wizardTitle);
        if (defaultSelectedFiles != null) {
            mainPage.setDefaultSelectedFiles(defaultSelectedFiles);
        }
        addPage(mainPage);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        return mainPage.finish();
    }

}
