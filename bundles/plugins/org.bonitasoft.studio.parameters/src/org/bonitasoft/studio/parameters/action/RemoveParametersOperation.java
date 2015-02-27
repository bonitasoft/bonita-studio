/*******************************************************************************
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.action;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.SaveConfigurationEMFCommand;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author aurelie
 *
 */
public class RemoveParametersOperation extends AbstractRefactorOperation<Parameter, Parameter, ParameterRefactorPair> {

    private final AbstractProcess process;

    public RemoveParametersOperation(final Parameter parameterToRemove, final AbstractProcess process) {
        super(RefactoringOperationType.REMOVE);
        addItemToRefactor(null, parameterToRemove);
        this.process = process;
    }

    private void deleteAllReferencesToParameter(final EditingDomain editingDomain, final Parameter parameter, final CompoundCommand cc) {
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(process, ExpressionPackage.Literals.EXPRESSION);
        for (final Expression exp : expressions) {
            if (ExpressionConstants.PARAMETER_TYPE.equals(exp.getType()) && exp.getName().equals(parameter.getName())) {
                // update name and content
                cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__NAME, ""));
                cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
                // update return type
                cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
                cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE));
                // update referenced parameter
                cc.append(RemoveCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                        exp.getReferencedElements()));
            }
        }
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand compoundCommand, final IProgressMonitor monitor) {
        monitor.beginTask(Messages.removeParameters, IProgressMonitor.UNKNOWN);
        for(final ParameterRefactorPair pairToRefactor : pairsToRefactor){
            deleteAllReferencesToParameter(getEditingDomain(), pairToRefactor.getOldValue(), compoundCommand);
            final String id = ModelHelper.getEObjectID(process);
            final String fileName = id + ".conf";
            final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getCurrentRepository()
                    .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
            final ProcessConfigurationFileStore file = processConfStore.getChild(fileName);
            Configuration localeConfiguration = null;
            Configuration localeConfigurationWorkingCopy = null;
            if (file != null) {
                localeConfiguration = file.getContent();
                localeConfigurationWorkingCopy = EcoreUtil.copy(localeConfiguration);
            }
            if (localeConfiguration != null) {

                for (final Parameter configParameter : localeConfiguration.getParameters()) {
                    if (configParameter.getName().equals(pairToRefactor.getOldValueName())) {
                        compoundCommand.append(new RemoveCommand(getEditingDomain(), localeConfiguration,
                                ConfigurationPackage.Literals.CONFIGURATION__PARAMETERS,
                                configParameter));
                    }
                }
                compoundCommand.append(new SaveConfigurationEMFCommand(file, localeConfigurationWorkingCopy, localeConfiguration));

            }
            compoundCommand.append(DeleteCommand.create(getEditingDomain(), pairToRefactor.getOldValue()));
        }
        return compoundCommand;
    }

    @Override
    protected AbstractScriptExpressionRefactoringAction<ParameterRefactorPair> getScriptExpressionRefactoringAction(final List<ParameterRefactorPair> pairsToRefactor,
            final List<Expression> scriptExpressions, final List<Expression> refactoredScriptExpression, final CompoundCommand compoundCommand, final EditingDomain domain,
            final RefactoringOperationType operationType) {
        return new ParameterScriptExpressionRefactoringAction(pairsToRefactor, scriptExpressions, refactoredScriptExpression, compoundCommand,
                domain, operationType);
    }

    @Override
    protected EObject getContainer(final Parameter oldValue) {
        return process;
    }

    @Override
    protected ParameterRefactorPair createRefactorPair(final Parameter newItem,	final Parameter oldItem) {
        return new ParameterRefactorPair(newItem, oldItem);
    }

}