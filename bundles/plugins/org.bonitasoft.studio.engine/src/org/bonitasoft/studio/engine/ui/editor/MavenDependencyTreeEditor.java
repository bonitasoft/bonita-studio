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

import java.lang.reflect.Field;

import org.apache.maven.artifact.Artifact;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.editor.pom.DependencyTreePage;
import org.eclipse.m2e.editor.pom.MavenPomEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.IFormPage;

/**
 * A single-page Editor that shows only POM dependencies in read-only form.
 */
public class MavenDependencyTreeEditor extends MavenPomEditor {

    public static final String EDITOR_ID = "org.bonitasoft.studio.engine.ui.editor.MavenDependencyTreeEditor";

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);
    }

    @Override
    protected void addPages() {
        super.addPages();

        getMavenPomEditorPages().clear();

        DependencyTreePage dependencyPage = (DependencyTreePage) findPage("org.eclipse.m2e.core.pom.dependencyTree");

        setScopeToCompile(dependencyPage);

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.m2e.editor.pom.MavenPomEditor#setPageText(int, java.lang.String)
     */
    @Override
    public void setPageText(int pageIndex, String text) {
        if (pageIndex >= 0) {
            super.setPageText(pageIndex, text);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.forms.editor.FormEditor#addPage(org.eclipse.ui.forms.editor.IFormPage)
     */
    @Override
    public int addPage(IFormPage page) throws PartInitException {
        if (page instanceof DependencyTreePage) {
            return super.addPage(page);
        } else {
            // still init the page
            page.init(getEditorSite(), getEditorInput());
            return -1;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.forms.editor.FormEditor#addPage(org.eclipse.ui.IEditorPart, org.eclipse.ui.IEditorInput)
     */
    @Override
    public int addPage(IEditorPart editor, IEditorInput input) throws PartInitException {
        if (editor instanceof DependencyTreePage) {
            return super.addPage(editor, input);
        } else {
            // still init the page
            editor.init(getEditorSite(), getEditorInput());
            return -1;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.forms.editor.FormEditor#setActivePage(int)
     */
    @Override
    protected void setActivePage(int pageIndex) {
        if (pageIndex >= 0) {
            super.setActivePage(pageIndex);
        }
    }

    /**
     * Reflective code that sets the internal scope to "compile" on DependencyTreePage.
     */
    private void setScopeToCompile(DependencyTreePage dependencyTreePage) {
        try {
            Field currentClasspathField = DependencyTreePage.class.getDeclaredField("currentClasspath");
            currentClasspathField.setAccessible(true);
            currentClasspathField.set(dependencyTreePage, Artifact.SCOPE_COMPILE);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    public void doSave(IProgressMonitor monitor) {
        // If the Dependencies page should still be editable and savable,
        // remove this override. If truly read-only, leave it empty.
    }

    @Override
    public void doSaveAs() {
        // read-only => no "save as"
    }

    @Override
    public boolean isSaveAsAllowed() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.m2e.editor.pom.MavenPomEditor#close(boolean)
     */
    @Override
    public void close(boolean save) {
        super.close(save);
        getEditorInput().notify();

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.part.EditorPart#isSaveOnCloseNeeded()
     */
    @Override
    public boolean isSaveOnCloseNeeded() {
        return false;
    }
}
