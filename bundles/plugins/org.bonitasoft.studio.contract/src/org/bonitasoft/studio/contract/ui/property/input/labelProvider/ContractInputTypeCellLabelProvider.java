/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.input.labelProvider;

import java.util.Objects;

import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

/**
 * @author Romain Bioteau
 */
public class ContractInputTypeCellLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    private boolean isDarkTheme;
    private Color darkModeSelectLineUnfocused;
    private ColumnViewer viewer;

    public ContractInputTypeCellLabelProvider(ColumnViewer viewer) {
        this.viewer = viewer;
        isDarkTheme = PreferenceUtil.isDarkTheme();
        darkModeSelectLineUnfocused = new Color(Display.getDefault(), ColorConstants.DARK_MODE_TABLE_SELECTED_UNFOCUS);
    }

    @Override
    public Image getImage(final Object element) {
        return null;
    }

    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
        final ContractInput element = (ContractInput) cell.getElement();
        final String text = getText(element);
        final StyledString styledString = new StyledString(text, new StyledString.Styler() {

            @Override
            public void applyStyles(TextStyle textStyle) {
                if (element.getType() == ContractInputType.DATE) {
                    textStyle.foreground = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
                }
            }
        });
        cell.setText(styledString.getString());
        cell.setStyleRanges(styledString.getStyleRanges());
    }

    @Override
    public String getText(Object element) {
        final ContractInputType type = ((ContractInput) element).getType();
        switch (type) {
            case DATE:
                return DateTypeLabels.DATE_DEPRECATED;
            case LOCALDATE:
                return DateTypeLabels.DATE_ONLY;
            case LOCALDATETIME:
                return DateTypeLabels.DATE_AND_TIME;
            case OFFSETDATETIME:
                return DateTypeLabels.DATE_TIME_WITH_TIMEZONE;
            default:
                return type.name();
        }
    }

    @Override
    protected void erase(Event event, Object element) {
        super.erase(event, element);

        // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
        // TODO Hopefully this could be removed on the futur (current date: 19/11/2020)
        if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
            if ((event.detail & SWT.SELECTED) != 0) {
                Rectangle bounds = event.getBounds();
                Color oldBg = event.gc.getBackground();
                if (isDarkTheme
                        && !Objects.equals(viewer.getControl(), viewer.getControl().getDisplay().getFocusControl())) {
                    // Selected line background is white on dark theme is the table doesn't have the focus (only for Owner drawn cells ofc). 
                    // We force it to a gray so it stays consistant with the theme (and became usable btw). 
                    // Should be fixed on swt.cocoa.macosx soon (please). 
                    event.gc.setBackground(darkModeSelectLineUnfocused);
                }
                event.gc.fillRectangle(bounds);
                event.gc.setBackground(oldBg);
                event.detail &= ~SWT.SELECTED;
            }
        }
    }

    @Override
    public void dispose() {
        darkModeSelectLineUnfocused.dispose();
        super.dispose();
    }

}
