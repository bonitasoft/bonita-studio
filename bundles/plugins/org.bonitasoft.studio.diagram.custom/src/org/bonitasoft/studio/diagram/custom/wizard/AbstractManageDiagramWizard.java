/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.wizard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author aurelie zara
 */
public abstract class AbstractManageDiagramWizard extends Wizard implements IWizard {

    public boolean confirmDelete(final List<DiagramFileStore> selectedDiagrams) {
        if (!selectedDiagrams.isEmpty()) {
            final StringBuilder stringBuilder = new StringBuilder(selectedDiagrams.size() == 1 ? "" : "\n");
            for (final DiagramFileStore file : selectedDiagrams) {
                stringBuilder.append(file.getName());
                stringBuilder.append("\n");
            }
            return MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.confirmProcessDeleteTitle,
                    NLS.bind(Messages.confirmProcessDeleteMessage, stringBuilder.toString()));
        }
        return false;
    }

    public boolean deleteDiagrams(final List<DiagramFileStore> files, ContentViewer viewer) {
        if (!files.isEmpty()) {
            try {
                for (final DiagramFileStore file : files) {
                    for (final AbstractProcess process : file.getProcesses()) {
                        final String uuid = ModelHelper.getEObjectID(process);
                        final IRepositoryFileStore confFile = getConfigurationRepositoryStore()
                                .getChild(uuid + "." + ProcessConfigurationRepositoryStore.CONF_EXT, true);
                        if (confFile != null) {
                            confFile.delete();
                        }
                    }

                    for (final IEditorPart editor : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                            .getDirtyEditors()) {
                        if (editor.getEditorInput().getName().equals(file.getName())) {
                            file.save(editor);
                            break;
                        }
                    }
                    final Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put(AbstractFileStore.ASK_ACTION_ON_CLOSE, false);
                    file.setParameters(parameters);
                    file.close();
                    file.delete();
                }
                if (viewer != null) {
                    viewer.setInput(getDiagramRepositoryStore());
                }
                return true;
            } catch (final Exception e1) {
                BonitaStudioLog.error(e1);
            }
        }
        return false;
    }

    protected ProcessConfigurationRepositoryStore getConfigurationRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
    }

    protected DiagramRepositoryStore getDiagramRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
    }

    @Override
    public boolean performCancel() {
        PlatformUtil.openIntroIfNoOtherEditorOpen();
        return super.performCancel();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        return false;
    }

}
