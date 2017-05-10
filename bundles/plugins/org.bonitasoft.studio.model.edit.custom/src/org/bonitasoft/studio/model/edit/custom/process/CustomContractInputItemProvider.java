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

import java.util.Collection;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.provider.ContractInputItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import com.google.common.collect.Lists;

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
        itemPropertyDescriptors
                .add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_ContractInput_type_feature"), //$NON-NLS-1$
                        getString("_UI_PropertyDescriptor_description", "_UI_ContractInput_type_feature", //$NON-NLS-1$//$NON-NLS-2$
                                "_UI_ContractInput_type"), //$NON-NLS-1$
                        ProcessPackage.Literals.CONTRACT_INPUT__TYPE,
                        true,
                        false,
                        false,
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
                                    final String label = getTypeLabel((ContractInputType) object);
                                    return String.format("%s - %s", label, javaType((ContractInputType) object));
                                }
                                return super.getText(object);
                            }

                            protected String getTypeLabel(final ContractInputType type) {
                                switch (type) {
                                    case DATE:
                                        return "DATE (NOT RECOMMENDED)";
                                    case LOCALDATE:
                                        return "DATE ONLY";
                                    case LOCALDATETIME:
                                        return "DATE-TIME (NO TIME ZONE)";
                                    case OFFSETDATETIME:
                                        return "DATE-TIME (TIME ZONE)";
                                    default:
                                        return super.getText(type);
                                }
                            }

                        };
                    }

                    /*
                     * (non-Javadoc)
                     * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#getComboBoxObjects(java.lang.Object)
                     */
                    @Override
                    protected Collection<?> getComboBoxObjects(Object object) {
                        final Collection<?> comboBoxObjects = Lists.newArrayList(ContractInputType.BOOLEAN,
                                ContractInputType.COMPLEX,
                                ContractInputType.LOCALDATE,
                                ContractInputType.LOCALDATETIME,
                                ContractInputType.OFFSETDATETIME,
                                ContractInputType.DECIMAL,
                                ContractInputType.FILE,
                                ContractInputType.INTEGER,
                                ContractInputType.LONG,
                                ContractInputType.TEXT,
                                ContractInputType.DATE);
                        if (getContractContainer((EObject) object) instanceof Task) {
                            comboBoxObjects.remove(ContractInputType.LONG);
                        }
                        return comboBoxObjects;
                    }

                    private EObject getContractContainer(EObject object) {
                        EObject current = object;
                        while (!(current instanceof Contract)) {
                            current = current.eContainer();
                        }
                        if (current != null) {
                            current = current.eContainer();
                        }
                        return current;
                    }
                });
    }

    private String javaType(final ContractInputType type) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(type);
        return contractInput.getJavaType();
    }

}
