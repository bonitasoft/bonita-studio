/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

import javax.xml.bind.JAXBException;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMJob;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.GroupIdValidator;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wst.sse.core.internal.text.JobSafeStructuredDocument;
import org.xml.sax.SAXException;

public class BusinessDataModelFormPart extends AbstractFormPart {

    public static final String DEFAULT_PACKAGE_NAME = "com.company.model";

    private DataBindingContext ctx = new DataBindingContext();
    private BusinessDataModelFormPage formPage;
    private BusinessObjectList businessObjectList;
    private BusinessObjectEditionControl businessObjectEditionControl;

    public BusinessDataModelFormPart(Composite businessDataModelComposite,
            BusinessDataModelFormPage formPage) {
        this.formPage = formPage;
        businessDataModelComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(20, 5).create());
        businessDataModelComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 600).create());

        Composite leftComposite = formPage.getToolkit().createComposite(businessDataModelComposite);
        leftComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        leftComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(405, SWT.DEFAULT).create());

        createBusinessObjectList(leftComposite);
        createMavenArtifactPropertiesGroup(leftComposite, ctx);

        Composite rightComposite = formPage.getToolkit().createComposite(businessDataModelComposite);
        rightComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        rightComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(600, SWT.DEFAULT).create());

        createBusinessObjectEditionControl(rightComposite);
    }

    private void createMavenArtifactPropertiesGroup(Composite parent, DataBindingContext ctx) {
        Section section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setText(Messages.mavenArtifactProperties);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().create());
        client.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10).create());

        IObservableValue<BusinessObjectModel> workingCopyObservable = formPage.observeWorkingCopy();
        Supplier<IStatus> canEditSupplier = () -> {
            boolean canEdit = formPage.getEditorContribution().containsBusinessObjects(workingCopyObservable)
                    && !formPage.getEditorContribution().isOnError(workingCopyObservable);
            return canEdit
                    ? ValidationStatus.ok()
                    : ValidationStatus.error(Messages.groupIdCannotBeEdited);
        };
        new TextWidget.Builder()
                .withLabel(Messages.groupId)
                .labelAbove()
                .transactionalEdit((oldValue, newValue) -> updateMavenDependency(oldValue, newValue), canEditSupplier)
                .fill()
                .withTootltip(Messages.mavenArtifactPropertiesHint)
                .grabHorizontalSpace()
                .bindTo(EMFObservables.observeDetailValue(Realm.getDefault(), workingCopyObservable,
                        BusinessDataModelPackage.Literals.BUSINESS_OBJECT_MODEL__GROUP_ID))
                .withTargetToModelStrategy(UpdateStrategyFactory.convertUpdateValueStrategy()
                        .withValidator(new GroupIdValidator(formPage.getRepositoryAccessor().getWorkspace()))
                        .create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(client);

        section.setClient(client);
    }

    private void updateMavenDependency(String oldValue, String newValue) {
        if (!Objects.equals(oldValue, newValue)) {
            BusinessObjectPlugin.getDefault().getPreferenceStore()
                    .setValue(BusinessObjectModelFileStore.BDM_DEPLOY_REQUIRED_PROPERTY, true); // Bypass the notification mechanism
            formPage.getEditorContribution().doSave(new NullProgressMonitor());
            BDMArtifactDescriptor loadArtifactDescriptor = formPage.getEditorContribution().loadBdmArtifactDescriptor();
            RemoveDependencyOperation operation = new RemoveDependencyOperation(oldValue,
                    GenerateBDMOperation.BDM_CLIENT, loadArtifactDescriptor.getVersion());
            BusinessObjectModelFileStore bdmFileStore = (BusinessObjectModelFileStore) formPage.getRepositoryAccessor()
                    .getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                    .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false);
            DeployBDMJob deployBDMJob = new DeployBDMJob(bdmFileStore, false);
            new WorkspaceJob("Remove Project BDM dependency") {

                @Override
                public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                    operation.run(monitor);
                    deployBDMJob.schedule();
                    return Status.OK_STATUS;
                }
            }.schedule();
        }
    }

    private void createBusinessObjectEditionControl(Composite parent) {
        businessObjectEditionControl = new BusinessObjectEditionControl(parent, formPage, ctx);
        ctx.bindValue(formPage.observeBusinessObjectSelected(), businessObjectEditionControl.observeSectionTitle(),
                updateValueStrategy().withConverter(ConverterBuilder.<BusinessObject, String> newConverter()
                        .fromType(BusinessObject.class)
                        .toType(String.class)
                        .withConvertFunction(o -> o != null ? o.getSimpleName() : "")
                        .create()).create(),
                neverUpdateValueStrategy().create());
        ctx.bindValue(businessObjectEditionControl.observeSectionVisible(), new ComputedValue<Boolean>(Boolean.TYPE) {

            @Override
            protected Boolean calculate() {
                return formPage.observeBusinessObjectSelected().getValue() != null;
            }
        });
    }

    private void createBusinessObjectList(Composite businessDataModelComposite) {
        businessObjectList = new BusinessObjectList(businessDataModelComposite, formPage, ctx);
        ctx.bindValue(businessObjectList.observeInput(), formPage.observeWorkingCopy());
        businessObjectList.expandAll();
    }

    @Override
    public void commit(boolean onSave) {
        BusinessObjectModel workingCopy = formPage.observeWorkingCopy().getValue();
        JobSafeStructuredDocument document = (JobSafeStructuredDocument) formPage.getDocument();
        DocumentRewriteSession session = null;
        try {
            session = document.startRewriteSession(DocumentRewriteSessionType.STRICTLY_SEQUENTIAL);
            document.set(new String(formPage.getParser().marshall(formPage.getConverter().toEngineModel(workingCopy))));
            BDMArtifactDescriptor bdmArtifactDescriptor = new BDMArtifactDescriptor();
            bdmArtifactDescriptor.setGroupId(workingCopy.getGroupId());
            formPage.getEditorContribution().saveBdmArtifactDescriptor(bdmArtifactDescriptor);
        } catch (final JAXBException | IOException | SAXException e) {
            throw new RuntimeException("Fail to update the document", e);
        } finally {
            if (session != null) {
                document.stopRewriteSession(session);
            }
        }
        super.commit(onSave);
        if (onSave) {
            getManagedForm().dirtyStateChanged();
        }
    }

    public void updateFieldDetailsTopControl() {
        businessObjectEditionControl.updateFieldDetailsTopControl();
    }

    public void refreshBusinessObjectList() {
        businessObjectList.refreshViewer();
    }

    public void showBusinessObjectSelection() {
        businessObjectList.showBusinessObjectSelection();
    }
}
