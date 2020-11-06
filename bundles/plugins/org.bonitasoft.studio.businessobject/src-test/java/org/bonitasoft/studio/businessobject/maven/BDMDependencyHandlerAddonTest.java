/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.maven;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BDMDependencyHandlerAddonTest {

    @Mock
    private IEventBroker eventBroker;
    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Test
    public void should_subscribe_handler_to_bdm_ployed_topic() throws Exception {
        final BDMDependencyHandlerAddon addon = new BDMDependencyHandlerAddon();

        addon.registerHandler(eventBroker, repositoryAccessor);

        verify(eventBroker).subscribe(eq("bdm/deployed"), notNull(InstallBDMDependenciesEventHandler.class));
    }

}
