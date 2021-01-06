/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.pattern.richtext;

import org.bonitasoft.studio.expression.editor.pattern.IDocumentViewer;
import org.bonitasoft.studio.expression.editor.pattern.PatternExpressionViewer;
import org.bonitasoft.studio.expression.editor.pattern.TextViewerDocumentViewerDelegate;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class RichPatternExpressionViewer extends PatternExpressionViewer {

    private MultiEditorDocumentViewerDelegate multiEditorDelegate;
    private RichTextEditorDocumentViewerDelegate richTextViewer;
    private TextViewerDocumentViewerDelegate plainTextEditor;

    public RichPatternExpressionViewer(final Composite parent, final int style) {
        super(parent, style);
    }

    @Override
    protected IDocumentViewer createViewer(Composite parent) {
        multiEditorDelegate = new MultiEditorDocumentViewerDelegate(parent);
        Composite tabFolder = (Composite) multiEditorDelegate.getControl();
        richTextViewer = new RichTextEditorDocumentViewerDelegate(tabFolder);
        SourceViewer textViewer = new SourceViewer(tabFolder, null, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        plainTextEditor = new TextViewerDocumentViewerDelegate(textViewer);
        multiEditorDelegate.addViewer(richTextViewer, "Rich text");
        multiEditorDelegate.addViewer(plainTextEditor, "Plain text");
        return multiEditorDelegate;
    }

    public void setRichTextActive(boolean activateRichText) {
        multiEditorDelegate.setActiveViewer(activateRichText ? richTextViewer : plainTextEditor);
    }
    

}
