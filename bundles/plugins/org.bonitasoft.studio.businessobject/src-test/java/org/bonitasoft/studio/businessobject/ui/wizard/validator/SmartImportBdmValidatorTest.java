package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class SmartImportBdmValidatorTest {

    @Test
    public void should_return_error_if_bo_are_duplicated_in_different_packages() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        bo1.addField(SimpleFieldBuilder.aStringField("field").build());
        BusinessObject bo2 = new BusinessObject("org.company.Person");
        bo2.addField(SimpleFieldBuilder.aStringField("field").build());
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);

        IStatus status = new SmartImportBdmValidator().validateCompatibility(model1, model2);

        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_return_error_if_bo_are_conflicting() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        bo1.addField(SimpleFieldBuilder.aStringField("field1").build());
        BusinessObject bo2 = new BusinessObject("com.company.Person");
        bo2.addField(SimpleFieldBuilder.aStringField("field2").build());
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);

        IStatus status = new SmartImportBdmValidator().validateCompatibility(model1, model2);

        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_return_ok_if_bo_are_equals() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        bo1.addField(SimpleFieldBuilder.aStringField("field").build());
        BusinessObject bo2 = new BusinessObject("com.company.Person");
        bo2.addField(SimpleFieldBuilder.aStringField("field").build());
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);

        IStatus status = new SmartImportBdmValidator().validateCompatibility(model1, model2);

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_return_ok_if_bo_names_are_not_duplicated() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        BusinessObject bo2 = new BusinessObject("com.company.Employee");
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);

        IStatus status = new SmartImportBdmValidator().validateCompatibility(model1, model2);

        StatusAssert.assertThat(status).isOK();
    }

}
