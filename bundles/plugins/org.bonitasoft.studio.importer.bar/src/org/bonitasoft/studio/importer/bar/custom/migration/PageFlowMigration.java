/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 *
 */
public class PageFlowMigration extends ReportCustomMigration {

	private final Map<String, String> confirmationMessages = new HashMap<String,String>();
	private final Map<String, String> entryRedirectionUrls = new HashMap<String,String>();
	private final Map<String, String> viewRedirectionUrls = new HashMap<String,String>();
	private final Map<String, String> recapRedirectionUrls = new HashMap<String,String>();
	private final Map<String, String> pageFlowTransitionConditions = new HashMap<String,String>();
	private final Map<String, List<Instance>> entryRedirectionActions = new HashMap<String,List<Instance>>();

	@Override
	public void migrateBefore(final Model model, final Metamodel metamodel)
			throws MigrationException {
		for(final Instance pageFlow : model.getAllInstances("process.PageFlow")){
			final String confirmationScript = pageFlow.get("confirmationMessage");
			final String redirectionScript = pageFlow.get("entryRedirectionURL");
			pageFlow.set("confirmationMessage", null);
			pageFlow.set("entryRedirectionURL", null);
			if(confirmationScript != null && !confirmationScript.trim().isEmpty()){
				confirmationMessages.put(pageFlow.getUuid(), confirmationScript);
			}
			if(redirectionScript != null && !redirectionScript.trim().isEmpty()){
				entryRedirectionUrls.put(pageFlow.getUuid(), redirectionScript);
			}
			final List<Instance> actions = pageFlow.get("entryRedirectionActions");
			final List<Instance> operations = new ArrayList<Instance>();
			for(final Instance groovyScript : actions){
				final StringToExpressionConverter converter = getConverter(model,getScope(pageFlow));
				final Instance operation = converter.parseOperation(groovyScript, String.class.getName(), true);
				operations.add(operation);
				model.delete(groovyScript);
			}
			if(!operations.isEmpty()){
				entryRedirectionActions.put(pageFlow.getUuid(), operations);
			}
		}

		for(final Instance pageFlow : model.getAllInstances("process.ViewPageFlow")){
			final String redirectionScript = pageFlow.get("viewPageFlowRedirectionURL");
			pageFlow.set("viewPageFlowRedirectionURL", null);
			if(redirectionScript != null && !redirectionScript.trim().isEmpty()){
				viewRedirectionUrls.put(pageFlow.getUuid(), redirectionScript);
			}
		}

		for(final Instance pageFlow : model.getAllInstances("process.RecapFlow")){
			final String redirectionScript = pageFlow.get("recapPageFlowRedirectionURL");
			pageFlow.set("recapPageFlowRedirectionURL", null);
			if(redirectionScript != null && !redirectionScript.trim().isEmpty()){
				recapRedirectionUrls.put(pageFlow.getUuid(), redirectionScript);
			}
		}

		for(final Instance pageFlowTransition : model.getAllInstances("process.PageFlowTransition")){
			final String condition = pageFlowTransition.get("condition");
			pageFlowTransition.set("condition", null);
			if(condition != null && !condition.trim().isEmpty()){
				pageFlowTransitionConditions.put(pageFlowTransition.getUuid(), condition);
			}
		}
	}

	@Override
	public void migrateAfter(final Model model, final Metamodel metamodel)
			throws MigrationException {
		for(final Instance pageFlow : model.getAllInstances("process.PageFlow")){
			setConfirmationMessage(pageFlow, model);
			setEntryRedirectionURL(pageFlow,model);
			setRedirectionActions(pageFlow,model);
		}
		for(final Instance pageFlow : model.getAllInstances("process.ViewPageFlow")){
			setViewRedirectionURL(pageFlow,model);
            final List<Instance> transientDataInstances = pageFlow.get("viewTransientData");
            for (final Instance viewTransientData : transientDataInstances) {
                model.delete(viewTransientData);
            }

            final List<Instance> connectorDataInstances = pageFlow.get("viewPageFlowConnectors");
            for (final Instance viewConnectorData : connectorDataInstances) {
                model.delete(viewConnectorData);
            }

            final List<Instance> transitionsInstances = pageFlow.get("viewPageFlowTransitions");
            for (final Instance transition : transitionsInstances) {
                model.delete(transition);
            }
		}
		for(final Instance pageFlow : model.getAllInstances("process.RecapFlow")){
			setRecapRedirectionURL(pageFlow,model);
		}
		for(final Instance pageFlowTransition : model.getAllInstances("process.PageFlowTransition")){
			setPageFlowTransitionCondition(pageFlowTransition,model);
		}
        for (final Instance viewForm : model.getAllInstances("form.ViewForm")) {
            final EReference ref = viewForm.getContainerReference();
            if (ref != null
                    && ref.getName().equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM.getName())
                    && ref.getEContainingClass().getName().equals(ProcessPackage.Literals.VIEW_PAGE_FLOW.getName())) {
                model.delete(viewForm);
            }
        }
        for (final Instance viewForm : model.getAllInstances("form.ViewForm")) {
            final EReference ref = viewForm.getContainerReference();
            if (ref != null
                    && ref.getName().equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM.getName())
                    && ref.getEContainingClass().getName().equals(ProcessPackage.Literals.VIEW_PAGE_FLOW.getName())) {
                model.delete(viewForm);
            }
        }
	}

	private void setPageFlowTransitionCondition(final Instance pageFlowTransition,final Model model) {
		Instance expression = null;
		if(pageFlowTransitionConditions.containsKey(pageFlowTransition.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(pageFlowTransition));
			final String url = pageFlowTransitionConditions.get(pageFlowTransition.getUuid());
			expression = converter.parse(url, Boolean.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "transitionScript");
			}
			final String containerClassName = pageFlowTransition.getContainerReference().getEContainingClass().getName();
			String propertyName = "";
			if(containerClassName.equals(ProcessPackage.Literals.PAGE_FLOW.getName())){
				propertyName = Messages.entryPageflowProperty;
			}else if(containerClassName.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW.getName())){
				propertyName =  Messages.viewPageflowProperty;
			}else if(containerClassName.equals(ProcessPackage.Literals.RECAP_FLOW.getName())){
				propertyName =  Messages.recapPageflowProperty;
			}
			addReportChange((String) pageFlowTransition.getContainer().get("name"),pageFlowTransition.getType().getEClass().getName(), pageFlowTransition.getUuid(),Messages.pageFlowTransitionMigrationDescription, propertyName, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Boolean.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		pageFlowTransition.set("condition", expression);

	}

	private void setRecapRedirectionURL(final Instance pageFlow, final Model model) {
		Instance expression = null;
		if(recapRedirectionUrls.containsKey(pageFlow.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(pageFlow));
			final String url = recapRedirectionUrls.get(pageFlow.getUuid());
			expression = converter.parse(url, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "redirectionUrlScript");
			}
			addReportChange((String) pageFlow.get("name"),pageFlow.getType().getEClass().getName(), pageFlow.getUuid(),Messages.redirectionUrlMigrationDescription, Messages.recapPageflowProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		pageFlow.set("recapPageFlowRedirectionURL", expression);
	}

	private void setViewRedirectionURL(final Instance pageFlow, final Model model) {
		Instance expression = null;
		if(viewRedirectionUrls.containsKey(pageFlow.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(pageFlow));
			final String url = viewRedirectionUrls.get(pageFlow.getUuid());
			expression = converter.parse(url, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "redirectionUrlScript");
			}
			addReportChange((String) pageFlow.get("name"),pageFlow.getType().getEClass().getName(), pageFlow.getUuid(),Messages.redirectionUrlMigrationDescription, Messages.viewPageflowProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		pageFlow.set("viewPageFlowRedirectionURL", expression);
	}

	private void setRedirectionActions(final Instance pageFlow, final Model model) {
		if(entryRedirectionActions.containsKey(pageFlow.getUuid())){
			pageFlow.get("entryRedirectionActions");
			for(final Instance operation : entryRedirectionActions.get(pageFlow.getUuid())){
				pageFlow.add("entryRedirectionActions", operation);
				addReportChange((String) pageFlow.get("name"),pageFlow.getType().getEClass().getName(), pageFlow.getUuid(),Messages.redirectionActionMigrationDescription, Messages.entryPageflowProperty, ExpressionConstants.SCRIPT_TYPE.equals(((Instance)operation.get("rightOperand")).get("type")) ? IStatus.WARNING : IStatus.OK);
			}
		}
	}

	private void setEntryRedirectionURL(final Instance pageFlow, final Model model) {
		Instance expression = null;
		if(entryRedirectionUrls.containsKey(pageFlow.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(pageFlow));
			final String url = entryRedirectionUrls.get(pageFlow.getUuid());
			expression = converter.parse(url, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "redirectionUrlScript");
			}
			addReportChange((String) pageFlow.get("name"),pageFlow.getType().getEClass().getName(), pageFlow.getUuid(),Messages.redirectionUrlMigrationDescription, Messages.entryPageflowProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		pageFlow.set("entryRedirectionURL", expression);
	}

	private void setConfirmationMessage(final Instance pageFlow, final Model model) {
		Instance expression = null;
		if(confirmationMessages.containsKey(pageFlow.getUuid())){
			final StringToExpressionConverter converter = getConverter(model,getScope(pageFlow));
			final String confirmationMessage = confirmationMessages.get(pageFlow.getUuid());
			expression = converter.parse(confirmationMessage, String.class.getName(), true);
			if(ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))){
				expression.set("name", "confirmationMessageScript");
			}
			addReportChange((String) pageFlow.get("name"),pageFlow.getType().getEClass().getName(), pageFlow.getUuid(),Messages.confirmationMessageMigrationDescription, Messages.confirmationProperty, ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK);
		}else{
			expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
		}
		pageFlow.set("confirmationMessage", expression);

	}
}
