package org.bonitasoft.studio.contract.ui.wizard.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.junit.Test;

public class InputTypeColumnLabelProviderTest {

    @Test
    public void should_return_input_type_name() {
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping((SimpleField) SimpleFieldBuilder.aTextField("employee")
                .withName("employee").build());
        final InputTypeColumnLabelProvider provider = new InputTypeColumnLabelProvider();
        assertThat(provider.getText(mapping)).isEqualTo(ContractInputType.TEXT.name());
    }

    @Test
    public void should_return_complex_input_type_name() {
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping((RelationField) RelationFieldBuilder.aCompositionField(
                "employee",
                BusinessObjectBuilder.aBO("com.company.Manager").build()));
        final InputTypeColumnLabelProvider provider = new InputTypeColumnLabelProvider();
        assertThat(provider.getText(mapping)).isEqualTo(ContractInputType.COMPLEX.name());
    }

}
