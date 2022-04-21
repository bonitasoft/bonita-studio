/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages;

import java.io.File;

import org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapTool;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * @author Maxence Raoux
 */
public abstract class AbstractPage extends GeneratedConnectorWizardPage {

    protected Boolean libraryLoaded;
    protected SapTool sapTool;

    public AbstractPage() {
        super(AbstractPage.class.getName());
        libraryLoaded = defineIfLibraryLoaded();
        sapTool = null;
    }

    private Boolean defineIfLibraryLoaded() {
        try {
            final File tomcatLibFolder = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(),
                    "tomcat" + File.separatorChar + "server" + File.separatorChar + "lib");
            final File endorsedFileEngine = new File(tomcatLibFolder, "sapjco3.jar");
            return Class.forName("com.sap.conn.jco.JCoFunction") != null && endorsedFileEngine.exists();
        } catch (final ClassNotFoundException | NoClassDefFoundError e) {
            return false;
        }
    }
}
