/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.diagram.custom.providers;

import java.util.Objects;

import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class DiagramColorProvider {

    public static Color darkModeBackgroundColor;
    public static Color darkModeBackgroundGradientColor;
    public static Color darkModeFontColor;
    public static Color darkModeTaskFontColor;

    static {
        LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(Display.getDefault()));
        darkModeBackgroundColor = resourceManager.createColor(ColorConstants.DARK_MODE_DIAGRAMS_BACKGROUND);
        darkModeBackgroundGradientColor = resourceManager.createColor(ColorConstants.DARK_MODE_DIAGRAMS_BACKGROUND_GRADIENT);
        darkModeFontColor = resourceManager.createColor(ColorConstants.DARK_MODE_DIAGRAMS_FONT_COLOR);
        darkModeTaskFontColor = resourceManager.createColor(ColorConstants.DARK_MODE_DIAGRAMS_TASK_FONT_COLOR);
    }

    public static Color getBackgroundColor(IPreferenceStore preferenceStore, Color currentColor) {
        if (PreferenceUtil.isDarkTheme()
                && isDefaultColor(preferenceStore, IPreferenceConstants.PREF_FILL_COLOR, currentColor)) {
            return darkModeBackgroundColor;
        }
        return currentColor;
    }

    public static Color getFontColor(IPreferenceStore preferenceStore, Color currentColor) {
        if (PreferenceUtil.isDarkTheme()
                && isDefaultColor(preferenceStore, IPreferenceConstants.PREF_FONT_COLOR, currentColor)) {
            return darkModeFontColor;
        }
        return currentColor;
    }

    public static Color getTaskFontColor(IPreferenceStore preferenceStore, Color currentColor) {
        if (PreferenceUtil.isDarkTheme()
                && isDefaultColor(preferenceStore, IPreferenceConstants.PREF_FONT_COLOR, currentColor)) {
            return darkModeTaskFontColor;
        }
        return currentColor;
    }

    private static boolean isDefaultColor(IPreferenceStore preferenceStore, String preferenceName, Color color) {
        RGB defaultColor = PreferenceConverter.getColor(preferenceStore, preferenceName);
        RGB currentColor = color.getRGB();
        return Objects.equals(defaultColor, currentColor);
    }

}
