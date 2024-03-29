/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.core.expressions.PropertyTester;

public class ProjectNotSharedPropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        return RepositoryManager.getInstance().getCurrentRepository().filter(IRepository::isShared).isEmpty();
    }
}
