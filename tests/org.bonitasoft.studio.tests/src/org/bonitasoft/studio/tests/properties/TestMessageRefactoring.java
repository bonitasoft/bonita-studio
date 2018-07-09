/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.properties;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.Objects;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.properties.sections.message.commands.UpdateMessageCommand;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMessageRefactoring {

    private static final String DIAGRAM_1_NAME = "Diagram 1";
    private static final String DIAGRAM_2_NAME = "Diagram 2";
    private static final String DIAGRAMS_VERSION = "1.0";

    private static final String POOL1 = "PoolTestRefactoring1";
    private static final String POOL2 = "PoolTestRefactoring2";
    private static final String POOL3 = "PoolTestRefactoring3";

    private static final String ORIGINAL_MESSAGE_NAME = "MessageToPool2";
    private static final String EDITED_MESSAGE_NAME = "MessageToPool3";

    private static final String CATCH_MESSAGE_1_NAME = "CatchMessage1";
    private static final String CATCH_MESSAGE_2_NAME = "CatchMessage2";

    private static RepositoryAccessor repositoryAccessor;

    private MainProcess diagram1MainProcess;
    private MainProcess diagram2MainProcess;

    @BeforeClass
    public static void init() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @Test
    public void should_refactor_messages() throws Exception {
        importDiagrams();
        openDiagrams();

        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram1MainProcess);

        AbstractProcess pool1 = getPool(diagram1MainProcess, POOL1);
        AbstractProcess pool2 = getPool(diagram1MainProcess, POOL2);
        AbstractProcess pool3 = getPool(diagram2MainProcess, POOL3);

        ThrowMessageEvent throwMessage = getFirstThrowMessageEvent(pool1);
        AbstractCatchMessageEvent catchMessage1 = getFirstCatchMessageEvent(pool2);
        AbstractCatchMessageEvent catchMessage2 = getFirstCatchMessageEvent(pool3);

        assertThat(throwMessage.getEvents()).hasSize(1);
        Message message = throwMessage.getEvents().get(0);

        assertOriginalState(catchMessage1, catchMessage2, message);

        refactorMessageName(editingDomain, throwMessage, message);

        assertThat(message.getName()).isEqualTo(EDITED_MESSAGE_NAME);
        assertThat(catchMessage1.getEvent()).isEqualTo(EDITED_MESSAGE_NAME);

        editingDomain.getCommandStack().undo();

        assertOriginalState(catchMessage1, catchMessage2, message);

        refactorMessageTarget(editingDomain, throwMessage, message);

        assertThat(message.getName()).isEqualTo(EDITED_MESSAGE_NAME);
        assertThat(message.getTargetProcessExpression().getContent()).isEqualTo(POOL3);
        assertThat(message.getTargetElementExpression().getContent()).isEqualTo(CATCH_MESSAGE_2_NAME);
        assertThat(catchMessage1.getEvent()).isNullOrEmpty();

        undoRefactorMessageTargetFromDistantDiagramToLocalDiagram(editingDomain, throwMessage, message);

        assertOriginalState(catchMessage1, catchMessage2, message);
    }

    private void undoRefactorMessageTargetFromDistantDiagramToLocalDiagram(TransactionalEditingDomain editingDomain,
            ThrowMessageEvent throwMessage,
            Message message) {
        EMFModelUpdater<Message> emfModelUpdater = new EMFModelUpdater<>();
        Message workingCopy = emfModelUpdater.from(message).getWorkingCopy();
        workingCopy.setName(ORIGINAL_MESSAGE_NAME);
        workingCopy.getTargetProcessExpression().setName(POOL2);
        workingCopy.getTargetProcessExpression().setContent(POOL2);
        workingCopy.getTargetElementExpression().setName(CATCH_MESSAGE_1_NAME);
        workingCopy.getTargetElementExpression().setContent(CATCH_MESSAGE_1_NAME);

        editingDomain.getCommandStack()
                .execute(new UpdateMessageCommand(editingDomain, throwMessage, message, workingCopy, emfModelUpdater));
    }

    private void refactorMessageTarget(TransactionalEditingDomain editingDomain, ThrowMessageEvent throwMessage,
            Message message) {
        EMFModelUpdater<Message> emfModelUpdater = new EMFModelUpdater<>();
        Message workingCopy = emfModelUpdater.from(message).getWorkingCopy();
        workingCopy.setName(EDITED_MESSAGE_NAME);
        workingCopy.getTargetProcessExpression().setName(POOL3);
        workingCopy.getTargetProcessExpression().setContent(POOL3);
        workingCopy.getTargetElementExpression().setName(CATCH_MESSAGE_2_NAME);
        workingCopy.getTargetElementExpression().setContent(CATCH_MESSAGE_2_NAME);

        editingDomain.getCommandStack()
                .execute(new UpdateMessageCommand(editingDomain, throwMessage, message, workingCopy, emfModelUpdater));
    }

    private void refactorMessageName(TransactionalEditingDomain editingDomain, ThrowMessageEvent throwMessage,
            Message message) {
        EMFModelUpdater<Message> emfModelUpdater = new EMFModelUpdater<>();
        Message workingCopy = emfModelUpdater.from(message).getWorkingCopy();
        workingCopy.setName(EDITED_MESSAGE_NAME);

        editingDomain.getCommandStack()
                .execute(new UpdateMessageCommand(editingDomain, throwMessage, message, workingCopy, emfModelUpdater));
    }

    private void assertOriginalState(AbstractCatchMessageEvent catchMessage1, AbstractCatchMessageEvent catchMessage2,
            Message message) {
        assertThat(message.getName()).isEqualTo(ORIGINAL_MESSAGE_NAME);
        assertThat(message.getTargetProcessExpression().getContent()).isEqualTo(POOL2);
        assertThat(message.getTargetProcessExpression().getName()).isEqualTo(POOL2);
        assertThat(message.getTargetElementExpression().getContent()).isEqualTo(CATCH_MESSAGE_1_NAME);
        assertThat(message.getTargetElementExpression().getName()).isEqualTo(CATCH_MESSAGE_1_NAME);

        assertThat(catchMessage1.getEvent()).isEqualTo(ORIGINAL_MESSAGE_NAME);
        assertThat(catchMessage2.getEvent()).isNullOrEmpty();
    }

    private ThrowMessageEvent getFirstThrowMessageEvent(AbstractProcess pool1) {
        return ModelHelper
                .getAllItemsOfType(pool1, ProcessPackage.Literals.THROW_MESSAGE_EVENT)
                .stream()
                .map(ThrowMessageEvent.class::cast)
                .findFirst()
                .get();
    }

    private AbstractCatchMessageEvent getFirstCatchMessageEvent(AbstractProcess pool) {
        return ModelHelper
                .getAllItemsOfType(pool, ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT)
                .stream()
                .map(AbstractCatchMessageEvent.class::cast)
                .findFirst()
                .get();
    }

    private AbstractProcess getPool(MainProcess mainProcess, String poolName) {
        return ModelHelper.getAllProcesses(mainProcess).stream()
                .filter(pool -> Objects.equals(pool.getName(), poolName))
                .findFirst()
                .get();
    }

    private void openDiagrams() {
        DiagramRepositoryStore diagramRepositoryStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore diagram1 = diagramRepositoryStore.getDiagram(DIAGRAM_1_NAME, DIAGRAMS_VERSION);
        DiagramFileStore diagram2 = diagramRepositoryStore.getDiagram(DIAGRAM_2_NAME, DIAGRAMS_VERSION);
        diagram1.open();
        diagram1MainProcess = diagram1.getContent();
        diagram2MainProcess = diagram2.getContent();
    }

    private void importDiagrams() throws Exception {
        ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        URL bosArchiveURL = FileLocator
                .toFileURL(TestMessageRefactoring.class.getResource("TestRefactoringMessage.bos"));
        op.setArchiveFile(FileLocator.toFileURL(bosArchiveURL).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
    }

}
