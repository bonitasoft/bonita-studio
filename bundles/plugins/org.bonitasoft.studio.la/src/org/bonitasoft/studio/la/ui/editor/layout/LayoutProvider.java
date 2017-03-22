/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.editor.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;

public class LayoutProvider {

    public static final String CUSTOMPAGE_PREFIX = "custompage_";

    private final WebPageRepositoryStore store;

    public LayoutProvider(WebPageRepositoryStore store) {
        this.store = store;
    }

    public List<LayoutDescriptor> getLayouts() {
        final List<LayoutDescriptor> layouts = new ArrayList<>();
        layouts.add(LayoutDescriptor.DEFAULT_LAYOUT);

        store.getChildren()
                .stream()
                .filter(webPageFileStore -> Objects.equals(webPageFileStore.getType(), WebPageFileStore.LAYOUT_TYPE))
                .map(fileStore -> new LayoutDescriptor(CUSTOMPAGE_PREFIX + fileStore.getDisplayName(),
                        fileStore.getDisplayName(), true))
                .forEach(layouts::add);

        return layouts;
    }

    public static String fromIdToName(String id) {
        return id.startsWith(CUSTOMPAGE_PREFIX) ? id.substring(CUSTOMPAGE_PREFIX.length()) : id;
    }

    public static String fromNameToId(String name) {
        return CUSTOMPAGE_PREFIX + name;
    }

    public boolean isDefaultLayout(String layoutId) {
        return LayoutDescriptor.DEFAULT_LAYOUT_ID.equals(layoutId);
    }

    public static IConverter toLayoutId() {
        return ConverterBuilder.<LayoutDescriptor, String> newConverter()
                .fromType(LayoutDescriptor.class)
                .toType(String.class)
                .withConvertFunction(LayoutDescriptor::getId)
                .create();
    }

    public static IConverter toLayoutDescriptor(Collection<LayoutDescriptor> layouts) {
        return ConverterBuilder.<String, LayoutDescriptor> newConverter()
                .fromType(String.class)
                .toType(LayoutDescriptor.class)
                .withConvertFunction(
                        id -> findLayoutById(layouts, id))
                .create();
    }

    private static LayoutDescriptor findLayoutById(Collection<LayoutDescriptor> layouts, String id) {
        final Optional<LayoutDescriptor> layout = layouts.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
        if (layout.isPresent()) {
            return layout.get();
        }
        final LayoutDescriptor newLayout = new LayoutDescriptor(id, id, false);
        if (layouts instanceof IObservableList) {
            layouts.add(newLayout);
        }
        return newLayout;
    }
}
