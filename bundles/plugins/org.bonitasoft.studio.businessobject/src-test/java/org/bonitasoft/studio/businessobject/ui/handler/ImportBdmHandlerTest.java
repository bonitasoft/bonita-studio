package org.bonitasoft.studio.businessobject.ui.handler;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.junit.Test;

public class ImportBdmHandlerTest {

    @Test
    public void should_merge_models() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        BusinessObject bo2 = new BusinessObject("org.company.Employee");
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);
        new ImportBdmHandler().performMerge(model1, model2);
        assertThat(model1.getBusinessObjects()).containsExactly(bo1, bo2);
    }

}
