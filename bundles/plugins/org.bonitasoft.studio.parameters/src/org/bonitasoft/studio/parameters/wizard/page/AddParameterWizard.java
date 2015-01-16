/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
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
import org.eclipse.xtext.ui.XtextProjectHelper;

/**
 * @author Maxence Raoux
 * 
 */
public class AddParameterWizard extends Wizard {

    private ParametersWizardPage addParameterPage;

    private AbstractProcess container;

    private Parameter parameterWorkingCopy;

    private EditingDomain editingDomain;

    public AddParameterWizard(AbstractProcess container, EditingDomain editingDomain) {
        this.container = container;
        this.setWindowTitle(Messages.newParameter);
        setDefaultPageImageDescriptor(Pics.getWizban());
        parameterWorkingCopy = ParameterFactory.eINSTANCE.createParameter();
        parameterWorkingCopy.setTypeClassname(String.class.getName());
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
        } catch (CoreException e1) {
            BonitaStudioLog.error(e1, ParameterPlugin.PLUGIN_ID);
            return false;
        }

        return true;
    }

    public void addPages() {
        addParameterPage = new ParametersWizardPage(parameterWorkingCopy, getExistingParametersName());
        addParameterPage.setTitle(Messages.bind(Messages.newParameterWizardTitle, container.getName()));
        addParameterPage.setDescription(Messages.newParameterWizardDescription);
        addPage(addParameterPage);
    }

    protected Set<String> getExistingParametersName() {
        Set<String> params = new HashSet<String>();
        for (Parameter p : container.getParameters()) {
            params.add(p.getName());
        }
        return params;
    }

    public Parameter getNewParameter() {
        return parameterWorkingCopy;
    }

}
