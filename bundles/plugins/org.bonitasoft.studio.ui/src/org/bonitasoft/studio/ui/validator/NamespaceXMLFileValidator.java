/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.validator;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class NamespaceXMLFileValidator implements IValidator {

    public static class Builder implements ValidatorBuilder<NamespaceXMLFileValidator> {

        private String message;
        private int severity = IStatus.ERROR;
        private String namespace;

        public Builder(String namespace) {
            this.namespace = namespace;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder warningLevel() {
            this.severity = IStatus.WARNING;
            return this;
        }

        public Builder infoLevel() {
            this.severity = IStatus.INFO;
            return this;
        }

        @Override
        public NamespaceXMLFileValidator create() {
            return new NamespaceXMLFileValidator(namespace, message, severity);
        }

    }

    private String message;
    private int severity;
    private String namespace;

    private NamespaceXMLFileValidator(String namespace, String message, int severity) {
        this.message = message;
        this.severity = severity;
        this.namespace = namespace;
    }

    @Override
    public IStatus validate(Object value) {
        try {
            File file = new File((String) value);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document document = dbf.newDocumentBuilder().parse(file);
            Element documentElement = document.getDocumentElement();
            if (!isValid(documentElement)) {
                switch (severity) {
                    case IStatus.ERROR:
                        return ValidationStatus.error(message);
                    case IStatus.WARNING:
                        return ValidationStatus.warning(message);
                    default:
                        return ValidationStatus.info(message);
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        return ValidationStatus.ok();
    }

    protected boolean isValid(Element documentElement) {
        return Objects.equals(documentElement.getNamespaceURI(), namespace);
    }
}
