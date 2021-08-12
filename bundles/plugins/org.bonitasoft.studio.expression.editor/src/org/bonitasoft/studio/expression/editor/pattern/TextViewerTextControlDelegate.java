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

import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;


public class TextViewerTextControlDelegate implements ITextControl {

    private TextViewer textViewer;

    public TextViewerTextControlDelegate(TextViewer textViewer) {
        this.textViewer = textViewer;
    }

    @Override
    public String getText() {
        return textViewer.getTextWidget().getText();
    }

    @Override
    public void setText(String text) {
        this.textViewer.getTextWidget().setText(text);
    }

    @Override
    public void addKeyListener(KeyListener listener) {
        this.textViewer.getTextWidget().addKeyListener(listener);
    }

    @Override
    public boolean getEditable() {
        return this.textViewer.getTextWidget().getEditable();
    }

    @Override
    public boolean getEnabled() {
        return this.textViewer.getTextWidget().getEnabled();
    }

    @Override
    public void setEditable(boolean editable) {
        this.textViewer.getTextWidget().setEditable(editable);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.textViewer.getTextWidget().setEnabled(enabled);
    }

    @Override
    public void setBackground(Color color) {
        this.textViewer.getTextWidget().setBackground(color);
    }

    @Override
    public Control getParent() {
        return this.textViewer.getTextWidget().getParent();
    }

    @Override
    public void setWordWrap(boolean wrap) {
        this.textViewer.getTextWidget().setWordWrap(wrap);
    }

}
