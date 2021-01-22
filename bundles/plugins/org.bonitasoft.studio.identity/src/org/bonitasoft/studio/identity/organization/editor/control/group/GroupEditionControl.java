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
import org.bonitasoft.studio.identity.organization.editor.provider.content.GroupContentProvider;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.validator.DisplayNameValidator;
import org.bonitasoft.studio.identity.organization.validator.GroupNameValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.Section;

public class GroupEditionControl {

    private GroupFormPage formPage;
    private IObservableValue<Group> selectedGroupObservable;
    private DataBindingContext ctx;
    private Section section;
    private TextWidget pathTextWidget;

    public GroupEditionControl(Composite parent, GroupFormPage formPage, IObservableValue<Group> selectedGroupObservable,
            DataBindingContext ctx) {
        this.formPage = formPage;
        this.selectedGroupObservable = selectedGroupObservable;
        this.ctx = ctx;
        this.section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().indent(0, 30).grab(true, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite mainComposite = formPage.getToolkit().createComposite(section);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10).create());

        createNameField(mainComposite);
        createDisplayNameField(mainComposite);
        createPathField(mainComposite);
        createDescriptionField(mainComposite);

        section.setClient(mainComposite);
    }

    private void createDescriptionField(Composite parent) {
        IObservableValue<String> descriptionObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedGroupObservable, OrganizationPackage.Literals.GROUP__DESCRIPTION);

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

    private void createPathField(Composite parent) {
        pathTextWidget = new TextWidget.Builder()
                .labelAbove()
                .withLabel(Messages.groupPath)
                .withMessage(Messages.groupPathMessage)
                .withButton(Pics.getImage(PicsConstants.copyToClipboard), Messages.copyToClipboard)
                .onClickButton(e -> copyToClipBoard(toPath(selectedGroupObservable.getValue())))
                .widthHint(400)
                .bindTo(selectedGroupObservable)
                .withModelToTargetStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<Group, String> newConverter()
                                .fromType(Group.class)
                                .toType(String.class)
                                .withConvertFunction(this::toPath)
                                .create()))
                .withTargetToModelStrategy(UpdateStrategyFactory.neverUpdateValueStrategy())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .readOnly()
                .createIn(parent);
        pathTextWidget.getControl().setEnabled(true);
    }

    private String toPath(Group group) {
        if (group != null) {
            return group.getParentPath() == null
                    ? group.getName()
                    : String.format("%s%s%s", group.getParentPath(), GroupContentProvider.GROUP_SEPARATOR, group.getName());
        }
        return "";
    }

    private void copyToClipBoard(String value) {
        Clipboard cb = new Clipboard(Display.getDefault());
        TextTransfer textTransfer = TextTransfer.getInstance();
        cb.setContents(new Object[] { value },
                new Transfer[] { textTransfer });
        cb.dispose();
    }

    private void createDisplayNameField(Composite parent) {
        IObservableValue<String> displayNameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedGroupObservable, OrganizationPackage.Literals.GROUP__DISPLAY_NAME);
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
        displayNameObservable.addValueChangeListener(e -> formPage.refreshSelectedGroup());
    }

    private void createNameField(Composite parent) {
        IObservableValue<String> nameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedGroupObservable, OrganizationPackage.Literals.GROUP__NAME);
        GroupNameValidator nameValidator = new GroupNameValidator(formPage, selectedGroupObservable);
        new TextWidget.Builder()
                .transactionalEdit()
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

    public IObservableValue<String> observeSectionTitle() {
        return PojoProperties.<Section, String> value("text").observe(section);
    }

    public IObservableValue<Boolean> observeSectionVisible() {
        return WidgetProperties.visible().observe(section);
    }

}
