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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.UIDesignerServerManager;
import org.bonitasoft.studio.designer.core.bar.BarResourceCreationException;
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceFactory;
import org.bonitasoft.studio.designer.core.bos.WebFormBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONException;

import com.google.common.io.ByteSource;

public class WebPageFileStore extends InFolderJSONFileStore implements IDeployable, WebResource {

    private WebFormBOSArchiveFileStoreProvider webFormBOSArchiveFileStoreProvider;

    private static final String ID_TYPE = "type";
    public static final String DISPLAY_NAME_KEY = "displayName";
    private static final String DESCRIPTION_KEY = "description";

    public static final String LAYOUT_TYPE = "layout";
    public static final String PAGE_TYPE = "page";
    public static final String DEPLOY_PAGE_COMMAND = "org.bonitasoft.studio.engine.deploy.page.command";

    private CustomPageBarResourceFactory customPageBarResourceFactory;

    public WebPageFileStore(final String fileName, final IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
        customPageBarResourceFactory = new CustomPageBarResourceFactory(PageDesignerURLFactory.INSTANCE);
    }

    public void setWebFormBOSArchiveFileStoreProvider(
            final WebFormBOSArchiveFileStoreProvider webFormBOSArchiveFileStoreProvider) {
        this.webFormBOSArchiveFileStoreProvider = webFormBOSArchiveFileStoreProvider;
    }

    @Override
    public Image getIcon() {
        return getParentStore().getIcon();
    }

    @Override
    protected IWorkbenchPart doOpen() {
        try {
            openBrowserOperation(urlFactory().openPage(getId())).execute();
        } catch (final MalformedURLException e) {
            BonitaStudioLog.error(String.format("Failed to open page %s", getId()), e);
        }
        return null;
    }

    @Override
    public Set<IRepositoryFileStore> getRelatedFileStore() {
        if (PlatformUtil.isHeadless() || !UIDesignerServerManager.getInstance().isStarted()) {
            return super.getRelatedFileStore();
        }
        if (webFormBOSArchiveFileStoreProvider != null) {
            try {
                return webFormBOSArchiveFileStoreProvider.getRelatedFileStore(this);
            } catch (BarResourceCreationException | IOException e) {
                BonitaStudioLog.error("Failed to retrieve page related file store", e);
            }
        }
        return super.getRelatedFileStore();
    }

    public String getType() {
        try {
            return getStringAttribute(ID_TYPE);
        } catch (final JSONException | ReadFileStoreException e) {
            BonitaStudioLog.error(
                    String.format("Failed to retrieve id in JSON file %s.json, with key %s.", getName(), ID_TYPE),
                    UIDesignerPlugin.PLUGIN_ID);
            return "page";
        }
    }

    @Override
    public String getDisplayName() {
        try {
            return getStringAttribute(DISPLAY_NAME_KEY);
        } catch (final JSONException | ReadFileStoreException e) {
            return super.getDisplayName();
        }

    }

    public String getDescription() {
        try {
            return getStringAttribute(DESCRIPTION_KEY);
        } catch (final JSONException | ReadFileStoreException e) {
            BonitaStudioLog.error(
                    String.format("Failed to retrieve id in JSON file %s.json, with key %s.", getName(), DESCRIPTION_KEY),
                    UIDesignerPlugin.PLUGIN_ID);
            return "";
        }
    }

    @Override
    public StyledString getStyledString() {
        StyledString styledString = new StyledString();
        String displayName = getDisplayName();
        String name = displayName == null || displayName.isEmpty() ? getName() : displayName;
        styledString.append(name);
        if (!Objects.equals(getCustomPageName(), name)) {
            styledString.append(String.format(" (%s)", getCustomPageName()), StyledString.COUNTER_STYLER);
        }
        String type = getType();
        if (type != null) {
            styledString.append(String.format(" %s", type.toUpperCase()), StyledString.QUALIFIER_STYLER);
        }
        return styledString;
    }

    @Override
    public void deploy() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", getName());
        executeCommand(DEPLOY_PAGE_COMMAND, parameters);
    }

    @Override
    public URI toURI() throws MalformedURLException, URISyntaxException {
        return urlFactory().page(getId()).toURI();
    }

    @Override
    public void build(IPath buildPath, IProgressMonitor monitor) throws CoreException {
        IPath webPageFolderPath = buildPath.append("webPage");
        IFolder webPageFolder = getRepository().getProject()
                .getFolder(webPageFolderPath.makeRelativeTo(getRepository().getProject().getFullPath()));
        if (!webPageFolder.exists()) {
            webPageFolder.create(true, true, new NullProgressMonitor());
        }
        monitor.subTask(String.format(Messages.buildingWebPage, getName()));
        try (InputStream inputStream = ByteSource.wrap(customPageBarResourceFactory.export(getId()))
                .openBufferedStream();) {
            IFile zipFile = webPageFolder.getFile(String.format("custompage_%s.zip", getCustomPageName()));
            zipFile.create(inputStream, true, new NullProgressMonitor());
        } catch (IOException e) {
            throw new CoreException(
                    ValidationStatus.error(String.format("An error occured while building %s", getName()), e));
        }
    }

}
