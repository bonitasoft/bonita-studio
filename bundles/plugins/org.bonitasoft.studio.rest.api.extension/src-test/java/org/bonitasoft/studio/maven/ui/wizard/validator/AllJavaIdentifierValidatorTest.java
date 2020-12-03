/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard.validator;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.maven.ui.wizard.validator.AllJavaIdentifierValidator;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.runtime.IStatus;
import org.junit.Rule;
import org.junit.Test;

public class AllJavaIdentifierValidatorTest {

    @Rule
    public RealmWithDisplay displayRule = new RealmWithDisplay();

    @Test
    public void should_only_accept_valid_java_identifiers() throws Exception {
        final IObservableList input = new WritableList();

        final MultiValidator validator = new AllJavaIdentifierValidator(input);
        input.add("invalid field name");
        StatusAssert.assertThat((IStatus) validator.getValidationStatus().getValue()).isNotOK();

        input.remove("invalid field name");
        input.add("validFieldName");
        StatusAssert.assertThat((IStatus) validator.getValidationStatus().getValue()).isOK();
    }

}
