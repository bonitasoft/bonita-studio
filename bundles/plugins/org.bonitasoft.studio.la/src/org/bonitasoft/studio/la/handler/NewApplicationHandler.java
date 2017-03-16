/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.handler;

import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplication;
import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplicationContainer;
import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.util.Optional;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.ui.control.NewApplicationPage;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Shell;

public class NewApplicationHandler {

    private static final String DEFAULT_PROFILE = "User";
    private static final String DEFAULT_LAYOUT = "custompage_defaultlayout";
    private static final String DEFAULT_THEME = "custompage_bootstrapdefaulttheme";

    @Execute
    public void openNewApplicationWizard(Shell activeShell, RepositoryAccessor repositoryAccessor) {
        createWizard(newWizard(), repositoryAccessor)
                .open(activeShell, Messages.create)
                .ifPresent(IRepositoryFileStore::open);
    }

    protected WizardBuilder<ApplicationFileStore> createWizard(WizardBuilder<ApplicationFileStore> builder,
            RepositoryAccessor repositoryAccessor) {
        final ApplicationNode applicationNode = newApplication("myApp", "My App", "1.0").create();
        applicationNode.setProfile(DEFAULT_PROFILE);
        applicationNode.setLayout(DEFAULT_LAYOUT);
        applicationNode.setTheme(DEFAULT_THEME);

        return builder
                .withTitle(Messages.createNewApplicationDescriptor)
                .havingPage(newPage()
                        .withTitle(Messages.newApplicationDescriptorTitle)
                        .withDescription(Messages.newApplicationDescription)
                        .withControl(new NewApplicationPage(applicationNode, repositoryAccessor)))
                .onFinish(container -> createApplicationFileStore(applicationNode, repositoryAccessor));
    }

    protected Optional<ApplicationFileStore> createApplicationFileStore(ApplicationNode applicationNode,
            RepositoryAccessor repositoryAccessor) {
        final ApplicationRepositoryStore repositoryStore = repositoryAccessor
                .getRepositoryStore(ApplicationRepositoryStore.class);
        final Optional<ApplicationFileStore> fileStore = Optional.ofNullable(repositoryStore
                .createRepositoryFileStore(String.format("%s.xml", applicationNode.getToken())));
        final ApplicationNodeContainer nodeContainer = newApplicationContainer().create();
        nodeContainer.addApplication(applicationNode);
        fileStore.ifPresent(file -> file.save(nodeContainer));
        return fileStore;
    }

}
