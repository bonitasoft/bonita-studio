package org.bonitasoft.studio.common.repository.ui.viewer;

import org.bonitasoft.studio.common.ui.IDisplayable;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Baptiste Mesta
 */
public class RepositoryTreeLabelProvider extends LabelProvider {

    private final boolean showImages;

    public RepositoryTreeLabelProvider() {
        this(true);
    }

    public RepositoryTreeLabelProvider(final boolean showImages) {
        super();
        this.showImages = showImages;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        IDisplayable displayable = Adapters.adapt(element, IDisplayable.class);
        if (displayable != null) {
            return displayable.getDisplayName();
        } else {
            return super.getText(element);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        if (showImages) {
            IDisplayable displayable = Adapters.adapt(element, IDisplayable.class);
            if (displayable != null) {
                return displayable.getIcon();
            } else {
                return super.getImage(element);
            }
        }
        return super.getImage(element);
    }

}
