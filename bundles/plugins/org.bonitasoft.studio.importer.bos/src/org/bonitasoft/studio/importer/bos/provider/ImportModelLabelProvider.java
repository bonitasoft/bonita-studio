package org.bonitasoft.studio.importer.bos.provider;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractImportModel;
import org.bonitasoft.studio.importer.bos.model.ConflictStatus;
import org.bonitasoft.studio.importer.bos.model.IPresentable;
import org.bonitasoft.studio.importer.bos.model.LegacyStoreModel;
import org.bonitasoft.studio.importer.bos.provider.ImportModelStyler.ConflictStyler;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Image;

public class ImportModelLabelProvider extends LabelProvider implements IStyledLabelProvider, IToolTipProvider {

    private static final int START_OFFSET = 0;
    private final List<Image> toDispose = new ArrayList<>();
    private final ConflictStyler conflictStyler;
    private final Styler sameContentStyler;
    private Styler notImportedStyle;

    public ImportModelLabelProvider(ImportModelStyler styler) {
        this.conflictStyler = styler.createConflictStyler();
        this.sameContentStyler = styler.createSameContentStyler();
        this.notImportedStyle = styler.createNotImportedStyler();
    }

    @Override
    public StyledString getStyledText(Object element) {
        final String name = getText(element);
        final StyledString styledString = new StyledString(name);
        if (element instanceof AbstractFileModel
                && ((AbstractFileModel) element).getStatus() == ConflictStatus.CONFLICTING) {
            styledString.setStyle(START_OFFSET, name.length(), conflictStyler);
        }
        if (element instanceof AbstractFileModel
                && ((AbstractFileModel) element).getStatus() == ConflictStatus.SAME_CONTENT) {
            styledString.append(String.format(" (%s)", Messages.skipped));
            styledString.setStyle(START_OFFSET, styledString.length(), sameContentStyler);
        }
        if (element instanceof LegacyStoreModel) {
            styledString.setStyle(START_OFFSET, styledString.length(), notImportedStyle);
            styledString.append(String.format(" (%s)", Messages.legacyFormsNotImported));
        }
        return styledString;
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
    public void dispose() {
        super.dispose();
        conflictStyler.dispose();
        toDispose.stream().forEach(Image::dispose);
    }

    @Override
    public String getToolTipText(Object element) {
        if (element instanceof AbstractImportModel) {
            IStatus validationStatus = ((AbstractImportModel) element).getValidationStatus();
            if (!validationStatus.isOK()) {
                return validationStatus.getMessage();
            }
        }
        return null;
    }

}
