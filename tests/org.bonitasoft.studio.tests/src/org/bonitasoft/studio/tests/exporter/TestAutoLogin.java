/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.exporter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Map;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Florine Boudin
 */
public class TestAutoLogin {

    private static final String CONF_NAME = "TestConfiguration";
    private DiagramFileStore diagramFileStore;

    @Before
    public void setUp() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveAllEditors(false);
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
        final NewDiagramCommandHandler newDiagram = new NewDiagramCommandHandler();
        diagramFileStore = newDiagram.newDiagram();
        MainProcess mainProcess = diagramFileStore.getContent();
        AbstractProcess pool = (AbstractProcess) mainProcess.getElements().get(0);
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagramFileStore.getEMFResource());
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                final Configuration conf = ConfigurationFactory.eINSTANCE.createConfiguration();
                conf.setName(CONF_NAME);
                pool.getConfigurations().add(conf);

                conf.setAnonymousPassword("bpm");
                conf.setAnonymousUserName("anonymous");

                pool.setAutoLogin(true);
                pool.getFormMapping().setType(FormMappingType.LEGACY);
                for (final Task t : ModelHelper.getAllElementOfTypeIn(pool, Task.class)) {
                    t.getFormMapping().setType(FormMappingType.LEGACY);
                }
            }
        });

        diagramFileStore.save(mainProcess);
    }

    @After
    public void tearDown() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
    }

    @Test
    public void testGenerateSecurityConfigFile() throws Exception {
        final String securityFileName = "security-config.properties";
        final String securityFilePath = "resources/forms/";
        Configuration conf = null;
        Pool pool = (Pool) diagramFileStore.getContent().getElements().get(0);
        for (final Configuration tmpConf : pool.getConfigurations()) {
            if (tmpConf.getName().equals(CONF_NAME)) {
                conf = tmpConf;
            }
        }

        assertNotNull("Configuration should not be null", conf);

        if (conf != null) {

            final BusinessArchive bar = BarExporter.getInstance().createBusinessArchive(pool, conf.getName(),
                    Collections.<EObject> emptySet());
            final Map<String, byte[]> map = bar.getResources();
            boolean hasSecurityFile = false;
            for (final String s : map.keySet()) {
                if (s.equals(securityFilePath + securityFileName)) {
                    hasSecurityFile = true;
                    break;
                }
            }

            assertTrue("The " + securityFileName + " file was not generated in the path " + securityFilePath,
                    hasSecurityFile);
        }

    }

}
