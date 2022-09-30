/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.customPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.la.application.core.BonitaPagesRegistry;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

public class CustomPageProvider implements IResourceChangeListener {

    public static final CustomPageDescriptor DEFAULT_THEME = new CustomPageDescriptor(
            CustomPageDescriptor.BONITA_THEME_ID,
            "Bonita theme", null);

    private final WebPageRepositoryStore webPageStore;
    private ThemeRepositoryStore themeStore;
    private List<CustomPageDescriptor> pages;
    private List<CustomPageDescriptor> layouts;
    private List<CustomPageDescriptor> themes;

    public CustomPageProvider(WebPageRepositoryStore store, ThemeRepositoryStore themeStore) {
        this.webPageStore = store;
        this.themeStore = themeStore;
    }

    public synchronized List<CustomPageDescriptor> getLayouts() {
        if (layouts == null) {
            layouts = new ArrayList<>();
            layouts.add(CustomPageDescriptor.DEFAULT_LAYOUT);
            webPageStore.getChildren()
                    .stream()
                    .filter(webPageFileStore -> Objects.equals(webPageFileStore.getType(),
                            WebPageFileStore.LAYOUT_TYPE))
                    .map(fileStore -> {
                        return new CustomPageDescriptor(
                                CustomPageDescriptor.CUSTOMPAGE_PREFIX + fileStore.getCustomPageName(),
                                IDisplayable.toDisplayName(fileStore).orElse(""), fileStore.getDescription());
                    })
                    .forEach(layouts::add);
        }
        return layouts;
    }

    public synchronized List<CustomPageDescriptor> getApplicationPages() {
        if (pages == null) {
            pages = Stream.concat(webPageStore.getChildren()
                    .stream()
                    .filter(webPageFileStore -> Objects.equals(webPageFileStore.getType(), WebPageFileStore.PAGE_TYPE))
                    .map(fileStore -> {
                        return new CustomPageDescriptor(
                                CustomPageDescriptor.CUSTOMPAGE_PREFIX + fileStore.getCustomPageName(),
                                IDisplayable.toDisplayName(fileStore).orElse(""), fileStore.getDescription());
                    }),
                    BonitaPagesRegistry.getInstance().getCustomPages().stream()
                            .map(p -> new CustomPageDescriptor(p.getPageId(), p.getDisplayName(), p.getDescription())))
                    .collect(Collectors.toList());
        }
        return pages;
    }

    public static String fromIdToName(String id) {
        return id.startsWith(CustomPageDescriptor.CUSTOMPAGE_PREFIX)
                ? id.substring(CustomPageDescriptor.CUSTOMPAGE_PREFIX.length()) : id;
    }

    public static String fromNameToId(String name) {
        return CustomPageDescriptor.CUSTOMPAGE_PREFIX + name;
    }

    public static boolean isDefaultLayout(String layoutId) {
        return CustomPageDescriptor.DEFAULT_LAYOUT_ID.equals(layoutId);
    }

    public static boolean isDefaultTheme(String themeId) {
        return CustomPageDescriptor.BONITA_THEME_ID.equals(themeId);
    }

    public static IConverter<String, String> getCustomPageIdConverter(Collection<CustomPageDescriptor> layouts) {
        return IConverter.<String, String> create(id -> findById(layouts, id).getId());
    }

    public static IConverter<String, CustomPageDescriptor> getCustomPageDescriptorConverter(
            Collection<CustomPageDescriptor> layouts) {
        return IConverter.<String, CustomPageDescriptor> create(id -> findById(layouts, id));
    }

    public static IConverter<String, String> getCustomPageDisplayNameConverter(
            Collection<CustomPageDescriptor> layouts) {
        return IConverter.<String, String> create(id -> findById(layouts, id).getDisplayName());
    }

    public static CustomPageDescriptor findById(Collection<CustomPageDescriptor> customPages,
            String id) {
        final Optional<CustomPageDescriptor> layout = customPages.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
        return layout.orElse(new CustomPageDescriptor(id, id, ""));
    }

    public WebPageRepositoryStore getWebPageStore() {
        return webPageStore;
    }

    public synchronized Collection<CustomPageDescriptor> getThemes() {
        if (themes == null) {
            themes = new ArrayList<>();
            themeStore.getChildren()
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(fileStore -> fileStore.getPageId() != null)
                    .map(fileStore -> new CustomPageDescriptor(
                            fileStore.getPageId(),
                            fileStore.getPageDisplayName(),
                            fileStore.getDescription()))
                    .forEach(themes::add);
            themes.add(DEFAULT_THEME);
        }
        return themes;
    }

    public ThemeRepositoryStore getThemeStore() {
        return themeStore;
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        try {
            IResourceDelta delta = event.getDelta();
            if (delta != null) {
                delta.accept(new IResourceDeltaVisitor() {

                    @Override
                    public boolean visit(IResourceDelta delta) throws CoreException {
                        IResource resource = delta.getResource();
                        if (resource != null && resource.getLocation() != null
                                && webPageStore.getResource().getLocation().isPrefixOf(resource.getLocation())) {
                            pages = null;
                            layouts = null;
                            return false;
                        }
                        if (resource != null && resource.getLocation() != null
                                && themeStore.getResource().getLocation().isPrefixOf(resource.getLocation())) {
                            cleanThemes();
                            return false;
                        }
                        return true;
                    }
                });
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    public void cleanThemes() {
        this.themes = null;
    }

    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    }

    public void init() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
    }

}
