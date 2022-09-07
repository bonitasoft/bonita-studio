/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.figures.CollapsableEventSubprocessFigure;
import org.bonitasoft.studio.diagram.custom.providers.DiagramColorProvider;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.BasicCompartment;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

/**
 * @author Aurelien Pupier
 */
public class CustomSubProcessEvent2EditPart extends SubProcessEvent2EditPart {

    public CustomSubProcessEvent2EditPart(View view) {
        super(view);
        addEditPartListener(new EditPartListener() {

            @Override
            public void childAdded(EditPart child, int index) {
                if (child instanceof CustomSubprocessEventCompartmentEditPart) {
                    if (!((BasicCompartment) ((CustomSubprocessEventCompartmentEditPart) child).getNotationView())
                            .isCollapsed()) {
                        ((CollapsableEventSubprocessFigure) getContentPane()).setUseGradient(false);
                        ((CustomSubprocessEventCompartmentEditPart) child).setBeforeExpandColor(
                                (Integer) getPreferredValue(NotationPackage.eINSTANCE.getFillStyle_FillColor()));
                        setBackgroundColor(ColorConstants.white);
                    }
                }

            }

            @Override
            public void partActivated(EditPart editpart) {
            }

            @Override
            public void partDeactivated(EditPart editpart) {
            }

            @Override
            public void removingChild(EditPart child, int index) {
            }

            @Override
            public void selectedStateChanged(EditPart editpart) {
            }

        });
    }

    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
        GridData constaint = new GridData();
        constaint.grabExcessHorizontalSpace = true;
        constaint.grabExcessVerticalSpace = true;
        constaint.verticalAlignment = GridData.FILL;
        constaint.horizontalAlignment = GridData.FILL;

        getContentPane().add(child, constaint);

    }

    @Override
    protected NodeFigure createNodePlate() {
        return new DefaultSizeNodeFigure(FiguresHelper.ACTIVITY_WIDTH,
                FiguresHelper.ACTIVITY_HEIGHT);
    }

    @Override
    public Object getPreferredValue(EStructuralFeature feature) {
        Object preferenceStore = getDiagramPreferencesHint().getPreferenceStore();
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
                return FigureUtilities.RGBToInteger(new RGB(235, 238, 242));
            }
        }
        return getStructuralFeatureValue(feature);
    }

    @Override
    protected void setBackgroundColor(Color color) {
        Object preferenceStore = getDiagramPreferencesHint().getPreferenceStore();
        if (preferenceStore instanceof IPreferenceStore) {
            super.setBackgroundColor(DiagramColorProvider.getBackgroundColor((IPreferenceStore) preferenceStore, color));
        } else {
            super.setBackgroundColor(color);
        }
    }

}
