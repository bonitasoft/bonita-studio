package org.bonitasoft.studio.businessobject.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.junit.Test;

public class SmartImportPackageModelTest {

    @Test
    public void should_return_bo_to_import() {
        SmartImportPackageModel packageModel = new SmartImportPackageModel(null, "com.bonitasoft");

        BusinessObject boConflicting = new BusinessObjectBuilder("com.bonitasoft.Conflicting").build();
        SmartImportBusinessObjectModel modelConflicting = new SmartImportBusinessObjectModel(null, packageModel,
                boConflicting);
        modelConflicting.setConflictStatus(ConflictStatus.CONFLICTING);

        BusinessObject boNone = new BusinessObjectBuilder("com.bonitasoft.NoneOverwrite").build();
        SmartImportBusinessObjectModel modelNone = new SmartImportBusinessObjectModel(null, packageModel, boNone);
        modelNone.setConflictStatus(ConflictStatus.NONE);

        BusinessObject boSameContent = new BusinessObjectBuilder("com.bonitasoft.SameContentOverwrite").build();
        SmartImportBusinessObjectModel modelSameContent = new SmartImportBusinessObjectModel(null, packageModel,
                boSameContent);
        modelSameContent.setConflictStatus(ConflictStatus.SAME_CONTENT);

        packageModel.getSmartImportableUnits().addAll(Arrays.asList(modelConflicting, modelNone, modelSameContent));
        assertThat(packageModel.getBusinessObjectsToImport()).containsExactlyInAnyOrder(boConflicting, boNone,
                boSameContent);
    }

}
