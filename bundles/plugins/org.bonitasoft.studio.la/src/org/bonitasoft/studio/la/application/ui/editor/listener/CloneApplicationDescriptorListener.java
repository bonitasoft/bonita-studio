/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.listener;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.xml.AbstractApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationMenuNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationTokenUnicityValidator;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.xml.sax.SAXException;

public class CloneApplicationDescriptorListener implements Listener {

    private AbstractApplicationNode application;
    private ApplicationFormPage formPage;

    public CloneApplicationDescriptorListener(AbstractApplicationNode application, ApplicationFormPage formPage) {
        this.application = application;
        this.formPage = formPage;
    }

    @Override
    public void handleEvent(Event event) {
        ApplicationNodeContainer workingCopy = formPage.getWorkingCopy();
        AbstractApplicationNode applicationCloned = cloneSimpleFields(incrementToken(application.getToken()));

        if (application instanceof ApplicationNode legacy) {
            ((ApplicationNode) applicationCloned).setApplicationPages(clonePages(legacy.getApplicationPages()));
            ((ApplicationNode) applicationCloned).setApplicationMenus(cloneMenus(legacy.getApplicationMenus()));
        }

        workingCopy.addApplication(applicationCloned);
        formPage.addApplicationToForm(applicationCloned);
        try {
            formPage.getDocument().set(new String(RepositoryManager.getInstance()
                    .getRepositoryStore(ApplicationRepositoryStore.class).getConverter().marshallToXML(workingCopy)));
        } catch (JAXBException | IOException | SAXException e) {
            throw new RuntimeException("Fail to update the document", e);
        }
        formPage.reflow();
    }

    public AbstractApplicationNode cloneSimpleFields(String newToken) {
        if (application instanceof ApplicationNode legacy) {
            return ApplicationNodeBuilder.newApplication(newToken,
                    application.getDisplayName(), application.getVersion())
                    .withDescription(application.getDescription())
                    .withProfile(application.getProfile())
                    .withHomePage(legacy.getHomePage())
                    .withIconPath(application.getIconPath())
                    .withLayout(legacy.getLayout())
                    .withTheme(legacy.getTheme())
                    .create();
        } else {
            return ApplicationNodeBuilder.newApplicationLink(newToken,
                    application.getDisplayName(), application.getVersion())
                    .withDescription(application.getDescription())
                    .withProfile(application.getProfile())
                    .withIconPath(application.getIconPath())
                    .create();
        }
    }

    private String incrementToken(String token) {
        String defaultToken = getDefaultToken(token);
        ApplicationTokenUnicityValidator tokenUnicity = new ApplicationTokenUnicityValidator(
                formPage.getRepositoryAccessor(),
                formPage.getWorkingCopy(), formPage.getEditorInput().getName());
        List<String> tokenList = tokenUnicity.getTokenList();
        return StringIncrementer.getNextIncrementIgnoringCase(defaultToken, tokenList);
    }

    private String getDefaultToken(String token) {
        char endToken = token.charAt(token.length() - 1);
        if (token.length() == 1 || Character.isLetter(endToken)) {
            return token;
        }
        return getDefaultToken(token.substring(0, token.length() - 1));
    }

    public List<ApplicationPageNode> clonePages(List<ApplicationPageNode> pages) {
        return pages.stream()
                .map(page -> ApplicationNodeBuilder.newApplicationPage(page.getCustomPage(), page.getToken()).create())
                .collect(Collectors.toList());
    }

    public List<ApplicationMenuNode> cloneMenus(List<ApplicationMenuNode> menus) {
        return menus.stream()
                .map(menu -> {
                    ApplicationMenuNode copy = ApplicationNodeBuilder
                            .newMenu(menu.getDisplayName(), menu.getApplicationPage()).create();
                    cloneMenus(menu.getApplicationMenus()).forEach(copy::addApplicationMenu);
                    return copy;
                })
                .collect(Collectors.toList());
    }

}
