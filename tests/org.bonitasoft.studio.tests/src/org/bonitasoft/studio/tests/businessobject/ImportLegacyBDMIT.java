/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.businessobject;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ImportLegacyBDMIT {

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> defStore;
    private DependencyRepositoryStore depStore;
    private RepositoryAccessor repositoryAccessor;

    @After
    public void deleteFileStore() throws Exception {
        defStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true).delete();
    }

    @Before
    public void setUp() throws Exception {
        BOSEngineManager.getInstance().start();
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        depStore = repositoryAccessor.getRepositoryStore(DependencyRepositoryStore.class);
        defStore = repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    @Test
    public void should_import_a_legacy_bdm_and_convert_it_to_xml_file() throws Exception {
        final ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        operation.setArchiveFile(
                new File(FileLocator.toFileURL(ImportLegacyBDMIT.class.getResource("/legacyBDM.bos")).getFile())
                        .getAbsolutePath());
        operation.run(AbstractRepository.NULL_PROGRESS_MONITOR);

        StatusAssert.assertThat(operation.getStatus()).hasSeverity(IStatus.INFO);
        assertThat(defStore.getResource().getFile(BusinessObjectModelFileStore.ZIP_FILENAME).getLocation().toFile().exists())
                .isFalse();
        assertThat(defStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true)).isNotNull();
        final BusinessObjectModel model = defStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true).getContent();
        assertThat(model).isNotNull();
        assertThat(model.getBusinessObjectsClassNames()).contains("com.bonita.lr.model.VacationRequest");
    }

    @Test
    public void should_import_a_bdm_without_namespace_and_add_it() throws Exception {
        ImportBosArchiveOperation operation = new ImportBosArchiveOperation(repositoryAccessor);
        operation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        operation.setArchiveFile(
                new File(FileLocator.toFileURL(ImportLegacyBDMIT.class.getResource("/bdmWithoutNamespace.bos")).getFile())
                        .getAbsolutePath());
        operation.run(AbstractRepository.NULL_PROGRESS_MONITOR);

        StatusAssert.assertThat(operation.getStatus()).hasSeverity(IStatus.INFO);

        IFile iFile = defStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true).getResource();
        String bomFileContent = IOUtils.toString(iFile.getContents(), Charset.defaultCharset());
        assertThat(bomFileContent).contains("xmlns=\"http://documentation.bonitasoft.com/bdm-xml-schema/1.0\"");
    }

}
