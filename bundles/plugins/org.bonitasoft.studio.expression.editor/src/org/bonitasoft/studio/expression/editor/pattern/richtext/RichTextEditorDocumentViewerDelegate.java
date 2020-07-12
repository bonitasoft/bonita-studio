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
package org.bonitasoft.studio.expression.editor.pattern.richtext;

import java.util.List;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.pattern.IDocumentViewer;
import org.bonitasoft.studio.expression.editor.pattern.ITextControl;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextViewerUndoManager;
import org.eclipse.nebula.widgets.richtext.RichTextEditor;
import org.eclipse.nebula.widgets.richtext.RichTextEditorConfiguration;
import org.eclipse.nebula.widgets.richtext.toolbar.ToolbarButton;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class RichTextEditorDocumentViewerDelegate implements IDocumentViewer {

    private RichTextEditor richTextEditor;
    private IDocument document;
    private IObservableValue<String> expressionContentObserveValue;

    public RichTextEditorDocumentViewerDelegate(Composite tabFolder) {
        RichTextEditorConfiguration richTextEditorConfiguration = new RichTextEditorConfiguration();
        richTextEditorConfiguration.setToolbarCollapsible(true);
        richTextEditorConfiguration.setRemovePasteFromWord(false);
        richTextEditorConfiguration.setToolbarInitialExpanded(false);
        this.richTextEditor = new RichTextEditor(tabFolder, richTextEditorConfiguration);
    }

    @Override
    public Control getControl() {
        return richTextEditor;
    }

    @Override
    public IDocument getDocument() {
        return document;
    }

    @Override
    public void setDocument(IDocument document) {
        this.document = document;

    }

    @Override
    public void connect(TextViewerUndoManager undoManager) {
        //Undo management already handled in rich text editor
    }

    @Override
    public void addTextListener(ITextListener object) {

    }

    @Override
    public void bindExpressionContentValue(DataBindingContext context,
            IObservableValue<String> expressionContentObserveValue, UpdateValueStrategy targetToModelStartegy) {
        this.expressionContentObserveValue = expressionContentObserveValue;
        richTextEditor.setText(expressionContentObserveValue.getValue());
        richTextEditor.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
                updateExpressionContent();
            }
        });
    }

    public void updateExpressionContent() {
        String text = richTextEditor.getText();
        expressionContentObserveValue.setValue(text);
        document.set(text);
    }

    @Override
    public void updateEditorContent(String text) {
        richTextEditor.setText(text);
        expressionContentObserveValue.setValue(text);
        document.set(text);
    }

    @Override
    public String getHelpMessage() {
        return Messages.richTextHelpMessage;
    }

    @Override
    public ITextControl getTextControl() {
        return new RichTextControlDelegate(richTextEditor);
    }

    @Override
    public void configureContentAssist(List<Expression> filteredExpressions) {
        Browser browser = richTextEditor.getEditorConfiguration().getBrowser();
        browser.addProgressListener(new ProgressAdapter() {

            @Override
            public void completed(ProgressEvent event) {
                richTextEditor.addModifyListener(e -> updateExpressionContent());
                richTextEditor.addToolbarButton(new InsertVariableButton(filteredExpressions));
                richTextEditor.updateToolbar();
            }

        });
    }

    class InsertVariableButton extends ToolbarButton {

        private List<Expression> filteredExpressions;

        public InsertVariableButton(List<Expression> filteredExpressions) {
            super("insertVariableButton", "insertVariableCommand", Messages.insertVariableButton, "other",
                    RichPatternExpressionViewer.class.getResource("/icons/Data.gif"));
            this.filteredExpressions = filteredExpressions;
        }

        @Override
        public String getJavascriptToExecute() {
            return null;
        }

        @Override
        public Object execute() {
            VariableSelectionDialog variableSelectionDialog = new VariableSelectionDialog(getControl().getShell(),
                    filteredExpressions);
            variableSelectionDialog.open();
            Expression selection = variableSelectionDialog.getSelection();
            String variablePattern = String.format("${%s}", selection.getName());
            if (!String.class.getName().equals(selection.getReturnType())) {
                variablePattern = String.format("${%s.toString()}", selection.getName());
            }
            richTextEditor.insertHTML(variablePattern);
            return null;
        }

    }

}
