/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.input;

import java.util.Objects;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


public abstract class CustomAdapterFactoryContentProvider extends AdapterFactoryContentProvider {

    public CustomAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#createPropertySource(java.lang.Object,
     * org.eclipse.emf.edit.provider.IItemPropertySource)
     */
    @Override
    protected IPropertySource createPropertySource(final Object object, IItemPropertySource itemPropertySource) {
        return new PropertySource(object, itemPropertySource){

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.edit.ui.provider.PropertySource#createPropertyDescriptor(org.eclipse.emf.edit.provider.IItemPropertyDescriptor)
             */
            @Override
            protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor) {
                return new PropertyDescriptor(object, itemPropertyDescriptor) {

                    /*
                     * (non-Javadoc)
                     * @see org.eclipse.emf.edit.ui.provider.PropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
                     */
                    @Override
                    public CellEditor createPropertyEditor(Composite composite) {
                        final Object feature = itemPropertyDescriptor.getFeature(this.object);
                        if (Objects.equals(feature, getCustomFeature())) {
                            return createCustomPropertyEditor(composite, object, itemPropertyDescriptor, getEditLabelProvider());
                        }
                        return super.createPropertyEditor(composite);
                    }

                };
            }
        };
    }

    protected abstract CellEditor createCustomPropertyEditor(Composite composite, Object object, IItemPropertyDescriptor itemPropertyDescriptor,
            ILabelProvider iLabelProvider);

    protected abstract Object getCustomFeature();

}
