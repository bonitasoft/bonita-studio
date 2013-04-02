/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.actions;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.actions.wizards.ImportFileWizard;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ToProcProcessor;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Mickael Istria
 * @author Baptiste Mesta
 * 
 */
public class ImportFileCommand extends AbstractHandler {

    /**
     * {@link ExecutionEvent} Parameter key to set the NAME of the files to import.
     * Must be a list of names separated by ";"
     */
    public static final String FILE_NAMES = "fileNames";
    /**
     * {@link ExecutionEvent} parameter key to set the folder in which files are located
     */
    public static final String BASE_FOLDER = "baseFolder";
    /**
     * {@link ExecutionEvent}  parameter key to skip the overwrite question.
     * Set it to "true" or "false". Default value will be "false", and overwrite
     * question will open.
     */
    public static final String SKIP_OVERWRITE_QUESTION = "skipOverwriteQuestion";
    private String baseFolder;
    private String[] fileNames;
    public static boolean isTest = false;
    private ImporterFactory importerFactory;


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
     * ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        Shell shell = Display.getCurrent().getActiveShell();
        if(event !=null){
            baseFolder = event.getParameter(BASE_FOLDER);
            fileNames = event.getParameter(FILE_NAMES)!=null?event.getParameter(FILE_NAMES).split(";"):null;

        }
        if (baseFolder != null && fileNames != null) {
            try {
                importProcessWithProgressMonitor(shell, baseFolder, fileNames);
            } catch (Exception ex) {
                MessageDialog.openError(shell, "Could not import", ex.getMessage());
            }
        } else {
            String selected = null;
            String baseFolder = "";
            ImportFileWizard importFileWizard = new ImportFileWizard();
            new WizardDialog(shell, importFileWizard).open();
            selected = importFileWizard.getSelectedFilePath();
            if (selected == null) {
                return null;
            }
            File selectedFile = new File(selected);
            importerFactory = importFileWizard.getSelectedTransfo();
            baseFolder = selectedFile.getParent();
            String[] fileNames = new String[1];
            fileNames[0] = selectedFile.getName();
            if (selected != null) {
                try {
                    importProcessWithProgressMonitor(shell, baseFolder, fileNames);
                } catch (Exception ex) {
                    MessageDialog.openError(shell, "Could not import", ex.getMessage());
                    BonitaStudioLog.error("Import has failed for file "+ selectedFile.getName(), ApplicationPlugin.PLUGIN_ID);
                    BonitaStudioLog.error(ex,ApplicationPlugin.PLUGIN_ID);
                }
            }
        }

        OperationHistoryFactory.getOperationHistory().dispose(IOperationHistory.GLOBAL_UNDO_CONTEXT, true, true, true);
        return null;
    }

    /**
     * @param shell
     * @param string
     * @param file
     */
    protected void importProcessWithProgressMonitor(final Shell shell, final String baseFolder, final String[] fileNames) {
        IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
        IRunnableWithProgress runnable = new IRunnableWithProgress() {

            @Override
            public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                try {
                    monitor.beginTask(Messages.importProcessProgressDialog,IProgressMonitor.UNKNOWN);

                    for(final String fileName : fileNames){
                        Display.getDefault().syncExec(new Runnable() {

                            @Override
                            public void run() {
                                DiagramRepositoryStore diagramStore = null;//lazy initialization
                                File file = new File(baseFolder + File.separator + fileName);
                                try {
                                    boolean oldPopup = FileActionDialog.getDisablePopup();
                                    DiagramFileStore diagramFile = null;
                                    if (importerFactory != null) {
                                        final ToProcProcessor processor = importerFactory.createProcessor(file.getName()) ;
                                        processor.createDiagram(file.toURI().toURL(), monitor)  ;

                                        if(processor.getErrors().size()>0) {
                                            Display.getDefault().syncExec(new Runnable() {
                                                @Override
                                                public void run() {

                                                    String message = Messages.errorWhileImporting_message;
                                                    StringBuilder stringBuilder = new StringBuilder(message);
                                                    for (Object error : processor.getErrors()) {
                                                        stringBuilder.append('\n');
                                                        stringBuilder.append(error.toString());
                                                    }
                                                    MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.errorWhileImporting_title, stringBuilder.toString());
                                                }
                                            });
                                        }


                                        if(processor.getResources() != null){
                                            // final ResourceSet resourceSet = new ResourceSetImpl();
                                            for(File f : processor.getResources()){
                                                //    Resource resource = resourceSet.createResource(URI.createFileURI(f.getAbsolutePath()));

                                                //    resource.load(getLoadOptions());
                                                //    MainProcess process = (MainProcess) resource.getContents().get(0);
                                                //    String destFileName = NamingUtils.toDiagramFilename(process.getName(),process.getVersion());
                                                //    resource.unload();
                                                final FileInputStream fis = new FileInputStream(f);
                                                if(diagramStore == null){
                                                    diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
                                                }
                                                diagramFile = diagramStore.importInputStream(f.getName(), fis) ;
                                                fis.close();
                                                f.delete();
                                            }
                                        }

                                    }

                                    FileActionDialog.setDisablePopup(oldPopup);
                                    if(diagramFile != null){
                                        diagramFile.open();
                                    } else {
                                        /*if it is null it might be because
                                         * the import was cancelled
                                         * or was not working
                                         * so do nothing*/
                                    }
                                    PlatformUtil.closeIntro() ;
                                    PlatformUtil.openIntroIfNoOtherEditorOpen() ;

                                } catch (final Exception e) {
                                    BonitaStudioLog.error(e) ;
                                    Display.getDefault().syncExec(new Runnable() {

                                        @Override
                                        public void run() {
                                        	String message =  Messages.errorWhileImporting_message;
                                        	if(e.getMessage() != null && !e.getMessage().isEmpty()){
                                        		message = e.getMessage();
                                        	}
                                            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.errorWhileImporting_title,message,e).open();
                                        }
                                    }) ;

                                }
                            }


                        });
                    }

                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                    throw new InvocationTargetException(e);
                }
            }
        };

        try {
            progressManager.run(false,false,runnable);
        } catch (InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (InterruptedException e) {
            BonitaStudioLog.error(e);
        }

    }

    protected Map<Object, Object> getLoadOptions() {
        final Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        return options;
    }

}
