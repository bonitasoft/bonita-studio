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
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.designer.ui.property.section.FormReferenceProposalLabelProvider;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class InternalMappingComposite extends Composite implements BonitaPreferenceConstants {

    private static final int WIDTH_HINT = 500;

    private final FormReferenceExpressionViewer targetFormExpressionViewer;
    private final RepositoryAccessor repositoryAccessor;
    private final Label info;

    public InternalMappingComposite(final Composite parent,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final RepositoryAccessor repositoryAccessor,
            final FormReferenceExpressionValidator formReferenceExpressionValidator,
            final CreateOrEditFormProposalListener createOrEditFormListener) {
        super(parent, SWT.NONE);
        this.repositoryAccessor = repositoryAccessor;
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 0, 10, 0).create());
        final Label label = widgetFactory.createLabel(this, Messages.targetForm);
        label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        targetFormExpressionViewer = createFormExpressionViewer(widgetFactory, repositoryAccessor,
                formReferenceExpressionValidator, createOrEditFormListener);

        info = widgetFactory.createLabel(this, "", SWT.WRAP);
        info.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).align(SWT.FILL, SWT.CENTER).create());
        widgetFactory.adapt(this);
    }

    protected FormReferenceExpressionViewer createFormExpressionViewer(
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final RepositoryAccessor repositoryAccessor,
            final FormReferenceExpressionValidator formReferenceExpressionValidator,
            final CreateOrEditFormProposalListener createOrEditFormListener) {
        final WebPageRepositoryStore webPageRepositoryStore = repositoryAccessor
                .getRepositoryStore(WebPageRepositoryStore.class);
        final FormReferenceExpressionViewer expressionViewer = new FormReferenceExpressionViewer(this, SWT.BORDER,
                widgetFactory,
                webPageRepositoryStore, createOrEditFormListener);
        expressionViewer.getControl()
                .setLayoutData(GridDataFactory.swtDefaults().hint(WIDTH_HINT, SWT.DEFAULT).grab(false, false).create());
        expressionViewer.setExpressionProposalLableProvider(new FormReferenceProposalLabelProvider());
        expressionViewer.addExpressionValidator(formReferenceExpressionValidator);
        expressionViewer.addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.FORM_REFERENCE_TYPE));
        expressionViewer.setProposalsFiltering(false);
        return expressionViewer;
    }

    public void doBindControl(final DataBindingContext context, final IObservableValue formMappingObservable) {
        context.bindValue(ViewersObservables.observeInput(targetFormExpressionViewer), formMappingObservable);
        context.bindValue(ViewersObservables.observeSingleSelection(targetFormExpressionViewer),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), formMappingObservable,
                        ProcessPackage.Literals.FORM_MAPPING__TARGET_FORM));
        doBindInfo(context, formMappingObservable);
    }

    protected void doBindInfo(final DataBindingContext context, final IObservableValue formMappingObservable) {
        final UpdateValueStrategy infoStrategy = new UpdateValueStrategy();
        infoStrategy.setConverter(new InfoMessageConverter(org.bonitasoft.studio.model.process.FormMappingType.INTERNAL));
        context.bindValue(SWTObservables.observeText(info), formMappingObservable, null, infoStrategy);
    }

}
