/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.condition.validation.ConditionModelJavaValidator;
import org.bonitasoft.studio.expression.editor.ExpressionEditorPlugin;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.xtext.diagnostics.Severity;
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
	public IStatus validate(final Object value) {
		if(value == null || value.toString().isEmpty() || !ExpressionConstants.CONDITION_TYPE.equals(inputExpression.getType())){
			return ValidationStatus.ok();
		}
		final Injector injector = ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
        final XtextComparisonExpressionLoader xtextComparisonExpressionLoader = getXtextExpressionLoader(injector,
                new ModelSearch(Collections::emptyList));
        Resource resource = null;
        try {
            resource = xtextComparisonExpressionLoader.loadResource(value.toString(), context);
        } catch (final ComparisonExpressionLoadException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(e.getMessage());
        }

        final IResourceValidator xtextResourceChecker = injector.getInstance(IResourceValidator.class);
		final MultiStatus status = new MultiStatus(ExpressionEditorPlugin.PLUGIN_ID, 0, "", null);
        final ConditionModelJavaValidator validator = injector.getInstance(ConditionModelJavaValidator.class);
        final ResourceSet resourceSet = getContextResourceSet();
        validator.setCurrentResourceSet(resourceSet);
		final List<Issue> issues = xtextResourceChecker.validate(resource, CheckMode.FAST_ONLY, null);

		if(issues.isEmpty()){
			updateDependencies(resource);
		}

		for(final Issue issue : issues){
			int severity = IStatus.ERROR;
			final Severity issueSeverity = issue.getSeverity();
			if(issueSeverity == Severity.WARNING){
				severity = IStatus.WARNING;
			}
			status.add(new Status(severity, ExpressionEditorPlugin.PLUGIN_ID, issue.getMessage()));
		}

		return status;
	}

    /**
     * Public for test purpose
     * 
     * @param injector
     * @return
     */
    public XtextComparisonExpressionLoader getXtextExpressionLoader(final Injector injector, IModelSearch modelSearch) {
        return new XtextComparisonExpressionLoader(injector.getInstance(ConditionModelGlobalScopeProvider.class),
                modelSearch, new ProjectXtextResourceProvider(injector));
    }

    /**
     * Public for test purpose
     *
     * @return
     */
    public ResourceSet getContextResourceSet() {
        final ResourceSet resourceSet = context.eResource().getResourceSet();
        return resourceSet;
    }

    private void updateDependencies(final Resource resource) {
		if(domain != null && inputExpression != null){
			domain.getCommandStack().execute(new RemoveCommand(domain, inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, inputExpression.getReferencedElements()));
			final Operation_Compare compareOp = (Operation_Compare) resource.getContents().get(0);
			if(compareOp != null){
				final List<Expression_ProcessRef> references = ModelHelper.getAllItemsOfType(compareOp, ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF);
				for(final Expression_ProcessRef ref : references){
                    final EObject dep = ComparisonExpressionUtil.getResolvedDependency(context, ref);
					domain.getCommandStack().execute(new AddCommand(domain, inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, EcoreUtil.copy(dep)));
				}
			}
		}else if(inputExpression != null){
			inputExpression.getReferencedElements().clear();
            inputExpression.getReferencedElements().addAll(ComparisonExpressionUtil.computeReferencedElement(context, resource));
		}
	}

	@Override
    public void setInputExpression(final Expression inputExpression) {
		this.inputExpression = inputExpression;
	}

	@Override
    public void setDomain(final EditingDomain domain) {
		this.domain = domain;
	}

	@Override
	public void setContext(final EObject context) {
		this.context = context;
	}

    @Override
    public boolean isRelevantForExpressionType(final String type) {
        return ExpressionConstants.CONDITION_TYPE.equals(type);
    }

}
