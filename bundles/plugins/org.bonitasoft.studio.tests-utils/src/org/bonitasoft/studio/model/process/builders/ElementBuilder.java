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
import org.bonitasoft.studio.model.process.Element;

/**
 * @author Romain Bioteau
 */
public abstract class ElementBuilder<T extends Element, B extends ElementBuilder<T, B>> implements Buildable<T> {

    protected final T builtEObject;
    private final B thisBuilder;

    protected ElementBuilder() {
        builtEObject = newInstance();
        thisBuilder = getThis();
    }

    public B withName(final String name) {
        getBuiltInstance().setName(name);
        return thisBuilder;
    }

    public B withDocumentation(final String documentation) {
        getBuiltInstance().setDocumentation(documentation);
        return thisBuilder;
    }

    public B havingTextAnnotationAttachment(final TextAnnotationAttachmentBuilder... textAnnotationAttachments) {
        if (textAnnotationAttachments != null) {
            for (final TextAnnotationAttachmentBuilder annotation : textAnnotationAttachments) {
                getBuiltInstance().getTextAnnotationAttachment().add(annotation.build());
            }
        }
        return thisBuilder;
    }

    @Override
    public T build() {
        return getBuiltInstance();
    }

    @SuppressWarnings("unchecked")
    protected B getThis() {
        return (B) this;
    }

    protected T getBuiltInstance() {
        return builtEObject;
    }

    protected abstract T newInstance();

}
