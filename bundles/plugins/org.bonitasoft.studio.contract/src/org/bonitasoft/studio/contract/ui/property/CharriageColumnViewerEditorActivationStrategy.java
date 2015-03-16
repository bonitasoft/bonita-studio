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
package org.bonitasoft.studio.contract.ui.property;

import java.util.EventObject;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;


/**
 * @author Romain Bioteau
 *
 */
public class CharriageColumnViewerEditorActivationStrategy extends ColumnViewerEditorActivationStrategy {

    public CharriageColumnViewerEditorActivationStrategy(final ColumnViewer viewer) {
        super(viewer);
    }

    @Override
    protected boolean isEditorActivationEvent(final ColumnViewerEditorActivationEvent event) {
        //Activate cell editor on Enter
        return super.isEditorActivationEvent(event)
                || enterKeyPressed(event);
    }

    protected boolean enterKeyPressed(final ColumnViewerEditorActivationEvent event) {
        final EventObject sourceEvent = event.sourceEvent;
        //event must not be cancelled
        if (sourceEvent instanceof KeyEvent && ((KeyEvent) sourceEvent).doit) {
            return event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR;
        }
        return false;
    }
}
