/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.properties;

import java.util.Set;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;

public class PropertySectionHistory {

    private static final String SHOW_DESCRIPTION_PROPERTY = "showDescription";
    private final String sectionId;
    private boolean isDescriptionVisible;
    private static final Set<String> sectionVisibleByDefault;
    static {
        sectionVisibleByDefault = Sets.newHashSet("OperationsPropertySection",
                "EntryFormMappingPropertySection",
                "CaseStartFormMappingPropertySection",
                "CaseOverviewFormMappingPropertySection",
                "ResourcePropertySection",
                "LookAndFeelPropertySection",
                "ConfirmationPropertySection",
                "ProcessEntryFormsSection",
                "ProcessEntryFormsSectionEx",
                "EntryFormsSection",
                "EntryFormsSectionEx",
                "OverviewFormsSection",
                "OverviewFormsSectionEx",
                "AdditionalResourcesPropertySection");
    }

    public PropertySectionHistory(final String sectionId) {
        this.sectionId = sectionId;
    }

    public void showDescription() {
        isDescriptionVisible = true;
    }

    public boolean isDescriptionVisible() {
        return isDescriptionVisible;
    }

    public void hideDescription() {
        isDescriptionVisible = false;
    }

    public void save() throws BackingStoreException {
        final IEclipsePreferences node = preferenceNode();
        node.putBoolean(descriptionVisibilityProperty(), isDescriptionVisible());
        node.flush();
    }

    public void load() {
        isDescriptionVisible = preferenceNode().getBoolean(descriptionVisibilityProperty(), defaultDescriptionVisibility());
    }

    private boolean defaultDescriptionVisibility() {
        return sectionVisibleByDefault.contains(sectionId);
    }

    private String descriptionVisibilityProperty() {
        return Joiner.on(".").join(sectionId, SHOW_DESCRIPTION_PROPERTY);
    }

    private IEclipsePreferences preferenceNode() {
        return InstanceScope.INSTANCE.getNode("org.bonitasoft.studio.common");
    }

    public void clear() throws BackingStoreException {
        preferenceNode().clear();
    }

}
