/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.connectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.ClassGenerator;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.SWTBotConnectorTestUtil;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ConnectorImplementationTest implements SWTBotConstants {

    private SWTGefBot bot = new SWTGefBot();

    @Before
    public void createDefinition() {
        final String id = "testEdit";
        final String version = "1.0.0";
        ConnectorDefinition connector = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        connector.setId(id);
        connector.setVersion(version);
        String defId = NamingUtils.toConnectorDefinitionFilename(connector.getId(), connector.getVersion(), false);
        String defFileName = defId + "." + "def";
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        IRepositoryFileStore fileStore = store.createRepositoryFileStore(defFileName);
        fileStore.save(connector);
    }

    @Test
    public void createConnectorImplementation() throws Exception {
        final int nbEditorsBefore = bot.editors().size();
        final String id = "testImplementation";
        final String definition = "testEdit";
        final String className = "MyConnectorImpl";
        final String packageName = "org.bonita.connector.test";
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);

        assertFalse("Finish button should be disabled", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());

        selectDefinition(definition);

        bot.textWithLabel("Implementation id *").setText(id);

        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorImplRepositoryStore store = (ConnectorImplRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
        ConnectorImplementation connectorImpl = store.getImplementation(id, "1.0.0");
        assertNotNull("connectorImplementation" + id + " was not created", connectorImpl);
        assertEquals("connectorImplementation id should be " + id, connectorImpl.getImplementationId(), id);

        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore + 1 == bot.editors().size();
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Editor for implementation has not been opened.";
            }
        }, 30000);
        int length = bot.activeEditor().toTextEditor().getText().length();
        Job.getJobManager().join(ResourcesPlugin.FAMILY_MANUAL_BUILD, Repository.NULL_PROGRESS_MONITOR);
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, Repository.NULL_PROGRESS_MONITOR);
        bot.sleep(1000);
        StyleRange[] styles = bot.activeEditor().toTextEditor().getStyles(0, 0, length);
        containsError(styles);
        removeImplementation(id);
    }

    @Test
    public void testPackageSyntax() throws Exception {
        final String id = "testImplementation";
        final String definition = "testEdit";
        final String className = "MyConnectorImpl";
        final String packageName = "org.bonita.connector.";
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);

        selectDefinition(definition);

        bot.textWithLabel("Implementation id *").setText(id);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        assertFalse("finish button should be desabled",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testClassNameSyntax() throws Exception {
        final String id = "testImplementation";
        final String definition = "testEdit";
        final String packageName = "org.bonita.connector";
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);
        selectDefinition(definition);
        bot.textWithLabel(Messages.implementationId + " *").setText(id);
        bot.textWithLabel("Package *").setText(packageName);
        bot.textWithLabel(Messages.classNameLabel + " *").setText("MyConnector.Implsfgsdf");
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return !bot.button(IDialogConstants.FINISH_LABEL).isEnabled();
            }

            @Override
            public String getFailureMessage() {
                return "finish button should be disabled";
            }
        });
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    public void testConnectorImplementationEdition() {
        final int nbEditorsBefore = bot.editors().size();
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);
        final String id = "testImplementation2";
        final String id2 = "textImplementation3";
        final String definition = "testEdit";
        final String className = "MyConnectorImpl2";
        final String packageName = "org.bonita.connector.test";

        selectDefinition(definition);

        bot.textWithLabel("Implementation id *").setText(id);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore + 1 == bot.editors().size();
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Editor for implementation has not been opened.";
            }
        }, 30000);
        bot.menu("Development").menu("Connectors").menu("Edit implementation...").click();
        bot.table().unselect();
        assertFalse("Edit button should be disabled",
                bot.button(Messages.Edit).isEnabled());
        bot.table().select(id + " (1.0.0) -- " + packageName + "." + className);
        bot.button(Messages.Edit).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        assertEquals("Implementation id should be " + id,
                bot.textWithLabel("Implementation id *").getText(), id);
        assertEquals("Version should be 1.0.0", bot.textWithLabel("Version *")
                .getText(), "1.0.0");
        assertEquals("Class name should be " + className,
                bot.textWithLabel("Class name *").getText(), className);
        assertEquals("Package should be " + packageName,
                bot.textWithLabel("Package *").getText(), packageName);
        bot.textWithLabel("Implementation id *").setText(id2);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorImplRepositoryStore store = (ConnectorImplRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ConnectorImplRepositoryStore.class);
        ConnectorImplementation connectorImpl = store.getImplementation(id,
                "1.0.0");
        assertNull("connectorImplementation with id" + id
                + "should not exist anymore", connectorImpl);
        connectorImpl = store.getImplementation(id2, "1.0.0");
        assertNotNull("connectorImplementation id was not edited correctly",
                connectorImpl);
        assertEquals("connector id should be " + id2,
                connectorImpl.getImplementationId(), id2);
        removeImplementation(id2);
    }

    protected void selectDefinition(final String definition) {
        bot.treeWithId(SWTBOT_ID_EXPLORER_LEFT_TREE).select(0);
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return bot.tableWithId(SWTBOT_ID_EXPLORER_RIGHT_TABLE).rowCount() > 0;
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "No items found in right table of connector explorer";
            }
        });
        bot.tableWithId(SWTBOT_ID_EXPLORER_RIGHT_TABLE).select(definition);
        bot.button(IDialogConstants.NEXT_LABEL).click();
    }

    @Test
    public void testConnectorImplEditionWithSameDefandClassName() {
        final int nbEditorsBefore = bot.editors().size();
        final String id = "testImplementation4";
        final String id2 = "testImplementation5";
        final String definition = "testEdit";
        final String className = "MyactorFilterImpl";
        final String packageName = "org.bonita.actorFilter.test";
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);

        selectDefinition(definition);

        bot.textWithLabel("Implementation id *").setText(id);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return nbEditorsBefore + 1 == bot.editors().size();
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Editor for implementation has not been opened.";
            }
        }, 30000);

        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);
        selectDefinition(definition);
        bot.textWithLabel("Implementation id *").setText(id2);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return !bot.button(IDialogConstants.FINISH_LABEL).isEnabled();
            }

            @Override
            public void init(SWTBot bot) {

            }

            @Override
            public String getFailureMessage() {
                return "Finish button should be disabled";
            }

        });
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        removeImplementation(id);
    }

    private void removeImplementation(String implId) {
        ConnectorImplRepositoryStore store = (ConnectorImplRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
        ConnectorImplementation impl = store.getImplementation(implId, "1.0.0");
        String fileName = impl.eResource().getURI().lastSegment();
        IRepositoryFileStore file = store.getChild(fileName, true);
        if (FileActionDialog.confirmDeletionQuestion(fileName)) {
            file.delete();
            String className = impl.getImplementationClassname();
            IRepositoryFileStore sourceFile = store.getChild(className, true);
            String abstarctClassName = ClassGenerator.getAbstractClassName(className);
            IRepositoryFileStore abstractFile = store.getChild(abstarctClassName, true);
            if (sourceFile != null && FileActionDialog.confirmDeletionQuestion(sourceFile.getName())) {
                sourceFile.delete();
                if (abstractFile != null) {
                    abstractFile.delete();
                }
            }
        }
    }

    @After
    public void removeDefinitionAndCloseAllEditors() {
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ConnectorDefRepositoryStore.class);
        IRepositoryFileStore file = store.getChild(NamingUtils.toConnectorDefinitionFilename("testEdit", "1.0.0", true), true);
        assertNotNull(file);
        file.delete();
        bot.closeAllEditors();
    }

    private void containsError(StyleRange[] styles) {
        for (StyleRange style : styles) {
            assertFalse("connector impl contains errors", style.underline);
        }
    }
}
