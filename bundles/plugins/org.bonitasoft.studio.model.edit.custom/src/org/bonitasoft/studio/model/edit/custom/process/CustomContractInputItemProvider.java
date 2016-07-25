/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.edit.custom.process;

import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.provider.ContractInputItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

public class CustomContractInputItemProvider
        extends ContractInputItemProvider {

    public CustomContractInputItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.model.process.provider.ContractInputItemProvider#addTypePropertyDescriptor(java.lang.Object)
     */
    @Override
    protected void addTypePropertyDescriptor(final Object object) {
        itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_ContractInput_type_feature"), //$NON-NLS-1$
                getString("_UI_PropertyDescriptor_description", "_UI_ContractInput_type_feature", "_UI_ContractInput_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ProcessPackage.Literals.CONTRACT_INPUT__TYPE,
                true,
                false,
                true,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                null,
                null) {

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#getLabelProvider(java.lang.Object)
             */
            @Override
            public IItemLabelProvider getLabelProvider(final Object object) {
                return new ItemDelegator(adapterFactory, resourceLocator) {

                    /*
                     * (non-Javadoc)
                     * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor.ItemDelegator#getText(java.lang.Object)
                     */
                    @Override
                    public String getText(final Object object) {
                        if (object instanceof ContractInputType) {
                            return String.format("%s (%s)", super.getText(object), javaType((ContractInputType) object));
                        }
                        return super.getText(object);
                    }

                };
            }
        });
    }

    private String javaType(final ContractInputType type) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(type);
        return contractInput.getJavaType();
    }

}
