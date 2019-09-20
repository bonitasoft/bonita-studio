package org.bonitasoft.studio.businessobject.ui.wizard.validator;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class MergeBdmValidatorTest {

    @Test
    public void should_return_error_if_bo_names_are_duplicated() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        BusinessObject bo2 = new BusinessObject("org.company.Person");
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);

        IStatus status = new MergeBdmValidator(model1).validateCompatibility(model1, model2);

        StatusAssert.assertThat(status).isError();
    }

    @Test
    public void should_return_ok_if_bo_names_are_not_duplicated() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        BusinessObject bo2 = new BusinessObject("com.company.Employee");
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);

        IStatus status = new MergeBdmValidator(model1).validateCompatibility(model1, model2);

        StatusAssert.assertThat(status).isOK();
    }

}
