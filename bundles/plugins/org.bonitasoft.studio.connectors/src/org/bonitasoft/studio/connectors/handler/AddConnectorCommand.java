///**
// * Copyright (C) 2009 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.connectors.handler;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.bonitasoft.studio.model.expression.AbstractExpression;
//import org.bonitasoft.studio.model.process.ConnectableElement;
//import org.bonitasoft.studio.model.process.Connector;
//import org.bonitasoft.studio.model.process.Element;
//import org.bonitasoft.studio.model.process.OutputParameterMapping;
//import org.bonitasoft.studio.model.process.ProcessFactory;
//import org.bonitasoft.studio.model.process.ProcessPackage;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.core.runtime.IAdaptable;
//import org.eclipse.core.runtime.IProgressMonitor;
//import org.eclipse.emf.ecore.EStructuralFeature;
//import org.eclipse.emf.transaction.TransactionalEditingDomain;
//
//public class AddConnectorCommand extends AbstractTransactionalCommand {
//
//	private Element element;
//	private ConnectorDescription connector;
//	private Map<String, Object> parameters;
//	protected Connector newConnector;
//	private String name;
//	private String desc;
//	private Configuration config;
//	private List<OutputParameterMapping> outputMappings;
//	private String label;
//	private final EStructuralFeature feature;
//
//	/**
//	 * @deprecated use the other constructor using the good {@link EStructuralFeature}
//	 *
//	 *
//	 * @param editingDomain
//	 * @param elementName
//	 * @param label
//	 * @param elementDesc
//	 * @param eObject
//	 * @param connector
//	 * @param config
//	 * @param parameters
//	 * @param outputMappings
//	 */
//	public AddConnectorCommand(TransactionalEditingDomain editingDomain, String elementName,String label, String elementDesc, ConnectableElement eObject, ConnectorDescription connector, Configuration config, Map<String, Object> parameters, List<OutputParameterMapping> outputMappings) {
//		this(editingDomain, elementName, label, elementDesc, eObject, ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS, connector, config, parameters, outputMappings);
//	}
//
//	public AddConnectorCommand(TransactionalEditingDomain editingDomain, String elementName,String label, String elementDesc, Element eObject, EStructuralFeature feature, ConnectorDescription connector, Configuration config, Map<String, Object> parameters, List<OutputParameterMapping> outputMappings) {
//		super(editingDomain,AddConnectorCommand.class.getName(),getWorkspaceFiles(eObject));
//		this.name = elementName;
//		this.label = label ;
//		this.desc = elementDesc;
//		this.element = eObject;
//		this.feature = feature;
//		this.connector = connector;
//		this.parameters = parameters;
//		this.config = config;
//		this.outputMappings = outputMappings;
//	}
//
//	@Override
//	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
//			IAdaptable info) throws ExecutionException {
//
//		newConnector = ProcessFactory.eINSTANCE.createConnector();
//		newConnector.setLabel(label);
//		newConnector.setName(name);
//		newConnector.setDocumentation(desc);
//		if (config != null) {
//			newConnector.setConfigurationId(config.getId());
//		}
//		newConnector.setConnectorId(connector.getId());
//		for (Entry<String, Object> entry : parameters.entrySet()) {
//			Parameter param = ProcessFactory.eINSTANCE.createParameter();
//			param.setKey(entry.getKey());
//			if(entry.getValue() instanceof AbstractExpression){
//				param.setExpression((AbstractExpression) entry.getValue()) ;
//			}else{
//				param.setValue(entry.getValue());
//			}
//
//
//			newConnector.getParameters().add(param);
//		}
//		newConnector.getOutputs().addAll(outputMappings);
//
//		((List) element.eGet(feature)).add(newConnector);
//		return CommandResult.newOKCommandResult();
//	}
//
//}
