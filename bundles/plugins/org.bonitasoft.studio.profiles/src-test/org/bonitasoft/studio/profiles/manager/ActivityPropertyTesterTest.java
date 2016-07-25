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
package org.bonitasoft.studio.profiles.manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ActivityPropertyTesterTest {

    @Mock
    private IPreferenceStore bonitaPrefStore;
    private ActivityPropertyTester tester;

    @Before
    public void setup() {
        tester = spy(new ActivityPropertyTester());
        doReturn(bonitaPrefStore).when(tester).getBonitaPreferenceStore();
    }

    @Test
    public void test6XModeEnabled() {
        doReturn(true).when(bonitaPrefStore).getBoolean(BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE);

        final boolean test = tester.test(null, ActivityPropertyTester.PROPERTY_IS_6XLEGACY_ENABLED, new String[] {}, null);

        assertThat(test).isTrue();
    }

    @Test
    public void test6XModeNotEnabled() {
        doReturn(false).when(bonitaPrefStore).getBoolean(BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE);

        final boolean test = tester.test(null, ActivityPropertyTester.PROPERTY_IS_6XLEGACY_ENABLED, new String[] {}, null);

        assertThat(test).isFalse();
    }

}
