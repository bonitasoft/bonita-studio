/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.pagedesigner.ui.contribution;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.inject.Inject;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.pagedesigner.PageDesignerPlugin;
import org.bonitasoft.studio.pagedesigner.core.command.UpdateFormMappingCommand;
import org.bonitasoft.studio.pagedesigner.core.expression.CreateNewFormProposalListener;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageFileStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @author Romain Bioteau
 */
@Creatable
public class NewFormContributionItem extends ContributionItem {

    private ToolItem toolItem;

    @Inject
    private CreateNewFormProposalListener createNewFormListener;

    @Inject
    private RepositoryAccessor repositoryAccessor;

    private ISelectionProvider selectionProvider;

    @Override
    public void update() {
        if (toolItem != null) {
            toolItem.setEnabled(canCreateNewForm());
            final PageFlow pageFlow = unwrap(selectionProvider.getSelection());
            if (pageFlow != null) {
                if (pageFlow instanceof Pool) {
                    toolItem.setToolTipText(NLS.bind(Messages.newFormTooltip, Messages.pool));
                } else {
                    toolItem.setToolTipText(NLS.bind(Messages.newFormTooltip, Messages.task));
                }
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return toolItem != null ? toolItem.isEnabled() : false;
    }

    protected boolean canCreateNewForm() {
        final PageFlow pageFlow = unwrap(selectionProvider.getSelection());
        if (pageFlow != null) {
            final FormMapping formMapping = pageFlow.getFormMapping();
            return formMapping.getType() == FormMappingType.URL ? isNullOrEmpty(formMapping.getUrl()) : !formMapping.getTargetForm().hasName();
        }
        return false;
    }

    private PageFlow unwrap(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            return (PageFlow) ((IStructuredSelection) selection).getFirstElement();
        }
        return null;
    }

    @Override
    public void fill(final ToolBar toolbar, final int style) {
        toolItem = new ToolItem(toolbar, SWT.LEFT | SWT.PUSH | SWT.NO_FOCUS);
        toolItem.setEnabled(false);
        toolItem.setToolTipText(Messages.newFormTooltip);
        toolItem.setImage(Pics.getImage("new_form.png", PageDesignerPlugin.getDefault()));
        toolItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                createNewForm();
            }
        });
    }

    protected void createNewForm() {
        final PageFlow pageflow = unwrap(selectionProvider.getSelection());
        final String newPageId = createNewFormListener.handleEvent(pageflow.getFormMapping(), null);

        final WebPageRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class);
        repositoryStore.refresh();
        final WebPageFileStore webPageFileStore = repositoryStore.getChild(newPageId);
        if (webPageFileStore != null) {
            final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(pageflow);
            editingDomain.getCommandStack().execute(new UpdateFormMappingCommand(editingDomain, pageflow.getFormMapping(),
                    ExpressionHelper.createFormReferenceExpression(webPageFileStore.getDisplayName(), newPageId)));
            toolItem.setEnabled(false);
        }
    }

    public void setSelectionProvider(final ISelectionProvider selectionProvider) {
        this.selectionProvider = selectionProvider;
    }

}
