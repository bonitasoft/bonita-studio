/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 */
package org.bonitasoft.studio.simulation.commands;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.bonitasoft.studio.simulation.repository.SimulationResourceRepositoryStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * 
 * @author Baptiste Mesta
 * 
 *         Handler that launch a dialog to export artifacts to a zip file
 * 
 */
public class ExportSimulationArtifacts extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        List<IRepositoryStore<? extends IRepositoryFileStore>> input = new ArrayList<IRepositoryStore<? extends IRepositoryFileStore>>() ;
        input.add(RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class)) ;
        input.add(RepositoryManager.getInstance().getRepositoryStore(SimulationLoadProfileRepositoryStore.class)) ;
        CommonRepositoryPlugin.exportArtifactsToFile(input,null, null);

        return null;
    }
}
