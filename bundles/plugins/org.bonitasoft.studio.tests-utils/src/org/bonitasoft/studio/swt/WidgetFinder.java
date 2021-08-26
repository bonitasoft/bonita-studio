/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.swt;

import java.util.List;

import org.bonitasoft.studio.swt.finder.ControlFinder;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.finders.Finder;
import org.eclipse.swtbot.swt.finder.finders.MenuFinder;
import org.eclipse.swtbot.swt.finder.matchers.WithId;
import org.eclipse.swtbot.swt.finder.matchers.WithLabel;
import org.eclipse.swtbot.swt.finder.matchers.WithMnemonic;

public class WidgetFinder {

    public <T extends Widget> T withLabel(final Shell shell, String label) {
        final ControlFinder controlFinder = new ControlFinder(shell);
        return uniqueResult(controlFinder.findControls(WithLabel.<T> withLabel(label, new Finder(controlFinder, new MenuFinder()))));
    }

    public <T extends Widget> T withMnemonic(final Shell shell, String mnemonic) {
        return uniqueResult(new ControlFinder(shell).findControls(WithMnemonic.<T> withMnemonic(mnemonic)));
    }

    public <T extends Widget> T withId(final Shell shell, String id) {
        return uniqueResult(new ControlFinder(shell).findControls(WithId.<T> withId(id)));
    }


    private <T extends Widget> T uniqueResult(final List<T> results) {
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }


}
