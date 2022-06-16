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

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class MessagePanel {

    private String id;
    private int status;
    private MessageControlSupplier<?> messageControlSupplier;

    public MessagePanel(String id, int status, MessageControlSupplier<?> messageControlSupplier) {
        this.id = id;
        this.status = status;
        this.messageControlSupplier = messageControlSupplier;
    }

    public void create(Composite container) {
        Composite panel = new Composite(container, SWT.NONE);
        panel.setData("id", id);
        panel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        panel.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        Label icon = new Label(panel, SWT.NONE);
        icon.setImage(statusImageDescriptor(status).createImage());
        icon.setLayoutData(GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.TOP).create());

        messageControlSupplier.create(panel);
    }

    private ImageDescriptor statusImageDescriptor(int status) {
        switch (status) {
            case IStatus.ERROR:
                return Pics.getImageDescriptor(PicsConstants.error);
            case IStatus.WARNING:
                return Pics.getImageDescriptor(PicsConstants.warning);
            default:
                return Pics.getImageDescriptor(PicsConstants.checkmark);
        }
    }

    public void dispose(Composite container) {
        findControl(id, container)
                .ifPresent(Control::dispose);
    }

    private Optional<Control> findControl(String id, Composite container) {
        return Stream.of(container.getChildren())
                .filter(c -> Objects.equals(c.getData("id"), id))
                .findFirst();
    }

    @FunctionalInterface
    public interface MessageControlSupplier<T extends Control> {

        T create(Composite parent);
        
    }

}
