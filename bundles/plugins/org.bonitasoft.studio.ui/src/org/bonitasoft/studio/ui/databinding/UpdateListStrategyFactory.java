/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.databinding;

import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.IConverter;

public class UpdateListStrategyFactory {

    private final UpdateListStrategy updateListStrategy;

    public UpdateListStrategyFactory(final int updatePolicy) {
        updateListStrategy = new UpdateListStrategy(updatePolicy);
    }

    public UpdateListStrategyFactory withConverter(final IConverter converter) {
        updateListStrategy.setConverter(converter);
        return this;
    }

    public UpdateListStrategy create() {
        return updateListStrategy;
    }
}
