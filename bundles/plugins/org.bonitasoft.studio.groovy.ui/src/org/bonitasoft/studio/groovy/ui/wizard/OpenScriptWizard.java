/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.groovy.ui.wizard;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Mickael Istria
 *
 */
public class OpenScriptWizard extends Wizard implements IWizard {

    private OpenScriptWizardPage page;
    private IRepositoryFileStore file;
    private final IRepositoryFileStore currentlyOpenFile;
    private final boolean withCreationButton;

    /**
     * @param withCreationButton
     * @param currentlyOpenScript: we will forbid deletion of this script
     */
    public OpenScriptWizard(boolean withCreationButton, IRepositoryFileStore currentlyOpenScript){
        this.withCreationButton = withCreationButton;
        currentlyOpenFile = currentlyOpenScript;
        setWindowTitle(Messages.manageGroovyScripts);
    }

    @Override
    public void addPages() {
        page = new OpenScriptWizardPage(this, withCreationButton);
        addPage(page);
    }

    @Override
    public boolean performFinish() {
        file = page.getFile();
        return true;
    }

    public IRepositoryFileStore getFile() {
        return file;
    }

    public IRepositoryFileStore getCurrentlyOpenScript() {
        return currentlyOpenFile;
    }

}
