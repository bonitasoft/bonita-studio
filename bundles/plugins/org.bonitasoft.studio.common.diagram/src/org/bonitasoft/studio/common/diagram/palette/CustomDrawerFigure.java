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

import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.SchemeBorder;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.Toggle;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.internal.ui.palette.PaletteColorUtil;
import org.eclipse.gef.internal.ui.palette.editparts.ColumnsLayout;
import org.eclipse.gef.internal.ui.palette.editparts.OverlayScrollPaneLayout;
import org.eclipse.gef.internal.ui.palette.editparts.PaletteContainerFlowLayout;
import org.eclipse.gef.internal.ui.palette.editparts.PinnablePaletteStackFigure;
import org.eclipse.gef.internal.ui.palette.editparts.RaisedBorder;
import org.eclipse.gef.ui.palette.PaletteViewerPreferences;
import org.eclipse.gef.ui.palette.editparts.PaletteToolbarLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 */
public class CustomDrawerFigure extends Figure {

    /** Scrollpane border constant for icon and column layout mode **/
    protected static final Border SCROLL_PANE_BORDER = new MarginBorder(2, 2,
            2, 2);
    /** Scrollpane border constant for list and details layout mode **/
    protected static final Border SCROLL_PANE_LIST_BORDER = new MarginBorder(2,
            0, 2, 0);
    /** Title margin border constant **/
    protected static final Border TITLE_MARGIN_BORDER = new MarginBorder(4, 2,
            2, 2);
    /** Toggle button border constant **/
    protected static final Border TOGGLE_BUTTON_BORDER = new RaisedBorder();
    /** Tooltip border constant **/
    protected static final Border TOOLTIP_BORDER = new CompoundBorder(
            new SchemeBorder(SchemeBorder.SCHEMES.RAISED), new MarginBorder(1));
    private Toggle collapseToggle;
    private Label drawerLabel, tipLabel;

    private boolean addedScrollpane = false;
    private int layoutMode = -1;
    private ScrollPane scrollpane;
    private boolean showPin = true, skipNextEvent;

    /**
     * This is the figure for the entire drawer label button.
     */
    private class CollapseToggle extends Toggle {

        public CollapseToggle(IFigure contents) {
            super(contents);
            setSelected(false);
            setRequestFocusEnabled(false);
        }

        @Override
        public IFigure getToolTip() {
            return buildTooltip();
        }

        @Override
        protected void paintFigure(Graphics g) {
            //			Rectangle r = Rectangle.SINGLETON;
            //			r.setBounds(getBounds());
            //			
            //			g.setBackgroundColor(ColorConstants.white);
            //	g.fillRectangle(r);
            // draw top border of drawer figure
            //			g.setForegroundColor(ColorConstants.lightGray);
            //			g.setLineWidth(1);
            //			g.drawLine(r.getTopLeft(), r.getTopRight());
        }
    }

    /**
     * Constructor
     * 
     * @param control
     *        The Control of the LWS to which this Figure belongs (it is
     *        used to display the drawer header with an EditPartTipHelper,
     *        if the header is not completely visible). It can be
     *        <code>null</code> (the tip won't be displayed).
     */
    public CustomDrawerFigure(final Control control) {
        /*
         * A PaletteToolbarLayout is being used here instead of a ToolbarLayout
         * so that the ScrollPane can be stretched to take up vertical space.
         * This affects selection and appearance (background color).
         */
        setLayoutManager(new PaletteToolbarLayout() {

            @Override
            protected boolean isChildGrowing(IFigure child) {
                int wHint = child.getBounds().width;
                return child.getPreferredSize(wHint, -1).height != child
                        .getMinimumSize(wHint, -1).height;
            }
        });

        Figure title = new Figure() {

            @Override
            protected void paintFigure(Graphics graphics) {
                Rectangle bounds = getBounds();
                graphics.setForegroundColor(isDarkTheme() ? ColorConstants.lightGray : ColorConstants.darkGray);
                graphics.setLineWidth(1);

                graphics.drawLine(3, drawerLabel.getTextBounds().getLeft().y, drawerLabel.getTextBounds().getLeft().x - 2,
                        drawerLabel.getTextBounds().getLeft().y);
                graphics.drawLine(drawerLabel.getTextBounds().getRight().x + 2, drawerLabel.getTextBounds().getRight().y,
                        bounds.width - 3, drawerLabel.getTextBounds().getRight().y);

            }
        };
        title.setBorder(new MarginBorder(5, 0,
                0, 0));
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHorizontalSpacing(2);
        title.setLayoutManager(borderLayout);

        drawerLabel = new Label() {

            @Override
            protected void paintFigure(Graphics graphics) {
                Rectangle bounds = getBounds();
                graphics.translate(bounds.x, bounds.y);
                graphics.setForegroundColor(isDarkTheme() ? ColorConstants.lightGray : ColorConstants.darkGray);
                graphics.drawText(getSubStringText(), getTextLocation());
                graphics.translate(-bounds.x, -bounds.y);
            }
        };
        drawerLabel.setLabelAlignment(Label.CENTER);
        title.add(drawerLabel, BorderLayout.CENTER);

        collapseToggle = new CollapseToggle(title);

        add(collapseToggle);
        createScrollpane(control);
    }

    private void createScrollpane(Control control) {
        scrollpane = new ScrollPane();
        scrollpane.getViewport().setContentsTracksWidth(true);
        scrollpane.setMinimumSize(new Dimension(0, 0));
        scrollpane.setHorizontalScrollBarVisibility(ScrollPane.NEVER);

        scrollpane.setLayoutManager(new OverlayScrollPaneLayout());
        scrollpane.setContents(new Figure());
        scrollpane.getContents().setOpaque(true);
        scrollpane.getContents()
                .setBackgroundColor(isDarkTheme() ? ColorConstants.darkGray : PaletteColorUtil.WIDGET_LIST_BACKGROUND);
    }

    private boolean isDarkTheme() {
        return PreferenceUtil.isDarkTheme();
    }

    IFigure buildTooltip() {
        return null;
    }

    /**
     * @return The Clickable that is used to expand/collapse the drawer.
     */
    public Clickable getCollapseToggle() {
        return collapseToggle;
    }

    /**
     * @return The content pane for this figure, i.e. the Figure to which
     *         children can be added.
     */
    public IFigure getContentPane() {
        return scrollpane.getContents();
    }

    /**
     * @see Figure#getMinimumSize(int, int)
     */
    @Override
    public Dimension getMinimumSize(int wHint, int hHint) {
        /*
         * Fix related to Bug #35176 The figure returns a minimum size that is
         * of at least a certain height, so as to prevent each drawer from
         * getting too small (in which case, the scrollbars cover up the entire
         * available space).
         */
        if (isExpanded()) {
            List children = getContentPane().getChildren();
            if (!children.isEmpty()) {
                Dimension result = collapseToggle
                        .getPreferredSize(wHint, hHint).getCopy();
                result.height += getContentPane().getInsets().getHeight();
                IFigure child = (IFigure) children.get(0);
                result.height += Math.min(80,
                        child.getPreferredSize(wHint, -1).height + 9);
                result.intersect(getPreferredSize(wHint, hHint)).expand(0, 15);
                result.setHeight(getSize().height);
                return result;
            }
        }
        return super.getMinimumSize(wHint, hHint);
    }

    /**
     * Returns the ScrollPane associated with this DrawerFigure
     * 
     * @return the ScrollPane
     */
    public ScrollPane getScrollpane() {
        return scrollpane;
    }

    protected void handleExpandStateChanged() {
        if (isExpanded()) {
            if (scrollpane.getParent() != this)
                add(scrollpane);
        } else {
            if (scrollpane.getParent() == this)
                remove(scrollpane);

            // collapse all pinnable palette stack children that aren't pinned
            for (Iterator iterator = getContentPane().getChildren().iterator(); iterator
                    .hasNext();) {
                Object child = iterator.next();
                if (child instanceof PinnablePaletteStackFigure
                        && !((PinnablePaletteStackFigure) child).isPinnedOpen()) {
                    ((PinnablePaletteStackFigure) child).setExpanded(false);
                }
            }

        }
    }

    /**
     * @return <code>true</code> if the drawer is expanded
     */
    public boolean isExpanded() {
        return collapseToggle.isSelected();
    }

    /**
     * @return <code>true</code> if the drawer is expanded and is pinned (i.e.,
     *         it can't be automatically collapsed)
     */
    public boolean isPinnedOpen() {
        return false;
    }

    /**
     * @return <code>true</code> if the drawer is expanded and its pin is
     *         showing
     */
    public boolean isPinShowing() {
        return isExpanded() && showPin;
    }

    public void setAnimating(boolean isAnimating) {
        if (isAnimating) {
            if (scrollpane.getParent() != this) {
                addedScrollpane = true;
                add(scrollpane);
            }
            scrollpane.setVerticalScrollBarVisibility(ScrollPane.NEVER);
        } else {
            scrollpane.setVerticalScrollBarVisibility(ScrollPane.AUTOMATIC);
            if (addedScrollpane) {
                remove(scrollpane);
                addedScrollpane = false;
            }
        }
    }

    public void setExpanded(boolean value) {
        collapseToggle.setSelected(value);
    }

    public void setLayoutMode(int layoutMode) {
        if (this.layoutMode == layoutMode) {
            return;
        }

        this.layoutMode = layoutMode;

        LayoutManager manager;
        if (layoutMode == PaletteViewerPreferences.LAYOUT_COLUMNS) {
            manager = new ColumnsLayout();
            getContentPane().setBorder(SCROLL_PANE_BORDER);
        } else if (layoutMode == PaletteViewerPreferences.LAYOUT_ICONS) {
            PaletteContainerFlowLayout fl = new PaletteContainerFlowLayout();
            fl.setMinorSpacing(0);
            fl.setMajorSpacing(0);
            manager = fl;
            getContentPane().setBorder(SCROLL_PANE_BORDER);
        } else {
            manager = new ToolbarLayout();
            getContentPane().setBorder(SCROLL_PANE_LIST_BORDER);
        }
        getContentPane().setLayoutManager(manager);
    }

    /**
     * Pins or unpins the drawer. The drawer can be pinned open only when it is
     * expanded. Attempts to pin it when it is collapsed will do nothing.
     * 
     * @param pinned
     *        <code>true</code> if the drawer is to be pinned
     */
    public void setPinned(boolean pinned) {

    }

    /**
     * Displays the given text in the drawer's header as its title.
     * 
     * @param s
     *        The title of the drawer
     */
    public void setTitle(String s) {
        drawerLabel.setText(s);
    }

    /**
     * Displays the given image in the header as the drawer's icon.
     * 
     * @param icon
     *        The icon for this drawer.
     */
    public void setTitleIcon(Image icon) {
        drawerLabel.setIcon(icon);
    }

    public void showPin(boolean show) {
        showPin = show;
        handleExpandStateChanged();
    }

}
