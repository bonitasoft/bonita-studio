package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.bpm.model.edit.ProcessEditPlugin;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.eclipse.core.runtime.Adapters;
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
        } else if (artifact instanceof FileStoreArtifact) {
            var displayable = Adapters.adapt(((FileStoreArtifact) artifact).getFileStore(), IDisplayable.class);
            if (displayable != null) {
                return displayable.getDisplayName();
            }
            return artifact.getName();
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
        if (artifact instanceof FileStoreArtifact) {
            var displayable = Adapters.adapt(((FileStoreArtifact) artifact).getFileStore(), IDisplayable.class);
            if (displayable != null) {
                return displayable.getIcon();
            }
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
            return styledString;
        } else if (artifact instanceof ProcessArtifact) {
            if (((ProcessArtifact) artifact).hasSingleVersion()) {
                ProcessVersion version = (ProcessVersion) ((ProcessArtifact) artifact).getLatestVersion();
                styledString.append("  " + version.getFileStore().getResource().getName(),
                        StyledString.DECORATIONS_STYLER);
            }
            return styledString;
        }
        if (artifact instanceof FileStoreArtifact) {
            var displayable = Adapters.adapt(((FileStoreArtifact) artifact).getFileStore(), IDisplayable.class);
            if (displayable != null) {
                return displayable.getStyledString();
            }
            return styledString;
        }
        return styledString;
    }

}
