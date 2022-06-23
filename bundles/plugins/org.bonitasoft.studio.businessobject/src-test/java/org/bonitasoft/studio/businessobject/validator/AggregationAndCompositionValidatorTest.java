package org.bonitasoft.studio.businessobject.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectModelBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.PackageBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.AggregationAndCompositionValidator;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class AggregationAndCompositionValidatorTest {

    IObservableValue<BusinessObjectModel> modelObservable;
    BusinessObject businessObject1;
    BusinessObject businessObject2;

    @Before
    public void init() {
        modelObservable = mock(IObservableValue.class);

        businessObject1 = createBusinessObject("bo1");
        businessObject2 = createBusinessObject("bo2");

        businessObject1.getFields().add(new RelationFieldBuilder()
                .withType(RelationType.AGGREGATION)
                .withReference(businessObject2)
                .create());
        businessObject1.getFields().add(new RelationFieldBuilder()
                .withType(RelationType.COMPOSITION)
                .withReference(businessObject2)
                .create());

        Package pakage = new PackageBuilder()
                .withName("com")
                .withBusinessObjects(businessObject1, businessObject2)
                .create();

        BusinessObjectModel bom = new BusinessObjectModelBuilder()
                .withPackages(pakage)
                .create();
        when(modelObservable.getValue()).thenReturn(bom);
    }

    private BusinessObject createBusinessObject(String name) {
        return new BusinessObjectBuilder()
                .withQualifiedName(String.format("com.%s", name))
                .create();
    }

    @Test
    public void should_return_warning_if_bo_used_in_composition_and_aggregation() {
        AggregationAndCompositionValidator validator = new AggregationAndCompositionValidator(modelObservable);

        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isOK();

        status = validator.validate(businessObject2);
        StatusAssert.assertThat(status).isWarning();
        assertThat(status.getMessage()).isEqualTo(
                String.format(Messages.boUsedInCompositionAndAggregation, businessObject2.getSimpleName()));

    }

}
