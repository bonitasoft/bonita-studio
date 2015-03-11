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

package org.bonitasoft.studio.simulation.tests;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.bonitasoft.simulation.engine.SimulationHelper;
import org.bonitasoft.simulation.model.process.SimActivity;
import org.bonitasoft.simulation.model.process.SimBooleanData;
import org.bonitasoft.simulation.model.process.SimData;
import org.bonitasoft.simulation.model.process.SimLiteralsData;
import org.bonitasoft.simulation.model.process.SimNamedElement;
import org.bonitasoft.simulation.model.process.SimNumberData;
import org.bonitasoft.simulation.model.process.SimProcess;
import org.bonitasoft.simulation.model.process.SimTransition;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.simulation.LoadProfile;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.simulation.commands.SimulationWithMonitorRunner;
import org.bonitasoft.studio.simulation.engine.SimulationExporter;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileFileStore;
import org.bonitasoft.studio.simulation.repository.SimulationLoadProfileRepositoryStore;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class TestSimulationExporter extends TestCase {

    private MainProcess proc;

    private AbstractProcess processe;

    private AbstractProcess dataProcess;

    private SimulationExporter simulationExporter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        final DiagramRepositoryStore drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore pa = drs.getChild("Loan-1.0.proc");
        if (pa == null) {
            final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
            final URL url = TestSimulationExporter.class.getResource("Loan_1_0.bos"); //$NON-NLS-1$

            op.setArchiveFile(FileLocator.toFileURL(url).getFile());
            op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
            op.run(new NullProgressMonitor());
            pa = drs.getChild("Loan-1.0.proc");
        }

        proc = pa.getContent();

        processe = ModelHelper.getAllProcesses(proc).get(0);
        DiagramFileStore pa2 = drs.getChild("TestSimulation-1.0.proc");
        if (pa2 == null) {
            final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
            final URL url = TestSimulationExporter.class.getResource("TestSimulation_1_0.bos"); //$NON-NLS-1$
            op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
            op.setArchiveFile(FileLocator.toFileURL(url).getFile());
            op.run(new NullProgressMonitor());
            pa2 = drs.getChild("TestSimulation-1.0.proc");
        }
        dataProcess = ModelHelper.getAllProcesses(pa2.getContent()).get(0);

        simulationExporter = new SimulationExporter();
    }

    public void testSetupIsOk() throws Exception {
        assertTrue("Tested artifact is corrupt", proc.getName().equals("Loan")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public void testExportProcess() throws Exception {

        final SimProcess simulationProcesse = simulationExporter.createSimulationProcess(processe);
        assertTrue("Missing simultion processes", simulationProcesse != null); //$NON-NLS-1$

        List<EClass> superTypes = new ArrayList<EClass>();
        superTypes.add(ProcessPackage.Literals.FLOW_ELEMENT);
        final List<Element> processElement = new ArrayList<Element>();

        ModelHelper.findAllElementsByNature(processe, processElement, superTypes);

        final List<SimNamedElement> simElements = SimulationHelper.findSimNamedElement(simulationProcesse, SimActivity.class);

        assertEquals("Can't find all SimActivities", processElement.size(), simElements.size());
        final List<Element> processTransition = new ArrayList<Element>();
        superTypes = new ArrayList<EClass>();
        superTypes.add(ProcessPackage.Literals.CONNECTION);
        ModelHelper.findAllElementsByNature(processe, processTransition, superTypes);

        final List<SimNamedElement> simTransitions = SimulationHelper.findSimNamedElement(simulationProcesse, SimTransition.class);

        assertEquals("Can't find all SimTransitions", processTransition.size(), simTransitions.size());

    }

    public void testExportSimulationData() throws Exception {
        boolean booleanDataNoExp = false;
        boolean booleanDataExp = false;
        boolean literalDataNoExp = false;
        boolean numberDataExp = false;
        boolean numberDataNoExp = false;

        for (final SimulationData el : dataProcess.getSimulationData()) {

            final SimulationData data = el;
            if (data.getName().equals("booleanDataNoExp")) {
                final SimData simData = simulationExporter.getData(data);
                assertTrue("bad data export", simData instanceof SimBooleanData);
                booleanDataNoExp = true;
                continue;
            }
            if (data.getName().equals("booleanDataExp")) {
                final SimData simData = simulationExporter.getData(data);
                assertTrue("bad data export", simData instanceof SimBooleanData);
                booleanDataExp = true;
                continue;
            }
            if (data.getName().equals("literalDataNoExp")) {
                final SimData simData = simulationExporter.getData(data);
                assertTrue("bad data export", simData instanceof SimLiteralsData);
                literalDataNoExp = true;
                continue;
            }
            if (data.getName().equals("numberDataExp")) {
                final SimData simData = simulationExporter.getData(data);
                assertTrue("bad data export", simData instanceof SimNumberData);
                numberDataExp = true;
                continue;
            }
            if (data.getName().equals("numberDataNoExp")) {
                final SimData simData = simulationExporter.getData(data);
                assertTrue("bad data export", simData instanceof SimNumberData);
                numberDataNoExp = true;
                continue;
            }
        }
        assertTrue("not all data were checked (test issue)", booleanDataNoExp && booleanDataExp && literalDataNoExp && numberDataExp && numberDataNoExp);
    }

    public void testRunSimulation() throws Exception {
        final File path = new File(System.getProperty("java.io.tmpdir") + File.separatorChar + "testSimu");
        path.delete();
        path.mkdir();

        final SimulationLoadProfileRepositoryStore slprs = RepositoryManager.getInstance().getRepositoryStore(
                SimulationLoadProfileRepositoryStore.class);
        final SimulationLoadProfileFileStore lp = slprs.getChild("LoadProfile_1." + SimulationLoadProfileRepositoryStore.SIMULATION_LOADPROFILE_EXT);

        final SimulationWithMonitorRunner runnable = new SimulationWithMonitorRunner(processe, path.getAbsolutePath(),
                ((LoadProfile) lp.getContent()).getName(),
                86400000L * 30L);
        final IProgressService serive = PlatformUI.getWorkbench().getProgressService();
        serive.run(true, false, runnable);

        assertNotNull("No Report has been generated", runnable.getReportFile());

        final File report = new File(runnable.getReportFile());
        assertTrue("Report doesn't exists", report.exists());
        final FileInputStream is = new FileInputStream(report);
        assertTrue("Report is empty", is.read() != -1);

        is.close();
        path.delete();

    }

}
