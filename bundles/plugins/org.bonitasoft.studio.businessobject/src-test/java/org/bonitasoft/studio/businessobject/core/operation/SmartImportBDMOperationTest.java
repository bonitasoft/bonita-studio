package org.bonitasoft.studio.businessobject.core.operation;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.junit.Test;

public class SmartImportBDMOperationTest {

    @Test
    public void should_merge_disjoint_models() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        BusinessObject bo2 = new BusinessObject("org.company.Employee");
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);
        new SmartImportBDMOperation(null, null).performImport(model1, model2);
        assertThat(model1.getBusinessObjects()).containsExactly(bo1, bo2);
    }

    @Test
    public void should_merge_models_without_duplicating_duplicates() {
        BusinessObjectModel model1 = new BusinessObjectModel();
        BusinessObjectModel model2 = new BusinessObjectModel();
        BusinessObject bo1 = new BusinessObject("com.company.Person");
        bo1.addField(SimpleFieldBuilder.aStringField("field").build());
        BusinessObject bo2 = new BusinessObject("org.company.Employee");
        BusinessObject bo3 = new BusinessObject("com.company.Person");
        bo3.addField(SimpleFieldBuilder.aStringField("field").build());
        model1.addBusinessObject(bo1);
        model2.addBusinessObject(bo2);
        model2.addBusinessObject(bo3);
        new SmartImportBDMOperation(null, null).performImport(model1, model2);
        assertThat(model1.getBusinessObjects()).containsExactly(bo1, bo2);
    }

}
