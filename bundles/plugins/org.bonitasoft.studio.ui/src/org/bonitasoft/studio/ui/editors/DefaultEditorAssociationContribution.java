/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.editors;

import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.EditorRegistry;

/**
 * Replace default editors with filtered editor
 */
public class DefaultEditorAssociationContribution implements IStartup {

    @Override
    public void earlyStartup() {
        final EditorRegistry editorRegistry = (EditorRegistry) WorkbenchPlugin.getDefault().getEditorRegistry();
        editorRegistry.removeExtension(null, new Object[] {
                editorRegistry.findEditor(GroovyEditor.EDITOR_ID),
                editorRegistry.findEditor("org.eclipse.jdt.ui.PropertiesFileEditor"),
                editorRegistry.findEditor("org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart")
        });
    }
}
