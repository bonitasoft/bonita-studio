/**
 * Copyright (C) 2012-2015 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.refactoring.core;

import static com.google.common.base.Predicates.and;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.bonitasoft.studio.common.predicate.ExpressionPredicates.containingReferencedElement;
import static org.bonitasoft.studio.common.predicate.ExpressionPredicates.withExpressionType;
import static org.bonitasoft.studio.common.predicate.ExpressionPredicates.withReferencedElement;
import static org.bonitasoft.studio.refactoring.core.script.ReferenceDiff.newReferenceDiff;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.refactoring.core.script.ConditionExpressionScriptContrainer;
import org.bonitasoft.studio.refactoring.core.script.GroovyExpressionScriptContrainer;
import org.bonitasoft.studio.refactoring.core.script.GroovyScriptRefactoringOperationFactory;
import org.bonitasoft.studio.refactoring.core.script.ReferenceDiff;
import org.bonitasoft.studio.refactoring.core.script.ScriptContainer;
import org.bonitasoft.studio.refactoring.core.script.TextExpressionScriptContainer;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractRefactorOperation<Y extends EObject, Z extends EObject, T extends RefactorPair<Y, Z>>
        implements IRunnableWithProgress {

    private static final int MIN_MONITOR_WORK = 3;
    private TransactionalEditingDomain domain;
    private CompoundCommand compoundCommand;
    private boolean canExecute = true;
    private boolean isCancelled = false;
    protected RefactoringOperationType operationType;
    private boolean askConfirmation;
    protected List<T> pairsToRefactor = new ArrayList<>();
    private final DependencyFeatureNameResolver dependencyFeatureNameResolver;

    public AbstractRefactorOperation(final RefactoringOperationType operationType) {
        this.operationType = operationType;
        compoundCommand = new CompoundCommand("Refactor Operation");
        dependencyFeatureNameResolver = new DependencyFeatureNameResolver();
    }

    protected CompoundCommand buildCompoundCommand(final IProgressMonitor monitor)
            throws InterruptedException, InvocationTargetException {
        if (canExecute()) {
            updateReferencesInScripts(monitor);
        }

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
        if (canExecute()) {
            compoundCommand = buildCompoundCommand(monitor);
        }

        if (canExecute()) {
            domain.getCommandStack().execute(compoundCommand);
            compoundCommand.dispose();
            compoundCommand = null;
        }
        monitor.done();
    }

    protected abstract CompoundCommand doBuildCompoundCommand(CompoundCommand cc, IProgressMonitor monitor);

    protected void updateReferencesInScripts(final IProgressMonitor monitor)
            throws InterruptedException, InvocationTargetException {
        final Set<ScriptContainer<?>> scriptExpressionsSetToRefactor = newHashSet();
        for (final RefactorPair<Y, Z> pairRefactor : pairsToRefactor) {
            if (shouldUpdateReferencesInScripts(pairRefactor)) {
                final Z oldValue = pairRefactor.getOldValue();
                if (oldValue instanceof EObject) {
                    scriptExpressionsSetToRefactor.addAll(allScriptWithReferencedElement(pairRefactor));
                }
            }
        }
        final List<ScriptContainer<?>> scripExpressionsToRefactor = newArrayList(scriptExpressionsSetToRefactor);
        if (!scripExpressionsToRefactor.isEmpty()) {
            if (scripExpressionsToRefactor.size() > MIN_MONITOR_WORK) {
                monitor.beginTask("Refactoring", scripExpressionsToRefactor.size());
            }
            performRefactoringForAllScripts(scripExpressionsToRefactor, monitor);
            final ScriptRefactoringAction<T> action = createScriptExpressionRefactoringAction(pairsToRefactor,
                    scripExpressionsToRefactor, compoundCommand, domain, operationType);
            if (action != null) {
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

    protected Set<ScriptContainer<?>> allScriptWithReferencedElement(final RefactorPair<Y, Z> pairRefactor) {
        final Set<ScriptContainer<?>> result = newHashSet();
        final Z oldValue = pairRefactor.getOldValue();
        final EObject container = getContainer(oldValue);
        result.addAll(allGroovyScriptWithReferencedElement(container, oldValue));
        result.addAll(allPatternScriptWithReferencedElement(container, oldValue));
        result.addAll(allConditionExpressionWithReferencedElement(container, oldValue));
        return result;
    }

    private Collection<? extends ScriptContainer<?>> allPatternScriptWithReferencedElement(final EObject container,
            final Z referencedElement) {
        return Sets.newHashSet(transform(
                filter(ModelHelper.getAllElementOfTypeIn(container, Expression.class),
                        and(withExpressionType(ExpressionConstants.PATTERN_TYPE), withReferencedElement(referencedElement))),
                toTextExpressionScriptContainer(
                        dependencyFeatureNameResolver.resolveNameDependencyFeatureFor(referencedElement))));
    }

    private Collection<? extends ScriptContainer<?>> allConditionExpressionWithReferencedElement(final EObject container,
            final Z referencedElement) {
        return Sets.newHashSet(transform(
                filter(ModelHelper.getAllElementOfTypeIn(container, Expression.class),
                        and(withExpressionType(ExpressionConstants.CONDITION_TYPE),
                                withReferencedElement(referencedElement))),
                toConditionExpressionScriptContainer(
                        dependencyFeatureNameResolver.resolveNameDependencyFeatureFor(referencedElement))));
    }

    private Collection<? extends ScriptContainer<?>> allGroovyScriptWithReferencedElement(final EObject container,
            final Z referencedElement) {
        return ModelHelper.getAllElementOfTypeIn(container, Expression.class).stream()
                .filter(exp -> Objects.equals(exp.getType(), ExpressionConstants.SCRIPT_TYPE))
                .filter(containingReferencedElement(referencedElement))
                .map(toGroovyExpressionScriptContainer(
                        dependencyFeatureNameResolver.resolveNameDependencyFeatureFor(referencedElement)))
                .collect(Collectors.toSet());
    }

    private Function<Expression, TextExpressionScriptContainer> toTextExpressionScriptContainer(
            final EAttribute dependencyNameFeature) {
        return new Function<Expression, TextExpressionScriptContainer>() {

            @Override
            public TextExpressionScriptContainer apply(final Expression expression) {
                return new TextExpressionScriptContainer(expression, dependencyNameFeature);
            }
        };
    }

    private Function<Expression, ConditionExpressionScriptContrainer> toConditionExpressionScriptContainer(
            final EAttribute dependencyNameFeature) {
        return new Function<Expression, ConditionExpressionScriptContrainer>() {

            @Override
            public ConditionExpressionScriptContrainer apply(final Expression expression) {
                return new ConditionExpressionScriptContrainer(expression, dependencyNameFeature);
            }
        };
    }

    private java.util.function.Function<Expression, GroovyExpressionScriptContrainer> toGroovyExpressionScriptContainer(
            final EAttribute dependencyNameFeature) {
        return expression -> new GroovyExpressionScriptContrainer(expression, dependencyNameFeature,
                new GroovyScriptRefactoringOperationFactory());
    }

    protected ScriptRefactoringAction<T> createScriptExpressionRefactoringAction(final List<T> pairsToRefactor,
            final List<ScriptContainer<?>> scriptExpressions, final CompoundCommand compoundCommand,
            final TransactionalEditingDomain domain,
            final RefactoringOperationType operationType) {
        return new ScriptRefactoringAction<>(pairsToRefactor, scriptExpressions, compoundCommand, domain, operationType);
    }

    protected void performRefactoringForAllScripts(final List<ScriptContainer<?>> groovyScriptExpressions,
            final IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        for (final ScriptContainer<?> expr : groovyScriptExpressions) {
            if (groovyScriptExpressions.size() > MIN_MONITOR_WORK) {
                if (monitor.isCanceled()) {
                    throw new InterruptedException("Monitor cancelled by user");
                }
                monitor.subTask(String.format("Searching '%s' references in script expressions... [%s/%s]",
                        pairsToRefactor.get(0).getOldValueName(),
                        groovyScriptExpressions.indexOf(expr) + 1,
                        groovyScriptExpressions.size()));
            }
            expr.updateScript(newArrayList(transform(pairsToRefactor, toRefDiff())), monitor);
            monitor.worked(1);
        }
    }

    private Function<RefactorPair<Y, Z>, ReferenceDiff> toRefDiff() {
        return new Function<RefactorPair<Y, Z>, ReferenceDiff>() {

            @Override
            public ReferenceDiff apply(final RefactorPair<Y, Z> input) {
                return newReferenceDiff(input.getOldValueName(), input.getNewValueName());
            }
        };
    }

    public void setEditingDomain(final TransactionalEditingDomain domain) {
        this.domain = domain;
    }

    public TransactionalEditingDomain getEditingDomain() {
        return domain;
    }

    /**
     * if you are using it surely means that you are doing things in several transactions
     * and it is bad as it breaks undo/redo
     */
    @Deprecated()
    public void setCompoundCommand(final CompoundCommand compoundCommand) {
        this.compoundCommand = compoundCommand;
    }

    public CompoundCommand getCompoundCommand() {
        return compoundCommand;
    }

    public void setAskConfirmation(final boolean askConfirmation) {
        this.askConfirmation = askConfirmation;
    }

    public boolean askConfirmation() {
        return askConfirmation;
    }

    public boolean canExecute() {
        return canExecute && compoundCommand != null;
    }

    public void setCanExecute(final boolean canExecute) {
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

    public void clearItemToRefactor() {
        pairsToRefactor.clear();
    }

}
