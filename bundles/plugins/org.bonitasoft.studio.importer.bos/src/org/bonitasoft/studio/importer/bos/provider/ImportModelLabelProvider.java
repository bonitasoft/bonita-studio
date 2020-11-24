package org.bonitasoft.studio.importer.bos.provider;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.repository.model.IPresentable;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractImportModel;
import org.bonitasoft.studio.importer.bos.model.LegacyStoreModel;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImportModelLabelProvider extends ColumnLabelProvider {

    private final List<Image> toDispose = new ArrayList<>();
    private Color conflictColor;
    private Color notImportedColor;

    @Override
    protected void initialize(ColumnViewer viewer, ViewerColumn column) {
        super.initialize(viewer, column);
        LocalResourceManager localResourceManager = new LocalResourceManager(JFaceResources.getResources(),
                viewer.getControl());
        conflictColor = localResourceManager.createColor(ColorConstants.ERROR_RGB);
        notImportedColor = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
        viewer.getColumnViewerEditor().addEditorActivationListener(refreshAllAfterEdit(viewer));
    }

    private ColumnViewerEditorActivationListener refreshAllAfterEdit(ColumnViewer viewer) {
        return new ColumnViewerEditorActivationListener() {

            @Override
            public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
            }

            @Override
            public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
            }

            @Override
            public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                if (viewer.getInput() instanceof Collection) {
                    viewer.getControl().getDisplay().asyncExec(
                            () -> {
                                if (viewer != null && !viewer.getControl().isDisposed()
                                        && viewer.getInput() != null) {
                                    viewer.update(((Collection<Object>) viewer.getInput()).toArray(), null);
                                }
                            });
                }
            }

            @Override
            public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
            }
        };
    }

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        cell.setText(getText(element));
        cell.setImage(getImage(element));

        if (hasStatus(element, ConflictStatus.CONFLICTING)) {
            cell.setForeground(conflictColor);
        }
        if (hasStatus(element, ConflictStatus.SAME_CONTENT)) {
            cell.setForeground(notImportedColor);
        }
        if (element instanceof LegacyStoreModel) {
            cell.setForeground(notImportedColor);
        }
    }

    public boolean hasStatus(Object element, ConflictStatus status) {
        if (element instanceof AbstractFileModel) {
            return Objects.equals(((AbstractFileModel) element).getStatus(), status);
        }
        if (element instanceof SmartImportableUnit) {
            return Objects.equals(((SmartImportableUnit) element).getConflictStatus(), status);
        }
        return false;
    }

    @Override
    public String getText(Object element) {
        checkArgument(element instanceof IPresentable);
        return ((IPresentable) element).getText();
    }

    @Override
    public Image getImage(Object element) {
        checkArgument(element instanceof IPresentable);
        Image image = ((IPresentable) element).getImage();
        if (image != null && element instanceof AbstractImportModel) {
            AbstractImportModel modelElement = (AbstractImportModel) element;
            if (modelElement.isConflicting()) {
                image = createConflictOverlay(image).createImage();
                toDispose.add(image);
            }
            IStatus validationStatus = modelElement.getValidationStatus();
            if (!validationStatus.isOK()) {
                image = createStatusOverlay(image, validationStatus).createImage();
                toDispose.add(image);
            }
        }
        return image;
    }

    protected DecorationOverlayIcon createConflictOverlay(final Image image) {
        return new DecorationOverlayIcon(image,
                Pics.getImageDescriptor("problem.gif", BosArchiveImporterPlugin.getDefault()),
                IDecoration.BOTTOM_RIGHT);
    }

    protected DecorationOverlayIcon createStatusOverlay(final Image image, IStatus status) {
        return new DecorationOverlayIcon(image,
                statusDecorator(status),
                IDecoration.BOTTOM_LEFT);
    }

    protected ImageDescriptor statusDecorator(IStatus status) {
        switch (status.getSeverity()) {
            case IStatus.ERROR:
                return JFaceResources.getImageRegistry().getDescriptor("org.eclipse.jface.fieldassist.IMG_DEC_FIELD_ERROR");
            case IStatus.WARNING:
                return JFaceResources.getImageRegistry()
                        .getDescriptor("org.eclipse.jface.fieldassist.IMG_DEC_FIELD_WARNING");
            default:
                return null;
        }
    }

    @Override
    public String getToolTipText(Object element) {
        if (element instanceof AbstractImportModel) {
            AbstractImportModel importModel = (AbstractImportModel) element;
            IStatus validationStatus = importModel.getValidationStatus();
            if (!validationStatus.isOK()) {
                return validationStatus.getMessage();
            }
            if (element instanceof AbstractFileModel) {
                if (importModel.isConflicting()) {
                    return String.format(Messages.conflictingArtifactTooltip, importModel.getText());
                }
                if (importModel.hasSameContent()) {
                    return String.format(Messages.identicalArtifactTooltip, importModel.getText());
                }
                return String.format(Messages.importedArtifactTooltip, importModel.getText());
            }
        } else if (element instanceof SmartImportableUnit) {
            return ((SmartImportableUnit) element).getToolTipText();
        }
        return null;
    }

    @Override
    public void dispose() {
        super.dispose();
        toDispose.forEach(Image::dispose);
    }

}
