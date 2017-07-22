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
package org.bonitasoft.studio.tests.expressionEditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Romain Bioteau
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class TestPatternExpressionViewer implements SWTBotConstants {

    private static final String DATA_NAME_1 = "myData1";
    private static final String DATA_NAME_2 = "myData2";
    private static final String DATA_NAME_3 = "myData3";
    private static final String QUERY = "SELECT ${" + DATA_NAME_1 + "} from MyTable WHERE ${" + DATA_NAME_2 + "}='"
            + DATA_NAME_3 + "'";
    private static final String JDBC_DB_CONNECTOR_ID = "database-jdbc";
    private static final String DB_CATEGORY_ID = "database";
    private static final String GENERIC_DB_CATEGORY_ID = "generic";
    private static final String GROOVY_SQL_QUERY = "\"SELECT \"+" + DATA_NAME_1 + "+\" from MyTable WHERE \"+" + DATA_NAME_2
            + "+\"='\"+" + DATA_NAME_3
            + "+\"'\"";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @Test
    public void testPatternExpressionViewer() {
        SWTBotTestUtil.createNewDiagram(bot);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
        createData(DATA_NAME_1);
        createData(DATA_NAME_2);
        createData(DATA_NAME_3);
        final String connectorLabel = getConnectorLabel(JDBC_DB_CONNECTOR_ID);
        final String connectorVersion = getConnectorVersion(JDBC_DB_CONNECTOR_ID);
        final String[] dbCategoryLabel = getCategoryLabels(new String[] { DB_CATEGORY_ID, GENERIC_DB_CATEGORY_ID });
        addDBConnectorWithPatternExpression(connectorLabel, connectorVersion, dbCategoryLabel, "patternDBConnector");
        fillPatternExpression();
        checkPatternExpressionModel();
        addDBConnectorWithPatternExpression(connectorLabel, connectorVersion, dbCategoryLabel, "groovyDBConnector");
        fillGroovyExpression();
        checkGroovyExpressionModel();
    }

    private void checkGroovyExpressionModel() {
        final DiagramEditPart diagramEp = (DiagramEditPart) bot.gefEditor(bot.activeEditor().getTitle()).mainEditPart()
                .part();
        final MainProcess diagram = (MainProcess) diagramEp.resolveSemanticElement();
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(diagram, ProcessPackage.Literals.CONNECTOR);
        boolean found = false;
        for (final Connector c : connectors) {
            if (c.getName().equals("groovyDBConnector")) {
                final List<Expression> expressions = ModelHelper.getAllItemsOfType(c, ExpressionPackage.Literals.EXPRESSION);
                for (final Expression exp : expressions) {
                    if (exp.getType().equals(ExpressionConstants.SCRIPT_TYPE)) {
                        found = true;
                        assertEquals("Invalid expression content", GROOVY_SQL_QUERY, exp.getContent());
                        assertEquals("Invalid expression return type", String.class.getName(), exp.getReturnType());
                        assertEquals("Invalid number of dependencies", 3, exp.getReferencedElements().size());
                        assertEquals("Invalid interpreter", ExpressionConstants.GROOVY, exp.getInterpreter());
                    }
                }
            }
        }
        assertTrue("Groovy expression not found", found);
    }

    private void checkPatternExpressionModel() {
        final DiagramEditPart diagramEp = (DiagramEditPart) bot.gefEditor(bot.activeEditor().getTitle()).mainEditPart()
                .part();
        final MainProcess diagram = (MainProcess) diagramEp.resolveSemanticElement();
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(diagram, ProcessPackage.Literals.CONNECTOR);
        boolean found = false;
        for (final Connector c : connectors) {
            if (c.getName().equals("patternDBConnector")) {
                final List<Expression> expressions = ModelHelper.getAllItemsOfType(c, ExpressionPackage.Literals.EXPRESSION);
                for (final Expression exp : expressions) {
                    if (exp.getType().equals(ExpressionConstants.PATTERN_TYPE)) {
                        found = true;
                        assertEquals("Invalid expression content", QUERY, exp.getContent());
                        assertEquals("Invalid expression return type", String.class.getName(), exp.getReturnType());
                        assertEquals("Invalid number of dependencies", 2, exp.getReferencedElements().size());
                        final org.bonitasoft.engine.expression.Expression engineExp = EngineExpressionUtil
                                .createExpression(exp);
                        assertEquals("Invalid expression convertion from studio to engine",
                                "SELECT ${" + DATA_NAME_1 + "} from MyTable WHERE ${" + DATA_NAME_2
                                        + "}='" + DATA_NAME_3 + "'",
                                engineExp.getContent());
                    }
                }
            }
        }
        assertTrue("Pattern expression not found", found);
    }

    private void addDBConnectorWithPatternExpression(final String connectorLabel,
            final String connectorVersion, final String[] dbCategoryLabel, final String connectorName) {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION_CONNECTORS_IN);
        bot.button("Add...").click();
        bot.text().setText(connectorLabel);
        bot.table().select(0);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.textWithLabel("Name *").setText(connectorName);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.NEXT_LABEL).click();
    }

    private void fillPatternExpression() {
        bot.styledText().setText(QUERY);
        bot.sleep(1000);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        if (bot.button(IDialogConstants.NEXT_LABEL).isEnabled()) {
            bot.button(IDialogConstants.NEXT_LABEL).click();
        }
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.sleep(1000);
        bot.activeEditor().save();
    }

    private void fillGroovyExpression() {
        SWTBotShell activeShell = bot.activeShell();
        bot.link(Messages.switchEditor).click(Messages.switchEditor.replaceAll("<A>", "").replaceAll("</A>", ""));
        bot.button(IDialogConstants.YES_LABEL).click();
        activeShell.setFocus();
        bot.waitUntil(Conditions.shellIsActive(activeShell.getText()));
        editGroovyEditor(0, "Request", String.class.getName(), "sqlQuery", GROOVY_SQL_QUERY);
        bot.sleep(1000);
        bot.button(IDialogConstants.NEXT_LABEL).click();
        if (bot.button(IDialogConstants.NEXT_LABEL).isEnabled()) {
            bot.button(IDialogConstants.NEXT_LABEL).click();
        }
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.FINISH_LABEL)), 5000);
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.sleep(1000);
        bot.activeEditor().save();
    }

    private String[] getCategoryLabels(final String[] categoryIds) {
        final String[] res = new String[categoryIds.length];
        for (int i = 0; i < categoryIds.length; i++) {
            final String categoryIdToSearch = categoryIds[i];
            final ConnectorDefRepositoryStore defSore = RepositoryManager.getInstance()
                    .getRepositoryStore(ConnectorDefRepositoryStore.class);
            for (final Category c : defSore.getResourceProvider().getAllCategories()) {
                if (c.getId().equals(categoryIdToSearch)) {
                    res[i] = defSore.getResourceProvider().getCategoryLabel(c);
                    continue;
                }
            }
        }
        return res;
    }

    private String getConnectorVersion(final String connectorId) {
        final ConnectorDefRepositoryStore defSore = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        for (final ConnectorDefinition def : defSore.getDefinitions()) {
            if (def.getId().equals(connectorId)) {
                return def.getVersion();
            }
        }
        return null;
    }

    private String getConnectorLabel(final String connectorId) {
        final ConnectorDefRepositoryStore defSore = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        for (final ConnectorDefinition def : defSore.getDefinitions()) {
            if (def.getId().equals(connectorId)) {
                return defSore.getResourceProvider().getConnectorDefinitionLabel(def);
            }
        }
        return null;
    }

    private void editGroovyEditor(final int buttonIndex, final String inputName, final String inputtype,
            final String scriptName, final String groovyScript) {
        SWTBotShell activeShell = bot.activeShell();
        bot.toolbarButtonWithId(SWTBOT_ID_EDITBUTTON, buttonIndex).click();
        bot.table().select("Script");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel("Name")), 10000);
        bot.textWithLabel("Name").setText(scriptName);
        bot.styledText().setText(groovyScript);
        assertFalse("return type combobox should be disabled", bot.comboBoxWithLabel("Return type").isEnabled());
        assertEquals("return type should be" + inputtype, bot.comboBoxWithLabel("Return type").getText(), inputtype);
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        bot.waitUntil(Conditions.shellIsActive(activeShell.getText()));
        assertEquals("wrong value for " + inputName, bot.textWithId(SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).getText(),
                scriptName);
    }

    private void createData(final String dataName) {
        SWTBotTestUtil.selectTabbedPropertyView(bot, SWTBotTestUtil.VIEWS_PROPERTIES_POOL_DATA_VARIABLES);
        bot.buttonWithId(SWTBotConstants.SWTBOT_ID_ADD_PROCESS_DATA).click();
        assertFalse(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.textWithLabel("Name *").setText(dataName);
        assertTrue(IDialogConstants.FINISH_LABEL + " should be disabled", bot
                .button(IDialogConstants.FINISH_LABEL).isEnabled());
        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

}
