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

import java.util.List;

import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;

/**
 * @author aurelie
 */
public class FieldToContractInputMappingObservableFactory implements IObservableFactory {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.databinding.observable.masterdetail.IObservableFactory#createObservable(java.lang.Object)
     */
    @Override
    public IObservable createObservable(final Object target) {
        if (target instanceof List<?>) {
            return new WritableList((List<?>) target, FieldToContractInputMapping.class);
        } else if (target instanceof RelationFieldToContractInputMapping) {
            return new WritableList(((RelationFieldToContractInputMapping) target).getChildren(), FieldToContractInputMapping.class);
        }
        return new WritableList();
    }

}
