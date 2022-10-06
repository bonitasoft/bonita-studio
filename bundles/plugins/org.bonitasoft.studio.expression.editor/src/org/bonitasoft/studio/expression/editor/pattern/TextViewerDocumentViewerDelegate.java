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

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.pattern.contentAssist.ExpressionContentAssistProcessor;
import org.bonitasoft.studio.expression.editor.pattern.contentAssist.PatternExpressionCompletionProcessor;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.TextViewerUndoManager;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Control;

import com.google.common.collect.Sets;

public class TextViewerDocumentViewerDelegate implements IDocumentViewer {

    public static final String GROOVY_EXPRESSION_CONTENT_TYPE = "__groovy_partition_content_type";
    private TextViewer textViewer;
    private Binding binding;

    public TextViewerDocumentViewerDelegate(TextViewer textViewer) {
        this.textViewer = textViewer;
    }

    @Override
    public Control getControl() {
        return textViewer.getControl();
    }

    @Override
    public IDocument getDocument() {
        return textViewer.getDocument();
    }

    @Override
    public void setDocument(IDocument document) {
        textViewer.setDocument(document);
    }

    @Override
    public void connect(TextViewerUndoManager undoManager) {
        textViewer.setUndoManager(undoManager);
        undoManager.connect(textViewer);
        textViewer.getTextWidget().addKeyListener(new org.eclipse.swt.events.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.stateMask == SWT.MOD1) && e.keyCode == 'z') {
                    undoManager.undo();
                } else if ((e.stateMask == SWT.MOD1) && e.keyCode == 'y') {
                    undoManager.redo();
                } else if ((e.stateMask == SWT.MOD1 + SWT.SHIFT) && e.keyCode == 'z') {
                    undoManager.redo();
                }
            }
        });
    }

    @Override
    public void addTextListener(ITextListener listener) {
        textViewer.addTextListener(listener);
    }

    @Override
    public ITextControl getTextControl() {
        return new TextViewerTextControlDelegate(textViewer);
    }

    @Override
    public String getHelpMessage() {
        return Messages.patternViewerHint;
    }

    @Override
    public void bindExpressionContentValue(DataBindingContext context,
            IObservableValue<String> expressionContentObserveValue,
            UpdateValueStrategy targetToModelStartegy) {
        if (binding != null) {
            binding.dispose();
            binding = null;
        }
        binding = context.bindValue(WidgetProperties.text(SWT.Modify).observe(getControl()),
                expressionContentObserveValue,
                targetToModelStartegy,
                null);
    }

    @Override
    public void configureContentAssist(List<Expression> filteredExpressions) {
        final ContentAssistant assistant = new ContentAssistant();
        final PatternExpressionCompletionProcessor javaCompletionProcessor = new PatternExpressionCompletionProcessor(
                new FakeEditorPart(), filteredExpressions);
        javaCompletionProcessor.setCompletionProposalAutoActivationCharacters(new char[] { '.' });
        assistant.setContentAssistProcessor(javaCompletionProcessor, GROOVY_EXPRESSION_CONTENT_TYPE);
        assistant.setShowEmptyList(true);
        assistant.enableAutoActivation(true);

        final ExpressionContentAssistProcessor contentAssisProcessor = new ExpressionContentAssistProcessor();
        contentAssisProcessor.setExpressions(Sets.newHashSet(filteredExpressions));
        assistant.setContentAssistProcessor(contentAssisProcessor, IDocument.DEFAULT_CONTENT_TYPE);
        assistant.setShowEmptyList(true);
        assistant.install(textViewer);
        textViewer.getControl().addKeyListener(new org.eclipse.swt.events.KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == SWT.SPACE) {
                    assistant.showPossibleCompletions();
                }
            }
        });
    }

    @Override
    public void updateEditorContent(String text) {
        // Content already databound
    }

}
