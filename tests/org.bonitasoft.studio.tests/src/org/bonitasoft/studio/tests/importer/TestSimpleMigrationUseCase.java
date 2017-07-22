/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.importer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.form.AbstractTable;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.kpi.AbstractKPIBinding;
import org.bonitasoft.studio.model.kpi.KpiPackage;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractTimerEvent;
import org.bonitasoft.studio.model.process.ActorFilter;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.CorrelationTypeActive;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorFileStore;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class TestSimpleMigrationUseCase {

    private static boolean disablepopup;

    @BeforeClass
    public static void disablePopup() {
        disablepopup = FileActionDialog.getDisablePopup();
        FileActionDialog.setDisablePopup(true);
    }

    @AfterClass
    public static void resetdisablePopup() {
        FileActionDialog.setDisablePopup(disablepopup);
    }

    @Test
    public void testDatatypesMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("AllDatatypes--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);

        final List<DataType> datatypes = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.DATA_TYPE);
        assertEquals("Invalid number of datatypes", 10, datatypes.size()); //9 provided + 1 enum
        final DataTypeSwitch typesSwitch = new DataTypeSwitch(datatypes);
        typesSwitch.testDatatypesConsistency();
        resource.unload();
    }

    @Test
    public void testCallActivityMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("Simple_Call_Activity--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        assertEquals("Call Activity is missing", 1,
                ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CALL_ACTIVITY).size());
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testDataDefaultValueMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("DefaultValueMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(mainProc, ExpressionPackage.Literals.EXPRESSION);
        int nbVariableExpression = 0;
        int nbScriptExpression = 0;
        int nbConstantExpression = 0;
        for (final Expression exp : expressions) {
            if (ExpressionConstants.VARIABLE_TYPE.equals(exp.getType()) && !ModelHelper.isAnExpressionCopy(exp)) {
                nbVariableExpression++;
            }
            if (ExpressionConstants.SCRIPT_TYPE.equals(exp.getType())) {
                nbScriptExpression++;
            }
            if (ExpressionConstants.CONSTANT_TYPE.equals(exp.getType()) && exp.getContent() != null
                    && !exp.getContent().isEmpty()) {
                nbConstantExpression++;
            }
        }
        assertEquals("Invalid number of variable expression", 3, nbVariableExpression);
        assertEquals("Invalid number of script expression", 3, nbScriptExpression);
        assertEquals("Invalid number of constant expression", 3, nbConstantExpression);
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testSequenceFlowConditionMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("SequenceFlowCondition_MigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(mainProc, ExpressionPackage.Literals.EXPRESSION);
        int nbVariableExpression = 0;
        int nbScriptExpression = 0;
        for (final Expression exp : expressions) {
            if (ExpressionConstants.VARIABLE_TYPE.equals(exp.getType())) {
                nbVariableExpression++;
            }
            if (ExpressionConstants.SCRIPT_TYPE.equals(exp.getType())) {
                nbScriptExpression++;
            }
        }
        assertEquals("Invalid number of variable expression", 1, nbVariableExpression);
        assertEquals("Invalid number of script expression", 1, nbScriptExpression);
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testRemoveDeadlineMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("DeadlineMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<NonInterruptingBoundaryTimerEvent> boundaryEvents = ModelHelper.getAllItemsOfType(mainProc,
                ProcessPackage.Literals.NON_INTERRUPTING_BOUNDARY_TIMER_EVENT);
        assertEquals("There should be non interrupting boundary events", 2, boundaryEvents.size());
        resource.unload();
    }

    @Test
    public void testPageflowTransitionConditionMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("PageFlowTransitionMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testPageflowRedirectionMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("PageFlowMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testRemoveConnectorsMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("RemoveConnectorMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        assertTrue("There should be no connector", connectors.isEmpty());
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testIterationMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("IterationMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(mainProc, ExpressionPackage.Literals.EXPRESSION);
        int nbVariableExpression = 0;
        int nbScriptExpression = 0;
        int nbConstantExpression = 0;
        for (final Expression exp : expressions) {
            if (ExpressionConstants.VARIABLE_TYPE.equals(exp.getType())) {
                nbVariableExpression++;
            }
            if (ExpressionConstants.SCRIPT_TYPE.equals(exp.getType())) {
                nbScriptExpression++;
            }
            if (ExpressionConstants.CONSTANT_TYPE.equals(exp.getType()) && exp.getContent() != null
                    && !exp.getContent().isEmpty()) {
                nbConstantExpression++;
            }
        }
        assertEquals("Invalid number of variable expression", 1, nbVariableExpression);
        assertEquals("Invalid number of script expression", 2, nbScriptExpression);
        assertEquals("Invalid number of constant expression", 4, nbConstantExpression);
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testPortalLabelMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("PortalLabelMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Expression> expressions = ModelHelper.getAllItemsOfType(mainProc, ExpressionPackage.Literals.EXPRESSION);
        int nbVariableExpression = 0;
        int nbScriptExpression = 0;
        int nbConstantExpression = 0;
        for (final Expression exp : expressions) {
            if (ExpressionConstants.VARIABLE_TYPE.equals(exp.getType())) {
                nbVariableExpression++;
            }
            if (ExpressionConstants.SCRIPT_TYPE.equals(exp.getType())) {
                nbScriptExpression++;
            }
            if (ExpressionConstants.CONSTANT_TYPE.equals(exp.getType()) && exp.getContent() != null
                    && !exp.getContent().isEmpty()) {
                nbConstantExpression++;
            }
        }
        assertEquals("Invalid number of variable expression", 1, nbVariableExpression);
        assertEquals("Invalid number of script expression", 1, nbScriptExpression);
        assertEquals("Invalid number of constant expression", 3, nbConstantExpression);
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testMessageContentMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("MessageMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Message> messages = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.MESSAGE);
        for (final Message message : messages) {
            assertFalse("Message content should not be empty", message.getMessageContent().getExpressions().isEmpty());
            assertFalse("Message target processs hould not be empty",
                    message.getTargetProcessExpression().getContent().isEmpty());
            assertFalse("Message target element should not be empty",
                    message.getTargetElementExpression().getContent().isEmpty());
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testMessageCorrelationMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("MessageCorrelationMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Message> messages = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.MESSAGE);
        final List<EObject> startMessageEventList = ModelHelper.getAllItemsOfType(mainProc,
                ProcessPackage.Literals.START_MESSAGE_EVENT);
        for (final Message message : messages) {
            assertFalse("Message content should not be empty", message.getMessageContent().getExpressions().isEmpty());
            assertFalse("Message target processs hould not be empty",
                    message.getTargetProcessExpression().getContent().isEmpty());
            assertFalse("Message target element should not be empty",
                    message.getTargetElementExpression().getContent().isEmpty());
            final Expression target = message.getTargetElementExpression();

            if (isTargetIsStartMessageEvent(startMessageEventList, target.getName())) {
                assertEquals("Invalid correlation type", CorrelationTypeActive.INACTIVE,
                        message.getCorrelation().getCorrelationType());
            } else {
                assertEquals("Invalid correlation type", CorrelationTypeActive.KEYS,
                        message.getCorrelation().getCorrelationType());
            }
            assertFalse("Invalid correlation association",
                    message.getCorrelation().getCorrelationAssociation().getExpressions().isEmpty());
        }
        final List<AbstractCatchMessageEvent> catchMessages = ModelHelper.getAllItemsOfType(mainProc,
                ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT);
        for (final AbstractCatchMessageEvent message : catchMessages) {
            if (!(message instanceof StartMessageEvent)) {
                assertFalse("Invalid correlation association", message.getCorrelation().getExpressions().isEmpty());
            } else {
                assertTrue("startMessageEvent should not contain correlation Keis", message.getCorrelation() == null);
            }
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    private boolean isTargetIsStartMessageEvent(final List<EObject> allStartMessagesEvent, final String name) {
        for (final EObject messageEvent : allStartMessagesEvent) {
            final StartMessageEvent sme = (StartMessageEvent) messageEvent;
            if (sme.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testTimerConditionMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("TimerConditionMigration--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<AbstractTimerEvent> timers = ModelHelper.getAllItemsOfType(mainProc,
                ProcessPackage.Literals.ABSTRACT_TIMER_EVENT);
        for (final AbstractTimerEvent timer : timers) {
            assertNotNull("Timer condition should not be empty", timer.getCondition());
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testAssignableMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("AssignableMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<ActorFilter> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.ACTOR_FILTER);
        assertTrue("There should be no actor filter", connectors.isEmpty());
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testFormActionsMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("FormActionsMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Form> forms = ModelHelper.getAllItemsOfType(mainProc, FormPackage.Literals.FORM);
        for (final Form form : forms) {
            assertFalse("Form actions are missing", form.getActions().isEmpty());
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testWidgetMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("WidgetMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Widget> widgets = ModelHelper.getAllItemsOfType(mainProc, FormPackage.Literals.WIDGET);
        for (final Widget widget : widgets) {
            if (!(widget.eContainer() instanceof Expression)) {
                if (!(widget instanceof FormButton)) {
                    assertNotNull("Widget action is missing", widget.getAction());
                    assertNotNull("Widget action is missing", widget.getAction().getLeftOperand());
                    assertNotNull("Widget action is missing", widget.getAction().getRightOperand());
                    assertThat(widget.getAction().getRightOperand().getContent()).isNotEmpty();
                    ExpressionAssert.assertThat(widget.getAction().getRightOperand())
                            .hasType(ExpressionConstants.FORM_FIELD_TYPE);
                }
            }
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testFormFieldMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("FormFieldMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<FormField> widgets = ModelHelper.getAllItemsOfType(mainProc, FormPackage.Literals.FORM_FIELD);
        for (final FormField widget : widgets) {
            if (!(widget.eContainer() instanceof Expression)) {
                if (!(widget instanceof FormButton)) {
                    assertNotNull("Widget example message is missing", widget.getExampleMessage());
                    assertNotNull("Widget after event expresion is missing", widget.getAfterEventExpression());
                }
            }
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testSubmitButtonActionsMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("SubmitButtonActionMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<SubmitFormButton> buttons = ModelHelper.getAllItemsOfType(mainProc,
                FormPackage.Literals.SUBMIT_FORM_BUTTON);
        for (final SubmitFormButton button : buttons) {
            assertFalse("Button actions are missing", button.getActions().isEmpty());
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testValidatorMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("ValidatorMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Validator> validators = ModelHelper.getAllItemsOfType(mainProc, FormPackage.Literals.VALIDATOR);
        RepositoryManager.getInstance().getCurrentRepository().build(Repository.NULL_PROGRESS_MONITOR);
        final IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
        final ValidatorDescriptorRepositoryStore validatorDescriptorStore = RepositoryManager.getInstance()
                .getRepositoryStore(
                        ValidatorDescriptorRepositoryStore.class);
        for (final Validator validator : validators) {
            if (!(ModelHelper.getParentWidget(validator).eContainer() instanceof Expression)) {
                assertNotNull("Validator error message is missing", validator.getDisplayName());
                assertNotNull("Validator parameter is missing", validator.getParameter());
                assertNotNull("Validator class not found in classpath", project.findType(validator.getValidatorClass()));
                final ValidatorDescriptor validatorDescriptor = validatorDescriptorStore
                        .getValidatorDescriptor(validator.getValidatorClass());
                assertNotNull("Validator descriptor is missing", validatorDescriptor);
                final ValidatorDescriptorFileStore filseSotre = validatorDescriptorStore
                        .getChild(validatorDescriptor.getName() + "."
                                + ValidatorDescriptorRepositoryStore.VALIDATOR_EXT);
                assertNotNull("Validator descriptor file store is missing", filseSotre);
            }
        }

        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testFileAndImageWidgetMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("FileAndImageWidgetMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<FileWidget> fileWidgets = ModelHelper.getAllItemsOfType(mainProc, FormPackage.Literals.FILE_WIDGET);
        for (final FileWidget fileWidget : fileWidgets) {
            if (!(ModelHelper.getParentWidget(fileWidget).eContainer() instanceof Expression)) {
                assertEquals("File widget input type is invalid", FileWidgetInputType.DOCUMENT, fileWidget.getInputType());
            }
        }
        final List<ImageWidget> imageWidgets = ModelHelper.getAllItemsOfType(mainProc, FormPackage.Literals.IMAGE_WIDGET);
        for (final ImageWidget imageWidget : imageWidgets) {
            if (!(ModelHelper.getParentWidget(imageWidget).eContainer() instanceof Expression)) {
                if (imageWidget.isIsADocument()) {
                    assertNotNull("Image widget document is not set", imageWidget.getDocument());
                } else {
                    assertNotNull("Image widget document is not set", imageWidget.getImgPath().getContent());
                }
            }
        }

        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testDuplicableMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("DuplicableMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Duplicable> duplicables = ModelHelper.getAllItemsOfType(mainProc, FormPackage.Literals.DUPLICABLE);
        for (final Duplicable duplicable : duplicables) {
            if (!(ModelHelper.getParentWidget(duplicable).eContainer() instanceof Expression)) {
                assertNotNull("Duplicable widget label for add is not set", duplicable.getDisplayLabelForAdd().getContent());
                assertNotNull("Duplicable widget label for remove is not set",
                        duplicable.getDisplayLabelForRemove().getContent());
                assertNotNull("Duplicable widget tooltip for add is not set", duplicable.getTooltipForAdd().getContent());
                assertNotNull("Duplicable widget tooltip for remove is not set",
                        duplicable.getTooltipForRemove().getContent());
                assertNotNull("Duplicable widget max duplication is not set",
                        duplicable.getMaxNumberOfDuplication().getContent());
                assertNotNull("Duplicable widget min duplication  is not set",
                        duplicable.getMinNumberOfDuplication().getContent());
            }
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testMandatoryFieldsMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("MandatoryFieldsMigraitonUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<MandatoryFieldsCustomization> fields = ModelHelper.getAllItemsOfType(mainProc,
                FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION);
        for (final MandatoryFieldsCustomization field : fields) {
            if (ModelHelper.getParentWidget(field) != null
                    && !(ModelHelper.getParentWidget(field).eContainer() instanceof Expression)) {
                assertNotNull("Mandatory label is not set", field.getMandatoryLabel().getContent());
                assertNotNull("Mandatory symbol is not set", field.getMandatorySymbol().getContent());
            }
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testTableMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("TableMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<AbstractTable> fields = ModelHelper.getAllItemsOfType(mainProc, FormPackage.Literals.ABSTRACT_TABLE);
        for (final AbstractTable field : fields) {
            if (!(ModelHelper.getParentWidget(field).eContainer() instanceof Expression)) {
                assertNotNull("Table content is not set", field.getTableExpression());
                assertNotNull("Horizontal header is not set", field.getHorizontalHeaderExpression());
                assertNotNull("Vertical header is not set", field.getVerticalHeaderExpression());
            }
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testKPIMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("KPIMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<AbstractKPIBinding> fields = ModelHelper.getAllItemsOfType(mainProc,
                KpiPackage.Literals.ABSTRACT_KPI_BINDING);
        assertTrue("KPI should be deleted", fields.isEmpty());
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testSimulationDataMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("SImulationDataMigrationUseCase--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<SimulationData> data = ModelHelper.getAllItemsOfType(mainProc,
                SimulationPackage.Literals.SIMULATION_DATA);
        for (final SimulationData d : data) {
            assertNotNull("Missing expression on simulation data", d.getExpression());
        }
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testSendTaskMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("TestSendTaskMigration--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);
        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<AbstractKPIBinding> messages = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.MESSAGE);
        assertTrue("Send Task should have one message only", messages.size() == 1);
        BarImporterTestUtil.assertViewsAreConsistent(resource);
        resource.unload();
    }

    @Test
    public void testConnectorIgnoreErrorMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("TestConnectorIgnoreErrorMigration--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);

        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Connector> connectors = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.CONNECTOR);
        for (final Connector c : connectors) {
            final String name = c.getName();
            if (name.equals("ignoreError")) {
                assertTrue(c.isIgnoreErrors());
            } else if (name.equals("putInFailedState")) {
                assertFalse(c.isIgnoreErrors());
            } else if (name.equals("throwEvent")) {
                assertFalse(c.isIgnoreErrors());
                assertTrue(c.isThrowErrorEvent());
            }
        }
        resource.unload();
    }

    @Test
    public void testDescriptionMaxLenghtMigration() throws Exception {
        final URL url = TestSimpleMigrationUseCase.class.getResource("MigrationDefect1--1.0.bar");
        final File migratedProc = BarImporterTestUtil.migrateBar(url);
        migratedProc.deleteOnExit();
        assertNotNull("Fail to migrate bar file", migratedProc);
        assertNotNull("Fail to migrate bar file", migratedProc.exists());
        final Resource resource = BarImporterTestUtil.assertIsLoadable(migratedProc);

        final MainProcess mainProc = BarImporterTestUtil.getMainProcess(resource);
        final List<Element> elements = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.Literals.ELEMENT);
        for (final Element e : elements) {
            final String documentation = e.getDocumentation();
            assertTrue(documentation.length() < 255);
        }
        resource.unload();
    }

}
