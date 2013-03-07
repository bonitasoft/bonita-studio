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
package org.bonitasoft.studio.engine.export.switcher;

import java.util.Collection;
import java.util.Set;

import org.bonitasoft.engine.bpm.model.ConnectorDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.ConnectorEvent;
import org.bonitasoft.engine.bpm.model.DataDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.DescriptionBuilder;
import org.bonitasoft.engine.bpm.model.FlowElementBuilder;
import org.bonitasoft.engine.bpm.model.ProcessDefinitionBuilder;
import org.bonitasoft.engine.core.operation.OperatorType;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;
import org.bonitasoft.studio.model.kpi.DatabaseKPIBinding;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;


/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractSwitch extends ProcessSwitch<Element> {

    protected final Set<EObject> eObjectNotExported;
  
    public static final String DB_CONNECTOR_FOR_KPI_ID = "database-jdbc";
    public static final String DB_CONNECTOR_VERSION = "1.0.0";
    public static final String DB_DRIVER = "driver";
    public static final String DB_URL = "url";
    public static final String DB_QUERY = "script";
    public static final String DB_USER = "username";
    public static final String DB_PASSWORD = "password";

    public AbstractSwitch(Set<EObject> eObjectNotExported){
        this.eObjectNotExported = eObjectNotExported ;
    }

    protected void addActors(final ProcessDefinitionBuilder builder , final AbstractProcess process) {
        for (Actor a : process.getActors()) {
            builder.addActor(a.getName()) ;
            if(a.isInitiator()){
                builder.setActorInitiator(a.getName()) ;
            }
        }
    }


    protected void addConnector(final FlowElementBuilder builder,final ConnectableElement element) {
        for (Connector connector : element.getConnectors()) {
            if(!eObjectNotExported.contains(connector)){
                final ConnectorDefinitionBuilder connectorBuilder = builder.addConnector(connector.getName(), connector.getDefinitionId(), connector.getDefinitionVersion(), ConnectorEvent.valueOf(connector.getEvent()));
                if(connector.isIgnoreErrors()){
                	connectorBuilder.ignoreError();
                }else if(connector.isThrowErrorEvent()){
                	connectorBuilder.throwErrorEventWhenFailed(connector.getNamedError());
                }
                for(ConnectorParameter parameter : connector.getConfiguration().getParameters()){
                    final Expression inputExpression = EngineExpressionUtil.createExpression(parameter.getExpression());
                    if(inputExpression != null){
                        connectorBuilder.addInput(parameter.getKey(), inputExpression) ;
                      
                    }else{
                        if(BonitaStudioLog.isLoggable(IStatus.OK)){
                            BonitaStudioLog.debug("Expression of input "+parameter.getKey() +" is null for connector "+connector.getName(), EnginePlugin.PLUGIN_ID);
                        }
                    }
                }
                for(Operation outputOpeartion : connector.getOutputs()){
                    String inputType = null ;
                    if(!outputOpeartion.getOperator().getInputTypes().isEmpty()){
                        inputType = outputOpeartion.getOperator().getInputTypes().get(0) ;
                    }
                    if(outputOpeartion.getLeftOperand() != null
                            && outputOpeartion.getLeftOperand().getContent() != null
                            && !outputOpeartion.getLeftOperand().getContent().isEmpty()
                            && outputOpeartion.getRightOperand() != null
                            && outputOpeartion.getRightOperand().getContent() != null){
                        connectorBuilder.addOutput(
                                EngineExpressionUtil.createLeftOperand(outputOpeartion.getLeftOperand()),
                                OperatorType.valueOf(outputOpeartion.getOperator().getType()),
                                outputOpeartion.getOperator().getExpression(),inputType,
                                EngineExpressionUtil.createExpression(outputOpeartion.getRightOperand())) ;
                    }
                }
            }
        }
    }

    protected void addKPIBinding(final FlowElementBuilder builder,final ConnectableElement element) {
        for (AbstractKPIBinding kpiBinding : element.getKpis()) {
            if(kpiBinding instanceof DatabaseKPIBinding){
                final ConnectorDefinitionBuilder connectorBuilder = builder.addConnector(kpiBinding.getName(),DB_CONNECTOR_FOR_KPI_ID, DB_CONNECTOR_VERSION, ConnectorEvent.valueOf(kpiBinding.getEvent()));
                DatabaseKPIBinding dbKPI = (DatabaseKPIBinding) kpiBinding;
                connectorBuilder.addInput(DB_DRIVER, EngineExpressionUtil.createExpression(dbKPI.getDriverclassName())) ;
                connectorBuilder.addInput(DB_URL, EngineExpressionUtil.createExpression(dbKPI.getJdbcUrl())) ;
                connectorBuilder.addInput(DB_QUERY, EngineExpressionUtil.createExpression(dbKPI.getRequest())) ;
                connectorBuilder.addInput(DB_USER, EngineExpressionUtil.createExpression(dbKPI.getUser())) ;
                connectorBuilder.addInput(DB_PASSWORD, EngineExpressionUtil.createExpression(dbKPI.getPassword())) ;
            }
        }
    }

    protected void addData(final FlowElementBuilder dataContainerBuilder,final DataAware dataAwareContainer) {
        for (final Data data : dataAwareContainer.getData()) {
            Expression expr = EngineExpressionUtil.createExpression(data.getDefaultValue());
            if(!data.isMultiple()){
                final ProcessSwitch<DataDefinitionBuilder> dataSwitch = new DataSwitch(data, expr, dataContainerBuilder) ;
                final DataDefinitionBuilder dataBuilder =  dataSwitch.doSwitch(data.getDataType());
                if(data.isTransient()){
                    dataBuilder.isTransient();
                }
            }else{
                if(expr == null){
                    expr = EngineExpressionUtil.createEmptyListExpression();
                }
                final DataDefinitionBuilder dataBuilder = dataContainerBuilder.addData(data.getName(), Collection.class.getName(), expr);
                if(data.isTransient()){
                    dataBuilder.isTransient();
                }
            }
        }
    }

    protected void addDescription(final DescriptionBuilder builder ,final String description){
        if(description != null && !description.isEmpty()){
            builder.addDescription(description) ;
        }
    }

}
