/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.eclipse.gmf.runtime.lite.svg;

import java.io.IOException;
import java.io.InterruptedIOException;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.xerces.parsers.SAXParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class CustomSAXSVGDocumentFactory extends SAXSVGDocumentFactory {


    public CustomSAXSVGDocumentFactory() {
        super(XMLResourceDescriptor.getXMLParserClassName());
    }

    /*
     * (non-Javadoc)
     * @see org.apache.batik.dom.util.SAXDocumentFactory#createDocument(org.xml.sax.InputSource)
     */
    @Override
    protected Document createDocument(InputSource is) throws IOException {
        try {
            final XMLReader parser = new SAXParser();

            parser.setContentHandler(this);
            parser.setDTDHandler(this);
            parser.setEntityResolver(this);
            parser.setErrorHandler((errorHandler == null) ? this : errorHandler);

            parser.setFeature("http://xml.org/sax/features/namespaces",
                    true);
            parser.setFeature("http://xml.org/sax/features/namespace-prefixes",
                    true);
            parser.setFeature("http://xml.org/sax/features/validation",
                    isValidating);
            parser.setProperty("http://xml.org/sax/properties/lexical-handler",
                    this);
            parser.parse(is);
        } catch (final SAXException e) {
            final Exception ex = e.getException();
            if (ex != null && ex instanceof InterruptedIOException) {
                throw (InterruptedIOException) ex;
            }
            throw new IOException(e.getMessage());
        }

        currentNode = null;
        final Document ret = document;
        document = null;
        locator = null;
        return ret;
    }
}
