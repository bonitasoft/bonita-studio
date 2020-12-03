/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.properties.sections.index;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author aurelie zara
 */
public class IndexSection extends AbstractBonitaDescriptionSection {

    private static final int MAX_LINES = 5;

    private List<ExpressionViewer> nameViewers;

    private List<ExpressionViewer> valueViewers;

    private EMFDataBindingContext context;

    private TransactionalEditingDomain editingDomain;

    private Pool pool;

    @Override
    protected void createContent(final Composite parent) {
        createAllLines(parent);
        refreshBindings();
    }

    protected void createAllLines(final Composite parent) {
        nameViewers = new ArrayList<>(MAX_LINES);
        valueViewers = new ArrayList<>(MAX_LINES);
        final TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();
        final Composite lineComposite = widgetFactory.createComposite(parent);
        lineComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .create());
        lineComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        final Label name = widgetFactory.createLabel(lineComposite,
                Messages.indexName);
        name.setLayoutData(GridDataFactory.fillDefaults().indent(16, 0)
                .create());
        final Label value = widgetFactory.createLabel(lineComposite,
                Messages.indexValue);
        value.setLayoutData(GridDataFactory.fillDefaults().indent(16, 0)
                .create());
        for (int i = 0; i < MAX_LINES; i++) {
            createLine(lineComposite, widgetFactory);
        }
    }

    protected void createLine(final Composite parent,
            final TabbedPropertySheetWidgetFactory widgetFactory) {
        final ExpressionViewer nameViewer = new ExpressionViewer(parent,
                SWT.BORDER, widgetFactory,
                ProcessPackage.Literals.SEARCH_INDEX__NAME) {

            @Override
            protected boolean shouldAddEditToolItem() {
                return false;
            }
        };
        nameViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(false, false)
                        .hint(350, SWT.DEFAULT).create());
        nameViewers.add(nameViewer);
        final ExpressionViewer valueViewer = new ExpressionViewer(parent,
                SWT.BORDER, widgetFactory,
                ProcessPackage.Literals.SEARCH_INDEX__VALUE);
        valueViewers.add(valueViewer);
        valueViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(false, false)
                        .hint(350, SWT.DEFAULT).create());

    }

    protected void refreshBindings() {
        if (context != null) {
            context.dispose();
        }
        context = new EMFDataBindingContext();
        if (pool != null) {
            for (final SearchIndex searchIndex : pool.getSearchIndexes()) {
                final int i = pool.getSearchIndexes().indexOf(searchIndex);
                bindNameViewer(searchIndex, i);
                bindValueViewer(searchIndex, i);
            }
        }
    }

    private void bindNameViewer(final SearchIndex searchIndex, final int index) {
        final ExpressionViewer nameViewer = nameViewers.get(index);
        nameViewer.setContext(pool);
        nameViewer.setInput(searchIndex);
        nameViewer.addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.CONSTANT_TYPE));
        nameViewer
                .addExpressionValidator(new SearchIndexNameUnicityValidator());
        nameViewer.manageNatureProviderAndAutocompletionProposal(null);
        final RefactorSearchIndexOperation operation = new RefactorSearchIndexOperation(
                pool);
        operation.setEditingDomain(getEditingDomain());
        nameViewer.setRefactorOperationToExecuteWhenUpdatingContent(operation);
        final RemoveSearchReferencesOperation removeSearchReferencesOperation = new RemoveSearchReferencesOperation(
                pool, searchIndex);
        removeSearchReferencesOperation.setEditingDomain(getEditingDomain());
        nameViewer.setRemoveOperation(removeSearchReferencesOperation);
        context.bindValue(
                ViewerProperties.singleSelection().observe(nameViewer),
                EMFEditProperties.value(editingDomain,
                        ProcessPackage.Literals.SEARCH_INDEX__NAME).observe(
                                searchIndex));
    }

    private void bindValueViewer(final SearchIndex searchIndex, final int index) {
        final ExpressionViewer valueViewer = valueViewers.get(index);
        valueViewer.setContext(getEObject());
        valueViewer.setInput(searchIndex);
        valueViewer.addFilter(createAvailableExpressionTypeFilter());
        context.bindValue(
                ViewerProperties.singleSelection().observe(valueViewer),
                EMFEditProperties.value(editingDomain,
                        ProcessPackage.Literals.SEARCH_INDEX__VALUE).observe(
                                searchIndex));
    }

    protected AvailableExpressionTypeFilter createAvailableExpressionTypeFilter() {
        return new AvailableExpressionTypeFilter(new String[] {
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.CONTRACT_INPUT_TYPE });
    }

    @Override
    public void refresh() {
        super.refresh();
    }

    @Override
    public String getSectionDescription() {
        return Messages.indexSearchDescription;
    }

    @Override
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    @Override
    public void setEObject(final EObject object) {
        if (object instanceof Pool) {
            pool = (Pool) object;
        } else {
            pool = (Pool) ModelHelper.getParentProcess(object);
        }
        refreshBindings();
    }

    @Override
    public void dispose() {
        if (context != null) {
            context.dispose();
        }
    }

}
