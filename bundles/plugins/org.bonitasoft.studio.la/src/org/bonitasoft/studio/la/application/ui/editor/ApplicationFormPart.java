/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageProvider;
import org.bonitasoft.studio.la.application.ui.editor.listener.AddApplicationDescriptorListener;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.xml.sax.SAXException;

public class ApplicationFormPart extends AbstractFormPart {

    private FormToolkit toolkit;
    private ApplicationFormPage formPage;
    private List<ApplicationDescriptorControl> applicationDescriptorControls = new ArrayList<>();
    protected CustomPageProvider customPageProvider;

    public ApplicationFormPart(Composite parent, ApplicationFormPage formPage) {
        ApplicationNodeContainer workingCopy = formPage.getWorkingCopy();
        this.formPage = formPage;
        RepositoryAccessor repositoryAccessor = formPage.getRepositoryAccessor();
        this.customPageProvider = new CustomPageProvider(
                repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class),
                repositoryAccessor.getRepositoryStore(ThemeRepositoryStore.class));
        customPageProvider.init();
        toolkit = formPage.getToolkit();
        if (workingCopy.getApplications().isEmpty()) {
            createNoApplicationsComposite(parent, formPage);
        } else {
            workingCopy.getApplications().stream()
                    .map(application -> {
                        Section section = toolkit.createSection(parent, Section.TWISTIE);
                        section.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                                BonitaThemeConstants.EDITOR_SECTION_BAKGROUND_ID);
                        return createApplicationDescriptorControl(formPage, application, section);
                    })
                    .forEach(applicationDescriptorControls::add);
            applicationDescriptorControls.stream().forEach(section -> {
                section.getTokenObservable().addValueChangeListener(e -> {
                    applicationDescriptorControls.stream()
                            .filter(s -> !section.equals(s))
                            .forEach(ApplicationDescriptorControl::updateTargets); // changing one token may change the status of an other one in the working copy. 
                });
            });
        }
    }

    protected ApplicationDescriptorControl createApplicationDescriptorControl(ApplicationFormPage formPage,
            ApplicationNode application,
            Section section) {
        return new ApplicationDescriptorControl(
                section,
                application,
                formPage,
                customPageProvider);
    }

    public void addApplicationToForm(Composite parent, ApplicationNode application) {
        Section section = toolkit.createSection(parent, Section.TWISTIE);
        section.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
        ApplicationDescriptorControl applicationDescriptorControl = new ApplicationDescriptorControl(
                section,
                application,
                formPage,
                customPageProvider);
        applicationDescriptorControls.add(applicationDescriptorControl);
    }

    public void removeApplicationFromForm(Composite parent, ApplicationNode application) {
        applicationDescriptorControls.stream()
                .filter(applicationControl -> Objects.equals(applicationControl.getApplication(), application))
                .findFirst()
                .ifPresent(applicationControl -> applicationControl.getControl().dispose());
        parent.layout();
    }

    private void createNoApplicationsComposite(Composite parent, ApplicationFormPage formPage) {
        final Composite composite = toolkit.createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.CENTER).create());
        final AddApplicationDescriptorListener listener = new AddApplicationDescriptorListener(composite.getShell(),
                formPage,
                formPage.getRepositoryAccessor());

        final ImageHyperlink imageHyperlink = toolkit.createImageHyperlink(composite, SWT.NO_FOCUS);
        imageHyperlink.setImage(Pics.getImage(PicsConstants.add_item_large));
        imageHyperlink.addHyperlinkListener(listener);
        imageHyperlink.setLayoutData(GridDataFactory.fillDefaults().create());
        Link labelLink = new Link(composite, SWT.NO_FOCUS);
        toolkit.adapt(labelLink, false, false);
        labelLink.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        labelLink.setText(String.format("<a>%s</a>", Messages.addApplicationDescriptor));
        labelLink.addListener(SWT.Selection, listener);
    }

    @Override
    public void commit(boolean onSave) {
        try {
            formPage.getDocument().set(new String(formPage.getParser().marshallToXML(formPage.getWorkingCopy())));
        } catch (final JAXBException | IOException | SAXException e) {
            throw new RuntimeException("Fail to update the document", e);
        }
        super.commit(onSave);
        if (onSave) {
            getManagedForm().dirtyStateChanged();
        }
    }

    public void expendApplication(List<String> applicationTokens) {
        applicationDescriptorControls
                .stream()
                .filter(applicationControl -> applicationTokens
                        .contains(applicationControl.getTokenObservable().getValue()))
                .forEach(applicationControl -> applicationControl.getControl().setExpanded(true));
        if (applicationTokens.isEmpty()) {
            applicationDescriptorControls
                    .stream()
                    .findFirst()
                    .map(ApplicationDescriptorControl::getControl)
                    .ifPresent(control -> control.setExpanded(true));
        }
    }

    public List<String> getExpendedApplications() {
        return applicationDescriptorControls.stream()
                .filter(applicationControl -> applicationControl.getControl().isExpanded())
                .map(ApplicationDescriptorControl::getTokenObservable)
                .map(IObservableValue::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public void dispose() {
        if (customPageProvider != null) {
            customPageProvider.dispose();
        }
        super.dispose();
    }

}
