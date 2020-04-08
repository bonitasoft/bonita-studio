/*
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences;

/**
 * @author Romain Bioteau
 *         Constant definitions for plug-in preferences
 */
public interface BonitaPreferenceConstants {

    public static final String APLLICATION_DEPLOYMENT_MODE = "appliDeploymentMode"; //$NON-NLS-1$
    public static final String ALL_IN_BAR = "allInBar"; //$NON-NLS-1$
    public static final String WAR_GENERATION = "warGeneration"; //$NON-NLS-1$
    public static final String CONSOLE_PORT = "consolePortPreference"; //$NON-NLS-1$
    public static final String CONSOLE_HOST = "consoleHost"; //$NON-NLS-1$
    public static final String CONSOLE_BROWSER_CHOICE = "browser-choice"; //$NON-NLS-1$
    public static final String INTERNAL_BROWSER = "0"; //$NON-NLS-1$
    public static final String EXTERNAL_BROWSER = "1"; //$NON-NLS-1$
    public static final String CONSOLE_BROWSER_ID = "org.bonitasoft.studio.console.browser"; //$NON-NLS-1$
    public static final String APPLICATION_BROWSER_ID = "org.bonitasoft.studio.application.browser"; //$NON-NLS-1$
    public static final String HELP_BROWSER_ID = "org.bonitasoft.studio.help.browser"; //$NON-NLS-1$
    public static final String DELETE_TENANT_ON_EXIT = "dropDataBase"; //$NON-NLS-1$
    public static final String LOAD_ORGANIZATION = "retrieveUsers"; //$NON-NLS-1$
    public static final String CURRENT_UXP_LOCALE = "currentUXPLocale"; //$NON-NLS-1$
    public static final String CURRENT_STUDIO_LOCALE = "currentStudioLocale"; //$NON-NLS-1$
    public static final String AUTOMATIC_ID = "automaticId"; //$NON-NLS-1$

    public static final String SHOW_CONDITION_ON_TRANSITION = "showConditiononTransition"; //$NON-NLS-1$

    /**
     * the connection line style; shows up in ConnectionsPreferencePage
     */
    public static final String PREF_LINE_STYLE = "Connectors.lineStyle"; //$NON-NLS-1$

    /**
     * the font; shows up in AppearancePreferencePage
     */
    public static final String PREF_DEFAULT_FONT = "Appearance.defaultFont"; //$NON-NLS-1$
    /**
     * the font color; shows up in AppearancePreferencePage
     */
    public static final String PREF_FONT_COLOR = "Appearance.fontColor"; //$NON-NLS-1$
    /**
     * the the fill color; shows up in AppearancePreferencePage
     */
    public static final String PREF_FILL_COLOR = "Appearance.fillColor"; //$NON-NLS-1$
    /**
     * the line color; shows up in AppearancePreferencePage
     */
    public static final String PREF_LINE_COLOR = "Appearance.lineColor"; //$NON-NLS-1$
    /**
     * the note fill color; shows up in AppearancePreferencePage
     */
    public static final String PREF_NOTE_FILL_COLOR = "Appearance.noteFillColor"; //$NON-NLS-1$
    /**
     * the note line color; shows up in AppearancePreferencePage
     */
    public static final String PREF_NOTE_LINE_COLOR = "Appearance.noteLineColor"; //$NON-NLS-1$

    /**
     * show connection handles preference; Global Preference
     */
    public static final String PREF_SHOW_CONNECTION_HANDLES = "Global.showConnectionHandles"; //$NON-NLS-1$

    /**
     * show popup bars preference;; Global Preference
     */
    public static final String PREF_SHOW_POPUP_BARS = "Global.showPopupBars"; //$NON-NLS-1$

    /**
     * prompt when user choose delete from model; Global Preference
     */
    public static final String PREF_PROMPT_ON_DEL_FROM_MODEL = "Global.promptOnDelFromModel"; //$NON-NLS-1$
    /**
     * prompt when user choose delete from diagram ; Global Preference
     */
    public static final String PREF_PROMPT_ON_DEL_FROM_DIAGRAM = "Global.promptOnDelFromDiagram"; //$NON-NLS-1$
    /**
     * enable Layout animation ; Global Preference
     */
    public static final String PREF_ENABLE_ANIMATED_LAYOUT = "Global.enableAnimatedLayout"; //$NON-NLS-1$
    /**
     * enable zoom animation; Global Preference
     */
    public static final String PREF_ENABLE_ANIMATED_ZOOM = "Global.enableAnimatedZoom"; //$NON-NLS-1$

    /**
     * enable anti-aliasing; Global Preference
     */
    public static final String PREF_ENABLE_ANTIALIAS = "Global.enableAntiAlias"; //$NON-NLS-1$

    /**
     * enable status line content; Global Preference
     *
     * @since 1.2
     */
    public static final String PREF_SHOW_STATUS_LINE = "Global.showStatusLine"; //$NON-NLS-1$

    /**
     * show grid preference; Grid/Rulers Preference Page
     */
    public static final String PREF_SHOW_GRID = "GridRuler.showGrid"; //$NON-NLS-1$
    /**
     * Show Rulers Preference; Grid/Rulers Preference Page
     */
    public static final String PREF_SHOW_RULERS = "GridRuler.showRulers"; //$NON-NLS-1$
    /**
     * Snap to grid preference; Grid/Rulers Preference Page
     */
    public static final String PREF_SNAP_TO_GRID = "GridRuler.snapToGrid"; //$NON-NLS-1$
    /**
     * ruler units preference; Grid/Rulers Preference Page
     */
    public static final String PREF_RULER_UNITS = "GridRuler.rulerUnits"; //$NON-NLS-1$
    /**
     * grid spacing preference; Grid/Rulers Preference Page
     */
    public static final String PREF_GRID_SPACING = "GridRuler.gridSpacing"; //$NON-NLS-1$
    /**
     * snap to geometry preference; Grid/Rulers Preference Page
     */
    public static final String PREF_SNAP_TO_GEOMETRY = "GridRuler.snapToGeometry"; //$NON-NLS-1$

    // PRINTING PREFERENCES
    public static String PREF_USE_WORKSPACE_SETTINGS = "print.useWorkspaceSettings"; //$NON-NLS-1$
    public static String PREF_USE_DIAGRAM_SETTINGS = "print.useDiagramSettings"; //$NON-NLS-1$

    public static String PREF_USE_INCHES = "print.useInches"; //$NON-NLS-1$
    public static String PREF_USE_MILLIM = "print.useMillim"; //$NON-NLS-1$

    public static String PREF_USE_PORTRAIT = "print.usePortrait"; //$NON-NLS-1$
    public static String PREF_USE_LANDSCAPE = "print.useLandscape"; //$NON-NLS-1$

    public static String PREF_PAGE_SIZE = "print.page.size"; //$NON-NLS-1$
    public static String PREF_PAGE_HEIGHT = "print.page.height"; //$NON-NLS-1$
    public static String PREF_PAGE_WIDTH = "print.page.width"; //$NON-NLS-1$

    public static String PREF_MARGIN_TOP = "print.margin.top"; //$NON-NLS-1$
    public static String PREF_MARGIN_BOTTOM = "print.margin.bottom"; //$NON-NLS-1$
    public static String PREF_MARGIN_LEFT = "print.margin.left"; //$NON-NLS-1$
    public static String PREF_MARGIN_RIGHT = "print.margin.right"; //$NON-NLS-1$
    // END PRINTING PREFERENCES

    public static final String DEFAULT_USERXP_THEME = "defaultUserXPTheme";
    public static final String DEFAULT_APPLICATION_THEME = "defaultAppliXPTheme";
    public static final String ASK_RENAME_ON_FIRST_SAVE = "renameOnFirstSave";
    public static final String ALWAYS_USE_SCRIPTING_MODE = "useScriptingMode";
    public static final String DEFAULT_STUDIO_LOCALE = "defaultLocale";
    public static final int DEFAULT_PORT = 8080;
    public static final String DEFAULT_HOST = "localhost";
    public static final String SHOW_LEGACY_6X_MODE = "showLegacy6XMode";

    public static final String UID_PORT = "uid.port";
    public static final String UID_JVM_OPTS = "uid.jvmOpts";
    public static final String CUSTOM_PAGE_DEBUG = "custom.page.debug";
    public static final String DATA_REPOSITORY_PORT = "data.repository.port";

    public static final String HIDE_BDM_REFACTOR_PREVIEW = "hideBdmRefactorPreview";

}
