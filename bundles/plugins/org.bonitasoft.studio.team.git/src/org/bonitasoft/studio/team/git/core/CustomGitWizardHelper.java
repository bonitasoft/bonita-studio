/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.git.core;

import org.eclipse.egit.ui.internal.UIText;
import org.eclipse.egit.ui.internal.push.PushBranchPage;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Widget;

public class CustomGitWizardHelper {

    public static void removeAdvancedPushLink(IWizard wizard) {
        PushBranchPage page = (PushBranchPage) wizard.getPage(UIText.PushBranchPage_PageName);
        Control control = page.getControl();
        if (control instanceof Composite) {
            Link link = searchWidget((Composite) control, Link.class);
            if (link != null) {
                link.dispose();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Widget> T searchWidget(Composite parent, Class<T> type) {
        for (Control c : parent.getChildren()) {
            if (c.getClass().isAssignableFrom(type)) {
                return (T) c;
            }
            if (c instanceof Composite) {
                T r = searchWidget((Composite) c, type);
                if (r != null) {
                    return r;
                }
            }
        }
        return null;
    }

}
