/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.refactoring.core.script;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.GroovyCompilationUnitFactory;
import org.bonitasoft.studio.refactoring.core.ProcessVariableRenamer;
import org.codehaus.groovy.eclipse.codeassist.requestor.CompletionNodeFinder;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;

/**
 * @author Romain Bioteau
 */
public class GroovyScriptRefactoringOperation implements IScriptRefactoringOperation {

    private String script;
    private final List<ReferenceDiff> diffs;
    private final GroovyCompilationUnitFactory groovyCompilationUnitFactory;

    public GroovyScriptRefactoringOperation(final String script, final List<ReferenceDiff> diffs,
            final GroovyCompilationUnitFactory groovyCompilationUnitFactory) {
        this.script = script;
        this.diffs = diffs;
        this.groovyCompilationUnitFactory = groovyCompilationUnitFactory;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        GroovyCompilationUnit compilationUnit = null;
        org.codehaus.groovy.ast.ASTNode astNode = null;
        try {
            compilationUnit = (GroovyCompilationUnit) groovyCompilationUnitFactory.newCompilationUnit(script, monitor);
            final CompletionNodeFinder finder = new CompletionNodeFinder(0, 0, 0, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
            final ContentAssistContext assistContext = finder.findContentAssistContext(compilationUnit);
            if (assistContext != null) {
                astNode = assistContext.containingCodeBlock;
            }
        } catch (final JavaModelException e1) {
            BonitaStudioLog.error("Failed to retrieve ASTNode from groovy script.", e1);
        }

        if (astNode != null) {
            final ProcessVariableRenamer variableRenamer = new ProcessVariableRenamer();
            final MultiTextEdit rename = variableRenamer.rename(astNode, diffs);
            if (rename.getChildrenSize() > 0) {
                final Document document = new Document(script);
                try {
                    rename.apply(document);
                } catch (final MalformedTreeException | BadLocationException e) {
                    BonitaStudioLog.error(e);
                }
                script = document.get();
            }
            try {
                if (compilationUnit != null) {
                    compilationUnit.delete(true, monitor);
                }
            } catch (final JavaModelException e) {
                BonitaStudioLog.error("Failed to remove compilation unit after refactor operation.", e);
            }
        }
    }

    protected String newScriptName() {
        return "script" + System.currentTimeMillis() + ".groovy";
    }

    protected IJavaProject javaProject() {
        return RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.refactoring.core.script.IScriptRefactoringOperation#getRefactoredScript()
     */
    @Override
    public String getRefactoredScript() {
        return script;
    }

}
