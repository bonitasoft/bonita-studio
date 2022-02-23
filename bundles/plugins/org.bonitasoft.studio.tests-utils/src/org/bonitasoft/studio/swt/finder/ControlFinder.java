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
package org.bonitasoft.studio.swt.finder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.hamcrest.Matcher;

public class ControlFinder extends org.eclipse.swtbot.swt.finder.finders.ControlFinder {

    private final Shell activeShell;

    public ControlFinder(Shell activeShell) {
        this.activeShell = activeShell;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swtbot.swt.finder.finders.ControlFinder#activeShell()
     */
    @Override
    public Shell activeShell() {
        return activeShell;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swtbot.swt.finder.finders.ControlFinder#findControls(org.hamcrest.Matcher)
     */
    @Override
    public <T extends Widget> List<T> findControls(Matcher<T> matcher) {
        return findControls(activeShell(), matcher, true);
    }

    @Override
    public <T extends Widget> List<T> findControls(final Widget parentWidget, final Matcher<T> matcher, final boolean recursive) {
        return UIThreadRunnable.syncExec(display, new ListResult<T>() {

            @Override
            public List<T> run() {
                return findControlsInternal(parentWidget, matcher);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private <T extends Widget> List<T> findControlsInternal(Widget parentWidget, Matcher<T> matcher) {
        final LinkedHashSet<T> controls = new LinkedHashSet<T>();
        if (matcher.matches(parentWidget) && !controls.contains(parentWidget)) {
            try {
                controls.add((T) parentWidget);
            } catch (final ClassCastException exception) {
                throw new IllegalArgumentException("The specified matcher should only match against is declared type.", exception);
            }
        }
        final List<Widget> children = getChildrenResolver().getChildren(parentWidget);
        controls.addAll(findControlsInternal(children, matcher));
        return new ArrayList<T>(controls);
    }

    private <T extends Widget> List<T> findControlsInternal(final List<Widget> widgets, final Matcher<T> matcher) {
        final LinkedHashSet<T> list = new LinkedHashSet<T>();
        for (final Widget w : widgets) {
            list.addAll(findControlsInternal(w, matcher));
        }
        return new ArrayList<T>(list);
    }
}
