/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.edit.custom.decision;

import org.bonitasoft.studio.model.edit.custom.i18n.Messages;
import org.bonitasoft.studio.model.process.decision.DecisionTableLine;
import org.bonitasoft.studio.model.process.decision.provider.DecisionTableLineItemProvider;
import org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction;
import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * @author Romain Bioteau
 *
 */
public class CustomDecisionTableLineItemProvider extends DecisionTableLineItemProvider {

	public CustomDecisionTableLineItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		DecisionTableLine line = (DecisionTableLine) object;
		if(line.getAction() instanceof TakeTransitionAction){
			if(((TakeTransitionAction)line.getAction()).isTakeTransition()){
				return Messages.line + " : " + Messages.takeTransitionAction;
			}else{
				return Messages.line + " : " + Messages.dontTakeTransitionAction;
			}
		}
	
		return super.getText(object);
	}

}
