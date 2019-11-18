/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.groovy.contentassist;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.groovy.BonitaScriptGroovyCompilationUnit;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

public class ExtendedJavaContentAssistInvocationContext extends JavaContentAssistInvocationContext {

    private Map<String, ScriptVariable> context;

    private Document tmpDocument;

    private int offset;

    public ExtendedJavaContentAssistInvocationContext(final ITextViewer viewer, final int offset,
            final IEditorPart editor) {
        super(viewer, offset, editor);
        this.context = new HashMap<>();
    }

    public ExtendedJavaContentAssistInvocationContext(IEditorPart editor, ITextViewer viewer, int offset,
            Document tmpDocument, int tmpOffset, Map<String, ScriptVariable> context) {
        super(viewer, tmpOffset, editor);
        this.offset = offset;
        this.context = context;
        this.tmpDocument = tmpDocument;
    }

    @Override
    public IDocument getDocument() {
        if (tmpDocument != null) {
            return tmpDocument;
        }
        return super.getDocument();
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public ICompilationUnit getCompilationUnit() {
        ICompilationUnit compilationUnit = super.getCompilationUnit();
        if (compilationUnit instanceof BonitaScriptGroovyCompilationUnit) {
            ((BonitaScriptGroovyCompilationUnit) compilationUnit).setContext(context);
        }
        return compilationUnit;
    }

}
