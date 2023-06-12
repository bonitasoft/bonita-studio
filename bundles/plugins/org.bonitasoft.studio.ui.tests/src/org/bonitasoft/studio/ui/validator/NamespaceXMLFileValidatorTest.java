package org.bonitasoft.studio.ui.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.w3c.dom.Element;

public class NamespaceXMLFileValidatorTest {

    @Test
    public void should_accept_valid_application_file() {
        Element element = mock(Element.class);
        when(element.getNamespaceURI()).thenReturn("valid_namespace");

        NamespaceXMLFileValidator validator = new NamespaceXMLFileValidator.Builder("valid_namespace")
                .create();

        assertTrue(validator.isValid(element));
    }

    @Test
    public void should_not_accept_valid_application_file() {
        Element element = mock(Element.class);
        when(element.getNamespaceURI()).thenReturn("invalid_namespace");

        NamespaceXMLFileValidator validator = new NamespaceXMLFileValidator.Builder("valid_namespace")
                .create();

        assertFalse(validator.isValid(element));
    }
}
