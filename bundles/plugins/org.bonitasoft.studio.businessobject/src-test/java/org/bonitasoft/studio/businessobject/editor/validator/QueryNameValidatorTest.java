package org.bonitasoft.studio.businessobject.editor.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.converter.BusinessDataModelConverter;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class QueryNameValidatorTest {

    private static final String QUERY1_NAME = "query1";

    private BusinessDataModelConverter converter = new BusinessDataModelConverter();
    private IObservableValue<BusinessObject> boObservable;
    private QueryNameValidator validator;

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    @Before
    public void init() {
        boObservable = new WritableValue<>();
        validator = spy(new QueryNameValidator(boObservable));
        doReturn(ValidationStatus.ok()).when(validator).validateJavaConvention(anyString());
    }

    @Test
    public void should_validate_null_or_empty_name() {
        Query query = new QueryBuilder().withName(null).create();
        boObservable.setValue(createBusinessObject(query));
        IStatus status = validator.validate(query);

        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.queryNameRequired);

        query.setName("");
        status = validator.validate(query);

        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.queryNameRequired);
    }

    @Test
    public void should_validate_name_length() {
        String nameToLong = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Query query = new QueryBuilder().withName(nameToLong)
                .create();
        boObservable.setValue(createBusinessObject(query));
        IStatus status = validator.validate(query);

        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(org.bonitasoft.studio.common.Messages.bind(org.bonitasoft.studio.common.Messages.fieldIsTooLong,
                        nameToLong, QueryNameValidator.MAX_QUERY_NAME_LENGTH));
    }

    @Test
    public void should_validate_provided_queries_name() {
        Query query = new QueryBuilder().withName("find").create();
        boObservable.setValue(createBusinessObject(query));
        IStatus status = validator.validate(query);

        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.queryNameReserved, "find"));
    }

    @Test
    public void should_validate_uniqueness() {
        Query query = new QueryBuilder().withName(QUERY1_NAME).create();
        boObservable.setValue(createBusinessObject(query));
        IStatus status = validator.validate(query);

        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.queryNameAlreadyExists);
    }

    private BusinessObject createBusinessObject(Query query) {
        BusinessObject bo = new BusinessObjectBuilder()
                .withQualifiedName("com.myBo")
                .withQueries(query, new QueryBuilder()
                        .withName(QUERY1_NAME)
                        .create())
                .create();

        converter.createDefaultQueries(bo)
                .forEach(bo.getDefaultQueries()::add);

        return bo;
    }

}
