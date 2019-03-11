/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.groovy.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.model.expression.Expression;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.CompileUnit;
import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

public class ExtendedJavaContentAssistInvocationContext extends JavaContentAssistInvocationContext {

	private boolean fCUComputed = false;

	private List<Expression> variableScope;

	private Document tmpDocument;

	private int offset;

	public static final String BONITA_KEYWORDS_DATA_KEY = "bonita.keywords";

	public static final String PROCESS_VARIABLES_DATA_KEY = "process.variables";

	public ExtendedJavaContentAssistInvocationContext(final ITextViewer viewer, final int offset,
			final IEditorPart editor) {
		super(viewer, offset, editor);
	}

	public ExtendedJavaContentAssistInvocationContext(IEditorPart editor, ITextViewer viewer, int offset,
			Document tmpDocument, int tmpOffset, List<Expression> variableScope) {
		super(viewer, tmpOffset, editor);
		this.offset = offset;
		this.variableScope = variableScope;
		this.tmpDocument = tmpDocument;
	}

	public List<Expression> getVariableScope() {
		return variableScope;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext#getDocument()
	 */
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
		if (!fCUComputed) {
			final GroovyCompilationUnit compilationUnit = (GroovyCompilationUnit) super.getCompilationUnit();
			final ITextViewer viewer = getViewer();
			List<ScriptVariable> processVariables = new ArrayList<>();
			List<ScriptVariable> providedVariables = new ArrayList<>();
			if (viewer != null) {
				processVariables = (List<ScriptVariable>) viewer.getTextWidget().getData(PROCESS_VARIABLES_DATA_KEY);
				providedVariables = (List<ScriptVariable>) viewer.getTextWidget().getData(BONITA_KEYWORDS_DATA_KEY);
			}
			final IJavaProject javaProject = compilationUnit.getJavaProject();
			if (processVariables != null) {
				for (final ScriptVariable scriptVariable : processVariables) {
					try {
						addToScope(compilationUnit, scriptVariable);
					} catch (final ClassNotFoundException e) {
						BonitaStudioLog.error(e);
					}
				}
			}
			if (providedVariables != null) {
				for (final ScriptVariable scriptVariable : providedVariables) {
					try {
						addToScope(compilationUnit, scriptVariable);
					} catch (final ClassNotFoundException e) {
						BonitaStudioLog.error(e);
					}
				}
			}
			if (variableScope != null) {
				for (final Expression expression : variableScope) {
					try {
						addToScope(compilationUnit,
								new ScriptVariable(expression.getName(), expression.getReturnType()));
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

	private void addToScope(final GroovyCompilationUnit compilationUnit, final ScriptVariable scriptVariable)
			throws ClassNotFoundException {
		CompileUnit cu = compilationUnit.getModuleNode().getUnit();
		ClassNode classNode = new ClassNode(cu.getClassLoader().loadClass(scriptVariable.getType()));
		final VariableExpression variableExpression = new VariableExpression(scriptVariable.getName(), classNode);
		variableExpression.setEnd(-1);
		final VariableScope vScope = compilationUnit.getModuleNode().getStatementBlock().getVariableScope();
		vScope.putDeclaredVariable(variableExpression);
	}

}
