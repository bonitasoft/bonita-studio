/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.ui.editor;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.sse.ui.StructuredTextEditor;

public class ApplicationEditor extends FormEditor {

    public static final String EDITOR_ID = "org.bonitasoft.studio.la.editor";

    private StructuredTextEditor fSourceEditor;

    @Override
    protected void addPages() {
        try {
            setPageText(addPage(createSourceEditor(), getEditorInput()), Messages.source);

        } catch (final PartInitException e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * @see org.eclipse.ui.part.WorkbenchPart#getPartName()
     */
    @Override
    public String getPartName() {
        return getEditorInput().getName();
    }

    private IEditorPart createSourceEditor() {
        fSourceEditor = new StructuredTextEditor();
        fSourceEditor.setEditorPart(this);
        return fSourceEditor;
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        fSourceEditor.doSave(monitor);
    }

    @Override
    public void doSaveAs() {
        fSourceEditor.doSaveAs();
    }

    @Override
    public boolean isDirty() {
        return fSourceEditor.isDirty();
    }

    @Override
    public boolean isSaveAsAllowed() {
        return fSourceEditor.isSaveAsAllowed();
    }

    @Override
    protected FormToolkit createToolkit(Display display) {
        return new FormToolkit(display);
    }

}
