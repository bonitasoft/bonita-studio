/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
 * Bonitasoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.actors.action;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.SaveConfigurationEMFCommand;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingPackage;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.AbstractRefactorOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */
public class RefactorActorOperation extends AbstractRefactorOperation<Actor, Actor, ActorRefactorPair> {

    private final AbstractProcess process;

    public RefactorActorOperation(final AbstractProcess process, final Actor actor, final String newValue) {
        super(RefactoringOperationType.UPDATE);
        this.process = process;
        final Actor copy = EcoreUtil.copy(actor);
        copy.setName(newValue);
        addItemToRefactor(copy, actor);
    }

    @Override
    protected CompoundCommand doBuildCompoundCommand(final CompoundCommand compoundCommand, final IProgressMonitor monitor) {
        monitor.beginTask(Messages.updateActorReferences, IProgressMonitor.UNKNOWN);
        final String id = ModelHelper.getEObjectID(process);
        final String fileName = id + ".conf";
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
        final ProcessConfigurationFileStore file = processConfStore.getChild(fileName, true);
        Configuration localeConfiguration = null;
        Configuration localConfigurationCopy = null;
        if (file != null) {
            try {
                localeConfiguration = file.getContent();
            } catch (ReadFileStoreException e) {
                BonitaStudioLog.warning(e.getMessage(), IdentityPlugin.PLUGIN_ID);
            }
            localConfigurationCopy = EcoreUtil.copy(localeConfiguration);
        }
        final List<Configuration> configurations = new ArrayList<Configuration>();
        if (localeConfiguration != null) {
            configurations.add(localeConfiguration);
        }
        configurations.addAll(process.getConfigurations());
        for (final ActorRefactorPair pairToRefactor : pairsToRefactor) {
            final Actor actor = pairToRefactor.getOldValue();
            for (final Configuration configuration : configurations) {
                if (configuration.getActorMappings() != null) {
                    final List<ActorMapping> actorMappings = configuration.getActorMappings().getActorMapping();
                    for (final ActorMapping actorMapping : actorMappings) {
                        if (actorMapping.getName().equals(actor.getName())) {
                            compoundCommand.append(SetCommand.create(getEditingDomain(), actorMapping, ActorMappingPackage.Literals.ACTOR_MAPPING__NAME,
                                    pairToRefactor.getNewValueName()));
                        }
                    }
                    if (localeConfiguration != null) {
                        compoundCommand.append(new SaveConfigurationEMFCommand(file, localConfigurationCopy, localeConfiguration));
                    }
                }
            }
            compoundCommand.append(SetCommand.create(getEditingDomain(), actor, ProcessPackage.Literals.ELEMENT__NAME, pairToRefactor.getNewValueName()));
        }
        return compoundCommand;
    }

    @Override
    protected EObject getContainer(final Actor oldValue) {
        return null;
    }

    @Override
    protected ActorRefactorPair createRefactorPair(final Actor newItem, final Actor oldItem) {
        return new ActorRefactorPair(newItem, oldItem);
    }

}
