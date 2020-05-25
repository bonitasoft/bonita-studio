/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.document.core.repository.DocumentRepositoryStore;

/**
 * Usefull methods through STudio API.
 *
 * @author Joachim Segala
 */
public class StudioAPIUtil {

    public static void importDocumentInBonitaStudioRepository(final String pFilePath) {
        final File newFile = new File(pFilePath);
        FileInputStream inputStream = null;
        try {
            final DocumentRepositoryStore ars = RepositoryManager.getInstance()
                    .getRepositoryStore(DocumentRepositoryStore.class);
            inputStream = new FileInputStream(newFile);
            ars.importInputStream(newFile.getName(), inputStream);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
