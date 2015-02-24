/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.refactoring.core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.codehaus.groovy.eclipse.codeassist.requestor.CompletionNodeFinder;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;

/**
 * @author Romain Bioteau
 *
 */
public abstract class AbstractRefactorOperation<Y,Z,T extends RefactorPair<Y,Z>> implements IRunnableWithProgress {

    private static final int MIN_MONITOR_WORK = 3;
    private TransactionalEditingDomain domain;
    private CompoundCommand compoundCommand;
    private boolean canExecute = true;
    private boolean isCancelled = false;
    protected RefactoringOperationType operationType;
    private boolean askConfirmation;
    protected List<T> pairsToRefactor = new ArrayList<T>();

    public AbstractRefactorOperation(final RefactoringOperationType operationType) {
        this.operationType = operationType;
    }

    protected CompoundCommand buildCompoundCommand(final IProgressMonitor monitor) throws InterruptedException {
        if (compoundCommand == null) {
            compoundCommand = new CompoundCommand("Refactor Operation");
        }
        updateReferencesInScripts(monitor);
        if (canExecute()) {
            compoundCommand = doBuildCompoundCommand(compoundCommand, monitor);
        }
        return compoundCommand;
    }

    protected boolean shouldUpdateReferencesInScripts(final RefactorPair<Y, Z> pairRefactor) {
        if (pairRefactor.getOldValueName() != null) {
            return !pairRefactor.getOldValueName().equals(pairRefactor.getNewValueName());
        }
        return false;
    }

    public IRunnableWithProgress createRunnableWithProgress() {
        return new IRunnableWithProgress() {

            @Override
            public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                buildCompoundCommand(monitor);
            }
        };
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        Assert.isNotNull(domain);

        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        compoundCommand = buildCompoundCommand(monitor);
        if (canExecute()) {
            domain.getCommandStack().execute(compoundCommand);
            compoundCommand.dispose();
            compoundCommand = null;
        }
        monitor.done();
    }

    protected abstract CompoundCommand doBuildCompoundCommand(CompoundCommand cc, IProgressMonitor monitor);

    protected void updateReferencesInScripts(final IProgressMonitor monitor) throws InterruptedException {
        final Set<Expression> scriptExpressionsSetToRefactor = new HashSet<Expression>();
        for(final RefactorPair<Y,Z> pairRefactor : pairsToRefactor){
            if (shouldUpdateReferencesInScripts(pairRefactor)) {
                final Z oldValue = pairRefactor.getOldValue();
                if (oldValue instanceof EObject) {
                    scriptExpressionsSetToRefactor.addAll(ModelHelper.findAllScriptAndConditionsExpressionWithReferencedElement(getContainer(oldValue),
                            (EObject) oldValue));
                }
            }
        }
        final List<Expression> scripExpressionsToRefactor = new ArrayList<Expression>(scriptExpressionsSetToRefactor);
        if (!scripExpressionsToRefactor.isEmpty()) {
            if (scripExpressionsToRefactor.size() > MIN_MONITOR_WORK) {
                monitor.beginTask("Refactoring", scripExpressionsToRefactor.size());
            }
            final List<Expression> refactoredScriptExpression = performRefactoringForAllScripts(scripExpressionsToRefactor, monitor);
            //TODO: improve in order to filter only the data refactored AND with references
            final AbstractScriptExpressionRefactoringAction<T> action = getScriptExpressionRefactoringAction(pairsToRefactor,
                    scripExpressionsToRefactor, refactoredScriptExpression, compoundCommand, domain, operationType);
            if (action != null) {
                action.setEditingDomain(domain);
                action.setAskConfirmation(askConfirmation());
                action.run(null);
                setCanExecute(!action.isCancelled());
                setCancelled(action.isCancelled());
            } else {
                setCanExecute(true);
            }
        } else {
            setCanExecute(true);
        }
    }

    protected abstract AbstractScriptExpressionRefactoringAction<T> getScriptExpressionRefactoringAction(List<T> pairsToRefactor,
            List<Expression> scriptExpressions,
            List<Expression> refactoredScriptExpression, CompoundCommand compoundCommand, EditingDomain domain, RefactoringOperationType operationType);

    protected List<Expression> performRefactoringForAllScripts(final List<Expression> groovyScriptExpressions, final IProgressMonitor monitor)
            throws InterruptedException {
        final List<Expression> newExpressions = new ArrayList<Expression>(groovyScriptExpressions.size());
        for (final Expression expr : groovyScriptExpressions) {
            if (groovyScriptExpressions.size() > MIN_MONITOR_WORK) {
                if (monitor.isCanceled()) {
                    throw new InterruptedException("Monitor cancelled by user");
                }
                monitor.subTask(String.format("Searching '%s' references in script expressions... [%s/%s]", pairsToRefactor.get(0).getOldValueName(),
                        groovyScriptExpressions.indexOf(expr) + 1,
                        groovyScriptExpressions.size()));
            }
            final Expression newExpr = EcoreUtil.copy(expr);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expr.getType())) {
                newExpr.setContent(performGroovyRefactoring(expr.getContent()));
            } else {
                String textRefactored = expr.getContent();
                for(final RefactorPair<Y, Z> pairToRefactor : pairsToRefactor){
                    textRefactored = performTextReplacement(pairToRefactor.getOldValueName(), pairToRefactor.getNewValueName(), textRefactored);
                }
                newExpr.setContent(textRefactored);
            }
            newExpressions.add(newExpr);
            monitor.worked(1);
        }
        return newExpressions;
    }

    private String performTextReplacement(final String elementNameToUpdate, final String newElementName, final String script) {
        final String contextRegex = "[\\W^_]";
        final Pattern p = Pattern.compile(elementNameToUpdate);
        final Matcher m = p.matcher(script);
        final StringBuffer buf = new StringBuffer();
        while (m.find()) {
            String prefix = null;
            String suffix = null;
            if (m.start() > 0) {
                prefix = script.substring(m.start() - 1, m.start());
            }
            if (m.end() < script.length()) {
                suffix = script.substring(m.end(), m.end() + 1);
            }
            if (prefix == null && suffix == null) {
                m.appendReplacement(buf, newElementName);
            } else {
                if (prefix != null && prefix.matches(contextRegex) && suffix == null) {
                    m.appendReplacement(buf, newElementName);
                } else {
                    if (prefix == null && suffix != null && suffix.matches(contextRegex)) {
                        m.appendReplacement(buf, newElementName);
                    } else {
                        if (prefix != null && suffix != null && prefix.matches(contextRegex) && suffix.matches(contextRegex)) {
                            m.appendReplacement(buf, newElementName);
                        }
                    }
                }
            }

        }
        m.appendTail(buf);
        return buf.toString();
    }

    private String performGroovyRefactoring(final String script) {
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
            for(final RefactorPair<Y,Z> pairToRefactor : pairsToRefactor){
                variableToRename.put(pairToRefactor.getOldValueName(), pairToRefactor.getNewValueName());
            }
            final MultiTextEdit rename = variableRenamer.rename(astNode, variableToRename);
            if (rename.getChildrenSize() > 0) {
                final Document document = new Document(script);
                try {
                    rename.apply(document);
                } catch (final MalformedTreeException e) {
                    BonitaStudioLog.error(e);
                } catch (final BadLocationException e) {
                    BonitaStudioLog.error(e);
                }
                final String scriptRefactored = document.get();
                forceDelete(compilationUnitFrom);
                return scriptRefactored;
            }
        }
        tmpGroovyFileStore.delete();
        forceDelete(compilationUnitFrom);
        return script;
    }

    protected void forceDelete(final GroovyCompilationUnit compilationUnit) {
        try {
            compilationUnit.delete(true, new NullProgressMonitor());
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        }
    }

    public void setEditingDomain(final TransactionalEditingDomain domain) {
        this.domain = domain;
    }

    protected TransactionalEditingDomain getEditingDomain() {
        return domain;
    }

    /**
     * if you are using it surely means that you are doing things in several transactions
     * and it is bad as it breaks undo/redo
     */
    @Deprecated ()
    public void setCompoundCommand(final CompoundCommand compoundCommand) {
        this.compoundCommand = compoundCommand;
    }

    public CompoundCommand getCompoundCommand() {
        return compoundCommand;
    }

    public void setAskConfirmation(final boolean askConfirmation) {
        this.askConfirmation = askConfirmation;
    }

    protected boolean askConfirmation() {
        return askConfirmation;
    }

    public boolean canExecute() {
        return canExecute;
    }

    protected void setCanExecute(final boolean canExecute) {
        this.canExecute = canExecute;
    }

    public void addItemToRefactor(final Y newItem, final Z oldItem) {
        pairsToRefactor.add(createRefactorPair(newItem, oldItem));
    }

    protected abstract T createRefactorPair(Y newItem, Z oldItem);

    protected abstract EObject getContainer(Z oldValue);

    public boolean isCancelled() {
        return isCancelled;
    }

    protected void setCancelled(final boolean isCancelled) {
        this.isCancelled = isCancelled;
    }


}
