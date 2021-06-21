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
package org.bonitasoft.studio.document.ui.control;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.function.Predicate.not;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withContractInputType;
import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withMultipleInHierarchy;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.ui.ContractInputLabelProvider;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public abstract class FileContractInputSelectionComposite extends Composite {

    private final ComboViewer contractInputComboViewer;

    public FileContractInputSelectionComposite(final Composite parent) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label contractLabel = new Label(this, SWT.NONE);
        contractLabel.setLayoutData(GridDataFactory.swtDefaults().create());
        contractLabel.setText(Messages.fileContractInput);

        contractInputComboViewer = new ComboViewer(this, SWT.BORDER | SWT.READ_ONLY);
        contractInputComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER)
                .grab(true, false).indent(10, 0).create());
        contractInputComboViewer.setContentProvider(ArrayContentProvider.getInstance());
        contractInputComboViewer.setLabelProvider(new ContractInputLabelProvider());
    }

    protected ComboViewer getContractInputComboViewer() {
        return contractInputComboViewer;
    }

    public void bindControl(final Document document, final EObject context,
            final EMFDataBindingContext emfDataBindingContext) {
        checkArgument(document != null);
        checkArgument(context != null);
        checkArgument(emfDataBindingContext != null);
        final IObservableValue observeInput = ViewersObservables.observeInput(getContractInputComboViewer());
        final IObservableValue multipleDocumentObervable = EMFObservables.observeValue(document,
                ProcessPackage.Literals.DOCUMENT__MULTIPLE);
        emfDataBindingContext.bindValue(observeInput,
                multipleDocumentObervable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(toInputList(document, context)).create());

        final IViewerObservableValue observeSingleSelection = ViewersObservables
                .observeSingleSelection(getContractInputComboViewer());
        ControlDecorationSupport.create(emfDataBindingContext.bindValue(observeSingleSelection,
                EMFObservables.observeValue(document, ProcessPackage.Literals.DOCUMENT__CONTRACT_INPUT)), SWT.LEFT);
        emfDataBindingContext.addValidationStatusProvider(
                createContractInputParameter(document, observeInput, observeSingleSelection));
    }

    protected abstract ValidationStatusProvider createContractInputParameter(Document document,
            IObservableValue observeInput,
            IObservableValue observeSingleSelection);

    private IConverter toInputList(final Document document, final EObject context) {
        return new Converter(Boolean.class, List.class) {

            @Override
            public Object convert(final Object fromObject) {
                final List<ContractInput> result = ModelHelper.getAllElementOfTypeIn(processContract(context),
                        ContractInput.class);
                return document.isMultiple() ? result.stream()
                        .filter(withContractInputType(ContractInputType.FILE).and(withMultipleInHierarchy()))
                        .collect(Collectors.toList())
                        : result.stream()
                                .filter(withContractInputType(ContractInputType.FILE)
                                        .and(not(withMultipleInHierarchy())))
                                .collect(Collectors.toList());
            }

        };
    }

    private EObject processContract(final EObject context) {
        return ModelHelper.getParentPool(context).getContract();
    }
}
