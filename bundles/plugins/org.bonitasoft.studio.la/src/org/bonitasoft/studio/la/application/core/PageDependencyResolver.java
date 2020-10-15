/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.core;

import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceFactory;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.operation.DeployPageRunnable;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class PageDependencyResolver {

    private static final String DEFAULT_LAYOUT_ID = "custompage_layoutBonita";
    private static final String LEGACY_DEFAULT_LAYOUT_ID = "custompage_defaultlayout";
    private final WebPageRepositoryStore store;
    private final PageAPI pageApi;
    private BonitaPagesRegistry pageRegistry;

    public PageDependencyResolver(WebPageRepositoryStore store,
            PageAPI pageApi,
            BonitaPagesRegistry pageRegistry) {
        this.store = store;
        this.pageApi = pageApi;
        this.pageRegistry = pageRegistry;
    }

    public Stream<IRunnableWithStatus> prepareDeployOperation(Stream<String> pages) {
        return pages
                .distinct()
                .filter(pageId -> !DEFAULT_LAYOUT_ID.equals(pageId) && !LEGACY_DEFAULT_LAYOUT_ID.equals(pageId))
                .filter(pageId -> {
                    Optional<EntryPage> page = pageRegistry.getPage(pageId);
                    return !page.isPresent() || page.filter(EntryPage::isCustom).isPresent();
                })
                .map(this::newDeployPageOperation);
    }

    private IRunnableWithStatus newDeployPageOperation(String pageId) {
        final Optional<WebPageFileStore> fStore = store.findByPageId(pageId);
        if (fStore.isPresent()) {
            return new DeployPageRunnable(pageApi,
                    new HttpClientFactory(),
                    customPageBarResourceFactory(),
                    fStore.get());
        }
        return new UnknownDeployStatus(String.format(Messages.unknownPageDeployStatus, pageId));
    }

    private CustomPageBarResourceFactory customPageBarResourceFactory() {
        return new CustomPageBarResourceFactory(new PageDesignerURLFactory(
                InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID)));
    }

}
