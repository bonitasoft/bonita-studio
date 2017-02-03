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
import org.bonitasoft.studio.importer.bos.provider.ImportModelStyler.ConflictStyler;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

public class ImportModelLabelProvider extends LabelProvider implements IStyledLabelProvider {

    private static final int START_OFFSET = 0;
    private final List<Image> toDispose = new ArrayList<>();
    private final ConflictStyler conflictStyler;
    private final Styler sameContentStyler;

    public ImportModelLabelProvider(ImportModelStyler styler) {
        this.conflictStyler = styler.createConflictStyler();
        this.sameContentStyler = styler.createSameContentStyler();
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
        final Image image = ((IPresentable) element).getImage();
        if (image != null && element instanceof AbstractImportModel && ((AbstractImportModel) element).isConflicting()) {
            final Image overlayImage = createConflictOverlay(image).createImage();
            toDispose.add(overlayImage);
            return overlayImage;
        }
        return image;
    }

    protected DecorationOverlayIcon createConflictOverlay(final Image image) {
        PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
        return new DecorationOverlayIcon(image,
                Pics.getImageDescriptor("problem.gif", BosArchiveImporterPlugin.getDefault()),
                IDecoration.BOTTOM_RIGHT);
    }

    @Override
    public void dispose() {
        super.dispose();
        conflictStyler.dispose();
        toDispose.stream().forEach(Image::dispose);
    }

}
