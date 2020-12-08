/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.control.group;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.group.GroupFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.validator.GroupNameValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

public class GroupEditionControl {

    private GroupFormPage formPage;
    private IObservableValue<Group> selectedGroupObservable;
    private DataBindingContext ctx;
    private Section section;

    public GroupEditionControl(Composite parent, GroupFormPage formPage, IObservableValue<Group> selectedGroupObservable,
            DataBindingContext ctx) {
        this.formPage = formPage;
        this.selectedGroupObservable = selectedGroupObservable;
        this.ctx = ctx;
        this.section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite mainComposite = formPage.getToolkit().createComposite(section);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        createNameField(mainComposite);
        createDisplayNameField(mainComposite);
        createPathField(mainComposite);
        createDescriptionField(mainComposite);

        section.setClient(mainComposite);
    }

    private void createDescriptionField(Composite mainComposite) {
        // TODO Auto-generated method stub

    }

    private void createPathField(Composite mainComposite) {
        // TODO Auto-generated method stub

    }

    private void createDisplayNameField(Composite mainComposite) {
        // TODO Auto-generated method stub

    }

    private void createNameField(Composite parent) {
        IObservableValue<String> nameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedGroupObservable, OrganizationPackage.Literals.GROUP__NAME);
        GroupNameValidator nameValidator = new GroupNameValidator(formPage.observeWorkingCopy(), selectedGroupObservable);
        new TextWidget.Builder()
                .transactionalEdit()
                .withLabel(Messages.name)
                .labelAbove()
                .fill()
                .widthHint(300)
                .bindTo(nameObservable)
                .withTargetToModelStrategy(convertUpdateValueStrategy().withValidator(nameValidator).create())
                .withModelToTargetStrategy(updateValueStrategy().withValidator(nameValidator).create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    public IObservableValue<String> observeSectionTitle() {
        return PojoProperties.<Section, String> value("text").observe(section);
    }

}
