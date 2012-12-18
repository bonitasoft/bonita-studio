/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 */
package org.bonitasoft.studio.simulation.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.bonitasoft.studio.simulation.repository.SimulationResourceRepositoryStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * 
 * @author Baptiste Mesta
 * 
 *         Handler that launch a dialog to export artifacts to a zip file
 * 
 */
public class ImportSimulationArtifact extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        List<IRepositoryStore> input = new ArrayList<IRepositoryStore>() ;
        final SimulationResourceRepositoryStore resourcesRepo = (SimulationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class) ;
        final SimulationLoadProfileRepositoryStore loadProfilesRepo = (SimulationLoadProfileRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class) ;
        input.add(resourcesRepo) ;
        input.add(loadProfilesRepo) ;

        // These filter extensions are used to filter which files are displayed.
        String[] FILTER_EXTS = { "*." + resourcesRepo.getCompatibleExtensions().toArray()[0] + ";*." + loadProfilesRepo.getCompatibleExtensions().toArray()[0]  + "" };

        FileDialog dialog = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN | SWT.MULTI);
        dialog.setFilterExtensions(FILTER_EXTS);
        final String filesNames = dialog.open();
        if (filesNames != null) {
            final String[] files = dialog.getFileNames();
            IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
            IRunnableWithProgress runnable = new IRunnableWithProgress() {
                @Override
                public void run(final org.eclipse.core.runtime.IProgressMonitor monitor) throws java.lang.reflect.InvocationTargetException,
                InterruptedException {
                    File parent = new File(filesNames).getParentFile();

                    for (int i = 0, n = files.length; i < n; i++) {
                        final File file = new File(parent, files[i]);
                        if (file.getName().endsWith((String) resourcesRepo.getCompatibleExtensions().toArray()[0])) {
                            final FileInputStream is;
                            try {
                                is = new FileInputStream(file);
                                Display.getDefault().syncExec(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            resourcesRepo.importInputStream(file.getName(), is);
                                        } catch (Exception e) {
                                            BonitaStudioLog.error(e);
                                        }
                                    }
                                });
                                try {
                                    is.close();
                                } catch (IOException e) {
                                    BonitaStudioLog.error(e);
                                }
                            } catch (FileNotFoundException e) {
                                BonitaStudioLog.error(e);
                            }
                        } else if (file.getName().endsWith((String) loadProfilesRepo.getCompatibleExtensions().toArray()[0])) {
                            final FileInputStream is;
                            try {
                                is = new FileInputStream(file);
                                Display.getDefault().syncExec(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            loadProfilesRepo.importInputStream(file.getName(), is                                                                                                                                                                                                                                                   );
                                        } catch (Exception e) {
                                            BonitaStudioLog.error(e);
                                        }
                                    }
                                });
                                try {
                                    is.close();
                                } catch (IOException e) {
                                    BonitaStudioLog.error(e);
                                }
                            } catch (FileNotFoundException e) {
                                BonitaStudioLog.error(e);
                            }
                        }

                    }
                };
            };
            try {
                progressManager.busyCursorWhile(runnable);
            } catch (InvocationTargetException e) {
                BonitaStudioLog.error(e);
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
            }

        }

        return null;
    }

}
