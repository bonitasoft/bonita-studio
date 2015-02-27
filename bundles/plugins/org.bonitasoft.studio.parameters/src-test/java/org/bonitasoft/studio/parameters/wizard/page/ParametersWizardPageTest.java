/**
 * 
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
        Parameter parameterWorkingCopy = ParameterFactory.eINSTANCE.createParameter();
        Set<String> existingParameterNames = new HashSet<String>();
        existingParameterNames.add("myParam2");
        ParametersWizardPage wizardPage = new ParametersWizardPage(parameterWorkingCopy, existingParameterNames);
        assertThat(wizardPage.isNameValid("myParam")).isNull();
    }

    @Test
    public void should_isNameValid_return_errormessage() throws Exception {
        Parameter parameterWorkingCopy = ParameterFactory.eINSTANCE.createParameter();
        Set<String> existingParameterNames = new HashSet<String>();
        existingParameterNames.add("myParam");
        ParametersWizardPage wizardPage = new ParametersWizardPage(parameterWorkingCopy, existingParameterNames);
        assertThat(wizardPage.isNameValid("myParam")).isEqualTo(Messages.invalidName);
    }
}
