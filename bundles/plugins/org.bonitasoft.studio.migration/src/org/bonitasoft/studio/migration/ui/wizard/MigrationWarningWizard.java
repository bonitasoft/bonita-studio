/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.ui.wizard;

import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.wizard.Wizard;


/**
 * @author Romain Bioteau
 *
 */
public class MigrationWarningWizard extends Wizard {

    public MigrationWarningWizard(){
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.migrationWarningTitle);
        setNeedsProgressMonitor(false);
        setForcePreviousAndNextButtons(false);
    }

    @Override
    public void addPages() {
        addPage(new MigrationWarningWizardPage());
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        return true;
    }

}
