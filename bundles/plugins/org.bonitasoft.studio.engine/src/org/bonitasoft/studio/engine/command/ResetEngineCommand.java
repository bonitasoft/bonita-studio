/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
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

package org.bonitasoft.studio.engine.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.api.ProcessManagementAPI;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoCriterion;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 *
 */
public class ResetEngineCommand extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        final IProgressService progressManager = PlatformUI.getWorkbench().getProgressService() ;
        final IRunnableWithProgress runnable = new IRunnableWithProgress(){


            @Override
            public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                monitor.beginTask(Messages.resetingEngine, IProgressMonitor.UNKNOWN);
                APISession session = null;
                try {
                    session = BOSEngineManager.getInstance().loginDefaultTenant(monitor);
                } catch (final Exception e1) {
                    BonitaStudioLog.error(e1, EnginePlugin.PLUGIN_ID);
                }
                if(session == null){
                    return;
                }
                try {
                    final ProcessManagementAPI processManagementAPI = BOSEngineManager.getInstance().getProcessAPI(session);
                    final int nbProcess = (int) processManagementAPI.getNumberOfProcessDeploymentInfos() ;
                    if(nbProcess > 0){
                        final List<ProcessDeploymentInfo> processes = processManagementAPI.getProcessDeploymentInfos(0,nbProcess , ProcessDeploymentInfoCriterion.DEFAULT) ;
                        final List<Long> processIds = new ArrayList<Long>() ;
                        for(final ProcessDeploymentInfo info : processes){
                            processIds.add(info.getProcessId()) ;
                        }
                        for(final Long id: processIds){
                            processManagementAPI.disableAndDeleteProcessDefinition(id);
                        }

                    }
                } catch (final Exception e) {
                    throw new InvocationTargetException(e);
                }finally{
                    if(BOSEngineManager.getInstance() != null){
                        BOSEngineManager.getInstance().logoutDefaultTenant(session) ;
                    }
                }

                BOSWebServerManager.getInstance().resetServer(monitor);

                monitor.done();

            }
        };

        try {
            progressManager.run(true, false, runnable);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }


}
