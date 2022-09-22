/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.wizard;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.PathTemplate;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

public class RestAPIExtensionLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    @Override
    public String getToolTipText(final Object element) {
        return null;
    }

    @Override
    public void update(final ViewerCell cell) {
        if (cell.getElement() instanceof CustomPageProjectFileStore) {
            final CustomPageProjectFileStore fileStore = (CustomPageProjectFileStore) cell.getElement();
            final StyledString styledString = new StyledString();
            styledString.append(fileStore.getDisplayName());
            if (cell.getElement() instanceof RestAPIExtensionFileStore) {
                styledString.append(" ");

                List<PathTemplate> pathTemplates;
                try {
                    pathTemplates = ((RestAPIExtensionFileStore)fileStore).getContent().getPathTemplates();
                } catch (ReadFileStoreException e) {
                    pathTemplates = Collections.emptyList();
                }
                for (final PathTemplate template : pathTemplates) {
                    styledString.append("../API/extension/" + template.toString(), StyledString.COUNTER_STYLER);
                    if (pathTemplates.indexOf(template) != pathTemplates.size() - 1) {
                        styledString.append(", ", StyledString.COUNTER_STYLER);
                    }
                }
            }
            cell.setText(styledString.getString());
            cell.setStyleRanges(styledString.getStyleRanges());
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        // only for filtered Tree
        return ((RestAPIExtensionFileStore) element).getDisplayName();
    }

}
