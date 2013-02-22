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
package org.bonitasoft.studio.connectors.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Group;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;

/**
 * @author Romain Bioteau
 *
 */
public class TestProvidedDefinitionAndImplementation extends TestCase {

	private ConnectorDefRepositoryStore connectorDefStore;
	private ConnectorImplRepositoryStore connectorImplStore;
	private DefinitionResourceProvider connectorResourceProvider;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
		connectorImplStore = (ConnectorImplRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
		connectorResourceProvider = DefinitionResourceProvider.getInstance(connectorDefStore, ConnectorPlugin.getDefault().getBundle());
	}

	public void testProvidedDefinitionsSanity() throws Exception {
		StringBuilder testReport = new StringBuilder("testProvidedDefinitionsSanity report:");
		for(ConnectorDefinition definition : connectorDefStore.getDefinitions()){
			String resourceName = definition.eResource().getURI().lastSegment();
			if(connectorDefStore.getChild(resourceName).isReadOnly()){
				if(!(definition.getId() != null && !definition.getId().isEmpty())){
					testReport.append("\n");
					testReport.append("Missing definition id for "+resourceName);
				}

				if(!(definition.getVersion() != null && !definition.getVersion().isEmpty())){
					testReport.append("\n");
					testReport.append("Missing definition version for "+resourceName);
				}

				if(!NamingUtils.toConnectorDefinitionFilename(definition.getId(), definition.getVersion(), true).equals(resourceName)){
					testReport.append("\n");
					testReport.append("Resource name doesn't match id and version for "+resourceName);
				}

				if(!(definition.getIcon() != null && !definition.getIcon().isEmpty())){
					testReport.append("\n");
					testReport.append("Missing definition icon for "+resourceName);
				}

				if(connectorResourceProvider.getDefinitionIcon(definition) == null){
					testReport.append("\n");
					testReport.append("Missing definition icon file for "+resourceName);
				}

				if(definition.getCategory().isEmpty()){
					testReport.append("\n");
					testReport.append("The definition should belong to at least one category for "+resourceName);
				}

				List<String> inputs = new ArrayList<String>();
				for(Input in : definition.getInput()){
//					try{
//						Class inputClazz = Class.forName(in.getType());
//						if(!(Collection.class.isAssignableFrom(inputClazz) || Serializable.class.isAssignableFrom(inputClazz))){
//							testReport.append("\n");
//							testReport.append("Input "+in.getName()+" with type "+in.getType()+" from "+definition.getId()+" ("+definition.getVersion()+") is not Serializable or is not a Collection");
//						}
//					}catch (Exception e) {
//
//					}
					inputs.add(in.getName());
				}
				for(String inputName : inputs){
					if(Collections.frequency(inputs, inputName) != 1){
						testReport.append("\n");
						testReport.append("Input "+inputName+" is duplicated in "+resourceName);
					}
				}
				List<String> bindInputs = new ArrayList<String>();
				for(Page p : definition.getPage()){

					if(!(p.getId() != null && !p.getId().isEmpty())){
						testReport.append("\n");
						testReport.append("Invalid page id in "+resourceName);
					}


					final String pageTitle = connectorResourceProvider.getPageTitle(definition, p.getId());
					if(!(pageTitle != null && !pageTitle.isEmpty())){
						testReport.append("\n");
						testReport.append("Invalid page title for "+p.getId()+" in "+resourceName);
					}

					final String pageDescription = connectorResourceProvider.getPageDescription(definition, p.getId());
					if(!(pageDescription != null && !pageDescription.isEmpty())){
						testReport.append("\n");
						testReport.append("Invalid page description for "+p.getId()+" in "+resourceName);
					}

					for(Component component : p.getWidget()){
						parsePageWidget(component,bindInputs, resourceName,definition,testReport);
					}
				}

				for(String inputName : inputs){
					final int frequency = Collections.frequency(bindInputs, inputName);
					if(frequency == 0){
						testReport.append("\n");
						testReport.append("Input "+inputName+" is not bound to any widget in "+resourceName);
					}
					if(frequency > 1){
						testReport.append("\n");
						testReport.append("Input "+inputName+" is bound to more than one widget in "+resourceName);
					}
				}

				List<String> outputs = new ArrayList<String>();
				for(String outputName : outputs){
					if(Collections.frequency(outputs, outputName) != 1){
						testReport.append("\n");
						testReport.append("Output "+outputName+" is duplicated in "+resourceName);
					}
				}
			}
		}
		if(!testReport.toString().equals("testProvidedDefinitionsSanity report:")){
			System.out.println(testReport.toString());
			fail(testReport.toString());
		}
	}

	private void parsePageWidget(Component component, List<String> bindInputs,String resourceName,ConnectorDefinition def, StringBuilder testReport) {
		if(component instanceof Group){
			if(!(component.getId() != null && !component.getId().isEmpty())){
				testReport.append("\n");
				testReport.append("Invalid widget id in "+resourceName);
			}

			final String fieldLabel = connectorResourceProvider.getFieldLabel(def, component.getId());
			if(!(fieldLabel != null && !fieldLabel.isEmpty())){
				testReport.append("\n");
				testReport.append("The widget "+component.getId()+" has no label in "+resourceName);
			}
			for(Component widget : ((Group)component).getWidget()){
				parsePageWidget(widget, bindInputs,resourceName,def,testReport);
			}
		}else if(component instanceof WidgetComponent){
			if(!(component.getId() != null && !component.getId().isEmpty())){
				testReport.append("\n");
				testReport.append("Invalid widget id in "+resourceName);
			}
			final String fieldLabel = connectorResourceProvider.getFieldLabel(def, component.getId());
			if(!(fieldLabel != null && !fieldLabel.isEmpty())){
				testReport.append("\n");
				testReport.append("The widget "+component.getId()+" has no label in "+resourceName);
			}
			bindInputs.add(((WidgetComponent) component).getInputName());
		}
	}

	public void testProvidedImplementationsSanity() throws Exception {
		StringBuilder testReport = new StringBuilder("testProvidedImplementationsSanity report:");
		List<ConnectorDefinition> definitions = connectorDefStore.getDefinitions();
		for(ConnectorImplementation implementation : connectorImplStore.getImplementations()){
			String resourceName = implementation.eResource().getURI().lastSegment();
			if(connectorImplStore.getChild(resourceName).isReadOnly()){
				if(implementation.getImplementationId() == null || implementation.getImplementationId().isEmpty()){
					testReport.append("\n");
					testReport.append("Missing implementation id for "+resourceName);
				}
				if(implementation.getImplementationVersion() == null || implementation.getImplementationVersion().isEmpty()){
					testReport.append("\n");
					testReport.append("Missing implementation version for "+resourceName);
				}
				if(implementation.getImplementationClassname() == null || implementation.getImplementationClassname().isEmpty()){
					testReport.append("\n");
					testReport.append("Missing implementation classname for "+resourceName);
				}
				if(!NamingUtils.toConnectorImplementationFilename(implementation.getImplementationId(), implementation.getImplementationVersion(), true).equals(resourceName)){
					testReport.append("\n");
					testReport.append("Resource name doesn't match id and version for "+resourceName);
				}

				if(implementation.getDefinitionId() == null || implementation.getDefinitionId().isEmpty()){
					testReport.append("\n");
					testReport.append("Missing definition id for "+resourceName);
				}

				if(implementation.getDefinitionVersion() == null || implementation.getDefinitionVersion().isEmpty()){
					testReport.append("\n");
					testReport.append("Missing definition version for "+resourceName);
				}

				if(connectorDefStore.getDefinition(implementation.getDefinitionId() , implementation.getDefinitionVersion(),definitions) == null){
					testReport.append("\n");
					testReport.append("Connector definition not found for implementation "+resourceName);
				}

				if(implementation.getJarDependencies() == null || implementation.getJarDependencies().getJarDependency().isEmpty()){
					testReport.append("\n");
					testReport.append("Missing jar dependencies for "+resourceName);
				}
				if(implementation.getJarDependencies() != null){
					for(String jarName : implementation.getJarDependencies().getJarDependency() ){
						final InputStream stream = connectorResourceProvider.getDependencyInputStream(jarName);
						if(stream == null){
							testReport.append("\n");
							testReport.append("A provided lib has not been found ("+jarName+") for "+resourceName);
						}
						if(stream != null){
							stream.close();
						}
					}
				}
			}
		}
		if(!testReport.toString().equals("testProvidedImplementationsSanity report:")){
			System.out.println(testReport.toString());
			fail(testReport.toString());
		}
	}


}
