/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.designer.ui.property.section.control;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.command.UpdateFormMappingCommand;
import org.bonitasoft.studio.designer.core.expression.CreateNewFormProposalListener;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.expression.editor.viewer.EditExpressionDialog;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.FormMapping;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class FormReferenceExpressionViewer extends ExpressionViewer {

    private final WebPageRepositoryStore pageStore;

    public FormReferenceExpressionViewer(final Composite composite, final int style, final TabbedPropertySheetWidgetFactory widgetFactory,
            final WebPageRepositoryStore pageStore) {
        super(composite, style, widgetFactory);
        this.pageStore = pageStore;
    }

    @Override
    protected EditExpressionDialog createEditDialog(final EObject editInput) {
        return null;
    }

    @Override
    protected String getContentTypeFromInput(final String input) {
        return ExpressionConstants.FORM_REFERENCE_TYPE;
    }

    @Override
    protected String overrideDefaultReturnType() {
        return ExpressionConstants.FORM_REFERENCE_TYPE;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer#getNameConverter()
     */
    @Override
    protected Converter getNameConverter() {
        return new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object fromObject) {
                final String content = getSelectedExpression().getContent();
                final WebPageFileStore webPageFileStore = pageStore.getChild(content);
                if (webPageFileStore == null) {
                    updateContent("");
                    updateName("");
                    return "";
                }
                final String displayName = webPageFileStore.getDisplayName();
                if (!displayName.equals(fromObject)) {
                    updateName(displayName);
                }
                return displayName;
            }
        };
    }

    protected void updateName(final String newName) {
        expressionItemProvider.setPropertyValue(getSelectedExpression(), ExpressionPackage.Literals.EXPRESSION__NAME.getName(), newName);
    }

    /**
     * @param formMappingObservable
     */
    public void setEditControlBehavior(final IObservableValue formMappingObservable, final CreateNewFormProposalListener createNewFormListener,
            final RepositoryAccessor repositoryAccessor) {
        final ToolItem editControl = getButtonControl();
        clearExistingSelectionListeners(editControl);
        editControl.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                createOReditForm(formMappingObservable, createNewFormListener, repositoryAccessor);
            }

        });
    }

    private void clearExistingSelectionListeners(final ToolItem editControl) {
        final Listener[] toRemove = editControl.getListeners(SWT.Selection);
        for (final Listener l : toRemove) {
            editControl.removeListener(SWT.Selection, l);
        }
    }

    protected void createOReditForm(final IObservableValue formMappingObservable, final CreateNewFormProposalListener createNewFormListener,
            final RepositoryAccessor repositoryAccessor) {
        final FormMapping mapping = (FormMapping) formMappingObservable.getValue();
        final Expression targetForm = mapping.getTargetForm();
        if (targetForm.hasContent()) {
            repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChild(targetForm.getContent()).open();
        } else {
            createNewForm(mapping, createNewFormListener, repositoryAccessor);
        }
    }

    protected void createNewForm(final FormMapping mapping, final CreateNewFormProposalListener createNewFormListener,
            final RepositoryAccessor repositoryAccessor) {
        final String newPageId = createNewFormListener.handleEvent(mapping, null);
        final WebPageRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class);
        repositoryStore.refresh();
        final WebPageFileStore webPageFileStore = repositoryStore.getChild(newPageId);
        if (webPageFileStore != null) {
            final TransactionalEditingDomain editingDomain = getEditingDomain(mapping);
            editingDomain.getCommandStack().execute(new UpdateFormMappingCommand(editingDomain, mapping,
                    ExpressionHelper.createFormReferenceExpression(webPageFileStore.getDisplayName(), newPageId)));
        }
    }

    /**
     * @param mapping
     * @return
     */
    public TransactionalEditingDomain getEditingDomain(final FormMapping mapping) {
        return TransactionUtil.getEditingDomain(mapping.eContainer());
    }

}
