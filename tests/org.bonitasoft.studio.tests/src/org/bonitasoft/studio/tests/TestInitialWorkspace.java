/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests;

import static org.junit.Assert.assertNotSame;

import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class TestInitialWorkspace {

    @Test
    public void testInitialWorkspaceContainsOrganization() throws Exception {
        OrganizationRepositoryStore store = (OrganizationRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class);
        assertNotSame(0, store.getChildren().size());
    }

    @Test
    public void testInitialWorkspaceContainsDefaultScript() throws Exception {
        ProvidedGroovyRepositoryStore store = (ProvidedGroovyRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ProvidedGroovyRepositoryStore.class);
        assertNotSame(0, store.getChildren().size());
    }

    @Test
    public void testInitialWorkspaceContainsXSD() throws Exception {
        XSDRepositoryStore store = (XSDRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(XSDRepositoryStore.class);
        assertNotSame(0, store.getChildren().size());
    }

}
