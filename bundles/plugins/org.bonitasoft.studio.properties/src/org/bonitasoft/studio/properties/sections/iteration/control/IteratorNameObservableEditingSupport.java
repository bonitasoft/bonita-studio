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
package org.bonitasoft.studio.properties.sections.iteration.control;

import static com.google.common.base.Preconditions.checkArgument;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.ColumnViewerUpdateListener;
import org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.refactoring.core.emf.EMFEditWithRefactorObservables;
import org.bonitasoft.studio.refactoring.core.emf.IRefactorOperationFactory;
import org.bonitasoft.studio.refactoring.core.emf.ObservableValueWithRefactor;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.progress.IProgressService;

public class IteratorNameObservableEditingSupport extends CustomTextEMFObservableValueEditingSupport {

    private static final int NAME_MAX_LENGTH = 50;
    private final IProgressService progressService;
    private final IRefactorOperationFactory iteratorRefactorOperationFactory;

    public IteratorNameObservableEditingSupport(final ColumnViewer viewer,
            final IMessageManager messageManager,
            final DataBindingContext dbc,
            final IRefactorOperationFactory iteratorRefactorOperationFactory,
            final IProgressService progressService) {
        super(viewer, ExpressionPackage.Literals.EXPRESSION__NAME, messageManager, dbc);
        this.progressService = progressService;
        this.iteratorRefactorOperationFactory = iteratorRefactorOperationFactory;
    }

    @Override
    protected IObservableValue doCreateElementObservable(final Object element, final ViewerCell cell) {
        checkArgument(element instanceof Expression);
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(element);
        final ObservableValueWithRefactor observableValue = EMFEditWithRefactorObservables.observeValueWithRefactor(
                editingDomain, (EObject) element,
                ExpressionPackage.Literals.EXPRESSION__NAME,
                iteratorRefactorOperationFactory,
                progressService);
        observableValue.addValueChangeListener(new ColumnViewerUpdateListener(getViewer(), element));
        final IObservableValue contentObserveValue = EMFEditObservables.observeValue(editingDomain, (EObject) element,
                ExpressionPackage.Literals.EXPRESSION__CONTENT);
        observableValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                contentObserveValue.setValue(event.diff.getNewValue());
            }
        });
        return observableValue;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport#taregtToModelConvertStrategy()
     */
    @Override
    protected UpdateValueStrategy targetToModelConvertStrategy(final EObject element) {
        return convertUpdateValueStrategy()
                .withValidator(
                        multiValidator()
                                .addValidator(mandatoryValidator(Messages.name))
                                .addValidator(maxLengthValidator(Messages.iterator, NAME_MAX_LENGTH))
                                .addValidator(groovyReferenceValidator(Messages.iterator).startsWithLowerCase())
                                .addValidator(uniqueValidator().in(visibleData(element)).onProperty("name"))).create();
    }

    private Iterable<?> visibleData(final EObject context) {
        return ModelHelper.getAccessibleData(ModelHelper.getParentPool(context));
    }

}
