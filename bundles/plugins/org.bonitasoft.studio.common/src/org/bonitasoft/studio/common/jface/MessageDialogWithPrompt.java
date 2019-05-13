/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import java.util.Optional;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.Section;

/**
 * @author Romain Bioteau
 */
public class MessageDialogWithPrompt extends MessageDialogWithToggle {

    private String detailsMessage;
    private boolean withToggle = true;
    private Optional<Listener> linkSelectionListener = Optional.empty();

    public MessageDialogWithPrompt(Shell parentShell, String dialogTitle,
            Image image, String message, int dialogImageType,
            String[] dialogButtonLabels, int defaultIndex,
            String toggleMessage, boolean toggleState) {
        this(parentShell, dialogTitle, image, message, null, dialogImageType,
                dialogButtonLabels, defaultIndex, toggleMessage, toggleState);
    }

    MessageDialogWithPrompt(Shell parentShell,
            String dialogTitle,
            Image image, String message,
            Listener linkSelectionListener,
            int dialogImageType,
            String[] dialogButtonLabels,
            int defaultIndex,
            String toggleMessage,
            boolean toggleState) {
        super(parentShell, dialogTitle, image, message, dialogImageType,
                dialogButtonLabels, defaultIndex, toggleMessage, toggleState);
        this.linkSelectionListener = Optional.ofNullable(linkSelectionListener);
        this.withToggle = toggleMessage != null;
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
            linkSelectionListener.ifPresent(listener -> messageLabel.addListener(SWT.Selection, listener));
            messageLabel.setText(message);
            GridDataFactory
                    .fillDefaults()
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, false)
                    .hint(
                            convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                            SWT.DEFAULT)
                    .applyTo(messageLabel);
        }
        return composite;
    }

    @Override
    protected Button createToggleButton(Composite parent) {
        return withToggle ? super.createToggleButton(parent) : null;
    }

    @Override
    protected void setToggleButton(Button button) {
        if (withToggle) {
            super.setToggleButton(button);
        }
    }

    @Override
    protected void setToggleMessage(String message) {
        if (withToggle) {
            super.setToggleMessage(message);
        }
    }

    @Override
    public void setToggleState(boolean toggleState) {
        if (withToggle) {
            super.setToggleState(toggleState);
        }
    }

    public static MessageDialogWithPrompt openOkCancelConfirm(Shell parent,
            String title, String message, String toggleMessage,
            boolean toggleState, IPreferenceStore store, String key) {
        return open(CONFIRM, parent, title, message, toggleMessage, toggleState, store, key, SWT.NONE);
    }

    public static MessageDialogWithPrompt open(int kind, Shell parent, String title,
            String message,
            Listener linkSelectionListener,
            String toggleMessage,
            boolean toggleState,
            IPreferenceStore store,
            String key,
            int style) {
        final MessageDialogWithPrompt dialog = new MessageDialogWithPrompt(parent,
                title,
                null, // accept the default window icon
                message,
                linkSelectionListener,
                kind,
                getButtonLabelsFor(kind),
                0,
                toggleMessage,
                toggleState);
        style &= SWT.SHEET;
        dialog.setShellStyle(dialog.getShellStyle() | style);
        dialog.setPrefStore(store);
        dialog.setPrefKey(key);
        dialog.open();
        return dialog;
    }

    public static MessageDialogWithPrompt open(int kind, Shell parent, String title,
            String message, String toggleMessage, boolean toggleState,
            IPreferenceStore store, String key, int style) {
        final MessageDialogWithPrompt dialog = new MessageDialogWithPrompt(parent,
                title, null, // accept the default window icon
                message, kind, getButtonLabelsFor(kind), 0,
                toggleMessage, toggleState);
        style &= SWT.SHEET;
        dialog.setShellStyle(dialog.getShellStyle() | style);
        dialog.setPrefStore(store);
        dialog.setPrefKey(key);
        dialog.open();
        return dialog;
    }

    public static MessageDialogWithPrompt openWithDetails(int kind,
            Shell parent,
            String title,
            String message,
            String toggleMessage,
            String detailMessage,
            boolean toggleState,
            IPreferenceStore store,
            String key,
            int style) {
        final MessageDialogWithPrompt dialog = new MessageDialogWithPrompt(parent,
                title, null, // accept the default window icon
                message, kind, getButtonLabelsFor(kind), 0,
                toggleMessage, toggleState);
        style &= SWT.SHEET;
        dialog.setShellStyle(dialog.getShellStyle() | style);
        dialog.setPrefStore(store);
        dialog.setPrefKey(key);
        dialog.setDetails(detailMessage);
        dialog.open();
        return dialog;
    }

    public static MessageDialogWithPrompt openWithDetails(int kind,
            Shell parent,
            String title,
            String message,
            String detailMessage,
            Listener linkSelectionListener,
            int style) {
        MessageDialogWithPrompt dialog = new MessageDialogWithPrompt(parent,
                title,
                null,
                message,
                linkSelectionListener,
                kind,
                getButtonLabelsFor(kind),
                0,
                null,
                false);
        style &= SWT.SHEET;
        dialog.setShellStyle(dialog.getShellStyle() | style);
        dialog.setDetails(detailMessage);
        dialog.open();
        return dialog;
    }

    public void setDetails(String detailsMessage) {
        this.detailsMessage = detailsMessage;
    }

    protected static String[] getButtonLabelsFor(int kind) {
        String[] dialogButtonLabels;
        switch (kind) {
            case ERROR:
            case INFORMATION:
            case WARNING: {
                dialogButtonLabels = new String[] { IDialogConstants.OK_LABEL };
                break;
            }
            case CONFIRM: {
                dialogButtonLabels = new String[] { IDialogConstants.OK_LABEL,
                        IDialogConstants.CANCEL_LABEL };
                break;
            }
            case QUESTION: {
                dialogButtonLabels = new String[] { IDialogConstants.YES_LABEL,
                        IDialogConstants.NO_LABEL };
                break;
            }
            case QUESTION_WITH_CANCEL: {
                dialogButtonLabels = new String[] { IDialogConstants.YES_LABEL,
                        IDialogConstants.NO_LABEL,
                        IDialogConstants.CANCEL_LABEL };
                break;
            }
            default: {
                throw new IllegalArgumentException("Illegal value for kind in MessageDialog.open()"); //$NON-NLS-1$
            }
        }
        return dialogButtonLabels;
    }

    @Override
    protected void buttonPressed(int buttonId) {
        super.buttonPressed(buttonId);

        final boolean toggleState = getToggleState();
        final IPreferenceStore prefStore = getPrefStore();
        final String prefKey = getPrefKey();
        if (buttonId != IDialogConstants.CANCEL_ID
                && prefStore != null && prefKey != null) {
            switch (buttonId) {
                case IDialogConstants.YES_ID:
                case IDialogConstants.YES_TO_ALL_ID:
                case IDialogConstants.PROCEED_ID:
                case IDialogConstants.OK_ID:
                    prefStore.setValue(prefKey, toggleState);
                    break;
                case IDialogConstants.NO_ID:
                case IDialogConstants.NO_TO_ALL_ID:
                    break;
            }
        }
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        if (detailsMessage != null) {
            parent.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).create());
            //Above Image filler
            Image image = getImage();
            if (image != null) {
                Label filler = new Label(parent, SWT.NULL);
                filler.setImage(image);
                GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING)
                        .applyTo(filler);
                filler.setVisible(false);
            }

            Section section = new Section(parent,
                    Section.TWISTIE | Section.NO_TITLE_FOCUS_BOX | Section.CLIENT_INDENT);
            section.setText(Messages.moreDetails);
            section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

            Composite client = new Composite(section, SWT.NONE);
            client.setLayoutData(GridDataFactory.fillDefaults().create());
            client.setLayout(GridLayoutFactory.fillDefaults().create());

            Link detailsLabel = new Link(client, getMessageLabelStyle());
            linkSelectionListener.ifPresent(listener -> detailsLabel.addListener(SWT.Selection, listener));
            detailsLabel.setText(detailsMessage);
            GridDataFactory
                    .fillDefaults()
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, false)
                    .hint(convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                            SWT.DEFAULT)
                    .applyTo(detailsLabel);
            section.setClient(client);
            section.addExpansionListener(new ExpansionAdapter() {

                @Override
                public void expansionStateChanged(ExpansionEvent e) {
                    parent.getShell().pack();
                }
            });
            return section;
        }
        return super.createCustomArea(parent);
    }
}
