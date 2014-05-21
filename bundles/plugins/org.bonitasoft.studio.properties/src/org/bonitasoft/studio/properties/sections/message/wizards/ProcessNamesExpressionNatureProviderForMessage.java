/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.message.wizards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.properties.sections.general.ProcessNamesExpressionNatureProvider;

/**
 * @author aurelie
 *
 */
public class ProcessNamesExpressionNatureProviderForMessage extends
ProcessNamesExpressionNatureProvider implements
IExpressionNatureProvider {
	@Override
	public Expression[] getExpressions() {
		List<Expression> result = new ArrayList<Expression>();
		final DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
		MainProcess diagram = ModelHelper.getMainProcess(getContext());
		AbstractProcess parentProcess = ModelHelper.getParentProcess(getContext());
		Set<String> names = new HashSet<String>();
		
		for(AbstractProcess p : ModelHelper.getAllProcesses(diagram)){
			if(!p.getName().equals(parentProcess.getName())){
				names.add(p.getName());
			}
		}
		for(AbstractProcess p : diagramStore.getAllProcesses()){
			if(!p.getName().equals(parentProcess.getName())){
				names.add(p.getName());
			}
		}
		
		for(String pName : names){
			Expression exp = ExpressionFactory.eINSTANCE.createExpression();
			exp.setName(pName);
			exp.setContent(pName);
			exp.setReturnType(String.class.getName());
			exp.setReturnTypeFixed(true);
			exp.setType(ExpressionConstants.CONSTANT_TYPE);
			result.add(exp);
		}
		return result.toArray(new Expression[result.size()]);
	}
}
