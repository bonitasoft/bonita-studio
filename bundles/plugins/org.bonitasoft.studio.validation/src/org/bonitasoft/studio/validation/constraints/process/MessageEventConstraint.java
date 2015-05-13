/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints.process;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CorrelationTypeActive;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.validation.ValidationPlugin;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class MessageEventConstraint extends AbstractLiveValidationMarkerConstraint {

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature featureTriggered = ctx.getFeature();
        if (featureTriggered.equals(ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION)) {
            final Expression targetProcess = (Expression) ctx.getFeatureNewValue();
            if (targetProcess == null || targetProcess.getContent() == null || targetProcess.getContent().isEmpty()) {
                return ctx.createFailureStatus(Messages.targetProcessNotSet);
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.messageEventConstraint";
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final Message event = (Message) ctx.getTarget();
        if (event.getTargetProcessExpression() == null || event.getTargetProcessExpression().getContent() == null
                || event.getTargetProcessExpression().getContent().isEmpty()) {
            return ctx.createFailureStatus(Messages.targetProcessNotSet);
        }
        else {
            if (ExpressionConstants.CONSTANT_TYPE.equals(event.getTargetProcessExpression().getType())) {
                final DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
                final List<AbstractProcess> processes = store.findProcesses(event.getTargetProcessExpression().getContent());
                if (processes.isEmpty()) {
                    return ctx
                            .createFailureStatus(Messages.bind(Messages.processDoesNotExist, event.getTargetProcessExpression().getContent(), event.getName()));
                }
                final Expression targetElem = event.getTargetElementExpression();
                if (targetElem != null && targetElem.getContent() != null && !targetElem.getContent().isEmpty()
                        && targetElem.getType().equals(ExpressionConstants.CONSTANT_TYPE)) {
                    final String targetElemName = targetElem.getContent();
                    for (final AbstractProcess p : processes) {
                        final List<AbstractCatchMessageEvent> events = ModelHelper.getAllItemsOfType(p, ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT);
                        for (final AbstractCatchMessageEvent ev : events) {
                            if (targetElemName.equals(ev.getName())) {
                                if (event.getCorrelation().getCorrelationType() != CorrelationTypeActive.INACTIVE) {
                                    final List<String> targetKeyList = new ArrayList<String>();
                                    final TableExpression correlation = ev.getCorrelation();
                                    if (correlation != null) {
                                        for (final ListExpression listExpression : correlation.getExpressions()) {
                                            targetKeyList.add(listExpression.getExpressions().get(0).getContent());
                                        }
                                    }
                                    final MultiStatus multi = new MultiStatus(ValidationPlugin.PLUGIN_ID, IStatus.OK, "", null);
                                    final List<String> eventKeyList = new ArrayList<String>();
                                    for (final ListExpression listExpression : event.getCorrelation().getCorrelationAssociation().getExpressions()) {
                                        if (listExpression.getExpressions().size() > 0) {
                                            final String eventKeyName = listExpression.getExpressions().get(0).getContent();
                                            eventKeyList.add(eventKeyName);
                                            if (!targetKeyList.contains(eventKeyName)) {
                                                final String[] messageArgs = { eventKeyName, event.getSource().getName(), targetElemName };
                                                multi.add(ctx.createFailureStatus(Messages.bind(Messages.Validation_CorrelationKeyNotUsed, messageArgs)));
                                            }
                                        }
                                    }
                                    for (final String eventKey : targetKeyList) {
                                        if (!eventKeyList.contains(eventKey)) {
                                            final String[] messageArgs = { eventKey, targetElemName, event.getSource().getName() };
                                            multi.add(ctx.createFailureStatus(Messages.bind(Messages.Validation_CorrelationKeyNotDefine, messageArgs)));
                                        }
                                    }
                                    if (!multi.isOK()) {
                                        return multi;
                                    }
                                }
                                return ctx.createSuccessStatus();
                            }
                        }
                    }
                    return ctx.createFailureStatus(Messages.bind(Messages.targetCatchMessageNotExists, targetElemName, event.getTargetProcessExpression()
                            .getContent()));
                }
            }
        }
        return ctx.createSuccessStatus();
    }

}
