/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.core.repository;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.model.PostMigrationOperationCollector;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.designer.core.operation.IndexingUIDOperation;
import org.bonitasoft.studio.designer.core.operation.MigrateUIDOperation;
import org.bonitasoft.studio.designer.core.resources.WorkspaceServerResource;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;
import org.json.JSONException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class WebPageRepositoryStore extends WebArtifactRepositoryStore<WebPageFileStore> {

    private static final String PAGE_ICON_PATH = "page.png";
    private static final Set<String> extensions = new HashSet<>();
    public static final String JSON_EXTENSION = "json";
    public static final String WEB_FORM_REPOSITORY_NAME = "web_page";

    static {
        extensions.add(JSON_EXTENSION);
    }

    @Override
    public String getName() {
        return WEB_FORM_REPOSITORY_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.formRepository;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(PAGE_ICON_PATH, UIDesignerPlugin.getDefault());
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    public WebPageFileStore createRepositoryFileStore(final String fileName) {
        WebPageFileStore webPageFileStore = null;
        IFolder folder = getResource().getFolder(fileName);
        if (folder.exists()) {
            webPageFileStore = folder.getFile(fileName + ".json").exists() ? new WebPageFileStore(fileName, this)
                    : null;
            if (webPageFileStore == null) {
                return null;
            }
            return webPageFileStore;
        }
        webPageFileStore = new WebPageFileStore(fileName, this);
        return webPageFileStore;
    }

    public Optional<WebPageFileStore> findByPageId(String pageId) {
        return getChildren().stream()
                .filter(fStore -> Objects.equals(pageId, "custompage_" + fStore.getCustomPageName()))
                .findFirst();
    }

    @Override
    public WebPageFileStore getChild(String uuid, boolean force) {
        IPath location = getResource().getLocation();
        if (location != null) {
            if (!PageUUIDResolver.indexFile(location.toFile()).exists() 
                    && UIDesignerServerManager.getInstance().isStarted()) {
                try {
                    new IndexingUIDOperation().run(AbstractRepository.NULL_PROGRESS_MONITOR);
                } catch (InvocationTargetException | InterruptedException e) {
                    BonitaStudioLog.error(e);
                }
            }
            String id = new PageUUIDResolver(location.toFile()).resolveUUID(uuid);
            WebPageFileStore page = super.getChild(id, force);
            if (page == null) {
                return super.getChild(uuid, force);
            }
            return page;
        }
        return null;
    }

    public String getDisplayNameFor(String uuid) {
        IPath location = getResource().getLocation();
        if (location != null) {
            File pageFolder = location.toFile();
            String id = new PageUUIDResolver(pageFolder).resolveUUID(uuid);
            return Stream.of(pageFolder.listFiles())
                    .filter(file -> file.getName().equals(id))
                    .map(file -> new File(file, file.getName() + ".json"))
                    .map(file -> {
                        try {
                            return new org.json.JSONObject(Files.toString(file, Charsets.UTF_8));
                        } catch (JSONException | IOException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .map(json -> {
                        try {
                            return json.getString("name");
                        } catch (JSONException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse("");
        }
        return null;
    }

    @Override
    public void migrate(PostMigrationOperationCollector postMigrationOperationCollector, IProgressMonitor monitor) throws CoreException, MigrationException {
        if (UIDesignerServerManager.getInstance().isStarted()) {
            try {
                WorkspaceServerResource.disable();
                MigrateUIDOperation migrateUIDOperation = new MigrateUIDOperation();
                migrateUIDOperation.run(monitor);
                if (Objects.equals(migrateUIDOperation.getStatus().getSeverity(), IStatus.ERROR)) {
                    throw new MigrationException(Messages.UIDMigrationFailedMessage, new Exception());
                }
            } catch (InvocationTargetException | InterruptedException e) {
                throw new MigrationException(e);
            }finally {
                WorkspaceServerResource.enable();
            }
        }
    }


}
