package org.bonitasoft.studio.businessobject.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Objects;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.model.SmartImportBdmModel.ConflictResult;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SmartImportBdmModelTest {

    private static final String IMPORTED_PACKAGE_1 = "com.bonitasoft";
    private static final String IMPORTED_PACKAGE_2 = "org.bonitasoft";

    private static final String IMPORTED_BO_1 = "BusinessObject1";
    private static final String IMPORTED_BO_2 = "BusinessObject2";
    private static final String IMPORTED_BO_3 = "BusinessObject3";

    private static final String IMPORTED_FIELD_1 = "field1";
    private static final String IMPORTED_FIELD_2 = "field2";
    private static final String IMPORTED_FIELD_3 = "field3";

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
    public void should_build_model_when_there_is_no_existing_bdm() throws Exception {
        SmartImportBdmModel model = new SmartImportBdmModel(null, converter, null);
        model.buildSmartImportModel(importedFile);

        assertThat(model.getConflictStatus()).isEqualTo(ConflictStatus.NONE);
        assertThat(model.getSmartImportableUnits()).hasSize(2);
        assertThat(model.getSmartImportableUnits()).allMatch(SmartImportPackageModel.class::isInstance);
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getConflictStatus)
                .allMatch(ConflictStatus.NONE::equals);
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_PACKAGE_1, IMPORTED_PACKAGE_2);
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getImportAction)
                .allMatch(ImportAction.OVERWRITE::equals);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits).hasSize(3);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits)
                .allMatch(SmartImportBusinessObjectModel.class::isInstance);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits)
                .extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_BO_1, IMPORTED_BO_2, IMPORTED_BO_3);
    }

    @Test
    public void should_build_model_when_models_are_not_conflicting() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo1 = BusinessObjectBuilder.aBO("com.company.OtherBusinessObject")
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        BusinessObject bo2 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, "NewBusinessObject"))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        existingBdm.addBusinessObject(bo1);
        existingBdm.addBusinessObject(bo2);
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);

        SmartImportBdmModel model = new SmartImportBdmModel(fileStore, converter, null);
        model.buildSmartImportModel(importedFile);

        assertThat(model.getConflictStatus()).isEqualTo(ConflictStatus.NONE);
        assertThat(model.getSmartImportableUnits()).hasSize(3);
        assertThat(model.getSmartImportableUnits()).allMatch(SmartImportPackageModel.class::isInstance);
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getConflictStatus)
                .containsExactlyInAnyOrder(ConflictStatus.NONE, ConflictStatus.NONE, ConflictStatus.SAME_CONTENT);
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_PACKAGE_1, IMPORTED_PACKAGE_2, "com.company");
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getImportAction)
                .allMatch(ImportAction.OVERWRITE::equals);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits).hasSize(5);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits)
                .allMatch(SmartImportBusinessObjectModel.class::isInstance);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits)
                .extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_BO_1, IMPORTED_BO_2, IMPORTED_BO_3, "OtherBusinessObject",
                        "NewBusinessObject");
    }

    @Test
    public void should_build_model_when_models_are_identical() throws Exception {
        BusinessObjectModel existingBdm = createImportedBdm();
        BusinessObject bo4 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, "bo4"))
                .withField(SimpleFieldBuilder.aStringField(IMPORTED_FIELD_1).build())
                .build();
        existingBdm.getBusinessObjects().add(bo4);

        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);

        SmartImportBdmModel model = new SmartImportBdmModel(fileStore, converter, null);
        model.buildSmartImportModel(importedFile);

        assertThat(model.getConflictStatus()).isEqualTo(ConflictStatus.SAME_CONTENT);
        assertThat(model.getSmartImportableUnits()).hasSize(2);
        assertThat(model.getSmartImportableUnits()).allMatch(SmartImportPackageModel.class::isInstance);
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getConflictStatus)
                .allMatch(ConflictStatus.SAME_CONTENT::equals);
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_PACKAGE_1, IMPORTED_PACKAGE_2);
        assertThat(model.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getImportAction)
                .allMatch(ImportAction.OVERWRITE::equals);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits).hasSize(4);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits)
                .allMatch(SmartImportBusinessObjectModel.class::isInstance);
        assertThat(model.getSmartImportableUnits()).flatExtracting(SmartImportableUnit::getSmartImportableUnits)
                .extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_BO_1, IMPORTED_BO_2, IMPORTED_BO_3, "bo4");
    }

    @Test
    public void should_build_model_when_models_are_conflicting() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo1 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("otherField").build())
                .build();
        BusinessObject bo2 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, "OtherBusinessOject"))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        existingBdm.addBusinessObject(bo1);
        existingBdm.addBusinessObject(bo2);
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);

        SmartImportBdmModel model = new SmartImportBdmModel(fileStore, converter, null);
        model.buildSmartImportModel(importedFile);

        assertThat(model.getConflictStatus()).isEqualTo(ConflictStatus.CONFLICTING);
        assertThat(model.getSmartImportableUnits()).hasSize(2);
        assertThat(model.getSmartImportableUnits()).allMatch(SmartImportPackageModel.class::isInstance);

        SmartImportableUnit package1 = model.getSmartImportableUnits().stream()
                .filter(unit -> Objects.equals(unit.getName(), IMPORTED_PACKAGE_1)).findFirst().get();
        SmartImportableUnit package2 = model.getSmartImportableUnits().stream()
                .filter(unit -> Objects.equals(unit.getName(), IMPORTED_PACKAGE_2)).findFirst().get();

        assertThat(package1.getConflictStatus()).isEqualTo(ConflictStatus.CONFLICTING);
        assertThat(package1.getSmartImportableUnits()).hasSize(2);
        assertThat(package1.getSmartImportableUnits()).allMatch(SmartImportBusinessObjectModel.class::isInstance);
        assertThat(package1.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getConflictStatus)
                .containsExactlyInAnyOrder(ConflictStatus.CONFLICTING, ConflictStatus.NONE);
        assertThat(package1.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_BO_1, IMPORTED_BO_3);
        assertThat(package1.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getImportAction)
                .containsExactly(ImportAction.OVERWRITE, ImportAction.OVERWRITE);

        assertThat(package2.getConflictStatus()).isEqualTo(ConflictStatus.NONE);
        assertThat(package2.getSmartImportableUnits()).hasSize(1);
        assertThat(package2.getSmartImportableUnits()).allMatch(SmartImportBusinessObjectModel.class::isInstance);
        assertThat(package2.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getConflictStatus)
                .allMatch(ConflictStatus.NONE::equals);
        assertThat(package2.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getName)
                .containsExactly(IMPORTED_BO_2);
        assertThat(package2.getSmartImportableUnits())
                .extracting(SmartImportableUnit::getImportAction)
                .containsExactly(ImportAction.OVERWRITE);
    }

    @Test
    public void should_return_status_conflicting() {
        BusinessObject existingBo = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();

        BusinessObject importedBo = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("otherField").build())
                .build();

        ConflictResult conflictResult= new SmartImportBdmModel(null, null, null)
                .computeConflictStatus(Arrays.asList(existingBo), importedBo);
        assertThat(conflictResult.getStatus()).isEqualTo(ConflictStatus.CONFLICTING);
        assertThat(conflictResult.getConflictingObjectName()).isEqualTo(existingBo.getQualifiedName());
    }

    @Test
    public void should_return_status_none() {
        BusinessObject existingBo = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();

        BusinessObject importedBo = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_2, IMPORTED_BO_2))
                .withField(SimpleFieldBuilder.aStringField("otherField").build())
                .build();

        ConflictResult conflictResult = new SmartImportBdmModel(null, null, null)
                .computeConflictStatus(Arrays.asList(existingBo), importedBo);
        assertThat(conflictResult.getStatus()).isEqualTo(ConflictStatus.NONE);
    }

    @Test
    public void should_return_status_same_content() {
        BusinessObject existingBo = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();

        BusinessObject importedBo = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();

        ConflictResult conflictResult = new SmartImportBdmModel(null, null, null)
                .computeConflictStatus(Arrays.asList(existingBo), importedBo);
        assertThat(conflictResult.getStatus()).isEqualTo(ConflictStatus.SAME_CONTENT);
    }

    @Test
    public void should_return_bo_to_import() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo1 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("otherField").build())
                .build();
        BusinessObject bo2 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, "OtherBo"))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        existingBdm.addBusinessObject(bo1);
        existingBdm.addBusinessObject(bo2);
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);

        SmartImportBdmModel model = new SmartImportBdmModel(fileStore, converter, null);
        model.buildSmartImportModel(importedFile);

        assertThat(model.getBusinessObjectsToImport(Arrays.asList(IMPORTED_PACKAGE_1, IMPORTED_PACKAGE_2)))
                .containsExactlyInAnyOrder(importedBdm.getBusinessObjects().toArray(new BusinessObject[0]));

        BusinessObject expectedResult = importedBdm.getBusinessObjects().stream()
                .filter(importedBo -> Objects.equals(importedBo.getSimpleName(), IMPORTED_BO_2)).findFirst().get();

        assertThat(model.getBusinessObjectsToImport(Arrays.asList(IMPORTED_PACKAGE_2)))
                .containsExactlyInAnyOrder(importedBdm.getBusinessObjects().toArray(new BusinessObject[0]));

        model.getSmartImportableUnits().stream()
                .filter(packageUnit -> Objects.equals(packageUnit.getName(), IMPORTED_PACKAGE_1))
                .forEach(packageUnit -> packageUnit.setImportAction(ImportAction.KEEP));
        assertThat(model.getBusinessObjectsToImport(Arrays.asList(IMPORTED_PACKAGE_1, IMPORTED_PACKAGE_2)))
                .containsExactly(expectedResult);
    }

    @Test
    public void should_update_package_model_according_to_import_action() {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo1 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField("otherField").build())
                .build();
        BusinessObject bo2 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, "OtherBusinessOject"))
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        existingBdm.addBusinessObject(bo1);
        existingBdm.addBusinessObject(bo2);
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);

        SmartImportBdmModel model = new SmartImportBdmModel(fileStore, converter, null);
        model.buildSmartImportModel(importedFile);

        SmartImportableUnit package1 = model.getSmartImportableUnits().stream()
                .filter(unit -> Objects.equals(unit.getName(), IMPORTED_PACKAGE_1)).findFirst().get();
        assertThat(package1.getImportAction()).isEqualTo(ImportAction.OVERWRITE);
        assertThat(package1.getConflictStatus()).isEqualTo(ConflictStatus.CONFLICTING);
        assertThat(package1.getSmartImportableUnits()).extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_BO_1, IMPORTED_BO_3);
        assertThat(package1.getSmartImportableUnits()).extracting(SmartImportableUnit::getConflictStatus)
                .containsExactlyInAnyOrder(ConflictStatus.CONFLICTING, ConflictStatus.NONE);

        package1.setImportAction(ImportAction.KEEP);
        model.updateSmartImportPackageModel((SmartImportPackageModel) package1);
        assertThat(package1.getSmartImportableUnits()).extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_BO_1, "OtherBusinessOject");
        assertThat(package1.getSmartImportableUnits()).extracting(SmartImportableUnit::getConflictStatus)
                .containsExactlyInAnyOrder(ConflictStatus.CONFLICTING, ConflictStatus.SAME_CONTENT);

        package1.setImportAction(ImportAction.OVERWRITE);
        model.updateSmartImportPackageModel((SmartImportPackageModel) package1);
        assertThat(package1.getSmartImportableUnits()).extracting(SmartImportableUnit::getName)
                .containsExactlyInAnyOrder(IMPORTED_BO_1, IMPORTED_BO_3);
        assertThat(package1.getSmartImportableUnits()).extracting(SmartImportableUnit::getConflictStatus)
                .containsExactlyInAnyOrder(ConflictStatus.CONFLICTING, ConflictStatus.NONE);

    }

    private BusinessObjectModel createImportedBdm() {
        BusinessObjectModel importedBdm = new BusinessObjectModel();
        BusinessObject bo1 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_1))
                .withField(SimpleFieldBuilder.aStringField(IMPORTED_FIELD_1).build())
                .build();
        BusinessObject bo3 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_1, IMPORTED_BO_3))
                .withField(SimpleFieldBuilder.aStringField(IMPORTED_FIELD_3).build())
                .build();
        BusinessObject bo2 = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE_2, IMPORTED_BO_2))
                .withField(SimpleFieldBuilder.aStringField(IMPORTED_FIELD_2).build())
                .build();
        importedBdm.addBusinessObject(bo1);
        importedBdm.addBusinessObject(bo2);
        importedBdm.addBusinessObject(bo3);
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
