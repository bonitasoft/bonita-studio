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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.validator.XMLContentValidator;
import org.xml.sax.SAXException;

public class ApplicationXMLContentValidator extends XMLContentValidator {

    private ApplicationNodeContainerConverter applicationNodeContainerConverter;

    public ApplicationXMLContentValidator(String errorMessage) {
        super(errorMessage);
        this.applicationNodeContainerConverter = RepositoryManager.getInstance()
                .getRepositoryStore(ApplicationRepositoryStore.class).getConverter();

    }

    @Override
    protected void validateModel(String value) throws JAXBException, IOException, SAXException {
        applicationNodeContainerConverter.unmarshallFromXML(Files.readAllBytes(Paths.get(value)));
    }
}
