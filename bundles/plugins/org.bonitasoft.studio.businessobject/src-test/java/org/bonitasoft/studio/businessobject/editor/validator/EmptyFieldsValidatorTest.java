package org.bonitasoft.studio.businessobject.editor.validator;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.junit.Test;

public class EmptyFieldsValidatorTest {

    @Test
    public void should_validate_bo_has_at_least_one_field() {
        EmptyFieldsValidator validator = new EmptyFieldsValidator();

        BusinessObject businessObject = new BusinessObjectBuilder()
                .withQualifiedName("myObject")
                .create();
        StatusAssert.assertThat(validator.validate(businessObject)).isError();
        businessObject.getFields().add(new SimpleFieldBuilder().withName("aField").create());
        StatusAssert.assertThat(validator.validate(businessObject)).isOK();
    }

}
