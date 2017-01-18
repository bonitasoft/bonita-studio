/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.editor;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;
import org.eclipse.core.runtime.content.XMLContentDescriber;
import org.xml.sax.SAXException;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

public class ApplicationContentDescriber extends XMLContentDescriber implements ITextContentDescriber {

    private final ApplicationNodeContainerConverter converter = new ApplicationNodeContainerConverter();

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.content.XMLContentDescriber#describe(java.io.Reader, org.eclipse.core.runtime.content.IContentDescription)
     */
    @Override
    public int describe(Reader input, IContentDescription description) throws IOException {
        if (super.describe(input, description) == VALID) {
            try {
                converter.unmarshallFromXML(CharStreams.toString(input).getBytes());
                return VALID;
            } catch (JAXBException | SAXException e) {
                return INVALID;
            }
        }
        return INVALID;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.content.XMLContentDescriber#describe(java.io.InputStream, org.eclipse.core.runtime.content.IContentDescription)
     */
    @Override
    public int describe(InputStream input, IContentDescription description) throws IOException {
        if (super.describe(input, description) == VALID) {
            try {
                converter.unmarshallFromXML(ByteStreams.toByteArray(input));
                return VALID;
            } catch (JAXBException | SAXException e) {
                return INVALID;
            }
        }
        return INVALID;
    }
}
