/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.viewer;

import java.util.Optional;
import java.util.function.Function;

import org.bonitasoft.studio.common.jface.AbstractCheckboxLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;

public class CheckboxLabelProviderBuilder<T> {

    private Optional<Function<T, Boolean>> isSelectedProvider = Optional.empty();
    private Optional<Function<T, Boolean>> isEnableProvider = Optional.empty();
    private Optional<Function<T, String>> tooltipProvider = Optional.empty();

    public CheckboxLabelProviderBuilder<T> withIsSelectedProvider(Function<T, Boolean> isSelectedProvider) {
        this.isSelectedProvider = Optional.ofNullable(isSelectedProvider);
        return this;
    }

    public CheckboxLabelProviderBuilder<T> withIsEnableProvider(Function<T, Boolean> isEnableProvider) {
        this.isEnableProvider = Optional.ofNullable(isEnableProvider);
        return this;
    }

    public CheckboxLabelProviderBuilder<T> withTooltipProvider(Function<T, String> tooltipProvider) {
        this.tooltipProvider = Optional.ofNullable(tooltipProvider);
        return this;
    }

    public AbstractCheckboxLabelProvider createCheckboxLabelProvider(ColumnViewer viewer) {
        return new AbstractCheckboxLabelProvider(viewer) {

            @Override
            protected boolean isSelected(Object element) {
                return isSelectedProvider.orElse(elt -> false).apply((T) element);
            }

            @Override
            protected boolean isEnabled(Object element) {
                return isEnableProvider.orElse(elt -> true).apply((T) element);
            }

            @Override
            public String getToolTipText(Object element) {
                return tooltipProvider.orElse(elt -> null).apply((T) element);
            }
        };
    }

}
