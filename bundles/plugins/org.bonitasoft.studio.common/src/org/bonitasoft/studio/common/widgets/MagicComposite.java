/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class MagicComposite extends Composite {

    public static final String HIDDEN = "MagicComposite.HIDDEN";

    /**
     * @param parent
     * @param style
     */
    public MagicComposite(Composite parent, int style) {
        super(parent, style);
    }

    @Override
    public Control[] getChildren() {
        List<Control> res = new ArrayList<Control>();
        for (Control child : super.getChildren()) {
            Object isHidden = child.getData(HIDDEN);
            if (isHidden == null || !(Boolean)isHidden) {
                res.add(child);
            }
        }
        return res.toArray(new Control[res.size()]);
    }

    public void hide(Control child) {
        child.setData(HIDDEN, true);
        child.setVisible(false);
    }

    public void show(Control child) {
        child.setData(HIDDEN, false);
        child.setVisible(true);
    }

}