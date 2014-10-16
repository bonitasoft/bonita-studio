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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jdt.internal.ui.javaeditor.JavaMarkerAnnotation;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;


/**
 * @author Romain Bioteau
 *
 */
public class UpdateCompilerAnnotationListener extends JobChangeAdapter {


    private final IAnnotationModel annotationModel;

    public UpdateCompilerAnnotationListener(final IAnnotationModel annotationModel) {
        this.annotationModel = annotationModel;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
     */
    @Override
    public void done(final IJobChangeEvent event) {
        final IStatus compliationStatus = event.getResult();
        clearAnnotations();
        if (compliationStatus.isMultiStatus()) {
            for (final IStatus errorStatus : compliationStatus.getChildren()) {
                final int offset = errorStatus.getCode();
                annotationModel.addAnnotation(new Annotation(JavaMarkerAnnotation.ERROR_ANNOTATION_TYPE, false, errorStatus.getMessage()),
                        new Position(offset));
            }
        }
    }

    protected void clearAnnotations() {
        final Iterator<?> it = annotationModel.getAnnotationIterator();
        while (it.hasNext()) {
            final Object annotation = it.next();
            annotationModel.removeAnnotation((Annotation) annotation);
        }
    }



}
