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
package org.bonitasoft.studio.pagedesigner.ui.property.section.control;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pagedesigner.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.bonitasoft.studio.pagedesigner.ui.property.section.FormReferenceProposalLabelProvider;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class PageDesignerMappingComposite extends Composite implements BonitaPreferenceConstants {

    private static final int WIDTH_HINT = 300;
    private final ExpressionViewer targetFormExpressionViewer;
    private final RepositoryAccessor repositoryAccessor;

    public PageDesignerMappingComposite(final Composite parent,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final IEclipsePreferences preferenceStore,
            final RepositoryAccessor repositoryAccessor,
            final FormReferenceExpressionValidator formReferenceExpressionValidator) {
        super(parent, SWT.NONE);
        this.repositoryAccessor = repositoryAccessor;
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 0, 10, 0).create());
        final Label label = widgetFactory.createLabel(this, Messages.targetForm);
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        targetFormExpressionViewer = new FormReferenceExpressionViewer(this, SWT.BORDER, widgetFactory);
        targetFormExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().hint(WIDTH_HINT, SWT.DEFAULT).create());
        targetFormExpressionViewer.setExpressionProposalLableProvider(new FormReferenceProposalLabelProvider());
        targetFormExpressionViewer.addExpressionValidator(formReferenceExpressionValidator);
        targetFormExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.FORM_REFERENCE_TYPE }));
        widgetFactory.adapt(this);
    }

    public void doBindControl(final DataBindingContext context, final IObservableValue formMappingObservable) {
        context.bindValue(ViewersObservables.observeInput(targetFormExpressionViewer), formMappingObservable);
        context.bindValue(ViewersObservables.observeSingleSelection(targetFormExpressionViewer),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), formMappingObservable, ProcessPackage.Literals.FORM_MAPPING__TARGET_FORM));

        final ToolItem editControl = targetFormExpressionViewer.getButtonControl();
        clearExistingSelectionListeners(editControl);
        editControl.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                editForm(formMappingObservable);
            }

        });
    }

    private void clearExistingSelectionListeners(final ToolItem editControl) {
        final Listener[] toRemove = editControl.getListeners(SWT.Selection);
        for (final Listener l : toRemove) {
            editControl.removeListener(SWT.Selection, l);
        }
    }

    protected void editForm(final IObservableValue formMappingObservable) {
        final FormMapping mapping = (FormMapping) formMappingObservable.getValue();
        final Expression targetForm = mapping.getTargetForm();
        if (targetForm.hasContent()) {
            repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChild(targetForm.getContent() + ".json").open();
        }
    }
}
