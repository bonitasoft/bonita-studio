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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.bonitasoft.studio.browser.operation.OpenBrowserOperation;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.bar.BarResourceCreationException;
import org.bonitasoft.studio.designer.core.bos.WebFormBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.json.JSONException;

/**
 * @author Romain Bioteau
 */
public class WebPageFileStore extends InFolderJSONFileStore {

    private WebFormBOSArchiveFileStoreProvider webFormBOSArchiveFileStoreProvider;

    private static final String ID_TYPE = "type";

    public static final String LAYOUT_TYPE = "layout";
    public static final String PAGE_TYPE = "page";

    public WebPageFileStore(final String fileName, final IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
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

    protected OpenBrowserOperation openBrowserOperation(final URL url) throws MalformedURLException {
        return new OpenBrowserOperation(url);
    }

    protected PageDesignerURLFactory urlFactory() {
        return new PageDesignerURLFactory(InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID));
    }

    @Override
    public Set<IRepositoryFileStore> getRelatedFileStore() {
        if (PlatformUtil.isHeadless()) {
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
}
