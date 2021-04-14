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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.comparator.UserComparator;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.identity.organization.editor.formpage.user.UserFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.PasswordType;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.validator.UserListValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

public class UserList {

    public static final String USER_LIST_VIEWER_ID = "userListViewerid";
    public static final String ADD_USER_BUTTON_ID = "addUserButtonId";
    public static final String REMOVE_BUTTON_ID = "deleteUserButtonId";
    public static final String DEFAULT_USER_PASSWORD = "bpm";

    private AbstractOrganizationFormPage formPage;
    private DataBindingContext ctx;
    protected Section section;
    private TableViewer viewer;
    private IObservableValue<User> selectionObservable;
    private IObservableList<User> input;
    private List<User> usersToFilter = new ArrayList<>();

    private ToolItem addItem;
    private ToolItem deleteItem;

    public UserList(Composite parent, AbstractOrganizationFormPage formPage, DataBindingContext ctx) {
        this.formPage = formPage;
        this.ctx = ctx;

        input = formPage.observeUsers();
        input.forEach(user -> {
            if (user.getPersonalData() == null) {
                user.setPersonalData(OrganizationFactory.eINSTANCE.createContactData());
            }
            if (user.getProfessionalData() == null) {
                user.setProfessionalData(OrganizationFactory.eINSTANCE.createContactData());
            }
        });

        section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 600).create());
        section.setText(Messages.organizationUsers);

        Composite userListComposite = createUserListComposite();

        section.setClient(userListComposite);
    }

    private Composite createUserListComposite() {
        Composite userListComposite = formPage.getToolkit().createComposite(section);
        userListComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).margins(5, 10).create());
        userListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(userListComposite);
        createSearchField(userListComposite);
        createViewer(userListComposite);
        enableButtons();

        return userListComposite;
    }

    protected void enableButtons() {
        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), new ComputedValueBuilder()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());
    }

    protected void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        formPage.getToolkit().adapt(viewer.getTable());
        viewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, USER_LIST_VIEWER_ID);
        viewer.addFilter(createSearchFilter());
        viewer.setComparator(new UserComparator(formPage::toUserDisplayName));

        ColumnViewerToolTipSupport.enableFor(viewer);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);
        viewer.setUseHashlookup(true);
        createUserColumn();
        viewer.setContentProvider(new ObservableListContentProvider<User>());
        viewer.setInput(input);
        selectionObservable = ViewerProperties.singleSelection(User.class).observe(viewer);
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return !usersToFilter.contains(element);
            }
        };
    }

    private void createUserColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        Image userImage = Pics.getImage(PicsConstants.organization_user);
        column.setLabelProvider(new LabelProviderBuilder<User>()
                .withTextProvider(formPage::toUserDisplayName)
                .withImageProvider(usr -> userImage)
                .withStatusProvider(groupStatusProvider(new UserListValidator(formPage.observeWorkingCopy(), ctx)))
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
    }

    public Function<User, IStatus> groupStatusProvider(IValidator<User> validators) {
        return element -> Stream.of(validators)
                .map(v -> v.validate(element))
                .map(status -> status instanceof MultiStatus ? Arrays.asList(status.getChildren()) : Arrays.asList(status))
                .flatMap(Collection::stream)
                .collect(StatusCollectors.toMultiStatus());
    }

    protected void createSearchField(Composite parent) {
        TextWidget searchWidget = createSearchWidget(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                usersToFilter.clear();
                input.stream()
                        .filter(user -> !matchesSearchValue(user, search))
                        .forEach(usersToFilter::add);
                viewer.refresh(); // apply filter
            });
        });
    }

    private boolean matchesSearchValue(User user, String search) {
        String userDisplayName = formPage.toUserDisplayName(user);
        return userDisplayName.toLowerCase().contains(search) || user.getUserName().toLowerCase().contains(search);
    }

    private TextWidget createSearchWidget(Composite parent) {
        return new SearchWidget.Builder()
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .withPlaceholder(Messages.search)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    protected void createToolbar(Composite parent) {
        Composite composite = formPage.getToolkit().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).create());

        ToolBar toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        Image addImage = new DecorationOverlayIcon(Pics.getImage(PicsConstants.organization_user),
                Pics.getImageDescriptor(PicsConstants.add_decorator), IDecoration.BOTTOM_RIGHT).createImage();

        addItem = new ToolItem(toolBar, SWT.PUSH);
        addItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_USER_BUTTON_ID);
        addItem.setImage(addImage);
        addItem.setText(Messages.addNewUser);
        addItem.setToolTipText(Messages.addUserTooltip);
        addItem.addListener(SWT.Selection, e -> addUser());

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_BUTTON_ID);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setText(Messages.delete);
        deleteItem.setToolTipText(Messages.deleteTooltip);
        deleteItem.addListener(SWT.Selection, e -> removeUser());
    }

    private void removeUser() {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteUserTitle,
                String.format(Messages.deleteUserMsg, formPage.toUserDisplayName(selectionObservable.getValue())))) {
            User selectedUser = selectionObservable.getValue();
            formPage.observeWorkingCopy().getValue().getMemberships().getMembership()
                    .removeIf(m -> Objects.equals(m.getUserName(), selectedUser.getUserName()));
            input.remove(selectedUser);
            formPage.updateDefaultUserViewerInput();
        }
    }

    private void addUser() {
        User user = OrganizationFactory.eINSTANCE.createUser();
        user.setUserName(generateUsername());
        user.setPassword(createPassword(DEFAULT_USER_PASSWORD));
        user.setPersonalData(OrganizationFactory.eINSTANCE.createContactData());
        user.setProfessionalData(OrganizationFactory.eINSTANCE.createContactData());
        user.setCustomUserInfoValues(OrganizationFactory.eINSTANCE.createCustomUserInfoValuesType());

        Organization organization = formPage.observeWorkingCopy().getValue();
        CustomUserInfoDefinitions customUserInfoDefinitions = organization.getCustomUserInfoDefinitions();
        if (customUserInfoDefinitions != null) {
            for (CustomUserInfoDefinition definitions : customUserInfoDefinitions.getCustomUserInfoDefinition()) {
                CustomUserInfoValue newValue = OrganizationFactory.eINSTANCE.createCustomUserInfoValue();
                newValue.setName(definitions.getName());
                newValue.setValue("");
                user.getCustomUserInfoValues().getCustomUserInfoValue().add(newValue);
            }
        }
        organization.getMemberships().getMembership()
                .add(((UserFormPage) formPage).createDefaultMembership(user.getUserName()));
        input.add(user);
        formPage.updateDefaultUserViewerInput();
        selectionObservable.setValue(user);
    }

    private String generateUsername() {
        final Set<String> names = input.stream().map(User::getUserName).collect(Collectors.toSet());
        return NamingUtils.generateNewName(names, Messages.defaultUserName, 1);
    }

    private PasswordType createPassword(final String defaultUserPassword) {
        final PasswordType password = OrganizationFactory.eINSTANCE.createPasswordType();
        password.setEncrypted(false);
        password.setValue(defaultUserPassword);
        return password;
    }

    public IObservableList<User> observeInput() {
        return input;
    }

    public IObservableValue<User> observeSelectedUser() {
        return selectionObservable;
    }

    public void refreshSelectedUser() {
        viewer.refresh(selectionObservable.getValue());
    }

    public void refreshUserList() {
        viewer.refresh();
    }

}
