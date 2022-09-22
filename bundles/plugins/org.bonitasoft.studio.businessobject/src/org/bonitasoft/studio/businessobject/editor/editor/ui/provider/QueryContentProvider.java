/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.provider;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;

public class QueryContentProvider implements ITreeContentProvider {

    private List<Query> roots;
    private IObservableValue<BusinessObject> input = new WritableValue<>();
    private IObservableList<Query> customQueries;
    private IObservableList<Query> defaultQueries;

    public QueryContentProvider() {
        // We create fake queries to be able to use detailObservables on viewer selection. The ideal would have been a String but it leads to cast exceptions. 
        Query defaultfakeQuery = new QueryBuilder().withName(Messages.defaultQueriesOption).create();
        Query customfakeQuery = new QueryBuilder().withName(Messages.customQueriesOption).create();
        roots = Arrays.asList(defaultfakeQuery, customfakeQuery);
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (newInput instanceof IObservableValue) {
            input = (IObservableValue<BusinessObject>) newInput;
            customQueries = EMFObservables.observeDetailList(Realm.getDefault(), input,
                    BusinessDataModelPackage.Literals.BUSINESS_OBJECT__QUERIES);
            defaultQueries = EMFObservables.observeDetailList(Realm.getDefault(), input,
                    BusinessDataModelPackage.Literals.BUSINESS_OBJECT__DEFAULT_QUERIES);
            input.addValueChangeListener(e -> {
                viewer.setSelection(new StructuredSelection());
                viewer.refresh();
            });
            viewer.refresh();
        } else if (newInput != null) {
            throw new IllegalArgumentException("Expecting an input of type IobservableValue<BusinessObject>");
        }
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return roots.toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (roots.contains(parentElement)) {
            return Objects.equals(((Query) parentElement).getName(), Messages.defaultQueriesOption)
                    ? defaultQueries.toArray()
                    : customQueries.toArray();
        }
        return null;
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof Query && !roots.contains(element)) {
            return defaultQueries.contains(element)
                    ? Messages.defaultQueriesOption
                    : Messages.customQueriesOption;
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        if (roots.contains(element)) {
            return Objects.equals(((Query) element).getName(), Messages.defaultQueriesOption)
                    ? true
                    : !customQueries.isEmpty();
        }
        return false;
    }

    public Query getCustomRoot() {
        return roots.get(1);
    }

}
