/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.control.provider;

import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.ui.control.model.ApplicationDescriptorWithFileName;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

public class ApplicationDescriptorWithFileNameLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    @Override
    public void update(ViewerCell cell) {
        if (cell.getElement() instanceof ApplicationDescriptorWithFileName) {
            final ApplicationDescriptorWithFileName descriptor = (ApplicationDescriptorWithFileName) cell.getElement();
            final StyledString styledString = new StyledString();

            styledString.append(descriptor.getApplicationNode().getToken());
            styledString.append(" " + descriptor.getFileName(), StyledString.QUALIFIER_STYLER);

            cell.setText(styledString.getString());
            cell.setImage(getImage(cell.getElement()));

            cell.setStyleRanges(styledString.getStyleRanges());
        }
    }

    @Override
    public Image getImage(Object element) {
        return Pics.getImage(PicsConstants.application);
    }

    @Override
    public String getText(Object element) {
        return ((ApplicationDescriptorWithFileName) element).getApplicationNode().getToken();
    }

}
