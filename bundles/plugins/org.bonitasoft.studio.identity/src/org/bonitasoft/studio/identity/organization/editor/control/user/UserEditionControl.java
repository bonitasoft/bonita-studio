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
package org.bonitasoft.studio.identity.organization.editor.control.user;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.user.UserFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.PasswordType;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.validator.UserNameValidator;
import org.bonitasoft.studio.identity.organization.validator.UserPasswordValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.widget.TextWidget.Builder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

import com.google.common.base.Strings;

public class UserEditionControl {

    private static final char CLEAR_CHAR = '\0';

    private UserFormPage formPage;
    private IObservableValue<User> selectedUserObservable;
    private DataBindingContext ctx;
    private Section section;
    private TextWidget passwordField;
    private char hiddenEchoChar;
    private boolean refreshingManagerViewer = false;
    private MembershipSection membershipSection;
    private InformationSection informationSection;

    public UserEditionControl(Composite parent, UserFormPage formPage, IObservableValue<User> selectedUserObservable,
            DataBindingContext ctx) {
        this.formPage = formPage;
        this.selectedUserObservable = selectedUserObservable;
        this.ctx = ctx;
        this.section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(
                GridDataFactory.fillDefaults().indent(0, 30).grab(false, true).hint(900, SWT.DEFAULT).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite mainComposite = formPage.getToolkit().createComposite(section);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(900, SWT.DEFAULT).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 10)
                .spacing(40, LayoutConstants.getSpacing().y).create());

        createGeneralFields(mainComposite);
        createInformationSection(mainComposite);

        section.setClient(mainComposite);
    }

    private void createInformationSection(Composite parent) {
        informationSection = new InformationSection(parent, formPage, ctx, selectedUserObservable);
    }

    private void createGeneralFields(Composite parent) {
        createUserNameField(parent);
        createMembershipSection(parent);
        createPasswordField(parent);

        Composite detailsComposite = formPage.getToolkit().createComposite(parent);
        detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).hint(400, SWT.DEFAULT).create());
        detailsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true)
                .spacing(20, LayoutConstants.getSpacing().y).create());
        createFirstNameField(detailsComposite);
        createLastNameField(detailsComposite);
        createTitleField(detailsComposite);
        createJobTitleField(detailsComposite);

        createManagerCombo(parent);
    }

    private void createTitleField(Composite parent) {
        IObservableValue<String> titleObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__TITLE);
        new TextWidget.Builder()
                .withLabel(Messages.userTitle)
                .labelAbove()
                .withPlaceholder(Messages.titleHint)
                .bindTo(titleObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .fill()
                .grabHorizontalSpace()
                .createIn(parent);
    }

    private void createJobTitleField(Composite parent) {
        IObservableValue<String> jobTitleObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__JOB_TITLE);
        new TextWidget.Builder()
                .withLabel(Messages.jobLabel)
                .labelAbove()
                .withPlaceholder(Messages.jobHint)
                .bindTo(jobTitleObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .fill()
                .grabHorizontalSpace()
                .createIn(parent);
    }

    private void createLastNameField(Composite parent) {
        IObservableValue<String> lastNameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__LAST_NAME);
        new TextWidget.Builder()
                .withLabel(Messages.lastName)
                .labelAbove()
                .withPlaceholder(Messages.lastNameHint)
                .bindTo(lastNameObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .fill()
                .grabHorizontalSpace()
                .createIn(parent);
        lastNameObservable.addValueChangeListener(e -> formPage.refreshSelectedUser());
    }

    private void createFirstNameField(Composite parent) {
        IObservableValue<String> firstNameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__FIRST_NAME);
        new TextWidget.Builder()
                .withLabel(Messages.firstName)
                .labelAbove()
                .withPlaceholder(Messages.firstNameHint)
                .bindTo(firstNameObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .fill()
                .grabHorizontalSpace()
                .createIn(parent);
        firstNameObservable.addValueChangeListener(e -> formPage.refreshSelectedUser());
    }

    private void createMembershipSection(Composite parent) {
        Composite membershipComposite = formPage.getToolkit().createComposite(parent);
        membershipComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(1, 5).create());
        membershipComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        membershipSection = new MembershipSection(membershipComposite, formPage, selectedUserObservable, ctx);
    }

    private void createManagerCombo(Composite parent) {
        Composite composite = formPage.getToolkit().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());

        Label label = formPage.getToolkit().createLabel(composite, Messages.manager);
        label.setLayoutData(GridDataFactory.fillDefaults().create());

        ComboViewer managerNameComboViewer = new ComboViewer(composite, SWT.READ_ONLY);
        managerNameComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        managerNameComboViewer.setLabelProvider(new LabelProvider());
        managerNameComboViewer.setContentProvider(new ArrayContentProvider());
        managerNameComboViewer.setComparator(new ViewerComparator());
        managerNameComboViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return selectedUserObservable.getValue() == null
                        ? false
                        : !Objects.equals(element, selectedUserObservable.getValue().getUserName());
            }
        });

        IObservableList<User> users = formPage.observeUsers();
        users.addChangeListener(e -> managerNameComboViewer.setInput(createInput(users)));
        managerNameComboViewer.setInput(createInput(users));

        IObservableValue<Object> managerComboSelectionObservable = ViewerProperties.singleSelection()
                .observe(managerNameComboViewer);

        selectedUserObservable.addValueChangeListener(e -> Display.getDefault().syncExec(() -> {
            if (selectedUserObservable.getValue() != null) {
                refreshingManagerViewer = true;
                managerNameComboViewer.refresh(); // trigger filter
                managerComboSelectionObservable.setValue(selectedUserObservable.getValue().getManager());
                refreshingManagerViewer = false;
            }
        }));

        managerComboSelectionObservable.addValueChangeListener(e -> Display.getDefault().syncExec(() -> {
            User selecteduser = selectedUserObservable.getValue();
            if (selecteduser != null && !refreshingManagerViewer) {
                selecteduser.setManager((String) e.diff.getNewValue());
            }
        }));
    }

    private List<String> createInput(IObservableList<User> users) {
        List<String> input = new ArrayList();
        input.add("");
        users.stream().map(User::getUserName).forEach(input::add);
        return input;
    }

    private void createPasswordField(Composite parent) {
        IObservableValue<PasswordType> passwordObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__PASSWORD);
        IObservableValue<String> passwordValueObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                passwordObservable, OrganizationPackage.Literals.PASSWORD_TYPE__VALUE);
        UserPasswordValidator validator = new UserPasswordValidator();
        UpdateValueStrategy updateStrategy = UpdateStrategyFactory.updateValueStrategy().withValidator(validator).create();

        Composite passwordComposite = formPage.getToolkit().createComposite(parent);
        passwordComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        passwordComposite.setLayoutData(GridDataFactory.fillDefaults().hint(300, SWT.DEFAULT).create());

        Builder builder = new TextWidget.Builder()
                .withLabel(Messages.password)
                .labelAbove()
                .withStyle(SWT.PASSWORD)
                .fill()
                .grabHorizontalSpace()
                .adapt(formPage.getToolkit())
                .bindTo(passwordValueObservable)
                .withTargetToModelStrategy(updateStrategy)
                .inContext(ctx);

        if (!isMacOS()) {
            builder.withButton(Pics.getImage("view.png", IdentityPlugin.getDefault()), Messages.showPassword)
                    .onClickButton(e -> showPassword());
        }

        passwordField = builder.createIn(passwordComposite);
        hiddenEchoChar = passwordField.getTextControl().getEchoChar();
        passwordField.observeText(SWT.Modify).addValueChangeListener(e -> {
            if (!Strings.isNullOrEmpty(e.diff.getOldValue())) { // This event is triggered when the pwd is updated AND when the first user is selected -> old value empty
                if (passwordObservable.getValue() != null) {
                    passwordObservable.getValue().setEncrypted(false);
                }
            }
        });
    }

    private void showPassword() {
        Text text = passwordField.getTextControl();
        Optional<ToolItem> button = passwordField.getButtonWithImage();
        if (button.isPresent()) {
            char echoChar = Objects.equals(text.getEchoChar(), hiddenEchoChar) ? CLEAR_CHAR : hiddenEchoChar;
            text.setEchoChar(echoChar);
        }
    }

    private boolean isMacOS() {
        return Platform.OS_MACOSX.equals(Platform.getOS());
    }

    private void createUserNameField(Composite parent) {
        IObservableValue<String> nameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                selectedUserObservable, OrganizationPackage.Literals.USER__USER_NAME);
        UserNameValidator nameValidator = new UserNameValidator(formPage.observeWorkingCopy(), selectedUserObservable);
        new TextWidget.Builder()
                .transactionalEdit((oldValue, newValue) -> refactorMemberships(oldValue, newValue))
                .withLabel(Messages.userName)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(nameObservable)
                .withTargetToModelStrategy(convertUpdateValueStrategy().withValidator(nameValidator).create())
                .withModelToTargetStrategy(updateValueStrategy().withValidator(nameValidator).create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private void refactorMemberships(String oldValue, String newValue) {
        formPage.observeMemberships().stream()
                .filter(m -> Objects.equals(m.getUserName(), oldValue))
                .forEach(m -> m.setUserName(newValue));
        formPage.refreshSelectedUser();
    }

    public IObservableValue<String> observeSectionTitle() {
        return PojoProperties.<Section, String> value("text").observe(section);
    }

    public IObservableValue<Boolean> observeSectionVisible() {
        return WidgetProperties.visible().observe(section);
    }

    public void refreshMembershipTable() {
        membershipSection.refreshTable();
    }

    public void redrawCustomInfoTable() {
        informationSection.redrawCustomInfoTable();
    }

}
