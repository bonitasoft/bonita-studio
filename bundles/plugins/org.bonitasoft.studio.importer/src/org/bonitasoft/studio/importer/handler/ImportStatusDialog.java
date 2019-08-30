/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.handler;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.importer.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.google.common.base.Predicate;

public class ImportStatusDialog extends MessageDialog {

    private final IStatus importStatus;

    public ImportStatusDialog(final Shell parentShell, final IStatus importStatus, String message, final boolean canOpen) {
        super(parentShell, org.bonitasoft.studio.importer.i18n.Messages.importResultTitle, null,
                message,
                importStatus.isOK() ? MessageDialog.INFORMATION : NONE,
                getLabels(canOpen,importStatus), 0);
        this.importStatus = importStatus;
    }
    

    @Override
    protected Point getInitialSize() {
        return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
    }

    protected static String[] getLabels(final boolean canOpen, IStatus status) {
        List<String> buttons =new ArrayList<>();
        if(canOpen) {
            buttons.add(Messages.seeDetails);
        } 
        if(status.getSeverity() != IStatus.ERROR) {
            buttons.add(Messages.deploy);
        }
        buttons.add(IDialogConstants.OK_LABEL);
        if(!status.isOK()) {
            buttons.add( Messages.copyToClipboard);
        }
        return buttons.toArray(new String[] {});
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        if (importStatus.isOK()) {
            return super.createCustomArea(parent);
        }
        final TableViewer problemsViewer = new TableViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        problemsViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).indent(0, 10).create());
        problemsViewer.setContentProvider(ArrayContentProvider.getInstance());
        problemsViewer.setComparator(new ViewerComparator() {
            @Override
            public int compare(Viewer viewer, Object e1, Object e2) {
                return Integer.compare(((IStatus) e2).getSeverity(), ((IStatus) e1).getSeverity());
            }
        });
        problemsViewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object element) {
                return ((IStatus) element).getMessage();
            }

            @Override
            public Image getImage(Object element) {
                switch (((IStatus) element).getSeverity()) {
                    case IStatus.ERROR:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
                    case IStatus.WARNING:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
                    case IStatus.INFO:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
                    default:
                        break;
                }
                return null;
            }
        });
        if (importStatus instanceof MultiStatus) {
            problemsViewer.setInput(newArrayList(filter(newArrayList(importStatus.getChildren()), notOk())));
        }

        return problemsViewer.getControl();
    }

    private Predicate<IStatus> notOk() {
        return new Predicate<IStatus>() {

            @Override
            public boolean apply(IStatus status) {
                return !status.isOK() || status.getSeverity() == IStatus.INFO;
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.MessageDialog#buttonPressed(int)
     */
    @Override
    protected void buttonPressed(int buttonId) {
        if (IDialogConstants.NO_ID != buttonId) {
            super.buttonPressed(buttonId);
        }
    }

    @Override
    protected Button createButton(final Composite parent, final int id, final String label, final boolean defaultButton) {
        if (Messages.seeDetails.equals(label)) {
            return super.createButton(parent, IDialogConstants.OPEN_ID, label, defaultButton);
        }
        if (Messages.deploy.equals(label)) {
            return super.createButton(parent, IDialogConstants.PROCEED_ID, label, defaultButton);
        }
        if (Messages.copyToClipboard.equals(label)) {
            final Button copyButton = super.createButton(parent, IDialogConstants.NO_ID, label, defaultButton);
            copyButton.addSelectionListener(new SelectionAdapter() {

                /*
                 * (non-Javadoc)
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                @Override
                public void widgetSelected(SelectionEvent e) {
                    final Clipboard cb = new Clipboard(Display.getDefault());
                    final TextTransfer textTransfer = TextTransfer.getInstance();
                    cb.setContents(new Object[] { statusMessages() },
                            new Transfer[] { textTransfer });
                }
            });
            return copyButton;
        }
        return super.createButton(parent, id, label, defaultButton);
    }

    protected String statusMessages() {
        if (importStatus instanceof MultiStatus) {
            return createMessageForMultiStatus((MultiStatus) importStatus);
        }
        return importStatus.getMessage();
    }

    protected String createMessageForMultiStatus(final MultiStatus status) {
        final StringBuilder sb = new StringBuilder();
        for (final IStatus childStatus : status.getChildren()) {
            if (!childStatus.isOK()) {
                sb.append(childStatus.getMessage());
                sb.append(SWT.CR);
            }
        }
        return sb.toString();
    }

}
