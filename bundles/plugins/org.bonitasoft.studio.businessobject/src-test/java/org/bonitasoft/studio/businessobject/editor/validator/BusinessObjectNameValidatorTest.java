package org.bonitasoft.studio.businessobject.editor.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectModelBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.PackageBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;

public class BusinessObjectNameValidatorTest {

    BusinessObjectNameValidator validator;
    BusinessObject businessObject1;

    @Before
    public void init() {
        businessObject1 = createBusinessObject("businessObject1");
        BusinessObject businessObject2 = createBusinessObject("businessObject2");

        Package pakage = new PackageBuilder()
                .withName("com")
                .withBusinessObjects(businessObject1, businessObject2)
                .create();

        BusinessObjectModel bom = new BusinessObjectModelBuilder()
                .withPackages(pakage)
                .create();
        IObservableValue<BusinessObjectModel> bomObservable = mock(IObservableValue.class);
        when(bomObservable.getValue()).thenReturn(bom);
        validator = spy(new BusinessObjectNameValidator(bomObservable));
        doReturn(ValidationStatus.ok()).when(validator).validateJavaConvention(anyString());
    }

    private BusinessObject createBusinessObject(String name) {
        return new BusinessObjectBuilder()
                .withQualifiedName(String.format("com.%s", name))
                .create();
    }

    @Test
    public void should_validate_a_valide_name() {
        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_validate_empty_name() {
        String emptyName = "";
        businessObject1.setSimpleName(emptyName);
        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.boNameRequired);

        String nullName = null;
        businessObject1.setSimpleName(nullName);
        status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.boNameRequired);
    }

    @Test
    public void should_validate_name_length() {
        String nameToLong = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        businessObject1.setSimpleName(nameToLong);
        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(org.bonitasoft.studio.common.Messages.bind(org.bonitasoft.studio.common.Messages.fieldIsTooLong,
                        nameToLong, BusinessObjectNameValidator.MAX_TABLE_NAME_LENGTH));
    }

    @Test
    public void should_validate_uniqueness() {
        String duplicatedName = "businessObject2";
        businessObject1.setSimpleName(duplicatedName);
        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.businessObjectNameAlreadyExists);
    }

    @Test
    public void should_validate_whitespace_char() {
        String invalidName = "a name";
        businessObject1.setSimpleName(invalidName);
        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.errorMessageNoWhitespaceInDataTypeNames);
    }

    @Test
    public void should_validate_underscore_char() {
        String invalidName = "a_name";
        businessObject1.setSimpleName(invalidName);
        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.errorMessageNoUnderscoreInBoNames);
    }

    @Test
    public void should_validate_sql_keyword() {
        String invalidName = "SELECT";
        businessObject1.setSimpleName(invalidName);
        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.reservedKeyWord, invalidName));
    }

    @Test
    public void should_validate_sql_invalid_identifier() {
        String invalidName = "*";
        businessObject1.setSimpleName(invalidName);
        IStatus status = validator.validate(businessObject1);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.invalidSQLIdentifier, invalidName));
    }

}
