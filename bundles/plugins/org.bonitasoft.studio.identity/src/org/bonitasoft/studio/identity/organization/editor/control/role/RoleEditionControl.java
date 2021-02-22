/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.control.role;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.role.RoleFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.validator.DisplayNameValidator;
import org.bonitasoft.studio.identity.organization.validator.RoleNameValidator;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

public class RoleEditionControl {

    private RoleFormPage formPage;
    private IObservableValue<Role> selectedRoleObservable;
    private DataBindingContext ctx;
    private Section section;
    private IObservableList<Membership> membershipList;

    public RoleEditionControl(Composite parent, RoleFormPage formPage, IObservableValue<Role> selectedRoleObservable,
            DataBindingContext ctx) {
        this.formPage = formPage;
        this.selectedRoleObservable = selectedRoleObservable;
        this.ctx = ctx;
        this.membershipList = formPage.observeMemberships();

        this.section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().indent(0, 30).grab(true, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite mainComposite = formPage.getToolkit().createComposite(section);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10).create());

        createNameField(mainComposite);
        createDisplayNameField(mainComposite);
        createDescriptionField(mainComposite);

        section.setClient(mainComposite);
    }

    private void createNameField(Composite parent) {
        IObservableValue<String> nameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedRoleObservable, OrganizationPackage.Literals.ROLE__NAME);
        RoleNameValidator nameValidator = new RoleNameValidator(formPage.observeWorkingCopy(), selectedRoleObservable);
        new TextWidget.Builder()
                .transactionalEdit((oldname, newName) -> formPage.refactorMemberships(membershipList, oldname, newName))
                .withLabel(Messages.name)
                .labelAbove()
                .widthHint(300)
                .bindTo(nameObservable)
                .withTargetToModelStrategy(convertUpdateValueStrategy().withValidator(nameValidator).create())
                .withModelToTargetStrategy(updateValueStrategy().withValidator(nameValidator).create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private void createDisplayNameField(Composite parent) {
        IObservableValue<String> displayNameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedRoleObservable, OrganizationPackage.Literals.ROLE__DISPLAY_NAME);
        DisplayNameValidator displayNameValidator = new DisplayNameValidator();
        new TextWidget.Builder()
                .withLabel(Messages.displayName)
                .labelAbove()
                .widthHint(400)
                .withDelay(500)
                .bindTo(displayNameObservable)
                .withTargetToModelStrategy(updateValueStrategy().withValidator(displayNameValidator).create())
                .withModelToTargetStrategy(updateValueStrategy().withValidator(displayNameValidator).create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
        displayNameObservable.addValueChangeListener(e -> formPage.refreshSelectedRole());
    }

    private void createDescriptionField(Composite parent) {
        IObservableValue<String> descriptionObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedRoleObservable, OrganizationPackage.Literals.ROLE__DESCRIPTION);

        new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .widthHint(400)
                .heightHint(100)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    public IObservableValue<String> observeSectionTitle() {
        return PojoProperties.<Section, String> value("text").observe(section);
    }

    public IObservableValue<Boolean> observeSectionVisible() {
        return WidgetProperties.visible().observe(section);
    }

}
