/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.actors.action;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.SaveConfigurationEMFCommand;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */
public class RefactorActorOperation extends AbstractRefactorOperation {

    private AbstractProcess process;

    private Actor actor;

    private String newValue;

    public RefactorActorOperation(AbstractProcess process, Actor actor, String newValue) {
        super(RefactoringOperationType.UPDATE);
        this.process = process;
        this.actor = actor;
        this.newValue = newValue;
    }

    @Override
    protected void doExecute(IProgressMonitor monitor) {
        monitor.beginTask(Messages.updateActorReferences, IProgressMonitor.UNKNOWN);
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

        CompoundCommand cc = new CompoundCommand();
        for (Configuration configuration : configurations) {
            if (configuration.getActorMappings() != null) {
                List<ActorMapping> actorMappings = configuration.getActorMappings().getActorMapping();
                for (ActorMapping actorMapping : actorMappings) {
                    if (actorMapping.getName().equals(actor.getName())) {
                        cc.append(SetCommand.create(domain, actorMapping, ActorMappingPackage.Literals.ACTOR_MAPPING__NAME, getNewValueName()));
                    }
                }
                if (localeConfiguration != null) {
                    cc.append(new SaveConfigurationEMFCommand(file, localConfigurationCopy, localeConfiguration));
                }
            }
        }
        cc.append(SetCommand.create(domain, actor, ProcessPackage.Literals.ELEMENT__NAME, getNewValueName()));
    }

    @Override
    protected AbstractScriptExpressionRefactoringAction getScriptExpressionRefactoringAction(EObject newValue, String oldName, String newName,
            List<Expression> scriptExpressions, List<Expression> refactoredScriptExpression, CompoundCommand compoundCommand, EditingDomain domain,
            RefactoringOperationType operationType) {
        return null;
    }

    @Override
    protected EObject getContainer() {
        return null;
    }

    @Override
    protected EObject getOldValue() {
        return actor;
    }

    @Override
    protected String getOldValueName() {
        return actor.getName();
    }

    @Override
    protected EObject getNewValue() {
        return null;
    }

    @Override
    protected String getNewValueName() {
        return newValue;
    }

}
