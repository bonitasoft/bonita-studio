package org.bonitasoft.studio.common.repository.provider;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

public class RepositoryLabelProvider extends StyledCellLabelProvider {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    public Color getBackground(final Object element) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    public Color getForeground(final Object element) {
        final IRepository repository = (IRepository) element;
        var project = Adapters.adapt(repository, BonitaProject.class);
        if (RepositoryManager.getInstance().getCurrentRepository().filter(element::equals).isPresent()) {
            return ColorConstants.gray;
        } else if (!ProductVersion.sameMinorVersion(project.getBonitaVersion())) {
            return ColorConstants.red;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
     */
    public Font getFont(final Object element) {
        return null;
    }

    public String getText(final IRepository element) {
        return IDisplayable.toDisplayName(element).orElseGet(element::getProjectId);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(final IRepository element) {
        return IDisplayable.adapt(element).map(IDisplayable::getIcon).orElse(null);
    }

    @Override
    public String getToolTipText(Object element) {
        IRepository repo = (IRepository) element;
        return repo.isShared()
                ? Messages.sharedWithGit
                : Messages.localRepository;
    }

    @Override
    public void update(final ViewerCell cell) {
        final IRepository element = (IRepository) cell.getElement();
        final StyledString styledString = new StyledString();

        styledString.append(getText(element), null);
        var project = Adapters.adapt(element, BonitaProject.class);
        styledString.append(String.format(" (Project Id: %s)", project.getId()), StyledString.COUNTER_STYLER);
        
        if (RepositoryManager.getInstance().getCurrentRepository().filter(element::equals).isPresent()) {
            styledString.append(" -- ", StyledString.QUALIFIER_STYLER);
            styledString.append(Messages.current, StyledString.DECORATIONS_STYLER);
        }
        cell.setForeground(getForeground(element));
        cell.setText(styledString.getString());
        cell.setImage(getImage(element));
        cell.setStyleRanges(styledString.getStyleRanges());
    }
}
