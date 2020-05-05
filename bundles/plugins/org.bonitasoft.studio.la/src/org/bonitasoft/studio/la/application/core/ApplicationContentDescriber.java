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
package org.bonitasoft.studio.la.application.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;
import org.eclipse.core.runtime.content.XMLContentDescriber;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

public class ApplicationContentDescriber extends XMLContentDescriber implements ITextContentDescriber {

    private static final String APPLICATION_NS = "http://documentation.bonitasoft.com/application-xml-schema/1.0";

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.content.XMLContentDescriber#describe(java.io.Reader,
     * org.eclipse.core.runtime.content.IContentDescription)
     */
    @Override
    public int describe(Reader input, IContentDescription description) throws IOException {
        if (super.describe(input, description) == VALID) {
            return validApplicationXML(CharStreams.toString(input));
        }
        return INVALID;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.runtime.content.XMLContentDescriber#describe(java.io.InputStream,
     * org.eclipse.core.runtime.content.IContentDescription)
     */
    @Override
    public int describe(InputStream input, IContentDescription description) throws IOException {
        if (super.describe(input, description) == VALID) {
            return validApplicationXML(new String(ByteStreams.toByteArray(input)));
        }
        return INVALID;
    }

    private int validApplicationXML(final String stringContent) throws IOException {
        if (stringContent == null || stringContent.isEmpty()) {
            return INVALID;
        }
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try (InputStream is = new ByteArrayInputStream(stringContent.getBytes())) {
            final Document document = dbf.newDocumentBuilder().parse(is);
            final Element documentElement = document.getDocumentElement();
            return APPLICATION_NS.equals(documentElement.getNamespaceURI()) ? VALID : INVALID;
        } catch (SAXException | ParserConfigurationException e) {
            return stringContent.contains(APPLICATION_NS) ? VALID : INVALID;
        }
    }
}
