package org.bonitasoft.studio.importer.bos.provider;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.repository.model.IPresentable;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.importer.bos.BosArchiveImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractImportModel;
import org.bonitasoft.studio.importer.bos.model.LegacyStoreModel;
import org.bonitasoft.studio.importer.bos.provider.ImportModelStyler.ConflictStyler;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
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
        String name = getText(element);
        StyledString styledString = new StyledString(name);
        if (hasStatus(element, ConflictStatus.CONFLICTING)) {
            styledString.setStyle(START_OFFSET, name.length(), conflictStyler);
        }
        if (hasStatus(element, ConflictStatus.SAME_CONTENT)) {
            styledString.append(String.format(" (%s)", Messages.skipped));
            styledString.setStyle(START_OFFSET, styledString.length(), sameContentStyler);
        }
        if (element instanceof LegacyStoreModel) {
            styledString.setStyle(START_OFFSET, styledString.length(), notImportedStyle);
            styledString.append(String.format(" (%s)", Messages.legacyFormsNotImported));
        }
        return styledString;
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
        //Initialize image registry with default decorator (error, warning...etc)
        FieldDecorationRegistry.getDefault();
        switch (status.getSeverity()) {
            case IStatus.ERROR:
                return JFaceResources.getImageRegistry().getDescriptor("org.eclipse.jface.fieldassist.IMG_DEC_FIELD_ERROR");
            case IStatus.WARNING:
                return JFaceResources.getImageRegistry().getDescriptor("org.eclipse.jface.fieldassist.IMG_DEC_FIELD_WARNING");
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
            AbstractImportModel importModel = (AbstractImportModel) element;
            IStatus validationStatus = importModel.getValidationStatus();
            if (!validationStatus.isOK()) {
                if(validationStatus instanceof MultiStatus) {
                    StringBuilder sb = new StringBuilder();
                    Stream.of(validationStatus.getChildren())
                    .filter(s -> !s.isOK())
                    .map(s -> s.getMessage())
                    .filter(Objects::nonNull)
                    .filter(s -> !s.isEmpty())
                    .forEach(message -> {
                        sb.append(message);
                        sb.append(System.lineSeparator());
                        });
                    sb.delete(sb.lastIndexOf(System.lineSeparator()), sb.length());
                    return sb.toString();
                }
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

}
