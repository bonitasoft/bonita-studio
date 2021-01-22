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

import javax.xml.bind.JAXBException;

import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.GroupIdValidator;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
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
        businessDataModelComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Composite leftComposite = formPage.getToolkit().createComposite(businessDataModelComposite);
        leftComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        leftComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(400, SWT.DEFAULT).create());

        createBusinessObjectList(leftComposite);
        createMavenArtifactPropertiesGroup(leftComposite, ctx);
        createBusinessObjectEditionControl(businessDataModelComposite);
    }

    private void createMavenArtifactPropertiesGroup(Composite parent, DataBindingContext ctx) {
        Section section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setText(Messages.mavenArtifactProperties);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10).create());

        new TextWidget.Builder()
                .withLabel(Messages.groupId)
                .labelAbove()
                .fill()
                .withTootltip(Messages.mavenArtifactPropertiesHint)
                .grabHorizontalSpace()
                .bindTo(EMFObservables.observeDetailValue(Realm.getDefault(), formPage.observeWorkingCopy(),
                        BusinessDataModelPackage.Literals.BUSINESS_OBJECT_MODEL__GROUP_ID))
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(new GroupIdValidator(formPage.getRepositoryAccessor().getWorkspace()))
                        .create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(client);

        formPage.getToolkit().createLabel(client, "");

        section.setClient(client);
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

    public void updateTopControl() {
        // TODO Auto-generated method stub
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
