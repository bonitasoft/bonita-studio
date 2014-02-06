/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.expression;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorOutputExpressionProvider implements IExpressionProvider {

    private final ConnectorDefRepositoryStore store;

    public ConnectorOutputExpressionProvider(){
        store = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
    }

    @Override
    public Set<Expression> getExpressions(EObject context) {
        Set<Expression> result = new HashSet<Expression>() ;
        ConnectorDefinition definition = null ;
        if (context instanceof ConnectorDefinition) {
            definition = (ConnectorDefinition) context ;
        }else if(context instanceof Connector){
            String defId =   ((Connector) context).getDefinitionId() ;
            String defVersion =   ((Connector) context).getDefinitionVersion() ;
            definition = store.getDefinition(defId, defVersion) ;
        }else{
            definition = (ConnectorDefinition) getConnectorDefinition(context) ;
        }
        if(definition != null){
            for(Output output : definition.getOutput()){
                result.add(createExpression(output)) ;
            }
        }
        return result;
    }

    private EObject getConnectorDefinition(EObject context) {
        EObject definition = context ;
        if(context != null){
            while (definition != null && !(definition instanceof ConnectorDefinition)) {
                if(definition instanceof Connector){
                    String defId =   ((Connector) definition).getDefinitionId() ;
                    String defVersion =   ((Connector) definition).getDefinitionVersion() ;
                    definition = store.getDefinition(defId, defVersion) ;
                    break ;
                }
                definition = definition.eContainer() ;
            }
        }
        return definition;
    }

    private Expression createExpression(Output output) {
        Expression exp = ExpressionFactory.eINSTANCE.createExpression() ;
        exp.setType(getExpressionType()) ;
        exp.setContent(output.getName()) ;
        exp.setName(output.getName()) ;
        exp.setReturnType(output.getType()) ;
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(output)) ;
        return exp;
    }

    @Override
    public String getExpressionType() {
        return ExpressionConstants.CONNECTOR_OUTPUT_TYPE;
    }

    @Override
    public Image getIcon(Expression expression) {
        if(expression.getReferencedElements().isEmpty()){
            return null ;
        }
        return Pics.getImage("output.png", ConnectorPlugin.getDefault());
    }

    @Override
    public String getProposalLabel(Expression expression) {
        return expression.getName() ;
    }

    @Override
    public boolean isRelevantFor(EObject context) {
        return context instanceof EObject && !getExpressions(context).isEmpty();
    }

    @Override
    public Image getTypeIcon() {
        return Pics.getImage("output.png", ConnectorPlugin.getDefault());
    }

    @Override
    public String getTypeLabel() {
        return Messages.connectorOutpuTypeLabel;
    }

    @Override
    public IExpressionEditor getExpressionEditor(Expression expression,EObject context) {
        return new ConnectorOutputExpressionEditor();
    }



}
