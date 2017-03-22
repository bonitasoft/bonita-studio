/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.editor.theme;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThemeDescriptor {

    public static final List<ThemeDescriptor> DEFAULT_THEMES = new ArrayList<>();
    static {
        DEFAULT_THEMES.add(new ThemeDescriptor("custompage_bootstrapdefaulttheme", "Bootstrap default theme"));
        DEFAULT_THEMES.add(new ThemeDescriptor("custompage_superherotheme", "Superhero theme"));
        DEFAULT_THEMES.add(new ThemeDescriptor("custompage_simplextheme", "Simplex theme"));
        DEFAULT_THEMES.add(new ThemeDescriptor("custompage_readabletheme", "Readable theme"));
        DEFAULT_THEMES.add(new ThemeDescriptor("custompage_flatlytheme", "Flatly theme"));
        DEFAULT_THEMES.add(new ThemeDescriptor("custompage_cosmotheme", "Cosmo theme"));
    }

    private String id;
    private String displayName;

    public ThemeDescriptor(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public static String fromIdToName(String id) {
        return DEFAULT_THEMES.stream()
                .filter(theme -> Objects.equals(theme.getId(), id))
                .findFirst()
                .orElse(new ThemeDescriptor(id, id)).getDisplayName();
    }

    public static String fromNameToId(String name) {
        return DEFAULT_THEMES.stream()
                .filter(theme -> Objects.equals(theme.getDisplayName(), name))
                .findFirst()
                .orElse(new ThemeDescriptor(name, name)).getId();
    }

    public static boolean isDefaultTheme(String nameOrId) {
        return DEFAULT_THEMES.stream()
                .anyMatch(theme -> Objects.equals(nameOrId, theme.getDisplayName())
                        || Objects.equals(nameOrId, theme.getId()));
    }
}
