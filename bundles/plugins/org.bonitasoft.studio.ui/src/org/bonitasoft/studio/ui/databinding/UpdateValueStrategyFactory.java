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
package org.bonitasoft.studio.ui.databinding;

import org.bonitasoft.studio.ui.validator.ValidatorBuilder;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.validation.IValidator;

public class UpdateValueStrategyFactory {

    private final UpdateValueStrategy updateValueStrategy;

    public UpdateValueStrategyFactory(final int updatePolicy) {
        updateValueStrategy = new UpdateValueStrategy(updatePolicy);
    }

    public UpdateValueStrategyFactory withValidator(final IValidator validator) {
        return withValidator(validator, ValidatorEvent.AFTER_GET);
    }

    public UpdateValueStrategyFactory withValidator(final ValidatorBuilder<?> validator) {
        return withValidator(validator.create(), ValidatorEvent.AFTER_GET);
    }

    public UpdateValueStrategyFactory withValidator(final IValidator validator, final ValidatorEvent event) {
        switch (event) {
            case AFTER_CONVERT:
                updateValueStrategy.setAfterConvertValidator(validator);
                break;
            case BEFORE_SET:
                updateValueStrategy.setBeforeSetValidator(validator);
                break;
            case AFTER_GET:
            default:
                updateValueStrategy.setAfterGetValidator(validator);
                break;
        }
        return this;
    }

    public UpdateValueStrategyFactory withConverter(final IConverter converter) {
        updateValueStrategy.setConverter(converter);
        return this;
    }

    public UpdateValueStrategy create() {
        return updateValueStrategy;
    }
}
