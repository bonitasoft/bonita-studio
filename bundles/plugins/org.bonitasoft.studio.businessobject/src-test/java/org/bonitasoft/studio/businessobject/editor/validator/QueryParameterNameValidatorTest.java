package org.bonitasoft.studio.businessobject.editor.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryParameterBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class QueryParameterNameValidatorTest {

    private static final String DUPLICATED_NAME = "duplicated";

    IObservableValue<Query> queryObservable;
    private QueryParameterNameValidator validator;

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    @Before
    public void init() {
        queryObservable = new WritableValue<>();
        validator = spy(new QueryParameterNameValidator(queryObservable));
        doReturn(ValidationStatus.ok()).when(validator).validateJavaConvention(anyString());
    }

    @Test
    public void should_validate_null_or_empty_name() {
        QueryParameter queryParameter = new QueryParameterBuilder().withName(null).create();

        IStatus status = validator.validate(queryParameter);

        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.queryParameterNameRequired);

        queryParameter.setName("");
        status = validator.validate(queryParameter);

        StatusAssert.assertThat(status).isError();
        assertThat(status.getMessage()).isEqualTo(Messages.queryParameterNameRequired);
    }

    @Test
    public void should_validate_reserved_name() {
        queryObservable.setValue(createQuery());
        String name = BDMQueryUtil.START_INDEX_PARAM_NAME;
        QueryParameter queryParameter = new QueryParameterBuilder().withName(name).create();

        IStatus status = validator.validate(queryParameter);

        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.queryParameterNameReservedForPagination, name));
    }

    @Test
    public void should_validate_uniqueness() {
        queryObservable.setValue(createQuery());
        QueryParameter queryParameter = new QueryParameterBuilder().withName(DUPLICATED_NAME).create();

        IStatus status = validator.validate(queryParameter);

        StatusAssert.assertThat(status).isError();
        assertThat(Arrays.asList(status.getChildren()).stream().map(IStatus::getMessage))
                .contains(Messages.bind(Messages.queryParameterNameAlreadyExists, DUPLICATED_NAME));
    }

    private Query createQuery() {
        return new QueryBuilder()
                .withName("query")
                .withQueryParameters(new QueryParameterBuilder().withName(DUPLICATED_NAME).create())
                .create();
    }

}
