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
package org.bonitasoft.studio.tests;

import java.util.Collections;

import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.form.custom.commands.CreateFormCommand;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 *
 */
public class TestBugSave extends TestCase {

    public void testCrashingSave() throws Exception {
        new NewDiagramCommandHandler().newDiagram().open();
        ProcessDiagramEditor processEditor = (ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        MainProcess mainProcess = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
        final CreateFormCommand formCommand = new CreateFormCommand(mainProcess,ProcessPackage.Literals.PAGE_FLOW__FORM,"form", "", Collections.<WidgetMapping>emptyList(), processEditor.getEditingDomain());
        formCommand.execute(new NullProgressMonitor(), null);
        final Form createdForm = (Form) formCommand.getCommandResult().getReturnValue();
        FormsUtils.createFormDiagram(createdForm, processEditor.getEditingDomain());
        FormsUtils.openDiagram(createdForm, processEditor.getEditingDomain());
        assertEquals("There should be only one resource", 1, processEditor.getEditingDomain().getResourceSet().getResources().size());
        // form
        FormDiagramEditor formEditor = (FormDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        assertEquals("There should be only one resource", 1, formEditor.getEditingDomain().getResourceSet().getResources().size());
        Form form = (Form)formEditor.getDiagramEditPart().resolveSemanticElement();
        SetCommand touchFormCommand = new SetCommand(formEditor.getEditingDomain(), form, ProcessPackage.Literals.ELEMENT__DOCUMENTATION, "desc");
        formEditor.getEditingDomain().getCommandStack().execute(touchFormCommand);
        assertEquals("There should be only one resource", 1, formEditor.getEditingDomain().getResourceSet().getResources().size());
        formEditor.doSave(new NullProgressMonitor());
        assertEquals("There should be only one resource", 1, formEditor.getEditingDomain().getResourceSet().getResources().size());
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(formEditor, false);
        // process
        processEditor = (ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        assertEquals("There should be only one resource", 1, processEditor.getEditingDomain().getResourceSet().getResources().size());
        mainProcess = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
        final SetCommand touchProcessCommand = new SetCommand(processEditor.getEditingDomain(), mainProcess, ProcessPackage.Literals.ELEMENT__DOCUMENTATION, "descProc");
        processEditor.getEditingDomain().getCommandStack().execute(touchProcessCommand);
        assertEquals("There should be only one resource", 1, processEditor.getEditingDomain().getResourceSet().getResources().size());
        FormsUtils.openDiagram(mainProcess.getForm().get(0),processEditor.getEditingDomain());
        assertEquals("There should be only one resource", 1, processEditor.getEditingDomain().getResourceSet().getResources().size());
        // form
        formEditor = (FormDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        assertEquals("There should be only one resource", 1, formEditor.getEditingDomain().getResourceSet().getResources().size());
        form = (Form)formEditor.getDiagramEditPart().resolveSemanticElement();
        touchFormCommand = new SetCommand(formEditor.getEditingDomain(), form, ProcessPackage.Literals.ELEMENT__DOCUMENTATION, "desc2");
        formEditor.getEditingDomain().getCommandStack().execute(touchFormCommand);
        assertEquals("There should be only one resource", 1, formEditor.getEditingDomain().getResourceSet().getResources().size());
        formEditor.doSave(new NullProgressMonitor());
        assertEquals("There should be only one resource", 1, processEditor.getEditingDomain().getResourceSet().getResources().size());
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(formEditor, false);
        // process
        processEditor = (ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        assertEquals("There should be only one resource", 1, processEditor.getEditingDomain().getResourceSet().getResources().size());
        mainProcess = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
        assertEquals("Process was not saved when switching to form", "descProc", mainProcess.getDocumentation());
    }
}
