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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.validation.ValidationPlugin;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;

import com.google.common.base.Objects;

public class QueryNameValidationConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraint.businessobject.checkQueryName";

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final BusinessObjectModelFileStore modelFileStore = getCurrentBDM();
        final MultiStatus multiStatus = new MultiStatus(ValidationPlugin.PLUGIN_ID, 0, null, null);
        if (modelFileStore != null) {
            for (final BusinessObject bo : modelFileStore.getBusinessObjects()) {
                for (final Query q : BDMQueryUtil.createProvidedQueriesForBusinessObject(bo)) {
                    for (final Query customQuery : bo.getQueries()) {
                        if (Objects.equal(customQuery.getName().toLowerCase(), q.getName().toLowerCase())) {
                            multiStatus.add(
                                    context.createFailureStatus(NLS.bind(Messages.conflictingQueryNamesInBusinessObject,
                                            bo.getSimpleName(), q.getName())));
                        }
                    }
                }
            }
            return multiStatus;
        }
        return context.createSuccessStatus();
    }

    protected BusinessObjectModelFileStore getCurrentBDM() {
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectModelRepositoryStore = getBusinessObjectDefinitionStore();
        return businessObjectModelRepositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
    }

    protected BusinessObjectModelRepositoryStore getBusinessObjectDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

}
