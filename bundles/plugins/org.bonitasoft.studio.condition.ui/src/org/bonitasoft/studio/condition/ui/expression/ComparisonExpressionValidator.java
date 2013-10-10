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
package org.bonitasoft.studio.condition.ui.expression;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.expression.editor.ExpressionEditorPlugin;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Injector;

/**
 * @author Romain Bioteau
 *
 */
public class ComparisonExpressionValidator implements IExpressionValidator {

	private Expression inputExpression;
	private EditingDomain domain;
	private EObject context;

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.validation.IValidator#validate(java.lang.Object)
	 */
	@Override
	public IStatus validate(Object value) {

		if(value == null || value.toString().isEmpty() || !ExpressionConstants.CONDITION_TYPE.equals(inputExpression.getType())){
			return ValidationStatus.ok();
		}
		final Injector injector = ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
		final IResourceValidator xtextResourceChecker =	injector.getInstance(IResourceValidator.class);
		final XtextResourceSetProvider xtextResourceSetProvider = injector.getInstance(XtextResourceSetProvider.class);
		final ResourceSet resourceSet = xtextResourceSetProvider.get(RepositoryManager.getInstance().getCurrentRepository().getProject());
		final XtextResource resource = (XtextResource) resourceSet.createResource(URI.createURI("somefile.cmodel"));
		try {
			resource.load(new StringInputStream(value.toString(), "UTF-8"), Collections.emptyMap());
		} catch (UnsupportedEncodingException e1) {
			BonitaStudioLog.error(e1, ExpressionEditorPlugin.PLUGIN_ID);
		} catch (IOException e1) {
			BonitaStudioLog.error(e1, ExpressionEditorPlugin.PLUGIN_ID);
		}
		final ConditionModelGlobalScopeProvider globalScopeProvider = injector.getInstance(ConditionModelGlobalScopeProvider.class);
		final List<String> accessibleObjects = new ArrayList<String>();
		for(Data d : ModelHelper.getAccessibleData(context)){
			accessibleObjects.add(ModelHelper.getEObjectID(d));
		}

		AbstractProcess process =  ModelHelper.getParentProcess(context);
		if(process != null){
			for(Parameter p : process.getParameters()){
				accessibleObjects.add(ModelHelper.getEObjectID(p));
			}
		}
		globalScopeProvider.setAccessibleEObjects(accessibleObjects);


		final MultiStatus status = new MultiStatus(ExpressionEditorPlugin.PLUGIN_ID, 0, "", null);
		final List<Issue> issues = xtextResourceChecker.validate(resource, CheckMode.FAST_ONLY, null);

		if(issues.isEmpty()){
			updateDependencies(resource);
		}

		for(Issue issue : issues){
			int severity = IStatus.ERROR;
			Severity issueSeverity = issue.getSeverity();
			if(issueSeverity == Severity.WARNING){
				severity = IStatus.WARNING;
			}
			status.add(new Status(severity, ExpressionEditorPlugin.PLUGIN_ID, issue.getMessage()));
		}

		return status;
	}

	private void updateDependencies(final XtextResource resource) {
		if(domain != null && inputExpression != null){
			domain.getCommandStack().execute(new RemoveCommand(domain, inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, inputExpression.getReferencedElements()));
			Operation_Compare compareOp = (Operation_Compare) resource.getContents().get(0);
			if(compareOp != null){
				List<Expression_ProcessRef> references = ModelHelper.getAllItemsOfType(compareOp, ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF);
				for(Expression_ProcessRef ref : references){
					EObject dep = resolveProxy(ref.getValue());
					List<EObject> orignalDep = ModelHelper.getAllItemsOfType( ModelHelper.getMainProcess(context), dep.eClass());
					for(EObject d : orignalDep){
						if(EcoreUtil.equals(dep, d)){
							dep = d;
							break;
						}
					}
					inputExpression.getReferencedElements().add(EcoreUtil.copy(dep));
					domain.getCommandStack().execute(new AddCommand(domain, inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, EcoreUtil.copy(dep)));
				}
			}
		}else if(inputExpression != null){
			inputExpression.getReferencedElements().clear();
			Operation_Compare compareOp = (Operation_Compare) resource.getContents().get(0);
			if(compareOp != null){
				List<Expression_ProcessRef> references = ModelHelper.getAllItemsOfType(compareOp, ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF);
				for(Expression_ProcessRef ref : references){
					EObject dep = resolveProxy(ref.getValue());
					List<EObject> orignalDep = ModelHelper.getAllItemsOfType( ModelHelper.getMainProcess(context), dep.eClass());
					for(EObject d : orignalDep){
						if(EcoreUtil.equals(dep, d)){
							dep = d;
							break;
						}
					}
					inputExpression.getReferencedElements().add(EcoreUtil.copy(dep));
				}
			}
		}
	}

	private EObject resolveProxy(EObject ref) {
		ResourceSet rSet = null;
		if(ref.eIsProxy()){
			rSet =context.eResource().getResourceSet();
		}
		EObject dep = EcoreUtil2.resolve(ref, rSet);
		if(rSet != null){
			rSet.getResources().remove(ref.eResource());
		}
		return dep;
	}

	public void setInputExpression(Expression inputExpression) {
		this.inputExpression = inputExpression;
	}

	public void setDomain(EditingDomain domain) {
		this.domain = domain;
	}

	@Override
	public void setContext(EObject context) {
		this.context = context;
	}

}
