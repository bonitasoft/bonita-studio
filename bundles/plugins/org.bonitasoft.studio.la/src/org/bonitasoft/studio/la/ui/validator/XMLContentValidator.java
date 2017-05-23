/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.ui.validator;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.xml.sax.SAXException;

public abstract class XMLContentValidator implements IValidator {

    private String errorMessage;

    public XMLContentValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public IStatus validate(Object value) {
        try {
            validateModel((String) value);
        } catch (JAXBException | IOException | SAXException e) {
            return ValidationStatus.error(errorMessage);
        }
        return ValidationStatus.ok();
    }

    protected abstract void validateModel(String value) throws JAXBException, IOException, SAXException;
}
