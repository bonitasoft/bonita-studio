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
package org.bonitasoft.studio.contract.ui.property.input.edit;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.progress.IProgressService;

public class InputNameObservableEditingSupport extends CustomTextEMFObservableValueEditingSupport {

    private static final int INPUT_NAME_MAX_LENGTH = 50;
    private final Contract contract;
    private final IProgressService progressService;

    public InputNameObservableEditingSupport(final ColumnViewer viewer,
            final Contract contract,
            final IMessageManager messageManager,
            final DataBindingContext dbc,
            final IProgressService progressService) {
        super(viewer, ProcessPackage.Literals.CONTRACT_INPUT__NAME, messageManager, dbc);
        this.contract = contract;
        this.progressService = progressService;
    }

    @Override
    protected TextCellEditor getCellEditor(final Object object) {
        final TextCellEditor textCellEditor = super.getCellEditor(object);
        final Text control = (Text) textCellEditor.getControl();
        textCellEditor.addListener(new RefactorInputNameListener(progressService, (ContractInput) object, control));
        return textCellEditor;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.databinding.CustomTextEMFObservableValueEditingSupport#taregtToModelConvertStrategy()
     */
    @Override
    protected UpdateValueStrategy taregtToModelConvertStrategy() {
        return convertUpdateValueStrategy()
                .withValidator(
                        multiValidator()
                                .addValidator(mandatoryValidator(Messages.name))
                                .addValidator(maxLengthValidator(Messages.name, INPUT_NAME_MAX_LENGTH))
                                .addValidator(groovyReferenceValidator(Messages.name).startsWithLowerCase())
                                .addValidator(uniqueValidator().in(allContractInput()).onProperty("name"))).create();
    }

    private Iterable<ContractInput> allContractInput() {
        return ModelHelper.getAllElementOfTypeIn(contract, ContractInput.class);
    }

}
