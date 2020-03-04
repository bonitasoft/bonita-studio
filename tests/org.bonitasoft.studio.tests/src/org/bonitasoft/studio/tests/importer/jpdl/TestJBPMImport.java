/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.importer.jpdl;

import java.io.File;
import java.net.URL;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.ConfigurationSynchronizer;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ImporterRegistry;
import org.bonitasoft.studio.importer.jpdl.JBPM3ImportFactory;
import org.bonitasoft.studio.importer.jpdl.JBPM3ToProc;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.XORGateway;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestJBPMImport extends TestCase {

    public org.eclipse.emf.common.util.URI toEMFURI(File file) {
        org.eclipse.emf.common.util.URI res = org.eclipse.emf.common.util.URI.createFileURI(file.getAbsolutePath());
        return res;
    }

    public void testImportIsAvailable() {
        for (ImporterFactory factory : ImporterRegistry.getInstance().getAllAvailableImports()) {
            if (factory instanceof JBPM3ImportFactory) {
                return;
            }
        }
        fail("Could not find JBPM3 Import Factory");
    }

    public void testSimpleProcess() throws Exception {
        URL srcUrl = getClass().getResource("testSimpleProcess1/processdefinition.xml");
        File destFile = new JBPM3ToProc().createDiagram(srcUrl, new NullProgressMonitor());
        destFile.deleteOnExit();
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess) resource.getContents().get(0);

        assertNbItems(mainProcess, 1, 1, 1, 0, 2, 2, 1, 9, 2);
        Activity mailNode = findActivity(mainProcess, "mail-node1");
        assertEquals("No mail connector found", "email", mailNode.getConnectors().get(0).getDefinitionId());

        final AbstractProcess process = (AbstractProcess) mainProcess.getElements().get(0);
        final ProcessConfigurationRepositoryStore store = (ProcessConfigurationRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        final String fileName = ModelHelper.getEObjectID(process) + ".conf";
        ProcessConfigurationFileStore fileStore = store.getChild(fileName, true);
        if (fileStore == null) {
            fileStore = store.createRepositoryFileStore(fileName);
            fileStore.save(ConfigurationFactory.eINSTANCE.createConfiguration());
        }
        final Configuration configuration = fileStore.getContent();
        final ConfigurationSynchronizer configurationSynchronizer = new ConfigurationSynchronizer(process, configuration);
        configurationSynchronizer.synchronize();
        assertFalse("Configuration should not be valid", configurationSynchronizer.isConfigurationValid());
        resource.unload();

    }

    public void testWebSale() throws Exception {
        URL srcUrl = getClass().getResource("websale/processdefinition.xml");
        File destFile = new JBPM3ToProc().createDiagram(srcUrl, new NullProgressMonitor());
        destFile.deleteOnExit();
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(toEMFURI(destFile), true);
        MainProcess mainProcess = (MainProcess) resource.getContents().get(0);

        assertNbItems(mainProcess, 1, 1, 3, 0, 2, 2, 0, 10, 0);
        resource.unload();
    }

    /**
     * @param string
     * @return
     */
    private Activity findActivity(MainProcess process, String string) {
        Pool pool = (Pool) process.getElements().get(0);
        for (Element child : pool.getElements()) {
            if (child instanceof Activity &&
                    (child.getName().equals(string) || child.getName().equals(string))) {
                return (Activity) child;
            }
        }
        return null;
    }

    /**
     * @param mainProcess
     * @param nbStartEvent
     * @param nbEndEvent
     * @param nbTasks
     * @param nbSubprocess
     * @param nbAutomatedActivity
     * @param nbAndGateways
     * @param nbXorGateways
     * @param nbFlows
     * @param nbConditions
     */
    private void assertNbItems(MainProcess mainProcess, int nbStartEvent, int nbEndEvent, int nbTasks, int nbSubprocess,
            int nbAutomatedActivity, int nbAndGateways, int nbXorGateways, int nbFlows, int nbConditions) {
        Pool pool = (Pool) mainProcess.getElements().get(0);
        for (Element item : pool.getElements()) {
            if (item instanceof StartEvent) {
                nbStartEvent--;
            } else if (item instanceof EndEvent) {
                nbEndEvent--;
            } else if (item instanceof Task) {
                nbTasks--;
            } else if (item instanceof CallActivity) {
                nbSubprocess--;
            } else if (item instanceof Activity) {
                nbAutomatedActivity--;
            } else if (item instanceof ANDGateway) {
                nbAndGateways--;
            } else if (item instanceof XORGateway) {
                nbXorGateways--;
            }
        }
        for (Connection connection : pool.getConnections()) {
            if (connection instanceof SequenceFlow) {
                nbFlows--;
                SequenceFlow flow = (SequenceFlow) connection;
                if (flow.getCondition() != null && flow.getCondition().getContent() != null
                        && flow.getCondition().getContent().trim().length() > 0) {
                    nbConditions--;
                }
            }
        }
        assertEquals("Not same number of start event", 0, nbStartEvent);
        assertEquals("Not same number of end event", 0, nbEndEvent);
        assertEquals("Not same number of tasks", 0, nbTasks);
        assertEquals("Not same number of subprocesses", 0, nbSubprocess);
        assertEquals("Not same number of activities", 0, nbAutomatedActivity);
        assertEquals("Not same number of transitions", 0, nbFlows);
        assertEquals("Not same number of AND", 0, nbAndGateways);
        assertEquals("Not same number of XOR", 0, nbXorGateways);
        assertEquals("Not same number of conditions", 0, nbConditions);
    }
}
