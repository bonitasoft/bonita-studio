package org.bonitasoft.studio.businessobject.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OverwriteImportBdmModelTest {

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
    public void should_build_model_when_models_are_conflicting_through_packages() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo = BusinessObjectBuilder.aBO(String.format("%s.%s", "com.company", IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("otherField").build())
                .build();
        existingBdm.addBusinessObject(bo);
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);

        OverwriteImportBdmModel model = new OverwriteImportBdmModel(fileStore, converter, null);
        model.buildSmartImportModel(importedFile);

        assertThat(model.getConflictStatus()).isEqualTo(ConflictStatus.CONFLICTING);
        assertThat(model.getSmartImportableUnits()).hasSize(2);
        assertThat(model.getSmartImportableUnits()).allMatch(SmartImportPackageModel.class::isInstance);

        SmartImportableUnit package1 = model.getSmartImportableUnits().stream()
                .filter(unit -> Objects.equals(unit.getName(), IMPORTED_PACKAGE_1)).findFirst().get();
        SmartImportableUnit package2 = model.getSmartImportableUnits().stream()
                .filter(unit -> Objects.equals(unit.getName(), IMPORTED_PACKAGE_2)).findFirst().get();

        assertThat(package1.getConflictStatus()).isEqualTo(ConflictStatus.CONFLICTING);
        assertThat(package1.getSmartImportableUnits()).hasSize(1);
        assertThat(package1.getSmartImportableUnits()).allMatch(SmartImportBusinessObjectModel.class::isInstance);
        assertThat(package1.getSmartImportableUnits()).extracting(SmartImportableUnit::getConflictStatus)
                .allMatch(ConflictStatus.CONFLICTING::equals);
        assertThat(package1.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getName)
                .containsExactly(IMPORTED_BO_1);
        assertThat(package1.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getImportAction)
                .containsExactly(ImportAction.OVERWRITE);

        assertThat(package2.getConflictStatus()).isEqualTo(ConflictStatus.NONE);
        assertThat(package2.getSmartImportableUnits()).hasSize(1);
        assertThat(package2.getSmartImportableUnits()).allMatch(SmartImportBusinessObjectModel.class::isInstance);
        assertThat(package2.getSmartImportableUnits()).extracting(SmartImportableUnit::getConflictStatus)
                .allMatch(ConflictStatus.NONE::equals);
        assertThat(package2.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getName)
                .containsExactly(IMPORTED_BO_2);
        assertThat(package2.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getImportAction)
                .containsExactly(ImportAction.OVERWRITE);
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
