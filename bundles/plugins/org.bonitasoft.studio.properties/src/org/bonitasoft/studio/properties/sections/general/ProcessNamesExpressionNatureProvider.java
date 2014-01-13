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
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class ProcessNamesExpressionNatureProvider implements IExpressionNatureProvider {

    private EObject context;

    @Override
    public Expression[] getExpressions() {
        List<Expression> result = new ArrayList<Expression>();
        final DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        List<String> names = new ArrayList<String>();
        MainProcess diagram = ModelHelper.getMainProcess(context);
        for(EObject p : ModelHelper.getAllItemsOfType(diagram, ProcessPackage.Literals.POOL)){
            if(!names.contains(((Pool) p).getName())){
                names.add(((Pool) p).getName());
            }
        }
        for(AbstractProcess p : diagramStore.getAllProcesses()){
            if(!names.contains(p.getName())){
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

    @Override
    public void setContext(final EObject context) {
        this.context = context ;
    }

    @Override
    public EObject getContext() {
        return context;
    }

}
