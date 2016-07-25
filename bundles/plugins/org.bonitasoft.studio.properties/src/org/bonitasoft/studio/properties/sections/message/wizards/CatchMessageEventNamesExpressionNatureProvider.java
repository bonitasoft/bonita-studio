/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.message.wizards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class CatchMessageEventNamesExpressionNatureProvider implements IExpressionNatureProvider {

	private List<AbstractProcess> processes;
	private ThrowMessageEvent throwMessage;


	@Override
    public Expression[] getExpressions(final EObject context) {
		final List<Expression> result = new ArrayList<Expression>();
		if(processes != null && !processes.isEmpty()){
			final Set<String> names = new HashSet<String>();
			for(final AbstractProcess process : processes){
				for(final AbstractCatchMessageEvent catchMessage :  ModelHelper.getAllCatchEvent(process)){
                    if (!names.contains(catchMessage.getName())
                            && (catchMessage.getIncomingMessag() == null || catchMessage.getIncomingMessag().getSource() == null || catchMessage
                                    .getIncomingMessag().getSource().equals(throwMessage))) {
						names.add(catchMessage.getName());
					}
				}
				for(final String pName : names){
					final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
					exp.setName(pName);
					exp.setContent(pName);
					exp.setReturnType(String.class.getName());
					exp.setReturnTypeFixed(true);
					exp.setType(ExpressionConstants.CONSTANT_TYPE);
					result.add(exp);
				}
			}
		}
		return result.toArray(new Expression[result.size()]);
	}

	public void setFoundProcesses(final List<AbstractProcess> processes){
		this.processes = processes;
	}


	public void setThrowMessage(final ThrowMessageEvent throwMessage){
		this.throwMessage = throwMessage;
	}


}
