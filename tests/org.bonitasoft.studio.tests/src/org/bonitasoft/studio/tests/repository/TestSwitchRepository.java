/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 */
package org.bonitasoft.studio.tests.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.common.Messages.bonitaName;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.groovy.library.FunctionsRepositoryFactory;
import org.bonitasoft.studio.groovy.library.IFunctionCategory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * @author Baptiste Mesta
 *
 */
public class TestSwitchRepository extends TestCase {

    private String currentWorkspace;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final DiagramRepositoryStore drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        if(drs.getAllProcesses().isEmpty()){
            final DiagramFileStore newDiagram = new NewDiagramCommandHandler().newDiagram();
            newDiagram.open();
        }
    }
    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveAllEditors(false);
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);

        if(!RepositoryManager.getInstance().getCurrentRepository().getName().equals(currentWorkspace)){
            TeamRepositoryUtil.switchToRepository(currentWorkspace, new NullProgressMonitor());
        }
    }

    /**
     * check that repositories are switched and artifacts are different
     * @throws InterruptedException
     * @throws OperationCanceledException
     */
    public void testSwitchRepositories() throws OperationCanceledException, InterruptedException {

        DiagramRepositoryStore drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);

        final List<String> availableProcess = new ArrayList<String>();
        for (final AbstractProcess process : drs.getAllProcesses()) {
            availableProcess.add(process.getName());
        }
        final List<String> jars = new ArrayList<String>();
        DependencyRepositoryStore depRs = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
        for (final IRepositoryFileStore jar : depRs.getChildren()) {
            jars.add(jar.getName());
        }

        currentWorkspace = RepositoryManager.getInstance().getCurrentRepository().getName();
        final char[] newWorkspacechar = currentWorkspace.toCharArray();
        for (int i = 0; i < newWorkspacechar.length; i++) {
            newWorkspacechar[i] = (char) (newWorkspacechar[i] + 1);
        }
        final String newWorkspace = new String(newWorkspacechar);
        TeamRepositoryUtil.switchToRepository(newWorkspace, new NullProgressMonitor());
        assertThat(RepositoryManager.getInstance().getCurrentRepository().getProject().isOpen()).isTrue();
        assertEquals(newWorkspace, RepositoryManager.getInstance().getCurrentRepository().getName());


        drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        assertEquals(0, drs.getAllProcesses().size()) ; //new repo should be empty

        TeamRepositoryUtil.switchToRepository(currentWorkspace, new NullProgressMonitor());
        assertEquals(currentWorkspace, RepositoryManager.getInstance().getCurrentRepository().getName());
        drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        depRs = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
        assertEquals(availableProcess.size(), drs.getAllProcesses().size()) ; //new repo should be empty
        assertEquals(jars.size(),depRs.getChildren().size());

        IFunctionCategory bonitaCategory = null;
        FunctionsRepositoryFactory.getFunctionCatgories(RepositoryManager.getInstance().getCurrentRepository());
        for (final IFunctionCategory category : FunctionsRepositoryFactory
                .getFunctionCatgories(RepositoryManager.getInstance().getCurrentRepository()).getCategories()) {
            if (category.getName().equals(bonitaName)) {
                bonitaCategory = category;
            }
        }
        assertNotNull("Bonita functions category not found after Switch Workspace", bonitaCategory);
        assertNotSame("Bonita function category is empty after Switch Workspace", 0, bonitaCategory.getFunctions().size());
        final String doc = bonitaCategory.getFunctions().get(0).getDocumentation();
        assertTrue("Doc for Bonita category methods not found after Switch Workspace", doc != null && doc.length() > 0);

    }

}
