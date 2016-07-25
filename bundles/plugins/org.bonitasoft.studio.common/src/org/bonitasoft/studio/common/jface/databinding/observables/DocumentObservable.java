/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.jface.databinding.observables;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.source.SourceViewer;


/**
 * @author Romain Bioteau
 *
 */
public class DocumentObservable extends AbstractObservableValue {

    private final IDocument document;

    public DocumentObservable(SourceViewer viewer){
        document = viewer.getDocument();
        document.addDocumentListener(new IDocumentListener() {


            private String previousText;

            @Override
            public void documentChanged(final DocumentEvent event) {
                fireValueChange(new ValueDiff() {

                    @Override
                    public Object getOldValue() {
                        return previousText;
                    }

                    @Override
                    public Object getNewValue() {
                        return event.getText();
                    }
                });
            }

            @Override
            public void documentAboutToBeChanged(DocumentEvent event) {
                previousText = event.getText();
            }
        });
    }

    @Override
    public Object getValueType() {
        return String.class;
    }

    @Override
    protected Object doGetValue() {
        return document.get();
    }



}
