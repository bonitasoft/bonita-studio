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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.bdm.DefineBdmWizardBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
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

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> bdmStore;

    private File tmpFile;

    @Before
    public void setUp() throws Exception {
        BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
        String layout = "EN_US";
        if (Platform.getOS().equals(Platform.OS_MACOSX)) {
            layout = "MAC_" + layout;
        }
        SWTBotPreferences.KEYBOARD_LAYOUT = layout;
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
        BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);

        DefineBdmWizardBot bdmWizardBot = workBenchBot.defineBDM();
        bdmWizardBot.withPackage("org.model.test")
                .addBusinessObject("Employee")
                .addAttribute("Employee", "firstName", FieldType.STRING.name())
                .setAttributeLength("Employee", "firstName", "25")
                .setMandatory("Employee", "firstName")
                .addAttribute("Employee", "lastaNme", FieldType.STRING.name())
                .addAttribute("Employee", "birthDate", DateTypeLabels.DATE_ONLY)
                .addAttribute("Employee", "age", FieldType.INTEGER.name())
                .addAttribute("Employee", "married", FieldType.BOOLEAN.name())
                .addAttribute("Employee", "resume", FieldType.TEXT.name())
                .addAttribute("Employee", "salary", FieldType.DOUBLE.name())
                .addAttribute("Employee", "skills", FieldType.STRING.name())
                .setMultiple("Employee", "skills")
                .addAttribute("Employee", "manager", "Employee")
                .setRelationType("Employee", "manager", "Aggregation");

        bdmWizardBot.addConstraint("Employee", "FIRSTLASTNAMEUNIQUE", "firstName -- STRING", "lastaNme -- STRING");
        bdmWizardBot.addIndex("Employee", "NAMEINDEX", "firstName -- STRING", "lastaNme -- STRING");

        bdmWizardBot.deploy();

        bdmWizardBot = workBenchBot.defineBDM();
        bdmWizardBot.renameAttribute("Employee", "lastaNme", "lastName");

        SWTBotShell activeShell = bot.activeShell();
        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.modelValidationFailedTitle));
        bot.button(IDialogConstants.CANCEL_LABEL).click();
        activeShell.setFocus();
        bdmWizardBot.editConstraint("Employee", "FIRSTLASTNAMEUNIQUE", "firstName -- STRING", "lastName -- STRING");
        bdmWizardBot.editIndex("Employee", "NAMEINDEX", "lastName -- STRING");

        final Map<String, String> queryParam = new HashMap<>();
        queryParam.put("maxSalary", Double.class.getName());
        bdmWizardBot.addCustomQuery("Employee",
                "findByMaxSalary",
                "SELECT e FROM Employee e WHERE e.salary < :maxSalary",
                queryParam,
                "Multiple (java.util.List)",
                0);

        bot.button(IDialogConstants.FINISH_LABEL).click();
        bot.button(IDialogConstants.OK_LABEL).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.bdmDeployedTitle), 30000);
        bot.button(IDialogConstants.OK_LABEL).click();

        validateBDMContent();
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
