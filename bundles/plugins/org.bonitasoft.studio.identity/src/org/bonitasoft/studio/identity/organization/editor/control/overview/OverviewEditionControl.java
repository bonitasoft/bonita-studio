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
package org.bonitasoft.studio.identity.organization.editor.control.overview;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;

import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.control.group.GroupListReadOnly;
import org.bonitasoft.studio.identity.organization.editor.control.role.RoleListReadOnly;
import org.bonitasoft.studio.identity.organization.editor.control.user.UserListReadOnly;
import org.bonitasoft.studio.identity.organization.editor.formpage.overview.OverviewFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.PasswordType;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.identity.organization.validator.OrganizationNameValidator;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.notification.BonitaNotificator;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;

public class OverviewEditionControl {

    private OverviewFormPage formPage;
    private IObservableValue<Organization> organizationObservable;
    private DataBindingContext ctx;
    private IObservableList<User> userListObservable;
    private ActiveOrganizationProvider activeOrganizationProvider;
    private IObservableList<User> users;
    private ComboViewer defaultUserComboViewer;
    private IViewerObservableValue<User> defaultUserSelectionObservable;
    private GroupListReadOnly groupList;
    private RoleListReadOnly roleList;
    private UserListReadOnly userList;

    public OverviewEditionControl(Composite parent, OverviewFormPage formPage,
            IObservableValue<Organization> organizationObservable, DataBindingContext ctx) {
        this.formPage = formPage;
        this.organizationObservable = organizationObservable;
        this.ctx = ctx;
        this.userListObservable = formPage.observeUsers();
        this.activeOrganizationProvider = new ActiveOrganizationProvider();
        this.users = formPage.observeUsers();

        Section overviewSection = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        overviewSection.setLayout(GridLayoutFactory.fillDefaults().create());
        overviewSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        overviewSection.setText(Messages.displayOrganizationOverviewPageTitle);

        Composite mainComposite = formPage.getToolkit().createComposite(overviewSection);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 30)
                        .create());

        createOverviewFields(mainComposite);

        Label separator = formPage.getToolkit().createLabel(mainComposite, "", SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createOrganizationContentOverview(mainComposite);

        overviewSection.setClient(mainComposite);
    }

    private void createOrganizationContentOverview(Composite parent) {
        Composite contentComposite = formPage.getToolkit().createComposite(parent);
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(3).spacing(40, 10).create());

        groupList = new GroupListReadOnly(contentComposite, formPage, ctx);
        groupList.expandAll();
        roleList = new RoleListReadOnly(contentComposite, formPage, ctx);
        userList = new UserListReadOnly(contentComposite, formPage, ctx);
    }

    private void createOverviewFields(Composite parent) {
        Composite overviewComposite = formPage.getToolkit().createComposite(parent);
        overviewComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        overviewComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(2).spacing(40, 10).create());

        createNameField(overviewComposite);
        createDescriptionField(overviewComposite);
        createDefaultUserField(overviewComposite);
    }

    private void createDefaultUserField(Composite parent) {
        Composite composite = formPage.getToolkit().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());

        Label label = formPage.getToolkit().createLabel(composite, Messages.defaultUserOrganizationTitle);
        label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.FILL).create());

        ControlDecoration controlDecoration = new ControlDecoration(label, SWT.RIGHT);
        controlDecoration.setMarginWidth(5);
        controlDecoration.setShowOnlyOnFocus(false);
        controlDecoration.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        controlDecoration.setDescriptionText(Messages.defaultUserTooltip);

        defaultUserComboViewer = new ComboViewer(composite, SWT.READ_ONLY);
        defaultUserComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().hint(300, SWT.DEFAULT).create());
        defaultUserComboViewer.setLabelProvider(new LabelProviderBuilder<User>()
                .withTextProvider(formPage::toUserDisplayName)
                .createLabelProvider());
        defaultUserComboViewer.setContentProvider(new ArrayContentProvider());
        defaultUserComboViewer.setComparator(new ViewerComparator());

        defaultUserSelectionObservable = ViewerProperties.singleSelection(User.class)
                .observe(defaultUserComboViewer);

        updateDefaultUserViewerInput();

        defaultUserSelectionObservable.addValueChangeListener(
                e -> updateDefaultUserPreference(defaultUserSelectionObservable.getValue().getUserName()));

        ctx.bindValue(WidgetProperties.visible().observe(composite), new ComputedValueBuilder()
                .withSupplier(() -> Objects.equals(activeOrganizationProvider.getActiveOrganization(),
                        organizationObservable.getValue().getName()))
                .build());
    }

    // Do not use an observable input here 
    // -> We perform a concret action when the selection change (see bellow), 
    // the use of an observable input and an ObservableListContentProvider leads to unwanted selection change
    // when the list is being disposed for ex..
    public void updateDefaultUserViewerInput() {
        defaultUserComboViewer.setInput(users.toArray(new User[users.size()]));
        defaultUserSelectionObservable.setValue(getDefaultUser());
    }

    private User getDefaultUser() {
        String defaultUser = new ActiveOrganizationProvider().getDefaultUser();
        return users.stream()
                .filter(user -> Objects.equals(user.getUserName(), defaultUser))
                .findFirst().orElse(null);
    }

    protected void updateDefaultUserPreference(String newName) {
        if (!Objects.equals(newName, activeOrganizationProvider.getDefaultUser())) {
            activeOrganizationProvider.saveDefaultUser(newName);
            activeOrganizationProvider.saveDefaultPassword(userListObservable.stream()
                    .filter(user -> Objects.equals(user.getUserName(), newName))
                    .findFirst()
                    .map(User::getPassword)
                    .map(PasswordType::getValue)
                    .orElse(""));
            BonitaNotificator.openNotification(Messages.defaultUserUpdatedTitle,
                    String.format(Messages.defaultUserUpdated, newName));
        }
    }

    private void createNameField(Composite parent) {
        IObservableValue<String> nameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                organizationObservable, OrganizationPackage.Literals.ORGANIZATION__NAME);
        OrganizationNameValidator nameValidator = new OrganizationNameValidator(formPage);
        new TextWidget.Builder()
                .withLabel(Messages.name)
                .labelAbove()
                .widthHint(300)
                .transactionalEdit((oldName, newName) -> renameOrgaFile(oldName, newName))
                .bindTo(nameObservable)
                .withTargetToModelStrategy(convertUpdateValueStrategy().withValidator(nameValidator).create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private void renameOrgaFile(String oldName, String newName) {
        if (!Objects.equals(oldName, newName)) {
            OrganizationRepositoryStore repositoryStore = getRepositoryStore();
            String fileName = formPage.getEditor().getEditorInput().getName();
            OrganizationFileStore fileStore = repositoryStore.getChild(fileName, true);

            if (formPage.isDirty()) {
                BonitaStudioLog.info(Messages.organizationSavedBeforeRename, IdentityPlugin.PLUGIN_ID);
                formPage.doSave(new NullProgressMonitor());
            }
            Display.getDefault().asyncExec(() -> {
                fileStore.rename(String.format("%s.%s", newName, OrganizationRepositoryStore.ORGANIZATION_EXT));
                formPage.doSave(new NullProgressMonitor());
            });
        }
    }

    private OrganizationRepositoryStore getRepositoryStore() {
        return formPage.getRepositoryAccessor()
                .getRepositoryStore(OrganizationRepositoryStore.class);
    }

    private void createDescriptionField(Composite parent) {
        IObservableValue<String> descriptionObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                organizationObservable, OrganizationPackage.Literals.ORGANIZATION__DESCRIPTION);

        new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .widthHint(500)
                .heightHint(100)
                .verticalSpan(2)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    public void refreshList() {
        groupList.refreshGroupList();
        roleList.refreshRoleList();
        userList.refreshUserList();
    }

}
