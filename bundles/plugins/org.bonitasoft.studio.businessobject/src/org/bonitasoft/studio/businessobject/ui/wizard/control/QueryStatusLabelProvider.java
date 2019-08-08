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
package org.bonitasoft.studio.businessobject.ui.wizard.control;

import static com.google.common.collect.Iterables.tryFind;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

public class QueryStatusLabelProvider extends StyledCellLabelProvider {

    private IObservableValue businessObjectObservableValue;

    public QueryStatusLabelProvider(IObservableValue businessObjectObservableValue) {
        this.businessObjectObservableValue = businessObjectObservableValue;
    }

    @Override
    public String getToolTipText(Object element) {
        Query query = (Query) element;
        if (!hasCountQuery(query)) {
            return Messages.bind(Messages.missingCountQuery, BDMQueryUtil.getCountQueryName(query.getName()));
        }
        return super.getToolTipText(element);
    }

    @Override
    protected void paint(final Event event, final Object element) {
        final Image img = getImage(element);
        if (img != null) {
            final Rectangle bounds = event.item instanceof TableItem ? ((TableItem) event.item).getBounds(event.index)
                    : ((TreeItem) event.item).getBounds(event.index);
            final Rectangle imgBounds = img.getBounds();
            bounds.width /= 2;
            bounds.width -= imgBounds.width / 2;
            bounds.height /= 2;
            bounds.height -= imgBounds.height / 2;

            final int x = bounds.width > 0 ? bounds.x + bounds.width : bounds.x;
            final int y = bounds.height > 0 ? bounds.y + bounds.height : bounds.y;

            if (SWT.getPlatform().equals("carbon")) {
                event.gc.drawImage(img, x + 2, y - 1);
            } else {
                event.gc.drawImage(img, x, y);
            }
        }
    }

    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
        cell.setImage(getImage(cell.getElement()));
    }

    protected Image getImage(Object element) {
        if (!hasCountQuery((Query) element)) {
            return getHintImage();
        }
        return null;
    }

    protected Image getHintImage() {
        return Pics.getImage(PicsConstants.hint);
    }

    private boolean hasCountQuery(Query query) {
        BusinessObject businessObject = (BusinessObject) businessObjectObservableValue.getValue();
        String returnType = query.getReturnType();
        if (List.class.getName().equals(returnType)) {
            String countQueryName = BDMQueryUtil.getCountQueryName(query.getName());
            Optional<Query> countQuery = tryFind(businessObject.getQueries(), countQueryWithName(countQueryName));
            return countQuery.isPresent();
        }
        return true;
    }

    private Predicate<Query> countQueryWithName(final String name) {
        return new Predicate<Query>() {

            @Override
            public boolean apply(Query query) {
                return Objects.equals(name, query.getName()) && Objects.equals(query.getReturnType(), Long.class.getName());
            }
        };
    }

}
