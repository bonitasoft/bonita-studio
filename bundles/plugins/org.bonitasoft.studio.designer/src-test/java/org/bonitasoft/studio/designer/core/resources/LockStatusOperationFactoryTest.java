/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.repository.extension.IGetLockStatusOperation;
import org.bonitasoft.studio.designer.core.resources.LockStatusOperationFactory;
import org.bonitasoft.studio.designer.core.resources.LockStatusOperationNotFound;
import org.eclipse.core.runtime.IConfigurationElement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class LockStatusOperationFactoryTest {

    @Spy
    private LockStatusOperationFactory lockStatusOperationProvider;

    @Mock
    private BonitaStudioExtensionRegistryManager registry;

    @Mock
    private IConfigurationElement configurationElement;

    private IGetLockStatusOperation lockStatusOperation;

    @Before
    public void setUp() throws Exception {
        doReturn(registry).when(lockStatusOperationProvider).extensionRegistry();
        doReturn(lockStatusOperation).when(configurationElement).createExecutableExtension("class");
    }

    @Test
    public void should_instantitate_a_LockStatusOperation_if_configuration_element_is_found_in_registry() throws Exception {
        doReturn(Arrays.asList(configurationElement).toArray(new IConfigurationElement[] {})).when(registry).getConfigurationElements(
                LockStatusOperationFactory.LOCK_STATUS_PROVIDER_EXTENSION_ID);

        assertThat(lockStatusOperationProvider.newLockStatusOperation()).isSameAs(lockStatusOperation);
    }

    @Test(expected = LockStatusOperationNotFound.class)
    public void should_throw_an_LockStatusOperationNotFound_if_no_configuration_element_are_found_in_registry_with_null_return() throws Exception {
        doReturn(null).when(registry).getConfigurationElements(
                LockStatusOperationFactory.LOCK_STATUS_PROVIDER_EXTENSION_ID);

        lockStatusOperationProvider.newLockStatusOperation();
    }

    @Test(expected = LockStatusOperationNotFound.class)
    public void should_throw_an_LockStatusOperationNotFound_if_no_configuration_element_are_found_in_registry_with_empty_array_return() throws Exception {
        doReturn(new IConfigurationElement[0]).when(registry).getConfigurationElements(
                LockStatusOperationFactory.LOCK_STATUS_PROVIDER_EXTENSION_ID);

        lockStatusOperationProvider.newLockStatusOperation();
    }
}
