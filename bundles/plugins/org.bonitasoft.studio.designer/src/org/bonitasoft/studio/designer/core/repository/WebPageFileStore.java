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
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.net.HttpClientFactory;
import org.bonitasoft.studio.common.repository.model.IBuildable;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.bar.FormBuilder;
import org.bonitasoft.studio.designer.core.bar.RestFormBuilder;
import org.bonitasoft.studio.designer.core.exception.PageIncompatibleException;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteSource;

public class WebPageFileStore extends InFolderJSONFileStore
        implements IDeployable, IBuildable, WebResource, Comparable<WebPageFileStore> {

    private static final String ID_TYPE = "type";
    public static final String DISPLAY_NAME_KEY = "displayName";
    private static final String DESCRIPTION_KEY = "description";
    private static final String EXTENSION_RESOURCE_PREFIX = "|extension/";

    public static final String LAYOUT_TYPE = "layout";
    public static final String PAGE_TYPE = "page";
    public static final String FORM_TYPE = "form";
    public static final String DEPLOY_PAGE_COMMAND = "org.bonitasoft.studio.engine.deploy.page.command";
    private ObjectMapper objectMapper = new ObjectMapper();

    private FormBuilder formBuilder;

    public WebPageFileStore(final String fileName, final IRepositoryStore<? extends IRepositoryFileStore> parentStore) {
        super(fileName, parentStore);
        formBuilder = new RestFormBuilder(PageDesignerURLFactory.INSTANCE);
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

    public String getType() {
        try {
            return getStringAttribute(ID_TYPE);
        } catch (ReadFileStoreException e) {
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
        } catch (ReadFileStoreException e) {
            return super.getDisplayName();
        }
    }

    public String getDescription() {
        try {
            return getStringAttribute(DESCRIPTION_KEY);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(
                    String.format("Failed to retrieve id in JSON file %s.json, with key %s.", getName(),
                            DESCRIPTION_KEY),
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
    public void deployInUI() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", getName());
        parameters.put("disablePopup", Boolean.FALSE.toString());
        executeCommand(DEPLOY_PAGE_COMMAND, parameters);
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        options.put("name", getName());
        options.put("disablePopup", Boolean.TRUE.toString());
        Object result = executeCommand(DEPLOY_PAGE_COMMAND, options);
        return result instanceof IStatus ? (IStatus) result : ValidationStatus.ok();
    }

    @Override
    public URI toURI() throws MalformedURLException, URISyntaxException {
        return urlFactory().page(getId()).toURI();
    }

    @Override
    public IStatus build(IPath buildPath, IProgressMonitor monitor) {
        IPath webPageFolderPath = buildPath.append("webPage");
        IFolder webPageFolder = getRepository().getProject()
                .getFolder(webPageFolderPath.makeRelativeTo(getRepository().getProject().getLocation()));
        if (!webPageFolder.exists()) {
            try {
                webPageFolder.create(true, true, new NullProgressMonitor());
            } catch (CoreException e) {
                return e.getStatus();
            }
        }
        monitor.subTask(String.format(Messages.buildingWebPage, getName()));
        try (InputStream inputStream = ByteSource.wrap(formBuilder.export(getId()))
                .openBufferedStream();) {
            IFile zipFile = webPageFolder.getFile(String.format("custompage_%s.zip", getCustomPageName()));
            zipFile.create(inputStream, true, new NullProgressMonitor());
        } catch (PageIncompatibleException | IOException e) {
            return ValidationStatus.error(String.format("An error occured while building %s", getName()), e);
        } catch (CoreException e) {
            return e.getStatus();
        }
        return ValidationStatus.ok();
    }

    @Override
    public int compareTo(WebPageFileStore o) {
        if (o != null) {
            int res = getPriority().compareTo(o.getPriority());
            if (res != 0) {
                return res;
            }
            String myDisplayName = getDisplayName();
            String otherDisplayName = o.getDisplayName() ;
            String myName = myDisplayName!= null ? myDisplayName : getName();
            String otherName = otherDisplayName != null ? otherDisplayName : o.getName();
            return myName.compareTo(otherName);
        }
        return 0;
    }

    private Integer getPriority() {
        switch (getType()) {
            case FORM_TYPE:
                return 0;
            case PAGE_TYPE:
                return 1;
            case LAYOUT_TYPE:
                return 2;
            default:
                return 3;
        }
    }

    public Collection<String> getPageResources() {
        try {
            HttpResponse<InputStream> response = HttpClientFactory.INSTANCE.send(HttpRequest.newBuilder(PageDesignerURLFactory.INSTANCE.resources(getId()).toURI())
                    .GET().build(), BodyHandlers.ofInputStream());
            return response != null ? parseExtensionResources(response) : Collections.emptyList();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return Collections.emptyList();
        }
    }

    private Collection<String> parseExtensionResources(HttpResponse<InputStream> response) {
        if (response != null) {
            try(var is = response.body()){
                List<String> resources = objectMapper.readValue(is, new TypeReference<List<String>>() {
                });
                return resources.stream().filter(r -> r.contains(EXTENSION_RESOURCE_PREFIX)).collect(Collectors.toSet());
            } catch (IOException e) {
               BonitaStudioLog.error(e);
            }
        }
        return Set.of();
    }

}
