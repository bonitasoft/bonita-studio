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
package org.bonitasoft.studio.application.ui.dialog;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.definition.preview.ChangePreview;
import org.bonitasoft.studio.application.operation.definition.preview.DefinitionUpdatePreviewResult;
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

public class DefinitionMigrationDialog extends MessageDialog {

    private static final int PROCEED_BUTTON_INDEX = 2;
    private DefinitionUpdatePreviewResult definitionUpdatePreview;

    public DefinitionMigrationDialog(Shell parentShell, DefinitionUpdatePreviewResult definitionUpdatePreview) {
        super(parentShell, Messages.definitionUpateTitle,
                null,
                Messages.definitionUpateMessage,
                MessageDialog.WARNING,
                PROCEED_BUTTON_INDEX,
                IDialogConstants.ABORT_LABEL, IDialogConstants.IGNORE_LABEL, IDialogConstants.PROCEED_LABEL);
        this.definitionUpdatePreview = definitionUpdatePreview;
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
            proceedButton.setEnabled(definitionUpdatePreview.containsUpdateWithoutBreakingChanges());
            return proceedButton;
        }
        return super.createButton(parent, id, label, defaultButton);
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
                .withImageProvider(DefinitionMigrationDialog::getImage)
                .withTooltipProvider(
                        change -> change.hasBreakingChanges() ? Messages.definitionUpdateWithBreakingChanges
                                : null)
                .createColumnLabelProvider());
        viewer.setInput(definitionUpdatePreview);

        ColumnViewerToolTipSupport.enableFor(viewer);

        return container;
    }

    private static Image getImage(ChangePreview change) {
        switch (change.getKind()) {
            case ADD:
                return Pics.getImage(PicsConstants.add_simple);
            case REMOVE:
                return Pics.getImage(PicsConstants.redMinus);
            default:
                Image updateImage = Pics.getImage(PicsConstants.updateDependencyHot);
                if (change.hasBreakingChanges()) {
                    return new DecorationOverlayIcon(updateImage,
                            UIPlugin.getImageDescriptor("icons/problem.gif"),
                            IDecoration.BOTTOM_RIGHT).createImage();
                }
                return updateImage;
        }
    }

    class ChangePreviewContentProvider implements ITreeContentProvider {

        @Override
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof DefinitionUpdatePreviewResult) {
                return ((DefinitionUpdatePreviewResult) inputElement).getChanges().toArray();
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
