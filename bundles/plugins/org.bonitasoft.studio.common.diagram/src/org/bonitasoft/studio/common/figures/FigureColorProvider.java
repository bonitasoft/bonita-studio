/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.figures;

import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class FigureColorProvider {

    public static Color darkModeGradientColor;

    static {
        LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(Display.getDefault()));
        darkModeGradientColor = resourceManager.createColor(ColorConstants.DARK_MODE_DIAGRAMS_BACKGROUND);
    }

    public static String getGradientHexaColor() {
        if (PreferenceUtil.isDarkTheme()) {
            return ColorConstants.DARK_MODE_ACTIVITY_GRADIENT_HEXA_COLOR;
        }
        return "#FFFFFF";
    }

    public static Color getGradientColor() {
        if (PreferenceUtil.isDarkTheme()) {
            return darkModeGradientColor;
        }
        return org.eclipse.draw2d.ColorConstants.white;
    }

}
