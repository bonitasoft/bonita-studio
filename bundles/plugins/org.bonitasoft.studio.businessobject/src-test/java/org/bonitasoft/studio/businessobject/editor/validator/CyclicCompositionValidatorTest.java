package org.bonitasoft.studio.businessobject.editor.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class CyclicCompositionValidatorTest {

    @Test
    public void should_detect_a_cycle_when_bo_reference_himself_in_composition() {
        CyclicCompositionValidator validator = new CyclicCompositionValidator();
        BusinessObject bo1 = createBusinessObject("bo1");
        bo1.getFields().add(new RelationFieldBuilder()
                .withType(RelationType.COMPOSITION)
                .withReference(bo1)
                .create());

        IStatus status = validator.validate(bo1);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(String.format(Messages.cyclicComposition, bo1.getSimpleName()));
    }

    @Test
    public void should_detect_a_cycle_between_several_objects() {
        CyclicCompositionValidator validator = new CyclicCompositionValidator();
        BusinessObject bo1 = createBusinessObject("bo1");
        BusinessObject bo2 = createBusinessObject("bo2");
        bo1.getFields().add(new RelationFieldBuilder()
                .withType(RelationType.COMPOSITION)
                .withReference(bo2)
                .create());
        bo2.getFields().add(new RelationFieldBuilder()
                .withType(RelationType.COMPOSITION)
                .withReference(bo1)
                .create());

        IStatus status = validator.validate(bo1);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(String.format(Messages.cyclicComposition, bo1.getSimpleName()));

        status = validator.validate(bo2);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(String.format(Messages.cyclicComposition, bo2.getSimpleName()));
    }

    @Test
    public void should_not_detect_a_cycle_if_relation_is_aggregation() {
        CyclicCompositionValidator validator = new CyclicCompositionValidator();
        BusinessObject bo1 = createBusinessObject("bo1");
        BusinessObject bo2 = createBusinessObject("bo2");
        bo1.getFields().add(new RelationFieldBuilder()
                .withType(RelationType.COMPOSITION)
                .withReference(bo2)
                .create());
        bo2.getFields().add(new RelationFieldBuilder()
                .withType(RelationType.AGGREGATION)
                .withReference(bo1)
                .create());

        IStatus status = validator.validate(bo1);
        StatusAssert.assertThat(status).isOK();

        status = validator.validate(bo2);
        StatusAssert.assertThat(status).isOK();
    }

    private BusinessObject createBusinessObject(String name) {
        return new BusinessObjectBuilder()
                .withQualifiedName(String.format("com.%s", name))
                .create();
    }

}
