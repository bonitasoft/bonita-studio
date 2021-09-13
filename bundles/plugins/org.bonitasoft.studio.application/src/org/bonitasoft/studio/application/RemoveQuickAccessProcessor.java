/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application;


import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.menu.MTrimContribution;

/**
 * @author Romain Bioteau
 *
 */
public class RemoveQuickAccessProcessor {

    @Execute
    public void removeQuickAccess(final MApplication application) {
        final List<MTrimContribution> trimContributions = application.getTrimContributions();
        for (final MTrimContribution trimContribution : trimContributions) {
            if ("org.eclipse.ui.ide.application.trimcontribution.QuickAccess".equals(trimContribution.getElementId())) {
                trimContributions.remove(trimContribution);
                break;
            }
        }
    }

}
