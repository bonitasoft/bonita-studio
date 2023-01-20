package org.bonitasoft.studio.businessobject.core.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.model.SmartImportBdmModel;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SmartImportBDMOperationTest {

    private static final String IMPORTED_PACKAGE_1 = "com.bonitasoft";
    private static final String IMPORTED_PACKAGE_2 = "org.bonitasoft";

    private static final String IMPORTED_BO_1 = "BusinessObject1";
    private static final String IMPORTED_BO_2 = "BusinessObject2";

    private static final String IMPORTED_FIELD_1 = "field1";
    private static final String IMPORTED_FIELD_2 = "field2";

    BusinessObjectModelConverter converter = new BusinessObjectModelConverter();
    private BusinessObjectModel importedBdm;
    private File importedFile;

    @Before
    public void init() throws Exception {
        importedBdm = createImportedBdm();
        importedFile = createBdmFile(importedBdm);
    }

    @After
    public void clean() {
        importedFile.delete();
    }

    @Test
    public void should_merge_disjoint_models() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo = BusinessObjectBuilder.aBO("com.company.OtherBusinessObject")
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        existingBdm.addBusinessObject(bo);
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);

        SmartImportBdmModel importedModel = new SmartImportBdmModel(fileStore, converter, null);
        importedModel.buildSmartImportModel(importedFile);

        new SmartImportBDMOperation(null, importedModel).performImport(existingBdm);
        assertThat(existingBdm.getBusinessObjects())
                .containsExactlyInAnyOrder(bo,
                        importedBdm.getBusinessObjects().get(0),
                        importedBdm.getBusinessObjects().get(1));
    }

    @Test
    public void should_merge_conflicting_models_according_to_user_choices() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject existingBo1 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("existingField1").build())
                .build();
        BusinessObject existingBo4 = BusinessObjectBuilder
                .aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, "NewBusinessObject"))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        BusinessObject existingBo2 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_2, IMPORTED_BO_2))
                .withField(SimpleFieldBuilder.aStringField("existingField2").build())
                .build();
        BusinessObject existingBo3 = BusinessObjectBuilder
                .aBO(String.format("%s.%s", IMPORTED_PACKAGE_2, "OtherBusinessObject"))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        existingBdm.addBusinessObject(existingBo1);
        existingBdm.addBusinessObject(existingBo2);
        existingBdm.addBusinessObject(existingBo3);
        existingBdm.addBusinessObject(existingBo4);
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);

        SmartImportBdmModel importedModel = new SmartImportBdmModel(fileStore, converter, null);
        importedModel.buildSmartImportModel(importedFile);
        importedModel.getSmartImportableUnits().stream()
                .filter(unit -> unit.getName().equals(IMPORTED_PACKAGE_1)).findFirst().get()
                .setImportAction(ImportAction.KEEP);

        new SmartImportBDMOperation(null, importedModel).performImport(existingBdm);
        BusinessObject importedBo2 = importedBdm.getBusinessObjects().stream()
                .filter(bo -> bo.getSimpleName().equals(IMPORTED_BO_2))
                .findFirst().get();
        assertThat(existingBdm.getBusinessObjects())
                .containsExactlyInAnyOrder(existingBo1, importedBo2, existingBo4);
    }

    private BusinessObjectModel createImportedBdm() {
        BusinessObjectModel importedBdm = new BusinessObjectModel();
        BusinessObject bo1 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField(IMPORTED_FIELD_1).build())
                .build();
        BusinessObject bo2 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_2, IMPORTED_BO_2))
                .withField(SimpleFieldBuilder.aStringField(IMPORTED_FIELD_2).build())
                .build();
        importedBdm.addBusinessObject(bo1);
        importedBdm.addBusinessObject(bo2);
        return importedBdm;
    }

    private File createBdmFile(BusinessObjectModel bdm) throws Exception {
        File file = new File("bom.xml");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(converter.marshall(bdm));
        }
        return file;
    }

}
