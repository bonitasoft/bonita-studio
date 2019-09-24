package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BusinessObjectNameCellEditorValidatorTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    BusinessObjectNameCellEditorValidator validator;
    IObservableValue<Object> selectionObservable;

    @Before
    public void init() {
        BusinessObjectModel model = new BusinessObjectModel();
        selectionObservable = new WritableValue<>();
        validator = new BusinessObjectNameCellEditorValidator(model, selectionObservable);
    }

    @Test
    public void should_validatePackage_returns_an_error_status_for_null_or_empty_package() throws Exception {
        IStatus status = validator.validate(null);
        StatusAssert.assertThat(status).isError();
        status = validator.validate("");
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_validate_input_length() {
        IStatus status = validator.validateInputLength(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_validate_invalid_char() {
        IStatus status = validator.validateInvalidChar("test_");
        StatusAssert.assertThat(status).isError();

        status = validator.validateInvalidChar("test ");
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_validate_sql_validity() {
        IStatus status = validator.validateSQLValidity("select");
        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_validate_uniqueness() {
        BusinessObject bo1 = new BusinessObject();
        BusinessObject bo2 = new BusinessObject();
        bo1.setQualifiedName("com.company.model.myObject1");
        bo2.setQualifiedName("com.company.model.myObject2");

        BusinessObjectModel model = new BusinessObjectModel();
        model.addBusinessObject(bo1);
        model.addBusinessObject(bo2);
        validator = new BusinessObjectNameCellEditorValidator(model, selectionObservable);

        selectionObservable.setValue(bo1);
        IStatus status = validator.validateUniqueness("myObject1");
        StatusAssert.assertThat(status).isOK();
        status = validator.validateUniqueness("myObject2");
        StatusAssert.assertThat(status).isError();
    }

}
