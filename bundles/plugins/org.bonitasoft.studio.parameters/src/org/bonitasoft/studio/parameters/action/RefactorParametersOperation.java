/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.parameters.action;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
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
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author aurelie Zara
 */
public class RefactorParametersOperation extends AbstractRefactorOperation<Parameter, Parameter, ParameterRefactorPair> {

    private final AbstractProcess process;

    public RefactorParametersOperation(final AbstractProcess process) {
        super(RefactoringOperationType.UPDATE);
        this.process = process;
    }

    private void refactorConfParameter(final EditingDomain editingDomain, final CompoundCommand cc) {
        final String id = ModelHelper.getEObjectID(process);
        final String fileName = id + ".conf";
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        final ProcessConfigurationFileStore file = processConfStore.getChild(fileName);
        Configuration localeConfiguration = null;
        Configuration localConfigurationCopy = null;
        if (file != null) {
            localeConfiguration = file.getContent();
            localConfigurationCopy = EcoreUtil.copy(localeConfiguration);
        }
        final List<Configuration> configurations = new ArrayList<>();
        if (localeConfiguration != null) {
            configurations.add(localeConfiguration);
        }
        configurations.addAll(process.getConfigurations());
        for (final Configuration configuration : configurations) {
            final List<Parameter> parameters = configuration.getParameters();
            for (final Parameter confParameter : parameters) {
                for (final ParameterRefactorPair pairToRefactor : pairsToRefactor) {
                    if (pairToRefactor.getOldValueName().equals(confParameter.getName())) {
                        cc.append(SetCommand.create(editingDomain, confParameter, ParameterPackage.Literals.PARAMETER__NAME,
                                pairToRefactor.getNewValueName()));
                        cc.append(SetCommand.create(editingDomain, confParameter,
                                ParameterPackage.Literals.PARAMETER__DESCRIPTION, pairToRefactor
                                        .getNewValue().getDescription()));
                        cc.append(SetCommand.create(editingDomain, confParameter,
                                ParameterPackage.Literals.PARAMETER__TYPE_CLASSNAME,
                                pairToRefactor.getNewValue().getTypeClassname()));
                    }
                }
            }
            if (configuration.equals(localeConfiguration)) {
                cc.append(new SaveConfigurationEMFCommand(file, localConfigurationCopy, localeConfiguration));
            }
        }
    }

    private void refactorReferencedExpressions(final EditingDomain editingDomain, final CompoundCommand cc) {
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(process, ExpressionPackage.Literals.EXPRESSION);
        for (final Expression exp : expressions) {
            for (final ParameterRefactorPair pairToRefactor : pairsToRefactor) {
                if (ExpressionConstants.PARAMETER_TYPE.equals(exp.getType())
                        && pairToRefactor.getOldValueName().equals(exp.getName())) {
                    cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__NAME,
                            pairToRefactor.getNewValueName()));
                    cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT,
                            pairToRefactor.getNewValueName()));
                    cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE,
                            pairToRefactor.getNewValue()
                                    .getTypeClassname()));

                    updateDependencies(cc, pairToRefactor, exp);
                }
            }
        }

    }

    private void updateDependencies(final CompoundCommand cc, final ParameterRefactorPair pairToRefactor,
            final Expression exp) {
        for (final EObject dependency : exp.getReferencedElements()) {
            if (dependency instanceof Parameter
                    && ((Parameter) dependency).getName().equals(pairToRefactor.getOldValueName())) {
                EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(dependency);
                updater.editWorkingCopy(ExpressionHelper.createDependencyFromEObject(pairToRefactor.getNewValue()));
                cc.append(updater.createUpdateCommand(getEditingDomain()));
            }
        }
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand compoundCommand, final IProgressMonitor monitor) {
        monitor.beginTask(Messages.updatingParameterReferences, IProgressMonitor.UNKNOWN);
        refactorConfParameter(getEditingDomain(), compoundCommand);
        refactorReferencedExpressions(getEditingDomain(), compoundCommand);
        return compoundCommand;
    }

    @Override
    protected EObject getContainer(final Parameter olValue) {
        return process;
    }

    @Override
    protected ParameterRefactorPair createRefactorPair(final Parameter newItem, final Parameter oldItem) {
        return new ParameterRefactorPair(newItem, oldItem);
    }

}
