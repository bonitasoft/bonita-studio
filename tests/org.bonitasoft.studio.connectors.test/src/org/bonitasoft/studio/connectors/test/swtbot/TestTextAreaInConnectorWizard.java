package org.bonitasoft.studio.connectors.test.swtbot;

import java.util.List;

import org.junit.Assert;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
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

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestTextAreaInConnectorWizard extends SWTBotGefTestCase {

    private final String connectorId = "textAreaInWizardTest";
    final String widgetId = "textWidget";
    final String pageId = "connectorDefPageId";
    final String textArea = "Text area editor";
    final String name = "testTextAreaConnector";
    final String version = "1.0.0";

    @Before
    public void disablePopup() {
        FileActionDialog.setDisablePopup(true);
    }

    @Test
    public void testTextAreaInConnectorConfigurationWizard() {
        createConnector(connectorId);
        SWTBotTestUtil.createNewDiagram(bot);

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addNewData(bot, "name", "Text", false, null);
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addNewData(bot, "age", "Integer", false, "5");
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA).click();
        SWTBotTestUtil.addNewData(bot, "mariage", "Boolean", false, null);

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION_CONNECTORS_IN);
        bot.button("Add...").click();
        Assert.assertFalse(IDialogConstants.NEXT_LABEL + " should be disabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.text().setText(connectorId);
        bot.table().select(connectorId);
        Assert.assertTrue(IDialogConstants.NEXT_LABEL + " should be disabled", bot
                .button(IDialogConstants.NEXT_LABEL).isEnabled());
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.NEXT_LABEL).click();
        Assert.assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel("Name *").setText(name);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        final String content = "hello name,\nyou are age and you are mariage";
        bot.styledTextWithLabel("text").setText(content);
        bot.sleep(2000); // Due to delayed observable on databinding
        bot.button(IDialogConstants.NEXT_LABEL).click();

        bot.button(IDialogConstants.FINISH_LABEL).click();

        final IGraphicalEditPart mainPart = (IGraphicalEditPart) bot.gefEditor(bot.activeEditor().getTitle()).mainEditPart().part();
        final MainProcess diagram = (MainProcess) mainPart.resolveSemanticElement();
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(diagram, ProcessPackage.Literals.CONNECTOR);
        assertEquals("Connector is missing", 1, connectors.size());
        final Connector connector = connectors.get(0);
        final ConnectorConfiguration conf = connector.getConfiguration();
        final ConnectorParameter param = conf.getParameters().get(0);
        assertEquals("Input1", param.getKey());
        assertNotNull(param.getExpression());
        final Expression exp = (Expression) param.getExpression();
        assertEquals("Invalid expression content", content, exp.getContent());
        assertEquals("Invalid expression type", ExpressionConstants.PATTERN_TYPE, exp.getType());
        assertEquals("Invalid expression dependencies count", 3, exp.getReferencedElements().size());

        boolean containsAge = false;
        boolean containsName = false;
        boolean containsMariage = false;
        for (final EObject dep : exp.getReferencedElements()) {
            if (((Data) dep).getName().equals("age")) {
                containsAge = true;
            }
            if (((Data) dep).getName().equals("mariage")) {
                containsMariage = true;
            }
            if (((Data) dep).getName().equals("name")) {
                containsName = true;
            }
        }

        assertTrue(containsMariage);
        assertTrue(containsName);
        assertTrue(containsAge);

    }

    public void createConnector(final String connectorDefinitionId) {

        SWTBotConnectorTestUtil.activateConnectorDefinitionShell(bot);
        bot.textWithLabel("Definition id *").setText(connectorDefinitionId);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.textWithLabel("Page id *").setText(pageId);
        bot.button("Add...").click();
        bot.textWithLabel("Widget id*").setText(widgetId);
        bot.textWithLabel("Display name").setText("text");
        bot.waitUntil(new ICondition() {

            public boolean test() throws Exception {
                return containsWidget(textArea);
            }

            public void init(final SWTBot bot) {
            }

            public String getFailureMessage() {
                return "combobox doesn't contain TextArea";
            }

        }, 5000);
        if (containsWidget(textArea)) {
            bot.comboBoxWithLabel("Widget type").setSelection(textArea);
            bot.comboBoxWithLabel("Input *").setSelection(0);
            bot.button(IDialogConstants.OK_LABEL).click();
        } else {
            bot.button(IDialogConstants.CANCEL_LABEL).click();
        }
        bot.button("Apply").click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button("Add...").click();
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    @Override
    @After
    public void tearDown() {
        bot.saveAllEditors();
        bot.closeAllEditors();
        if (Messages.addWidget.equals(bot.activeShell().getText())) {
            if (!bot.button(IDialogConstants.OK_LABEL).isEnabled()) {
                bot.button(IDialogConstants.CANCEL_LABEL).click();
            } else {
                bot.button(IDialogConstants.OK_LABEL).click();
            }
        }
        final String title = "New definition...";
        if (title.equals(bot.activeShell().getText())) {
            bot.button(IDialogConstants.CANCEL_LABEL).click();
            bot.button(IDialogConstants.CANCEL_LABEL).click();
        }
    }

    private boolean containsWidget(final String widgetName) {
        final String[] widgets = bot.comboBoxWithLabel("Widget type").items();
        for (final String widget : widgets) {
            if (widget.equals(widgetName)) {
                return true;
            }
        }
        return false;
    }

}
