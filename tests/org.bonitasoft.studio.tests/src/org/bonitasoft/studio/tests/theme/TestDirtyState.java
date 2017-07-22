/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.theme;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.theme.model.Binding;

import junit.framework.TestCase;

/**
 * @author Romain Bioteau
 */
public class TestDirtyState extends TestCase {

    public void testBindingMap() throws Exception {
        Binding b1 = new Binding("b1", "description for b1", "#b1", "toto.css");
        Binding b2 = new Binding("b1", "description for b1", "#b1", "toto.css");
        Binding b3 = new Binding("b3", "description for b3", "#b3", "toto.css");
        assertEquals(b1, b2);
        assertTrue(!b1.equals(b3));

        Map<String, Binding> bindings1 = new HashMap<String, Binding>();
        bindings1.put("b", b1);
        Map<String, Binding> bindings2 = new HashMap<String, Binding>();
        bindings2.put("b", b2);

        assertEquals(bindings1, bindings2);

        Map<String, Binding> bindings3 = new HashMap<String, Binding>();
        bindings1.put("b", b3);

        assertFalse(bindings1.equals(bindings3));
    }

}
