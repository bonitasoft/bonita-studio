/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms.wizard;

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.collect.Iterables.find;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.diagram.form.custom.commands.CreateFormCommand;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.MultiInstantiable;
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
    protected EStructuralFeature pageFlowFormFeature;
    protected final BusinessObjectModelRepositoryStore businessObjectStore;

    public SelectFormWizard(final Element element, final EStructuralFeature pageFlowFormFeature, final TransactionalEditingDomain editingDomain,
            final BusinessObjectModelRepositoryStore businessObjectStore) {
        pageFlow = element;
        this.businessObjectStore = businessObjectStore;
        this.editingDomain = editingDomain;
        this.pageFlowFormFeature = pageFlowFormFeature;
        setWindowTitle(Messages.addFormTitle);
        setDefaultPageImageDescriptor(Pics.getWizban());
    }

    @Override
    public void addPages() {
        final SelectGeneratedWidgetsWizardPage selectGeneratedWidgetsWizardPage = createSelectGeneratedWidgetsWizardPage(generateDefaultFormName(),
                getAccessibleModelElements(pageFlow, pageFlowFormFeature));
        addPage(selectGeneratedWidgetsWizardPage);
    }

    protected SelectGeneratedWidgetsWizardPage createSelectGeneratedWidgetsWizardPage(final String defaultFormName, final List<EObject> inputElements) {
        final SelectGeneratedWidgetsWizardPage page = new SelectGeneratedWidgetsWizardPage(pageFlow, defaultFormName, inputElements,
                businessObjectStore);
        page.setTitle(Messages.createForm_title);
        page.setDescription(Messages.createForm_desc);
        return page;
    }

    protected List<EObject> getAccessibleModelElements(final Element container, final EStructuralFeature pageFlowFormFeature) {
        final List<EObject> elements = new ArrayList<EObject>();
        if (container instanceof PageFlow) {
            final List<Data> allData = ModelHelper.getAccessibleDataInFormsWithNoRestriction(container, pageFlowFormFeature);
            for (final Data currentData : allData) {
                final EClass eClassData = currentData.getDataType().eClass();
                if (!aJavaObjectData(eClassData)
                        && !anXmlData(eClassData)
                        && !aMultipleBusinessData(currentData)
                        && !aBusinessDataWithoutSimpleFields(currentData)) {
                    elements.add(currentData);
                }
            }
            if (container instanceof MultiInstantiable) {
                addMultiInstanceIterator(container, elements);
            }
        }
        final AbstractProcess parentProcess = ModelHelper.getParentProcess(container);
        if (parentProcess instanceof Pool) {
            elements.addAll(((Pool) parentProcess).getDocuments());
        }

        return elements;
    }

    private void addMultiInstanceIterator(final Element container, final List<EObject> elements) {
        if (isDataMultiInstantiated((MultiInstantiable) container)) {
            final Data dataFromIteratorExpression = ExpressionHelper.dataFromIteratorExpression((MultiInstantiable) container,
                    ((MultiInstantiable) container).getIteratorExpression(), ModelHelper.getMainProcess(container));
            final EClass eClassData = dataFromIteratorExpression.getDataType().eClass();
            if (!aJavaObjectData(eClassData)
                    && !anXmlData(eClassData)) {
                elements.add(dataFromIteratorExpression);
            }
        }
    }

    private boolean aJavaObjectData(final EClass eClassData) {
        return ProcessPackage.eINSTANCE.getJavaType().isSuperTypeOf(eClassData);
    }

    private boolean anXmlData(final EClass eClassData) {
        return ProcessPackage.eINSTANCE.getXMLType().isSuperTypeOf(eClassData);
    }

    private boolean aBusinessDataWithoutSimpleFields(final Data currentData) {
        return currentData instanceof BusinessObjectData && !hasSimpleField(((BusinessObjectData) currentData).getClassName());
    }

    private boolean hasSimpleField(final String className) {
        final BusinessObjectModelFileStore modelFileStore = businessObjectStore.getChildByQualifiedName(className);
        final BusinessObject bo = modelFileStore.getBusinessObject(className);
        if (bo != null) {
            return find(bo.getFields(), instanceOf(SimpleField.class), null) != null;
        }
        return true;
    }

    private boolean aMultipleBusinessData(final Data currentData) {
        return currentData instanceof BusinessObjectData && currentData.isMultiple();
    }

    protected boolean isDataMultiInstantiated(final MultiInstantiable element) {
        return (element.getType() == MultiInstanceType.PARALLEL || element.getType() == MultiInstanceType.SEQUENTIAL) && !element.isUseCardinality()
                && element.getIteratorExpression() != null && element.getIteratorExpression().getContent() != null;
    }

    protected String generateDefaultFormName() {
        final String baseName = pageFlow.getName();
        final int i = ((List<?>) pageFlow.eGet(pageFlowFormFeature)).size();
        for (final Iterator<?> iterator = ((List<?>) pageFlow.eGet(pageFlowFormFeature)).iterator(); iterator.hasNext();) {
            final Form form = (Form) iterator.next();
            if (!form.getName().equals(baseName + i)) {
                return baseName + (i <= 0 ? "" : "_" + i);
            }

        }
        return baseName + (i <= 0 ? "" : "_" + i);
    }

    @Override
    public boolean performFinish() {
        return createForm();
    }

    protected boolean createForm() {
        final SelectGeneratedWidgetsWizardPage selectGeneratedWidgetsWizardPage = getSelectGeneratedWidgetsWizardPage();
        final String name = selectGeneratedWidgetsWizardPage.getFormName();
        selectGeneratedWidgetsWizardPage.setMessage(null);
        boolean allreadyExists = false;
        for (final Iterator<?> iterator = ((List<?>) pageFlow.eGet(pageFlowFormFeature)).iterator(); iterator.hasNext();) {
            final Form form = (Form) iterator.next();
            allreadyExists = form.getName().equals(NamingUtils.toJavaIdentifier(name, true));
            if (allreadyExists) {
                selectGeneratedWidgetsWizardPage.setMessage(Messages.error_allreadyExists, IMessageProvider.ERROR);
                return false;
            }
        }
        if (name.length() == 0) {
            selectGeneratedWidgetsWizardPage.setMessage(Messages.error_empty, IMessageProvider.ERROR);
            return false;
        }
        final List<? extends WidgetMapping> widgesMappings = selectGeneratedWidgetsWizardPage.getWidgetMappings();
        final CreateFormCommand createFormCmd = getCreateFormCommand(name, widgesMappings);
        try {
            createFormCmd.execute(Repository.NULL_PROGRESS_MONITOR, null);
        } catch (final ExecutionException e) {
            BonitaStudioLog.error(e);
        }
        final Form createdForm = (Form) createFormCmd.getCommandResult().getReturnValue();
        FormsUtils.createFormDiagram(createdForm, editingDomain);
        return FormsUtils.openDiagram(createdForm, editingDomain) != null;
    }

    protected SelectGeneratedWidgetsWizardPage getSelectGeneratedWidgetsWizardPage() {
        return SelectGeneratedWidgetsWizardPage.class.cast(getPage(SelectGeneratedWidgetsWizardPage.class.getName()));
    }

    protected CreateFormCommand getCreateFormCommand(final String name,
            final List<? extends WidgetMapping> widgesMappings) {
        if (widgesMappings == null) {
            return new CreateFormCommand(pageFlow, pageFlowFormFeature, name, getSelectGeneratedWidgetsWizardPage().getFormDescription(), editingDomain);
        } else {
            return new CreateFormCommand(pageFlow, pageFlowFormFeature, name, getSelectGeneratedWidgetsWizardPage().getFormDescription(), widgesMappings,
                    editingDomain);
        }
    }

    @Override
    public boolean canFinish() {
        return getSelectGeneratedWidgetsWizardPage().isPageComplete();

    }

}
