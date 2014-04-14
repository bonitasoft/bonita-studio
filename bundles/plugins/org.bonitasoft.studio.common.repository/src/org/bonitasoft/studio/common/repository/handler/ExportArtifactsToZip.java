/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 */

package org.bonitasoft.studio.common.repository.handler;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.ui.wizard.ExportRepositoryWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Romain Bioteau
 *
 *	Handler that launch a dialog to export artifacts to a zip file
 *
 */
public class ExportArtifactsToZip extends AbstractHandler {


    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        String defaultName = currentRepository.getName() + "_" + new SimpleDateFormat("ddMMyy_HHmm").format(new Date()) + ".bos";
        Set<Object> selectedFiles = new HashSet<Object>() ;
        for(IRepositoryStore store : currentRepository.getAllExportableStores()){
            List<IRepositoryFileStore> files = store.getChildren() ;
            if( files != null){
                files.remove(null) ;
                selectedFiles.addAll(files) ;
            }
        }

        ExportRepositoryWizard wizard = new ExportRepositoryWizard(currentRepository.getAllExportableStores(),true,selectedFiles,defaultName,Messages.exportRepositoryTitle) ;
        WizardDialog dialog = new WizardDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                wizard ){
        	protected void initializeBounds() {
        		super.initializeBounds();
        		getShell().setSize(500, 500); 
        	}
        };
        dialog.setTitle(Messages.exportArtifactsWizard_title);
        dialog.open() ;
        return null;
    }

}
