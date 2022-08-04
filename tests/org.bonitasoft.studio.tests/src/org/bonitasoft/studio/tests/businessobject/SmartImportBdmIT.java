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
package org.bonitasoft.studio.tests.businessobject;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.core.repository.AbstractBDMFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ZipUtil;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.bdm.ImportBdmWizardBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SmartImportBdmIT {

    final static String BUSINESS_PACKAGE = "com.company.business";
    final static String HUMAN_PACKAGE = "com.company.human";
    final static String EMPLOYEE_PACKAGE = "com.company.employee";

    final static String LOAN_BO = "Loan";
    final static String ACCOUNT_BO = "Account";
    final static String PERSON_BO = "Person";
    final static String ADDRESS_BO = "Address";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    private BusinessObjectModelRepositoryStore repositoryStore;

    @Before
    public void initBdm() throws Exception {
        repositoryStore = RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        AbstractBDMFileStore bdmFileStore = repositoryStore.getChild("bom.xml", true);
        if (bdmFileStore != null) {
            bdmFileStore.delete();
        }
        importInitialBdm();
    }

    @Test
    public void should_not_import_when_bdm_are_identicals() throws Exception {
        BotApplicationWorkbenchWindow workbenchBot = new BotApplicationWorkbenchWindow(bot);
        ImportBdmWizardBot importBot = workbenchBot.importBDM();
        importBot.setArchive(getFileURL("/smartImport/model_initial.zip"));

        SWTBotTree importTree = bot.tree();
        bot.waitUntil(importBot.treeItemAvailable(importTree, String.format("%s (%s)", BUSINESS_PACKAGE, Messages.skipped)));
        bot.waitUntil(importBot.treeItemAvailable(importTree, String.format("%s (%s)", HUMAN_PACKAGE, Messages.skipped)));
        assertThat(bot.button(org.bonitasoft.studio.ui.i18n.Messages.importLabel).isEnabled()).isFalse();
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void should_import_when_bdm_are_not_confilicting() throws Exception {
        BotApplicationWorkbenchWindow workbenchBot = new BotApplicationWorkbenchWindow(bot);
        ImportBdmWizardBot importBot = workbenchBot.importBDM();
        importBot.setArchive(getFileURL("/smartImport/model_not_conflicting.zip"));

        SWTBotTree importTree = bot.tree();
        bot.waitUntil(importBot.treeItemAvailable(importTree, BUSINESS_PACKAGE));
        bot.waitUntil(importBot.treeItemAvailable(importTree, String.format("%s (%s)", HUMAN_PACKAGE, Messages.skipped)));
        assertThat(bot.button(org.bonitasoft.studio.ui.i18n.Messages.importLabel).isEnabled()).isTrue();

        SWTBotTreeItem treeItem = importTree.getTreeItem(BUSINESS_PACKAGE);
        treeItem.expand();
        bot.waitUntil(importBot.treeNodeAvailable(treeItem, ACCOUNT_BO));
        bot.waitUntil(importBot.treeNodeAvailable(treeItem, String.format("%s (%s)", LOAN_BO, Messages.skipped)));

        importBot.doImport();

        BusinessObjectModelFileStore fileStore = (BusinessObjectModelFileStore) repositoryStore.getChild("bom.xml", true);
        assertThat(fileStore.getContent().getBusinessObjects()).extracting(BusinessObject::getQualifiedName)
                .containsExactlyInAnyOrder(toQualifiedName(BUSINESS_PACKAGE, LOAN_BO),
                        toQualifiedName(BUSINESS_PACKAGE, ACCOUNT_BO),
                        toQualifiedName(HUMAN_PACKAGE, PERSON_BO),
                        toQualifiedName(HUMAN_PACKAGE, ADDRESS_BO));
    }

    @Test
    public void should_import_according_to_user_choices_when_conflicts_are_solvable() throws Exception {
        BotApplicationWorkbenchWindow workbenchBot = new BotApplicationWorkbenchWindow(bot);
        ImportBdmWizardBot importBot = workbenchBot.importBDM();
        importBot.setArchive(getFileURL("/smartImport/model_conflicting_ok.zip"));

        SWTBotTree importTree = bot.tree();
        bot.waitUntil(importBot.treeItemAvailable(importTree, HUMAN_PACKAGE));
        bot.waitUntil(importBot.treeItemAvailable(importTree, String.format("%s (%s)", BUSINESS_PACKAGE, Messages.skipped)));
        assertThat(bot.button(org.bonitasoft.studio.ui.i18n.Messages.importLabel).isEnabled()).isTrue();

        SWTBotTreeItem treeItem = importTree.getTreeItem(HUMAN_PACKAGE);
        treeItem.expand();
        bot.waitUntil(importBot.treeNodeAvailable(treeItem, String.format(org.bonitasoft.studio.businessobject.i18n.Messages.conflictingWithSameObject, PERSON_BO)));
        bot.waitUntil(importBot.treeNodeNotAvailable(treeItem, String.format("%s (%s)", ADDRESS_BO, Messages.skipped)));

        assertThat(treeItem.cell(1)).isEqualTo(ImportAction.OVERWRITE.toString());

        importBot.setImportAction(HUMAN_PACKAGE, ImportAction.KEEP.toString());
        bot.waitUntil(importBot.treeNodeAvailable(treeItem, String.format(org.bonitasoft.studio.businessobject.i18n.Messages.conflictingWithSameObject, PERSON_BO)));
        bot.waitUntil(importBot.treeNodeAvailable(treeItem, String.format("%s (%s)", ADDRESS_BO, Messages.skipped)));

        importBot.setImportAction(HUMAN_PACKAGE, ImportAction.OVERWRITE.toString());
        bot.waitUntil(importBot.treeNodeAvailable(treeItem, String.format(org.bonitasoft.studio.businessobject.i18n.Messages.conflictingWithSameObject, PERSON_BO)));
        bot.waitUntil(importBot.treeNodeNotAvailable(treeItem, String.format("%s (%s)", ADDRESS_BO, Messages.skipped)));

        importBot.doImport();

        BusinessObjectModelFileStore fileStore = (BusinessObjectModelFileStore) repositoryStore.getChild("bom.xml", true);
        assertThat(fileStore.getContent().getBusinessObjects()).extracting(BusinessObject::getQualifiedName)
                .containsExactlyInAnyOrder(toQualifiedName(BUSINESS_PACKAGE, LOAN_BO),
                        toQualifiedName(HUMAN_PACKAGE, PERSON_BO));
    }

    @Test
    public void should_overwrite_when_conflicts_are_not_solvable() throws Exception {
        BotApplicationWorkbenchWindow workbenchBot = new BotApplicationWorkbenchWindow(bot);
        ImportBdmWizardBot importBot = workbenchBot.importBDM();
        importBot.setArchive(getFileURL("/smartImport/model_conflicting_not_ok.zip"));

        SWTBotTree importTree = bot.tree();
        bot.waitUntil(importBot.treeItemAvailable(importTree, EMPLOYEE_PACKAGE));
        bot.waitUntil(importBot.treeItemAvailable(importTree, BUSINESS_PACKAGE));
        assertThat(bot.button(org.bonitasoft.studio.ui.i18n.Messages.importLabel).isEnabled()).isTrue();

        SWTBotTreeItem businessTreeItem = importTree.getTreeItem(BUSINESS_PACKAGE);
        SWTBotTreeItem personTreeItem = importTree.getTreeItem(EMPLOYEE_PACKAGE);
        businessTreeItem.expand();
        personTreeItem.expand();

        bot.waitUntil(importBot.treeNodeAvailable(businessTreeItem, String.format(org.bonitasoft.studio.businessobject.i18n.Messages.conflictingWithSameObject, LOAN_BO)));
        bot.waitUntil(importBot.treeNodeAvailable(personTreeItem, String.format(org.bonitasoft.studio.businessobject.i18n.Messages.objectAlreadyExistsInAnotherPackage, PERSON_BO, "com.company.human")));

        importBot.doImport();

        BusinessObjectModelFileStore fileStore = (BusinessObjectModelFileStore) repositoryStore.getChild("bom.xml", true);
        assertThat(fileStore.getContent().getBusinessObjects()).extracting(BusinessObject::getQualifiedName)
                .containsExactlyInAnyOrder(toQualifiedName(BUSINESS_PACKAGE, LOAN_BO),
                        toQualifiedName(EMPLOYEE_PACKAGE, PERSON_BO));
    }

    private String toQualifiedName(String packageName, String name) {
        return String.format("%s.%s", packageName, name);
    }

    private void importInitialBdm() throws Exception {
        File zipFile = new File(getFileURL("/smartImport/model_initial.zip").getFile());
        File file = ZipUtil.unzip(zipFile)
                .resolve(BusinessObjectModelFileStore.BOM_FILENAME)
                .toFile();
        try (InputStream is = new FileInputStream(file)) {
            repositoryStore.importInputStream("bom.xml", is);
        }
    }

    private URL getFileURL(String filePath) throws Exception {
        return FileLocator.toFileURL(SmartImportBdmIT.class.getResource(filePath));
    }

}
