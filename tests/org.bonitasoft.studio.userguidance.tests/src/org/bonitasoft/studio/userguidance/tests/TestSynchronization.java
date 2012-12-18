/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.userguidance.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.bonitasoft.studio.userguidance.wizards.DocSynchronizer;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class TestSynchronization  {

    @Test
    @Ignore
    public void testDocSynchronization() throws Exception {
        long timestamp = System.currentTimeMillis() ;
        new DocSynchronizer().run(new NullProgressMonitor()) ;
        assertTrue("A doc folder should exists",!DocSynchronizer.getLatestDocRoot().isEmpty()) ;
        File docFolder = new File(DocSynchronizer.getLatestDocRoot()) ;
        assertTrue("The doc folder file doesn't exists",docFolder.exists());
        System.out.println("new doc last modified: "+docFolder.lastModified());
        System.out.println("start time of the test: "+timestamp);
        assertTrue("Update ad failed", docFolder.lastModified() >= timestamp);
        assertTrue("The doc folder is empty",docFolder.listFiles().length > 0);
    }

}
