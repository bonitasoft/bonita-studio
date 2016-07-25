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

import java.util.ResourceBundle;

import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.groovy.eclipse.refactoring.actions.FormatKind;
import org.codehaus.groovy.eclipse.refactoring.actions.OrganizeGroovyImportsAction;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.ui.texteditor.TextOperationAction;


/**
 * @author Romain Bioteau
 *
 */
public class GroovyEditorActionFactory {

    private TextOperationAction deleteAction;
    private final GroovyEditor editor;
    private BonitaFormatGroovyAction formatAction;
    private OrganizeGroovyImportsAction organizeImportsAction;

    public GroovyEditorActionFactory(final GroovyEditor editor) {
        this.editor = editor;
    }

    protected TextOperationAction getDeleteAction() {
        if (deleteAction == null) {
            deleteAction = new TextOperationAction(
                    getTextEditorBundle(), "Editor.Delete.", editor, ITextOperationTarget.DELETE);
        }
        return deleteAction;
    }

    protected ResourceBundle getTextEditorBundle() {
        return ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages");
    }

    protected TextOperationAction getUndoAction() {
        if (deleteAction == null) {
            deleteAction = new TextOperationAction(
                    getTextEditorBundle(), "Editor.Undo.", editor, ITextOperationTarget.UNDO);
        }
        return deleteAction;
    }

    protected TextOperationAction getRedoAction() {
        if (deleteAction == null) {
            deleteAction = new TextOperationAction(
                    getTextEditorBundle(), "Editor.Redo.", editor, ITextOperationTarget.REDO);
        }
        return deleteAction;
    }

    protected BonitaFormatGroovyAction getFormatAction() {
        if (formatAction == null) {
            formatAction = new BonitaFormatGroovyAction(editor.getEditorSite(), FormatKind.FORMAT, editor, editor
                    .getGroovyCompilationUnit());
        }
        return formatAction;
    }

    public OrganizeGroovyImportsAction getOrganizeImportAction() {
        if (organizeImportsAction == null) {
            organizeImportsAction = new OrganizeGroovyImportsAction(editor);
        }
        return organizeImportsAction;
    }


}
