package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.model.edit.ProcessEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class ArtifactDisplayable implements IDisplayable {

    private Artifact artifact;

    public ArtifactDisplayable(Artifact artifact) {
        this.artifact = artifact;
    }

    @Override
    public String getDisplayName() {
        if (artifact instanceof ProcessVersion) {
            return ((ProcessVersion) artifact).getVersion();
        } else {
            return artifact.getName();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.IDisplayable#getIcon()
     */
    @Override
    public Image getIcon() {
        if (artifact instanceof ProcessArtifact) {
            return ExtendedImageRegistry.INSTANCE
                    .getImage(ProcessEditPlugin.INSTANCE.getPluginResourceLocator().getImage("full/obj16/Pool"));
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.IDisplayable#getStyledString()
     */
    @Override
    public StyledString getStyledString() {
        StyledString styledString = IDisplayable.super.getStyledString();
        if (artifact instanceof ProcessVersion) {
            styledString.append("  " + ((ProcessVersion) artifact).getFileStore().getResource().getName(),
                    StyledString.DECORATIONS_STYLER);
        } else if (artifact instanceof ProcessArtifact) {
            if (((ProcessArtifact) artifact).hasSingleVersion()) {
                ProcessVersion version = (ProcessVersion) ((ProcessArtifact) artifact).getLatestVersion();
                styledString.append("  " + version.getFileStore().getResource().getName(),
                        StyledString.DECORATIONS_STYLER);
            }
        }
        return styledString;
    }

}
