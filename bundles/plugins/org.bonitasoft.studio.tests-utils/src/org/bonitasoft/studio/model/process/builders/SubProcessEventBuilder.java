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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SubProcessEvent;

public class SubProcessEventBuilder extends ElementBuilder<SubProcessEvent, SubProcessEventBuilder> {

    public static SubProcessEventBuilder aSubProcessEvent() {
        return new SubProcessEventBuilder();
    }

    public SubProcessEventBuilder havingElements(final ElementBuilder<?, ?>... elements) {
        if (elements != null) {
            for (final ElementBuilder<?, ?> elementBuilder : elements) {
                final Element element = elementBuilder.build();
                getBuiltInstance().getElements().add(element);
            }
        }
        return getThis();
    }

    public SubProcessEventBuilder havingElements(final Element... elements) {
        if (elements != null) {
            for (final Element element : elements) {
                getBuiltInstance().getElements().add(element);
            }
        }
        return getThis();
    }

    @Override
    protected SubProcessEvent newInstance() {
        return ProcessFactory.eINSTANCE.createSubProcessEvent();
    }
}
