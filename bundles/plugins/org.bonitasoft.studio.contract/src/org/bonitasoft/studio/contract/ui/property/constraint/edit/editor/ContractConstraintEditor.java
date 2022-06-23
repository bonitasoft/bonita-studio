/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import org.bonitasoft.studio.groovy.ui.viewer.BonitaGroovyEditor;
import org.codehaus.groovy.eclipse.GroovyPlugin;
import org.codehaus.groovy.eclipse.editor.GroovyColorManager;
import org.eclipse.jdt.ui.actions.IJavaEditorActionDefinitionIds;
import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

public class ContractConstraintEditor extends BonitaGroovyEditor {

    public ContractConstraintEditor() {
        super();
        setPreferenceStore(GroovyPlugin.getDefault().getPreferenceStore());
    }

    @Override
    public void editorContextMenuAboutToShow(final IMenuManager menu) {
        menu.dispose();
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

    @Override
    public JavaSourceViewerConfiguration createJavaSourceViewerConfiguration() {
        return new ConstraintExpressionSourceViewerConfiguration(getColorManager(), getPreferenceStore(), this);
    }

    protected GroovyColorManager getColorManager() {
        return GroovyPlugin.getDefault().getTextTools().getColorManager();
    }

}
