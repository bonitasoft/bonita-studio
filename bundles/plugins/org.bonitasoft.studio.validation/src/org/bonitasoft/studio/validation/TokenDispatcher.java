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
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.FlowElement;
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

	public TokenDispatcher(SequenceFlow sequenceFlow){
		this.sequenceFlow = sequenceFlow;
	}

	public TokenDispatcher(){

	}

	public void setSequenceFlow(SequenceFlow sequenceFlow) {
		this.sequenceFlow = sequenceFlow;
	}

	public SequenceFlow getSequenceFlow() {
		return sequenceFlow;
	}

	public String getToken() {
		Assert.isNotNull(sequenceFlow);
		String token= null;	
		EObject sourceFlowElement = sequenceFlow.getSource();
		if(sourceFlowElement instanceof CatchLinkEvent){
			final CatchLinkEvent catchLink = (CatchLinkEvent) sourceFlowElement;
			if(catchLink.getFrom().isEmpty()){
				//Invalid catch link
				return null;
			}else if(catchLink.getFrom().size() > 0){
				token = getFirstIncomingSequenceFlow(catchLink.getFrom().get(0)).getPathToken();
			}
		}else if(sourceFlowElement instanceof FlowElement){
			if(isContinuous((FlowElement) sourceFlowElement)){ //Same token as previous one
				token = getFirstIncomingSequenceFlow((TargetElement) sourceFlowElement).getPathToken();
			}else if(isStartingFlowElement((FlowElement) sourceFlowElement)){ //Set initial token
				token = ModelHelper.getEObjectID(ModelHelper.getParentProcess(sourceFlowElement));
			}else if(isSplitting((FlowElement) sourceFlowElement)){
				token = ModelHelper.getEObjectID(sourceFlowElement);
			}else if(isMerging((FlowElement) sourceFlowElement)){
				token = getParentToken(sourceFlowElement);
			}
		}else if (sourceFlowElement instanceof BoundaryEvent){
			BoundaryEvent sourceBoundary = (BoundaryEvent) sourceFlowElement;
			FlowElement flowElement = ModelHelper.getParentFlowElement(sourceBoundary);
			if(sourceBoundary instanceof NonInterruptingBoundaryTimerEvent){//NON INTERRUPTING ARE LIKE SPLIT
				token = ModelHelper.getEObjectID(sourceBoundary);
			}else{//INTERRUPTING ARE SUBSTITUTION OF PARENT WITH ONE AND ONLY OUTPUT FLOW
				if(countIncomingSequenceFlows(flowElement) == 0){
					token = ModelHelper.getEObjectID(ModelHelper.getParentProcess(sourceFlowElement));
				}else if(countIncomingSequenceFlows(flowElement) == 1){
					SequenceFlow flow = getFirstIncomingSequenceFlow(flowElement);
					token = flow.getPathToken();
				}else{
					token = getParentToken(flowElement);
				}
			}
		}
		return token ;
	}

	protected String getParentToken(EObject sourceFlowElement) {
		String token;
		FlowElement lastSplit = (FlowElement) sourceFlowElement;
		while (isMerging(lastSplit)) {
			SequenceFlow flow = getFirstIncomingSequenceFlow(lastSplit);
			String ftoken = flow.getPathToken();
			if(ftoken == null || ftoken.isEmpty()){
				return null; //Will be compute later
			}
			if(!ftoken.equals( ModelHelper.getEObjectID(ModelHelper.getParentProcess(sourceFlowElement)))){
				final EObject tokenSourceElement = lastSplit.eResource().getEObject(ftoken);
				if(tokenSourceElement instanceof FlowElement){
					lastSplit = (FlowElement) tokenSourceElement;
				}
			}else{
				break;
			}
		}
		SequenceFlow flow = getFirstIncomingSequenceFlow(lastSplit);
		token = flow.getPathToken();
		return token;
	}

	protected SequenceFlow getFirstIncomingSequenceFlow(TargetElement sourceFlowElement) {
		TargetElement tagetElement = sourceFlowElement;
		if(sourceFlowElement instanceof CatchLinkEvent){
			tagetElement = ((CatchLinkEvent) sourceFlowElement).getFrom().get(0);
		}
		if(tagetElement != null){
			for(org.bonitasoft.studio.model.process.Connection c : tagetElement.getIncoming()){
				if(c instanceof SequenceFlow){
					//					if(c.getSource() instanceof CatchLinkEvent){
					//						CatchLinkEvent event =  (CatchLinkEvent) c.getSource();
					//						if(!event.getFrom().isEmpty()){
					//							ThrowLinkEvent tEvent =event.getFrom().get(0);
					//							if(!tEvent.getIncoming().isEmpty()){
					//								return (SequenceFlow) tEvent.getIncoming().get(0);
					//							}
					//						}
					//					}
					return (SequenceFlow) c;
				}
			}
		}
		return null;
	}


	protected boolean isSplitting(FlowElement sourceFlowElement) {
		int cpt = countOutgoingSequenceFlows(sourceFlowElement);
		return cpt > 1;
	}


	protected int countOutgoingSequenceFlows(SourceElement sourceFlowElement) {
		int cpt =0 ;
		for(org.bonitasoft.studio.model.process.Connection c : sourceFlowElement.getOutgoing()){
			if(c instanceof SequenceFlow){
				TargetElement elem = c.getTarget();
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


	protected boolean isMerging(FlowElement sourceFlowElement) {
		int cpt = countIncomingSequenceFlows(sourceFlowElement);
		return cpt > 1;
	}


	protected boolean isStartingFlowElement(FlowElement sourceFlowElement) {
		int cpt = countIncomingSequenceFlows(sourceFlowElement);
		return cpt == 0 && !(sourceFlowElement instanceof CatchLinkEvent); 
	}


	protected int countIncomingSequenceFlows(FlowElement sourceFlowElement) {
		int cpt = 0 ;
		for(org.bonitasoft.studio.model.process.Connection c : sourceFlowElement.getIncoming()){
			if(c instanceof SequenceFlow){
				SourceElement elem = c.getSource();
				if(elem instanceof CatchLinkEvent){
					for(ThrowLinkEvent event : ((CatchLinkEvent) elem).getFrom()){
						cpt = cpt + event.getIncoming().size();
					}
				}else{
					cpt++;
				}
			}
		}
		return cpt;
	}


	protected boolean isContinuous(FlowElement sourceFlowElement) {
		return countOutgoingSequenceFlows(sourceFlowElement) == 1 &&
				countIncomingSequenceFlows(sourceFlowElement) == 1 ;
	}

	public void recomputeAllToken(Pool process) {
		List<FlowElement> flowElements = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.FLOW_ELEMENT);
		List<SequenceFlow> allSequenceFlow = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.SEQUENCE_FLOW);
		for(SequenceFlow sflow : allSequenceFlow){
			sflow.eSetDeliver(false);
			sflow.setPathToken("");
		}
		List<FlowElement> startingElements = new ArrayList<FlowElement>();
		for(FlowElement flowElement : flowElements){
			if(isStartingFlowElement(flowElement)){
				startingElements.add(flowElement);
			}
		}
		for(FlowElement start : startingElements){
			visit(start);
		}
		if(BonitaStudioLog.isLoggable(IStatus.OK)){
			StringBuilder sb = new StringBuilder();
			sb.append("Validating Inclusive Merge...\n");
			for(SequenceFlow sf : allSequenceFlow){
				sb.append(sf.getSource().getName()+"->"+sf.getTarget().getName()+"="+sf.getPathToken());
				sb.append("\n");
			}
			BonitaStudioLog.debug(sb.toString(), ValidationPlugin.PLUGIN_ID);
		}
	}


	private void visit(SourceElement sourceElement) {
		boolean allOutgoingFlowsHaveToken = true;
		for(Connection c : sourceElement.getOutgoing()){
			if(c instanceof SequenceFlow && (((SequenceFlow) c).getPathToken() == null || ((SequenceFlow) c).getPathToken().isEmpty())){
				setSequenceFlow((SequenceFlow) c);
				final String token = getToken();
				c.eSetDeliver(false);
				c.eSet(ProcessPackage.Literals.SEQUENCE_FLOW__PATH_TOKEN, token);
				allOutgoingFlowsHaveToken = false;
			}
		}
		if(!allOutgoingFlowsHaveToken){
			for(Connection c : sourceElement.getOutgoing()){
				if(c instanceof SequenceFlow){
					EObject target = c.getTarget();
					if(target instanceof ThrowLinkEvent){
						visit((SourceElement) ((ThrowLinkEvent)target).getTo());
					}else{
						if(target instanceof Activity){
							for(BoundaryEvent event : ((Activity) target).getBoundaryIntermediateEvents()){
								visit(event);
							}
						}
						if(target instanceof FlowElement){
							visit((SourceElement) target);
						}
					}
				}
			}
		}
	}
}
