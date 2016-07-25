/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.perspectives;

import java.util.Collections;
import java.util.Iterator;

import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.form.custom.commands.CreateFormCommand;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.bonitasoft.studio.util.test.async.TestAsyncThread;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * @author Baptiste Mesta
 *
 */
public class TestAutomaticSwitchPerspective extends TestCase {

    private static final String ProcessPerspective = "org.bonitasoft.studio.perspective.process";
    private static final String FormPerspective = "org.bonitasoft.studio.common.perspective.form";

    public void testSwitchPerspectiveToForms() throws Exception {
        final DiagramFileStore fileStore = new NewDiagramCommandHandler().newDiagram();
        fileStore.open();
        final MainProcess process = fileStore.getContent();

        assertTrue("Wrong perspective when opening the form",new TestAsyncThread(10, 500) {

            @Override
            public boolean isTestGreen() throws Exception {

                final RunnableWithResult<Boolean> runnable = new RunnableWithResult<Boolean>(){

                    private boolean processPerspective = false;

                    @Override
                    public void run() {
                        processPerspective  = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId().equals(ProcessPerspective);
                    }

                    @Override
                    public Boolean getResult() {
                        return processPerspective;
                    }

                    @Override
                    public IStatus getStatus() {
                        return Status.OK_STATUS;
                    }

                    @Override
                    public void setStatus(final IStatus arg0) {
                    }

                };
                Display.getDefault().syncExec(runnable);
                return runnable.getResult();
            }
        }.evaluate());


        PageFlow pageflow = null;
        for (final Iterator<?> iterator = process.eAllContents(); iterator.hasNext();) {
            final EObject type = (EObject) iterator.next();
            if(type instanceof PageFlow){
                pageflow = (PageFlow) type;
                break;
            }
        }
        IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        final TransactionalEditingDomain editingDomain = ((ProcessDiagramEditor)editor).getEditingDomain();
        final CreateFormCommand formCommand = new CreateFormCommand(pageflow,ProcessPackage.Literals.PAGE_FLOW__FORM,"testForm","form to test perspectives", Collections.<WidgetMapping>emptyList(), editingDomain);
        formCommand.execute(new NullProgressMonitor(), null);
        final Form createdForm = (Form) formCommand.getCommandResult().getReturnValue();
        FormsUtils.createFormDiagram(createdForm, editingDomain);
        FormsUtils.openDiagram(createdForm, editingDomain);

        assertTrue("Wrong perspective when opening the form",new TestAsyncThread(10, 500) {

            @Override
            public boolean isTestGreen() throws Exception {

                final RunnableWithResult<Boolean> runnable = new RunnableWithResult<Boolean>(){

                    private boolean editorIsForm = false;

                    @Override
                    public void run() {
                        editorIsForm  = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId().equals(FormPerspective);
                    }

                    @Override
                    public Boolean getResult() {
                        return editorIsForm;
                    }

                    @Override
                    public IStatus getStatus() {
                        return Status.OK_STATUS;
                    }

                    @Override
                    public void setStatus(final IStatus arg0) {
                    }

                };
                Display.getDefault().syncExec(runnable);
                return runnable.getResult();
            }
        }.evaluate());



        editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        ((FormDiagramEditor)editor).doSave(new NullProgressMonitor());
        ((FormDiagramEditor)editor).close(true);
        assertTrue(new TestAsyncThread(10, 500) {
            @Override
            public boolean isTestGreen() throws Exception {
                final RunnableWithResult<Boolean> runnable = new RunnableWithResult<Boolean>(){

                    private boolean editorIsProcess = false;

                    @Override
                    public void run() {
                        editorIsProcess  = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof ProcessDiagramEditor;
                    }

                    @Override
                    public Boolean getResult() {
                        return editorIsProcess;
                    }

                    @Override
                    public IStatus getStatus() {
                        return Status.OK_STATUS;
                    }

                    @Override
                    public void setStatus(final IStatus arg0) {
                    }

                };
                Display.getDefault().syncExec(runnable);
                return runnable.getResult();
            }
        }.evaluate());
        assertTrue("Wrong perspective when closing the form",PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId().equals(ProcessPerspective));


    }

}
