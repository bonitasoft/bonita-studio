/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.listener;

import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplication;
import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.control.AddApplicationDescriptorPage;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageDescriptor;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationTokenUnicityValidator;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.xml.sax.SAXException;

public class AddApplicationDescriptorListener extends HyperlinkAdapter implements Listener {

    private static final String DEFAULT_PROFILE = "User";
    private static final String DEFAULT_TOKEN = "myApp";

    private final ApplicationFormPage applicationFormPage;
    private final ApplicationNodeContainerConverter applicationNodeContainerConverter;
    private final Shell shell;
    private final RepositoryAccessor repositoryAccessor;

    public AddApplicationDescriptorListener(Shell shell, ApplicationFormPage applicationFormPage,
            RepositoryAccessor repositoryAccessor) {
        this.shell = shell;
        this.applicationFormPage = applicationFormPage;
        this.applicationNodeContainerConverter = repositoryAccessor
                .getRepositoryStore(ApplicationRepositoryStore.class).getConverter();
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    public void handleEvent(Event event) {
        createWizard(newWizard())
                .open(shell, Messages.add)
                .ifPresent(this::addApplicationNode);
    }

    @Override
    public void linkActivated(HyperlinkEvent e) {
        handleEvent(null);
    }

    protected void addApplicationNode(ApplicationNode application) {
        final ApplicationNodeContainer workingCopy = applicationFormPage.getWorkingCopy();
        workingCopy.addApplication(application);
        applicationFormPage.recreateForm();
        applicationFormPage.expendApplication(Arrays.asList(application.getToken()));
        try {
            applicationFormPage.getDocument().set(new String(applicationNodeContainerConverter.marshallToXML(workingCopy)));
        } catch (JAXBException | IOException | SAXException e) {
            throw new RuntimeException("Fail to update the document", e);
        }
        applicationFormPage.reflow();
    }

    private WizardBuilder<ApplicationNode> createWizard(WizardBuilder<ApplicationNode> builder) {

        final AddApplicationDescriptorPage addDescriptorPage = new AddApplicationDescriptorPage(applicationFormPage,
                getToken(), "My App", "1.0");
        return builder
                .withTitle(Messages.addApplicationDescriptorTitle)
                .withSize(700, 450)
                .havingPage(newPage()
                        .withTitle(Messages.addApplicationDescriptorTitle)
                        .withDescription(Messages.addApplicationDescriptor)
                        .withControl(addDescriptorPage))
                .onFinish(container -> addDescriptorPage.isNew()
                        ? newApplicationDescriptor(addDescriptorPage)
                        : cloneApplicationdescriptor(addDescriptorPage.getNewTokenFromExisting(),
                                addDescriptorPage.getSelection()));
    }

    private Optional<ApplicationNode> newApplicationDescriptor(final AddApplicationDescriptorPage addDescriptorPage) {
        ApplicationNode applicationNode = newApplication(addDescriptorPage.getNewToken(), addDescriptorPage.getDisplayName(),
                addDescriptorPage.getVersion()).create();
        applicationNode.setProfile(DEFAULT_PROFILE);
        applicationNode.setLayout(CustomPageDescriptor.DEFAULT_LAYOUT.getId());
        applicationNode.setTheme(CustomPageDescriptor.DEFAULT_THEME.getId());
        return Optional.of(applicationNode);
    }

    private Optional<ApplicationNode> cloneApplicationdescriptor(String newToken, ApplicationNode toDuplicate) {
        CloneApplicationDescriptorListener cloner = new CloneApplicationDescriptorListener(toDuplicate,
                applicationFormPage);
        ApplicationNode applicationNode = cloner.cloneSimpleFields(newToken);
        if (toDuplicate.getApplicationPages() != null) {
            applicationNode.setApplicationPages(cloner.clonePages(toDuplicate.getApplicationPages()));
        }
        if (toDuplicate.getApplicationMenus() != null) {
            applicationNode.setApplicationMenus(cloner.cloneMenus(toDuplicate.getApplicationMenus()));
        }
        return Optional.of(applicationNode);
    }

    private String getToken() {
        final ApplicationTokenUnicityValidator tokenUnicity = new ApplicationTokenUnicityValidator(repositoryAccessor,
                applicationFormPage.getWorkingCopy(), applicationFormPage.getEditorInput().getName());
        final List<String> tokenList = tokenUnicity.getTokenList();
        return StringIncrementer.getNextIncrementIgnoringCase(DEFAULT_TOKEN, tokenList);
    }

}
