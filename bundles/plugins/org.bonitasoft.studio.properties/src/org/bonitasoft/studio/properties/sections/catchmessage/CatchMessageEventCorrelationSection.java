/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.catchmessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Correlation;
import org.bonitasoft.studio.model.process.CorrelationTypeActive;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Aurelien Pupier
 * @author Aurelie Zara (add validator on correlation keys)
 * 
 */
public class CatchMessageEventCorrelationSection extends
AbstractBonitaDescriptionSection {

    private TabbedPropertySheetPage aTabbedPropertySheetPage;
    private ExpressionCollectionViewer ecv;

    @Override
    public void createControls(Composite parent,
            TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        this.aTabbedPropertySheetPage = aTabbedPropertySheetPage;
        doCreateControls(parent);
    }

    private void doCreateControls(Composite parent) {

        Composite mainComposite = aTabbedPropertySheetPage.getWidgetFactory()
                .createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1)
                .extendedMargins(15, 5, 15, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createAutoFillButton(mainComposite);
        ecv = new ExpressionCollectionViewer(mainComposite, 5, false, 2, true,
                null, aTabbedPropertySheetPage.getWidgetFactory(),
                getEditingDomain(), false, false);
        ecv.setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
                .hint(100, SWT.DEFAULT).create());
        ecv.setAddRowLabel(Messages.AddCorrelation);
        ecv.setRemoveRowLabel(Messages.removeCorrelation);
        IExpressionNatureProvider provider = new CorrelationIdNatureProvider();
        provider.setContext(getCatchMessageEvent());
        ecv.addExpressionNatureProvider(provider);

        ecv.addModifyListener(new Listener() {

            @Override
            public void handleEvent(Event event) {
                validateCorrelationKeys();

            }

        });
        List<ViewerFilter> filters = new ArrayList<ViewerFilter>(1);
        filters.add(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement,
                    Object element) {
                if (element instanceof Expression) {
                    return ExpressionConstants.CONSTANT_TYPE
                            .equals(((Expression) element).getType());
                } else if (element instanceof IExpressionProvider) {
                    return ExpressionConstants.CONSTANT_TYPE
                            .equals(((IExpressionProvider) element)
                                    .getExpressionType());
                } else {
                    return false;
                }
            }
        });
        ecv.setViewerFilters(filters);
        refresh();
    }

    private void createAutoFillButton(Composite parent) {
        Button autoFillButton = aTabbedPropertySheetPage
                .getWidgetFactory()
                .createButton(parent, Messages.autoFillMessageContent, SWT.FLAT);
        autoFillButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
					
					@Override
					public void run() {
						  createCorrelationKeyValueLinesIfNeeded();
					}
				});
              
            }

			protected void createCorrelationKeyValueLinesIfNeeded() {
				MessageFlow incomingMessag = getCatchMessageEvent().getIncomingMessag();
                if (incomingMessag != null) {
                    final Message message = ModelHelper.findEvent(
                            getCatchMessageEvent(), incomingMessag.getName());
                    if (message != null) {
                        Correlation correlation = message.getCorrelation();
                        if (!CorrelationTypeActive.INACTIVE.equals(correlation.getCorrelationType())) {
                            TableExpression correlationAssociationSourceMessage = correlation.getCorrelationAssociation();
                            for (ListExpression correlationAssociationLineSourceMessage : correlationAssociationSourceMessage.getExpressions()) {
                                Expression correlationKeySourceMessage = correlationAssociationLineSourceMessage.getExpressions().get(0);
                                TableExpression correlationAssociationTargetMessage = getCatchMessageEvent().getCorrelation();
                                if (correlationAssociationTargetMessage != null) {
                                    boolean alreadyExists = false;
                                    String correlationKeySourceMessageName = correlationKeySourceMessage.getName();
                                    if(correlationKeySourceMessageName != null){
                                        for (ListExpression correlationAssociationLineTargetMessage : correlationAssociationTargetMessage.getExpressions()) {
                                            Expression correlationKeyTargetMessage = correlationAssociationLineTargetMessage.getExpressions().get(0);
                                            if (correlationKeySourceMessageName != null
                                            		&& correlationKeyTargetMessage != null
                                            		&& correlationKeySourceMessageName.equals(correlationKeyTargetMessage.getName())) {
                                                alreadyExists = true;
                                                break;
                                            }
                                        }
                                        if (!alreadyExists) {
                                            createCorrelationKeyValueLineFor(correlationKeySourceMessageName);
                                        }
                                    }
                                }
                            }
                        }
                        /* refresh UI */
                        ecv.refresh();
                    }

                }
			}

            protected void createCorrelationKeyValueLineFor(
                    String correlationKeySourceMessageName) {
                ListExpression keyValueCorrelationExpressionToAdd = ExpressionFactory.eINSTANCE
                        .createListExpression();
                Expression correlationKeyToAdd = ExpressionFactory.eINSTANCE
                        .createExpression();
                correlationKeyToAdd.setContent(correlationKeySourceMessageName);
                correlationKeyToAdd.setName(correlationKeySourceMessageName);
                correlationKeyToAdd.setReturnType(String.class.getName());
                correlationKeyToAdd.setType(ExpressionConstants.CONSTANT_TYPE);
                keyValueCorrelationExpressionToAdd.getExpressions().add(
                        correlationKeyToAdd);

                /* Search if there is a data with an equivalent name */
                List<Data> accessibleData = ModelHelper
                        .getAccessibleData(getCatchMessageEvent());
                for (Data data : accessibleData) {
                    if (correlationKeySourceMessageName.equals(data.getName())) {
                        Expression correlationValueToAdd = ExpressionFactory.eINSTANCE
                                .createExpression();
                        correlationValueToAdd.setContent(data.getName());
                        correlationValueToAdd.setName(data.getName());
                        correlationValueToAdd .setReturnType(org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data));
                        correlationValueToAdd .setType(ExpressionConstants.VARIABLE_TYPE);
                        correlationValueToAdd.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(data));
                        keyValueCorrelationExpressionToAdd.getExpressions() .add(correlationValueToAdd);
                    }
                }
                Command addCommand = AddCommand
                        .create(getEditingDomain(),
                                getCatchMessageEvent().getCorrelation(),
                                ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS,
                                keyValueCorrelationExpressionToAdd);
                getEditingDomain().getCommandStack().execute(addCommand);
            }
        });
    }

    private AbstractCatchMessageEvent getCatchMessageEvent() {
        return (AbstractCatchMessageEvent) getEObject();
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        if (ecv != null && getEObject() != null) {
            ecv.setInput(getEObject());
            TableExpression messageCorrelation = getCatchMessageEvent()
                    .getCorrelation();
            if (messageCorrelation == null) {
                messageCorrelation = ExpressionFactory.eINSTANCE
                        .createTableExpression();
                getEditingDomain()
                .getCommandStack()
                .execute(
                        SetCommand
                        .create(getEditingDomain(),
                                getCatchMessageEvent(),
                                ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__CORRELATION,
                                messageCorrelation));
            }
            ecv.setSelection(messageCorrelation);

            ecv.setEditingDomain(getEditingDomain());
            ecv.refresh();
        }
    }

    protected void validateCorrelationKeys() {
        //TODO add validation to context
        IValidator validator = new IValidator() {

            @Override
            public IStatus validate(Object arg0) {
                if ((arg0 != null)
                        && (arg0 instanceof AbstractCatchMessageEvent)) {
                    TableExpression table = null;
                    if (ecv.getViewer().getInput() instanceof TableExpression) {
                        table = (TableExpression) ecv.getViewer().getInput();
                    }
                    if (table != null) {
                        AbstractCatchMessageEvent messageEvent = (AbstractCatchMessageEvent) arg0;
                        MessageFlow incomingMessag = messageEvent
                                .getIncomingMessag();
                        Set<String> correlationKeys = new HashSet<String>();
                        TableExpression throwCorrelations = null;
                        if (incomingMessag != null) {
                            final Message message = ModelHelper.findEvent(
                                    messageEvent, incomingMessag.getName());
                            if (message != null) {
                                throwCorrelations = message.getCorrelation()
                                        .getCorrelationAssociation();
                                for (ListExpression row : throwCorrelations
                                        .getExpressions()) {
                                    List<org.bonitasoft.studio.model.expression.Expression> col = row
                                            .getExpressions();
                                    if (col.size() == 2) {
                                        correlationKeys.add(col.get(0)
                                                .getName());
                                    }
                                }
                                for (ListExpression row : table
                                        .getExpressions()) {
                                    List<org.bonitasoft.studio.model.expression.Expression> col = row
                                            .getExpressions();
                                    if (col.size() == 2) {
                                        Expression expr = col.get(0);
                                        if (expr != null
                                                && expr.getName() != null
                                                && !expr.getName().isEmpty()
                                                && ExpressionConstants.CONSTANT_TYPE
                                                .equals(expr.getType())) {
                                            if (!correlationKeys.contains(expr
                                                    .getName())) {
                                                return ValidationStatus
                                                        .warning(Messages
                                                                .bind(Messages.messageContentIdExistenceWarning,
                                                                        expr.getName(),
                                                                        "correlation"));
                                            }
                                        } else {
                                            if (expr != null
                                                    && expr.getName() != null
                                                    && !expr.getName()
                                                    .isEmpty()) {
                                                return (ValidationStatus
                                                        .warning(Messages.throwMessageExpressionTypeWarning));
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            return ValidationStatus
                                    .warning(Messages.NoIncomingMessageWarning);
                        }
                    }
                }
                return ValidationStatus.OK_STATUS;
            }

        };
        //	IStatus status = validator.validate(getCatchMessageEvent());
    }

    @Override
    public String getSectionDescription() {
        return Messages.catchMessageEventCorrelationSectionDescription;
    }

}
