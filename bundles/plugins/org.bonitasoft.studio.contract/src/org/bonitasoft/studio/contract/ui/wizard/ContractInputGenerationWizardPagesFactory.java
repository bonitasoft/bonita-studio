/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.wizard;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.groovy.ui.viewer.GroovySourceViewerFactory;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractContainer;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;

/**
 * @author aurelie
 */
public class ContractInputGenerationWizardPagesFactory {

    public CreateContractInputFromBusinessObjectWizardPage createCreateContratInputFromBusinessObjectWizardPage(
            ContractContainer contractContainer,
            GenerationOptions generationOptions,
            WritableValue selectedDataObservable,
            WritableValue rootNameObservable,
            FieldToContractInputMappingFactory fieldToContractInputMappingFactory,
            WritableList fieldToContractInputMappingsObservable,
            BusinessObjectModelRepositoryStore businessObjectStore) {
        return new CreateContractInputFromBusinessObjectWizardPage(contractContainer, generationOptions,
                selectedDataObservable, fieldToContractInputMappingFactory, fieldToContractInputMappingsObservable,
                businessObjectStore);
    }

    public SelectDataWizardPage createSelectBusinessDataWizardPage(Contract contract,
            List<Data> availableBusinessData,
            List<Document> availableDocuments,
            WritableValue selectedDataObservable,
            WritableValue rootNameObservable,
           GenerationOptions generateOptions, 
            BusinessObjectModelRepositoryStore businessObjectStore) {
        return new SelectDataWizardPage(contract, availableBusinessData, availableDocuments, selectedDataObservable,
                rootNameObservable, generateOptions,businessObjectStore);
    }

    public GeneratedScriptPreviewPage createGeneratedScriptPreviewPage(WritableValue rootNameObservable,
            WritableList fieldToContactInputMappingsObservable, WritableValue selectedDataObservable,
            RepositoryAccessor repositoryAccessor,
            FieldToContractInputMappingOperationBuilder operationBuilder,
            FieldToContractInputMappingExpressionBuilder expressionBuilder,
            GroovySourceViewerFactory sourceViewerFactory) {
        return new GeneratedScriptPreviewPage(rootNameObservable, fieldToContactInputMappingsObservable,
                selectedDataObservable, repositoryAccessor,
                operationBuilder, expressionBuilder, sourceViewerFactory);
    }
}
