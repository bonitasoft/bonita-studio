/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.pattern;

import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextViewerUndoManager;
import org.eclipse.swt.widgets.Control;

public interface IDocumentViewer {

    Control getControl();

    IDocument getDocument();

    void setDocument(IDocument document);

    void connect(TextViewerUndoManager undoManager);

    void addTextListener(ITextListener object);

    ITextControl getTextControl();

    String getHelpMessage();
    
    void bindExpressionContentValue(DataBindingContext context, IObservableValue<String> expressionContentObserveValue,
            UpdateValueStrategy targetToModelStartegy);

    void configureContentAssist(List<Expression> filteredExpressions);

    void updateEditorContent(String text);
}
