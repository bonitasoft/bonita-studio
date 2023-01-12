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

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.viewers.LabelProvider;

public class QueryResultTypeLabelProvider extends LabelProvider {

    private IObservableValue<BusinessObject> boSelectedObservable;

    public QueryResultTypeLabelProvider(IObservableValue<BusinessObject> boSelectedObservable) {
        this.boSelectedObservable = boSelectedObservable;
    }

    @Override
    public String getText(final Object element) {
        final String className = element.toString();
        if (Objects.equals(List.class.getName(), className)) {
            return Messages.multipleReturnType;
        } else if (Objects.equals(Long.class.getName(), className)) {
            return className + " (COUNT,SUM...etc)";
        } else if (Objects.equals(Double.class.getName(), className)) {
            return className + " (AVG...etc)";
        } else if (boSelectedObservable.getValue() != null
                && Objects.equals(boSelectedObservable.getValue().getQualifiedName(), className)) {
            return Messages.bind(Messages.single, boSelectedObservable.getValue().getSimpleName());
        }
        return super.getText(element);
    }

}
