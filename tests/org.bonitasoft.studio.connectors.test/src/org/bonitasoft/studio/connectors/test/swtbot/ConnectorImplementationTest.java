/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.test.swtbot;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.ClassGenerator;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aur�lie Zara
 * 
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ConnectorImplementationTest extends SWTBotGefTestCase {

    @Before
    public void createDefinition() {
        final String id = "testEdit";
        final String version = "1.0.0";
        ConnectorDefinition connector = ConnectorDefinitionFactory.eINSTANCE
                .createConnectorDefinition();
        connector.setId(id);
        connector.setVersion(version);
        String defId = NamingUtils.toConnectorDefinitionFilename(
                connector.getId(), connector.getVersion(), false);
        String defFileName = defId + "." + "def";
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ConnectorDefRepositoryStore.class);
        IRepositoryFileStore fileStore = store
                .createRepositoryFileStore(defFileName);
        fileStore.save(connector);
    }

    @Test
    public void createConnectorImplementation() throws Exception {
        // TODO add dependancies test
        final String id = "testImplementation";
        final String definition = "testEdit (1.0.0)";
        final String className = "MyConnectorImpl";
        final String packageName = "org.bonita.connector.test";
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);
        assertFalse("Finish button should be disabled",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel("Implementation id *").setText(id);
        bot.comboBoxWithLabel("Definition *").setSelection(definition);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        ConnectorImplRepositoryStore store = (ConnectorImplRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ConnectorImplRepositoryStore.class);
        ConnectorImplementation connectorImpl = store.getImplementation(id,
                "1.0.0");
        assertNotNull("connectorImplementation" + id + " was not created",
                connectorImpl);
        assertEquals("connectorImplementation id should be " + id,
                connectorImpl.getImplementationId(), id);
    }

    @Test
    public void testPackageSyntax() throws Exception {
        final String id = "testImplementation";
        final String definition = "testEdit (1.0.0)";
        final String className = "MyConnectorImpl";
        final String packageName = "org.bonita.connector.";
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);
        bot.textWithLabel("Implementation id *").setText(id);
        bot.comboBoxWithLabel("Definition *").setSelection(definition);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        assertFalse("finish button should be desabled",
                bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();

    }

    @Test
    public void testConnectorImplementationEdition() {
        final int nbEditorsBefore = bot.editors().size();
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);
        final String id = "testImplementation2";
        final String id2 = "textImplementation3";
        final String definition = "testEdit (1.0.0)";
        final String className = "MyConnectorImpl2";
        final String packageName = "org.bonita.connector.test";
        bot.textWithLabel("Implementation id *").setText(id);
        bot.comboBoxWithLabel("Definition *").setSelection(definition);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(new ICondition() {

            public boolean test() throws Exception {
                return nbEditorsBefore +1 == bot.editors().size();
            }

            public void init(SWTBot bot) {
            }

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

    }

    @Test
    public void testConnectorImplEditionWithSameDefandClassName() {
        final int nbEditorsBefore = bot.editors().size();
        final String id = "testImplementation4";
        final String id2 = "testImplementation5";
        final String definition = "testEdit (1.0.0)";
        final String className = "MyactorFilterImpl";
        final String packageName = "org.bonita.actorFilter.test";
        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);
        bot.textWithLabel("Implementation id *").setText(id);
        bot.comboBoxWithLabel("Definition *").setSelection(definition);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(new ICondition() {

            public boolean test() throws Exception {
                return nbEditorsBefore + 1 == bot.editors().size();
            }

            public void init(SWTBot bot) {
            }

            public String getFailureMessage() {
                return "Editor for implementation has not been opened.";
            }
        }, 30000);

        SWTBotConnectorTestUtil.activateConnectorImplementationShell(bot);
        bot.textWithLabel("Implementation id *").setText(id2);
        bot.comboBoxWithLabel("Definition *").setSelection(definition);
        bot.textWithLabel("Class name *").setText(className);
        bot.textWithLabel("Package *").setText(packageName);
        bot.waitUntil(new ICondition() {

            public boolean test() throws Exception {
                return !bot.button(IDialogConstants.FINISH_LABEL).isEnabled();
            }

            public void init(SWTBot bot) {

            }

            public String getFailureMessage() {
                return "Finish button should be disabled";
            }

        });
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        removeImplementation(id);
    }

    private void removeImplementation(String implId){
        ConnectorImplRepositoryStore store = (ConnectorImplRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
        ConnectorImplementation impl=store.getImplementation(implId,"1.0.0");
        String fileName = impl.eResource().getURI().lastSegment() ;
        IRepositoryFileStore file = store.getChild(fileName) ;
        if(FileActionDialog.confirmDeletionQuestion(fileName)){
            file.delete() ;
            String className = impl.getImplementationClassname() ;
            IRepositoryFileStore sourceFile = store.getChild(className) ;
            String abstarctClassName = ClassGenerator.getAbstractClassName(className) ;
            IRepositoryFileStore abstractFile =store.getChild(abstarctClassName) ;
            if(sourceFile != null && FileActionDialog.confirmDeletionQuestion(sourceFile.getName())){
                sourceFile.delete() ;
                if(abstractFile != null ){
                    abstractFile.delete() ;
                }
            }
        }
    }



    @After
    public void removeDefinitionAndCloseAllEditors(){
        ConnectorDefRepositoryStore store = (ConnectorDefRepositoryStore) RepositoryManager
                .getInstance().getRepositoryStore(
                        ConnectorDefRepositoryStore.class);
        IRepositoryFileStore file = store.getChild(NamingUtils.toConnectorDefinitionFilename("testEdit", "1.0.0", true));
        assertNotNull(file);
        file.delete();
        bot.closeAllEditors();
        bot.waitUntil(new ICondition(){

			public boolean test() throws Exception {
				
				return bot.editors().isEmpty();
			}

			public void init(SWTBot bot) {
				// TODO Auto-generated method stub
				
			}

			public String getFailureMessage() {
				// TODO Auto-generated method stub
				return null;
			}
        	
        });
    }


}
