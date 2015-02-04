/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.InclusiveGateway;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class TokenDispatcher {

    private SequenceFlow sequenceFlow;

    public TokenDispatcher(final SequenceFlow sequenceFlow){
        this.sequenceFlow = sequenceFlow;
    }

    public TokenDispatcher(){

    }

    public void setSequenceFlow(final SequenceFlow sequenceFlow) {
        this.sequenceFlow = sequenceFlow;
    }

    public SequenceFlow getSequenceFlow() {
        return sequenceFlow;
    }

    public String getToken() {
        Assert.isNotNull(sequenceFlow);
        String token= null;
        final EObject sourceFlowElement = sequenceFlow.getSource();
        if(sourceFlowElement instanceof CatchLinkEvent){
            final CatchLinkEvent catchLink = (CatchLinkEvent) sourceFlowElement;
            if(catchLink.getFrom().isEmpty()){
                //Invalid catch link
                return null;
            }else if(catchLink.getFrom().size() > 0){
                token = getFirstIncomingSequenceFlow(catchLink.getFrom().get(0)).getPathToken();
            }
        }else if(sourceFlowElement instanceof FlowElement){
            if (isStartingFlowElement((FlowElement) sourceFlowElement)) { //Set initial token
                token = createToken(ModelHelper.getParentProcess(sourceFlowElement));
            } else if (isContinuous((FlowElement) sourceFlowElement)) { //Same token as previous one
                token = getFirstIncomingSequenceFlow((TargetElement) sourceFlowElement).getPathToken();
            }else if(isSplitting((FlowElement) sourceFlowElement)){
                token = createToken(sourceFlowElement);
            }else if(isMerging((FlowElement) sourceFlowElement)){
                token = getIncomingToken(sourceFlowElement);
            } else {
                if (allIncomingTokenSet((FlowElement) sourceFlowElement)) {
                    if (sameTokenForAllIncomingFlows((FlowElement) sourceFlowElement)) {
                        token = getFirstIncomingSequenceFlow((TargetElement) sourceFlowElement).getPathToken();//Like continuous
                    } else {
                        token = createToken(sourceFlowElement);//Like a split
                    }
                } else {
                    return null;//Computed later
                }

            }
        }else if (sourceFlowElement instanceof BoundaryEvent){
            final BoundaryEvent sourceBoundary = (BoundaryEvent) sourceFlowElement;
            final FlowElement flowElement = ModelHelper.getParentFlowElement(sourceBoundary);
            if(sourceBoundary instanceof NonInterruptingBoundaryTimerEvent){//NON INTERRUPTING ARE LIKE SPLIT
                token = createToken(sourceBoundary);
            }else{//INTERRUPTING ARE SUBSTITUTION OF PARENT WITH ONE AND ONLY OUTPUT FLOW
                if(countIncomingSequenceFlows(flowElement) == 0){
                    token = createToken(ModelHelper.getParentProcess(sourceFlowElement));
                }else if(countIncomingSequenceFlows(flowElement) == 1){
                    final SequenceFlow flow = getFirstIncomingSequenceFlow(flowElement);
                    token = flow.getPathToken();
                }else{
                    token = getIncomingToken(flowElement);
                }
            }
        }
        return token ;
    }

    protected String createToken(final EObject eObject) {
        return ModelHelper.getEObjectID(eObject);
    }

    private boolean allIncomingTokenSet(final FlowElement sourceFlowElement) {
        for (final org.bonitasoft.studio.model.process.Connection c : sourceFlowElement.getIncoming()) {
            if (c instanceof SequenceFlow) {
                final String pathToken = ((SequenceFlow) c).getPathToken();
                if (pathToken == null || pathToken.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    protected String getIncomingToken(final EObject sourceFlowElement) {
        String token;
        FlowElement lastSplit = (FlowElement) sourceFlowElement;
        while (isMerging(lastSplit)) {
            final SequenceFlow flow = getFirstIncomingSequenceFlow(lastSplit);
            final String ftoken = flow.getPathToken();
            if(ftoken == null || ftoken.isEmpty()){
                return null; //Will be compute later
            }
            if (!ftoken.equals(createToken(ModelHelper.getParentProcess(sourceFlowElement)))) {
                final EObject tokenSourceElement = lastSplit.eResource().getEObject(ftoken);
                if (tokenSourceElement instanceof FlowElement) {
                    lastSplit = (FlowElement) tokenSourceElement;
                } else if (tokenSourceElement instanceof NonInterruptingBoundaryTimerEvent) {
                    break;
                }
            }else{
                break;
            }
        }
        final SequenceFlow flow = getFirstIncomingSequenceFlow(lastSplit);
        token = flow.getPathToken();
        return token;
    }

    protected SequenceFlow getFirstIncomingSequenceFlow(final TargetElement sourceFlowElement) {
        TargetElement tagetElement = sourceFlowElement;
        SequenceFlow incomingFlow = null;
        if(sourceFlowElement instanceof CatchLinkEvent){
            tagetElement = ((CatchLinkEvent) sourceFlowElement).getFrom().get(0);
        }
        if(tagetElement != null){
            for(final org.bonitasoft.studio.model.process.Connection c : tagetElement.getIncoming()){
                if(c instanceof SequenceFlow){
                    incomingFlow = (SequenceFlow)c;
                    if (incomingFlow.getPathToken()!=null && !incomingFlow.getPathToken().isEmpty()){
                        return incomingFlow;
                    }
                }
            }
        }
        return incomingFlow;
    }


    protected boolean isSplitting(final FlowElement sourceFlowElement) {
        final int cpt = countOutgoingSequenceFlows(sourceFlowElement);
        return cpt > 1;
    }


    protected int countOutgoingSequenceFlows(final SourceElement sourceFlowElement) {
        int cpt =0 ;
        for(final org.bonitasoft.studio.model.process.Connection c : sourceFlowElement.getOutgoing()){
            if(c instanceof SequenceFlow){
                final TargetElement elem = c.getTarget();
                if(elem instanceof ThrowLinkEvent){
                    final CatchLinkEvent event = ((ThrowLinkEvent) elem).getTo();
                    cpt = cpt + event.getOutgoing().size();
                }else{
                    cpt++;
                }
            }
        }
        return cpt;
    }


    protected boolean isMerging(final FlowElement sourceFlowElement) {
        return countIncomingSequenceFlows(sourceFlowElement) > 1 && (sourceFlowElement instanceof InclusiveGateway || sourceFlowElement instanceof ANDGateway);
    }


    protected boolean isStartingFlowElement(final FlowElement sourceFlowElement) {
        final int cpt = countIncomingSequenceFlows(sourceFlowElement);
        return cpt == 0 && !(sourceFlowElement instanceof CatchLinkEvent);
    }


    protected int countIncomingSequenceFlows(final FlowElement sourceFlowElement) {
        int cpt = 0 ;
        for(final org.bonitasoft.studio.model.process.Connection c : sourceFlowElement.getIncoming()){
            if(c instanceof SequenceFlow){
                final SourceElement elem = c.getSource();
                if(elem instanceof CatchLinkEvent){
                    for(final ThrowLinkEvent event : ((CatchLinkEvent) elem).getFrom()){
                        cpt = cpt + event.getIncoming().size();
                    }
                }else{
                    cpt++;
                }
            }
        }
        return cpt;
    }


    protected boolean isContinuous(final FlowElement sourceFlowElement) {
        if (sourceFlowElement instanceof ANDGateway || sourceFlowElement instanceof InclusiveGateway) {
            return countOutgoingSequenceFlows(sourceFlowElement) == 1 && countIncomingSequenceFlows(sourceFlowElement) == 1;
        }
        return true;

    }

    protected boolean sameTokenForAllIncomingFlows(final FlowElement sourceFlowElement) {
        String lastToken = null;
        for (final org.bonitasoft.studio.model.process.Connection c : sourceFlowElement.getIncoming()) {
            if (c instanceof SequenceFlow) {
                final String pathToken = ((SequenceFlow) c).getPathToken();
                if (lastToken != null && pathToken != null && !pathToken.equals(lastToken)) {
                    return false;
                }
                lastToken = pathToken;
            }
        }
        return true;
    }

    public void recomputeAllToken(final Pool process) {
        final List<FlowElement> flowElements = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.FLOW_ELEMENT);
        final List<SequenceFlow> allSequenceFlow = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.SEQUENCE_FLOW);
        for(final SequenceFlow sflow : allSequenceFlow){
            setToken(sflow, "");
        }
        final List<FlowElement> startingElements = new ArrayList<FlowElement>();
        for(final FlowElement flowElement : flowElements){
            if(isStartingFlowElement(flowElement)){
                startingElements.add(flowElement);
            }
        }
        for(final FlowElement start : startingElements){
            visit(start);
        }
        if(BonitaStudioLog.isLoggable(IStatus.OK)){
            final StringBuilder sb = new StringBuilder();
            sb.append("Validating Inclusive Merge...\n");
            for(final SequenceFlow sf : allSequenceFlow){
                sb.append(sf.getSource().getName()+"->"+sf.getTarget().getName()+"="+sf.getPathToken());
                sb.append("\n");
            }
            BonitaStudioLog.debug(sb.toString(), ValidationPlugin.PLUGIN_ID);
        }
    }

    protected void setToken(final SequenceFlow sflow, final String token) {
        sflow.eSetDeliver(false);
        sflow.eSet(ProcessPackage.Literals.SEQUENCE_FLOW__PATH_TOKEN, token);
        sflow.eSetDeliver(true);
    }


    private void visit(final SourceElement sourceElement) {
        BonitaStudioLog.debug("Token dispatcher visiting " + sourceElement.getName(), ValidationPlugin.PLUGIN_ID);
        boolean allOutgoingFlowsHaveToken = true;
        for(final Connection c : sourceElement.getOutgoing()){
            if(c instanceof SequenceFlow && (((SequenceFlow) c).getPathToken() == null || ((SequenceFlow) c).getPathToken().isEmpty())){
                setSequenceFlow((SequenceFlow) c);
                final String token = getToken();
                setToken((SequenceFlow) c, token);
                allOutgoingFlowsHaveToken = false;
            }
        }
        if(!allOutgoingFlowsHaveToken){
            for(final Connection c : sourceElement.getOutgoing()){
                if(c instanceof SequenceFlow){
                    final EObject target = c.getTarget();
                    if (!sourceElement.equals(target)) {
                        if (target instanceof ThrowLinkEvent) {
                            visit(((ThrowLinkEvent) target).getTo());
                        } else {
                            if (target instanceof Activity) {
                                for (final BoundaryEvent event : ((Activity) target).getBoundaryIntermediateEvents()) {
                                    visit(event);
                                }
                            }
                            if (target instanceof FlowElement) {
                                visit((SourceElement) target);
                            }
                        }
                    }
                }
            }
        }
    }
}
