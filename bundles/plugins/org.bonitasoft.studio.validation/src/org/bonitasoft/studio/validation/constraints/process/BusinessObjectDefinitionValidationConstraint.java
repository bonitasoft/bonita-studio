/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/

package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectDefinitionValidationConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.ex.constraint.businessobject.checkBusinessObjectExist";

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final EObject eObj = context.getTarget();
        if (eObj instanceof BusinessObjectData) {
            final BusinessObjectData businessObjectData = (BusinessObjectData) eObj;
            if (!businessObjectDefinitionExists(businessObjectData)) {
                return context.createFailureStatus(
                        Messages.bind(Messages.businessObjectDefinitionNotFound, businessObjectData.getClassName()));
            }
        }
        return context.createSuccessStatus();
    }

    protected boolean businessObjectDefinitionExists(final BusinessObjectData businessObjectData) {
        return getBusinessObjectDefinitionStore().getChildByQualifiedName(businessObjectData.getClassName()).isPresent();
    }

    protected BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> getBusinessObjectDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

}
