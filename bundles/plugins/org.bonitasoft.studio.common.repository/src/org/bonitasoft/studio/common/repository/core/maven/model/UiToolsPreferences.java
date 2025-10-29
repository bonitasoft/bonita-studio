/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.osgi.service.prefs.BackingStoreException;

/**
 * The user preferences information regarding UI tools.<br>
 * This includes which UI tools start automatically, versions...
 */
public class UiToolsPreferences {

    /**
     * The manager for storing and loading UI tools project preferences.
     */
    public static class Manager {

        /**
         * Get a manager for the given <b>-app</b> project.
         * 
         * @param project the app project
         * @return the preference manager
         */
        public static Manager forProject(IProject project) {
            return new Manager(new ProjectScope(project));
        }

        private final IScopeContext preferenceScope;
        private UiToolsPreferences preferenceValues;

        /**
         * Default Constructor.
         * 
         * @param projectScope the project scope to use for the preferences
         */
        private Manager(ProjectScope projectScope) {
            this.preferenceScope = projectScope;
            this.preferenceValues = UiToolsPreferences.getPreferenceValues(projectScope);
        }

        /**
         * Get the preference values.
         * 
         * @return the preference values for UI tools, including UI Builder and UI Designer preferences.
         */
        public UiToolsPreferences getPreferenceValues() {
            return preferenceValues;
        }

        /**
         * Overwrite the preference values with the given values and save it.
         * 
         * @param preferenceValues the preference values to set
         */
        public void savePreferenceValues(UiToolsPreferences preferenceValues) {
            this.preferenceValues = preferenceValues;
            // save preferences to the scoped preferences node
            var preferenceNode = preferenceScope.getNode(CommonRepositoryPlugin.PLUGIN_ID);
            preferenceValues.toolSpecificPreferences
                    .forEach(p -> p.putPreferenceValues(preferenceNode, preferenceScope));
            try {
                preferenceNode.flush();
            } catch (BackingStoreException e) {
                BonitaStudioLog.error("Failed to persist project preferences.", e);
            }
        }

        /**
         * Add a listener for preference changes.
         * 
         * @param listener the preference change listener
         */
        public void addPreferenceListener(IPreferenceChangeListener listener) {
            preferenceScope.getNode(CommonRepositoryPlugin.PLUGIN_ID).addPreferenceChangeListener(listener);
        }

        /**
         * Remove a listener for preference changes.
         * 
         * @param listener the preference change listener
         */
        public void removePreferenceListener(IPreferenceChangeListener listener) {
            preferenceScope.getNode(CommonRepositoryPlugin.PLUGIN_ID).removePreferenceChangeListener(listener);
        }

    }

    /**
     * Preferences for a particular UI tool.<br/>
     * This interface makes these preferences extensible for subscription UI tools.
     */
    public static interface UiToolPreferences {

        /**
         * Put the preference values to the given preference node, so it can be saved to project.
         * 
         * @param preferenceNode the preference node to put the values to
         * @param preferenceScope the scope context for the preferences, in case another location is needed
         */
        public void putPreferenceValues(IEclipsePreferences preferenceNode, IScopeContext preferenceScope);
    }

    /**
     * Factory for preferences for a particular UI tool.<br/>
     * This factory is in charge of loading the preferences from the appropriate scope and building default preferences.
     */
    public static interface UiToolPreferencesFactory<E extends UiToolPreferences> {

        /**
         * Get default preferences for this particular UI tool.
         * 
         * @return default preferences
         */
        public E defaultPreferences();

        /**
         * Get preference values for this particular UI tool.
         * 
         * @param prefNode the project-scoped preference node
         * @param preferenceScope the scope context for the preferences, in case another location is needed
         * @return loaded preference values
         */
        public E getPreferenceValues(IEclipsePreferences prefNode, IScopeContext preferenceScope);

    }

    /**
     * Preferences for UI Designer.
     */
    public static class UidPreferences implements UiToolPreferences {

        private static final String PREF_PREFIX = "uidesigner.";
        /**
         * Field name for autostart preference.<br/>
         * To use with databinding.
         */
        public static final String AUTOSTART_FIELD_NAME = "autostart";
        /** Preference key for autostart */
        private static final String AUTOSTART_PREF_KEY = PREF_PREFIX + AUTOSTART_FIELD_NAME;

        /** Should remain private and copied (not referenced) to ensure it is never modified */
        private static final UidPreferences DEFAULT_PREFERENCES = new UidPreferences(true);

        private boolean autostart;

        public UidPreferences(boolean autostart) {
            this.autostart = autostart;
        }

        public boolean isAutostart() {
            return autostart;
        }

        public void setAutostart(boolean autostart) {
            this.autostart = autostart;
        }

        /**
         * Check if the event is for the concerned field.
         * 
         * @param fieldName the field name (e.g. {@link #AUTOSTART_FIELD_NAME})
         * @param event the preference change event
         * @return true when the event has the key for the corresponding field
         */
        public static boolean isEventForField(String fieldName, PreferenceChangeEvent event) {
            return (PREF_PREFIX + fieldName).equals(event.getKey());
        }

        /**
         * Get preference values for UI Designer
         * 
         * @param prefNode the project-scoped preference node
         * @return preference values
         */
        private static UidPreferences getPreferenceValues(IEclipsePreferences prefNode) {
            return new UidPreferences(prefNode.getBoolean(AUTOSTART_PREF_KEY, DEFAULT_PREFERENCES.autostart));
        }

        /**
         * Get default preferences for UI Designer.
         * 
         * @return default preferences
         */
        public static UidPreferences defaultPreferences() {
            return new UidPreferences(DEFAULT_PREFERENCES.autostart);
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return Objects.hash(autostart);
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            UidPreferences other = (UidPreferences) obj;
            return Objects.equals(autostart, other.autostart);
        }

        @Override
        public void putPreferenceValues(IEclipsePreferences preferenceNode, IScopeContext preferenceScope) {
            preferenceNode.putBoolean(UidPreferences.AUTOSTART_PREF_KEY, autostart);
        }
    }

    private static List<UiToolPreferencesFactory<? extends UiToolPreferences>> toolSpecificPreferencesFactories = new ArrayList<>(
            2);
    static {
        UiToolPreferencesFactory<UidPreferences> uidFactory = new UiToolPreferencesFactory<>() {

            @Override
            public UidPreferences defaultPreferences() {
                return UidPreferences.defaultPreferences();
            }

            @Override
            public UidPreferences getPreferenceValues(IEclipsePreferences prefNode, IScopeContext preferenceScope) {
                return UidPreferences.getPreferenceValues(prefNode);
            }
        };
        toolSpecificPreferencesFactories.add(uidFactory);
    }

    public static <E extends UiToolPreferences> void registerToolSpecificPreferencesFactory(
            UiToolPreferencesFactory<E> factory) {
        toolSpecificPreferencesFactories.add(factory);
    }

    private Set<UiToolPreferences> toolSpecificPreferences;

    public UiToolsPreferences(Set<? extends UiToolPreferences> toolSpecificPreferences) {
        this.toolSpecificPreferences = new HashSet<>(toolSpecificPreferences);
    }

    public <E extends UiToolPreferences> E getToolPreferences(Class<E> clazz) {
        return toolSpecificPreferences.stream().filter(clazz::isInstance).map(clazz::cast).findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No tool-specific preferences found for class: " + clazz.getName()));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(toolSpecificPreferences);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UiToolsPreferences other = (UiToolsPreferences) obj;
        return Objects.equals(toolSpecificPreferences, other.toolSpecificPreferences);
    }

    /**
     * Get preference values for UI Tools
     * 
     * @param preferenceScope the scoped preferences
     * @return preference values
     */
    private static UiToolsPreferences getPreferenceValues(IScopeContext preferenceScope) {
        var node = preferenceScope.getNode(CommonRepositoryPlugin.PLUGIN_ID);
        var values = toolSpecificPreferencesFactories.stream().map(f -> f.getPreferenceValues(node, preferenceScope))
                .collect(Collectors.toSet());
        return new UiToolsPreferences(values);
    }

    /**
     * Get default preferences for UI tools.
     * 
     * @return default preferences
     */
    public static UiToolsPreferences defaultPreferences() {
        var values = toolSpecificPreferencesFactories.stream().map(f -> f.defaultPreferences())
                .collect(Collectors.toSet());
        return new UiToolsPreferences(values);
    }

}
