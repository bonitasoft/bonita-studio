/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.core.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageValidationRuleTest {

    @Mock
    private MessageValidationRule messageValidationRule;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        when(messageValidationRule.getMessage(any(IStatus.class))).thenCallRealMethod();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getMessage_reutrns_status_message() throws Exception {
        assertThat(messageValidationRule.getMessage(ValidationStatus.ok())).isEqualTo(ValidationStatus.ok().getMessage());
    }

    @Test(expected=IllegalArgumentException.class)
    public void should_getMessage_throws_IllegalArgumentException() throws Exception {
        messageValidationRule.getMessage(null);
    }

}
