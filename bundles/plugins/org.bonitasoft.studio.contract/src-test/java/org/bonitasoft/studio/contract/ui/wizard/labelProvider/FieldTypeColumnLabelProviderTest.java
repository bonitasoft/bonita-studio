package org.bonitasoft.studio.contract.ui.wizard.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.engine.bpm.contract.Type;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.RelationFieldBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.junit.Test;

public class FieldTypeColumnLabelProviderTest {

    @Test
    public void should_return_simple_field_type_name() {
        final SimpleFieldToContractInputMapping mapping = new SimpleFieldToContractInputMapping((SimpleField) SimpleFieldBuilder.aTextField("employee")
                .withName("employee").build());
        final FieldTypeColumnLabelProvider provider = new FieldTypeColumnLabelProvider();
        assertThat(provider.getText(mapping)).isEqualTo(Type.TEXT.name());
    }

    @Test
    public void should_return_complex_field_type_name() {
        final RelationFieldToContractInputMapping mapping = new RelationFieldToContractInputMapping((RelationField) RelationFieldBuilder.aCompositionField(
                "employee",
                BusinessObjectBuilder.aBO("com.company.Manager").build()));
        final FieldTypeColumnLabelProvider provider = new FieldTypeColumnLabelProvider();
        assertThat(provider.getText(mapping)).isEqualTo("com.company.Manager");
    }
}
