/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.validation.TokenDispatcher;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Florine Boudin
 */
public class TestTokenDispatcher {

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void init() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }

    @Test
    public void testReturnTokenOfNonInterruptedBoudaryTimerEvent() throws Exception {

        /* Import a base process for the test */
        final ProcessDiagramEditor processEditor = importBos("BS-10043_InfiniteLoopValidation-1.0.bos");
        final MainProcess mainProcess = (MainProcess) processEditor.getDiagramEditPart().resolveSemanticElement();

        final Pool parentProcess = (Pool) mainProcess.getElements().get(0);
        TransactionalEditingDomain domain = GMFEditingDomainFactory.getInstance()
                .getEditingDomain(parentProcess.eResource().getResourceSet());
        if (domain == null) {
            domain = TransactionUtil.getEditingDomain(parentProcess);
        }
        if (domain == null) {
            domain = GMFEditingDomainFactory.getInstance().createEditingDomain();
        }

        final TokenDispatcher tokenD = new TokenDispatcher();
        NonInterruptingBoundaryTimerEvent boundaryEvent = null;
        SequenceFlow nonInterruptedOutgoingconnection = null;
        SequenceFlow taskOutgoingconnection = null;
        Task humanTask = null;

        for (final Connection transition : parentProcess.getConnections()) {
            if (transition.getSource() instanceof NonInterruptingBoundaryTimerEvent) {
                boundaryEvent = (NonInterruptingBoundaryTimerEvent) transition.getSource();
                nonInterruptedOutgoingconnection = (SequenceFlow) transition;
            } else if (transition.getSource() instanceof Task) {
                humanTask = (Task) transition.getSource();
                taskOutgoingconnection = (SequenceFlow) transition;
            }
        }

        assertNotNull(nonInterruptedOutgoingconnection);
        assertNotNull(boundaryEvent);
        assertNotNull(humanTask);

        // Test the token returned is the one of the source transition
        tokenD.setSequenceFlow(nonInterruptedOutgoingconnection);
        final String nonInterruptedToken = tokenD.getToken();
        assertEquals("The token return should be the one of the NonInterruptingBoundaryTimerEvent",
                ModelHelper.getEObjectID(nonInterruptedOutgoingconnection.getSource()),
                nonInterruptedToken);

        // Test Non Interrupted token is different from the Task token
        tokenD.setSequenceFlow(taskOutgoingconnection);
        assertTrue("Non Interrupted token and task token must be different", !nonInterruptedToken.equals(tokenD.getToken()));
    }

    private ProcessDiagramEditor importBos(final String processResourceName)
            throws IOException, InvocationTargetException, InterruptedException {
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL = FileLocator.toFileURL(TestTokenDispatcher.class.getResource(processResourceName));
        op.setArchiveFile(FileLocator.toFileURL(fileURL).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(new NullProgressMonitor());
        ProcessDiagramEditor processEditor = null;
        for (final IRepositoryFileStore f : op.getFileStoresToOpen()) {
            final IWorkbenchPart iWorkbenchPart = f.open();
            if (iWorkbenchPart instanceof ProcessDiagramEditor) {
                processEditor = (ProcessDiagramEditor) iWorkbenchPart;

            }
        }
        return processEditor;
    }

}
