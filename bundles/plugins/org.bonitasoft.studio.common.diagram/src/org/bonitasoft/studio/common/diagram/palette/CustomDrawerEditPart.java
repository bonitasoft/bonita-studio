/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.diagram.palette;

import org.eclipse.draw2d.FocusEvent;
import org.eclipse.draw2d.FocusListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ExposeHelper;
import org.eclipse.gef.MouseWheelHelper;
import org.eclipse.gef.editparts.ViewportExposeHelper;
import org.eclipse.gef.editparts.ViewportMouseWheelHelper;
import org.eclipse.gef.internal.InternalImages;
import org.eclipse.gef.internal.ui.palette.editparts.PaletteScrollBar;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.ui.palette.PaletteViewerPreferences;
import org.eclipse.gef.ui.palette.editparts.PaletteAnimator;
import org.eclipse.gef.ui.palette.editparts.PaletteEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;

/**
 * @author Romain Bioteau
 */
public class CustomDrawerEditPart extends PaletteEditPart implements EditPart {

    private String label;

    public CustomDrawerEditPart(PaletteDrawer model) {
        super(model);
    }

    @Override
    public IFigure createFigure() {
        final CustomDrawerFigure fig = new CustomDrawerFigure(getViewer().getControl());
        fig.setExpanded(true);
        fig.setPinned(true);

        fig.getCollapseToggle().addFocusListener(new FocusListener.Stub() {

            @Override
            public void focusGained(FocusEvent fe) {
                getViewer().select(CustomDrawerEditPart.this);
            }
        });

        return fig;
    }

    /**
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(Class)
     */
    @Override
    public Object getAdapter(Class key) {
        if (key == ExposeHelper.class) {
            ViewportExposeHelper helper = new ViewportExposeHelper(this);
            helper.setMinimumFrameCount(6);
            helper.setMargin(new Insets(PaletteScrollBar.BUTTON_HEIGHT, 0,
                    PaletteScrollBar.BUTTON_HEIGHT, 0));
            return helper;
        }
        if (key == MouseWheelHelper.class)
            return new ViewportMouseWheelHelper(this);
        return super.getAdapter(key);
    }

    private PaletteAnimator getPaletteAnimator() {
        return (PaletteAnimator) getViewer().getEditPartRegistry().get(
                PaletteAnimator.class);
    }

    /**
     * Convenience method that provides access to the PaletteDrawer that is the
     * model.
     * 
     * @return The model PaletteDrawer
     */
    public PaletteDrawer getDrawer() {
        return (PaletteDrawer) getPaletteEntry();
    }

    /**
     * Convenience method to get the DrawerFigure for the model drawer.
     * 
     * @return The DrawerFigure created in {@link #createFigure()}
     */
    public CustomDrawerFigure getDrawerFigure() {
        return (CustomDrawerFigure) getFigure();
    }

    /**
     * @see org.eclipse.gef.GraphicalEditPart#getContentPane()
     */
    @Override
    public IFigure getContentPane() {
        return getDrawerFigure().getContentPane();
    }

    public boolean isExpanded() {
        return getDrawerFigure().isExpanded();
    }

    public boolean isPinnedOpen() {
        return getDrawerFigure().isPinnedOpen();
    }

    /**
     * @see org.eclipse.gef.ui.palette.editparts.PaletteEditPart#nameNeededInToolTip()
     */
    @Override
    protected boolean nameNeededInToolTip() {
        return false;
    }

    /**
     * @return <code>true</code> if the DrawerFigure can be pinned open. This is
     *         only true when the drawer is expanded and the auto-collapse
     *         strategy is
     *         <code>PaletteViewerPreferences.COLLAPSE_AS_NEEDED</code>.
     */
    public boolean canBePinned() {
        return getDrawerFigure().isPinShowing();
    }

    /**
     * @see org.eclipse.gef.ui.palette.editparts.PaletteEditPart#createAccessible()
     */
    @Override
    protected AccessibleEditPart createAccessible() {
        return new AccessibleGraphicalEditPart() {

            @Override
            public void getDescription(AccessibleEvent e) {
                e.result = getPaletteEntry().getDescription();
            }

            @Override
            public void getName(AccessibleEvent e) {
                e.result = getPaletteEntry().getLabel();
            }

            @Override
            public void getRole(AccessibleControlEvent e) {
                e.detail = ACC.ROLE_TREE;
            }

            @Override
            public void getState(AccessibleControlEvent e) {
                super.getState(e);
                e.detail |= isExpanded() ? ACC.STATE_EXPANDED
                        : ACC.STATE_COLLAPSED;
            }
        };
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        getDrawerFigure().setToolTip(createToolTip());

        ImageDescriptor img = getDrawer().getSmallIcon();
        if (img == null && getDrawer().showDefaultIcon()) {
            img = InternalImages.DESC_FOLDER_OPEN;
        }
        setImageDescriptor(img);

        getDrawerFigure().setTitle(getPaletteEntry().getLabel());
        getDrawerFigure().setLayoutMode(getLayoutSetting());

        boolean showPin = getPreferenceSource().getAutoCollapseSetting() == PaletteViewerPreferences.COLLAPSE_AS_NEEDED;
        getDrawerFigure().showPin(showPin);
    }

    /**
     * @see org.eclipse.gef.ui.palette.editparts.PaletteEditPart#restoreState(org.eclipse.ui.IMemento)
     */
    @Override
    public void restoreState(IMemento memento) {
        RangeModel rModel = getDrawerFigure().getScrollpane().getViewport()
                .getVerticalRangeModel();
        rModel.setMinimum(memento.getInteger(RangeModel.PROPERTY_MINIMUM)
                .intValue());
        rModel.setMaximum(memento.getInteger(RangeModel.PROPERTY_MAXIMUM)
                .intValue());
        rModel.setExtent(memento.getInteger(RangeModel.PROPERTY_EXTENT)
                .intValue());
        rModel.setValue(memento.getInteger(RangeModel.PROPERTY_VALUE)
                .intValue());
        super.restoreState(memento);
    }

    /**
     * @see org.eclipse.gef.ui.palette.editparts.PaletteEditPart#saveState(org.eclipse.ui.IMemento)
     */
    @Override
    public void saveState(IMemento memento) {
        RangeModel rModel = getDrawerFigure().getScrollpane().getViewport()
                .getVerticalRangeModel();
        memento.putInteger(RangeModel.PROPERTY_MINIMUM, rModel.getMinimum());
        memento.putInteger(RangeModel.PROPERTY_MAXIMUM, rModel.getMaximum());
        memento.putInteger(RangeModel.PROPERTY_EXTENT, rModel.getExtent());
        memento.putInteger(RangeModel.PROPERTY_VALUE, rModel.getValue());
        super.saveState(memento);
    }

    /**
     * Sets the expansion state of the DrawerFigure
     * 
     * @param expanded
     *        <code>true</code> if the drawer is expanded; false otherwise.
     */
    public void setExpanded(boolean expanded) {
        getDrawerFigure().setExpanded(expanded);
    }

    /**
     * @see org.eclipse.gef.ui.palette.editparts.PaletteEditPart#setImageInFigure(Image)
     */
    @Override
    protected void setImageInFigure(Image image) {
        ;
    }

    public void setPinnedOpen(boolean pinned) {

    }

}
