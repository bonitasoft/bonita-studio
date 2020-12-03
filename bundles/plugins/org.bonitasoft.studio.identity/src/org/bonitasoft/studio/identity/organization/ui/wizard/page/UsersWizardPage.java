/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.identity.organization.ui.wizard.page;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.identity.organization.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.PasswordType;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.ui.editingsupport.CustomUserInformationDefinitionNameEditingSupport;
import org.bonitasoft.studio.identity.organization.ui.editingsupport.CustomerUserInformationDefinitionDescriptionEditingSupport;
import org.bonitasoft.studio.identity.organization.ui.provider.label.OrganizationGroupLabelProvider;
import org.bonitasoft.studio.identity.organization.validator.UserEmptyInputValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.widget.NativeTabFolderWidget;
import org.bonitasoft.studio.ui.widget.NativeTabItemWidget;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.observable.masterdetail.DetailObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class UsersWizardPage extends AbstractOrganizationWizardPage {

    private static final int SHORT_FIELD_MAX_LENGTH = 50;
    private static final int LONG_FIELD_MAX_LENGTH = 255;
    private static final int CUSTOM_USER_DEFINITION_VALUE_LIMIT_SIZE = LONG_FIELD_MAX_LENGTH;
    private static final String DEFAULT_USER_PASSWORD = "bpm";
    private static final char CLEAR_CHAR = '\0';

    CustomUserInfoDefinitions infoDefinitions;

    private NativeTabItemWidget generalTab;
    private NativeTabItemWidget personalTab;
    private NativeTabItemWidget professionnalTab;
    private NativeTabFolderWidget tab;
    private final List<Membership> userMemberShips = new ArrayList<>();
    private NativeTabItemWidget memberShipTab;
    private NativeTabItemWidget customTab;
    private NativeTabItemWidget infoTab;

    TableViewer customUserInfoTable;
    private IObservableList customUserInfoObservableList;
    private CustomUserInformationDefinitionNameEditingSupport customUserInformationDefinitionNameEditingSupport;
    private IViewerObservableValue userSingleSelectionObservable;
    private NativeTabItemWidget userTab;
    private Composite labelComposite;
    private ComboViewer managerNameComboViewer;
    private SelectionAdapter selectionAdapter;

    public UsersWizardPage() {
        super(UsersWizardPage.class.getName());
        setTitle(Messages.displayUsersPageTitle);
        setDescription(Messages.displayUsersPageDesc);
    }

    @Override
    protected void configureViewer(final StructuredViewer viewer) {

        final TableViewer userTableViewer = (TableViewer) viewer;
        final Table table = userTableViewer.getTable();

        userSingleSelectionObservable = ViewersObservables.observeSingleSelection(getViewer());
        userSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final User selectedUser = (User) event.diff.getNewValue();
                final boolean isAUserSelected = selectedUser != null;
                setControlEnabled(getInfoGroup(), isAUserSelected);

                if (isAUserSelected) {
                    if (selectedUser.getPersonalData() == null) {
                        selectedUser.setPersonalData(OrganizationFactory.eINSTANCE.createContactData());
                    }

                    if (selectedUser.getProfessionalData() == null) {
                        selectedUser.setProfessionalData(OrganizationFactory.eINSTANCE.createContactData());
                    }
                }

                final NativeTabItemWidget item = tab.getSelection();
                if (item.equals(memberShipTab)) {
                    disposeTabItemControl(memberShipTab);
                    refreshMembershipTab();
                } else if (item.equals(customTab)) {
                    disposeTabItemControl(customTab);
                    refreshCustomTab();
                }

            }
        });

        addFirstNameColumn(userTableViewer);

        addLastNameColumn(userTableViewer);

        addUserNameColumn(userTableViewer);

        addTableColumLayout(table);

        if (userList != null && getViewer() != null) {
            getViewer().setInput(userList);
        }
    }

    private void addUserNameColumn(final StructuredViewer viewer) {
        final TableViewerColumn column = new TableViewerColumn((TableViewer) viewer, SWT.FILL);
        final TableColumn usernameColumn = column.getColumn();
        column.getColumn().setText(Messages.userName);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((User) element).getUserName();
            }
        });

        column.getColumn().setWidth(90);
        column.getColumn().setMoveable(false);
        column.getColumn().setResizable(true);

        final TableColumnSorter sorter = new TableColumnSorter((TableViewer) viewer);
        sorter.setColumn(usernameColumn);
    }

    private void addLastNameColumn(final StructuredViewer viewer) {
        final TableViewerColumn column = new TableViewerColumn((TableViewer) viewer, SWT.FILL);
        column.getColumn().setText(Messages.lastName);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((User) element).getLastName();
            }
        });

        column.getColumn().setWidth(90);
        column.getColumn().setMoveable(false);
        column.getColumn().setResizable(true);
    }

    private void addFirstNameColumn(final StructuredViewer viewer) {
        final TableViewerColumn column = new TableViewerColumn((TableViewer) viewer, SWT.FILL);
        column.getColumn().setText(Messages.firstName);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((User) element).getFirstName();
            }
        });

        column.getColumn().setWidth(90);
        column.getColumn().setMoveable(false);
        column.getColumn().setResizable(true);
    }

    @Override
    public void setOrganization(final Organization organization) {
        super.setOrganization(organization);
        if (organization != null && getViewer() != null) {
            getViewer().setInput(userList);
            if (customUserInfoTable != null) {
                if (organization.getCustomUserInfoDefinitions() == null) {
                    organization
                            .setCustomUserInfoDefinitions(
                                    OrganizationFactory.eINSTANCE.createCustomUserInfoDefinitions());
                }
                customUserInfoObservableList = EMFProperties
                        .list(OrganizationPackage.Literals.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION)
                        .observe(organization.getCustomUserInfoDefinitions());
                customUserInfoTable.setInput(customUserInfoObservableList);
                customUserInfoObservableList.addListChangeListener(new IListChangeListener() {

                    @Override
                    public void handleListChange(final ListChangeEvent event) {
                        if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                            getViewer().refresh(infoTab);
                        }
                    }
                });
            }
            customUserInformationDefinitionNameEditingSupport.setOrganization(organization);
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
    }

    @Override
    protected void configureInfoGroup(final Group group) {
        group.setText(Messages.details);

        Composite detailsComposite = new Composite(group, SWT.NONE);
        detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        detailsComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        IViewerObservableValue<Object> selection = ViewerProperties.singleSelection().observe(getViewer());
        context.bindValue(WidgetProperties.visible().observe(detailsComposite), new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return selection.getValue() != null;
            }
        });

        Composite generalDetailsComposite = new Composite(detailsComposite, SWT.NONE);
        generalDetailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        generalDetailsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 2).create());

        createUserNameField(generalDetailsComposite);
        createPasswordField(generalDetailsComposite);
        createManagerCombo(generalDetailsComposite);

        tab = new NativeTabFolderWidget.Builder().createIn(detailsComposite);
        tab.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        tab.setLayout(GridLayoutFactory.fillDefaults().create());

        selectionAdapter = new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                Control control = null;
                Object item = e.item;

                if (item.equals(generalTab.getItem())) {
                    disposeTabItemControl(generalTab);
                    final ScrolledComposite sc = createScrolledComposite();
                    control = createGeneralControl(sc);

                    sc.setContent(control);

                    generalTab.setControl(sc);

                } else if (item.equals(personalTab.getItem())) {
                    disposeTabItemControl(personalTab);
                    final ScrolledComposite sc = createScrolledComposite();
                    control = createContactInfoControl(sc, OrganizationPackage.Literals.USER__PERSONAL_DATA);

                    sc.setContent(control);
                    personalTab.setControl(sc);

                } else if (item.equals(professionnalTab.getItem())) {
                    disposeTabItemControl(professionnalTab);
                    final ScrolledComposite sc = createScrolledComposite();
                    control = createContactInfoControl(sc, OrganizationPackage.Literals.USER__PROFESSIONAL_DATA);

                    sc.setContent(control);
                    professionnalTab.setControl(sc);
                }

                else if (item.equals(memberShipTab.getItem())) {
                    disposeTabItemControl(memberShipTab);
                    final ScrolledComposite sc = createScrolledComposite();
                    control = createMembershipControl(sc);
                    sc.setContent(control);
                    memberShipTab.setControl(sc);
                }

                else if (item.equals(customTab.getItem())) {
                    disposeTabItemControl(customTab);
                    final ScrolledComposite sc = createScrolledComposite();
                    control = createCustomControl(sc);
                    sc.setContent(control);

                    customTab.setControl(sc);
                }
            }

        };

        tab.addSelectionListener(selectionAdapter);

        generalTab = new NativeTabItemWidget.Builder()
                .withText(Messages.general)
                .createIn(tab);

        memberShipTab = new NativeTabItemWidget.Builder()
                .withText(Messages.membership + " *")
                .withStyle(SWT.SCROLL_LINE)
                .createIn(tab);

        personalTab = new NativeTabItemWidget.Builder()
                .withText(Messages.personalData)
                .createIn(tab);

        professionnalTab = new NativeTabItemWidget.Builder()
                .withText(Messages.professionalData)
                .createIn(tab);

        customTab = new NativeTabItemWidget.Builder()
                .withText(Messages.other)
                .withStyle(SWT.SCROLL_LINE)
                .createIn(tab);

        tab.setSelection(generalTab);

        setControlEnabled(getInfoGroup(), false);

        refreshGeneralTab();
    }

    private void updatedScrolMinSize(final Control control, final ScrolledComposite sc) {
        final Point point = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        sc.setMinSize(new Point(point.x, point.y));
    }

    private void createManagerCombo(final Composite rightColumnComposite) {
        final Label managerName = new Label(rightColumnComposite, SWT.NONE);
        managerName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        managerName.setText(Messages.manager);

        managerNameComboViewer = new ComboViewer(rightColumnComposite, SWT.BORDER | SWT.READ_ONLY);
        managerNameComboViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).indent(5, 0).create());
        managerNameComboViewer.setLabelProvider(new LabelProvider());
        managerNameComboViewer.setContentProvider(new ObservableListContentProvider());

        userSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final User currentUser = (User) userSingleSelectionObservable.getValue();
                if (userList != null) {
                    final WritableList users = new WritableList();
                    users.add("");
                    for (final User u : userList) {
                        if (!u.equals(currentUser)) {
                            users.add(u.getUserName());
                        }
                    }

                    managerNameComboViewer.setInput(users);
                    if (currentUser != null && currentUser.getManager() != null) {
                        managerNameComboViewer.setSelection(new StructuredSelection(currentUser.getManager()));
                    } else {
                        managerNameComboViewer.setSelection(new StructuredSelection(""));
                    }
                }
            }
        });

        managerNameComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final String eventObject = (String) ((StructuredSelection) event.getSelection()).getFirstElement();
                String selectedManager = "";
                if (!isNullOrEmpty(eventObject)) {
                    selectedManager = eventObject;
                }
                final User selectedUser = (User) userSingleSelectionObservable.getValue();
                if (selectedUser != null) {
                    selectedUser.setManager(selectedManager);
                }
            }
        });
    }

    private void createPasswordField(final Composite rightColumnComposite) {
        final Label passwordLabel = new Label(rightColumnComposite, SWT.NONE);
        passwordLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        passwordLabel.setText(Messages.password + " *");

        Composite passwordComposite = new Composite(rightColumnComposite, SWT.NONE);
        passwordComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        passwordComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Text passwordText = new Text(passwordComposite, SWT.BORDER | SWT.PASSWORD);
        passwordText.setLayoutData(
                GridDataFactory.swtDefaults()
                        .align(SWT.FILL, SWT.CENTER)
                        .grab(true, false)
                        .indent(5, 0)
                        .span(Platform.OS_MACOSX.equals(Platform.getOS()) ? 2 : 1, 1)
                        .create());
        char echoChar = passwordText.getEchoChar();

        if (!"macosx".equals(Platform.getOS())) {
            final Button viewPaswsordButton = new Button(passwordComposite, SWT.TOGGLE);
            viewPaswsordButton.setLayoutData(GridDataFactory.swtDefaults().create());
            viewPaswsordButton.setImage(Pics.getImage("view.png", IdentityPlugin.getDefault()));
            viewPaswsordButton.setToolTipText(Messages.showPassword);
            viewPaswsordButton.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    passwordText.setEchoChar(viewPaswsordButton.getSelection() ? CLEAR_CHAR : echoChar);
                }
            });
        }

        final UpdateValueStrategy mandatoryStrategy = new UpdateValueStrategy();
        mandatoryStrategy
                .setAfterGetValidator(new UserEmptyInputValidator(Messages.password, userSingleSelectionObservable));

        final IObservableValue userPasswordObservableValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                userSingleSelectionObservable,
                OrganizationPackage.Literals.USER__PASSWORD);

        final IObservableValue passwordValueObservableValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                userPasswordObservableValue,
                OrganizationPackage.Literals.PASSWORD_TYPE__VALUE);
        passwordValueObservableValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final IObservableValue value = event.getObservableValue();
                final PasswordType password = (PasswordType) ((DetailObservableValue) value).getObserved();
                if (password != null) {
                    password.setEncrypted(false);
                }
            }
        });

        final Binding binding = context.bindValue(SWTObservables.observeText(passwordText, SWT.Modify),
                passwordValueObservableValue, mandatoryStrategy, null);
        ControlDecorationSupport.create(binding, SWT.LEFT, rightColumnComposite, new ControlDecorationUpdater() {

            @Override
            protected void update(final ControlDecoration decoration, final IStatus status) {
                if (userSingleSelectionObservable.getValue() != null) {
                    super.update(decoration, status);
                }
            }
        });

    }

    private void createUserNameField(final Composite rightColumnComposite) {
        final Label userName = new Label(rightColumnComposite, SWT.NONE);
        userName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        userName.setText(Messages.userName + " *");

        final Text usernameText = new Text(rightColumnComposite, SWT.BORDER);
        usernameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 0).create());
        usernameText.setMessage(Messages.userNameHint);

        final UpdateValueStrategy stategy = new UpdateValueStrategy();
        stategy.setConverter(new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object from) {
                if (from != null && userSingleSelectionObservable != null
                        && userSingleSelectionObservable.getValue() != null) {
                    final User user = (User) userSingleSelectionObservable.getValue();
                    updateDelegueeMembership(user.getUserName(), from.toString());
                }
                return from;
            }
        });
        stategy.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {

                if (isNotUserSelected()) {
                    return Status.OK_STATUS;
                }

                if (value.toString().isEmpty()) {
                    return ValidationStatus.error(Messages.userNameIsEmpty);
                }

                if (value.toString().length() > NAME_SIZE) {
                    return ValidationStatus.error(Messages.nameLimitSize);
                }

                final User currentUser = (User) userSingleSelectionObservable.getValue();
                for (final User u : userList) {
                    if (!u.equals(currentUser)) {
                        if (u.getUserName().equals(value)) {
                            return ValidationStatus.error(Messages.userNameAlreadyExists);
                        }
                    }
                }
                return Status.OK_STATUS;
            }
        });

        final IObservableValue userNameValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                userSingleSelectionObservable,
                OrganizationPackage.Literals.USER__USER_NAME);
        final Binding binding = context.bindValue(SWTObservables.observeText(usernameText, SWT.Modify), userNameValue,
                stategy, null);
        ControlDecorationSupport.create(binding, SWT.LEFT, rightColumnComposite, new ControlDecorationUpdater() {

            @Override
            protected void update(final ControlDecoration decoration, final IStatus status) {
                if (userSingleSelectionObservable.getValue() != null) {
                    super.update(decoration, status);
                }
            }
        });

        userNameValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                handleUserNameChange(event);
            }

        });
    }

    private void updateDelegueeMembership(final String oldUserName, final String newUserName) {
        for (final Membership m : membershipList) {
            if (oldUserName.equals(m.getUserName())) {
                m.setUserName(newUserName);
            }
        }
    }

    private void handleFirstNameChange(final ValueChangeEvent event) {
        final User user = (User) userSingleSelectionObservable.getValue();
        if (user != null) {
            final User oldUser = EcoreUtil.copy(user);
            final Object oldValue = event.diff.getOldValue();
            if (oldValue != null && oldUser != null) {
                oldUser.setFirstName(oldValue.toString());
                if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                    getViewer().refresh(user);
                }
            }
        }
    }

    private void handleLastNameChange(final ValueChangeEvent event) {
        final User user = (User) userSingleSelectionObservable.getValue();
        if (user != null) {
            final User oldUser = EcoreUtil.copy(user);
            final Object oldValue = event.diff.getOldValue();
            if (oldValue != null) {
                oldUser.setLastName(oldValue.toString());
                if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                    getViewer().refresh(user);
                }
            }
        }
    }

    private void handleUserNameChange(final ValueChangeEvent event) {
        final User user = (User) userSingleSelectionObservable.getValue();
        if (user != null) {
            final User oldUser = EcoreUtil.copy(user);
            final Object oldValue = event.diff.getOldValue();
            if (oldValue != null) {
                if (oldUser != null) {
                    oldUser.setUserName(oldValue.toString());
                }
                if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                    getViewer().refresh(user);
                }
            }
        }
    }

    /**
     * @return
     */
    private ScrolledComposite createScrolledComposite() {
        final ScrolledComposite sc = new ScrolledComposite(tab.getTabFolder(), SWT.V_SCROLL);
        sc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).create());
        sc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, SWT.DEFAULT).create());
        sc.setExpandHorizontal(true);
        sc.setExpandVertical(true);
        return sc;
    }

    protected Control createCustomControl(final Composite parent) {

        final Composite otherInfoComposite = new Composite(parent, SWT.NONE);
        otherInfoComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).equalWidth(false).create());
        otherInfoComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, SWT.DEFAULT).create());

        final User selectedUser = (User) userSingleSelectionObservable.getValue();
        if (selectedUser != null) {

            if (selectedUser.getCustomUserInfoValues() == null) {
                selectedUser.setCustomUserInfoValues(OrganizationFactory.eINSTANCE.createCustomUserInfoValuesType());
            }

            final IObservableValue customUserInfoValuesValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                    userSingleSelectionObservable,
                    OrganizationPackage.Literals.USER__CUSTOM_USER_INFO_VALUES);

            final IObservableList customUserInfoListValue = EMFObservables.observeDetailList(Realm.getDefault(),
                    customUserInfoValuesValue,
                    OrganizationPackage.Literals.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE);

            final EList<CustomUserInfoValue> customUserInfoValueList = sortCustomUserInfoValues(selectedUser);
            for (final CustomUserInfoValue infoValue : customUserInfoValueList) {

                final UpdateValueStrategy strategy = new UpdateValueStrategy();
                strategy.setAfterGetValidator(new IValidator() {

                    @Override
                    public IStatus validate(final Object arg0) {
                        final String value = (String) arg0;
                        if (value.length() > CUSTOM_USER_DEFINITION_VALUE_LIMIT_SIZE) {
                            return ValidationStatus.error(Messages.customUserInfoValueLimitSize);
                        }
                        return ValidationStatus.ok();
                    }
                });

                final Label labelName = new Label(otherInfoComposite, SWT.LEFT | SWT.WRAP);
                labelName.setLayoutData(GridDataFactory.fillDefaults().hint(150, SWT.DEFAULT).create());
                labelName.setText(infoValue.getName());

                final Text textValue = new Text(otherInfoComposite, SWT.BORDER);
                textValue.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

                context.bindValue(SWTObservables.observeText(textValue, SWT.Modify),
                        EMFObservables.observeValue(infoValue,
                                OrganizationPackage.Literals.CUSTOM_USER_INFO_VALUE__VALUE),
                        strategy, null);

            }
        }

        // LINK
        final Link addInfoLink = new Link(otherInfoComposite, SWT.NONE);
        addInfoLink.setText("<A>" + Messages.customUserInfoOtherTabLink + "</A>");
        addInfoLink.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                tabFolder.setSelection(infoTab);
                getViewer().refresh(infoTab);
            }

        });
        addInfoLink.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

        return otherInfoComposite;
    }

    private EList<CustomUserInfoValue> sortCustomUserInfoValues(final User selectedUser) {
        final EList<CustomUserInfoValue> customUserInfoValueList = selectedUser.getCustomUserInfoValues()
                .getCustomUserInfoValue();
        ECollections.sort(customUserInfoValueList, new Comparator<CustomUserInfoValue>() {

            @Override
            public int compare(final CustomUserInfoValue o1, final CustomUserInfoValue o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return customUserInfoValueList;
    }

    protected Control createMembershipControl(final Composite parent) {

        final Composite detailsInfoComposite = new Composite(parent, SWT.NONE);
        detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        detailsInfoComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(5).margins(5, 5).equalWidth(false).create());

        final User selectedUser = (User) userSingleSelectionObservable.getValue();
        if (selectedUser != null) {
            userMemberShips.clear();
            for (final Membership m : membershipList) {
                if (m.getUserName().equals(selectedUser.getUserName())) {
                    userMemberShips.add(m);
                }
            }

            for (final Membership membership : userMemberShips) {

                createMembershipGroupCombo(detailsInfoComposite, membership);
                createMemberShipRoleCombo(detailsInfoComposite, membership);

                final Button removeMembershipButton = new Button(detailsInfoComposite, SWT.FLAT);
                removeMembershipButton.setVisible(userMemberShips.size() > 1);
                removeMembershipButton.setImage(Pics.getImage("delete.png"));
                removeMembershipButton.setToolTipText(Messages.delete);
                removeMembershipButton.setLayoutData(GridDataFactory.swtDefaults().create());
                removeMembershipButton.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        removeMembershipAction(membership);
                    }
                });
            }
        }

        final Button addMembershipButton = new Button(detailsInfoComposite, SWT.PUSH);
        addMembershipButton.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).span(5, 1).align(SWT.END, SWT.CENTER).create());
        addMembershipButton.setText(Messages.addMembership);
        addMembershipButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addMembershipAction();
            }
        });

        return detailsInfoComposite;
    }

    protected void addMembershipAction() {
        final Membership m = OrganizationFactory.eINSTANCE.createMembership();
        final User u = (User) ((IStructuredSelection) getViewer().getSelection()).getFirstElement();
        m.setUserName(u.getUserName());
        membershipList.add(m);
        final Event ev = new Event();
        ev.item = tab.getSelection().getItem();
        tab.notifyListeners(SWT.Selection, ev);
    }

    private void removeMembershipAction(final Membership membership) {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteMembershipTitle,
                Messages.deleteMembershipMsg)) {
            membershipList.remove(membership);
            final Event ev = new Event();
            ev.item = tab.getSelection().getItem();
            tab.notifyListeners(SWT.Selection, ev);
        }
    }

    private void createMemberShipRoleCombo(final Composite detailsInfoComposite, final Membership membership) {

        final Label roleName = new Label(detailsInfoComposite, SWT.NONE);
        roleName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        roleName.setText(Messages.role);

        final Combo roleNameCombo = new Combo(detailsInfoComposite, SWT.BORDER | SWT.READ_ONLY);
        roleNameCombo
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create());

        for (final org.bonitasoft.studio.identity.organization.model.organization.Role role : roleList) {
            roleNameCombo.add(role.getName());
        }

        final UpdateValueStrategy targetStrategy = new UpdateValueStrategy();
        targetStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.emtpyMembershipValue));

        final IObservableValue membershipRoleValue = EMFObservables.observeValue(membership,
                OrganizationPackage.Literals.MEMBERSHIP__ROLE_NAME);
        final Binding binding = context.bindValue(SWTObservables.observeText(roleNameCombo), membershipRoleValue,
                targetStrategy, null);
        ControlDecorationSupport.create(binding, SWT.LEFT, detailsInfoComposite);
    }

    private void createMembershipGroupCombo(final Composite detailsInfoComposite,
            final Membership membership) {

        final Label groupName = new Label(detailsInfoComposite, SWT.NONE);
        groupName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        groupName.setText(Messages.groupName);

        final ComboViewer groupNameCombo = new ComboViewer(detailsInfoComposite, SWT.BORDER | SWT.READ_ONLY);
        groupNameCombo.getCombo()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create());
        groupNameCombo.setContentProvider(new ObservableListContentProvider());
        groupNameCombo.setLabelProvider(new OrganizationGroupLabelProvider());
        //        for(final org.bonitasoft.studio.actors.model.organization.Group g : groupList){
        //            groupNameCombo.add(GroupContentProvider.getGroupPath(g)) ;
        //        }

        final IObservableList observeGroupList = EMFObservables.observeList(organization.getGroups(),
                OrganizationPackage.Literals.GROUPS__GROUP);

        final UpdateValueStrategy targetStrategy = new UpdateValueStrategy();
        targetStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.emtpyMembershipValue));
        targetStrategy
                .setConverter(new Converter(org.bonitasoft.studio.identity.organization.model.organization.Group.class, String.class) {

                    @Override
                    public Object convert(final Object from) {
                        final org.bonitasoft.studio.identity.organization.model.organization.Group group = (org.bonitasoft.studio.identity.organization.model.organization.Group) from;
                        if (group != null) {
                            if (group.getParentPath() == null || group.getParentPath().isEmpty()) {
                                membership.setGroupParentPath(null);
                            } else {
                                membership.setGroupParentPath(group.getParentPath());
                            }
                            return group.getName();
                        } else {
                            return "";
                        }
                    }
                });

        final UpdateValueStrategy modelStrategy = new UpdateValueStrategy();
        modelStrategy
                .setConverter(new Converter(String.class, org.bonitasoft.studio.identity.organization.model.organization.Group.class) {

                    @Override
                    public Object convert(final Object from) {
                        final String groupName = (String) from;
                        final Iterator<org.bonitasoft.studio.identity.organization.model.organization.Group> iterator = observeGroupList
                                .iterator();
                        while (iterator.hasNext()) {
                            final org.bonitasoft.studio.identity.organization.model.organization.Group group = iterator.next();
                            if (group.getName().equals(groupName)) {
                                final String gParentPath = group.getParentPath();
                                final String mGroupParentPath = membership.getGroupParentPath();
                                if (gParentPath == null && mGroupParentPath == null
                                        || gParentPath.equals(mGroupParentPath)) {
                                    return group;
                                }
                            }

                        }
                        return null;

                    }
                });

        groupNameCombo.setInput(observeGroupList);
        final IObservableValue membershipValue = EMFObservables.observeValue(membership,
                OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME);
        final Binding binding = context.bindValue(ViewersObservables.observeSingleSelection(groupNameCombo),
                membershipValue,
                targetStrategy, modelStrategy);
        ControlDecorationSupport.create(binding, SWT.LEFT, detailsInfoComposite);
    }

    protected Control createContactInfoControl(final Composite parent, final EReference ref) {

        final Composite detailsInfoComposite = new Composite(parent, SWT.NONE);

        detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).minSize(0, 0).create());
        detailsInfoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(false).create());

        createEmailField(detailsInfoComposite, ref);
        createPhoneField(detailsInfoComposite, ref);
        createMobileField(detailsInfoComposite, ref);
        createFaxField(detailsInfoComposite, ref);
        createWebSiteField(detailsInfoComposite, ref);
        createBuildingInfoFields(detailsInfoComposite, ref);
        createAddressField(detailsInfoComposite, ref);
        createAddressInfoFields(detailsInfoComposite, ref);
        createCountryField(detailsInfoComposite, ref);
        return detailsInfoComposite;

    }

    private void createFaxField(final Composite detailsInfoComposite, final EReference reference) {
        final Label faxLabel = new Label(detailsInfoComposite, SWT.NONE);
        faxLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        faxLabel.setText(Messages.faxLabel);

        final Text faxText = new Text(detailsInfoComposite, SWT.BORDER);
        faxText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        faxText.setMessage(Messages.faxHint);
        bindTextToUserAttribute(faxText, reference, OrganizationPackage.Literals.CONTACT_DATA__FAX_NUMBER,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.faxLabel, SHORT_FIELD_MAX_LENGTH)).create());
    }

    private void createMobileField(final Composite detailsInfoComposite, final EReference reference) {
        final Label mobileLabel = new Label(detailsInfoComposite, SWT.NONE);
        mobileLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        mobileLabel.setText(Messages.mobileLabel);

        final Text mobileText = new Text(detailsInfoComposite, SWT.BORDER);
        mobileText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mobileText.setMessage(Messages.mobileHint);
        bindTextToUserAttribute(mobileText, reference, OrganizationPackage.Literals.CONTACT_DATA__MOBILE_NUMBER,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.mobileLabel, SHORT_FIELD_MAX_LENGTH)).create());
    }

    private void createPhoneField(final Composite detailsInfoComposite, final EReference reference) {
        final Label phoneLabel = new Label(detailsInfoComposite, SWT.NONE);
        phoneLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        phoneLabel.setText(Messages.phoneLabel);

        final Text phoneText = new Text(detailsInfoComposite, SWT.BORDER);
        phoneText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        phoneText.setMessage(Messages.phoneHint);
        bindTextToUserAttribute(phoneText, reference, OrganizationPackage.Literals.CONTACT_DATA__PHONE_NUMBER,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.phoneLabel, SHORT_FIELD_MAX_LENGTH)).create());
    }

    private void createWebSiteField(final Composite detailsInfoComposite, final EReference reference) {
        final Label websiteLabel = new Label(detailsInfoComposite, SWT.NONE);
        websiteLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        websiteLabel.setText(Messages.websiteLabel);

        final Text websiteText = new Text(detailsInfoComposite, SWT.BORDER);
        websiteText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        websiteText.setMessage(Messages.websiteHint);
        bindTextToUserAttribute(websiteText, reference, OrganizationPackage.Literals.CONTACT_DATA__WEBSITE,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.websiteLabel, LONG_FIELD_MAX_LENGTH)).create());
    }

    private void createBuildingInfoFields(final Composite detailsInfoComposite, final EReference reference) {
        final Label buildingLabel = new Label(detailsInfoComposite, SWT.NONE);
        buildingLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        buildingLabel.setText(Messages.buildingLabel);

        final Composite buildingInfo = new Composite(detailsInfoComposite, SWT.NONE);
        buildingInfo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        buildingInfo.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).spacing(2, 0).equalWidth(false).create());

        final Text buildingText = new Text(buildingInfo, SWT.BORDER);
        buildingText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        buildingText.setMessage(Messages.buildingHint);
        bindTextToUserAttribute(buildingText, reference, OrganizationPackage.Literals.CONTACT_DATA__BUILDING,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.buildingLabel, SHORT_FIELD_MAX_LENGTH)).create());

        createPersonalRoomField(buildingInfo, reference);
    }

    private void createPersonalRoomField(final Composite buildingInfo, final EReference reference) {
        final Label roomLabel = new Label(buildingInfo, SWT.NONE);
        roomLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        roomLabel.setText(Messages.roomLabel);

        final Text roomText = new Text(buildingInfo, SWT.BORDER);
        roomText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        roomText.setMessage(Messages.roomHint);
        bindTextToUserAttribute(roomText, reference, OrganizationPackage.Literals.CONTACT_DATA__ROOM,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.roomLabel, SHORT_FIELD_MAX_LENGTH)).create());
    }

    private void createAddressField(final Composite detailsInfoComposite, final EReference reference) {
        final Label adressLabel = new Label(detailsInfoComposite, SWT.NONE);
        adressLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        adressLabel.setText(Messages.addressLabel);

        final Text adressText = new Text(detailsInfoComposite, SWT.BORDER);
        adressText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        adressText.setMessage(Messages.addressHint);
        bindTextToUserAttribute(adressText, reference, OrganizationPackage.Literals.CONTACT_DATA__ADDRESS,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.addressLabel, LONG_FIELD_MAX_LENGTH)).create());
    }

    private void createAddressInfoFields(final Composite detailsInfoComposite, final EReference reference) {
        final Label cityLabel = new Label(detailsInfoComposite, SWT.NONE);
        cityLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        cityLabel.setText(Messages.cityLabel);

        final Composite addressInfo = new Composite(detailsInfoComposite, SWT.NONE);
        addressInfo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addressInfo.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(5).margins(0, 0).spacing(2, 0).equalWidth(false).create());

        final Text cityText = new Text(addressInfo, SWT.BORDER);
        cityText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        cityText.setMessage(Messages.cityHint);
        bindTextToUserAttribute(cityText, reference, OrganizationPackage.Literals.CONTACT_DATA__CITY,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.cityLabel, LONG_FIELD_MAX_LENGTH)).create());

        createPersonalStateField(addressInfo, reference);

        createPersonalZipCodeField(addressInfo, reference);
    }

    private void createPersonalStateField(final Composite addressInfo, final EReference reference) {
        final Label stateLabel = new Label(addressInfo, SWT.NONE);
        stateLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        stateLabel.setText(Messages.stateLabel);

        final Text stateText = new Text(addressInfo, SWT.BORDER);
        stateText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        stateText.setMessage(Messages.stateHint);
        bindTextToUserAttribute(stateText, reference, OrganizationPackage.Literals.CONTACT_DATA__STATE,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.stateLabel, LONG_FIELD_MAX_LENGTH)).create());
    }

    private void createPersonalZipCodeField(final Composite addressInfo, final EReference reference) {
        final Label zipcodeLabel = new Label(addressInfo, SWT.NONE);
        zipcodeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        zipcodeLabel.setText(Messages.zipCodeLabel);

        final Text zipCodeText = new Text(addressInfo, SWT.BORDER);
        zipCodeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        zipCodeText.setMessage(Messages.zipCodeHint);
        bindTextToUserAttribute(zipCodeText, reference, OrganizationPackage.Literals.CONTACT_DATA__ZIP_CODE,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.zipCodeLabel, SHORT_FIELD_MAX_LENGTH)).create());
    }

    private void createEmailField(final Composite detailsInfoComposite, final EReference reference) {
        final Label emailLabel = new Label(detailsInfoComposite, SWT.NONE);
        emailLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        emailLabel.setText(Messages.emailLabel);

        final Text emailText = new Text(detailsInfoComposite, SWT.BORDER);
        emailText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        emailText.setMessage(Messages.emailHint);

        bindTextToUserAttribute(emailText, reference, OrganizationPackage.Literals.CONTACT_DATA__EMAIL,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.countryLabel, LONG_FIELD_MAX_LENGTH)).create());

    }

    private void createCountryField(final Composite detailsInfoComposite, final EReference reference) {
        final Label countryLabel = new Label(detailsInfoComposite, SWT.NONE);
        countryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        countryLabel.setText(Messages.countryLabel);

        final Text countryText = new Text(detailsInfoComposite, SWT.BORDER);
        countryText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        countryText.setMessage(Messages.coutryHint);

        bindTextToUserAttribute(countryText, reference, OrganizationPackage.Literals.CONTACT_DATA__COUNTRY,
                updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.countryLabel, LONG_FIELD_MAX_LENGTH)).create());
    }

    private Binding bindTextToUserAttribute(final Text text, final EReference reference, final EAttribute attribute,
            final UpdateValueStrategy targetUpdateValueStrategy) {
        final IObservableValue personalDataValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                userSingleSelectionObservable, reference);
        final IObservableValue propertyUserValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                personalDataValue,
                attribute);
        return context.bindValue(SWTObservables.observeText(text, SWT.Modify), propertyUserValue,
                targetUpdateValueStrategy,
                null);
    }

    protected Control createGeneralControl(final Composite parent) {

        final Composite detailsInfoComposite = new Composite(parent, SWT.NONE);
        detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        detailsInfoComposite
                .setLayout(GridLayoutFactory.swtDefaults().numColumns(2).margins(5, 5).equalWidth(false).create());

        createGeneralTitleField(detailsInfoComposite);

        createGeneralFirstNameField(detailsInfoComposite);

        createGeneralLastNameField(detailsInfoComposite);

        createGeneralJobLabel(detailsInfoComposite);

        return detailsInfoComposite;
    }

    private void createGeneralJobLabel(final Composite detailsInfoComposite) {
        final Label jobLabel = new Label(detailsInfoComposite, SWT.NONE);
        jobLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        jobLabel.setText(Messages.jobLabel);

        final Text jobText = new Text(detailsInfoComposite, SWT.BORDER);
        jobText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        jobText.setMessage(Messages.jobHint);
        final IObservableValue jobUserValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                userSingleSelectionObservable,
                OrganizationPackage.Literals.USER__JOB_TITLE);
        context.bindValue(SWTObservables.observeText(jobText, SWT.Modify), jobUserValue);

    }

    private void createGeneralLastNameField(final Composite detailsInfoComposite) {
        final Label lastName = new Label(detailsInfoComposite, SWT.NONE);
        lastName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        lastName.setText(Messages.lastName);

        final Text lastNameText = new Text(detailsInfoComposite, SWT.BORDER);
        lastNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        lastNameText.setMessage(Messages.lastNameHint);

        final IObservableValue lastNameUserValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                userSingleSelectionObservable,
                OrganizationPackage.Literals.USER__LAST_NAME);
        context.bindValue(SWTObservables.observeText(lastNameText, SWT.Modify), lastNameUserValue);

        lastNameUserValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                handleLastNameChange(event);
            }
        });
    }

    private void createGeneralFirstNameField(final Composite detailsInfoComposite) {
        final Label firstName = new Label(detailsInfoComposite, SWT.NONE);
        firstName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        firstName.setText(Messages.firstName);

        final Text firstNameText = new Text(detailsInfoComposite, SWT.BORDER);
        firstNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        firstNameText.setMessage(Messages.firstNameHint);

        final IObservableValue firstNameUserValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                userSingleSelectionObservable,
                OrganizationPackage.Literals.USER__FIRST_NAME);
        context.bindValue(SWTObservables.observeText(firstNameText, SWT.Modify), firstNameUserValue);

        firstNameUserValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                handleFirstNameChange(event);
            }
        });
    }

    private void createGeneralTitleField(final Composite detailsInfoComposite) {
        final Label titleLabel = new Label(detailsInfoComposite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        titleLabel.setText(Messages.userTitle);

        final Text titleText = new Text(detailsInfoComposite, SWT.BORDER);
        titleText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleText.setMessage(Messages.titleHint);
        //		widgetMap.put(OrganizationPackage.Literals.USER__TITLE, titleText) ;

        final IObservableValue titleUserValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                userSingleSelectionObservable,
                OrganizationPackage.Literals.USER__TITLE);
        context.bindValue(SWTObservables.observeText(titleText, SWT.Modify), titleUserValue);

    }

    @Override
    protected void addButtonSelected() {
        final User user = OrganizationFactory.eINSTANCE.createUser();
        user.setUserName(generateUsername());
        user.setPassword(createPassword(DEFAULT_USER_PASSWORD));
        user.setCustomUserInfoValues(OrganizationFactory.eINSTANCE.createCustomUserInfoValuesType());
        for (final CustomUserInfoDefinition definitions : organization.getCustomUserInfoDefinitions()
                .getCustomUserInfoDefinition()) {
            final CustomUserInfoValue newValue = OrganizationFactory.eINSTANCE.createCustomUserInfoValue();
            newValue.setName(definitions.getName());
            newValue.setValue("");
            user.getCustomUserInfoValues().getCustomUserInfoValue().add(newValue);
        }

        //add a default empty membership for the new User
        final Membership defaultMembershipForNewUser = OrganizationFactory.eINSTANCE.createMembership();
        defaultMembershipForNewUser.setUserName(user.getUserName());
        membershipList.add(defaultMembershipForNewUser);

        userList.add(user);
        getViewer().setInput(userList);
        getViewer().setSelection(new StructuredSelection(user));
    }

    private PasswordType createPassword(final String defaultUserPassword) {
        final PasswordType password = OrganizationFactory.eINSTANCE.createPasswordType();
        password.setEncrypted(false);
        password.setValue(defaultUserPassword);
        return password;
    }

    private String generateUsername() {
        final Set<String> names = new HashSet<>();
        for (final User u : userList) {
            names.add(u.getUserName());
        }

        return NamingUtils.generateNewName(names, Messages.defaultUserName, 1);
    }

    @Override
    protected void removeButtonSelected() {
        for (final Object sel : ((IStructuredSelection) getViewer().getSelection()).toList()) {
            if (sel instanceof User) {
                final List<Membership> toRemove = new ArrayList<>();
                for (final Membership m : membershipList) {
                    if (m.getUserName().equals(((User) sel).getUserName())) {
                        toRemove.add(m);
                    }
                }
                membershipList.removeAll(toRemove);
                userList.remove(sel);
            }
        }
        getViewer().setInput(userList);
        selectionChanged(new SelectionChangedEvent(getViewer(), new StructuredSelection()));
    }

    @Override
    protected boolean viewerSelect(final Object element, final String searchQuery) {
        if (searchQuery == null || searchQuery.isEmpty()
                || ((User) element).getUserName() != null
                        && ((User) element).getUserName().toLowerCase().contains(searchQuery.toLowerCase())
                || ((User) element).getFirstName() != null
                        && ((User) element).getFirstName().toLowerCase().contains(searchQuery.toLowerCase())
                || ((User) element).getLastName() != null
                        && ((User) element).getLastName().toLowerCase().contains(searchQuery.toLowerCase())) {
            return true;
        }
        return false;
    }

    @Override
    public void createControl(final Composite parent) {

        tabFolder = new NativeTabFolderWidget.Builder()
                .createIn(parent);
        //new TabFolder(parent, SWT.TOP);
        tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        tabFolder.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (e.item.equals(userTab.getItem())) {
                    refreshCustomTab();
                    refreshMembershipTab();
                }
            }

        });

        Composite userTabComposite = new Composite(tabFolder.getTabFolder(), SWT.NONE);
        userTabComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        userTabComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        userTab = new NativeTabItemWidget.Builder()
                .withText(Messages.listOfUsersTabTitle)
                .withControl(userTabComposite)
                .createIn(tabFolder);

        super.createControl(userTabComposite);
        super.setControl(userTabComposite);

        infoTab = new NativeTabItemWidget.Builder()
                .withText(Messages.customUserInformationTabTitle)
                .withControl(addInformationComposite())
                .createIn(tabFolder);

        tabFolder.setSelection(userTab);
    }

    private void refreshCustomTab() {
        final ScrolledComposite sc = createScrolledComposite();
        final Control control = createCustomControl(sc);
        updatedScrolMinSize(control, sc);
        sc.setContent(control);
        customTab.setControl(sc);
    }

    private void refreshGeneralTab() {
        final ScrolledComposite sc = createScrolledComposite();
        final Control control = createGeneralControl(sc);
        updatedScrolMinSize(control, sc);

        sc.setContent(control);
        generalTab.setControl(sc);
    }

    private void refreshMembershipTab() {
        final ScrolledComposite sc = createScrolledComposite();

        final Control control = createMembershipControl(sc);
        updatedScrolMinSize(control, sc);

        sc.setContent(control);
        memberShipTab.setControl(sc);
    }

    private Composite addInformationComposite() {
        final Composite infoCompo = new Composite(tabFolder.getTabFolder(), SWT.NONE);
        infoCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        infoCompo.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());

        //add label

        labelComposite = new Composite(infoCompo, SWT.NONE);
        labelComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        labelComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Label labelCustomUserInfo = new Label(labelComposite, SWT.WRAP);
        labelCustomUserInfo.setText(Messages.labelExplicationCustomUserInformation);
        labelCustomUserInfo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Composite groupComposite = new Composite(infoCompo, SWT.NONE);
        groupComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        groupComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(10, 0).equalWidth(true).create());

        // Group Default Information
        final Group defaultGroup = new Group(groupComposite, SWT.FILL);
        setDefaultUserInformationGroup(defaultGroup);

        // Group Other informations
        final Group otherGroup = new Group(groupComposite, SWT.FILL);
        setOtherGroup(otherGroup);

        return infoCompo;
    }

    protected void setOtherGroup(final Group otherGroup) {

        otherGroup.setText(Messages.otherInformationGroupTitle);
        otherGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        otherGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Composite otherGroupComposite = new Composite(otherGroup, SWT.NONE);
        otherGroupComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        otherGroupComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).equalWidth(false).create());

        createCustomUserInfoTableButtonComposite(otherGroupComposite);

        createCustomUserInformationTableComposite(otherGroupComposite);

    }

    private void createCustomUserInfoTableButtonComposite(final Composite otherGroupComposite) {
        // BUTTONS
        final Composite buttons = new Composite(otherGroupComposite, SWT.NONE);
        buttons.setLayoutData(GridDataFactory.fillDefaults().indent(0, 25).create());
        buttons.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(true).spacing(0, 2).create());

        final GridData gridDataButton = GridDataFactory.fillDefaults().grab(true, false).create();

        // ADD BUTTON
        final Button addInfoButton = new Button(buttons, SWT.FLAT);
        addInfoButton.setLayoutData(gridDataButton);
        addInfoButton.setText(Messages.otherInformationGroupAddButton);
        addInfoButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addCustomUserInfoDefinitionAction();
            }
        });

        final Button removeInfoButton = new Button(buttons, SWT.FLAT);
        removeInfoButton.setLayoutData(gridDataButton);
        removeInfoButton.setText(Messages.otherInformationGroupRemoveButton);
        removeInfoButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event arg0) {
                final List<CustomUserInfoDefinition> definitions = ((IStructuredSelection) customUserInfoTable
                        .getSelection()).toList();
                String listDef = "";
                for (final CustomUserInfoDefinition def : definitions) {
                    listDef = listDef + def.getName() + "\n";
                }

                listDef += "\n\n" + Messages.otherInformationGroupRemoveDialogTextWarning;
                if (!definitions.isEmpty()
                        && MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                                Messages.otherInformationGroupRemoveDialogTitle,
                                Messages.bind(Messages.otherInformationGroupRemoveDialogText, listDef))) {

                    customUserInfoObservableList.removeAll(definitions);
                    for (final User user : organization.getUsers().getUser()) {
                        final List<CustomUserInfoValue> toRemove = new ArrayList<>();
                        for (final CustomUserInfoDefinition def : definitions) {
                            for (final CustomUserInfoValue v : user.getCustomUserInfoValues()
                                    .getCustomUserInfoValue()) {
                                if (v.getName().equals(def.getName())) {
                                    toRemove.add(v);
                                }
                            }
                        }
                        user.getCustomUserInfoValues().getCustomUserInfoValue().removeAll(toRemove);
                    }
                }
            }
        });
    }

    private void createCustomUserInformationTableComposite(final Composite otherGroupComposite) {

        final Composite tableComposite = new Composite(otherGroupComposite, SWT.NONE);
        tableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        tableComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).create());

        // TABLE VIEWER Custom User Definitions
        customUserInfoTable = new TableViewer(tableComposite, SWT.H_SCROLL | SWT.V_SCROLL |
                SWT.FULL_SELECTION | SWT.BORDER | SWT.MULTI);
        customUserInfoTable.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Table table = customUserInfoTable.getTable();
        table.setHeaderVisible(true);
        customUserInfoTable.setContentProvider(new ObservableListContentProvider());

        final TableViewerColumn nameColumn = new TableViewerColumn(customUserInfoTable, SWT.NONE);
        final TableColumnSorter sorter = new TableColumnSorter(customUserInfoTable);
        sorter.setColumn(nameColumn.getColumn());
        nameColumn.getColumn().setText(Messages.customUserInfoName + " *");
        nameColumn.getColumn().setWidth(100);
        customUserInformationDefinitionNameEditingSupport = new CustomUserInformationDefinitionNameEditingSupport(
                nameColumn.getViewer(), context);
        nameColumn.setEditingSupport(customUserInformationDefinitionNameEditingSupport);
        nameColumn.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((CustomUserInfoDefinition) element).getName();
            }
        });

        final TableViewerColumn descriptionColumn = new TableViewerColumn(customUserInfoTable, SWT.NONE);
        descriptionColumn.getColumn().setText(Messages.customUserInfoDescription);
        descriptionColumn.getColumn().setWidth(100);
        descriptionColumn.setEditingSupport(
                new CustomerUserInformationDefinitionDescriptionEditingSupport(nameColumn.getViewer(), context));
        descriptionColumn.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((CustomUserInfoDefinition) element).getDescription();
            }
        });

        final TableColumnLayout tcLayout = new TableColumnLayout();
        tcLayout.setColumnData(table.getColumn(0), new ColumnWeightData(1));
        tcLayout.setColumnData(table.getColumn(1), new ColumnWeightData(2));
        table.getParent().setLayout(tcLayout);
    }

    public void addCustomUserInfoDefinitionAction() {

        final String customUserInfoName = NamingUtils.generateNewNameCaseInsensitive(
                getLowerCaseExistingCustomerUserInfoName(),
                Messages.defaultCustomUserInformationName);
        final String customUserInfoDescription = "";

        // add new CustomUserInfoDefinition
        final CustomUserInfoDefinition customUserInfo = OrganizationFactory.eINSTANCE.createCustomUserInfoDefinition();
        customUserInfo.setName(customUserInfoName);
        customUserInfo.setDescription(customUserInfoDescription);
        customUserInfoObservableList.add(customUserInfo);
        customUserInfoTable.setSelection(new StructuredSelection(customUserInfo));

        // add this new CustomUserInfo as a a CustomUserInfoValue for the User
        for (final User user : organization.getUsers().getUser()) {
            final CustomUserInfoValue newCustomUserInfoValueType = OrganizationFactory.eINSTANCE
                    .createCustomUserInfoValue();
            newCustomUserInfoValueType.setName(customUserInfoName);
            newCustomUserInfoValueType.setValue("");

            if (user.getCustomUserInfoValues() == null) {
                user.setCustomUserInfoValues(OrganizationFactory.eINSTANCE.createCustomUserInfoValuesType());
            }
            user.getCustomUserInfoValues().getCustomUserInfoValue().add(newCustomUserInfoValueType);
        }
    }

    private Set<String> getLowerCaseExistingCustomerUserInfoName() {
        final Set<String> existingCustomUserInfoNames = new HashSet<>();
        if (organization != null) {
            if (organization.getCustomUserInfoDefinitions() == null) {
                organization
                        .setCustomUserInfoDefinitions(OrganizationFactory.eINSTANCE.createCustomUserInfoDefinitions());
            }
            for (final CustomUserInfoDefinition custom : organization.getCustomUserInfoDefinitions()
                    .getCustomUserInfoDefinition()) {
                existingCustomUserInfoNames.add(custom.getName().toLowerCase());
            }
        }
        return existingCustomUserInfoNames;
    }

    protected void setDefaultUserInformationGroup(final Group defaultGroup) {
        defaultGroup.setText(Messages.defaultInformationGroupTitle);
        defaultGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        defaultGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Composite globalComposite = new Composite(defaultGroup, SWT.FILL);
        globalComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).create());
        globalComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Composite tablesComposite = new Composite(globalComposite, SWT.FILL);
        tablesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).equalWidth(true).create());
        tablesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createGeneralDataTable(tablesComposite);
        createBusinessCardTable(tablesComposite);
        createPersonalDataTable(tablesComposite);

        final Composite membershipsComposite = new Composite(globalComposite, SWT.NONE);
        membershipsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        membershipsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createMembershipsTable(membershipsComposite);
    }

    private void createGeneralDataTable(final Composite tables) {
        final Composite generalDataTableComposite = new Composite(tables, SWT.NONE);
        generalDataTableComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        generalDataTableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Table generalDataTable = new Table(generalDataTableComposite, SWT.BORDER);
        generalDataTable.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        generalDataTable.setHeaderVisible(true);

        final String[] generalDataItems = getGeneralDataItems();
        final TableColumn generalData = new TableColumn(generalDataTable, SWT.NONE);
        generalData.setText(Messages.defaultInformationGroupGeneralDataTableTitle);
        generalData.setResizable(false);

        for (int i = 0; i < generalDataItems.length; i++) {
            final TableItem item = new TableItem(generalDataTable, SWT.NONE | SWT.FILL);
            item.setText(generalDataItems[i]);
        }

        addTableColumLayout(generalDataTable);
    }

    private void createBusinessCardTable(final Composite tables) {
        final Composite businessCardTableComposite = new Composite(tables, SWT.NONE);
        businessCardTableComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        businessCardTableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Table businessCardTable = new Table(businessCardTableComposite, SWT.BORDER);
        businessCardTable.setHeaderVisible(true);

        final String[] businessCardTitles = getBusinessCardItems();
        final TableColumn businessCardColumn = new TableColumn(businessCardTable, SWT.NONE);
        businessCardColumn.setText(Messages.defaultInformationGroupBusinessCardTableTitle);
        businessCardColumn.setResizable(false);

        for (int i = 0; i < businessCardTitles.length; i++) {
            final TableItem item = new TableItem(businessCardTable, SWT.NONE);
            item.setText(businessCardTitles[i]);
        }
        addTableColumLayout(businessCardTable);
    }

    private void createPersonalDataTable(final Composite tables) {
        final Composite personalTableComposite = new Composite(tables, SWT.NONE);
        personalTableComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        personalTableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Table personnalTable = new Table(personalTableComposite, SWT.BORDER);
        personnalTable.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        personnalTable.setHeaderVisible(true);

        final String[] personalTitles = getPersonalItems();
        final TableColumn personalColumn = new TableColumn(personnalTable, SWT.NONE);
        personalColumn.setText(Messages.defaultInformationGroupPersonalTableTitle);
        personalColumn.setResizable(false);

        for (int i = 0; i < personalTitles.length; i++) {
            final TableItem item = new TableItem(personnalTable, SWT.NONE);
            item.setText(personalTitles[i]);
        }
        addTableColumLayout(personnalTable);
    }

    private void createMembershipsTable(final Composite memberships) {

        final Table membershipTable = new Table(memberships, SWT.BORDER | SWT.FILL);
        membershipTable.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        membershipTable.setHeaderVisible(true);

        final TableColumn membershipColumn = new TableColumn(membershipTable, SWT.NONE | SWT.FILL);
        membershipColumn.setText(Messages.defaultInformationGroupMembershipsTableTitle);
        membershipColumn.setResizable(false);

        final TableItem descriptionMembership = new TableItem(membershipTable, SWT.NONE);
        descriptionMembership.setText(Messages.defaultInformationGroupMembershipsTableText);
        addTableColumLayout(membershipTable);
    }

    protected String[] getGeneralDataItems() {
        final String[] titles = { Messages.userTitle,
                Messages.firstName,
                Messages.lastName,
                Messages.userName + " *",
                Messages.password + " *",
                Messages.jobLabel,
                Messages.manager };
        return titles;
    }

    protected String[] getBusinessCardItems() {
        final String[] titles = { Messages.emailLabel,
                Messages.phoneLabel,
                Messages.mobileLabel,
                Messages.faxLabel,
                Messages.websiteLabel,
                Messages.buildingLabel,
                Messages.roomLabel,
                Messages.addressLabel,
                Messages.cityLabel,
                Messages.stateLabel,
                Messages.zipCodeLabel,
                Messages.countryLabel };
        return titles;
    }

    protected String[] getPersonalItems() {
        final String[] titles = { Messages.emailLabel,
                Messages.phoneLabel,
                Messages.mobileLabel,
                Messages.faxLabel,
                Messages.websiteLabel,
                Messages.buildingLabel,
                Messages.roomLabel,
                Messages.addressLabel,
                Messages.cityLabel,
                Messages.stateLabel,
                Messages.zipCodeLabel,
                Messages.countryLabel };
        return titles;
    }

    @Override
    public boolean isPageComplete() {
        return super.isPageComplete() || userSingleSelectionObservable.getValue() == null;
    }

    private boolean isNotUserSelected() {
        return userSingleSelectionObservable == null || userSingleSelectionObservable.getValue() == null;
    }

    private void disposeTabItemControl(NativeTabItemWidget item) {
        Control c = item.getControl();
        if (c != null && !c.isDisposed()) {
            c.dispose();
        }
    }
}
