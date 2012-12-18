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

import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.eclipse.emf.common.notify.Notification;
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
		super.handleNotificationEvent(notification);
	}
	
}
