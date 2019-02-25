/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.ui;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.TextStyle;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectDataStyledLabelProvider extends DataStyledTreeLabelProvider {

    private final BusinessObjectModelRepositoryStore store;

    public BusinessObjectDataStyledLabelProvider(final BusinessObjectModelRepositoryStore store,
            final IObservableMap... attributeMaps) {
        super(attributeMaps);
        this.store = store;
    }

    @Override
    public void update(final ViewerCell cell) {
        super.update(cell);
        if (cell.getElement() instanceof BusinessObjectData) {
            final BusinessObjectData data = (BusinessObjectData) cell.getElement();
            if (!businessObjectDefinitionExists(data)) {
                final StyledString styledString = createStrikethroughStyle(cell.getText(), data.getClassName());
                cell.setText(styledString.getString());
                cell.setStyleRanges(styledString.getStyleRanges());
            }
        }
    }

    protected StyledString createStrikethroughStyle(final String initialText,
            final String businessObjectId) {
        final StyledString styledString = new StyledString(initialText);
        styledString.setStyle(0, styledString.length(), new org.eclipse.jface.viewers.StyledString.Styler() {

            @Override
            public void applyStyles(final TextStyle textStyle) {
                textStyle.strikeout = true;
            }
        });
        styledString.append(" ");
        styledString.append(Messages.bind(Messages.businessObjectNotFound, businessObjectId));
        return styledString;
    }

    protected boolean businessObjectDefinitionExists(final BusinessObjectData data) {
        String className = data.getClassName();
        return className != null && store != null && store.getChildByQualifiedName(className).isPresent();
    }

}
