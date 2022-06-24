/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.exporter.bpmn.transfo.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.XMLDataBuilder.anXMLData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.ModelFactory;


/**
 * @author Romain Bioteau
 *
 */
public class XMLNamespaceResolverTest {

    private DocumentRoot documentRoot;
    private XMLNamespaceResolver xmlNamespaceResolver;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        documentRoot = ModelFactory.eINSTANCE.createDocumentRoot();
        xmlNamespaceResolver = new XMLNamespaceResolver(documentRoot);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void should_resolveNamespace_throw_NullPointerException_for_null_argument() throws Exception {
        xmlNamespaceResolver.resolveNamespacePrefix(null);
    }

    @Test
    public void should_resolveNamespace_create_default_namespaces() throws Exception {
        documentRoot.getXMLNSPrefixMap().put("aNullPrefix", null);
        documentRoot.getXMLNSPrefixMap().put("aPrefix", "http://www.myNS.com");

        assertThat(xmlNamespaceResolver.resolveNamespacePrefix(
                anXMLData()
                        .build())).isEqualTo("n0");

        assertThat(xmlNamespaceResolver.resolveNamespacePrefix(
                anXMLData()
                        .build())).isEqualTo("n1");
    }

    @Test
    public void should_resolveNamespace_retrieved_namespace_from_registry() throws Exception {
        documentRoot.getXMLNSPrefixMap().put("aPrefix", "http://www.myNS.com");

        assertThat(xmlNamespaceResolver.resolveNamespacePrefix(
                anXMLData()
                        .withNamespace("http://www.myNS.com")
                        .build())).isEqualTo("aPrefix");
    }

}
