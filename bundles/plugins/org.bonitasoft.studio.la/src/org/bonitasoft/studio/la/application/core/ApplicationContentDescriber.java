/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
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
     * @see org.eclipse.core.runtime.content.XMLContentDescriber#describe(java.io.Reader, org.eclipse.core.runtime.content.IContentDescription)
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
     * @see org.eclipse.core.runtime.content.XMLContentDescriber#describe(java.io.InputStream, org.eclipse.core.runtime.content.IContentDescription)
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
