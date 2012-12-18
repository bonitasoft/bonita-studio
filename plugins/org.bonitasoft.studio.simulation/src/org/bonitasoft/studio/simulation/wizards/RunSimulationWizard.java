/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.simulation.wizards;

import java.io.File;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.simulation.SimulationPlugin;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;

/**
 * @author Baptiste Mesta
 *
 */
public class RunSimulationWizard extends Wizard {

    protected static final String lastSelectedProcessKey = "lastSelectedProcess";
    protected static final String lastPathKey = "pathForReports";

    private RunSimulationWizardPage page;
    private final AbstractProcess selectedProcess;
    private String path;
    private String loadProfileId;

    /**
     * @param selectedProcess
     */
    public RunSimulationWizard(AbstractProcess selectedProcess) {
        this.selectedProcess = selectedProcess;
        setWindowTitle(Messages.RunSimulationWizard_WindowTitle);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override
    public void addPages() {
        page = new RunSimulationWizardPage(selectedProcess);
        addPage(page);
        super.addPages();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        page.setErrorMessage(null);
        path = page.getPath();
        File file = new File(path);
        if(!file.exists()){
            page.setErrorMessage(Messages.RunSimulationWizard_Error_folderDoesNotExists);
            return false;
        }

        if(!file.isDirectory()){
            page.setErrorMessage(Messages.RunSimulationWizard_Error_folderisFile);
            return false;
        }
        loadProfileId = page.getLoadProfileId();
        IRepositoryStore loadProfileStore = RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class) ;
        IRepositoryFileStore artifact = loadProfileStore.getChild(loadProfileId+"."+SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);
        if(artifact == null ){
            page.setErrorMessage(NLS.bind(Messages.RunSimulationWizard_Error_missingLoadProfile,loadProfileId));
            return false;
        }

        getDialogSettings().put(lastPathKey, path);
        getDialogSettings().put(lastSelectedProcessKey, page.getProcess().getName());
        return true;
    }

    /**
     * @return the selectedProcess
     */
    public AbstractProcess getSelectedProcess() {
        return page.getProcess();
    }
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the loadProfileId
     */
    public String getLoadProfileId() {
        return loadProfileId;
    }

    public long getTimespan() {
        return page.getTimespan();
    }

    @Override
    public IDialogSettings getDialogSettings() {
        return SimulationPlugin.getDefault().getDialogSettings();
    }
}
