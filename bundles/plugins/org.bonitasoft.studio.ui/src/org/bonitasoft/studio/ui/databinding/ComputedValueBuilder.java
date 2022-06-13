/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.databinding;

import java.util.function.Supplier;

import org.eclipse.core.databinding.observable.value.ComputedValue;

public class ComputedValueBuilder<T> {

    private Supplier<T> supplier;

    public ComputedValueBuilder<T> withSupplier(Supplier<T> supplier) {
        this.supplier = supplier;
        return this;
    }

    public ComputedValue<T> build() {
        return new ComputedValue<T>() {

            @Override
            protected T calculate() {
                return supplier.get();
            }
        };
    }
}
