/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.core;

import java.util.Collection;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.ISourceProvider;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;

public class CustomHandlerService implements IHandlerService {

    private IEvaluationContext evaluationContext;

    public CustomHandlerService(IEvaluationContext evaluationContext) {
        this.evaluationContext = evaluationContext;
    }

    @Override
    public IEvaluationContext getCurrentState() {
        return evaluationContext;
    }

    @Override
    public void addSourceProvider(ISourceProvider provider) {

    }

    @Override
    public void removeSourceProvider(ISourceProvider provider) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public IHandlerActivation activateHandler(IHandlerActivation activation) {
        return null;
    }

    @Override
    public IHandlerActivation activateHandler(String commandId, IHandler handler) {
        return null;
    }

    @Override
    public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression) {
        return null;
    }

    @Override
    public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression, boolean global) {
        return null;
    }

    @Override
    public IHandlerActivation activateHandler(String commandId, IHandler handler, Expression expression,
            int sourcePriorities) {
        return null;
    }

    @Override
    public ExecutionEvent createExecutionEvent(Command command, Event event) {
        return null;
    }

    @Override
    public ExecutionEvent createExecutionEvent(ParameterizedCommand command, Event event) {
        return null;
    }

    @Override
    public void deactivateHandler(IHandlerActivation activation) {

    }

    @Override
    public void deactivateHandlers(Collection activations) {

    }

    @Override
    public Object executeCommand(String commandId, Event event)
            throws ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {
        return null;
    }

    @Override
    public Object executeCommand(ParameterizedCommand command, Event event)
            throws ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {
        return null;
    }

    @Override
    public Object executeCommandInContext(ParameterizedCommand command, Event event, IEvaluationContext context)
            throws ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {
        return null;
    }

    @Override
    public IEvaluationContext createContextSnapshot(boolean includeSelection) {
        return null;
    }

    @Override
    public void readRegistry() {

    }

    @Override
    public void setHelpContextId(IHandler handler, String helpContextId) {

    }

}
