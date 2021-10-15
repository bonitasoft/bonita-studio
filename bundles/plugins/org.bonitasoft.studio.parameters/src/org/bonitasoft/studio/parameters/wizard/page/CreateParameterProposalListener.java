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
package org.bonitasoft.studio.parameters.wizard.page;

import java.util.Objects;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.EnvironmentProviderFactory;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

public class CreateParameterProposalListener implements IProposalListener {

    private EnvironmentProviderFactory environmentProviderFactory = new EnvironmentProviderFactory();

    @Override
    public String handleEvent(final EObject context, final String fixedReturnType, String defaultValue) {
        Assert.isNotNull(context);
        final AbstractProcess parentProcess = ModelHelper.getParentProcess(context);
        Parameter parameter = ParameterFactory.eINSTANCE.createParameter();
        parameter.setTypeClassname(getParameterType(fixedReturnType));
        final AddParameterWizard parameterWizard = new AddParameterWizard(parentProcess,
                parameter,
                TransactionUtil.getEditingDomain(context));
        final ParameterWizardDialog parameterDialog = new ParameterWizardDialog(
                Display.getDefault().getActiveShell(), parameterWizard);
        if (parameterDialog.open() == Window.OK) {
            final Parameter param = EcoreUtil.copy(parameterWizard.getNewParameter());
            if (defaultValue != null) {
                param.setValue(defaultValue);
                ProcessConfigurationRepositoryStore store = RepositoryManager.getInstance()
                        .getRepositoryStore(ProcessConfigurationRepositoryStore.class);
                ProcessConfigurationFileStore configurationFileStore = store.getChild(
                        ModelHelper.getEObjectID(parentProcess) + "." + ProcessConfigurationRepositoryStore.CONF_EXT,
                        false);
                if (configurationFileStore != null) {
                    try {
                        Configuration localConfiguration = configurationFileStore.getContent();
                        localConfiguration.getParameters().add(EcoreUtil.copy(param));
                        configurationFileStore.save(localConfiguration);
                    } catch (ReadFileStoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }
                environmentProviderFactory
                        .getEnvironmentProvider()
                        .getEnvironment()
                        .stream()
                        .forEach(env -> addParameterValue(parentProcess, env, param));
            }
            if (param != null) {
                return param.getName();
            }
        }
        return null;
    }

    private String getParameterType(String returnType) {
        if (Double.class.getName().equals(returnType)) {
            return Double.class.getName();
        }
        if (Boolean.class.getName().equals(returnType)) {
            return Boolean.class.getName();
        }
        if (Integer.class.getName().equals(returnType)) {
            return Integer.class.getName();
        }
        return String.class.getName();
    }

    private void addParameterValue(AbstractProcess parentProcess, String envName, Parameter paramater) {
        parentProcess.getConfigurations().stream()
                .filter(conf -> Objects.equals(envName, conf.getName()))
                .findFirst()
                .ifPresent(conf -> {
                    TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(parentProcess);
                    editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, conf,
                            ConfigurationPackage.Literals.CONFIGURATION__PARAMETERS, EcoreUtil.copy(paramater)));
                });
    }

    @Override
    public void setEStructuralFeature(final EStructuralFeature feature) {

    }

    @Override
    public boolean isRelevant(final EObject context, final ISelection selection) {
        return true;
    }

}
