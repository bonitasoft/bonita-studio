/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.groovy.ui.contentassist;

import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

public class ExtendedJavaContentAssistInvocationContext extends JavaContentAssistInvocationContext {

    private boolean fCUComputed = false;

    public ExtendedJavaContentAssistInvocationContext(final ITextViewer viewer, final int offset, final IEditorPart editor) {
        super(viewer, offset, editor);
    }

    @Override
    public ICompilationUnit getCompilationUnit() {
        if (!fCUComputed) {
            final GroovyCompilationUnit compilationUnit = (GroovyCompilationUnit) super.getCompilationUnit();
            final ClassLoader classloader = RepositoryManager.getInstance().getCurrentRepository().createProjectClassloader(Repository.NULL_PROGRESS_MONITOR);
            final List<ScriptVariable> processVariables = (List<ScriptVariable>) getViewer().getTextWidget()
                    .getData(GroovyViewer.PROCESS_VARIABLES_DATA_KEY);
            final List<ScriptVariable> providedVariables = (List<ScriptVariable>) getViewer().getTextWidget()
                    .getData(GroovyViewer.BONITA_KEYWORDS_DATA_KEY);
            final VariableScope variableScope = compilationUnit.getModuleNode().getStatementBlock().getVariableScope();
            if (processVariables != null) {
                for (final ScriptVariable scriptVariable : processVariables) {
                    try {
                        addToScope(classloader, variableScope, scriptVariable);
                    } catch (final ClassNotFoundException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
            if (providedVariables != null) {
                for (final ScriptVariable scriptVariable : providedVariables) {
                    try {
                        addToScope(classloader, variableScope, scriptVariable);
                    } catch (final ClassNotFoundException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
            fCUComputed = true;
            return compilationUnit;
        }
        return super.getCompilationUnit();
    }

    private void addToScope(final ClassLoader classloader, final VariableScope variableScope, final ScriptVariable scriptVariable)
            throws ClassNotFoundException {
        final ClassNode classNode = new ClassNode(classloader.loadClass(scriptVariable.getType()));
        final VariableExpression variableExpression = new VariableExpression(scriptVariable.getName(), classNode);
        variableExpression.setEnd(-1);
        variableScope.putDeclaredVariable(variableExpression);
    }
}
