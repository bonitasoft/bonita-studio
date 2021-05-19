/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.la.application.core.ApplicationDependencyResolver;
import org.bonitasoft.studio.la.application.ui.editor.contribution.AddApplicationDescriptorContributionItem;
import org.bonitasoft.studio.la.application.ui.editor.contribution.DeleteContributionItem;
import org.bonitasoft.studio.la.application.ui.editor.contribution.DeployContributionItem;
import org.bonitasoft.studio.la.application.ui.editor.contribution.ExportApplicationContributionItem;
import org.bonitasoft.studio.la.application.ui.editor.contribution.RenameContributionItem;
import org.bonitasoft.studio.la.application.ui.editor.customPage.ApplicationEditorProviders;
import org.bonitasoft.studio.ui.editors.xmlEditors.AbstractFormPage;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.widgets.ToolBar;

public class ApplicationFormPage extends AbstractFormPage<ApplicationNodeContainer> implements IDocumentListener {

    public static final String DEFAULT_USER_ID = "User";
    public static final String DEFAULT_ADMINISTRATOR_ID = "Administrator";

    private ApplicationFormPart applicationFormPart;
    private ApplicationNodeContainerConverter parser;
    private List<String> expendedApplications = new ArrayList<>();
    private ApplicationEditorProviders applicationEditorProviders;

    public ApplicationFormPage(String id, String title, ApplicationNodeContainerConverter parser, IEclipseContext context) {
        super(id, title, context);
        this.parser = parser;
        this.applicationEditorProviders = createApplicationEditorProviders();
    }

    protected ApplicationEditorProviders createApplicationEditorProviders() {
        return new ApplicationEditorProviders(repositoryAccessor);
    }

    @Override
    public ApplicationEditor getEditor() {
        return (ApplicationEditor) super.getEditor();
    }

    @Override
    protected void createForm() {
        applicationFormPart = createApplicationFormPart();
        getDocument().addDocumentListener(this);
        getManagedForm().addPart(applicationFormPart);
        expendApplication(expendedApplications);
    }

    protected ApplicationFormPart createApplicationFormPart() {
        return new ApplicationFormPart(scrolledForm.getBody(), this);
    }

    public void addApplicationToForm(ApplicationNode application) {
        applicationFormPart.addApplicationToForm(scrolledForm.getBody(), application);
    }

    public void removeApplicationFromForm(ApplicationNode application) {
        applicationFormPart.removeApplicationFromForm(scrolledForm.getBody(), application);
    }

    @Override
    protected void createHeaderContent(ToolBar toolBar) {
        toolBarManager.add(new AddApplicationDescriptorContributionItem(
                toolBar.getShell(), this, repositoryAccessor));
        toolBarManager.add(new DeployContributionItem(this, createApplicationDependencyResolver()));
        toolBarManager.add(new RenameContributionItem(this));
        toolBarManager.add(new ExportApplicationContributionItem(this));
        toolBarManager.add(new DeleteContributionItem(getEditor()));
        toolBarManager.update(true);
    }

    protected ApplicationDependencyResolver createApplicationDependencyResolver() {
        return new ApplicationDependencyResolver();
    }

    public void makeDirty() {
        if (applicationFormPart != null) {
            applicationFormPart.markDirty();
            getManagedForm().dirtyStateChanged();
        }
    }

    public void makeStale() {
        if (applicationFormPart != null) {
            applicationFormPart.markStale();
        }
    }

    public boolean isStale() {
        return applicationFormPart != null && applicationFormPart.isStale();
    }

    @Override
    public void documentAboutToBeChanged(DocumentEvent event) {
    }

    @Override
    public void documentChanged(DocumentEvent event) {
        if (!isActive()) {
            makeStale();
        }
    }

    public ApplicationNodeContainerConverter getParser() {
        return parser;
    }

    public void saveExpendedApplications() {
        if (applicationFormPart != null) {
            this.expendedApplications = applicationFormPart.getExpendedApplications();
        }
    }

    public void expendApplication(List<String> applicationTokens) {
        applicationFormPart.expendApplication(applicationTokens);
    }

    public List<String> getExpendedApplications() {
        return expendedApplications;
    }

    public ApplicationEditorProviders getApplicationEditorProviders() {
        return applicationEditorProviders;
    }

    public void disposeProviders() {
        applicationEditorProviders.dispose();
    }

}
