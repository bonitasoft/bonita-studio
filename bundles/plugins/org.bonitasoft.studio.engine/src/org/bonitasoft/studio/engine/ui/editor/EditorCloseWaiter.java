/**
 * Copyright (C) 2025 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.ui.editor;

import java.util.concurrent.atomic.AtomicBoolean;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class EditorCloseWaiter {

    private AtomicBoolean editorClosed = new AtomicBoolean(false);

    /**
     */
    public EditorCloseWaiter() {
    }

    /**
     * Opens the editor using the given input and editor ID.
     * If waiting is enabled, blocks until the editor is closed.
     *
     * @param input the FileEditorInput to open in the editor
     * @param editorId the ID of the editor to open
     */
    public void openEditorAndWait(FileEditorInput input, String editorId) {
        // Open the editor on the UI thread.
        Display.getDefault().execute(() -> {
            IWorkbenchPage page = PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage();
            try {
                page.openEditor(input, editorId);
                // Add a listener to be notified when the editor closes.
                page.addPartListener(new IPartListener2() {

                    @Override
                    public void partClosed(IWorkbenchPartReference partRef) {
                        // Check if the closed part is our editor.
                        if (partRef.getPart(false) instanceof MavenDependencyTreeEditor) {
                            notifyEditorClosed();
                        }
                    }
                });
            } catch (PartInitException e) {
                // prevent waiting indefinitely if the editor cannot be opened
                editorClosed.set(true);
                BonitaStudioLog.error(e);
            }
        });

        // Block until the adapter signals that the editor has closed.
        waitForEditorClose();
    }

    /**
     * Blocks the calling thread until the editor is closed.
     */
    public synchronized void waitForEditorClose() {
        while (!editorClosed.get()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                BonitaStudioLog.error(e);
            }
        }
    }

    /**
     * Notifies any waiting threads that the editor has been closed.
     */
    public synchronized void notifyEditorClosed() {
        editorClosed.set(true);
        notifyAll();
    }
}
