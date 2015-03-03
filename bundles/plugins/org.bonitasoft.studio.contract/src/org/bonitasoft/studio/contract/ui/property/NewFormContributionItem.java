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
package org.bonitasoft.studio.contract.ui.property;

import static org.assertj.core.util.Strings.isNullOrEmpty;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProvider;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProviderAdapterFactory;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.provider.FormMappingItemProvider;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.pagedesigner.core.expression.CreateNewFormProposalListener;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageFileStore;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 */
public class NewFormContributionItem implements IContributionItem {

    private final IObservableValue selectionObservableValue;
    private ToolItem toolItem;

    public NewFormContributionItem(final IObservableValue selectionObservableValue) {
        this.selectionObservableValue = selectionObservableValue;
    }

    @Override
    public void update(final String arg0) {
    }

    @Override
    public void update() {
        if (toolItem != null) {
            toolItem.setEnabled(isEnabled());
        }
    }

    @Override
    public void setVisible(final boolean arg0) {

    }

    @Override
    public void setParent(final IContributionManager arg0) {

    }

    @Override
    public void saveWidgetState() {

    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public boolean isSeparator() {
        return false;
    }

    @Override
    public boolean isGroupMarker() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        final Task task = (Task) selectionObservableValue.getValue();
        if (task != null) {
            final FormMapping formMapping = task.getFormMapping();
            return formMapping.isExternal() ? isNullOrEmpty(formMapping.getUrl()) : !formMapping.getTargetForm().hasName();
        }
        return false;
    }

    @Override
    public boolean isDynamic() {
        return false;
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void fill(final CoolBar arg0, final int arg1) {

    }

    @Override
    public void fill(final ToolBar toolbar, final int arg1) {
        toolItem = new ToolItem(toolbar, SWT.LEFT | SWT.PUSH | SWT.NO_FOCUS);
        toolItem.setEnabled(false);
        toolItem.setToolTipText(Messages.newFormTooltip);
        toolItem.setImage(Pics.getImage("new_form.png", ContractPlugin.getDefault()));

        final CreateNewFormProposalListener createNewFormProposalListener = new CreateNewFormProposalListener(
                InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID), PlatformUI.getWorkbench().getProgressService());
        toolItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Task task = (Task) selectionObservableValue.getValue();
                final String newPageId = createNewFormProposalListener.handleEvent(task.getFormMapping(), null);
                final ExpressionItemProvider expressionItemProvider = new ExpressionItemProvider(new ExpressionItemProviderAdapterFactory());
                final RepositoryAccessor repositoryAccessor = getRepositoryAccessor();
                final WebPageRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class);
                repositoryStore.refresh();
                final WebPageFileStore webPageFileStore = repositoryStore.getChild(newPageId + ".json");
                if (webPageFileStore != null) {
                    final Expression targetForm = task.getFormMapping().getTargetForm();
                    expressionItemProvider.setPropertyValue(targetForm,
                            ExpressionPackage.Literals.EXPRESSION__NAME.getName(), webPageFileStore.getDisplayName());
                    expressionItemProvider.setPropertyValue(targetForm,
                            ExpressionPackage.Literals.EXPRESSION__CONTENT.getName(), newPageId);
                    expressionItemProvider.setPropertyValue(targetForm,
                            ExpressionPackage.Literals.EXPRESSION__TYPE.getName(), ExpressionConstants.FORM_REFERENCE_TYPE);
                    expressionItemProvider.setPropertyValue(targetForm,
                            ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE.getName(), String.class.getName());
                    expressionItemProvider.setPropertyValue(targetForm,
                            ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE_FIXED.getName(), true);

                    final FormMappingItemProvider formMappingItemProvider = new FormMappingItemProvider(new ProcessItemProviderAdapterFactory());
                    formMappingItemProvider.setPropertyValue(task.getFormMapping(), ProcessPackage.Literals.FORM_MAPPING__EXTERNAL.getName(), false);
                    toolItem.setEnabled(false);
                }
            }
        });
    }

    protected RepositoryAccessor getRepositoryAccessor() {
        final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        return repositoryAccessor;
    }

    @Override
    public void fill(final Menu parent, final int index) {

    }

    @Override
    public void fill(final Composite arg0) {

    }

    @Override
    public void dispose() {

    }
}
