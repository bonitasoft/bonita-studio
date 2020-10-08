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
package org.bonitasoft.studio.groovy.ui.viewer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.job.UnknownElementsIndexer;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jdt.internal.ui.javaeditor.JavaMarkerAnnotation;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;


/**
 * @author Romain Bioteau
 *
 */
public class UpdateUnknownReferencesListener extends JobChangeAdapter {


    private final IAnnotationModel annotationModel;
    private final IDocument document;

    public UpdateUnknownReferencesListener(final IDocument document, final IAnnotationModel annotationModel) {
        this.annotationModel = annotationModel;
        this.document = document;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
     */
    @Override
    public void done(final IJobChangeEvent event) {
        final Job job = event.getJob();
        if (job instanceof UnknownElementsIndexer) {
            clearAnnotations();
            for (final String variable : ((UnknownElementsIndexer) job).getUnknownVaraibles()) {
                for (final Entry<Annotation, Position> annotation : createWarningAnnotations(variable).entrySet()) {
                    annotationModel.addAnnotation(annotation.getKey(),annotation.getValue());
                }
            }
            for (final Entry<String, Position> variable : ((UnknownElementsIndexer) job).getOverridenVariables().entrySet()) {
                annotationModel.addAnnotation(new Annotation(JavaMarkerAnnotation.WARNING_ANNOTATION_TYPE, false, Messages.bind(
                        Messages.warningAssigningAVariableWithSameNameAsProcessVariable, variable.getKey())),
                        variable.getValue());
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

    private Map<Annotation,Position> createWarningAnnotations(final String variable) {
        final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
        final String expression = document.get();
        final Map<Annotation,Position> annotations= new HashMap<Annotation,Position>();
        try {
            IRegion region = finder.find(0, variable, true, true, true, false);
            while (region != null) {
                final Position position = new Position(region.getOffset(), region.getLength());
                if (!isInAStringExpression(variable, region, expression)) {
                    annotations.put(new Annotation(JavaMarkerAnnotation.WARNING_ANNOTATION_TYPE, false, createDescription(variable)), position);
                }
                region = finder.find(position.getOffset() + position.getLength(), variable, true, true, true, false);

            }
        } catch (final BadLocationException e) {

        }
        return annotations ;
    }



    private boolean isInAStringExpression(final String name, final IRegion index, final String expression) {
        if (index.getOffset() > 0) {
            int nbStringChars1 = 0;
            int nbStringChars2 = 0;

            for (int i = 0; i < index.getOffset(); i++) {
                final char c = expression.charAt(i);
                if ('"' == c) {
                    nbStringChars1++;
                } else if ('\'' == c) {
                    nbStringChars2++;
                }
            }
            return !(nbStringChars1 % 2 == 0 && nbStringChars2 % 2 == 0);

        }
        return false;
    }

    private String createDescription(final String key) {
        return key + " " + Messages.groovyUnresolved;
    }


}
