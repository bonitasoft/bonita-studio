/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.configuration;

import java.util.Objects;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Resource;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

public class AdditionalResourcesConfigurationSyncronizer implements IConfigurationSynchronizer {

    @Override
    public void synchronize(Configuration configuration, AbstractProcess process, CompoundCommand cc,
            EditingDomain editingDomain) {
        Pool pool = ModelHelper.getParentPool(process);
        if (pool != null) {
            addNewAdditionalResources(configuration, pool, editingDomain, cc);
            removeDeletedAdditionalResources(configuration, pool, editingDomain, cc);
        }
    }

    private void removeDeletedAdditionalResources(Configuration configuration, Pool pool,
            EditingDomain editingDomain, CompoundCommand cc) {
        configuration.getAdditionalResources().stream()
                .filter(additionalResource -> pool.getAdditionalResources().stream()
                        .noneMatch(anAdditionalResource -> Objects.equals(additionalResource.getBarPath(),
                                anAdditionalResource.getName())))
                .forEach(additionalResource -> {
                    Command command = createRemoveCommand(editingDomain, configuration, additionalResource);
                    cc.append(command);
                });
    }

    private void addNewAdditionalResources(Configuration configuration, Pool pool, EditingDomain editingDomain,
            CompoundCommand cc) {
        pool.getAdditionalResources().stream()
                .filter(additionalResource -> configuration.getAdditionalResources().stream()
                        .noneMatch(anAdditionalResource -> Objects.equals(additionalResource.getName(),
                                anAdditionalResource.getBarPath())))
                .forEach(additionalResource -> {
                    Resource newAdditionalResource = ConfigurationFactory.eINSTANCE.createResource();
                    newAdditionalResource.setBarPath(additionalResource.getName());
                    Command command = createAddCommand(editingDomain, configuration, newAdditionalResource);
                    cc.append(command);
                });
    }

    protected Command createAddCommand(EditingDomain editingDomain, Configuration configuration, Resource newResource) {
        return AddCommand.create(editingDomain, configuration,
                ConfigurationPackage.Literals.CONFIGURATION__ADDITIONAL_RESOURCES, newResource);
    }

    protected Command createRemoveCommand(EditingDomain editingDomain, Configuration configuration,
            Resource resourceToRemove) {
        return RemoveCommand.create(editingDomain, configuration,
                ConfigurationPackage.Literals.CONFIGURATION__ADDITIONAL_RESOURCES, resourceToRemove);
    }

    @Override
    public String getFragmentContainerId() {
        return null;
    }

    @Override
    public EStructuralFeature getDependencyKind() {
        return null;
    }

}
