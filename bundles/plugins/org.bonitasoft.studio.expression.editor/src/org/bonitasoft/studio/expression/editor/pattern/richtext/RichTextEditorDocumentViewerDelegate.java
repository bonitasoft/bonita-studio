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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.pattern.IDocumentViewer;
import org.bonitasoft.studio.expression.editor.pattern.ITextControl;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextViewerUndoManager;
import org.eclipse.nebula.widgets.richtext.RichTextEditor;
import org.eclipse.nebula.widgets.richtext.RichTextEditorConfiguration;
import org.eclipse.nebula.widgets.richtext.toolbar.ToolbarButton;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class RichTextEditorDocumentViewerDelegate implements IDocumentViewer {

    private RichTextEditor richTextEditor;
    private IDocument document;
    private IObservableValue<String> expressionContentObserveValue;
    private InsertVariableButton insertVariableButton;

    public RichTextEditorDocumentViewerDelegate(Composite tabFolder) {
        RichTextEditorConfiguration richTextEditorConfiguration = new RichTextEditorConfiguration();
        richTextEditorConfiguration.setToolbarCollapsible(true);
        richTextEditorConfiguration.setRemovePasteFromWord(true);
        richTextEditorConfiguration.setRemovePasteText(true);
        richTextEditorConfiguration.setRemoveFormat(false);
        richTextEditorConfiguration.setToolbarInitialExpanded(true);
        richTextEditorConfiguration.setOption(RichTextEditorConfiguration.REMOVE_PLUGINS, "elementspath,flash");
        richTextEditorConfiguration.setOption(RichTextEditorConfiguration.TOOLBAR_GROUPS, "["
                + "{ name: 'styles' },"
                + "{ name: 'basicstyles', groups: [ 'basicstyles','colors', 'cleanup' ] },"
                + "{ name: 'paragraph', groups: [ 'list', 'indent', 'align' ] },"
                + "{ name: 'links' },"
                + "{ name: 'other' },"
                + "{ name: 'insert' }"
                + "]");
        List<String> hiddenButtons = new ArrayList<>();
        if (Platform.OS_MACOSX.equals(Platform.getOS())) {
            hiddenButtons.add("TextColor");
            hiddenButtons.add("BGColor");
            hiddenButtons.add("Styles");
            hiddenButtons.add("Font");
            hiddenButtons.add("FontSize");
        }
        hiddenButtons.add("Flash");
        hiddenButtons.add("PageBreak");
        hiddenButtons.add("Iframe");
        hiddenButtons.add("Smiley");
        hiddenButtons.add("Subscript");
        hiddenButtons.add("Anchor");

        richTextEditorConfiguration.removeDefaultToolbarButton(hiddenButtons.toArray(new String[] {}));
        insertVariableButton = new InsertVariableButton();
        richTextEditorConfiguration.addToolbarButton(insertVariableButton);
        this.richTextEditor = new RichTextEditor(tabFolder, richTextEditorConfiguration);
        richTextEditor.addModifyListener(e -> updateExpressionContent());
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
        // N/A
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
        if(expressionContentObserveValue != null) {
            String text = richTextEditor.getText();
            expressionContentObserveValue.setValue(text);
            document.set(text);
        }
    }

    @Override
    public void updateEditorContent(String text) {
        if(expressionContentObserveValue != null) {
            richTextEditor.setText(text);
            expressionContentObserveValue.setValue(text);
            document.set(text);
        }
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
        insertVariableButton.setFilteredExpressions(filteredExpressions);
    }

    class InsertVariableButton extends ToolbarButton {

        private List<Expression> filteredExpressions;

        public InsertVariableButton() {
            super("insertVariableButton", "insertVariableCommand", Messages.insertVariableButton, "other",
                    RichPatternExpressionViewer.class.getResource("/icons/Data.gif"));
        }

        public void setFilteredExpressions(List<Expression> filteredExpressions) {
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
