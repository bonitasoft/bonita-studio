/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bos.wizard;

import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.swt.widgets.Composite;

public class MessagePanelEventHandler implements IValueChangeListener<Boolean> {

    private Composite container;
    private MessagePanel messagePanel;

    public MessagePanelEventHandler(Composite container, MessagePanel messagePanel) {
        this.container = container;
        this.messagePanel = messagePanel;
    }

    @Override
    public void handleValueChange(ValueChangeEvent<? extends Boolean> event) {
        if (!container.isDisposed()) {
            if (event.diff.getNewValue()) {
                messagePanel.create(container);
            } else {
                messagePanel.dispose(container);
            }
            container.layout();
        }
    }

}
