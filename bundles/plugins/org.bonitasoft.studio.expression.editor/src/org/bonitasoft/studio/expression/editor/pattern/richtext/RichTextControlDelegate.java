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

import org.bonitasoft.studio.expression.editor.pattern.ITextControl;
import org.eclipse.nebula.widgets.richtext.RichTextEditor;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;


public class RichTextControlDelegate implements ITextControl {

    private RichTextEditor richTextEditor;

    public RichTextControlDelegate(RichTextEditor richTextEditor) {
        this.richTextEditor = richTextEditor;
    }

    @Override
    public String getText() {
        return richTextEditor.getText();
    }

    @Override
    public void setText(String text) {
        this.richTextEditor.setText(text);
    }

    @Override
    public void addKeyListener(KeyListener listener) {
        this.richTextEditor.addKeyListener(listener);
    }

    @Override
    public boolean getEditable() {
        return richTextEditor.isEditable();
    }

    @Override
    public boolean getEnabled() {
        return richTextEditor.isEnabled();
    }

    @Override
    public void setEditable(boolean editable) {
        richTextEditor.setEditable(editable);
    }

    @Override
    public void setEnabled(boolean enabled) {
        richTextEditor.setEnabled(enabled);
    }

    @Override
    public void setBackground(Color color) {
        richTextEditor.setBackground(color);
    }

    @Override
    public Control getParent() {
        return richTextEditor.getParent();
    }

    @Override
    public void setWordWrap(boolean wrap) {
        // Not implemented
    }

}
