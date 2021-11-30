package org.bonitasoft.studio.businessobject.ui.wizard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.model.OverwriteImportBdmModel;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.businessObject.BusinessObjectBuilder;
import org.bonitasoft.studio.model.businessObject.FieldBuilder.SimpleFieldBuilder;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SmartImportBdmPageTest {

    private static final String IMPORTED_PACKAGE = "com.bonitasoft";
    private static final String IMPORTED_BO = "BusinessObject";
    private static final String IMPORTED_FIELD = "field";

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

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
    public void should_perform_smart_import_when_there_is_no_existing_bdm() throws Exception {
        SmartImportBdmPage page = createPage(null);
        page.parseInput();
        assertThat(page.importBdmModelObservable).isNotNull();
        assertThat(page.importBdmModelObservable).isNotInstanceOf(OverwriteImportBdmModel.class);
        assertThat(page.canPerformSmartImport()).isTrue();
    }

    @Test
    public void should_perform_smart_import_when_models_are_not_conflicting() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo = BusinessObjectBuilder.aBO("org.bonitasoft.OtherBusinessObject")
                .withField(SimpleFieldBuilder.aStringField("field").build())
                .build();
        existingBdm.addBusinessObject(bo);
        SmartImportBdmPage page = createPage(existingBdm);
        page.parseInput();
        assertThat(page.importBdmModelObservable).isNotNull();
        assertThat(page.importBdmModelObservable).isNotInstanceOf(OverwriteImportBdmModel.class);
        assertThat(page.canPerformSmartImport()).isTrue();
    }

    @Test
    public void should_perform_smart_import_when_models_are_mono_package_conflicting() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE, IMPORTED_BO))
                .withField(SimpleFieldBuilder.aStringField("otherField").build())
                .build();
        existingBdm.addBusinessObject(bo);

        SmartImportBdmPage page = createPage(existingBdm);
        page.parseInput();
        assertThat(page.importBdmModelObservable).isNotNull();
        assertThat(page.importBdmModelObservable).isNotInstanceOf(OverwriteImportBdmModel.class);
        assertThat(page.canPerformSmartImport()).isTrue();
    }

    @Test
    public void should_not_perform_smart_import_when_models_are_multi_package_conflicting() throws Exception {
        BusinessObjectModel existingBdm = new BusinessObjectModel();
        BusinessObject bo = BusinessObjectBuilder.aBO(String.format("%s.%s", "org.bonitasoft", IMPORTED_BO))
                .withField(SimpleFieldBuilder.aStringField("otherField").build())
                .build();
        existingBdm.addBusinessObject(bo);

        SmartImportBdmPage page = createPage(existingBdm);
        page.parseInput();
        assertThat(page.importBdmModelObservable.getValue()).isNotNull();
        assertThat(page.importBdmModelObservable.getValue()).isInstanceOf(OverwriteImportBdmModel.class);
        assertThat(page.canPerformSmartImport()).isFalse();
    }

    private SmartImportBdmPage createPage(BusinessObjectModel existingBdm) throws Exception, IOException {
        RepositoryAccessor repositoryAccessor = createRepositoryAccessor(existingBdm);
        SmartImportBdmPage page = spy(new SmartImportBdmPage(repositoryAccessor));
        doReturn(importedFile).when(page).retrieveFileToImport();
        doNothing().when(page).updateInput(any());
        page.converter = converter;

        page.setFilePath("path");
        return page;
    }

    private RepositoryAccessor createRepositoryAccessor(BusinessObjectModel existingBdm) throws ReadFileStoreException {
        BusinessObjectModelFileStore fileStore = mock(BusinessObjectModelFileStore.class);
        when(fileStore.getContent()).thenReturn(existingBdm);
        when(fileStore.getAdapter(BusinessObjectModelFileStore.class)).thenReturn(fileStore);
        BusinessObjectModelRepositoryStore repositoryStore = mock(BusinessObjectModelRepositoryStore.class);
        when(repositoryStore.getChild("bom.xml", true)).thenReturn(existingBdm != null ? fileStore : null);
        RepositoryAccessor repositoryAccessor = mock(RepositoryAccessor.class);
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(repositoryStore);
        return repositoryAccessor;
    }

    private BusinessObjectModel createImportedBdm() {
        BusinessObjectModel importedBdm = new BusinessObjectModel();
        BusinessObject bo = BusinessObjectBuilder.aBO(String.format("%s.%s", IMPORTED_PACKAGE, IMPORTED_BO))
                .withField(SimpleFieldBuilder.aStringField(IMPORTED_FIELD).build())
                .build();
        importedBdm.addBusinessObject(bo);
        return importedBdm;
    }

    private File createBdmFile(BusinessObjectModel bdm) throws Exception {
        File file = new File("bom.xml");
        file.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(converter.marshall(bdm));
        }
        return file;
    }

}
