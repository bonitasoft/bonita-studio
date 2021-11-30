/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.extension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.swt.widgets.Display;

@Creatable
public class ApplicationCollector {

    private RepositoryAccessor repositoryAccessor;
    private ExceptionDialogHandler exceptionDialogHandler;

    @Inject
    public ApplicationCollector(RepositoryAccessor repositoryAccessor, ExceptionDialogHandler exceptionDialogHandler) {
        this.repositoryAccessor = repositoryAccessor;
        this.exceptionDialogHandler = exceptionDialogHandler;
    }

    public Map<ApplicationFileStore, List<String>> findApplications(String themeId) {
        ApplicationRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class);
        var apps = new HashMap<ApplicationFileStore, List<String>>();
        for (ApplicationFileStore fileStore : repositoryStore.getChildren()) {
            try {
                Display.getDefault().syncExec(fileStore::saveOpenedEditor);
                ApplicationNodeContainer appNodeContainer = fileStore.getContent();
                appNodeContainer.getApplications().stream()
                        .filter(app -> Objects.equals(app.getTheme(), themeId))
                        .map(ApplicationNode::getToken)
                        .forEach(token -> apps.compute(fileStore, (key, value) -> {
                            var tokenList = value != null ? value : new ArrayList<String>();
                            tokenList.add(token);
                            return tokenList;
                        }));
            } catch (ReadFileStoreException e) {
                exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
            }
        }
        return apps;
    }

}
