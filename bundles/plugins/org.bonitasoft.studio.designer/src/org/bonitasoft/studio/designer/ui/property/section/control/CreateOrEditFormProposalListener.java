/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.ui.property.section.control;

import javax.inject.Inject;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.FormScope;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.expression.CreateNewFormProposalListener;
import org.bonitasoft.studio.designer.core.operation.CreateFormFromContractOperation;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.FormMapping;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author aurelie
 */
@Creatable
public class CreateOrEditFormProposalListener extends CreateNewFormProposalListener {

    @Inject
    public CreateOrEditFormProposalListener(final PageDesignerURLFactory pageDesignerURLFactory, final IProgressService progressService,
            final RepositoryAccessor repositoryAccessor) {
        super(pageDesignerURLFactory, progressService, repositoryAccessor);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.designer.core.expression.CreateNewFormProposalListener#handleEvent(org.eclipse.emf.ecore.EObject, java.lang.String)
     */
    @Override
    public String handleEvent(final EObject context, final String fixedReturnType) {
        final FormMapping mapping = (FormMapping) context;
        final Expression targetForm = mapping.getTargetForm();
        if (targetForm.hasContent()) {
            final WebPageFileStore pageStore = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChild(targetForm.getContent());
            if (pageStore != null) {
                pageStore.open();
            } else {
                MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.formDoesntExistAnymoreTitle,
                        Messages.bind(Messages.bind(Messages.formDoesntExistAnymoreMessage, targetForm.getName()), targetForm.getName()));
            }
            return null;
        } else {
            return super.handleEvent(context, fixedReturnType);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.designer.core.expression.CreateNewFormProposalListener#doCreateFormOperation(org.bonitasoft.studio.designer.core.PageDesignerURLFactory
     * , java.lang.String, org.bonitasoft.studio.model.process.Contract)
     */
    @Override
    protected CreateFormFromContractOperation doCreateFormOperation(final PageDesignerURLFactory pageDesignerURLBuilder, final String formName,
            final Contract contract, final FormScope scope) {
        return super.doCreateFormOperation(pageDesignerURLBuilder, formName, contract, scope);
    }

}
