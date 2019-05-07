/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder.aBO;
import static org.bonitasoft.studio.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.studio.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContractConstraintBuilderTest {

    private static final boolean NULLABLE = true;

    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> bdmStore;

    @Before
    public void setUp() throws Exception {
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(bdmStore);
    }

    @Test
    public void should_create_a_constraint_for_mandatory_field() throws Exception {
        ContractConstraintBuilder builder = createBuilder();
        BusinessObject invoice = aBO("org.test.Invoice")
                .withField(invoiceLines(!NULLABLE))
                .build();
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Invoice")).thenReturn(Optional.of(invoice));
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.InvoiceLine"))
                .thenReturn(Optional.of(invoiceLineBo()));

        List<ContractConstraint> constraints = builder.buildConstraints(aContractInput()
                .withName("invoiceInput")
                .withType(ContractInputType.COMPLEX)
                .withDataReference("invoice")
                .havingInput(aContractInput()
                        .withName("invoiceLines")
                        .withType(ContractInputType.COMPLEX)
                        .single()
                        .havingInput(aContractInput()
                                .withName("price")))
                .build(),
                aPool().havingData(aBusinessData()
                        .withName("invoice")
                        .withClassname("org.test.Invoice")).build());

        assertThat(constraints).extracting("name", "expression", "errorMessage", "inputNames")
                .contains(tuple("mandatory_invoiceInput_invoiceLines", "invoiceInput?.invoiceLines != null",
                        "InvoiceLine is mandatory for Invoice", Collections.singletonList("invoiceInput")));
    }

    @Test
    public void should_not_create_a_constraint_for_nullable_field() throws Exception {
        ContractConstraintBuilder builder = createBuilder();
        BusinessObject invoice = aBO("org.test.Invoice")
                .withField(invoiceLines(NULLABLE))
                .build();
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Invoice")).thenReturn(Optional.of(invoice));
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.InvoiceLine"))
                .thenReturn(Optional.of(invoiceLineBo()));

        List<ContractConstraint> constraints = builder.buildConstraints(aContractInput()
                .withName("invoiceInput")
                .withType(ContractInputType.COMPLEX)
                .withDataReference("invoice")
                .havingInput(aContractInput()
                        .withName("invoiceLines")
                        .withType(ContractInputType.COMPLEX)
                        .single()
                        .havingInput(aContractInput()
                                .withName("price")))
                .build(),
                aPool().havingData(aBusinessData()
                        .withName("invoice")
                        .withClassname("org.test.Invoice")).build());

        assertThat(constraints).isEmpty();
    }

    @Test
    public void should_create_a_constraint_for_mandatory_field_in_a_multiple_parent() throws Exception {
        ContractConstraintBuilder builder = createBuilder();
        BusinessObject invoice = aBO("org.test.Invoice")
                .withField(invoiceLines(!NULLABLE))
                .build();
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Invoice")).thenReturn(Optional.of(invoice));
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.InvoiceLine"))
                .thenReturn(Optional.of(invoiceLineBo()));
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Product"))
                .thenReturn(Optional.of(productBo()));

        List<ContractConstraint> constraints = builder.buildConstraints(aContractInput()
                .withName("invoiceInput")
                .withType(ContractInputType.COMPLEX)
                .withDataReference("invoice")
                .havingInput(aContractInput()
                        .withName("invoiceLines")
                        .withType(ContractInputType.COMPLEX)
                        .multiple()
                        .havingInput(aContractInput()
                                .withName("product")
                                .withType(ContractInputType.COMPLEX)))
                .build(),
                aPool().havingData(aBusinessData()
                        .withName("invoice")
                        .withClassname("org.test.Invoice")).build());

        assertThat(constraints).extracting("name", "expression", "errorMessage", "inputNames")
                .contains(tuple("mandatory_invoiceInput_invoiceLines_product",
                        "invoiceInput?.invoiceLines?.product.flatten().every{it!=null}",
                        "Product is mandatory for InvoiceLine", Collections.singletonList("invoiceInput")));
    }

    @Test
    public void should_create_a_constraint_for_aggregated_fields_persistenceId_in_a_multiple_parent() throws Exception {
        ContractConstraintBuilder builder = createBuilder();
        BusinessObject invoice = aBO("org.test.Invoice")
                .withField(invoiceLines(!NULLABLE))
                .build();
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Invoice")).thenReturn(Optional.of(invoice));
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.InvoiceLine"))
                .thenReturn(Optional.of(invoiceLineBo()));
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Product"))
                .thenReturn(Optional.of(productBo()));

        List<ContractConstraint> constraints = builder.buildConstraints(aContractInput()
                .withName("invoiceInput")
                .withType(ContractInputType.COMPLEX)
                .withDataReference("invoice")
                .havingInput(aContractInput()
                        .withName("invoiceLines")
                        .withType(ContractInputType.COMPLEX)
                        .multiple()
                        .havingInput(aContractInput()
                                .withName("product")
                                .withType(ContractInputType.COMPLEX)))
                .build(),
                aPool().havingData(aBusinessData()
                        .withName("invoice")
                        .withClassname("org.test.Invoice")).build());

        assertThat(constraints).extracting("name", "expression", "errorMessage", "inputNames")
                .contains(tuple("aggregation_invoiceInput_invoiceLines_product",
                        "invoiceInput?.invoiceLines?.product.flatten().every{!it || it.persistenceId_string}",
                        "Product must reference an existing instance with a persistenceId for InvoiceLine",
                        Collections.singletonList("invoiceInput")));
    }

    @Test
    public void should_create_a_constraint_for_aggregated_fields_persistenceId() throws Exception {
        ContractConstraintBuilder builder = createBuilder();
        BusinessObject invoice = aBO("org.test.Invoice")
                .withField(customerAggregation())
                .build();
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Invoice")).thenReturn(Optional.of(invoice));
        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Customer"))
                .thenReturn(Optional.of(customerBo()));

        List<ContractConstraint> constraints = builder.buildConstraints(aContractInput()
                .withName("invoiceInput")
                .withType(ContractInputType.COMPLEX)
                .withDataReference("invoice")
                .havingInput(aContractInput()
                        .withName("customer")
                        .withType(ContractInputType.COMPLEX)
                        .single()
                        .havingInput(aContractInput()
                                .withName("name")
                                .withType(ContractInputType.TEXT)))
                .build(),
                aPool().havingData(aBusinessData()
                        .withName("invoice")
                        .withClassname("org.test.Invoice")).build());

        assertThat(constraints).extracting("name", "expression", "errorMessage", "inputNames")
                .contains(tuple("aggregation_invoiceInput_customer",
                        "!invoiceInput?.customer || invoiceInput?.customer?.persistenceId_string",
                        "Customer must reference an existing instance with a persistenceId for Invoice",
                        Collections.singletonList("invoiceInput")));
    }

    @Test
    public void should_create_a_constraint_for_field_with_type_long() {
        ContractConstraintBuilder builder = createBuilder();
        BusinessObject invoice = aBO("org.test.Invoice")
                .withField(SimpleFieldBuilder.aLongField("amount").build())
                .build();

        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Invoice")).thenReturn(Optional.of(invoice));

        List<ContractConstraint> constraints = builder.buildConstraints(aContractInput()
                .withName("invoiceInput")
                .withType(ContractInputType.COMPLEX)
                .withDataReference("invoice")
                .havingInput(aContractInput()
                        .withName("amount")
                        .withType(ContractInputType.TEXT)
                        .single())
                .build(),
                aPool().havingData(aBusinessData()
                        .withName("invoice")
                        .withClassname("org.test.Invoice")).build());

        assertThat(constraints).extracting("name", "expression", "errorMessage", "inputNames")
                .contains(tuple("type_long_invoiceInput_amount",
                        "!invoiceInput?.amount || invoiceInput?.amount.isLong()",
                        "A Long value is expected for Invoice.amount",
                        Collections.singletonList("invoiceInput")));
    }

    @Test
    public void should_create_a_constraint_for_field_with_type_long_and_multiple_parent() {
        ContractConstraintBuilder builder = createBuilder();
        BusinessObject invoice = aBO("org.test.Invoice")
                .withField(SimpleFieldBuilder.aLongField("amount").build())
                .build();

        when(bdmStore.getBusinessObjectByQualifiedName("org.test.Invoice")).thenReturn(Optional.of(invoice));

        List<ContractConstraint> constraints = builder.buildConstraints(aContractInput()
                .withName("invoiceInput")
                .multiple()
                .withType(ContractInputType.COMPLEX)
                .withDataReference("invoice")
                .havingInput(aContractInput()
                        .withName("amount")
                        .withType(ContractInputType.TEXT)
                        .single())
                .build(),
                aPool().havingData(aBusinessData()
                        .withName("invoice")
                        .withClassname("org.test.Invoice")).build());

        assertThat(constraints).extracting("name", "expression", "errorMessage", "inputNames")
                .contains(tuple("type_long_invoiceInput_amount",
                        "invoiceInput?.amount.flatten().findAll().every{ it.isLong() }",
                        "A Long value is expected for Invoice.amount",
                        Collections.singletonList("invoiceInput")));
    }

    private RelationField customerAggregation() {
        return RelationFieldBuilder.anAggregationField("customer", customerBo());
    }

    private ContractConstraintBuilder createBuilder() {
        return new ContractConstraintBuilder(repositoryAccessor);
    }

    private RelationField invoiceLines(boolean nullable) {
        RelationField relationField = RelationFieldBuilder.aCompositionField("invoiceLines", invoiceLineBo());
        relationField.setNullable(nullable);
        relationField.setCollection(true);
        return relationField;
    }

    private BusinessObject invoiceLineBo() {
        RelationField productField = RelationFieldBuilder.anAggregationField("product", productBo());
        productField.setNullable(false);
        return aBO("org.test.InvoiceLine")
                .withField(SimpleFieldBuilder.aDoubleField("price").build())
                .withField(productField)
                .build();
    }

    private BusinessObject productBo() {
        return aBO("org.test.Product")
                .withField(SimpleFieldBuilder.aDoubleField("price").build())
                .build();
    }

    private BusinessObject customerBo() {
        return aBO("org.test.Customer")
                .withField(SimpleFieldBuilder.aStringField("name").build())
                .build();
    }

}
