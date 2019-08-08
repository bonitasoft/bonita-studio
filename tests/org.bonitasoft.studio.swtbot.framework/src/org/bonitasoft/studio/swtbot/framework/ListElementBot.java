/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.swtbot.framework;

import org.eclipse.swt.SWT;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement;

public class ListElementBot extends AbstractSWTBotControl<ListElement> {

    public ListElementBot(ListElement tab) throws WidgetNotFoundException {
        super(tab);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot#click()
     */
    @Override
    protected AbstractSWTBot<ListElement> click() {
        waitForEnabled();
        notify(SWT.MouseEnter);
        notify(SWT.MouseMove);
        notify(SWT.Activate);
        notify(SWT.FocusIn);
        notify(SWT.MouseDown);
        notify(SWT.MouseUp);
        notify(SWT.Selection);
        notify(SWT.MouseHover);
        notify(SWT.MouseMove);
        notify(SWT.MouseExit);
        notify(SWT.Deactivate);
        notify(SWT.FocusOut);
        return this;
    }

}
