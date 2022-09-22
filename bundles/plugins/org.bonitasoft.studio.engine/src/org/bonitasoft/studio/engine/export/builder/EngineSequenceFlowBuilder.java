/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export.builder;

import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.studio.engine.export.DecisionTableUtil;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;


/**
 * @author Romain Bioteau
 *
 */
public class EngineSequenceFlowBuilder extends ProcessSwitch<SequenceFlow> {

	private final FlowElementBuilder builder;

	public EngineSequenceFlowBuilder(FlowElementBuilder processBuilder){
		builder = processBuilder ;
	}

	@Override
	public SequenceFlow caseSequenceFlow(SequenceFlow sequenceFlow) {
		SourceElement source = sequenceFlow.getSource();
		TargetElement target = sequenceFlow.getTarget();

		if(source == null ){
			throw new RuntimeException("Source of sequenceflow is null") ;
		}

		if( target == null){
			throw new RuntimeException("Target of sequenceflow is null") ;
		}

		String sourceId = source.getName();
		String targetId = target.getName();

		if(target instanceof ThrowLinkEvent || source instanceof CatchLinkEvent){/*link with catch or throw link event*/
			addLinkEvents(builder, sequenceFlow);
		} else {
			if(!(source instanceof ANDGateway)){
				final SequenceFlowConditionType conditionType = sequenceFlow.getConditionType();
				final Expression condition = sequenceFlow.getCondition();
				
				if(sequenceFlow.isIsDefault() && !(source instanceof BoundaryEvent)){
					builder.addDefaultTransition(sourceId, targetId);
				} else if(conditionType == SequenceFlowConditionType.EXPRESSION
						&& condition != null
						&& condition.getContent() != null
						&& !condition.getContent().isEmpty()){
					org.bonitasoft.engine.expression.Expression conditionExpression = EngineExpressionUtil.createExpression(condition);
					if(conditionExpression == null){
					    throw new RuntimeException("Condition expression "+condition.getName()+" on SequenceFlow from "+sourceId+" to "+targetId +" is invalid");
					}
                    builder.addTransition(sourceId, targetId, conditionExpression);
				}else if(conditionType == SequenceFlowConditionType.DECISION_TABLE){
					builder.addTransition(sourceId, targetId, EngineExpressionUtil.createExpression(DecisionTableUtil.toGroovyScriptExpression(sequenceFlow.getDecisionTable())));
				} else{
					builder.addTransition(sourceId, targetId);
				}
			}else{
				builder.addTransition(sourceId, targetId);
			}
		}

		return sequenceFlow;
	}

	protected void addLinkEvents(FlowElementBuilder builder, SequenceFlow sequenceFlow) {
		if(sequenceFlow.getTarget() instanceof ThrowLinkEvent){
			final ThrowLinkEvent throwLink = (ThrowLinkEvent) sequenceFlow.getTarget() ;
			if(null != throwLink.getTo()){
				final CatchLinkEvent target = throwLink.getTo() ;
				for(Connection c : target.getOutgoing()){
					String sourceId = sequenceFlow.getSource().getName();
					String targetId = c.getTarget().getName();
					org.bonitasoft.studio.model.expression.Expression transitionCondition = sequenceFlow.getCondition();
					if(sequenceFlow.isIsDefault()) {
						builder.addDefaultTransition(sourceId,targetId);
					}else if(null != transitionCondition && transitionCondition.getContent() != null && !transitionCondition.getContent().isEmpty()) {
						builder.addTransition(sourceId,targetId,EngineExpressionUtil.createExpression(transitionCondition));
					}else{
						builder.addTransition(sourceId,targetId);
					}

				}
			}else{
				throw new RuntimeException(Messages.bind(Messages.linkGoToIsNull,throwLink.getName()));
			}
		}
	}

}
