/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.command;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveFactory;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 * @author Romain Bioteau
 */
public class ExportAsBosArchiveHandler extends AbstractHandler {

    private static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();
    public static final String CONFIGURATION_PARAMETER_NAME = "__PROCESS_CONFIGURATION__";
    public static String DEST_FILE_PARAMETER_NAME = "__EXPORT_DEST_FILE__"; //$NON-NLS-1$
    private String configurationId;

    private IRepositoryStore<? extends IRepositoryFileStore> processConfStore;
    private IRepositoryStore<? extends IRepositoryFileStore> diagramStore;

    /**
     * @return a List<File> of the all the created bar or proc
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        processConfStore = RepositoryManager.getInstance()
                .getRepositoryStore(ProcessConfigurationRepositoryStore.class);

        String destFilePath = null;
        if (event != null) {
            destFilePath = event.getParameter(DEST_FILE_PARAMETER_NAME);
            configurationId = event.getParameter(CONFIGURATION_PARAMETER_NAME);
        }
        if (configurationId == null) {
            configurationId = ConfigurationPlugin.getDefault().getPreferenceStore()
                    .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        }

        IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (!(part instanceof DiagramEditor)) {
            throw new ExecutionException("Can't find a process editor");
        }

        final DiagramEditPart diagram = ((DiagramEditor) part).getDiagramEditPart();
        if (part.isDirty()) {
            if (acceptSave()) {
                part.doSave(AbstractRepository.NULL_PROGRESS_MONITOR);
            } else {
                return null;
            }
        }

        if (destFilePath == null) {
            destFilePath = selectTargetDir();
        }
        if (destFilePath == null) {
            return null;
        }
        final File outputDir = new File(destFilePath);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        final MainProcess mainProcess = ModelHelper.getMainProcess(diagram.resolveSemanticElement());
        DiagramFileStore diagramFile = (DiagramFileStore) diagramStore
                .getChild(NamingUtils.toDiagramFilename(mainProcess), true);
        Assert.isNotNull(diagramFile, "Diagram not found in repository");

        final String archiveName = mainProcess.getName() + "_" + mainProcess.getVersion();
        final File tmpDir = new File(TMP_DIR, archiveName);
        PlatformUtil.delete(tmpDir, AbstractRepository.NULL_PROGRESS_MONITOR);
        tmpDir.mkdirs();

        try {
            diagramFile.export(tmpDir.getAbsolutePath());
        } catch (IOException e1) {
            throw new ExecutionException(e1.getMessage());
        }
        for (final AbstractProcess process : diagramFile.getProcesses()) {
            try {
                String processUUID = ModelHelper.getEObjectID(process);
                IRepositoryFileStore file = processConfStore
                        .getChild(processUUID + "." + ProcessConfigurationRepositoryStore.CONF_EXT, true);
                file.export(tmpDir.getAbsolutePath());

                final File targetBarFile = new File(tmpDir, process.getName() + "--" + process.getVersion() + ".bar");
                targetBarFile.delete();
                BusinessArchive bar = BarExporter.getInstance().createBusinessArchive(process, configurationId);
                BusinessArchiveFactory.writeBusinessArchiveToFile(bar, targetBarFile);
            } catch (Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        final File targetFile = new File(outputDir, archiveName + ".zip");
        try (final FileOutputStream fos = new FileOutputStream(targetFile);
                final ZipOutputStream zos = new ZipOutputStream(fos);) {

            if (targetFile.exists()) {
                if (FileActionDialog.overwriteQuestion(targetFile.getName())) {
                    targetFile.delete();
                } else {
                    return null;
                }
            }
            FileUtil.zipDir(tmpDir.getAbsolutePath(), zos, tmpDir.getAbsolutePath());
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        } finally {
            PlatformUtil.delete(tmpDir, AbstractRepository.NULL_PROGRESS_MONITOR);
        }
        return null;
    }

    /**
     * @param diagramProcess
     * @param def
     */
    private String selectTargetDir() {
        String destFilePath = null;
        DirectoryDialog fd = new DirectoryDialog(Display.getCurrent().getActiveShell(), SWT.SAVE);
        fd.setMessage(Messages.exportProcessMessage);
        IDialogSettings settings = EnginePlugin.getDefault().getDialogSettings();
        fd.setFilterPath(settings.get(EnginePlugin.BAR_DEFAULT_PATH));
        fd.setText(Messages.exportProcessTitle);
        destFilePath = fd.open();
        if (destFilePath != null) {
            settings.put(EnginePlugin.BAR_DEFAULT_PATH, destFilePath);
        }
        return destFilePath;
    }

    /**
     * @return
     */
    private boolean acceptSave() {
        return MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                Messages.confirmSaveTitle, Messages.confirmSaveMessage);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (part instanceof DiagramEditor) {
            EObject root = ((DiagramEditor) part).getDiagramEditPart().resolveSemanticElement();
            return root instanceof MainProcess;
        }
        return false;
    }
}
