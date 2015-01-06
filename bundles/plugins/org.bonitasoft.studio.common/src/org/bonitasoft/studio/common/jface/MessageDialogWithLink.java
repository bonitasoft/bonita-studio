/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import java.io.IOException;
import java.net.URI;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class MessageDialogWithLink extends MessageDialog {

    private final URI uri;

    public MessageDialogWithLink(final Shell shell, final String title, final Image image, final String message, final int kind, final String[] buttonLabels,
            final int defaultSelection, final URI uri) {
        super(shell, title, image, message, kind, buttonLabels, defaultSelection);
        this.uri = uri;
    }

    @Override
    protected Control createMessageArea(final Composite composite) {
        // create composite
        // create image
        final Image image = getImage();
        if (image != null) {
            imageLabel = new Label(composite, SWT.NULL);
            image.setBackground(imageLabel.getBackground());
            imageLabel.setImage(image);
            GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING)
                    .applyTo(imageLabel);
        }
        // create message
        if (message != null) {
            final Link messageLabel = new Link(composite, getMessageLabelStyle());
            messageLabel.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(final org.eclipse.swt.events.SelectionEvent e) {
                    try {
                        java.awt.Desktop.getDesktop().browse(uri);
                    } catch (final IOException e1) {
                        BonitaStudioLog.error(e1);
                    }

                };
            });
            messageLabel.setText(message);
            GridDataFactory
                    .fillDefaults()
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, false)
                    .hint(
                            convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                            SWT.DEFAULT).applyTo(messageLabel);
        }
        return composite;
    }


}
