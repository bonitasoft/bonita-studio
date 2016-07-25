/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.gmf;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.NonResizableLabelEditPolicy;


public class CustomNonResizableLabelEditPolicy extends NonResizableLabelEditPolicy {

    @Override
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        if (moveNotAllowed(request)) {
            return UnexecutableCommand.INSTANCE;
        }
        return super.getMoveCommand(request);
    }

    private boolean moveNotAllowed(final ChangeBoundsRequest request) {
        return !ChangeBoundsRequestUtil.isMovingToAnotherProcess(getHost(), request);
    }

}

