/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
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

import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;

/**
 * @author Mickael Istria
 */
public class CustomProcessItemProviderAdapterFactory extends ProcessItemProviderAdapterFactory {

    @Override
    public Adapter createConnectorAdapter() {
        if (connectorItemProvider == null) {
            connectorItemProvider = new CustomConnectorItemProvider(this);
        }

        return connectorItemProvider;
    }

    @Override
    public Adapter createDataAdapter() {
        if (dataItemProvider == null) {
            dataItemProvider = new CustomDataItemProvider(this);
        }

        return dataItemProvider;
    }

    @Override
    public Adapter createJavaObjectDataAdapter() {
        if (javaObjectDataItemProvider == null) {
            javaObjectDataItemProvider = new CustomJavaObjectDataItemProvider(this);
        }

        return javaObjectDataItemProvider;
    }


    @Override
    public Adapter createMessageAdapter() {
        if (messageItemProvider == null) {
            messageItemProvider = new CustomMessageItemProvider(this);
        }

        return messageItemProvider;
    }

    @Override
    public Adapter createMainProcessAdapter() {
        if (mainProcessItemProvider == null) {
            mainProcessItemProvider = new CustomMainProcessItemProvider(this);
        }

        return mainProcessItemProvider;
    }

    @Override
    public Adapter createFormMappingAdapter() {
        if (formMappingItemProvider == null) {
            formMappingItemProvider = new CustomFormMappingItemProvider(this);
        }
        return formMappingItemProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory#createContractInputAdapter()
     */
    @Override
    public Adapter createContractInputAdapter() {
        if (contractInputItemProvider == null) {
            contractInputItemProvider = new CustomContractInputItemProvider(this);
        }
        return contractInputItemProvider;
    }
}
