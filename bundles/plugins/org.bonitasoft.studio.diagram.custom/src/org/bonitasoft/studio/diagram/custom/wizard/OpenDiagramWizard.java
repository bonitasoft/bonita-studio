/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 */
public class OpenDiagramWizard extends AbstractManageDiagramWizard {

    private OpenDiagramWizardPage page;

    /**
     * Default constructor to open process and not example.
     */
    public OpenDiagramWizard() {
        setNeedsProgressMonitor(true);
        setWindowTitle(Messages.openProcessWizardPage_title);
    }

    @Override
    public void addPages() {
        page = new OpenDiagramWizardPage(getDiagramRepositoryStore());
        addPage(page);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        try {
            getContainer().run(false, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.openingDiagramProgressText, IProgressMonitor.UNKNOWN);
                    final List<DiagramFileStore> files = page.getSelectedDiagrams();
                    final List<DiagramFileStore> dirtyFiles = new ArrayList<DiagramFileStore>(0);

                    final Map<DiagramFileStore, Boolean> filesToOpen = new HashMap<DiagramFileStore, Boolean>();
                    final StringBuilder stringBuilder = new StringBuilder(files.size() == 1 ? "" : "\n");

                    // get dirtyEditor and list of processes related to them
                    for (final DiagramFileStore file : files) {
                        filesToOpen.put(file, true);
                        for (final IEditorPart editor : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getDirtyEditors()) {
                            if (editor.getEditorInput().getName().equals(file.getName())) {
                                dirtyFiles.add(file);
                                stringBuilder.append(file.getName());
                                stringBuilder.append("\n");
                                break;
                            }
                        }
                    }
                    // case of dirty diagrams
                    if (!dirtyFiles.isEmpty() && !MessageDialog.openQuestion(page.getShell(), Messages.confirmProcessOverrideTitle,
                            NLS.bind(Messages.confirmProcessOverrideMessage, stringBuilder.toString()))) {
                        for (final DiagramFileStore file : dirtyFiles) {
                            filesToOpen.put(file, false);
                        }
                    }

                    // Open closed, already open, not dirty diagrams, for dirty ones, depending on openQuestion called before
                    for (final DiagramFileStore file : files) {
                        if (filesToOpen.get(file)) {
                            final IEditorReference openEditor = PlatformUtil.getOpenEditor(file.getName());
                            if (openEditor != null) {
                                PlatformUtil.swtichToOpenedEditor(openEditor);
                            } else {
                                file.open();
                            }
                        }
                    }

                    monitor.done();
                }

            });
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }

        return true;
    }

}
