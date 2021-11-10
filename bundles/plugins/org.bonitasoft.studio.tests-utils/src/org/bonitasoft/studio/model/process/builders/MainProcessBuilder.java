/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class MainProcessBuilder extends ElementBuilder<MainProcess, MainProcessBuilder> {

    public static MainProcessBuilder aMainProcess() {
        return new MainProcessBuilder();
    }

    public MainProcessBuilder withVersion(final String version) {
        getBuiltInstance().setVersion(version);
        return getThis();
    }

    public MainProcessBuilder havingElements(final ElementBuilder<?, ?>... elements) {
        if (elements != null) {
            for (final ElementBuilder<?, ?> elementBuilder : elements) {
                final Element element = elementBuilder.build();
                getBuiltInstance().getElements().add(element);
                if (element instanceof FlowElement) {
                    for (final Connection connection : ((FlowElement) element).getOutgoing()) {
                        if (connection.eContainer() == null) {
                            getBuiltInstance().getConnections().add(connection);
                        }
                    }
                    for (final Connection connection : ((FlowElement) element).getIncoming()) {
                        if (connection.eContainer() == null) {
                            getBuiltInstance().getConnections().add(connection);
                        }
                    }
                }
            }
        }
        return getThis();
    }

    public MainProcessBuilder havingFormMapping(final FormMapping formMapping) {
        getBuiltInstance().setFormMapping(formMapping);
        return this;
    }

    public MainProcessBuilder havingDatatypes(final DataType... dataTypes) {
        for (final DataType dt : dataTypes) {
            getBuiltInstance().getDatatypes().add(dt);
        }
        return getThis();
    }

    @SafeVarargs
    public final MainProcessBuilder havingDatatypes(final Buildable<? extends DataType>... dataTypeBuildables) {
        for (final Buildable<? extends DataType> dt : dataTypeBuildables) {
            getBuiltInstance().getDatatypes().add(dt.build());
        }
        return getThis();
    }

    @Override
    protected MainProcess newInstance() {
        return ProcessFactory.eINSTANCE.createMainProcess();
    }

}
