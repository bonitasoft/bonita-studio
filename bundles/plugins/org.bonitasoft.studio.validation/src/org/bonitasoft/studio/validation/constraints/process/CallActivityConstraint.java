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
package org.bonitasoft.studio.validation.constraints.process;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * @author Baptiste Mesta
 * 
 */
public class CallActivityConstraint extends AbstractLiveValidationMarkerConstraint {


    @Override
    protected IStatus performLiveValidation(IValidationContext ctx) {
        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext ctx) {
        CallActivity subProcess = (CallActivity) ctx.getTarget();
        final DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final Expression subprocessName = subProcess.getCalledActivityName();
        if(subprocessName == null || subprocessName.getContent() == null || subprocessName.getContent().isEmpty()){
            return ctx.createFailureStatus(new Object[] { Messages.Validation_NoSubProcess });
        }
        final Expression subprocessVersion = subProcess.getCalledActivityVersion();
        AbstractProcess subProc = diagramStore.findProcess(subprocessName.getContent(), subprocessVersion.getContent());

        if(subProc == null){
            return ctx.createSuccessStatus( );
        }

        //        if(subProcesses.isEmpty()){
        //            return ctx.createSuccessStatus();
        //        }else{
        //            if(subProcesses.size() == 1){
        //                subProc = subProcesses.get(0) ;
        //            }else{
        //                /*Search for the correct version*/
        //                String subprocessVersion = subProcess.getCalledActivityVersion();
        //                if(subprocessVersion == null || subprocessVersion.equals(org.bonitasoft.studio.properties.i18n.Messages.latestLabel)) {
        //                    /*Find the latest in the repository?*/
        //                    subProc = subProcesses.get(0);
        //                } else {
        //                    List<AbstractProcess> subProcMatchingVersion = new ArrayList<AbstractProcess>();
        //                    for(AbstractProcess ap : subProcesses){
        //                        if(subprocessVersion.equals(ap.getVersion())){
        //                            subProcMatchingVersion.add(ap);
        //                        }
        //                    }
        //                    if(subProcMatchingVersion.isEmpty()){
        //                        /*version might be a groovy expression*/
        //                        subProc = subProcesses.get(0) ;
        //                    } else if(subProcMatchingVersion.size() == 1){
        //                        subProc = subProcesses.get(0) ;
        //                    } else {
        //                        /*There are several pools with same id/version*/
        //                        return ctx.createFailureStatus(new Object[] {Messages.bind(Messages.Validation_DuplicateID_Subprocess,subprocessName)});
        //                    }
        //                }
        //            }
        //        }

        List<Data> data = ModelHelper.getAccessibleData(subProc);

        for(OutputMapping out : subProcess.getOutputMappings()){
            if(out.getProcessTarget() == null){
                return ctx.createFailureStatus(new Object[] {Messages.bind(Messages.Validation_Subprocess_OutputMapping_SourceData_Not_Found,out.getSubprocessSource())});
            }
            if(!exist(out.getSubprocessSource(),data)){
                return ctx.createFailureStatus(new Object[] {Messages.bind(Messages.Validation_Subprocess_OutputMapping_SourceData_Not_Found,out.getSubprocessSource())});
            }
        }
        for(InputMapping in : subProcess.getInputMappings()){
            if(in.getProcessSource() == null){
                return ctx.createFailureStatus(new Object[] {Messages.bind(Messages.Validation_Subprocess_InputMapping_TargetData_Not_Found,in.getSubprocessTarget())});
            }
            if(!exist(in.getSubprocessTarget(),data)){
                return ctx.createFailureStatus(new Object[] {Messages.bind(Messages.Validation_Subprocess_InputMapping_TargetData_Not_Found,in.getSubprocessTarget())});
            }
        }

        return ctx.createSuccessStatus();
    }

    private boolean exist(String subprocessTarget, List<Data> data) {
        for(Data d : data){
            if(subprocessTarget != null && subprocessTarget.equals(d.getName())){
                return true ;
            }
        }
        return false;
    }


    @Override
    protected String getMarkerType(DiagramEditor editor) {
        return ProcessMarkerNavigationProvider.MARKER_TYPE;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.CallActivity";
    }

}
