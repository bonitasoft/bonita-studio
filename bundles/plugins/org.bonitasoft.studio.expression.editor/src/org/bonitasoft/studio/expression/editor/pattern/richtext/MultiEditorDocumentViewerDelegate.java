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
import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.studio.expression.editor.pattern.IDocumentViewer;
import org.bonitasoft.studio.expression.editor.pattern.ITextControl;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextViewerUndoManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class MultiEditorDocumentViewerDelegate implements IDocumentViewer {

    private static final String VIEWER_DATA_KEY = "viewer";
    private CTabFolder tabFolder;
    private List<IDocumentViewer> viewers = new ArrayList<>();
    private IDocumentViewer selectedViewer;

    public MultiEditorDocumentViewerDelegate(Composite parent) {
        tabFolder = new CTabFolder(parent, SWT.BOTTOM );
        tabFolder.setLayout(GridLayoutFactory.fillDefaults().create());
        tabFolder.setBorderVisible(false);
        tabFolder.marginHeight = 0;
        tabFolder.marginWidth = 0;
        tabFolder.setSelectionBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
        tabFolder.addSelectionListener(new SelectionAdapter() {
            
            @Override
            public void widgetSelected(SelectionEvent e) {
                setActiveViewer((IDocumentViewer) e.item.getData(VIEWER_DATA_KEY));
            }
        });
    }

    public void addViewer(IDocumentViewer viewer, String editorName) {
        if (!viewers.contains(viewer)) {
            viewers.add(viewer);

            CTabItem editorTab = new CTabItem(tabFolder, SWT.NONE);
            editorTab.setText(editorName);
            editorTab.setControl(viewer.getControl());
            editorTab.setData(VIEWER_DATA_KEY,viewer);
            setActiveViewer(viewer);
        }
    }

    public void setActiveViewer(IDocumentViewer viewer) {
        if(selectedViewer != null && selectedViewer.getDocument() != null) {
            String content = selectedViewer.getDocument().get();
            viewer.updateEditorContent(content);
        }
        this.selectedViewer = viewer;
        Stream.of(tabFolder.getItems()).filter(tab -> Objects.equals(tab.getData(VIEWER_DATA_KEY), selectedViewer))
                .findFirst()
                .ifPresent(tab -> tabFolder.setSelection(tab));
    }

    @Override
    public Control getControl() {
        return tabFolder;
    }

    @Override
    public IDocument getDocument() {
        return selectedViewer.getDocument();
    }

    @Override
    public void setDocument(IDocument document) {
        viewers.stream().forEach(v -> v.setDocument(document));
    }

    @Override
    public void connect(TextViewerUndoManager undoManager) {
        viewers.stream().forEach(v -> v.connect(undoManager));
    }

    @Override
    public void addTextListener(ITextListener object) {
        viewers.stream().forEach(v -> v.addTextListener(object));
    }

    @Override
    public String getHelpMessage() {
        return selectedViewer.getHelpMessage();
    }

    @Override
    public void bindExpressionContentValue(DataBindingContext context,
            IObservableValue<String> expressionContentObserveValue, UpdateValueStrategy targetToModelStartegy) {
        viewers.stream().forEach(
                v -> v.bindExpressionContentValue(context, expressionContentObserveValue, targetToModelStartegy));
    }

    @Override
    public ITextControl getTextControl() {
        return selectedViewer.getTextControl();
    }

    @Override
    public void configureContentAssist(List<Expression> filteredExpressions) {
        viewers.stream().forEach(v -> v.configureContentAssist(filteredExpressions));
    }

    @Override
    public void updateEditorContent(String text) {
        viewers.stream().forEach(v -> v.updateEditorContent(text));
    }

}
