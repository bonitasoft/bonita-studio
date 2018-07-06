/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.console.test.util;

import java.net.URL;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.Platform;

/**
 * @author Aurelien Pupier
 */
public class ProcessRegistry {

    public static MainProcess createBasicProcess() {
        MainProcess process = ProcessFactory.eINSTANCE.createMainProcess();
        process.setName("processName");
        process.setVersion("1.0");
        return process;
    }

    /**
     * @param name of the bar in the folder procExamplesTest
     * @return
     * @throws Exception
     */
    public static MainProcess getTestExampleProcess(String procFileName) throws Exception {
        URL fileUrl = Platform.getBundle("org.bonitasoft.studio.util.tests").getEntry("procExamplesTest/" + procFileName);
        DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore fileStore = store.importInputStream(procFileName, fileUrl.openStream());
        return fileStore.getContent();
    }


}
