/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.document.ui.styler;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.TextStyle;

public class AdditionalResourceStyler extends Styler {

    Color color;

    public AdditionalResourceStyler(Color color) {
        this.color = color;
    }

    @Override
    public void applyStyles(TextStyle textStyle) {
        textStyle.foreground = color;
        textStyle.font = JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT);
    }
}
