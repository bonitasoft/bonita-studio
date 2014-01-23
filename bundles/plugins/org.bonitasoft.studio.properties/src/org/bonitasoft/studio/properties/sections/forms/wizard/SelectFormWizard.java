/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.forms.wizard;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.diagram.form.custom.commands.CreateFormCommand;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.Wizard;

public class SelectFormWizard extends Wizard {

	protected SelectGeneratedWidgetsWizardPage selectGeneratedWidgetsWizardPage;
	protected Element pageFlow;
	protected TransactionalEditingDomain editingDomain;
	protected EStructuralFeature feature;

	public SelectFormWizard(Element element, EStructuralFeature feature, TransactionalEditingDomain editingDomain) {
		pageFlow = element;
		this.editingDomain = editingDomain;
		this.feature = feature;
		setWindowTitle(Messages.addFormTitle);
		setDefaultPageImageDescriptor(Pics.getWizban());
	}

	@Override
	public void addPages() {
		selectGeneratedWidgetsWizardPage = new SelectGeneratedWidgetsWizardPage(pageFlow,feature);
		addPage(selectGeneratedWidgetsWizardPage);
	}

	@Override
	public boolean performFinish() {
		return createForm();
	}

	protected boolean createForm() {
		String name = selectGeneratedWidgetsWizardPage.getFormName();
		selectGeneratedWidgetsWizardPage.setMessage(null);
		boolean allreadyExists = false;
		for (Iterator<?> iterator = ((List<?>)pageFlow.eGet(feature)).iterator(); iterator.hasNext();) {
			Form form = (Form) iterator.next();
			allreadyExists = form.getName().equals(NamingUtils.toJavaIdentifier(name,true));
			if (allreadyExists) {
				selectGeneratedWidgetsWizardPage.setMessage(Messages.error_allreadyExists, IMessageProvider.ERROR);
				return false;
			}
		}
		if (name.length() == 0) {
			selectGeneratedWidgetsWizardPage.setMessage(Messages.error_empty, IMessageProvider.ERROR);
			return false;
		}
		Map<EObject, Widget> widgets = selectGeneratedWidgetsWizardPage.getWidgetsToGenerate();
		CreateFormCommand createFormCmd = getCreateFormCommand(name, widgets);
		try {
			createFormCmd.execute(Repository.NULL_PROGRESS_MONITOR, null);
		} catch (ExecutionException e) {
			BonitaStudioLog.error(e);
		}
		Form createdForm = (Form) createFormCmd.getCommandResult().getReturnValue();
		FormsUtils.createDiagram(createdForm, editingDomain, pageFlow);
		return FormsUtils.openDiagram(createdForm, editingDomain) != null;
	}

	protected CreateFormCommand getCreateFormCommand(String name,
			Map<EObject, Widget> widgets) {
		return new CreateFormCommand(pageFlow, feature, name, selectGeneratedWidgetsWizardPage.getFormDescription(), widgets, editingDomain);
	}

	@Override
	public boolean canFinish() {
		return selectGeneratedWidgetsWizardPage.isPageComplete();

	}

}
