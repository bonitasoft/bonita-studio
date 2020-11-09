package org.bonitasoft.studio.data.ui.wizard;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.tryFind;
import static org.eclipse.core.databinding.validation.ValidationStatus.error;
import static org.eclipse.core.databinding.validation.ValidationStatus.ok;

import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;

import com.google.common.base.Predicate;

class DataNameUnicityValidator implements IValidator {

    private final Data originalData;
    private final Iterable<Data> dataInScope;

    DataNameUnicityValidator(final Iterable<Data> dataInScope, final Data originalData) {
        checkArgument(dataInScope != null);
        this.dataInScope = dataInScope;
        this.originalData = originalData;
    }

    @Override
    public IStatus validate(final Object value) {
        return tryFind(dataInScope, anotherDataWithSameName(value)).isPresent() ? error(Messages.dataAlreadyExist) : ok();
    }

    private Predicate<Data> anotherDataWithSameName(final Object value) {
        return new Predicate<Data>() {

            @Override
            public boolean apply(final Data otherData) {
                return !otherData.equals(originalData) && value != null && value.toString().equalsIgnoreCase(otherData.getName());
            }
        };
    }

}
