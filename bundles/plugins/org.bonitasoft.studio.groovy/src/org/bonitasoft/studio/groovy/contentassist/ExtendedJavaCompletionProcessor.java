/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.groovy.contentassist;

import org.eclipse.jdt.internal.ui.text.java.JavaCompletionProcessor;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;

public class ExtendedJavaCompletionProcessor extends JavaCompletionProcessor {

    public ExtendedJavaCompletionProcessor(final IEditorPart editor, final ContentAssistant assistant,
            final String partition) {
        super(editor, assistant, partition);
    }

    @Override
    protected ContentAssistInvocationContext createContext(final ITextViewer viewer, final int offset) {
        return new ExtendedJavaContentAssistInvocationContext(viewer, offset, fEditor);
    }

}
