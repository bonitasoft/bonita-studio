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
package org.bonitasoft.studio.ui.wizard;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.junit.Rule;
import org.junit.Test;

public class WizardBuilderTest {

    class Person {

        private String name;
        private String title;
        private String manager;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public String getManager() {
            return manager;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Rule
    public RealmWithDisplay rule = new RealmWithDisplay();

    @Test
    public void should_create_a_new_wizard() throws Exception {
        final Person person = new Person();
        person.setTitle("Mr");
        newWizard()
                .withTitle("My Wizard Window")
                .havingPage(newPage()
                        .withTitle("Page1")
                        .withDescription("Desc1")
                        .withDatabindingContext(() -> new DataBindingContext())
                        .withControl((parent, ctx) -> {

                            final Composite container = new Composite(parent, SWT.NONE);
                            container.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(15, 10).create());

                            new TextWidget.Builder()
                                    .withLabel("Name")
                                    .withLabelWidth(100)
                                    .withLayoutData(GridDataFactory.fillDefaults().grab(true, false).create())
                                    .bindTo(PojoObservables.observeValue(person, "name"))
                                    .inContext(ctx)
                                    .withTargetToModelStrategy(
                                            updateValueStrategy().withValidator(new EmptyInputValidator.Builder().withMessage("Name is mandatory").create()))
                                    .createIn(container);

                            new TextWidget.Builder()
                                    .withPlaceholder("Mr,Miss")
                                    .withLabel("Title")
                                    .withLabelWidth(100)
                                    .withMessage("Example: Mr, Ms..")
                                    .fill()
                                    .grabHorizontalSpace()
                                    .readOnly()
                                    .bindTo(PojoObservables.observeValue(person, "title"))
                                    .inContext(ctx)
                                    .withTargetToModelStrategy(
                                            updateValueStrategy()
                                                    .withValidator(new EmptyInputValidator.Builder().withMessage("Title is mandatory").warningLevel().create()))
                                    .createIn(container);

                            new ComboWidget.Builder()
                                    .withLabel("Manager With Super long label")
                                    .withLabelWidth(100)
                                    .labelAbove()
                                    .readOnly()
                                    .withMessage("Choose the manager of this employee")
                                    .withItems("", "Romain", "Toto")
                                    .withLayoutData(GridDataFactory.fillDefaults().grab(true, false).create())
                                    .bindTo(PojoObservables.observeValue(person, "manager"))
                                    .inContext(ctx)
                                    .withTargetToModelStrategy(updateValueStrategy().withValidator(value -> {
                                        return value == null || ((String) value).isEmpty() ? ValidationStatus.warning("Manager is missing")
                                                : ValidationStatus.ok();
                                    }))
                                    .createIn(container);

                            return container;
                        }))
                .onFinish(() -> {
                    return MessageDialog.openConfirm(Display.getDefault().getActiveShell(), String.format("Create %s ?", person.getName()),
                            "A new person will be added into the contact list.");
                })/* .open(rule.getShell()) */;

    }

}
