package org.bonitasoft.studio.businessobject.editor.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectModelBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.PackageBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class AttributeReferenceExitenceValidatorTest {

    IObservableValue<BusinessObjectModel> modelObservable;
    BusinessObject businessObjectWithUnknownBo;
    BusinessObject businessObjectValid;
    RelationField invalidField;
    RelationField validField;

    @Before
    public void init() {
        modelObservable = mock(IObservableValue.class);

        businessObjectWithUnknownBo = createBusinessObject("bo1");
        businessObjectValid = createBusinessObject("bo2");

        invalidField = new RelationFieldBuilder()
                .withName("field")
                .withType(RelationType.COMPOSITION)
                .withReference(createBusinessObject("unknownBo"))
                .create();
        businessObjectWithUnknownBo.getFields().add(invalidField);

        validField = new RelationFieldBuilder()
                .withType(RelationType.AGGREGATION)
                .withReference(businessObjectWithUnknownBo)
                .create();
        businessObjectValid.getFields().add(validField);

        Package pakage = new PackageBuilder()
                .withName("com")
                .withBusinessObjects(businessObjectWithUnknownBo, businessObjectValid)
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
    public void should_validate_unknown_attribute_reference_on_businessObjects() {
        AttributeReferenceExitenceValidator validator = new AttributeReferenceExitenceValidator(modelObservable);

        StatusAssert.assertThat(validator.validate(businessObjectValid)).isOK();

        IStatus status = validator.validate(businessObjectWithUnknownBo);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getChildren()).extracting(IStatus::getMessage)
                .containsExactly(String.format(Messages.unknownBusinessObjectReference, invalidField.getName()));
    }

    @Test
    public void should_validate_unknown_attribute_reference_on_attributes() {
        AttributeReferenceExitenceValidator validator = new AttributeReferenceExitenceValidator(modelObservable);

        StatusAssert.assertThat(validator.validate(validField)).isOK();

        IStatus status = validator.validate(invalidField);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage())
                .isEqualTo(String.format(Messages.unknownBusinessObjectReference, invalidField.getName()));
    }

}
