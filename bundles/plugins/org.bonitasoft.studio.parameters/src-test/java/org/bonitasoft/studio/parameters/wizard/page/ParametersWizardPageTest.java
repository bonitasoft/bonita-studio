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
package org.bonitasoft.studio.parameters.wizard.page;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain
 *
 */
public class ParametersWizardPageTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_isNameValid_return_null() throws Exception {
        final Parameter parameterWorkingCopy = ParameterFactory.eINSTANCE.createParameter();
        final Set<String> existingParameterNames = new HashSet<String>();
        existingParameterNames.add("myParam2");
        final ParametersWizardPage wizardPage = new ParametersWizardPage(parameterWorkingCopy, existingParameterNames);
        assertThat(wizardPage.isNameValid("myParam")).isNull();
    }

    @Test
    public void should_isNameValid_return_errormessage() throws Exception {
        final Parameter parameterWorkingCopy = ParameterFactory.eINSTANCE.createParameter();
        final Set<String> existingParameterNames = new HashSet<String>();
        existingParameterNames.add("myParam");
        final ParametersWizardPage wizardPage = new ParametersWizardPage(parameterWorkingCopy, existingParameterNames);
        assertThat(wizardPage.isNameValid("myParam")).isEqualTo(Messages.invalidName);
    }
}
