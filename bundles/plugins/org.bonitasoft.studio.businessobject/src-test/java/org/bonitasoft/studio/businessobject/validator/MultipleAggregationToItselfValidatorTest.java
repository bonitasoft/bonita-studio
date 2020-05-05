package org.bonitasoft.studio.businessobject.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.MultipleAggregationToItselfValidator;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class MultipleAggregationToItselfValidatorTest {

    @Test
    public void should_validate_multiple_aggregation_to_itself() {
        MultipleAggregationToItselfValidator validator = new MultipleAggregationToItselfValidator();
        BusinessObject businessObject = createBusinessObject("com.myBo");
        businessObject.getFields().add(createMultipleAggregationField(businessObject, "field"));

        IStatus status = validator.validate(businessObject);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage())
                .isEqualTo(String.format(Messages.multipleAggregationToItself, businessObject.getSimpleName()));
    }

    private RelationField createMultipleAggregationField(BusinessObject reference, String fieldName) {
        return new RelationFieldBuilder()
                .withCollection(true)
                .withName(fieldName)
                .withReference(reference)
                .withType(RelationType.AGGREGATION)
                .create();
    }

    private BusinessObject createBusinessObject(String name) {
        return new BusinessObjectBuilder()
                .withQualifiedName(String.format("com.%s", name))
                .create();
    }

}
