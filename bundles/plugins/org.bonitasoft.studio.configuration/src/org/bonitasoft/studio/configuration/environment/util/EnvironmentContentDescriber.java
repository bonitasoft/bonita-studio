package org.bonitasoft.studio.configuration.environment.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.studio.configuration.environment.EnvironmentPackage;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.XMLContentDescriber;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

public class EnvironmentContentDescriber extends XMLContentDescriber {

	    @Override
	    public int describe(Reader input, IContentDescription description) throws IOException {
	        if (super.describe(input, description) == VALID) {
	            return validEnvironmentXML(CharStreams.toString(input));
	        }
	        return INVALID;
	    }

	    @Override
	    public int describe(InputStream input, IContentDescription description) throws IOException {
	        if (super.describe(input, description) == VALID) {
	            return validEnvironmentXML(new String(ByteStreams.toByteArray(input)));
	        }
	        return INVALID;
	    }

	    private int validEnvironmentXML(final String stringContent) throws IOException {
	        if (stringContent == null || stringContent.isEmpty()) {
	            return INVALID;
	        }
	        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setNamespaceAware(true);
	        dbf.setValidating(false);
	        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
	        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
	        try (InputStream is = new ByteArrayInputStream(stringContent.getBytes())) {
	            final Document document = dbf.newDocumentBuilder().parse(is);
	            final Element documentElement = document.getDocumentElement();
	            return EnvironmentPackage.eNS_URI.equals(documentElement.getNamespaceURI()) ? VALID : INVALID;
	        } catch (SAXException | ParserConfigurationException e) {
	            return stringContent.contains(EnvironmentPackage.eNS_URI) ? VALID : INVALID;
	        }
	    }
}
