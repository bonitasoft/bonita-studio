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
package org.bonitasoft.studio.properties.sections.general;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class ProcessVersionsExpressionNatureProvider implements IExpressionNatureProvider {

    @Override
    public Expression[] getExpressions(final EObject context) {
        final List<Expression> result = new ArrayList<Expression>();
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final List<String> versions = new ArrayList<String>();
        final MainProcess diagram = ModelHelper.getMainProcess(context);
        if(context instanceof CallActivity){
            final Expression exp = ((CallActivity)context).getCalledActivityName();
            if(exp != null
                    && ExpressionConstants.CONSTANT_TYPE.equals(exp.getType())
                    && exp.getContent() != null
                    && !exp.getContent().isEmpty()){
                final String processName = exp.getContent();
                for(final EObject p : ModelHelper.getAllItemsOfType(diagram, ProcessPackage.Literals.POOL)){
                    if(processName.equals(((Pool) p).getName())){
                        if(!versions.contains(((Pool) p).getVersion())){
                            versions.add(((Pool) p).getVersion());
                        }
                    }
                }
                for(final AbstractProcess p : diagramStore.getAllProcesses()){
                    if(processName.equals(((Pool) p).getName())){
                        if(!versions.contains(((Pool) p).getVersion())){
                            versions.add(((Pool) p).getVersion());
                        }
                    }
                }
            }
        }
        for(final String version : versions){
            final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
            exp.setName(version);
            exp.setContent(version);
            exp.setReturnType(String.class.getName());
            exp.setReturnTypeFixed(true);
            exp.setType(ExpressionConstants.CONSTANT_TYPE);
            result.add(exp);
        }
        return result.toArray(new Expression[result.size()]);
    }

}
