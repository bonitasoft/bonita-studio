/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.List;

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractDefinitionSelectionImpementationWizardPage;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.provider.UniqueConnectorDefinitionContentProvider;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * @author Romain Bioteau
 *
 */
public class SelectAdvancedConnectorDefinitionWizardPage extends AbstractDefinitionSelectionImpementationWizardPage {

	private Connector workingCopy;

	public SelectAdvancedConnectorDefinitionWizardPage(Connector workingCopy,
			List<ConnectorImplementation> existingImpl,
			List<ExtendedConnectorDefinition> definitions,
			String pageTitle,
			String pageDescription) {
		super(existingImpl, definitions, pageTitle, pageDescription);
		this.workingCopy = workingCopy;
	}

	@Override
	protected ITreeContentProvider getContentProvider() {
		return new UniqueConnectorDefinitionContentProvider();
	}

	@Override
	protected ITreeContentProvider getCustomContentProvider() {
		return new UniqueConnectorDefinitionContentProvider(true);
	}

	@Override
	protected void bindValue() {
		final IViewerObservableValue observeSingleSelection = ViewersObservables.observeSingleSelection(explorer.getRightTableViewer());
		final IViewerObservableValue versionObservable = ViewersObservables.observeSingleSelection(versionCombo);
		final AbstractDefinitionSelectionImpementationWizardPage thisPage = this;
		observeSingleSelection.addValueChangeListener(new IValueChangeListener() {
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				updateSelectedConnectorDefinition(observeSingleSelection,versionObservable,	thisPage);
			}
		});
		
		
		versionObservable.addValueChangeListener(new IValueChangeListener() {
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				updateSelectedConnectorDefinition(observeSingleSelection,versionObservable,thisPage);
			}
		});
		

		IValidator selectionValidator = new IValidator() {
			@Override
			public IStatus validate(Object value) {
				return validateSelection(value);
			}
		} ;

		UpdateValueStrategy idStrategy = new UpdateValueStrategy() ;
		idStrategy.setBeforeSetValidator(selectionValidator) ;
		idStrategy.setConverter(new Converter(ConnectorDefinition.class,String.class) {

			@Override
			public Object convert(Object from) {
				if(from instanceof ConnectorDefinition){
					return ((ConnectorDefinition) from).getId() ;
				}
				return null;
			}
		}) ;

		UpdateValueStrategy versionStrategy = new UpdateValueStrategy() ;
		versionStrategy.setBeforeSetValidator(selectionValidator) ;
		versionStrategy.setConverter(new Converter(ConnectorDefinition.class,String.class) {

			@Override
			public Object convert(Object from) {
				if(from instanceof ConnectorDefinition){
					return ((ConnectorDefinition) from).getVersion() ;
				}
				return null;
			}
		}) ;
		context.bindValue(observeSingleSelection, EMFObservables.observeValue(workingCopy, ProcessPackage.Literals.CONNECTOR__DEFINITION_ID),idStrategy,null)  ;
		context.bindValue(ViewersObservables.observeSingleSelection(versionCombo), EMFObservables.observeValue(workingCopy, ProcessPackage.Literals.CONNECTOR__DEFINITION_VERSION))  ;
		context.bindValue(observeSingleSelection, EMFObservables.observeValue(workingCopy.getConfiguration(), ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__DEFINITION_ID),idStrategy,null)  ;
		context.bindValue(ViewersObservables.observeSingleSelection(versionCombo), EMFObservables.observeValue(workingCopy.getConfiguration(), ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__VERSION))  ;
	}
	
	protected void updateSelectedConnectorDefinition(
			final IViewerObservableValue observeSingleSelection,
			IViewerObservableValue versionObservable, final AbstractDefinitionSelectionImpementationWizardPage thisPage) {
		final Object o = observeSingleSelection.getValue();
		if(o instanceof ConnectorDefinition){
			ExtendedConnectorDefinition selectedDef = null;
			String version = (String) versionObservable.getValue();
			for(ExtendedConnectorDefinition def : definitions){
				if(((ConnectorDefinition) o).getId().equals(def.getId()) && version.equals(def.getVersion())){
					selectedDef = def;
					break;
				}
			}
			
			thisPage.setSelectedConnectorDefinition(selectedDef);
			setPageComplete(true);
		}
	}

	protected IStatus validateSelection(Object value) {
		if(value == null || value instanceof Category){
			return new Status(IStatus.ERROR,ConnectorPlugin.PLUGIN_ID, Messages.selectAConnectorDefWarning);
		}
		return Status.OK_STATUS;
	}

}
