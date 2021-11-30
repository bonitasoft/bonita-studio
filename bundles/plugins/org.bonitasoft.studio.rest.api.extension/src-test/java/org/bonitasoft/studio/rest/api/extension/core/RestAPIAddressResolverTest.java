/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestAPIAddressResolverTest {

    @Mock
    private IEclipsePreferences preferenceStore;

    @Test
    public void should_return_resolved_address() throws Exception {
        when(preferenceStore.get(BonitaPreferenceConstants.CONSOLE_HOST, BonitaPreferenceConstants.DEFAULT_HOST)).thenReturn("localhost");
        when(preferenceStore.getInt(BonitaPreferenceConstants.CONSOLE_PORT, BonitaPreferenceConstants.DEFAULT_PORT)).thenReturn(8080);
        final RestAPIAddressResolver restAPIAddressResolver = new RestAPIAddressResolver(preferenceStore);

        assertThat(restAPIAddressResolver.getAddress("myPath/template")).isEqualTo("http://localhost:8080/bonita/API/extension/myPath/template");
    }

    @Test
    public void should_return_empty_address_if_pathTemaplte_is_null_or_empty() throws Exception {
        final RestAPIAddressResolver restAPIAddressResolver = new RestAPIAddressResolver(preferenceStore);

        assertThat(restAPIAddressResolver.getAddress("")).isEmpty();
    }

}
