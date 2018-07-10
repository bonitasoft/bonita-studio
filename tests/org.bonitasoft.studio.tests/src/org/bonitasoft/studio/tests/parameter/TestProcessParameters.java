/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.parameters.action.ExportParametersAction;
import org.bonitasoft.studio.parameters.action.ImportParametersAction;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class TestProcessParameters {

    private static final String CONF_NAME = "TestConfiguration";
    private static AbstractProcess pool;

    @Before
    public void setUp() throws Exception {
        if (pool == null) {
            final DiagramFileStore diagramFileStore = new NewDiagramCommandHandler().newDiagram();
            diagramFileStore.open();
            pool = (AbstractProcess) EcoreUtil.copy(diagramFileStore.getContent()).getElements().get(0);
            final Configuration conf = ConfigurationFactory.eINSTANCE.createConfiguration();

            conf.setName(CONF_NAME);
            pool.getConfigurations().add(conf);

            final Parameter p1 = ParameterFactory.eINSTANCE.createParameter();
            p1.setName("dbUrl");
            p1.setTypeClassname(String.class.getName());
            pool.getParameters().add(p1);

            final Parameter p2 = ParameterFactory.eINSTANCE.createParameter();
            p2.setName("password");
            p2.setTypeClassname(String.class.getName());
            pool.getParameters().add(p2);

            final Parameter p3 = ParameterFactory.eINSTANCE.createParameter();
            p3.setName("port");
            p3.setTypeClassname(Integer.class.getName());
            pool.getParameters().add(p3);

            pool.getFormMapping().setType(FormMappingType.NONE);
            for (final Task t : ModelHelper.getAllElementOfTypeIn(pool, Task.class)) {
                t.getFormMapping().setType(FormMappingType.NONE);
            }

            new ConfigurationSynchronizer(pool, pool.getConfigurations().get(0)).synchronize();
        }
    }

    @After
    public void tearDown() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
    }

    @Test
    public void testNewProcessParameterInitialized() throws Exception {
        Assert.assertNotNull(pool);
        Assert.assertTrue("Configuration should be available", pool.getConfigurations().size() == 1);
    }

    @Test
    public void testImportParameters() throws Exception {
        Assert.assertNotNull(pool);
        Assert.assertEquals("Parameter import failed", 3, pool.getParameters().size());

        new ConfigurationSynchronizer(pool, pool.getConfigurations().get(0)).synchronize();
        Assert.assertEquals("Parameter import failed", 3, pool.getConfigurations().get(0).getParameters().size());

        importParamters();

        boolean allValueImported = true;
        for (final Parameter p : pool.getConfigurations().get(0).getParameters()) {
            if (p.getValue() == null) {
                allValueImported = false;
            }
        }
        Assert.assertTrue("Parameter import failed", allValueImported);
    }

    protected void importParamters() throws IOException {
        ImportParametersAction action;
        URL fileUrl;
        File fileToImport;
        action = new ImportParametersAction();
        fileUrl = TestProcessParameters.class.getResource("Parameters.properties");
        fileToImport = new File(FileLocator.toFileURL(fileUrl).getFile());
        action.setProcess(pool);
        action.setConfiguration(pool.getConfigurations().get(0));
        action.setFilePath(fileToImport.getAbsolutePath());
        action.run();
    }

    @Test
    public void testExportProperties() throws Exception {
        Assert.assertNotNull(pool);
        importParamters();
        final File exportedFile = new File(ProjectUtil.getBonitaStudioWorkFolder(), "parameters.properties");
        exportedFile.delete();
        final ExportParametersAction action = new ExportParametersAction();
        action.setProcess(pool);
        action.setConfiguration(pool.getConfigurations().get(0));
        action.setTargetPath(ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath());
        action.run();

        Assert.assertTrue("Exported file doesn't exists", exportedFile.exists() && exportedFile.length() > 0);

        final Properties p = new Properties();
        p.load(new FileInputStream(exportedFile));
        Assert.assertEquals("Exported file not xpected parameters count", 3, p.size());
    }

    @Test
    public void testDeployProcessWithParameters() throws Exception {
        Assert.assertNotNull(pool);
        importParamters();
        final BusinessArchive bar = BarExporter.getInstance().createBusinessArchive(pool,
                pool.getConfigurations().get(0).getName());
        Assert.assertEquals("Missing parameter in bar", 3, bar.getParameters().size());
    }

}
