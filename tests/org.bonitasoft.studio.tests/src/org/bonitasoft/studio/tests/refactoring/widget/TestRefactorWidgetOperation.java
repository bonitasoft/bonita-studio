/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.refactoring.widget;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.studio.properties.form.sections.general.contributions.RefactorWidgetOperation;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 *
 */
public class TestRefactorWidgetOperation {

	private RefactorWidgetOperation refactorWidgetOperation;
    private Pool process;
	private TextFormField widgetToRefactor;
	final String initialName = "initialName";

	@Test
    public void testBasicRenameWidget() throws InvocationTargetException, InterruptedException{
    	final String newName = "newName";
		refactorWidgetOperation = new RefactorWidgetOperation(widgetToRefactor, newName);
        final TransactionalEditingDomain domain = createEditingDomain();
		refactorWidgetOperation.setEditingDomain(domain);
    	refactorWidgetOperation.run(new NullProgressMonitor());

    	assertEquals("Renaming of Widget name is not working", newName, widgetToRefactor.getName());

    	domain.getCommandStack().undo();

    	assertEquals("Undo Renaming of Widget name is not working", initialName, widgetToRefactor.getName());
    }

	@Test
    public void testRenameWidgetReferencedInScript() throws InvocationTargetException, InterruptedException{

		final Widget widgetWithScript = FormFactory.eINSTANCE.createTextFormField();
		final Expression scriptRefereincingRefactoredWidget = ExpressionFactory.eINSTANCE.createExpression();
		scriptRefereincingRefactoredWidget.setContent("${"+WidgetHelper.FIELD_PREFIX+initialName+"}");
		scriptRefereincingRefactoredWidget.setType(ExpressionConstants.SCRIPT_TYPE);
		scriptRefereincingRefactoredWidget.getReferencedElements().add(EcoreUtil.copy(widgetToRefactor));
		widgetWithScript.setDisplayAfterEventDependsOnConditionScript(scriptRefereincingRefactoredWidget);
		process.getForm().get(0).getWidgets().add(widgetWithScript);

    	final String newName = "newName";
		refactorWidgetOperation = new RefactorWidgetOperation(widgetToRefactor, newName);
        final TransactionalEditingDomain domain = createEditingDomain();
		refactorWidgetOperation.setEditingDomain(domain);
    	refactorWidgetOperation.run(new NullProgressMonitor());

    	assertEquals("Renaming of Widget name is not working", newName, widgetToRefactor.getName());
    	assertEquals("Renaming of Widget name doesn't update script", "${"+WidgetHelper.FIELD_PREFIX+newName+"}",scriptRefereincingRefactoredWidget.getContent());
    	assertEquals("Renaming of Widget name doesn't update reference in script", newName,((Widget) scriptRefereincingRefactoredWidget.getReferencedElements().get(0)).getName());

    	domain.getCommandStack().undo();

    	assertEquals("Undo Renaming of Widget name is not working", initialName, widgetToRefactor.getName());
    	assertEquals("Renaming of Widget name doesn't update script", "${"+WidgetHelper.FIELD_PREFIX+initialName+"}",scriptRefereincingRefactoredWidget.getContent());
    	assertEquals("Renaming of Widget name doesn't update reference in script", initialName,((Widget) scriptRefereincingRefactoredWidget.getReferencedElements().get(0)).getName());
    }

	@Test
    public void testRenameWidgetReferenced() throws InvocationTargetException, InterruptedException{

		final Widget widgetWithScript = FormFactory.eINSTANCE.createTextFormField();
		final Expression scriptReferencingRefactoredWidget = ExpressionFactory.eINSTANCE.createExpression();
		scriptReferencingRefactoredWidget.setName(WidgetHelper.FIELD_PREFIX+initialName);
		scriptReferencingRefactoredWidget.setType(ExpressionConstants.FORM_FIELD_TYPE);
		scriptReferencingRefactoredWidget.getReferencedElements().add(EcoreUtil.copy(widgetToRefactor));
		widgetWithScript.setDisplayAfterEventDependsOnConditionScript(scriptReferencingRefactoredWidget);
		process.getForm().get(0).getWidgets().add(widgetWithScript);

    	final String newName = "newName";
		refactorWidgetOperation = new RefactorWidgetOperation(widgetToRefactor, newName);
        final TransactionalEditingDomain domain = createEditingDomain();
		refactorWidgetOperation.setEditingDomain(domain);
    	refactorWidgetOperation.run(new NullProgressMonitor());

    	assertEquals("Renaming of Widget name is not working", newName, widgetToRefactor.getName());
    	assertEquals("Renaming of Widget name doesn't update reference in script", newName,((Widget) scriptReferencingRefactoredWidget.getReferencedElements().get(0)).getName());

    	domain.getCommandStack().undo();

    	assertEquals("Undo Renaming of Widget name is not working", initialName, widgetToRefactor.getName());
    	assertEquals("Renaming of Widget name doesn't update reference in script", initialName,((Widget) scriptReferencingRefactoredWidget.getReferencedElements().get(0)).getName());
    }

    @Before
    public void setUp() throws Exception {
    	process = null;
        createProcessWithWidget();
    }

    private void createProcessWithWidget() {
    	 if (process == null) {
             final MainProcess mainProcess = ProcessFactory.eINSTANCE.createMainProcess();
             ModelHelper.addDataTypes(mainProcess);
             process = ProcessFactory.eINSTANCE.createPool();
             mainProcess.getElements().add(process);

             final Form form = FormFactory.eINSTANCE.createForm();
             widgetToRefactor = FormFactory.eINSTANCE.createTextFormField();

             widgetToRefactor.setName(initialName);
             form.getWidgets().add(widgetToRefactor);
			 process.getForm().add(form);
    	 }
	}

    private TransactionalEditingDomain createEditingDomain() {
        final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

        // command stack that will notify this editor as commands are executed
        final TransactionalCommandStackImpl commandStack = new TransactionalCommandStackImpl();

        // Create the editing domain with our adapterFactory and command stack.
        return new TransactionalEditingDomainImpl(adapterFactory, commandStack);
    }
}
