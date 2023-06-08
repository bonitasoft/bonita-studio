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
package org.bonitasoft.studio.data.ui.property.section.control;

import java.util.Collections;
import java.util.Set;

import org.bonitasoft.studio.data.ui.wizard.DataWizard;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class DataWizardFactory {

    private final EStructuralFeature dataFeature;
    private final Set<EStructuralFeature> dataFeatureToCheckUniqueID;
    private final boolean showAutoGenerateForm;
    private final boolean isPageFlowContext;
    private final boolean isOverviewContext;

    public DataWizardFactory(final EStructuralFeature dataFeature,
            final Set<EStructuralFeature> dataFeatureToCheckUniqueID,
            final boolean showAutoGenerateForm,
            final boolean isPageFlowContext,
            final boolean isOverviewContext) {
        this.dataFeature = dataFeature;
        this.dataFeatureToCheckUniqueID = dataFeatureToCheckUniqueID;
        this.showAutoGenerateForm = showAutoGenerateForm;
        this.isPageFlowContext = isPageFlowContext;
        this.isOverviewContext = isOverviewContext;
    }

    public DataWizardFactory(final EStructuralFeature dataFeature) {
        this(dataFeature, Collections.singleton(dataFeature), true, false, false);
    }

    public DataWizard createAddWizard(final TransactionalEditingDomain domain, final DataAware container) {
        final DataWizard wizard = new DataWizard(domain, container, dataFeature, dataFeatureToCheckUniqueID, showAutoGenerateForm);
        wizard.setIsPageFlowContext(isPageFlowContext);
        wizard.setIsOverviewContext(isOverviewContext);
        return wizard;
    }

    public DataWizard createEditWizard(final TransactionalEditingDomain domain, final Data data) {
        final DataWizard wizard = new DataWizard(domain, data, dataFeature, dataFeatureToCheckUniqueID, showAutoGenerateForm);
        wizard.setIsPageFlowContext(isPageFlowContext);
        wizard.setIsOverviewContext(isOverviewContext);
        return wizard;
    }
}
