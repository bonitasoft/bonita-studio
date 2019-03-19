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
package org.bonitasoft.studio.contract.ui.wizard;

import java.util.List;

import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.wizard.GenerationOptions.EditMode;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

/**
 * @author aurelie
 */
public class EmptySelectionMultivalidator extends MultiValidator {

    private final IObservableSet checkedElements;
    private List<FieldToContractInputMapping> mappings;
    private final EObject container;
    private final WritableValue selectedDataObservable;
    private IObservableValue<EditMode> editModeObservable;

    public EmptySelectionMultivalidator(final WritableValue selectedDataObservable, final IObservableSet checkedElements,
            final List<FieldToContractInputMapping> mappings, final EObject container, IObservableValue<EditMode> editModeObservable) {
        this.checkedElements = checkedElements;
        this.mappings = mappings;
        this.container = container;
        this.selectedDataObservable = selectedDataObservable;
        this.editModeObservable = editModeObservable;
    }

    @Override
    protected IStatus validate() {
        if (selectedDataObservable.getValue() instanceof BusinessObjectData) {
            if (checkedElements.isEmpty()) {
                return ValidationStatus.error(Messages.atLeastOneAttributeShouldBeSelectedError);
            } else if(editModeObservable.getValue() == EditMode.CREATE){
                final StringBuilder sb = new StringBuilder();
                validateMandatoryFieldsNotSelected(sb, mappings, checkedElements);
                if (sb.length() > 0) {
                    int lastIndexOf = sb.lastIndexOf(",");
                    if (sb.indexOf(",") == lastIndexOf) {
                        sb.replace(lastIndexOf, lastIndexOf + 1, "");
                    }
                    final String message = container instanceof Task ? Messages.mandatoryFieldsNotSelectedStepWarning
                            : Messages.mandatoryFieldsNotSelectedWarning;
                    return ValidationStatus.warning(Messages.bind(message, sb.toString()));
                }
            }
        }
        return ValidationStatus.ok();
    }

    private void validateMandatoryFieldsNotSelected(final StringBuilder sb,
            final List<FieldToContractInputMapping> mappings, final IObservableSet checkedElements) {
        BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

            @Override
            public void run() {
                for (final FieldToContractInputMapping mapping : mappings) {
                    if (!checkedElements.contains(mapping) && !mapping.isGenerated() && !mapping.getField().isNullable()) {
                        if (mapping.getParent() != null) {
                            sb.append(mapping.getParent().getField().getName());
                            sb.append(".");
                        }
                        sb.append(mapping.getField().getName());
                        sb.append(", ");
                    } else {
                        if (checkedElements.contains(mapping) && mapping.isGenerated()) {
                            validateMandatoryFieldsNotSelected(sb, mapping.getChildren(), checkedElements);
                        }
                    }
                }
            }

        });

    }

    public void setMappings(final List<FieldToContractInputMapping> mappings) {
        this.mappings = mappings;
    }

}
