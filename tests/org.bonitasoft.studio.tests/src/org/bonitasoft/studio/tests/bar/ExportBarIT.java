/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.bar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.engine.bpm.bar.BusinessArchiveFactory.readBusinessArchive;
import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.eclipse.core.runtime.FileLocator.toFileURL;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingModel;
import org.bonitasoft.engine.form.FormMappingTarget;
import org.bonitasoft.engine.form.FormMappingType;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.ExportBarOperation;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @author Romain Bioteau
 */
public class ExportBarIT {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    private BOSEngineManager engineManager;

    private APISession session;

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void init() throws Exception {
        engineManager = BOSEngineManager.getInstance();
        session = engineManager.loginDefaultTenant(new NullProgressMonitor());
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }

    @After
    public void closeSession() throws Exception {
        if (session != null) {
            engineManager.logoutDefaultTenant(session);
        }
    }

    @Test
    public void should_import_a_process_with_new_form_mapping_export_it_as_a_bar_file() throws Exception {
        File bosToImportFile = Paths
                .get(toFileURL(ExportBarIT.class.getResource("/DiagramWithNewFormMapping-1.0.bos")).toURI())
                .toFile();

        ImportBosArchiveOperation importBosArchiveOperation = new ImportBosArchiveOperation(repositoryAccessor)
                .disableValidation();
        importBosArchiveOperation.setArchiveFile(bosToImportFile.getAbsolutePath());
        importBosArchiveOperation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        Display.getDefault().syncExec(() -> {
            try {
                importBosArchiveOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException("Error while importing bos archive");
            }
        });

        assertThat(importBosArchiveOperation.getStatus()).isOK();
        assertThat(importBosArchiveOperation.getFileStoresToOpen()).extracting("name")
                .containsOnly("DiagramWithNewFormMapping-1.0.proc");

        final File targetBarFolder = tmpFolder.newFolder("targetBarFolder");
        final ExportBarOperation exportBarOperation = new ExportBarOperation();
        final DiagramFileStore diagram = (DiagramFileStore) importBosArchiveOperation.getFileStoresToOpen().get(0);
        AbstractProcess process = diagram.getProcesses(true).get(0);
        Pool pool = ModelHelper.getParentPool(process);

        var commonResourcesToCopy = new File(FileLocator.toFileURL(ExportBarIT.class.getResource("/_common")).getFile());
        var processResourcesToCopy = new File(
                FileLocator.toFileURL(ExportBarIT.class.getResource(String.format("/%s", pool.getName()))).getFile());
        var processVersionResourcesToCopy = new File(FileLocator
                .toFileURL(ExportBarIT.class.getResource(String.format("/%s-%s", pool.getName(), pool.getVersion())))
                .getFile());
        var resourcesFolder = repositoryAccessor.getCurrentRepository().getProject().getFolder("src/main/resources/_common");
        PlatformUtil.copyResource(resourcesFolder.getLocation().toFile(), commonResourcesToCopy,
                AbstractRepository.NULL_PROGRESS_MONITOR);
        PlatformUtil.copyResource(resourcesFolder.getLocation().toFile(), processResourcesToCopy,
                AbstractRepository.NULL_PROGRESS_MONITOR);
        PlatformUtil.copyResource(resourcesFolder.getLocation().toFile(), processVersionResourcesToCopy,
                AbstractRepository.NULL_PROGRESS_MONITOR);
        repositoryAccessor.getCurrentRepository().getProject().refreshLocal(IResource.DEPTH_INFINITE,
                AbstractRepository.NULL_PROGRESS_MONITOR);

        exportBarOperation.addProcessToDeploy(process);
        exportBarOperation.setTargetFolder(targetBarFolder.getAbsolutePath());
        exportBarOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);

        //Then
        assertThat(exportBarOperation.getStatus()).isOK();
        assertThat(exportBarOperation.getGeneratedBars()).hasSize(1);

        //Given
        final File generatedBarFile = exportBarOperation.getGeneratedBars().get(0);
        BusinessArchive businessArchive = readBusinessArchive(generatedBarFile);
        assertThat(businessArchive).isNotNull();

        //Expect
        final FormMappingModel formMappingModel = businessArchive.getFormMappingModel();
        assertThat(formMappingModel.getFormMappings()).extracting("form", "type", "taskname", "target").contains(
                tuple("custompage_instantiationForm", FormMappingType.PROCESS_START, null, FormMappingTarget.INTERNAL),
                tuple("custompage_overviewPage", FormMappingType.PROCESS_OVERVIEW, null, FormMappingTarget.INTERNAL),
                tuple("custompage_stepForm", FormMappingType.TASK, "Step1", FormMappingTarget.INTERNAL),
                tuple("http://www.google.com", FormMappingType.TASK, "Step2", FormMappingTarget.URL));
        assertThat(businessArchive.getResource("resources/customPages/custompage_instantiationForm.zip")).isNotEmpty();
        assertThat(businessArchive.getResource("resources/customPages/custompage_overviewPage.zip")).isNotEmpty();
        assertThat(businessArchive.getResource("resources/customPages/custompage_stepForm.zip")).isNotEmpty();
        assertThat(new String(businessArchive.getResource("resources/resource.txt"))).isEqualTo("processVersion");
        assertThat(new String(businessArchive.getResource("resources/resource2.txt"))).isEqualTo("Salut");
        assertThat(new String(businessArchive.getResource("resources/resource3.txt"))).isEqualTo("Hello");
    }
}
