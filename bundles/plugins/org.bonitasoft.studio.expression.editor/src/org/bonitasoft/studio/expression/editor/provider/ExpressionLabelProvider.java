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
package org.bonitasoft.studio.expression.editor.provider;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.autocompletion.ExpressionProposal;
import org.bonitasoft.studio.expression.editor.autocompletion.IExpressionProposalLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CatchMessageEvent;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ExpressionLabelProvider extends LabelProvider implements IExpressionProposalLabelProvider {


	@Override
	public Image getImage(Object expression) {
		if(expression instanceof ExpressionProposal){
			expression = ((ExpressionProposal) expression).getExpression() ; 
		}
		if(expression instanceof Expression){
			for(IExpressionProvider provider : ExpressionEditorService.getInstance().getExpressionProviders()){
				if(provider.getExpressionType().equals(((Expression) expression).getType())){
					return 	provider.getIcon((Expression) expression) ;
				}
			}
			if(ExpressionConstants.CONSTANT_TYPE.equals(((Expression) expression).getType()) && ((Expression) expression).getContent() != null && !((Expression) expression).getContent().isEmpty()){
				return Pics.getImage(PicsConstants.constant) ;
			}

		}
		return super.getImage(expression) ;
	}

	@Override
	public String getText(Object expression) {
		if(expression instanceof ExpressionProposal){
			expression = ((ExpressionProposal) expression).getExpression() ; 
		}
		if(expression instanceof Expression){
			for(IExpressionProvider provider : ExpressionEditorService.getInstance().getExpressionProviders()){
				if(provider.getExpressionType().equals(((Expression) expression).getType())){
					return 	provider.getProposalLabel((Expression) expression) ;
				}
			}
			if(ExpressionConstants.CONSTANT_TYPE.equals(((Expression) expression).getType()) && !((Expression) expression).getContent().isEmpty()){
				return ((Expression) expression).getName() ;
			}

		}
		return super.getText(expression) ;
	}

	@Override
	public String getDescription(Expression expression) {
		if(ExpressionConstants.MESSAGE_ID_TYPE.equals(expression.getType())){
			//			final FlowElement parentFlowElement = ModelHelper.getParentFlowElement(expression);
			//			final AbstractProcess parentProcess = ModelHelper.getParentProcess(expression);
			//			if(parentFlowElement instanceof AbstractCatchMessageEvent){
			//				MessageFlow incomingMessag = ((AbstractCatchMessageEvent) parentFlowElement).getIncomingMessag();
			//				if(incomingMessag != null){
			//					final ThrowMessageEvent source = incomingMessag.getSource();
			//					EList<Message> events = source.getEvents();
			//					for (Message message : events) {
			//						Expression targetProcessExpression = message.getTargetProcessExpression();						
			//						if(ExpressionConstants.CONSTANT_TYPE.equals(targetProcessExpression.getType())){
			//							if(parentProcess.getName().equals(targetProcessExpression.getContent())){
			//								Expression targetElementExpression = message.getTargetElementExpression();
			//								if(ExpressionConstants.CONSTANT_TYPE.equals(targetElementExpression.getType())){
			//									if(parentFlowElement.getName().equals(targetElementExpression.getContent())){
			//										//TableExpression messageContent = message.getMessageContent();
			//										//TODO: implement return type for message content
			//									}
			//								}
			//							}
			//						}
			//					}
			//				}
			//			}
			return "";
		}
		return expression.getReturnType();
	}

	@Override
	public String getContent(Expression expression) {
		return expression.getContent();
	}

}
