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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.diagram.form.custom.commands.CreateFormCommand;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.forms.FormsUtils;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.Wizard;

public class SelectFormWizard extends Wizard {


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
		SelectGeneratedWidgetsWizardPage selectGeneratedWidgetsWizardPage = createSelectGeneratedWidgetsWizardPage(generateDefaultFormName(),getAccessibleModelElements());
		addPage(selectGeneratedWidgetsWizardPage);
	}

	protected SelectGeneratedWidgetsWizardPage createSelectGeneratedWidgetsWizardPage(String defaultFormName, List<EObject> inputElements) {
		SelectGeneratedWidgetsWizardPage page = new SelectGeneratedWidgetsWizardPage(defaultFormName,inputElements);
		page.setTitle(Messages.createForm_title);
		page.setDescription(Messages.createForm_desc);
		return page;
	}

	protected List<EObject> getAccessibleModelElements() {
		List<EObject> elements = new ArrayList<EObject>();
		if(pageFlow instanceof PageFlow){
			List<Data> allData = ModelHelper.getAccessibleDataInFormsWithNoRestriction(pageFlow, feature);
			for (Data currentData : allData) {
				EClass eClassData = currentData.getDataType().eClass();
				if(!ProcessPackage.eINSTANCE.getJavaType().isSuperTypeOf(eClassData)
						&& !ProcessPackage.eINSTANCE.getXMLType().isSuperTypeOf(eClassData)) {
					elements.add(currentData);
				}
			}
		}
		final AbstractProcess parentProcess = ModelHelper.getParentProcess(pageFlow);
		if(parentProcess instanceof Pool){
			elements.addAll(((Pool) parentProcess).getDocuments());
		}

		return elements;
	}

	protected String generateDefaultFormName() {
		String baseName = pageFlow.getName();
		int i = ((List<?>) pageFlow.eGet(feature)).size();
		for (Iterator<?> iterator = ((List<?>) pageFlow.eGet(feature)).iterator(); iterator.hasNext();) {
			Form form = (Form) iterator.next();
			if(! form.getName().equals(baseName+i)){
				return baseName+(i<=0?"":"_"+i);
			}

		}
		return baseName+(i<=0?"":"_"+i);
	}

	@Override
	public boolean performFinish() {
		return createForm();
	}

	protected boolean createForm() {
		SelectGeneratedWidgetsWizardPage selectGeneratedWidgetsWizardPage = getSelectGeneratedWidgetsWizardPage();
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
		List<? extends WidgetMapping> widgesMappings = selectGeneratedWidgetsWizardPage.getWidgetMappings();
		CreateFormCommand createFormCmd = getCreateFormCommand(name, widgesMappings);
		try {
			createFormCmd.execute(Repository.NULL_PROGRESS_MONITOR, null);
		} catch (ExecutionException e) {
			BonitaStudioLog.error(e);
		}
		Form createdForm = (Form) createFormCmd.getCommandResult().getReturnValue();
		FormsUtils.createDiagram(createdForm, editingDomain, pageFlow);
		return FormsUtils.openDiagram(createdForm, editingDomain) != null;
	}

	protected SelectGeneratedWidgetsWizardPage getSelectGeneratedWidgetsWizardPage() {
		return SelectGeneratedWidgetsWizardPage.class.cast(getPage(SelectGeneratedWidgetsWizardPage.class.getName()));
	}

	protected CreateFormCommand getCreateFormCommand(String name,
			List<? extends WidgetMapping> widgesMappings) {
		if(widgesMappings == null){
			return new CreateFormCommand(pageFlow, feature, name, getSelectGeneratedWidgetsWizardPage().getFormDescription(), editingDomain);
		}else{
			return new CreateFormCommand(pageFlow, feature, name, getSelectGeneratedWidgetsWizardPage().getFormDescription(), widgesMappings, editingDomain);
		}
	}

	@Override
	public boolean canFinish() {
		return getSelectGeneratedWidgetsWizardPage().isPageComplete();

	}

}
