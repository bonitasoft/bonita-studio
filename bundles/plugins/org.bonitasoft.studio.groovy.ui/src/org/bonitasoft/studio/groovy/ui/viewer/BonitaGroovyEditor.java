/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.groovy.ui.viewer;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.groovy.BonitaScriptGroovyCompilationUnit;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.codehaus.groovy.eclipse.GroovyPlugin;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.groovy.eclipse.editor.GroovyTextTools;
import org.eclipse.jdt.ui.actions.IJavaEditorActionDefinitionIds;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

public class BonitaGroovyEditor extends GroovyEditor {

    private Map<String, ScriptVariable> context = new HashMap<>();

    @Override
    public void editorContextMenuAboutToShow(final IMenuManager menu) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.internal.ui.javaeditor.JavaEditor#updateStatusLine()
     */
    @Override
    protected void updateStatusLine() {

    }

    /*
     * (non-Javadoc)
     * @see org.codehaus.groovy.eclipse.editor.GroovyEditor#createActions()
     */
    @Override
    protected void createActions() {
        super.createActions();
        //Disable unsupported actions for groovy editor inside dialogs
        setAction(ITextEditorActionConstants.FIND, null);
        setAction(IJavaEditorActionDefinitionIds.SHOW_OUTLINE, null);
        setAction(IJavaEditorActionDefinitionIds.EXTRACT_CLASS, null);
        setAction(IJavaEditorActionDefinitionIds.EXTERNALIZE_STRINGS, null);
        setAction(IJavaEditorActionDefinitionIds.EXTRACT_INTERFACE, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_CALL_HIERARCHY, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_HIERARCHY, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_EDITOR, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_HYPERLINK, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_SUPER_IMPLEMENTATION, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_IMPLEMENTATION, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_ATTACHED_JAVADOC, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_STRUCTURE, null);
        setAction(IJavaEditorActionDefinitionIds.SHOW_IN_BREADCRUMB, null);
        setAction(IJavaEditorActionDefinitionIds.OPEN_TYPE_HIERARCHY, null);
        setAction(IJavaEditorActionDefinitionIds.PULL_UP, null);
        setAction(IJavaEditorActionDefinitionIds.PUSH_DOWN, null);
        setAction(IJavaEditorActionDefinitionIds.SHOW_IN_NAVIGATOR_VIEW, null);
        setAction(IJavaEditorActionDefinitionIds.SEARCH_DECLARATIONS_IN_HIERARCHY, null);
        setAction(IJavaEditorActionDefinitionIds.SEARCH_DECLARATIONS_IN_PROJECTS, null);
        setAction(IJavaEditorActionDefinitionIds.SEARCH_DECLARATIONS_IN_WORKING_SET, null);
        setAction(IJavaEditorActionDefinitionIds.SEARCH_DECLARATIONS_IN_WORKSPACE, null);
        if (fActionGroups != null) {
            fActionGroups.dispose();
            fActionGroups = null;
        }
    }

    public void setContext(Map<String, ScriptVariable> context) {
        this.context = context;
    }

    @Override
    public BonitaScriptGroovyCompilationUnit getGroovyCompilationUnit() {
        BonitaScriptGroovyCompilationUnit groovyCompilationUnit = (BonitaScriptGroovyCompilationUnit) super.getGroovyCompilationUnit();
        groovyCompilationUnit.setContext(context);
        return groovyCompilationUnit;
    }

    @Override
    public JavaSourceViewerConfiguration createJavaSourceViewerConfiguration() {
        GroovyTextTools textTools = GroovyPlugin.getDefault().getTextTools();
        return new BonitaGroovyConfiguration(textTools.getColorManager(), getPreferenceStore(), this);
    }

}
