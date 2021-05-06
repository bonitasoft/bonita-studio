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
package org.bonitasoft.studio.application.operation.extension.participant.preview;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.definition.preview.DefinitionRemovedChange;
import org.bonitasoft.studio.application.operation.extension.participant.definition.preview.DefinitionVersionUpdateChange;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.UIPlugin;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class PreviewConfirmationDialog extends MessageDialog {

    private static final int PROCEED_BUTTON_INDEX = 2;
    private PreviewResult previewResult;

    public PreviewConfirmationDialog(Shell parentShell, PreviewResult previewResult) {
        super(parentShell, Messages.updateProcessesTitle,
                null,
                getDialogMessage(previewResult),
                MessageDialog.WARNING,
                PROCEED_BUTTON_INDEX,
                IDialogConstants.ABORT_LABEL, IDialogConstants.IGNORE_LABEL, IDialogConstants.PROCEED_LABEL);
        this.previewResult = previewResult;
    }

    private static String getDialogMessage(PreviewResult previewResult) {
        String header = isConnectorDefinitionUpdate(previewResult) ? Messages.definitionUpateMessage : Messages.otherDependencyUpdateMessage;
        return header + "  " + Messages.updateActionsMessage;
    }

    private static boolean isConnectorDefinitionUpdate(PreviewResult previewResult) {
        return previewResult.getChanges().stream().anyMatch(c -> c instanceof DefinitionVersionUpdateChange || c instanceof DefinitionRemovedChange);
    }

    @Override
    protected void setShellStyle(int newShellStyle) {
        super.setShellStyle(SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL  | getDefaultOrientation());
    }
    
    @Override
    protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
        if (IDialogConstants.ABORT_LABEL.equals(label)) {
            return super.createButton(parent, IDialogConstants.ABORT_ID, label, defaultButton);
        }
        if (IDialogConstants.IGNORE_LABEL.equals(label)) {
            return super.createButton(parent, IDialogConstants.IGNORE_ID, label, defaultButton);
        }
        if (IDialogConstants.PROCEED_LABEL.equals(label)) {
            Button proceedButton = super.createButton(parent, IDialogConstants.PROCEED_ID, label, defaultButton);
            proceedButton.setEnabled(previewResult.canProceed());
            return proceedButton;
        }
        return super.createButton(parent, id, label, defaultButton);
    }
    
    @Override
    protected boolean canHandleShellCloseEvent() {
        return false;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        container.setLayout(GridLayoutFactory.fillDefaults().create());

        TreeViewer viewer = new TreeViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getControl().setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true)
                .hint(SWT.DEFAULT, 200)
                .create());

        viewer.setContentProvider(new ChangePreviewContentProvider());
        viewer.setLabelProvider(new LabelProviderBuilder<ChangePreview>()
                .withTextProvider(ChangePreview::getDescription)
                .withImageProvider(PreviewConfirmationDialog::getImage)
                .withTooltipProvider(
                        change -> change.hasBreakingChanges() ? 
                                Messages.definitionUpdateWithBreakingChanges
                                : null)
                .createColumnLabelProvider());
        viewer.setInput(previewResult);

        ColumnViewerToolTipSupport.enableFor(viewer);

        return container;
    }

    private static Image getImage(ChangePreview change) {
        switch (change.getKind()) {
            case ADD:
                return addProblemDecorator(change, Pics.getImage(PicsConstants.add_simple));
            case REMOVE:
                return addProblemDecorator(change, Pics.getImage(PicsConstants.redMinus));
            default:
                return addProblemDecorator(change, Pics.getImage(PicsConstants.updateDependencyHot));
        }
    }

    private static Image addProblemDecorator(ChangePreview change, Image image) {
        if (change.hasBreakingChanges()) {
            return new DecorationOverlayIcon(image,
                    UIPlugin.getImageDescriptor("icons/problem.gif"),
                    IDecoration.BOTTOM_RIGHT).createImage();
        }
        return image;
    }

    class ChangePreviewContentProvider implements ITreeContentProvider {

        @Override
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof PreviewResult) {
                return ((PreviewResult) inputElement).getChanges().stream()
                        .filter(ChangePreview::showInPreviewDialog)
                        .toArray();
            }
            return new Object[0];
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof ChangePreview) {
                return ((ChangePreview) parentElement).getDetails().toArray();
            }
            return new Object[0];
        }

        @Override
        public Object getParent(Object element) {
            if (element instanceof ChangePreview) {
                return ((ChangePreview) element).getParent();
            }
            return null;
        }

        @Override
        public boolean hasChildren(Object element) {
            if (element instanceof ChangePreview) {
                return !((ChangePreview) element).getDetails().isEmpty();
            }
            return false;
        }

    }

}
