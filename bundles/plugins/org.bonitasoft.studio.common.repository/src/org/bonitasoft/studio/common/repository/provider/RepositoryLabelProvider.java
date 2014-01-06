package org.bonitasoft.studio.common.repository.provider;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

public class RepositoryLabelProvider extends StyledCellLabelProvider {


    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    public Color getBackground(Object element) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    public Color getForeground(Object element) {
        IRepository repository = (IRepository) element ;
        if(RepositoryManager.getInstance().getCurrentRepository().equals(element)){
            return ColorConstants.gray;
        }else if (!ProductVersion.sameMinorVersion(repository.getVersion())){
            return ColorConstants.red;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
     */
    public Font getFont(Object element) {
        return null;
    }

    public String getText(IRepository element) {
        return element.getDispslayName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(IRepository element) {
        return element.getIcon();
    }

    @Override
    public void update(ViewerCell cell) {
        IRepository element = (IRepository) cell.getElement();
        StyledString styledString = new StyledString();

        styledString.append(getText(element), null);
        if(RepositoryManager.getInstance().getCurrentRepository().equals(element)){
            styledString.append(" -- ",StyledString.QUALIFIER_STYLER) ;
            styledString.append(Messages.current, StyledString.DECORATIONS_STYLER);
        }


        cell.setText(styledString.getString());
        cell.setImage(getImage(element)) ;
        cell.setStyleRanges(styledString.getStyleRanges());
    }
}