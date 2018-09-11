/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.catchmessage;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.operation.OperationsComposite;
import org.bonitasoft.studio.expression.editor.operation.PropertyOperationsComposite;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Aurelien Pupier
 * @author Aurelie Zara
 */
public class CatchMessageContentEventSection extends AbstractBonitaDescriptionSection {

    private OperationsComposite alc;
    private Composite mainComposite;
    MessageContentExpressionValidator validator;
    private EObject lastEObject;

    @Override
    protected void createContent(final Composite parent) {
        mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(15, 25, 15, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createAutoFillButton();
        validator = new MessageContentExpressionValidator();
        alc = new PropertyOperationsComposite(getTabbedPropertySheetPage(), mainComposite,
                new AvailableExpressionTypeFilter(new String[] {
                        ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.MESSAGE_ID_TYPE }),
                new AvailableExpressionTypeFilter(
                        new String[] { ExpressionConstants.VARIABLE_TYPE }));
        alc.addActionExpressionValidator(validator);
        alc.addActionExpressionValidator(validator);
        alc.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

    }

    private void createAutoFillButton() {
        final Button autoFillButton = getWidgetFactory().createButton(mainComposite, Messages.autoFillMessageContent,
                SWT.FLAT);
        autoFillButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

                    @Override
                    public void run() {
                        final MessageFlow incomingMessag = getCatchMessageEvent().getIncomingMessag();
                        if (incomingMessag != null) {
                            final Message message = ModelHelper.findEvent(getCatchMessageEvent(), incomingMessag.getName());
                            if (message != null) {
                                mapContentForMessage(message);
                                /* refresh UI */
                                refreshUI();
                            }
                        }
                    }

                    private void mapContentForMessage(final Message message) {
                        final TableExpression throwMessageContent = message.getMessageContent();
                        for (final ListExpression row : throwMessageContent.getExpressions()) {
                            final List<org.bonitasoft.studio.model.expression.Expression> col = row.getExpressions();
                            if (col.size() == 2) {
                                final String throwMessageContentExpressionName = col.get(0).getName();
                                if (throwMessageContentExpressionName != null
                                        && !isMappingAlreadyExists(throwMessageContentExpressionName)) {
                                    createNewMessageContentLine(throwMessageContentExpressionName);
                                }
                            }
                        }
                    }

                    private boolean isMappingAlreadyExists(final String throwMessageContentExpressionName) {
                        final EList<Operation> catchMessageContents = getCatchMessageEvent().getMessageContent();
                        for (final Operation messageContent : catchMessageContents) {
                            final Expression actionExpression = messageContent.getRightOperand();
                            if (actionExpression != null
                                    && throwMessageContentExpressionName.equals(actionExpression.getName())) {
                                return true;
                            }
                        }
                        return false;
                    }
                });

            }

            private void createNewMessageContentLine(final String throwMessageContentExpressionName) {
                /* add it if not */
                final Operation newActionMessageContent = ExpressionFactory.eINSTANCE.createOperation();
                final Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
                assignment.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
                newActionMessageContent.setOperator(assignment);
                Expression rightExpression = ExpressionProviderService.getInstance()
                        .getExpressionProvider(ExpressionConstants.MESSAGE_ID_TYPE)
                        .getExpressions(lastEObject).stream()
                        .filter(exp -> throwMessageContentExpressionName.equals(exp.getName()))
                        .findFirst().orElse(null);
                if (rightExpression != null) {
                    newActionMessageContent.setRightOperand(rightExpression);
                } else {
                    newActionMessageContent.setRightOperand(ExpressionFactory.eINSTANCE.createExpression());
                }

                newActionMessageContent.setLeftOperand(ExpressionFactory.eINSTANCE.createExpression());

                /*
                 * check if there is a data with the same name,
                 * if yes, assign it
                 */
                final List<Data> accessibleData = ModelHelper.getAccessibleData(getCatchMessageEvent());
                for (final Data data : accessibleData) {
                    if (throwMessageContentExpressionName.equals(data.getName())) {
                        final Expression dataExpression = ExpressionFactory.eINSTANCE.createExpression();
                        dataExpression.setName(data.getName());
                        dataExpression.setContent(data.getName());
                        dataExpression.setReturnType(org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data));
                        dataExpression.setType(ExpressionConstants.VARIABLE_TYPE);
                        dataExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(data));
                        newActionMessageContent.setLeftOperand(dataExpression);
                    }
                }

                final Command addCommand = AddCommand.create(getEditingDomain(), getCatchMessageEvent(),
                        ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__MESSAGE_CONTENT, newActionMessageContent);
                getEditingDomain().getCommandStack().execute(addCommand);
            }

        });
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        if (lastEObject == null || lastEObject != null && !lastEObject.equals(getEObject())) {
            lastEObject = getEObject();
            refreshUI();
        }
    }

    private void refreshUI() {
        validator.setCatchMessageEvent(getCatchMessageEvent());
        alc.setEObject(getCatchMessageEvent());
        alc.setContext(new EMFDataBindingContext());
        alc.removeLinesUI();
        alc.fillTable();
        alc.refresh();
    }

    private AbstractCatchMessageEvent getCatchMessageEvent() {
        return (AbstractCatchMessageEvent) getEObject();
    }

    @Override
    public String getSectionDescription() {
        return Messages.catchMessageContentEventSectionDescription;
    }
}
