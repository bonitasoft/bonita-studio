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
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;
import org.eclipse.core.runtime.Assert;
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
		if(sourceFlowElement instanceof FlowElement){
			if(isContinuous((FlowElement) sourceFlowElement)){ //Same token as previous one
				token = getFirstIncomingSequenceFlow(sourceFlowElement).getPathToken();
			}else if(isStartingFlowElement((FlowElement) sourceFlowElement)){ //Set initial token
				if(!(sourceFlowElement instanceof CatchLinkEvent)){ 
					token = ModelHelper.getEObjectID(ModelHelper.getParentProcess(sourceFlowElement));
				}else{
					//TODO catch link event use case
				}
			}else if(isSplitting((FlowElement) sourceFlowElement)){
				token = ModelHelper.getEObjectID(sourceFlowElement);
			}else if(isMerging((FlowElement) sourceFlowElement)){
				SequenceFlow sflow = getFirstIncomingSequenceFlow(sourceFlowElement);
				String firstToken = sflow.getPathToken();
				if(!firstToken.equals( ModelHelper.getEObjectID(ModelHelper.getParentProcess(sourceFlowElement)))){
					final EObject firstTokenSourceElement = sourceFlowElement.eResource().getEObject(firstToken);
					if(firstTokenSourceElement != null && firstTokenSourceElement instanceof FlowElement){
						if(isMerging((FlowElement) firstTokenSourceElement)){

						}else if(isContinuous( (FlowElement) firstTokenSourceElement) || isSplitting((FlowElement) firstTokenSourceElement)){
							token = getFirstIncomingSequenceFlow(firstTokenSourceElement).getPathToken();
						}
					}
				}else{
					token = ModelHelper.getEObjectID(sourceFlowElement);
				} 
			}
		}
		return token ;
	}

	protected SequenceFlow getFirstIncomingSequenceFlow(EObject sourceFlowElement) {
		for(org.bonitasoft.studio.model.process.Connection c : ((TargetElement) sourceFlowElement).getIncoming()){
			if(c instanceof SequenceFlow){
				return (SequenceFlow) c;
			}
		}
		return null;
	}


	protected boolean isSplitting(FlowElement sourceFlowElement) {
		int cpt = countOutgoingSequenceFlows(sourceFlowElement);
		return cpt > 1;
	}


	protected int countOutgoingSequenceFlows(FlowElement sourceFlowElement) {
		int cpt =0 ;
		for(org.bonitasoft.studio.model.process.Connection c : ((SourceElement) sourceFlowElement).getOutgoing()){
			if(c instanceof SequenceFlow){
				cpt++;
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
		return cpt == 0;
	}


	protected int countIncomingSequenceFlows(FlowElement sourceFlowElement) {
		int cpt = 0 ;
		for(org.bonitasoft.studio.model.process.Connection c : ((TargetElement) sourceFlowElement).getIncoming()){
			if(c instanceof SequenceFlow){
				cpt++;
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
		List<FlowElement> startingElements = new ArrayList<FlowElement>();
		for(FlowElement flowElement : flowElements){
			if(isStartingFlowElement(flowElement)){
				startingElements.add(flowElement);
			}
		}
		for(FlowElement start : startingElements){
			visit(start);
		}
		
	}

	private void visit(FlowElement flowElement) {
		for(Connection c : flowElement.getOutgoing()){
			if(c instanceof SequenceFlow){
				setSequenceFlow((SequenceFlow) c);
				final String token = getToken();
				c.eSetDeliver(false);
				c.eSet(ProcessPackage.Literals.SEQUENCE_FLOW__PATH_TOKEN, token);
			}
		}
		for(Connection c : flowElement.getOutgoing()){
			if(c instanceof SequenceFlow){
				EObject target = c.getTarget();
				if(target instanceof FlowElement){
					visit((FlowElement) target);
				}
			}
		}
	}


}
