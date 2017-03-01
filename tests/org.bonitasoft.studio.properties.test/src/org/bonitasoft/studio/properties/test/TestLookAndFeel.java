/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.properties.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore.ResourceType;
import org.bonitasoft.studio.properties.sections.lookandfeel.LookAndFeelPropertySection;
import org.eclipse.core.resources.ResourcesPlugin;

import junit.framework.TestCase;

/**
 * @author Maxence Raoux
 */
public class TestLookAndFeel extends TestCase {

    @SuppressWarnings("static-access")
    public void testDownloadDefaultTemplate() {
        LookAndFeelPropertySection tester = new LookAndFeelPropertySection();
        ResourceType t = ApplicationResourceFileStore.ResourceType.PROCESS_TEMPLATE;
        final String fileName = "process.html";
        assertNotNull(
                "ResourceType PORCESS_TEMPLATE not exist anymore, but this test is based on it",
                t);

        tester.downloadDefaultTemplate(t, ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().getAbsolutePath()
                + File.separatorChar + fileName);
        final File createdFile = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().getAbsolutePath()
                + File.separatorChar + fileName);
        createdFile.deleteOnExit();
        assertNotNull("File " + fileName + " is asked to be downloaded but is not", createdFile);

        try {
            @SuppressWarnings("unused")
            FileInputStream fromIn = new FileInputStream(createdFile);
        } catch (FileNotFoundException e) {
            BonitaStudioLog.log(e.getMessage());
            fail("File " + fileName + " were not present in the destination folder");
        }
    }
}
