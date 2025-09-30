/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.migration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.core.migration.ui.MigrationStepWizardPage;
import org.bonitasoft.studio.common.repository.core.migration.ui.ProjectMigrationWizard;
import org.bonitasoft.studio.common.repository.core.migration.ui.ProjectMigrationWizardDialog;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.conditions.ShellWithRegexIsActive;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ProjectMigrationWizardTest {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    private Path project = Path.of("./");

    private Supplier<SWTBotButton> back = () -> bot.button(IDialogConstants.BACK_LABEL);

    private Supplier<SWTBotButton> next = () -> bot.button(IDialogConstants.NEXT_LABEL);

    private Supplier<SWTBotButton> executeAll = () -> bot.button(Messages.projectMigrationExecuteAllSteps);

    private Supplier<SWTBotButton> finish = () -> bot.button(IDialogConstants.FINISH_LABEL);

    private Supplier<SWTBotButton> cancel = () -> bot.button(IDialogConstants.CANCEL_LABEL);

    /**
     * Open the wizard asynchronously and get future result.
     * 
     * @param wizard wizard to open
     * @return reference with future open result
     */
    private AtomicReference<Optional<Integer>> openWizard(ProjectMigrationWizard wizard) {
        AtomicReference<Optional<Integer>> openResult = new AtomicReference<>(Optional.empty());
        SWTBotTestUtil.waitUntilRootShellIsActive(bot);
        bot.activeShell().display.asyncExec(() -> {
            int code = new ProjectMigrationWizardDialog(null, wizard).open();
            openResult.set(Optional.of(code));
        });

        var regex = "\\Q"
                + MessageFormat.format(org.bonitasoft.studio.common.repository.Messages.projectMigration, "\\E\\d+\\Q")
                + "\\E";
        bot.waitUntil(new ShellWithRegexIsActive(regex));
        return openResult;
    }

    @Test
    public void testMigrationWizardWithTwoSteps() {

        var wiz = new ProjectMigrationWizard(project);
        wiz.addPage(new MigrationStepWizardPage(new OkStep("1")));
        wiz.addPage(new MigrationStepWizardPage(new OkStep("2")));

        AtomicReference<Optional<Integer>> openResult = openWizard(wiz);
        var wizardShell = bot.activeShell();

        // 1st step, no Back button visible, Next and Execute All are enabled
        assertThrows("Back button should not exist", WidgetNotFoundException.class, back::get);
        assertTrue("Next button should be enabled", next.get().isEnabled());
        assertTrue("Execute All button should be enabled", executeAll.get().isEnabled());
        assertThrows("Finish button should not exist", WidgetNotFoundException.class, finish::get);

        next.get().click();
        // last step, no Back button visible, Next is disabled, Finish is enabled
        assertThrows("Back button should not exist", WidgetNotFoundException.class, back::get);
        assertFalse("Next button should be disabled", next.get().isEnabled());
        assertThrows("Execute All button should not exist", WidgetNotFoundException.class, executeAll::get);
        assertTrue("Finish button should be enabled", finish.get().isEnabled());

        finish.get().click();

        bot.waitUntil(Conditions.shellCloses(wizardShell));
        assertThat(openResult.get()).hasValue(IDialogConstants.OK_ID);
    }

    @Test
    public void testMigrationWizardCancel() {

        var wiz = new ProjectMigrationWizard(project);
        wiz.addPage(new MigrationStepWizardPage(new OkStep("1")));
        wiz.addPage(new MigrationStepWizardPage(new OkStep("2")));

        AtomicReference<Optional<Integer>> openResult = openWizard(wiz);
        var wizardShell = bot.activeShell();

        // 1st step, no Back button visible, Next and Execute All are enabled
        assertThrows("Back button should not exist", WidgetNotFoundException.class, back::get);
        assertTrue("Next button should be enabled", next.get().isEnabled());
        assertTrue("Execute All button should be enabled", executeAll.get().isEnabled());
        assertThrows("Finish button should not exist", WidgetNotFoundException.class, finish::get);

        cancel.get().click();

        bot.waitUntil(Conditions.shellCloses(wizardShell));
        assertThat(openResult.get()).hasValue(IDialogConstants.CANCEL_ID);
    }

    @Test
    public void testMigrationWizardWithSkippedStep() {

        var wiz = new ProjectMigrationWizard(project);
        wiz.addPage(new MigrationStepWizardPage(new OkStep("1")));
        wiz.addPage(new MigrationStepWizardPage(new SkippedStep("2")));

        AtomicReference<Optional<Integer>> openResult = openWizard(wiz);
        var wizardShell = bot.activeShell();

        // 1st step & last step, no Back button visible, Next is disabled, Finish is enabled
        assertThrows("Back button should not exist", WidgetNotFoundException.class, back::get);
        assertFalse("Next button should be disabled", next.get().isEnabled());
        assertThrows("Execute All button should not exist", WidgetNotFoundException.class, executeAll::get);
        assertTrue("Finish button should be enabled", finish.get().isEnabled());

        finish.get().click();

        bot.waitUntil(Conditions.shellCloses(wizardShell));
        assertThat(openResult.get()).hasValue(IDialogConstants.OK_ID);
    }

    @Test
    public void testMigrationWizardWithFailedStep() {

        var wiz = new ProjectMigrationWizard(project);
        wiz.addPage(new MigrationStepWizardPage(new OkStep("1")));
        wiz.addPage(new MigrationStepWizardPage(new ErrorStep("2")));

        AtomicReference<Optional<Integer>> openResult = openWizard(wiz);
        var wizardShell = bot.activeShell();

        // 1st step, no Back button visible, Next and Execute All are enabled
        assertThrows("Back button should not exist", WidgetNotFoundException.class, back::get);
        assertTrue("Next button should be enabled", next.get().isEnabled());
        assertTrue("Execute All button should be enabled", executeAll.get().isEnabled());
        assertThrows("Finish button should not exist", WidgetNotFoundException.class, finish::get);

        next.get().click();
        // failed step, no Back button visible, Next is disabled, Finish is disabled
        assertThrows("Back button should not exist", WidgetNotFoundException.class, back::get);
        assertFalse("Next button should be disabled", next.get().isEnabled());
        assertThrows("Execute All button should not exist", WidgetNotFoundException.class, executeAll::get);
        assertFalse("Finish button should be disabled", finish.get().isEnabled());

        cancel.get().click();

        bot.waitUntil(Conditions.shellCloses(wizardShell));
        assertThat(openResult.get()).hasValue(IDialogConstants.CANCEL_ID);
    }

    @Test
    public void testMigrationWizardWithCancelledStep() {

        var wiz = new ProjectMigrationWizard(project);
        wiz.addPage(new MigrationStepWizardPage(new OkStep("1")));
        wiz.addPage(new MigrationStepWizardPage(new CancelledStep("2")));

        AtomicReference<Optional<Integer>> openResult = openWizard(wiz);
        var wizardShell = bot.activeShell();

        // 1st step, no Back button visible, Next and Execute All are enabled
        assertThrows("Back button should not exist", WidgetNotFoundException.class, back::get);
        assertTrue("Next button should be enabled", next.get().isEnabled());
        assertTrue("Execute All button should be enabled", executeAll.get().isEnabled());
        assertThrows("Finish button should not exist", WidgetNotFoundException.class, finish::get);

        next.get().click();
        // cancelled step, no Back button visible, Next is disabled, Finish is disabled
        assertThrows("Back button should not exist", WidgetNotFoundException.class, back::get);
        assertFalse("Next button should be disabled", next.get().isEnabled());
        assertThrows("Execute All button should not exist", WidgetNotFoundException.class, executeAll::get);
        assertFalse("Finish button should be disabled", finish.get().isEnabled());

        cancel.get().click();

        bot.waitUntil(Conditions.shellCloses(wizardShell));
        assertThat(openResult.get()).hasValue(IDialogConstants.CANCEL_ID);
    }

    private static class OkStep implements MigrationStep {

        private String name;

        public OkStep(String stepName) {
            this.name = stepName;
        }

        @Override
        public MigrationReport run(Path projectRoot, IProgressMonitor monitor) throws CoreException {
            return MigrationReport.emptyReport();
        }

        /*
         * (non-Javadoc)
         * @see org.bonitasoft.studio.common.repository.core.migration.MigrationStep#getDescription()
         */
        @Override
        public StepDescription getDescription() {
            return new StepDescription(name, getClass().getName());
        }
    }

    private static class ErrorStep implements MigrationStep {

        private String name;

        public ErrorStep(String stepName) {
            this.name = stepName;
        }

        @Override
        public MigrationReport run(Path projectRoot, IProgressMonitor monitor) throws CoreException {
            throw new CoreException(Status.error("Error"));
        }

        /*
         * (non-Javadoc)
         * @see org.bonitasoft.studio.common.repository.core.migration.MigrationStep#getDescription()
         */
        @Override
        public StepDescription getDescription() {
            return new StepDescription(name, getClass().getName());
        }
    }

    private static class SkippedStep extends OkStep {

        public SkippedStep(String stepName) {
            super(stepName);
        }

        /*
         * (non-Javadoc)
         * @see org.bonitasoft.studio.common.repository.core.migration.MigrationStep#appliesToProject(java.nio.file.Path)
         */
        @Override
        public boolean appliesToProject(Path projectRoot) throws CoreException {
            return false;
        }
    }

    private static class CancelledStep extends OkStep {

        public CancelledStep(String stepName) {
            super(stepName);
        }

        /*
         * (non-Javadoc)
         * @see org.bonitasoft.studio.common.repository.core.migration.MigrationStep#appliesToProject(java.nio.file.Path)
         */
        @Override
        public boolean appliesToProject(Path projectRoot) throws CoreException {
            throw new CoreException(Status.error("Cancelled"));
        }
    }

}
