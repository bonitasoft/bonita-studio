/*******************************************************************************
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.action;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.SaveConfigurationEMFCommand;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author aurelie Zara
 * 
 */
public class RefactorParametersOperation extends AbstractRefactorOperation<Parameter, Parameter, ParameterRefactorPair> {

    private AbstractProcess process;

    public RefactorParametersOperation(AbstractProcess process) {
        super(RefactoringOperationType.UPDATE);
        this.process = process;
    }

    private void refactorConfParameter(EditingDomain editingDomain, CompoundCommand cc) {
        String id = ModelHelper.getEObjectID(process);
        String fileName = id + ".conf";
        ProcessConfigurationRepositoryStore processConfStore = (ProcessConfigurationRepositoryStore) RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        ProcessConfigurationFileStore file = processConfStore.getChild(fileName);
        Configuration localeConfiguration = null;
        Configuration localConfigurationCopy = null;
        if (file != null) {
            localeConfiguration = file.getContent();
            localConfigurationCopy = EcoreUtil.copy(localeConfiguration);
        }
        List<Configuration> configurations = new ArrayList<Configuration>();
        if (localeConfiguration != null) {
            configurations.add(localeConfiguration);
        }
        configurations.addAll(process.getConfigurations());
        for (Configuration configuration : configurations) {
        	List<Parameter> parameters = configuration.getParameters();
        	for (Parameter confParameter : parameters) {
        		for(ParameterRefactorPair pairToRefactor : pairsToRefactor){
        			if (pairToRefactor.getOldValueName().equals(confParameter.getName())) {
        				cc.append(SetCommand.create(editingDomain, confParameter, ParameterPackage.Literals.PARAMETER__NAME, pairToRefactor.getNewValueName()));
        				cc.append(SetCommand.create(editingDomain, confParameter, ParameterPackage.Literals.PARAMETER__DESCRIPTION, pairToRefactor.getNewValue().getDescription()));
        				cc.append(SetCommand.create(editingDomain, confParameter, ParameterPackage.Literals.PARAMETER__TYPE_CLASSNAME,
        						pairToRefactor.getNewValue().getTypeClassname()));
        			}
        		}
        	}
        	if (configuration.equals(localeConfiguration)) {
                cc.append(new SaveConfigurationEMFCommand(file, localConfigurationCopy, localeConfiguration));
            }
        }
    }

    private void refactorReferencedExpressions(EditingDomain editingDomain, CompoundCommand cc) {
        List<Expression> expressions = ModelHelper.getAllItemsOfType(process, ExpressionPackage.Literals.EXPRESSION);
        for (Expression exp : expressions) {
        	for(ParameterRefactorPair pairToRefactor : pairsToRefactor){
        		if (ExpressionConstants.PARAMETER_TYPE.equals(exp.getType()) && pairToRefactor.getOldValueName().equals(exp.getName())) {
        			cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__NAME, pairToRefactor.getNewValueName()));
        			cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, pairToRefactor.getNewValueName()));
        			cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, pairToRefactor.getNewValue().getTypeClassname()));
        			cc.append(RemoveCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, exp.getReferencedElements()));
        			cc.append(AddCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, EcoreUtil.copy(pairToRefactor.getNewValue())));
        		}
        	}
        }

    }

    @Override
    protected void doExecute(IProgressMonitor monitor) {
        monitor.beginTask(Messages.updatingParameterReferences, IProgressMonitor.UNKNOWN);
        refactorConfParameter(domain, compoundCommand);
        refactorReferencedExpressions(domain, compoundCommand);
    }

    @Override
    protected AbstractScriptExpressionRefactoringAction<ParameterRefactorPair> getScriptExpressionRefactoringAction(List<ParameterRefactorPair> pairsToRefactor,
            List<Expression> scriptExpressions, List<Expression> refactoredScriptExpression, CompoundCommand compoundCommand, EditingDomain domain,
            RefactoringOperationType operationType) {
        return new ParameterScriptExpressionRefactoringAction(pairsToRefactor, scriptExpressions, refactoredScriptExpression, compoundCommand,
                domain, operationType);
    }

    @Override
    protected EObject getContainer(Parameter olValue) {
        return process;
    }
    
	@Override
	protected ParameterRefactorPair createRefactorPair(Parameter newItem, Parameter oldItem) {
		return new ParameterRefactorPair(newItem, oldItem);
	}

}
