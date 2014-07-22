/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.ui;


import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.document.DocumentPlugin;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.refactoring.RefactorDocumentOperation;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.xtext.ui.XtextProjectHelper;

public class DocumentWizard extends Wizard {

    private final EObject context;
    private final Document document;
    private final Document documentWorkingCopy;

    public DocumentWizard(final EObject context){
        super();
        this.context=context;
        setWindowTitle(Messages.newDocument);
        setDefaultPageImageDescriptor(Pics.getWizban());
        documentWorkingCopy = ProcessFactory.eINSTANCE.createDocument();
        final Expression mimeTypeExpression = ExpressionHelper.createConstantExpression("", String.class.getName());
        mimeTypeExpression.setReturnTypeFixed(true);
        documentWorkingCopy.setMimeType(mimeTypeExpression);
        final Expression urlExpression = ExpressionHelper.createConstantExpression("", String.class.getName());
        urlExpression.setReturnTypeFixed(true);
        documentWorkingCopy.setUrl(urlExpression);
        document = null;
    }

    public DocumentWizard(final EObject context, final Document document) {
        super();
        this.context = context;
        setWindowTitle(Messages.newDocument);
        setDefaultPageImageDescriptor(Pics.getWizban());
        this.document = document;
        documentWorkingCopy = EcoreUtil.copy(document);
    }

    @Override
    public void addPages() {
        addPage(new DocumentWizardPage(context, documentWorkingCopy));
    }

    @Override
    public boolean performFinish() {
        final Pool pool = (Pool) ModelHelper.getParentProcess(context);
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(context);
        if (document == null) {
            editingDomain.getCommandStack().execute(new AddCommand(editingDomain, pool.getDocuments(), documentWorkingCopy));
        } else {
            //TODO: handle edit case
            final RefactorDocumentOperation refactorDocumentOperation = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
            refactorDocumentOperation.setEditingDomain(editingDomain);
            refactorDocumentOperation.addItemToRefactor(documentWorkingCopy, document);
            refactorDocumentOperation.setAskConfirmation(true);
            try {
                getContainer().run(false, false, refactorDocumentOperation);
            } catch (final InvocationTargetException e) {
                BonitaStudioLog.error(e);
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }

        refreshProject();
        return true;
    }

    private void refreshProject() {
        try {
            RepositoryManager.getInstance().getCurrentRepository().getProject()
            .build(IncrementalProjectBuilder.FULL_BUILD, XtextProjectHelper.BUILDER_ID, Collections.<String, String> emptyMap(), null);
        } catch (final CoreException e1) {
            BonitaStudioLog.error(e1, DocumentPlugin.PLUGIN_ID);
        }
    }


    public Document getDocument(){
        return documentWorkingCopy;
    }

}
