/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.wizard;

import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;

/**
 * @author aurelie
 */
public class DocumentStyledLabelProvider extends DataStyledTreeLabelProvider {

    @Override
    public void update(final ViewerCell cell) {
        final Object element = cell.getElement();
        if (element instanceof Document) {
            final Document document = (Document) element;
            final StyledString styledString = new StyledString();
            styledString.append(document.getName());
            final String decoration = " -- " + getTypeLabel(document);
            styledString.append(decoration, StyledString.DECORATIONS_STYLER);
            styledString.append(" -- ", StyledString.DECORATIONS_STYLER);
            cell.setText(styledString.getString());
            cell.setStyleRanges(styledString.getStyleRanges());
            cell.setImage(getImage(element));
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        return ((Document) element).getName();
    }

    private String getTypeLabel(final Document element) {
        final StringBuilder builder = new StringBuilder();
        builder.append(element.getDocumentType().name());
        return builder.toString();
    }
}
