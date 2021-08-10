/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer;

import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImporterPriorityDisplayComparatorTest {

    private ImporterFactory if1;
    private ImporterFactory if2;

    @Before
    public void setUp() throws Exception {
        if1 = new ImporterFactory() {

            @Override
            public ToProcProcessor createProcessor(final String resourceName) {
                return null;
            }

            @Override
            public boolean appliesTo(final String resourceName) {
                return false;
            }

            @Override
            public int getPriorityDisplay() {
                return 1;
            }
        };

        if2 = new ImporterFactory() {

            @Override
            public ToProcProcessor createProcessor(final String resourceName) {
                return null;
            }

            @Override
            public boolean appliesTo(final String resourceName) {
                return false;
            }

            @Override
            public int getPriorityDisplay() {
                return 2;
            }
        };
    }

    @Test
    public void testCompareViewerObject() {
        Assert.assertTrue("", new ImporterPriorityDisplayComparator().compare(null, if1, if2) < 0);
        Assert.assertTrue("Two ImporterFactor with same priority shoudl return 0",
                new ImporterPriorityDisplayComparator().compare(null, if1, if1) == 0);
        Assert.assertTrue("", new ImporterPriorityDisplayComparator().compare(null, if2, if1) > 0);
        Assert.assertTrue("If wrong parameter is provided, it shoudl return equal by default",
                new ImporterPriorityDisplayComparator().compare(null, 3, if1) == 0);
        Assert.assertTrue("If wrong parameter is provided, it shoudl return equal by default",
                new ImporterPriorityDisplayComparator().compare(null, if1, 3) == 0);
    }

}
