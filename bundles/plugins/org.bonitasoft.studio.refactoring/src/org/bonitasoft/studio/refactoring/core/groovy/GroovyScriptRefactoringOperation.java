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
package org.bonitasoft.studio.refactoring.core.groovy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.refactoring.core.ProcessVariableRenamer;
import org.codehaus.groovy.eclipse.codeassist.requestor.CompletionNodeFinder;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;

/**
 * @author Romain Bioteau
 */
public class GroovyScriptRefactoringOperation implements IRunnableWithProgress {

    private String script;
    private final List<ReferenceDiff> diffs;

    public GroovyScriptRefactoringOperation(final String script, final List<ReferenceDiff> diffs) {
        this.script = script;
        this.diffs = diffs;
    }

    public List<ReferenceDiff> getDiffs() {
        return diffs;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        final ProvidedGroovyRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ProvidedGroovyRepositoryStore.class);
        final GroovyFileStore tmpGroovyFileStore = store.createRepositoryFileStore("script" + System.currentTimeMillis() + ".groovy");
        tmpGroovyFileStore.save(script);
        final GroovyCompilationUnit compilationUnitFrom = (GroovyCompilationUnit) JavaCore.createCompilationUnitFrom(tmpGroovyFileStore.getResource());

        final CompletionNodeFinder finder = new CompletionNodeFinder(0, 0, 0, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
        final ContentAssistContext assistContext = finder.findContentAssistContext(compilationUnitFrom);

        org.codehaus.groovy.ast.ASTNode astNode = null;
        if (assistContext != null) {
            astNode = assistContext.containingCodeBlock;
        }
        if (astNode != null) {
            final ProcessVariableRenamer variableRenamer = new ProcessVariableRenamer();
            final Map<String, String> variableToRename = new HashMap<String, String>();
            for (final ReferenceDiff diff : diffs) {
                variableToRename.put(diff.getOldRef(), diff.getNewRef());
            }
            final MultiTextEdit rename = variableRenamer.rename(astNode, variableToRename);
            if (rename.getChildrenSize() > 0) {
                final Document document = new Document(script);
                try {
                    rename.apply(document);
                } catch (final MalformedTreeException | BadLocationException e) {
                    BonitaStudioLog.error(e);
                }
                script = document.get();
            }
        }
        tmpGroovyFileStore.delete();
        forceDelete(compilationUnitFrom);
    }

    public String getScript() {
        return script;
    }

    private void forceDelete(final GroovyCompilationUnit compilationUnit) {
        try {
            if (compilationUnit.exists()) {
                compilationUnit.delete(true, new NullProgressMonitor());
            }
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        }
    }

}
