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

package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.CatchMessageEvent;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CustomMessageFlowEditPart extends MessageFlowEditPart {


	public CustomMessageFlowEditPart(View view) {
		super(view);
	}

	@Override
	protected void handleNotificationEvent(Notification notification) {
		Object feature = notification.getFeature();
		if(feature.equals(NotationPackage.Literals.ROUTING_STYLE__CLOSEST_DISTANCE)){
			refreshRouterChange() ;
			refreshSourceAnchor();
			refreshTargetAnchor();
		}
		if(feature.equals(ProcessPackage.Literals.MESSAGE_FLOW__TARGET)){
			updateMessageFlow(notification);
		}
		super.handleNotificationEvent(notification);
	}

	private void updateMessageFlow(Notification notification) {
		MessageFlow messageFlow = (MessageFlow)resolveSemanticElement();
		if(messageFlow==null){
			return ;
		}
		String messageFlowName = messageFlow.getName();

		// old CatchMessage
		Object object = notification.getOldValue();
		if(object instanceof CatchMessageEvent){
			CatchMessageEvent oldTarget = (CatchMessageEvent) object;
			oldTarget.setIncomingMessag(null);
			oldTarget.setEvent("");

		}

		// new CatchMessage
		object = notification.getNewValue();
		if(object instanceof CatchMessageEvent){

			// update CatchMessage
			CatchMessageEvent newTarget = (CatchMessageEvent) object;
			newTarget.setIncomingMessag(messageFlow);
			newTarget.setEvent(messageFlowName);

			// update messageFlow
			messageFlow.setTarget(newTarget);
			ThrowMessageEvent throwMessage = messageFlow.getSource();

			// update message
			for( Message message : throwMessage.getEvents()){
				if(message.getName().equals(messageFlowName)){

					// set CathMessageEvent in the Message
					Expression expr = ExpressionHelper.createConstantExpression(newTarget.getName(), String.class.getName() );
					message.setTargetElementExpression(expr);

					// set pool of the target in the Message
					expr = ExpressionHelper.createConstantExpression(ModelHelper.getParentProcess(newTarget).getName(), String.class.getName() );
					message.setTargetProcessExpression(expr);
					break;
				}
			}
		}
	}
	
}
