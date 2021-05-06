/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.views.extension.card.zoom;

import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

public interface IZoomable {

    public Control createZoomedControl(Composite parent);

    public void addZoomListener(ZoomListener listener);

    public ZoomListener getZoomListener();

    public default void addZoomBehavior(Control zoomControl) {
        IThemeEngine engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
        Cursor cursorHand = zoomControl.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        Cursor cursorArrow = zoomControl.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);

        zoomControl.addListener(SWT.MouseUp, e -> {
            if (getZoomListener() != null) {
                Rectangle bounds = zoomControl.getBounds();
                if (e.x >= 0 && e.x <= bounds.width && e.y >= 0 && e.y <= bounds.height) {
                    getZoomListener().zoom(e);
                }
            }
        });

        zoomControl.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseEnter(MouseEvent e) {
                zoomControl.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                        BonitaThemeConstants.CARD_HIGHLIGHT_TITLE_COLOR);
                engine.applyStyles(zoomControl, false);
                zoomControl.setCursor(cursorHand);
            }

            @Override
            public void mouseExit(MouseEvent e) {
                zoomControl.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
                engine.applyStyles(zoomControl, false);
                zoomControl.setCursor(cursorArrow);
            }
        });
    }

}
