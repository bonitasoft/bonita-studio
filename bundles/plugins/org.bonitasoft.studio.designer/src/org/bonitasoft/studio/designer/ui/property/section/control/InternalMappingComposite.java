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
package org.bonitasoft.studio.designer.ui.property.section.control;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.designer.ui.property.section.FormReferenceProposalLabelProvider;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProvider;
import org.bonitasoft.studio.model.expression.provider.ExpressionItemProviderAdapterFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class InternalMappingComposite extends Composite implements BonitaPreferenceConstants {

    private static final int WIDTH_HINT = 300;

    private final FormReferenceExpressionViewer targetFormExpressionViewer;
    private final RepositoryAccessor repositoryAccessor;
    private final WebPageNameResourceChangeListener webPageNameResourceChangeListener;

    public InternalMappingComposite(final Composite parent,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final IEclipsePreferences preferenceStore,
            final RepositoryAccessor repositoryAccessor,
            final FormReferenceExpressionValidator formReferenceExpressionValidator,
            final CreateOrEditFormProposalListener createOrEditFormListener) {
        super(parent, SWT.NONE);
        this.repositoryAccessor = repositoryAccessor;
        webPageNameResourceChangeListener = new WebPageNameResourceChangeListener(new ExpressionItemProvider(
                new ExpressionItemProviderAdapterFactory()));
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 0, 10, 0).create());
        final Label label = widgetFactory.createLabel(this, Messages.targetForm);
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        final WebPageRepositoryStore webPageRepositoryStore = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class);
        targetFormExpressionViewer = new FormReferenceExpressionViewer(this, SWT.BORDER, widgetFactory,
                webPageRepositoryStore, createOrEditFormListener);
        targetFormExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().hint(WIDTH_HINT, SWT.DEFAULT).create());
        targetFormExpressionViewer.setExpressionProposalLableProvider(new FormReferenceProposalLabelProvider());
        targetFormExpressionViewer.addExpressionValidator(formReferenceExpressionValidator);
        targetFormExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.FORM_REFERENCE_TYPE }));
        targetFormExpressionViewer.setProposalsFiltering(false);
        targetFormExpressionViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (selection instanceof Expression) {
                    webPageNameResourceChangeListener.setExpression((Expression) selection);
                    final String content = ((Expression) selection).getContent();
                    final WebPageFileStore webPageFileStore = webPageRepositoryStore.getChild(content);
                    if (webPageFileStore != null) {
                        webPageNameResourceChangeListener.setWebPageFileStore(webPageFileStore);
                        webPageNameResourceChangeListener.setJSONFile(webPageFileStore.getJSONIFile());
                    }
                }
            }
        });
        addResourceChangeListener(webPageNameResourceChangeListener);
        widgetFactory.adapt(this);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    @Override
    public void dispose() {
        removeResourceChangeListener(webPageNameResourceChangeListener);
        super.dispose();
    }

    private void addResourceChangeListener(final IResourceChangeListener resourceChangeListener) {
        repositoryAccessor.getWorkspace().addResourceChangeListener(resourceChangeListener);
    }

    private void removeResourceChangeListener(final IResourceChangeListener resourceChangeListener) {
        repositoryAccessor.getWorkspace().removeResourceChangeListener(resourceChangeListener);
    }

    public void doBindControl(final DataBindingContext context, final IObservableValue formMappingObservable) {
        context.bindValue(ViewersObservables.observeInput(targetFormExpressionViewer), formMappingObservable);
        context.bindValue(ViewersObservables.observeSingleSelection(targetFormExpressionViewer),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), formMappingObservable, ProcessPackage.Literals.FORM_MAPPING__TARGET_FORM));
    }

}
