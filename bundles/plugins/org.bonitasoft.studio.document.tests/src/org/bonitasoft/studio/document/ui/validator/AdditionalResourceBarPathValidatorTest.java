package org.bonitasoft.studio.document.ui.validator;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.model.process.AdditionalResource;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Before;
import org.junit.Test;

public class AdditionalResourceBarPathValidatorTest {

    private static final String CURRENT_BAR_PATH = "toto.txt";
    private static final String USED_BAR_PATH = "tutu.txt";
    private static final String FREE_BAR_PATH = "titi.txt";

    AdditionalResourceBarPathValidator validator;

    @Before
    public void init() {
        Pool pool = ProcessFactory.eINSTANCE.createPool();
        AdditionalResource additionalResource = ProcessFactory.eINSTANCE.createAdditionalResource();
        AdditionalResource otherAdditionalResource = ProcessFactory.eINSTANCE.createAdditionalResource();
        additionalResource.setName(CURRENT_BAR_PATH);
        otherAdditionalResource.setName(USED_BAR_PATH);
        pool.getAdditionalResources().add(additionalResource);
        pool.getAdditionalResources().add(otherAdditionalResource);
        validator = new AdditionalResourceBarPathValidator(pool, additionalResource);
    }

    @Test
    public void should_return_error_for_empty_bar_path() {
        StatusAssert.assertThat(validator.validate(null)).isError();
        StatusAssert.assertThat(validator.validate("")).isError();
    }

    @Test
    public void should_validate_unicity() {
        StatusAssert.assertThat(validator.validate(FREE_BAR_PATH)).isOK();
        StatusAssert.assertThat(validator.validate(CURRENT_BAR_PATH)).isOK();
        StatusAssert.assertThat(validator.validate(USED_BAR_PATH)).isError();
    }

}
