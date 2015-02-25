/*******************************************************************************
 * Copyright (C) 2013-2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.wizard.page;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.parameters.ParameterPlugin;
import org.bonitasoft.studio.parameters.action.RefactorParametersOperation;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.xtext.ui.XtextProjectHelper;

/**
 * @author Romain Bioteau
 *
 */
public class EditParameterWizard extends Wizard {

    private ParametersWizardPage parameterWizardPage;

    private final AbstractProcess container;

    private final Parameter parameter;

    private final Parameter parameterWorkingCopy;

    private final EditingDomain editingDomain;

    public EditParameterWizard(final AbstractProcess container, final Parameter parameter, final EditingDomain editingDomain) {
        Assert.isNotNull(parameter);
        this.container = container;
        this.parameter = parameter;
        parameterWorkingCopy = EcoreUtil.copy(parameter);
        this.editingDomain = editingDomain;
        setWindowTitle(Messages.editParameterWizardTitle);
        setNeedsProgressMonitor(true);
        setDefaultPageImageDescriptor(Pics.getWizban());
    }

    @Override
    public boolean performFinish() {
        final RefactorParametersOperation operation = new RefactorParametersOperation(container);
        operation.addItemToRefactor(parameterWorkingCopy, parameter);
        operation.setEditingDomain(TransactionUtil.getEditingDomain(container));
        operation.setAskConfirmation(!parameter.getName().equals(parameterWorkingCopy.getName()));
        try {
            getContainer().run(true, true, operation);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
            return false;
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
            return false;
        }
        if (!operation.isCancelled()) {
            final CompoundCommand cc = new CompoundCommand();
            for (final EStructuralFeature feature : parameter.eClass().getEAllStructuralFeatures()) {
                cc.append(SetCommand.create(editingDomain, parameter, feature, parameterWorkingCopy.eGet(feature)));
            }
            editingDomain.getCommandStack().execute(cc);
        } else {
            return false;
        }
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
        parameterWizardPage = new ParametersWizardPage(parameterWorkingCopy, getExistingParametersName());
        parameterWizardPage.setTitle(Messages.bind(Messages.editParameterWizardTitle, container.getName()));
        parameterWizardPage.setDescription(Messages.editParameterWizardDescription);
        addPage(parameterWizardPage);
    }

    protected Set<String> getExistingParametersName() {
        final Set<String> params = new HashSet<String>();
        for (final Parameter p : container.getParameters()) {
            if (!p.equals(parameter)) {
                params.add(p.getName());
            }
        }
        return params;
    }

}
