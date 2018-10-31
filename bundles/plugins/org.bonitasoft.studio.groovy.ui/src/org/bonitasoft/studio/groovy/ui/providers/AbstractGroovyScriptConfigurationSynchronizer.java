/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.ui.providers;

import java.io.File;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

public abstract class AbstractGroovyScriptConfigurationSynchronizer implements IConfigurationSynchronizer {

    @Override
    public void synchronize(Configuration configuration, AbstractProcess process, CompoundCommand cc,
            EditingDomain editingDomain) {
        GroovyRepositoryStore store = (GroovyRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(GroovyRepositoryStore.class);
        addNewPackage(configuration, process, store, cc, editingDomain);
        removeDeletedPackage(configuration, store, cc, editingDomain);
    }

    private void addNewPackage(Configuration configuration, AbstractProcess process, GroovyRepositoryStore store,
            CompoundCommand cc, EditingDomain editingDomain) {
        IFolder srcFolder = store.getResource();
        FragmentContainer groovyContainer = getContainer(configuration);
        Assert.isNotNull(groovyContainer);

        try {
            srcFolder.accept(new IResourceVisitor() {

                @Override
                public boolean visit(IResource resource) throws CoreException {
                    if (Objects.equals("groovy", resource.getFileExtension())) {
                        boolean exists = false;
                        for (Fragment f : groovyContainer.getFragments()) {
                            if (f.getValue().equals(toPath(srcFolder, resource))) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            Fragment newFragment = ConfigurationFactory.eINSTANCE.createFragment();
                            newFragment.setType(FragmentTypes.GROOVY_SCRIPT);
                            String path = toPath(srcFolder, resource);
                            newFragment.setKey(path);
                            newFragment.setValue(path);
                            List<Expression> expressions = ModelHelper.getAllItemsOfType(process,
                                    ExpressionPackage.Literals.EXPRESSION);
                            newFragment.setExported(false);
                            String qualifiedName = path.replaceAll(File.separator, ".").substring(0,
                                    path.lastIndexOf(".groovy"));
                            for (Expression exp : expressions) {
                                if (exp.getType() != null && exp.getType().equals(ExpressionConstants.SCRIPT_TYPE)) {
                                    if (exp.getContent() != null && exp.getContent().contains(qualifiedName)) {
                                        newFragment.setExported(true);
                                        break;
                                    }
                                }
                            }
                            cc.append(AddCommand.create(editingDomain, groovyContainer,
                                    ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, newFragment));
                        }
                        return false;
                    }
                    return true;
                }
            });
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }

    }

    protected abstract FragmentContainer getContainer(Configuration configuration);

    protected String toPath(IFolder srcFolder, IResource resource) {
        return resource.getLocation().makeRelativeTo(srcFolder.getLocation()).toString();
    }

    private void removeDeletedPackage(Configuration configuration, GroovyRepositoryStore store, CompoundCommand cc,
            EditingDomain editingDomain) {
        IFolder srcFolder = store.getResource();
        FragmentContainer container = getContainer(configuration);
        for (Fragment f : container.getFragments()) {
            IResource member = srcFolder.findMember(Path.fromOSString(f.getValue()));
            if (member == null || !member.exists()) {
                cc.append(RemoveCommand.create(editingDomain, container,
                        ConfigurationPackage.Literals.FRAGMENT_CONTAINER__FRAGMENTS, f));
            }
        }
    }

    @Override
    public String getFragmentContainerId() {
        return FragmentTypes.GROOVY_SCRIPT;
    }

}
