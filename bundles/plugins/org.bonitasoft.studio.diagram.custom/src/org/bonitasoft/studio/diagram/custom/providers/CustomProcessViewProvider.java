/**
 * Copyright (C) 2010-2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.providers;

import java.util.ArrayList;

import org.bonitasoft.studio.diagram.custom.parts.CustomPoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessViewProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.LineStyle;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

/**
 * @author Romain Bioteau
 */
public class CustomProcessViewProvider extends ProcessViewProvider {

    @Override
    public Node createPool_2007(final EObject domainElement, final View containerView,
            final int index, final boolean persisted, final PreferencesHint preferencesHint) {
        final Node node = super.createPool_2007(domainElement, containerView, index, persisted, preferencesHint);
        node.getStyles().add(NotationFactory.eINSTANCE.createFillStyle());
        final Size layoutConstraint = (Size) node.getLayoutConstraint();
        if (layoutConstraint.getWidth() <= 0) {
            layoutConstraint.setWidth(CustomPoolEditPart.getDefaultWidth());
            layoutConstraint.setHeight(CustomPoolEditPart.CONSTANT_DEFAULT_HEIGHT);
        }
        return node;
    }

    @Override
    public Node createLane_3007(final EObject domainElement, final View containerView,
            final int index, final boolean persisted, final PreferencesHint preferencesHint) {
        final Node node = super.createLane_3007(domainElement, containerView, index, persisted, preferencesHint);
        node.getStyles().add(NotationFactory.eINSTANCE.createFillStyle());
        return node;
    }

    @Override
    public Edge createMessageFlow_4002(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Connector edge = NotationFactory.eINSTANCE.createConnector();
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        final RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
                .createRelativeBendpoints();
        final ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(
                2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(ProcessVisualIDRegistry
                .getType(MessageFlowEditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        final org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(edge,
                NotationPackage.eINSTANCE.getLineStyle_LineColor(),
                FigureUtilities.RGBToInteger(lineRGB));
        final FontStyle edgeFontStyle = (FontStyle) edge
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            final FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            final org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        final Routing routing = Routing.get(Routing.RECTILINEAR_LITERAL.getName());

        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(),
                    routing);
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoundedCornersStyle_RoundedBendpointsRadius(),
                    10);
        }
        final Node label6003 = createLabel(edge,
                ProcessVisualIDRegistry
                        .getType(MessageFlowLabelEditPart.VISUAL_ID));
        label6003.setLayoutConstraint(NotationFactory.eINSTANCE
                .createLocation());
        final Location location6003 = (Location) label6003.getLayoutConstraint();
        location6003.setX(0);
        location6003.setY(-10);
        return edge;
    }

    @Override
    public Edge createSequenceFlow_4001(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Connector edge = NotationFactory.eINSTANCE.createConnector();
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        final RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
                .createRelativeBendpoints();
        final ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(
                2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(ProcessVisualIDRegistry
                .getType(SequenceFlowEditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
                .getPreferenceStore();

        final org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(
                prefStore, IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(edge,
                NotationPackage.eINSTANCE.getLineStyle_LineColor(),
                FigureUtilities.RGBToInteger(lineRGB));
        final FontStyle edgeFontStyle = (FontStyle) edge
                .getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            final FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            final org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
                    .getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
                    .intValue());
        }
        final Routing routing = Routing.get(Routing.RECTILINEAR_LITERAL.getName());

        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(),
                    routing);
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoundedCornersStyle_RoundedBendpointsRadius(),
                    10);
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_ClosestDistance(),
                    false);
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_AvoidObstructions(),
                    false);
        }
        final Node label6001 = createLabel(edge,
                ProcessVisualIDRegistry
                        .getType(SequenceFlowNameEditPart.VISUAL_ID));
        label6001.setLayoutConstraint(NotationFactory.eINSTANCE
                .createLocation());
        label6001.setVisible(true);
        final Location location6001 = (Location) label6001.getLayoutConstraint();
        location6001.setX(0);
        location6001.setY(-10);
        return edge;
    }

    private Node createLabel(final View owner, final String hint) {
        final DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
        rv.setType(hint);
        ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
        return rv;
    }

    @Override
    public Node createActivity_3006(final EObject domainElement, final View containerView,
            final int index, final boolean persisted, final PreferencesHint preferencesHint) {
        final Node node = super.createActivity_3006(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createActivity_2006(final EObject domainElement, final View containerView,
            final int index, final boolean persisted, final PreferencesHint preferencesHint) {
        final Node node = super.createActivity_2006(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createTask_2004(final EObject domainElement, final View containerView,
            final int index, final boolean persisted, final PreferencesHint preferencesHint) {
        final Node node = super.createTask_2004(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createTask_3005(final EObject domainElement, final View containerView,
            final int index, final boolean persisted, final PreferencesHint preferencesHint) {
        final Node node = super.createTask_3005(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createScriptTask_2028(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createScriptTask_2028(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createScriptTask_3028(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createScriptTask_3028(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createServiceTask_2027(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createServiceTask_2027(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createServiceTask_3027(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createServiceTask_3027(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createSendTask_2026(final EObject domainElement, final View containerView,
            final int index, final boolean persisted, final PreferencesHint preferencesHint) {
        final Node node = super.createSendTask_2026(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createSendTask_3025(final EObject domainElement, final View containerView,
            final int index, final boolean persisted, final PreferencesHint preferencesHint) {
        final Node node = super.createSendTask_3025(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createReceiveTask_2025(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createReceiveTask_2025(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createReceiveTask_3026(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createReceiveTask_3026(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createCallActivity_2036(final EObject domainElement, final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createCallActivity_2036(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

    @Override
    public Node createSubProcessEvent_2031(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {

        final Node node = super.createSubProcessEvent_2031(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        for (final Object child : node.getPersistedChildren()) {
            if (child instanceof DrawerStyle) {
                ((DrawerStyle) child).setCollapsed(true);
            }
        }
        return node;
    }

    @Override
    public Node createSubProcessEvent_3058(final EObject domainElement,
            final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createSubProcessEvent_3058(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        for (final Object child : node.getPersistedChildren()) {
            if (child instanceof DrawerStyle) {
                final Object store = preferencesHint.getPreferenceStore();
                boolean isCollapsed = true;
                if (store instanceof org.eclipse.jface.preference.PreferenceStore) {
                    isCollapsed = ((org.eclipse.jface.preference.PreferenceStore) preferencesHint.getPreferenceStore()).getBoolean("isCollapsed");
                }
                ((DrawerStyle) child).setCollapsed(isCollapsed);
            }
        }
        return node;
    }

    @Override
    public Node createCallActivity_3063(final EObject domainElement, final View containerView, final int index, final boolean persisted,
            final PreferencesHint preferencesHint) {
        final Node node = super.createCallActivity_3063(domainElement, containerView, index,
                persisted, preferencesHint);
        ((FillStyle) node.getStyle(NotationPackage.eINSTANCE.getFillStyle())).setFillColor(new RGB(184, 185, 218).hashCode());
        ((LineStyle) node.getStyle(NotationPackage.eINSTANCE.getLineStyle())).setLineColor(new RGB(44, 109, 163).hashCode());
        return node;
    }

}
