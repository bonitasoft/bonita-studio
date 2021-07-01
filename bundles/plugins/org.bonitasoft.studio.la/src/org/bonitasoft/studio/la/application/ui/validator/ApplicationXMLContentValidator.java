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
package org.bonitasoft.studio.la.application.ui.validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.xml.sax.SAXException;

public class ApplicationXMLContentValidator implements IValidator<String> {

    private ApplicationRepositoryStore store;

    public ApplicationXMLContentValidator(ApplicationRepositoryStore store) {
        this.store = store;
    }

    @Override
    public IStatus validate(String value) {
        try {
            IStatus status = validateVersion(value);
            if (!status.isOK()) {
                return status;
            }
            store.getConverter().unmarshallFromXML(Files.readAllBytes(Paths.get(value)));
        } catch (JAXBException | IOException | SAXException e) {
            return ValidationStatus.error(Messages.notAnApplicationError);
        }
        return ValidationStatus.ok();
    }

    private IStatus validateVersion(String filePath) throws IOException {
        File file = Paths.get(filePath).toFile();
        try (InputStream is = new FileInputStream(file)) {
            return store.validate(file.getName(), is);
        }
    }
}
