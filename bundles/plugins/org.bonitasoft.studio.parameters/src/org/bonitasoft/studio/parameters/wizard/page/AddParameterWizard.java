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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.parameters.ParameterPlugin;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.ui.XtextProjectHelper;

public class AddParameterWizard extends Wizard {

    private final AbstractProcess container;

    private final Parameter parameterWorkingCopy;

    private final EditingDomain editingDomain;
    
    public AddParameterWizard(final AbstractProcess container, final EditingDomain editingDomain) {
        this(container, defaultParameter(),editingDomain);
    }
    
    private static Parameter defaultParameter() {
        Parameter parameter = ParameterFactory.eINSTANCE.createParameter();
        parameter.setTypeClassname(String.class.getName());
        return parameter;
    }

    public AddParameterWizard(final AbstractProcess container, Parameter parameterWorkingCopy, final EditingDomain editingDomain) {
        this.container = container;
        setWindowTitle(Messages.newParameter);
        setDefaultPageImageDescriptor(Pics.getWizban());
        this.parameterWorkingCopy = parameterWorkingCopy;
        this.editingDomain = editingDomain;
    }

    @Override
    public boolean performFinish() {
        editingDomain.getCommandStack().execute(
                AddCommand.create(editingDomain, container,
                        ProcessPackage.Literals.ABSTRACT_PROCESS__PARAMETERS,
                        parameterWorkingCopy));

        try {
            RepositoryManager.getInstance().getCurrentRepository().getProject()
                    .build(IncrementalProjectBuilder.FULL_BUILD, XtextProjectHelper.BUILDER_ID, Collections.<String, String> emptyMap(), null);
        } catch (final CoreException e1) {
            BonitaStudioLog.error(e1, ParameterPlugin.PLUGIN_ID);
            return false;
        }

        return true;
    }

    @Override
    public void addPages() {
        ParametersWizardPage addParameterPage = new ParametersWizardPage(parameterWorkingCopy, getExistingParametersName());
        addParameterPage.setTitle(NLS.bind(Messages.newParameterWizardTitle, container.getName()));
        addParameterPage.setDescription(Messages.newParameterWizardDescription);
        addPage(addParameterPage);
    }

    protected Set<String> getExistingParametersName() {
        final Set<String> params = new HashSet<>();
        for (final Parameter p : container.getParameters()) {
            params.add(p.getName());
        }
        return params;
    }

    public Parameter getNewParameter() {
        return parameterWorkingCopy;
    }

}
