/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.common.figures.CustomSVGFigure;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.graphics.ColorRegistry;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * @author Romain Bioteau
 */
public class CustomTask2EditPart extends Task2EditPart {

    private Color backgroundColor;
    private Color foregroundColor;

    public CustomTask2EditPart(View view) {
        super(view);
    }

    @Override
    protected void setBackgroundColor(Color color) {
        if (primaryShape != null) {
            backgroundColor = ColorRegistry.getInstance()
                    .getColor(((LineStyle) getNotationView().getStyle(NotationPackage.eINSTANCE.getLineStyle()))
                            .getLineColor());
            ((CustomSVGFigure) primaryShape).setColor(backgroundColor, color);
        }
    }

    @Override
    protected void setForegroundColor(Color color) {
        if (primaryShape != null) {
            foregroundColor = ColorRegistry.getInstance()
                    .getColor(((FillStyle) getNotationView().getStyle(NotationPackage.eINSTANCE.getFillStyle()))
                            .getFillColor());
            ((CustomSVGFigure) primaryShape).setColor(color, foregroundColor);
        }
    }

    @Override
    public Object getPreferredValue(EStructuralFeature feature) {
        final Object preferenceStore = getDiagramPreferencesHint().getPreferenceStore();
        if (preferenceStore instanceof IPreferenceStore) {
            if (feature == NotationPackage.eINSTANCE.getLineStyle_LineColor()) {
                return FigureUtilities.RGBToInteger(new RGB(44, 109, 163));
            } else if (feature == NotationPackage.eINSTANCE
                    .getFontStyle_FontColor()) {
                return FigureUtilities.RGBToInteger(PreferenceConverter
                        .getColor((IPreferenceStore) preferenceStore,
                                IPreferenceConstants.PREF_FONT_COLOR));
            } else if (feature == NotationPackage.eINSTANCE
                    .getFillStyle_FillColor()) {
                return FigureUtilities.RGBToInteger(new RGB(184, 185, 218));
            }
        }

        return getStructuralFeatureValue(feature);
    }

}
