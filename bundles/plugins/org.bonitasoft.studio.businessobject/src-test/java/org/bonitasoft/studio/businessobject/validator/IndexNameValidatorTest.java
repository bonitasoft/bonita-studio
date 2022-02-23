package org.bonitasoft.studio.businessobject.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectModelBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.IndexBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.PackageBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.IndexNameValidator;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class IndexNameValidatorTest {

    private static final String DUPLICATED_NAME = "duplicated";

    @Mock
    IObservableValue<BusinessObjectModel> modelObservable = mock(IObservableValue.class);

    IndexNameValidator validator;
    Index index;

    @Before
    public void init() {
        initModel();
        validator = spy(new IndexNameValidator(modelObservable));
        doReturn(ValidationStatus.ok()).when(validator).validateJavaConvention(anyString());
        index = new IndexBuilder().create();
    }

    private void initModel() {

        BusinessObject businessObject1 = new BusinessObjectBuilder()
                .withIndexes(new IndexBuilder().withName(DUPLICATED_NAME).create())
                .create();

        Package pakage = new PackageBuilder()
                .withName("com")
                .withBusinessObjects(businessObject1)
                .create();

        BusinessObjectModel bdm = new BusinessObjectModelBuilder()
                .withPackages(pakage)
                .create();

        when(modelObservable.getValue()).thenReturn(bdm);
    }

    @Test
    public void should_validate_empty_name() {
        String emptyName = "";
        index.setName(emptyName);
        IStatus status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.indexWithoutName);

        String nullName = null;
        index.setName(nullName);
        status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.indexWithoutName);
    }

    @Test
    public void should_validate_name_length() {
        String nameToLong = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        index.setName(nameToLong);
        IStatus status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(org.bonitasoft.studio.common.Messages.bind(org.bonitasoft.studio.common.Messages.fieldIsTooLong,
                        nameToLong, IndexNameValidator.MAX_INDEX_NAME_LENGTH));
    }

    @Test
    public void should_validate_sql_keyword() {
        String invalidName = "SELECT";
        index.setName(invalidName);
        IStatus status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.reservedKeyWord, invalidName));
    }

    @Test
    public void should_validate_sql_invalid_identifier() {
        String invalidName = "*";
        index.setName(invalidName);
        IStatus status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.invalidSQLIdentifier, invalidName));
    }

    @Test
    public void should_validate_uniqueness() {
        String duplicatedName = DUPLICATED_NAME;
        index.setName(duplicatedName);
        IStatus status = validator.validate(index);
        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.indexNameAlreadyExists);
    }

}
