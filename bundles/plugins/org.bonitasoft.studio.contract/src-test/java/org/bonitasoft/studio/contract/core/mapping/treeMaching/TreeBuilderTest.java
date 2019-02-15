package org.bonitasoft.studio.contract.core.mapping.treeMaching;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.builders.ContractBuilder;
import org.junit.Before;
import org.junit.Test;

public class TreeBuilderTest {

    private BusinessDataStore store;
    private List<BusinessObjectData> businessData = new ArrayList<>();

    @Before
    public void setUp() {
        store = mock(BusinessDataStore.class);
        businessData.add(aData("myData", "org.test.Employee"));
        when(store.getBusinessData()).thenReturn(businessData);
        RelationField addressField = RelationFieldBuilder.aCompositionField("address", aBO("org.test.Address")
                .withField(SimpleFieldBuilder.aStringField("street").build())
                .build());
        addressField.setCollection(true);
        when(store.getBusinessObject("org.test.Employee")).thenReturn(Optional.of(aBO("org.test.Employee")
                .withField(SimpleFieldBuilder.aStringField("firstName").build())
                .withField(SimpleFieldBuilder.aDateOnlyField("birthDate").build())
                .withField(addressField)
                .build()));
    }

    @Test
    public void should_build_tree() throws Exception {
        TreeBuilder treeBuilder = new TreeBuilder(store);

        Contract contract = createMatchingContract();
        TreeNode node = treeBuilder.buildBusinessObjectTree(businessData.get(0), contract.getInputs().get(0));

        assertThat(node.getInput().getName()).isEqualTo("myDataInput");
        assertThat(node.getRef().getName()).isEqualTo("myData");
        assertThat(node.getChildren()).extracting("input.name", "ref.name").contains(tuple("address", "address"));
        assertThat(node.getChildren().get(0).getChildren()).isEmpty();
    }

    @SuppressWarnings("unchecked")
    private Contract createMatchingContract() {
        return ContractBuilder.aContract()
                .havingInput(aContractInput()
                        .withName("myDataInput")
                        .withType(ContractInputType.COMPLEX)
                        .havingInput(aContractInput().withName("firstName").withType(ContractInputType.TEXT),
                                aContractInput().withName("birthDate").withType(ContractInputType.LOCALDATE),
                                aContractInput().withName("address").withType(ContractInputType.COMPLEX)
                                        .havingInput(aContractInput().withName("street").withType(ContractInputType.TEXT),
                                                aContractInput().withName("city").withType(ContractInputType.TEXT))))
                .build();
    }

    private BusinessObjectData aData(String name, String type) {
        BusinessObjectData data = ProcessFactory.eINSTANCE.createBusinessObjectData();
        data.setName(name);
        data.setClassName(type);
        return data;
    }

}
