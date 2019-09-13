/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.dialog;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.ui.provider.TypedLabelProvider;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class MultiStatusDialog extends ProblemsDialog<IStatus> {

    private MultiStatus status;
    private Predicate<IStatus> canFinish;
    private int finishId;
    private int level = STATUS_LEVEL.get(IStatus.OK);
    private static final Map<Integer, Integer> STATUS_LEVEL = new HashMap<>();

    static {
        STATUS_LEVEL.put(IStatus.CANCEL, 0);
        STATUS_LEVEL.put(IStatus.ERROR, 1);
        STATUS_LEVEL.put(IStatus.WARNING, 2);
        STATUS_LEVEL.put(IStatus.INFO, 3);
        STATUS_LEVEL.put(IStatus.OK, 4);
    }

    public MultiStatusDialog(Shell parentShell, String dialogTitle,
            String dialogMessage,
            int dialogImageType,
            String[] dialogButtonLabels,
            MultiStatus status) {
        super(parentShell, dialogTitle, dialogMessage, dialogImageType, dialogButtonLabels);
        this.status = status;
    }

    public MultiStatusDialog(Shell parentShell, String dialogTitle,
            String dialogMessage,
            int dialogImageType,
            String[] dialogButtonLabels,
            MultiStatus status,
            int finishId,
            Predicate<IStatus> canFinish) {
        super(parentShell, dialogTitle, dialogMessage, dialogImageType, dialogButtonLabels);
        this.status = status;
        this.canFinish = canFinish;
        this.finishId = finishId;
    }

    @Override
    protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
        Button button = super.createButton(parent, id, label, defaultButton);
        if (finishId == id && canFinish != null) {
            button.setEnabled(canFinish.test(status));
        }
        return button;
    }

    public MultiStatusDialog(Shell shell, String dialogTitle, String deployStatusMessage, String[] dialogButtonLabels,
            MultiStatus status) {
        this(shell, dialogTitle, deployStatusMessage, dialogImageType(status), dialogButtonLabels, status);
    }

    private static int dialogImageType(MultiStatus status) {
        switch (status.getSeverity()) {
            case IStatus.ERROR:
                return MessageDialog.ERROR;
            case IStatus.WARNING:
                return MessageDialog.WARNING;
            case IStatus.INFO:
            default:
                return MessageDialog.INFORMATION;
        }
    }

    @Override
    protected TypedLabelProvider<IStatus> getTypedLabelProvider() {
        return new TypedLabelProvider<IStatus>() {

            @Override
            public String getText(IStatus element) {
                return element.getMessage();
            }

            @Override
            public Image getImage(IStatus element) {
                switch (element.getSeverity()) {
                    case IStatus.WARNING:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
                    case IStatus.ERROR:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
                    case IStatus.INFO:
                    default:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
                }
            }
        };
    }

    @Override
    protected ViewerComparator getComparator() {
        return null;
    }

    @Override
    protected Collection<IStatus> getInput() {
        return Stream
                .of(status.getChildren())
                .map(this::getChildren)
                .flatMap(Collection::stream)
                .filter(s -> STATUS_LEVEL.get(s.getSeverity()) <= level)
                .sorted(new StatusSeverityComparator())
                .collect(Collectors.toList());
    }

    private List<IStatus> getChildren(IStatus status) {
        if (status instanceof MultiStatus) {
            return Arrays.asList(status.getChildren()).stream()
                    .map(this::getChildren)
                    .flatMap(Collection::stream)
                    .filter(s -> STATUS_LEVEL.get(s.getSeverity()) <= level)
                    .sorted(new StatusSeverityComparator())
                    .collect(Collectors.toList());
        }
        return Arrays.asList(status);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    static class StatusSeverityComparator implements Comparator<IStatus> {

        @Override
        public int compare(IStatus s1, IStatus s2) {
            return STATUS_LEVEL.get(s1.getSeverity()).compareTo(STATUS_LEVEL.get(s2.getSeverity()));
        }

    }

}
