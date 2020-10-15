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

import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.la.application.core.BonitaPagesRegistry;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.eclipse.core.databinding.conversion.IConverter;

public class CustomPageProvider {

    public static final List<CustomPageDescriptor> DEFAULT_THEMES = new ArrayList<>();
    static {
        DEFAULT_THEMES.add(CustomPageDescriptor.DEFAULT_THEME);
        DEFAULT_THEMES.add(new CustomPageDescriptor(CustomPageDescriptor.BONITA_THEME_ID, "Bonita theme", null));
        DEFAULT_THEMES.add(new CustomPageDescriptor("custompage_superherotheme", "Superhero theme", null));
        DEFAULT_THEMES.add(new CustomPageDescriptor("custompage_simplextheme", "Simplex theme", null));
        DEFAULT_THEMES.add(new CustomPageDescriptor("custompage_readabletheme", "Readable theme", null));
        DEFAULT_THEMES.add(new CustomPageDescriptor("custompage_flatlytheme", "Flatly theme", null));
        DEFAULT_THEMES.add(new CustomPageDescriptor("custompage_cosmotheme", "Cosmo theme", null));
    }

    private final WebPageRepositoryStore webPageStore;
    private ThemeRepositoryStore themeStore;

    public CustomPageProvider(WebPageRepositoryStore store, ThemeRepositoryStore themeStore) {
        this.webPageStore = store;
        this.themeStore = themeStore;
    }

    public List<CustomPageDescriptor> getLayouts() {
        final List<CustomPageDescriptor> layouts = new ArrayList<>();
        layouts.add(CustomPageDescriptor.DEFAULT_LAYOUT);
        webPageStore.getChildren()
                .stream()
                .filter(webPageFileStore -> Objects.equals(webPageFileStore.getType(), WebPageFileStore.LAYOUT_TYPE))
                .map(fileStore -> new CustomPageDescriptor(
                        CustomPageDescriptor.CUSTOMPAGE_PREFIX + fileStore.getCustomPageName(),
                        fileStore.getDisplayName(), fileStore.getDescription()))
                .forEach(layouts::add);
        layouts.add(CustomPageDescriptor.LEGACY_DEFAULT_LAYOUT);
        return layouts;
    }

    public List<CustomPageDescriptor> getApplicationPages() {
        final List<CustomPageDescriptor> pages = new ArrayList<>();
        webPageStore.getChildren()
                .stream()
                .filter(webPageFileStore -> Objects.equals(webPageFileStore.getType(), WebPageFileStore.PAGE_TYPE))
                .map(fileStore -> new CustomPageDescriptor(
                        CustomPageDescriptor.CUSTOMPAGE_PREFIX + fileStore.getCustomPageName(),
                        fileStore.getDisplayName(), fileStore.getDescription()))
                .forEach(pages::add);
        BonitaPagesRegistry.getInstance().getCustomPages().stream()
                .map(p -> new CustomPageDescriptor(p.getPageId(), p.getDisplayName(), p.getDescription()))
                .forEach(pages::add);

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
        return CustomPageDescriptor.DEFAULT_LAYOUT_ID.equals(layoutId)
                || CustomPageDescriptor.LEGACY_DEFAULT_LAYOUT_ID.equals(layoutId);
    }

    public static boolean isDefaultTheme(String themeId) {
        return CustomPageDescriptor.DEFAULT_THEME.getId().equals(themeId)
                || CustomPageDescriptor.BONITA_THEME_ID.equals(themeId);
    }

    public static IConverter<String, String> getCustomPageIdConverter(Collection<CustomPageDescriptor> layouts) {
        return ConverterBuilder.<String, String> newConverter()
                .fromType(String.class)
                .toType(String.class)
                .withConvertFunction(id -> findById(layouts, id).getId())
                .create();
    }

    public static IConverter<String, CustomPageDescriptor> getCustomPageDescriptorConverter(
            Collection<CustomPageDescriptor> layouts) {
        return ConverterBuilder.<String, CustomPageDescriptor> newConverter()
                .fromType(String.class)
                .toType(CustomPageDescriptor.class)
                .withConvertFunction(
                        id -> findById(layouts, id))
                .create();
    }

    public static IConverter<String, String> getCustomPageDisplayNameConverter(Collection<CustomPageDescriptor> layouts) {
        return ConverterBuilder.<String, String> newConverter()
                .fromType(String.class)
                .toType(String.class)
                .withConvertFunction(
                        id -> findById(layouts, id).getDisplayName())
                .create();
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

    public Collection<CustomPageDescriptor> getThemes() {
        final List<CustomPageDescriptor> pages = new ArrayList<>();
        themeStore.getChildren()
                .stream()
                .filter(Objects::nonNull)
                .filter(fileStore -> fileStore.getPageId() != null)
                .map(fileStore -> new CustomPageDescriptor(
                        fileStore.getPageId(),
                        fileStore.getPageDisplayName(),
                        fileStore.getDescription()))
                .forEach(pages::add);
        pages.addAll(DEFAULT_THEMES);
        return pages;
    }

    public ThemeRepositoryStore getThemeStore() {
        return themeStore;
    }

}
