/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard.validator;

import java.util.Iterator;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;


public class AllJavaIdentifierValidator extends MultiValidator {

    private final IObservableList input;

    public AllJavaIdentifierValidator(IObservableList input) {
        this.input = input;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.databinding.validation.MultiValidator#validate()
     */
    @Override
    protected IStatus validate() {
        final Iterator iterator = input.iterator();
        while (iterator.hasNext()) {
            final String parameter = (String) iterator.next();
            final IStatus status = JavaConventions.validateIdentifier(parameter, JavaCore.VERSION_1_7, JavaCore.VERSION_1_7);
            if (!status.isOK()) {
                return status;
            }
        }
        return ValidationStatus.ok();
    }

}
