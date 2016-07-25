/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;

/**
 * @author Romain Bioteau
 *
 */
public class TextAnnotationAttachmentBuilder {

    private final TextAnnotationAttachment textAnnotationAttachment;

    private TextAnnotationAttachmentBuilder(final TextAnnotationAttachment textAnnotationAttachment) {
        this.textAnnotationAttachment = textAnnotationAttachment;
    }

    public static TextAnnotationAttachmentBuilder create() {
        return new TextAnnotationAttachmentBuilder(ProcessFactory.eINSTANCE.createTextAnnotationAttachment());
    }


    public TextAnnotationAttachmentBuilder havingSource(final TextAnnotationBuilder textAnnotation) {
        textAnnotationAttachment.setSource(textAnnotation.build());
        return this;
    }

    public TextAnnotationAttachmentBuilder havingTarget(final ElementBuilder<?, ?> element) {
        textAnnotationAttachment.setTarget(element.build());
        return this;
    }

    public TextAnnotationAttachment build() {
        return textAnnotationAttachment;
    }

}
