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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.expression.FieldToContractInputMappingExpressionBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.contract.core.mapping.operation.FieldToContractInputMappingOperationBuilder;
import org.bonitasoft.studio.groovy.ui.viewer.GroovySourceViewerFactory;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Composite;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author aurelie
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneratedScriptPreviewPageTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private FieldToContractInputMappingOperationBuilder operationBuilder;

    @Mock
    private IPreferenceStore preferenceStore;

    @Mock
    private FieldToContractInputMappingExpressionBuilder expressionBuilder;

    @Mock
    private ContractInputGenerationInfoDialogFactory dialogFactory;

    @Mock
    private GroovySourceViewerFactory sourceViewerFactory;

    @Mock
    private GroovyViewer groovyViewer;

    @Mock
    private SourceViewer sourceViewer;

    @Mock
    private IDocument document;

    @Mock
    Composite composite;

    @Test
    public void testShouldBeGeneratedWhenPageIsVisible() throws JavaModelException, BusinessObjectInstantiationException {
        final BusinessObjectData data = aBusinessData().withName("employee").withClassname("org.company.Employee").build();
        final Pool process = aPool().havingContract(aContract()).build();
        process.getData().add(data);
        final WritableValue selectedDataObservable = new WritableValue();
        selectedDataObservable.setValue(data);
        final WritableValue rootNameObservable = new WritableValue();
        rootNameObservable.setValue(data.getName() + "Input");
        final WritableList fieldToContactInputMappingsObservable = new WritableList();
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping(
                SimpleFieldBuilder.aStringField(
                        "firstName").build());
        fieldToContactInputMappingsObservable.add(mapping);

        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(store);
        when(store.getBusinessObjectByQualifiedName("org.company.Employee"))
                .thenReturn(Optional.of(BusinessObjectBuilder.aBO("org.company.Employee")
                        .withField(SimpleFieldBuilder.aStringField("firstName").build()).build()));
        when(sourceViewerFactory.createSourceViewer(any(Composite.class), any(Boolean.class))).thenReturn(groovyViewer);
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        when(groovyViewer.getDocument()).thenReturn(document);
        when(expressionBuilder.toExpression(any(BusinessObjectData.class), any(FieldToContractInputMapping.class),
                anyBoolean())).thenReturn(
                        aGroovyScriptExpression().build());

        final GeneratedScriptPreviewPage previewPage = new GeneratedScriptPreviewPage(rootNameObservable,
                fieldToContactInputMappingsObservable,
                selectedDataObservable, repositoryAccessor, operationBuilder, expressionBuilder, sourceViewerFactory);
        final IWizard wizard = mock(IWizard.class);
        when(wizard.getContainer()).thenReturn(new TestWizardContainer(realmWithDisplay.getShell()));
        previewPage.setWizard(wizard);
        previewPage.createControl(realmWithDisplay.createComposite());
        previewPage.setVisible(true);

        assertThat(previewPage.getRootContractInputGenerator()).isNotNull();
        assertThat(previewPage.getRootContractInputGenerator().getInitialValueExpression()).isNotNull();
    }
}
