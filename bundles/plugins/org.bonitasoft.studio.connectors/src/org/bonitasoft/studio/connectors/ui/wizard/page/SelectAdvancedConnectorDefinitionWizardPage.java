/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractDefinitionSelectionImpementationWizardPage;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.provider.UniqueConnectorDefinitionContentProvider;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * @author Romain Bioteau
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
        final IObservableValue<ConnectorDefinition> selectedConnectorDefinitionObservable = ViewerProperties
                .singleSelection(ConnectorDefinition.class).observe(explorer.getRightTableViewer());
        final IObservableValue<String> selectedDefinitionVersionObservable = ViewerProperties
                .singleSelection(String.class).observe(versionCombo);
        selectedConnectorDefinitionObservable
                .addValueChangeListener(e -> updateSelectedConnectorDefinition(selectedConnectorDefinitionObservable,
                        selectedDefinitionVersionObservable));
        selectedDefinitionVersionObservable
                .addValueChangeListener(e -> updateSelectedConnectorDefinition(selectedConnectorDefinitionObservable,
                        selectedDefinitionVersionObservable));

        UpdateValueStrategy<ConnectorDefinition, String> idStrategy = new UpdateValueStrategy<>();
        idStrategy.setAfterGetValidator(selectionValidator());
        idStrategy.setConverter(new Converter<ConnectorDefinition, String>(ConnectorDefinition.class, String.class) {

            @Override
            public String convert(ConnectorDefinition from) {
                if (from instanceof ConnectorDefinition) {
                    return from.getId();
                }
                return null;
            }
        });

        context.bindValue(selectedConnectorDefinitionObservable,
                EMFObservables.observeValue(workingCopy, ProcessPackage.Literals.CONNECTOR__DEFINITION_ID),
                idStrategy,
                null);
        context.bindValue(selectedDefinitionVersionObservable,
                EMFObservables.observeValue(workingCopy, ProcessPackage.Literals.CONNECTOR__DEFINITION_VERSION));
        context.bindValue(selectedConnectorDefinitionObservable,
                EMFObservables.observeValue(workingCopy.getConfiguration(),
                        ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__DEFINITION_ID),
                idStrategy,
                null);
        context.bindValue(selectedDefinitionVersionObservable,
                EMFObservables.observeValue(workingCopy.getConfiguration(),
                        ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__VERSION));
    }

    protected void updateSelectedConnectorDefinition(IObservableValue<ConnectorDefinition> selectedDefinitionObservable,
            IObservableValue<String> versionObservable) {
        ConnectorDefinition selectedDef = selectedDefinitionObservable.getValue();
        String version = versionObservable.getValue();
        if (selectedDef != null) {
            definitions.stream()
                    .filter(def -> Objects.equals(selectedDef.getId(), def.getId())
                            && Objects.equals(version, def.getVersion()))
                    .findFirst()
                    .ifPresent(def -> {
                        setSelectedConnectorDefinition(def);
                        setPageComplete(true);
                    });
        }
    }

    protected IValidator<ConnectorDefinition> selectionValidator() {
        return def -> def != null ? ValidationStatus.ok() : ValidationStatus.error(Messages.selectAConnectorDefWarning);
    }

}
