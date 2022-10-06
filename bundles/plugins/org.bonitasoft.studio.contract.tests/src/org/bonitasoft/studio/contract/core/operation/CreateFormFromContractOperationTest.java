package org.bonitasoft.studio.contract.core.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.BusinessDataStore;
import org.bonitasoft.studio.designer.core.FormScope;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.junit.Before;
import org.junit.Test;

public class CreateFormFromContractOperationTest {

    static final String BUSINESS_DATA_1_NAME = "businessData1";
    static final String BUSINESS_DATA_2_NAME = "businessData2";
    static final String BO1_FIELD1_NAME = "bo1Field1";
    static final String BO1_CPLX_FIELD_NAME = "bo1CplxField";
    static final String BO2_FIELD1_NAME = "bo2Field1";
    static final String BO2_FIELD2_NAME = "bo2Field2";
    private RepositoryAccessor repositoryAccessor;
    private BusinessDataStore businessDataStore;
    private BusinessObject bo2;
    private BusinessObject bo1;

    @Before
    public void init() {
        repositoryAccessor = repositoryAccessor();
        businessDataStore = businessDataStore();
    }

    @Test
    public void should_not_ask_for_read_only_if_an_input_is_in_create_mode() {
        ContractInput input = aContractInput().inCreateMode(true).build();
        Contract contract = aContract().havingInput(input).build();
        assertThat(operation(contract).containsAttributesToDisplayInReadOnly(contract, businessDataStore)).isFalse();
    }

    @Test
    public void should_not_ask_for_read_only_if_an_input_is_in_edit_mode_has_no_business_data() {
        ContractInput input = aContractInput().inCreateMode(false).build();
        Contract contract = aContract().havingInput(input).build();
        assertThat(operation(contract).containsAttributesToDisplayInReadOnly(contract, businessDataStore)).isFalse();
    }

    @Test
    public void should_not_ask_for_read_only_if_an_input_is_in_edit_mode_with_a_business_data_and_all_attributes_checked() {
        ContractInput input = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .build();

        ContractInput bo1Field1Input = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .withName(BO1_FIELD1_NAME)
                .withType(ContractInputType.TEXT)
                .build();

        ContractInput bo1CplxFieldInput = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .withName(BO1_CPLX_FIELD_NAME)
                .withType(ContractInputType.COMPLEX)
                .build();

        ContractInput bo2Field1Input = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .withName(BO2_FIELD1_NAME)
                .withType(ContractInputType.TEXT)
                .build();

        ContractInput bo2Field2Input = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .withName(BO2_FIELD2_NAME)
                .withType(ContractInputType.TEXT)
                .build();

        bo1CplxFieldInput.getInputs().add(bo2Field1Input);
        bo1CplxFieldInput.getInputs().add(bo2Field2Input);
        input.getInputs().add(bo1Field1Input);
        input.getInputs().add(bo1CplxFieldInput);

        Contract contract = aContract().havingInput(input).build();
        assertThat(operation(contract).containsAttributesToDisplayInReadOnly(contract, businessDataStore)).isFalse();
    }

    @Test
    public void should_ask_for_read_only_if_an_input_is_in_edit_mode_with_a_business_data_and_some_attributes_unchecked() {
        ContractInput input = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .build();

        ContractInput bo1Field1Input = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .withName(BO1_FIELD1_NAME)
                .withType(ContractInputType.TEXT)
                .build();

        ContractInput bo1CplxFieldInput = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .withName(BO1_CPLX_FIELD_NAME)
                .withType(ContractInputType.COMPLEX)
                .build();

        ContractInput bo2Field1Input = aContractInput()
                .inCreateMode(false)
                .withDataReference(BUSINESS_DATA_1_NAME)
                .withName(BO2_FIELD1_NAME)
                .withType(ContractInputType.TEXT)
                .build();

        bo1CplxFieldInput.getInputs().add(bo2Field1Input);
        input.getInputs().add(bo1Field1Input);
        input.getInputs().add(bo1CplxFieldInput);

        Contract contract = aContract().havingInput(input).build();
        assertThat(operation(contract).containsAttributesToDisplayInReadOnly(contract, businessDataStore)).isTrue();
    }

    private CreateFormFromContractOperation operation(Contract contract) {
        return new CreateFormFromContractOperation(designerUrlFactory(),
                contract, FormScope.PROCESS, repositoryAccessor);
    }

    private PageDesignerURLFactory designerUrlFactory() {
        IEclipsePreferences preferenceStoreMock = mock(IEclipsePreferences.class);
        return new PageDesignerURLFactory(preferenceStoreMock);
    }

    private BusinessDataStore businessDataStore() {
        BusinessDataStore dataStore = mock(BusinessDataStore.class);
        when(dataStore.getBusinessData())
                .thenReturn(Arrays.asList(
                        new BusinessObjectDataBuilder().withName(BUSINESS_DATA_1_NAME).withClassname(BUSINESS_DATA_1_NAME)
                                .build()));
        when(dataStore.getBusinessObject(BUSINESS_DATA_1_NAME)).thenReturn(Optional.of(bo1));
        when(dataStore.getBusinessObject(BUSINESS_DATA_2_NAME)).thenReturn(Optional.of(bo2));
        return dataStore;
    }

    private RepositoryAccessor repositoryAccessor() {
        bo2 = new BusinessObjectBuilder("org." + BUSINESS_DATA_2_NAME)
                .withField(FieldBuilder.aStringField(BO2_FIELD1_NAME).build())
                .withField(FieldBuilder.aStringField(BO2_FIELD2_NAME).build())
                .build();

        bo1 = new BusinessObjectBuilder("org." + BUSINESS_DATA_1_NAME)
                .withField(FieldBuilder.aStringField(BO1_FIELD1_NAME).build())
                .withField(FieldBuilder.aCompositionField(BO1_CPLX_FIELD_NAME, bo2))
                .build();

        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = mock(
                BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getBusinessObjectByQualifiedName("org." + BUSINESS_DATA_1_NAME)).thenReturn(Optional.of(bo1));
        when(repositoryStore.getBusinessObjectByQualifiedName("org." + BUSINESS_DATA_2_NAME)).thenReturn(Optional.of(bo2));

        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(repositoryStore);

        return repositoryAccessor;
    }

}
