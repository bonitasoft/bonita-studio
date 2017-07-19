/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 */
package org.bonitasoft.studio.tests.businessobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.FieldTypeEditingSupport;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class CreateDeployExportBusinessObjectIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    private Keyboard keyboard;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private BusinessObjectModelRepositoryStore bdmStore;

    private File tmpFile;

    @Before
    public void setUp() throws Exception {
        BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
        String layout = "EN_US";
        if (Platform.getOS().equals(Platform.OS_MACOSX)) {
            layout = "MAC_" + layout;
        }
        SWTBotPreferences.KEYBOARD_LAYOUT = layout;
        keyboard = KeyboardFactory.getSWTKeyboard();
        tmpFile = temporaryFolder.newFile("bdm.zip");

        bdmStore = RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        final BusinessObjectModelFileStore businessObjectModelFileStore = bdmStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME);
        if (businessObjectModelFileStore != null) {
            businessObjectModelFileStore.delete();
        }
    }

    @After
    public void clean() {
        if (tmpFile != null) {
            tmpFile.delete();
        }
    }

    @Test
    public void shouldCreateAndPublishABusinessObject() throws Exception {
        // Create a business object
        bot.menu("Development").menu("Business Data Model").menu("Manage...").click();
        bot.waitUntil(Conditions.shellIsActive("Manage Business Data Model"));

        bot.textWithLabel("Package").setText("org.model.test");

        // Add Employee Business Object
        final String listBOGroupTitle = "List of Business Objects";
        bot.buttonInGroup("Add", listBOGroupTitle).click();
        SWTBotTable table = bot.tableInGroup(listBOGroupTitle);
        table.click(0, 0);
        bot.text("BusinessObject1").typeText("Employee");
        keyboard.pressShortcut(Keystrokes.CR);

        // Add attributes
        addAttribute("Employee", "firstName", FieldType.STRING.name(), 0);
        setAttributeLength("Employee", "firstName", "25");
        setMandatory("Employee", "firstName");

        addAttribute("Employee", "lastaNme", FieldType.STRING.name(), 1);
        addAttribute("Employee", "birthDate", DateTypeLabels.DATE_ONLY, 2);
        addAttribute("Employee", "age", FieldType.INTEGER.name(), 3);
        addAttribute("Employee", "married", FieldType.BOOLEAN.name(), 4);
        addAttribute("Employee", "resume", FieldType.TEXT.name(), 5);
        addAttribute("Employee", "salary", FieldType.DOUBLE.name(), 6);
        addAttribute("Employee", "skills", FieldType.STRING.name(), 7);
        setMultiple("Employee", "skills");

        addAttribute("Employee", "manager", "Employee", 8);
        setRelationType("Employee", "manager", "Aggregation");

        // Add constraint
        addConstraint("Employee", "FIRSTLASTNAMEUNIQUE", new String[] { "firstName -- STRING", "lastaNme -- STRING" }, 0);

        // Add index
        addIndex("Employee", "NAMEINDEX", new String[] { "firstName -- STRING", "lastaNme -- STRING" }, 0);

        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.bdmDeployedTitle), 30000);
        bot.button(IDialogConstants.OK_LABEL).click();

        // Edit business object
        bot.menu("Development").menu("Business Data Model").menu("Manage...").click();
        table = bot.tableInGroup(listBOGroupTitle);

        final SWTBotTable attributeTable = bot.tableInGroup("Employee");
        attributeTable.click(1, 0);
        bot.textInGroup("lastaNme", "Employee").selectAll().typeText("lastName");
        keyboard.pressShortcut(Keystrokes.CR);
        activeShell = bot.activeShell();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive("Validation failed"));
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
        editConstraint("Employee", new String[] { "firstName -- STRING", "lastName -- STRING" }, 0);
        editIndex("Employee", "NAMEINDEX", new String[] { "lastName -- STRING" }, 0);

        // Add custom query
        final Map<String, String> queryParam = new HashMap<>();
        queryParam.put("maxSalary", Double.class.getName());
        addCustomQuery("Employee", "findByMaxSalary", "SELECT e FROM Employee e WHERE e.salary < :maxSalary", queryParam,
                "Multiple (java.util.List)", 0);

        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.bdmDeployedTitle), 30000);
        bot.button(IDialogConstants.OK_LABEL).click();

        // Validate model content
        validateBDMContent();

        // Export business data model
        exportBDM();

    }

    protected void validateBDMContent() {
        final BusinessObjectModelFileStore fStore = bdmStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME);
        assertThat(fStore).isNotNull();
        final BusinessObjectModel businessObjectModel = fStore.getContent();
        assertThat(businessObjectModel).isNotNull();
        assertThat(businessObjectModel.getBusinessObjects()).extracting("qualifiedName")
                .containsOnly("org.model.test.Employee");
        final BusinessObject employeeBusinessObject = businessObjectModel.getBusinessObjects().get(0);
        assertThat(employeeBusinessObject.getFields())
                .extracting("name", "type")
                .contains(tuple("firstName", FieldType.STRING),
                        tuple("lastName", FieldType.STRING),
                        tuple("birthDate", FieldType.LOCALDATE),
                        tuple("age", FieldType.INTEGER),
                        tuple("married", FieldType.BOOLEAN),
                        tuple("resume", FieldType.TEXT),
                        tuple("salary", FieldType.DOUBLE),
                        tuple("skills", FieldType.STRING));
        assertThat(employeeBusinessObject.getFields())
                .extractingResultOf("isNullable", Boolean.class)
                .containsExactly(false,
                        true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        true,
                        true);
        assertThat(employeeBusinessObject.getFields())
                .extractingResultOf("isCollection", Boolean.class)
                .containsExactly(false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        true,
                        false);
        assertThat(employeeBusinessObject.getFields())
                .extracting("name", "type")
                .contains(tuple("manager", Type.AGGREGATION));
        assertThat(((SimpleField) employeeBusinessObject.getFields().get(0)).getLength()).isEqualTo(25);

        assertThat(employeeBusinessObject.getUniqueConstraints()).hasSize(1);
        assertThat(employeeBusinessObject.getUniqueConstraints().get(0).getName()).isEqualTo("FIRSTLASTNAMEUNIQUE");
        assertThat(employeeBusinessObject.getUniqueConstraints().get(0).getFieldNames()).contains("firstName", "lastName");

        assertThat(employeeBusinessObject.getIndexes()).extracting("name", "fieldNames")
                .containsExactly(tuple("NAMEINDEX", Arrays.asList("lastName", "firstName")));

        assertThat(employeeBusinessObject.getQueries()).extracting("name", "content", "returnType")
                .containsExactly(tuple("findByMaxSalary", "SELECT e FROM Employee e WHERE e.salary < :maxSalary",
                        List.class.getName()));
        assertThat(employeeBusinessObject.getQueries().get(0).getQueryParameters()).extracting("name", "className")
                .containsExactly(tuple("maxSalary", Double.class.getName()));
    }

    protected void addCustomQuery(final String boName, final String queryName, final String content,
            final Map<String, String> queryParam,
            final String returnType, final int queryIndex) {
        bot.tabItem("Queries").activate();
        bot.radio("Custom").click();
        bot.buttonInGroup("Add", boName).click();
        final SWTBotTable table = bot.tableInGroup(boName);

        table.click(queryIndex, 1);
        bot.textInGroup("query1", boName).typeText(queryName);

        table.click(queryIndex, 2);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("...").click();
        bot.waitUntil(Conditions.shellIsActive("Create query"));
        bot.styledText().setText(content);

        final SWTBotTable paramTableBot = bot.table();
        final int rowCount = paramTableBot.rowCount();
        final List<String> items = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            items.add(paramTableBot.getTableItem(i).getText());
        }
        if (!items.isEmpty()) {
            paramTableBot.select(items.toArray(new String[items.size()]));
        }
        bot.button("Delete").click();

        int index = 0;
        for (final Entry<String, String> paramEntry : queryParam.entrySet()) {
            bot.button("Add").click();
            paramTableBot.click(index, 0);
            bot.text("param1").typeText(paramEntry.getKey());

            paramTableBot.click(index, 1);
            bot.ccomboBox(String.class.getName()).setSelection(paramEntry.getValue());
            keyboard.pressShortcut(Keystrokes.CR);
            index++;
        }

        bot.comboBoxWithLabel("Result type").setSelection(returnType);
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
    }

    protected void setMandatory(final String boName, final String attributeName) {
        bot.tabItem("Attributes").activate();
        final SWTBotTable attributeTable = bot.tableInGroup(boName);
        final int attributeIndex = attributeTable.indexOf(attributeName, 0);
        attributeTable.click(attributeIndex, 3);
    }

    protected void setMultiple(final String boName, final String attributeName) {
        bot.tabItem("Attributes").activate();
        final SWTBotTable attributeTable = bot.tableInGroup(boName);
        final int attributeIndex = attributeTable.indexOf(attributeName, 0);
        attributeTable.click(attributeIndex, 2);
    }

    protected void setAttributeLength(final String boName, final String attributeName, final String length) {
        bot.tabItem("Attributes").activate();
        final SWTBotTable attributeTable = bot.tableInGroup(boName);
        final int attributeIndex = attributeTable.indexOf(attributeName, 0);
        attributeTable.select(attributeIndex);
        bot.ccomboBoxInGroup("Details for " + attributeName).setText(length);
    }

    protected void setRelationType(final String boName, final String attributeName, final String relationType) {
        bot.tabItem("Attributes").activate();
        final SWTBotTable attributeTable = bot.tableInGroup(boName);
        final int attributeIndex = attributeTable.indexOf(attributeName, 0);
        attributeTable.select(attributeIndex);
        bot.comboBoxInGroup("Details for " + attributeName).setSelection(relationType);
    }

    protected void addAttribute(final String boName, final String attributeName, final String type,
            final int attributeIndex) {
        SWTBotShell activeShell = bot.activeShell();
        bot.tabItem("Attributes").activate();
        bot.buttonInGroup("Add", boName).click();
        final SWTBotTable attributeTable = bot.tableInGroup(boName);

        attributeTable.click(attributeIndex, 0);
        bot.textInGroup("attribute1", boName).typeText(attributeName);
        keyboard.pressShortcut(Keystrokes.CR);
        attributeTable.click(attributeIndex, 1);

        SWTBot activeBot = activeShell.bot();
        SWTBotCCombo ccomboBoxInGroup = activeBot.ccomboBoxWithId(FieldTypeEditingSupport.TYPE_COMBO_EDITOR_ID);
        activeBot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return Stream.of(ccomboBoxInGroup.items()).anyMatch(type::equals);
            }

            @Override
            public String getFailureMessage() {
                return String.format("No attribute type '%s' found in combo", type);
            }
        });
        ccomboBoxInGroup.setSelection(type);
    }

    protected void addConstraint(final String boName, final String constraintName, final String[] selectFields,
            final int constraintIndex) {
        bot.tabItem("Unique constraints").activate();
        bot.buttonInGroup("Add", boName).click();
        final SWTBotTable table = bot.tableInGroup(boName);

        table.click(constraintIndex, 0);
        bot.textInGroup("UNIQUE_CONSTRAINT_1", boName).typeText(constraintName);

        table.click(constraintIndex, 1);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("...").click();
        bot.waitUntil(Conditions.shellIsActive("Select attributes"));
        for (final String f : selectFields) {
            bot.table().getTableItem(f).check();
        }
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
    }

    protected void editConstraint(final String boName, final String[] selectFields, final int constraintIndex) {
        bot.tabItem("Unique constraints").activate();
        final SWTBotTable table = bot.tableInGroup(boName);
        table.click(constraintIndex, 0);
        table.click(constraintIndex, 1);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("...").click();
        bot.waitUntil(Conditions.shellIsActive("Select attributes"));
        for (final String f : selectFields) {
            bot.table().getTableItem(f).check();
        }
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
    }

    protected void addIndex(final String boName, final String indexName, final String[] selectFields, final int indexIndex) {
        bot.tabItem("Indexes").activate();
        bot.buttonInGroup("Add", boName).click();
        final SWTBotTable table = bot.tableInGroup(boName);

        table.click(indexIndex, 0);
        bot.textInGroup("INDEX_1", boName).typeText(indexName);

        table.click(indexIndex, 1);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("...").click();
        bot.waitUntil(Conditions.shellIsActive("Select attributes for " + indexName));
        bot.tableWithLabel("Available attributes").select(selectFields);
        bot.button("Add").click();
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
    }

    protected void editIndex(final String boName, final String indexName, final String[] selectFields,
            final int indexIndex) {
        bot.tabItem("Indexes").activate();
        final SWTBotTable table = bot.tableInGroup(boName);
        table.click(indexIndex, 1);
        SWTBotShell activeShell = bot.activeShell();
        bot.button("...").click();
        bot.waitUntil(Conditions.shellIsActive("Select attributes for " + indexName));
        bot.tableWithLabel("Available attributes").select(selectFields);
        bot.button("Add").click();
        bot.tableWithLabel("Indexed attributes").select(selectFields);
        bot.button("Up").click();
        bot.button(IDialogConstants.OK_LABEL).click();
        activeShell.setFocus();
    }

    protected void exportBDM() {
        bot.menu("Development").menu("Business Data Model").menu("Export...").click();
        assertTrue("Export button should be enabled", bot.button("Export").isEnabled());

        bot.text().setText("/User/FakePath/");
        assertFalse("Export button should be disabled", bot.button("Export").isEnabled());

        bot.text().setText(tmpFile.getParentFile().getAbsolutePath());
        assertTrue("Export button should be enabled", bot.button("Export").isEnabled());

        bot.button("Export").click();

        bot.waitUntil(Conditions.shellIsActive("Export completed"), 30000);
        bot.button(IDialogConstants.OK_LABEL).click();

        assertThat(tmpFile).exists();
        assertThat(tmpFile.length()).isGreaterThan(0);
        assertThat(tmpFile.getName()).endsWith(".zip").contains("bdm");
    }

}
